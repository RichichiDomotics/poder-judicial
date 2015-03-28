/**
* Nombre del Programa : SalaJAVS.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/11/2011
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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entidad que representa la sala con JAVS.
 * @version 1.0
 * @author GustavoBP
 *
 */
@Entity
@Table(name = "SalaJAVS")
public class SalaJAVS implements java.io.Serializable {

	private static final long serialVersionUID = -1606880488103311866L;
	
	// Fields
	private Long salaJAVSId;
	private String direccionIP;
	private String usuario;
	private String password;
	private SalaAudiencia salaAudiencia;
	

	public SalaJAVS() {
		super();
	}

	/**
	 * @param salaJAVSId
	 * @param direccionIP
	 * @param usuario
	 * @param password
	 * @param salaAudienciaId
	 */
	public SalaJAVS(Long salaJAVSId, String direccionIP, String usuario,
			String password) {
		super();
		this.salaJAVSId = salaJAVSId;
		this.direccionIP = direccionIP;
		this.usuario = usuario;
		this.password = password;
	}


	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SalaJAVS_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getSalaJAVSId() {
		return salaJAVSId;
	}

    
	public void setSalaJAVSId(Long salaJAVSId) {
		this.salaJAVSId = salaJAVSId;
	}

	@Column(name = "cDireccionIP", nullable = false, length = 20)
	public String getDireccionIP() {
		return direccionIP;
	}

	public void setDireccionIP(String direccionIP) {
		this.direccionIP = direccionIP;
	}

	@Column(name = "cUsuario", nullable = false, length = 20)
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "cPassword", nullable = false, length = 20)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SalaAudiencia_id", nullable = false)
	public SalaAudiencia getSalaAudiencia() {
		return salaAudiencia;
	}

	public void setSalaAudiencia(SalaAudiencia salaAudiencia) {
		this.salaAudiencia = salaAudiencia;
	}
}
