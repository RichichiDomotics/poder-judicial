/**
*
* Nombre del Programa : FamiliarDTO.java                                    
* Autor                            : rgama                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                     
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO para la transferencia de parametros de Familiar entre la vista y servicios.                      
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

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;



/**
 * @author rgama
 * @version 1.0
 */
public class FamiliarDTO extends GenericDTO {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = -1455681169675001869L;
	
	private Long familiarId;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Short edad;
	private ValorDTO escolaridad;
	private ValorDTO estadoCivil;
	private ValorDTO ocupacion;
	private EntrevistaComplementariaDTO entrevistaComplementaria;
	private CatRelacionDTO relacion; 
	
	public FamiliarDTO(Long idObjeto) {
		this.familiarId = idObjeto;
	}
	public FamiliarDTO() {
	}
	/**
	 * Método de acceso al campo familiarId.
	 * @return El valor del campo familiarId
	 */
	public Long getFamiliarId() {
		return familiarId;
	}
	/**
	 * Asigna el valor al campo familiarId.
	 * @param familiarId el valor familiarId a asignar
	 */
	public void setFamiliarId(Long familiarId) {
		this.familiarId = familiarId;
	}
	/**
	 * Método de acceso al campo nombre.
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
	 * Método de acceso al campo apellidoPaterno.
	 * @return El valor del campo apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * Asigna el valor al campo apellidoPaterno.
	 * @param apellidoPaterno el valor apellidoPaterno a asignar
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * Método de acceso al campo apellidoMaterno.
	 * @return El valor del campo apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * Asigna el valor al campo apellidoMaterno.
	 * @param apellidoMaterno el valor apellidoMaterno a asignar
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * Método de acceso al campo edad.
	 * @return El valor del campo edad
	 */
	public Short getEdad() {
		return edad;
	}
	/**
	 * Asigna el valor al campo edad.
	 * @param edad el valor edad a asignar
	 */
	public void setEdad(Short edad) {
		this.edad = edad;
	}
	/**
	 * Método de acceso al campo escolaridad.
	 * @return El valor del campo escolaridad
	 */
	public ValorDTO getEscolaridad() {
		return escolaridad;
	}
	/**
	 * Asigna el valor al campo escolaridad.
	 * @param escolaridad el valor escolaridad a asignar
	 */
	public void setEscolaridad(ValorDTO escolaridad) {
		this.escolaridad = escolaridad;
	}
	/**
	 * Método de acceso al campo estadoCivil.
	 * @return El valor del campo estadoCivil
	 */
	public ValorDTO getEstadoCivil() {
		return estadoCivil;
	}
	/**
	 * Asigna el valor al campo estadoCivil.
	 * @param estadoCivil el valor estadoCivil a asignar
	 */
	public void setEstadoCivil(ValorDTO estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	/**
	 * Método de acceso al campo ocupacion.
	 * @return El valor del campo ocupacion
	 */
	public ValorDTO getOcupacion() {
		return ocupacion;
	}
	/**
	 * Asigna el valor al campo ocupacion.
	 * @param ocupacion el valor ocupacion a asignar
	 */
	public void setOcupacion(ValorDTO ocupacion) {
		this.ocupacion = ocupacion;
	}
	/**
	 * Método de acceso al campo entrevistaComplementaria.
	 * @return El valor del campo entrevistaComplementaria
	 */
	public EntrevistaComplementariaDTO getEntrevistaComplementaria() {
		return entrevistaComplementaria;
	}
	/**
	 * Asigna el valor al campo entrevistaComplementaria.
	 * @param entrevistaComplementaria el valor entrevistaComplementaria a asignar
	 */
	public void setEntrevistaComplementaria(
			EntrevistaComplementariaDTO entrevistaComplementaria) {
		this.entrevistaComplementaria = entrevistaComplementaria;
	}
	/**
	 * Método de acceso al campo catRelacionDTO.
	 * @return El valor del campo catRelacionDTO
	 */
	/**
	 * Método de acceso al campo relacion.
	 * @return El valor del campo relacion
	 */
	public CatRelacionDTO getRelacion() {
		return relacion;
	}
	/**
	 * Asigna el valor al campo relacion.
	 * @param relacion el valor relacion a asignar
	 */
	public void setRelacion(CatRelacionDTO relacion) {
		this.relacion = relacion;
	}
	
	
}
