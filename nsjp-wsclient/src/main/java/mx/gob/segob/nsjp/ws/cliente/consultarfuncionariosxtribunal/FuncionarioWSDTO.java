
package mx.gob.segob.nsjp.ws.cliente.consultarfuncionariosxtribunal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for funcionarioWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="funcionarioWSDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.nsjp.segob.gob.mx/}genericWSDTO">
 *       &lt;sequence>
 *         &lt;element name="apellidoMaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellidoPaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="claveFuncionario" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="especialidadId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="jerarquiaOrganizacionalId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreDiscriminante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombrePuesto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puestoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipoEspecialidadId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "funcionarioWSDTO", propOrder = {
    "apellidoMaterno",
    "apellidoPaterno",
    "claveFuncionario",
    "email",
    "especialidadId",
    "jerarquiaOrganizacionalId",
    "nombre",
    "nombreArea",
    "nombreDiscriminante",
    "nombrePuesto",
    "numeroEmpleado",
    "puestoId",
    "tipoEspecialidadId"
})
public class FuncionarioWSDTO
    extends GenericWSDTO
{

    protected String apellidoMaterno;
    protected String apellidoPaterno;
    protected Long claveFuncionario;
    protected String email;
    protected Long especialidadId;
    protected Long jerarquiaOrganizacionalId;
    protected String nombre;
    protected String nombreArea;
    protected String nombreDiscriminante;
    protected String nombrePuesto;
    protected String numeroEmpleado;
    protected Long puestoId;
    protected Long tipoEspecialidadId;

    /**
     * Gets the value of the apellidoMaterno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Sets the value of the apellidoMaterno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoMaterno(String value) {
        this.apellidoMaterno = value;
    }

    /**
     * Gets the value of the apellidoPaterno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Sets the value of the apellidoPaterno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoPaterno(String value) {
        this.apellidoPaterno = value;
    }

    /**
     * Gets the value of the claveFuncionario property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getClaveFuncionario() {
        return claveFuncionario;
    }

    /**
     * Sets the value of the claveFuncionario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setClaveFuncionario(Long value) {
        this.claveFuncionario = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the especialidadId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEspecialidadId() {
        return especialidadId;
    }

    /**
     * Sets the value of the especialidadId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEspecialidadId(Long value) {
        this.especialidadId = value;
    }

    /**
     * Gets the value of the jerarquiaOrganizacionalId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getJerarquiaOrganizacionalId() {
        return jerarquiaOrganizacionalId;
    }

    /**
     * Sets the value of the jerarquiaOrganizacionalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setJerarquiaOrganizacionalId(Long value) {
        this.jerarquiaOrganizacionalId = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the nombreArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreArea() {
        return nombreArea;
    }

    /**
     * Sets the value of the nombreArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreArea(String value) {
        this.nombreArea = value;
    }

    /**
     * Gets the value of the nombreDiscriminante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreDiscriminante() {
        return nombreDiscriminante;
    }

    /**
     * Sets the value of the nombreDiscriminante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreDiscriminante(String value) {
        this.nombreDiscriminante = value;
    }

    /**
     * Gets the value of the nombrePuesto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePuesto() {
        return nombrePuesto;
    }

    /**
     * Sets the value of the nombrePuesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePuesto(String value) {
        this.nombrePuesto = value;
    }

    /**
     * Gets the value of the numeroEmpleado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    /**
     * Sets the value of the numeroEmpleado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroEmpleado(String value) {
        this.numeroEmpleado = value;
    }

    /**
     * Gets the value of the puestoId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPuestoId() {
        return puestoId;
    }

    /**
     * Sets the value of the puestoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPuestoId(Long value) {
        this.puestoId = value;
    }

    /**
     * Gets the value of the tipoEspecialidadId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoEspecialidadId() {
        return tipoEspecialidadId;
    }

    /**
     * Sets the value of the tipoEspecialidadId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoEspecialidadId(Long value) {
        this.tipoEspecialidadId = value;
    }

}
