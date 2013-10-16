/**
 * 
 */
package org.renjin.statet.ui.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import de.walware.ecommons.ts.ITool;
import de.walware.ecommons.ts.IToolRunnable;
import de.walware.ecommons.ts.IToolService;

/**
 * @author rytina (andy.rytina@gmail.com)
 *
 */
public class RenjinCancelRunnable implements IToolRunnable{

	@Override
	public boolean changed(int arg0, ITool arg1) {
		return false;
	}

	@Override
	public String getLabel() {
		return null;
	}

	@Override
	public String getTypeId() {
		return null;
	}

	@Override
	public boolean isRunnableIn(ITool arg0) {
		return false;
	}

	@Override
	public void run(IToolService arg0, IProgressMonitor arg1)
			throws CoreException {
		
	}

}
