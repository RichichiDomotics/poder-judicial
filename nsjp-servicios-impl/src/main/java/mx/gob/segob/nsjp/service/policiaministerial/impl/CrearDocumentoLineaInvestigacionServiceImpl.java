/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial.impl;

import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.BODY_TAG_APERTURA;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.BODY_TAG_CIERRE;

import java.io.ByteArrayOutputStream;
import java.io.StringBufferInputStream;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import mx.gob.segob.nsjp.comun.constants.ConstantesGenerales;
import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.HTMLUtils;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.policiaministerial.SeguimientoLIDAO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.actividad.RegistrarActividadService;
import mx.gob.segob.nsjp.service.documento.GuardarDocumentoService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.forma.impl.transform.FormaTransformer;
import mx.gob.segob.nsjp.service.policiaministerial.ConsultarLineaInvestigacionService;
import mx.gob.segob.nsjp.service.policiaministerial.CrearDocumentoLineaInvestigacionService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 * @author adrian
 * 
 */
@Service
@Transactional
public class CrearDocumentoLineaInvestigacionServiceImpl implements
		CrearDocumentoLineaInvestigacionService {

	public final static Logger logger = Logger
			.getLogger(CrearDocumentoLineaInvestigacionServiceImpl.class);

	@Autowired
	private SeguimientoLIDAO seguimientoLIDAO;
	@Autowired
	private ActividadDAO actividadDAO;
	@Autowired
	private FormaDAO formaDAO;
	@Autowired
	private GuardarDocumentoService guardarDocumentoService;
	@Autowired
	private RegistrarActividadService registrarActividadService;
	@Autowired
	private FuncionarioDAO funcionarioDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private ConsultarLineaInvestigacionService consultarLineaInvestigacionService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.service.policiaministerial.
	 * CrearDocumentoLineaInvestigacionService
	 * #crearDocumentoLineaInvestigacion(mx
	 * .gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO)
	 */
	@Override
	public DocumentoDTO crearDocumentoLineaInvestigacion(
			LineaInvestigacionDTO investigacionDTO, Boolean esGuardado)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR UNA LINEA DE INVESTIGACIÓN ****/");

		if (investigacionDTO == null || esGuardado == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (investigacionDTO.getLineaInvestigacionId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		LineaInvestigacionDTO lineaDTO = consultarLineaInvestigacionService
				.consultarLineaInvestigacion(investigacionDTO);

		/* Recupera el expediente y la actividad Actual */
		Actividad actividadActual = actividadDAO
				.obtenerActividadActual(lineaDTO.getExpedienteDTO().getExpedienteId());

		DocumentoDTO documento = null;
		// buscar si en la actividadActual existe un documento con guardado
		// parcial
		if (actividadActual != null
				&& actividadActual.getDocumento() != null
				&& StringUtils.isNotBlank(actividadActual.getDocumento()
						.getTextoParcial())
				&& actividadActual.getDocumento().getArchivoDigital() == null) {
			// ent
			documento = DocumentoTransformer
					.transformarDocumento(actividadActual.getDocumento());
		}
		if (actividadActual == null) {
			actividadActual = new Actividad();
			Funcionario claveFuncionario = funcionarioDAO
					.consultarFuncionarioXExpediente(numeroExpedienteDAO
							.consultarNumeroExpedienteXExpedienteId(
									lineaDTO.getExpedienteDTO().getExpedienteId())
							.getNumeroExpediente());
			actividadActual.setActividadId(registrarActividadService
					.registrarActividad(
							new ExpedienteDTO(lineaDTO.getExpedienteDTO().getExpedienteId()),
							new FuncionarioDTO(claveFuncionario
									.getClaveFuncionario()),
							Actividades.ADJUNTAR_ARCHIVO_DIGITAL.getValorId()));
		}
		// si no existe un documento con guardado parcial entonces se crea un
		// documento con el cuerpo de la
		// forma que se va a emitir en ese momento
		if (documento == null) {
			documento = new DocumentoDTO();
			if (lineaDTO != null) {
				documento.setTextoParcial(lineaDTO.toString());
				/* Genera PDF */
				documento.setDocumentoId(guardadoDirecto(documento, lineaDTO,
						lineaDTO.getExpedienteDTO(),
						esGuardado));
			}
		}

		return documento;
	}

	private Long guardadoDirecto(DocumentoDTO documento,
			LineaInvestigacionDTO lineaDTO, ExpedienteDTO expDTO,
			Boolean esGuardado) throws NSJPNegocioException {
		Double version = 1.0;

		ArchivoDigitalDTO archivo = null;

		ByteArrayOutputStream archivoPDF = null;

		archivoPDF = generarReportePDFDeHTML(documento.getTextoParcial());
		// Crear el archivo digital
		archivo = new ArchivoDigitalDTO();
		archivo.setContenido(archivoPDF.toByteArray());
		archivo.setNombreArchivo("Linea de investigación "
				+ lineaDTO.getConsecutivo());
		archivo.setTipoArchivo(ConstantesGenerales.EXTENSION_PDF);
		documento.setArchivoDigital(archivo);
		documento.setFechaCreacion(new Date());
		FormaDTO forma = FormaTransformer.transformarForma(formaDAO
				.consultarFormaPorNombre("Blanco"));
		documento.setFormaDTO(forma);// Plantilla en blanco
		documento.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.INFORME
				.getValorId()));
		documento.setNombreDocumento("Linea de investigación "
				+ lineaDTO.getConsecutivo());
		documento.setVersion(version);
		if (esGuardado) {
			documento.setTextoParcial(null);
			return guardarDocumentoService.guardarDocumento(documento, expDTO);
		} else
			return 0L;
	}

	/**
	 * Utiliza las librerías de XHTML y iText para generar un reporte en PDF a
	 * partir de un archivo XHTML
	 * 
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("deprecation")
	protected ByteArrayOutputStream generarReportePDFDeHTML(String xHTML) {

		ByteArrayOutputStream archivoPDF = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(new StringBufferInputStream(HTMLUtils
					.encodeHtmlToXhtml(BODY_TAG_APERTURA + xHTML
							+ BODY_TAG_CIERRE)));

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);

			archivoPDF = new ByteArrayOutputStream();

			renderer.layout();

			renderer.createPDF(archivoPDF);
		} catch (Exception e) {
			logger.error(e);
		}

		return archivoPDF;

	}

}
