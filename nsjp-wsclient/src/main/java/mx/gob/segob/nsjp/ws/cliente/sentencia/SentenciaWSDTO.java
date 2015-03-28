
package mx.gob.segob.nsjp.ws.cliente.sentencia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for sentenciaWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sentenciaWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="bcumplida" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="blesionado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="cnumeroCausa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cnus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dfechaFinPena" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dfechaInicioPena" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="involucradoDTO" type="{http://impl.ws.service.nsjp.segob.gob.mx/}involucradoWSDTO" minOccurs="0"/>
 *         &lt;element name="ipuntosPorAcumular" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="numeroExpedienteDTO" type="{http://impl.ws.service.nsjp.segob.gob.mx/}expedienteWSDTO" minOccurs="0"/>
 *         &lt;element name="sentenciaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipoDeSentenciaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sentenciaWSDTO", propOrder = {
    "bcumplida",
    "blesionado",
    "cnumeroCausa",
    "cnus",
    "dfechaFinPena",
    "dfechaInicioPena",
    "involucradoDTO",
    "ipuntosPorAcumular",
    "numeroExpedienteDTO",
    "sentenciaId",
    "tipoDeSentenciaId"
})
public class SentenciaWSDTO
    extends GenericWSDTO
{

    protected Boolean bcumplida;
    protected Boolean blesionado;
    protected String cnumeroCausa;
    protected String cnus;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dfechaFinPena;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dfechaInicioPena;
    protected InvolucradoWSDTO involucradoDTO;
    protected Long ipuntosPorAcumular;
    protected ExpedienteWSDTO numeroExpedienteDTO;
    protected Long sentenciaId;
    protected Long tipoDeSentenciaId;

    /**
     * Gets the value of the bcumplida property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBcumplida() {
        return bcumplida;
    }

    /**
     * Sets the value of the bcumplida property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBcumplida(Boolean value) {
        this.bcumplida = value;
    }

    /**
     * Gets the value of the blesionado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBlesionado() {
        return blesionado;
    }

    /**
     * Sets the value of the blesionado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBlesionado(Boolean value) {
        this.blesionado = value;
    }

    /**
     * Gets the value of the cnumeroCausa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnumeroCausa() {
        return cnumeroCausa;
    }

    /**
     * Sets the value of the cnumeroCausa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnumeroCausa(String value) {
        this.cnumeroCausa = value;
    }

    /**
     * Gets the value of the cnus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnus() {
        return cnus;
    }

    /**
     * Sets the value of the cnus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnus(String value) {
        this.cnus = value;
    }

    /**
     * Gets the value of the dfechaFinPena property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDfechaFinPena() {
        return dfechaFinPena;
    }

    /**
     * Sets the value of the dfechaFinPena property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDfechaFinPena(XMLGregorianCalendar value) {
        this.dfechaFinPena = value;
    }

    /**
     * Gets the value of the dfechaInicioPena property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDfechaInicioPena() {
        return dfechaInicioPena;
    }

    /**
     * Sets the value of the dfechaInicioPena property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDfechaInicioPena(XMLGregorianCalendar value) {
        this.dfechaInicioPena = value;
    }

    /**
     * Gets the value of the involucradoDTO property.
     * 
     * @return
     *     possible object is
     *     {@link InvolucradoWSDTO }
     *     
     */
    public InvolucradoWSDTO getInvolucradoDTO() {
        return involucradoDTO;
    }

    /**
     * Sets the value of the involucradoDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvolucradoWSDTO }
     *     
     */
    public void setInvolucradoDTO(InvolucradoWSDTO value) {
        this.involucradoDTO = value;
    }

    /**
     * Gets the value of the ipuntosPorAcumular property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIpuntosPorAcumular() {
        return ipuntosPorAcumular;
    }

    /**
     * Sets the value of the ipuntosPorAcumular property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIpuntosPorAcumular(Long value) {
        this.ipuntosPorAcumular = value;
    }

    /**
     * Gets the value of the numeroExpedienteDTO property.
     * 
     * @return
     *     possible object is
     *     {@link ExpedienteWSDTO }
     *     
     */
    public ExpedienteWSDTO getNumeroExpedienteDTO() {
        return numeroExpedienteDTO;
    }

    /**
     * Sets the value of the numeroExpedienteDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpedienteWSDTO }
     *     
     */
    public void setNumeroExpedienteDTO(ExpedienteWSDTO value) {
        this.numeroExpedienteDTO = value;
    }

    /**
     * Gets the value of the sentenciaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSentenciaId() {
        return sentenciaId;
    }

    /**
     * Sets the value of the sentenciaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSentenciaId(Long value) {
        this.sentenciaId = value;
    }

    /**
     * Gets the value of the tipoDeSentenciaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoDeSentenciaId() {
        return tipoDeSentenciaId;
    }

    /**
     * Sets the value of the tipoDeSentenciaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoDeSentenciaId(Long value) {
        this.tipoDeSentenciaId = value;
    }

}
