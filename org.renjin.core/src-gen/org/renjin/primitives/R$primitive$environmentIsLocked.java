
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;

public class R$primitive$environmentIsLocked
    extends BuiltinFunction
{


    public R$primitive$environmentIsLocked() {
        super("environmentIsLocked");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0);
            }
            throw new EvalException("environmentIsLocked: too many arguments, expected at most 1.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tenvironmentIsLocked(Environment)", e.getMessage());
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
        throw new EvalException("environmentIsLocked: max arity is 1");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$environmentIsLocked.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0)
        throws Exception
    {
        if (arg0 instanceof Environment) {
            return WrapperRuntime.wrapResult(Types.environmentIsLocked(((Environment) arg0)));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tenvironmentIsLocked(%s)\n\tExpected:\n\tenvironmentIsLocked(Environment)", arg0 .getTypeName()));
        }
    }

}
