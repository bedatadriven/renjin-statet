/*
 * R : A Computer Language for Statistical Data Analysis
 * Copyright (C) 1995, 1996  Robert Gentleman and Ross Ihaka
 * Copyright (C) 1997-2008  The R Development Core Team
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

package org.renjin.sexp;

import org.renjin.eval.Context;


public abstract class BuiltinFunction extends PrimitiveFunction {

  public static final String TYPE_NAME = "builtin";
  public static final String IMPLICIT_CLASS = "function";
  
  private final String name;
  
  public BuiltinFunction(String name) {
    this.name = name;
  }
  
  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public final String getTypeName() {
    return TYPE_NAME;
  }

  @Override
  public final String getImplicitClass() {
    return IMPLICIT_CLASS;
  }

  @Override
  public final void accept(SexpVisitor visitor) {
    visitor.visit(this);
  }

  /**
   * Applies this {@code BuiltinFunction} to the given the
   * @param context the runtime context in which to evaluate this function
   * @param call the original function call
   * @param argumentNames the names of the arguments
   * @param arguments the <b><i>evaluated</i></b> arguments
   * @return the result of the function
   */
  public SEXP apply(Context context, Environment rho, FunctionCall call, String[] argumentNames,
      SEXP arguments[]) {
    throw new UnsupportedOperationException(getName());
  }
}
