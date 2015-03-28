/**
* Nombre del Programa : RegistrarDelitoDenunciaServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para registrar los delitos de la denuncia
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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.service.delito.RegistrarDelitoDenunciaService;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;

/**
 * Implementacion del servicio para registrar los delitos de la denuncia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class RegistrarDelitoDenunciaServiceImpl implements
		RegistrarDelitoDenunciaService {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(RegistrarDelitoDenunciaServiceImpl.class);
	
	@Autowired
	private DelitoDAO delitoDAO;
	
	@Override
	public List<DelitoDTO> registrarDelitoDenuncia(List<DelitoDTO> delitosDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGITRAR LOS DELITOS DE LA DENUNCIA ****/");
		
		List<DelitoDTO> retornoDelitos = new ArrayList<DelitoDTO>(); 
		for (DelitoDTO delitoDTO : delitosDTO) {
			Delito delito = DelitoTransfromer.transformarDelito(delitoDTO);
			delito.setEsProbable(true);
			Long idDelito = delitoDAO.create(delito);
			retornoDelitos.add(new DelitoDTO(idDelito));
		}
		
		return retornoDelitos;
	}

}
