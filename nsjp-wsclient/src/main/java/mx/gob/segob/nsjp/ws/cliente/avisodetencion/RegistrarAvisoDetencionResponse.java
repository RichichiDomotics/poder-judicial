
package mx.gob.segob.nsjp.ws.cliente.avisodetencion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registrarAvisoDetencionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registrarAvisoDetencionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ws.service.nsjp.segob.gob.mx/}avisoDetencionWSDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrarAvisoDetencionResponse", propOrder = {
    "_return"
})
public class RegistrarAvisoDetencionResponse {

    @XmlElement(name = "return")
    protected AvisoDetencionWSDTO _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link AvisoDetencionWSDTO }
     *     
     */
    public AvisoDetencionWSDTO getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link AvisoDetencionWSDTO }
     *     
     */
    public void setReturn(AvisoDetencionWSDTO value) {
        this._return = value;
    }

}
