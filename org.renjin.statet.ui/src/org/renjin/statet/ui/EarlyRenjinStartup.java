package org.renjin.statet.ui;
/**
 * 
 */


import java.lang.reflect.Field;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;

/**
 * This is really a ugly hack to exchange the extensions of StatET with a modified version to 
 * integrate Renjin into StatET. Here I need the expertise of Stephan Wahlbrink from StatET
 * to develop a better solution.
 *  
 * @author rytina (andy.rytina@gmail.com)
 *
 */
public class EarlyRenjinStartup implements org.eclipse.ui.IStartup{

	/**
	 * 
	 */
	private static final String STATET_UICONTRIBUTION_NAMESPACE = "de.walware.statet.r.console.ui";
	
	private static final String LOCAL_CONSOLE_CATEGORY = "de.walware.statet.r.launchConfigurationTabGroups.RConsole";
	private static final String REMOTE_CONSOLE_CATEGORY = "de.walware.statet.r.launchConfigurationTabGroups.RRemoteConsole";
	private static final String R_LAUNCH_DELEGATE = "de.walware.statet.r.launchConfigurationTypes.RConsole";
	
	@Override
	public void earlyStartup() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		for (IExtension ext : registry.getExtensions(STATET_UICONTRIBUTION_NAMESPACE)) {
			if(testId(ext, LOCAL_CONSOLE_CATEGORY)){
				registry.removeExtension(ext, getMasterToken(registry));
			}
			if(testId(ext, REMOTE_CONSOLE_CATEGORY)){
				registry.removeExtension(ext, getMasterToken(registry));
			}
			if(testId(ext, R_LAUNCH_DELEGATE)){
				registry.removeExtension(ext, getMasterToken(registry));
			}
		}
		
	}
	
	private  Object getMasterToken(IExtensionRegistry registry) 
	{
		try{
			Field masterTokenField = registry.getClass().getDeclaredField("masterToken");
			masterTokenField.setAccessible(true);
			Object masterToken = masterTokenField.get(registry);
			return masterToken;
		}catch (Throwable e){
			e.printStackTrace();
			return null;
		}
	}	

	private boolean testId(IExtension iExtension, String id) {
		IConfigurationElement[] exts;
		try{
			exts = iExtension.getConfigurationElements();
		}catch (Throwable e){
			return false;
		}
		for (IConfigurationElement cEl: exts) {
			if(id.equals(cEl.getAttribute("id"))){
				return true;
			}
		}
		return false;
	}
	
}
