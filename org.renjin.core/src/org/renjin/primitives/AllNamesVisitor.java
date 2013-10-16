package org.renjin.primitives;

import java.util.Set;

import org.renjin.primitives.annotations.Primitive;
import org.renjin.sexp.ExpressionVector;
import org.renjin.sexp.FunctionCall;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.SexpVisitor;
import org.renjin.sexp.StringArrayVector;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Symbol;

import com.google.common.collect.Sets;

public class AllNamesVisitor extends SexpVisitor<StringVector> {

  private StringVector.Builder names = StringVector.newBuilder();
  private Set<Symbol> set = Sets.newIdentityHashSet();
  private boolean includeFunctionNames;
  private int maxNames;
  private boolean unique;
  
  @Override
  public void visit(ExpressionVector vector) {
    for(SEXP expr : vector) {
      expr.accept(this);
    }
  }

  @Override
  public void visit(FunctionCall call) {
    if(includeFunctionNames) {
      call.getFunction().accept(this);
    }
    for(SEXP expr : call.getArguments().values()) {
      expr.accept(this);
    }
  }

  @Override
  public void visit(Symbol name) {
    if(!unique || !set.contains(name)) {
      if(maxNames == -1 || names.length() < maxNames) {
        names.add(StringArrayVector.valueOf(name.getPrintName()));
        set.add(name);
      }
    }
  }
  
  @Primitive("all.names")
  public static StringVector allNames(SEXP expr, boolean function, int maxNames, boolean unique) {
    AllNamesVisitor visitor = new AllNamesVisitor();
    visitor.includeFunctionNames = function;
    visitor.maxNames = maxNames;
    visitor.unique = unique;
    expr.accept(visitor);
    return visitor.names.build();
  }
}
