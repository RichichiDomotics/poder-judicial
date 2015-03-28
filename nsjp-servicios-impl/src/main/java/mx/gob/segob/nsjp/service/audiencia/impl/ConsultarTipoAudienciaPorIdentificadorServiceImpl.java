/**
* Nombre del Programa : ConsultarTipoAudienciaPorIdentificadorServiceImpl.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/06/2011
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
package mx.gob.segob.nsjp.service.audiencia.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.audiencia.ConsultarTipoAudienciaPorIdentificadorService;


/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
@Service
@Transactional
public class ConsultarTipoAudienciaPorIdentificadorServiceImpl implements
		ConsultarTipoAudienciaPorIdentificadorService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarTipoAudienciaPorIdentificadorServiceImpl.class);
	
	@Autowired
	private AudienciaDAO audDao;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.audiencia.ConsultarTipoAudienciaPorIdentificadorService#consultarTipoAudienciaPorIdentificador(mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO)
	 */
	@Override
	public ValorDTO consultarTipoAudienciaPorIdentificador(
			AudienciaDTO audienciaDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR TIPO DE AUDIENCIA DE UNA AUDIENCIA DADA ****/");
		
		/*Verificación de parámetros*/
		if (audienciaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(audienciaDTO.getId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			
		/*Operación*/
		Audiencia audiencia = audDao.read(audienciaDTO.getId());
		
		/*Transformación*/
		Valor tipoAud = audiencia.getTipo();
		ValorDTO tipoDTO = new ValorDTO(tipoAud.getValorId(), tipoAud.getValor(), tipoAud.getCampoCatalogo().getNombreCampo());
		
		return tipoDTO;
	}

}
