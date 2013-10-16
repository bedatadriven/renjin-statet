
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.subset.Subsetting;
import org.renjin.sexp.AbstractSEXP;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.ListVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.SpecialFunction;

public class R$primitive$$
    extends SpecialFunction
{


    public R$primitive$$() {
        super("$");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            if (((AbstractSEXP) s0).isObject()) {
                SEXP genericResult = S3 .tryDispatchFromPrimitive(context, environment, call, "$", s0, args);
                if (genericResult!= null) {
                    return genericResult;
                }
            }
            SEXP s1 = argIt.next();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1);
            }
            throw new EvalException("$: too many arguments, expected at most 2.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\t$(PairList, any)\n\t$(Environment, any)\n\t$(list, any)", e.getMessage());
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
        throw new EvalException("$: max arity is 2");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$$.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1)
        throws Exception
    {
        if ((arg0 instanceof ListVector)&&(arg1 instanceof SEXP)) {
            return Subsetting.getElementByName(((ListVector) arg0), ((SEXP) arg1));
        } else {
            if ((arg0 instanceof PairList)&&(arg1 instanceof SEXP)) {
                return Subsetting.getElementByName(((PairList) arg0), ((SEXP) arg1));
            } else {
                if ((arg0 instanceof Environment)&&(arg1 instanceof SEXP)) {
                    return Subsetting.getElementByName(((Environment) arg0), ((SEXP) arg1));
                } else {
                    throw new EvalException(String.format("Invalid argument:\n\t$(%s, %s)\n\tExpected:\n\t$(PairList, any)\n\t$(Environment, any)\n\t$(list, any)", arg0 .getTypeName(), arg1 .getTypeName()));
                }
            }
        }
    }

}
