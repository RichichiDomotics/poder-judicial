/**
* Nombre del Programa : EliminarOrganizacionServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para elimianar una organizacion
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
package mx.gob.segob.nsjp.service.organizacion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.organizacion.OrganizacionDAO;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Organizacion;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.service.involucrado.EliminarInvolucradoService;
import mx.gob.segob.nsjp.service.organizacion.EliminarOrganizacionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para elimianar una organizacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class EliminarOrganizacionServiceImpl implements
		EliminarOrganizacionService {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(EliminarOrganizacionServiceImpl.class);
	
	@Autowired
	private RelacionDAO relacionDAO;
	@Autowired
	private OrganizacionDAO organizacionDAO;
	@Autowired
	private DomicilioDAO domicilioDAO;
	@Autowired
	private EliminarInvolucradoService eliminarInvolucradoService;
	
	@Override
	public void eliminarOrganizacion(OrganizacionDTO organizacionDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ELIMINAR UNA ORGANIZACION ****/");
		
		if (!(organizacionDTO!=null && organizacionDTO.getElementoId()>0))
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		//Verificar y eliminar relaciones de la organizacion
		//DOMICILIO
		//List<Relacion> listRelaciones = relacionDAO.consultarRelacionByElemento(organizacionDTO.getElementoId());
		List<Relacion> listRelaciones = relacionDAO.obtenerRelacionSimple(organizacionDTO.getElementoId(), new Long(Relaciones.UBICACION.ordinal()));
		if (listRelaciones!= null && ! listRelaciones.isEmpty()) {
			Domicilio domicilio =  domicilioDAO.read(listRelaciones.get(0).getElementoByComplementoId().getElementoId());
			domicilioDAO.delete(domicilio);
			//No es necesario Eliminar la relacion se elimina al eliminar la entidad //relacionDAO.delete(relacion);
		}
		
		//Eliminar al Representante Legal y sus relaciones
		List<Relacion> lRelaciones = relacionDAO.obtenerRelacionSimple(organizacionDTO.getElementoId(), new Long(Relaciones.REPRESENTANTE_LEGAL.ordinal()));
		if (lRelaciones!= null && ! lRelaciones.isEmpty()) {
			InvolucradoDTO representanteLegal = new InvolucradoDTO(lRelaciones.get(0).getElementoByComplementoId().getElementoId());
			eliminarInvolucradoService.eliminarInvolucrado(representanteLegal); 
			//No es necesario Eliminar la relacion se elimina al eliminar la entidad //relacionDAO.delete(relacion);
		}
		//NO APLICA Contacto para la organización
		//lRelaciones = relacionDAO.obtenerRelacionSimple(organizacionDTO.getElementoId(), new Long(Relaciones.CONTACTO.ordinal()));
		//Eliminar la organizacion
		Organizacion organizacion = organizacionDAO.read(organizacionDTO.getElementoId());
		organizacionDAO.delete(organizacion);
		//No es necesario Eliminar la relacion se elimina al eliminar la entidad 
	}
}
