
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.annotations.processor.WrapperRuntime;
import org.renjin.primitives.graphics.Plot;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.ListVector;
import org.renjin.sexp.Null;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Symbol;

public class R$primitive$title
    extends BuiltinFunction
{


    public R$primitive$title() {
        super("title");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            ListVector.NamedBuilder varArgs = new ListVector.NamedBuilder();
            ListVector varArgList;
            SEXP pos1;
            SEXP pos2;
            SEXP pos3;
            SEXP pos4;
            double pos5;
            boolean pos6;
            pos1 = ((SEXP) argIt.evalNext());
            pos2 = ((SEXP) argIt.evalNext());
            pos3 = ((SEXP) argIt.evalNext());
            pos4 = ((SEXP) argIt.evalNext());
            pos5 = WrapperRuntime.convertToDoublePrimitive(argIt.evalNext());
            pos6 = WrapperRuntime.convertToBooleanPrimitive(argIt.evalNext());
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
                    varArgs.add(name, evaluated);
                }
            }
            varArgList = varArgs.build();
            Plot.title(context, pos1, pos2, pos3, pos4, pos5, pos6, varArgList);
            return Null.INSTANCE;
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\ttitle(any, any, any, any, double(1), logical(1), ...)", e.getMessage());
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
            SEXP pos1;
            SEXP pos2;
            SEXP pos3;
            SEXP pos4;
            double pos5;
            boolean pos6;
            pos1 = ((SEXP) args[ 0 ]);
            pos2 = ((SEXP) args[ 1 ]);
            pos3 = ((SEXP) args[ 2 ]);
            pos4 = ((SEXP) args[ 3 ]);
            pos5 = WrapperRuntime.convertToDoublePrimitive(args[ 4 ]);
            pos6 = WrapperRuntime.convertToBooleanPrimitive(args[ 5 ]);
            for (int i = 6; (i<(args.length)); i ++) {
                varArgs.add(argNames[i], args[i]);
            }
            varArgList = varArgs.build();
            Plot.title(context, pos1, pos2, pos3, pos4, pos5, pos6, varArgList);
            return Null.INSTANCE;
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
        return R$primitive$title.doApply(context, environment, call, argNames, args);
    }

}
