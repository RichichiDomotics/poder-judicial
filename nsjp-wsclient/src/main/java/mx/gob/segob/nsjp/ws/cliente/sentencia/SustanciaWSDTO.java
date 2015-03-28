
package mx.gob.segob.nsjp.ws.cliente.sentencia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sustanciaWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sustanciaWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.ws.service.nsjp.segob.gob.mx/}objetoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="empaque" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipoSustancia" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="unidadMedida" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sustanciaWSDTO", propOrder = {
    "cantidad",
    "empaque",
    "tipoSustancia",
    "unidadMedida"
})
public class SustanciaWSDTO
    extends ObjetoWSDTO
{

    protected Short cantidad;
    protected Long empaque;
    protected Long tipoSustancia;
    protected Long unidadMedida;

    /**
     * Gets the value of the cantidad property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getCantidad() {
        return cantidad;
    }

    /**
     * Sets the value of the cantidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setCantidad(Short value) {
        this.cantidad = value;
    }

    /**
     * Gets the value of the empaque property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEmpaque() {
        return empaque;
    }

    /**
     * Sets the value of the empaque property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEmpaque(Long value) {
        this.empaque = value;
    }

    /**
     * Gets the value of the tipoSustancia property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoSustancia() {
        return tipoSustancia;
    }

    /**
     * Sets the value of the tipoSustancia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoSustancia(Long value) {
        this.tipoSustancia = value;
    }

    /**
     * Gets the value of the unidadMedida property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUnidadMedida() {
        return unidadMedida;
    }

    /**
     * Sets the value of the unidadMedida property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUnidadMedida(Long value) {
        this.unidadMedida = value;
    }

}
