package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AgendaAlarma entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AgendaAlarma")
public class AgendaAlarma implements java.io.Serializable {

    // Fields

    private Long agendaAlarmaId;
    private EventoCita eventoCita;
    private String tituloAlarma;
    private Short programacionRepeticion;
    private Date fechaAlarma;
    private String estatus;
    private String motivo;

    // Constructors

    /** default constructor */
    public AgendaAlarma() {
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AgendaAlarma_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getAgendaAlarmaId() {
        return this.agendaAlarmaId;
    }

    public void setAgendaAlarmaId(Long agendaAlarmaId) {
        this.agendaAlarmaId = agendaAlarmaId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EventoCita_id", nullable = false)
    public EventoCita getEventoCita() {
        return this.eventoCita;
    }

    public void setEventoCita(EventoCita eventoCita) {
        this.eventoCita = eventoCita;
    }

    @Column(name = "cTituloAlarma", nullable = false, length = 50)
    public String getTituloAlarma() {
        return this.tituloAlarma;
    }

    public void setTituloAlarma(String ctituloAlarma) {
        this.tituloAlarma = ctituloAlarma;
    }

    @Column(name = "iProgramacionRepeticion", precision = 4, scale = 0)
    public Short getProgramacionRepeticion() {
        return this.programacionRepeticion;
    }

    public void setProgramacionRepeticion(Short iprogramacionRepeticion) {
        this.programacionRepeticion = iprogramacionRepeticion;
    }

    @Column(name = "dFechaAlarma", nullable = false, length = 23)
    public Date getFechaAlarma() {
        return this.fechaAlarma;
    }

    public void setFechaAlarma(Date dfechaAlarma) {
        this.fechaAlarma = dfechaAlarma;
    }

    @Column(name = "cEstatus", length = 1)
    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String cestatus) {
        this.estatus = cestatus;
    }

    @Column(name = "cMotivo", length = 100)
    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String cmotivo) {
        this.motivo = cmotivo;
    }

}