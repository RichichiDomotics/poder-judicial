/**
 * 
 */
package mx.gob.segob.nsjp.dto.audiencia;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * DTO para la transferencia de parametros de SalaJAVS entre la vista y
 * servicios.
 * 
 * @author GustavoBP
 *
 */
public class SalaJAVSDTO extends GenericDTO {
	
	private static final long serialVersionUID = -7880610212168692071L;
	
	private Long salaJAVSId;
	private String direccionIP;
	private String usuarioJAVS;
	private String password;
	
	public SalaJAVSDTO() {
		super();
	}
	
	
	/**
	 * @param salaJAVSId
	 * @param direccionIP
	 * @param usuario
	 * @param password
	 */
	public SalaJAVSDTO(Long salaJAVSId, String direccionIP, String usuarioJAVS,
			String password) {
		super();
		this.salaJAVSId = salaJAVSId;
		this.direccionIP = direccionIP;
		this.usuarioJAVS = usuarioJAVS;
		this.password = password;
	}
	/**
	 * @return the salaJAVSId
	 */
	public Long getSalaJAVSId() {
		return salaJAVSId;
	}
	/**
	 * @param salaJAVSId the salaJAVSId to set
	 */
	public void setSalaJAVSId(Long salaJAVSId) {
		this.salaJAVSId = salaJAVSId;
	}
	/**
	 * @return the direccionIP
	 */
	public String getDireccionIP() {
		return direccionIP;
	}
	/**
	 * @param direccionIP the direccionIP to set
	 */
	public void setDireccionIP(String direccionIP) {
		this.direccionIP = direccionIP;
	}
	/**
	 * @return the usuarioJAVS
	 */
	public String getUsuarioJAVS() {
		return usuarioJAVS;
	}


	/**
	 * @param usuarioJAVS the usuarioJAVS to set
	 */
	public void setUsuarioJAVS(String usuarioJAVS) {
		this.usuarioJAVS = usuarioJAVS;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
