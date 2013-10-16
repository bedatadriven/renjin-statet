
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

public class R$primitive$rect
    extends BuiltinFunction
{


    public R$primitive$rect() {
        super("rect");
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
            Vector pos5;
            Vector pos6;
            Vector pos7;
            Vector pos8;
            pos1 = ((Vector) argIt.evalNext());
            pos2 = ((Vector) argIt.evalNext());
            pos3 = ((Vector) argIt.evalNext());
            pos4 = ((Vector) argIt.evalNext());
            pos5 = ((Vector) argIt.evalNext());
            pos6 = ((Vector) argIt.evalNext());
            pos7 = ((Vector) argIt.evalNext());
            pos8 = ((Vector) argIt.evalNext());
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
            Plot.rect(context, pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, varArgList);
            return Null.INSTANCE;
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\trect(Vector, Vector, Vector, Vector, Vector, Vector, Vector, Vector, ...)", e.getMessage());
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
            Vector pos5;
            Vector pos6;
            Vector pos7;
            Vector pos8;
            pos1 = ((Vector) args[ 0 ]);
            pos2 = ((Vector) args[ 1 ]);
            pos3 = ((Vector) args[ 2 ]);
            pos4 = ((Vector) args[ 3 ]);
            pos5 = ((Vector) args[ 4 ]);
            pos6 = ((Vector) args[ 5 ]);
            pos7 = ((Vector) args[ 6 ]);
            pos8 = ((Vector) args[ 7 ]);
            for (int i = 8; (i<(args.length)); i ++) {
                varArgs.add(argNames[i], args[i]);
            }
            varArgList = varArgs.build();
            Plot.rect(context, pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, varArgList);
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
        return R$primitive$rect.doApply(context, environment, call, argNames, args);
    }

}
