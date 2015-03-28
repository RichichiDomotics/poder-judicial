/**
 * 
 */
package mx.gob.segob.nsjp.dto.involucrado;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDetencionDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;

/**
 * @author vaguirre
 * 
 */
public class InvolucradoDTO extends PersonaDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3342151012609615692L;

    private Long involucradoIdDefensor;
    private String motivoComparecencia;
    private Boolean esServidor;
    private Boolean esDetenido;
    private Boolean esProtegido;
    /**
     * Indica la relaci�n entre el denunciante y la victima:<br>
     * 0 = son elementos diferentes<br>
     * 1 = son el mismo<br>
     */
    private Short condicion;
    /** 
     * @see mx.gob.segob.nsjp.comun.enums.involucrado.PersonalidadJuridica
     */
    private Long tipoPersona;
    private String desconocido;
    private ValorDTO valorSituacionJuridica;
    private ValorDTO valorIdReligion;
    private ValorDTO valorIdEstadoCivil;
    private ValorDTO valorIdIdioma;
    private ValorDTO valorIdEscolaridad;
    private ValorDTO valorIdParentesco;
    private List<ValorDTO> valorIdOcupacion;
    private List<ValorDTO> valorIdNacionalidad;
    private ExpedienteDTO expedienteDTO;
    private FuncionarioDTO funcionario;
    private List<Long> idsDetenidos;
    private List<AliasInvolucradoDTO> aliasInvolucradoDTO = new ArrayList<AliasInvolucradoDTO>();
    private List<BiometricoDTO> biometricosDTO = new ArrayList<BiometricoDTO>();
    private OrganizacionDTO organizacionDTO = new OrganizacionDTO();
    private List<DetencionDTO> detenciones = new ArrayList<DetencionDTO>();
    private List<PerfilDTO> perfiles = new ArrayList<PerfilDTO>();
    private List<DelitoDTO> delitosCometidos = null;
    private List<RelacionDetencionDTO> relsDetencion = new ArrayList<RelacionDetencionDTO>();
    private Long idSolicitudDefensor;
    private MediaFiliacionDTO mediaFiliacionDTO;
    private List<MedidaCautelarDTO> medidasCautelaresDTO = new ArrayList<MedidaCautelarDTO>();
    private List<MedidaAlternaDTO> medidasAlternasDTO = new ArrayList<MedidaAlternaDTO>();
    private List<MedidaDTO> medidasDTO = new ArrayList<MedidaDTO>();
    private Boolean esMismoDomicilio;
    private SentenciaDTO sentenciaDTO;


    /**
     * Para la notificaci�n de eventos el involucrado tiene un conjunto de
     * notificaciones
     */
    private List<NotificacionDTO> notificaciones = new ArrayList<NotificacionDTO>();

    private ConfInstitucionDTO institucionPresenta;

    public boolean isVictima() {
        return (this.getCalidadDTO().getCalidades().getValorId().longValue() == Calidades.VICTIMA_PERSONA
                .getValorId().longValue())
                || (this.getCalidadDTO().getCalidades().getValorId()
                        .longValue() == Calidades.VICTIMA_PERSONA.getValorId()
                        .longValue() && getCondicion() == 1);
    }

    public InvolucradoDTO(Long elementoId) {
        super();
        setElementoId(elementoId);
    }

    /**
     * @param involucradoId
     * @param motivoComparecencia
     * @param esServidor
     * @param esDetenido
     * @param condicion
     * @param tipoPersona
     * @param domicilios
     */
    public InvolucradoDTO(Long involucradoId, String motivoComparecencia,
            Boolean esServidor, Boolean esDetenido, Short condicion,
            Long tipoPersona) {
        super();
        // this.involucradoId = involucradoId;
        this.motivoComparecencia = motivoComparecencia;
        this.esServidor = esServidor;
        this.esDetenido = esDetenido;
        this.condicion = condicion;
        this.tipoPersona = tipoPersona;
    }

    /**
	 * 
	 */
    public InvolucradoDTO() {

    }
    /**
     * Agrega una medida
     * 
     * @param medidaCau
     */
    public void addMedidaCautelar(MedidaCautelarDTO medidaCau) {
        if (this.medidasCautelaresDTO == null) {
            this.medidasCautelaresDTO = new ArrayList<MedidaCautelarDTO>();
        }
        this.medidasCautelaresDTO.add(medidaCau);
    }
    /**
     * 
     * @param det
     */
    public void addDetencion(DetencionDTO det) {
        if (this.detenciones == null) {
            this.detenciones = new ArrayList<DetencionDTO>();
        }
        detenciones.add(det);
    }
    /**
     * Agrega un alias
     * 
     * @param unAlias
     */
    public void addAlias(AliasInvolucradoDTO unAlias) {
        if (this.aliasInvolucradoDTO == null) {
            this.aliasInvolucradoDTO = new ArrayList<AliasInvolucradoDTO>();
        }
        this.aliasInvolucradoDTO.add(unAlias);
    }
    /**
     * Agrega un delito.
     * 
     * @param unDelito
     */
    public void addDelitoCometido(DelitoDTO unDelito) {
        if (this.delitosCometidos == null) {
            this.delitosCometidos = new ArrayList<DelitoDTO>();
        }
        this.delitosCometidos.add(unDelito);
    }

    /**
     * Recupera la �ltima detenci�n.
     * 
     * @return
     */
    public DetencionDTO getUltimaDetencion() {
        if (this.detenciones == null || this.detenciones.isEmpty()) {
            return null;
        }
        return this.detenciones.get((this.detenciones.size() - 1));
    }

    /**
     * M�todo de acceso al campo perfiles.
     * 
     * @return El valor del campo perfiles
     */
    public List<PerfilDTO> getPerfiles() {
        return perfiles;
    }

    /**
     * Asigna el valor al campo perfiles.
     * 
     * @param perfiles
     *            el valor perfiles a asignar
     */
    public void setPerfiles(List<PerfilDTO> perfiles) {
        this.perfiles = perfiles;
    }

    /**
     * M�todo de acceso al campo detenciones.
     * 
     * @return El valor del campo detenciones
     */
    public List<DetencionDTO> getDetenciones() {
        return detenciones;
    }

    /**
     * Asigna el valor al campo detenciones.
     * 
     * @param detenciones
     *            el valor detenciones a asignar
     */
    public void setDetenciones(List<DetencionDTO> detenciones) {
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
     * Método de acceso al campo desconocido.
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
     * 
     * @return El valor del campo valorSituacionJuridica
     */
    public ValorDTO getValorSituacionJuridica() {
        return valorSituacionJuridica;
    }

    /**
     * Asigna el valor al campo valorSituacionJuridica.
     * 
     * @param valorSituacionJuridica
     *            el valor valorSituacionJuridica a asignar
     */
    public void setValorSituacionJuridica(ValorDTO valorSituacionJuridica) {
        this.valorSituacionJuridica = valorSituacionJuridica;
    }

    /**
     * Método de acceso al campo valorIdReligion.
     * 
     * @return El valor del campo valorIdReligion
     */
    public ValorDTO getValorIdReligion() {
        return valorIdReligion;
    }

    /**
     * Asigna el valor al campo valorIdReligion.
     * 
     * @param valorIdReligion
     *            el valor valorIdReligion a asignar
     */
    public void setValorIdReligion(ValorDTO valorIdReligion) {
        this.valorIdReligion = valorIdReligion;
    }

    /**
     * Método de acceso al campo valorIdEstadoCivil.
     * 
     * @return El valor del campo valorIdEstadoCivil
     */
    public ValorDTO getValorIdEstadoCivil() {
        return valorIdEstadoCivil;
    }

    /**
     * Asigna el valor al campo valorIdEstadoCivil.
     * 
     * @param valorIdEstadoCivil
     *            el valor valorIdEstadoCivil a asignar
     */
    public void setValorIdEstadoCivil(ValorDTO valorIdEstadoCivil) {
        this.valorIdEstadoCivil = valorIdEstadoCivil;
    }

    /**
     * Método de acceso al campo valorIdOcupacion.
     * 
     * @return El valor del campo valorIdOcupacion
     */
    public List<ValorDTO> getValorIdOcupacion() {
        return valorIdOcupacion;
    }

    /**
     * Asigna el valor al campo valorIdOcupacion.
     * 
     * @param valorIdOcupacion
     *            el valor valorIdOcupacion a asignar
     */
    public void setValorIdOcupacion(List<ValorDTO> valorIdOcupacion) {
        this.valorIdOcupacion = valorIdOcupacion;
    }

    /**
     * Método de acceso al campo valorIdNacionalidad.
     * 
     * @return El valor del campo valorIdNacionalidad
     */
    public List<ValorDTO> getValorIdNacionalidad() {
        return valorIdNacionalidad;
    }

    /**
     * Asigna el valor al campo valorIdNacionalidad.
     * 
     * @param valorIdNacionalidad
     *            el valor valorIdNacionalidad a asignar
     */
    public void setValorIdNacionalidad(List<ValorDTO> valorIdNacionalidad) {
        this.valorIdNacionalidad = valorIdNacionalidad;
    }

    /**
     * Método de acceso al campo valorIdIdioma.
     * 
     * @return El valor del campo valorIdIdioma
     */
    public ValorDTO getValorIdIdioma() {
        return valorIdIdioma;
    }

    /**
     * Asigna el valor al campo valorIdIdioma.
     * 
     * @param valorIdIdioma
     *            el valor valorIdIdioma a asignar
     */
    public void setValorIdIdioma(ValorDTO valorIdIdioma) {
        this.valorIdIdioma = valorIdIdioma;
    }

    /**
     * Método de acceso al campo valorIdEscolaridad.
     * 
     * @return El valor del campo valorIdEscolaridad
     */
    public ValorDTO getValorIdEscolaridad() {
        return valorIdEscolaridad;
    }

    /**
     * Asigna el valor al campo valorIdEscolaridad.
     * 
     * @param valorIdEscolaridad
     *            el valor valorIdEscolaridad a asignar
     */
    public void setValorIdEscolaridad(ValorDTO valorIdEscolaridad) {
        this.valorIdEscolaridad = valorIdEscolaridad;
    }

    /**
     * Método de acceso al campo valorIdParentesco.
     * 
     * @return El valor del campo valorIdParentesco
     */
    public ValorDTO getValorIdParentesco() {
        return valorIdParentesco;
    }

    /**
     * Asigna el valor al campo valorIdParentesco.
     * 
     * @param valorIdParentesco
     *            el valor valorIdParentesco a asignar
     */
    public void setValorIdParentesco(ValorDTO valorIdParentesco) {
        this.valorIdParentesco = valorIdParentesco;
    }

    /**
     * Método de acceso al campo expedienteDTO.
     * 
     * @return El valor del campo expedienteDTO
     */
    public ExpedienteDTO getExpedienteDTO() {
        return expedienteDTO;
    }

    /**
     * Asigna el valor al campo expedienteDTO.
     * 
     * @param expedienteDTO
     *            el valor expedienteDTO a asignar
     */
    public void setExpedienteDTO(ExpedienteDTO expedienteDTO) {
        this.expedienteDTO = expedienteDTO;
    }

    /**
     * Método de acceso al campo aliasInvolucradoDTO.
     * 
     * @return El valor del campo aliasInvolucradoDTO
     */
    public List<AliasInvolucradoDTO> getAliasInvolucradoDTO() {
        return aliasInvolucradoDTO;
    }

    /**
     * Asigna el valor al campo aliasInvolucradoDTO.
     * 
     * @param aliasInvolucradoDTO
     *            el valor aliasInvolucradoDTO a asignar
     */
    public void setAliasInvolucradoDTO(
            List<AliasInvolucradoDTO> aliasInvolucradoDTO) {
        this.aliasInvolucradoDTO = aliasInvolucradoDTO;
    }

    /**
     * Método de acceso al campo biometricosDTO.
     * 
     * @return El valor del campo biometricosDTO
     */
    public List<BiometricoDTO> getBiometricosDTO() {
        return biometricosDTO;
    }

    /**
     * Asigna el valor al campo biometricosDTO.
     * 
     * @param biometricosDTO
     *            el valor biometricosDTO a asignar
     */
    public void setBiometricosDTO(List<BiometricoDTO> biometricosDTO) {
        this.biometricosDTO = biometricosDTO;
    }

    /**
     * Método de acceso al campo organizacionDTO.
     * 
     * @return El valor del campo organizacionDTO
     */
    public OrganizacionDTO getOrganizacionDTO() {
        return organizacionDTO;
    }

    /**
     * Asigna el valor al campo organizacionDTO.
     * 
     * @param organizacionDTO
     *            el valor organizacionDTO a asignar
     */
    public void setOrganizacionDTO(OrganizacionDTO organizacionDTO) {
        this.organizacionDTO = organizacionDTO;
    }

    /**
     * Método de acceso al campo funcionario.
     * 
     * @return El valor del campo funcionario
     */
    public FuncionarioDTO getFuncionario() {
        return funcionario;
    }

    /**
     * Asigna el valor al campo funcionario.
     * 
     * @param funcionario
     *            el valor funcionario a asignar
     */
    public void setFuncionario(FuncionarioDTO funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * M�todo de acceso al campo notificaciones.
     * 
     * @return El valor del campo notificaciones
     */
    public List<NotificacionDTO> getNotificaciones() {
        return notificaciones;
    }

    /**
     * Asigna el valor al campo notificaciones.
     * 
     * @param notificaciones
     *            el valor notificaciones a asignar
     */
    public void setNotificaciones(List<NotificacionDTO> notificaciones) {
        this.notificaciones = notificaciones;
    }

    /**
     * M�todo de acceso al campo delitosCometidos.
     * 
     * @return El valor del campo delitosCometidos
     */
    public List<DelitoDTO> getDelitosCometidos() {
        return delitosCometidos;
    }

    /**
     * Asigna el valor al campo delitosCometidos.
     * 
     * @param delitosCometidos
     *            el valor delitosCometidos a asignar
     */
    public void setDelitosCometidos(List<DelitoDTO> delitosCometidos) {
        this.delitosCometidos = delitosCometidos;
    }

    /**
     * M�todo de acceso al campo idsDetenidos.
     * 
     * @return El valor del campo idsDetenidos
     */
    public List<Long> getIdsDetenidos() {
        return idsDetenidos;
    }

    /**
     * Asigna el valor al campo idsDetenidos.
     * 
     * @param idsDetenidos
     *            el valor idsDetenidos a asignar
     */
    public void setIdsDetenidos(List<Long> idsDetenidos) {
        this.idsDetenidos = idsDetenidos;
    }

    /**
     * M�todo de acceso al campo relsDetencion.
     * 
     * @return El valor del campo relsDetencion
     */
    public List<RelacionDetencionDTO> getRelsDetencion() {
        return relsDetencion;
    }

    /**
     * Asigna el valor al campo relsDetencion.
     * 
     * @param relsDetencion
     *            el valor relsDetencion a asignar
     */
    public void setRelsDetencion(List<RelacionDetencionDTO> relsDetencion) {
        this.relsDetencion = relsDetencion;
    }

    /**
     * M�todo de acceso al campo institucionPresenta
     * 
     * @return El valor del campo institucionPresenta
     */
    public void setInstitucionPresenta(ConfInstitucionDTO institucionPresenta) {
        this.institucionPresenta = institucionPresenta;
    }

    /**
     * Asigna el valor al campo institucionPresenta
     * 
     * @param idsDetenidos
     *            el valor institucionPresenta a asignar
     */
    public ConfInstitucionDTO getInstitucionPresenta() {
        return institucionPresenta;
    }

    /**
     * M�todo de acceso al campo idSolicitudDefensor.
     * 
     * @return El valor del campo idSolicitudDefensor
     */
    public Long getIdSolicitudDefensor() {
        return idSolicitudDefensor;
    }

    /**
     * Asigna el valor al campo idSolicitudDefensor.
     * 
     * @param idSolicitudDefensor
     *            el valor idSolicitudDefensor a asignar
     */
    public void setIdSolicitudDefensor(Long idSolicitudDefensor) {
        this.idSolicitudDefensor = idSolicitudDefensor;
    }

    /**
     * M�todo de acceso al campo mediaFiliacionDTO.
     * 
     * @return El valor del campo mediaFiliacionDTO
     */
    public MediaFiliacionDTO getMediaFiliacionDTO() {
        return mediaFiliacionDTO;
    }

    /**
     * Asigna el valor al campo mediaFiliacionDTO.
     * 
     * @param mediaFiliacionDTO
     *            el valor mediaFiliacionDTO a asignar
     */
    public void setMediaFiliacionDTO(MediaFiliacionDTO mediaFiliacionDTO) {
        this.mediaFiliacionDTO = mediaFiliacionDTO;
    }

    /**
     * M�todo de acceso al campo medidasCautelaresDTO.
     * 
     * @return El valor del campo medidasCautelaresDTO
     */
    public List<MedidaCautelarDTO> getMedidasCautelaresDTO() {
        return medidasCautelaresDTO;
    }

    /**
     * Asigna el valor al campo medidasCautelaresDTO.
     * 
     * @param medidasCautelaresDTO
     *            el valor medidasCautelaresDTO a asignar
     */
    public void setMedidasCautelaresDTO(
            List<MedidaCautelarDTO> medidasCautelaresDTO) {
        this.medidasCautelaresDTO = medidasCautelaresDTO;
    }

    /**
	 * M�todo de acceso al campo medidasAlternasDTO.
	 * @return El valor del campo medidasAlternasDTO
	 */
	public List<MedidaAlternaDTO> getMedidasAlternasDTO() {
		return medidasAlternasDTO;
	}

	/**
	 * Asigna el valor al campo medidasAlternasDTO.
	 * @param medidasAlternasDTO el valor medidasAlternasDTO a asignar
	 */
	public void setMedidasAlternasDTO(List<MedidaAlternaDTO> medidasAlternasDTO) {
		this.medidasAlternasDTO = medidasAlternasDTO;
	}

	/**
     * M�todo de acceso al campo medidasDTO.
     * 
     * @return El valor del campo medidasDTO
     */
    public List<MedidaDTO> getMedidasDTO() {
        return medidasDTO;
    }

    /**
     * Asigna el valor al campo medidasDTO.
     * 
     * @param medidasDTO
     *            el valor medidasDTO a asignar
     */
    public void setMedidasDTO(List<MedidaDTO> medidasDTO) {
        this.medidasDTO = medidasDTO;
    }

	/**
	 * @return the involucradoIdDefensor
	 */
	public Long getInvolucradoIdDefensor() {
		return involucradoIdDefensor;
	}

	/**
	 * @param involucradoIdDefensor the involucradoIdDefensor to set
	 */
	public void setInvolucradoIdDefensor(Long involucradoIdDefensor) {
		this.involucradoIdDefensor = involucradoIdDefensor;
	}

	/**
	 * M�todo de acceso al campo esMismoDomicilio.
	 * @return El valor del campo esMismoDomicilio
	 */
	public Boolean getEsMismoDomicilio() {
		return esMismoDomicilio;
	}

	/**
	 * Asigna el valor al campo esMismoDomicilio.
	 * @param esMismoDomicilio el valor esMismoDomicilio a asignar
	 */
	public void setEsMismoDomicilio(Boolean esMismoDomicilio) {
		this.esMismoDomicilio = esMismoDomicilio;
	}

	/**
	 * Permite indicar si se trata de un testigo protegido.
	 * @return esProtegido
	 */
	public Boolean getEsProtegido() {
		return esProtegido;
	}

	public void setEsProtegido(Boolean esProtegido) {
		this.esProtegido = esProtegido;
	}

	/**
	 * @param sentenciaDTO the sentenciaDTO to set
	 */
	public void setSentenciaDTO(SentenciaDTO sentenciaDTO) {
		this.sentenciaDTO = sentenciaDTO;
	}

	/**
	 * @return the sentenciaDTO
	 */
	public SentenciaDTO getSentenciaDTO() {
		return sentenciaDTO;
	}
	
}
