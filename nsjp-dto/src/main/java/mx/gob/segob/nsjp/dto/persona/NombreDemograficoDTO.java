/**
 * 
 */
package mx.gob.segob.nsjp.dto.persona;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;

/**
 * @author vaguirre
 * 
 */
public class NombreDemograficoDTO extends GenericDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = -2730451725377370808L;

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
    private ValorDTO paisValorDTO;
    private MunicipioDTO municipioDTO;
    private EntidadFederativaDTO entidadFederativaDTO;

    
    /**
     * @param nombreDemograficoId
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     */
    public NombreDemograficoDTO(Long nombreDemograficoId, String nombre,
            String apellidoPaterno, String apellidoMaterno) {
        super();
        this.nombreDemograficoId = nombreDemograficoId;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }
    /**
     * Default.
     */
    public NombreDemograficoDTO(){
        super();
    }
    
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
     * Método de acceso al campo paisValorDTO.
     * 
     * @return El valor del campo paisValorDTO
     */
    public ValorDTO getPaisValorDTO() {
        return paisValorDTO;
    }
    /**
     * Asigna el valor al campo paisValorDTO.
     * 
     * @param paisValorDTO
     *            el valor paisValorDTO a asignar
     */
    public void setPaisValorDTO(ValorDTO paisValorDTO) {
        this.paisValorDTO = paisValorDTO;
    }
    /**
     * Método de acceso al campo municipioDTO.
     * 
     * @return El valor del campo municipioDTO
     */
    public MunicipioDTO getMunicipioDTO() {
        return municipioDTO;
    }
    /**
     * Asigna el valor al campo municipioDTO.
     * 
     * @param municipioDTO
     *            el valor municipioDTO a asignar
     */
    public void setMunicipioDTO(MunicipioDTO municipioDTO) {
        this.municipioDTO = municipioDTO;
    }
    /**
     * Método de acceso al campo entidadFederativaDTO.
     * 
     * @return El valor del campo entidadFederativaDTO
     */
    public EntidadFederativaDTO getEntidadFederativaDTO() {
        return entidadFederativaDTO;
    }
    /**
     * Asigna el valor al campo entidadFederativaDTO.
     * 
     * @param entidadFederativaDTO
     *            el valor entidadFederativaDTO a asignar
     */
    public void setEntidadFederativaDTO(
            EntidadFederativaDTO entidadFederativaDTO) {
        this.entidadFederativaDTO = entidadFederativaDTO;
    }

}
