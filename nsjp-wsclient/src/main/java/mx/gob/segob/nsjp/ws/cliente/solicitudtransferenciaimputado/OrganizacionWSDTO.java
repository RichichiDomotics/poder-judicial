
package mx.gob.segob.nsjp.ws.cliente.solicitudtransferenciaimputado;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for organizacionWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="organizacionWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}elementoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="areaDeInfluencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionDelictiva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direccionInternet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="domicilioWSDTO" type="{http://ws.service.nsjp.segob.gob.mx/}domicilioWSDTO" minOccurs="0"/>
 *         &lt;element name="giro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreCorto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreOrganizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroActaConstitutiva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propositoCiberespacio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="representanteLegal" type="{http://ws.service.nsjp.segob.gob.mx/}involucradoWSDTO" minOccurs="0"/>
 *         &lt;element name="rfc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoCiberespacio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorByComunidadVirtualVal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorByOrganizacionFormalVal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorBySectorGubernamentalVal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorByTipoOrganizacionVal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "organizacionWSDTO", propOrder = {
    "areaDeInfluencia",
    "descripcionDelictiva",
    "direccionInternet",
    "domicilioWSDTO",
    "giro",
    "nombreCorto",
    "nombreOrganizacion",
    "numeroActaConstitutiva",
    "propositoCiberespacio",
    "representanteLegal",
    "rfc",
    "tipoCiberespacio",
    "valorByComunidadVirtualVal",
    "valorByOrganizacionFormalVal",
    "valorBySectorGubernamentalVal",
    "valorByTipoOrganizacionVal"
})
public class OrganizacionWSDTO
    extends ElementoWSDTO
{

    protected String areaDeInfluencia;
    protected String descripcionDelictiva;
    protected String direccionInternet;
    protected DomicilioWSDTO domicilioWSDTO;
    protected String giro;
    protected String nombreCorto;
    protected String nombreOrganizacion;
    protected String numeroActaConstitutiva;
    protected String propositoCiberespacio;
    protected InvolucradoWSDTO representanteLegal;
    protected String rfc;
    protected String tipoCiberespacio;
    protected Long valorByComunidadVirtualVal;
    protected Long valorByOrganizacionFormalVal;
    protected Long valorBySectorGubernamentalVal;
    protected Long valorByTipoOrganizacionVal;

    /**
     * Gets the value of the areaDeInfluencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaDeInfluencia() {
        return areaDeInfluencia;
    }

    /**
     * Sets the value of the areaDeInfluencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaDeInfluencia(String value) {
        this.areaDeInfluencia = value;
    }

    /**
     * Gets the value of the descripcionDelictiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionDelictiva() {
        return descripcionDelictiva;
    }

    /**
     * Sets the value of the descripcionDelictiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionDelictiva(String value) {
        this.descripcionDelictiva = value;
    }

    /**
     * Gets the value of the direccionInternet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionInternet() {
        return direccionInternet;
    }

    /**
     * Sets the value of the direccionInternet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionInternet(String value) {
        this.direccionInternet = value;
    }

    /**
     * Gets the value of the domicilioWSDTO property.
     * 
     * @return
     *     possible object is
     *     {@link DomicilioWSDTO }
     *     
     */
    public DomicilioWSDTO getDomicilioWSDTO() {
        return domicilioWSDTO;
    }

    /**
     * Sets the value of the domicilioWSDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomicilioWSDTO }
     *     
     */
    public void setDomicilioWSDTO(DomicilioWSDTO value) {
        this.domicilioWSDTO = value;
    }

    /**
     * Gets the value of the giro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGiro() {
        return giro;
    }

    /**
     * Sets the value of the giro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGiro(String value) {
        this.giro = value;
    }

    /**
     * Gets the value of the nombreCorto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCorto() {
        return nombreCorto;
    }

    /**
     * Sets the value of the nombreCorto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCorto(String value) {
        this.nombreCorto = value;
    }

    /**
     * Gets the value of the nombreOrganizacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    /**
     * Sets the value of the nombreOrganizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOrganizacion(String value) {
        this.nombreOrganizacion = value;
    }

    /**
     * Gets the value of the numeroActaConstitutiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroActaConstitutiva() {
        return numeroActaConstitutiva;
    }

    /**
     * Sets the value of the numeroActaConstitutiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroActaConstitutiva(String value) {
        this.numeroActaConstitutiva = value;
    }

    /**
     * Gets the value of the propositoCiberespacio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropositoCiberespacio() {
        return propositoCiberespacio;
    }

    /**
     * Sets the value of the propositoCiberespacio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropositoCiberespacio(String value) {
        this.propositoCiberespacio = value;
    }

    /**
     * Gets the value of the representanteLegal property.
     * 
     * @return
     *     possible object is
     *     {@link InvolucradoWSDTO }
     *     
     */
    public InvolucradoWSDTO getRepresentanteLegal() {
        return representanteLegal;
    }

    /**
     * Sets the value of the representanteLegal property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvolucradoWSDTO }
     *     
     */
    public void setRepresentanteLegal(InvolucradoWSDTO value) {
        this.representanteLegal = value;
    }

    /**
     * Gets the value of the rfc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Sets the value of the rfc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRfc(String value) {
        this.rfc = value;
    }

    /**
     * Gets the value of the tipoCiberespacio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCiberespacio() {
        return tipoCiberespacio;
    }

    /**
     * Sets the value of the tipoCiberespacio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCiberespacio(String value) {
        this.tipoCiberespacio = value;
    }

    /**
     * Gets the value of the valorByComunidadVirtualVal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorByComunidadVirtualVal() {
        return valorByComunidadVirtualVal;
    }

    /**
     * Sets the value of the valorByComunidadVirtualVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorByComunidadVirtualVal(Long value) {
        this.valorByComunidadVirtualVal = value;
    }

    /**
     * Gets the value of the valorByOrganizacionFormalVal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorByOrganizacionFormalVal() {
        return valorByOrganizacionFormalVal;
    }

    /**
     * Sets the value of the valorByOrganizacionFormalVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorByOrganizacionFormalVal(Long value) {
        this.valorByOrganizacionFormalVal = value;
    }

    /**
     * Gets the value of the valorBySectorGubernamentalVal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorBySectorGubernamentalVal() {
        return valorBySectorGubernamentalVal;
    }

    /**
     * Sets the value of the valorBySectorGubernamentalVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorBySectorGubernamentalVal(Long value) {
        this.valorBySectorGubernamentalVal = value;
    }

    /**
     * Gets the value of the valorByTipoOrganizacionVal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorByTipoOrganizacionVal() {
        return valorByTipoOrganizacionVal;
    }

    /**
     * Sets the value of the valorByTipoOrganizacionVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorByTipoOrganizacionVal(Long value) {
        this.valorByTipoOrganizacionVal = value;
    }

}
