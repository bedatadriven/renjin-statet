
package org.renjin.primitives;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.sexp.Environment;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.SpecialFunction;

public class R$primitive$$amp$amp
    extends SpecialFunction
{


    public R$primitive$$amp$amp() {
        super("&&");
    }

    public SEXP apply(Context context, Environment environment, FunctionCall call, PairList args) {
        try {
            return Comparison.and(context, environment, call);
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
