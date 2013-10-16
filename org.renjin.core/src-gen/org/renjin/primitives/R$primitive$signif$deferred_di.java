
package org.renjin.primitives;

import org.renjin.primitives.vector.DeferredComputation;
import org.renjin.sexp.AttributeMap;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.IntVector;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Vector;

public class R$primitive$signif$deferred_di
    extends DoubleVector
    implements DeferredComputation
{

    private final Vector arg0;
    private final int argLength0;
    private final Vector arg1;
    private final int argLength1;
    private int length;

    public R$primitive$signif$deferred_di(Vector arg0, Vector arg1, AttributeMap attributes) {
        super(attributes);
        length = 0;
        this.arg0 = arg0;
        argLength0 = arg0 .length();
        this.arg1 = arg1;
        argLength1 = arg1 .length();
        length = Math.max(argLength0, argLength1);
    }

    public double getElementAsDouble(int index) {
        int i0;
        if (index<argLength0) {
            i0 = index;
        } else {
            i0 = (index%argLength0);
        }
        double arg0_i = arg0 .getElementAsDouble(i0);
        if (DoubleVector.isNA(arg0_i)) {
            return DoubleVector.NA;
        }
        int i1;
        if (index<argLength1) {
            i1 = index;
        } else {
            i1 = (index%argLength1);
        }
        int arg1_i = arg1 .getElementAsInt(i1);
        if (IntVector.isNA(arg1_i)) {
            return DoubleVector.NA;
        }
        return MathExt.signif(arg0_i, arg1_i);
    }

    public int length() {
        return length;
    }

    public SEXP cloneWithNewAttributes(AttributeMap attributes) {
        return new R$primitive$signif$deferred_di(arg0, arg1, attributes);
    }

    public Vector[] getOperands() {
        return new Vector[] {arg0, arg1 };
    }

    public String getComputationName() {
        return "signif";
    }

    public static double compute(double p0, int p1) {
        return MathExt.signif(p0, p1);
    }

}
