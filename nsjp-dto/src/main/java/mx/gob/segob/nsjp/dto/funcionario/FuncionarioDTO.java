/**
 * Nombre del Programa : FuncionarioDTO.java
 * Autor                            : cesar
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dto.funcionario;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public class FuncionarioDTO extends GenericDTO
        implements
            Comparable<FuncionarioDTO> {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3640811564646806214L;

    private Long claveFuncionario;
    private PersonaDTO persona;
    private FuncionarioDTO funcionarioJefe;
    private String nombreFuncionario;
    private String apellidoPaternoFuncionario;
    private String apellidoMaternoFuncionario;
    private String sexo;
    private String rfc;
    private String curp;
    private ArchivoDigitalDTO archivoDigital;
    private java.util.Date fechaNacimiento;
    private java.util.Date fechaIngreso;
    private String email;
    private String cedula;
    private Double cargaTrabajo;
    private Boolean esPar;
    private String numeroEmpleado;

    private DepartamentoDTO departamento;
    private ValorDTO puesto;
    private ValorDTO especialidad;
    private ConfInstitucionDTO institucion = new ConfInstitucionDTO();
    private String nombreInstitucion;
    private ValorDTO tipoEspecialidad;
    private JerarquiaOrganizacionalDTO jerarquiaOrganizacional;
    private List<MedioDeContactoDTO> mediosContacto = new ArrayList<MedioDeContactoDTO>();
    private String nombreCompleto;
  //AGENCIAS
    private CatDiscriminanteDTO discriminante;
    //UIE
    private CatUIEspecializadaDTO unidadIEspecializada;
    

    /**
     * Para la notificaciòn de eventos el funcionario tiene un conjunto de
     * notificaciones
     */
    private List<NotificacionDTO> notificaciones = new ArrayList<NotificacionDTO>();

    
    private boolean mejorDisponibilidad = false;
    
    /**
	 * 
	 */
    public FuncionarioDTO() {
        super();
    }
    /**
     * @param icaveFuncionario
     */
    public FuncionarioDTO(Long claveFuncionario) {
        super();
        this.claveFuncionario = claveFuncionario;
    }
       
    //Implementado para ser utilizado en Servicio de ConsultarBandejaVisitaduria
    public boolean equals(Object obj) {
		if ((this == obj))
			return true;
		if ((obj == null))
			return false;
		if (!(obj instanceof FuncionarioDTO))
			return false;
		FuncionarioDTO funcionTemp = (FuncionarioDTO) obj;

		return ((this.getClaveFuncionario().longValue() == funcionTemp
				.getClaveFuncionario().longValue()));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (this.getClaveFuncionario() == null ? 0 : this
						.getClaveFuncionario().hashCode());
		return result;
	}
	
    
    /**
     * 
     * @param medioContactillo
     */
    public void addMedioContacto(MedioDeContactoDTO medioContactillo) {
        if (this.mediosContacto == null) {
            this.mediosContacto = new ArrayList<MedioDeContactoDTO>();
        }
        this.mediosContacto.add(medioContactillo);
    }

    /**
     * Método de acceso al campo icaveFuncionario.
     * 
     * @return El valor del campo icaveFuncionario
     */
    public Long getClaveFuncionario() {
        return claveFuncionario;
    }
    /**
     * Asigna el valor al campo icaveFuncionario.
     * 
     * @param icaveFuncionario
     *            el valor icaveFuncionario a asignar
     */
    public void setClaveFuncionario(Long claveFuncionario) {
        this.claveFuncionario = claveFuncionario;
    }
    /**
     * Método de acceso al campo persona.
     * 
     * @return El valor del campo persona
     */
    public PersonaDTO getPersona() {
        return persona;
    }
    /**
     * Asigna el valor al campo persona.
     * 
     * @param persona
     *            el valor persona a asignar
     */
    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }
    
    public ArchivoDigitalDTO getArchivoDigital() {
        return this.archivoDigital;
    }

    public void setArchivoDigital(ArchivoDigitalDTO archivoDigital) {
        this.archivoDigital = archivoDigital;
    }

    /**
     * Método de acceso al campo funcionarioJefe.
     * 
     * @return El valor del campo funcionarioJefe
     */
    public FuncionarioDTO getFuncionarioJefe() {
        return funcionarioJefe;
    }
    /**
     * Asigna el valor al campo funcionarioJefe.
     * 
     * @param funcionarioJefe
     *            el valor funcionarioJefe a asignar
     */
    public void setFuncionarioJefe(FuncionarioDTO funcionarioJefe) {
        this.funcionarioJefe = funcionarioJefe;
    }
    /**
     * Método de acceso al campo nombreFuncionario.
     * 
     * @return El valor del campo nombreFuncionario
     */
    public String getNombreFuncionario() {
        return nombreFuncionario;
    }
    /**
     * Asigna el valor al campo nombreFuncionario.
     * 
     * @param nombreFuncionario
     *            el valor nombreFuncionario a asignar
     */
    public void setNombreFuncionario(String nombreFuncionario) {
        this.nombreFuncionario = nombreFuncionario;
    }
    /**
     * Método de acceso al campo apellidoPaternoFuncionario.
     * 
     * @return El valor del campo apellidoPaternoFuncionario
     */
    public String getApellidoPaternoFuncionario() {
        return apellidoPaternoFuncionario;
    }
    /**
     * Asigna el valor al campo apellidoPaternoFuncionario.
     * 
     * @param apellidoPaternoFuncionario
     *            el valor apellidoPaternoFuncionario a asignar
     */
    public void setApellidoPaternoFuncionario(String apellidoPaternoFuncionario) {
        this.apellidoPaternoFuncionario = apellidoPaternoFuncionario;
    }
    /**
     * Método de acceso al campo apellidoMaternoFuncionario.
     * 
     * @return El valor del campo apellidoMaternoFuncionario
     */
    public String getApellidoMaternoFuncionario() {
        return apellidoMaternoFuncionario;
    }
    /**
     * Asigna el valor al campo apellidoMaternoFuncionario.
     * 
     * @param apellidoMaternoFuncionario
     *            el valor apellidoMaternoFuncionario a asignar
     */
    public void setApellidoMaternoFuncionario(String apellidoMaternoFuncionario) {
        this.apellidoMaternoFuncionario = apellidoMaternoFuncionario;
    }
    /**
     * Método de acceso al campo sexo.
     * 
     * @return El valor del campo sexo
     */
    public String getSexo() {
        return sexo;
    }
    /**
     * Asigna el valor al campo sexo.
     * 
     * @param sexo
     *            el valor sexo a asignar
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    /**
     * Método de acceso al campo rfc.
     * 
     * @return El valor del campo rfc
     */
    public String getRfc() {
        return rfc;
    }
    /**
     * Asigna el valor al campo rfc.
     * 
     * @param rfc
     *            el valor rfc a asignar
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    /**
     * Método de acceso al campo curp.
     * 
     * @return El valor del campo curp
     */
    public String getCurp() {
        return curp;
    }
    /**
     * Asigna el valor al campo curp.
     * 
     * @param curp
     *            el valor curp a asignar
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }
    /**
     * Método de acceso al campo fechaNacimiento.
     * 
     * @return El valor del campo fechaNacimiento
     */
    public java.util.Date getFechaNacimiento() {
        return fechaNacimiento;
    }    
    /**
     * Método de acceso al campo fechaIngreso.
     * 
     * @return El valor del campo fechaIngreso
     */
    public java.util.Date getFechaIngreso() {
        return fechaIngreso;
    }
    /**
     * Asigna el valor al campo fechaNacimiento.
     * 
     * @param fechaNacimiento
     *            el valor fechaNacimiento a asignar
     */
    public void setFechaNacimiento(java.util.Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    /**
     * Asigna el valor al campo fechaIngreso
     *        
     * @param fechaIngreso
     *            el valor fechaIngreso a asignar
     */
    public void setFechaIngreso(java.util.Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }    
    /**
     * Método de acceso al campo email.
     * 
     * @return El valor del campo email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Asigna el valor al campo email.
     * 
     * @param email
     *            el valor email a asignar
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Método de acceso al campo cedula.
     * 
     * @return El valor del campo cedula
     */
    public String getCedula() {
        return cedula;
    }
    /**
     * Asigna el valor al campo cedula.
     * 
     * @param cedula
     *            el valor cedula a asignar
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    /**
     * Método de acceso al campo departamento.
     * 
     * @return El valor del campo departamento
     */
    public DepartamentoDTO getDepartamento() {
        return departamento;
    }
    /**
     * Asigna el valor al campo departamento.
     * 
     * @param departamento
     *            el valor departamento a asignar
     */
    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }

    public ValorDTO getPuesto() {
        return puesto;
    }
    public void setPuesto(ValorDTO puesto) {
        this.puesto = puesto;
    }
    public ValorDTO getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(ValorDTO especialidad) {
        this.especialidad = especialidad;
    }
    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }
    public String getNombreInstitucion() {
        return nombreInstitucion;
    }
    /**
     * Método de acceso al campo cargaTrabajo.
     * 
     * @return El valor del campo cargaTrabajo
     */
    public Double getCargaTrabajo() {
        return cargaTrabajo;
    }
    /**
     * Asigna el valor al campo cargaTrabajo.
     * 
     * @param cargaTrabajo
     *            el valor cargaTrabajo a asignar
     */
    public void setCargaTrabajo(Double cargaTrabajo) {
        this.cargaTrabajo = cargaTrabajo;
    }
    /**
     * Método de acceso al campo tipoEspecialidad.
     * 
     * @return El valor del campo tipoEspecialidad
     */
    public ValorDTO getTipoEspecialidad() {
        return tipoEspecialidad;
    }
    /**
     * Asigna el valor al campo tipoEspecialidad.
     * 
     * @param tipoEspecialidad
     *            el valor tipoEspecialidad a asignar
     */
    public void setTipoEspecialidad(ValorDTO tipoEspecialidad) {
        this.tipoEspecialidad = tipoEspecialidad;
    }

    /**
     * Método de acceso al campo esPar.
     * 
     * @return El valor del campo esPar
     */
    public Boolean getEsPar() {
        return esPar;
    }
    /**
     * Asigna el valor al campo esPar.
     * 
     * @param esPar
     *            el valor esPar a asignar
     */
    public void setEsPar(Boolean esPar) {
        this.esPar = esPar;
    }
    @Override
    public int compareTo(FuncionarioDTO funcionario) {
        // Proteger de valores null en carga de trabajo
        if (this.cargaTrabajo == null)
            this.cargaTrabajo = 0.0;
        if (funcionario.getCargaTrabajo() == null)
            funcionario.setCargaTrabajo(0.0);

        if (funcionario != null && funcionario.getCargaTrabajo() != null)
            return (this.cargaTrabajo.compareTo(funcionario.getCargaTrabajo()));
        else {
            return 0;
        }
    }

    public String getNombreCompleto() {
        final StringBuffer nc = new StringBuffer();
        if (StringUtils.isNotBlank(nombreFuncionario)) {
            nc.append(nombreFuncionario).append(" ");
        }
        if (StringUtils.isNotBlank(apellidoPaternoFuncionario)) {
            nc.append(apellidoPaternoFuncionario).append(" ");
        }
        if (StringUtils.isNotBlank(apellidoMaternoFuncionario)) {
            nc.append(apellidoMaternoFuncionario);
        }
        
        if(nc.length() >0){
        	return nc.toString().trim();
        }
        return this.nombreCompleto;
    }
    /**
     * Método de acceso al campo mediosContacto.
     * 
     * @return El valor del campo mediosContacto
     */
    public List<MedioDeContactoDTO> getMediosContacto() {
        return mediosContacto;
    }
    /**
     * Asigna el valor al campo mediosContacto.
     * 
     * @param mediosContacto
     *            el valor mediosContacto a asignar
     */
    public void setMediosContacto(List<MedioDeContactoDTO> mediosContacto) {
        this.mediosContacto = mediosContacto;
    }
    /**
     * Método de acceso al campo numeroEmpleado.
     * 
     * @return El valor del campo numeroEmpleado
     */
    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }
    /**
     * Asigna el valor al campo numeroEmpleado.
     * 
     * @param numeroEmpleado
     *            el valor numeroEmpleado a asignar
     */
    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }
    /**
     * Asigna el valor al campo nombreCompleto.
     * 
     * @param nombreCompleto
     *            el valor nombreCompleto a asignar
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    /**
     * Método de acceso al campo jerarquiaOrganizacional.
     * @return El valor del campo jerarquiaOrganizacional
     */
    public JerarquiaOrganizacionalDTO getJerarquiaOrganizacional() {
        return jerarquiaOrganizacional;
    }
    /**
     * Asigna el valor al campo jerarquiaOrganizacional.
     * @param jerarquiaOrganizacional el valor jerarquiaOrganizacional a asignar
     */
    public void setJerarquiaOrganizacional(
            JerarquiaOrganizacionalDTO jerarquiaOrganizacional) {
        this.jerarquiaOrganizacional = jerarquiaOrganizacional;
    }
	/**
	 * Regresa el valor de la propiedad notificaciones
	 * @return the notificaciones
	 */
	public List<NotificacionDTO> getNotificaciones() {
		return notificaciones;
	}
	/**
	 * Establece el valor de la propiedad notificaciones
	 * @param notificaciones valo notificaciones a almacenar
	 */
	public void setNotificaciones(List<NotificacionDTO> notificaciones) {
		this.notificaciones = notificaciones;
	}
	/**
	 * Regresa el valor de la propiedad mejorDisponibilidad
	 * @return the mejorDisponibilidad
	 */
	public boolean isMejorDisponibilidad() {
		return mejorDisponibilidad;
	}
	/**
	 * Regresa el valor de la propiedad institucion
	 * @return the institucion
	 */
	public ConfInstitucionDTO getInstitucion() {
		return institucion;
	}
	/**
	 * Establece el valor de la propiedad institucion
	 * @param institucion valo institucion a almacenar
	 */
	public void setInstitucion(ConfInstitucionDTO institucion) {
		this.institucion = institucion;
	}
	/**
	 * Establece el valor de la propiedad mejorDisponibilidad
	 * @param mejorDisponibilidad valo mejorDisponibilidad a almacenar
	 */
	public void setMejorDisponibilidad(boolean mejorDisponibilidad) {
		this.mejorDisponibilidad = mejorDisponibilidad;
	}
	/**
	 * @return the discriminante
	 */
	public CatDiscriminanteDTO getDiscriminante() {
		return discriminante;
	}
	/**
	 * @param discriminante the discriminante to set
	 */
	public void setDiscriminante(CatDiscriminanteDTO discriminante) {
		this.discriminante = discriminante;
	}
	/**
	 * Asigna el valor al campo unidadIEspecializada.
	 * @param unidadIEspecializada el valor unidadIEspecializada a asignar
	 */
	public void setUnidadIEspecializada(CatUIEspecializadaDTO unidadIEspecializada) {
		this.unidadIEspecializada = unidadIEspecializada;
	}
	/**
	 * Método de acceso al campo unidadIEspecializada.
	 * @return El valor del campo unidadIEspecializada
	 */
	public CatUIEspecializadaDTO getUnidadIEspecializada() {
		return unidadIEspecializada;
	}
	

}
