/**
 * Nombre del Programa  : FinalizarAudienciaServieImpl
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 29 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Finaliza la audiencia y almacena la información de final de audienccia
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

import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.audiencia.FinalizarAudienciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class FinalizarAudienciaServiceImpl implements FinalizarAudienciaService {

	/**
     * Objeto de Acceso a Datos para la entidad Involucrado.
     */
	@Autowired
	private AudienciaDAO audienciaDAO;
	
	@Override
	public void finalizarAudienciaService(AudienciaDTO audienciaDTO) throws NSJPNegocioException {

		Audiencia audiencia = audienciaDAO.read(audienciaDTO.getId());
		
		Set<Actividad> actividades = audiencia.getNumeroExpediente().getExpediente().getActividads();
		Actividad auxiliar = null, finalAudiencia = null;
		Iterator<Actividad> iterator = actividades.iterator(); 
		while(iterator.hasNext()){
			auxiliar =  iterator.next();
			if(auxiliar.getTipoActividad().getValorId().longValue() == Actividades.FINAL_DE_AUDIENCIA.getValorId().longValue()){
				finalAudiencia = auxiliar;
			}
		}
		
		if(finalAudiencia == null || ( finalAudiencia != null && finalAudiencia.getDocumento() == null)){
			throw new NSJPNegocioException(CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO);
		}
		audiencia.setFechaHoraFin(Calendar.getInstance().getTime());
		audiencia.setEstatus(new Valor(EstatusAudiencia.FINALIZADA.getValorId()));
		audienciaDAO.saveOrUpdate(audiencia);
	}

}
