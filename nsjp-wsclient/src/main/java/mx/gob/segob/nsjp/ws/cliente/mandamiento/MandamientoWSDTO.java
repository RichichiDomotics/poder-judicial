
package mx.gob.segob.nsjp.ws.cliente.mandamiento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mandamientoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mandamientoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}documentoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="idEstatus" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idTipoMandamiento" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mandamientoWSDTO", propOrder = {
    "idEstatus",
    "idTipoMandamiento"
})
public class MandamientoWSDTO
    extends DocumentoWSDTO
{

    protected Long idEstatus;
    protected Long idTipoMandamiento;

    /**
     * Gets the value of the idEstatus property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdEstatus() {
        return idEstatus;
    }

    /**
     * Sets the value of the idEstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdEstatus(Long value) {
        this.idEstatus = value;
    }

    /**
     * Gets the value of the idTipoMandamiento property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTipoMandamiento() {
        return idTipoMandamiento;
    }

    /**
     * Sets the value of the idTipoMandamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTipoMandamiento(Long value) {
        this.idTipoMandamiento = value;
    }

}
