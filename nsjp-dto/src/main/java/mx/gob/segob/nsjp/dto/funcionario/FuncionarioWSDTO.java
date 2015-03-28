/**
 * Nombre del Programa : FuncionarioWSDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 Sep 2011
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

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class FuncionarioWSDTO extends GenericWSDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Long puestoId;
    private Long especialidadId;
    private Long tipoEspecialidadId;
    private Long jerarquiaOrganizacionalId;
    private String numeroEmpleado;
    private Long claveFuncionario;
    private String nombrePuesto;
    private String nombreArea;
    private String email;
    private String nombreDiscriminante;
  
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
     * Método de acceso al campo puestoId.
     * @return El valor del campo puestoId
     */
    public Long getPuestoId() {
        return puestoId;
    }
    /**
     * Asigna el valor al campo puestoId.
     * @param puestoId el valor puestoId a asignar
     */
    public void setPuestoId(Long puestoId) {
        this.puestoId = puestoId;
    }
    /**
     * Método de acceso al campo especialidadId.
     * @return El valor del campo especialidadId
     */
    public Long getEspecialidadId() {
        return especialidadId;
    }
    /**
     * Asigna el valor al campo especialidadId.
     * @param especialidadId el valor especialidadId a asignar
     */
    public void setEspecialidadId(Long especialidadId) {
        this.especialidadId = especialidadId;
    }
    /**
     * Método de acceso al campo tipoEspecialidadId.
     * @return El valor del campo tipoEspecialidadId
     */
    public Long getTipoEspecialidadId() {
        return tipoEspecialidadId;
    }
    /**
     * Asigna el valor al campo tipoEspecialidadId.
     * @param tipoEspecialidadId el valor tipoEspecialidadId a asignar
     */
    public void setTipoEspecialidadId(Long tipoEspecialidadId) {
        this.tipoEspecialidadId = tipoEspecialidadId;
    }
    /**
     * Método de acceso al campo numeroEmpleado.
     * @return El valor del campo numeroEmpleado
     */
    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }
    /**
     * Asigna el valor al campo numeroEmpleado.
     * @param numeroEmpleado el valor numeroEmpleado a asignar
     */
    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }
    /**
     * Método de acceso al campo jerarquiaOrganizacionalId.
     * @return El valor del campo jerarquiaOrganizacionalId
     */
    public Long getJerarquiaOrganizacionalId() {
        return jerarquiaOrganizacionalId;
    }
    /**
     * Asigna el valor al campo jerarquiaOrganizacionalId.
     * @param jerarquiaOrganizacionalId el valor jerarquiaOrganizacionalId a asignar
     */
    public void setJerarquiaOrganizacionalId(Long jerarquiaOrganizacionalId) {
        this.jerarquiaOrganizacionalId = jerarquiaOrganizacionalId;
    }
	public Long getClaveFuncionario() {
		return claveFuncionario;
	}
	public void setClaveFuncionario(Long claveFuncionario) {
		this.claveFuncionario = claveFuncionario;
	}
	
	/**
	 * Método de acceso al campo nombrePuesto.
	 * @return El valor del campo nombrePuesto
	 */
	public String getNombrePuesto() {
		return nombrePuesto;
	}
	
	/**
	 * Asigna el valor al campo nombrePuesto.
	 * @param nombrePuesto el valor nombrePuesto a asignar
	 */
	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}
	
	/**
	 * Método de acceso al campo nombreArea.
	 * @return El valor del campo nombreArea
	 */
	public String getNombreArea() {
		return nombreArea;
	}
	
	/**
	 * Asigna el valor al campo nombreArea.
	 * @param nombreArea el valor nombreArea a asignar
	 */
	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}
	
	/**
	 * Método de acceso al campo email.
	 * @return El valor del campo email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Asigna el valor al campo email.
	 * @param email el valor email a asignar
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Método de acceso al campo nombreDiscriminante.
	 * @return El valor del campo nombreDiscriminante
	 */
	public String getNombreDiscriminante() {
		return nombreDiscriminante;
	}
	
	/**
	 * Asigna el valor al campo nombreDiscriminante.
	 * @param nombreDiscriminante el valor nombreDiscriminante a asignar
	 */
	public void setNombreDiscriminante(String nombreDiscriminante) {
		this.nombreDiscriminante = nombreDiscriminante;
	}
    
    
}
