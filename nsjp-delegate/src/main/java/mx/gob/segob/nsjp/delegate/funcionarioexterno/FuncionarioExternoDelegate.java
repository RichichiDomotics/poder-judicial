/**
* Nombre del Programa 		: FuncionarioExternoDelegate.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 								Fecha: 13 Apr 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Describir el objetivo de la clase de manera breve
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 								Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 								Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.delegate.funcionarioexterno;

import mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public interface FuncionarioExternoDelegate {
	
	/**
	 * M&eacute;todo que lleva a cabo la persistencia de un funcionario externo dentro
	 * de la base de datos.
	 * @param funcionarioExternoDTO - el funcionario externo a persistir.
	 * @return funcionarioExternoDTO - el mismo funcionario externo con su id asociado.
	 */
	public FuncionarioExternoDTO crearFuncionarioExterno(FuncionarioExternoDTO funcionarioExternoDTO);
	
	/**
	 * M&eacute;todo encargado de llevar a cabo la actualizaci&oacute;n de un funcionario
	 * externo dentro de la base de datos.
	 * @param funcionarioExternoDTO - el funcionario externo a actualizar.
	 */
	public void actualizarFuncionarioExterno(FuncionarioExternoDTO funcionarioExternoDTO);
	
	/**
	 * M&eacute;todo encargado de llevar a cabo la baja f&iacute;sica del
	 * funcionario externo persistido en la base de datos.
	 * @param funcionarioExternoDTO - El funcionario externo a eliminar.
	 */
	public void eliminarFuncionarioExterno(FuncionarioExternoDTO funcionarioExternoDTO);
	
	/**
	 * M&eacute;todo que lleva a cabo la consulta de un funcionario externo a trav&eacute;s de
	 * su identificador asociado.
	 * @param funcionarioExternoDTO - DTO que incluye el identificador del funcionario externo a 
	 * 								  consultar.
	 * @return funcionarioExternoDTO - DTO con todos los datos asociados del funcionario externo.
	 */
	public FuncionarioExternoDTO consultarFuncionarioExternoPorId(FuncionarioExternoDTO funcionarioExternoDTO);

}
