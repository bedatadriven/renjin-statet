
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
import org.renjin.sexp.LogicalVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Symbols;
import org.renjin.sexp.Vector;

public class R$primitive$file$append
    extends BuiltinFunction
{


    public R$primitive$file$append() {
        super("file.append");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            SEXP s1 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1);
            }
            throw new EvalException("file.append: too many arguments, expected at most 2.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tfile.append(character, character)", e.getMessage());
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
        throw new EvalException("file.append: max arity is 2");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$file$append.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1)
        throws Exception
    {
        if (((arg0 .length() == 0)||((arg0 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0))))&&((arg1 .length() == 0)||((arg1 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg1))))) {
            Vector vector1 = ((Vector) WrapperRuntime.convertToVector(arg0));
            int length1 = vector1 .length();
            int currentElementIndex1 = 0;
            Vector vector2 = ((Vector) WrapperRuntime.convertToVector(arg1));
            int length2 = vector2 .length();
            int currentElementIndex2 = 0;
            int cycles = 0;
            if (length1 == 0) {
                return LogicalVector.EMPTY;
            }
            if (length1 >cycles) {
                cycles = length1;
            }
            if (length2 == 0) {
                return LogicalVector.EMPTY;
            }
            if (length2 >cycles) {
                cycles = length2;
            }
            org.renjin.sexp.LogicalArrayVector.Builder builder = new org.renjin.sexp.LogicalArrayVector.Builder(cycles);
            for (int i = 0; (i!= cycles); i ++) {
                if (vector1 .isElementNA(currentElementIndex1)||vector2 .isElementNA(currentElementIndex2)) {
                    builder.setNA(i);
                } else {
                    builder.set(i, Files.fileAppend(context, vector1 .getElementAsString(currentElementIndex1), vector2 .getElementAsString(currentElementIndex2)));
                }
                currentElementIndex1 += 1;
                if (currentElementIndex1 == length1) {
                    currentElementIndex1 = 0;
                }
                currentElementIndex2 += 1;
                if (currentElementIndex2 == length2) {
                    currentElementIndex2 = 0;
                }
            }
            if (length2 == cycles) {
                builder.copySomeAttributesFrom(vector2, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            if (length1 == cycles) {
                builder.copySomeAttributesFrom(vector1, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
            }
            return builder.build();
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tfile.append(%s, %s)\n\tExpected:\n\tfile.append(character, character)", arg0 .getTypeName(), arg1 .getTypeName()));
        }
    }

}
