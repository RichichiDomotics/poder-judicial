
package mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for casoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="casoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="fechaApertura" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="involucradosDTO" type="{http://ws.service.nsjp.segob.gob.mx/}involucradoWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="numeroGeneralCaso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "casoWSDTO", propOrder = {
    "fechaApertura",
    "involucradosDTO",
    "numeroGeneralCaso"
})
public class CasoWSDTO
    extends GenericWSDTO
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaApertura;
    @XmlElement(nillable = true)
    protected List<InvolucradoWSDTO> involucradosDTO;
    protected String numeroGeneralCaso;

    /**
     * Gets the value of the fechaApertura property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaApertura() {
        return fechaApertura;
    }

    /**
     * Sets the value of the fechaApertura property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaApertura(XMLGregorianCalendar value) {
        this.fechaApertura = value;
    }

    /**
     * Gets the value of the involucradosDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the involucradosDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvolucradosDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvolucradoWSDTO }
     * 
     * 
     */
    public List<InvolucradoWSDTO> getInvolucradosDTO() {
        if (involucradosDTO == null) {
            involucradosDTO = new ArrayList<InvolucradoWSDTO>();
        }
        return this.involucradosDTO;
    }

    /**
     * Gets the value of the numeroGeneralCaso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroGeneralCaso() {
        return numeroGeneralCaso;
    }

    /**
     * Sets the value of the numeroGeneralCaso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroGeneralCaso(String value) {
        this.numeroGeneralCaso = value;
    }

}
