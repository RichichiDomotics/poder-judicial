
package mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for animalWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="animalWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}objetoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="estadoAnimal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="razaAnimal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoAnimal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "animalWSDTO", propOrder = {
    "estadoAnimal",
    "razaAnimal",
    "tipoAnimal"
})
public class AnimalWSDTO
    extends ObjetoWSDTO
{

    protected String estadoAnimal;
    protected String razaAnimal;
    protected Long tipoAnimal;

    /**
     * Gets the value of the estadoAnimal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoAnimal() {
        return estadoAnimal;
    }

    /**
     * Sets the value of the estadoAnimal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoAnimal(String value) {
        this.estadoAnimal = value;
    }

    /**
     * Gets the value of the razaAnimal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazaAnimal() {
        return razaAnimal;
    }

    /**
     * Sets the value of the razaAnimal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazaAnimal(String value) {
        this.razaAnimal = value;
    }

    /**
     * Gets the value of the tipoAnimal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoAnimal() {
        return tipoAnimal;
    }

    /**
     * Sets the value of the tipoAnimal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoAnimal(Long value) {
        this.tipoAnimal = value;
    }

}
