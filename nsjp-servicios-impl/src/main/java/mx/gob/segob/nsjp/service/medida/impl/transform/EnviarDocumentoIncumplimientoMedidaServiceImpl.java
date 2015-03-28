/**
* Nombre del Programa : EnviarDocumentoIncumplimientoMedidaServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 03/11/2011
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
package mx.gob.segob.nsjp.service.medida.impl.transform;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.documento.GuardarDocumentoService;
import mx.gob.segob.nsjp.service.infra.impl.transform.enviardocumentoincumplimiento.DocumentoWSDTOTransformer;
import mx.gob.segob.nsjp.service.medida.EnviarDocumentoIncumplimientoMedidaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio que permite enviar el el Documento de Incumplimiento de la Medida
 * a otra institución mediante un WebService.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service("enviarDocumentoIncumplimientoMedidaService")
@Transactional
public class EnviarDocumentoIncumplimientoMedidaServiceImpl implements
		EnviarDocumentoIncumplimientoMedidaService {

	private final static Logger logger = Logger
    .getLogger(EnviarDocumentoIncumplimientoMedidaServiceImpl.class);
	
	@Autowired
	private ExpedienteDAO expedienteDAO;
	@Autowired
	private GuardarDocumentoService documentoService;
    		
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.medida.EnviarDocumentoIncumplimientoMedidaService#enviarDocumentoDeIncumplimientoDeMedida(mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO, java.lang.String, java.lang.String)
	 */
	@Override
	public Long enviarDocumentoDeIncumplimientoDeMedida(
			DocumentoWSDTO documentoWSDTO, String numeroCausa,
			String numeroCarpetaEjecucion) throws NSJPNegocioException {
		
		Long idRegreso = 0L;
		
		logger.info("*************** DOCUMENTO RECIBIDO  ***************");
		logger.info("DocumentoWSDTO: "+ documentoWSDTO);
		logger.info("NumeroCausa: "+ numeroCausa);
		logger.info("NumeroCarpetaEjecucion: "+ numeroCarpetaEjecucion);
		if(documentoWSDTO!= null )
			logger.info("DocumentoWSDTO - ArchivoDigital : "+ documentoWSDTO.getArchivoDigital());
		
		if(documentoWSDTO==null || numeroCausa ==null || numeroCausa.trim().isEmpty())
			return idRegreso; 
		
		DocumentoDTO documentoDTO = DocumentoWSDTOTransformer.transformarDTO(documentoWSDTO);
		logger.info(" DocumentoTransformado:" + documentoDTO); 
		//Buscar el expediente Asociado a la Causa
		Long expId = expedienteDAO.consultarExpedientePorNumeroExpediente(numeroCausa);
		if(expId==null )
			return idRegreso;
		
		logger.info(" Expediente Id "+ expId+", a relacionar con el Documento:" + documentoDTO);
		//Guardar el documento
		idRegreso = documentoService.guardarDocumento(documentoDTO, new ExpedienteDTO(expId));
		logger.info(" Documento Guardado:"+ idRegreso);
		
		return idRegreso;
	}

}
