/**
* Nombre del Programa : ModificarDomicilioServicioImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para modificar la informacion de un domicilio
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.domicilio.ModificarDomicilioServicio;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;

/**
 * Implementacion del servicio para modificar la informacion de un domicilio.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ModificarDomicilioServicioImpl implements
		ModificarDomicilioServicio {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(ModificarDomicilioServicioImpl.class);
	
	@Autowired
	private DomicilioDAO domicilioDAO;
	
	@Override
	public void modificarDomicilio(DomicilioDTO domicilioDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA MODIFICAR DOMICILIO ****/ " + domicilioDTO);
		logger.info("DOMICILIODTO*:"+domicilioDTO);
		
		Domicilio domicilio = domicilioDAO.read(domicilioDTO.getElementoId());
		Domicilio domicilioMod = DomicilioTransformer.transformarDomicilio(domicilioDTO);
		
		domicilio = DomicilioTransformer.transformarDomicilioUpdate(domicilio, domicilioMod);
		logger.info("DOMICILIODTO - TipoElemento:"+domicilio.getTipoElemento());
		logger.info("DOMICILIODTO - ValorByTipoCalleVal:"+domicilio.getValorByTipoCalleVal());
		domicilio.setTipoElemento(new Valor(Elementos.LUGAR.getValorId()));
		
		
		domicilioDAO.update(domicilio);
	}

}
