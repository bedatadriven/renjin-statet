
package org.renjin.primitives;

import org.renjin.primitives.vector.DeferredComputation;
import org.renjin.sexp.AttributeMap;
import org.renjin.sexp.LogicalVector;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Vector;

public class R$primitive$$amp$deferred_dd
    extends LogicalVector
    implements DeferredComputation
{

    private final Vector arg0;
    private final int argLength0;
    private final Vector arg1;
    private final int argLength1;
    private int length;

    public R$primitive$$amp$deferred_dd(Vector arg0, Vector arg1, AttributeMap attributes) {
        super(attributes);
        length = 0;
        this.arg0 = arg0;
        argLength0 = arg0 .length();
        this.arg1 = arg1;
        argLength1 = arg1 .length();
        length = Math.max(argLength0, argLength1);
    }

    public int getElementAsRawLogical(int index) {
        int i0;
        if (index<argLength0) {
            i0 = index;
        } else {
            i0 = (index%argLength0);
        }
        double arg0_i = arg0 .getElementAsDouble(i0);
        int i1;
        if (index<argLength1) {
            i1 = index;
        } else {
            i1 = (index%argLength1);
        }
        double arg1_i = arg1 .getElementAsDouble(i1);
        return Ops.and(arg0_i, arg1_i).getInternalValue();
    }

    public int length() {
        return length;
    }

    public SEXP cloneWithNewAttributes(AttributeMap attributes) {
        return new R$primitive$$amp$deferred_dd(arg0, arg1, attributes);
    }

    public Vector[] getOperands() {
        return new Vector[] {arg0, arg1 };
    }

    public String getComputationName() {
        return "&";
    }

    public static int compute(double p0, double p1) {
        return Ops.and(p0, p1).getInternalValue();
    }

}
