
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.sexp.AbstractSEXP;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;

public class R$primitive$names
    extends BuiltinFunction
{


    public R$primitive$names() {
        super("names");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            if (((AbstractSEXP) s0).isObject()) {
                SEXP genericResult = S3 .tryDispatchFromPrimitive(context, environment, call, "names", s0, args);
                if (genericResult!= null) {
                    return genericResult;
                }
            }
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0);
            }
            throw new EvalException("names: too many arguments, expected at most 1.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tnames(any)", e.getMessage());
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
        throw new EvalException("names: max arity is 1");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$names.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0)
        throws Exception
    {
        if (arg0 instanceof SEXP) {
            return Attributes.getNames(((SEXP) arg0));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tnames(%s)\n\tExpected:\n\tnames(any)", arg0 .getTypeName()));
        }
    }

}
