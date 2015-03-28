/**
* Nombre del Programa : GrupoObjetosDTO.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Objeto de tranferencia de datos con el resumen de un grupo de objetos
* asociados al expediente, permite enviar información resumida y concisa a la capa de vista
* sin tener que enviar la totalidad de los datos de un objeto, 
* esta clase se utiliza para colocar información de resumen en el árbol
* de elementos al editar un documento.
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
package mx.gob.segob.nsjp.dto.documento;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Objeto de tranferencia de datos con el resumen de un grupo de objetos
 * asociados al expediente, permite enviar información resumida y concisa a la capa de vista
 * sin tener que enviar la totalidad de los datos de un objeto, 
 * esta clase se utiliza para colocar información de resumen en el árbol
 * de elementos al editar un documento.
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class GrupoObjetosExpedienteDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1897101695536187080L;
	private Objetos tipoObjeto;
	private String descripcionGrupo;
	private List<ObjetoResumenDTO> objetos;
	/**
	 * Método de acceso al campo tipoObjeto.
	 * @return El valor del campo tipoObjeto
	 */
	public Objetos getTipoObjeto() {
		return tipoObjeto;
	}
	/**
	 * Asigna el valor al campo tipoObjeto.
	 * @param tipoObjeto el valor tipoObjeto a asignar
	 */
	public void setTipoObjeto(Objetos tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}
	/**
	 * Método de acceso al campo descripcionGrupo.
	 * @return El valor del campo descripcionGrupo
	 */
	public String getDescripcionGrupo() {
		return descripcionGrupo;
	}
	/**
	 * Asigna el valor al campo descripcionGrupo.
	 * @param descripcionGrupo el valor descripcionGrupo a asignar
	 */
	public void setDescripcionGrupo(String descripcionGrupo) {
		this.descripcionGrupo = descripcionGrupo;
	}
	/**
	 * Método de acceso al campo objetos.
	 * @return El valor del campo objetos
	 */
	public List<ObjetoResumenDTO> getObjetos() {
		return objetos;
	}
	/**
	 * Asigna el valor al campo objetos.
	 * @param objetos el valor objetos a asignar
	 */
	public void setObjetos(List<ObjetoResumenDTO> objetos) {
		this.objetos = objetos;
	}
	
}
