/**
 * 
 */
package mx.gob.segob.nsjp.service.ws.infra.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author vaguirre
 * 
 */
@Service
public class ApplicationContextAwareWSNSJPImpl implements ApplicationContextAware {
	public static ApplicationContext springApplicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		springApplicationContext = applicationContext;
	}

}
