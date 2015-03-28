/**
* Nombre del Programa : ConsultarEnviarInformePolicialHomologadoServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/09/2011
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
package mx.gob.segob.nsjp.service.informepolicial.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.service.informepolicial.ConsultarEnviarInformePolicialHomologadoService;
import mx.gob.segob.nsjp.service.informepolicial.ConsultarInformePolicialHomologadoService;
import mx.gob.segob.nsjp.service.infra.ProcuraduriaClienteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio que permite Consultar el IPH por Folio
 * y posteriormente invocar al WS para ser enviado PGJ
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class ConsultarEnviarInformePolicialHomologadoServiceImpl implements
		ConsultarEnviarInformePolicialHomologadoService {

	private final static Logger logger = Logger.getLogger(ConsultarInformePolicialHomologadoServiceImpl.class);
			
	@Autowired 
	private ProcuraduriaClienteService procuraduriaClienteService;
	@Autowired 
	private ConsultarInformePolicialHomologadoService iphService;
	
	@Override
	public InformePolicialHomologadoDTO consultarEnviarInformePolicialHomologado(
			Long folioIPH, Long idAgencia)
			throws NSJPNegocioException {
		if(folioIPH==null || folioIPH<0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			
		InformePolicialHomologadoDTO informePolicialHomologadoDTO = iphService.consultarInformePolicialHomologadoPorFolio(folioIPH);
		
		if(informePolicialHomologadoDTO==null || informePolicialHomologadoDTO.getInformePolicialHomologadoId()==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);

		return procuraduriaClienteService.enviarInformePolicialHomologado(informePolicialHomologadoDTO, idAgencia);
	}

}
