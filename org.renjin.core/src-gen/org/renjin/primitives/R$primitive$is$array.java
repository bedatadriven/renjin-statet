
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.sexp.AbstractSEXP;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;

public class R$primitive$is$array
    extends BuiltinFunction
{


    public R$primitive$is$array() {
        super("is.array");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            if (((AbstractSEXP) s0).isObject()) {
                SEXP genericResult = S3 .tryDispatchFromPrimitive(context, environment, call, "is.array", s0, args);
                if (genericResult!= null) {
                    return genericResult;
                }
            }
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0);
            }
            throw new EvalException("is.array: too many arguments, expected at most 1.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tis.array(any)", e.getMessage());
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
            if ((args.length) == 1) {
                return doApply(context, environment, args[ 0 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("is.array: max arity is 1");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$is$array.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0)
        throws Exception
    {
        if (arg0 instanceof SEXP) {
            return WrapperRuntime.wrapResult(Types.isArray(((SEXP) arg0)));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tis.array(%s)\n\tExpected:\n\tis.array(any)", arg0 .getTypeName()));
        }
    }

}
