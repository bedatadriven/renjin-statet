
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
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Vector;

public class R$primitive$type$convert
    extends BuiltinFunction
{


    public R$primitive$type$convert() {
        super("type.convert");
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
            throw new EvalException("type.convert: too many arguments, expected at most 4.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\ttype.convert(character, character, logical(1), character(1))", e.getMessage());
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
        throw new EvalException("type.convert: max arity is 4");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$type$convert.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3)
        throws Exception
    {
        if ((((arg0 instanceof StringVector)&&(arg1 instanceof StringVector))&&(arg2 instanceof Vector))&&((arg3 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg3)))) {
            return Scan.typeConvert(((StringVector) arg0), ((StringVector) arg1), WrapperRuntime.convertToBooleanPrimitive(arg2), WrapperRuntime.convertToString(arg3));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\ttype.convert(%s, %s, %s, %s)\n\tExpected:\n\ttype.convert(character, character, logical(1), character(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName()));
        }
    }

}
