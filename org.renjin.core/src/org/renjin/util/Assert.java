/**
 * 
 */
package org.renjin.util;

import javax.tools.JavaCompiler;

/**
 * @author rytina (andy.rytina@gmail.com)
 *
 */
public class Assert {

	public static void isNotNull(Object object, String string) {
		if(object==null){
			throw new RuntimeException(string);
		}
	}

}
