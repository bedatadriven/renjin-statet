/**
 * 
 */
package de.walware.statet.r.internal.console.ui.launching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.walware.statet.r.console.ui.launching.RConsoleLaunching;

/**
 * @author rytina (andy.rytina@gmail.com)
 *
 */
public class CustomRConsoleMainTab extends RConsoleMainTab{

	
	
	@Override
	protected RConsoleType[] loadTypes() {
		final List<RConsoleType> types = new ArrayList<RConsoleType>(Arrays.asList(super.loadTypes()));
		types.add(new RConsoleType("Renjin", CustomRConsoleLaunchDelegate.LOCAL_RENJIN, false, false)); 
		return types.toArray(new RConsoleType[types.size()]);
	}
}
