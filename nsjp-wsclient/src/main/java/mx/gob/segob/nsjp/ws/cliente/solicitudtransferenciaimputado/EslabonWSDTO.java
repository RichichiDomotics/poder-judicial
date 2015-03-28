
package mx.gob.segob.nsjp.ws.cliente.solicitudtransferenciaimputado;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for eslabonWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eslabonWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="documento" type="{http://ws.service.nsjp.segob.gob.mx/}documentoWSDTO" minOccurs="0"/>
 *         &lt;element name="evidencia" type="{http://ws.service.nsjp.segob.gob.mx/}evidenciaWSDTO" minOccurs="0"/>
 *         &lt;element name="fechaEntrega" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaRecibe" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idTpoEslabon" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="numeroEslabon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="observacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="posicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quienEntrega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quienRecibe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ubicacionFisica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eslabonWSDTO", propOrder = {
    "documento",
    "evidencia",
    "fechaEntrega",
    "fechaRecibe",
    "idTpoEslabon",
    "numeroEslabon",
    "observacion",
    "posicion",
    "quienEntrega",
    "quienRecibe",
    "ubicacionFisica"
})
public class EslabonWSDTO
    extends GenericWSDTO
{

    protected DocumentoWSDTO documento;
    protected EvidenciaWSDTO evidencia;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaEntrega;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRecibe;
    protected Long idTpoEslabon;
    protected Integer numeroEslabon;
    protected String observacion;
    protected String posicion;
    protected String quienEntrega;
    protected String quienRecibe;
    protected String ubicacionFisica;

    /**
     * Gets the value of the documento property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoWSDTO }
     *     
     */
    public DocumentoWSDTO getDocumento() {
        return documento;
    }

    /**
     * Sets the value of the documento property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoWSDTO }
     *     
     */
    public void setDocumento(DocumentoWSDTO value) {
        this.documento = value;
    }

    /**
     * Gets the value of the evidencia property.
     * 
     * @return
     *     possible object is
     *     {@link EvidenciaWSDTO }
     *     
     */
    public EvidenciaWSDTO getEvidencia() {
        return evidencia;
    }

    /**
     * Sets the value of the evidencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link EvidenciaWSDTO }
     *     
     */
    public void setEvidencia(EvidenciaWSDTO value) {
        this.evidencia = value;
    }

    /**
     * Gets the value of the fechaEntrega property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * Sets the value of the fechaEntrega property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEntrega(XMLGregorianCalendar value) {
        this.fechaEntrega = value;
    }

    /**
     * Gets the value of the fechaRecibe property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRecibe() {
        return fechaRecibe;
    }

    /**
     * Sets the value of the fechaRecibe property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRecibe(XMLGregorianCalendar value) {
        this.fechaRecibe = value;
    }

    /**
     * Gets the value of the idTpoEslabon property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTpoEslabon() {
        return idTpoEslabon;
    }

    /**
     * Sets the value of the idTpoEslabon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTpoEslabon(Long value) {
        this.idTpoEslabon = value;
    }

    /**
     * Gets the value of the numeroEslabon property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroEslabon() {
        return numeroEslabon;
    }

    /**
     * Sets the value of the numeroEslabon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroEslabon(Integer value) {
        this.numeroEslabon = value;
    }

    /**
     * Gets the value of the observacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Sets the value of the observacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacion(String value) {
        this.observacion = value;
    }

    /**
     * Gets the value of the posicion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosicion() {
        return posicion;
    }

    /**
     * Sets the value of the posicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosicion(String value) {
        this.posicion = value;
    }

    /**
     * Gets the value of the quienEntrega property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuienEntrega() {
        return quienEntrega;
    }

    /**
     * Sets the value of the quienEntrega property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuienEntrega(String value) {
        this.quienEntrega = value;
    }

    /**
     * Gets the value of the quienRecibe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuienRecibe() {
        return quienRecibe;
    }

    /**
     * Sets the value of the quienRecibe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuienRecibe(String value) {
        this.quienRecibe = value;
    }

    /**
     * Gets the value of the ubicacionFisica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUbicacionFisica() {
        return ubicacionFisica;
    }

    /**
     * Sets the value of the ubicacionFisica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUbicacionFisica(String value) {
        this.ubicacionFisica = value;
    }

}
