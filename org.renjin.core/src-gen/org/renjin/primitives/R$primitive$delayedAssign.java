
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

public class R$primitive$delayedAssign
    extends BuiltinFunction
{


    public R$primitive$delayedAssign() {
        super("delayedAssign");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            SEXP s1 = argIt.evalNext();
            SEXP s2 = argIt.evalNext();
            SEXP s3 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2, s3);
            }
            throw new EvalException("delayedAssign: too many arguments, expected at most 4.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tdelayedAssign(character(1), any, Environment, Environment)", e.getMessage());
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
            if ((args.length) == 4) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ], args[ 3 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("delayedAssign: max arity is 4");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$delayedAssign.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3)
        throws Exception
    {
        if (((((arg0 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0)))&&(arg1 instanceof SEXP))&&(arg2 instanceof Environment))&&(arg3 instanceof Environment)) {
            Evaluation.delayedAssign(context, WrapperRuntime.convertToString(arg0), ((SEXP) arg1), ((Environment) arg2), ((Environment) arg3));
            return Null.INSTANCE;
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tdelayedAssign(%s, %s, %s, %s)\n\tExpected:\n\tdelayedAssign(character(1), any, Environment, Environment)", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName()));
        }
    }

}
