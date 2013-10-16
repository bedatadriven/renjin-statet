
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
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Symbols;
import org.renjin.sexp.Vector;

public class R$primitive$gsub
    extends BuiltinFunction
{


    public R$primitive$gsub() {
        super("gsub");
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
            SEXP s6 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2, s3, s4, s5, s6);
            }
            throw new EvalException("gsub: too many arguments, expected at most 7.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tgsub(character(1), character(1), character, logical(1), logical(1), logical(1), logical(1))", e.getMessage());
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
            if ((args.length) == 7) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ], args[ 3 ], args[ 4 ], args[ 5 ], args[ 6 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("gsub: max arity is 7");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$gsub.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4, SEXP arg5, SEXP arg6)
        throws Exception
    {
        if ((((((((arg0 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0)))&&((arg1 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg1))))&&((arg2 .length() == 0)||((arg2 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg2)))))&&(arg3 instanceof Vector))&&(arg4 instanceof Vector))&&(arg5 instanceof Vector))&&(arg6 instanceof Vector)) {
            Vector vector2 = ((Vector) WrapperRuntime.convertToVector(arg2));
            int length2 = vector2 .length();
            int currentElementIndex2 = 0;
            int cycles = 0;
            if (length2 == 0) {
                return StringVector.EMPTY;
            }
            if (length2 >cycles) {
                cycles = length2;
            }
            StringVector.Builder builder = new StringVector.Builder(cycles);
            for (int i = 0; (i!= cycles); i ++) {
                if (vector2 .isElementNA(currentElementIndex2)) {
                    builder.setNA(i);
                } else {
                    builder.set(i, Text.gsub(WrapperRuntime.convertToString(arg0), WrapperRuntime.convertToString(arg1), vector2 .getElementAsString(currentElementIndex2), WrapperRuntime.convertToBooleanPrimitive(arg3), WrapperRuntime.convertToBooleanPrimitive(arg4), WrapperRuntime.convertToBooleanPrimitive(arg5), WrapperRuntime.convertToBooleanPrimitive(arg6)));
                }
                currentElementIndex2 += 1;
                if (currentElementIndex2 == length2) {
                    currentElementIndex2 = 0;
                }
            }
            if (length2 == cycles) {
                builder.copySomeAttributesFrom(vector2, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            return builder.build();
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tgsub(%s, %s, %s, %s, %s, %s, %s)\n\tExpected:\n\tgsub(character(1), character(1), character, logical(1), logical(1), logical(1), logical(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName(), arg5 .getTypeName(), arg6 .getTypeName()));
        }
    }

}
