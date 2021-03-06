
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.text.Text;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Vector;

public class R$primitive$grep
    extends BuiltinFunction
{


    public R$primitive$grep() {
        super("grep");
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
            throw new EvalException("grep: too many arguments, expected at most 8.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tgrep(character(1), character, logical(1), logical(1), logical(1), logical(1), logical(1), logical(1))", e.getMessage());
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
        throw new EvalException("grep: max arity is 8");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$grep.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4, SEXP arg5, SEXP arg6, SEXP arg7)
        throws Exception
    {
        if (((((((((arg0 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0)))&&(arg1 instanceof StringVector))&&(arg2 instanceof Vector))&&(arg3 instanceof Vector))&&(arg4 instanceof Vector))&&(arg5 instanceof Vector))&&(arg6 instanceof Vector))&&(arg7 instanceof Vector)) {
            return Text.grep(WrapperRuntime.convertToString(arg0), ((StringVector) arg1), WrapperRuntime.convertToBooleanPrimitive(arg2), WrapperRuntime.convertToBooleanPrimitive(arg3), WrapperRuntime.convertToBooleanPrimitive(arg4), WrapperRuntime.convertToBooleanPrimitive(arg5), WrapperRuntime.convertToBooleanPrimitive(arg6), WrapperRuntime.convertToBooleanPrimitive(arg7));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tgrep(%s, %s, %s, %s, %s, %s, %s, %s)\n\tExpected:\n\tgrep(character(1), character, logical(1), logical(1), logical(1), logical(1), logical(1), logical(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName(), arg5 .getTypeName(), arg6 .getTypeName(), arg7 .getTypeName()));
        }
    }

}
