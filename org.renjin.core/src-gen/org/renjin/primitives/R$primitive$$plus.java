
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.vector.DeferredComputation;
import org.renjin.sexp.AbstractSEXP;
import org.renjin.sexp.AttributeMap;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.ComplexVector;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Vector;

public class R$primitive$$plus
    extends BuiltinFunction
{


    public R$primitive$$plus() {
        super("+");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            if (!argIt.hasNext()) {
                if (((AbstractSEXP) s0).isObject()) {
                    SEXP genericResult = S3 .tryDispatchGroupFromPrimitive(context, environment, call, "Ops", "+", s0);
                    if (genericResult!= null) {
                        return genericResult;
                    }
                }
                return this.doApply(context, environment, s0);
            }
            SEXP s1 = argIt.evalNext();
            if (!argIt.hasNext()) {
                if (((AbstractSEXP) s0).isObject()||((AbstractSEXP) s1).isObject()) {
                    SEXP genericResult = S3 .tryDispatchGroupFromPrimitive(context, environment, call, "Ops", "+", s0, s1);
                    if (genericResult!= null) {
                        return genericResult;
                    }
                }
                return this.doApply(context, environment, s0, s1);
            }
            throw new EvalException("+: too many arguments, expected at most 2.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\t+(Vector)\n\t+(Complex, Complex)\n\t+(double, double)", e.getMessage());
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
            if ((args.length) == 1) {
                return doApply(context, environment, args[ 0 ]);
            }
            if ((args.length) == 2) {
                return doApply(context, environment, args[ 0 ], args[ 1 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("+: max arity is 2");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$$plus.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0)
        throws Exception
    {
        if (arg0 instanceof Vector) {
            return Ops.plus(((Vector) arg0));
        } else {
            throw new EvalException(String.format("Invalid argument:\n\t+(%s)\n\tExpected:\n\t+(Vector)\n\t+(Complex, Complex)\n\t+(double, double)", arg0 .getTypeName()));
        }
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1)
        throws Exception
    {
        if (((arg0 .length() == 0)||((arg0 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0))))&&((arg1 .length() == 0)||((arg1 instanceof Vector)&&DoubleVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg1))))) {
            Vector vector0 = ((Vector) WrapperRuntime.convertToVector(arg0));
            int length0 = vector0 .length();
            int currentElementIndex0 = 0;
            Vector vector1 = ((Vector) WrapperRuntime.convertToVector(arg1));
            int length1 = vector1 .length();
            int currentElementIndex1 = 0;
            int cycles = 0;
            if (length0 == 0) {
                return DoubleVector.EMPTY;
            }
            if (length0 >cycles) {
                cycles = length0;
            }
            if (length1 == 0) {
                return DoubleVector.EMPTY;
            }
            if (length1 >cycles) {
                cycles = length1;
            }
            if (((cycles > 100)||(vector0 instanceof DeferredComputation))||(vector1 instanceof DeferredComputation)) {
                return new R$primitive$$plus$deferred_dd(vector0, vector1, AttributeMap.combineAttributes(vector0, vector1));
            }
            org.renjin.sexp.DoubleArrayVector.Builder builder = new org.renjin.sexp.DoubleArrayVector.Builder(cycles);
            for (int i = 0; (i!= cycles); i ++) {
                if (vector0 .isElementNA(currentElementIndex0)||vector1 .isElementNA(currentElementIndex1)) {
                    builder.setNA(i);
                } else {
                    builder.set(i, Ops.plus(vector0 .getElementAsDouble(currentElementIndex0), vector1 .getElementAsDouble(currentElementIndex1)));
                }
                currentElementIndex0 += 1;
                if (currentElementIndex0 == length0) {
                    currentElementIndex0 = 0;
                }
                currentElementIndex1 += 1;
                if (currentElementIndex1 == length1) {
                    currentElementIndex1 = 0;
                }
            }
            if (length1 == cycles) {
                builder.copyAttributesFrom(vector1);
            }
            if (length0 == cycles) {
                builder.copyAttributesFrom(vector0);
            }
            return builder.build();
        } else {
            if (((arg0 .length() == 0)||((arg0 instanceof Vector)&&ComplexVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0))))&&((arg1 .length() == 0)||((arg1 instanceof Vector)&&ComplexVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg1))))) {
                Vector vector0 = ((Vector) WrapperRuntime.convertToVector(arg0));
                int length0 = vector0 .length();
                int currentElementIndex0 = 0;
                Vector vector1 = ((Vector) WrapperRuntime.convertToVector(arg1));
                int length1 = vector1 .length();
                int currentElementIndex1 = 0;
                int cycles = 0;
                if (length0 == 0) {
                    return ComplexVector.EMPTY;
                }
                if (length0 >cycles) {
                    cycles = length0;
                }
                if (length1 == 0) {
                    return ComplexVector.EMPTY;
                }
                if (length1 >cycles) {
                    cycles = length1;
                }
                ComplexVector.Builder builder = new ComplexVector.Builder(cycles);
                for (int i = 0; (i!= cycles); i ++) {
                    if (vector0 .isElementNA(currentElementIndex0)||vector1 .isElementNA(currentElementIndex1)) {
                        builder.setNA(i);
                    } else {
                        builder.set(i, Ops.plus(vector0 .getElementAsComplex(currentElementIndex0), vector1 .getElementAsComplex(currentElementIndex1)));
                    }
                    currentElementIndex0 += 1;
                    if (currentElementIndex0 == length0) {
                        currentElementIndex0 = 0;
                    }
                    currentElementIndex1 += 1;
                    if (currentElementIndex1 == length1) {
                        currentElementIndex1 = 0;
                    }
                }
                if (length1 == cycles) {
                    builder.copyAttributesFrom(vector1);
                }
                if (length0 == cycles) {
                    builder.copyAttributesFrom(vector0);
                }
                return builder.build();
            } else {
                throw new EvalException(String.format("Invalid argument:\n\t+(%s, %s)\n\tExpected:\n\t+(Vector)\n\t+(Complex, Complex)\n\t+(double, double)", arg0 .getTypeName(), arg1 .getTypeName()));
            }
        }
    }

}
