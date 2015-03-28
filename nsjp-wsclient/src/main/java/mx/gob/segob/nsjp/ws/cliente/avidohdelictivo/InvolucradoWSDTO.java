
package mx.gob.segob.nsjp.ws.cliente.avidohdelictivo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for involucradoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="involucradoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}personaWSDTO">
 *       &lt;sequence>
 *         &lt;element name="aliasInvolucrado" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="condicion" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="delitosCometidos" type="{http://ws.service.nsjp.segob.gob.mx/}delitoWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="desconocido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="detenciones" type="{http://ws.service.nsjp.segob.gob.mx/}detencionWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="esDetenido" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="esServidor" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="idSolicitudDefensor" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idsDetenidos" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="institucionPresenta" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mediaFiliacionWSDTO" type="{http://ws.service.nsjp.segob.gob.mx/}mediaFiliacionWSDTO" minOccurs="0"/>
 *         &lt;element name="motivoComparecencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organizacion" type="{http://ws.service.nsjp.segob.gob.mx/}organizacionWSDTO" minOccurs="0"/>
 *         &lt;element name="tipoPersona" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorIdEscolaridad" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorIdEstadoCivil" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorIdIdioma" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorIdNacionalidad" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="valorIdOcupacion" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="valorIdParentesco" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorIdReligion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorSituacionJuridica" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "involucradoWSDTO", propOrder = {
    "aliasInvolucrado",
    "condicion",
    "delitosCometidos",
    "desconocido",
    "detenciones",
    "esDetenido",
    "esServidor",
    "idSolicitudDefensor",
    "idsDetenidos",
    "institucionPresenta",
    "mediaFiliacionWSDTO",
    "motivoComparecencia",
    "organizacion",
    "tipoPersona",
    "valorIdEscolaridad",
    "valorIdEstadoCivil",
    "valorIdIdioma",
    "valorIdNacionalidad",
    "valorIdOcupacion",
    "valorIdParentesco",
    "valorIdReligion",
    "valorSituacionJuridica"
})
public class InvolucradoWSDTO
    extends PersonaWSDTO
{

    @XmlElement(nillable = true)
    protected List<String> aliasInvolucrado;
    protected Short condicion;
    @XmlElement(nillable = true)
    protected List<DelitoWSDTO> delitosCometidos;
    protected String desconocido;
    @XmlElement(nillable = true)
    protected List<DetencionWSDTO> detenciones;
    protected Boolean esDetenido;
    protected Boolean esServidor;
    protected Long idSolicitudDefensor;
    @XmlElement(nillable = true)
    protected List<Long> idsDetenidos;
    protected Long institucionPresenta;
    protected MediaFiliacionWSDTO mediaFiliacionWSDTO;
    protected String motivoComparecencia;
    protected OrganizacionWSDTO organizacion;
    protected Long tipoPersona;
    protected Long valorIdEscolaridad;
    protected Long valorIdEstadoCivil;
    protected Long valorIdIdioma;
    @XmlElement(nillable = true)
    protected List<Long> valorIdNacionalidad;
    @XmlElement(nillable = true)
    protected List<Long> valorIdOcupacion;
    protected Long valorIdParentesco;
    protected Long valorIdReligion;
    protected Long valorSituacionJuridica;

    /**
     * Gets the value of the aliasInvolucrado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aliasInvolucrado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAliasInvolucrado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAliasInvolucrado() {
        if (aliasInvolucrado == null) {
            aliasInvolucrado = new ArrayList<String>();
        }
        return this.aliasInvolucrado;
    }

    /**
     * Gets the value of the condicion property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getCondicion() {
        return condicion;
    }

    /**
     * Sets the value of the condicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setCondicion(Short value) {
        this.condicion = value;
    }

    /**
     * Gets the value of the delitosCometidos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the delitosCometidos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDelitosCometidos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DelitoWSDTO }
     * 
     * 
     */
    public List<DelitoWSDTO> getDelitosCometidos() {
        if (delitosCometidos == null) {
            delitosCometidos = new ArrayList<DelitoWSDTO>();
        }
        return this.delitosCometidos;
    }

    /**
     * Gets the value of the desconocido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesconocido() {
        return desconocido;
    }

    /**
     * Sets the value of the desconocido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesconocido(String value) {
        this.desconocido = value;
    }

    /**
     * Gets the value of the detenciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detenciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetenciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetencionWSDTO }
     * 
     * 
     */
    public List<DetencionWSDTO> getDetenciones() {
        if (detenciones == null) {
            detenciones = new ArrayList<DetencionWSDTO>();
        }
        return this.detenciones;
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
     * Gets the value of the esServidor property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsServidor() {
        return esServidor;
    }

    /**
     * Sets the value of the esServidor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsServidor(Boolean value) {
        this.esServidor = value;
    }

    /**
     * Gets the value of the idSolicitudDefensor property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdSolicitudDefensor() {
        return idSolicitudDefensor;
    }

    /**
     * Sets the value of the idSolicitudDefensor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdSolicitudDefensor(Long value) {
        this.idSolicitudDefensor = value;
    }

    /**
     * Gets the value of the idsDetenidos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idsDetenidos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdsDetenidos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getIdsDetenidos() {
        if (idsDetenidos == null) {
            idsDetenidos = new ArrayList<Long>();
        }
        return this.idsDetenidos;
    }

    /**
     * Gets the value of the institucionPresenta property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInstitucionPresenta() {
        return institucionPresenta;
    }

    /**
     * Sets the value of the institucionPresenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInstitucionPresenta(Long value) {
        this.institucionPresenta = value;
    }

    /**
     * Gets the value of the mediaFiliacionWSDTO property.
     * 
     * @return
     *     possible object is
     *     {@link MediaFiliacionWSDTO }
     *     
     */
    public MediaFiliacionWSDTO getMediaFiliacionWSDTO() {
        return mediaFiliacionWSDTO;
    }

    /**
     * Sets the value of the mediaFiliacionWSDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link MediaFiliacionWSDTO }
     *     
     */
    public void setMediaFiliacionWSDTO(MediaFiliacionWSDTO value) {
        this.mediaFiliacionWSDTO = value;
    }

    /**
     * Gets the value of the motivoComparecencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoComparecencia() {
        return motivoComparecencia;
    }

    /**
     * Sets the value of the motivoComparecencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoComparecencia(String value) {
        this.motivoComparecencia = value;
    }

    /**
     * Gets the value of the organizacion property.
     * 
     * @return
     *     possible object is
     *     {@link OrganizacionWSDTO }
     *     
     */
    public OrganizacionWSDTO getOrganizacion() {
        return organizacion;
    }

    /**
     * Sets the value of the organizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizacionWSDTO }
     *     
     */
    public void setOrganizacion(OrganizacionWSDTO value) {
        this.organizacion = value;
    }

    /**
     * Gets the value of the tipoPersona property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Sets the value of the tipoPersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoPersona(Long value) {
        this.tipoPersona = value;
    }

    /**
     * Gets the value of the valorIdEscolaridad property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorIdEscolaridad() {
        return valorIdEscolaridad;
    }

    /**
     * Sets the value of the valorIdEscolaridad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorIdEscolaridad(Long value) {
        this.valorIdEscolaridad = value;
    }

    /**
     * Gets the value of the valorIdEstadoCivil property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorIdEstadoCivil() {
        return valorIdEstadoCivil;
    }

    /**
     * Sets the value of the valorIdEstadoCivil property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorIdEstadoCivil(Long value) {
        this.valorIdEstadoCivil = value;
    }

    /**
     * Gets the value of the valorIdIdioma property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorIdIdioma() {
        return valorIdIdioma;
    }

    /**
     * Sets the value of the valorIdIdioma property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorIdIdioma(Long value) {
        this.valorIdIdioma = value;
    }

    /**
     * Gets the value of the valorIdNacionalidad property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valorIdNacionalidad property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValorIdNacionalidad().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getValorIdNacionalidad() {
        if (valorIdNacionalidad == null) {
            valorIdNacionalidad = new ArrayList<Long>();
        }
        return this.valorIdNacionalidad;
    }

    /**
     * Gets the value of the valorIdOcupacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valorIdOcupacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValorIdOcupacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getValorIdOcupacion() {
        if (valorIdOcupacion == null) {
            valorIdOcupacion = new ArrayList<Long>();
        }
        return this.valorIdOcupacion;
    }

    /**
     * Gets the value of the valorIdParentesco property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorIdParentesco() {
        return valorIdParentesco;
    }

    /**
     * Sets the value of the valorIdParentesco property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorIdParentesco(Long value) {
        this.valorIdParentesco = value;
    }

    /**
     * Gets the value of the valorIdReligion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorIdReligion() {
        return valorIdReligion;
    }

    /**
     * Sets the value of the valorIdReligion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorIdReligion(Long value) {
        this.valorIdReligion = value;
    }

    /**
     * Gets the value of the valorSituacionJuridica property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorSituacionJuridica() {
        return valorSituacionJuridica;
    }

    /**
     * Sets the value of the valorSituacionJuridica property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorSituacionJuridica(Long value) {
        this.valorSituacionJuridica = value;
    }

}
