/**
* Nombre del Programa : ModificarDelitoServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para actualizar la informacion del Delito
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
package mx.gob.segob.nsjp.service.delito.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.service.delito.ModificarDelitoService;

/**
 * Implementacion del servicio para actualizar la informacion del Delito.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ModificarDelitoServiceImpl implements ModificarDelitoService {

	/**
	 * 	
	 */
	public final static Logger logger = Logger.getLogger(ModificarDelitoServiceImpl.class);
	
	@Autowired
	private DelitoDAO delitoDAO;
	
	@Override
	public void modificarDelito(List<DelitoDTO> delitosDTO)
			throws NSJPNegocioException {
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ACTUALIZAR LA INFORMACION DEL DELITO ****/");
		
		for (DelitoDTO delitoDTO : delitosDTO) {
			if (delitoDTO.getDelitoId()==null)
				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			
			Delito delito = delitoDAO.read(delitoDTO.getDelitoId());
			delito.setEsProbable(delitoDTO.getEsProbable());
			delito.setEsPrincipal(delitoDTO.getEsPrincipal());
			delitoDAO.update(delito);
		}
	}

}
