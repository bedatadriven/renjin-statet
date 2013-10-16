/*
 * R : A Computer Language for Statistical Data Analysis
 * Copyright (C) 1995, 1996  Robert Gentleman and Ross Ihaka
 * Copyright (C) 1997--2008  The R Development Core Team
 * Copyright (C) 2003, 2004  The R Foundation
 * Copyright (C) 2010 bedatadriven
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.Current;
import org.renjin.primitives.annotations.Primitive;
import org.renjin.sexp.*;

/**
 * These functions provide a mechanism for handling unusual conditions,
 * including errors and warnings.
 *
 * <p>The tryCatch mechanism is similar to Java error handling.
 * Calling handlers are based on Common Lisp and Dylan.
 * Restarts are based on the Common Lisp restart mechanism.</p>
 *
 * <p>The condition system provides a mechanism for signaling and handling unusual conditions, including errors and
 * warnings. Conditions are represented as objects that contain information about the condition that occurred,
 * such as a message and the call in which the condition occurred. Currently conditions are S3-style objects,
 * though this may eventually change.</p>
 *
 * <p>Conditions are objects inheriting from the abstract class condition. Errors and warnings are objects inheriting
 * from the abstract subclasses error and warning. The class simpleError is the class used by stop and all internal
 * error signals. Similarly, simpleWarning is used by warning, and simpleMessage is used by message. The constructors
 * by the same names take a string describing the condition as argument and an optional call. The functions
 * conditionMessage and conditionCall are generic functions that return the message and call of a condition.</p>
 *
 * <p>Conditions are signaled by signalCondition. In addition, the stop and warning functions have been modified
 * to also accept condition arguments.</p>
 *
 * <p>The function tryCatch evaluates its expression argument in a context where the handlers provided in the ...
 * argument are available. The finally expression is then evaluated in the context in which tryCatch was called;
 * that is, the handlers supplied to the current tryCatch call are not active when the finally expression is evaluated.
 *
 * <p>Handlers provided in the ... argument to tryCatch are established for the duration of the evaluation of expr.
 * If no condition is signaled when evaluating expr then tryCatch returns the value of the expression.
 *
 * <p>If a condition is signaled while evaluating expr then established handlers are checked, starting with
 * the most recently established ones, for one matching the class of the condition. When several handlers are
 * supplied in a single tryCatch then the first one is considered more recent than the second. If a handler is
 * found then control is transferred to the tryCatch call that established the handler, the handler found and all
 *  more recent handlers are disestablished, the handler is called with the condition as its argument, and the
 *  result returned by the handler is returned as the value of the tryCatch call.
 *
 * <p>Calling handlers are established by withCallingHandlers. If a condition is signaled and the applicable
 * handler is a calling handler, then the handler is called by signalCondition in the context where the
 *  condition was signaled but with the available handlers restricted to those below the handler called
 * in the handler stack. If the handler returns, then the next handler is tried; once the last handler
 * has been tried, signalCondition returns NULL.
 *
 * <p>User interrupts signal a condition of class interrupt that inherits directly from class condition
 * before executing the default interrupt action.
 *
 * <p>Restarts are used for establishing recovery protocols. They can be established using withRestarts.
 * One pre-established restart is an abort restart that represents a jump to top level.
 *
 * <p>findRestart and computeRestarts find the available restarts. findRestart returns the most recently
 * established restart of the specified name. computeRestarts returns a list of all restarts. Both can
 * be given a condition argument and will then ignore restarts that do not apply to the condition.
 *
 * <p>invokeRestart transfers control to the point where the specified restart was established and calls
 * the restart's handler with the arguments, if any, given as additional arguments to invokeRestart.
 * The restart argument to invokeRestart can be a character string, in which case findRestart is used to
 * find the restart.
 *
 * <p>New restarts for withRestarts can be specified in several ways. The simplest is in name=function form where
 * the function is the handler to call when the restart is invoked. Another simple variant is as name=string
 * where the string is stored in the description field of the restart object returned by findRestart; in this
 * case the handler ignores its arguments and returns NULL. The most flexible form of a restart specification is
 * as a list that can include several fields, including handler, description, and test. The test field should
 * contain a function of one argument, a condition, that returns TRUE if the restart applies to the condition
 * and FALSE if it does not; the default function returns TRUE for all conditions.
 *
 * <p>One additional field that can be specified for a restart is interactive. This should be a function of no
 * arguments that returns a list of arguments to pass to the restart handler. The list could be obtained by
 *  interacting with the user if necessary. The function invokeRestartInteractively calls this function to
 * obtain the arguments to use when invoking the restart. The default interactive method queries the user
 * for values for the formal arguments of the handler function.
 *
 */
public class Conditions {

  private Conditions() {}

  public static class ErrorMessage {
    private String value;

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    } 
  }

  /**
   * Conditions are somewhat analogous to exceptions in the JVM, while handlers
   * are like catch clauses.
   *
   * This internal function registers a new handler in the context for conditions
   * of a given class.
   *
   * @param context
   * @param classes the S3 conditions classes to be handled
   * @param handlers the functions which
   * @param parentEnv 
   * @param target not clear-- doesn't see to be used anymore
   * @param calling
   * @return
   */
  @Primitive(".addCondHands")
  public static void addConditionHandlers(@Current Context context,
                                          StringVector classes,
                                          ListVector handlers,
                                          Environment parentEnv,
                                          SEXP target,
                                          LogicalVector calling) {

    if(classes.length() != handlers.length()) {
      throw new EvalException("bad handler data");
    }

    int n = handlers.length();

    for (int i = n - 1; i >= 0; i--) {
      context.setConditionHandler(classes.getElementAsString(i),
          Promise.repromise(parentEnv, handlers.getElementAsSEXP(i)));
    }
  }

  @Primitive(".addRestart")
  public static void addRestart(SEXP restart) {
    // TODO : implement
  }


  @Primitive(".signalCondition")
  public static void signalCondition(@Current Context context, SEXP condition, String message, SEXP call) {

    StringVector conditionClasses = condition.getS3Class();

    while(!context.isTopLevel()) {
      for(String conditionClass : conditionClasses) {
        SEXP handler = context.getConditionHandler(conditionClass);
        if(handler != null) {
          FunctionCall handlerCall = FunctionCall.newCall(handler, condition);
          context.evaluate(handlerCall);
          return;
        }
      }

      context = context.getParent();
    }
  }
  
  @Primitive(".dfltStop")
  public static void defaultStop(@Current Context context, String message, FunctionCall call) {
    EvalException e = new EvalException(message);
    e.initContext(context);
    throw e;
  }
  
  @Primitive(".dfltStop")
  public static void defaultStop(@Current Context context, String message, Null nz) {
    EvalException e = new EvalException(message);
    e.initContext(context);
    throw e;
  }
  
  @Primitive
  public static void stop(@Current Context context, boolean call, String message) {
    throw new EvalException(message);
  }
  
  @Primitive
  public static String geterrmessage(@Current Context context) {
    ErrorMessage errorMessage = context.getSession().getSingleton(ErrorMessage.class);
    return errorMessage.getValue();
  }
  
  @Primitive
  public static void seterrmessage(@Current Context context, String message) {
    ErrorMessage errorMessage = context.getSession().getSingleton(ErrorMessage.class);
    errorMessage.setValue(message);
  }
}
