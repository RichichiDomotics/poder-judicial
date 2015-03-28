/**
* Nombre del Programa : EnviarDocumentoAInstitucionServerServiceImpl.java
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.service.infra.impl.transform.enviardocumentoincumplimiento.DocumentoWSDTOTransformer;

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
		  
@Service("enviarDocumentoAInstitucionServerService")
@Transactional
public class EnviarDocumentoAInstitucionServerServiceImpl implements
	EnviarDocumentoAInstitucionServerService {

	private final static Logger logger = Logger
    .getLogger(EnviarDocumentoAInstitucionServerServiceImpl.class);
	
	@Autowired
	private GuardarDocumentoService documentoService;
    @Autowired
    private CasoDAO casoDAO;
	
    		

	@Override
	public Long enviarDocumentoAInstitucion(
			DocumentoWSDTO documentoWSDTO, String numeroDeCaso) throws NSJPNegocioException {
		
		Long idRegreso = 0L;
		Expediente exp = null;
        DocumentoDTO documentoDTO = null;
		
		logger.info("*************** DOCUMENTO RECIBIDO  ***************");
		logger.info("DocumentoWSDTO: "+ documentoWSDTO);
		logger.info("numeroDeCaso: "+ numeroDeCaso);
		if(documentoWSDTO!= null )
			logger.info("DocumentoWSDTO - ArchivoDigital : "+ documentoWSDTO.getArchivoDigital());
		
		if(documentoWSDTO==null || numeroDeCaso ==null || numeroDeCaso.trim().isEmpty())
			return idRegreso; 
		
		//Buscar el expediente Asociado al caso
		List<Caso> casos = casoDAO.consultarCasosPorNumero(numeroDeCaso);
	    Caso caso = !casos.isEmpty() ? casos.get(0) : null;
	    if(caso != null){
            if (caso.getExpedientes() != null && caso.getExpedientes().size() == 1) {
                exp = caso.getExpedientes().iterator().next();	    	    	
            }
            if (exp != null) {// hay expediente
            	documentoDTO = DocumentoWSDTOTransformer.transformarDTO(documentoWSDTO);
    			logger.info(" DocumentoTransformado:" + documentoDTO);
    			logger.info(" Expediente Id "+ exp.getExpedienteId() +", a relacionar con el Documento:" + documentoDTO);
    			//Guardar el documento
    			idRegreso = documentoService.guardarDocumento(documentoDTO, new ExpedienteDTO(exp.getExpedienteId()));
    			logger.info(" Documento Guardado:"+ idRegreso);
            }
	    }		
	    return idRegreso;
	}
}
