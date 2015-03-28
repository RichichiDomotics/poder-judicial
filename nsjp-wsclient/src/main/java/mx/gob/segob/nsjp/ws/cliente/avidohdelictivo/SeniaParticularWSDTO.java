
package mx.gob.segob.nsjp.ws.cliente.avidohdelictivo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for seniaParticularWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="seniaParticularWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="descripcionCicatrices" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionDefectosFisicos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionLunares" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionOtraSenia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionProtesis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionTatuajes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tieneCicatrices" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tieneDefectosFisicos" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tieneLunares" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tieneOtraSenia" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tieneProtesis" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tieneTatuajes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "seniaParticularWSDTO", propOrder = {
    "descripcionCicatrices",
    "descripcionDefectosFisicos",
    "descripcionLunares",
    "descripcionOtraSenia",
    "descripcionProtesis",
    "descripcionTatuajes",
    "tieneCicatrices",
    "tieneDefectosFisicos",
    "tieneLunares",
    "tieneOtraSenia",
    "tieneProtesis",
    "tieneTatuajes"
})
public class SeniaParticularWSDTO
    extends GenericWSDTO
{

    protected String descripcionCicatrices;
    protected String descripcionDefectosFisicos;
    protected String descripcionLunares;
    protected String descripcionOtraSenia;
    protected String descripcionProtesis;
    protected String descripcionTatuajes;
    protected Boolean tieneCicatrices;
    protected Boolean tieneDefectosFisicos;
    protected Boolean tieneLunares;
    protected Boolean tieneOtraSenia;
    protected Boolean tieneProtesis;
    protected Boolean tieneTatuajes;

    /**
     * Gets the value of the descripcionCicatrices property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionCicatrices() {
        return descripcionCicatrices;
    }

    /**
     * Sets the value of the descripcionCicatrices property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionCicatrices(String value) {
        this.descripcionCicatrices = value;
    }

    /**
     * Gets the value of the descripcionDefectosFisicos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionDefectosFisicos() {
        return descripcionDefectosFisicos;
    }

    /**
     * Sets the value of the descripcionDefectosFisicos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionDefectosFisicos(String value) {
        this.descripcionDefectosFisicos = value;
    }

    /**
     * Gets the value of the descripcionLunares property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionLunares() {
        return descripcionLunares;
    }

    /**
     * Sets the value of the descripcionLunares property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionLunares(String value) {
        this.descripcionLunares = value;
    }

    /**
     * Gets the value of the descripcionOtraSenia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionOtraSenia() {
        return descripcionOtraSenia;
    }

    /**
     * Sets the value of the descripcionOtraSenia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionOtraSenia(String value) {
        this.descripcionOtraSenia = value;
    }

    /**
     * Gets the value of the descripcionProtesis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionProtesis() {
        return descripcionProtesis;
    }

    /**
     * Sets the value of the descripcionProtesis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionProtesis(String value) {
        this.descripcionProtesis = value;
    }

    /**
     * Gets the value of the descripcionTatuajes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionTatuajes() {
        return descripcionTatuajes;
    }

    /**
     * Sets the value of the descripcionTatuajes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionTatuajes(String value) {
        this.descripcionTatuajes = value;
    }

    /**
     * Gets the value of the tieneCicatrices property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneCicatrices() {
        return tieneCicatrices;
    }

    /**
     * Sets the value of the tieneCicatrices property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneCicatrices(Boolean value) {
        this.tieneCicatrices = value;
    }

    /**
     * Gets the value of the tieneDefectosFisicos property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneDefectosFisicos() {
        return tieneDefectosFisicos;
    }

    /**
     * Sets the value of the tieneDefectosFisicos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneDefectosFisicos(Boolean value) {
        this.tieneDefectosFisicos = value;
    }

    /**
     * Gets the value of the tieneLunares property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneLunares() {
        return tieneLunares;
    }

    /**
     * Sets the value of the tieneLunares property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneLunares(Boolean value) {
        this.tieneLunares = value;
    }

    /**
     * Gets the value of the tieneOtraSenia property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneOtraSenia() {
        return tieneOtraSenia;
    }

    /**
     * Sets the value of the tieneOtraSenia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneOtraSenia(Boolean value) {
        this.tieneOtraSenia = value;
    }

    /**
     * Gets the value of the tieneProtesis property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneProtesis() {
        return tieneProtesis;
    }

    /**
     * Sets the value of the tieneProtesis property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneProtesis(Boolean value) {
        this.tieneProtesis = value;
    }

    /**
     * Gets the value of the tieneTatuajes property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneTatuajes() {
        return tieneTatuajes;
    }

    /**
     * Sets the value of the tieneTatuajes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneTatuajes(Boolean value) {
        this.tieneTatuajes = value;
    }

}
