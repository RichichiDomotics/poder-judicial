/**
 * Nombre del Programa : ConsultarCausasPorIdCasoServiceImpl.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 29/07/2011
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
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.service.expediente.ConsultarCausasPorIdCasoService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
@Service
@Transactional
public class ConsultarCausasPorIdCasoServiceImpl implements
	ConsultarCausasPorIdCasoService {

	@Autowired
	ExpedienteDAO expedienteDAO;

	public final static Logger logger = Logger
			.getLogger(ConsultarCausasPorIdCasoServiceImpl.class);

	public List<ExpedienteDTO> consultarCausasPorIdCasoService(Long idCaso)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR CAUSAS DE UN EXPEDIENTE ****/");

		if (idCaso == null || idCaso <= 0) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}

		List<Expediente> loExpedientes = expedienteDAO
				.consultarCausasPorIdCaso(idCaso);
		logger.info("Expedientes recuperados : " + loExpedientes.size());

		List<ExpedienteDTO> listRetorno = new ArrayList<ExpedienteDTO>();
		for (Expediente loExp : loExpedientes) {
			listRetorno.add(ExpedienteTransformer
					.transformarExpedienteBasico(loExp));
		}
		return listRetorno;
	}
}
