/**
 * 
 */
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.CartaCumplimientoDAO;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.CartaCumplimiento;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.documento.GuardarControversiaResueltaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adrian
 * 
 */
@Service
@Transactional
public class GuardarControversiaResueltaServiceImpl implements
		GuardarControversiaResueltaService {

	public final static Logger logger = Logger
			.getLogger(GuardarControversiaResueltaServiceImpl.class);
	@Autowired
	private FormaDAO formaDAO;
	@Autowired
	private CartaCumplimientoDAO cumplimientoDAO;
	@Autowired
	private ActividadDAO actividadDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.documento.GuardarControversiaResueltaService
	 * #guardarControversiaResuelta
	 * (mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO)
	 */
	@Override
	public Long guardarControversiaResuelta(CartaCumplimientoDTO cumplimientoDTO)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR UNA CARTA CUMPLIMIENTO Y ASOCIAR A EXPEDIENTE MEDIANTE UNA ACTIVIDAD ****/");

		if (cumplimientoDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (cumplimientoDTO.getFuncionario() == null
				|| cumplimientoDTO.getExpedienteDTO() == null
				|| cumplimientoDTO.getArchivoDigital() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (cumplimientoDTO.getFuncionario().getClaveFuncionario() == null
				|| cumplimientoDTO.getExpedienteDTO().getExpedienteId() == null
				|| cumplimientoDTO.getArchivoDigital() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		CartaCumplimiento carta = new CartaCumplimiento();
		/* Obligatorios de Documento */
		carta.setFechaCreacion(new Date());
		if (cumplimientoDTO.getNombreDocumento() == null)
			carta.setNombreDocumento("CARTA DE CUMPLIMIENTO DE ACUERDO");
		carta.setTipoDocumento(new Valor(
				TipoDocumento.CARTA_DE_CUMPLIMIENTO_DE_ACUERDO.getValorId()));
		// Plantilla en Blanco
		if (cumplimientoDTO.getFormaDTO() == null)
			carta.setForma(formaDAO.consultarFormaPorNombre("Blanco"));
		/* Requeridos por la posterior consulta */
		carta.setResponsableDocumento(new Funcionario(cumplimientoDTO.getFuncionario()
				.getClaveFuncionario()));
		carta.setEsLeido(false);
		carta.setArchivoDigital(ArchivoDigitalTransformer
				.transformarArchivoDigitalDTO(cumplimientoDTO
						.getArchivoDigital()));

		Long idCumplimineto = cumplimientoDAO.create(carta);

		/* Registra nueva actividad */
		Actividad actividad = new Actividad();
		Documento documento = new Documento();
		documento.setDocumentoId(idCumplimineto);
		actividad.setDocumento(documento);
		actividad.setFuncionario(new Funcionario(cumplimientoDTO.getFuncionario().getClaveFuncionario()));
		actividad.setExpediente(new Expediente(cumplimientoDTO
				.getExpedienteDTO().getExpedienteId()));
		actividad.setFechaCreacion(new Date());
		actividad.setTipoActividad(new Valor(Actividades.ELABORAR_CARTA_DE_CUMPLIMIENTO_DE_ACUERDO.getValorId()));

		actividadDAO.create(actividad);

		return idCumplimineto;
	}

}
