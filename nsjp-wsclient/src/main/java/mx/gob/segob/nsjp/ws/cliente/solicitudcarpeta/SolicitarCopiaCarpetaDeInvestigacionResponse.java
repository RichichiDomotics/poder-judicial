
package mx.gob.segob.nsjp.ws.cliente.solicitudcarpeta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for solicitarCopiaCarpetaDeInvestigacionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="solicitarCopiaCarpetaDeInvestigacionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ws.service.nsjp.segob.gob.mx/}solicitudAudienciaBasicoWSDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitarCopiaCarpetaDeInvestigacionResponse", propOrder = {
    "_return"
})
public class SolicitarCopiaCarpetaDeInvestigacionResponse {

    @XmlElement(name = "return")
    protected SolicitudAudienciaBasicoWSDTO _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudAudienciaBasicoWSDTO }
     *     
     */
    public SolicitudAudienciaBasicoWSDTO getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudAudienciaBasicoWSDTO }
     *     
     */
    public void setReturn(SolicitudAudienciaBasicoWSDTO value) {
        this._return = value;
    }

}
