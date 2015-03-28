
package mx.gob.segob.nsjp.ws.cliente.solicitudtranscripcion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registrarSolicitudTranscripcion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registrarSolicitudTranscripcion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://ws.service.nsjp.segob.gob.mx/}solicitudTranscripcionWSDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrarSolicitudTranscripcion", propOrder = {
    "arg0"
})
public class RegistrarSolicitudTranscripcion {

    protected SolicitudTranscripcionWSDTO arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudTranscripcionWSDTO }
     *     
     */
    public SolicitudTranscripcionWSDTO getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudTranscripcionWSDTO }
     *     
     */
    public void setArg0(SolicitudTranscripcionWSDTO value) {
        this.arg0 = value;
    }

}
