
package mx.gob.segob.nsjp.ws.cliente.solicitudcarpeta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for domicilioWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="domicilioWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}lugarWSDTO">
 *       &lt;sequence>
 *         &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="asentamientoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ciudadId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="edificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entidadId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="entreCalle1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entreCalle2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="municipioId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="numeroExterior" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroInterior" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroLote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="referencias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorCalleId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "domicilioWSDTO", propOrder = {
    "alias",
    "asentamientoId",
    "calle",
    "ciudadId",
    "edificio",
    "entidadId",
    "entreCalle1",
    "entreCalle2",
    "municipioId",
    "numeroExterior",
    "numeroInterior",
    "numeroLote",
    "referencias",
    "valorCalleId"
})
public class DomicilioWSDTO
    extends LugarWSDTO
{

    protected String alias;
    protected Long asentamientoId;
    protected String calle;
    protected Long ciudadId;
    protected String edificio;
    protected Long entidadId;
    protected String entreCalle1;
    protected String entreCalle2;
    protected Long municipioId;
    protected String numeroExterior;
    protected String numeroInterior;
    protected String numeroLote;
    protected String referencias;
    protected Long valorCalleId;

    /**
     * Gets the value of the alias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlias(String value) {
        this.alias = value;
    }

    /**
     * Gets the value of the asentamientoId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAsentamientoId() {
        return asentamientoId;
    }

    /**
     * Sets the value of the asentamientoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAsentamientoId(Long value) {
        this.asentamientoId = value;
    }

    /**
     * Gets the value of the calle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Sets the value of the calle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalle(String value) {
        this.calle = value;
    }

    /**
     * Gets the value of the ciudadId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCiudadId() {
        return ciudadId;
    }

    /**
     * Sets the value of the ciudadId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCiudadId(Long value) {
        this.ciudadId = value;
    }

    /**
     * Gets the value of the edificio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEdificio() {
        return edificio;
    }

    /**
     * Sets the value of the edificio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEdificio(String value) {
        this.edificio = value;
    }

    /**
     * Gets the value of the entidadId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEntidadId() {
        return entidadId;
    }

    /**
     * Sets the value of the entidadId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEntidadId(Long value) {
        this.entidadId = value;
    }

    /**
     * Gets the value of the entreCalle1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntreCalle1() {
        return entreCalle1;
    }

    /**
     * Sets the value of the entreCalle1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntreCalle1(String value) {
        this.entreCalle1 = value;
    }

    /**
     * Gets the value of the entreCalle2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntreCalle2() {
        return entreCalle2;
    }

    /**
     * Sets the value of the entreCalle2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntreCalle2(String value) {
        this.entreCalle2 = value;
    }

    /**
     * Gets the value of the municipioId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMunicipioId() {
        return municipioId;
    }

    /**
     * Sets the value of the municipioId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMunicipioId(Long value) {
        this.municipioId = value;
    }

    /**
     * Gets the value of the numeroExterior property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroExterior() {
        return numeroExterior;
    }

    /**
     * Sets the value of the numeroExterior property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroExterior(String value) {
        this.numeroExterior = value;
    }

    /**
     * Gets the value of the numeroInterior property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroInterior() {
        return numeroInterior;
    }

    /**
     * Sets the value of the numeroInterior property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroInterior(String value) {
        this.numeroInterior = value;
    }

    /**
     * Gets the value of the numeroLote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroLote() {
        return numeroLote;
    }

    /**
     * Sets the value of the numeroLote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroLote(String value) {
        this.numeroLote = value;
    }

    /**
     * Gets the value of the referencias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencias() {
        return referencias;
    }

    /**
     * Sets the value of the referencias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencias(String value) {
        this.referencias = value;
    }

    /**
     * Gets the value of the valorCalleId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorCalleId() {
        return valorCalleId;
    }

    /**
     * Sets the value of the valorCalleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorCalleId(Long value) {
        this.valorCalleId = value;
    }

}
