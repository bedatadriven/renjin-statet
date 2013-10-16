
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.models.Models;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Vector;

public class R$primitive$model$frame
    extends BuiltinFunction
{


    public R$primitive$model$frame() {
        super("model.frame");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            SEXP s1 = argIt.evalNext();
            SEXP s2 = argIt.evalNext();
            SEXP s3 = argIt.evalNext();
            SEXP s4 = argIt.evalNext();
            SEXP s5 = argIt.evalNext();
            SEXP s6 = argIt.evalNext();
            SEXP s7 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2, s3, s4, s5, s6, s7);
            }
            throw new EvalException("model.frame: too many arguments, expected at most 8.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tmodel.frame(any, Vector, Vector, Vector, Vector, Vector, any, any)", e.getMessage());
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
            if ((args.length) == 8) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ], args[ 3 ], args[ 4 ], args[ 5 ], args[ 6 ], args[ 7 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("model.frame: max arity is 8");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$model$frame.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4, SEXP arg5, SEXP arg6, SEXP arg7)
        throws Exception
    {
        if ((((((((arg0 instanceof SEXP)&&(arg1 instanceof Vector))&&(arg2 instanceof Vector))&&(arg3 instanceof Vector))&&(arg4 instanceof Vector))&&(arg5 instanceof Vector))&&(arg6 instanceof SEXP))&&(arg7 instanceof SEXP)) {
            return Models.modelFrame(context, environment, ((SEXP) arg0), ((Vector) arg1), ((Vector) arg2), ((Vector) arg3), ((Vector) arg4), ((Vector) arg5), ((SEXP) arg6), ((SEXP) arg7));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tmodel.frame(%s, %s, %s, %s, %s, %s, %s, %s)\n\tExpected:\n\tmodel.frame(any, Vector, Vector, Vector, Vector, Vector, any, any)", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName(), arg5 .getTypeName(), arg6 .getTypeName(), arg7 .getTypeName()));
        }
    }

}
