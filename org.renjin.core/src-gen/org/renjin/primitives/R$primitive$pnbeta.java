
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.random.Distributions;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Symbols;
import org.renjin.sexp.Vector;

public class R$primitive$pnbeta
    extends BuiltinFunction
{


    public R$primitive$pnbeta() {
        super("pnbeta");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            SEXP s1 = argIt.evalNext();
            SEXP s2 = argIt.evalNext();
            SEXP s3 = argIt.evalNext();
            SEXP s4 = argIt.evalNext();
            SEXP s5 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2, s3, s4, s5);
            }
            throw new EvalException("pnbeta: too many arguments, expected at most 6.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tpnbeta(double, double, double, double, logical(1), logical(1))", e.getMessage());
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
            if ((args.length) == 6) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ], args[ 3 ], args[ 4 ], args[ 5 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("pnbeta: max arity is 6");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$pnbeta.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4, SEXP arg5)
        throws Exception
    {
        if (((((((arg0 .length() == 0)||((arg0 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0))))&&((arg1 .length() == 0)||((arg1 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg1)))))&&((arg2 .length() == 0)||((arg2 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg2)))))&&((arg3 .length() == 0)||((arg3 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg3)))))&&(arg4 instanceof Vector))&&(arg5 instanceof Vector)) {
            Vector vector0 = ((Vector) WrapperRuntime.convertToVector(arg0));
            int length0 = vector0 .length();
            int currentElementIndex0 = 0;
            Vector vector1 = ((Vector) WrapperRuntime.convertToVector(arg1));
            int length1 = vector1 .length();
            int currentElementIndex1 = 0;
            Vector vector2 = ((Vector) WrapperRuntime.convertToVector(arg2));
            int length2 = vector2 .length();
            int currentElementIndex2 = 0;
            Vector vector3 = ((Vector) WrapperRuntime.convertToVector(arg3));
            int length3 = vector3 .length();
            int currentElementIndex3 = 0;
            int cycles = 0;
            if (length0 == 0) {
                return DoubleVector.EMPTY;
            }
            if (length0 >cycles) {
                cycles = length0;
            }
            if (length1 == 0) {
                return DoubleVector.EMPTY;
            }
            if (length1 >cycles) {
                cycles = length1;
            }
            if (length2 == 0) {
                return DoubleVector.EMPTY;
            }
            if (length2 >cycles) {
                cycles = length2;
            }
            if (length3 == 0) {
                return DoubleVector.EMPTY;
            }
            if (length3 >cycles) {
                cycles = length3;
            }
            org.renjin.sexp.DoubleArrayVector.Builder builder = new org.renjin.sexp.DoubleArrayVector.Builder(cycles);
            for (int i = 0; (i!= cycles); i ++) {
                if (((vector0 .isElementNA(currentElementIndex0)||vector1 .isElementNA(currentElementIndex1))||vector2 .isElementNA(currentElementIndex2))||vector3 .isElementNA(currentElementIndex3)) {
                    builder.setNA(i);
                } else {
                    builder.set(i, Distributions.pnbeta(vector0 .getElementAsDouble(currentElementIndex0), vector1 .getElementAsDouble(currentElementIndex1), vector2 .getElementAsDouble(currentElementIndex2), vector3 .getElementAsDouble(currentElementIndex3), WrapperRuntime.convertToBooleanPrimitive(arg4), WrapperRuntime.convertToBooleanPrimitive(arg5)));
                }
                currentElementIndex0 += 1;
                if (currentElementIndex0 == length0) {
                    currentElementIndex0 = 0;
                }
                currentElementIndex1 += 1;
                if (currentElementIndex1 == length1) {
                    currentElementIndex1 = 0;
                }
                currentElementIndex2 += 1;
                if (currentElementIndex2 == length2) {
                    currentElementIndex2 = 0;
                }
                currentElementIndex3 += 1;
                if (currentElementIndex3 == length3) {
                    currentElementIndex3 = 0;
                }
            }
            if (length3 == cycles) {
                builder.copySomeAttributesFrom(vector3, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            if (length2 == cycles) {
                builder.copySomeAttributesFrom(vector2, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            if (length1 == cycles) {
                builder.copySomeAttributesFrom(vector1, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            if (length0 == cycles) {
                builder.copySomeAttributesFrom(vector0, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            return builder.build();
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tpnbeta(%s, %s, %s, %s, %s, %s)\n\tExpected:\n\tpnbeta(double, double, double, double, logical(1), logical(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName(), arg5 .getTypeName()));
        }
    }

}
