package mx.gob.segob.nsjp.dto.involucrado;

import java.util.HashSet;
import java.util.Set;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO;

/**
 * @author rgama
 * 
 */
public class ImplicadoDTO extends GenericDTO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5603618149924021383L;
	private Long implicadoId;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Set<QuejaCiudadanaDTO> quejaCiudadanasForAfectadoId = new HashSet<QuejaCiudadanaDTO>(
            0);
    private Set<QuejaCiudadanaDTO> quejaCiudadanasForDenunciadoId = new HashSet<QuejaCiudadanaDTO>(
            0);
    private Set<QuejaCiudadanaDTO> quejaCiudadanasForQuejosoId = new HashSet<QuejaCiudadanaDTO>(
            0);
    private Set<MedioDeContactoDTO> medioDeContactos = new HashSet<MedioDeContactoDTO>(
            0);
    private ValorDTO tipoCalidad;
    private Set<AvisoHechoDelictivoDTO> avisoHechoDelictivos = new HashSet<AvisoHechoDelictivoDTO>(0);
    
	public ImplicadoDTO(long id) {
		this.implicadoId = id;
	}
	public ImplicadoDTO() {
	}
	/**
	 * Método de acceso al campo implicadoId.
	 * @return El valor del campo implicadoId
	 */
	public Long getImplicadoId() {
		return implicadoId;
	}
	/**
	 * Asigna el valor al campo implicadoId.
	 * @param implicadoId el valor implicadoId a asignar
	 */
	public void setImplicadoId(Long implicadoId) {
		this.implicadoId = implicadoId;
	}
	/**
	 * Método de acceso al campo nombre.
	 * @return El valor del campo nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Asigna el valor al campo nombre.
	 * @param nombre el valor nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Método de acceso al campo apellidoPaterno.
	 * @return El valor del campo apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * Asigna el valor al campo apellidoPaterno.
	 * @param apellidoPaterno el valor apellidoPaterno a asignar
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * Método de acceso al campo apellidoMaterno.
	 * @return El valor del campo apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * Asigna el valor al campo apellidoMaterno.
	 * @param apellidoMaterno el valor apellidoMaterno a asignar
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * Método de acceso al campo quejaCiudadanasForAfectadoId.
	 * @return El valor del campo quejaCiudadanasForAfectadoId
	 */
	public Set<QuejaCiudadanaDTO> getQuejaCiudadanasForAfectadoId() {
		return quejaCiudadanasForAfectadoId;
	}
	/**
	 * Asigna el valor al campo quejaCiudadanasForAfectadoId.
	 * @param quejaCiudadanasForAfectadoId el valor quejaCiudadanasForAfectadoId a asignar
	 */
	public void setQuejaCiudadanasForAfectadoId(
			Set<QuejaCiudadanaDTO> quejaCiudadanasForAfectadoId) {
		this.quejaCiudadanasForAfectadoId = quejaCiudadanasForAfectadoId;
	}
	/**
	 * Método de acceso al campo quejaCiudadanasForDenunciadoId.
	 * @return El valor del campo quejaCiudadanasForDenunciadoId
	 */
	public Set<QuejaCiudadanaDTO> getQuejaCiudadanasForDenunciadoId() {
		return quejaCiudadanasForDenunciadoId;
	}
	/**
	 * Asigna el valor al campo quejaCiudadanasForDenunciadoId.
	 * @param quejaCiudadanasForDenunciadoId el valor quejaCiudadanasForDenunciadoId a asignar
	 */
	public void setQuejaCiudadanasForDenunciadoId(
			Set<QuejaCiudadanaDTO> quejaCiudadanasForDenunciadoId) {
		this.quejaCiudadanasForDenunciadoId = quejaCiudadanasForDenunciadoId;
	}
	/**
	 * Método de acceso al campo quejaCiudadanasForQuejosoId.
	 * @return El valor del campo quejaCiudadanasForQuejosoId
	 */
	public Set<QuejaCiudadanaDTO> getQuejaCiudadanasForQuejosoId() {
		return quejaCiudadanasForQuejosoId;
	}
	/**
	 * Asigna el valor al campo quejaCiudadanasForQuejosoId.
	 * @param quejaCiudadanasForQuejosoId el valor quejaCiudadanasForQuejosoId a asignar
	 */
	public void setQuejaCiudadanasForQuejosoId(
			Set<QuejaCiudadanaDTO> quejaCiudadanasForQuejosoId) {
		this.quejaCiudadanasForQuejosoId = quejaCiudadanasForQuejosoId;
	}
	/**
	 * Método de acceso al campo medioDeContactos.
	 * @return El valor del campo medioDeContactos
	 */
	public Set<MedioDeContactoDTO> getMedioDeContactos() {
		return medioDeContactos;
	}
	/**
	 * Asigna el valor al campo medioDeContactos.
	 * @param medioDeContactos el valor medioDeContactos a asignar
	 */
	public void setMedioDeContactos(Set<MedioDeContactoDTO> medioDeContactos) {
		this.medioDeContactos = medioDeContactos;
	}
	/**
	 * Método de acceso al campo tipoCalidad.
	 * @return El valor del campo tipoCalidad
	 */
	public ValorDTO getTipoCalidad() {
		return tipoCalidad;
	}
	/**
	 * Asigna el valor al campo tipoCalidad.
	 * @param tipoCalidad el valor tipoCalidad a asignar
	 */
	public void setTipoCalidad(ValorDTO tipoCalidad) {
		this.tipoCalidad = tipoCalidad;
	}
	/**
	 * Método de acceso al campo avisoHechoDelictivos.
	 * @return El valor del campo avisoHechoDelictivos
	 */
	public Set<AvisoHechoDelictivoDTO> getAvisoHechoDelictivos() {
		return avisoHechoDelictivos;
	}
	/**
	 * Asigna el valor al campo avisoHechoDelictivos.
	 * @param avisoHechoDelictivos el valor avisoHechoDelictivos a asignar
	 */
	public void setAvisoHechoDelictivos(
			Set<AvisoHechoDelictivoDTO> avisoHechoDelictivos) {
		this.avisoHechoDelictivos = avisoHechoDelictivos;
	}

}
