package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Caso entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Caso", uniqueConstraints = @UniqueConstraint(columnNames = "cNumeroGeneralCaso"))
public class Caso implements java.io.Serializable {

    // Fields

    private Long casoId;
    private String numeroGeneralCaso;
    private java.util.Date fechaApertura;
    private java.util.Date fechaCierre;
    private String imputado;
    private String victima;
    private Short estatus;
    private Set<Expediente> expedientes = new HashSet<Expediente>(0);
    private Valor etapa;


    // Constructors

    /** default constructor */
    public Caso() {
    }

    /** super minimal constructor */
    public Caso(Long casoId) {
        this.casoId = casoId;
    }

    
    /** minimal constructor */
    public Caso(Long casoId, String numeroGeneralCaso) {
        this.casoId = casoId;
        this.numeroGeneralCaso = numeroGeneralCaso;
    }
    public Caso(Long casoId, String numeroGeneralCaso, Short status) {
        this.casoId = casoId;
        this.numeroGeneralCaso = numeroGeneralCaso;
        this.estatus = status;
    }
    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Caso_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getCasoId() {
        return this.casoId;
    }

    public void setCasoId(Long casoId) {
        this.casoId = casoId;
    }

    @Column(name = "cNumeroGeneralCaso", unique = true, nullable = false, length = 27)
    public String getNumeroGeneralCaso() {
        return this.numeroGeneralCaso;
    }

    public void setNumeroGeneralCaso(String numeroGeneralCaso) {
        this.numeroGeneralCaso = numeroGeneralCaso;
    }

    @Column(name = "dFechaApertura", length = 23)
    public java.util.Date getFechaApertura() {
        return this.fechaApertura;
    }

    public void setFechaApertura(java.util.Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    @Column(name = "dFechaCierre", length = 23)
    public java.util.Date getFechaCierre() {
        return this.fechaCierre;
    }

    public void setFechaCierre(java.util.Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @Column(name = "cImputado", length = 60)
    public String getImputado() {
        return this.imputado;
    }

    public void setImputado(String imputado) {
        this.imputado = imputado;
    }

    @Column(name = "cVictima", length = 60)
    public String getVictima() {
        return this.victima;
    }

    public void setVictima(String victima) {
        this.victima = victima;
    }

    @Column(name = "iEstatus", precision = 4, scale = 0)
    public Short getEstatus() {
        return this.estatus;
    }

    public void setEstatus(Short estatus) {
        this.estatus = estatus;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "caso")
    public Set<Expediente> getExpedientes() {
        return this.expedientes;
    }

    public void setExpedientes(Set<Expediente> expedientes) {
        this.expedientes = expedientes;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Etapa_val", nullable = true)
    public Valor getEtapa() {
        return etapa;
    }

    public void setEtapa(Valor etapa) {
        this.etapa = etapa;
    }

    public String toString(){
    	return numeroGeneralCaso;
    }
}
