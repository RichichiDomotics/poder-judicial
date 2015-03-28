/**
* Nombre del Programa : ConsultarDocumentosDictamenPorTipoService.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/07/2011
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
package mx.gob.segob.nsjp.service.documento;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Interfaz del contrato de negocio para las distintas formas
 * de consultar los dictamenes provenientes de servicios periciales
 * ya sean dictámenes o informes
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface ConsultarDocumentosDictamenPorTipoService {

	/**
	 * Consulta los documentos de servicios periciales que ha recibido
	 * un funcionario
	 * @param funcionarioQueRecibe Funcionario que realiza la consulta a la pantalla
	 * @param tipoDocumento Tipo de documento que se desea obtener, generalmente dictamen o informe
	 * @return Lista de documentos recibidos para el funcionario
	 */
	List<DictamenDTO> consultarDocumentosRecibidosAMP(Formas tipoDocumento,FuncionarioDTO funcionarioQueRecibe) throws NSJPNegocioException;
	/**
	 * Consulta el detalle de un dictamen en base a su identificador
	 * @param dictamenId Dictamen ID
	 * @return Dictamen encontrado
	 */
	DictamenDTO consultarDetalleDictamenPorId(Long dictamenId);
	
}
