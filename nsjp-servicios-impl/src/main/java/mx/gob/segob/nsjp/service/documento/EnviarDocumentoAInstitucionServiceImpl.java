/**
* Nombre del Programa : EnviarDocumentoAInstitucionServiceImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 16/02/2012
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
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.service.infra.ClienteGeneralService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio que permite enviar un Documento a cualquier otra institución mediante un WebService.
 * 
 * @version 1.0
 * @author rgama
 *
 */
@Service("enviarDocumentoAInstitucionService")
@Transactional
public class EnviarDocumentoAInstitucionServiceImpl implements
	EnviarDocumentoAInstitucionService {

	private final static Logger logger = Logger
    .getLogger(EnviarDocumentoAInstitucionServiceImpl.class);
	
	@Autowired
	private ClienteGeneralService clienteGeneralService;
	
    		
	@Override
	public Long enviarDocumentoAInstitucion(
			DocumentoDTO documentoDTO, String numeroDeCaso, Instituciones target)
			throws NSJPNegocioException {
			return clienteGeneralService.enviarDocumentoAInstitucion(documentoDTO, numeroDeCaso, target);
	}
}
