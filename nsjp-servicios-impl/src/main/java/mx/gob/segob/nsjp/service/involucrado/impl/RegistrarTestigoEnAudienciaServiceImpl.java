/**
 * Nombre del Programa  : RegistrarTestigoEnAudienciaServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Registra un ojeto en la audiencia
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.involucrado.impl;

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.relacion.TipoRelacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.InvolucradoAudienciaDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.model.InvolucradoAudienciaId;
import mx.gob.segob.nsjp.service.domicilio.IngresarDomicilioService;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.involucrado.RegistrarTestigoEnAudienciaService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.relacion.GenerarRelacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrarTestigoEnAudienciaServiceImpl implements
		RegistrarTestigoEnAudienciaService {

	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private AudienciaDAO audienciaDAO;
	@Autowired
	private InvolucradoAudienciaDAO involucradoAudienciaDAO;
	@Autowired
	private IngresarDomicilioService ingresarDomicilioService;
	@Autowired
	private GenerarRelacionService generarRelacionService;
	
	@Override
	public void registrarTestigoEnAudiencia(AudienciaDTO audiencia,
			InvolucradoDTO testigoDTO) throws NSJPNegocioException {
		
		Audiencia au = audienciaDAO.read(audiencia.getId());
			CalidadDTO calidadDTO = new CalidadDTO();
			calidadDTO.setCalidades(Calidades.TESTIGO);
		testigoDTO.setCalidadDTO(calidadDTO);
		testigoDTO.setTipoPersona(1L);
		testigoDTO.setMotivoComparecencia("Tetigo");
		
		DomicilioDTO direccion = testigoDTO.getDomicilio();
			ExpedienteDTO expediente = new ExpedienteDTO();
			expediente.setExpedienteId(au.getExpediente().getExpedienteId());
		direccion.setExpedienteDTO(expediente);
		Long idDomicilio = ingresarDomicilioService.ingresarDomicilio(direccion);
		
		Involucrado testigo =  InvolucradoTransformer.transformarInvolucrado(testigoDTO);
		testigo.setExpediente(au.getExpediente());
		testigo.setFechaCreacionElemento(Calendar.getInstance().getTime());		
		Long idTestigo = involucradoDAO.create(testigo);
		testigo.setElementoId(idTestigo);
		
		generarRelacionService.generarRelacion(idTestigo, idDomicilio, Relaciones.RESIDENCIA, TipoRelacion.EXPLICITA.getValorId().shortValue());
		
		InvolucradoAudiencia ia = new InvolucradoAudiencia();
			InvolucradoAudienciaId iaID = new InvolucradoAudienciaId();	
			iaID.setaudienciaId(au.getAudienciaId());
			iaID.setInvolucradoId(idTestigo);
		ia.setId(iaID);
		ia.setAudiencia(au);
		ia.setInvolucrado(testigo);
		
		involucradoAudienciaDAO.create(ia);
		
	}

}
