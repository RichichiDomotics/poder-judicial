
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
 *         &lt;element name="S_Audiencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="S_Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sAudiencia",
    "sUsuario"
})
@XmlRootElement(name = "consultar_Audiencia")
public class ConsultarAudiencia {

    @XmlElement(name = "S_Audiencia")
    protected String sAudiencia;
    @XmlElement(name = "S_Usuario")
    protected String sUsuario;

    /**
     * Gets the value of the sAudiencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSAudiencia() {
        return sAudiencia;
    }

    /**
     * Sets the value of the sAudiencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSAudiencia(String value) {
        this.sAudiencia = value;
    }

    /**
     * Gets the value of the sUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUsuario() {
        return sUsuario;
    }

    /**
     * Sets the value of the sUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUsuario(String value) {
        this.sUsuario = value;
    }

}
