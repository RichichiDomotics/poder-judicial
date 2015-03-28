
package mx.gob.segob.nsjp.ws.cliente.avidohdelictivo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for calidadWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="calidadWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="calidades" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="descripcionEstadoFisico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorIdCalidad" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calidadWSDTO", propOrder = {
    "calidades",
    "descripcionEstadoFisico",
    "valorIdCalidad"
})
public class CalidadWSDTO
    extends GenericWSDTO
{

    protected Long calidades;
    protected String descripcionEstadoFisico;
    protected Long valorIdCalidad;

    /**
     * Gets the value of the calidades property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCalidades() {
        return calidades;
    }

    /**
     * Sets the value of the calidades property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCalidades(Long value) {
        this.calidades = value;
    }

    /**
     * Gets the value of the descripcionEstadoFisico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionEstadoFisico() {
        return descripcionEstadoFisico;
    }

    /**
     * Sets the value of the descripcionEstadoFisico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionEstadoFisico(String value) {
        this.descripcionEstadoFisico = value;
    }

    /**
     * Gets the value of the valorIdCalidad property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorIdCalidad() {
        return valorIdCalidad;
    }

    /**
     * Sets the value of the valorIdCalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorIdCalidad(Long value) {
        this.valorIdCalidad = value;
    }

}
