
package mx.gob.segob.nsjp.ws.cliente.avisodetencion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for avisoDetencionWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="avisoDetencionWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="avisoDetencionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="delitos" type="{http://ws.service.nsjp.segob.gob.mx/}delitoWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="detencion" type="{http://ws.service.nsjp.segob.gob.mx/}detencionWSDTO" minOccurs="0"/>
 *         &lt;element name="estatusNotificacionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="folioNotificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCasoAsociado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "avisoDetencionWSDTO", propOrder = {
    "avisoDetencionId",
    "delitos",
    "detencion",
    "estatusNotificacionId",
    "folioNotificacion",
    "numeroCasoAsociado"
})
public class AvisoDetencionWSDTO
    extends GenericWSDTO
{

    protected Long avisoDetencionId;
    @XmlElement(nillable = true)
    protected List<DelitoWSDTO> delitos;
    protected DetencionWSDTO detencion;
    protected Long estatusNotificacionId;
    protected String folioNotificacion;
    protected String numeroCasoAsociado;

    /**
     * Gets the value of the avisoDetencionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAvisoDetencionId() {
        return avisoDetencionId;
    }

    /**
     * Sets the value of the avisoDetencionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAvisoDetencionId(Long value) {
        this.avisoDetencionId = value;
    }

    /**
     * Gets the value of the delitos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the delitos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDelitos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DelitoWSDTO }
     * 
     * 
     */
    public List<DelitoWSDTO> getDelitos() {
        if (delitos == null) {
            delitos = new ArrayList<DelitoWSDTO>();
        }
        return this.delitos;
    }

    /**
     * Gets the value of the detencion property.
     * 
     * @return
     *     possible object is
     *     {@link DetencionWSDTO }
     *     
     */
    public DetencionWSDTO getDetencion() {
        return detencion;
    }

    /**
     * Sets the value of the detencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link DetencionWSDTO }
     *     
     */
    public void setDetencion(DetencionWSDTO value) {
        this.detencion = value;
    }

    /**
     * Gets the value of the estatusNotificacionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstatusNotificacionId() {
        return estatusNotificacionId;
    }

    /**
     * Sets the value of the estatusNotificacionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstatusNotificacionId(Long value) {
        this.estatusNotificacionId = value;
    }

    /**
     * Gets the value of the folioNotificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioNotificacion() {
        return folioNotificacion;
    }

    /**
     * Sets the value of the folioNotificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioNotificacion(String value) {
        this.folioNotificacion = value;
    }

    /**
     * Gets the value of the numeroCasoAsociado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCasoAsociado() {
        return numeroCasoAsociado;
    }

    /**
     * Sets the value of the numeroCasoAsociado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCasoAsociado(String value) {
        this.numeroCasoAsociado = value;
    }

}
