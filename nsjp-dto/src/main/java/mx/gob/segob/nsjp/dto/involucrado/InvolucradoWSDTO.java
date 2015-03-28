/**
*
* Nombre del Programa : InvolucradoWSDTO.java                                    
* Autor                            : GustavoBP                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 22/07/2012 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Involucrado.                      
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
package mx.gob.segob.nsjp.dto.involucrado;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoWSDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionWSDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaWSDTO;

/**
 * 
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Involucrado.
 * @author GustavoBP
 * @version 1.0
 */
public class InvolucradoWSDTO extends PersonaWSDTO {

    private String motivoComparecencia;
    private Boolean esServidor;
    private Boolean esDetenido;
    private Short condicion;
    private Long tipoPersona;
    private String desconocido;
    private Long valorSituacionJuridica;
    private Long valorIdReligion;
    private Long valorIdEstadoCivil;
    private Long valorIdIdioma;
    private Long valorIdEscolaridad;
    private Long valorIdParentesco;
    private List<Long> valorIdOcupacion;
    private List<Long> valorIdNacionalidad;
    private List<Long> idsDetenidos;
    private List<String> aliasInvolucrado = new ArrayList<String>();
    private OrganizacionWSDTO organizacion = new OrganizacionWSDTO();
    private List<DetencionWSDTO> detenciones = new ArrayList<DetencionWSDTO>();
    private List<DelitoWSDTO> delitosCometidos = new ArrayList<DelitoWSDTO>();
    private Long idSolicitudDefensor;
    private MediaFiliacionWSDTO mediaFiliacionWSDTO;
    protected Long institucionPresenta;
    
    /**
     * @param involucradoId
     * @param motivoComparecencia
     * @param esServidor
     * @param esDetenido
     * @param condicion
     * @param tipoPersona
     * @param domicilios
     */
    public InvolucradoWSDTO(String motivoComparecencia,
            Boolean esServidor, Boolean esDetenido, Short condicion,
            Long tipoPersona) {
        super();
        this.motivoComparecencia = motivoComparecencia;
        this.esServidor = esServidor;
        this.esDetenido = esDetenido;
        this.condicion = condicion;
        this.tipoPersona = tipoPersona;
    }

    /**
	 * 
	 */
    public InvolucradoWSDTO() {

    }
    
    /**
     * Mï¿½todo de acceso al campo detenciones.
     * 
     * @return El valor del campo detenciones
     */
    public List<DetencionWSDTO> getDetenciones() {
        return detenciones;
    }

    /**
     * Asigna el valor al campo detenciones.
     * 
     * @param detenciones
     *            el valor detenciones a asignar
     */
    public void setDetenciones(List<DetencionWSDTO> detenciones) {
        this.detenciones = detenciones;
    }

    /**
     * @return the motivoComparecencia
     */
    public String getMotivoComparecencia() {
        return motivoComparecencia;
    }

    /**
     * @param motivoComparecencia
     *            the motivoComparecencia to set
     */
    public void setMotivoComparecencia(String motivoComparecencia) {
        this.motivoComparecencia = motivoComparecencia;
    }

    /**
     * @return the esServidor
     */
    public Boolean getEsServidor() {
        return esServidor;
    }

    /**
     * @param esServidor
     *            the esServidor to set
     */
    public void setEsServidor(Boolean esServidor) {
        this.esServidor = esServidor;
    }

    /**
     * @return the esDetenido
     */
    public Boolean getEsDetenido() {
        return esDetenido;
    }

    /**
     * @param esDetenido
     *            the esDetenido to set
     */
    public void setEsDetenido(Boolean esDetenido) {
        this.esDetenido = esDetenido;
    }

    /**
     * @return the condicion
     */
    public Short getCondicion() {
        return condicion;
    }

    /**
     * @param condicion
     *            the condicion to set
     */
    public void setCondicion(Short condicion) {
        this.condicion = condicion;
    }

    /**
     * @return the tipoPersona
     */
    public Long getTipoPersona() {
        return tipoPersona;
    }

    /**
     * @param tipoPersona
     *            the tipoPersona to set
     */
    public void setTipoPersona(Long tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * MÃ©todo de acceso al campo desconocido.
     * 
     * @return El valor del campo desconocido
     */
    public String getDesconocido() {
        return desconocido;
    }

    /**
     * Asigna el valor al campo desconocido.
     * 
     * @param desconocido
     *            el valor desconocido a asignar
     */
    public void setDesconocido(String desconocido) {
        this.desconocido = desconocido;
    }

    /**
	 * Método de acceso al campo valorSituacionJuridica.
	 * @return El valor del campo valorSituacionJuridica
	 */
	public Long getValorSituacionJuridica() {
		return valorSituacionJuridica;
	}

	/**
	 * Asigna el valor al campo valorSituacionJuridica.
	 * @param valorSituacionJuridica el valor valorSituacionJuridica a asignar
	 */
	public void setValorSituacionJuridica(Long valorSituacionJuridica) {
		this.valorSituacionJuridica = valorSituacionJuridica;
	}

	/**
     * MÃ©todo de acceso al campo valorIdReligion.
     * 
     * @return El valor del campo valorIdReligion
     */
    public Long getValorIdReligion() {
        return valorIdReligion;
    }

    /**
     * Asigna el valor al campo valorIdReligion.
     * 
     * @param valorIdReligion
     *            el valor valorIdReligion a asignar
     */
    public void setValorIdReligion(Long valorIdReligion) {
        this.valorIdReligion = valorIdReligion;
    }

    /**
     * MÃ©todo de acceso al campo valorIdEstadoCivil.
     * 
     * @return El valor del campo valorIdEstadoCivil
     */
    public Long getValorIdEstadoCivil() {
        return valorIdEstadoCivil;
    }

    /**
     * Asigna el valor al campo valorIdEstadoCivil.
     * 
     * @param valorIdEstadoCivil
     *            el valor valorIdEstadoCivil a asignar
     */
    public void setValorIdEstadoCivil(Long valorIdEstadoCivil) {
        this.valorIdEstadoCivil = valorIdEstadoCivil;
    }

    /**
     * MÃ©todo de acceso al campo valorIdOcupacion.
     * 
     * @return El valor del campo valorIdOcupacion
     */
    public List<Long> getValorIdOcupacion() {
        return valorIdOcupacion;
    }

    /**
     * Asigna el valor al campo valorIdOcupacion.
     * 
     * @param valorIdOcupacion
     *            el valor valorIdOcupacion a asignar
     */
    public void setValorIdOcupacion(List<Long> valorIdOcupacion) {
        this.valorIdOcupacion = valorIdOcupacion;
    }

    /**
     * MÃ©todo de acceso al campo valorIdNacionalidad.
     * 
     * @return El valor del campo valorIdNacionalidad
     */
    public List<Long> getValorIdNacionalidad() {
        return valorIdNacionalidad;
    }

    /**
     * Asigna el valor al campo valorIdNacionalidad.
     * 
     * @param valorIdNacionalidad
     *            el valor valorIdNacionalidad a asignar
     */
    public void setValorIdNacionalidad(List<Long> valorIdNacionalidad) {
        this.valorIdNacionalidad = valorIdNacionalidad;
    }

    /**
     * MÃ©todo de acceso al campo valorIdIdioma.
     * 
     * @return El valor del campo valorIdIdioma
     */
    public Long getValorIdIdioma() {
        return valorIdIdioma;
    }

    /**
     * Asigna el valor al campo valorIdIdioma.
     * 
     * @param valorIdIdioma
     *            el valor valorIdIdioma a asignar
     */
    public void setValorIdIdioma(Long valorIdIdioma) {
        this.valorIdIdioma = valorIdIdioma;
    }

    /**
     * MÃ©todo de acceso al campo valorIdEscolaridad.
     * 
     * @return El valor del campo valorIdEscolaridad
     */
    public Long getValorIdEscolaridad() {
        return valorIdEscolaridad;
    }

    /**
     * Asigna el valor al campo valorIdEscolaridad.
     * 
     * @param valorIdEscolaridad
     *            el valor valorIdEscolaridad a asignar
     */
    public void setValorIdEscolaridad(Long valorIdEscolaridad) {
        this.valorIdEscolaridad = valorIdEscolaridad;
    }

    /**
     * MÃ©todo de acceso al campo valorIdParentesco.
     * 
     * @return El valor del campo valorIdParentesco
     */
    public Long getValorIdParentesco() {
        return valorIdParentesco;
    }

    /**
     * Asigna el valor al campo valorIdParentesco.
     * 
     * @param valorIdParentesco
     *            el valor valorIdParentesco a asignar
     */
    public void setValorIdParentesco(Long valorIdParentesco) {
        this.valorIdParentesco = valorIdParentesco;
    }

    /**
     * MÃ©todo de acceso al campo aliasInvolucrado.
     * 
     * @return El valor del campo aliasInvolucrado
     */
    public List<String> getAliasInvolucrado() {
        return aliasInvolucrado;
    }

    /**
     * Asigna el valor al campo aliasInvolucrado.
     * 
     * @param aliasInvolucrado
     *            el valor aliasInvolucrado a asignar
     */
    public void setAliasInvolucrado(
            List<String> aliasInvolucrado) {
        this.aliasInvolucrado = aliasInvolucrado;
    }

    /**
     * MÃ©todo de acceso al campo Organizacion.
     * 
     * @return El valor del campo Organizacion
     */
    public OrganizacionWSDTO getOrganizacion() {
        return organizacion;
    }

    /**
     * Asigna el valor al campo Organizacion.
     * 
     * @param organizacion
     *            el valor Organizacion a asignar
     */
    public void setOrganizacion(OrganizacionWSDTO organizacion) {
        this.organizacion = organizacion;
    }
   

	/**
	 * Método de acceso al campo delitosCometidos.
	 * @return El valor del campo delitosCometidos
	 */
	public List<DelitoWSDTO> getDelitosCometidos() {
		return delitosCometidos;
	}

	/**
	 * Asigna el valor al campo delitosCometidos.
	 * @param delitosCometidos el valor delitosCometidos a asignar
	 */
	public void setDelitosCometidos(List<DelitoWSDTO> delitosCometidos) {
		this.delitosCometidos = delitosCometidos;
	}

	/**
	 * Método de acceso al campo idsDetenidos.
	 * @return El valor del campo idsDetenidos
	 */
	public List<Long> getIdsDetenidos() {
		return idsDetenidos;
	}

	/**
	 * Asigna el valor al campo idsDetenidos.
	 * @param idsDetenidos el valor idsDetenidos a asignar
	 */
	public void setIdsDetenidos(List<Long> idsDetenidos) {
		this.idsDetenidos = idsDetenidos;
	}

	/**
	 * Método de acceso al campo idSolicitudDefensor.
	 * @return El valor del campo idSolicitudDefensor
	 */
	public Long getIdSolicitudDefensor() {
		return idSolicitudDefensor;
	}

	/**
	 * Asigna el valor al campo idSolicitudDefensor.
	 * @param idSolicitudDefensor el valor idSolicitudDefensor a asignar
	 */
	public void setIdSolicitudDefensor(Long idSolicitudDefensor) {
		this.idSolicitudDefensor = idSolicitudDefensor;
	}

	/**
	 * Método de acceso al campo mediaFiliacionWSDTO.
	 * @return El valor del campo mediaFiliacionWSDTO
	 */
	public MediaFiliacionWSDTO getMediaFiliacionWSDTO() {
		return mediaFiliacionWSDTO;
	}

	/**
	 * Asigna el valor al campo mediaFiliacionWSDTO.
	 * @param mediaFiliacionWSDTO el valor mediaFiliacionWSDTO a asignar
	 */
	public void setMediaFiliacionWSDTO(MediaFiliacionWSDTO mediaFiliacionWSDTO) {
		this.mediaFiliacionWSDTO = mediaFiliacionWSDTO;
	}

	/**
	 * Método de acceso al campo institucionPresenta.
	 * @return El valor del campo institucionPresenta
	 */
	public Long getInstitucionPresenta() {
		return institucionPresenta;
	}

	/**
	 * Asigna el valor al campo institucionPresenta.
	 * @param institucionPresenta el valor institucionPresenta a asignar
	 */
	public void setInstitucionPresenta(Long institucionPresenta) {
		this.institucionPresenta = institucionPresenta;
	}
}
