
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
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

public class R$primitive$scan
    extends BuiltinFunction
{


    public R$primitive$scan() {
        super("scan");
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
            SEXP s11 = argIt.evalNext();
            SEXP s12 = argIt.evalNext();
            SEXP s13 = argIt.evalNext();
            SEXP s14 = argIt.evalNext();
            SEXP s15 = argIt.evalNext();
            SEXP s16 = argIt.evalNext();
            SEXP s17 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17);
            }
            throw new EvalException("scan: too many arguments, expected at most 18.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tscan(any, Vector, integer(1), character(1), character(1), character(1), integer(1), integer(1), character, logical(1), logical(1), logical(1), logical(1), logical(1), logical(1), character(1), logical(1), character(1))", e.getMessage());
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
            if ((args.length) == 18) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ], args[ 3 ], args[ 4 ], args[ 5 ], args[ 6 ], args[ 7 ], args[ 8 ], args[ 9 ], args[ 10 ], args[ 11 ], args[ 12 ], args[ 13 ], args[ 14 ], args[ 15 ], args[ 16 ], args[ 17 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("scan: max arity is 18");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$scan.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2, SEXP arg3, SEXP arg4, SEXP arg5, SEXP arg6, SEXP arg7, SEXP arg8, SEXP arg9, SEXP arg10, SEXP arg11, SEXP arg12, SEXP arg13, SEXP arg14, SEXP arg15, SEXP arg16, SEXP arg17)
        throws Exception
    {
        if ((((((((((((((((((arg0 instanceof SEXP)&&(arg1 instanceof Vector))&&(((arg2 instanceof IntVector)||(arg2 instanceof DoubleVector))||(arg2 instanceof LogicalVector)))&&((arg3 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg3))))&&((arg4 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg4))))&&((arg5 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg5))))&&(((arg6 instanceof IntVector)||(arg6 instanceof DoubleVector))||(arg6 instanceof LogicalVector)))&&(((arg7 instanceof IntVector)||(arg7 instanceof DoubleVector))||(arg7 instanceof LogicalVector)))&&(arg8 instanceof StringVector))&&(arg9 instanceof Vector))&&(arg10 instanceof Vector))&&(arg11 instanceof Vector))&&(arg12 instanceof Vector))&&(arg13 instanceof Vector))&&(arg14 instanceof Vector))&&((arg15 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg15))))&&(arg16 instanceof Vector))&&((arg17 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg17)))) {
            return Scan.scan(context, ((SEXP) arg0), ((Vector) arg1), WrapperRuntime.convertToInt(arg2), WrapperRuntime.convertToString(arg3), WrapperRuntime.convertToString(arg4), WrapperRuntime.convertToString(arg5), WrapperRuntime.convertToInt(arg6), WrapperRuntime.convertToInt(arg7), ((StringVector) arg8), WrapperRuntime.convertToBooleanPrimitive(arg9), WrapperRuntime.convertToBooleanPrimitive(arg10), WrapperRuntime.convertToBooleanPrimitive(arg11), WrapperRuntime.convertToBooleanPrimitive(arg12), WrapperRuntime.convertToBooleanPrimitive(arg13), WrapperRuntime.convertToBooleanPrimitive(arg14), WrapperRuntime.convertToString(arg15), WrapperRuntime.convertToBooleanPrimitive(arg16), WrapperRuntime.convertToString(arg17));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\tscan(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)\n\tExpected:\n\tscan(any, Vector, integer(1), character(1), character(1), character(1), integer(1), integer(1), character, logical(1), logical(1), logical(1), logical(1), logical(1), logical(1), character(1), logical(1), character(1))", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName(), arg3 .getTypeName(), arg4 .getTypeName(), arg5 .getTypeName(), arg6 .getTypeName(), arg7 .getTypeName(), arg8 .getTypeName(), arg9 .getTypeName(), arg10 .getTypeName(), arg11 .getTypeName(), arg12 .getTypeName(), arg13 .getTypeName(), arg14 .getTypeName(), arg15 .getTypeName(), arg16 .getTypeName(), arg17 .getTypeName()));
        }
    }

}
