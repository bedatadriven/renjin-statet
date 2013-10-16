/*
 * R : A Computer Language for Statistical Data Analysis
 * Copyright (C) 1995, 1996  Robert Gentleman and Ross Ihaka
 * Copyright (C) 1997--2008  The R Development Core Team
 * Copyright (C) 2003, 2004  The R Foundation
 * Copyright (C) 2010 bedatadriven
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.renjin.primitives;

import com.google.common.collect.Lists;
import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.primitives.annotations.ArgumentList;
import org.renjin.primitives.annotations.Current;
import org.renjin.primitives.annotations.Generic;
import org.renjin.primitives.annotations.Primitive;
import org.renjin.sexp.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {

  public static Vector sort(StringVector x, boolean decreasing) {

    if(x.getAttribute(Symbols.NAMES)!= Null.INSTANCE) {
      throw new EvalException("sorting of vectors with names not yet implemented!");
    }

    String sorted[] = x.toArray();

    if(decreasing) {
      Arrays.sort(sorted, Collections.reverseOrder());
    }else{
      Arrays.sort(sorted);
    }

    return new StringArrayVector(sorted, x.getAttributes());
  }

  public static Vector sort(DoubleVector x, boolean decreasing) {

    if(x.getAttribute(Symbols.NAMES)!= Null.INSTANCE) {
      throw new EvalException("sorting of vectors with names not yet implemented!");
    }

    double sorted[] = x.toDoubleArray();

    Arrays.sort(sorted);

    if(decreasing) {
      reverse(sorted);
    }

    return (Vector) DoubleArrayVector.unsafe(sorted).setAttributes(x.getAttributes());
  }
  
  private static void reverse(double[] b) {
    int left  = 0;          
    int right = b.length-1; 

    while (left < right) {
      double temp = b[left]; 
      b[left]  = b[right]; 
      b[right] = temp;

      // move the bounds toward the center
      left++;
      right--;
    }
  }

  public static Vector sort(IntVector x, boolean decreasing) {

    if(x.getAttribute(Symbols.NAMES)!= Null.INSTANCE) {
      throw new EvalException("sorting of vectors with names not yet implemented!");
    }

    int sorted[] = x.toIntArray();
    
    Arrays.sort(sorted);

    if(decreasing) {
      reverse(sorted);
    }

    return new IntArrayVector(sorted, x.getAttributes());
  }

  @Primitive("is.unsorted")
  public static boolean isUnsorted(AtomicVector x, boolean strictly) {
    for(int i=1;i<x.length();++i) {
      int z = x.compare(i-1, i);
      if(z > 0) {
        return true;
      } else if(strictly && z == 0) {
        return true;
      }
    }
    return false;
  }
  
  @Primitive("is.unsorted")
  public static LogicalVector isUnsorted(ListVector x, boolean strictly) {
    return LogicalVector.NA_VECTOR;
  }
 
  @Primitive("qsort")
  public static DoubleVector qsort(DoubleVector x, LogicalVector returnIndexes) {

    if(returnIndexes.isElementTrue(0)) {
      throw new EvalException("qsort(indexes=TRUE) not yet implemented");
    }
    
    double[] values = x.toDoubleArray();
    Arrays.sort(values);
    
    DoubleVector sorted = new DoubleArrayVector(values, x.getAttributes());
    
    // drop the names attributes if present because it will not be sorted
    return (DoubleVector)sorted
            .setAttribute(Symbols.NAMES, Null.INSTANCE);  
  }
  
  @Primitive("psort")
  public static DoubleVector psort(DoubleVector x, Vector indexes) {
    // stub implementation: we just do a full sort
    return qsort(x, LogicalVector.FALSE);
  }

  @Primitive("qsort")
  public static IntVector qsort(IntVector x, LogicalVector returnIndexes) {

    if(returnIndexes.isElementTrue(0)) {
      throw new EvalException("qsort(indexes=TRUE) not yet implemented");
    }
    
    int[] values = x.toIntArray();
    Arrays.sort(values);
    
    IntVector sorted = new IntArrayVector(values, x.getAttributes());
    
    // drop the names attributes if present because it will not be sorted
    return (IntVector)sorted
            .setAttribute(Symbols.NAMES, Null.INSTANCE);  
  }
  
  @Primitive("psort")
  public static IntVector psort(IntVector x, Vector indexes) {
    return qsort(x, LogicalVector.FALSE);
  }
  
  private static void reverse(int[] b) {
    int left  = 0;          
    int right = b.length-1; 

    while (left < right) {
      int temp = b[left]; 
      b[left]  = b[right]; 
      b[right] = temp;

      // move the bounds toward the center
      left++;
      right--;
    }
  }
  
  /**
   * Returns a permutation which rearranges its first argument into ascending or
   * descending order, breaking ties by further arguments.
   *
   * <p>This function is like a spreadsheet sort function.
   * Each argument is a column.
   *
   * @param columns
   * @return
   */
  public static Vector order(boolean naLast, final boolean decreasing, @ArgumentList final ListVector columns) {
        
    if (columns.length() == 0) {
      return Null.INSTANCE;
    }
    
    int numRows = columns.getElementAsSEXP(0).length();

    for (int i = 0; i != columns.length(); ++i) {
      if (columns.getElementAsSEXP(i).length() != numRows) {
        throw new EvalException("argument lengths differ");
      }
    }

    List<Integer> ordering = Lists.newArrayListWithCapacity(numRows);
    for (int i = 0; i != numRows; ++i) {
      ordering.add(i);
    }

    Collections.sort(ordering, new Comparator<Integer>() {

      @Override
      public int compare(Integer row1, Integer row2) {
        int col = 0;
        int rel;
        while ((rel = compare(row1, row2, col)) == 0) {
          col++;
          if (col == columns.length()) {
            return 0;
          }
        }
        return decreasing ? -rel : rel;
      }

      private int compare(Integer row1, Integer row2, int col) {
        return ((AtomicVector)columns.get(col)).compare(row1, row2);
      }
    });

    IntArrayVector.Builder result = new IntArrayVector.Builder();
    for (Integer index : ordering) {
      result.add(index + 1);
    }

    return result.build();
  }   

  @Primitive("which.min")
  public static IntVector whichMin(Vector v) {
    if (v.length() == 0) {
      IntArrayVector.Builder b = new IntArrayVector.Builder();
      return (b.build());
    }
    int minIndex = 0;
    double globalMin = v.getElementAsDouble(0);
    //this loop would be started from 1 but it needs more code. I think this is fine.
    for (int i = 0; i < v.length(); i++) {
      if (v.getElementAsDouble(i) < globalMin) {
        globalMin = v.getElementAsDouble(i);
        minIndex = i;
      }
    }
    return (new IntArrayVector(minIndex + 1));
  }

  @Primitive("which.max")
  public static IntVector whichMax(Vector v) {
    if (v.length() == 0) {
      IntArrayVector.Builder b = new IntArrayVector.Builder();
      return (b.build());
    }
    int maxIndex = 0;
    double globalMax = v.getElementAsDouble(0);
    for (int i = 0; i < v.length(); i++) {
      if (v.getElementAsDouble(i) > globalMax) {
        globalMax = v.getElementAsDouble(i);
        maxIndex = i;
      }
    }
    return (new IntArrayVector(maxIndex + 1));
  }
  
  @Primitive
  @Generic
  public static SEXP xtfrm(@Current Context context, SEXP x) {
    FunctionCall defaultCall = FunctionCall.newCall(Symbol.get("xtfrm.default"), x);
    return context.evaluate(defaultCall);
  }
}
