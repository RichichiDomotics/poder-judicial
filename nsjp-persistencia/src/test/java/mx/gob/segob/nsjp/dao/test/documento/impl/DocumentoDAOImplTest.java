/**
 * Nombre del Programa : DocumentoDAOImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 May 2011
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
package mx.gob.segob.nsjp.dao.test.documento.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.documento.TipoForma;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dao.documento.IndiceEstructuradoDAO;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.IndiceEstructurado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class DocumentoDAOImplTest extends BaseTestPersistencia<DocumentoDAO> {

	@Autowired
	private ArchivoDigitalDAO archivoDigitalDAO;
	@Autowired
	private FormaDAO formaDAO;
	@Autowired
	private IndiceEstructuradoDAO indiceEstructuradoDAO;

	public void testRead() {
		//Long id = daoServcice.create(crearDocumentoPrueba());
		Long id = 487L;
		Documento doc = daoServcice.read(id);
		assertNotNull("Se esperaba un documento", doc);

		//daoServcice.delete(doc);
		//archivoDigitalDAO.delete(doc.getArchivoDigital());
	}

	public void testWrite() {

		Long idNuevo = daoServcice.create(crearDocumentoPrueba());
//		Documento doc = daoServcice.read(idNuevo);
		assertNotNull("Documento no pudo ser creado, se esperaba un ID",
				idNuevo);

//		daoServcice.delete(doc);
//		archivoDigitalDAO.delete(doc.getArchivoDigital());
	}

	private Documento crearDocumentoPrueba() {
		ArchivoDigital ad = new ArchivoDigital();
		ad.setNombreArchivo("d2.pdf");
		ad.setTipoArchivo("pdf");
		ad.setContenido(obtenerArchivo("/archivos/d2.pdf"));
		Long id = archivoDigitalDAO.create(ad);
		ad.setArchivoDigitalId(id);
		Forma forma = formaDAO.read(new Long(1));
		Documento doc = new Documento();
		doc.setForma(forma);
		doc.setArchivoDigital(ad);
		doc.setFechaCreacion(new Date());
		doc.setFolioDocumento("5335");
		doc.setNombreDocumento("Acuerdos de Defensa");
		doc.setNumeroFojas("500");
		doc.setOrigenDocumento((short) 1);

//		doc.setTextoParcial("<p>Hola Mundo</p>");
		doc.setVersion(1.5);
		doc.setTipoDocumento(new Valor(new Long(82)));
		return doc;
	}

	public void testConsultarDocumentosExpediente() {
		List<Documento> respuesta = daoServcice
				.consultarDocumentoPorExpediente(1264L);
		assertNotNull(respuesta);
		assertTrue("La lista debe tener minimo un registro",
				respuesta.size() > 0);
		logger.info("Documentos obtenidos : " + respuesta.size());
	}

	public void testConsultarDocumentosXTipoDocumento() {
		Long tipoDocumento = 83L;
		Long numeroExpedienteId = null;
		List<Documento> documentos = daoServcice
				.consultarDocumentosXExpedienteYTipoDocumento(numeroExpedienteId,
						tipoDocumento);

		logger.info("Existen " + documentos.size());
		for (Documento doc : documentos) {
			logger.info("************************************************");
			logger.info("Id Docuemnto: "+doc.getDocumentoId()+": "+doc.getNombreDocumento());
			if(doc.getActividad() != null){
//				Actividad miAct=null;
//				miAct=doc.getActividad();
				logger.info(" Número de Expediente: "+doc.getExpediente().getExpedienteId());
				logger.info(" Número de Caso: "+doc.getExpediente().getCaso().getCasoId() +" / "+doc.getExpediente().getCaso().getNumeroGeneralCaso());
			}else
				logger.info("No trae expediente");
			String tipo=(doc.getTipoDocumento()!=null)?doc.getTipoDocumento().getValor():"No trae";
			logger.info(" Tipo de Documento: "+ tipo);
			logger.info(" Nombre del perito: ");
			logger.info(" Especialidad: ");
			logger.info(" Fecha de Recepción: "+doc.getFechaCreacion());

		}
	}
	
	public void testConsultarDocumentoPorNumeroExpedienteId(){
		
		List<Documento> docs = daoServcice.consultarDocumentosPorNumeroExpedienteId(17L);
		logger.debug("Numero de documentos " + docs.size());
		assertNotNull(docs);
		
	}
	
	public void testConsultarDocumentosAudienciaByTipoForma () {
		List<Documento> respuesta = daoServcice.consultarDocumentosAudienciaByTipoForma(14L, TipoForma.RESOLUCION.getValorId());
		
		assertTrue("La lista debe regresar minimo una lista : ", respuesta.size()>0);
		logger.info("La lista debe regresar minimo una lista : " + respuesta.size());
	}
	
	
	public void testConsultarIndicesEstruturado(){		
		List<IndiceEstructurado> obejtos = indiceEstructuradoDAO.consultarIndicesEstructuradosPorTipoOficio(2L);
		logger.debug("Numero de Indices estructurados " + obejtos.size());
		assertNotNull(obejtos);		
	}
	
	public void consultarDocumentosXTipoDocumento(){
		List<Documento> documentos = daoServcice.consultarDocumentosXTipoDocumento(TipoDocumento.ACTA.getValorId());
		assertNotNull(documentos);
		logger.info("Existen "+documentos.size()+ " documentos");
		for (Documento doc : documentos) {
			logger.info("------------------------------");
//			logger.info("Número de caso: "+doc.getActividad().getExpediente().getCaso().getCasoId()+" ("+doc.getActividad().getExpediente().getCaso().getNumeroGeneralCaso()+")");
			logger.info("Identificador de la controversia resuelta: "+doc.getDocumentoId());
			logger.info("Nombre completo del fiscal que llevó a cabo la controversia: "+doc.getResponsableDocumento().getNombreCompleto());
			logger.info("Nombre del documento: "+doc.getNombreDocumento());
			logger.info("Bandera si ya ha sido leída la controversia: "+doc.getEsEnviado());
			logger.info("Fecha de envío de la misma(creacion doc): "+doc.getFechaCreacion());
//			logger.info("Fecha de envío de la misma(creacion act): "+doc.getActividad().getFechaCreacion());
			logger.info("Archivo digital: "+doc.getArchivoDigital().getArchivoDigitalId());
		}
	}

	public void testConsultarDocumentoPorId() {
		Long documentoId = 355L;
		Documento  doc = daoServcice
				.consultarDocumentoPorId(75L);
		assertNotNull(" El documento consultado no existe.ID:"+ documentoId,doc);

		logger.info("Id Docuemnto: "+doc.getDocumentoId()+": "+doc.getNombreDocumento());
		if(doc.getActividad() != null){
			logger.info(" Número de Expediente: "+doc.getExpediente().getExpedienteId());
			logger.info(" Número de Caso: "+doc.getExpediente().getCaso().getCasoId() +" / "+doc.getExpediente().getCaso().getNumeroGeneralCaso());
		}else
			logger.info("No trae expediente");
		String tipo=(doc.getTipoDocumento()!=null)?doc.getTipoDocumento().getValor():"No trae";
		logger.info(" Tipo de Documento: "+ tipo);
		logger.info(" Fecha de Recepción: "+doc.getFechaCreacion());
		logger.info(" ArchivoDigital: "+doc.getArchivoDigital());
		logger.info(" Actividad: "+doc.getActividad());
	}
	
	public void testArchDigID(){
		Long archivoDigitalID=480L;
		Documento respuesta = daoServcice.consultarDocumentoPorArchivoDigital(archivoDigitalID);
		logger.debug(respuesta.getArchivoDigital().getNombreArchivo());
		logger.debug(respuesta.getArchivoDigital().getArchivoDigitalId().longValue());		
		logger.debug(respuesta.getDocumentoId());
	}
	
	
	public void testConsultarDocumentosReinsercionSocial() {
		
		
		Funcionario funcionario = new Funcionario(48L);
		Documento documento = new Documento();
		NumeroExpediente numeroExpediente = new NumeroExpediente();
		numeroExpediente.setNumeroExpedienteId(1L);
		List<Documento> respuesta = null;
			try{
			
			respuesta = daoServcice
				.consultarDocumentosReinsercionSocial(funcionario, documento, numeroExpediente );
			}catch(Exception exception){
			}
		assertNotNull(respuesta);
		assertTrue("La lista debe tener minimo un registro",
				respuesta.size() > 0);
		logger.info("Documentos obtenidos : " + respuesta.size());
	}
	
	
}

