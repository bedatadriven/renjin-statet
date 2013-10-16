
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.ListVector;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Symbol;

public class R$primitive$$Call
    extends BuiltinFunction
{


    public R$primitive$$Call() {
        super(".Call");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            ListVector.NamedBuilder varArgs = new ListVector.NamedBuilder();
            ListVector varArgList;
            String pos2;
            String flag0 = null;
            pos2 = WrapperRuntime.convertToString(argIt.evalNext());
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
                    if ("PACKAGE".equals(name)) {
                        flag0 = WrapperRuntime.convertToString(evaluated);
                    } else {
                        varArgs.add(name, evaluated);
                    }
                }
            }
            varArgList = varArgs.build();
            return Native.dotCall(context, environment, pos2, varArgList, flag0);
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\t.Call(character(1), ..., character(1))", e.getMessage());
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
            String pos2;
            String flag0 = null;
            pos2 = WrapperRuntime.convertToString(args[ 0 ]);
            for (int i = 1; (i<(args.length)); i ++) {
                varArgs.add(argNames[i], args[i]);
            }
            varArgList = varArgs.build();
            return Native.dotCall(context, environment, pos2, varArgList, flag0);
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
        return R$primitive$$Call.doApply(context, environment, call, argNames, args);
    }

}
