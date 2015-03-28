
package mx.gob.segob.nsjp.ws.cliente.solicitudcarpeta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for cadenaDeCustodiaWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cadenaDeCustodiaWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="cadenaDeCustodiaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="expediente" type="{http://ws.service.nsjp.segob.gob.mx/}expedienteWSDTO" minOccurs="0"/>
 *         &lt;element name="fechaIntercambio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaLevantamiento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="folio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quienEmbala" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quienEntrega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quienRecibe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quienTransporta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cadenaDeCustodiaWSDTO", propOrder = {
    "cadenaDeCustodiaId",
    "expediente",
    "fechaIntercambio",
    "fechaLevantamiento",
    "folio",
    "observacion",
    "quienEmbala",
    "quienEntrega",
    "quienRecibe",
    "quienTransporta"
})
public class CadenaDeCustodiaWSDTO
    extends GenericWSDTO
{

    protected Long cadenaDeCustodiaId;
    protected ExpedienteWSDTO expediente;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaIntercambio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaLevantamiento;
    protected String folio;
    protected String observacion;
    protected String quienEmbala;
    protected String quienEntrega;
    protected String quienRecibe;
    protected String quienTransporta;

    /**
     * Gets the value of the cadenaDeCustodiaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCadenaDeCustodiaId() {
        return cadenaDeCustodiaId;
    }

    /**
     * Sets the value of the cadenaDeCustodiaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCadenaDeCustodiaId(Long value) {
        this.cadenaDeCustodiaId = value;
    }

    /**
     * Gets the value of the expediente property.
     * 
     * @return
     *     possible object is
     *     {@link ExpedienteWSDTO }
     *     
     */
    public ExpedienteWSDTO getExpediente() {
        return expediente;
    }

    /**
     * Sets the value of the expediente property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpedienteWSDTO }
     *     
     */
    public void setExpediente(ExpedienteWSDTO value) {
        this.expediente = value;
    }

    /**
     * Gets the value of the fechaIntercambio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaIntercambio() {
        return fechaIntercambio;
    }

    /**
     * Sets the value of the fechaIntercambio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaIntercambio(XMLGregorianCalendar value) {
        this.fechaIntercambio = value;
    }

    /**
     * Gets the value of the fechaLevantamiento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaLevantamiento() {
        return fechaLevantamiento;
    }

    /**
     * Sets the value of the fechaLevantamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaLevantamiento(XMLGregorianCalendar value) {
        this.fechaLevantamiento = value;
    }

    /**
     * Gets the value of the folio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Sets the value of the folio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolio(String value) {
        this.folio = value;
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
     * Gets the value of the quienEmbala property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuienEmbala() {
        return quienEmbala;
    }

    /**
     * Sets the value of the quienEmbala property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuienEmbala(String value) {
        this.quienEmbala = value;
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
     * Gets the value of the quienTransporta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuienTransporta() {
        return quienTransporta;
    }

    /**
     * Sets the value of the quienTransporta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuienTransporta(String value) {
        this.quienTransporta = value;
    }

}
