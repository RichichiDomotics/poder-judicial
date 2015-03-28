
package mx.gob.segob.nsjp.ws.cliente.enviardocumentoincumplimientomedida;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for genericWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="genericWSDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confInstitucionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "genericWSDTO", propOrder = {
    "confInstitucionId"
})
@XmlSeeAlso({
    DocumentoWSDTO.class,
    ArchivoDigitalWSDTO.class
})
public class GenericWSDTO {

    protected Long confInstitucionId;

    /**
     * Gets the value of the confInstitucionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getConfInstitucionId() {
        return confInstitucionId;
    }

    /**
     * Sets the value of the confInstitucionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setConfInstitucionId(Long value) {
        this.confInstitucionId = value;
    }

}
