
package mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for criterioConsultaFuncionarioExternoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="criterioConsultaFuncionarioExternoWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="expedienteWSDTO" type="{http://ws.service.nsjp.segob.gob.mx/}expedienteWSDTO" minOccurs="0"/>
 *         &lt;element name="funcionarioWSDTO" type="{http://ws.service.nsjp.segob.gob.mx/}funcionarioWSDTO" minOccurs="0"/>
 *         &lt;element name="rolWSDTO" type="{http://ws.service.nsjp.segob.gob.mx/}rolWSDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "criterioConsultaFuncionarioExternoWSDTO", propOrder = {
    "expedienteWSDTO",
    "funcionarioWSDTO",
    "rolWSDTO"
})
public class CriterioConsultaFuncionarioExternoWSDTO
    extends GenericWSDTO
{

    protected ExpedienteWSDTO expedienteWSDTO;
    protected FuncionarioWSDTO funcionarioWSDTO;
    protected RolWSDTO rolWSDTO;

    /**
     * Gets the value of the expedienteWSDTO property.
     * 
     * @return
     *     possible object is
     *     {@link ExpedienteWSDTO }
     *     
     */
    public ExpedienteWSDTO getExpedienteWSDTO() {
        return expedienteWSDTO;
    }

    /**
     * Sets the value of the expedienteWSDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpedienteWSDTO }
     *     
     */
    public void setExpedienteWSDTO(ExpedienteWSDTO value) {
        this.expedienteWSDTO = value;
    }

    /**
     * Gets the value of the funcionarioWSDTO property.
     * 
     * @return
     *     possible object is
     *     {@link FuncionarioWSDTO }
     *     
     */
    public FuncionarioWSDTO getFuncionarioWSDTO() {
        return funcionarioWSDTO;
    }

    /**
     * Sets the value of the funcionarioWSDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link FuncionarioWSDTO }
     *     
     */
    public void setFuncionarioWSDTO(FuncionarioWSDTO value) {
        this.funcionarioWSDTO = value;
    }

    /**
     * Gets the value of the rolWSDTO property.
     * 
     * @return
     *     possible object is
     *     {@link RolWSDTO }
     *     
     */
    public RolWSDTO getRolWSDTO() {
        return rolWSDTO;
    }

    /**
     * Sets the value of the rolWSDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link RolWSDTO }
     *     
     */
    public void setRolWSDTO(RolWSDTO value) {
        this.rolWSDTO = value;
    }

}
