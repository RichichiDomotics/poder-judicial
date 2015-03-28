/**
* Nombre del Programa : ObtenerDetalleCarpetaEjecucionServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de servicio para obtener la informacion de una carpeta de ejecucion
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
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.expediente.ObtenerDetalleCarpetaEjecucionService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion de servicio para obtener la informacion de una carpeta de ejecucion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ObtenerDetalleCarpetaEjecucionServiceImpl implements ObtenerDetalleCarpetaEjecucionService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(ObtenerDetalleCarpetaEjecucionServiceImpl.class);
	
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private InvolucradoDAO involucradoDAO;
	
	@Override
	public ExpedienteDTO obtenerDetalleCarpetaEjecucion(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICO PARA OBTENER EL DETALLE DE LA CARPETA DE EJECUCION ****/");
		
		if (expedienteDTO.getNumeroExpedienteId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		NumeroExpediente carpetaEjecucion = numeroExpedienteDAO.read(expedienteDTO.getNumeroExpedienteId());
		
		ExpedienteDTO carpetaEjecucionDTO = ExpedienteTransformer.transformarExpedienteBasico(carpetaEjecucion);
		
		if (carpetaEjecucion==null)
			throw new NSJPNegocioException(CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO); 		
		
		logger.debug("/**** Buscar Inv. Para el expediente : "+carpetaEjecucion.getExpediente().getExpedienteId());
		List<Involucrado> involucrados = involucradoDAO.consultarInvolucradosByExpediente(carpetaEjecucion.getExpediente().getExpedienteId());
			
		for (Involucrado involucrado : involucrados) {			
			if (involucrado.getSituacionJuridica()!=null && involucrado.getSituacionJuridica().getValorId().equals(SituacionJuridica.SENTENCIADO.getValorId())) {
				logger.debug("/**** SE OBTIENE EL SENTENCIADO ****/");
				carpetaEjecucionDTO.setInputado(InvolucradoTransformer.transformarInvolucradoBasico(involucrado));
			}								
		}			
			
		if (carpetaEjecucion.getNumeroExpedientePadre()!=null)			
			carpetaEjecucionDTO.setCausaPadre(ExpedienteTransformer.transformarExpedienteBasico(carpetaEjecucion.getNumeroExpedientePadre()));

//		List<Sentencia> sentencias = sentenciaDAO.
		
		return carpetaEjecucionDTO;
	}

}
