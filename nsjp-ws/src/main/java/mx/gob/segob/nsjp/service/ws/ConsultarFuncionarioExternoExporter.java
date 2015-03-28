/**
* Nombre del Programa 		: ConsultarFuncionarioExternoExporter.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 							Fecha: 11 Apr 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Interfaz que expone el web service de consulta de 
* 							  funcionarios externos por criterio.
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 							Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.ws;

import javax.jws.WebService;

import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioExternoService;

/**
 * Interfaz que expone el web service de consulta de funcionarios externos por criterio.
 * @version 1.0
 * @author EdgarTE
 *
 */
@WebService
public interface ConsultarFuncionarioExternoExporter extends
		ConsultarFuncionarioExternoService {

}
