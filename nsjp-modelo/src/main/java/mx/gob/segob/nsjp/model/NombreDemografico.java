package mx.gob.segob.nsjp.model;

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
 * NombreDemografico entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "NombreDemografico")
public class NombreDemografico implements java.io.Serializable {

    // Fields

    private Long nombreDemograficoId;
    private Persona persona;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String sexo;
    private String rfc;
    private String curp;
    private java.util.Date fechaNacimiento;
    private Short edadAproximada;
    private Boolean esVerdadero;
    private String lugarNacimiento;
    private Valor paisNacimiento;
    private Municipio municipioNacimiento;
    private EntidadFederativa entidadFederativaNacimiento;

    // Constructors

    /** default constructor */
    public NombreDemografico() {
    }

    /** minimal constructor */
    public NombreDemografico(Long nombreDemograficoId, Persona persona) {
        this.nombreDemograficoId = nombreDemograficoId;
        this.persona = persona;
    }

    /** full constructor */
    public NombreDemografico(Long nombreDemograficoId, Persona persona,
            String nombre, String apellidoPaterno, String apellidoMaterno,
            String sexo, String rfc, String curp,
            java.util.Date fechaNacimiento, Short edadAproximada,
            Boolean esVerdadero, String lugarNacimiento) {
        this.nombreDemograficoId = nombreDemograficoId;
        this.persona = persona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.sexo = sexo;
        this.rfc = rfc;
        this.curp = curp;
        this.fechaNacimiento = fechaNacimiento;
        this.edadAproximada = edadAproximada;
        this.esVerdadero = esVerdadero;
        this.lugarNacimiento = lugarNacimiento;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NombreDemografico_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getNombreDemograficoId() {
        return this.nombreDemograficoId;
    }

    public void setNombreDemograficoId(Long nombreDemograficoId) {
        this.nombreDemograficoId = nombreDemograficoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Persona_id", nullable = false)
    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Column(name = "cNombre", length = 50)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "cApellidoPaterno", length = 50)
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Column(name = "cApellidoMaterno", length = 50)
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @Column(name = "cSexo", length = 1)
    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Column(name = "cRFC", length = 13)
    public String getRfc() {
        return this.rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Column(name = "cCURP", length = 18)
    public String getCurp() {
        return this.curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    @Column(name = "dFechaNacimiento", length = 23)
    public java.util.Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(java.util.Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Column(name = "iEdadAproximada", precision = 4, scale = 0)
    public Short getEdadAproximada() {
        return this.edadAproximada;
    }

    public void setEdadAproximada(Short edadAproximada) {
        this.edadAproximada = edadAproximada;
    }

    @Column(name = "bEsVerdadero", precision = 1, scale = 0)
    public Boolean getEsVerdadero() {
        return this.esVerdadero;
    }

    public void setEsVerdadero(Boolean esVerdadero) {
        this.esVerdadero = esVerdadero;
    }

    @Column(name = "cLugarNacimiento", length = 60)
    public String getLugarNacimiento() {
        return this.lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    /**
     * Método de acceso al campo paisNacimiento.
     * 
     * @return El valor del campo paisNacimiento
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PaisNacimiento_val")
    public Valor getPaisNacimiento() {
        return paisNacimiento;
    }

    /**
     * Asigna el valor al campo paisNacimiento.
     * 
     * @param paisNacimiento
     *            el valor paisNacimiento a asignar
     */
    public void setPaisNacimiento(Valor paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    /**
     * Método de acceso al campo municipioNacimiento.
     * 
     * @return El valor del campo municipioNacimiento
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Municipio_id")
    public Municipio getMunicipioNacimiento() {
        return municipioNacimiento;
    }

    /**
     * Asigna el valor al campo municipioNacimiento.
     * 
     * @param municipioNacimiento
     *            el valor municipioNacimiento a asignar
     */
    public void setMunicipioNacimiento(Municipio municipioNacimiento) {
        this.municipioNacimiento = municipioNacimiento;
    }

    /**
     * Método de acceso al campo entidadFederativaNacimiento.
     * 
     * @return El valor del campo entidadFederativaNacimiento
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EntidadFederativa_id")
    public EntidadFederativa getEntidadFederativaNacimiento() {
        return entidadFederativaNacimiento;
    }

    /**
     * Asigna el valor al campo entidadFederativaNacimiento.
     * 
     * @param entidadFederativaNacimiento
     *            el valor entidadFederativaNacimiento a asignar
     */
    public void setEntidadFederativaNacimiento(
            EntidadFederativa entidadFederativaNacimiento) {
        this.entidadFederativaNacimiento = entidadFederativaNacimiento;
    }

}
