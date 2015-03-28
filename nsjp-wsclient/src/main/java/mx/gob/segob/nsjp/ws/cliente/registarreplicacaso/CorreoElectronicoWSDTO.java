
package mx.gob.segob.nsjp.ws.cliente.registarreplicacaso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for correoElectronicoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="correoElectronicoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="direccionElectronica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "correoElectronicoWSDTO", propOrder = {
    "direccionElectronica"
})
public class CorreoElectronicoWSDTO
    extends GenericWSDTO
{

    protected String direccionElectronica;

    /**
     * Gets the value of the direccionElectronica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionElectronica() {
        return direccionElectronica;
    }

    /**
     * Sets the value of the direccionElectronica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionElectronica(String value) {
        this.direccionElectronica = value;
    }

}
