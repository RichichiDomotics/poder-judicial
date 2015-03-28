
package mx.gob.segob.nsjp.ws.cliente.avidohdelictivo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for hechoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hechoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="descNarrativa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="domicilio" type="{http://ws.service.nsjp.segob.gob.mx/}domicilioWSDTO" minOccurs="0"/>
 *         &lt;element name="lugar" type="{http://ws.service.nsjp.segob.gob.mx/}lugarWSDTO" minOccurs="0"/>
 *         &lt;element name="tiempo" type="{http://ws.service.nsjp.segob.gob.mx/}tiempoWSDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hechoWSDTO", propOrder = {
    "descNarrativa",
    "domicilio",
    "lugar",
    "tiempo"
})
public class HechoWSDTO
    extends GenericWSDTO
{

    protected String descNarrativa;
    protected DomicilioWSDTO domicilio;
    protected LugarWSDTO lugar;
    protected TiempoWSDTO tiempo;

    /**
     * Gets the value of the descNarrativa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescNarrativa() {
        return descNarrativa;
    }

    /**
     * Sets the value of the descNarrativa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescNarrativa(String value) {
        this.descNarrativa = value;
    }

    /**
     * Gets the value of the domicilio property.
     * 
     * @return
     *     possible object is
     *     {@link DomicilioWSDTO }
     *     
     */
    public DomicilioWSDTO getDomicilio() {
        return domicilio;
    }

    /**
     * Sets the value of the domicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomicilioWSDTO }
     *     
     */
    public void setDomicilio(DomicilioWSDTO value) {
        this.domicilio = value;
    }

    /**
     * Gets the value of the lugar property.
     * 
     * @return
     *     possible object is
     *     {@link LugarWSDTO }
     *     
     */
    public LugarWSDTO getLugar() {
        return lugar;
    }

    /**
     * Sets the value of the lugar property.
     * 
     * @param value
     *     allowed object is
     *     {@link LugarWSDTO }
     *     
     */
    public void setLugar(LugarWSDTO value) {
        this.lugar = value;
    }

    /**
     * Gets the value of the tiempo property.
     * 
     * @return
     *     possible object is
     *     {@link TiempoWSDTO }
     *     
     */
    public TiempoWSDTO getTiempo() {
        return tiempo;
    }

    /**
     * Sets the value of the tiempo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TiempoWSDTO }
     *     
     */
    public void setTiempo(TiempoWSDTO value) {
        this.tiempo = value;
    }

}
