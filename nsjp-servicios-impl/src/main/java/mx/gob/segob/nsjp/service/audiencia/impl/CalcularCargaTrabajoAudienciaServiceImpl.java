/**
 * Nombre del Programa  : CalcularCargaTrabajoAudienciaServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Registra un involucrado con calidad de Testigo en la audiencia
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
package mx.gob.segob.nsjp.service.audiencia.impl;

import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.service.audiencia.CalcularCargaTrabajoAudienciaService;
import mx.gob.segob.nsjp.service.audiencia.ConsultarComplejidadAudienciaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CalcularCargaTrabajoAudienciaServiceImpl implements
		CalcularCargaTrabajoAudienciaService {

	public final static Logger logger = Logger.getLogger(CalcularCargaTrabajoAudienciaServiceImpl.class);
	
	@Autowired
	AudienciaDAO audienciaDAO;
	@Autowired
	ConsultarComplejidadAudienciaService ccas;
	
	@Override
	public Double calcularCargaTrabajoAudiencia(AudienciaDTO audienciaDTO)
			throws NSJPNegocioException {
		Audiencia audiencia = audienciaDAO.read(audienciaDTO.getId());
		Double complejidad = Double.parseDouble(ccas.consultarComplejidadAudiencia(audienciaDTO).getValor());

		Set<InvolucradoAudiencia> involucradosAudiencia = audiencia.getInvlucradoAudiencias();
		Involucrado involucrado;
		int delitos = 0;
		Calidad calidad;
		Calidades c;
		double involucradoDelidos = 0.0;
		logger.debug("involucradosAudiencia.size() :: "+involucradosAudiencia.size());
		for(InvolucradoAudiencia involucradoAudiencia : involucradosAudiencia){
			involucrado =  involucradoAudiencia.getInvolucrado();
			calidad = involucrado.getCalidad();
			if(calidad != null && calidad.getTipoCalidad() != null){
				c = Calidades.getByValor(calidad.getTipoCalidad().getValorId());
				switch(c){
				case PROBABLE_RESPONSABLE_PERSONA:
				case PROBABLE_RESPONSABLE_ORGANIZACION:
					delitos = involucrado.getDelitosCometidos().size();
					involucradoDelidos += delitos;
					break;
				}
				logger.debug("Calidad :: " + c);
				logger.debug("involucradoDelidos :: " + involucradoDelidos);
			}
		}

		return complejidad + Math.log(involucradoDelidos);
	}

}
