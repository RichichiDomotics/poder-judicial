/**
* Nombre del Programa : IngresarLugarServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar el ingreso de un Lugar
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
package mx.gob.segob.nsjp.service.lugar.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.lugar.AreaGeograficaDAO;
import mx.gob.segob.nsjp.dao.lugar.CoordenadaGeograficaDAO;
import mx.gob.segob.nsjp.dao.lugar.EspacioAereoDAO;
import mx.gob.segob.nsjp.dao.lugar.EspacioMaritimoDAO;
import mx.gob.segob.nsjp.dao.lugar.PuntoCarreteroDAO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.lugar.AreaGeograficaDTO;
import mx.gob.segob.nsjp.dto.lugar.EspacioAereoDTO;
import mx.gob.segob.nsjp.dto.lugar.EspacioMaritimoDTO;
import mx.gob.segob.nsjp.dto.lugar.InmediacionDTO;
import mx.gob.segob.nsjp.dto.lugar.PuntoCarreteroDTO;
import mx.gob.segob.nsjp.model.AreaGeografica;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.CoordenadaGeografica;
import mx.gob.segob.nsjp.model.EspacioAereo;
import mx.gob.segob.nsjp.model.EspacioMaritimo;
import mx.gob.segob.nsjp.model.Inmediacion;
import mx.gob.segob.nsjp.model.Lugar;
import mx.gob.segob.nsjp.model.PuntoCarretero;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.domicilio.IngresarDomicilioService;
import mx.gob.segob.nsjp.service.lugar.IngresarLugarService;
import mx.gob.segob.nsjp.service.lugar.impl.transform.LugarTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para realizar el ingreso de un Lugar.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class IngresarLugarServiceImpl implements IngresarLugarService {
	
	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(IngresarLugarServiceImpl.class);
	
	@Autowired
	private AreaGeograficaDAO areaGeograficaDAO;
	@Autowired
	private EspacioAereoDAO espacioAereoDAO;
	@Autowired 
	private EspacioMaritimoDAO espacioMaritimoDAO;
	@Autowired
	private PuntoCarreteroDAO puntoCarreteroDAO;
	@Autowired
	private CoordenadaGeograficaDAO coordenadaGeograficaDAO;
	
	@Autowired
	private IngresarDomicilioService ingresarDomicilioService;
	
	@Override
	public LugarDTO ingresarLugar(LugarDTO lugarDTO)
			throws NSJPNegocioException {
		
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA INGRESAR UN LUGAR ****/");
		
		LugarDTO lugarRetDTO = new LugarDTO();
		Long nuevoLugar = new Long(0);
		
		if (lugarDTO instanceof DomicilioDTO) {
			nuevoLugar = ingresarDomicilioService.ingresarDomicilio((DomicilioDTO)lugarDTO);
		} else if (lugarDTO instanceof AreaGeograficaDTO) {
			nuevoLugar = ingresarAreaGeografica((AreaGeograficaDTO)lugarDTO);
		} else if (lugarDTO instanceof EspacioAereoDTO) {
			nuevoLugar = ingresarEspacioAereo((EspacioAereoDTO)lugarDTO);
		} else if (lugarDTO instanceof EspacioMaritimoDTO) {
			nuevoLugar = ingresarEspacioMaritimo((EspacioMaritimoDTO)lugarDTO);
		} else if (lugarDTO instanceof PuntoCarreteroDTO) {
			nuevoLugar = ingresarPuntoCarretero((PuntoCarreteroDTO)lugarDTO);
		}		
				
		if (lugarDTO.getLatitud()!=null || lugarDTO.getLongitud()!=null) {
			CoordenadaGeografica coordenadas = new CoordenadaGeografica();
			coordenadas.setLatitud(lugarDTO.getLatitud());
			coordenadas.setLongitud(lugarDTO.getLongitud());
			coordenadas.setLugar(new Lugar(nuevoLugar));
			coordenadaGeograficaDAO.create(coordenadas);
		}
		
		lugarRetDTO.setElementoId(nuevoLugar);		
		return lugarRetDTO;
	}	

	public Long ingresarAreaGeografica (AreaGeograficaDTO areaGeograficaDTO) throws NSJPNegocioException {
		
		if (areaGeograficaDTO.getCalidadDTO()==null
				|| areaGeograficaDTO.getExpedienteDTO()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA INGRESAR UN AREA GEOGRAFICA ****/");
		
		AreaGeografica areaGeografica = LugarTransformer.transformarAreaGeografica(areaGeograficaDTO);		
		areaGeografica.setCalidad(obtenerCalidadLugar(areaGeograficaDTO.getCalidadDTO()));
		
		if (areaGeograficaDTO.getInmediaciones()!=null && areaGeograficaDTO.getInmediaciones().size()>0) 
			areaGeografica.setInmediacions(ingresarInmediaciones(areaGeograficaDTO.getInmediaciones(), areaGeografica));		
		
		return areaGeograficaDAO.create(areaGeografica);
	}
		
	public Long ingresarEspacioAereo (EspacioAereoDTO espacioAereoDTO) throws NSJPNegocioException {
		
		if (espacioAereoDTO.getCalidadDTO()==null
				|| espacioAereoDTO.getExpedienteDTO()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA INGRESAR UN ESPACIO AEREO ****/");
		
		EspacioAereo espacioAereo = LugarTransformer.transformarEspacioAereo(espacioAereoDTO);			
		espacioAereo.setCalidad(obtenerCalidadLugar(espacioAereoDTO.getCalidadDTO()));
		
		if (espacioAereoDTO.getInmediaciones()!=null && espacioAereoDTO.getInmediaciones().size()>0) 
			espacioAereo.setInmediacions(ingresarInmediaciones(espacioAereoDTO.getInmediaciones(), espacioAereo));		
						
		return espacioAereoDAO.create(espacioAereo);
	}
	
	private Long ingresarEspacioMaritimo(EspacioMaritimoDTO espacioMaritimoDTO) throws NSJPNegocioException {
		if (espacioMaritimoDTO.getCalidadDTO()==null
				|| espacioMaritimoDTO.getExpedienteDTO()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA INGRESAR UN ESPACIO MARITIMO ****/" + espacioMaritimoDTO.getCalidadDTO());
		
		EspacioMaritimo espacioMaritimo = LugarTransformer.transformarEspacioMaritimo(espacioMaritimoDTO);
		espacioMaritimo.setCalidad(obtenerCalidadLugar(espacioMaritimoDTO.getCalidadDTO()));
		
		if (espacioMaritimoDTO.getInmediaciones()!=null && espacioMaritimoDTO.getInmediaciones().size()>0)
			espacioMaritimo.setInmediacions(ingresarInmediaciones(espacioMaritimoDTO.getInmediaciones(), espacioMaritimo));
		
		return espacioMaritimoDAO.create(espacioMaritimo);
	}
	
	private Long ingresarPuntoCarretero(PuntoCarreteroDTO puntoCarreteroDTO) throws NSJPNegocioException {
		if (puntoCarreteroDTO.getCalidadDTO()==null
				|| puntoCarreteroDTO.getExpedienteDTO()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA INGRESAR UN PUNTO CARRETERO ****/");
		
		PuntoCarretero puntoCarretero = LugarTransformer.transformarPuntoCarretero(puntoCarreteroDTO);
		puntoCarretero.setCalidad(obtenerCalidadLugar(puntoCarreteroDTO.getCalidadDTO()));
		
		if (puntoCarreteroDTO.getInmediaciones()!=null && puntoCarreteroDTO.getInmediaciones().size()>0)
			puntoCarretero.setInmediacions(ingresarInmediaciones(puntoCarreteroDTO.getInmediaciones(), puntoCarretero));
		
		return puntoCarreteroDAO.create(puntoCarretero);
	}	
	
	/**
	 * Contruye la coleccion de Inmediaciones de un lugar para ser ingresado
	 * @param listInmsDTO
	 * @param lugar
	 * @return Coleccion de inmediaciones
	 */
	public Set<Inmediacion> ingresarInmediaciones (List<InmediacionDTO> listInmsDTO, Lugar lugar) {
		Set<Inmediacion> inmediaciones = new HashSet<Inmediacion>(0);
		for (InmediacionDTO inmediacionDTO : listInmsDTO) {
			Inmediacion inmediacion = LugarTransformer.transformarInmediacion(inmediacionDTO);
			inmediacion.setLugar(lugar);
			inmediaciones.add(inmediacion);					
		}	
		return inmediaciones;
	}
	
	/**
	 * Obtiene la calidad del lugar para ser ingresada
	 * @param calidadDTO
	 * @return
	 */
	public Calidad obtenerCalidadLugar (CalidadDTO calidadDTO) {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA OBTENER LA CALIDAD DEL LUGAR ****/");
		
		Calidad calidad = new Calidad();
		calidad.setDescripcionEstadoFisico(calidadDTO.getDescripcionEstadoFisico());
		calidad.setTipoCalidad(new Valor(calidadDTO.getCalidades().getValorId()));
		return calidad;
	}

}
