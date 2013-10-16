
package org.renjin.primitives;

import org.renjin.primitives.vector.DeferredComputation;
import org.renjin.sexp.AttributeMap;
import org.renjin.sexp.IntVector;
import org.renjin.sexp.LogicalVector;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Vector;

public class R$primitive$$bang$deferred_b
    extends LogicalVector
    implements DeferredComputation
{

    private final Vector arg0;
    private final int argLength0;
    private int length;

    public R$primitive$$bang$deferred_b(Vector arg0, AttributeMap attributes) {
        super(attributes);
        length = 0;
        this.arg0 = arg0;
        argLength0 = arg0 .length();
        length = argLength0;
    }

    public int getElementAsRawLogical(int index) {
        int arg0_i = arg0 .getElementAsRawLogical(index);
        if (IntVector.isNA(arg0_i)) {
            return IntVector.NA;
        }
        if (Ops.not((arg0_i!= 0))) {
            return  1;
        } else {
            return  0;
        }
    }

    public int length() {
        return length;
    }

    public SEXP cloneWithNewAttributes(AttributeMap attributes) {
        return new R$primitive$$bang$deferred_b(arg0, attributes);
    }

    public Vector[] getOperands() {
        return new Vector[] {arg0 };
    }

    public String getComputationName() {
        return "!";
    }

    public static int compute(int p0) {
        if (Ops.not((p0 != 0))) {
            return  1;
        } else {
            return  0;
        }
    }

}
