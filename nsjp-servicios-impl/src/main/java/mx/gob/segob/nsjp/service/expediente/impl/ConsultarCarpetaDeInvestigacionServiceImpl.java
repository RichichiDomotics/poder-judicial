/**
* Nombre del Programa : ConsultarCarpetaDeInvestigacionServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/07/2011
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
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciaPorIdObjetoService;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.expediente.ConsultarCarpetaDeInvestigacionService;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class ConsultarCarpetaDeInvestigacionServiceImpl implements
		ConsultarCarpetaDeInvestigacionService {
	
	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(BuscarExpedienteServiceImpl.class);
	@Autowired
	private CasoDAO casoDAO;
	@Autowired
	private ExpedienteDAO expedienteDAO;
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
	
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.expediente.ConsultarCarpetaDeInvestigacionService#consultarCarpetaDeInvestigacion(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ExpedienteDTO consultarCarpetaDeInvestigacion(
			String numeroGeneralCaso ) throws NSJPNegocioException {
		if( numeroGeneralCaso == null )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		ExpedienteDTO carpetaInvestigacion = new ExpedienteDTO();

		//Consultar el Caso
		List<Caso> casos = casoDAO.consultarCasosPorNumero("%"+numeroGeneralCaso+"%");
		if(casos == null || casos.isEmpty())
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
			
		Long idCaso = casos.get(0).getCasoId();
		Long idConfInstitucion = new Long(Areas.PGJ.ordinal()); //Procuraduria
		logger.info(" Caso ID:" + idCaso);
		
		//Consultar el expediente por Caso y de acuerdo al confIn
		List<Expediente> expedientes = expedienteDAO.consultarExpedientesPorIdCasoConfInstitucion(idCaso, idConfInstitucion);
		
		if(expedientes==null || expedientes.isEmpty() || expedientes.get(0)==null || 
				expedientes.get(0).getExpedienteId()== null || expedientes.get(0).getNumeroExpedienteId()==null )
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);

		//Regla de Negocio Un Expediente esta asociado a un Caso 
		carpetaInvestigacion.setExpedienteId(expedientes.get(0).getExpedienteId());
		carpetaInvestigacion.setNumeroExpedienteId(expedientes.get(0).getNumeroExpedienteId());

		//Obtener los datos completos del expediente 
		carpetaInvestigacion.setInvolucradosSolicitados(true);
		carpetaInvestigacion.setObjetosSolicitados(true);
		carpetaInvestigacion.setDocumentosSolicitados(true);
		carpetaInvestigacion.setDomicliosInvolucradoSolicitados(true);
		carpetaInvestigacion= buscarExpedienteService.obtenerExpediente(carpetaInvestigacion);
		
		//Consultar Objetos asociados al Expediente.. 
		logger.info("Objetos Recuperados:"+ carpetaInvestigacion.getObjetosDTO().size());
		
		//Obtener los datos completos del involucrado
		if(carpetaInvestigacion.getInvolucradosDTO()!=null && !carpetaInvestigacion.getInvolucradosDTO().isEmpty()){
			List<InvolucradoDTO> involucradosDTO = new ArrayList<InvolucradoDTO>(0);
			for (InvolucradoDTO involucradoDTO : carpetaInvestigacion.getInvolucradosDTO()) {
				InvolucradoDTO involucradoCompletoDTO =  consultarIndividuoService.obtenerInvolucrado( involucradoDTO );
				involucradosDTO.add(involucradoCompletoDTO);
			}
			carpetaInvestigacion.setInvolucradosDTO(involucradosDTO);
			logger.info(" Involucrados Recuperados: "+  carpetaInvestigacion.getInvolucradosDTO().size());
		}
		
		if(carpetaInvestigacion.getObjetosDTO() != null && !carpetaInvestigacion.getObjetosDTO().isEmpty()){
			for (ObjetoDTO objetoDTO : carpetaInvestigacion.getObjetosDTO()) {
				objetoDTO.setEvidencia(consultarEvidenciaPorIdObjetoService.consultarEvidenciaPorIdObjeto(objetoDTO.getElementoId(), false));
			}
		}
		
		
		//Obtener Datos completos de Documento y su ArchivoDigital
		if(carpetaInvestigacion.getDocumentosDTO()!= null && !carpetaInvestigacion.getDocumentosDTO().isEmpty()){
			List<DocumentoDTO> documentosDTON = new ArrayList<DocumentoDTO>();
			for (DocumentoDTO documentoDTO : carpetaInvestigacion.getDocumentosDTO()) {
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
			carpetaInvestigacion.setDocumentosDTO(documentosDTON);
			logger.info(" Documentos Recuperados: "+  carpetaInvestigacion.getDocumentosDTO().size());
		}
		
		//Obtener los Delitos y Delito Principal de la Carpeta de Investigacion
		if(carpetaInvestigacion.getDelitos()!= null && !carpetaInvestigacion.getDelitos().isEmpty()){
			List<DelitoDTO> delitosN = new ArrayList<DelitoDTO>();
			List<DelitoDTO> delitosV = carpetaInvestigacion.getDelitos();
			for (DelitoDTO delitoDTO : delitosV) {
				if(delitoDTO.getDelitoId()!= null){
					Delito delito = delitoDAO.read(delitoDTO.getDelitoId());
					logger.info(" delito: "+  delito.getEsPrincipal() + " EsProbable: "+  delito.getEsProbable()
							+ " Expediente: "+  delito.getExpediente());
					delitosN.add(DelitoTransfromer.transformarDelito(delito));
					if(delito.getEsPrincipal()){
						carpetaInvestigacion.setDelitoPrincipal(DelitoTransfromer.transformarDelito(delito));
					}
				}
			}
			carpetaInvestigacion.setDelitos(delitosN);
			logger.info(" Delitos Recuperados: "+  carpetaInvestigacion.getDelitos().size());
		}
		
		/*
		//Buscar el involucrado en los expedientes 
		encontrado: 
		for (Expediente expediente : expedientes) {					
			List<Involucrado> involucrados = involucradoDAO.consultarInvolucradosByExpediente(expediente.getExpedienteId());
			
			for (Involucrado involucrado : involucrados) {
				logger.info(" Involucrado:"+ involucrado.getElementoId() + "- calidad:"+ involucrado.getCalidad() +" CalidadId:"+ involucrado.getCalidad().getTipoCalidad().getValorId());
				nombreCompletoBD ="";
				
				
				//Verificar la calidad de Probable Responsable	
				//Buscar nombre demografico si se trata de una persona Fisica
				if (involucrado.getCalidad().getTipoCalidad().getValorId().equals(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId()) 
						&& ! involucrado.getNombreDemograficos().isEmpty()) {
					NombreDemografico nombreDemografico =(NombreDemografico) involucrado.getNombreDemograficos().toArray()[0];
					if(nombreDemografico!=null){
						nombreCompletoBD = nombreDemografico.getNombre() + 
								nombreDemografico.getApellidoPaterno() + nombreDemografico.getApellidoMaterno();
						
						logger.info("Fisica - nombreCompleto:"+nombreCompleto );
						logger.info("Fisica - nombreCompletoBD:"+nombreCompletoBD );
						nombreCompleto = nombreCompleto.replaceAll(" ", "").toUpperCase();
						nombreCompletoBD = nombreCompletoBD.replaceAll(" ", "").toUpperCase();
					}	
				}else  //Buscar por nombre de la Organizacion se se trata de una persona moral 
					if (involucrado.getCalidad().getTipoCalidad().getValorId().equals(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION.getValorId())){
						
						//if( involucrado.get)
						//Obtener la Organiazción
						Organizacion organizacion = organizacionDAO.obtenerOrganizacionByRelacion(involucrado.getElementoId(), new Long(Relaciones.ORGANIZACION_INVOLUCRADA.ordinal()));
						if(organizacion != null){
							nombreCompletoBD = organizacion.getNombreOrganizacion();
							logger.info("Moral - nombreCompleto:"+nombreCompleto );
							logger.info("Moral - nombreCompletoBD:"+nombreCompletoBD );
							nombreCompleto = nombreCompleto.replaceAll(" ", "").toUpperCase();
							nombreCompletoBD = nombreCompletoBD.replaceAll(" ", "").toUpperCase();	
						} 
					}
						
				if(nombreCompletoBD!= "" && nombreCompleto.equals(nombreCompletoBD)){
						logger.info("Se encontro el expediente:"+nombreCompleto );
						carpetaInvestigacion.setExpedienteId(expediente.getExpedienteId());
						carpetaInvestigacion.setNumeroExpedienteId(expediente.getNumeroExpedienteId());
						idInvolucrado = involucrado.getElementoId();
						break encontrado;
				}
		}
	}
		
	//Verificar si se ha encontrado un expediente relacionado
	if(carpetaInvestigacion.getExpedienteId()!= null && carpetaInvestigacion.getExpedienteId() > 0 ){
		//Obtener los datos completos del expediente 
		carpetaInvestigacion= buscarExpedienteService.obtenerExpediente(carpetaInvestigacion);
		//Obtener Datos completos de Documento y su ArchivoDigital
		if(carpetaInvestigacion.getDocumentosDTO()!= null && !carpetaInvestigacion.getDocumentosDTO().isEmpty()){
			List<DocumentoDTO> documentosDTON = new ArrayList<DocumentoDTO>();
			for (DocumentoDTO documentoDTO : carpetaInvestigacion.getDocumentosDTO()) {
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
			carpetaInvestigacion.setDocumentosDTO(documentosDTON);
		}
		
		//Obtener los datos completos del involucrado
		InvolucradoDTO responsableDTO =  consultarIndividuoService.obtenerInvolucrado( new InvolucradoDTO( idInvolucrado));
		List<InvolucradoDTO> involucradosDTO = new ArrayList<InvolucradoDTO>(0);
		involucradosDTO.add(responsableDTO);
		carpetaInvestigacion.setInvolucradosDTO(involucradosDTO);
		
		
		//Obtener los Delitos y Delito Principal de la Carpeta de Investigacion
		//Consultar los datos de delito
		if(carpetaInvestigacion.getDelitos()!= null && !carpetaInvestigacion.getDelitos().isEmpty()){
			List<DelitoDTO> delitosN = new ArrayList<DelitoDTO>();
			List<DelitoDTO> delitosV = carpetaInvestigacion.getDelitos();
			for (DelitoDTO delitoDTO : delitosV) {
				if(delitoDTO.getDelitoId()!= null){
					Delito delito = delitoDAO.read(delitoDTO.getDelitoId());
					logger.info(" delito: "+  delito.getEsPrincipal());
					logger.info(" delito: "+  delito.getEsProbable());
					logger.info(" delito: "+  delito.getExpediente());
					delitosN.add(DelitoTransfromer.transformarDelito(delito));
					if(delito.getEsPrincipal()){
						carpetaInvestigacion.setDelitoPrincipal(DelitoTransfromer.transformarDelito(delito));
					}
				}
			}
			carpetaInvestigacion.getDelitos().clear();
			carpetaInvestigacion.setDelitos(delitosN);
		}
	}	
	*/
		
	return carpetaInvestigacion;
	}

}
