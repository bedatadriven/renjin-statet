
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.processor.ArgumentException;
import org.renjin.primitives.annotations.processor.ArgumentIterator;
import org.renjin.primitives.graphics.Plot;
import org.renjin.sexp.BuiltinFunction;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.ListVector;
import org.renjin.sexp.Null;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Symbol;
import org.renjin.sexp.Vector;

public class R$primitive$plot$window
    extends BuiltinFunction
{


    public R$primitive$plot$window() {
        super("plot.window");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            ListVector.NamedBuilder varArgs = new ListVector.NamedBuilder();
            ListVector varArgList;
            Vector pos1;
            Vector pos2;
            Vector pos3;
            Vector pos4;
            pos1 = ((Vector) argIt.evalNext());
            pos2 = ((Vector) argIt.evalNext());
            pos3 = ((Vector) argIt.evalNext());
            pos4 = ((Vector) argIt.evalNext());
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
            Plot.plotWindow(context, pos1, pos2, pos3, pos4, varArgList);
            return Null.INSTANCE;
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\tplot.window(Vector, Vector, Vector, Vector, ...)", e.getMessage());
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
            Vector pos1;
            Vector pos2;
            Vector pos3;
            Vector pos4;
            pos1 = ((Vector) args[ 0 ]);
            pos2 = ((Vector) args[ 1 ]);
            pos3 = ((Vector) args[ 2 ]);
            pos4 = ((Vector) args[ 3 ]);
            for (int i = 4; (i<(args.length)); i ++) {
                varArgs.add(argNames[i], args[i]);
            }
            varArgList = varArgs.build();
            Plot.plotWindow(context, pos1, pos2, pos3, pos4, varArgList);
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
        return R$primitive$plot$window.doApply(context, environment, call, argNames, args);
    }

}
