
package mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for informePolicialHomologadoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="informePolicialHomologadoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="anio" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="expediente" type="{http://ws.service.nsjp.segob.gob.mx/}expedienteWSDTO" minOccurs="0"/>
 *         &lt;element name="fechaCaptura" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="folioIPH" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="informePolicialHomologadoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="numeroOficio" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="objetivosGenerales" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoParticipacion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "informePolicialHomologadoWSDTO", propOrder = {
    "anio",
    "expediente",
    "fechaCaptura",
    "folioIPH",
    "informePolicialHomologadoId",
    "numeroOficio",
    "objetivosGenerales",
    "tipoParticipacion"
})
public class InformePolicialHomologadoWSDTO
    extends GenericWSDTO
{

    protected Long anio;
    protected ExpedienteWSDTO expediente;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCaptura;
    protected Long folioIPH;
    protected Long informePolicialHomologadoId;
    protected Long numeroOficio;
    protected String objetivosGenerales;
    protected Long tipoParticipacion;

    /**
     * Gets the value of the anio property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAnio() {
        return anio;
    }

    /**
     * Sets the value of the anio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAnio(Long value) {
        this.anio = value;
    }

    /**
     * Gets the value of the expediente property.
     * 
     * @return
     *     possible object is
     *     {@link ExpedienteWSDTO }
     *     
     */
    public ExpedienteWSDTO getExpediente() {
        return expediente;
    }

    /**
     * Sets the value of the expediente property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpedienteWSDTO }
     *     
     */
    public void setExpediente(ExpedienteWSDTO value) {
        this.expediente = value;
    }

    /**
     * Gets the value of the fechaCaptura property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCaptura() {
        return fechaCaptura;
    }

    /**
     * Sets the value of the fechaCaptura property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCaptura(XMLGregorianCalendar value) {
        this.fechaCaptura = value;
    }

    /**
     * Gets the value of the folioIPH property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFolioIPH() {
        return folioIPH;
    }

    /**
     * Sets the value of the folioIPH property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFolioIPH(Long value) {
        this.folioIPH = value;
    }

    /**
     * Gets the value of the informePolicialHomologadoId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInformePolicialHomologadoId() {
        return informePolicialHomologadoId;
    }

    /**
     * Sets the value of the informePolicialHomologadoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInformePolicialHomologadoId(Long value) {
        this.informePolicialHomologadoId = value;
    }

    /**
     * Gets the value of the numeroOficio property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumeroOficio() {
        return numeroOficio;
    }

    /**
     * Sets the value of the numeroOficio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumeroOficio(Long value) {
        this.numeroOficio = value;
    }

    /**
     * Gets the value of the objetivosGenerales property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjetivosGenerales() {
        return objetivosGenerales;
    }

    /**
     * Sets the value of the objetivosGenerales property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjetivosGenerales(String value) {
        this.objetivosGenerales = value;
    }

    /**
     * Gets the value of the tipoParticipacion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoParticipacion() {
        return tipoParticipacion;
    }

    /**
     * Sets the value of the tipoParticipacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoParticipacion(Long value) {
        this.tipoParticipacion = value;
    }

}
