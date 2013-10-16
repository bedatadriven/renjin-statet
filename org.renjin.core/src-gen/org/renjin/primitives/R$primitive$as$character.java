
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.sexp.AbstractSEXP;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Symbol;
import org.renjin.sexp.Vector;

public class R$primitive$as$character
    extends BuiltinFunction
{


    public R$primitive$as$character() {
        super("as.character");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            if (((AbstractSEXP) s0).isObject()) {
                SEXP genericResult = S3 .tryDispatchFromPrimitive(context, environment, call, "as.character", s0, args);
                if (genericResult!= null) {
                    return genericResult;
                }
            }
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0);
            }
            throw new EvalException("as.character: too many arguments, expected at most 1.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tas.character(pairlist)\n\tas.character(Vector)\n\tas.character(Symbol)\n\tas.character(Environment)", e.getMessage());
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
        throw new EvalException("as.character: max arity is 1");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$as$character.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0)
        throws Exception
    {
        if (arg0 instanceof Vector) {
            return Types.asCharacter(((Vector) arg0));
        } else {
            if (arg0 instanceof PairList.Node) {
                return Types.asCharacter(((PairList.Node) arg0));
            } else {
                if (arg0 instanceof Symbol) {
                    return Types.asCharacter(((Symbol) arg0));
                } else {
                    if (arg0 instanceof Environment) {
                        return Types.asCharacter(((Environment) arg0));
                    } else {
                        throw new EvalException(String.format("Invalid argument:\n\tas.character(%s)\n\tExpected:\n\tas.character(pairlist)\n\tas.character(Vector)\n\tas.character(Symbol)\n\tas.character(Environment)", arg0 .getTypeName()));
                    }
                }
            }
        }
    }

}
