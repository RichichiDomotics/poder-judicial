
package mx.gob.segob.nsjp.ws.cliente.agendaraudienciajavs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consultar_ResolutivosResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "consultarResolutivosResult"
})
@XmlRootElement(name = "consultar_ResolutivosResponse")
public class ConsultarResolutivosResponse {

    @XmlElement(name = "consultar_ResolutivosResult")
    protected int consultarResolutivosResult;

    /**
     * Gets the value of the consultarResolutivosResult property.
     * 
     */
    public int getConsultarResolutivosResult() {
        return consultarResolutivosResult;
    }

    /**
     * Sets the value of the consultarResolutivosResult property.
     * 
     */
    public void setConsultarResolutivosResult(int value) {
        this.consultarResolutivosResult = value;
    }

}
