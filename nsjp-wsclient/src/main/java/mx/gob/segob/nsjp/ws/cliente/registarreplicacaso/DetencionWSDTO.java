
package mx.gob.segob.nsjp.ws.cliente.registarreplicacaso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for detencionWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detencionWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="fechaFinDetencion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaInicioDetencion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaRecepcionDetencion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="horaFinDetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horaInicioDetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horaRecepcionDetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lugarDetencion" type="{http://ws.service.nsjp.segob.gob.mx/}lugarWSDTO" minOccurs="0"/>
 *         &lt;element name="lugarDetencionProvicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivoDetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strFechaFinDetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strFechaInicioDetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strFechaRecepcionDetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detencionWSDTO", propOrder = {
    "fechaFinDetencion",
    "fechaInicioDetencion",
    "fechaRecepcionDetencion",
    "horaFinDetencion",
    "horaInicioDetencion",
    "horaRecepcionDetencion",
    "lugarDetencion",
    "lugarDetencionProvicional",
    "motivoDetencion",
    "observaciones",
    "strFechaFinDetencion",
    "strFechaInicioDetencion",
    "strFechaRecepcionDetencion"
})
public class DetencionWSDTO
    extends GenericWSDTO
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaFinDetencion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicioDetencion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRecepcionDetencion;
    protected String horaFinDetencion;
    protected String horaInicioDetencion;
    protected String horaRecepcionDetencion;
    protected LugarWSDTO lugarDetencion;
    protected String lugarDetencionProvicional;
    protected String motivoDetencion;
    protected String observaciones;
    protected String strFechaFinDetencion;
    protected String strFechaInicioDetencion;
    protected String strFechaRecepcionDetencion;

    /**
     * Gets the value of the fechaFinDetencion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFinDetencion() {
        return fechaFinDetencion;
    }

    /**
     * Sets the value of the fechaFinDetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFinDetencion(XMLGregorianCalendar value) {
        this.fechaFinDetencion = value;
    }

    /**
     * Gets the value of the fechaInicioDetencion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicioDetencion() {
        return fechaInicioDetencion;
    }

    /**
     * Sets the value of the fechaInicioDetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioDetencion(XMLGregorianCalendar value) {
        this.fechaInicioDetencion = value;
    }

    /**
     * Gets the value of the fechaRecepcionDetencion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRecepcionDetencion() {
        return fechaRecepcionDetencion;
    }

    /**
     * Sets the value of the fechaRecepcionDetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRecepcionDetencion(XMLGregorianCalendar value) {
        this.fechaRecepcionDetencion = value;
    }

    /**
     * Gets the value of the horaFinDetencion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraFinDetencion() {
        return horaFinDetencion;
    }

    /**
     * Sets the value of the horaFinDetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraFinDetencion(String value) {
        this.horaFinDetencion = value;
    }

    /**
     * Gets the value of the horaInicioDetencion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraInicioDetencion() {
        return horaInicioDetencion;
    }

    /**
     * Sets the value of the horaInicioDetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraInicioDetencion(String value) {
        this.horaInicioDetencion = value;
    }

    /**
     * Gets the value of the horaRecepcionDetencion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraRecepcionDetencion() {
        return horaRecepcionDetencion;
    }

    /**
     * Sets the value of the horaRecepcionDetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraRecepcionDetencion(String value) {
        this.horaRecepcionDetencion = value;
    }

    /**
     * Gets the value of the lugarDetencion property.
     * 
     * @return
     *     possible object is
     *     {@link LugarWSDTO }
     *     
     */
    public LugarWSDTO getLugarDetencion() {
        return lugarDetencion;
    }

    /**
     * Sets the value of the lugarDetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link LugarWSDTO }
     *     
     */
    public void setLugarDetencion(LugarWSDTO value) {
        this.lugarDetencion = value;
    }

    /**
     * Gets the value of the lugarDetencionProvicional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarDetencionProvicional() {
        return lugarDetencionProvicional;
    }

    /**
     * Sets the value of the lugarDetencionProvicional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarDetencionProvicional(String value) {
        this.lugarDetencionProvicional = value;
    }

    /**
     * Gets the value of the motivoDetencion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoDetencion() {
        return motivoDetencion;
    }

    /**
     * Sets the value of the motivoDetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoDetencion(String value) {
        this.motivoDetencion = value;
    }

    /**
     * Gets the value of the observaciones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Sets the value of the observaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservaciones(String value) {
        this.observaciones = value;
    }

    /**
     * Gets the value of the strFechaFinDetencion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrFechaFinDetencion() {
        return strFechaFinDetencion;
    }

    /**
     * Sets the value of the strFechaFinDetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrFechaFinDetencion(String value) {
        this.strFechaFinDetencion = value;
    }

    /**
     * Gets the value of the strFechaInicioDetencion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrFechaInicioDetencion() {
        return strFechaInicioDetencion;
    }

    /**
     * Sets the value of the strFechaInicioDetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrFechaInicioDetencion(String value) {
        this.strFechaInicioDetencion = value;
    }

    /**
     * Gets the value of the strFechaRecepcionDetencion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrFechaRecepcionDetencion() {
        return strFechaRecepcionDetencion;
    }

    /**
     * Sets the value of the strFechaRecepcionDetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrFechaRecepcionDetencion(String value) {
        this.strFechaRecepcionDetencion = value;
    }

}
