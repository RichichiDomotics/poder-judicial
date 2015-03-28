
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
 *         &lt;element name="agendar_AudienciaResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "agendarAudienciaResult"
})
@XmlRootElement(name = "agendar_AudienciaResponse")
public class AgendarAudienciaResponse {

    @XmlElement(name = "agendar_AudienciaResult")
    protected int agendarAudienciaResult;

    /**
     * Gets the value of the agendarAudienciaResult property.
     * 
     */
    public int getAgendarAudienciaResult() {
        return agendarAudienciaResult;
    }

    /**
     * Sets the value of the agendarAudienciaResult property.
     * 
     */
    public void setAgendarAudienciaResult(int value) {
        this.agendarAudienciaResult = value;
    }

}
