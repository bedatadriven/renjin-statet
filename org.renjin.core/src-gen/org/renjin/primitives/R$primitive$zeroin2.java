
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.optimize.Roots;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.Environment;
import org.renjin.sexp.Function;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.IntVector;
import org.renjin.sexp.LogicalVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Vector;

public class R$primitive$zeroin2
    extends BuiltinFunction
{


    public R$primitive$zeroin2() {
        super("zeroin2");
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
            throw new EvalException("zeroin2: too many arguments, expected at most 7.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tzeroin2(Function, double(1), double(1), double(1), double(1), double(1), integer(1))", e.getMessage());
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
        throw new EvalException("zeroin2: max arity is 7");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$zeroin2 .doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4, SEXP arg5, SEXP arg6)
        throws Exception
    {
        if (((((((arg0 instanceof Function)&&((arg1 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg1))))&&((arg2 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg2))))&&((arg3 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg3))))&&((arg4 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg4))))&&((arg5 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg5))))&&(((arg6 instanceof IntVector)||(arg6 instanceof DoubleVector))||(arg6 instanceof LogicalVector))) {
            return Roots.zeroin2(context, environment, ((Function) arg0), WrapperRuntime.convertToDoublePrimitive(arg1), WrapperRuntime.convertToDoublePrimitive(arg2), WrapperRuntime.convertToDoublePrimitive(arg3), WrapperRuntime.convertToDoublePrimitive(arg4), WrapperRuntime.convertToDoublePrimitive(arg5), WrapperRuntime.convertToInt(arg6));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tzeroin2(%s, %s, %s, %s, %s, %s, %s)\n\tExpected:\n\tzeroin2(Function, double(1), double(1), double(1), double(1), double(1), integer(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName(), arg5 .getTypeName(), arg6 .getTypeName()));
        }
    }

}
