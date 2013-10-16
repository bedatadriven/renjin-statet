
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.IntVector;
import org.renjin.sexp.LogicalVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Symbols;
import org.renjin.sexp.Vector;

public class R$primitive$choose
    extends BuiltinFunction
{


    public R$primitive$choose() {
        super("choose");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            SEXP s1 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1);
            }
            throw new EvalException("choose: too many arguments, expected at most 2.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tchoose(double, integer)", e.getMessage());
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
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("choose: max arity is 2");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$choose.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1)
        throws Exception
    {
        if (((arg0 .length() == 0)||((arg0 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0))))&&((arg1 .length() == 0)||(((arg1 instanceof IntVector)||(arg1 instanceof DoubleVector))||(arg1 instanceof LogicalVector)))) {
            Vector vector0 = ((Vector) WrapperRuntime.convertToVector(arg0));
            int length0 = vector0 .length();
            int currentElementIndex0 = 0;
            Vector vector1 = ((Vector) WrapperRuntime.convertToVector(arg1));
            int length1 = vector1 .length();
            int currentElementIndex1 = 0;
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
            org.renjin.sexp.DoubleArrayVector.Builder builder = new org.renjin.sexp.DoubleArrayVector.Builder(cycles);
            for (int i = 0; (i!= cycles); i ++) {
                if (vector0 .isElementNA(currentElementIndex0)||vector1 .isElementNA(currentElementIndex1)) {
                    builder.setNA(i);
                } else {
                    builder.set(i, MathExt.choose(vector0 .getElementAsDouble(currentElementIndex0), vector1 .getElementAsInt(currentElementIndex1)));
                }
                currentElementIndex0 += 1;
                if (currentElementIndex0 == length0) {
                    currentElementIndex0 = 0;
                }
                currentElementIndex1 += 1;
                if (currentElementIndex1 == length1) {
                    currentElementIndex1 = 0;
                }
            }
            if (length1 == cycles) {
                builder.copySomeAttributesFrom(vector1, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            if (length0 == cycles) {
                builder.copySomeAttributesFrom(vector0, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            return builder.build();
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tchoose(%s, %s)\n\tExpected:\n\tchoose(double, integer)", arg0 .getTypeName(), arg1 .getTypeName()));
        }
    }

}
