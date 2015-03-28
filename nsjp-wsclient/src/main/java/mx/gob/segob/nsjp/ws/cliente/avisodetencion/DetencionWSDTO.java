
package mx.gob.segob.nsjp.ws.cliente.avisodetencion;

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
 *         &lt;element name="apellidoMaternoDetenido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellidoPaternoDetenido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="detenido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaFinDetencion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaInicioDetencion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaRecepcionDetecion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="folioElemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lugarDetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lugarDetencionProvisional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivoDetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreDetenido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "apellidoMaternoDetenido",
    "apellidoPaternoDetenido",
    "detenido",
    "fechaFinDetencion",
    "fechaInicioDetencion",
    "fechaRecepcionDetecion",
    "folioElemento",
    "lugarDetencion",
    "lugarDetencionProvisional",
    "motivoDetencion",
    "nombreDetenido",
    "observaciones"
})
public class DetencionWSDTO
    extends GenericWSDTO
{

    protected String apellidoMaternoDetenido;
    protected String apellidoPaternoDetenido;
    protected String detenido;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaFinDetencion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicioDetencion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRecepcionDetecion;
    protected String folioElemento;
    protected String lugarDetencion;
    protected String lugarDetencionProvisional;
    protected String motivoDetencion;
    protected String nombreDetenido;
    protected String observaciones;

    /**
     * Gets the value of the apellidoMaternoDetenido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoMaternoDetenido() {
        return apellidoMaternoDetenido;
    }

    /**
     * Sets the value of the apellidoMaternoDetenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoMaternoDetenido(String value) {
        this.apellidoMaternoDetenido = value;
    }

    /**
     * Gets the value of the apellidoPaternoDetenido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoPaternoDetenido() {
        return apellidoPaternoDetenido;
    }

    /**
     * Sets the value of the apellidoPaternoDetenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoPaternoDetenido(String value) {
        this.apellidoPaternoDetenido = value;
    }

    /**
     * Gets the value of the detenido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetenido() {
        return detenido;
    }

    /**
     * Sets the value of the detenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetenido(String value) {
        this.detenido = value;
    }

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
     * Gets the value of the fechaRecepcionDetecion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRecepcionDetecion() {
        return fechaRecepcionDetecion;
    }

    /**
     * Sets the value of the fechaRecepcionDetecion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRecepcionDetecion(XMLGregorianCalendar value) {
        this.fechaRecepcionDetecion = value;
    }

    /**
     * Gets the value of the folioElemento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioElemento() {
        return folioElemento;
    }

    /**
     * Sets the value of the folioElemento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioElemento(String value) {
        this.folioElemento = value;
    }

    /**
     * Gets the value of the lugarDetencion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarDetencion() {
        return lugarDetencion;
    }

    /**
     * Sets the value of the lugarDetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarDetencion(String value) {
        this.lugarDetencion = value;
    }

    /**
     * Gets the value of the lugarDetencionProvisional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarDetencionProvisional() {
        return lugarDetencionProvisional;
    }

    /**
     * Sets the value of the lugarDetencionProvisional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarDetencionProvisional(String value) {
        this.lugarDetencionProvisional = value;
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
     * Gets the value of the nombreDetenido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreDetenido() {
        return nombreDetenido;
    }

    /**
     * Sets the value of the nombreDetenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreDetenido(String value) {
        this.nombreDetenido = value;
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

}
