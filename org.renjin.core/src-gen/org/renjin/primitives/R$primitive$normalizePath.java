
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.files.Files;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Symbols;
import org.renjin.sexp.Vector;

public class R$primitive$normalizePath
    extends BuiltinFunction
{


    public R$primitive$normalizePath() {
        super("normalizePath");
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
            throw new EvalException("normalizePath: too many arguments, expected at most 3.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tnormalizePath(character, character(1), any)", e.getMessage());
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
        throw new EvalException("normalizePath: max arity is 3");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$normalizePath.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2)
        throws Exception
    {
        if ((((arg0 .length() == 0)||((arg0 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0))))&&((arg1 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg1))))&&(arg2 instanceof SEXP)) {
            Vector vector1 = ((Vector) WrapperRuntime.convertToVector(arg0));
            int length1 = vector1 .length();
            int currentElementIndex1 = 0;
            int cycles = 0;
            if (length1 == 0) {
                return StringVector.EMPTY;
            }
            if (length1 >cycles) {
                cycles = length1;
            }
            StringVector.Builder builder = new StringVector.Builder(cycles);
            for (int i = 0; (i!= cycles); i ++) {
                if (vector1 .isElementNA(currentElementIndex1)) {
                    builder.setNA(i);
                } else {
                    builder.set(i, Files.normalizePath(context, vector1 .getElementAsString(currentElementIndex1), WrapperRuntime.convertToString(arg1), ((SEXP) arg2)));
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
            throw new EvalException(String.format("Invalid argument:\n\tnormalizePath(%s, %s, %s)\n\tExpected:\n\tnormalizePath(character, character(1), any)", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName()));
        }
    }

}
