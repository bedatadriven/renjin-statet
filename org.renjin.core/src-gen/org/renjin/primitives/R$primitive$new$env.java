
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
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Vector;

public class R$primitive$new$env
    extends BuiltinFunction
{


    public R$primitive$new$env() {
        super("new.env");
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
            throw new EvalException("new.env: too many arguments, expected at most 3.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tnew.env(logical(1), Environment, integer(1))", e.getMessage());
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
        throw new EvalException("new.env: max arity is 3");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$new$env.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2)
        throws Exception
    {
        if (((arg0 instanceof Vector)&&(arg1 instanceof Environment))&&(((arg2 instanceof IntVector)||(arg2 instanceof DoubleVector))||(arg2 instanceof LogicalVector))) {
            return Types.newEnv(WrapperRuntime.convertToBooleanPrimitive(arg0), ((Environment) arg1), WrapperRuntime.convertToInt(arg2));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tnew.env(%s, %s, %s)\n\tExpected:\n\tnew.env(logical(1), Environment, integer(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName()));
        }
    }

}
