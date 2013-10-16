
package org.renjin.primitives;

import org.renjin.primitives.vector.DeferredComputation;
import org.renjin.sexp.AttributeMap;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Vector;

public class R$primitive$sinh$deferred_d
    extends DoubleVector
    implements DeferredComputation
{

    private final Vector arg0;
    private final int argLength0;
    private int length;

    public R$primitive$sinh$deferred_d(Vector arg0, AttributeMap attributes) {
        super(attributes);
        length = 0;
        this.arg0 = arg0;
        argLength0 = arg0 .length();
        length = argLength0;
    }

    public double getElementAsDouble(int index) {
        double arg0_i = arg0 .getElementAsDouble(index);
        if (DoubleVector.isNA(arg0_i)) {
            return DoubleVector.NA;
        }
        return Math.sinh(arg0_i);
    }

    public int length() {
        return length;
    }

    public SEXP cloneWithNewAttributes(AttributeMap attributes) {
        return new R$primitive$sinh$deferred_d(arg0, attributes);
    }

    public Vector[] getOperands() {
        return new Vector[] {arg0 };
    }

    public String getComputationName() {
        return "sinh";
    }

    public static double compute(double p0) {
        return Math.sinh(p0);
    }

}
