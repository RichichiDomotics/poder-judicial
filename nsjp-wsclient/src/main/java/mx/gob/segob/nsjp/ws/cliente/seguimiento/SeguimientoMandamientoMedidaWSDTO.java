
package mx.gob.segob.nsjp.ws.cliente.seguimiento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for seguimientoMandamientoMedidaWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="seguimientoMandamientoMedidaWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="archivoDigital" type="{http://ws.service.nsjp.segob.gob.mx/}archivoDigitalWSDTO" minOccurs="0"/>
 *         &lt;element name="folioDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idEstatus" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipoOperacion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "seguimientoMandamientoMedidaWSDTO", propOrder = {
    "archivoDigital",
    "folioDocumento",
    "idEstatus",
    "tipoOperacion"
})
public class SeguimientoMandamientoMedidaWSDTO
    extends GenericWSDTO
{

    protected ArchivoDigitalWSDTO archivoDigital;
    protected String folioDocumento;
    protected Long idEstatus;
    protected Long tipoOperacion;

    /**
     * Gets the value of the archivoDigital property.
     * 
     * @return
     *     possible object is
     *     {@link ArchivoDigitalWSDTO }
     *     
     */
    public ArchivoDigitalWSDTO getArchivoDigital() {
        return archivoDigital;
    }

    /**
     * Sets the value of the archivoDigital property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArchivoDigitalWSDTO }
     *     
     */
    public void setArchivoDigital(ArchivoDigitalWSDTO value) {
        this.archivoDigital = value;
    }

    /**
     * Gets the value of the folioDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioDocumento() {
        return folioDocumento;
    }

    /**
     * Sets the value of the folioDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioDocumento(String value) {
        this.folioDocumento = value;
    }

    /**
     * Gets the value of the idEstatus property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdEstatus() {
        return idEstatus;
    }

    /**
     * Sets the value of the idEstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdEstatus(Long value) {
        this.idEstatus = value;
    }

    /**
     * Gets the value of the tipoOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Sets the value of the tipoOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoOperacion(Long value) {
        this.tipoOperacion = value;
    }

}
