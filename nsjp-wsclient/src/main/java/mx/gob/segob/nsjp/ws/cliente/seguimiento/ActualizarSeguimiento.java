
package mx.gob.segob.nsjp.ws.cliente.seguimiento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for actualizarSeguimiento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="actualizarSeguimiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://ws.service.nsjp.segob.gob.mx/}seguimientoMandamientoMedidaWSDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actualizarSeguimiento", propOrder = {
    "arg0"
})
public class ActualizarSeguimiento {

    protected SeguimientoMandamientoMedidaWSDTO arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link SeguimientoMandamientoMedidaWSDTO }
     *     
     */
    public SeguimientoMandamientoMedidaWSDTO getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeguimientoMandamientoMedidaWSDTO }
     *     
     */
    public void setArg0(SeguimientoMandamientoMedidaWSDTO value) {
        this.arg0 = value;
    }

}
