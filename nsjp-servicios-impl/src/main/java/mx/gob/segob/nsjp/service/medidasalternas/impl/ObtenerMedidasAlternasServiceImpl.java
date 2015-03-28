/**
* Nombre del Programa : ObtenerMedidasAlternasServiceImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 03 Nov 2011
* Marca de cambio        : N/A
* Descripcion General    : 
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
package mx.gob.segob.nsjp.service.medidasalternas.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.model.MedidaAlterna;
import mx.gob.segob.nsjp.service.medidasalternas.ObtenerMedidasAlternasService;
import mx.gob.segob.nsjp.service.medidasalternas.impl.transform.MedidaAlternaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @version 1.0
 * @author rgama
 *
 */

@Service
@Transactional
public class ObtenerMedidasAlternasServiceImpl implements
 ObtenerMedidasAlternasService {

	private final static Logger logger = Logger.getLogger(ObtenerMedidasAlternasServiceImpl.class);
	
	@Autowired
	private mx.gob.segob.nsjp.dao.medida.MedidaAlternaDAO medidaAlternaDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MedidaAlternaDTO> obtenerMedidasAlternasIncumplidasDelDiaAnterior()
			throws NSJPNegocioException {
		
				
		if (logger.isDebugEnabled())
			logger.debug(":::obtenerMedidasAlternasIncumplidasDelDiaAnterior:::");

		List<MedidaAlternaDTO> lstMedidasBDDTO = new ArrayList<MedidaAlternaDTO>();
		List<MedidaAlterna> lstMedidas = medidaAlternaDAO.obtenerMedidasAlternasIncumplidasDelDiaAnterior();


		//Se eliminan duplicados de la lista
		Set<MedidaAlterna> listaSinDuplicados = new HashSet<MedidaAlterna>();
		for (MedidaAlterna medidaCautelarBD : lstMedidas) {
			listaSinDuplicados.add(medidaCautelarBD);
		}
		//Se transforman las medidas alternas
		for(MedidaAlterna loMedidaAlternaBD : listaSinDuplicados) {
			lstMedidasBDDTO.add(MedidaAlternaTransformer.TransformaMedidaAlterna(loMedidaAlternaBD));
		}
		return lstMedidasBDDTO;
	}

}
