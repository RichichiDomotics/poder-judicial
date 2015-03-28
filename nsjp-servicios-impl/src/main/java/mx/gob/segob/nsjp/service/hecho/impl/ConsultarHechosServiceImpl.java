/**
 * Nombre del Programa : ConsultarHechosServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 11/07/2011
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
package mx.gob.segob.nsjp.service.hecho.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.hecho.HechoDAO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.model.Hecho;
import mx.gob.segob.nsjp.service.hecho.ConsultarHechosService;
import mx.gob.segob.nsjp.service.hecho.impl.transform.HechoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
@Service
@Transactional
public class ConsultarHechosServiceImpl implements ConsultarHechosService {

	public final static Logger logger = Logger
			.getLogger(ModificarHechoServiceImpl.class);

	@Autowired
	private HechoDAO hechoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.hecho.ConsultarHechosService#consultarHechos
	 * (mx.gob.segob.nsjp.dto.hecho.HechoDTO)
	 */
	@Override
	public List<HechoDTO> consultarHechos(HechoDTO hechoDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR HECHOS POR ALGUN CRITERIO (ID, EXPEDIENTE, LUGAR, TIEMPO) ****/");

		Hecho hecho;
		List<HechoDTO> dtos = new ArrayList<HechoDTO>();
		if (hechoDTO == null) {
			List<Long> ids = hechoDao.findAllId();
			for (Long id : ids) {
				dtos.add(HechoTransformer.transformarHecho(hechoDao.read(id)));
			}
		} else if (hechoDTO.getHechoId() != null) {
			hecho = hechoDao.read(hechoDTO.getHechoId());
			dtos.add(HechoTransformer.transformarHecho(hecho));
		} else {
			hecho = HechoTransformer.transformarHecho(hechoDTO);
			List<Hecho> hechos = hechoDao.consultarHechos(hecho);
			for (Hecho cho : hechos) {
				dtos.add(HechoTransformer.transformarHecho(cho));
			}
		}

		return dtos;
	}
}
