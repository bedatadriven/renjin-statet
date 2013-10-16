
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

public class R$primitive$inherits
    extends BuiltinFunction
{


    public R$primitive$inherits() {
        super("inherits");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            SEXP s1 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1);
            }
            SEXP s2 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2);
            }
            throw new EvalException("inherits: too many arguments, expected at most 3.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tinherits(any, character, logical(1))\n\tinherits(any, character)\n\tinherits(any, character)", e.getMessage());
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
            if ((args.length) == 2) {
                return doApply(context, environment, args[ 0 ], args[ 1 ]);
            }
            if ((args.length) == 3) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("inherits: max arity is 3");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$inherits.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1)
        throws Exception
    {
        if ((arg0 instanceof SEXP)&&(arg1 instanceof StringVector)) {
            return WrapperRuntime.wrapResult(Attributes.inherits(((SEXP) arg0), ((StringVector) arg1)));
        } else {
            if ((arg0 instanceof SEXP)&&((arg1 .length() == 0)||((arg1 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg1))))) {
                Vector vector1 = ((Vector) WrapperRuntime.convertToVector(arg1));
                int length1 = vector1 .length();
                int currentElementIndex1 = 0;
                int cycles = 0;
                if (length1 == 0) {
                    return LogicalVector.EMPTY;
                }
                if (length1 >cycles) {
                    cycles = length1;
                }
                org.renjin.sexp.LogicalArrayVector.Builder builder = new org.renjin.sexp.LogicalArrayVector.Builder(cycles);
                for (int i = 0; (i!= cycles); i ++) {
                    if (vector1 .isElementNA(currentElementIndex1)) {
                        builder.setNA(i);
                    } else {
                        builder.set(i, Attributes.inherits(((SEXP) arg0), vector1 .getElementAsString(currentElementIndex1)));
                    }
                    currentElementIndex1 += 1;
                    if (currentElementIndex1 == length1) {
                        currentElementIndex1 = 0;
                    }
                }
                if (length1 == cycles) {
                    builder.copySomeAttributesFrom(vector1, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
                }
                return builder.build();
            } else {
                throw new EvalException(String.format("Invalid argument:\n\tinherits(%s, %s)\n\tExpected:\n\tinherits(any, character, logical(1))\n\tinherits(any, character)\n\tinherits(any, character)", arg0 .getTypeName(), arg1 .getTypeName()));
            }
        }
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2)
        throws Exception
    {
        if (((arg0 instanceof SEXP)&&(arg1 instanceof StringVector))&&(arg2 instanceof Vector)) {
            return Attributes.inherits(((SEXP) arg0), ((StringVector) arg1), WrapperRuntime.convertToBooleanPrimitive(arg2));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tinherits(%s, %s, %s)\n\tExpected:\n\tinherits(any, character, logical(1))\n\tinherits(any, character)\n\tinherits(any, character)", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName()));
        }
    }

}
