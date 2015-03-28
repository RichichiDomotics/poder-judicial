
package mx.gob.segob.nsjp.ws.cliente.sentencia;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for personaWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="personaWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.ws.service.nsjp.segob.gob.mx/}elementoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="correos" type="{http://impl.ws.service.nsjp.segob.gob.mx/}correoElectronicoWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="domicilio" type="{http://impl.ws.service.nsjp.segob.gob.mx/}domicilioWSDTO" minOccurs="0"/>
 *         &lt;element name="domicilioNotificacion" type="{http://impl.ws.service.nsjp.segob.gob.mx/}domicilioWSDTO" minOccurs="0"/>
 *         &lt;element name="esVivo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="folioIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombresDemograficos" type="{http://impl.ws.service.nsjp.segob.gob.mx/}nombreDemograficoWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="telefonos" type="{http://impl.ws.service.nsjp.segob.gob.mx/}telefonoWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="valorIdIdentificaion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personaWSDTO", propOrder = {
    "correos",
    "domicilio",
    "domicilioNotificacion",
    "esVivo",
    "folioIdentificacion",
    "nombresDemograficos",
    "telefonos",
    "valorIdIdentificaion"
})
@XmlSeeAlso({
    InvolucradoWSDTO.class
})
public class PersonaWSDTO
    extends ElementoWSDTO
{

    @XmlElement(nillable = true)
    protected List<CorreoElectronicoWSDTO> correos;
    protected DomicilioWSDTO domicilio;
    protected DomicilioWSDTO domicilioNotificacion;
    protected Long esVivo;
    protected String folioIdentificacion;
    @XmlElement(nillable = true)
    protected List<NombreDemograficoWSDTO> nombresDemograficos;
    @XmlElement(nillable = true)
    protected List<TelefonoWSDTO> telefonos;
    protected Long valorIdIdentificaion;

    /**
     * Gets the value of the correos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the correos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCorreos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CorreoElectronicoWSDTO }
     * 
     * 
     */
    public List<CorreoElectronicoWSDTO> getCorreos() {
        if (correos == null) {
            correos = new ArrayList<CorreoElectronicoWSDTO>();
        }
        return this.correos;
    }

    /**
     * Gets the value of the domicilio property.
     * 
     * @return
     *     possible object is
     *     {@link DomicilioWSDTO }
     *     
     */
    public DomicilioWSDTO getDomicilio() {
        return domicilio;
    }

    /**
     * Sets the value of the domicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomicilioWSDTO }
     *     
     */
    public void setDomicilio(DomicilioWSDTO value) {
        this.domicilio = value;
    }

    /**
     * Gets the value of the domicilioNotificacion property.
     * 
     * @return
     *     possible object is
     *     {@link DomicilioWSDTO }
     *     
     */
    public DomicilioWSDTO getDomicilioNotificacion() {
        return domicilioNotificacion;
    }

    /**
     * Sets the value of the domicilioNotificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomicilioWSDTO }
     *     
     */
    public void setDomicilioNotificacion(DomicilioWSDTO value) {
        this.domicilioNotificacion = value;
    }

    /**
     * Gets the value of the esVivo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEsVivo() {
        return esVivo;
    }

    /**
     * Sets the value of the esVivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEsVivo(Long value) {
        this.esVivo = value;
    }

    /**
     * Gets the value of the folioIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioIdentificacion() {
        return folioIdentificacion;
    }

    /**
     * Sets the value of the folioIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioIdentificacion(String value) {
        this.folioIdentificacion = value;
    }

    /**
     * Gets the value of the nombresDemograficos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nombresDemograficos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNombresDemograficos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NombreDemograficoWSDTO }
     * 
     * 
     */
    public List<NombreDemograficoWSDTO> getNombresDemograficos() {
        if (nombresDemograficos == null) {
            nombresDemograficos = new ArrayList<NombreDemograficoWSDTO>();
        }
        return this.nombresDemograficos;
    }

    /**
     * Gets the value of the telefonos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the telefonos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTelefonos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TelefonoWSDTO }
     * 
     * 
     */
    public List<TelefonoWSDTO> getTelefonos() {
        if (telefonos == null) {
            telefonos = new ArrayList<TelefonoWSDTO>();
        }
        return this.telefonos;
    }

    /**
     * Gets the value of the valorIdIdentificaion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorIdIdentificaion() {
        return valorIdIdentificaion;
    }

    /**
     * Sets the value of the valorIdIdentificaion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorIdIdentificaion(Long value) {
        this.valorIdIdentificaion = value;
    }

}
