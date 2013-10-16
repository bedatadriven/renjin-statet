/**
 * 
 */
package de.walware.statet.r.internal.console.ui.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.osgi.util.NLS;
import org.renjin.statet.ui.launching.RConsoleRenjinLaunchDelegate;

import de.walware.ecommons.ICommonStatusConstants;
import de.walware.ecommons.debug.ui.LaunchConfigUtil;
import de.walware.statet.r.console.ui.launching.RConsoleLaunching;
import de.walware.statet.r.internal.console.ui.RConsoleUIPlugin;

/**
 * @author rytina (andy.rytina@gmail.com)
 *
 */
public class CustomRConsoleLaunchDelegate extends RConsoleLaunchDelegate{
	
	public static final String LOCAL_RENJIN = "local.renjin";
	
	@Override
	public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch,
			IProgressMonitor monitor) throws CoreException {
		try {
			monitor = LaunchConfigUtil.initProgressMonitor(configuration, monitor, 100);
			if (monitor.isCanceled()) {
				return;
			}
			
			final String type = configuration.getAttribute(RConsoleLaunching.ATTR_TYPE, ""); //$NON-NLS-1$
			if (type.equals(RConsoleLaunching.LOCAL_RTERM) || type.equals("rterm")) { //$NON-NLS-1$
				new RConsoleRTermLaunchDelegate().launch(configuration, mode, launch, monitor);
				return;
			}
			if (type.equals(RConsoleLaunching.LOCAL_RJS)) {
				new RConsoleRJLaunchDelegate().launch(configuration, mode, launch, monitor);
				return;
			}
			if (type.equals(LOCAL_RENJIN)) {
				new RConsoleRenjinLaunchDelegate().launch(configuration, mode, launch, monitor);
				return;
			}
			throw new CoreException(new Status(IStatus.ERROR, RConsoleUIPlugin.PLUGIN_ID,
					ICommonStatusConstants.LAUNCHCONFIG_ERROR,
					NLS.bind("R Console launch type ''{0}'' is not available.", type), null ));
		}
		finally {
			monitor.done();
		}
	}

}
