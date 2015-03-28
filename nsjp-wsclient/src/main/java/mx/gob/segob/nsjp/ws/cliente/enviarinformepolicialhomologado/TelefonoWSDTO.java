
package mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for telefonoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="telefonoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="codigoArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroTelefonico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorTipoTelefonoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "telefonoWSDTO", propOrder = {
    "codigoArea",
    "codigoPais",
    "numeroTelefonico",
    "valorTipoTelefonoId"
})
public class TelefonoWSDTO
    extends GenericWSDTO
{

    protected String codigoArea;
    protected String codigoPais;
    protected String numeroTelefonico;
    protected Long valorTipoTelefonoId;

    /**
     * Gets the value of the codigoArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoArea() {
        return codigoArea;
    }

    /**
     * Sets the value of the codigoArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoArea(String value) {
        this.codigoArea = value;
    }

    /**
     * Gets the value of the codigoPais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPais() {
        return codigoPais;
    }

    /**
     * Sets the value of the codigoPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPais(String value) {
        this.codigoPais = value;
    }

    /**
     * Gets the value of the numeroTelefonico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    /**
     * Sets the value of the numeroTelefonico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTelefonico(String value) {
        this.numeroTelefonico = value;
    }

    /**
     * Gets the value of the valorTipoTelefonoId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorTipoTelefonoId() {
        return valorTipoTelefonoId;
    }

    /**
     * Sets the value of the valorTipoTelefonoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorTipoTelefonoId(Long value) {
        this.valorTipoTelefonoId = value;
    }

}
