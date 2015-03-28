/**
* Nombre del Programa : BuscarCasoServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28 Apr 2011
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
package mx.gob.segob.nsjp.service.test.caso.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.caso.FiltroCasoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.FiltroExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Vehiculo;
import mx.gob.segob.nsjp.service.caso.BuscarCasoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
public class BuscarCasoServiceImplTest extends BaseTestServicios<BuscarCasoService> {

	public void testBuscarCasoPorNumero () {
		CasoDTO casoDTO = new CasoDTO();

		casoDTO.setNumeroGeneralCaso("00024");
		casoDTO.setUsuario(getUsuario());
		try {
			List<CasoDTO> respuesta = service.buscarCasoPorNumero(casoDTO);
			
			assertFalse("La lista no debe estar vacia ", respuesta.isEmpty());
			
			for (CasoDTO caso1DTO : respuesta) {
				logger.info("Caso recupeado : " + caso1DTO.getCasoId());
				for (ExpedienteDTO expedienteDTO : caso1DTO.getExpedintesDTO()) {
					logger.info("Expediente : " + expedienteDTO.getExpedienteId());
				}
			}
			
		} catch (NSJPNegocioException e) {			
			logger.error(e.getMessage());
		}
	}
	
	
	public void _testBuscarCasoPorAlias () {
		FiltroExpedienteDTO filtrosDTO = new FiltroExpedienteDTO();
		filtrosDTO.setAlias("caras");
		filtrosDTO.setTipoBusqueda(1L);
		
		try {
			List<InvolucradoDTO> respuesta = service.buscarCasoPorFiltros(filtrosDTO);
			
			assertFalse("La lista no debe estar vacia ", respuesta.isEmpty());
			
			for (InvolucradoDTO involucradoDTO : respuesta) {
				logger.info("Caso recupeado : " + involucradoDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso());
				logger.info("Caso recupeado : " + involucradoDTO.getNombresDemograficoDTO().size());
			}
			
		} catch (NSJPNegocioException e) {			
			logger.error(e.getMessage());
		}
	}
	
	public void _testBuscarCasoPorDelito () {
		FiltroCasoDTO filtrosDTO = new FiltroCasoDTO();
		filtrosDTO.setDelito("2");
		
		try {
			List<CasoDTO> casos = service.buscarCasoPorFiltros(filtrosDTO);
			assertFalse("La lista no puede estar vacia ", casos.isEmpty());
			logger.debug("Casos recuperados " + casos.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testConsultarCasoPorExpediente(){
		
		ExpedienteDTO expedienteDTO=new ExpedienteDTO(133L);
		try {
			CasoDTO caso = service.consultarCasoPorExpediente(expedienteDTO);
			logger.info("El caso es:"+
					"\nID: "+caso.getCasoId()+
					"\nNumero: "+caso.getNumeroGeneralCaso());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void testBuscarReincidenciaDeElementos_Vehiculo() {
		try {
			VehiculoDTO vehiculoDTO = new VehiculoDTO();
			vehiculoDTO.setNoSerie("");
			vehiculoDTO.setPlaca("748PUY");
			List<CasoDTO> casos = service.buscarReincidenciaDeElementos(vehiculoDTO);
			 for (CasoDTO caso : casos) {
				 logger.debug("ID caso:" + caso.getCasoId());
				 logger.debug("Numero General del caso:"+ caso.getNumeroGeneralCaso());
				 logger.debug("Fecha del caso:" + caso.getFechaApertura());
				 logger.debug("Estatus:" + caso.getEstatus().getNombre());		        	
				}
			assertFalse("La lista no puede estar vacia ", casos.isEmpty());
			logger.debug("Casos recuperados " + casos.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testBuscarReincidenciaDeElementos_Persona() {
		try {
			InvolucradoDTO persona = new InvolucradoDTO();
			List<NombreDemograficoDTO> nombreDemos = new ArrayList<NombreDemograficoDTO>();
			NombreDemograficoDTO loND = new NombreDemograficoDTO();
			loND.setNombre("armando");
			loND.setApellidoPaterno("castaneda");
			loND.setApellidoMaterno("tenango");
			nombreDemos.add(loND);
			persona.setNombresDemograficoDTO(nombreDemos);
			List<CasoDTO> casos = service.buscarReincidenciaDeElementos(persona);
			 for (CasoDTO caso : casos) {
				 logger.debug("ID caso:" + caso.getCasoId());
				 logger.debug("Numero General del caso:"+ caso.getNumeroGeneralCaso());
				 logger.debug("Fecha del caso:" + caso.getFechaApertura());
				 logger.debug("Estatus:" + caso.getEstatus().getNombre());		        	
				}
			assertFalse("La lista no puede estar vacia ", casos.isEmpty());
			logger.debug("Casos recuperados " + casos.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
}
