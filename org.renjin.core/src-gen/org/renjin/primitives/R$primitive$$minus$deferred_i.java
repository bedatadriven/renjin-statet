
package org.renjin.primitives;

import org.renjin.primitives.vector.DeferredComputation;
import org.renjin.sexp.AttributeMap;
import org.renjin.sexp.IntVector;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Vector;

public class R$primitive$$minus$deferred_i
    extends IntVector
    implements DeferredComputation
{

    private final Vector arg0;
    private final int argLength0;
    private int length;

    public R$primitive$$minus$deferred_i(Vector arg0, AttributeMap attributes) {
        super(attributes);
        length = 0;
        this.arg0 = arg0;
        argLength0 = arg0 .length();
        length = argLength0;
    }

    public int getElementAsInt(int index) {
        int arg0_i = arg0 .getElementAsInt(index);
        if (IntVector.isNA(arg0_i)) {
            return IntVector.NA;
        }
        return Ops.minus(arg0_i);
    }

    public int length() {
        return length;
    }

    public SEXP cloneWithNewAttributes(AttributeMap attributes) {
        return new R$primitive$$minus$deferred_i(arg0, attributes);
    }

    public Vector[] getOperands() {
        return new Vector[] {arg0 };
    }

    public String getComputationName() {
        return "-";
    }

    public static int compute(int p0) {
        return Ops.minus(p0);
    }

}
