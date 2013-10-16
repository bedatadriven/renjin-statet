
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.text.Text;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.IntVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Symbols;
import org.renjin.sexp.Vector;

public class R$primitive$nchar
    extends BuiltinFunction
{


    public R$primitive$nchar() {
        super("nchar");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            SEXP s1 = argIt.evalNext();
            SEXP s2 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2);
            }
            throw new EvalException("nchar: too many arguments, expected at most 3.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tnchar(character, character(1), logical(1))", e.getMessage());
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
        throw new EvalException("nchar: max arity is 3");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$nchar.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2)
        throws Exception
    {
        if ((((arg0 .length() == 0)||((arg0 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0))))&&((arg1 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg1))))&&(arg2 instanceof Vector)) {
            Vector vector0 = ((Vector) WrapperRuntime.convertToVector(arg0));
            int length0 = vector0 .length();
            int currentElementIndex0 = 0;
            int cycles = 0;
            if (length0 == 0) {
                return IntVector.EMPTY;
            }
            if (length0 >cycles) {
                cycles = length0;
            }
            org.renjin.sexp.IntArrayVector.Builder builder = new org.renjin.sexp.IntArrayVector.Builder(cycles);
            for (int i = 0; (i!= cycles); i ++) {
                builder.set(i, Text.nchar(vector0 .getElementAsString(currentElementIndex0), WrapperRuntime.convertToString(arg1), WrapperRuntime.convertToBooleanPrimitive(arg2)));
                currentElementIndex0 += 1;
                if (currentElementIndex0 == length0) {
                    currentElementIndex0 = 0;
                }
            }
            if (length0 == cycles) {
                builder.copySomeAttributesFrom(vector0, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            return builder.build();
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tnchar(%s, %s, %s)\n\tExpected:\n\tnchar(character, character(1), logical(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName()));
        }
    }

}
