package mx.gob.segob.nsjp.dto.agendaalarma;

import java.util.Date;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;

/**
 *
 * @author Jacob Lobaco
 */
public class AgendaAlarmaDTO extends GenericDTO{

    private Long agendaAlarmaId;
    private EventoCitaDTO eventoCita;
    private String tituloAlarma;
    private Short programacionRepeticion;
    private Date fechaAlarma;
    private String estatus;
    private String motivo;

    public Long getAgendaAlarmaId() {
        return agendaAlarmaId;
    }

    public void setAgendaAlarmaId(Long agendaAlarmaId) {
        this.agendaAlarmaId = agendaAlarmaId;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public EventoCitaDTO getEventoCita() {
        return eventoCita;
    }

    public void setEventoCita(EventoCitaDTO eventoCita) {
        this.eventoCita = eventoCita;
    }

    public Date getFechaAlarma() {
        return fechaAlarma;
    }

    public void setFechaAlarma(Date fechaAlarma) {
        this.fechaAlarma = fechaAlarma;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Short getProgramacionRepeticion() {
        return programacionRepeticion;
    }

    public void setProgramacionRepeticion(Short programacionRepeticion) {
        this.programacionRepeticion = programacionRepeticion;
    }

    public String getTituloAlarma() {
        return tituloAlarma;
    }

    public void setTituloAlarma(String tituloAlarma) {
        this.tituloAlarma = tituloAlarma;
    }
}
