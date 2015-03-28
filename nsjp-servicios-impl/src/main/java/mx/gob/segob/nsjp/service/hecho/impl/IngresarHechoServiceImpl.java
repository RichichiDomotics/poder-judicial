/**
 * Nombre del Programa : IngresarHechoService.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del servicio para realizar el ingreso de un Hecho
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

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.hecho.HechoDAO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.hecho.TiempoDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Hecho;
import mx.gob.segob.nsjp.model.Lugar;
import mx.gob.segob.nsjp.model.Tiempo;
import mx.gob.segob.nsjp.service.hecho.IngresarTiempoService;
import mx.gob.segob.nsjp.service.hecho.impl.transform.HechoTransformer;
import mx.gob.segob.nsjp.service.lugar.IngresarLugarService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para realizar el ingreso de un Hecho.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Service
@Transactional
public class IngresarHechoServiceImpl implements
		mx.gob.segob.nsjp.service.hecho.IngresarHechoService {

	/**
	 * 
	 */
	public static final Logger logger = Logger
			.getLogger(IngresarHechoServiceImpl.class);

	@Autowired
	private IngresarLugarService ingresarLugarService;
	@Autowired
	private IngresarTiempoService ingresarTiempoService;

	@Autowired
	private HechoDAO hechoDAO;
	@Autowired
	private ExpedienteDAO expedienteDAO;

	@Override
	public HechoDTO ingresarHecho(HechoDTO hechoDTO)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA INGRESAR/MODIFCAR UN HECHO ****/");

		logger.info(hechoDTO.getExpediente());
		logger.info(hechoDTO.getLugar());
		logger.info(hechoDTO.getTiempo());

		if (hechoDTO.getExpediente() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		HechoDTO loHechoBD = null;
		if(hechoDTO.getHechoId() != null && hechoDTO.getHechoId() > 0){
			//Actualizamos el la narrativa de un hecho
			Hecho loHechoParaActualizar = hechoDAO.read(hechoDTO.getHechoId());
			loHechoParaActualizar.setDescNarrativa(hechoDTO.getDescNarrativa());
			hechoDAO.update(loHechoParaActualizar);
		}
		else{
			Hecho hecho = HechoTransformer.transformarHecho(hechoDTO);

			Expediente exp = expedienteDAO.read(hechoDTO.getExpediente()
					.getExpedienteId());

			if (hechoDTO.getLugar() != null) {
				LugarDTO lugarDTO = ingresarLugarService.ingresarLugar(hechoDTO
						.getLugar());
				hecho.setLugar(new Lugar(lugarDTO.getElementoId()));
			}else
				hecho.setLugar(null);
			logger.debug("hecho.getDescNarrativa() :: " + hecho.getDescNarrativa());
			if (hechoDTO.getTiempo() != null) {
				TiempoDTO tiempoDTO = ingresarTiempoService.ingresarTiempo(hechoDTO
						.getTiempo());
				hecho.setTiempo(new Tiempo(tiempoDTO.getTiempoId()));
			}else
				hecho.setTiempo(null);
			hecho.setExpediente(exp);
			loHechoBD = new HechoDTO(hechoDAO.create(hecho));
		}
		return loHechoBD;
	}

}
