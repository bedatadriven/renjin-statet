
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.LogicalVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Symbols;
import org.renjin.sexp.Vector;

public class R$primitive$identical
    extends BuiltinFunction
{


    public R$primitive$identical() {
        super("identical");
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
            throw new EvalException("identical: too many arguments, expected at most 6.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tidentical(any, any, logical, logical, logical, logical)", e.getMessage());
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
        throw new EvalException("identical: max arity is 6");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$identical.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4, SEXP arg5)
        throws Exception
    {
        if ((((((arg0 instanceof SEXP)&&(arg1 instanceof SEXP))&&((arg2 .length() == 0)||(arg2 instanceof Vector)))&&((arg3 .length() == 0)||(arg3 instanceof Vector)))&&((arg4 .length() == 0)||(arg4 instanceof Vector)))&&((arg5 .length() == 0)||(arg5 instanceof Vector))) {
            Vector vector2 = ((Vector) WrapperRuntime.convertToVector(arg2));
            int length2 = vector2 .length();
            int currentElementIndex2 = 0;
            Vector vector3 = ((Vector) WrapperRuntime.convertToVector(arg3));
            int length3 = vector3 .length();
            int currentElementIndex3 = 0;
            Vector vector4 = ((Vector) WrapperRuntime.convertToVector(arg4));
            int length4 = vector4 .length();
            int currentElementIndex4 = 0;
            Vector vector5 = ((Vector) WrapperRuntime.convertToVector(arg5));
            int length5 = vector5 .length();
            int currentElementIndex5 = 0;
            int cycles = 0;
            if (length2 == 0) {
                return LogicalVector.EMPTY;
            }
            if (length2 >cycles) {
                cycles = length2;
            }
            if (length3 == 0) {
                return LogicalVector.EMPTY;
            }
            if (length3 >cycles) {
                cycles = length3;
            }
            if (length4 == 0) {
                return LogicalVector.EMPTY;
            }
            if (length4 >cycles) {
                cycles = length4;
            }
            if (length5 == 0) {
                return LogicalVector.EMPTY;
            }
            if (length5 >cycles) {
                cycles = length5;
            }
            org.renjin.sexp.LogicalArrayVector.Builder builder = new org.renjin.sexp.LogicalArrayVector.Builder(cycles);
            for (int i = 0; (i!= cycles); i ++) {
                if (((vector2 .isElementNA(currentElementIndex2)||vector3 .isElementNA(currentElementIndex3))||vector4 .isElementNA(currentElementIndex4))||vector5 .isElementNA(currentElementIndex5)) {
                    builder.setNA(i);
                } else {
                    builder.set(i, Types.identical(((SEXP) arg0), ((SEXP) arg1), vector2 .isElementTrue(currentElementIndex2), vector3 .isElementTrue(currentElementIndex3), vector4 .isElementTrue(currentElementIndex4), vector5 .isElementTrue(currentElementIndex5)));
                }
                currentElementIndex2 += 1;
                if (currentElementIndex2 == length2) {
                    currentElementIndex2 = 0;
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
            if (length2 == cycles) {
                builder.copySomeAttributesFrom(vector2, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            return builder.build();
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tidentical(%s, %s, %s, %s, %s, %s)\n\tExpected:\n\tidentical(any, any, logical, logical, logical, logical)", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName(), arg5 .getTypeName()));
        }
    }

}
