
package mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rolWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rolWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="descripcionRol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreRol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rolId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rolWSDTO", propOrder = {
    "descripcionRol",
    "nombreRol",
    "rolId"
})
public class RolWSDTO
    extends GenericWSDTO
{

    protected String descripcionRol;
    protected String nombreRol;
    protected Long rolId;

    /**
     * Gets the value of the descripcionRol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionRol() {
        return descripcionRol;
    }

    /**
     * Sets the value of the descripcionRol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionRol(String value) {
        this.descripcionRol = value;
    }

    /**
     * Gets the value of the nombreRol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * Sets the value of the nombreRol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreRol(String value) {
        this.nombreRol = value;
    }

    /**
     * Gets the value of the rolId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRolId() {
        return rolId;
    }

    /**
     * Sets the value of the rolId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRolId(Long value) {
        this.rolId = value;
    }

}
