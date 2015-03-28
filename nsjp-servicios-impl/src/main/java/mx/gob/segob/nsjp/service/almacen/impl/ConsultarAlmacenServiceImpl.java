/**
 * Nombre del Programa : ConsultarAlmacenServiceImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 2 Aug 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del servicio para realizar las consultar de Almacen
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
package mx.gob.segob.nsjp.service.almacen.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.almacen.AlmacenDAO;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.service.almacen.ConsultarAlmacenService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.AlmacenTransformer;

/**
 * Implementacion del servicio para realizar las consultar de Almacen.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Service
@Transactional
public class ConsultarAlmacenServiceImpl implements ConsultarAlmacenService {

	/**
	 * 
	 */
	public final static Logger logger = Logger
			.getLogger(ConsultarAlmacenServiceImpl.class);

	@Autowired
	private AlmacenDAO almacenDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.service.almacen.ConsultarAlmacenService#
	 * obtenerAlmacenDelExpediente
	 * (mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public AlmacenDTO obtenerAlmacenDelExpediente(ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA OBTENER EL ALMACEN DE UN EXPEDIENTE ****/");

		if (expedienteDTO.getNumeroExpedienteId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		Almacen almacen = almacenDAO
				.obtenerAlmacenByNumExpediente(expedienteDTO
						.getNumeroExpedienteId());

		AlmacenDTO almacenDTO = new AlmacenDTO();
		if (almacen != null)
			almacenDTO = AlmacenTransformer.transformarAlmacen(almacen);

		return almacenDTO;
	}

	@Override
	public List<AlmacenDTO> consultarAlmacenesExpedientePorEstatus(
			Boolean estatus, Boolean tipo) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LOS ALMACENES DE EXPEDIENTES ****/");

		List<Almacen> almacenes = almacenDAO
				.consultarAlmacenesExpedienteByEstatus(estatus, tipo);

		List<AlmacenDTO> almacenesDTO = new ArrayList<AlmacenDTO>();
		for (Almacen almacen : almacenes) {
			almacenesDTO.add(AlmacenTransformer.transformarAlmacen(almacen));
		}
		return almacenesDTO;
	}

	@Override
	public AlmacenDTO consultarDetalleAlmacenPorId(Long almacenId)
			throws NSJPNegocioException {
		AlmacenDTO resp = new AlmacenDTO();
		Almacen almacen = new Almacen();
		if (logger.isDebugEnabled()) {
			logger.debug("/**** SERVICIO PARA OBTENER los datos generales del almacén y las relaciones con : ****/");
			logger.debug("/****•	Domicilio ****/");
			logger.debug("/****•	FuncionarioAlta ****/");
			logger.debug("/****•	FuncionarioAutoriza ****/");
		}

		if (almacenId == null || almacenId <= 0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		almacen = almacenDAO.consultarDetalleAlmacenPorId(almacenId);

		if (resp != null)
			resp = AlmacenTransformer.transformarAlmacen(almacen);

		return resp;
	}

}
