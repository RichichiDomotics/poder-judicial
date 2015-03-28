
package mx.gob.segob.nsjp.ws.cliente.enviarcarpetainvestigacion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for lugarWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lugarWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}elementoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inmediaciones" type="{http://ws.service.nsjp.segob.gob.mx/}inmediacionWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="latitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latitudGrados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latitudMinutos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latitudN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latitudSegundos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitudE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitudGrados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitudMinutos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitudSegundos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lugarWSDTO", propOrder = {
    "descripcion",
    "inmediaciones",
    "latitud",
    "latitudGrados",
    "latitudMinutos",
    "latitudN",
    "latitudSegundos",
    "longitud",
    "longitudE",
    "longitudGrados",
    "longitudMinutos",
    "longitudSegundos"
})
@XmlSeeAlso({
    DomicilioWSDTO.class
})
public class LugarWSDTO
    extends ElementoWSDTO
{

    protected String descripcion;
    @XmlElement(nillable = true)
    protected List<InmediacionWSDTO> inmediaciones;
    protected String latitud;
    protected String latitudGrados;
    protected String latitudMinutos;
    protected String latitudN;
    protected String latitudSegundos;
    protected String longitud;
    protected String longitudE;
    protected String longitudGrados;
    protected String longitudMinutos;
    protected String longitudSegundos;

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the inmediaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inmediaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInmediaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InmediacionWSDTO }
     * 
     * 
     */
    public List<InmediacionWSDTO> getInmediaciones() {
        if (inmediaciones == null) {
            inmediaciones = new ArrayList<InmediacionWSDTO>();
        }
        return this.inmediaciones;
    }

    /**
     * Gets the value of the latitud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * Sets the value of the latitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitud(String value) {
        this.latitud = value;
    }

    /**
     * Gets the value of the latitudGrados property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitudGrados() {
        return latitudGrados;
    }

    /**
     * Sets the value of the latitudGrados property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitudGrados(String value) {
        this.latitudGrados = value;
    }

    /**
     * Gets the value of the latitudMinutos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitudMinutos() {
        return latitudMinutos;
    }

    /**
     * Sets the value of the latitudMinutos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitudMinutos(String value) {
        this.latitudMinutos = value;
    }

    /**
     * Gets the value of the latitudN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitudN() {
        return latitudN;
    }

    /**
     * Sets the value of the latitudN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitudN(String value) {
        this.latitudN = value;
    }

    /**
     * Gets the value of the latitudSegundos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitudSegundos() {
        return latitudSegundos;
    }

    /**
     * Sets the value of the latitudSegundos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitudSegundos(String value) {
        this.latitudSegundos = value;
    }

    /**
     * Gets the value of the longitud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * Sets the value of the longitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitud(String value) {
        this.longitud = value;
    }

    /**
     * Gets the value of the longitudE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitudE() {
        return longitudE;
    }

    /**
     * Sets the value of the longitudE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitudE(String value) {
        this.longitudE = value;
    }

    /**
     * Gets the value of the longitudGrados property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitudGrados() {
        return longitudGrados;
    }

    /**
     * Sets the value of the longitudGrados property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitudGrados(String value) {
        this.longitudGrados = value;
    }

    /**
     * Gets the value of the longitudMinutos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitudMinutos() {
        return longitudMinutos;
    }

    /**
     * Sets the value of the longitudMinutos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitudMinutos(String value) {
        this.longitudMinutos = value;
    }

    /**
     * Gets the value of the longitudSegundos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitudSegundos() {
        return longitudSegundos;
    }

    /**
     * Sets the value of the longitudSegundos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitudSegundos(String value) {
        this.longitudSegundos = value;
    }

}
