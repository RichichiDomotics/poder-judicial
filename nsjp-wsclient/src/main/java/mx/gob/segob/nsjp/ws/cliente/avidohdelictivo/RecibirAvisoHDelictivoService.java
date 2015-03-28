
package mx.gob.segob.nsjp.ws.cliente.avidohdelictivo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recibirAvisoHDelictivoService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="recibirAvisoHDelictivoService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://ws.service.nsjp.segob.gob.mx/}avisoHechoDelictivoWSDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recibirAvisoHDelictivoService", propOrder = {
    "arg0"
})
public class RecibirAvisoHDelictivoService {

    protected AvisoHechoDelictivoWSDTO arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link AvisoHechoDelictivoWSDTO }
     *     
     */
    public AvisoHechoDelictivoWSDTO getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link AvisoHechoDelictivoWSDTO }
     *     
     */
    public void setArg0(AvisoHechoDelictivoWSDTO value) {
        this.arg0 = value;
    }

}
