
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.optimize.Optimizations;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.Environment;
import org.renjin.sexp.Function;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Symbols;
import org.renjin.sexp.Vector;

public class R$primitive$fmin
    extends BuiltinFunction
{


    public R$primitive$fmin() {
        super("fmin");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            SEXP s1 = argIt.evalNext();
            SEXP s2 = argIt.evalNext();
            SEXP s3 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2, s3);
            }
            throw new EvalException("fmin: too many arguments, expected at most 4.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tfmin(Function, double, double, double)", e.getMessage());
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
    }

    public static SEXP doApply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        try {
            if ((args.length) == 4) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ], args[ 3 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("fmin: max arity is 4");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$fmin.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3)
        throws Exception
    {
        if ((((arg0 instanceof Function)&&((arg1 .length() == 0)||((arg1 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg1)))))&&((arg2 .length() == 0)||((arg2 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg2)))))&&((arg3 .length() == 0)||((arg3 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg3))))) {
            Vector vector3 = ((Vector) WrapperRuntime.convertToVector(arg1));
            int length3 = vector3 .length();
            int currentElementIndex3 = 0;
            Vector vector4 = ((Vector) WrapperRuntime.convertToVector(arg2));
            int length4 = vector4 .length();
            int currentElementIndex4 = 0;
            Vector vector5 = ((Vector) WrapperRuntime.convertToVector(arg3));
            int length5 = vector5 .length();
            int currentElementIndex5 = 0;
            int cycles = 0;
            if (length3 == 0) {
                return DoubleVector.EMPTY;
            }
            if (length3 >cycles) {
                cycles = length3;
            }
            if (length4 == 0) {
                return DoubleVector.EMPTY;
            }
            if (length4 >cycles) {
                cycles = length4;
            }
            if (length5 == 0) {
                return DoubleVector.EMPTY;
            }
            if (length5 >cycles) {
                cycles = length5;
            }
            org.renjin.sexp.DoubleArrayVector.Builder builder = new org.renjin.sexp.DoubleArrayVector.Builder(cycles);
            for (int i = 0; (i!= cycles); i ++) {
                if ((vector3 .isElementNA(currentElementIndex3)||vector4 .isElementNA(currentElementIndex4))||vector5 .isElementNA(currentElementIndex5)) {
                    builder.setNA(i);
                } else {
                    builder.set(i, Optimizations.fmin(context, environment, ((Function) arg0), vector3 .getElementAsDouble(currentElementIndex3), vector4 .getElementAsDouble(currentElementIndex4), vector5 .getElementAsDouble(currentElementIndex5)));
                }
                currentElementIndex3 += 1;
                if (currentElementIndex3 == length3) {
                    currentElementIndex3 = 0;
                }
                currentElementIndex4 += 1;
                if (currentElementIndex4 == length4) {
                    currentElementIndex4 = 0;
                }
                currentElementIndex5 += 1;
                if (currentElementIndex5 == length5) {
                    currentElementIndex5 = 0;
                }
            }
            if (length5 == cycles) {
                builder.copySomeAttributesFrom(vector5, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            if (length4 == cycles) {
                builder.copySomeAttributesFrom(vector4, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            if (length3 == cycles) {
                builder.copySomeAttributesFrom(vector3, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            return builder.build();
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tfmin(%s, %s, %s, %s)\n\tExpected:\n\tfmin(Function, double, double, double)", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName()));
        }
    }

}
