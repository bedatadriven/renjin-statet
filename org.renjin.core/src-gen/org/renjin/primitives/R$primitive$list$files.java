
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.files.Files;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Vector;

public class R$primitive$list$files
    extends BuiltinFunction
{


    public R$primitive$list$files() {
        super("list.files");
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
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2, s3, s4, s5, s6);
            }
            throw new EvalException("list.files: too many arguments, expected at most 7.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tlist.files(character, character(1), logical(1), logical(1), logical(1), logical(1), logical(1))", e.getMessage());
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
            if ((args.length) == 7) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ], args[ 3 ], args[ 4 ], args[ 5 ], args[ 6 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("list.files: max arity is 7");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$list$files.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4, SEXP arg5, SEXP arg6)
        throws Exception
    {
        if (((((((arg0 instanceof StringVector)&&((arg1 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg1))))&&(arg2 instanceof Vector))&&(arg3 instanceof Vector))&&(arg4 instanceof Vector))&&(arg5 instanceof Vector))&&(arg6 instanceof Vector)) {
            return Files.listFiles(context, ((StringVector) arg0), WrapperRuntime.convertToString(arg1), WrapperRuntime.convertToBooleanPrimitive(arg2), WrapperRuntime.convertToBooleanPrimitive(arg3), WrapperRuntime.convertToBooleanPrimitive(arg4), WrapperRuntime.convertToBooleanPrimitive(arg5), WrapperRuntime.convertToBooleanPrimitive(arg6));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tlist.files(%s, %s, %s, %s, %s, %s, %s)\n\tExpected:\n\tlist.files(character, character(1), logical(1), logical(1), logical(1), logical(1), logical(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName(), arg5 .getTypeName(), arg6 .getTypeName()));
        }
    }

}
