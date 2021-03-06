
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.ComplexVector;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.IntVector;
import org.renjin.sexp.Null;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Symbols;
import org.renjin.sexp.Vector;

public class R$primitive$Mod
    extends BuiltinFunction
{


    public R$primitive$Mod() {
        super("Mod");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0);
            }
            throw new EvalException("Mod: too many arguments, expected at most 1.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tMod(Complex)\n\tMod(IntVector)\n\tMod(DoubleVector)\n\tMod(Null)", e.getMessage());
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
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("Mod: max arity is 1");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$Mod.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0)
        throws Exception
    {
        if (arg0 instanceof Null) {
            return ComplexGroup.Mod(((Null) arg0));
        } else {
            if (arg0 instanceof IntVector) {
                return ComplexGroup.Mod(((IntVector) arg0));
            } else {
                if (arg0 instanceof DoubleVector) {
                    return ComplexGroup.Mod(((DoubleVector) arg0));
                } else {
                    if ((arg0 .length() == 0)||((arg0 instanceof Vector)&&ComplexVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0)))) {
                        Vector vector0 = ((Vector) WrapperRuntime.convertToVector(arg0));
                        int length0 = vector0 .length();
                        int currentElementIndex0 = 0;
                        int cycles = 0;
                        if (length0 == 0) {
                            return DoubleVector.EMPTY;
                        }
                        if (length0 >cycles) {
                            cycles = length0;
                        }
                        org.renjin.sexp.DoubleArrayVector.Builder builder = new org.renjin.sexp.DoubleArrayVector.Builder(cycles);
                        for (int i = 0; (i!= cycles); i ++) {
                            if (vector0 .isElementNA(currentElementIndex0)) {
                                builder.setNA(i);
                            } else {
                                builder.set(i, ComplexGroup.Mod(vector0 .getElementAsComplex(currentElementIndex0)));
                            }
                            currentElementIndex0 += 1;
                            if (currentElementIndex0 == length0) {
                                currentElementIndex0 = 0;
                            }
                        }
                        if (length0 == cycles) {
                            builder.copySomeAttributesFrom(vector0, Symbols.DIM, Symbols.DIMNAMES, Symbols.NAMES);
                        }
                        return builder.build();
                    } else {
                        throw new EvalException(String.format("Invalid argument:\n\tMod(%s)\n\tExpected:\n\tMod(Complex)\n\tMod(IntVector)\n\tMod(DoubleVector)\n\tMod(Null)", arg0 .getTypeName()));
                    }
                }
            }
        }
    }

}
