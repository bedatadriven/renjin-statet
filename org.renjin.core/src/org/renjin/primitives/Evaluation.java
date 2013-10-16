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

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.renjin.eval.Calls;
import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.parser.RParser;
import org.renjin.primitives.annotations.Current;
import org.renjin.primitives.annotations.Evaluate;
import org.renjin.primitives.annotations.PassThrough;
import org.renjin.primitives.annotations.Primitive;
import org.renjin.primitives.io.connections.Connection;
import org.renjin.primitives.io.connections.Connections;
import org.renjin.primitives.special.ReturnException;
import org.renjin.sexp.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class Evaluation {



  /**
   * There are no restrictions on name: it can be a non-syntactic name (see make.names).
   *
   * The pos argument can specify the environment in which to assign the object in any
   * of several ways: as an integer (the position in the search list); as the character
   * string name of an element in the search list; or as an environment (including using
   * sys.frame to access the currently active function calls). The envir argument is an
   *  alternative way to specify an environment, but is primarily there for back compatibility.
   *
   * assign does not dispatch assignment methods, so it cannot be used to
   *  set elements of vectors, names, attributes, etc.
   *
   * Note that assignment to an attached list or data frame changes the attached copy
   *  and not the original object: see attach and with.
   */
  @Primitive
  public static SEXP assign(@Current Context context, String name, SEXP value, Environment environ, boolean inherits) {

    Symbol symbol = Symbol.get(name);
    if(!inherits) {
      environ.setVariable(symbol, value);
    } else {
      while(environ != Environment.EMPTY && !environ.hasVariable(symbol)) {
        environ = environ.getParent();
      }
      if(environ == Environment.EMPTY) {
        context.getGlobalEnvironment().setVariable(symbol, value);
      } else {
        environ.setVariable(symbol, value);
      }
    }
    context.setInvisibleFlag();
    return value;
  }

  @Primitive
  public static void delayedAssign(@Current Context context, String x, SEXP expr, Environment evalEnv, Environment assignEnv) {
    assignEnv.setVariable(Symbol.get(x), Promise.repromise(evalEnv, expr));
  }


  /**
   * This is the so-called complex assignment, such as:
   *  class(x) <- "foo" or
   *  length(x) <- 3
   *
   *
   */

  @Primitive("on.exit")
  public static void onExit( @Current Context context, @Evaluate(false) SEXP exp, boolean add ) {
    if(add) {
      context.addOnExit(exp);
    } else {
      context.setOnExit(exp);
    }
  }

  @Primitive
  public static ListVector lapply(@Current Context context, @Current Environment rho, Vector vector,
      Function function) {

    ListVector.Builder builder = ListVector.newBuilder();
    for(int i=0;i!=vector.length();++i) {
      // For historical reasons, the calls created by lapply are unevaluated, and code has
      // been written (e.g. bquote) that relies on this.
      FunctionCall getElementCall = FunctionCall.newCall(Symbol.get("[["), (SEXP)vector, new IntArrayVector(i+1));
      FunctionCall applyFunctionCall = new FunctionCall((SEXP)function, new PairList.Node(getElementCall,
          new PairList.Node(Symbols.ELLIPSES, Null.INSTANCE)));
      builder.add( context.evaluate(applyFunctionCall, rho) );
    }
    builder.copySomeAttributesFrom(vector, Symbols.NAMES);
    return builder.build();
  }

  @Primitive
  public static Vector vapply(@Current Context context, @Current Environment rho, Vector vector,
      Function function, Vector funValue, boolean useNames) {
    
    // Retrieve the additional arguments from the `...` value 
    // in the closure that called us
    PairList extraArgs = (PairList)rho.getVariable(Symbols.ELLIPSES);
    
    Vector.Builder result = funValue.getVectorType().newBuilderWithInitialCapacity(vector.length());
    for(int i=0;i!=vector.length();++i) {

      // build function call 
      PairList.Builder args = new PairList.Builder();
      
      FunctionCall getCall = FunctionCall.newCall(
          Symbol.get("[["), vector, new IntArrayVector(i+1));
      
      args.add(getCall);
      args.addAll(extraArgs);
      FunctionCall call = new FunctionCall(function, args.build());
      
      // evaluate
      SEXP x = context.evaluate(call);
      
      // check the result
      if(!(x instanceof Vector) || 
          x.length() != funValue.length() ||
          ((Vector)x).getVectorType().isWiderThan(funValue)) {
        throw new EvalException("values must be type '%s',\n but %s result is type '%s'",
            funValue.getTypeName(),
            Deparse.deparseExp(context, call),
            x.getTypeName());
            
      }
      for(int j=0;j!=funValue.length();++j) {
        result.addFrom(x, j);
       }
    }
    
    if(useNames) {
      result.setAttribute(Symbols.NAMES, vector.getAttribute(Symbols.NAMES));
    }
    if(funValue.length() != 1) {
      result.setDim(funValue.length(), vector.length());
    }
    
    return result.build();
  }

  public static ListVector mapply(Context context, SEXP f, SEXP varyingArgs, Vector constantArgs, Environment rho) {

    int longest = 0;
    int[] lengths = new int[varyingArgs.length()];
    for(int i = 0; i < varyingArgs.length(); i++){
      lengths[i] = varyingArgs.getElementAsSEXP(i).length();
      if (lengths[i] > longest) {
        longest=lengths[i];
      }
    }

    
    ListVector.Builder result = ListVector.newBuilder();
    
    Symbol doubleBracket = Symbol.get("[[");
    
    for(int i = 0; i<longest; ++i) {
    
      /* build a call
         f(dots[[1]][[4]],dots[[2]][[4]],dots[[3]][[4]],d=7)
      */
      
      PairList.Builder args = new PairList.Builder();
      for(int j = 0; j!=varyingArgs.length();++ j) {
        SEXP arg = varyingArgs.getElementAsSEXP(j);
        args.add(varyingArgs.getName(j),
            FunctionCall.newCall(doubleBracket, arg, IntVector.valueOf( (i % arg.length()) + 1 )));
      }
      if(constantArgs.length() > 0) {
        args.addAll((ListVector)constantArgs);
      }
      result.add(context.evaluate(new FunctionCall(f, args.build()), rho));
    }
       
    return result.build();
  }


  @Primitive("return")
  public static SEXP doReturn(@Current Environment rho, SEXP value) {
    throw new ReturnException(rho, value);
  }

  @Primitive("do.call")
  public static SEXP doCall(@Current Context context, Function what, ListVector arguments, Environment environment) {
    PairList argumentPairList = new PairList.Builder().addAll(arguments).build();
    FunctionCall call = new FunctionCall(what, argumentPairList);
    return context.evaluate(call, environment);
  }

  @Primitive("do.call")
  public static SEXP doCall(@Current Context context, @Current Environment rho, String what, ListVector arguments, Environment environment) {
    SEXP function = environment
            .findVariable(Symbol.get(what))
            .force(context);

    return doCall(context, (Function) function, arguments, environment);
  }

  @PassThrough
  @Primitive
  public static SEXP call(@Current Context context, @Current Environment rho, FunctionCall call) {
    if(call.length() < 1) {
      throw new EvalException("first argument must be character string");
    }
    SEXP name = context.evaluate(call.getArgument(0), rho);
    if(!(name instanceof StringVector) || name.length() != 1) {
      throw new EvalException("first argument must be character string");
    }

    return new FunctionCall(Symbol.get(((StringVector) name).getElementAsString(0)),
        ((PairList.Node)call.getArguments()).getNextNode());
  }

  @Primitive
  public static SEXP eval(@Current Context context,
                                SEXP expression, SEXP environment,
                                SEXP enclosing) {

    Environment rho;
    if(environment instanceof Environment) {
      rho = (Environment) environment;
    } else {
      
      /*
       * If ‘envir’ is ‘NULL’ it is interpreted as an empty list so no
       *    values could be found in ‘envir’ and look-up goes directly to
       * ‘enclos’.
       */
      if(environment == Null.INSTANCE) {
        environment = ListVector.EMPTY;
      }
      
      /* If envir is a list (such as a data frame) or pairlist, it is copied into a temporary environment
       * (with enclosure enclos), and the temporary environment is used for evaluation. So if expr
       * changes any of the components named in the (pair)list, the changes are lost.
       */
      Environment parent = enclosing == Null.INSTANCE ? context.getEnvironment().getBaseEnvironment() :
          EvalException.<Environment>checkedCast(enclosing);

      rho = Environment.createChildEnvironment(parent);

      if(environment instanceof ListVector) {
        for(NamedValue namedValue : ((ListVector) environment).namedValues()) {
          if(!StringVector.isNA(namedValue.getName())) {
            rho.setVariable(Symbol.get(namedValue.getName()), namedValue.getValue());
          }
        }
      } else {
        throw new EvalException("invalid 'environ' argument: " + environment);
      }
    }

    // we need to create a new context for the evaluated code, otherwise sys.parent 
    // calls and the like will not be able to access this root environment of the script
    
    Context evalContext = context.beginEvalContext(rho);
    
    SEXP result = evalContext.evaluate( expression, rho);
    
    evalContext.exit();
    
    return result;
  }
  
  
  /**
   * Evaluates the expression and then packs it into a named ListVector
   * containing the value and the visibility flag
   */
  @Primitive("eval.with.vis")
  public static SEXP evalWithVis(@Current Context context,
      SEXP expression, SEXP environment,
      SEXP enclosing) {
    
    SEXP result = eval(context, expression, environment, enclosing);
    ListVector.NamedBuilder list = new ListVector.NamedBuilder();
    list.add("value", result);
    list.add("visible", context.getSession().isInvisible());
    return list.build();
  }

  @Primitive
  public static SEXP quote(@Evaluate(false) SEXP exp) {
    return exp;
  }
  
  @Primitive
  public static boolean missing(@Current Context context, @Current Environment rho, @Evaluate(false) Symbol symbol) {
    SEXP value = rho.findVariable(symbol);
    
    if(value == Symbol.UNBOUND_VALUE) {
      throw new EvalException("'missing' can only be used for arguments");
    } else if(value == Symbol.MISSING_ARG) {
      return true;
    } else if(isPromisedMissingArg(value)) {
      return true;
    } 
    
    
    // we need to rematch the arguments to determine whether the value was actually provided
    // or whether 'value' contains the default value
    //
    // this seems quite expensive, perhaps there's a faster way?
    PairList rematched = Calls.matchArguments(
        Calls.stripDefaultValues(context.getClosure().getFormals()),
        context.getCall().getArguments());
    SEXP providedValue = rematched.findByTag(symbol);

    return providedValue == Symbol.MISSING_ARG;
    //return false;
    
  }


  private static boolean isPromisedMissingArg(SEXP exp) {
    if(exp instanceof Promise) {
      Promise promise = (Promise)exp;
      if(!promise.isEvaluated() && promise.getExpression() instanceof Symbol) {
        Symbol argumentName = (Symbol) promise.getExpression();
        SEXP argumentValue = promise.getEnvironment().getVariable(argumentName);
        if(argumentValue == Symbol.MISSING_ARG) {
          return true;          
        } else if(isPromisedMissingArg(argumentValue)) {
          return true;
        }
      }
    } 
    return false;
  }


  @Primitive
  public static ExpressionVector parse(@Current Context context, SEXP file, SEXP maxExpressions, Vector text,
                                       String prompt, SEXP sourceFile, String encoding) throws IOException {
    List<SEXP> expressions = Lists.newArrayList();
    if(text != Null.INSTANCE) {
      for(int i=0;i!=text.length();++i) {
        String line = text.getElementAsString(i);
        try {
          ExpressionVector result = RParser.parseSource(new StringReader(line + "\n"));
          Iterables.addAll(expressions, result);
        } catch (IOException e) {
          throw new EvalException("I/O Exception occurred during parse: " + e.getMessage());
        }
      }
    } else if(file.inherits("connection")) {
      Connection conn = Connections.getConnection(context, file);
      Reader reader = new InputStreamReader(conn.getInputStream());
      ExpressionVector result = RParser.parseSource(reader);
      Iterables.addAll(expressions, result);
    }

    return new ExpressionVector(expressions);
  }

  public static int nargs(@Current Context context) {
    return context.getArguments().length();
  }
  
  @Primitive(".Primitive")
  public static PrimitiveFunction getPrimitive(String name) {
    PrimitiveFunction fn = Primitives.getBuiltin(Symbol.get(name));
    if(fn == null) {
      throw new EvalException("No such primitive function");
    }
    return fn;
  }

  @Primitive
  public static void remove(StringVector names, Environment envir, boolean inherits) {
    if(inherits) {
      throw new EvalException("remove(inherits=TRUE) is not yet implemented");
    }
    for(String name : names) {
      envir.remove(Symbol.get(name));
    }
  }
}
