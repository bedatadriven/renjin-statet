
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
import org.renjin.sexp.Vector;

public class R$primitive$print$default
    extends BuiltinFunction
{


    public R$primitive$print$default() {
        super("print.default");
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
            SEXP s8 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2, s3, s4, s5, s6, s7, s8);
            }
            throw new EvalException("print.default: too many arguments, expected at most 9.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tprint.default(any, any, logical(1), any, any, any, any, any, any)", e.getMessage());
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
            if ((args.length) == 9) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ], args[ 3 ], args[ 4 ], args[ 5 ], args[ 6 ], args[ 7 ], args[ 8 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("print.default: max arity is 9");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$print$default.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4, SEXP arg5, SEXP arg6, SEXP arg7, SEXP arg8)
        throws Exception
    {
        if (((((((((arg0 instanceof SEXP)&&(arg1 instanceof SEXP))&&(arg2 instanceof Vector))&&(arg3 instanceof SEXP))&&(arg4 instanceof SEXP))&&(arg5 instanceof SEXP))&&(arg6 instanceof SEXP))&&(arg7 instanceof SEXP))&&(arg8 instanceof SEXP)) {
            return Print.printDefault(context, ((SEXP) arg0), ((SEXP) arg1), WrapperRuntime.convertToBooleanPrimitive(arg2), ((SEXP) arg3), ((SEXP) arg4), ((SEXP) arg5), ((SEXP) arg6), ((SEXP) arg7), ((SEXP) arg8));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tprint.default(%s, %s, %s, %s, %s, %s, %s, %s, %s)\n\tExpected:\n\tprint.default(any, any, logical(1), any, any, any, any, any, any)", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName(), arg5 .getTypeName(), arg6 .getTypeName(), arg7 .getTypeName(), arg8 .getTypeName()));
        }
    }

}
