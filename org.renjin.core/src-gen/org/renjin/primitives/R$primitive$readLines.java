
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.io.connections.Connections;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.IntVector;
import org.renjin.sexp.LogicalVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Vector;

public class R$primitive$readLines
    extends BuiltinFunction
{


    public R$primitive$readLines() {
        super("readLines");
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
            throw new EvalException("readLines: too many arguments, expected at most 5.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\treadLines(any, integer(1), logical(1), logical(1), character(1))", e.getMessage());
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
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("readLines: max arity is 5");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$readLines.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4)
        throws Exception
    {
        if (((((arg0 instanceof SEXP)&&(((arg1 instanceof IntVector)||(arg1 instanceof DoubleVector))||(arg1 instanceof LogicalVector)))&&(arg2 instanceof Vector))&&(arg3 instanceof Vector))&&((arg4 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg4)))) {
            return Connections.readLines(context, ((SEXP) arg0), WrapperRuntime.convertToInt(arg1), WrapperRuntime.convertToBooleanPrimitive(arg2), WrapperRuntime.convertToBooleanPrimitive(arg3), WrapperRuntime.convertToString(arg4));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\treadLines(%s, %s, %s, %s, %s)\n\tExpected:\n\treadLines(any, integer(1), logical(1), logical(1), character(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName()));
        }
    }

}
