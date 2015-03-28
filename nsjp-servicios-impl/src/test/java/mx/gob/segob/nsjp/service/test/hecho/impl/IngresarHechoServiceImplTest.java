/**
* Nombre del Programa : IngresarHechoServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de ingresar Hecho
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
package mx.gob.segob.nsjp.service.test.hecho.impl;

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
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.hecho.TiempoDTO;
import mx.gob.segob.nsjp.dto.lugar.InmediacionDTO;
import mx.gob.segob.nsjp.dto.lugar.PuntoCarreteroDTO;
import mx.gob.segob.nsjp.service.hecho.IngresarHechoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de ingresar Hecho.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class IngresarHechoServiceImplTest extends BaseTestServicios<IngresarHechoService> {

	public void testIngresarHecho () {
		HechoDTO hechoDTO = new HechoDTO();
		
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
		CalidadDTO calidadDTO = new CalidadDTO();
		calidadDTO.setDescripcionEstadoFisico("En buen estado lugar");
		calidadDTO.setValorIdCalidad(new ValorDTO(Calidades.LUGAR_HECHOS.getValorId()));
		calidadDTO.setCalidades(Calidades.LUGAR_HECHOS);
		PuntoCarreteroDTO puntoCarreteroDTO = new PuntoCarreteroDTO();
		puntoCarreteroDTO.setValorTipoCarretera(new ValorDTO(708L));
		puntoCarreteroDTO.setNombreCarretera("PH. unitaria");
		puntoCarreteroDTO.setNumCarretera("PH.unit");
		puntoCarreteroDTO.setKilometro((short)1);
		puntoCarreteroDTO.setNombreParaje("PH. unitaria");
		puntoCarreteroDTO.setNombreTramo("PH. nitaria");
		puntoCarreteroDTO.setPoblacionesVecinas("P. unitaria");
		puntoCarreteroDTO.setDescripcion("PH. unitario");
		puntoCarreteroDTO.setLatitud("12.12");
		puntoCarreteroDTO.setLongitud("15.35");
		puntoCarreteroDTO.setInmediaciones(inmediaciones);
		puntoCarreteroDTO.setCalidadDTO(calidadDTO);
		puntoCarreteroDTO.setExpedienteDTO(new ExpedienteDTO(1L)); //Importante expediente
		puntoCarreteroDTO.setFechaCreacionElemento(new Date());
		puntoCarreteroDTO.setValorIdElemento(new ValorDTO(Elementos.LUGAR.getValorId()));
		
		TiempoDTO tiempoDTO = new TiempoDTO();		
		
		Long expedienteId = 1L;
		ExpedienteDTO expedienteDTO = new ExpedienteDTO(expedienteId);
		
		//Especificamente  1768
		tiempoDTO.setTipoRegistro(new ValorDTO(1768L));
		tiempoDTO.setFechaInicio(new Date());
		
		//Lapso 1769
//		tiempoDTO.setTipoRegistro(new ValorDTO(1769L));
//		tiempoDTO.setFechaInicio(new Date());
//		tiempoDTO.setFechaFin(new Date());

		//Otro 1770
//		tiempoDTO.setTipoRegistro(new ValorDTO(1770L));
//		tiempoDTO.setDescripcion("Pru Uni 1770");
//		
		
		hechoDTO.setTiempo(tiempoDTO);
		hechoDTO.setDescNarrativa("Prueba unit con Tiempo y con Lugar");
		
		//LUGAR
		hechoDTO.setLugar(generarDomicilio(expedienteDTO));
		hechoDTO.setExpediente(expedienteDTO); //Importante expediente
		
		try {
			HechoDTO respuesta =  service.ingresarHecho(hechoDTO);
			assertNotNull(respuesta);
			assertTrue("El identificador del hecho debe ser mayor a cero", respuesta.getHechoId()>0);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	private DomicilioDTO generarDomicilio(ExpedienteDTO expedienteDTO){
		DomicilioDTO domicilio = new DomicilioDTO();
		domicilio.setCalle("calle1N");
		domicilio.setNumeroExterior("10N");
		domicilio.setNumeroInterior("201N");
		domicilio.setNumeroLote("LoteN");
		domicilio.setReferencias("Ref MODN");
		domicilio.setEntreCalle1("entre calle 1N");
		domicilio.setEntreCalle2("entre calle 2N");
		domicilio.setAlias("RanchitoN");
		domicilio.setEdificio("CN");
		domicilio.setAsentamientoDTO(new AsentamientoDTO(20684L));
		domicilio.setEntidadDTO(new EntidadFederativaDTO(23L));
		domicilio.setMunicipioDTO(new MunicipioDTO(1L));
		domicilio.setCiudadDTO(new CiudadDTO(1L));
		domicilio.setValorCalleId(new ValorDTO(72L)); //Tipo Calle
		
		domicilio.setFechaCreacionElemento(new Date());
		//Lugar -> Elemento
		domicilio.setDescripcion("descripcion domicilio 1N");
		//CoordenadaGeografica - Lugar
		domicilio.setLatitud("100");
		domicilio.setLongitud("101");
		//Calidad de Domicilio
		CalidadDTO calidadDomicilio = new CalidadDTO();
		calidadDomicilio.setDescripcionEstadoFisico("En buen estado");
		calidadDomicilio.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
		calidadDomicilio.setCalidades(Calidades.DOMICILIO);				
		domicilio.setCalidadDTO(calidadDomicilio);
		domicilio.setExpedienteDTO(expedienteDTO);
		return domicilio;
	}
}
