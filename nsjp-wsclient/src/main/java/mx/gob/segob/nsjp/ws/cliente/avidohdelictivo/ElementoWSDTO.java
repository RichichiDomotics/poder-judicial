
package mx.gob.segob.nsjp.ws.cliente.avidohdelictivo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for elementoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="elementoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="calidad" type="{http://ws.service.nsjp.segob.gob.mx/}calidadWSDTO" minOccurs="0"/>
 *         &lt;element name="folioElemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fotoDelElemento" type="{http://ws.service.nsjp.segob.gob.mx/}archivoDigitalWSDTO" minOccurs="0"/>
 *         &lt;element name="strDescripcionRelacionarElemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorIdElemento" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "elementoWSDTO", propOrder = {
    "calidad",
    "folioElemento",
    "fotoDelElemento",
    "strDescripcionRelacionarElemento",
    "valorIdElemento"
})
@XmlSeeAlso({
    OrganizacionWSDTO.class,
    LugarWSDTO.class,
    PersonaWSDTO.class
})
public class ElementoWSDTO
    extends GenericWSDTO
{

    protected CalidadWSDTO calidad;
    protected String folioElemento;
    protected ArchivoDigitalWSDTO fotoDelElemento;
    protected String strDescripcionRelacionarElemento;
    protected Long valorIdElemento;

    /**
     * Gets the value of the calidad property.
     * 
     * @return
     *     possible object is
     *     {@link CalidadWSDTO }
     *     
     */
    public CalidadWSDTO getCalidad() {
        return calidad;
    }

    /**
     * Sets the value of the calidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalidadWSDTO }
     *     
     */
    public void setCalidad(CalidadWSDTO value) {
        this.calidad = value;
    }

    /**
     * Gets the value of the folioElemento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioElemento() {
        return folioElemento;
    }

    /**
     * Sets the value of the folioElemento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioElemento(String value) {
        this.folioElemento = value;
    }

    /**
     * Gets the value of the fotoDelElemento property.
     * 
     * @return
     *     possible object is
     *     {@link ArchivoDigitalWSDTO }
     *     
     */
    public ArchivoDigitalWSDTO getFotoDelElemento() {
        return fotoDelElemento;
    }

    /**
     * Sets the value of the fotoDelElemento property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArchivoDigitalWSDTO }
     *     
     */
    public void setFotoDelElemento(ArchivoDigitalWSDTO value) {
        this.fotoDelElemento = value;
    }

    /**
     * Gets the value of the strDescripcionRelacionarElemento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrDescripcionRelacionarElemento() {
        return strDescripcionRelacionarElemento;
    }

    /**
     * Sets the value of the strDescripcionRelacionarElemento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrDescripcionRelacionarElemento(String value) {
        this.strDescripcionRelacionarElemento = value;
    }

    /**
     * Gets the value of the valorIdElemento property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValorIdElemento() {
        return valorIdElemento;
    }

    /**
     * Sets the value of the valorIdElemento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValorIdElemento(Long value) {
        this.valorIdElemento = value;
    }

}
