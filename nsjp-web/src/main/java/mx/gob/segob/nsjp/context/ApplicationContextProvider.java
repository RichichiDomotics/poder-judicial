/**
 * 
 */
package mx.gob.segob.nsjp.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * This class provides an application-wide access to the
 * Spring ApplicationContext! The ApplicationContext is
 * injected in a static method of the class "AppContext".
 *
 * Use AppContext.getApplicationContext() to get access
 * to all Spring Beans. 
 * 
 * @author LuisMG
 *
 */
public class ApplicationContextProvider implements ApplicationContextAware {

	/*
	 * 
	 *  (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) 
			throws BeansException {
		 // Wiring the ApplicationContext into a static method 
        AppContext.setApplicationContext(applicationContext);  


	}

}
