/**
 * Nombre del Programa : GuardarDocumentoServiceImplTest.java
 * Autor                            : Emigdio
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 16/06/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Clase de pruebas unitarias para el servicio de guardado de documento
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.CuerpoOficioEstructuradoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.IndiceEstructuradoDTO;
import mx.gob.segob.nsjp.dto.documento.OficioEstructuradoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.service.documento.GuardarDocumentoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de pruebas unitarias para el servicio de guardado de documento
 * 
 * @version 1.0
 * @author Emigdio
 * 
 */
public class GuardarDocumentoServiceImplTest extends
		BaseTestServicios<GuardarDocumentoService> {

	/**
	 * Ejecuta la prueba del guardado parcial del documento
	 */
	public void testGuardarDocumentoParcial() {

		ExpedienteDTO expediente = new ExpedienteDTO(new Long(2));
		FormaDTO forma = new FormaDTO(new Long(1));
		expediente.setArea(new AreaDTO(Areas.COORDINACION_ATENCION_TEMPRANA_PG));
		DocumentoDTO documento = new DocumentoDTO();
		documento.setFormaDTO(forma);
		documento.setFechaCreacion(new Date());
		documento.setNombreDocumento("TEST");
		documento.setTipoDocumentoDTO(new ValorDTO(82L));
		documento.setTextoParcial("<span>hola</span>");
		documento.setUsuario(getUsuario());
		documento.setOficioEstructuradoDTO(llenarOficioEstructurado());

		try {
			service.guardarDocumento(documento, expediente);
		} catch (NSJPNegocioException e) {
			logger.equals(e);
			assertTrue("Servicio generó excepción", false);
		}

	}

	public void guardarDocumentoDeSolicitud() {
		DocumentoDTO documento = new DocumentoDTO();
		documento.setDocumentoId(5L);
		documento.setFechaCreacion(new Date());
		documento.setTextoParcial("<span>hola</span>");
		documento.setFormaDTO(new FormaDTO(1L));
		documento.setNombreDocumento("prueba");
		try {
			service.guardarDocumento(documento, null);
		} catch (NSJPNegocioException e) {
			logger.equals(e);
			assertTrue("Servicio generó excepción", false);
		}

	}

	public void testGuardarTeoriaDeCaso() {
		ExpedienteDTO expediente = new ExpedienteDTO(new Long(3));
		expediente.setArea(new AreaDTO(Areas.COORDINACION_ATENCION_TEMPRANA_PG));
		DocumentoDTO documento = new DocumentoDTO();

		documento.setTextoParcial("<span>hola teoría de caso</span>");

		documento.setUsuario(getUsuario());
		documento.setExpedienteDTO(expediente);

		documento.setOficioEstructuradoDTO(llenarOficioEstructurado());
		try {
			service.guardarTeoriaDeCaso(documento);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	private OficioEstructuradoDTO llenarOficioEstructurado() {
		OficioEstructuradoDTO oficio = new OficioEstructuradoDTO();

		oficio.setOficioEstructuradoId(4L);
		
		oficio.setEncabezado("Este encabezado");
		oficio.setPie("Este pié");
		oficio.setTipoOficio(new ValorDTO(1L));
		List<CuerpoOficioEstructuradoDTO> cuerposOficio = new ArrayList<CuerpoOficioEstructuradoDTO>();
		CuerpoOficioEstructuradoDTO cuerpo;
//		for (int i = 5; i < 7; i++) {
//			cuerpo = new CuerpoOficioEstructuradoDTO();
////			cuerpo.setCuerpoOficioEstructuradoId(new Long(i));
////			cuerpo.setOficioEstructuradoDTO(oficio);
//			cuerpo.setInteresa(true);
//		
//			cuerpo.setNumeracion(i);
//			cuerpo.setSecuencia(i);
//			cuerpo.setTexto("Este es el texto de prueba para el cuerpo número "
//					+ i);
//			IndiceEstructuradoDTO indice = new IndiceEstructuradoDTO();
//			indice.setIndiceEstructuradoId(9L);
//			indice.setTipoOficio(new ValorDTO(1L));
//			cuerpo.setIndiceEstructurado(indice);
//			cuerposOficio.add(cuerpo);
//		}
		
		cuerpo = new CuerpoOficioEstructuradoDTO();
		cuerpo.setInteresa(true);
		cuerpo.setOficioEstructuradoDTO(oficio);
		
		cuerpo.setNumeracion(66);
		cuerpo.setSecuencia(66);
		cuerpo.setTexto("Este es el texto de prueba para el cuerpo número " + 66);
		IndiceEstructuradoDTO indice = new IndiceEstructuradoDTO();
		indice.setIndiceEstructuradoId(9L);
		indice.setTipoOficio(new ValorDTO(1L));
		cuerpo.setIndiceEstructurado(indice);
		
		cuerposOficio.add(cuerpo);

		oficio.setCuerposOficio(cuerposOficio);

		return oficio;
	}

//	private OficioEstructuradoDTO llenarOficioEstructurado2() {
//		OficioEstructuradoDTO oficio = new OficioEstructuradoDTO();
//
//		oficio.setOficioEstructuradoId(14L);
//		oficio.setEncabezado("Este encabezado");
//		oficio.setPie("Este pié");
//		oficio.setTipoOficio(new ValorDTO(1L));
//		List<CuerpoOficioEstructuradoDTO> cuerposOficio = new ArrayList<CuerpoOficioEstructuradoDTO>();
//		CuerpoOficioEstructuradoDTO cuerpo;
//		for (int i = 2; i < 5; i++) {
//			cuerpo = new CuerpoOficioEstructuradoDTO();
//			cuerpo.setCuerpoOficioEstructuradoId(new Long(i));
//			cuerpo.setNumeracion(i);
//			cuerpo.setSecuencia(i);
//			cuerpo.setTexto("Este es el texto de prueba para el cuerpo número "
//					+ i);
//			IndiceEstructuradoDTO indice = new IndiceEstructuradoDTO();
//			indice.setIndiceEstructuradoId(1L);
//			indice.setTipoOficio(new ValorDTO(1L));
//			cuerpo.setIndiceEstructurado(indice);
//			cuerposOficio.add(cuerpo);
//		}
//		cuerpo = new CuerpoOficioEstructuradoDTO();
//		cuerpo.setNumeracion(66);
//		cuerpo.setSecuencia(66);
//		cuerpo.setTexto("Este es el texto de prueba para el cuerpo número " + 66);
//		IndiceEstructuradoDTO indice = new IndiceEstructuradoDTO();
//		indice.setIndiceEstructuradoId(1L);
//		indice.setTipoOficio(new ValorDTO(1L));
//		cuerpo.setIndiceEstructurado(indice);
//		cuerposOficio.add(cuerpo);
//
//		oficio.setCuerposOficio(cuerposOficio);
//
//		return oficio;
//	}
}
