
package mx.gob.segob.nsjp.ws.cliente.consultaraudiencia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for involucradoAudienciaDefWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="involucradoAudienciaDefWSDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calidad" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="delito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="detenido" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "involucradoAudienciaDefWSDTO", propOrder = {
    "calidad",
    "delito",
    "detenido",
    "nombre"
})
public class InvolucradoAudienciaDefWSDTO {

    protected Long calidad;
    protected String delito;
    protected Boolean detenido;
    protected String nombre;

    /**
     * Gets the value of the calidad property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCalidad() {
        return calidad;
    }

    /**
     * Sets the value of the calidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCalidad(Long value) {
        this.calidad = value;
    }

    /**
     * Gets the value of the delito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelito() {
        return delito;
    }

    /**
     * Sets the value of the delito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelito(String value) {
        this.delito = value;
    }

    /**
     * Gets the value of the detenido property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDetenido() {
        return detenido;
    }

    /**
     * Sets the value of the detenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDetenido(Boolean value) {
        this.detenido = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

}
