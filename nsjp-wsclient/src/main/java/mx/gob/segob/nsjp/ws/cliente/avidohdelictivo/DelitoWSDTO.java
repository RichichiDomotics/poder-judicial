
package mx.gob.segob.nsjp.ws.cliente.avidohdelictivo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for delitoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="delitoWSDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="claveDelito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="esGrave" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="esPrincipal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="esProbable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="idCatDelito" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nombreDelito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delitoWSDTO", propOrder = {
    "claveDelito",
    "esGrave",
    "esPrincipal",
    "esProbable",
    "idCatDelito",
    "nombreDelito"
})
public class DelitoWSDTO {

    protected String claveDelito;
    protected Boolean esGrave;
    protected Boolean esPrincipal;
    protected Boolean esProbable;
    protected Long idCatDelito;
    protected String nombreDelito;

    /**
     * Gets the value of the claveDelito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveDelito() {
        return claveDelito;
    }

    /**
     * Sets the value of the claveDelito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveDelito(String value) {
        this.claveDelito = value;
    }

    /**
     * Gets the value of the esGrave property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsGrave() {
        return esGrave;
    }

    /**
     * Sets the value of the esGrave property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsGrave(Boolean value) {
        this.esGrave = value;
    }

    /**
     * Gets the value of the esPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsPrincipal() {
        return esPrincipal;
    }

    /**
     * Sets the value of the esPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsPrincipal(Boolean value) {
        this.esPrincipal = value;
    }

    /**
     * Gets the value of the esProbable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsProbable() {
        return esProbable;
    }

    /**
     * Sets the value of the esProbable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsProbable(Boolean value) {
        this.esProbable = value;
    }

    /**
     * Gets the value of the idCatDelito property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCatDelito() {
        return idCatDelito;
    }

    /**
     * Sets the value of the idCatDelito property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCatDelito(Long value) {
        this.idCatDelito = value;
    }

    /**
     * Gets the value of the nombreDelito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreDelito() {
        return nombreDelito;
    }

    /**
     * Sets the value of the nombreDelito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreDelito(String value) {
        this.nombreDelito = value;
    }

}
