
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

public class R$primitive$axis
    extends BuiltinFunction
{


    public R$primitive$axis() {
        super("axis");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            ArgumentIterator argIt = new ArgumentIterator(context, environment, args);
            ListVector.NamedBuilder varArgs = new ListVector.NamedBuilder();
            ListVector varArgList;
            SEXP pos0;
            SEXP pos1;
            SEXP pos2;
            SEXP pos3;
            SEXP pos4;
            SEXP pos5;
            SEXP pos6;
            SEXP pos7;
            SEXP pos8;
            SEXP pos9;
            SEXP pos10;
            SEXP pos11;
            SEXP pos12;
            SEXP pos13;
            SEXP pos14;
            pos0 = ((SEXP) argIt.evalNext());
            pos1 = ((SEXP) argIt.evalNext());
            pos2 = ((SEXP) argIt.evalNext());
            pos3 = ((SEXP) argIt.evalNext());
            pos4 = ((SEXP) argIt.evalNext());
            pos5 = ((SEXP) argIt.evalNext());
            pos6 = ((SEXP) argIt.evalNext());
            pos7 = ((SEXP) argIt.evalNext());
            pos8 = ((SEXP) argIt.evalNext());
            pos9 = ((SEXP) argIt.evalNext());
            pos10 = ((SEXP) argIt.evalNext());
            pos11 = ((SEXP) argIt.evalNext());
            pos12 = ((SEXP) argIt.evalNext());
            pos13 = ((SEXP) argIt.evalNext());
            pos14 = ((SEXP) argIt.evalNext());
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
            Plot.axis(pos0, pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, pos9, pos10, pos11, pos12, pos13, pos14, varArgList);
            return Null.INSTANCE;
        } catch (ArgumentException e) {
            throw new EvalException(context, "Invalid argument: %s. Expected:\n\taxis(any, any, any, any, any, any, any, any, any, any, any, any, any, any, any, ...)", e.getMessage());
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
            SEXP pos1;
            SEXP pos2;
            SEXP pos3;
            SEXP pos4;
            SEXP pos5;
            SEXP pos6;
            SEXP pos7;
            SEXP pos8;
            SEXP pos9;
            SEXP pos10;
            SEXP pos11;
            SEXP pos12;
            SEXP pos13;
            SEXP pos14;
            pos0 = ((SEXP) args[ 0 ]);
            pos1 = ((SEXP) args[ 1 ]);
            pos2 = ((SEXP) args[ 2 ]);
            pos3 = ((SEXP) args[ 3 ]);
            pos4 = ((SEXP) args[ 4 ]);
            pos5 = ((SEXP) args[ 5 ]);
            pos6 = ((SEXP) args[ 6 ]);
            pos7 = ((SEXP) args[ 7 ]);
            pos8 = ((SEXP) args[ 8 ]);
            pos9 = ((SEXP) args[ 9 ]);
            pos10 = ((SEXP) args[ 10 ]);
            pos11 = ((SEXP) args[ 11 ]);
            pos12 = ((SEXP) args[ 12 ]);
            pos13 = ((SEXP) args[ 13 ]);
            pos14 = ((SEXP) args[ 14 ]);
            for (int i = 15; (i<(args.length)); i ++) {
                varArgs.add(argNames[i], args[i]);
            }
            varArgList = varArgs.build();
            Plot.axis(pos0, pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, pos9, pos10, pos11, pos12, pos13, pos14, varArgList);
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
        return R$primitive$axis.doApply(context, environment, call, argNames, args);
    }

}
