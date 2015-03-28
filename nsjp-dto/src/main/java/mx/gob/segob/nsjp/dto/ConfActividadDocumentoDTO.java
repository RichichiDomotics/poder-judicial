/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.segob.nsjp.dto;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;

/**
 *
 * @author Jacob Lobaco
 */
public class ConfActividadDocumentoDTO extends GenericDTO{

	private Long confActividadDocumentoId;
	private JerarquiaOrganizacionalDTO jerarquiaOrganizacional;
	private Boolean muestraEnCombo;
	private String accion;
    private Long tipoActividadId;
    private String nombreActividad;
    private Long tipoDocumentoId;
    private String nombreDocumento;
    private ValorDTO estadoCambioExpediente;
    private Integer grupoActividad;
    private Boolean usaEditor;
    private FormaDTO forma;
    

    public Long getConfActividadDocumentoId() {
		return confActividadDocumentoId;
	}

	public void setConfActividadDocumentoId(Long confActividadDocumentoId) {
		this.confActividadDocumentoId = confActividadDocumentoId;
	}

	public JerarquiaOrganizacionalDTO getJerarquiaOrganizacional() {
		return jerarquiaOrganizacional;
	}

	public void setJerarquiaOrganizacional(
			JerarquiaOrganizacionalDTO jerarquiaOrganizacional) {
		this.jerarquiaOrganizacional = jerarquiaOrganizacional;
	}

	public Boolean getMuestraEnCombo() {
		return muestraEnCombo;
	}

	public void setMuestraEnCombo(Boolean muestraEnCombo) {
		this.muestraEnCombo = muestraEnCombo;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Boolean getUsaEditor() {
		return usaEditor;
	}

	public void setUsaEditor(Boolean usaEditor) {
		this.usaEditor = usaEditor;
	}

	/**
     * Get the value of tipoActividadId
     *
     * @return the value of tipoActividadId
     */
    public Long getTipoActividadId() {
        return tipoActividadId;
    }

    /**
     * Set the value of tipoActividadId
     *
     * @param tipoActividadId new value of tipoActividadId
     */
    public void setTipoActividadId(Long tipoActividadId) {
        this.tipoActividadId = tipoActividadId;
    }

    

    /**
     * Get the value of nombreActividad
     *
     * @return the value of nombreActividad
     */
    public String getNombreActividad() {
        return nombreActividad;
    }

    /**
     * Set the value of nombreActividad
     *
     * @param nombreActividad new value of nombreActividad
     */
    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }
    

    /**
     * Get the value of tipoDocumentoId
     *
     * @return the value of tipoDocumentoId
     */
    public Long getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    /**
     * Set the value of tipoDocumentoId
     *
     * @param tipoDocumentoId new value of tipoDocumentoId
     */
    public void setTipoDocumentoId(Long tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }
    

    /**
     * Get the value of nombreDocumento
     *
     * @return the value of nombreDocumento
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * Set the value of nombreDocumento
     *
     * @param nombreDocumento new value of nombreDocumento
     */
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }
    

    /**
	 * Método de acceso al campo estadoCambioExpediente.
	 * @return El valor del campo estadoCambioExpediente
	 */
	public ValorDTO getEstadoCambioExpediente() {
		return estadoCambioExpediente;
	}

	/**
	 * Asigna el valor al campo estadoCambioExpediente.
	 * @param estadoCambioExpediente el valor estadoCambioExpediente a asignar
	 */
	public void setEstadoCambioExpediente(ValorDTO estadoCambioExpediente) {
		this.estadoCambioExpediente = estadoCambioExpediente;
	}

	/**
     * Get the value of grupoActividad
     *
     * @return the value of grupoActividad
     */
    public Integer getGrupoActividad() {
        return grupoActividad;
    }

    /**
     * Set the value of grupoActividad
     *
     * @param grupoActividad new value of grupoActividad
     */
    public void setGrupoActividad(Integer grupoActividad) {
        this.grupoActividad = grupoActividad;
    }
    

    /**
     * Get the value of usaEditor
     *
     * @return the value of usaEditor
     */
    public boolean isUsaEditor() {
        return usaEditor;
    }

    /**
     * Set the value of usaEditor
     *
     * @param usaEditor new value of usaEditor
     */
    public void setUsaEditor(boolean usaEditor) {
        this.usaEditor = usaEditor;
    }

	/**
	 * Método de acceso al campo forma.
	 * @return El valor del campo forma
	 */
	public FormaDTO getForma() {
		return forma;
	}

	/**
	 * Asigna el valor al campo forma.
	 * @param forma el valor forma a asignar
	 */
	public void setForma(FormaDTO forma) {
		this.forma = forma;
	}
}
