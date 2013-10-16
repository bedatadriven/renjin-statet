
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.models.Models;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.SpecialFunction;

public class R$primitive$$tilde
    extends SpecialFunction
{


    public R$primitive$$tilde() {
        super("~");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            return Models.tilde(context, environment, call);
        } catch (EvalException e) {
            e.initContext(context);
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new EvalException(e);
        }
    }

}
