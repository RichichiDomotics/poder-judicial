/**
 * Nombre del Programa : CatDelitoDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 2 Jun 2011
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
package mx.gob.segob.nsjp.dto.catalogo;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.dto.institucion.InstitucionDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class CatDelitoDTO extends GenericDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4618514099766933220L;
	private Long catDelitoId;
    private String claveDelito;
    private DepartamentoDTO departamento;
    private InstitucionDTO institucion;
    private String nombre;
    private Boolean esGrave;
    private Boolean esAccionPenPriv;
    private Long penaMinimaAnios;
    private Long penaMinimaMeses;
    private Long penaMinimaDias;
    private Long penaMaximaAnios;
    private Long penaMaximaMeses;
    private Long penaMaximaDias;
    private CatUIEspecializadaDTO unidadIEspecializada;

    public CatDelitoDTO(Long catDelitoId, String claveDelito, String nombre,
			Boolean esGrave) {
		super();
		this.catDelitoId = catDelitoId;
		this.claveDelito = claveDelito;
		this.nombre = nombre;
		this.esGrave = esGrave;
	}
    
	public CatDelitoDTO() {
	}
	
    public CatDelitoDTO(Long catDelitoId) {
		super();
		this.catDelitoId = catDelitoId;
	}

	/**
     * Indica si el seguimiento es interno en base a la existencia de la
     * relación con departamento.
     * 
     * @return
     */
    public boolean isSeguimientoInterno() {
        return (departamento != null);
    }

    /**
     * Método de acceso al campo claveDelito.
     * 
     * @return El valor del campo claveDelito
     */
    public String getClaveDelito() {
        return claveDelito;
    }
    /**
     * Asigna el valor al campo claveDelito.
     * 
     * @param claveDelito
     *            el valor claveDelito a asignar
     */
    public void setClaveDelito(String claveDelito) {
        this.claveDelito = claveDelito;
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
    /**
     * Método de acceso al campo institucion.
     * 
     * @return El valor del campo institucion
     */
    public InstitucionDTO getInstitucion() {
        return institucion;
    }
    /**
     * Asigna el valor al campo institucion.
     * 
     * @param institucion
     *            el valor institucion a asignar
     */
    public void setInstitucion(InstitucionDTO institucion) {
        this.institucion = institucion;
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
     * Método de acceso al campo esGrave.
     * 
     * @return El valor del campo esGrave
     */
    public Boolean getEsGrave() {
        return esGrave;
    }
    /**
     * Asigna el valor al campo esGrave.
     * 
     * @param esGrave
     *            el valor esGrave a asignar
     */
    public void setEsGrave(Boolean esGrave) {
        this.esGrave = esGrave;
    }

	public Boolean getEsAccionPenPriv() {
		return esAccionPenPriv;
	}
	public void setEsAccionPenPriv(Boolean esAccionPenPriv) {
		this.esAccionPenPriv = esAccionPenPriv;
	}
	/**
	 * Asigna el valor al campo catDelitoId.
	 * @param catDelitoId el valor catDelitoId a asignar
	 */
	public void setCatDelitoId(Long catDelitoId) {
		this.catDelitoId = catDelitoId;
	}

	/**
	 * Método de acceso al campo catDelitoId.
	 * @return El valor del campo catDelitoId
	 */
	public Long getCatDelitoId() {
		return catDelitoId;
	}


	/**
	 * @return the penaMinimaAnios
	 */
	public Long getPenaMinimaAnios() {
		return penaMinimaAnios;
	}

	/**
	 * @param penaMinimaAnios the penaMinimaAnios to set
	 */
	public void setPenaMinimaAnios(Long penaMinimaAnios) {
		this.penaMinimaAnios = penaMinimaAnios;
	}

	/**
	 * @return the penaMinimaMeses
	 */
	public Long getPenaMinimaMeses() {
		return penaMinimaMeses;
	}

	/**
	 * @param penaMinimaMeses the penaMinimaMeses to set
	 */
	public void setPenaMinimaMeses(Long penaMinimaMeses) {
		this.penaMinimaMeses = penaMinimaMeses;
	}

	/**
	 * @return the penaMinimaDias
	 */
	public Long getPenaMinimaDias() {
		return penaMinimaDias;
	}

	/**
	 * @param penaMinimaDias the penaMinimaDias to set
	 */
	public void setPenaMinimaDias(Long penaMinimaDias) {
		this.penaMinimaDias = penaMinimaDias;
	}

	/**
	 * @return the penaMaximaAnios
	 */
	public Long getPenaMaximaAnios() {
		return penaMaximaAnios;
	}

	/**
	 * @param penaMaximaAnios the penaMaximaAnios to set
	 */
	public void setPenaMaximaAnios(Long penaMaximaAnios) {
		this.penaMaximaAnios = penaMaximaAnios;
	}

	/**
	 * @return the penaMaximaMeses
	 */
	public Long getPenaMaximaMeses() {
		return penaMaximaMeses;
	}

	/**
	 * @param penaMaximaMeses the penaMaximaMeses to set
	 */
	public void setPenaMaximaMeses(Long penaMaximaMeses) {
		this.penaMaximaMeses = penaMaximaMeses;
	}

	/**
	 * @return the penaMaximaDias
	 */
	public Long getPenaMaximaDias() {
		return penaMaximaDias;
	}

	/**
	 * @param penaMaximaDias the penaMaximaDias to set
	 */
	public void setPenaMaximaDias(Long penaMaximaDias) {
		this.penaMaximaDias = penaMaximaDias;
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
