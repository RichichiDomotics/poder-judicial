/**
*
* Nombre del Programa : EntrevistaComplementaria.java                                    
* Autor                            : rgama                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                     
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO para la transferencia de parametros de EntrevistaComplementaria entre la vista y servicios.                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.dto.sesion;

import java.util.HashSet;
import java.util.Set;




/**
 * @author rgama
 * @version 1.0
 */
public class EntrevistaComplementariaDTO extends SesionDTO {

	private static final long serialVersionUID = 722526631129238704L;
	private String motivoConsulta;
	private String concienciaProblema;
	private String impresionDiagnostico;
	private String hipotesisFamilia;
	private Set<FamiliarDTO> familiares = new HashSet<FamiliarDTO>(0);
	
	
	
	public EntrevistaComplementariaDTO(Long idObjeto) {
		super.setSesionId(idObjeto);
	}
	public EntrevistaComplementariaDTO() {
	}
	/**
	 * Método de acceso al campo motivoConsulta.
	 * @return El valor del campo motivoConsulta
	 */
	public String getMotivoConsulta() {
		return motivoConsulta;
	}
	/**
	 * Asigna el valor al campo motivoConsulta.
	 * @param motivoConsulta el valor motivoConsulta a asignar
	 */
	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}
	/**
	 * Método de acceso al campo concienciaProblema.
	 * @return El valor del campo concienciaProblema
	 */
	public String getConcienciaProblema() {
		return concienciaProblema;
	}
	/**
	 * Asigna el valor al campo concienciaProblema.
	 * @param concienciaProblema el valor concienciaProblema a asignar
	 */
	public void setConcienciaProblema(String concienciaProblema) {
		this.concienciaProblema = concienciaProblema;
	}
	/**
	 * Método de acceso al campo impresionDiagnostico.
	 * @return El valor del campo impresionDiagnostico
	 */
	public String getImpresionDiagnostico() {
		return impresionDiagnostico;
	}
	/**
	 * Asigna el valor al campo impresionDiagnostico.
	 * @param impresionDiagnostico el valor impresionDiagnostico a asignar
	 */
	public void setImpresionDiagnostico(String impresionDiagnostico) {
		this.impresionDiagnostico = impresionDiagnostico;
	}
	/**
	 * Método de acceso al campo hipotesisFamilia.
	 * @return El valor del campo hipotesisFamilia
	 */
	public String getHipotesisFamilia() {
		return hipotesisFamilia;
	}
	/**
	 * Asigna el valor al campo hipotesisFamilia.
	 * @param hipotesisFamilia el valor hipotesisFamilia a asignar
	 */
	public void setHipotesisFamilia(String hipotesisFamilia) {
		this.hipotesisFamilia = hipotesisFamilia;
	}
	/**
	 * Método de acceso al campo familiares.
	 * @return El valor del campo familiares
	 */
	public Set<FamiliarDTO> getFamiliares() {
		return familiares;
	}
	/**
	 * Asigna el valor al campo familiares.
	 * @param familiares el valor familiares a asignar
	 */
	public void setFamiliares(Set<FamiliarDTO> familiares) {
		this.familiares = familiares;
	}	
}
