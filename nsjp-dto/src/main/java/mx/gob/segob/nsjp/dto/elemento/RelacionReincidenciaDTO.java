/**
*
* Nombre del Programa : RelacionReincidenciaDTO.java                                    
* Autor                            : rgama                                         
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 23/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO para la transferencia de parametros de RelacionReincidencia entre la vista y servicios.                      
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
package mx.gob.segob.nsjp.dto.elemento;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;


/**
 * @author rgama
 * @version 1.0
 */
public class RelacionReincidenciaDTO extends GenericDTO {
	
	private static final long serialVersionUID = -7931851623229238259L;

	// Fields
	private Long relacionReincidenciaId;
	private java.util.Date fechaRelacion;	
	private String strFechaRelacion;	

	//Caso relacionado
	private CasoDTO caso;
	//Funcionario relaciona
    private FuncionarioDTO funcionario;
    private ElementoDTO elemento;
    
	public RelacionReincidenciaDTO() {
	}
    
	public RelacionReincidenciaDTO(Long relacionReincidenciaId, CasoDTO caso,
			FuncionarioDTO funcionario) {
		super();
		this.relacionReincidenciaId = relacionReincidenciaId;
		this.caso = caso;
		this.funcionario = funcionario;
	}
	public RelacionReincidenciaDTO(Long relacionReincidenciaId) {
		super();
		this.relacionReincidenciaId = relacionReincidenciaId;
	}
	/**
	 * Método de acceso al campo relacionReincidenciaId.
	 * @return El valor del campo relacionReincidenciaId
	 */
	public Long getRelacionReincidenciaId() {
		return relacionReincidenciaId;
	}
	/**
	 * Asigna el valor al campo relacionReincidenciaId.
	 * @param relacionReincidenciaId el valor relacionReincidenciaId a asignar
	 */
	public void setRelacionReincidenciaId(Long relacionReincidenciaId) {
		this.relacionReincidenciaId = relacionReincidenciaId;
	}
	/**
	 * Método de acceso al campo fechaRelacion.
	 * @return El valor del campo fechaRelacion
	 */
	public java.util.Date getFechaRelacion() {
		return fechaRelacion;
	}
	/**
	 * Asigna el valor al campo fechaRelacion.
	 * @param fechaRelacion el valor fechaRelacion a asignar
	 */
	public void setFechaRelacion(java.util.Date fechaRelacion) {
		this.fechaRelacion = fechaRelacion;
	}
	/**
	 * Método de acceso al campo caso.
	 * @return El valor del campo caso
	 */
	public CasoDTO getCaso() {
		return caso;
	}
	/**
	 * Asigna el valor al campo caso.
	 * @param caso el valor caso a asignar
	 */
	public void setCaso(CasoDTO caso) {
		this.caso = caso;
	}
	/**
	 * Método de acceso al campo funcionario.
	 * @return El valor del campo funcionario
	 */
	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}
	/**
	 * Asigna el valor al campo funcionario.
	 * @param funcionario el valor funcionario a asignar
	 */
	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}
	/**
	 * Método de acceso al campo elemento.
	 * @return El valor del campo elemento
	 */
	public ElementoDTO getElemento() {
		return elemento;
	}
	/**
	 * Asigna el valor al campo elemento.
	 * @param elemento el valor elemento a asignar
	 */
	public void setElemento(ElementoDTO elemento) {
		this.elemento = elemento;
	}
	/**
	 * Método de acceso al campo strFechaRelacion.
	 * @return El valor del campo strFechaRelacion
	 */
	public String getStrFechaRelacion() {
		if(strFechaRelacion != null){
			return strFechaRelacion;
		}
		return (fechaRelacion!=null?DateUtils.formatear(fechaRelacion):null);
	}
	/**
	 * Asigna el valor al campo strFechaRelacion.
	 * @param strFechaRelacion el valor strFechaRelacion a asignar
	 */
	public void setStrFechaRelacion(String strFechaRelacion) {
		this.strFechaRelacion = strFechaRelacion;
	}
	
}
