/**
* Nombre del Programa : EnviarMedidaAlternaServiceImpl.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.medida.MedidaAlternaDAO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.model.MedidaAlterna;
import mx.gob.segob.nsjp.service.infra.SSPClienteService;
import mx.gob.segob.nsjp.service.medidasalternas.EnviarMedidaAlternaService;
import mx.gob.segob.nsjp.service.medidasalternas.impl.transform.MedidaAlternaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class EnviarMedidaAlternaServiceImpl implements
		EnviarMedidaAlternaService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(EnviarMedidaAlternaServiceImpl.class);
	
	@Autowired
	private SSPClienteService sspClienteService;
	@Autowired
	private MedidaAlternaDAO medidaAlternaDAO;
	
	@Override
	public MedidaAlternaDTO enviarMedidaAlternaASSP(Long idMedidaAlterna)
			throws NSJPNegocioException {
		
		MedidaAlterna medidaAlterna = medidaAlternaDAO.read(idMedidaAlterna);
		
		logger.debug("-----------------------------------");
		logger.debug("/**** Inv Nombre "+medidaAlterna.getInvolucrado().getNombreDemograficos());
		logger.debug("/**** Archivo Dig "+medidaAlterna.getArchivoDigital());
		logger.debug("/**** Num Carpeta "+medidaAlterna.getNumeroCarpetaEjecucion());
		logger.debug("/**** Juez Ord "+medidaAlterna.getJuezOrdena());		
		logger.debug("/**** Num caso "+medidaAlterna.getNumeroCaso());
		logger.debug("/**** Num causa "+medidaAlterna.getNumeroCausa());
		logger.debug("/**** Periodicidad "+medidaAlterna.getValorPeriodicidad());
		logger.debug("/**** Fecha creacion "+medidaAlterna.getFechaCreacion());
		logger.debug("/**** Fecha inicio "+medidaAlterna.getFechaInicio());
		logger.debug("/**** Fecha fin "+medidaAlterna.getFechaFin());
		logger.debug("-----------------------------------");
		
		sspClienteService.enviarMedidaAlterna(medidaAlterna);
		return MedidaAlternaTransformer.TransformaMedidaAlterna(medidaAlterna);
	}

}
