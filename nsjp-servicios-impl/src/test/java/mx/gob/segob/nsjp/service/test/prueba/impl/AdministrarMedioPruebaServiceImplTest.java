/**
 * 
 */
package mx.gob.segob.nsjp.service.test.prueba.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.MedioPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionDatoMedioPruebaDTO;
import mx.gob.segob.nsjp.service.prueba.AdministrarMedioPruebaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class AdministrarMedioPruebaServiceImplTest extends BaseTestServicios<AdministrarMedioPruebaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.prueba.impl.AdministrarMedioPruebaServiceImpl#registrarMedioPruebaConRelacionADatoPrueba(mx.gob.segob.nsjp.dto.prueba.MedioPruebaDTO, mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO)}.
	 */
	public void testRegistrarMedioPruebaConRelacionADatoPrueba() {
		//Setear el medioPruebaId para actualizar y datoDTO ==null
		DatoPruebaDTO datoDTO =new DatoPruebaDTO(41L, null, null);
//		MedioPruebaDTO medioDTO=new MedioPruebaDTO(null, "Fotografía2", "66A", "Fotografía ampliada del arma", "En almacen 66 del expediente");
		
		MedioPruebaDTO medioDTO=new MedioPruebaDTO(null, "Testigo presente", "66AT", "Familiar del imputado", "Familiar del imputado");
		
		//MP -> Documento
//		DocumentoDTO documentoDTO = new DocumentoDTO();
//		documentoDTO.setNombreDocumento("Fotografia del Arma");
//		ArchivoDigitalDTO archivoDigital = new ArchivoDigitalDTO();
//		byte[] contenido = {12};
//		archivoDigital.setContenido(contenido);
//		archivoDigital.setNombreArchivo("Foto");
//		archivoDigital.setTipoArchivo(".pdf");
//		documentoDTO.setArchivoDigital(archivoDigital);
//		medioDTO.setDocumentoMedioPrueba(documentoDTO);
		
		//MP -> Involucrado
		Long elementoId = 1L;
		ElementoDTO involucrado = new InvolucradoDTO();
		involucrado.setElementoId(elementoId);
		medioDTO.setElementoMedioPrueba(involucrado);
		
		try {
			medioDTO=service.registrarMedioPruebaConRelacionADatoPrueba(medioDTO, datoDTO);
			assertNotNull(medioDTO);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testAceptarCarncelarRelacionMedioPrueba(){
		RelacionDatoMedioPruebaDTO dto=new RelacionDatoMedioPruebaDTO(18L, true, null, null, null, null);
		try {
			Long relacion = service.aceptarCarncelarRelacionMedioPrueba(dto);
			assertNotNull(relacion);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.prueba.impl.AdministrarMedioPruebaServiceImpl#relacionarMedioPruebaConDatoPrueba(mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO, java.util.List)}.
	 */
	public void testRelacionarMedioPruebaConDatoPrueba() {
		DatoPruebaDTO datoDTO=new DatoPruebaDTO(34L, null, null);
		List<MedioPruebaDTO> mediosDTO=new ArrayList<MedioPruebaDTO>();
		MedioPruebaDTO medio=new MedioPruebaDTO(35L, null, null);
		mediosDTO.add(medio);
		try {
			mediosDTO=service.relacionarMedioPruebaConDatoPrueba(datoDTO, mediosDTO);
			assertNotNull(mediosDTO);
			logger.info("Existen "+mediosDTO.size()+ " Medios no relacionados");
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.prueba.impl.AdministrarMedioPruebaServiceImpl#consultarMediosPruebaPorDatoPrueba(mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO)}.
	 */
	public void testConsultarMediosPruebaPorDatoPrueba() {
		
		Long datoPruebaId = 33L;
		DatoPruebaDTO datoPruebaDTO=new DatoPruebaDTO(datoPruebaId, null, null);
		ExpedienteDTO expediente=new ExpedienteDTO();
		expediente.setNumeroExpediente("NSJYUCPJ20112633334");
		datoPruebaDTO.setExpediente(expediente);
		Boolean relacionados = true;
		
		try {
			List<MedioPruebaDTO> mediosDTO = service.consultarMediosPruebaPorDatoPrueba(datoPruebaDTO, relacionados);
			assertNotNull(mediosDTO);
			logger.info("Existen "+mediosDTO.size()+ " Medios");
			for (MedioPruebaDTO dto : mediosDTO) {
				logger.info("-----------");
				logger.info("MedioPruebaId: "+dto.getMedioPruebaId());
				logger.info("NombreMedio: "+dto.getNombreMedio());
				logger.info("getRelacionesDatoMedioPrueba: "+dto.getRelacionesDatoMedioPrueba());
				if(dto.getRelacionesDatoMedioPrueba()!= null &&!dto.getRelacionesDatoMedioPrueba().isEmpty()){
					for (RelacionDatoMedioPruebaDTO relDPMPDTO : dto.getRelacionesDatoMedioPrueba()) {
						logger.info("relDPMPDTO: "+relDPMPDTO.getRelacionDatoMedioPruebaId());
						logger.info("getEsAceptado: "+relDPMPDTO.getEsAceptado());
						logger.info("getMotivoCancelacion: "+relDPMPDTO.getMotivoCancelacion());
						logger.info("getTiempoCancelacion: "+relDPMPDTO.getTiempoCancelacion());
						logger.info("getDatoPrueba: "+relDPMPDTO.getDatoPrueba());
						logger.info("getMedioPrueba: "+relDPMPDTO.getMedioPrueba());
					}
				}
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testConsultarMedioPrueba(){
		try {
			Long medioPruebaId = 47L;
			MedioPruebaDTO medio = service.consultarMedioPrueba(medioPruebaId);
			assertNotNull(medio);
			logger.info("Medio: "+medio.toString());
			if(medio.getDocumentoMedioPrueba()!= null){
				logger.info("Documento: " + medio.getDocumentoMedioPrueba());
				if(medio.getDocumentoMedioPrueba().getArchivoDigital()!= null){
					logger.info("ArchivoDigital: " + medio.getDocumentoMedioPrueba().getArchivoDigital());
				}
			}
			if(medio.getElementoMedioPrueba()!= null){
				logger.info("Elemento Id: " + medio.getElementoMedioPrueba());
				logger.info("Elemento Id: " + medio.getElementoMedioPrueba().getElementoId());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testConsultarDatosPruebaXMedioPrueba(){
		try {
			List<DatoPruebaDTO> datos = service.consultarDatosPruebaXMedioPrueba(6L,false);
			assertNotNull(datos);
			logger.info("Existen "+datos.size()+" datos");
			for (DatoPruebaDTO dto : datos) {
				logger.info(dto.getNombreDato());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
