
package mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mediaFiliacionWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mediaFiliacionWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="alturaNariz" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="alturaNasoLabial" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="anchoNariz" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="baseNariz" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="cabelloCantidad" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="cabelloImplantacion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="calvicieTipo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="colorCabello" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="colorOjos" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="complexion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="direccionCeja" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="dorsoNariz" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="espesorLabioInf" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="espesorLabioSup" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="estatura" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="factorRH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formaCabello" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="formaCeja" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="formaMenton" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="formaOjos" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="formaOreja" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="frenteAltura" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="frenteAncho" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="frenteInclinacion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="helixAdherencia" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="helixContorno" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="helixOriginal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="helixPosterior" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="helixSuperior" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="implantacionCeja" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="inclinacionMenton" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="labioComisuras" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="labiosProminencia" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="orejaLobAdherencia" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="orejaLobContorno" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="orejaLobDimension" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="orejaLobParticularidad" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="orejaTamanio" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="perfil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="peso" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="raizNariz" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="seniaParticular" type="{http://ws.service.nsjp.segob.gob.mx/}seniaParticularWSDTO" minOccurs="0"/>
 *         &lt;element name="tamanioBoca" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tamanioCeja" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tamanioOjo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tez" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tieneBarba" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tieneBigote" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tipoCara" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipoMenton" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipoSangre" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="usaLentes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mediaFiliacionWSDTO", propOrder = {
    "alturaNariz",
    "alturaNasoLabial",
    "anchoNariz",
    "baseNariz",
    "cabelloCantidad",
    "cabelloImplantacion",
    "calvicieTipo",
    "colorCabello",
    "colorOjos",
    "complexion",
    "direccionCeja",
    "dorsoNariz",
    "espesorLabioInf",
    "espesorLabioSup",
    "estatura",
    "factorRH",
    "formaCabello",
    "formaCeja",
    "formaMenton",
    "formaOjos",
    "formaOreja",
    "frenteAltura",
    "frenteAncho",
    "frenteInclinacion",
    "helixAdherencia",
    "helixContorno",
    "helixOriginal",
    "helixPosterior",
    "helixSuperior",
    "implantacionCeja",
    "inclinacionMenton",
    "labioComisuras",
    "labiosProminencia",
    "orejaLobAdherencia",
    "orejaLobContorno",
    "orejaLobDimension",
    "orejaLobParticularidad",
    "orejaTamanio",
    "perfil",
    "peso",
    "raizNariz",
    "seniaParticular",
    "tamanioBoca",
    "tamanioCeja",
    "tamanioOjo",
    "tez",
    "tieneBarba",
    "tieneBigote",
    "tipoCara",
    "tipoMenton",
    "tipoSangre",
    "usaLentes"
})
public class MediaFiliacionWSDTO
    extends GenericWSDTO
{

    protected Long alturaNariz;
    protected Long alturaNasoLabial;
    protected Long anchoNariz;
    protected Long baseNariz;
    protected Long cabelloCantidad;
    protected Long cabelloImplantacion;
    protected Long calvicieTipo;
    protected Long colorCabello;
    protected Long colorOjos;
    protected Long complexion;
    protected Long direccionCeja;
    protected Long dorsoNariz;
    protected Long espesorLabioInf;
    protected Long espesorLabioSup;
    protected Double estatura;
    protected String factorRH;
    protected Long formaCabello;
    protected Long formaCeja;
    protected Long formaMenton;
    protected Long formaOjos;
    protected Long formaOreja;
    protected Long frenteAltura;
    protected Long frenteAncho;
    protected Long frenteInclinacion;
    protected Long helixAdherencia;
    protected Long helixContorno;
    protected Long helixOriginal;
    protected Long helixPosterior;
    protected Long helixSuperior;
    protected Long implantacionCeja;
    protected Long inclinacionMenton;
    protected Long labioComisuras;
    protected Long labiosProminencia;
    protected Long orejaLobAdherencia;
    protected Long orejaLobContorno;
    protected Long orejaLobDimension;
    protected Long orejaLobParticularidad;
    protected Long orejaTamanio;
    protected String perfil;
    protected Double peso;
    protected Long raizNariz;
    protected SeniaParticularWSDTO seniaParticular;
    protected Long tamanioBoca;
    protected Long tamanioCeja;
    protected Long tamanioOjo;
    protected Long tez;
    protected Boolean tieneBarba;
    protected Boolean tieneBigote;
    protected Long tipoCara;
    protected Long tipoMenton;
    protected Long tipoSangre;
    protected Boolean usaLentes;

    /**
     * Gets the value of the alturaNariz property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAlturaNariz() {
        return alturaNariz;
    }

    /**
     * Sets the value of the alturaNariz property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAlturaNariz(Long value) {
        this.alturaNariz = value;
    }

    /**
     * Gets the value of the alturaNasoLabial property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAlturaNasoLabial() {
        return alturaNasoLabial;
    }

    /**
     * Sets the value of the alturaNasoLabial property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAlturaNasoLabial(Long value) {
        this.alturaNasoLabial = value;
    }

    /**
     * Gets the value of the anchoNariz property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAnchoNariz() {
        return anchoNariz;
    }

    /**
     * Sets the value of the anchoNariz property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAnchoNariz(Long value) {
        this.anchoNariz = value;
    }

    /**
     * Gets the value of the baseNariz property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBaseNariz() {
        return baseNariz;
    }

    /**
     * Sets the value of the baseNariz property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBaseNariz(Long value) {
        this.baseNariz = value;
    }

    /**
     * Gets the value of the cabelloCantidad property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCabelloCantidad() {
        return cabelloCantidad;
    }

    /**
     * Sets the value of the cabelloCantidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCabelloCantidad(Long value) {
        this.cabelloCantidad = value;
    }

    /**
     * Gets the value of the cabelloImplantacion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCabelloImplantacion() {
        return cabelloImplantacion;
    }

    /**
     * Sets the value of the cabelloImplantacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCabelloImplantacion(Long value) {
        this.cabelloImplantacion = value;
    }

    /**
     * Gets the value of the calvicieTipo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCalvicieTipo() {
        return calvicieTipo;
    }

    /**
     * Sets the value of the calvicieTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCalvicieTipo(Long value) {
        this.calvicieTipo = value;
    }

    /**
     * Gets the value of the colorCabello property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getColorCabello() {
        return colorCabello;
    }

    /**
     * Sets the value of the colorCabello property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setColorCabello(Long value) {
        this.colorCabello = value;
    }

    /**
     * Gets the value of the colorOjos property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getColorOjos() {
        return colorOjos;
    }

    /**
     * Sets the value of the colorOjos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setColorOjos(Long value) {
        this.colorOjos = value;
    }

    /**
     * Gets the value of the complexion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getComplexion() {
        return complexion;
    }

    /**
     * Sets the value of the complexion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setComplexion(Long value) {
        this.complexion = value;
    }

    /**
     * Gets the value of the direccionCeja property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDireccionCeja() {
        return direccionCeja;
    }

    /**
     * Sets the value of the direccionCeja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDireccionCeja(Long value) {
        this.direccionCeja = value;
    }

    /**
     * Gets the value of the dorsoNariz property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDorsoNariz() {
        return dorsoNariz;
    }

    /**
     * Sets the value of the dorsoNariz property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDorsoNariz(Long value) {
        this.dorsoNariz = value;
    }

    /**
     * Gets the value of the espesorLabioInf property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEspesorLabioInf() {
        return espesorLabioInf;
    }

    /**
     * Sets the value of the espesorLabioInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEspesorLabioInf(Long value) {
        this.espesorLabioInf = value;
    }

    /**
     * Gets the value of the espesorLabioSup property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEspesorLabioSup() {
        return espesorLabioSup;
    }

    /**
     * Sets the value of the espesorLabioSup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEspesorLabioSup(Long value) {
        this.espesorLabioSup = value;
    }

    /**
     * Gets the value of the estatura property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEstatura() {
        return estatura;
    }

    /**
     * Sets the value of the estatura property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEstatura(Double value) {
        this.estatura = value;
    }

    /**
     * Gets the value of the factorRH property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactorRH() {
        return factorRH;
    }

    /**
     * Sets the value of the factorRH property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactorRH(String value) {
        this.factorRH = value;
    }

    /**
     * Gets the value of the formaCabello property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFormaCabello() {
        return formaCabello;
    }

    /**
     * Sets the value of the formaCabello property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFormaCabello(Long value) {
        this.formaCabello = value;
    }

    /**
     * Gets the value of the formaCeja property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFormaCeja() {
        return formaCeja;
    }

    /**
     * Sets the value of the formaCeja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFormaCeja(Long value) {
        this.formaCeja = value;
    }

    /**
     * Gets the value of the formaMenton property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFormaMenton() {
        return formaMenton;
    }

    /**
     * Sets the value of the formaMenton property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFormaMenton(Long value) {
        this.formaMenton = value;
    }

    /**
     * Gets the value of the formaOjos property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFormaOjos() {
        return formaOjos;
    }

    /**
     * Sets the value of the formaOjos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFormaOjos(Long value) {
        this.formaOjos = value;
    }

    /**
     * Gets the value of the formaOreja property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFormaOreja() {
        return formaOreja;
    }

    /**
     * Sets the value of the formaOreja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFormaOreja(Long value) {
        this.formaOreja = value;
    }

    /**
     * Gets the value of the frenteAltura property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFrenteAltura() {
        return frenteAltura;
    }

    /**
     * Sets the value of the frenteAltura property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFrenteAltura(Long value) {
        this.frenteAltura = value;
    }

    /**
     * Gets the value of the frenteAncho property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFrenteAncho() {
        return frenteAncho;
    }

    /**
     * Sets the value of the frenteAncho property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFrenteAncho(Long value) {
        this.frenteAncho = value;
    }

    /**
     * Gets the value of the frenteInclinacion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFrenteInclinacion() {
        return frenteInclinacion;
    }

    /**
     * Sets the value of the frenteInclinacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFrenteInclinacion(Long value) {
        this.frenteInclinacion = value;
    }

    /**
     * Gets the value of the helixAdherencia property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHelixAdherencia() {
        return helixAdherencia;
    }

    /**
     * Sets the value of the helixAdherencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHelixAdherencia(Long value) {
        this.helixAdherencia = value;
    }

    /**
     * Gets the value of the helixContorno property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHelixContorno() {
        return helixContorno;
    }

    /**
     * Sets the value of the helixContorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHelixContorno(Long value) {
        this.helixContorno = value;
    }

    /**
     * Gets the value of the helixOriginal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHelixOriginal() {
        return helixOriginal;
    }

    /**
     * Sets the value of the helixOriginal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHelixOriginal(Long value) {
        this.helixOriginal = value;
    }

    /**
     * Gets the value of the helixPosterior property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHelixPosterior() {
        return helixPosterior;
    }

    /**
     * Sets the value of the helixPosterior property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHelixPosterior(Long value) {
        this.helixPosterior = value;
    }

    /**
     * Gets the value of the helixSuperior property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHelixSuperior() {
        return helixSuperior;
    }

    /**
     * Sets the value of the helixSuperior property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHelixSuperior(Long value) {
        this.helixSuperior = value;
    }

    /**
     * Gets the value of the implantacionCeja property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getImplantacionCeja() {
        return implantacionCeja;
    }

    /**
     * Sets the value of the implantacionCeja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setImplantacionCeja(Long value) {
        this.implantacionCeja = value;
    }

    /**
     * Gets the value of the inclinacionMenton property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInclinacionMenton() {
        return inclinacionMenton;
    }

    /**
     * Sets the value of the inclinacionMenton property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInclinacionMenton(Long value) {
        this.inclinacionMenton = value;
    }

    /**
     * Gets the value of the labioComisuras property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLabioComisuras() {
        return labioComisuras;
    }

    /**
     * Sets the value of the labioComisuras property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLabioComisuras(Long value) {
        this.labioComisuras = value;
    }

    /**
     * Gets the value of the labiosProminencia property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLabiosProminencia() {
        return labiosProminencia;
    }

    /**
     * Sets the value of the labiosProminencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLabiosProminencia(Long value) {
        this.labiosProminencia = value;
    }

    /**
     * Gets the value of the orejaLobAdherencia property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrejaLobAdherencia() {
        return orejaLobAdherencia;
    }

    /**
     * Sets the value of the orejaLobAdherencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrejaLobAdherencia(Long value) {
        this.orejaLobAdherencia = value;
    }

    /**
     * Gets the value of the orejaLobContorno property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrejaLobContorno() {
        return orejaLobContorno;
    }

    /**
     * Sets the value of the orejaLobContorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrejaLobContorno(Long value) {
        this.orejaLobContorno = value;
    }

    /**
     * Gets the value of the orejaLobDimension property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrejaLobDimension() {
        return orejaLobDimension;
    }

    /**
     * Sets the value of the orejaLobDimension property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrejaLobDimension(Long value) {
        this.orejaLobDimension = value;
    }

    /**
     * Gets the value of the orejaLobParticularidad property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrejaLobParticularidad() {
        return orejaLobParticularidad;
    }

    /**
     * Sets the value of the orejaLobParticularidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrejaLobParticularidad(Long value) {
        this.orejaLobParticularidad = value;
    }

    /**
     * Gets the value of the orejaTamanio property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrejaTamanio() {
        return orejaTamanio;
    }

    /**
     * Sets the value of the orejaTamanio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrejaTamanio(Long value) {
        this.orejaTamanio = value;
    }

    /**
     * Gets the value of the perfil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * Sets the value of the perfil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerfil(String value) {
        this.perfil = value;
    }

    /**
     * Gets the value of the peso property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * Sets the value of the peso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPeso(Double value) {
        this.peso = value;
    }

    /**
     * Gets the value of the raizNariz property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRaizNariz() {
        return raizNariz;
    }

    /**
     * Sets the value of the raizNariz property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRaizNariz(Long value) {
        this.raizNariz = value;
    }

    /**
     * Gets the value of the seniaParticular property.
     * 
     * @return
     *     possible object is
     *     {@link SeniaParticularWSDTO }
     *     
     */
    public SeniaParticularWSDTO getSeniaParticular() {
        return seniaParticular;
    }

    /**
     * Sets the value of the seniaParticular property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeniaParticularWSDTO }
     *     
     */
    public void setSeniaParticular(SeniaParticularWSDTO value) {
        this.seniaParticular = value;
    }

    /**
     * Gets the value of the tamanioBoca property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTamanioBoca() {
        return tamanioBoca;
    }

    /**
     * Sets the value of the tamanioBoca property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTamanioBoca(Long value) {
        this.tamanioBoca = value;
    }

    /**
     * Gets the value of the tamanioCeja property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTamanioCeja() {
        return tamanioCeja;
    }

    /**
     * Sets the value of the tamanioCeja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTamanioCeja(Long value) {
        this.tamanioCeja = value;
    }

    /**
     * Gets the value of the tamanioOjo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTamanioOjo() {
        return tamanioOjo;
    }

    /**
     * Sets the value of the tamanioOjo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTamanioOjo(Long value) {
        this.tamanioOjo = value;
    }

    /**
     * Gets the value of the tez property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTez() {
        return tez;
    }

    /**
     * Sets the value of the tez property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTez(Long value) {
        this.tez = value;
    }

    /**
     * Gets the value of the tieneBarba property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneBarba() {
        return tieneBarba;
    }

    /**
     * Sets the value of the tieneBarba property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneBarba(Boolean value) {
        this.tieneBarba = value;
    }

    /**
     * Gets the value of the tieneBigote property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneBigote() {
        return tieneBigote;
    }

    /**
     * Sets the value of the tieneBigote property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneBigote(Boolean value) {
        this.tieneBigote = value;
    }

    /**
     * Gets the value of the tipoCara property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoCara() {
        return tipoCara;
    }

    /**
     * Sets the value of the tipoCara property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoCara(Long value) {
        this.tipoCara = value;
    }

    /**
     * Gets the value of the tipoMenton property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoMenton() {
        return tipoMenton;
    }

    /**
     * Sets the value of the tipoMenton property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoMenton(Long value) {
        this.tipoMenton = value;
    }

    /**
     * Gets the value of the tipoSangre property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoSangre() {
        return tipoSangre;
    }

    /**
     * Sets the value of the tipoSangre property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoSangre(Long value) {
        this.tipoSangre = value;
    }

    /**
     * Gets the value of the usaLentes property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUsaLentes() {
        return usaLentes;
    }

    /**
     * Sets the value of the usaLentes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUsaLentes(Boolean value) {
        this.usaLentes = value;
    }

}
