/**
* Nombre del Programa : JerarquiaOrgTipoSolicitud.java
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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad de Cruce de Jerarquia Organizacional con el Catalogo de Tipo de Solicitud
 * 
 * Permite determinar el tipo de solicitud q puede acceder por jerarquia organizacional, 
 * donde jerarquia se refiere a Institucion, area, departamento.. etc.
 *  * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Entity
@Table(name = "JerarquiaOrgTipoSolicitud")
public class JerarquiaOrgTipoSolicitud implements java.io.Serializable {

	private JerarquiaOrgTipoSolicitudId id;
	private JerarquiaOrganizacional jerarquiaOrganizacional;
	private Valor tipoSolicitud;
	
	public JerarquiaOrgTipoSolicitud() {
	}

	public JerarquiaOrgTipoSolicitud(JerarquiaOrgTipoSolicitudId id, JerarquiaOrganizacional jerarquiaOrganizacional,
			Valor tipoSolicitud) {
		this.id = id;
		this.jerarquiaOrganizacional = jerarquiaOrganizacional;
		this.tipoSolicitud = tipoSolicitud;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "valorId", column = @Column(name = "Valor_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "jerarquiaOrganizacionalId", column = @Column(name = "JerarquiaOrganizacional_id", nullable = false, precision = 18, scale = 0)) })
	public JerarquiaOrgTipoSolicitudId getId() {
		return this.id;
	}

	public void setId(JerarquiaOrgTipoSolicitudId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JerarquiaOrganizacional_id", nullable = false, insertable = false, updatable = false)
	public JerarquiaOrganizacional getJerarquiaOrganizacional() {
		return this.jerarquiaOrganizacional;
	}

	public void setJerarquiaOrganizacional(JerarquiaOrganizacional jerarquiaOrganizacional) {
		this.jerarquiaOrganizacional = jerarquiaOrganizacional;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Valor_id", nullable = false, insertable = false, updatable = false)
	public Valor getTipoSolicitud() {
		return this.tipoSolicitud;
	}

	public void setTipoSolicitud(
			Valor tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	
}
