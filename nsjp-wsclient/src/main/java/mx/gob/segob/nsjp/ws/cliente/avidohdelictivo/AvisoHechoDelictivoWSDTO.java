
package mx.gob.segob.nsjp.ws.cliente.avidohdelictivo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for avisoHechoDelictivoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="avisoHechoDelictivoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}notificacionWSDTO">
 *       &lt;sequence>
 *         &lt;element name="catDelitoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="esAnonimo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="fechaAtencion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="hecho" type="{http://ws.service.nsjp.segob.gob.mx/}hechoWSDTO" minOccurs="0"/>
 *         &lt;element name="implicado" type="{http://ws.service.nsjp.segob.gob.mx/}involucradoWSDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "avisoHechoDelictivoWSDTO", propOrder = {
    "catDelitoId",
    "esAnonimo",
    "fechaAtencion",
    "hecho",
    "implicado"
})
public class AvisoHechoDelictivoWSDTO
    extends NotificacionWSDTO
{

    protected Long catDelitoId;
    protected Boolean esAnonimo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAtencion;
    protected HechoWSDTO hecho;
    protected InvolucradoWSDTO implicado;

    /**
     * Gets the value of the catDelitoId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCatDelitoId() {
        return catDelitoId;
    }

    /**
     * Sets the value of the catDelitoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCatDelitoId(Long value) {
        this.catDelitoId = value;
    }

    /**
     * Gets the value of the esAnonimo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsAnonimo() {
        return esAnonimo;
    }

    /**
     * Sets the value of the esAnonimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsAnonimo(Boolean value) {
        this.esAnonimo = value;
    }

    /**
     * Gets the value of the fechaAtencion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAtencion() {
        return fechaAtencion;
    }

    /**
     * Sets the value of the fechaAtencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAtencion(XMLGregorianCalendar value) {
        this.fechaAtencion = value;
    }

    /**
     * Gets the value of the hecho property.
     * 
     * @return
     *     possible object is
     *     {@link HechoWSDTO }
     *     
     */
    public HechoWSDTO getHecho() {
        return hecho;
    }

    /**
     * Sets the value of the hecho property.
     * 
     * @param value
     *     allowed object is
     *     {@link HechoWSDTO }
     *     
     */
    public void setHecho(HechoWSDTO value) {
        this.hecho = value;
    }

    /**
     * Gets the value of the implicado property.
     * 
     * @return
     *     possible object is
     *     {@link InvolucradoWSDTO }
     *     
     */
    public InvolucradoWSDTO getImplicado() {
        return implicado;
    }

    /**
     * Sets the value of the implicado property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvolucradoWSDTO }
     *     
     */
    public void setImplicado(InvolucradoWSDTO value) {
        this.implicado = value;
    }

}
