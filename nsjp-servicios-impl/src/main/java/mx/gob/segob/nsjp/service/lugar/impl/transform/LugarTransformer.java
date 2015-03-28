/**
* Nombre del Programa : LugarTransfromer.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para realizar la conversion de Lugar a LugarDTO y viceversa
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
package mx.gob.segob.nsjp.service.lugar.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.lugar.AreaGeograficaDTO;
import mx.gob.segob.nsjp.dto.lugar.EspacioAereoDTO;
import mx.gob.segob.nsjp.dto.lugar.EspacioMaritimoDTO;
import mx.gob.segob.nsjp.dto.lugar.InmediacionDTO;
import mx.gob.segob.nsjp.dto.lugar.PuntoCarreteroDTO;
import mx.gob.segob.nsjp.model.AreaGeografica;
import mx.gob.segob.nsjp.model.CoordenadaGeografica;
import mx.gob.segob.nsjp.model.EspacioAereo;
import mx.gob.segob.nsjp.model.EspacioMaritimo;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Inmediacion;
import mx.gob.segob.nsjp.model.Lugar;
import mx.gob.segob.nsjp.model.PuntoCarretero;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Clase para realizar la conversion de Lugar a LugarDTO y viceversa.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class LugarTransformer {

	/**
	 * 
	 * @param areaGeograficaDTO
	 * @return
	 */
	public static AreaGeografica transformarAreaGeografica (AreaGeograficaDTO areaGeograficaDTO) {
		AreaGeografica areaGeografica = new AreaGeografica();
		
		//AreaGeografica
		areaGeografica.setNombre(areaGeograficaDTO.getNombre());
		areaGeografica.setReferencias(areaGeograficaDTO.getReferencias());
		//Lugar
		areaGeografica.setDescripcion(areaGeograficaDTO.getDescripcion());
		//Elemento
		areaGeografica.setFechaCreacionElemento(areaGeograficaDTO.getFechaCreacionElemento());
		if (areaGeograficaDTO.getValorIdElemento()!=null)
			areaGeografica.setTipoElemento(new Valor(areaGeograficaDTO.getValorIdElemento().getIdCampo()));
		if (areaGeograficaDTO.getExpedienteDTO()!=null)
			areaGeografica.setExpediente(new Expediente(areaGeograficaDTO.getExpedienteDTO().getExpedienteId()));
		
		return areaGeografica;
	}
	 
	/**
	 * 
	 * @param lg
	 * @return
	 */
	public static AreaGeograficaDTO transformarAreaGeografica(AreaGeografica areaGeo) {
		AreaGeograficaDTO areaGeoDTO = new AreaGeograficaDTO();
		
		areaGeoDTO.setElementoId(areaGeo.getElementoId());
		areaGeoDTO.setNombre(areaGeo.getNombre());
		areaGeoDTO.setReferencias(areaGeo.getReferencias());
		
		areaGeoDTO.setDescripcion(areaGeo.getDescripcion());
		
		areaGeoDTO.setFechaCreacionElemento(areaGeo.getFechaCreacionElemento());
		if (areaGeo.getTipoElemento()!=null)
			areaGeoDTO.setValorIdElemento(new ValorDTO(areaGeo.getTipoElemento().getValorId()));
		if (areaGeo.getExpediente()!=null)
			areaGeoDTO.setExpedienteDTO(new ExpedienteDTO(areaGeo.getExpediente().getExpedienteId()));
		
		return areaGeoDTO;
	}
	
	
	/**
	 * Transforma de InmediacionDTO a Inmediacion
	 * @param inmediacionDTO
	 * @return
	 */
	public static Inmediacion transformarInmediacion (InmediacionDTO inmediacionDTO){
		Inmediacion inmediacion = new Inmediacion();
		
		inmediacion.setDescripcion(inmediacionDTO.getDescripcion());
		if (inmediacionDTO.getValor()!=null)
			inmediacion.setValor(new Valor(inmediacionDTO.getValor().getIdCampo()));
		
		return inmediacion;
	}

	/**
	 * Transforma de EspacioAereoDTO a EspacioAereo
	 * @param espacioAereoDTO
	 * @return
	 */
	public static EspacioAereo transformarEspacioAereo(
			EspacioAereoDTO espacioAereoDTO) {
		EspacioAereo espacioAereo = new EspacioAereo();
		//EspacioAereo
		espacioAereo.setLineaAerea(espacioAereoDTO.getLineaAerea());
		espacioAereo.setRuta(espacioAereoDTO.getRuta());
		//Lugar
		espacioAereo.setDescripcion(espacioAereoDTO.getDescripcion());
		//Elemento
		espacioAereo.setFechaCreacionElemento(espacioAereoDTO.getFechaCreacionElemento());
		if (espacioAereoDTO.getValorIdElemento()!=null)
			espacioAereo.setTipoElemento(new Valor(espacioAereoDTO.getValorIdElemento().getIdCampo()));
		if (espacioAereoDTO.getExpedienteDTO()!=null)
			espacioAereo.setExpediente(new Expediente(espacioAereoDTO.getExpedienteDTO().getExpedienteId()));
		
		return espacioAereo;
	}
	
	/**
	 * 
	 * @param lg
	 * @return
	 */
	public static EspacioAereoDTO transformarEspacioAereo(EspacioAereo espacioAereo) {
		EspacioAereoDTO espacioAereoDTO = new EspacioAereoDTO();
		//EspacioAereo
		espacioAereoDTO.setLineaAerea(espacioAereo.getLineaAerea());
		espacioAereoDTO.setRuta(espacioAereo.getRuta());
		//Lugar
		espacioAereoDTO.setDescripcion(espacioAereo.getDescripcion());
		//Elemento
		espacioAereoDTO.setFechaCreacionElemento(espacioAereo.getFechaCreacionElemento());
		if (espacioAereo.getTipoElemento()!=null)
			espacioAereoDTO.setValorIdElemento(new ValorDTO(espacioAereo.getTipoElemento().getValorId()));
		if (espacioAereo.getExpediente()!=null)
			espacioAereoDTO.setExpedienteDTO(new ExpedienteDTO(espacioAereo.getExpediente().getExpedienteId()));
		
		return espacioAereoDTO;
	}
	

	/**
	 * Transforma de {@link EspacioMaritimoDTO} a {@link EspacioMaritimo}
	 * @param espacioMaritimoDTO
	 * @return
	 */
	public static EspacioMaritimo transformarEspacioMaritimo(
			EspacioMaritimoDTO espacioMaritimoDTO) {
		EspacioMaritimo espacioMaritimo = new EspacioMaritimo();
		//EspaicoMaritimo
		espacioMaritimo.setZonaCostera(espacioMaritimoDTO.getZonaCostera());
		espacioMaritimo.setZonaMaritima(espacioMaritimoDTO.getZonaMaritima());
		espacioMaritimo.setPuerto(espacioMaritimo.getPuerto());
		//Lugar
		espacioMaritimo.setDescripcion(espacioMaritimoDTO.getDescripcion());
		//Elemento
		espacioMaritimo.setFechaCreacionElemento(espacioMaritimoDTO.getFechaCreacionElemento());
		if (espacioMaritimoDTO.getValorIdElemento()!=null)
			espacioMaritimo.setTipoElemento(new Valor(espacioMaritimoDTO.getValorIdElemento().getIdCampo()));
		if (espacioMaritimoDTO.getExpedienteDTO()!=null)
			espacioMaritimo.setExpediente(new Expediente(espacioMaritimoDTO.getExpedienteDTO().getExpedienteId()));
		return espacioMaritimo;
	}
	
	/**
	 * 
	 * @param lg
	 * @return
	 */
	public static EspacioMaritimoDTO transformarEspacioMaritimo(
			EspacioMaritimo espMar) {
		EspacioMaritimoDTO espMarDTO = new EspacioMaritimoDTO();
		//EspaicoMaritimo
		espMarDTO.setZonaCostera(espMar.getZonaCostera());
		espMarDTO.setZonaMaritima(espMar.getZonaMaritima());
		espMarDTO.setPuerto(espMar.getPuerto());
		//Lugar
		espMarDTO.setDescripcion(espMar.getDescripcion());
		//Elemento
		espMarDTO.setFechaCreacionElemento(espMar.getFechaCreacionElemento());
		if (espMar.getTipoElemento()!=null)
			espMarDTO.setValorIdElemento(new ValorDTO(espMar.getTipoElemento().getValorId()));
		if (espMar.getExpediente()!=null)
			espMarDTO.setExpedienteDTO(new ExpedienteDTO(espMar.getExpediente().getExpedienteId()));
		return espMarDTO;
	}	

	/**
	 * Transfomra de {@link PuntoCarreteroDTO} a {@link PuntoCarretero}
	 * @param puntoCarreteroDTO
	 * @return
	 */
	public static PuntoCarretero transformarPuntoCarretero(
			PuntoCarreteroDTO puntoCarreteroDTO) {
		PuntoCarretero puntoCarretero = new PuntoCarretero();
		//PuntoCarretero
		puntoCarretero.setValor(new Valor(puntoCarreteroDTO.getValorTipoCarretera().getIdCampo()));
		puntoCarretero.setNombreCarretera(puntoCarreteroDTO.getNombreCarretera());
		puntoCarretero.setNumCarretera(puntoCarreteroDTO.getNumCarretera());
		puntoCarretero.setKilometro(puntoCarreteroDTO.getKilometro());
		puntoCarretero.setNombreParaje(puntoCarreteroDTO.getNombreParaje());
		puntoCarretero.setNombreTramo(puntoCarreteroDTO.getNombreTramo());
		puntoCarretero.setPoblacionesVecinas(puntoCarreteroDTO.getPoblacionesVecinas());
		//Lugar
		puntoCarretero.setDescripcion(puntoCarreteroDTO.getDescripcion());
		//Elemento
		puntoCarretero.setFechaCreacionElemento(puntoCarreteroDTO.getFechaCreacionElemento());
		if (puntoCarreteroDTO.getValorIdElemento()!=null)
			puntoCarretero.setTipoElemento(new Valor(puntoCarreteroDTO.getValorIdElemento().getIdCampo()));
		if (puntoCarreteroDTO.getExpedienteDTO()!=null)
			puntoCarretero.setExpediente(new Expediente(puntoCarreteroDTO.getExpedienteDTO().getExpedienteId()));
		
		return puntoCarretero;
	}
	
	/**
	 * 
	 * @param lg
	 * @return
	 */
	public static PuntoCarreteroDTO transformarPuntoCarretero(PuntoCarretero punCarr) {
		PuntoCarreteroDTO punCarrDTO = new PuntoCarreteroDTO();
		//punCarrDTO
		punCarrDTO.setValorTipoCarretera(new ValorDTO(punCarr.getValor().getValorId()));
		punCarrDTO.setNombreCarretera(punCarr.getNombreCarretera());
		punCarrDTO.setNumCarretera(punCarr.getNumCarretera());
		punCarrDTO.setKilometro(punCarr.getKilometro());
		punCarrDTO.setNombreParaje(punCarr.getNombreParaje());
		punCarrDTO.setNombreTramo(punCarr.getNombreTramo());
		punCarrDTO.setPoblacionesVecinas(punCarr.getPoblacionesVecinas());
		//Lugar
		punCarrDTO.setDescripcion(punCarr.getDescripcion());
		//Elemento
		punCarrDTO.setFechaCreacionElemento(punCarr.getFechaCreacionElemento());
		if (punCarr.getValor()!=null)
			punCarrDTO.setValorIdElemento(new ValorDTO(punCarr.getValor().getValorId()));
		if (punCarr.getExpediente()!=null)
			punCarrDTO.setExpedienteDTO(new ExpedienteDTO(punCarr.getExpediente().getExpedienteId()));
		
		return punCarrDTO;
	}
		
	/**
	 * 
	 * @param lugarDTO
	 * @return
	 */
	public static Lugar transformarLugar(LugarDTO lugarDTO) {
		Lugar lugar = new Lugar();
		//Lugar
		lugar.setDescripcion(lugarDTO.getDescripcion());
		lugar.setElementoId(lugarDTO.getElementoId());
		
		//Elemento
		lugar.setFechaCreacionElemento(lugarDTO.getFechaCreacionElemento());
		if (lugarDTO.getValorIdElemento()!=null)
			lugar.setTipoElemento(new Valor(lugarDTO.getValorIdElemento().getIdCampo()));
		if (lugarDTO.getExpedienteDTO()!=null)
			lugar.setExpediente(new Expediente(lugarDTO.getExpedienteDTO().getExpedienteId()));
		
		return lugar;
	}

	public static LugarDTO transformarLugar(Lugar lugar) {
		if (lugar==null){
			return null;
		}
		LugarDTO lugarDTO = new LugarDTO();
		lugarDTO.setElementoId(lugar.getElementoId());
		//Lugar
		lugarDTO.setDescripcion(lugar.getDescripcion());		
		//Elemento
		lugarDTO.setFechaCreacionElemento(lugar.getFechaCreacionElemento());
		
		if(lugar.getCoordenadaGeograficas()!= null && !lugar.getCoordenadaGeograficas().isEmpty()){
			for (CoordenadaGeografica coorGeo : lugar.getCoordenadaGeograficas()) {
				lugarDTO.setLatitud(coorGeo.getLatitud());
				lugarDTO.setLongitud(coorGeo.getLongitud());
			}		
		}
		return lugarDTO;
	}
	
}
