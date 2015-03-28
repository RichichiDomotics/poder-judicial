/**
* Nombre del Programa : AvisoDetencionServiceImpl.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del contrato de Servicio para avisos de detencion
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
package mx.gob.segob.nsjp.service.avisodetencion.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.avisodetencion.AvisoDetencionDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.DefensaInvolucradoDAO;
import mx.gob.segob.nsjp.dao.parametro.ParametroDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.model.AvisoDetencion;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Parametro;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.avisodetencion.AvisoDetencionService;
import mx.gob.segob.nsjp.service.avisodetencion.impl.transform.AvisoDetencionTransformer;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.funcionario.impl.ConsultarAbogadosServiceImpl;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

/**
 * Implementacion del contrato de Servicio para avisos de detencion
 * @version 1.0
 * @author Tattva-IT
 *
 */

@Service
@Transactional
public class AvisoDetencionServiceImpl implements AvisoDetencionService {

	@Autowired
	private AvisoDetencionDAO avisoDetencionDAO;
	@Autowired
	private ParametroDAO parametroDAO;
	@Autowired 
	private ExpedienteDAO expedienteDAO;
	@Autowired
	private DefensaInvolucradoDAO defensaInvolucradoDAO;
	
	@Autowired
	private AsignarNumeroExpedienteService asignarNumeroExpedienteService;
	
	
	private final static Logger logger = Logger.getLogger(AvisoDetencionServiceImpl.class);
	
	@Override
	public List<AvisoDetencionDTO> obtenerAvisosDetencionPorEstatus(
			EstatusNotificacion estatusnotificacion)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("SERVICIO PARA LA CONSULTA DE AVISOS DE DETENCION POR ESTATUS");
		
		List<AvisoDetencion> avisosDetencion = avisoDetencionDAO.obtenerAvisosDetencionPorEstatus(estatusnotificacion.getValorId());
		List<AvisoDetencionDTO> lsResp = new ArrayList<AvisoDetencionDTO>();
		if (!avisosDetencion.isEmpty())
			lsResp= AvisoDetencionTransformer.transformarAvisosDetencion(avisosDetencion);
		
		return lsResp;
	}

	@Override
	public List<AvisoDetencionDTO> consultarAvisosDetencionHistoricoByEstatus(
			EstatusNotificacion estatusNotificacion)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA LA CONSULTA DE AVISOS DE DETENCION DE ACUERDO A LA " +
															"VARIBLE DEL HISTORICO Y POR ESTATUS  ****/");
		
		Parametro parametro = parametroDAO.obtenerPorClave(Parametros.LIMITE_HISTORICO_CONSULTAS);		
		Long dias = new Long(parametro.getValor());

		Calendar calTemp = Calendar.getInstance();
		calTemp.setTime(new Date());
		calTemp.add(calTemp.DATE, -dias.intValue());
				
		List<AvisoDetencion> avisosDetencion = avisoDetencionDAO.consultarAvisosDetencionHistoricoByEstatus(calTemp.getTime(), estatusNotificacion.getValorId());
		
		List<AvisoDetencionDTO> avisosDetDTO = new ArrayList<AvisoDetencionDTO>();
		for (AvisoDetencion avisoDetencion : avisosDetencion) {
			avisosDetDTO.add(AvisoDetencionTransformer.transformarAvisoDetencion(avisoDetencion));
		}
		return avisosDetDTO;
	}

	@Override
	public AvisoDetencionDTO obtenerAvisoDetencion(
			AvisoDetencionDTO avisoDetencionDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
		    logger.debug("Recuperando Aviso de Detención con id :: " + avisoDetencionDTO.getDocumentoId());
		
		if (avisoDetencionDTO.getDocumentoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		AvisoDetencion avisoDetencion = avisoDetencionDAO.read(avisoDetencionDTO.getDocumentoId());
		AvisoDetencionDTO avisoDetDTO = AvisoDetencionTransformer.transformarAvisoDetencion(avisoDetencion);
				
		return avisoDetDTO;
	}

	@Override
	public AvisoDetencionDTO atenderAvisoDetencion(
			AvisoDetencionDTO avisoDetencionDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA COLOCAR EL ESTATUS DEL AVISO EN ATENDIDO ****/");
		
		if (avisoDetencionDTO.getDocumentoId()==null || avisoDetencionDTO.getUsuario()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		ExpedienteDTO nuevoExp = new ExpedienteDTO();
		nuevoExp.setArea(new AreaDTO(Areas.COORDINACION_DEFENSORIA));
		nuevoExp.setEtapa(new ValorDTO(EtapasExpediente.INTEGRACION.getValorId()));
		nuevoExp.setUsuario(avisoDetencionDTO.getUsuario());
		
		ExpedienteDTO expGenerado =  asignarNumeroExpedienteService.asignarNumeroExpediente(nuevoExp);
		Expediente expediente = expedienteDAO.read(expGenerado.getExpedienteId());
		logger.debug("/**** SE GENERO EL EXPEDIENTE PARA DEFENSORIA ****/");
		
		AvisoDetencion avisoDetencion = avisoDetencionDAO.read(avisoDetencionDTO.getDocumentoId());
		avisoDetencion.setEstatus(new Valor(EstatusNotificacion.ATENDIDA.getValorId()));
		avisoDetencion.setExpediente(expediente);
		avisoDetencionDAO.update(avisoDetencion);
		
		AvisoDetencionDTO aviDetRetorno = new AvisoDetencionDTO();
		aviDetRetorno.setDocumentoId(avisoDetencion.getDocumentoId());	
		ExpedienteDTO expedienteDTO = new ExpedienteDTO(expediente.getExpedienteId());
		expedienteDTO.setNumeroExpediente(expediente.getNumeroExpediente());
		aviDetRetorno.setExpedienteDTO(expedienteDTO);
		
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SE ATENDIO CORRECTAMENTE EL AVISO DETENCION ****/");			
		
		return aviDetRetorno;
	}

}
