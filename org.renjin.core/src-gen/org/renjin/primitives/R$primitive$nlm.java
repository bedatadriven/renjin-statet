
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.optimize.Optimizations;
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

public class R$primitive$nlm
    extends BuiltinFunction
{


    public R$primitive$nlm() {
        super("nlm");
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
            SEXP s9 = argIt.evalNext();
            SEXP s10 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
            }
            throw new EvalException("nlm: too many arguments, expected at most 11.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tnlm(Function, DoubleVector, logical(1), DoubleVector, double(1), integer(1), integer(1), double(1), double(1), double(1), integer(1))", e.getMessage());
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
            if ((args.length) == 11) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ], args[ 3 ], args[ 4 ], args[ 5 ], args[ 6 ], args[ 7 ], args[ 8 ], args[ 9 ], args[ 10 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("nlm: max arity is 11");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$nlm.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4, SEXP arg5, SEXP arg6, SEXP arg7, SEXP arg8, SEXP arg9, SEXP arg10)
        throws Exception
    {
        if (((((((((((arg0 instanceof Function)&&(arg1 instanceof DoubleVector))&&(arg2 instanceof Vector))&&(arg3 instanceof DoubleVector))&&((arg4 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg4))))&&(((arg5 instanceof IntVector)||(arg5 instanceof DoubleVector))||(arg5 instanceof LogicalVector)))&&(((arg6 instanceof IntVector)||(arg6 instanceof DoubleVector))||(arg6 instanceof LogicalVector)))&&((arg7 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg7))))&&((arg8 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg8))))&&((arg9 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg9))))&&(((arg10 instanceof IntVector)||(arg10 instanceof DoubleVector))||(arg10 instanceof LogicalVector))) {
            return Optimizations.nlm(context, environment, ((Function) arg0), ((DoubleVector) arg1), WrapperRuntime.convertToBooleanPrimitive(arg2), ((DoubleVector) arg3), WrapperRuntime.convertToDoublePrimitive(arg4), WrapperRuntime.convertToInt(arg5), WrapperRuntime.convertToInt(arg6), WrapperRuntime.convertToDoublePrimitive(arg7), WrapperRuntime.convertToDoublePrimitive(arg8), WrapperRuntime.convertToDoublePrimitive(arg9), WrapperRuntime.convertToInt(arg10));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tnlm(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)\n\tExpected:\n\tnlm(Function, DoubleVector, logical(1), DoubleVector, double(1), integer(1), integer(1), double(1), double(1), double(1), integer(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName(), arg5 .getTypeName(), arg6 .getTypeName(), arg7 .getTypeName(), arg8 .getTypeName(), arg9 .getTypeName(), arg10 .getTypeName()));
        }
    }

}
