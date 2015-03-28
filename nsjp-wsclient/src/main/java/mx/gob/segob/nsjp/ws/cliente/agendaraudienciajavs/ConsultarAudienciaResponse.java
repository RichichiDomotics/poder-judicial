
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
 *         &lt;element name="consultar_AudienciaResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "consultarAudienciaResult"
})
@XmlRootElement(name = "consultar_AudienciaResponse")
public class ConsultarAudienciaResponse {

    @XmlElement(name = "consultar_AudienciaResult")
    protected int consultarAudienciaResult;

    /**
     * Gets the value of the consultarAudienciaResult property.
     * 
     */
    public int getConsultarAudienciaResult() {
        return consultarAudienciaResult;
    }

    /**
     * Sets the value of the consultarAudienciaResult property.
     * 
     */
    public void setConsultarAudienciaResult(int value) {
        this.consultarAudienciaResult = value;
    }

}
