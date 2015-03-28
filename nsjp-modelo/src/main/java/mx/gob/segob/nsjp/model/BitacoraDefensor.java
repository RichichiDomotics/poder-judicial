package mx.gob.segob.nsjp.model;

import java.sql.Timestamp;
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
 * BitacoraDefensor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BitacoraDefensor")
public class BitacoraDefensor implements java.io.Serializable {

    // Fields

    private Long bitacoraDefensorId;
    private Funcionario funcionario;
    private Expediente expediente;
    private Boolean lecturaDerechos;
    private Boolean esJudicializacion;
    private Date fechaJudicializacion;
    private Boolean esLiberacion;
    private Date fechaLiberacion;
    private String observaciones;
    private Boolean esAceptado;

    // Constructors

    /** default constructor */
    public BitacoraDefensor() {
    }

    /** minimal constructor */
    public BitacoraDefensor(Long bitacoraDefensorId, Funcionario funcionario,
            Expediente expediente) {
        this.bitacoraDefensorId = bitacoraDefensorId;
        this.funcionario = funcionario;
        this.expediente = expediente;
    }



    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BitacoraDefensor_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getBitacoraDefensorId() {
        return this.bitacoraDefensorId;
    }

    public void setBitacoraDefensorId(Long bitacoraDefensorId) {
        this.bitacoraDefensorId = bitacoraDefensorId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionario", nullable = false)
    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Expediente_id", nullable = false)
    public Expediente getExpediente() {
        return this.expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    @Column(name = "bLecturaDerechos", precision = 1, scale = 0)
    public Boolean getLecturaDerechos() {
        return this.lecturaDerechos;
    }

    public void setLecturaDerechos(Boolean blecturaDerechos) {
        this.lecturaDerechos = blecturaDerechos;
    }

    @Column(name = "bEsJudicializacion", precision = 1, scale = 0)
    public Boolean getEsJudicializacion() {
        return this.esJudicializacion;
    }

    public void setEsJudicializacion(Boolean besJudicializacion) {
        this.esJudicializacion = besJudicializacion;
    }

    @Column(name = "dFechaJudicializacion", length = 23)
    public Date getFechaJudicializacion() {
        return this.fechaJudicializacion;
    }

    public void setFechaJudicializacion(Date dfechaJudicializacion) {
        this.fechaJudicializacion = dfechaJudicializacion;
    }

    @Column(name = "bEsLiberacion", precision = 1, scale = 0)
    public Boolean getEsLiberacion() {
        return this.esLiberacion;
    }

    public void setEsLiberacion(Boolean besLiberacion) {
        this.esLiberacion = besLiberacion;
    }

    @Column(name = "dFechaLiberacion", length = 23)
    public Date getFechaLiberacion() {
        return this.fechaLiberacion;
    }

    public void setFechaLiberacion(Date dfechaLiberacion) {
        this.fechaLiberacion = dfechaLiberacion;
    }

    @Column(name = "cObservaciones", length = 150)
    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String cobservaciones) {
        this.observaciones = cobservaciones;
    }

    /**
     * Método de acceso al campo esAceptado.
     * @return El valor del campo esAceptado
     */
    @Column(name = "bEsAceptado", precision = 1, scale = 0)
    public Boolean getEsAceptado() {
        return esAceptado;
    }

    /**
     * Asigna el valor al campo esAceptado.
     * @param esAceptado el valor esAceptado a asignar
     */
    public void setEsAceptado(Boolean esAceptado) {
        this.esAceptado = esAceptado;
    }

}