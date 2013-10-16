
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.matrix.Matrices;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.IntVector;
import org.renjin.sexp.LogicalVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Vector;

public class R$primitive$matrix
    extends BuiltinFunction
{


    public R$primitive$matrix() {
        super("matrix");
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
            throw new EvalException("matrix: too many arguments, expected at most 7.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tmatrix(Vector, integer(1), integer(1), logical(1), Vector, logical(1), logical(1))", e.getMessage());
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
        throw new EvalException("matrix: max arity is 7");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$matrix.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4, SEXP arg5, SEXP arg6)
        throws Exception
    {
        if (((((((arg0 instanceof Vector)&&(((arg1 instanceof IntVector)||(arg1 instanceof DoubleVector))||(arg1 instanceof LogicalVector)))&&(((arg2 instanceof IntVector)||(arg2 instanceof DoubleVector))||(arg2 instanceof LogicalVector)))&&(arg3 instanceof Vector))&&(arg4 instanceof Vector))&&(arg5 instanceof Vector))&&(arg6 instanceof Vector)) {
            return Matrices.matrix(context, ((Vector) arg0), WrapperRuntime.convertToInt(arg1), WrapperRuntime.convertToInt(arg2), WrapperRuntime.convertToBooleanPrimitive(arg3), ((Vector) arg4), WrapperRuntime.convertToBooleanPrimitive(arg5), WrapperRuntime.convertToBooleanPrimitive(arg6));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tmatrix(%s, %s, %s, %s, %s, %s, %s)\n\tExpected:\n\tmatrix(Vector, integer(1), integer(1), logical(1), Vector, logical(1), logical(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName(), arg5 .getTypeName(), arg6 .getTypeName()));
        }
    }

}
