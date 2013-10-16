
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
import org.renjin.sexp.RawVector;
import org.renjin.sexp.SEXP;

public class R$primitive$rawShift
    extends BuiltinFunction
{


    public R$primitive$rawShift() {
        super("rawShift");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            SEXP s1 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1);
            }
            throw new EvalException("rawShift: too many arguments, expected at most 2.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\trawShift(RawVector, integer(1))", e.getMessage());
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
        throw new EvalException("rawShift: max arity is 2");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$rawShift.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1)
        throws Exception
    {
        if ((arg0 instanceof RawVector)&&(((arg1 instanceof IntVector)||(arg1 instanceof DoubleVector))||(arg1 instanceof LogicalVector))) {
            return Types.rawShift(((RawVector) arg0), WrapperRuntime.convertToInt(arg1));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\trawShift(%s, %s)\n\tExpected:\n\trawShift(RawVector, integer(1))", arg0 .getTypeName(), arg1 .getTypeName()));
        }
    }

}
