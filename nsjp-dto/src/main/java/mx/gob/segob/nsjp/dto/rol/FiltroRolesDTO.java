/**
 * 
 */
package mx.gob.segob.nsjp.dto.rol;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * @author IgnacioFO
 *
 */
public class FiltroRolesDTO extends GenericDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6782535294357004122L;
	private String idRolSelec;
	private Long confInstitucionId;

	/**
	 * Constructor por default
	 */
	public FiltroRolesDTO(){
		
	}
	/**
	 * Constructor Mínimo
	 * @param confInstitucionId
	 */
	public FiltroRolesDTO (Long confInstitucionId){
		this.confInstitucionId=confInstitucionId;
	}
	/**
	 * @return the idRolSelec
	 */
	public String getIdRolSelec() {
		return idRolSelec;
	}
	
	/**
	 * @param idRolSelec the idRolSelec to set
	 */
	public void setIdRolSelec(String idRolSelec) {
		this.idRolSelec = idRolSelec;
	}

	/**
	 * @return the confInstitucionId
	 */
	public Long getConfInstitucionId() {
		return confInstitucionId;
	}

	/**
	 * @param confInstitucionId the confInstitucionId to set
	 */
	public void setConfInstitucionId(Long confInstitucionId) {
		this.confInstitucionId = confInstitucionId;
	}
	
	

}
