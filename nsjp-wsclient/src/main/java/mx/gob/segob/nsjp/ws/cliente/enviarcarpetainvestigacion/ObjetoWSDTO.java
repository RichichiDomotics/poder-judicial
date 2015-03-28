
package mx.gob.segob.nsjp.ws.cliente.enviarcarpetainvestigacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for objetoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="objetoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}elementoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="esPertenencia" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="evidencia" type="{http://ws.service.nsjp.segob.gob.mx/}evidenciaWSDTO" minOccurs="0"/>
 *         &lt;element name="institucionPresenta" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipoObjeto" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorByCondicionFisicaVal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "objetoWSDTO", propOrder = {
    "descripcion",
    "esPertenencia",
    "evidencia",
    "institucionPresenta",
    "tipoObjeto",
    "valorByCondicionFisicaVal"
})
@XmlSeeAlso({
    SustanciaWSDTO.class,
    VegetalWSDTO.class,
    ObraArteWSDTO.class,
    DocumentoOficialWSDTO.class,
    EquipoComputoWSDTO.class,
    ArmaWSDTO.class,
    ExplosivoWSDTO.class,
    EmbarcacionWSDTO.class,
    VehiculoWSDTO.class,
    TelefoniaWSDTO.class,
    AeronaveWSDTO.class,
    JoyaWSDTO.class,
    NumerarioWSDTO.class,
    InmuebleWSDTO.class,
    AnimalWSDTO.class
})
public class ObjetoWSDTO
    extends ElementoWSDTO
{

    protected String descripcion;
    protected boolean esPertenencia;
    protected EvidenciaWSDTO evidencia;
    protected Long institucionPresenta;
    protected Long tipoObjeto;
    protected Long valorByCondicionFisicaVal;

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
     * Gets the value of the esPertenencia property.
     * 
     */
    public boolean isEsPertenencia() {
        return esPertenencia;
    }

    /**
     * Sets the value of the esPertenencia property.
     * 
     */
    public void setEsPertenencia(boolean value) {
        this.esPertenencia = value;
    }

    /**
     * Gets the value of the evidencia property.
     * 
     * @return
     *     possible object is
     *     {@link EvidenciaWSDTO }
     *     
     */
    public EvidenciaWSDTO getEvidencia() {
        return evidencia;
    }

    /**
     * Sets the value of the evidencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link EvidenciaWSDTO }
     *     
     */
    public void setEvidencia(EvidenciaWSDTO value) {
        this.evidencia = value;
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
     * Gets the value of the tipoObjeto property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoObjeto() {
        return tipoObjeto;
    }

    /**
     * Sets the value of the tipoObjeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoObjeto(Long value) {
        this.tipoObjeto = value;
    }

    /**
     * Gets the value of the valorByCondicionFisicaVal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorByCondicionFisicaVal() {
        return valorByCondicionFisicaVal;
    }

    /**
     * Sets the value of the valorByCondicionFisicaVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorByCondicionFisicaVal(Long value) {
        this.valorByCondicionFisicaVal = value;
    }

}
