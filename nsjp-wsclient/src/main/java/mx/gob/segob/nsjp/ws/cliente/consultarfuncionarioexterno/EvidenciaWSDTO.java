
package mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for evidenciaWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="evidenciaWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="cadenaDeCustodia" type="{http://ws.service.nsjp.segob.gob.mx/}cadenaDeCustodiaWSDTO" minOccurs="0"/>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codigoBarras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eslabones" type="{http://ws.service.nsjp.segob.gob.mx/}eslabonWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="evidenciaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="fechaLevantamiento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="funcionario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="funcionarioBaja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idDestinoLegal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idEstatus" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="motivoBaja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroEvidencia" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="objeto" type="{http://ws.service.nsjp.segob.gob.mx/}objetoWSDTO" minOccurs="0"/>
 *         &lt;element name="origenEvidencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ultimoEslabon" type="{http://ws.service.nsjp.segob.gob.mx/}eslabonWSDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "evidenciaWSDTO", propOrder = {
    "cadenaDeCustodia",
    "cantidad",
    "codigoBarras",
    "descripcion",
    "eslabones",
    "evidenciaId",
    "fechaLevantamiento",
    "funcionario",
    "funcionarioBaja",
    "idDestinoLegal",
    "idEstatus",
    "motivoBaja",
    "numeroEvidencia",
    "objeto",
    "origenEvidencia",
    "ultimoEslabon"
})
public class EvidenciaWSDTO
    extends GenericWSDTO
{

    protected CadenaDeCustodiaWSDTO cadenaDeCustodia;
    protected int cantidad;
    protected String codigoBarras;
    protected String descripcion;
    @XmlElement(nillable = true)
    protected List<EslabonWSDTO> eslabones;
    protected Long evidenciaId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaLevantamiento;
    protected String funcionario;
    protected String funcionarioBaja;
    protected Long idDestinoLegal;
    protected Long idEstatus;
    protected String motivoBaja;
    protected Long numeroEvidencia;
    protected ObjetoWSDTO objeto;
    protected String origenEvidencia;
    protected EslabonWSDTO ultimoEslabon;

    /**
     * Gets the value of the cadenaDeCustodia property.
     * 
     * @return
     *     possible object is
     *     {@link CadenaDeCustodiaWSDTO }
     *     
     */
    public CadenaDeCustodiaWSDTO getCadenaDeCustodia() {
        return cadenaDeCustodia;
    }

    /**
     * Sets the value of the cadenaDeCustodia property.
     * 
     * @param value
     *     allowed object is
     *     {@link CadenaDeCustodiaWSDTO }
     *     
     */
    public void setCadenaDeCustodia(CadenaDeCustodiaWSDTO value) {
        this.cadenaDeCustodia = value;
    }

    /**
     * Gets the value of the cantidad property.
     * 
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Sets the value of the cantidad property.
     * 
     */
    public void setCantidad(int value) {
        this.cantidad = value;
    }

    /**
     * Gets the value of the codigoBarras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * Sets the value of the codigoBarras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoBarras(String value) {
        this.codigoBarras = value;
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
     * Gets the value of the eslabones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eslabones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEslabones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EslabonWSDTO }
     * 
     * 
     */
    public List<EslabonWSDTO> getEslabones() {
        if (eslabones == null) {
            eslabones = new ArrayList<EslabonWSDTO>();
        }
        return this.eslabones;
    }

    /**
     * Gets the value of the evidenciaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEvidenciaId() {
        return evidenciaId;
    }

    /**
     * Sets the value of the evidenciaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEvidenciaId(Long value) {
        this.evidenciaId = value;
    }

    /**
     * Gets the value of the fechaLevantamiento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaLevantamiento() {
        return fechaLevantamiento;
    }

    /**
     * Sets the value of the fechaLevantamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaLevantamiento(XMLGregorianCalendar value) {
        this.fechaLevantamiento = value;
    }

    /**
     * Gets the value of the funcionario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncionario() {
        return funcionario;
    }

    /**
     * Sets the value of the funcionario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncionario(String value) {
        this.funcionario = value;
    }

    /**
     * Gets the value of the funcionarioBaja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncionarioBaja() {
        return funcionarioBaja;
    }

    /**
     * Sets the value of the funcionarioBaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncionarioBaja(String value) {
        this.funcionarioBaja = value;
    }

    /**
     * Gets the value of the idDestinoLegal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdDestinoLegal() {
        return idDestinoLegal;
    }

    /**
     * Sets the value of the idDestinoLegal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdDestinoLegal(Long value) {
        this.idDestinoLegal = value;
    }

    /**
     * Gets the value of the idEstatus property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdEstatus() {
        return idEstatus;
    }

    /**
     * Sets the value of the idEstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdEstatus(Long value) {
        this.idEstatus = value;
    }

    /**
     * Gets the value of the motivoBaja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoBaja() {
        return motivoBaja;
    }

    /**
     * Sets the value of the motivoBaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoBaja(String value) {
        this.motivoBaja = value;
    }

    /**
     * Gets the value of the numeroEvidencia property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumeroEvidencia() {
        return numeroEvidencia;
    }

    /**
     * Sets the value of the numeroEvidencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumeroEvidencia(Long value) {
        this.numeroEvidencia = value;
    }

    /**
     * Gets the value of the objeto property.
     * 
     * @return
     *     possible object is
     *     {@link ObjetoWSDTO }
     *     
     */
    public ObjetoWSDTO getObjeto() {
        return objeto;
    }

    /**
     * Sets the value of the objeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjetoWSDTO }
     *     
     */
    public void setObjeto(ObjetoWSDTO value) {
        this.objeto = value;
    }

    /**
     * Gets the value of the origenEvidencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigenEvidencia() {
        return origenEvidencia;
    }

    /**
     * Sets the value of the origenEvidencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigenEvidencia(String value) {
        this.origenEvidencia = value;
    }

    /**
     * Gets the value of the ultimoEslabon property.
     * 
     * @return
     *     possible object is
     *     {@link EslabonWSDTO }
     *     
     */
    public EslabonWSDTO getUltimoEslabon() {
        return ultimoEslabon;
    }

    /**
     * Sets the value of the ultimoEslabon property.
     * 
     * @param value
     *     allowed object is
     *     {@link EslabonWSDTO }
     *     
     */
    public void setUltimoEslabon(EslabonWSDTO value) {
        this.ultimoEslabon = value;
    }

}
