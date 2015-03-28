
package mx.gob.segob.nsjp.ws.cliente.medidaalterna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for medidaWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="medidaWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}documentoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="descripcionMedida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idEstatus" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idValorPeriodicidad" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idValorTipoMedida" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="juezOrdena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreSujeto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCarpetaEjecucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCaso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCausa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aMaternoSujeto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aPaternoSujeto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "medidaWSDTO", propOrder = {
    "descripcionMedida",
    "fechaFin",
    "fechaInicio",
    "idEstatus",
    "idValorPeriodicidad",
    "idValorTipoMedida",
    "juezOrdena",
    "nombreSujeto",
    "numeroCarpetaEjecucion",
    "numeroCaso",
    "numeroCausa",
    "aMaternoSujeto",
    "aPaternoSujeto"
})
@XmlSeeAlso({
    MedidaAlternaWSDTO.class
})
public class MedidaWSDTO
    extends DocumentoWSDTO
{

    protected String descripcionMedida;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaFin;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicio;
    protected Long idEstatus;
    protected Long idValorPeriodicidad;
    protected Long idValorTipoMedida;
    protected String juezOrdena;
    protected String nombreSujeto;
    protected String numeroCarpetaEjecucion;
    protected String numeroCaso;
    protected String numeroCausa;
    protected String aMaternoSujeto;
    protected String aPaternoSujeto;

    /**
     * Gets the value of the descripcionMedida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionMedida() {
        return descripcionMedida;
    }

    /**
     * Sets the value of the descripcionMedida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionMedida(String value) {
        this.descripcionMedida = value;
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
     * Gets the value of the idValorPeriodicidad property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdValorPeriodicidad() {
        return idValorPeriodicidad;
    }

    /**
     * Sets the value of the idValorPeriodicidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdValorPeriodicidad(Long value) {
        this.idValorPeriodicidad = value;
    }

    /**
     * Gets the value of the idValorTipoMedida property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdValorTipoMedida() {
        return idValorTipoMedida;
    }

    /**
     * Sets the value of the idValorTipoMedida property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdValorTipoMedida(Long value) {
        this.idValorTipoMedida = value;
    }

    /**
     * Gets the value of the juezOrdena property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJuezOrdena() {
        return juezOrdena;
    }

    /**
     * Sets the value of the juezOrdena property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJuezOrdena(String value) {
        this.juezOrdena = value;
    }

    /**
     * Gets the value of the nombreSujeto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreSujeto() {
        return nombreSujeto;
    }

    /**
     * Sets the value of the nombreSujeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreSujeto(String value) {
        this.nombreSujeto = value;
    }

    /**
     * Gets the value of the numeroCarpetaEjecucion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCarpetaEjecucion() {
        return numeroCarpetaEjecucion;
    }

    /**
     * Sets the value of the numeroCarpetaEjecucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCarpetaEjecucion(String value) {
        this.numeroCarpetaEjecucion = value;
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
     * Gets the value of the aMaternoSujeto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAMaternoSujeto() {
        return aMaternoSujeto;
    }

    /**
     * Sets the value of the aMaternoSujeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAMaternoSujeto(String value) {
        this.aMaternoSujeto = value;
    }

    /**
     * Gets the value of the aPaternoSujeto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPaternoSujeto() {
        return aPaternoSujeto;
    }

    /**
     * Sets the value of the aPaternoSujeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPaternoSujeto(String value) {
        this.aPaternoSujeto = value;
    }

}
