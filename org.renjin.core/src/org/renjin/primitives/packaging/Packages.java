package org.renjin.primitives.packaging;

import java.io.IOException;

import org.renjin.eval.Context;
import org.renjin.primitives.annotations.Current;
import org.renjin.primitives.annotations.Evaluate;
import org.renjin.primitives.annotations.Primitive;
import org.renjin.sexp.Environment;
import org.renjin.sexp.HashFrame;
import org.renjin.sexp.PairList;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Symbol;
import org.renjin.sexp.Symbols;

public class Packages {

  @Primitive
  public static void library(
      @Current Context context,
      @Current NamespaceRegistry namespaceRegistry, 
      @Evaluate(false) Symbol packageName) throws IOException {
    
    Namespace namespace = namespaceRegistry.getNamespace(packageName);
    
    // Create the package environment
    Environment packageEnv = context.getGlobalEnvironment().insertAbove(new HashFrame());
    packageEnv.setAttribute(Symbols.NAME, StringVector.valueOf("package:" + packageName));
    // Copy in the namespace's exports
    namespace.copyExportsTo(packageEnv);
    
    // Load datasets
    for(String dataset : namespace.getPackage().getDatasets()) {
      System.out.println(dataset + " loading");
      SEXP data = namespace.getPackage().loadDataset(dataset);
      System.out.println(dataset + " => " + data.getTypeName());
      if(data instanceof PairList) {
        PairList list = (PairList)data;
        for(PairList.Node node : list.nodes()) {
          System.out.println("found dataset " + node.getTag());
          packageEnv.setVariable(node.getTag(), node.getValue());
        }
      } else {
        System.out.println(dataset + " is not a pairlist, ignoring");
      }
    }
    
  }
  
  
}
