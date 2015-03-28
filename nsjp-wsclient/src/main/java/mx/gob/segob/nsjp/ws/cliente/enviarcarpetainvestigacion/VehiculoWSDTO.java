
package mx.gob.segob.nsjp.ws.cliente.enviarcarpetainvestigacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for vehiculoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vehiculoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}objetoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="esBlindado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="modelo" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="noCilindros" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="noMotor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noPuertas" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="noSerie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nrfv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="placa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorByColorVal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorByMarcaVal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorByPaisOrigenVal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorBySubmarcaVal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="valorByTipoVehiculo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vehiculoWSDTO", propOrder = {
    "esBlindado",
    "modelo",
    "noCilindros",
    "noMotor",
    "noPuertas",
    "noSerie",
    "nrfv",
    "placa",
    "valorByColorVal",
    "valorByMarcaVal",
    "valorByPaisOrigenVal",
    "valorBySubmarcaVal",
    "valorByTipoVehiculo"
})
public class VehiculoWSDTO
    extends ObjetoWSDTO
{

    protected Boolean esBlindado;
    protected Short modelo;
    protected Short noCilindros;
    protected String noMotor;
    protected Short noPuertas;
    protected String noSerie;
    protected String nrfv;
    protected String placa;
    protected Long valorByColorVal;
    protected Long valorByMarcaVal;
    protected Long valorByPaisOrigenVal;
    protected Long valorBySubmarcaVal;
    protected Long valorByTipoVehiculo;

    /**
     * Gets the value of the esBlindado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsBlindado() {
        return esBlindado;
    }

    /**
     * Sets the value of the esBlindado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsBlindado(Boolean value) {
        this.esBlindado = value;
    }

    /**
     * Gets the value of the modelo property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getModelo() {
        return modelo;
    }

    /**
     * Sets the value of the modelo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setModelo(Short value) {
        this.modelo = value;
    }

    /**
     * Gets the value of the noCilindros property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getNoCilindros() {
        return noCilindros;
    }

    /**
     * Sets the value of the noCilindros property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setNoCilindros(Short value) {
        this.noCilindros = value;
    }

    /**
     * Gets the value of the noMotor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoMotor() {
        return noMotor;
    }

    /**
     * Sets the value of the noMotor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoMotor(String value) {
        this.noMotor = value;
    }

    /**
     * Gets the value of the noPuertas property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getNoPuertas() {
        return noPuertas;
    }

    /**
     * Sets the value of the noPuertas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setNoPuertas(Short value) {
        this.noPuertas = value;
    }

    /**
     * Gets the value of the noSerie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoSerie() {
        return noSerie;
    }

    /**
     * Sets the value of the noSerie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoSerie(String value) {
        this.noSerie = value;
    }

    /**
     * Gets the value of the nrfv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrfv() {
        return nrfv;
    }

    /**
     * Sets the value of the nrfv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrfv(String value) {
        this.nrfv = value;
    }

    /**
     * Gets the value of the placa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Sets the value of the placa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaca(String value) {
        this.placa = value;
    }

    /**
     * Gets the value of the valorByColorVal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorByColorVal() {
        return valorByColorVal;
    }

    /**
     * Sets the value of the valorByColorVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorByColorVal(Long value) {
        this.valorByColorVal = value;
    }

    /**
     * Gets the value of the valorByMarcaVal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorByMarcaVal() {
        return valorByMarcaVal;
    }

    /**
     * Sets the value of the valorByMarcaVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorByMarcaVal(Long value) {
        this.valorByMarcaVal = value;
    }

    /**
     * Gets the value of the valorByPaisOrigenVal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorByPaisOrigenVal() {
        return valorByPaisOrigenVal;
    }

    /**
     * Sets the value of the valorByPaisOrigenVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorByPaisOrigenVal(Long value) {
        this.valorByPaisOrigenVal = value;
    }

    /**
     * Gets the value of the valorBySubmarcaVal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorBySubmarcaVal() {
        return valorBySubmarcaVal;
    }

    /**
     * Sets the value of the valorBySubmarcaVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorBySubmarcaVal(Long value) {
        this.valorBySubmarcaVal = value;
    }

    /**
     * Gets the value of the valorByTipoVehiculo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorByTipoVehiculo() {
        return valorByTipoVehiculo;
    }

    /**
     * Sets the value of the valorByTipoVehiculo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorByTipoVehiculo(Long value) {
        this.valorByTipoVehiculo = value;
    }

}
