/**
* Nombre del Programa : AdministrarDatoPruebaServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/09/2011
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
package mx.gob.segob.nsjp.service.test.prueba.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionDatoMedioPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionPruebaInvolucradoDTO;
import mx.gob.segob.nsjp.service.prueba.AdministrarDatoPruebaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.apache.log4j.Logger;

/**
 * Pruebas Unitarias para los servicios de Administrar Datos de Pruebas.
 * 
 * @version 1.0
 * @author GustavoBP
 */
public class AdministrarDatoPruebaServiceImplTest  extends BaseTestServicios< AdministrarDatoPruebaService> {
	private final static Logger logger = Logger.getLogger(AdministrarDatoPruebaServiceImplTest.class);
			

	public void testConsultarDatosPruebaPorFiltro(){
		List<DatoPruebaDTO> listaDatosPrueba = new ArrayList<DatoPruebaDTO>();
		DatoPruebaDTO filtroDTO = new DatoPruebaDTO();
		String numeroExpediente ="NSJYUCPJ20112633334";
		
		try {
			listaDatosPrueba = service.consultarDatosPruebaPorFiltro(filtroDTO, numeroExpediente );
			assertTrue("La lista debe tener minimo un registro : ", listaDatosPrueba.size()>0);
			logger.info("Número de registros : " + listaDatosPrueba.size());
			for (DatoPruebaDTO datoPruebaDTO : listaDatosPrueba) {
				AdministrarDatoPruebaServiceImplTest.imprimirDatoPrueba(datoPruebaDTO);
			}
			logger.info("Número de registros : " + listaDatosPrueba.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	
	public void testRegistrarDatoPrueba(){
		DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO();
		datoPruebaDTO.setNombreDato("Arma2");
		datoPruebaDTO.setNumeroIdentificacion("1234QWER5678");
		datoPruebaDTO.setDescripcion("Es el arma encontrada bajo la cama");
		String numeroExpediente ="NSJYUCPJ20112633334";
		
		
		try {
			DatoPruebaDTO datoPruebaRegresoDTO  = service.registrarActualizarDatoPrueba(datoPruebaDTO, numeroExpediente);
			assertNotNull("El servicio debe retornar un datoPruebaDTO creado ", datoPruebaRegresoDTO);
			AdministrarDatoPruebaServiceImplTest.imprimirDatoPrueba(datoPruebaDTO);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
			assertTrue(false);
		}
	}
	
	public void testActualizarDatoPrueba(){
		DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO();
		
		//Indispensable el ID
		Long datoPruebaId = 1L;
		datoPruebaDTO.setDatoPruebaId(datoPruebaId);
		datoPruebaDTO.setNombreDato("Dato Prueba service Modificado");
		datoPruebaDTO.setNumeroIdentificacion("1234QWER5678 10");
		String numeroExpediente =""; //No es necesario
		
		try {
			DatoPruebaDTO datoPruebaRegresoDTO  = service.registrarActualizarDatoPrueba(datoPruebaDTO, numeroExpediente );
			assertNotNull("El servicio debe retornar un datoPruebaDTO creado ", datoPruebaRegresoDTO);
			AdministrarDatoPruebaServiceImplTest.imprimirDatoPrueba(datoPruebaDTO);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public static  void imprimirDatoPrueba(DatoPruebaDTO datoPruebaDTO){
		if(datoPruebaDTO==null)
			return ;
		logger.info(" datoPrueba - DatoPruebaId:"+datoPruebaDTO.getDatoPruebaId());
		logger.info(" datoPrueba - NombreDato:"+datoPruebaDTO.getNombreDato());
		logger.info(" datoPrueba - NumeroIdentificacion:"+datoPruebaDTO.getNumeroIdentificacion());
		logger.info(" datoPrueba - Descripcion:"+datoPruebaDTO.getDescripcion());
		logger.info(" datoPrueba - FolioCadenaCustodia:"+datoPruebaDTO.getFolioCadenaCustodia());
		logger.info(" datoPrueba - EsAceptado:"+datoPruebaDTO.getEsAceptado());
		logger.info(" datoPrueba - TiempoCancelacion:"+datoPruebaDTO.getTiempoCancelacion());
		
		logger.info(" datoPrueba - Expediente:"+datoPruebaDTO.getExpediente());
		logger.info(" datoPrueba - ElementoPrueba:"+datoPruebaDTO.getElementoPrueba());
//		logger.info(" datoPrueba - RelacionesDatosMedioPrueba:"+datoPrueba.getRelacionesDatosMedioPrueba());
//		logger.info(" datoPrueba - RelacionesPruebaInvolucrado:"+datoPrueba.getRelacionesPruebaInvolucrado());	
	}
	
	public void testRelacionarPruebaAInvolucrado(){
		DatoPruebaDTO datoDTO=new DatoPruebaDTO(41L, null, null);
		List<InvolucradoDTO> listaResponsables=new ArrayList<InvolucradoDTO>();
		listaResponsables.add(new InvolucradoDTO(4L));
		try {
			Long idPrueba = service.relacionarPruebaAInvolucrado(datoDTO, listaResponsables);
			assertNotNull(idPrueba);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testAceptarCancelarDatoPrueba(){
		DatoPruebaDTO datoDTO=new DatoPruebaDTO(20L, null, null);
		datoDTO.setEsAceptado(true);
		try {
			datoDTO = service.aceptarCancelarDatoPrueba(datoDTO);
			assertNotNull(datoDTO);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testConsultarDatoPrueba(){
		try {
			DatoPruebaDTO dato = service.consultarDatoPrueba(51L);
			assertNotNull(dato);
			logger.info("ID: "+dato.getDatoPruebaId());
			logger.info("Nombre: "+dato.getNombreDato());
			logger.info("Numero: "+dato.getNumeroIdentificacion());
			logger.info("Descripcion: "+dato.getDescripcion());
			logger.info("Folio CC: "+dato.getFolioCadenaCustodia());
			if(dato.getEsAceptado()!=null){
				logger.info("Es aceptado: "+dato.getEsAceptado());
				if(!dato.getEsAceptado()){
				logger.info("Fecha Rechazo: "+dato.getTiempoCancelacion());
				logger.info("Motivo rechazo: "+dato.getMotivoCancelacion());
				}
			}else
				logger.info("Es aceptado: Pendiente");
			
			logger.info("Elemento: "+dato.getElementoPrueba());
			if(dato.getRelacionesDatosMedioPrueba()!=null&&!dato.getRelacionesDatosMedioPrueba().isEmpty()){
				for (RelacionDatoMedioPruebaDTO relacion : dato.getRelacionesDatosMedioPrueba()) {
					logger.info("* ID: "+relacion);
					logger.info("* MedioID: "+relacion.getMedioPrueba().getMedioPruebaId());
					logger.info("* Medio: "+relacion.getMedioPrueba().getNombreMedio());
					if(relacion.getEsAceptado()!=null){
						logger.info("*Es aceptado: "+relacion.getEsAceptado());
						if(!relacion.getEsAceptado()){
						logger.info("*Fecha Rechazo: "+relacion.getTiempoCancelacion());
						logger.info("*Motivo rechazo: "+relacion.getMotivoCancelacion());
						}
					}else
						logger.info("*Es aceptado: Pendiente");
				}
			}
			if(dato.getRelacionesPruebaInvolucrado()!=null&&!dato.getRelacionesPruebaInvolucrado().isEmpty()){
				for (RelacionPruebaInvolucradoDTO relInv : dato.getRelacionesPruebaInvolucrado()) {
					logger.info("%Id: "+relInv.getRelacionPruebaInvolucradoId());
					logger.info("%InvolId: "+relInv.getInvolucrado().getElementoId());
					logger.info("%Invol: "+relInv.getInvolucrado().getNombreCompleto());
				}
			}
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
	public void testConsultarDatosPruebaXAudiencia(){
		List<InvolucradoDTO> listaResponsables = new ArrayList<InvolucradoDTO>();
		InvolucradoDTO involucradoDTO = new InvolucradoDTO(24L);
		listaResponsables.add(involucradoDTO);
		
		String numeroExpediente = "NSJYUCPJ20112633334";
		
		try {
			List<DatoPruebaDTO>  listaDatosPrueba = service.consultarDatosPruebaXAudiencia(listaResponsables, numeroExpediente);
			assertTrue("La lista debe tener minimo un registro : ", listaDatosPrueba.size()>0);
			logger.info("Número de registros : " + listaDatosPrueba.size());
			for (DatoPruebaDTO datoPruebaDTO : listaDatosPrueba) {
				AdministrarDatoPruebaServiceImplTest.imprimirDatoPrueba(datoPruebaDTO);
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testObtenerImputadosAudienciaSinRelacionConPrueba(){
		
		try {
			AudienciaDTO audienciaDTO = new AudienciaDTO();
			audienciaDTO.setId(107L);
			String numeroExpediente = "NSJYUCPJ20112633334"; 
			DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO();
			datoPruebaDTO.setDatoPruebaId(41L);
			
			
			List<InvolucradoViewDTO> listaResponsables = service.obtenerImputadosAudienciaSinRelacionConPrueba(
					audienciaDTO, datoPruebaDTO, numeroExpediente);
			assertTrue("La lista debe tener minimo un registro : ", listaResponsables.size()>0);
			logger.info("Número de registros : " + listaResponsables.size());
			for (InvolucradoViewDTO involucradoViewDTO : listaResponsables) {
				logger.info("Involucrado ID: " + involucradoViewDTO.getInvolucradoId());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testConsultarPruebasPorNumeroExpediente(){
		List<DatoPruebaDTO> listaDatosPrueba = new ArrayList<DatoPruebaDTO>();
		String numeroExpediente ="NSJYUCPJ20114433333";
		
		try {
			listaDatosPrueba = service.consultarPruebasPorNumeroExpediente(numeroExpediente );
			assertTrue("La lista debe tener minimo un registro : ", listaDatosPrueba.size()>0);
			logger.info("Número de registros : " + listaDatosPrueba.size());
			for (DatoPruebaDTO datoPruebaDTO : listaDatosPrueba) {
				AdministrarDatoPruebaServiceImplTest.imprimirDatoPrueba(datoPruebaDTO);
			}
			logger.info("Número de registros : " + listaDatosPrueba.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
}
