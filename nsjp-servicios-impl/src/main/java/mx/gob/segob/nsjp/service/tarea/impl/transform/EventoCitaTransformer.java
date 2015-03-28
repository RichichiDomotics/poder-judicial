/**
* Nombre del Programa : EventoCitaTransformer.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Convierte de EventoCita a EventoCitaDTO
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
package mx.gob.segob.nsjp.service.tarea.impl.transform;

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.model.EventoCita;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Convierte de EventoCita a EventoCitaDTO.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class EventoCitaTransformer {

	/**
	 * 
	 * @param eventoCita
	 * @return
	 */
	public static EventoCita transformarEventoCita(EventoCitaDTO eventoCitaDTO) {
		EventoCita eventoCita = new EventoCita();
		Calendar calendario = Calendar.getInstance();
		
		eventoCita.setEventoCitaId(eventoCitaDTO.getEventoCitaId());
		eventoCita.setDescripcionEvento(eventoCitaDTO.getDescripcionEvento());
		eventoCita.setNombreEvento(eventoCitaDTO.getNombreEvento());
		eventoCita.setFechaInicioEvento(eventoCitaDTO.getFechaInicioEvento());
		eventoCita.setFechaFinEvento(eventoCitaDTO.getFechaFinEvento());
		eventoCita.setFechaNotificacion(eventoCitaDTO.getFechaNotificacion());
		eventoCita.setDireccion(eventoCitaDTO.getDireccion());
		eventoCita.setTipoEvento(new Valor(eventoCitaDTO.getTipoEvento().getIdCampo()));
		if (eventoCitaDTO.getEstatus()!=null){
			eventoCita.setEstatus(new Valor(eventoCitaDTO.getEstatus().getIdCampo()));
		}
		if (eventoCitaDTO.getEsAlertaAlarma()!= null){
			eventoCita.setTieneAlarma(eventoCitaDTO.getEsAlertaAlarma());
		}
		
		return eventoCita;
	}

	public static EventoCitaDTO transformarEventoCita(EventoCita eventoCita) {
		EventoCitaDTO eventoCitaDTO = new EventoCitaDTO();
		
		eventoCitaDTO.setEventoCitaId(eventoCita.getEventoCitaId());
		eventoCitaDTO.setDescripcionEvento(eventoCita.getDescripcionEvento());
		eventoCitaDTO.setNombreEvento(eventoCita.getNombreEvento());
		eventoCitaDTO.setFechaInicioEvento(eventoCita.getFechaInicioEvento());
		eventoCitaDTO.setFechaFinEvento(eventoCita.getFechaFinEvento());
		eventoCitaDTO.setStrFechaInicioEvento(DateUtils.formatear(eventoCita.getFechaInicioEvento()));
		eventoCitaDTO.setStrFechaFinEvento(DateUtils.formatear(eventoCita.getFechaInicioEvento()));
		eventoCitaDTO.setHoraInicioEvento(DateUtils.formatearHora(eventoCita.getFechaInicioEvento()));
		eventoCitaDTO.setHoraFinEvento(DateUtils.formatearHora(eventoCita.getFechaFinEvento()));
		
		eventoCitaDTO.setFechaNotificacion(eventoCita.getFechaNotificacion());
		eventoCitaDTO.setDireccion(eventoCita.getDireccion());
		
		if (eventoCita.getEstatus()!=null){
			eventoCitaDTO.setEstatus(new ValorDTO(eventoCita.getEstatus().getValorId(), eventoCita.getEstatus().getValor()));
		}
		if (eventoCita.getTieneAlarma() != null){
			eventoCitaDTO.setEsAlertaAlarma(eventoCita.getTieneAlarma());
		}
		if(eventoCita.getTipoEvento() != null){
			eventoCitaDTO.setTipoEvento(new ValorDTO(eventoCita.getTipoEvento().getValorId()));
		}
		
		return eventoCitaDTO;
	}

}
