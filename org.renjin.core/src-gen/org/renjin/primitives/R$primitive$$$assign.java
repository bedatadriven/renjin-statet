
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.subset.Subsetting;
import org.renjin.sexp.AbstractSEXP;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.ListVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.SpecialFunction;
import org.renjin.sexp.Symbol;

public class R$primitive$$$assign
    extends SpecialFunction
{


    public R$primitive$$$assign() {
        super("$<-");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            SEXP s0 = argIt.evalNext();
            if (((AbstractSEXP) s0).isObject()) {
                SEXP genericResult = S3 .tryDispatchFromPrimitive(context, environment, call, "$<-", s0, args);
                if (genericResult!= null) {
                    return genericResult;
                }
            }
            SEXP s1 = argIt.next();
            SEXP s2 = argIt.evalNext();
            if (!argIt.hasNext()) {
                return this.doApply(context, environment, s0, s1, s2);
            }
            throw new EvalException("$<-: too many arguments, expected at most 3.");
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\t$<-(list, Symbol, any)\n\t$<-(Environment, Symbol, any)\n\t$<-(pairlist, Symbol, any)", e.getMessage());
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
            if ((args.length) == 3) {
                return doApply(context, environment, args[ 0 ], args[ 1 ], args[ 2 ]);
            }
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
        throw new EvalException("$<-: max arity is 3");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$$$assign.doApply(context, environment, call, argNames, args);
    }

    public static SEXP doApply(Context context, Environment environment, SEXP arg0, SEXP arg1, SEXP arg2)
        throws Exception
    {
        if (((arg0 instanceof ListVector)&&(arg1 instanceof Symbol))&&(arg2 instanceof SEXP)) {
            return Subsetting.setElementByName(((ListVector) arg0), ((Symbol) arg1), ((SEXP) arg2));
        } else {
            if (((arg0 instanceof Environment)&&(arg1 instanceof Symbol))&&(arg2 instanceof SEXP)) {
                return Subsetting.setElementByName(((Environment) arg0), ((Symbol) arg1), ((SEXP) arg2));
            } else {
                if (((arg0 instanceof PairList.Node)&&(arg1 instanceof Symbol))&&(arg2 instanceof SEXP)) {
                    return Subsetting.setElementByName(((PairList.Node) arg0), ((Symbol) arg1), ((SEXP) arg2));
                } else {
                    throw new EvalException(String.format("Invalid argument:\n\t$<-(%s, %s, %s)\n\tExpected:\n\t$<-(list, Symbol, any)\n\t$<-(Environment, Symbol, any)\n\t$<-(pairlist, Symbol, any)", arg0 .getTypeName(), arg1 .getTypeName(), arg2 .getTypeName()));
                }
            }
        }
    }

}
