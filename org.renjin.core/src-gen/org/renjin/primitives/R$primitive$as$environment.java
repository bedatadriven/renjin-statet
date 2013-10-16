
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
import org.renjin.sexp.ListVector;
import org.renjin.sexp.LogicalVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.S4Object;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Vector;

public class R$primitive$as$environment
    extends BuiltinFunction
{


    public R$primitive$as$environment() {
        super("as.environment");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0);
            }
            throw new EvalException("as.environment: too many arguments, expected at most 1.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tas.environment(Environment)\n\tas.environment(integer(1))\n\tas.environment(character(1))\n\tas.environment(list)\n\tas.environment(S4Object)", e.getMessage());
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
        throw new EvalException("as.environment: max arity is 1");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$as$environment.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0)
        throws Exception
    {
        if (((arg0 instanceof IntVector)||(arg0 instanceof DoubleVector))||(arg0 instanceof LogicalVector)) {
            return Types.asEnvironment(context, WrapperRuntime.convertToInt(arg0));
        } else {
            if (arg0 instanceof ListVector) {
                return Types.asEnvironment(((ListVector) arg0));
            } else {
                if (arg0 instanceof Environment) {
                    return Types.asEnvironment(((Environment) arg0));
                } else {
                    if (arg0 instanceof S4Object) {
                        return Types.asEnvironment(((S4Object) arg0));
                    } else {
                        if ((arg0 instanceof Vector)&&StringVector.VECTOR_TYPE.isWiderThanOrEqualTo(((Vector) arg0))) {
                            return Types.asEnvironment(context, WrapperRuntime.convertToString(arg0));
                        } else {
                            throw new EvalException(String.format("Invalid argument:\n\tas.environment(%s)\n\tExpected:\n\tas.environment(Environment)\n\tas.environment(integer(1))\n\tas.environment(character(1))\n\tas.environment(list)\n\tas.environment(S4Object)", arg0 .getTypeName()));
                        }
                    }
                }
            }
        }
    }

}
