
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

public class R$primitive$tempfile
    extends BuiltinFunction
{


    public R$primitive$tempfile() {
        super("tempfile");
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
            throw new EvalException("tempfile: too many arguments, expected at most 3.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\ttempfile(character, character, character)", e.getMessage());
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
        throw new EvalException("tempfile: max arity is 3");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$tempfile.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2)
        throws Exception
    {
        if ((((arg0 .length() == 0)||((arg0 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0))))&&((arg1 .length() == 0)||((arg1 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg1)))))&&((arg2 .length() == 0)||((arg2 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg2))))) {
            Vector vector0 = ((Vector) WrapperRuntime.convertToVector(arg0));
            int length0 = vector0 .length();
            int currentElementIndex0 = 0;
            Vector vector1 = ((Vector) WrapperRuntime.convertToVector(arg1));
            int length1 = vector1 .length();
            int currentElementIndex1 = 0;
            Vector vector2 = ((Vector) WrapperRuntime.convertToVector(arg2));
            int length2 = vector2 .length();
            int currentElementIndex2 = 0;
            int cycles = 0;
            if (length0 == 0) {
                return StringVector.EMPTY;
            }
            if (length0 >cycles) {
                cycles = length0;
            }
            if (length1 == 0) {
                return StringVector.EMPTY;
            }
            if (length1 >cycles) {
                cycles = length1;
            }
            if (length2 == 0) {
                return StringVector.EMPTY;
            }
            if (length2 >cycles) {
                cycles = length2;
            }
            StringVector.Builder builder = new StringVector.Builder(cycles);
            for (int i = 0; (i!= cycles); i ++) {
                if ((vector0 .isElementNA(currentElementIndex0)||vector1 .isElementNA(currentElementIndex1))||vector2 .isElementNA(currentElementIndex2)) {
                    builder.setNA(i);
                } else {
                    builder.set(i, Files.tempfile(vector0 .getElementAsString(currentElementIndex0), vector1 .getElementAsString(currentElementIndex1), vector2 .getElementAsString(currentElementIndex2)));
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
            throw new EvalException(String.format("Invalid argument:\n\ttempfile(%s, %s, %s)\n\tExpected:\n\ttempfile(character, character, character)", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName()));
        }
    }

}
