
package mx.gob.segob.nsjp.ws.cliente.solicitudcarpeta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for notificacionWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="notificacionWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}documentoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="consecutivoNotificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folioNotificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificacionWSDTO", propOrder = {
    "consecutivoNotificacion",
    "folioNotificacion"
})
public class NotificacionWSDTO
    extends DocumentoWSDTO
{

    protected String consecutivoNotificacion;
    protected String folioNotificacion;

    /**
     * Gets the value of the consecutivoNotificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsecutivoNotificacion() {
        return consecutivoNotificacion;
    }

    /**
     * Sets the value of the consecutivoNotificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsecutivoNotificacion(String value) {
        this.consecutivoNotificacion = value;
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

}
