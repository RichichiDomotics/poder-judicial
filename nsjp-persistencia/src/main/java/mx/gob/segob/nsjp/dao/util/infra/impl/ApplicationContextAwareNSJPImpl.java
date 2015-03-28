/**
* Nombre del Programa : ApplicationContextAwareNSJPImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01/09/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dao.util.infra.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
public class ApplicationContextAwareNSJPImpl implements ApplicationContextAware {
	public static ApplicationContext springApplicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		springApplicationContext = applicationContext;
	}

}
