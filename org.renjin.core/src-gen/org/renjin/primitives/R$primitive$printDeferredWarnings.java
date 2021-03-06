
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.Null;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;

public class R$primitive$printDeferredWarnings
    extends BuiltinFunction
{


    public R$primitive$printDeferredWarnings() {
        super("printDeferredWarnings");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            if (!argIt.hasNext()) {
                return this.doApply(context, environment);
            }
            throw new EvalException("printDeferredWarnings: too many arguments, expected at most 0.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tprintDeferredWarnings()", e.getMessage());
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
            if ((args.length) == 0) {
                return doApply(context, environment);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("printDeferredWarnings: max arity is 0");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$printDeferredWarnings.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment)
        throws Exception
    {
        Warning.printDeferredWarnings(context);
        return Null.INSTANCE;
    }

}
