
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
 * <p>Java class for solicitudAudienciaBasicoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="solicitudAudienciaBasicoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="areaDestino" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="areaSolicitanteId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="audiencia" type="{http://ws.service.nsjp.segob.gob.mx/}audienciaWSDTO" minOccurs="0"/>
 *         &lt;element name="fechaLimite" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="folioSolicitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="involucradosDTO" type="{http://ws.service.nsjp.segob.gob.mx/}involucradoWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nombreInvolucrado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreSolicitante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCasoAsociado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="objetosDTO" type="{http://ws.service.nsjp.segob.gob.mx/}objetoWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="solicitante" type="{http://ws.service.nsjp.segob.gob.mx/}funcionarioWSDTO" minOccurs="0"/>
 *         &lt;element name="solicitanteExternoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="solicitudId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudAudienciaBasicoWSDTO", propOrder = {
    "areaDestino",
    "areaSolicitanteId",
    "audiencia",
    "fechaLimite",
    "folioSolicitud",
    "involucradosDTO",
    "nombreInvolucrado",
    "nombreSolicitante",
    "numeroCasoAsociado",
    "objetosDTO",
    "solicitante",
    "solicitanteExternoId",
    "solicitudId"
})
public class SolicitudAudienciaBasicoWSDTO
    extends GenericWSDTO
{

    protected Long areaDestino;
    protected Long areaSolicitanteId;
    protected AudienciaWSDTO audiencia;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaLimite;
    protected String folioSolicitud;
    @XmlElement(nillable = true)
    protected List<InvolucradoWSDTO> involucradosDTO;
    protected String nombreInvolucrado;
    protected String nombreSolicitante;
    protected String numeroCasoAsociado;
    @XmlElement(nillable = true)
    protected List<ObjetoWSDTO> objetosDTO;
    protected FuncionarioWSDTO solicitante;
    protected Long solicitanteExternoId;
    protected Long solicitudId;

    /**
     * Gets the value of the areaDestino property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAreaDestino() {
        return areaDestino;
    }

    /**
     * Sets the value of the areaDestino property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAreaDestino(Long value) {
        this.areaDestino = value;
    }

    /**
     * Gets the value of the areaSolicitanteId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAreaSolicitanteId() {
        return areaSolicitanteId;
    }

    /**
     * Sets the value of the areaSolicitanteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAreaSolicitanteId(Long value) {
        this.areaSolicitanteId = value;
    }

    /**
     * Gets the value of the audiencia property.
     * 
     * @return
     *     possible object is
     *     {@link AudienciaWSDTO }
     *     
     */
    public AudienciaWSDTO getAudiencia() {
        return audiencia;
    }

    /**
     * Sets the value of the audiencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link AudienciaWSDTO }
     *     
     */
    public void setAudiencia(AudienciaWSDTO value) {
        this.audiencia = value;
    }

    /**
     * Gets the value of the fechaLimite property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaLimite() {
        return fechaLimite;
    }

    /**
     * Sets the value of the fechaLimite property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaLimite(XMLGregorianCalendar value) {
        this.fechaLimite = value;
    }

    /**
     * Gets the value of the folioSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioSolicitud() {
        return folioSolicitud;
    }

    /**
     * Sets the value of the folioSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioSolicitud(String value) {
        this.folioSolicitud = value;
    }

    /**
     * Gets the value of the involucradosDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the involucradosDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvolucradosDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvolucradoWSDTO }
     * 
     * 
     */
    public List<InvolucradoWSDTO> getInvolucradosDTO() {
        if (involucradosDTO == null) {
            involucradosDTO = new ArrayList<InvolucradoWSDTO>();
        }
        return this.involucradosDTO;
    }

    /**
     * Gets the value of the nombreInvolucrado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreInvolucrado() {
        return nombreInvolucrado;
    }

    /**
     * Sets the value of the nombreInvolucrado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreInvolucrado(String value) {
        this.nombreInvolucrado = value;
    }

    /**
     * Gets the value of the nombreSolicitante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    /**
     * Sets the value of the nombreSolicitante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreSolicitante(String value) {
        this.nombreSolicitante = value;
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

    /**
     * Gets the value of the objetosDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objetosDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjetosDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjetoWSDTO }
     * 
     * 
     */
    public List<ObjetoWSDTO> getObjetosDTO() {
        if (objetosDTO == null) {
            objetosDTO = new ArrayList<ObjetoWSDTO>();
        }
        return this.objetosDTO;
    }

    /**
     * Gets the value of the solicitante property.
     * 
     * @return
     *     possible object is
     *     {@link FuncionarioWSDTO }
     *     
     */
    public FuncionarioWSDTO getSolicitante() {
        return solicitante;
    }

    /**
     * Sets the value of the solicitante property.
     * 
     * @param value
     *     allowed object is
     *     {@link FuncionarioWSDTO }
     *     
     */
    public void setSolicitante(FuncionarioWSDTO value) {
        this.solicitante = value;
    }

    /**
     * Gets the value of the solicitanteExternoId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSolicitanteExternoId() {
        return solicitanteExternoId;
    }

    /**
     * Sets the value of the solicitanteExternoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSolicitanteExternoId(Long value) {
        this.solicitanteExternoId = value;
    }

    /**
     * Gets the value of the solicitudId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSolicitudId() {
        return solicitudId;
    }

    /**
     * Sets the value of the solicitudId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSolicitudId(Long value) {
        this.solicitudId = value;
    }

    /**
     * Asigna el valor al campo involucradosDTO.
     * @param involucradosDTO el valor involucradosDTO a asignar
     */
    public void setInvolucradosDTO(List<InvolucradoWSDTO> involucradosDTO) {
        this.involucradosDTO = involucradosDTO;
    }

}
