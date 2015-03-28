/**
 * Nombre del Programa : ConsultarIndiceEstructuradoServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27/07/2011
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
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.IndiceEstructuradoDAO;
import mx.gob.segob.nsjp.dto.documento.IndiceEstructuradoDTO;
import mx.gob.segob.nsjp.model.IndiceEstructurado;
import mx.gob.segob.nsjp.service.documento.ConsultarIndicesEstructuradosService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.IndiceEstructuradoTransformer;
import mx.gob.segob.nsjp.service.forma.impl.ConsultarFormaPlantillaServiceImpl;

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
public class ConsultarIndicesEstructuradosServiceImpl implements ConsultarIndicesEstructuradosService {

	public final static Logger logger = Logger
			.getLogger(ConsultarFormaPlantillaServiceImpl.class);

	@Autowired
	private IndiceEstructuradoDAO indiceEstructuradoDAO;

	@Override
	public List<IndiceEstructuradoDTO> consultarIndicesEstructuradosXTipoOficio(Long tipoOficioId)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LOS INDICES ESTRUCTURADOS ****/");

		/* Operación */
		List<IndiceEstructurado> loObjetos = indiceEstructuradoDAO.consultarIndicesEstructuradosPorTipoOficio(tipoOficioId);

		/* Transformación */
		List<IndiceEstructuradoDTO> objsDTO = new ArrayList<IndiceEstructuradoDTO>();
		for (IndiceEstructurado obj : loObjetos) {
			objsDTO.add(IndiceEstructuradoTransformer.transformarIndiceEstructurado(obj));
		}
		logger.info("IndiceEstructurados.size(:-)" + objsDTO.size());
		return objsDTO;
	}

}
