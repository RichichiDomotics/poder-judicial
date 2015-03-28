/**
 * Nombre del Programa : JerarquiaOrganizacionalDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Jul 2011
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
package mx.gob.segob.nsjp.dto.institucion;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class JerarquiaOrganizacionalDTO extends GenericDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6125121845701182536L;
	private Long jerarquiaOrganizacionalId;
	private String nombre;

	/**
     * 
     */
    public JerarquiaOrganizacionalDTO() {
        super();
    }
    /**
	 * @param jerarquiaOrganizacionalId
	 */
	public JerarquiaOrganizacionalDTO(Long jerarquiaOrganizacionalId) {
		super();
		this.jerarquiaOrganizacionalId = jerarquiaOrganizacionalId;
	}
    /**
     * @param jerarquiaOrganizacionalId
     */
    public JerarquiaOrganizacionalDTO(Long jerarquiaOrganizacionalId, String nom) {
        super();
        this.jerarquiaOrganizacionalId = jerarquiaOrganizacionalId;
        this.nombre = nom;
    }
	/**
	 * Método de acceso al campo jerarquiaOrganizacionalId.
	 * 
	 * @return El valor del campo jerarquiaOrganizacionalId
	 */
	public Long getJerarquiaOrganizacionalId() {
		return jerarquiaOrganizacionalId;
	}

	/**
	 * Asigna el valor al campo jerarquiaOrganizacionalId.
	 * 
	 * @param jerarquiaOrganizacionalId
	 *            el valor jerarquiaOrganizacionalId a asignar
	 */
	public void setJerarquiaOrganizacionalId(Long jerarquiaOrganizacionalId) {
		this.jerarquiaOrganizacionalId = jerarquiaOrganizacionalId;
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
}
