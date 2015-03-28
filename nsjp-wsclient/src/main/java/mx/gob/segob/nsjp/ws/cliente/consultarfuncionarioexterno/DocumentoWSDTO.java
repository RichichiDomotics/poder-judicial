
package mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for documentoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="actividad" type="{http://ws.service.nsjp.segob.gob.mx/}actividadWSDTO" minOccurs="0"/>
 *         &lt;element name="archivoDigital" type="{http://ws.service.nsjp.segob.gob.mx/}archivoDigitalWSDTO" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaCreacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="folioDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nombreDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroFojas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="origenDocumento" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="strFechaCreacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strHoraCreacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoParcial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDocumentoDTO" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentoWSDTO", propOrder = {
    "actividad",
    "archivoDigital",
    "descripcion",
    "fechaCreacion",
    "folioDocumento",
    "formaId",
    "nombreDocumento",
    "numeroFojas",
    "origenDocumento",
    "strFechaCreacion",
    "strHoraCreacion",
    "textoParcial",
    "tipoDocumentoDTO",
    "version"
})
public class DocumentoWSDTO
    extends GenericWSDTO
{

    protected ActividadWSDTO actividad;
    protected ArchivoDigitalWSDTO archivoDigital;
    protected String descripcion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCreacion;
    protected String folioDocumento;
    protected Long formaId;
    protected String nombreDocumento;
    protected String numeroFojas;
    protected Short origenDocumento;
    protected String strFechaCreacion;
    protected String strHoraCreacion;
    protected String textoParcial;
    protected Long tipoDocumentoDTO;
    protected Double version;

    /**
     * Gets the value of the actividad property.
     * 
     * @return
     *     possible object is
     *     {@link ActividadWSDTO }
     *     
     */
    public ActividadWSDTO getActividad() {
        return actividad;
    }

    /**
     * Sets the value of the actividad property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActividadWSDTO }
     *     
     */
    public void setActividad(ActividadWSDTO value) {
        this.actividad = value;
    }

    /**
     * Gets the value of the archivoDigital property.
     * 
     * @return
     *     possible object is
     *     {@link ArchivoDigitalWSDTO }
     *     
     */
    public ArchivoDigitalWSDTO getArchivoDigital() {
        return archivoDigital;
    }

    /**
     * Sets the value of the archivoDigital property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArchivoDigitalWSDTO }
     *     
     */
    public void setArchivoDigital(ArchivoDigitalWSDTO value) {
        this.archivoDigital = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the fechaCreacion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Sets the value of the fechaCreacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCreacion(XMLGregorianCalendar value) {
        this.fechaCreacion = value;
    }

    /**
     * Gets the value of the folioDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioDocumento() {
        return folioDocumento;
    }

    /**
     * Sets the value of the folioDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioDocumento(String value) {
        this.folioDocumento = value;
    }

    /**
     * Gets the value of the formaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFormaId() {
        return formaId;
    }

    /**
     * Sets the value of the formaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFormaId(Long value) {
        this.formaId = value;
    }

    /**
     * Gets the value of the nombreDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * Sets the value of the nombreDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreDocumento(String value) {
        this.nombreDocumento = value;
    }

    /**
     * Gets the value of the numeroFojas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroFojas() {
        return numeroFojas;
    }

    /**
     * Sets the value of the numeroFojas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroFojas(String value) {
        this.numeroFojas = value;
    }

    /**
     * Gets the value of the origenDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getOrigenDocumento() {
        return origenDocumento;
    }

    /**
     * Sets the value of the origenDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setOrigenDocumento(Short value) {
        this.origenDocumento = value;
    }

    /**
     * Gets the value of the strFechaCreacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrFechaCreacion() {
        return strFechaCreacion;
    }

    /**
     * Sets the value of the strFechaCreacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrFechaCreacion(String value) {
        this.strFechaCreacion = value;
    }

    /**
     * Gets the value of the strHoraCreacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrHoraCreacion() {
        return strHoraCreacion;
    }

    /**
     * Sets the value of the strHoraCreacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrHoraCreacion(String value) {
        this.strHoraCreacion = value;
    }

    /**
     * Gets the value of the textoParcial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoParcial() {
        return textoParcial;
    }

    /**
     * Sets the value of the textoParcial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoParcial(String value) {
        this.textoParcial = value;
    }

    /**
     * Gets the value of the tipoDocumentoDTO property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoDocumentoDTO() {
        return tipoDocumentoDTO;
    }

    /**
     * Sets the value of the tipoDocumentoDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoDocumentoDTO(Long value) {
        this.tipoDocumentoDTO = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVersion(Double value) {
        this.version = value;
    }

}
