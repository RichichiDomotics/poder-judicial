/**
* Nombre del Programa : ConsultarInvolucradosPorAudienciaServiceImpl.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/06/2011
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
package mx.gob.segob.nsjp.service.involucrado.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.service.forma.impl.ConsultarFormaPlantillaServiceImpl;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradosPorAudienciaService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
@Service
public class ConsultarInvolucradosPorAudienciaServiceImpl implements
		ConsultarInvolucradosPorAudienciaService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarInvolucradosPorAudienciaServiceImpl.class);
	
	@Autowired
	private InvolucradoDAO invDao;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradosPorAudienciaService#consultarInvolucradosPorAudiencia(java.lang.Long, mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO)
	 */
	@Override
	public List<InvolucradoDTO> consultarInvolucradosPorAudiencia(
			Long calidadValor, AudienciaDTO audienciaDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR INVOLUCRADOS DE UN TIPO POR UNA AUDIENCIA DADA ****/");
		
		/*Verificación de parámetros*/
		if (audienciaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (audienciaDTO.getId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if(calidadValor==null)
			calidadValor=new Long(-1L);
		
		/*Operación*/
		List<Involucrado> involucrados = invDao.obtenerInvolucradosByAudiencia(audienciaDTO.getId(), calidadValor);
		
		
		/*Transformación*/
		List<InvolucradoDTO> invsDTO = new ArrayList<InvolucradoDTO>();
		for (Involucrado inv : involucrados) {
			invsDTO.add(InvolucradoTransformer.transformarInvolucrado(inv));
			
		}
		return invsDTO;
	}

}
