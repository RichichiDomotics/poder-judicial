
package mx.gob.segob.nsjp.ws.cliente.solicitudtransferenciaimputado;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for solicitudTrasladoImputadoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="solicitudTrasladoImputadoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}solicitudAudienciaBasicoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="folioAudiecia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folioInvolucrado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudTrasladoImputadoWSDTO", propOrder = {
    "folioAudiecia",
    "folioInvolucrado"
})
public class SolicitudTrasladoImputadoWSDTO
    extends SolicitudAudienciaBasicoWSDTO
{

    protected String folioAudiecia;
    protected String folioInvolucrado;

    /**
     * Gets the value of the folioAudiecia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioAudiecia() {
        return folioAudiecia;
    }

    /**
     * Sets the value of the folioAudiecia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioAudiecia(String value) {
        this.folioAudiecia = value;
    }

    /**
     * Gets the value of the folioInvolucrado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioInvolucrado() {
        return folioInvolucrado;
    }

    /**
     * Sets the value of the folioInvolucrado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioInvolucrado(String value) {
        this.folioInvolucrado = value;
    }

}
