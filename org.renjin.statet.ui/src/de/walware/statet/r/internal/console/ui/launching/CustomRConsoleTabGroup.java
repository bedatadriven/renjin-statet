/**
 * 
 */
package de.walware.statet.r.internal.console.ui.launching;

import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.debug.ui.sourcelookup.SourceLookupTab;

import de.walware.statet.nico.ui.util.CommonTabForConsole;
import de.walware.statet.r.internal.console.ui.launching.ExtJavaJRETab;
import de.walware.statet.r.internal.console.ui.launching.RConsoleMainTab;
import de.walware.statet.r.internal.console.ui.launching.RConsoleOptionsTab;
import de.walware.statet.r.internal.console.ui.launching.RConsoleTabGroup;
import de.walware.statet.r.internal.console.ui.launching.RConsoleTabGroup.ExtJavaClasspathTab;
import de.walware.statet.r.launching.ui.EnvironmentTabForR;
import de.walware.statet.r.launching.ui.REnvTab;

/**
 * @author rytina (andy.rytina@gmail.com)
 *
 */
public class CustomRConsoleTabGroup extends RConsoleTabGroup{

	@Override
	public void createTabs(final ILaunchConfigurationDialog dialog, final String mode) {
		final RConsoleMainTab mainTab = new CustomRConsoleMainTab();
		final REnvTab renvTab = new REnvTab(true, false);
		final boolean jdt = true;
		
		final ILaunchConfigurationTab[] tabs = jdt ? new ILaunchConfigurationTab[] {
				mainTab,
				renvTab,
				new RConsoleOptionsTab(),
				new EnvironmentTabForR(),
				
				new ExtJavaJRETab(mainTab, renvTab),
				new ExtJavaClasspathTab(),
				new SourceLookupTab(),
				
				new CommonTabForConsole()
		} : new ILaunchConfigurationTab[] {
				mainTab,
				renvTab,
				new EnvironmentTabForR(),
				
				new CommonTabForConsole()
		};
		setTabs(tabs);
	}
	
}
