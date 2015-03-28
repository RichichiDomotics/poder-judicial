/**
 * Nombre del Programa : Medida.java
 * Autor                            : GustavoBP
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 04/08/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Clase que contiene las operaciones y los atributos para realizar la función
 * asociada a las medidas que le son impuestas a los involucrados
 * 
 * @version 1.0
 * @author GustavoBP
 * 
 */
@Entity
@Table(name = "Medida")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Medida_id")
public class Medida extends Documento {

    // Atributos
    private Date fechaInicio;
    private Date fechaFin;
    private String descripcionMedida;

    // Relaciones
    private Involucrado involucrado;
    private Funcionario funcionario;
    private Valor valorPeriodicidad;
    private Valor valorTipoMedida;
    private Domicilio domicilio;
    private CompromisoPeriodico compromisoPeriodico;
    private List<Incidencia> incidencias;
    /**
     * Nombre de la persona que dará seguimiento a la Medida, <br/> 
     * cuando no sea un funcionario.
     */
    private String seguimiento; 
    private NumeroExpediente numeroExpediente;
    private Valor estatus;
    /**
     * Número de la Causa en PJ. Campo usando en SSP.
     */
    private String numeroCausa;
    /**
     * Número de la carpeta de ejecución en PJ. Campo usando en SSP.
     */
    private String numeroCarpetaEjecucion;
    /**
     * Nombre del juez de PJ. Campo usando en SSP.
     */
    private String juezOrdena;
    /**
     * Número del caso. Campo usando en SSP.
     */
    private String numeroCaso;
    
    private Mandamiento mandamiento;
    
    public Medida() {
    }

    public Medida(Long medidaId) {
        super();
        setDocumentoId(medidaId);
    }

    /**
     * @param fechaInicio
     * @param fechaFin
     * @param descripcionMedida
     * @param involucrado
     * @param funcionario
     * @param valorPeriodicidad
     * @param valorTipoMedida
     */
    public Medida(Date fechaInicio, Date fechaFin, String descripcionMedida,
            Involucrado involucrado, Funcionario funcionario,
            Valor valorPeriodicidad, Valor valorTipoMedida) {
        super();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcionMedida = descripcionMedida;
        this.involucrado = involucrado;
        this.funcionario = funcionario;
        this.valorPeriodicidad = valorPeriodicidad;
        this.valorTipoMedida = valorTipoMedida;
    }

    @Column(name = "dFechaInicio", nullable = false, length = 23)
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Column(name = "dFechaFin", length = 23)
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Column(name = "cDescripcionMedida")
    public String getDescripcionMedida() {
        return descripcionMedida;
    }

    public void setDescripcionMedida(String descripcionMedida) {
        this.descripcionMedida = descripcionMedida;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Involucrado_id", nullable = false)
    public Involucrado getInvolucrado() {
        return involucrado;
    }

    public void setInvolucrado(Involucrado involucrado) {
        this.involucrado = involucrado;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionario")
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Periodicidad_val")
    public Valor getValorPeriodicidad() {
        return valorPeriodicidad;
    }

    public void setValorPeriodicidad(Valor valorPeriodicidad) {
        this.valorPeriodicidad = valorPeriodicidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoMedida_val", nullable = false)
    public Valor getValorTipoMedida() {
        return valorTipoMedida;
    }

    public void setValorTipoMedida(Valor valorTipoMedida) {
        this.valorTipoMedida = valorTipoMedida;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Domicilio_id")
    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medida")
    public CompromisoPeriodico getCompromisoPeriodico() {
        return compromisoPeriodico;
    }

    public void setCompromisoPeriodico(CompromisoPeriodico compromisoPeriodico) {
        this.compromisoPeriodico = compromisoPeriodico;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medida")
    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }
    @Column(name = "cSeguimiento", nullable = true, length = 60)
    public String getSeguimiento() {
        return this.seguimiento;
    }

    public void setSeguimiento(String seguimiento) {
        this.seguimiento = seguimiento;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumeroExpediente_id", nullable = false)
    public NumeroExpediente getNumeroExpediente() {
        return this.numeroExpediente;
    }

    public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    /**
     * Método de acceso al campo estatus.
     * @return El valor del campo estatus
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Estatus_val", nullable = false)
    public Valor getEstatus() {
        return estatus;
    }

    /**
     * Asigna el valor al campo estatus.
     * @param estatus el valor estatus a asignar
     */
    public void setEstatus(Valor estatus) {
        this.estatus = estatus;
    }

    /**
     * Método de acceso al campo numeroCausa.
     * @return El valor del campo numeroCausa
     */
    @Column(name = "cNumeroCausa", nullable = true, length = 23)
    public String getNumeroCausa() {
        return numeroCausa;
    }

    /**
     * Asigna el valor al campo numeroCausa.
     * @param numeroCausa el valor numeroCausa a asignar
     */
    public void setNumeroCausa(String numeroCausa) {
        this.numeroCausa = numeroCausa;
    }

    /**
     * Método de acceso al campo numeroCarpetaEjecucion.
     * @return El valor del campo numeroCarpetaEjecucion
     */
    @Column(name = "cNumeroCarpetaEjecucion", nullable = true, length = 23)
    public String getNumeroCarpetaEjecucion() {
        return numeroCarpetaEjecucion;
    }

    /**
     * Asigna el valor al campo numeroCarpetaEjecucion.
     * @param numeroCarpetaEjecucion el valor numeroCarpetaEjecucion a asignar
     */
    public void setNumeroCarpetaEjecucion(String numeroCarpetaEjecucion) {
        this.numeroCarpetaEjecucion = numeroCarpetaEjecucion;
    }

    /**
     * Método de acceso al campo juezOrdena.
     * @return El valor del campo juezOrdena
     */
    @Column(name = "cJuezOrdena", length = 60)
    public String getJuezOrdena() {
        return juezOrdena;
    }

    /**
     * Asigna el valor al campo juezOrdena.
     * @param juezOrdena el valor juezOrdena a asignar
     */
    public void setJuezOrdena(String juezOrdena) {
        this.juezOrdena = juezOrdena;
    }

    /**
     * Método de acceso al campo numeroCaso.
     * @return El valor del campo numeroCaso
     */
    @Column(name = "cNumeroCaso", length = 28)
    public String getNumeroCaso() {
        return numeroCaso;
    }

    /**
     * Asigna el valor al campo numeroCaso.
     * @param numeroCaso el valor numeroCaso a asignar
     */
    public void setNumeroCaso(String numeroCaso) {
        this.numeroCaso = numeroCaso;
    }

    /**
     * Método de acceso al campo mandamiento.
     * @return El valor del campo mandamiento
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="medida")
    public Mandamiento getMandamiento() {
        return mandamiento;
    }

    /**
     * Asigna el valor al campo mandamiento.
     * @param mandamiento el valor mandamiento a asignar
     */
    public void setMandamiento(Mandamiento mandamiento) {
        this.mandamiento = mandamiento;
    }

}
