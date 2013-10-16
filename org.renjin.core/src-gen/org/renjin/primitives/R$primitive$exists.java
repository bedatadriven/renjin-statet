
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
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Symbols;
import org.renjin.sexp.Vector;

public class R$primitive$exists
    extends BuiltinFunction
{


    public R$primitive$exists() {
        super("exists");
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
            throw new EvalException("exists: too many arguments, expected at most 4.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\texists(character, Environment, character, logical)", e.getMessage());
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
        throw new EvalException("exists: max arity is 4");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$exists.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3)
        throws Exception
    {
        if (((((arg0 .length() == 0)||((arg0 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0))))&&(arg1 instanceof Environment))&&((arg2 .length() == 0)||((arg2 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg2)))))&&((arg3 .length() == 0)||(arg3 instanceof Vector))) {
            Vector vector1 = ((Vector) WrapperRuntime.convertToVector(arg0));
            int length1 = vector1 .length();
            int currentElementIndex1 = 0;
            Vector vector3 = ((Vector) WrapperRuntime.convertToVector(arg2));
            int length3 = vector3 .length();
            int currentElementIndex3 = 0;
            Vector vector4 = ((Vector) WrapperRuntime.convertToVector(arg3));
            int length4 = vector4 .length();
            int currentElementIndex4 = 0;
            int cycles = 0;
            if (length1 == 0) {
                return LogicalVector.EMPTY;
            }
            if (length1 >cycles) {
                cycles = length1;
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
            org.renjin.sexp.LogicalArrayVector.Builder builder = new org.renjin.sexp.LogicalArrayVector.Builder(cycles);
            for (int i = 0; (i!= cycles); i ++) {
                if ((vector1 .isElementNA(currentElementIndex1)||vector3 .isElementNA(currentElementIndex3))||vector4 .isElementNA(currentElementIndex4)) {
                    builder.setNA(i);
                } else {
                    builder.set(i, Types.exists(context, vector1 .getElementAsString(currentElementIndex1), ((Environment) arg1), vector3 .getElementAsString(currentElementIndex3), vector4 .isElementTrue(currentElementIndex4)));
                }
                currentElementIndex1 += 1;
                if (currentElementIndex1 == length1) {
                    currentElementIndex1 = 0;
                }
                currentElementIndex3 += 1;
                if (currentElementIndex3 == length3) {
                    currentElementIndex3 = 0;
                }
                currentElementIndex4 += 1;
                if (currentElementIndex4 == length4) {
                    currentElementIndex4 = 0;
                }
            }
            if (length4 == cycles) {
                builder.copySomeAttributesFrom(vector4, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            if (length3 == cycles) {
                builder.copySomeAttributesFrom(vector3, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            if (length1 == cycles) {
                builder.copySomeAttributesFrom(vector1, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            return builder.build();
        } else {
            throw new EvalException(String.format("Invalid argument:\n\texists(%s, %s, %s, %s)\n\tExpected:\n\texists(character, Environment, character, logical)", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName()));
        }
    }

}
