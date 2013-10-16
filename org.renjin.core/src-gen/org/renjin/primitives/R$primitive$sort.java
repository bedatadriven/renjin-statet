
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
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Vector;

public class R$primitive$sort
    extends BuiltinFunction
{


    public R$primitive$sort() {
        super("sort");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            SEXP s1 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1);
            }
            throw new EvalException("sort: too many arguments, expected at most 2.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tsort(IntVector, logical(1))\n\tsort(character, logical(1))\n\tsort(DoubleVector, logical(1))", e.getMessage());
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
        throw new EvalException("sort: max arity is 2");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$sort.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1)
        throws Exception
    {
        if ((arg0 instanceof IntVector)&&(arg1 instanceof Vector)) {
            return Sort.sort(((IntVector) arg0), WrapperRuntime.convertToBooleanPrimitive(arg1));
        } else {
            if ((arg0 instanceof DoubleVector)&&(arg1 instanceof Vector)) {
                return Sort.sort(((DoubleVector) arg0), WrapperRuntime.convertToBooleanPrimitive(arg1));
            } else {
                if ((arg0 instanceof StringVector)&&(arg1 instanceof Vector)) {
                    return Sort.sort(((StringVector) arg0), WrapperRuntime.convertToBooleanPrimitive(arg1));
                } else {
                    throw new EvalException(String.format("Invalid argument:\n\tsort(%s, %s)\n\tExpected:\n\tsort(IntVector, logical(1))\n\tsort(character, logical(1))\n\tsort(DoubleVector, logical(1))", arg0 .getTypeName(), arg1 .getTypeName()));
                }
            }
        }
    }

}
