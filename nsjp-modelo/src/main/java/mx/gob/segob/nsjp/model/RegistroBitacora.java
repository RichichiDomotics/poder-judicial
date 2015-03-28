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
 * RegistroBitacora entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RegistroBitacora")
public class RegistroBitacora implements java.io.Serializable {

    // Fields

    private Long registroBitacoraId;
    private Valor tipoMovimiento;
    private NumeroExpediente numeroExpediente;
    private Date fechaInicio;
    private String nuevo;
    private Short consecutivo;
    // Constructors

    /** default constructor */
    public RegistroBitacora() {
    }

    /**
     * 
     * @param registroBitacoraId
     * @param tipoMovimientoId
     * @param tipoMovimientoVal
     * @param fechaInicio
     * @param nuevo
     * @param consecutivo
     */
    public RegistroBitacora(Long registroBitacoraId, Long tipoMovimientoId,
            String tipoMovimientoVal, Date fechaInicio, String nuevo,
            Short consecutivo) {
        super();
        this.registroBitacoraId = registroBitacoraId;
        this.tipoMovimiento = new Valor(tipoMovimientoId, tipoMovimientoVal);
        this.fechaInicio = fechaInicio;
        this.nuevo = nuevo;
        this.consecutivo = consecutivo;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RegistroBitacora_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getRegistroBitacoraId() {
        return this.registroBitacoraId;
    }

    public void setRegistroBitacoraId(Long registroBitacoraId) {
        this.registroBitacoraId = registroBitacoraId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoMovimiento_val", nullable = false)
    public Valor getTipoMovimiento() {
        return this.tipoMovimiento;
    }

    public void setTipoMovimiento(Valor valor) {
        this.tipoMovimiento = valor;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumeroExpediente_id", nullable = false)
    public NumeroExpediente getNumeroExpediente() {
        return this.numeroExpediente;
    }

    public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    @Column(name = "dFechaInicio", nullable = false, length = 23)
    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date dfechaInicio) {
        this.fechaInicio = dfechaInicio;
    }

    @Column(name = "cNuevo", length = 150)
    public String getNuevo() {
        return this.nuevo;
    }

    public void setNuevo(String cposterior) {
        this.nuevo = cposterior;
    }
    @Column(name = "iConsecutivo", nullable = false, precision = 4, scale = 0)
    public Short getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(Short iconsecutivo) {
        this.consecutivo = iconsecutivo;
    }
}