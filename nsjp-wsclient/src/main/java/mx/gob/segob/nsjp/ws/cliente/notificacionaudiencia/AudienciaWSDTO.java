
package mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for audienciaWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="audienciaWSDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="audienciaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="domicilioSala" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duracionEstimada" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="estatusAudienciaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="fechaAsignacionSala" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaHoraAudiencia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="folioAudiencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="involucrados" type="{http://ws.service.nsjp.segob.gob.mx/}involucradoWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="motivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreSala" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notificaion" type="{http://ws.service.nsjp.segob.gob.mx/}notificacionWSDTO" minOccurs="0"/>
 *         &lt;element name="numeroCausa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroGeneralCaso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroTocaOrCarpeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salaTemporal" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tipoAudienciaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ubicacionSala" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "audienciaWSDTO", propOrder = {
    "audienciaId",
    "domicilioSala",
    "duracionEstimada",
    "estatusAudienciaId",
    "fechaAsignacionSala",
    "fechaFin",
    "fechaHoraAudiencia",
    "fechaInicio",
    "folioAudiencia",
    "involucrados",
    "motivo",
    "nombreSala",
    "notificaion",
    "numeroCausa",
    "numeroGeneralCaso",
    "numeroTocaOrCarpeta",
    "salaTemporal",
    "tipoAudienciaId",
    "ubicacionSala"
})
public class AudienciaWSDTO {

    protected Long audienciaId;
    protected String domicilioSala;
    protected Integer duracionEstimada;
    protected Long estatusAudienciaId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAsignacionSala;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaFin;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaHoraAudiencia;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicio;
    protected String folioAudiencia;
    @XmlElement(nillable = true)
    protected List<InvolucradoWSDTO> involucrados;
    protected String motivo;
    protected String nombreSala;
    protected NotificacionWSDTO notificaion;
    protected String numeroCausa;
    protected String numeroGeneralCaso;
    protected String numeroTocaOrCarpeta;
    protected boolean salaTemporal;
    protected Long tipoAudienciaId;
    protected String ubicacionSala;

    /**
     * Gets the value of the audienciaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAudienciaId() {
        return audienciaId;
    }

    /**
     * Sets the value of the audienciaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAudienciaId(Long value) {
        this.audienciaId = value;
    }

    /**
     * Gets the value of the domicilioSala property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomicilioSala() {
        return domicilioSala;
    }

    /**
     * Sets the value of the domicilioSala property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomicilioSala(String value) {
        this.domicilioSala = value;
    }

    /**
     * Gets the value of the duracionEstimada property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDuracionEstimada() {
        return duracionEstimada;
    }

    /**
     * Sets the value of the duracionEstimada property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDuracionEstimada(Integer value) {
        this.duracionEstimada = value;
    }

    /**
     * Gets the value of the estatusAudienciaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstatusAudienciaId() {
        return estatusAudienciaId;
    }

    /**
     * Sets the value of the estatusAudienciaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstatusAudienciaId(Long value) {
        this.estatusAudienciaId = value;
    }

    /**
     * Gets the value of the fechaAsignacionSala property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAsignacionSala() {
        return fechaAsignacionSala;
    }

    /**
     * Sets the value of the fechaAsignacionSala property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAsignacionSala(XMLGregorianCalendar value) {
        this.fechaAsignacionSala = value;
    }

    /**
     * Gets the value of the fechaFin property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets the value of the fechaFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFin(XMLGregorianCalendar value) {
        this.fechaFin = value;
    }

    /**
     * Gets the value of the fechaHoraAudiencia property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaHoraAudiencia() {
        return fechaHoraAudiencia;
    }

    /**
     * Sets the value of the fechaHoraAudiencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaHoraAudiencia(XMLGregorianCalendar value) {
        this.fechaHoraAudiencia = value;
    }

    /**
     * Gets the value of the fechaInicio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the value of the fechaInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicio(XMLGregorianCalendar value) {
        this.fechaInicio = value;
    }

    /**
     * Gets the value of the folioAudiencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioAudiencia() {
        return folioAudiencia;
    }

    /**
     * Sets the value of the folioAudiencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioAudiencia(String value) {
        this.folioAudiencia = value;
    }

    /**
     * Gets the value of the involucrados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the involucrados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvolucrados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvolucradoWSDTO }
     * 
     * 
     */
    public List<InvolucradoWSDTO> getInvolucrados() {
        if (involucrados == null) {
            involucrados = new ArrayList<InvolucradoWSDTO>();
        }
        return this.involucrados;
    }

    /**
     * Gets the value of the motivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Sets the value of the motivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivo(String value) {
        this.motivo = value;
    }

    /**
     * Gets the value of the nombreSala property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreSala() {
        return nombreSala;
    }

    /**
     * Sets the value of the nombreSala property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreSala(String value) {
        this.nombreSala = value;
    }

    /**
     * Gets the value of the notificaion property.
     * 
     * @return
     *     possible object is
     *     {@link NotificacionWSDTO }
     *     
     */
    public NotificacionWSDTO getNotificaion() {
        return notificaion;
    }

    /**
     * Sets the value of the notificaion property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotificacionWSDTO }
     *     
     */
    public void setNotificaion(NotificacionWSDTO value) {
        this.notificaion = value;
    }

    /**
     * Gets the value of the numeroCausa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCausa() {
        return numeroCausa;
    }

    /**
     * Sets the value of the numeroCausa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCausa(String value) {
        this.numeroCausa = value;
    }

    /**
     * Gets the value of the numeroGeneralCaso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroGeneralCaso() {
        return numeroGeneralCaso;
    }

    /**
     * Sets the value of the numeroGeneralCaso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroGeneralCaso(String value) {
        this.numeroGeneralCaso = value;
    }

    /**
     * Gets the value of the numeroTocaOrCarpeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTocaOrCarpeta() {
        return numeroTocaOrCarpeta;
    }

    /**
     * Sets the value of the numeroTocaOrCarpeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTocaOrCarpeta(String value) {
        this.numeroTocaOrCarpeta = value;
    }

    /**
     * Gets the value of the salaTemporal property.
     * 
     */
    public boolean isSalaTemporal() {
        return salaTemporal;
    }

    /**
     * Sets the value of the salaTemporal property.
     * 
     */
    public void setSalaTemporal(boolean value) {
        this.salaTemporal = value;
    }

    /**
     * Gets the value of the tipoAudienciaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoAudienciaId() {
        return tipoAudienciaId;
    }

    /**
     * Sets the value of the tipoAudienciaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoAudienciaId(Long value) {
        this.tipoAudienciaId = value;
    }

    /**
     * Gets the value of the ubicacionSala property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUbicacionSala() {
        return ubicacionSala;
    }

    /**
     * Sets the value of the ubicacionSala property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUbicacionSala(String value) {
        this.ubicacionSala = value;
    }

}
