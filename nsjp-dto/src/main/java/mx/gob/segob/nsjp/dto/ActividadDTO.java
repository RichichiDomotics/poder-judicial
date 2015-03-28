/**
 * Nombre del Programa : ActividadDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 May 2011
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
package mx.gob.segob.nsjp.dto;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.actividad.ActividadesRS;
import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ActividadDTO extends GenericDTO {
	private ActividadesRS tipoActividadRS;
    private Actividades tipoActividad;
    private String nombre;
    private Date fechaCreacion;
    private Long actividadId;
    /**
     * M�todo de acceso al campo tipoActividad.
     * 
     * @return El valor del campo tipoActividad
     */
    public Actividades getTipoActividad() {
        return tipoActividad;
    }

    /**
     * Asigna el valor al campo tipoActividad.
     * 
     * @param tipoActividad
     *            el valor tipoActividad a asignar
     */
    public void setTipoActividad(Actividades tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    /**
     * M�todo de acceso al campo nombre.
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
	 * M�todo de acceso al campo fechaCreacion.
	 * @return El valor del campo fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Asigna el valor al campo fechaCreacion.
	 * @param fechaCreacion el valor fechaCreacion a asignar
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * M�todo de acceso al campo actividadId.
	 * @return El valor del campo actividadId
	 */
	public Long getActividadId() {
		return actividadId;
	}

	/**
	 * Asigna el valor al campo actividadId.
	 * @param actividadId el valor actividadId a asignar
	 */
	public void setActividadId(Long actividadId) {
		this.actividadId = actividadId;
	}

	/**
	 * @return the tipoActividadRS
	 */
	public ActividadesRS getTipoActividadRS() {
		return tipoActividadRS;
	}

	/**
	 * @param tipoActividadRS the tipoActividadRS to set
	 */
	public void setTipoActividadRS(ActividadesRS tipoActividadRS) {
		this.tipoActividadRS = tipoActividadRS;
	}
    
    
}
