/**
* Nombre del Programa : DomicilioTransformer.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la conversion de objetos Domicilio y DomicilioDTO
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
package mx.gob.segob.nsjp.service.domicilio.impl.transform;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioExtranjeroDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.domicilio.TipoAsentamientoDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.model.Asentamiento;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.CatTipoAsentamiento;
import mx.gob.segob.nsjp.model.Ciudad;
import mx.gob.segob.nsjp.model.CoordenadaGeografica;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.DomicilioExtranjero;
import mx.gob.segob.nsjp.model.EntidadFederativa;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Municipio;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Clase para la conversion de objetos Domicilio y DomicilioDTO.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class DomicilioTransformer {

	/**
	 * 
	 * @param domicilio
	 * @return
	 */
	public static DomicilioDTO transformarDomicilio (Domicilio domicilio) {		
		
		if (domicilio instanceof DomicilioExtranjero) {
			DomicilioExtranjeroDTO domExtranjeroDTO = new DomicilioExtranjeroDTO();
			DomicilioExtranjero domExtranjero = (DomicilioExtranjero) domicilio;
			
			domExtranjeroDTO.setElementoId(domExtranjero.getElementoId());
			domExtranjeroDTO.setFolioElemento(domExtranjero.getFolioElemento());
			domExtranjeroDTO.setFechaCreacionElemento(domExtranjero.getFechaCreacionElemento());
			
			domExtranjeroDTO.setDescripcion(domExtranjero.getDescripcion());
			
			domExtranjeroDTO.setAlias(domExtranjero.getAlias());
			domExtranjeroDTO.setCalle(domExtranjero.getCalle());
			domExtranjeroDTO.setNumeroExterior(domExtranjero.getNumeroExterior());
			domExtranjeroDTO.setNumeroInterior(domExtranjero.getNumeroInterior());
			domExtranjeroDTO.setCalle(domExtranjero.getCalle());
			domExtranjeroDTO.setEntreCalle1(domExtranjero.getEntreCalle1());
			domExtranjeroDTO.setEntreCalle2(domExtranjero.getEntreCalle2());
			domExtranjeroDTO.setEdificio(domExtranjero.getEdificio());
			domExtranjeroDTO.setReferencias(domExtranjero.getReferencias());
			
			domExtranjeroDTO.setEstado(domExtranjero.getEstadoStr());
			domExtranjeroDTO.setCiudad(domExtranjero.getCiudadStr());
			domExtranjeroDTO.setMunicipio(domExtranjero.getMunicipioStr());
			domExtranjeroDTO.setAsentamientoExt(domExtranjero.getAsentamientoExt());
			domExtranjeroDTO.setCodigoPostal(domExtranjero.getCodigoPostal());
			domExtranjeroDTO.setPaisValor(new ValorDTO(domExtranjero.getValor().getValorId(), domExtranjero.getValor().getValor()));
			
			if (domExtranjero.getCoordenadaGeograficas()!=null) {
				for (CoordenadaGeografica coorGeo : domExtranjero.getCoordenadaGeograficas()) {
					domExtranjeroDTO.setLatitud(coorGeo.getLatitud());
					domExtranjeroDTO.setLongitud(coorGeo.getLongitud());
				}			
			}
			
			if (domExtranjero.getCalidad() != null
					&& domExtranjero.getCalidad().getTipoCalidad() != null) {
				Calidad calidad = domExtranjero.getCalidad();
				CalidadDTO calidadDto = new CalidadDTO();
				calidadDto.setCalidadId(calidad.getCalidadId());
				calidadDto.setValorIdCalidad(new ValorDTO(calidad.getTipoCalidad()
						.getValorId(), calidad.getTipoCalidad().getValor()));
				calidadDto.setDescripcionEstadoFisico(calidad
						.getDescripcionEstadoFisico());
				calidadDto.setCalidades(Calidades.getByValor(calidad.getTipoCalidad()
	                    .getValorId()));
				domExtranjeroDTO.setCalidadDTO(calidadDto);
			}
			
			return domExtranjeroDTO;
		}
		else {
			DomicilioDTO domicilioDTO = new DomicilioDTO();
			
			domicilioDTO.setElementoId(domicilio.getElementoId());
			domicilioDTO.setFolioElemento(domicilio.getFolioElemento());
			domicilioDTO.setFechaCreacionElemento(domicilio.getFechaCreacionElemento());
			
			domicilioDTO.setDescripcion(domicilio.getDescripcion());
			
			domicilioDTO.setAlias(domicilio.getAlias());
			domicilioDTO.setCalle(domicilio.getCalle());
			domicilioDTO.setNumeroExterior(domicilio.getNumeroExterior());
			domicilioDTO.setNumeroInterior(domicilio.getNumeroInterior());
			domicilioDTO.setCalle(domicilio.getCalle());
			domicilioDTO.setEntreCalle1(domicilio.getEntreCalle1());
			domicilioDTO.setEntreCalle2(domicilio.getEntreCalle2());
			domicilioDTO.setEdificio(domicilio.getEdificio());
			domicilioDTO.setReferencias(domicilio.getReferencias());
			if (domicilio.getAsentamiento()!=null) 
				domicilioDTO.setAsentamientoDTO(transformarAsentamiento(domicilio.getAsentamiento()));
			if (domicilio.getValorByTipoCalleVal()!=null)
				domicilioDTO.setValorCalleId(new ValorDTO(domicilio.getValorByTipoCalleVal().getValorId(), domicilio.getValorByTipoCalleVal().getValor()));
			if (domicilio.getMunicipio()!=null)
				domicilioDTO.setMunicipioDTO(new MunicipioDTO(domicilio.getMunicipio().getMunicipioId()));
			if (domicilio.getEntidadFederativa()!=null)
				domicilioDTO.setEntidadDTO(new EntidadFederativaDTO(domicilio.getEntidadFederativa().getEntidadFederativaId()));
			if (domicilio.getCiudad()!=null)
				domicilioDTO.setCiudadDTO(new CiudadDTO(domicilio.getCiudad().getCiudadId(),null,domicilio.getCiudad().getNombreCiudad(), transformarEntidadFederativa(domicilio.getEntidadFederativa())));
		
			if (domicilio.getCoordenadaGeograficas()!=null) {
				for (CoordenadaGeografica coorGeo : domicilio.getCoordenadaGeograficas()) {
					domicilioDTO.setLatitud(coorGeo.getLatitud());
					domicilioDTO.setLongitud(coorGeo.getLongitud());
				}			
			}
			
			if (domicilio.getCalidad() != null
					&& domicilio.getCalidad().getTipoCalidad() != null) {
				Calidad calidad = domicilio.getCalidad();
				CalidadDTO calidadDto = new CalidadDTO();
				calidadDto.setCalidadId(calidad.getCalidadId());
				calidadDto.setValorIdCalidad(new ValorDTO(calidad.getTipoCalidad()
						.getValorId(), calidad.getTipoCalidad().getValor()));
				calidadDto.setDescripcionEstadoFisico(calidad
						.getDescripcionEstadoFisico());
				calidadDto.setCalidades(Calidades.getByValor(calidad.getTipoCalidad()
	                    .getValorId()));
				domicilioDTO.setCalidadDTO(calidadDto);
			}
			
			return domicilioDTO;
		}				
	}
	
	
	public static Domicilio transformarDomicilio (DomicilioDTO domicilioDTO) {
		Domicilio domicilio = new Domicilio();
		
		if (domicilioDTO.getElementoId()!=null)
			domicilio.setElementoId(domicilioDTO.getElementoId());
		
		domicilio.setFolioElemento(domicilioDTO.getFolioElemento());
		domicilio.setFechaCreacionElemento(domicilioDTO.getFechaCreacionElemento()==null?new Date():  domicilioDTO.getFechaCreacionElemento());
		domicilio.setTipoElemento(new Valor(Elementos.LUGAR.getValorId()));
		
		domicilio.setDescripcion(domicilioDTO.getDescripcion());
		
		domicilio.setAlias(domicilioDTO.getAlias());
		domicilio.setCalle(domicilioDTO.getCalle());
		domicilio.setNumeroExterior(domicilioDTO.getNumeroExterior());
		domicilio.setNumeroInterior(domicilioDTO.getNumeroInterior());
		domicilio.setNumeroLote(domicilioDTO.getNumeroLote());
		domicilio.setCalle(domicilioDTO.getCalle());
		domicilio.setEntreCalle1(domicilioDTO.getEntreCalle1());
		domicilio.setEntreCalle2(domicilioDTO.getEntreCalle2());
		domicilio.setEdificio(domicilioDTO.getEdificio());	
		domicilio.setReferencias(domicilioDTO.getReferencias());
         
		if (domicilioDTO.getExpedienteDTO()!=null) 
			domicilio.setExpediente(new Expediente(domicilioDTO.getExpedienteDTO().getExpedienteId()));		
		if (domicilioDTO.getAsentamientoDTO()!=null && domicilioDTO.getAsentamientoDTO().getAsentamientoId()!=null) 
			domicilio.setAsentamiento(new Asentamiento(domicilioDTO.getAsentamientoDTO().getAsentamientoId()));
		if (domicilioDTO.getValorCalleId()!=null && domicilioDTO.getValorCalleId().getIdCampo()!=null)
			domicilio.setValorByTipoCalleVal(new Valor(domicilioDTO.getValorCalleId().getIdCampo(), domicilioDTO.getValorCalleId().getValor()));
		if (domicilioDTO.getEntidadDTO()!=null && domicilioDTO.getEntidadDTO().getEntidadFederativaId()!=null)
			domicilio.setEntidadFederativa(new EntidadFederativa(domicilioDTO.getEntidadDTO().getEntidadFederativaId()));
		if (domicilioDTO.getMunicipioDTO()!=null && domicilioDTO.getMunicipioDTO().getMunicipioId()!=null)
			domicilio.setMunicipio(new Municipio(domicilioDTO.getMunicipioDTO().getMunicipioId()));
		if (domicilioDTO.getCiudadDTO()!=null && domicilioDTO.getCiudadDTO().getCiudadId()!=null)
			domicilio.setCiudad(new Ciudad(domicilioDTO.getCiudadDTO().getCiudadId()));
			
		if (domicilioDTO.getLatitud() !=null  && domicilioDTO.getLongitud()!=null) {
			CoordenadaGeografica coordenadaGeografica = new CoordenadaGeografica();
			coordenadaGeografica.setLatitud(domicilioDTO.getLatitud());
			coordenadaGeografica.setLongitud(domicilioDTO.getLongitud());
			
			Set<CoordenadaGeografica> coordenadaGeograficas = new HashSet<CoordenadaGeografica>(0);
			coordenadaGeograficas.add(coordenadaGeografica);
			coordenadaGeografica.setLugar(domicilio);
			domicilio.setCoordenadaGeograficas(coordenadaGeograficas);
		}

		return domicilio;
	}
	
	
	/**
	 * 
	 * @param asentamiento
	 * @return
	 */
	public static AsentamientoDTO transformarAsentamiento (Asentamiento asentamiento) {
		AsentamientoDTO asentamientoDTO = new AsentamientoDTO();
		
		asentamientoDTO.setAsentamientoId(asentamiento.getAsentamientoId());
		asentamientoDTO.setCodigoPostal(asentamiento.getCodigoPostal());
		asentamientoDTO.setNombreAsentamiento(asentamiento.getNombreAsentamiento());		
		
		if (asentamiento.getMunicipio()!=null)
			asentamientoDTO.setMunicipioDTO(transformarMunicipio(asentamiento.getMunicipio()));
		if (asentamiento.getCiudad()!=null)
			asentamientoDTO.setCiudadDTO(transformarCiudad(asentamiento.getCiudad()));
		if (asentamiento.getCatTipoAsentamiento()!=null)
			asentamientoDTO.setTipoAsentamientoDTO(transformarTipoAsentamiento(asentamiento.getCatTipoAsentamiento()));
		return asentamientoDTO;
	}
	
	
	/**
	 * 
	 * @param asentamientoDTO
	 * @return
	 */
	public static Asentamiento transformarAsentamiento (AsentamientoDTO asentamientoDTO) {
		Asentamiento asentamiento = new Asentamiento();
		
		asentamiento.setAsentamientoId(asentamientoDTO.getAsentamientoId());
		asentamiento.setCodigoPostal(asentamientoDTO.getCodigoPostal());
		asentamiento.setNombreAsentamiento(asentamientoDTO.getNombreAsentamiento());		
		
		if (asentamientoDTO.getMunicipioDTO()!=null)
			asentamiento.setMunicipio(transformarMunicipio(asentamientoDTO.getMunicipioDTO()));
		if (asentamientoDTO.getCiudadDTO()!=null)
			asentamiento.setCiudad(transformarCiudad(asentamientoDTO.getCiudadDTO()));
		if(asentamientoDTO.getTipoAsentamientoDTO() != null)
			asentamiento.setCatTipoAsentamiento(transformarTipoAsentamiento(asentamientoDTO.getTipoAsentamientoDTO()));
		return asentamiento;
	}
	
	
	/**
	 * 
	 * @param municipio
	 * @return
	 */
	public static MunicipioDTO transformarMunicipio (Municipio municipio) {
		MunicipioDTO municipioDTO = new MunicipioDTO();
		
		municipioDTO.setMunicipioId(municipio.getMunicipioId());
		municipioDTO.setNombreMunicipio(municipio.getNombreMunicipio());
		
		if (municipio.getEntidadFederativa()!=null)
			municipioDTO.setEntidadFederativaDTO(transformarEntidad(municipio.getEntidadFederativa()));
		
		return municipioDTO;
	}
	
	
	/**
	 * 
	 * @param municipioDTO
	 * @return
	 */
	public static Municipio transformarMunicipio (MunicipioDTO municipioDTO) {
		Municipio municipio = new Municipio();
		
		municipio.setMunicipioId(municipioDTO.getMunicipioId());
		municipio.setNombreMunicipio(municipioDTO.getNombreMunicipio());
		
		if (municipioDTO.getEntidadFederativaDTO()!=null)
			municipio.setEntidadFederativa(transformarEntidad(municipioDTO.getEntidadFederativaDTO()));
		
		return municipio;
	}
	
	
	/**
	 * 
	 * @param ciudad
	 * @return
	 */
	public static CiudadDTO transformarCiudad (Ciudad ciudad) {
		CiudadDTO ciudadDTO = new CiudadDTO();
		
		ciudadDTO.setCiudadId(ciudad.getCiudadId());
		ciudadDTO.setAbreviacion(ciudad.getAbreviacion());
		ciudadDTO.setNombreCiudad(ciudad.getNombreCiudad());
		
		if (ciudad.getEntidadFederativa()!=null) 
			ciudadDTO.setEntidadFederativaDTO(transformarEntidad(ciudad.getEntidadFederativa()));
				
		return ciudadDTO;
	}
	
	
	/**
	 * 
	 * @param ciudadDTO
	 * @return
	 */
	public static Ciudad transformarCiudad (CiudadDTO ciudadDTO) {
		Ciudad ciudad = new Ciudad();
		
		ciudad.setCiudadId(ciudadDTO.getCiudadId());
		ciudad.setAbreviacion(ciudadDTO.getAbreviacion());
		ciudad.setNombreCiudad(ciudadDTO.getNombreCiudad());
		
		if (ciudadDTO.getEntidadFederativaDTO()!=null) 
			ciudad.setEntidadFederativa(transformarEntidad(ciudadDTO.getEntidadFederativaDTO()));
				
		return ciudad;
	}
	
	
	/**
	 * 
	 * @param entidad
	 * @return
	 */
	public static EntidadFederativaDTO transformarEntidad (EntidadFederativa entidad) {
		EntidadFederativaDTO entidadDTO = new EntidadFederativaDTO();
		
		entidadDTO.setEntidadFederativaId(entidad.getEntidadFederativaId());
		entidadDTO.setAbreviacion(entidad.getAbreviacion());
		entidadDTO.setCzonaGeografica(entidad.getZonaGeografica());
		entidadDTO.setNombreEntidad(entidad.getNombre());
		if (entidad.getPais()!=null)
			entidadDTO.setValorIdPais(new ValorDTO(entidad.getPais().getValorId(), entidad.getPais().getValor()));
		
		return entidadDTO;
	}
	
	
	public static EntidadFederativa transformarEntidad (EntidadFederativaDTO entidadDTO) {
		EntidadFederativa entidad = new EntidadFederativa();
		
		entidad.setEntidadFederativaId(entidadDTO.getEntidadFederativaId());
		entidad.setAbreviacion(entidadDTO.getAbreviacion());
		entidad.setZonaGeografica(entidadDTO.getCzonaGeografica());
		entidad.setNombre(entidadDTO.getNombreEntidad());
		if (entidadDTO.getValorIdPais()!=null)
			entidad.setPais(new Valor(entidadDTO.getValorIdPais().getIdCampo(), entidadDTO.getValorIdPais().getValor()));
		
		return entidad;
	}


	public static Domicilio transformarDomicilioUpdate(Domicilio domicilio,
			Domicilio domicilioMod) {
		if(domicilioMod == null || domicilio == null)
			return null;
		domicilio.setFechaCreacionElemento(domicilioMod.getFechaCreacionElemento());
		domicilio.setTipoElemento(domicilioMod.getTipoElemento());		
		domicilio.setDescripcion(domicilioMod.getDescripcion());		
		domicilio.setAlias(domicilioMod.getAlias());
		domicilio.setCalle(domicilioMod.getCalle());
		domicilio.setNumeroExterior(domicilioMod.getNumeroExterior());
		domicilio.setNumeroInterior(domicilioMod.getNumeroInterior());
		domicilio.setNumeroLote(domicilioMod.getNumeroLote());
		domicilio.setCalle(domicilioMod.getCalle());
		domicilio.setEntreCalle1(domicilioMod.getEntreCalle1());
		domicilio.setEntreCalle2(domicilioMod.getEntreCalle2());
		domicilio.setEdificio(domicilioMod.getEdificio());		     
//		domicilio.setExpediente(domicilioMod.getExpediente());
		domicilio.setReferencias(domicilioMod.getReferencias());
		domicilio.setAsentamiento(domicilioMod.getAsentamiento());		
		domicilio.setValorByTipoCalleVal(domicilioMod.getValorByTipoCalleVal());		
		domicilio.setEntidadFederativa(domicilioMod.getEntidadFederativa());				
		domicilio.setMunicipio(domicilioMod.getMunicipio());		
		domicilio.setCiudad(domicilioMod.getCiudad());
		
		//Contiene las coordenadas de BD  y hay nuevas coordenadas a ingresar
		if( !domicilio.getCoordenadaGeograficas().isEmpty() && !domicilioMod.getCoordenadaGeograficas().isEmpty()  ){
			CoordenadaGeografica coorNueva = (CoordenadaGeografica)domicilioMod.getCoordenadaGeograficas().toArray()[0];
			for (CoordenadaGeografica coordenadaGeografica : domicilio.getCoordenadaGeograficas()) {
				coordenadaGeografica.setLatitud(coorNueva.getLatitud());
				coordenadaGeografica.setLongitud(coorNueva.getLongitud());
			}
		}else{ //Son nuevas coordenadas
			domicilio.setCoordenadaGeograficas(domicilioMod.getCoordenadaGeograficas());	
		}		
		
		return domicilio;
	}


	public static DomicilioExtranjero transformarDomExtranjero(
			DomicilioExtranjeroDTO domicilioExtDTO) {
		DomicilioExtranjero domicilioExtranjero = new DomicilioExtranjero();
		
		domicilioExtranjero.setFolioElemento(domicilioExtDTO.getFolioElemento());
		domicilioExtranjero.setFechaCreacionElemento(domicilioExtDTO.getFechaCreacionElemento());
		domicilioExtranjero.setTipoElemento(new Valor(Elementos.LUGAR.getValorId()));
		
		domicilioExtranjero.setDescripcion(domicilioExtDTO.getDescripcion());
		
		domicilioExtranjero.setAlias(domicilioExtDTO.getAlias());
		domicilioExtranjero.setCalle(domicilioExtDTO.getCalle());
		domicilioExtranjero.setNumeroExterior(domicilioExtDTO.getNumeroExterior());
		domicilioExtranjero.setNumeroInterior(domicilioExtDTO.getNumeroInterior());
		domicilioExtranjero.setNumeroLote(domicilioExtDTO.getNumeroLote());
		domicilioExtranjero.setCalle(domicilioExtDTO.getCalle());
		domicilioExtranjero.setReferencias(domicilioExtDTO.getReferencias());
		domicilioExtranjero.setEntreCalle1(domicilioExtDTO.getEntreCalle1());
		domicilioExtranjero.setEntreCalle2(domicilioExtDTO.getEntreCalle2());
		domicilioExtranjero.setEdificio(domicilioExtDTO.getEdificio());
		
		domicilioExtranjero.setCodigoPostal(domicilioExtDTO.getCodigoPostal());
		domicilioExtranjero.setAsentamientoExt(domicilioExtDTO.getAsentamientoExt());
		domicilioExtranjero.setMunicipioStr(domicilioExtDTO.getMunicipio());
		domicilioExtranjero.setCiudadStr(domicilioExtDTO.getCiudad());
		domicilioExtranjero.setEstadoStr(domicilioExtDTO.getEstado());
		domicilioExtranjero.setPais(domicilioExtDTO.getPais());
         
		if (domicilioExtDTO.getExpedienteDTO()!=null) 
			domicilioExtranjero.setExpediente(new Expediente(domicilioExtDTO.getExpedienteDTO().getExpedienteId()));
		if (domicilioExtDTO.getPaisValor()!=null)
			domicilioExtranjero.setValor(new Valor(domicilioExtDTO.getPaisValor().getIdCampo()));

		if (domicilioExtDTO.getLatitud() !=null  && domicilioExtDTO.getLongitud()!=null) {
			CoordenadaGeografica coordenadaGeografica = new CoordenadaGeografica();
			coordenadaGeografica.setLatitud(domicilioExtDTO.getLatitud());
			coordenadaGeografica.setLongitud(domicilioExtDTO.getLongitud());
			
			Set<CoordenadaGeografica> coordenadaGeograficas = new HashSet<CoordenadaGeografica>(0);
			coordenadaGeograficas.add(coordenadaGeografica);
			coordenadaGeografica.setLugar(domicilioExtranjero);
			domicilioExtranjero.setCoordenadaGeograficas(coordenadaGeograficas);
		}

		return domicilioExtranjero;
	}
	
	/**
	 * 
	 * @param aoTipoAsentamiento
	 * @return
	 */
	public static TipoAsentamientoDTO transformarTipoAsentamiento(CatTipoAsentamiento aoTipoAsentamiento) {
		TipoAsentamientoDTO loTipoAsentamientoDTO = null;
		if(aoTipoAsentamiento != null){
			loTipoAsentamientoDTO = new TipoAsentamientoDTO(aoTipoAsentamiento.getCatTipoAsentamientoId(),
					aoTipoAsentamiento.getNombre());	
		}			
		return loTipoAsentamientoDTO;
	}
	
	
	/**
	 * 
	 * @param aoTipoAsentamientoDTO
	 * @return
	 */
	public static CatTipoAsentamiento transformarTipoAsentamiento(TipoAsentamientoDTO aoTipoAsentamientoDTO) {
		CatTipoAsentamiento loTipoAsentamiento = new CatTipoAsentamiento();
		if(aoTipoAsentamientoDTO != null && aoTipoAsentamientoDTO.getTipoAsentamientoId() != null)
			loTipoAsentamiento.setCatTipoAsentamientoId(aoTipoAsentamientoDTO.getTipoAsentamientoId());
		if(aoTipoAsentamientoDTO != null && aoTipoAsentamientoDTO.getTipoAsentamiento() != null)
			loTipoAsentamiento.setNombre(aoTipoAsentamientoDTO.getTipoAsentamiento());
		return loTipoAsentamiento;
	}
	
	public static EntidadFederativaDTO transformarEntidadFederativa(EntidadFederativa entidadFederativa) {
		EntidadFederativaDTO entidadFederativaDTO = new EntidadFederativaDTO();
		ValorDTO paisDTO = new ValorDTO();
		
		paisDTO.setIdCampo(entidadFederativa.getPais().getValorId());
		
		entidadFederativaDTO.setEntidadFederativaId(entidadFederativa.getEntidadFederativaId());
		entidadFederativaDTO.setNombreEntidad(entidadFederativa.getNombre());
		entidadFederativaDTO.setValorIdPais(paisDTO);
				
		return entidadFederativaDTO;
	}
	
	
}
