/**
 * Nombre del Programa : ConsultarDocumentosXTipoDocumentoTest.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 28/06/2011
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
package mx.gob.segob.nsjp.service.test.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentosXTipoDocumentoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
public class ConsultarDocumentosXTipoDocumentoTest extends
		BaseTestServicios<ConsultarDocumentosXTipoDocumentoService> {

	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.service.documento.impl.ConsultarDocumentosXTipoDocumentoServiceImpl#consultarDocumentosXTipoDocumento(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO, java.lang.Long)}
	 * .
	 */
	public void testConsultarDocumentosXTipoDocumento() {
		Long tipoDocumento = TipoDocumento.AMPARO.getValorId();
		Boolean valor=true; 
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setExpedienteId(186L);
		expedienteDTO.setNumeroExpedienteId(198L);

		try {
			List<DocumentoDTO> docums = service
					.consultarDocumentosXTipoDocumento(expedienteDTO,
							tipoDocumento);
			logger.info("Existen " + docums.size());
			if(docums.size()==0) valor=false;
			for (DocumentoDTO doc : docums) {
				logger.info("************************************************");
				logger.info("Id Docuemnto: " + doc.getDocumentoId() + ": "
						+ doc.getNombreDocumento());
				if (doc.getExpedienteDTO() != null) {
					logger.info(" Número de Expediente: "
							+ doc.getExpedienteDTO().getExpedienteId());
					logger.info(" Número de Caso: "
							+ doc.getExpedienteDTO().getCasoDTO().getCasoId()
							+ " / "
							+ doc.getExpedienteDTO().getCasoDTO()
									.getNumeroGeneralCaso());
				} else
				{
					logger.info("No trae expediente");
				}
				if(valor==false) logger.info("No se encontro documentos");
				String tipo = (doc.getTipoDocumentoDTO() != null) ? doc
						.getTipoDocumentoDTO().getValor() : "No trae";
				logger.info(" Tipo de Documento: " + tipo);
				logger.info(" Nombre del perito: ");
				logger.info(" Especialidad: ");
				logger.info(" Fecha de Recepción: " + doc.getFechaCreacion());
				logger.info(" Descripcion: " + doc.getDescripcion());
				logger.info(" Folio: " + doc.getFolioDocumento());
				if(doc.getOficioEstructuradoDTO()!=null)
					logger.info("oficio: "+doc.getOficioEstructuradoDTO().getEncabezado());
			}

		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue("Fallo: consultarDocumentosXTipoDocumento", false);
		}
	}

}
