/**
 * Nombre del Programa : ConsultarNotificacionesXUsuarioServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 18-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.notificacion.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.service.notificacion.ConsultarNotificacionesXUsuarioService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultarNotificacionesXUsuarioServiceImpl implements
		ConsultarNotificacionesXUsuarioService {

	/**
	 * Logger de la clase.
	 */
	private final static Logger logger = Logger
			.getLogger(ConsultarNotificacionesXUsuarioServiceImpl.class);
	@Autowired
	private NotificacionDAO notificacionDao;

	@Override
	public long consultarNumeroTotalDeNotificacionesXUsuario(
			FuncionarioDTO funcionarioDto, ValorDTO estatusDto) {
		// Funcionario funcionario =
		// FuncionarioTransformer.transformarFuncionario(funcionarioDto);
		// Valor estatus = new Valor(estatusDto.getIdCampo());
		// return
		// notificacionDao.consultarNumeroTotalDeNotificacionesXUsuario(funcionario,
		// estatus);
		return 23;
	}

	@Override
	public List<NotificacionDTO> consultarNotificacionesXUsuario(
			FuncionarioDTO funcionarioDto, ValorDTO estatusDto, int pagina,
			int numeroDeRegistros, String campoOrden, int direccionOrden)
			throws NSJPNegocioException {
		List<NotificacionDTO> notificacionesDto = new LinkedList<NotificacionDTO>();
		int total = 23;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2011);
		c.set(Calendar.MONTH, 7);
		c.set(Calendar.DATE, 10);
		for (int i = 0; i < total; i++) {
			NotificacionDTO nueva = new NotificacionDTO();
			nueva.setDocumentoId((long) i);
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			CasoDTO casoDTO = new CasoDTO();
			casoDTO.setNumeroGeneralCaso("NGC00000abc" + i);
			expedienteDTO.setCasoDTO(casoDTO);
			nueva.setExpedienteDTO(expedienteDTO);
			nueva.setNumeroExpedienteUsuarioNotif("NE00000" + i);
			nueva.setMotivo("MTVO " + i);
			nueva.setFechaCreacion(c.getTime());
			notificacionesDto.add(nueva);
		}
		return notificacionesDto;
	}

	@Override
	public List<NotificacionDTO> consultarNotificacionesXFuncionario(
			FuncionarioDTO funcionarioDto, ValorDTO estatusDto)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR NOTIFICACIONES POR FUNCIONARIO ****/");

		if (funcionarioDto == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (funcionarioDto.getClaveFuncionario() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		Long estatus = null;
		if (estatusDto != null)
			estatus = estatusDto.getIdCampo();
		List<Notificacion> notifiaciones = notificacionDao
				.consultarNotificacionesXFuncionario(
						funcionarioDto.getClaveFuncionario(), estatus);
		List<NotificacionDTO> notificacionesDto = new ArrayList<NotificacionDTO>();
		for (Notificacion not : notifiaciones) {
			notificacionesDto.add(NotificacionTransformer
					.transformarNotificacion(not));
		}

		return notificacionesDto;
	}
}
