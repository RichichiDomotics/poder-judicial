
package mx.gob.segob.nsjp.ws.cliente.consultaraudiencia;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for audienciaDefensoriaWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="audienciaDefensoriaWSDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="audienciaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="caracter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="domicilioSala" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaAudiencia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="filtroFechaFinal" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="filtroFechaInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idDistritoFiltroAudiencias" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="involucrados" type="{http://ws.service.nsjp.segob.gob.mx/}involucradoAudienciaDefWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nombreSala" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCaso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "audienciaDefensoriaWSDTO", propOrder = {
    "audienciaId",
    "caracter",
    "domicilioSala",
    "fechaAudiencia",
    "filtroFechaFinal",
    "filtroFechaInicio",
    "idDistritoFiltroAudiencias",
    "involucrados",
    "nombreSala",
    "numeroCaso",
    "tipoAudienciaId",
    "ubicacionSala"
})
public class AudienciaDefensoriaWSDTO {

    protected Long audienciaId;
    protected String caracter;
    protected String domicilioSala;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAudiencia;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar filtroFechaFinal;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar filtroFechaInicio;
    protected Long idDistritoFiltroAudiencias;
    @XmlElement(nillable = true)
    protected List<InvolucradoAudienciaDefWSDTO> involucrados;
    protected String nombreSala;
    protected String numeroCaso;
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
     * Gets the value of the caracter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaracter() {
        return caracter;
    }

    /**
     * Sets the value of the caracter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaracter(String value) {
        this.caracter = value;
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
     * Gets the value of the fechaAudiencia property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAudiencia() {
        return fechaAudiencia;
    }

    /**
     * Sets the value of the fechaAudiencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAudiencia(XMLGregorianCalendar value) {
        this.fechaAudiencia = value;
    }

    /**
     * Gets the value of the filtroFechaFinal property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFiltroFechaFinal() {
        return filtroFechaFinal;
    }

    /**
     * Sets the value of the filtroFechaFinal property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFiltroFechaFinal(XMLGregorianCalendar value) {
        this.filtroFechaFinal = value;
    }

    /**
     * Gets the value of the filtroFechaInicio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFiltroFechaInicio() {
        return filtroFechaInicio;
    }

    /**
     * Sets the value of the filtroFechaInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFiltroFechaInicio(XMLGregorianCalendar value) {
        this.filtroFechaInicio = value;
    }

    /**
     * Gets the value of the idDistritoFiltroAudiencias property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdDistritoFiltroAudiencias() {
        return idDistritoFiltroAudiencias;
    }

    /**
     * Sets the value of the idDistritoFiltroAudiencias property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdDistritoFiltroAudiencias(Long value) {
        this.idDistritoFiltroAudiencias = value;
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
     * {@link InvolucradoAudienciaDefWSDTO }
     * 
     * 
     */
    public List<InvolucradoAudienciaDefWSDTO> getInvolucrados() {
        if (involucrados == null) {
            involucrados = new ArrayList<InvolucradoAudienciaDefWSDTO>();
        }
        return this.involucrados;
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
     * Gets the value of the numeroCaso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCaso() {
        return numeroCaso;
    }

    /**
     * Sets the value of the numeroCaso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCaso(String value) {
        this.numeroCaso = value;
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
