
package mx.gob.segob.nsjp.ws.cliente.solicituddefensor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for solicitudDefensorWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="solicitudDefensorWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}solicitudAudienciaBasicoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="apellidoMaternoImputado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellidoPaternoImputado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="delitos" type="{http://ws.service.nsjp.segob.gob.mx/}delitoWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="esDetenido" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="folioElemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreImputado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudDefensorWSDTO", propOrder = {
    "apellidoMaternoImputado",
    "apellidoPaternoImputado",
    "delitos",
    "esDetenido",
    "folioElemento",
    "nombreImputado"
})
public class SolicitudDefensorWSDTO
    extends SolicitudAudienciaBasicoWSDTO
{

    protected String apellidoMaternoImputado;
    protected String apellidoPaternoImputado;
    @XmlElement(nillable = true)
    protected List<DelitoWSDTO> delitos;
    protected Boolean esDetenido;
    protected String folioElemento;
    protected String nombreImputado;

    /**
     * Gets the value of the apellidoMaternoImputado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoMaternoImputado() {
        return apellidoMaternoImputado;
    }

    /**
     * Sets the value of the apellidoMaternoImputado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoMaternoImputado(String value) {
        this.apellidoMaternoImputado = value;
    }

    /**
     * Gets the value of the apellidoPaternoImputado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoPaternoImputado() {
        return apellidoPaternoImputado;
    }

    /**
     * Sets the value of the apellidoPaternoImputado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoPaternoImputado(String value) {
        this.apellidoPaternoImputado = value;
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
     * Gets the value of the esDetenido property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsDetenido() {
        return esDetenido;
    }

    /**
     * Sets the value of the esDetenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsDetenido(Boolean value) {
        this.esDetenido = value;
    }

    /**
     * Gets the value of the folioElemento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioElemento() {
        return folioElemento;
    }

    /**
     * Sets the value of the folioElemento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioElemento(String value) {
        this.folioElemento = value;
    }

    /**
     * Gets the value of the nombreImputado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreImputado() {
        return nombreImputado;
    }

    /**
     * Sets the value of the nombreImputado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreImputado(String value) {
        this.nombreImputado = value;
    }

}
