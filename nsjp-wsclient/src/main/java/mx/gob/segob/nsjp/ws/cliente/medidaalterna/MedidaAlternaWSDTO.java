
package mx.gob.segob.nsjp.ws.cliente.medidaalterna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for medidaAlternaWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="medidaAlternaWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}medidaWSDTO">
 *       &lt;sequence>
 *         &lt;element name="anios" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="meses" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "medidaAlternaWSDTO", propOrder = {
    "anios",
    "meses"
})
public class MedidaAlternaWSDTO
    extends MedidaWSDTO
{

    protected Short anios;
    protected Short meses;

    /**
     * Gets the value of the anios property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getAnios() {
        return anios;
    }

    /**
     * Sets the value of the anios property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setAnios(Short value) {
        this.anios = value;
    }

    /**
     * Gets the value of the meses property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getMeses() {
        return meses;
    }

    /**
     * Sets the value of the meses property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setMeses(Short value) {
        this.meses = value;
    }

}
