/**
* Nombre del Programa : IngresarDomicilioServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar la insercion de Domicilio
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
package mx.gob.segob.nsjp.service.domicilio.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioExtranjeroDAO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioExtranjeroDTO;
import mx.gob.segob.nsjp.dto.lugar.InmediacionDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.DomicilioExtranjero;
import mx.gob.segob.nsjp.model.Inmediacion;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.domicilio.IngresarDomicilioService;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.lugar.impl.transform.LugarTransformer;

/**
 * Implementacion del servicio para realizar la insercion de Domicilio.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
public class IngresarDomicilioServiceImpl implements IngresarDomicilioService {

	private final static Logger logger = Logger.getLogger(IngresarDomicilioServiceImpl.class);
	
	
	@Autowired
	private DomicilioDAO domicilioDao;
	@Autowired
	private DomicilioExtranjeroDAO domicilioExtranjeroDAO;
	
	@Override
	public Long ingresarDomicilio(DomicilioDTO domicilioDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled()) {
			logger.debug("/**** SERVICIO PARA INGRESAR DOMICILIO ****/");
			logger.debug("domicilioDTO.getExpedienteDTO() :: "+domicilioDTO);
		}
		Long idDomicilio = 0L;
		Calidad calidad = new Calidad();
		
		if (domicilioDTO.getCalidadDTO()!=null)
        {	
			calidad.setDescripcionEstadoFisico(domicilioDTO.getCalidadDTO().getDescripcionEstadoFisico());            	         
			if (domicilioDTO.getCalidadDTO().getCalidades()!=null)
            {	
				calidad.setTipoCalidad(new Valor(new Long(domicilioDTO.getCalidadDTO().getCalidades().getValorId())));                       	
				
            }	
        }	
		
		DomicilioExtranjero domExtranjero = null;
		Domicilio domicilio  = null;

		if (domicilioDTO instanceof DomicilioExtranjeroDTO) { 
			domExtranjero = DomicilioTransformer.transformarDomExtranjero((DomicilioExtranjeroDTO)domicilioDTO);
			domExtranjero.setCalidad(calidad);
		} else {
			domicilio = DomicilioTransformer.transformarDomicilio(domicilioDTO);			
			domicilio.setCalidad(calidad);			
		}
				
		if (domicilioDTO.getInmediaciones()!=null && domicilioDTO.getInmediaciones().size()>0) {
			Set<Inmediacion> inmediaciones = new HashSet<Inmediacion>(0);
			for (InmediacionDTO inmediacionDTO : domicilioDTO.getInmediaciones()) {
				Inmediacion inmediacion = LugarTransformer.transformarInmediacion(inmediacionDTO);
				if (domExtranjero!=null)
					inmediacion.setLugar(domExtranjero);
				else 
					inmediacion.setLugar(domicilio);
				inmediaciones.add(inmediacion);					
			}					
			if (domExtranjero!=null)
				domExtranjero.setInmediacions(inmediaciones);
			else 
				domicilio.setInmediacions(inmediaciones);
		}
													
		if (domExtranjero!=null)
			idDomicilio = domicilioExtranjeroDAO.create(domExtranjero);
		else 
			idDomicilio = domicilioDao.create(domicilio);
				
		if (logger.isDebugEnabled())
			logger.debug("/**** SE  GENERO EL DOMICILIO CON ID " + idDomicilio + " ****/");
		
		return idDomicilio;
	}

	
	
}
