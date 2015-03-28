/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author adrian
 * 
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "QuejaCiudadana")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "QuejaCiudadana_id")
public class QuejaCiudadana extends Documento {

    private String folioQueja;
    private Date fechaRegistro;
    private String descripcionQueja;
    private String numeroExpediente;
    private Valor tipoQueja;
    private Valor motivoRechazo;
    private Valor estatusQueja;
    private QuejaConfirmada quejaConfirmada;

    // implicados
    private Implicado denunciado;
    private Implicado quejoso;
    private Implicado afectado;
    // documentos generados
    private Documento sancion;
    private Documento respuestaCiudadana;

    /**
     * @return the folioQueja
     */
    @Column(name = "cFolioQueja", length = 12, nullable = false)
    public String getFolioQueja() {
        return folioQueja;
    }
    /**
     * @return the fechaRegistro
     */
    @Column(name = "dFechaRegistro", length = 23, nullable = false)
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    /**
     * @return the descripcionQueja
     */
    @Column(name = "cDescripcionQueja", nullable = false)
    public String getDescripcionQueja() {
        return descripcionQueja;
    }

    /**
     * @return the numeroExpediente
     */
    @Column(name = "cNumeroExpediente", length = 19)
    public String getNumeroExpediente() {
        return numeroExpediente;
    }
    /**
     * @return the tipoQueja
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoQueja_val", nullable = false)
    public Valor getTipoQueja() {
        return tipoQueja;
    }
    /**
     * @return the motivoRechazo
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MotivoRechazo_val")
    public Valor getMotivoRechazo() {
        return motivoRechazo;
    }
    /**
     * @return the estatusQueja
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EstatusQueja_val", nullable = false)
    public Valor getEstatusQueja() {
        return estatusQueja;
    }
    /**
     * @return the quejaConfirmada
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quejaCiudadana")
    public QuejaConfirmada getQuejaConfirmada() {
        return quejaConfirmada;
    }
    /**
     * @return the documentos
     */

    /**
     * @param folioQueja
     *            the folioQueja to set
     */
    public void setFolioQueja(String folioQueja) {
        this.folioQueja = folioQueja;
    }
    /**
     * @param fechaRegistro
     *            the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    /**
     * @param descripcionQueja
     *            the descripcionQueja to set
     */
    public void setDescripcionQueja(String descripcionQueja) {
        this.descripcionQueja = descripcionQueja;
    }
    /**
     * @param numeroExpediente
     *            the numeroExpediente to set
     */
    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }
    /**
     * @param tipoQueja
     *            the tipoQueja to set
     */
    public void setTipoQueja(Valor tipoQueja) {
        this.tipoQueja = tipoQueja;
    }
    /**
     * @param motivoRechazo
     *            the motivoRechazo to set
     */
    public void setMotivoRechazo(Valor motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }
    /**
     * @param estatusQueja
     *            the estatusQueja to set
     */
    public void setEstatusQueja(Valor estatusQueja) {
        this.estatusQueja = estatusQueja;
    }
    /**
     * @param involucrado
     *            the involucrado to set
     */
    /**
     * @param quejaConfirmada
     *            the quejaConfirmada to set
     */
    public void setQuejaConfirmada(QuejaConfirmada quejaConfirmada) {
        this.quejaConfirmada = quejaConfirmada;
    }
    /**
     * Método de acceso al campo denunciado.
     * 
     * @return El valor del campo denunciado
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Denunciado_id")
    public Implicado getDenunciado() {
        return denunciado;
    }
    /**
     * Asigna el valor al campo denunciado.
     * 
     * @param denunciado
     *            el valor denunciado a asignar
     */
    public void setDenunciado(Implicado denunciado) {
        this.denunciado = denunciado;
    }
    /**
     * Método de acceso al campo quejoso.
     * 
     * @return El valor del campo quejoso
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Quejoso_id")
    public Implicado getQuejoso() {
        return quejoso;
    }
    /**
     * Asigna el valor al campo quejoso.
     * 
     * @param quejoso
     *            el valor quejoso a asignar
     */
    public void setQuejoso(Implicado quejoso) {
        this.quejoso = quejoso;
    }
    /**
     * Método de acceso al campo afectado.
     * 
     * @return El valor del campo afectado
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Afectado_id")
    public Implicado getAfectado() {
        return afectado;
    }
    /**
     * Asigna el valor al campo afectado.
     * 
     * @param afectado
     *            el valor afectado a asignar
     */
    public void setAfectado(Implicado afectado) {
        this.afectado = afectado;
    }
    /**
     * Método de acceso al campo sancion.
     * 
     * @return El valor del campo sancion
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sancion_id")
    public Documento getSancion() {
        return sancion;
    }
    /**
     * Asigna el valor al campo sancion.
     * 
     * @param sancion
     *            el valor sancion a asignar
     */
    public void setSancion(Documento sancion) {
        this.sancion = sancion;
    }
    /**
     * Método de acceso al campo respuestaCiudadana.
     * 
     * @return El valor del campo respuestaCiudadana
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RespuestaCiudadana_id")
    public Documento getRespuestaCiudadana() {
        return respuestaCiudadana;
    }
    /**
     * Asigna el valor al campo respuestaCiudadana.
     * 
     * @param respuestaCiudadana
     *            el valor respuestaCiudadana a asignar
     */
    public void setRespuestaCiudadana(Documento respuestaCiudadana) {
        this.respuestaCiudadana = respuestaCiudadana;
    }

}
