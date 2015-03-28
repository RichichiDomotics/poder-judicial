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
 * TurnoLaboral entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TurnoLaboral")
public class TurnoLaboral implements java.io.Serializable {

    // Fields

    private Long turnoLaboralId;
    private String nombreTurno;
    /**
     * Tomar solo la hora y el minuto.
     */
    private Date horaInicioTurno;
    /**
     * Tomar solo la hora y el minuto.
     */
    private Date horaFinTurno;

    // Constructors

    /** default constructor */
    public TurnoLaboral() {
    }

    /** minimal constructor */
    public TurnoLaboral(Long turnoLaboralId, String cnombreTurno,
            Date dhoraInicioTurno, Date dhoraFinTurno) {
        this.turnoLaboralId = turnoLaboralId;
        this.nombreTurno = cnombreTurno;
        this.horaInicioTurno = dhoraInicioTurno;
        this.horaFinTurno = dhoraFinTurno;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TurnoLaboral_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getTurnoLaboralId() {
        return this.turnoLaboralId;
    }

    public void setTurnoLaboralId(Long turnoLaboralId) {
        this.turnoLaboralId = turnoLaboralId;
    }

    @Column(name = "cNombreTurno", nullable = false, length = 15)
    public String getNombreTurno() {
        return this.nombreTurno;
    }

    public void setNombreTurno(String cnombreTurno) {
        this.nombreTurno = cnombreTurno;
    }

    @Column(name = "dHoraInicioTurno", nullable = false, length = 16)
    public Date getHoraInicioTurno() {
        return this.horaInicioTurno;
    }

    public void setHoraInicioTurno(Date dhoraInicioTurno) {
        this.horaInicioTurno = dhoraInicioTurno;
    }

    @Column(name = "dHoraFinTurno", nullable = false, length = 16)
    public Date getHoraFinTurno() {
        return this.horaFinTurno;
    }

    public void setHoraFinTurno(Date dhoraFinTurno) {
        this.horaFinTurno = dhoraFinTurno;
    }

}