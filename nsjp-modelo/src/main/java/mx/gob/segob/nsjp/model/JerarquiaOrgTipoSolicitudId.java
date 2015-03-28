/**
* Nombre del Programa : JerarquiaOrgTipoSolicitudId.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 16/08/2011
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
package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Relación de Cruce entre entidad de JerarquiaOrganizacional y Valor (para TipoSolicitud)
 *  
 * @version 1.0
 * @author GustavoBP
 *
 */
@Embeddable
public class JerarquiaOrgTipoSolicitudId implements java.io.Serializable {

	private Long jerarquiaOrganizacionalId;
	private Long tipoSolicitudId;

	
	public JerarquiaOrgTipoSolicitudId() {
	}

	public JerarquiaOrgTipoSolicitudId(Long jerarquiaOrganizacionalId,
			Long tipoSolicitudId) {
		this.jerarquiaOrganizacionalId = jerarquiaOrganizacionalId;
		this.tipoSolicitudId = tipoSolicitudId;
	}


	@Column(name = "JerarquiaOrganizacional_id", nullable = false, precision = 18, scale = 0)
	public Long getjerarquiaOrganizacionalId() {
		return this.jerarquiaOrganizacionalId;
	}

	public void setjerarquiaOrganizacionalId(Long jerarquiaOrganizacionalId) {
		this.jerarquiaOrganizacionalId = jerarquiaOrganizacionalId;
	}

	@Column(name = "Valor_id", nullable = false, precision = 18, scale = 0)
	public Long getTipoSolicitudId() {
		return this.tipoSolicitudId;
	}

	public void setTipoSolicitudId(Long tipoSolicitudId) {
		this.tipoSolicitudId = tipoSolicitudId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof JerarquiaOrgTipoSolicitudId))
			return false;
		JerarquiaOrgTipoSolicitudId castOther = (JerarquiaOrgTipoSolicitudId) other;

		return ((this.getjerarquiaOrganizacionalId() == castOther
				.getjerarquiaOrganizacionalId()) || (this
				.getjerarquiaOrganizacionalId() != null
				&& castOther.getjerarquiaOrganizacionalId() != null && this
				.getjerarquiaOrganizacionalId().equals(
						castOther.getjerarquiaOrganizacionalId())))
				&& ((this.getTipoSolicitudId() == castOther.getTipoSolicitudId()) || (this
						.getTipoSolicitudId() != null
						&& castOther.getTipoSolicitudId() != null && this
						.getTipoSolicitudId().equals(
								castOther.getTipoSolicitudId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getjerarquiaOrganizacionalId() == null ? 0 : this
						.getjerarquiaOrganizacionalId().hashCode());
		result = 37
				* result
				+ (getTipoSolicitudId() == null ? 0 : this.getTipoSolicitudId()
						.hashCode());
		return result;
	}
	
}
