/**
* Nombre del Programa : ConsultarInformePolicialHomologadoServiceImpl.java
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

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.informepolicial.InformePolicialHomologadoDAO;
import mx.gob.segob.nsjp.dao.informepolicial.OperativoDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.OperativoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.InformePolicialHomologado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Operativo;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciaPorIdObjetoService;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.informepolicial.ConsultarInformePolicialHomologadoService;
import mx.gob.segob.nsjp.service.informepolicial.impl.transform.InformePolicialHomologadoTransformer;
import mx.gob.segob.nsjp.service.informepolicial.impl.transform.OperativoTransformer;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio que permite la consulta del IPH completo.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class ConsultarInformePolicialHomologadoServiceImpl implements
		ConsultarInformePolicialHomologadoService {

	private static final Logger logger  = Logger.getLogger(ConsultarInformePolicialHomologadoServiceImpl.class);
	
	@Autowired
	private InformePolicialHomologadoDAO informePolicialHomologadoDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private BuscarExpedienteService buscarExpedienteService;
	@Autowired
	private ConsultarIndividuoService consultarIndividuoService; 
	@Autowired
	private ArchivoDigitalDAO archivoDigitalDAO; 
	@Autowired
	private DelitoDAO delitoDAO;
	@Autowired
	private ConsultarEvidenciaPorIdObjetoService consultarEvidenciaPorIdObjetoService;
	@Autowired
	private OperativoDAO operativoDAO;
	
	
	@Override
	public InformePolicialHomologadoDTO consultarInformePolicialHomologadoPorFolio(
			Long folioIPH) throws NSJPNegocioException {
		
		InformePolicialHomologadoDTO iphDTO = new InformePolicialHomologadoDTO();
		
		if(folioIPH ==null || folioIPH < 0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		
		InformePolicialHomologado iph = informePolicialHomologadoDAO.consultaInformePorFolio(folioIPH);
		if(iph==null || iph.getExpediente()==null || iph.getExpediente().getExpedienteId()==null )
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		
		logger.info(" Expediente: " + iph.getExpediente().getExpedienteId());
		//Obtener las relaciones con el IPH
		//Numero Expediente
		NumeroExpediente numExp =  numeroExpedienteDAO.obtenerNumeroExpedienteXExpediente(iph.getExpediente().getExpedienteId());
		if(numExp==null || numExp.getNumeroExpedienteId()==null || numExp.getNumeroExpedienteId()<0)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		logger.info(" Expediente: " + numExp.getExpediente());
		logger.info(" Expediente - getNumeroExpedienteId: " + numExp.getNumeroExpedienteId());
		
		//Expediente
		ExpedienteDTO expDTO = new ExpedienteDTO();
		expDTO.setExpedienteId(iph.getExpediente().getExpedienteId());
		expDTO.setNumeroExpedienteId(numExp.getNumeroExpedienteId());
		
		//Obtener los datos completos del expediente
	    expDTO.setInvolucradosSolicitados(true);
	    expDTO.setDomicliosInvolucradoSolicitados(true);
	    expDTO.setObjetosSolicitados(true);
	    expDTO.setCadenasCustodiaSolicitadas(true);
	    expDTO.setDocumentosSolicitados(true);
	    expDTO.setHechoSolicitado(true);
	    
		expDTO= buscarExpedienteService.obtenerExpediente(expDTO);
		
		//Consultar Objetos asociados al Expediente.. 
		logger.info("Objetos Recuperados:"+ expDTO.getObjetosDTO().size());
		
		//Obtener los datos completos del involucrado
		if(expDTO.getInvolucradosDTO()!=null && !expDTO.getInvolucradosDTO().isEmpty()){
			List<InvolucradoDTO> involucradosDTO = new ArrayList<InvolucradoDTO>(0);
			for (InvolucradoDTO involucradoDTO : expDTO.getInvolucradosDTO()) {
				InvolucradoDTO involucradoCompletoDTO =  consultarIndividuoService.obtenerInvolucrado( involucradoDTO );
				involucradosDTO.add(involucradoCompletoDTO);
			}
			expDTO.setInvolucradosDTO(involucradosDTO);
			logger.info(" Involucrados Recuperados: "+  expDTO.getInvolucradosDTO().size());
		}

		if(expDTO.getObjetosDTO() != null && !expDTO.getObjetosDTO().isEmpty()){
			for (ObjetoDTO objetoDTO : expDTO.getObjetosDTO()) {
				objetoDTO.setEvidencia(consultarEvidenciaPorIdObjetoService.consultarEvidenciaPorIdObjeto(objetoDTO.getElementoId(), false));
			}
			logger.info(" Objetos Recuperados: "+  expDTO.getObjetosDTO().size());
		}
		
		//Obtener Datos completos de Documento y su ArchivoDigital
		if(expDTO.getDocumentosDTO()!= null && !expDTO.getDocumentosDTO().isEmpty()){
			List<DocumentoDTO> documentosDTON = new ArrayList<DocumentoDTO>();
			for (DocumentoDTO documentoDTO : expDTO.getDocumentosDTO()) {
				//logger.info("ID:"+documentoDTO.getDocumentoId());
				if(documentoDTO.getDocumentoId()!= null){
						//Documento  documento = documentoDAO.read(documentoDTO.getDocumentoId());
					ArchivoDigital archivoDigital = archivoDigitalDAO.consultarArchivoDigitalPorDocumento(documentoDTO.getDocumentoId());
					documentoDTO.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigital(archivoDigital));
						//documento.setArchivoDigital(archivoDigital);
						//documentoDTO = DocumentoTransformer.transformarDocumento(documento);
					documentosDTON.add(documentoDTO);
				}
			}
			expDTO.setDocumentosDTO(documentosDTON);
			logger.info(" Documentos Recuperados: "+  expDTO.getDocumentosDTO().size());
		}
		
		//Obtener los Delitos y Delito Principal 
		if(expDTO.getDelitos()!= null && !expDTO.getDelitos().isEmpty()){
			List<DelitoDTO> delitosN = new ArrayList<DelitoDTO>();
			List<DelitoDTO> delitosV = expDTO.getDelitos();
			for (DelitoDTO delitoDTO : delitosV) {
				if(delitoDTO.getDelitoId()!= null){
					Delito delito = delitoDAO.read(delitoDTO.getDelitoId());
					logger.info(" delito: "+  delito.getEsPrincipal() + " EsProbable: "+  delito.getEsProbable()
							+ " Expediente: "+  delito.getExpediente());
					delitosN.add(DelitoTransfromer.transformarDelito(delito));
					if(delito.getEsPrincipal()){
						expDTO.setDelitoPrincipal(DelitoTransfromer.transformarDelito(delito));
					}
				}
			}
			expDTO.setDelitos(delitosN);
			logger.info(" Delitos Recuperados: "+  expDTO.getDelitos().size());
		}
		
		iphDTO = InformePolicialHomologadoTransformer.tranformIPH(iph);

		//Consultar el Operativo
		Operativo operativo = operativoDAO.consultarOperativoPorIdIPH(iph.getInformePolicialHomologadoId());
		if(operativo!= null){
			OperativoDTO operativoDTO = OperativoTransformer.transformOperativo(operativo);
			iphDTO.setOperativo(operativoDTO);
		}
		
		iphDTO.setExpediente(expDTO);
		
		return iphDTO;
	}

}
