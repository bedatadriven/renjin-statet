
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.sexp.AbstractSEXP;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.ListVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Symbol;

public class R$primitive$all
    extends BuiltinFunction
{


    public R$primitive$all() {
        super("all");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            ListVector.NamedBuilder varArgs = new ListVector.NamedBuilder();
            ListVector varArgList;
            boolean flag0 = false;
            while (argIt.hasNext()) {
                PairList.Node node = argIt.nextNode();
                SEXP value = node.getValue();
                SEXP evaluated;
                if (value == Symbol.MISSING_ARG) {
                    evaluated = value;
                } else {
                    evaluated = context.evaluate(value, environment);
                }
                if (!node.hasName()) {
                    varArgs.add(evaluated);
                } else {
                    String name = node.getName();
                    if ("na.rm".equals(name)) {
                        flag0 = WrapperRuntime.convertToBooleanPrimitive(evaluated);
                    } else {
                        varArgs.add(name, evaluated);
                    }
                }
            }
            varArgList = varArgs.build();
            if ((varArgs.length()> 0)&&((AbstractSEXP) varArgList.getElementAsSEXP(0)).isObject()) {
                SEXP genericResult = S3 .tryDispatchSummaryFromPrimitive(context, environment, call, "all", varArgList, flag0);
                if (genericResult!= null) {
                    return genericResult;
                }
            }
            return WrapperRuntime.wrapResult(Summary.all(varArgList, flag0));
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tall(..., logical(1))", e.getMessage());
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
            ListVector.NamedBuilder varArgs = new ListVector.NamedBuilder();
            ListVector varArgList;
            boolean flag0 = false;
            for (int i = 0; (i<(args.length)); i ++) {
                varArgs.add(argNames[i], args[i]);
            }
            varArgList = varArgs.build();
            return WrapperRuntime.wrapResult(Summary.all(varArgList, flag0));
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, String[] argNames, SEXP[] args) {
        return R$primitive$all.doApply(context, environment, call, argNames, args);
    }

}
