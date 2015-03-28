
package mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for numerarioWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="numerarioWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}objetoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ctaTesoreria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaDeposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "numerarioWSDTO", propOrder = {
    "cantidad",
    "ctaTesoreria",
    "fechaDeposito",
    "moneda"
})
public class NumerarioWSDTO
    extends ObjetoWSDTO
{

    protected Long cantidad;
    protected String ctaTesoreria;
    protected String fechaDeposito;
    protected String moneda;

    /**
     * Gets the value of the cantidad property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCantidad() {
        return cantidad;
    }

    /**
     * Sets the value of the cantidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCantidad(Long value) {
        this.cantidad = value;
    }

    /**
     * Gets the value of the ctaTesoreria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCtaTesoreria() {
        return ctaTesoreria;
    }

    /**
     * Sets the value of the ctaTesoreria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCtaTesoreria(String value) {
        this.ctaTesoreria = value;
    }

    /**
     * Gets the value of the fechaDeposito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDeposito() {
        return fechaDeposito;
    }

    /**
     * Sets the value of the fechaDeposito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDeposito(String value) {
        this.fechaDeposito = value;
    }

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
    }

}
