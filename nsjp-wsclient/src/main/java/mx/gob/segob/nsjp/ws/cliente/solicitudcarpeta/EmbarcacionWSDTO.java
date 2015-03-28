
package mx.gob.segob.nsjp.ws.cliente.solicitudcarpeta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for embarcacionWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="embarcacionWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}objetoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="marca" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="matricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noMotor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noSerie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreEmbarcacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paisOrigen" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="subMarca" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipoEmbarcacion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "embarcacionWSDTO", propOrder = {
    "color",
    "marca",
    "matricula",
    "noMotor",
    "noSerie",
    "nombreEmbarcacion",
    "paisOrigen",
    "subMarca",
    "tipoEmbarcacion"
})
public class EmbarcacionWSDTO
    extends ObjetoWSDTO
{

    protected Long color;
    protected Long marca;
    protected String matricula;
    protected String noMotor;
    protected String noSerie;
    protected String nombreEmbarcacion;
    protected Long paisOrigen;
    protected Long subMarca;
    protected Long tipoEmbarcacion;

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setColor(Long value) {
        this.color = value;
    }

    /**
     * Gets the value of the marca property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMarca() {
        return marca;
    }

    /**
     * Sets the value of the marca property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMarca(Long value) {
        this.marca = value;
    }

    /**
     * Gets the value of the matricula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Sets the value of the matricula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatricula(String value) {
        this.matricula = value;
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
     * Gets the value of the nombreEmbarcacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEmbarcacion() {
        return nombreEmbarcacion;
    }

    /**
     * Sets the value of the nombreEmbarcacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEmbarcacion(String value) {
        this.nombreEmbarcacion = value;
    }

    /**
     * Gets the value of the paisOrigen property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPaisOrigen() {
        return paisOrigen;
    }

    /**
     * Sets the value of the paisOrigen property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPaisOrigen(Long value) {
        this.paisOrigen = value;
    }

    /**
     * Gets the value of the subMarca property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSubMarca() {
        return subMarca;
    }

    /**
     * Sets the value of the subMarca property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubMarca(Long value) {
        this.subMarca = value;
    }

    /**
     * Gets the value of the tipoEmbarcacion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    /**
     * Sets the value of the tipoEmbarcacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoEmbarcacion(Long value) {
        this.tipoEmbarcacion = value;
    }

}
