
package mx.gob.segob.nsjp.ws.cliente.solicituddefensor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registrarSolicitudDefensorResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registrarSolicitudDefensorResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ws.service.nsjp.segob.gob.mx/}solicitudDefensorWSDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrarSolicitudDefensorResponse", propOrder = {
    "_return"
})
public class RegistrarSolicitudDefensorResponse {

    @XmlElement(name = "return")
    protected SolicitudDefensorWSDTO _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudDefensorWSDTO }
     *     
     */
    public SolicitudDefensorWSDTO getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudDefensorWSDTO }
     *     
     */
    public void setReturn(SolicitudDefensorWSDTO value) {
        this._return = value;
    }

}
