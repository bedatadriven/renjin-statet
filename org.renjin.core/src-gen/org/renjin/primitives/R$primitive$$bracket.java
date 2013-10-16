
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.subset.Subsetting;
import org.renjin.sexp.AbstractSEXP;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.ListVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.SpecialFunction;
import org.renjin.sexp.Symbol;

public class R$primitive$$bracket
    extends SpecialFunction
{


    public R$primitive$$bracket() {
        super("[");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            ListVector.NamedBuilder varArgs = new ListVector.NamedBuilder();
            ListVector varArgList;
            SEXP pos0;
            boolean flag0 = true;
            pos0 = ((SEXP) argIt.evalNext());
            if (((AbstractSEXP) pos0).isObject()) {
                SEXP genericResult = S3 .tryDispatchFromPrimitive(context, environment, call, "[", pos0, args);
                if (genericResult!= null) {
                    return genericResult;
                }
            }
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
                    if ("drop".equals(name)) {
                        flag0 = WrapperRuntime.convertToBooleanPrimitive(evaluated);
                    } else {
                        varArgs.add(name, evaluated);
                    }
                }
            }
            varArgList = varArgs.build();
            return Subsetting.getSubset(pos0, varArgList, flag0);
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\t[(any, ..., logical(1))", e.getMessage());
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
            SEXP pos0;
            boolean flag0 = true;
            pos0 = ((SEXP) args[ 0 ]);
            for (int i = 1; (i<(args.length)); i ++) {
                varArgs.add(argNames[i], args[i]);
            }
            varArgList = varArgs.build();
            return Subsetting.getSubset(pos0, varArgList, flag0);
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
        return R$primitive$$bracket.doApply(context, environment, call, argNames, args);
    }

}
