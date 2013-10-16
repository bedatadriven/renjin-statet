
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.io.serialization.Serialization;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.Null;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;

public class R$primitive$unserializeFromConn
    extends BuiltinFunction
{


    public R$primitive$unserializeFromConn() {
        super("unserializeFromConn");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            SEXP s1 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1);
            }
            throw new EvalException("unserializeFromConn: too many arguments, expected at most 2.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tunserializeFromConn(any, Null)\n\tunserializeFromConn(any, Environment)", e.getMessage());
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
        throw new EvalException("unserializeFromConn: max arity is 2");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$unserializeFromConn.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1)
        throws Exception
    {
        if ((arg0 instanceof SEXP)&&(arg1 instanceof Null)) {
            return Serialization.unserializeFromConn(context, ((SEXP) arg0), ((Null) arg1));
        } else {
            if ((arg0 instanceof SEXP)&&(arg1 instanceof Environment)) {
                return Serialization.unserializeFromConn(context, ((SEXP) arg0), ((Environment) arg1));
            } else {
                throw new EvalException(String.format("Invalid argument:\n\tunserializeFromConn(%s, %s)\n\tExpected:\n\tunserializeFromConn(any, Null)\n\tunserializeFromConn(any, Environment)", arg0 .getTypeName(), arg1 .getTypeName()));
            }
        }
    }

}
