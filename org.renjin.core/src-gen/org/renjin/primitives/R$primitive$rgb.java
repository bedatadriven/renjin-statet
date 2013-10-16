
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.graphics.RgbHsv;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;

public class R$primitive$rgb
    extends BuiltinFunction
{


    public R$primitive$rgb() {
        super("rgb");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            SEXP s1 = argIt.evalNext();
            SEXP s2 = argIt.evalNext();
            SEXP s3 = argIt.evalNext();
            SEXP s4 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2, s3, s4);
            }
            SEXP s5 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2, s3, s4, s5);
            }
            throw new EvalException("rgb: too many arguments, expected at most 6.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\trgb(DoubleVector, DoubleVector, DoubleVector, DoubleVector, DoubleVector, character)\n\trgb(DoubleVector, DoubleVector, DoubleVector, DoubleVector, character)", e.getMessage());
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
            if ((args.length) == 5) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ], args[ 3 ], args[ 4 ]);
            }
            if ((args.length) == 6) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ], args[ 3 ], args[ 4 ], args[ 5 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("rgb: max arity is 6");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$rgb.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4)
        throws Exception
    {
        if (((((arg0 instanceof DoubleVector)&&(arg1 instanceof DoubleVector))&&(arg2 instanceof DoubleVector))&&(arg3 instanceof DoubleVector))&&(arg4 instanceof StringVector)) {
            return RgbHsv.rgb(((DoubleVector) arg0), ((DoubleVector) arg1), ((DoubleVector) arg2), ((DoubleVector) arg3), ((StringVector) arg4));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\trgb(%s, %s, %s, %s, %s)\n\tExpected:\n\trgb(DoubleVector, DoubleVector, DoubleVector, DoubleVector, DoubleVector, character)\n\trgb(DoubleVector, DoubleVector, DoubleVector, DoubleVector, character)", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName()));
        }
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4, SEXP arg5)
        throws Exception
    {
        if ((((((arg0 instanceof DoubleVector)&&(arg1 instanceof DoubleVector))&&(arg2 instanceof DoubleVector))&&(arg3 instanceof DoubleVector))&&(arg4 instanceof DoubleVector))&&(arg5 instanceof StringVector)) {
            return RgbHsv.rgb(((DoubleVector) arg0), ((DoubleVector) arg1), ((DoubleVector) arg2), ((DoubleVector) arg3), ((DoubleVector) arg4), ((StringVector) arg5));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\trgb(%s, %s, %s, %s, %s, %s)\n\tExpected:\n\trgb(DoubleVector, DoubleVector, DoubleVector, DoubleVector, DoubleVector, character)\n\trgb(DoubleVector, DoubleVector, DoubleVector, DoubleVector, character)", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName(), arg5 .getTypeName()));
        }
    }

}
