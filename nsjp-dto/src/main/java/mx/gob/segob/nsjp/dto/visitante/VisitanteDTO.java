/**
 * 
 */
package mx.gob.segob.nsjp.dto.visitante;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * @author LuisMG
 *
 */
public class VisitanteDTO extends GenericDTO{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -127100453028874814L;
	private Long idVisitante;
	private String cIP;
	private String cMac;
	private Integer iIntentos;
	
	/**
	 * Constructor por default
	 */
	public VisitanteDTO(){
		
	}
	
	/**
	 * Constructor mínimo
	 */
	public VisitanteDTO(String cIP,Integer iIntentos){
		this.cIP=cIP;
		this.iIntentos=iIntentos;
	}

	/**
	 * @return the idVisitante
	 */
	public Long getIdVisitante() {
		return idVisitante;
	}

	/**
	 * @param idVisitante the idVisitante to set
	 */
	public void setIdVisitante(Long idVisitante) {
		this.idVisitante = idVisitante;
	}

	/**
	 * @return the cIP
	 */
	public String getcIP() {
		return cIP;
	}

	/**
	 * @param cIP the cIP to set
	 */
	public void setcIP(String cIP) {
		this.cIP = cIP;
	}

	/**
	 * @return the cMac
	 */
	public String getcMac() {
		return cMac;
	}

	/**
	 * @param cMac the cMac to set
	 */
	public void setcMac(String cMac) {
		this.cMac = cMac;
	}

	/**
	 * @return the iIntentos
	 */
	public Integer getiIntentos() {
		return iIntentos;
	}

	/**
	 * @param iIntentos the iIntentos to set
	 */
	public void setiIntentos(Integer iIntentos) {
		this.iIntentos = iIntentos;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
