
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.Null;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Vector;

public class R$primitive$remove
    extends BuiltinFunction
{


    public R$primitive$remove() {
        super("remove");
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
            throw new EvalException("remove: too many arguments, expected at most 3.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tremove(character, Environment, logical(1))", e.getMessage());
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
        throw new EvalException("remove: max arity is 3");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$remove.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2)
        throws Exception
    {
        if (((arg0 instanceof StringVector)&&(arg1 instanceof Environment))&&(arg2 instanceof Vector)) {
            Evaluation.remove(((StringVector) arg0), ((Environment) arg1), WrapperRuntime.convertToBooleanPrimitive(arg2));
            return Null.INSTANCE;
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tremove(%s, %s, %s)\n\tExpected:\n\tremove(character, Environment, logical(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName()));
        }
    }

}
