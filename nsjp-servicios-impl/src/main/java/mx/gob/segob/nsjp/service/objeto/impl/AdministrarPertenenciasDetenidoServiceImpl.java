/**
 * Nombre del Programa  : AdministrarPertenenciasDetenidoServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 15 Jul 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Permite realizar consultas, inserciones y eliminaciones de objetos que pertenecen a un detenido.
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.objeto.impl;

import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.relacion.TipoRelacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.dao.objeto.ObjetoDAO;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.CatRelacion;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.AdministrarPertenenciasDetenidoService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetoTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Permite realizar consultas, inserciones y eliminaciones de objetos que pertenecen a un detenido..
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
@Service
@Transactional
public class AdministrarPertenenciasDetenidoServiceImpl implements
		AdministrarPertenenciasDetenidoService {

	@Autowired
	private ObjetoDAO objetoDAO;
	@Autowired
	private RelacionDAO relacionDAO;
	@Autowired
	private CalidadDAO calidadDAO;
	
	public List<ObjetoDTO> consultarInventarioPertenenciasDetenido(
			InvolucradoDTO detenido) throws NSJPNegocioException {
		
		if( detenido == null || detenido.getElementoId() == null || detenido.getElementoId()<0 )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Objeto> objs = objetoDAO.consultarInventarioPertenenciasDetenido(detenido.getElementoId());
		List<ObjetoDTO> objetos = new LinkedList<ObjetoDTO>();
		for(Objeto objeto : objs){
			objetos.add(ObjetoTransformer.transformarObjeto(objeto));
		}
		return objetos;
	}

	public Long registrarPertenenciaDetenido(ObjetoDTO objeto,
			InvolucradoDTO detenido) throws NSJPNegocioException {
		
		if( detenido == null || detenido.getElementoId() == null || detenido.getElementoId()<0 ||
				objeto.getExpedienteDTO()== null || objeto.getExpedienteDTO().getExpedienteId()== null || 
				objeto.getExpedienteDTO().getExpedienteId() < 0 )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Objeto object = ObjetoTransformer.transformarObjeto(objeto);
		
		CalidadDTO calidadDTO = new CalidadDTO();
		Calidad cal = new Calidad();
		calidadDTO.setCalidades(Calidades.PERTENENCIA);
        if (calidadDTO != null && calidadDTO.getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(calidadDTO.getDescripcionEstadoFisico());
        }
        if (calidadDTO != null && calidadDTO.getCalidades() != null) {
            cal.setTipoCalidad(new Valor(calidadDTO.getCalidades().getValorId()));
        }
        Long idCalidad = this.calidadDAO.create(cal);
        cal.setCalidadId(idCalidad);
        object.setCalidad(cal);
        
		Long id = objetoDAO.create(object);
		object.setElementoId(id);
        
		Relacion relacion = new Relacion();
		CatRelacion catRelacion = new CatRelacion();
		catRelacion.setCatRelacionId((long)Relaciones.PROPIETARIA.ordinal());
		relacion.setCatRelacion(catRelacion);
		relacion.setElementoByComplementoId(object);
		Involucrado inv = new Involucrado();
		inv.setElementoId(detenido.getElementoId());
		relacion.setElementoBySujetoId(inv);
		relacion.setTipoRelacion(TipoRelacion.EXPLICITA.getValorId().shortValue());// FIXME VERIFICAR QUE ESTO SEA CORRECTO
		relacionDAO.create(relacion);
		return id;
	}

	public void eliminarPertenenciaDeteneido(ObjetoDTO objetoDTO,
			InvolucradoDTO detenido) throws NSJPNegocioException {

		if( detenido == null || detenido.getElementoId() == null || detenido.getElementoId()<0 ||
				objetoDTO == null || objetoDTO.getElementoId() == null || objetoDTO.getElementoId()<0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Objeto object = objetoDAO.read(objetoDTO.getElementoId());
		//Consultar las relaciones del objeto asociado al involucrado.
		//boolean existe = relacionDAO.existeRelacion(new Long(Relaciones.PROPIETARIA.ordinal()), detenido.getElementoId(), objeto.getElementoId());
		List<Relacion> relaciones = relacionDAO.obtenerRelacionSimple(object.getElementoId(),new Long(Relaciones.PROPIETARIA.ordinal()));
		for (Relacion relacion : relaciones)
				relacionDAO.delete(relacion);
		objetoDAO.delete(object);
	}

}
