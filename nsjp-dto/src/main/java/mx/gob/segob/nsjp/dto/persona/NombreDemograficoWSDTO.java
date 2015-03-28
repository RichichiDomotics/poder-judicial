/**
*
* Nombre del Programa : NombreDemograficoWSDTO.java                                    
* Autor                            : GustavoBP                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 22/07/2012 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Nombre Demografico.                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.dto.persona;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Nombre Demografico
 * @author GustavoBP
 * @version 1.0
 */
public class NombreDemograficoWSDTO extends GenericWSDTO {

    private Long nombreDemograficoId;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String sexo;
    private String rfc;
    private String curp;
    private Date fechaNacimiento;
    private String strFechaNacimiento;
    private Short edadAproximada;
    private Boolean esVerdadero;
    private String lugarNacimiento;
    private Long paisValorId;
    private Long municipioId;
    private Long entidadFederativaId;

    /**
     * Método de acceso al campo nombreDemograficoId.
     * 
     * @return El valor del campo nombreDemograficoId
     */
    public Long getNombreDemograficoId() {
        return nombreDemograficoId;
    }

    public String getNombreCompleto() {
        final StringBuffer nc = new StringBuffer();
        if (StringUtils.isNotBlank(nombre)) {
            nc.append(nombre).append(" ");
        }
        if (StringUtils.isNotBlank(apellidoPaterno)) {
            nc.append(apellidoPaterno).append(" ");
        }
        if (StringUtils.isNotBlank(apellidoMaterno)) {
            nc.append(apellidoMaterno);
        }
        return nc.toString().trim();
    }

    /**
     * Asigna el valor al campo nombreDemograficoId.
     * 
     * @param nombreDemograficoId
     *            el valor nombreDemograficoId a asignar
     */
    public void setNombreDemograficoId(Long nombreDemograficoId) {
        this.nombreDemograficoId = nombreDemograficoId;
    }
    /**
     * Método de acceso al campo nombre.
     * 
     * @return El valor del campo nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Asigna el valor al campo nombre.
     * 
     * @param nombre
     *            el valor nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Método de acceso al campo apellidoPaterno.
     * 
     * @return El valor del campo apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    /**
     * Asigna el valor al campo apellidoPaterno.
     * 
     * @param apellidoPaterno
     *            el valor apellidoPaterno a asignar
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    /**
     * Método de acceso al campo apellidoMaterno.
     * 
     * @return El valor del campo apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    /**
     * Asigna el valor al campo apellidoMaterno.
     * 
     * @param apellidoMaterno
     *            el valor apellidoMaterno a asignar
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }
    /**
     * @param sexo
     *            the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    /**
     * @return the crfc
     */
    public String getRfc() {
        return rfc;
    }
    /**
     * @param crfc
     *            the crfc to set
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    /**
     * @return the ccurp
     */
    public String getCurp() {
        return curp;
    }
    /**
     * @param ccurp
     *            the ccurp to set
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }
    /**
     * @return the dfechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    /**
     * @param dfechaNacimiento
     *            the dfechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    /**
     * @return the sfechaNacimiento
     */
    public String getStrFechaNacimiento() {
        return strFechaNacimiento;
    }
    /**
     * @param sfechaNacimiento
     *            the sfechaNacimiento to set
     */
    public void setStrFechaNacimiento(String strFechaNacimiento) {
        this.strFechaNacimiento = strFechaNacimiento;
    }
    /**
     * @return the iedadAproximada
     */
    public Short getEdadAproximada() {
        return edadAproximada;
    }
    /**
     * @param iedadAproximada
     *            the iedadAproximada to set
     */
    public void setEdadAproximada(Short edadAproximada) {
        this.edadAproximada = edadAproximada;
    }
    /**
     * @return the esVerdadero
     */
    public Boolean getEsVerdadero() {
        return esVerdadero;
    }
    /**
     * @param esVerdadero
     *            the esVerdadero to set
     */
    public void setEsVerdadero(Boolean esVerdadero) {
        this.esVerdadero = esVerdadero;
    }
    /**
     * @return the lugarNacimiento
     */
    public String getLugarNacimiento() {
        return lugarNacimiento;
    }
    /**
     * @param lugarNacimiento
     *            the lugarNacimiento to set
     */
    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }
    /**
     * Método de acceso al campo paisValorId.
     * 
     * @return El valor del campo paisValorId
     */
    public Long getPaisValorId() {
        return paisValorId;
    }
    /**
     * Asigna el valor al campo paisValorId.
     * 
     * @param paisValorDTO
     *            el valor paisValorDTO a asignar
     */
    public void setPaisValorId(Long paisValorId) {
        this.paisValorId = paisValorId;
    }
    /**
     * Método de acceso al campo municipioId.
     * 
     * @return El valor del campo municipioId
     */
    public Long getMunicipioId() {
        return municipioId;
    }
    /**
     * Asigna el valor al campo municipioId.
     * 
     * @param municipioId
     *            el valor municipioId a asignar
     */
    public void setMunicipioId(Long municipioId) {
        this.municipioId = municipioId;
    }
    /**
     * Método de acceso al campo entidadFederativaId.
     * 
     * @return El valor del campo entidadFederativaId
     */
    public Long getEntidadFederativaId() {
        return entidadFederativaId;
    }
    /**
     * Asigna el valor al campo entidadFederativaId.
     * 
     * @param entidadFederativaId
     *            el valor entidadFederativaId a asignar
     */
    public void setEntidadFederativaId(
            Long entidadFederativaId) {
        this.entidadFederativaId = entidadFederativaId;
    }

}
