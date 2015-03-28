/**
* Nombre del Programa : ExpedientePrint.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Esta clase tiene como objetivo imprimir lo datos de un expediente 
* 							en entidad, DTO y WSDTO, para Expediente, ExpedienteDTO  y ExpedienteWSDTO.
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
package mx.gob.segob.nsjp.services.dtos.print;

import java.util.List;

import org.apache.log4j.Logger;

import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarcarpetainvestigacion.ArchivoDigitalWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarcarpetainvestigacion.DelitoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarcarpetainvestigacion.DocumentoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarcarpetainvestigacion.ExpedienteWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarcarpetainvestigacion.InvolucradoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarcarpetainvestigacion.ObjetoWSDTO;

/**
 * Esta clase tiene como objetivo imprimir lo datos de un expediente 
 * en entidad, DTO y WSDTO, para Expediente, ExpedienteDTO  y ExpedienteWSDTO. 
 *
 * Se hace uso de logger.info en vez de System.out.println con el objetivo de mandar 
 * a imprimir sin traza.  Se hace con un Find Replace.
 * 
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ExpedientePrint {

	//TODO GBP Cambiar a infra.... hacer uso de "IMPRIMIR OBJETO DINAMICO" Basarse en Expediente.transform
	private final static Logger logger = Logger.getLogger(ExpedientePrint.class);
	
	/********************************* DTO *********************************/
	public static void imprimirDatosExpediente(ExpedienteDTO expedienteDTO ){
	 
		logger.info("********************************expedienteDTO********************************");
		
		if(expedienteDTO==null)
			return;
	
		logger.info("getNumeroExpedienteId "
				+ expedienteDTO.getNumeroExpedienteId());
		logger.info("Expediente id " + expedienteDTO.getExpedienteId());
		logger.info("ELEMENTOS:" + expedienteDTO.getElementosDTO());
		logger.info("No. expediente " + expedienteDTO.getNumeroExpediente());
		logger.info("Involucrados " + expedienteDTO.getInvolucradosDTO().size());
		logger.info("Actividad Actual " + expedienteDTO.getActividadActual());
		logger.info("Delitos " + expedienteDTO.getDelitos());
		if(expedienteDTO.getDelitos()!= null && !expedienteDTO.getDelitos().isEmpty()){
			logger.info("Delitos Total:" + expedienteDTO.getDelitos().size());
			for (DelitoDTO delitoDTO : expedienteDTO.getDelitos()) {
				logger.info(" Delito: "+ delitoDTO);
				logger.info(" Delito ID: "+ delitoDTO.getDelitoId());
				logger.info(" Delito EsPrincipal: "+ delitoDTO.getEsPrincipal());
				logger.info(" Delito ClaveDelito: N/A");
				logger.info(" Delito NombreDelito: "+ delitoDTO.getNombreDelito());
			}
		}
		logger.info("Delito principal " + expedienteDTO.getDelitoPrincipal());
		logger.info("Hecho " + expedienteDTO.getHechoDTO());
		if (expedienteDTO.getHechoDTO() != null) {
			HechoDTO hechoDTO =expedienteDTO.getHechoDTO() ; 
			logger.info("Hecho - DescNarrativa: "+ hechoDTO.getDescNarrativa());
			logger.info("Hecho - Lugar: "+ hechoDTO.getLugar());
			if (hechoDTO.getLugar() instanceof DomicilioDTO ){
				DomicilioDTO domicilioDTO = (DomicilioDTO) hechoDTO.getLugar();
				logger.info(" Descripcion:"+domicilioDTO.getDescripcion());
				logger.info(" Calle:"+domicilioDTO.getCalle());
				logger.info(" NumeroExterior:"+domicilioDTO.getNumeroExterior());
				logger.info(" AsentamientoDTO:"+domicilioDTO.getAsentamientoDTO());
				logger.info(" EntidadDTO:"+domicilioDTO.getEntidadDTO());
				logger.info(" MunicipioDTO:"+domicilioDTO.getMunicipioDTO());
				logger.info(" CiudadDTO:"+domicilioDTO.getCiudadDTO());
				logger.info(" NumeroInterior:"+domicilioDTO.getNumeroInterior());
			}
			logger.info("Hecho - Domicilio: "+ hechoDTO.getDomicilio());
			logger.info("Hecho - Tiempo: "+ hechoDTO.getTiempo());
			if(hechoDTO.getTiempo()!= null){
				logger.info("Tiempo - TiempoId: "+ hechoDTO.getTiempo().getTiempoId());
				logger.info("Tiempo - Descripcion: "+ hechoDTO.getTiempo().getDescripcion());
				logger.info("Tiempo - FechaInicio: "+ hechoDTO.getTiempo().getFechaInicio());
				logger.info("Tiempo - FechaFin: "+ hechoDTO.getTiempo().getFechaFin());
				logger.info("Tiempo - TipoRegistro: "+ hechoDTO.getTiempo().getTipoRegistro());
			}
		}
		
		logger.info("ObjetosDTO : " + expedienteDTO.getObjetosDTO());
		if(expedienteDTO.getObjetosDTO()!= null || !expedienteDTO.getObjetosDTO().isEmpty()){
			logger.info("ObjetosDTO Total: " + expedienteDTO.getObjetosDTO().size());
			List<ObjetoDTO> objetosDTO = expedienteDTO.getObjetosDTO();
			for (ObjetoDTO objetoDTO : objetosDTO) {
				logger.info("OBJ - ClassName:"+objetoDTO.getClass().getName());
				logger.info("OBJ - ElementoId:"+objetoDTO.getElementoId());
				if(objetoDTO.getInstitucionPresenta()!=null)
					logger.info("OBJ - InstitucionPresenta:"+objetoDTO.getInstitucionPresenta().getConfInstitucionId());
				
				if(objetoDTO.getTipoObjeto()!= null)
					logger.info("OBJ - TipoObjeto:"+objetoDTO.getTipoObjeto().getValorId());
				logger.info("OBJ - Descripcion:"+objetoDTO.getDescripcion());
				logger.info("OBJ - EsPertenencia:"+objetoDTO.getEsPertenencia());
				
				if(objetoDTO.getCalidadDTO()!= null){
					logger.info("OBJ - CalidadDTO - getCalidadId:"+objetoDTO.getCalidadDTO().getCalidadId());
					logger.info("OBJ - CalidadDTO - ValorIdCalidad:"+objetoDTO.getCalidadDTO().getValorIdCalidad());
					logger.info("OBJ - CalidadDTO - DescripcionEstadoFisico:"+objetoDTO.getCalidadDTO().getDescripcionEstadoFisico());
					logger.info("OBJ - CalidadDTO - Calidades:"+objetoDTO.getCalidadDTO().getCalidades());
				}
				if(objetoDTO.getFotoDelElemento()!=null){
					logger.info("OBJ - FotoDelElemento - NombreArchivo: "+objetoDTO.getFotoDelElemento().getNombreArchivo());
					logger.info("OBJ - FotoDelElemento - TipoArchivo: "+objetoDTO.getFotoDelElemento().getTipoArchivo());
					logger.info("OBJ - FotoDelElemento - ConfInstitucionId: N/A" );
					logger.info("OBJ - FotoDelElemento - Contenido().length: "+objetoDTO.getFotoDelElemento().getContenido().length);
				}
				if(objetoDTO.getEvidencia() != null){
					logger.info("OBJ - Evidencia - Codigo Barras: "+objetoDTO.getEvidencia().getCodigoBarras());
					logger.info("OBJ - Evidencia - Descripcion: "+objetoDTO.getEvidencia().getDescripcion());
					if(objetoDTO.getEvidencia().getCadenaDeCustodia() != null){
						logger.info("OBJ - Evidencia - Cadena De Custodia - Folio: "+objetoDTO.getEvidencia().getCadenaDeCustodia().getFolio());
						logger.info("OBJ - Evidencia - Cadena De Custodia - Fecha Levantamiento: "+objetoDTO.getEvidencia().getCadenaDeCustodia().getFechaLevantamiento());
						logger.info("OBJ - Evidencia - Cadena De Custodia - Fecha Levantamiento: "+objetoDTO.getEvidencia().getCadenaDeCustodia().getFechaLevantamiento());
					}
				}
			}
		}

		logger.info("Documentos: " + expedienteDTO.getDocumentosDTO().size());
		for (DocumentoDTO loDocumentoDTO : expedienteDTO.getDocumentosDTO()) {
			logger.info("--------- ID Documento: "
					+ loDocumentoDTO.getDocumentoId() + " -------------");
			logger.info("--------- Documento: "+ loDocumentoDTO);
			logger.info("Nombre Documento: "
					+ loDocumentoDTO.getNombreDocumento());
			logger.info("Tipo Documento: "
					+ loDocumentoDTO.getTipoDocumentoDTO());
			if(loDocumentoDTO.getTipoDocumentoDTO()!= null){
				logger.info("Tipo Documento: "
					+ loDocumentoDTO.getTipoDocumentoDTO().getIdCampo());
			}
			logger.info("Archivo Digital: "
					+ loDocumentoDTO.getArchivoDigital());
			if(loDocumentoDTO.getArchivoDigital()!=null){
				ArchivoDigitalDTO archivoDigitalDTO = loDocumentoDTO.getArchivoDigital();
				logger.info("Archivo Digital -  Nombre :" + archivoDigitalDTO.getNombreArchivo());
				logger.info("Tipo Archivo: " + archivoDigitalDTO.getTipoArchivo());
				logger.info("Archivo Digital -  Tamaño : " + archivoDigitalDTO.getContenido().length);
			}
			logger.info("Numero Fojas: "
					+ loDocumentoDTO.getNumeroFojas());
			logger.info("Forma Documento: "
					+ loDocumentoDTO.getFormaDTO());
			if(loDocumentoDTO.getFormaDTO()!= null ){
				FormaDTO formaDTO = loDocumentoDTO.getFormaDTO();
				logger.info("Forma getFormaId:"+ formaDTO.getFormaId());
				logger.info("Forma getTipoFormaDTO:"+ formaDTO.getTipoFormaDTO());
				logger.info("Forma getDescForma:"+ formaDTO.getDescForma());
				logger.info("Forma getCuerpo:"+ formaDTO.getCuerpo());
				logger.info("Forma getNombre:"+ formaDTO.getNombre());
				logger.info("Forma getTipoDocumentoDTO:"+ formaDTO.getTipoDocumentoDTO());
				logger.info("Forma getFechaCreacion:"+ formaDTO.getFechaCreacion());
			}
			
			logger.info("-------------------------------");
		}
		
		if( expedienteDTO.getInvolucradosDTO()!= null && !expedienteDTO.getInvolucradosDTO().isEmpty()){
			List<InvolucradoDTO> lInvolucradoDTO = expedienteDTO.getInvolucradosDTO();

			for (InvolucradoDTO involucradoDTO : lInvolucradoDTO) {
				logger.info("-------------INVOLUCRADO------------------");
				
				logger.info("** ID:" + involucradoDTO.getElementoId());
				if(involucradoDTO.getCalidadDTO()!=null ){
					logger.info("Calidad:** " + 
						involucradoDTO.getCalidadDTO().getValorIdCalidad()+ "**  - " + involucradoDTO.getCalidadDTO() );
				}
				logger.info(" TIPO PERSONA: **"+(involucradoDTO.getTipoPersona().equals(0L)?"MORAL":"FISICA") + "**");
				
				InvolucradoPrint.imprimirDatosInvolucrado(involucradoDTO);
			}
		}
		logger.info("******************************** FIN DTO ********************************");
	}

	/********************************* WSDTO *********************************/
	
	public static void imprimirDatosExpediente(ExpedienteWSDTO expedienteWSDTO ){
		 
		logger.info("********************************expedienteWSDTO********************************");
		
		if(expedienteWSDTO==null)
			return;
	
		logger.info("getNumeroExpedienteId "
				+ expedienteWSDTO.getNumeroExpedienteId());
		logger.info("Expediente id N/A");
		logger.info("ELEMENTOS: N/A");
		logger.info("No. expediente " + expedienteWSDTO.getNumeroExpediente());
		logger.info("Involucrados " + expedienteWSDTO.getInvolucradosDTO().size());
		logger.info("Actividad Actual N/A" );
		logger.info("Delitos " + expedienteWSDTO.getDelitos());
		if(expedienteWSDTO.getDelitos()!= null && !expedienteWSDTO.getDelitos().isEmpty()){
			logger.info("Delitos Total:" + expedienteWSDTO.getDelitos().size());
			for (DelitoWSDTO delitoWSDTO : expedienteWSDTO.getDelitos()) {
				logger.info(" Delito: "+ delitoWSDTO);
				logger.info(" Delito ID: N/A");
				logger.info(" Delito EsPrincipal: N/A");
				logger.info(" Delito ClaveDelito: "+ delitoWSDTO.getClaveDelito());
				logger.info(" Delito NombreDelito: "+ delitoWSDTO.getNombreDelito());
			}
		}
		logger.info("Delito principal " + expedienteWSDTO.getDelitoPrincipal());
		logger.info("Hecho N/A");
		
		logger.info("ObjetosDTO : " + expedienteWSDTO.getObjetosDTO());
		if(expedienteWSDTO.getObjetosDTO()!= null || !expedienteWSDTO.getObjetosDTO().isEmpty()){
			logger.info("ObjetosDTO Total: " + expedienteWSDTO.getObjetosDTO().size());
			List<ObjetoWSDTO> objetosDTO = expedienteWSDTO.getObjetosDTO();
			for (ObjetoWSDTO objetoDTO : objetosDTO) {
				logger.info("OBJ - ClassName:"+objetoDTO.getClass().getName());
				logger.info("OBJ - ElementoId: N/A");
				logger.info("OBJ - InstitucionPresenta:"+objetoDTO.getInstitucionPresenta());
				
				if(objetoDTO.getTipoObjeto()!= null)
					logger.info("OBJ - TipoObjeto: " + objetoDTO.getTipoObjeto());
				logger.info("OBJ - Descripcion:"+objetoDTO.getDescripcion());
				logger.info("OBJ - EsPertenencia: N/A");
				
				if(objetoDTO.getCalidad()!= null){
					logger.info("OBJ - CalidadDTO - getCalidadId: N/A");
					logger.info("OBJ - CalidadDTO - ValorIdCalidad:"+objetoDTO.getCalidad().getValorIdCalidad());
					logger.info("OBJ - CalidadDTO - DescripcionEstadoFisico:"+objetoDTO.getCalidad().getDescripcionEstadoFisico());
					logger.info("OBJ - CalidadDTO - getCalidades:"+objetoDTO.getCalidad().getCalidades());
				}
				if(objetoDTO.getFotoDelElemento()!=null){
					logger.info("OBJ - FotoDelElemento - NombreArchivo: "+objetoDTO.getFotoDelElemento().getNombreArchivo());
					logger.info("OBJ - FotoDelElemento - TipoArchivo: "+objetoDTO.getFotoDelElemento().getTipoArchivo());
					logger.info("OBJ - FotoDelElemento - ConfInstitucionId: "+objetoDTO.getFotoDelElemento().getConfInstitucionId());
					logger.info("OBJ - FotoDelElemento - Contenido().length: "+objetoDTO.getFotoDelElemento().getContenido().length);
				}
			}
		}


		logger.info("Documentos: " + expedienteWSDTO.getDocumentosDTO().size());
		for (DocumentoWSDTO loDocumentoWSDTO : expedienteWSDTO.getDocumentosDTO()) {
			logger.info("--------- ID Documento: N/A -------------");
			logger.info("--------- Documento: "+ loDocumentoWSDTO);
			logger.info("Nombre Documento: "
					+ loDocumentoWSDTO.getNombreDocumento());
			logger.info("Tipo Documento: "
					+ loDocumentoWSDTO.getTipoDocumentoDTO());
			if(loDocumentoWSDTO.getTipoDocumentoDTO()!= null){
				logger.info("Tipo Documento: "
					+ loDocumentoWSDTO.getTipoDocumentoDTO());
			}
			logger.info("Archivo Digital: "
					+ loDocumentoWSDTO.getArchivoDigital());
			if(loDocumentoWSDTO.getArchivoDigital()!=null){
				ArchivoDigitalWSDTO archivoDigitalWSDTO = loDocumentoWSDTO.getArchivoDigital();
				logger.info("Archivo Digital -  Nombre :" + archivoDigitalWSDTO.getNombreArchivo());
				logger.info("Tipo Archivo: " + archivoDigitalWSDTO.getTipoArchivo());
				logger.info("Archivo Digital -  Tamaño : " + archivoDigitalWSDTO.getContenido().length);
			}
			logger.info("Numero Fojas: "
					+ loDocumentoWSDTO.getNumeroFojas());
			logger.info("Forma Documento: "
					+ loDocumentoWSDTO.getFormaId());
//			if(loDocumentoWSDTO.getFormaDTO()!= null ){
//				FormaDTO formaDTO = loDocumentoWSDTO.getFormaDTO();
//				logger.info("Forma getFormaId:"+ formaDTO.getFormaId());
//				logger.info("Forma getTipoFormaDTO:"+ formaDTO.getTipoFormaDTO());
//				logger.info("Forma getDescForma:"+ formaDTO.getDescForma());
//				logger.info("Forma getCuerpo:"+ formaDTO.getCuerpo());
//				logger.info("Forma getNombre:"+ formaDTO.getNombre());
//				logger.info("Forma getTipoDocumentoDTO:"+ formaDTO.getTipoDocumentoDTO());
//				logger.info("Forma getFechaCreacion:"+ formaDTO.getFechaCreacion());
//			}
			
			logger.info("-------------------------------");
		}
		
		if( expedienteWSDTO.getInvolucradosDTO()!= null && !expedienteWSDTO.getInvolucradosDTO().isEmpty()){
			List<InvolucradoWSDTO> lInvolucradoWSDTO = expedienteWSDTO.getInvolucradosDTO();

			for (InvolucradoWSDTO involucradoWSDTO : lInvolucradoWSDTO) {
				logger.info("-------------INVOLUCRADO------------------");
				
				logger.info("** ID: N/A" );
				if(involucradoWSDTO.getCalidad()!=null ){
					logger.info("Calidad:** " + 
						involucradoWSDTO.getCalidad().getValorIdCalidad()+ "**  - " + involucradoWSDTO.getCalidad() );
				}
				logger.info(" TIPO PERSONA: **"+(involucradoWSDTO.getTipoPersona().equals(0L)?"MORAL":"FISICA") + "**");
				
				InvolucradoPrint.imprimirDatosInvolucrado(involucradoWSDTO);
			}
		}
		logger.info("******************************** FIN WSDTO ********************************");
	}

}
