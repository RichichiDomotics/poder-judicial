/**
* Nombre del Programa : RelacionServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para generar las relaciones entre elementos
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
package mx.gob.segob.nsjp.service.relacion.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.model.CatRelacion;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.service.relacion.GenerarRelacionService;

/**
 * Implementacion del servicio para generar las relaciones entre elementos.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
public class GenerarRelacionServiceImpl implements GenerarRelacionService {

	private final static Logger logger = Logger.getLogger(GenerarRelacionServiceImpl.class);
	
	@Autowired
	RelacionDAO relacionDAO;
	
	@Override
	public Long generarRelacion(Long sujeto, Long complemento,
			Relaciones relacion, Short tipoRelacion)  throws NSJPNegocioException  {
		
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GENERAR UNA RELACION ****/");
		
		Long idRelacion = 0L;
		Relacion relacionGen = new Relacion();
		
		relacionGen.setElementoBySujetoId(new Elemento(sujeto));
		relacionGen.setElementoByComplementoId(new Elemento(complemento));
		relacionGen.setTipoRelacion(tipoRelacion);
		relacionGen.setCatRelacion(new CatRelacion(new Long(relacion.ordinal())));
		
		idRelacion = relacionDAO.create(relacionGen);
		
		if(logger.isDebugEnabled())
			logger.debug("/**** SE GENERO LA RELACION " + relacion.toString() + " con id " + idRelacion +" ****/");
		
		return idRelacion;
	}

}
