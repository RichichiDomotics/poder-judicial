
package mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inmuebleWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inmuebleWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}objetoWSDTO">
 *       &lt;sequence>
 *         &lt;element name="superficie" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tipoConstruccion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipoInmueble" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="unidadMedidaConstruccion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="unidadMedidaTerreno" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mConstruccion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inmuebleWSDTO", propOrder = {
    "superficie",
    "tipoConstruccion",
    "tipoInmueble",
    "unidadMedidaConstruccion",
    "unidadMedidaTerreno",
    "mConstruccion"
})
public class InmuebleWSDTO
    extends ObjetoWSDTO
{

    protected Integer superficie;
    protected Long tipoConstruccion;
    protected Long tipoInmueble;
    protected Long unidadMedidaConstruccion;
    protected Long unidadMedidaTerreno;
    protected Integer mConstruccion;

    /**
     * Gets the value of the superficie property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSuperficie() {
        return superficie;
    }

    /**
     * Sets the value of the superficie property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSuperficie(Integer value) {
        this.superficie = value;
    }

    /**
     * Gets the value of the tipoConstruccion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoConstruccion() {
        return tipoConstruccion;
    }

    /**
     * Sets the value of the tipoConstruccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoConstruccion(Long value) {
        this.tipoConstruccion = value;
    }

    /**
     * Gets the value of the tipoInmueble property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoInmueble() {
        return tipoInmueble;
    }

    /**
     * Sets the value of the tipoInmueble property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoInmueble(Long value) {
        this.tipoInmueble = value;
    }

    /**
     * Gets the value of the unidadMedidaConstruccion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUnidadMedidaConstruccion() {
        return unidadMedidaConstruccion;
    }

    /**
     * Sets the value of the unidadMedidaConstruccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUnidadMedidaConstruccion(Long value) {
        this.unidadMedidaConstruccion = value;
    }

    /**
     * Gets the value of the unidadMedidaTerreno property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUnidadMedidaTerreno() {
        return unidadMedidaTerreno;
    }

    /**
     * Sets the value of the unidadMedidaTerreno property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUnidadMedidaTerreno(Long value) {
        this.unidadMedidaTerreno = value;
    }

    /**
     * Gets the value of the mConstruccion property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMConstruccion() {
        return mConstruccion;
    }

    /**
     * Sets the value of the mConstruccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMConstruccion(Integer value) {
        this.mConstruccion = value;
    }

}
