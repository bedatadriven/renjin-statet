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
import org.renjin.primitives.annotations.PassThrough;
import org.renjin.primitives.annotations.Primitive;
import org.renjin.sexp.*;


public class Comparison {

  private Comparison() { }


  /**
   * The logical || operator reqires the implementation use minimal evaluation,
   * therefore we cannot use the overloaded function calls as is standard.
   *
   * Comparing doubles or booleans works as generally expected. Comparing two vectors
   * will only compare the first element in each vector.
   */
  @PassThrough
  @Primitive("||")
  public static LogicalVector or(Context context, Environment rho, FunctionCall call) {

    Logical x = checkedToLogical(context.evaluate(call.getArgument(0), rho), "invalid 'x' type in 'x || y'");

    if(x == Logical.TRUE) {
      return LogicalVector.TRUE;
    }

    Logical y = checkedToLogical(context.evaluate(call.getArgument(1), rho), "invalid 'y' type in 'x || y'");
    if(y == Logical.TRUE) {
      return LogicalVector.TRUE;
    }

    if(x == Logical.NA || y == Logical.NA) {
      return LogicalVector.NA_VECTOR;
    } else {
      return LogicalVector.FALSE;
    }
  }

  /**
   * The logical && operator requires the implementation use minimal evaluation,
   * therefore we cannot use the overloaded function calls as is standard.
   *
   * Comparing doubles or booleans works as generally expected. Comparing two vectors
   * will only compare the first element in each vector.
   */
  @PassThrough
  @Primitive("&&")
  public static LogicalVector and(Context context, Environment rho, FunctionCall call) {

    Logical x = checkedToLogical(context.evaluate(call.getArgument(0), rho), "invalid 'x' type in 'x && y'");

    if(x == Logical.FALSE) {
      return LogicalVector.FALSE;
    }

    Logical y = checkedToLogical(context.evaluate(call.getArgument(1), rho), "invalid 'y' type in 'x && y'");

    if(y == Logical.FALSE) {
      return LogicalVector.FALSE;
    } else if(x == Logical.TRUE && y == Logical.TRUE) {
      return LogicalVector.TRUE;
    } else {
      return LogicalVector.NA_VECTOR;
    }
  }


  private static Logical checkedToLogical(SEXP exp, String errorMessage) {
    if(exp instanceof AtomicVector) {
      AtomicVector vector = (AtomicVector) exp;
      if(vector.length() > 0) {
        return vector.getElementAsLogical(0);
      }
    }
    throw new EvalException(errorMessage);
  }
}
