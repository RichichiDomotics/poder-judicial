
package mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aeronaveWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aeronaveWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}objetoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="marca" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="matricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modelo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noMotor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noSerie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paisProcedencia" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="subMarca" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipoAeroNave" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aeronaveWSDTO", propOrder = {
    "color",
    "marca",
    "matricula",
    "modelo",
    "noMotor",
    "noSerie",
    "paisProcedencia",
    "subMarca",
    "tipoAeroNave"
})
public class AeronaveWSDTO
    extends ObjetoWSDTO
{

    protected Long color;
    protected Long marca;
    protected String matricula;
    protected String modelo;
    protected String noMotor;
    protected String noSerie;
    protected Long paisProcedencia;
    protected Long subMarca;
    protected Long tipoAeroNave;

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
     * Gets the value of the modelo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Sets the value of the modelo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelo(String value) {
        this.modelo = value;
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
     * Gets the value of the paisProcedencia property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPaisProcedencia() {
        return paisProcedencia;
    }

    /**
     * Sets the value of the paisProcedencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPaisProcedencia(Long value) {
        this.paisProcedencia = value;
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
     * Gets the value of the tipoAeroNave property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoAeroNave() {
        return tipoAeroNave;
    }

    /**
     * Sets the value of the tipoAeroNave property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoAeroNave(Long value) {
        this.tipoAeroNave = value;
    }

}
