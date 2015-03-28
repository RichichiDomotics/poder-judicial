/**
 * Nombre del Programa : RegistrarActaCircunstanciadaServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13/07/2011
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

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ActaCircunstanciadaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.expediente.RegistrarActaCircunstanciadaService;
import mx.gob.segob.nsjp.service.hecho.IngresarHechoService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;

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
public class RegistrarActaCircunstanciadaServiceImpl implements
		RegistrarActaCircunstanciadaService {

	public final static Logger logger = Logger
			.getLogger(RegistrarActaCircunstanciadaServiceImpl.class);

	@Autowired
	private IngresarHechoService ingresarHechoService;
	@Autowired
	private IngresarIndividuoService ingresarIndividuoService;
 
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.expediente.RegistrarActaCircunstanciadaService
	 * #registrarActaCircunstanciada
	 * (mx.gob.segob.nsjp.dto.expediente.ActaCircunstanciadaDTO,
	 * mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public ExpedienteDTO registrarActaCircunstanciada(
			ActaCircunstanciadaDTO actaDTO, ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
		{
			logger.debug("registrarActaCircunstanciada_expedienteDTO ::"+ expedienteDTO);
			logger.debug("registrarActaCircunstanciada_actaDTO ::"+ actaDTO);
			logger.debug("registrarActaCircunstanciada_actaDTO_involucrado ::"+ actaDTO.getInvolucradoDTO());
			logger.debug("registrarActaCircunstanciada_actaDTO_hecho ::"+ actaDTO.getHechoDTO());
			
			logger.debug("/**** SERVICIO PARA REGISTRAR LOS ELEMENTOS DE UN ACTA CIRCUNSTANCIADA EN UN EXPEDIENTE DADO ****/");
		}

		/* Verificación de parámetros */
		if (expedienteDTO == null || actaDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (actaDTO.getInvolucradoDTO() == null
				|| actaDTO.getHechoDTO() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/* Operación */
		HechoDTO hecho = actaDTO.getHechoDTO();
		hecho.setExpediente(expedienteDTO);
		ingresarHechoService.ingresarHecho(hecho);

		InvolucradoDTO involucrado = actaDTO.getInvolucradoDTO();
		involucrado.setExpedienteDTO(expedienteDTO);
		if (involucrado.getDomicilio() != null) {
			DomicilioDTO domi = involucrado.getDomicilio();
			domi.setExpedienteDTO(expedienteDTO);
			domi.setFechaCreacionElemento(new Date());
		}
		if (involucrado.getDomicilioNotificacion() != null) {
			DomicilioDTO domi = involucrado.getDomicilioNotificacion();
			domi.setExpedienteDTO(expedienteDTO);
			domi.setFechaCreacionElemento(new Date());
		}
		
		CalidadDTO cualidad=new CalidadDTO();
		cualidad.setCalidades(Calidades.DENUNCIANTE);
		cualidad.setCalidadId(Calidades.DENUNCIANTE.getValorId());		
		involucrado.setCalidadDTO(cualidad);

		ingresarIndividuoService.ingresarIndividuo(involucrado);

		return expedienteDTO;
	}

}
