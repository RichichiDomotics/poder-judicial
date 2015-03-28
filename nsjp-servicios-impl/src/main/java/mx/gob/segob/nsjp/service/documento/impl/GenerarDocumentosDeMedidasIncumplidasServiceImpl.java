/**
* Nombre del Programa : GenerarDocumentosDeMedidasIncumplidasService.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 04 Nov 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para generar documentos de medidas incumplidas
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
package mx.gob.segob.nsjp.service.documento.impl;

import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.BODY_TAG_APERTURA;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.BODY_TAG_CIERRE;

import java.io.ByteArrayOutputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import mx.gob.segob.nsjp.comun.constants.ConstantesGenerales;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.HTMLUtils;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.service.documento.CargarDocumentoService;
import mx.gob.segob.nsjp.service.documento.GenerarDocumentosDeMedidasIncumplidasService;
import mx.gob.segob.nsjp.service.documento.GuardarDocumentoService;
import mx.gob.segob.nsjp.service.infra.PJClienteService;
import mx.gob.segob.nsjp.service.medidasalternas.ObtenerMedidasAlternasService;
import mx.gob.segob.nsjp.service.medidascautelares.ObtenerMedidasCautelaresService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 * Implementacion del servicio para generar documentos de medidas incumplidas
 * @version 1.0
 * @author rgama
 *
 */
@Service
@Transactional
public class GenerarDocumentosDeMedidasIncumplidasServiceImpl implements GenerarDocumentosDeMedidasIncumplidasService {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(GenerarDocumentosDeMedidasIncumplidasServiceImpl.class); 

	@Autowired
	private DocumentoDAO documentoDAO;
	
	@Autowired
	private ObtenerMedidasCautelaresService medidasCautelaresService;
	
	@Autowired
	private ObtenerMedidasAlternasService medidasAlternasService;
 
	@Autowired
	private GuardarDocumentoService guardarDocumentoService;
	
	@Autowired
	private CargarDocumentoService cargarDocumentoService;
	
	
	@Autowired
	private PJClienteService pjClienteService;
	
	@Override
	public List<DocumentoDTO> generarDocumentosDeMedidasIncumplidas() throws NSJPNegocioException {
		

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GENERAR DOCUMENTOS DE MEDIDAS INCUMPLIDAS ****/");
		
		//Se consultan las Medidas CAUTELARES incumplidas
		List<MedidaCautelarDTO> loMedidasCautelares = 
			medidasCautelaresService.obtenerMedidasCautelaresPorFechaIncumplientoDelDiaAnterior();
		logger.debug(loMedidasCautelares.size()+ " Medidas cautelares incumplidas se encontraron");
		
		//Se consultan las Medidas ALTERNAS incumplidas
		List<MedidaAlternaDTO> loMedidasAlternas = 
			medidasAlternasService.obtenerMedidasAlternasIncumplidasDelDiaAnterior();
		logger.debug(loMedidasAlternas.size()+ " Medidas alternas incumplidas se encontraron");

		List<DocumentoDTO> documentosGenerados= new ArrayList<DocumentoDTO>();

		//Se generan el documento(s) respectivo(s) por Medida(s) cautelares
		for (MedidaCautelarDTO medidaCautelarDTO : loMedidasCautelares) {
			if(medidaCautelarDTO.getNumeroCausa() != null){				
				DocumentoDTO loDocumento = new DocumentoDTO();
				loDocumento.setFormaDTO(new FormaDTO(Formas.INCUMPLIMIETO_DE_MEDIDA_CAUTELAR.getValorId()));
				loDocumento.setTextoParcial("Medida Cautelar Incumplida");
				loDocumento.setFechaCreacion(new Date());
				loDocumento.setNombreDocumento("Medida Cautelar Incumplida");
				loDocumento.setTipoDocumentoDTO(new ValorDTO(89L));
					//Permite generar el archivo digital
					ArchivoDigitalDTO archivo = null;				
					ByteArrayOutputStream archivoPDF = null;				
					archivoPDF = generarReportePDFDeHTML(loDocumento.getTextoParcial());
				    //Crear el archivo digital
				    archivo = new ArchivoDigitalDTO();
				    archivo.setContenido(archivoPDF.toByteArray());
				    archivo.setNombreArchivo("Medida Cautelar Incumplida");
				    archivo.setTipoArchivo(ConstantesGenerales.EXTENSION_PDF);				
				    loDocumento.setArchivoDigital(archivo);
				logger.info("ID de Medida: " + medidaCautelarDTO.getDocumentoId() + "Numeo de Expediente: " + medidaCautelarDTO.getExpedienteDTO().getNumeroExpediente());
				Long idDocumento = guardarDocumentoService.guardarDocumento(loDocumento, medidaCautelarDTO.getExpedienteDTO());
				logger.info("Se creo el documento con identificador(Medida Cautelar): " + idDocumento);
				documentosGenerados.add(new DocumentoDTO(idDocumento));
				//Enviar documento a PJ
				logger.info(" - Numero de Causa(Medida Cautelar): " + medidaCautelarDTO.getNumeroCausa());
				logger.info(" - Medida Cautelar: " + medidaCautelarDTO.getFechaFin());
				DocumentoDTO loDocumentoCargado = cargarDocumentoService.cargarDocumentoPorId(idDocumento);
				logger.info(" - DocumentoDTO(Medida Cautelar): " + loDocumentoCargado);
				loDocumentoCargado.setArchivoDigital(archivo);
				logger.info(" - Archivo digital: " + loDocumentoCargado.getArchivoDigital());
				pjClienteService.enviarDocumentoIncumplimientoMedidaPJ(loDocumentoCargado, medidaCautelarDTO.getNumeroCausa(), medidaCautelarDTO.getNumeroCarpetaEjecucion());
			}			
		}
		
		//Se generan el documento(s) respectivo(s) por Medida(s) alternas
		for (MedidaAlternaDTO medidaAlternaDTO : loMedidasAlternas) {
			if(medidaAlternaDTO.getNumeroCausa() != null){
				DocumentoDTO loDocumento = new DocumentoDTO();
				loDocumento.setFormaDTO(new FormaDTO(Formas.INCUMPLIMIENTO_MEDIDA_ALTERNATIVA.getValorId()));
				loDocumento.setTextoParcial("Medida Alterna Incumplida");
				loDocumento.setFechaCreacion(new Date());
				loDocumento.setNombreDocumento("Medida Alterna Incumplida");
				loDocumento.setTipoDocumentoDTO(new ValorDTO(89L));
					//Permite generar el archivo digital
					ArchivoDigitalDTO archivo = null;				
					ByteArrayOutputStream archivoPDF = null;				
					archivoPDF = generarReportePDFDeHTML(loDocumento.getTextoParcial());
				    archivo = new ArchivoDigitalDTO();
				    archivo.setContenido(archivoPDF.toByteArray());
				    archivo.setNombreArchivo("Medida Alterna Incumplida");
				    archivo.setTipoArchivo(ConstantesGenerales.EXTENSION_PDF);				
				    loDocumento.setArchivoDigital(archivo);
				
				//logger.info("ID de Medida: " + medidaAlternaDTO.getDocumentoId() + "Numeo de Expediente: " + medidaAlternaDTO.getExpedienteDTO().getNumeroExpediente());
				Long idDocumento = guardarDocumentoService.guardarDocumento(loDocumento, medidaAlternaDTO.getExpedienteDTO());
				logger.info("Se creo el documento con identificador(Medida Alterna): " + idDocumento);
				documentosGenerados.add(new DocumentoDTO(idDocumento));
				//Enviar documento a PJ
				logger.info(" - Numero de Carpeta Ejecucion(Medida Alterna): " + medidaAlternaDTO.getNumeroCarpetaEjecucion());
				logger.info(" - Medida Alterna: " + medidaAlternaDTO.getFechaFin());
				DocumentoDTO loDocumentoCargado = cargarDocumentoService.cargarDocumentoPorId(idDocumento);
				loDocumentoCargado.setArchivoDigital(archivo);
				logger.info(" - DocumentoDTO(Medida Alterna): " + loDocumentoCargado);
				logger.info(" - Archivo digital: " + loDocumentoCargado.getArchivoDigital());
				
				pjClienteService.enviarDocumentoIncumplimientoMedidaPJ(loDocumentoCargado, medidaAlternaDTO.getNumeroCausa(), medidaAlternaDTO.getNumeroCarpetaEjecucion());
			}
		}
		return documentosGenerados;
	}
	
	protected ByteArrayOutputStream generarReportePDFDeHTML(String xHTML){
		
		ByteArrayOutputStream archivoPDF = null;
		try{
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		    @SuppressWarnings("deprecation")
			Document doc = builder.parse(new StringBufferInputStream(
		    		HTMLUtils.encodeHtmlToXhtml(BODY_TAG_APERTURA 
	        				+ xHTML +
	        				BODY_TAG_CIERRE)));

		    ITextRenderer renderer = new ITextRenderer();
		    renderer.setDocument(doc, null);
		    
	        archivoPDF = new ByteArrayOutputStream();
	        
		    renderer.layout();
		    
		    renderer.createPDF(archivoPDF);
		}catch (Exception e) {
			logger.error(e);
		}
		
	    
	    return archivoPDF;
		
	}

}
