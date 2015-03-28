/**
* Nombre del Programa : IngresarLugarServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para ingresar un Lugar
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
package mx.gob.segob.nsjp.service.test.lugar.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioExtranjeroDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.lugar.AreaGeograficaDTO;
import mx.gob.segob.nsjp.dto.lugar.EspacioAereoDTO;
import mx.gob.segob.nsjp.dto.lugar.EspacioMaritimoDTO;
import mx.gob.segob.nsjp.dto.lugar.InmediacionDTO;
import mx.gob.segob.nsjp.dto.lugar.PuntoCarreteroDTO;
import mx.gob.segob.nsjp.service.lugar.IngresarLugarService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para ingresar un Lugar.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class IngresarLugarServiceImplTest extends
		BaseTestServicios<IngresarLugarService> {

	public void testIngresarLugar() {
		
		LugarDTO respuesta = new LugarDTO();
		DomicilioDTO domicilioDTO = new DomicilioDTO();
		DomicilioExtranjeroDTO domicilioExtranjeroDTO = new DomicilioExtranjeroDTO();
		AreaGeograficaDTO areaGeograficaDTO = new AreaGeograficaDTO();
		EspacioAereoDTO espacioAereoDTO = new EspacioAereoDTO();
		EspacioMaritimoDTO espacioMaritimoDTO = new EspacioMaritimoDTO();
		PuntoCarreteroDTO puntoCarreteroDTO = new PuntoCarreteroDTO(); 
		
		List<InmediacionDTO> inmediaciones = new ArrayList<InmediacionDTO>();
		
		InmediacionDTO inmediacionDTO = new InmediacionDTO();
		InmediacionDTO inmediacionDTO2 = new InmediacionDTO();
		InmediacionDTO inmediacionDTO3 = new InmediacionDTO();
		
		inmediacionDTO.setDescripcion("Inm esp aereo");
		inmediacionDTO.setValor(new ValorDTO(34L));
		inmediaciones.add(inmediacionDTO);
		inmediacionDTO2.setDescripcion("Inm esp aereo 2");
		inmediacionDTO2.setValor(new ValorDTO(36L));
		inmediaciones.add(inmediacionDTO2);
		inmediacionDTO3.setDescripcion("Inm esp aereo 3");
		inmediacionDTO3.setValor(new ValorDTO(35L));
		inmediaciones.add(inmediacionDTO3);
		
		int condicion = 1;
		
		CalidadDTO calidadDTO = new CalidadDTO();
		calidadDTO.setDescripcionEstadoFisico("En buen estado lugar");
		calidadDTO.setValorIdCalidad(new ValorDTO(Calidades.LUGAR_HECHOS.getValorId()));
		calidadDTO.setCalidades(Calidades.LUGAR_HECHOS);
		
		domicilioDTO.setValorCalleId(new ValorDTO(96L));
		domicilioDTO.setEntidadDTO(new EntidadFederativaDTO(23L));
		domicilioDTO.setCiudadDTO(new CiudadDTO());
		domicilioDTO.setMunicipioDTO(new MunicipioDTO(25L));
		domicilioDTO.setValorIdElemento(new ValorDTO(31L));
		domicilioDTO.setCalle("calleLugar1");
		domicilioDTO.setDescripcion("descripcion domicilio lugar 1");
		domicilioDTO.setInmediaciones(inmediaciones);
		domicilioDTO.setFechaCreacionElemento(new Date());
		domicilioDTO.setEntreCalle1("entre calle lugar 1");
		domicilioDTO.setEntreCalle2("entre calle lugar 2");
		domicilioDTO.setAlias("Ranchito lugar ");	
		domicilioDTO.setCalidadDTO(calidadDTO);
		domicilioDTO.setExpedienteDTO(new ExpedienteDTO(2L));
		domicilioDTO.setAsentamientoDTO(new AsentamientoDTO(3L));
						
		domicilioExtranjeroDTO.setValorIdElemento(new ValorDTO(31L));
		domicilioExtranjeroDTO.setCalle("calleLugar1");
		domicilioExtranjeroDTO.setDescripcion("descripcion domicilio lugar 1");
		domicilioExtranjeroDTO.setInmediaciones(inmediaciones);
		domicilioExtranjeroDTO.setFechaCreacionElemento(new Date());
		domicilioExtranjeroDTO.setEntreCalle1("entre calle lugar 1");
		domicilioExtranjeroDTO.setEntreCalle2("entre calle lugar 2");
		domicilioDTO.setAlias("Ranchito lugar ");	
		domicilioExtranjeroDTO.setCalidadDTO(calidadDTO);
		domicilioExtranjeroDTO.setExpedienteDTO(new ExpedienteDTO(2L));
		domicilioExtranjeroDTO.setCiudad("Prue U");
		domicilioExtranjeroDTO.setMunicipio("Mun U");
		domicilioExtranjeroDTO.setPaisValor(new ValorDTO(2L));
		domicilioExtranjeroDTO.setPais("Prue");
		domicilioExtranjeroDTO.setEstado("Est U");
				
		areaGeograficaDTO.setNombre("Nom area geo");
		areaGeograficaDTO.setReferencias("No las hay");
		areaGeograficaDTO.setDescripcion("Desc prue unitaria area geo");
		areaGeograficaDTO.setInmediaciones(inmediaciones);
		areaGeograficaDTO.setCalidadDTO(calidadDTO);
		areaGeograficaDTO.setExpedienteDTO(new ExpedienteDTO(1L));
		areaGeograficaDTO.setFechaCreacionElemento(new Date());
		areaGeograficaDTO.setValorIdElemento(new ValorDTO(Elementos.LUGAR.getValorId()));
		
		espacioAereoDTO.setLineaAerea("Linea prueba");
		espacioAereoDTO.setRuta("Prueba unit");
		espacioAereoDTO.setDescripcion("Desc prue unitaria espacio aereo");
		espacioAereoDTO.setInmediaciones(inmediaciones);
		espacioAereoDTO.setCalidadDTO(calidadDTO);
		espacioAereoDTO.setExpedienteDTO(new ExpedienteDTO(1L));
		espacioAereoDTO.setFechaCreacionElemento(new Date());
		espacioAereoDTO.setValorIdElemento(new ValorDTO(Elementos.LUGAR.getValorId()));
		
		espacioMaritimoDTO.setZonaCostera("Costera p unitaria");
		espacioMaritimoDTO.setZonaMaritima("Maritima p unitaria");
		espacioMaritimoDTO.setPuerto("Peurto p. unitaria");
		espacioMaritimoDTO.setDescripcion("Desc prue unitaria espacio marit");
		espacioMaritimoDTO.setInmediaciones(inmediaciones);
		espacioMaritimoDTO.setCalidadDTO(calidadDTO);
		espacioMaritimoDTO.setExpedienteDTO(new ExpedienteDTO(1L));
		espacioMaritimoDTO.setFechaCreacionElemento(new Date());
		espacioMaritimoDTO.setValorIdElemento(new ValorDTO(Elementos.LUGAR.getValorId()));
		
		puntoCarreteroDTO.setValorTipoCarretera(new ValorDTO(708L));
		puntoCarreteroDTO.setNombreCarretera("P. unitaria");
		puntoCarreteroDTO.setNumCarretera("P. unit");
		puntoCarreteroDTO.setKilometro((short)1);
		puntoCarreteroDTO.setNombreParaje("P. unitaria");
		puntoCarreteroDTO.setNombreTramo("P. nitaria");
		puntoCarreteroDTO.setPoblacionesVecinas("P. unitaria");
		puntoCarreteroDTO.setDescripcion("P. unitario");
		puntoCarreteroDTO.setLatitud("12.12");
		puntoCarreteroDTO.setLongitud("15.35");
		puntoCarreteroDTO.setInmediaciones(inmediaciones);
		puntoCarreteroDTO.setCalidadDTO(calidadDTO);
		puntoCarreteroDTO.setExpedienteDTO(new ExpedienteDTO(1L));
		puntoCarreteroDTO.setFechaCreacionElemento(new Date());
		puntoCarreteroDTO.setValorIdElemento(new ValorDTO(Elementos.LUGAR.getValorId()));
		
		try {
			if (condicion==1)
				respuesta = service.ingresarLugar(domicilioDTO);
			else if  (condicion==2)
				respuesta = service.ingresarLugar(areaGeograficaDTO);
			else if (condicion==3)
				respuesta = service.ingresarLugar(espacioAereoDTO);
			else if (condicion==4)
				respuesta = service.ingresarLugar(espacioMaritimoDTO);
			else if (condicion==5)
				respuesta = service.ingresarLugar(puntoCarreteroDTO);
			else if (condicion==6)
				respuesta = service.ingresarLugar(domicilioExtranjeroDTO);
				assertNotNull(respuesta);
				assertTrue("El identificado del lugar debe ser mayor a cero ", respuesta.getElementoId()>0);
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage());
		}
		
	}
}
