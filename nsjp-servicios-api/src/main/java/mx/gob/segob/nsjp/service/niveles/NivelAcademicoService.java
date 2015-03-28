/**
* Nombre del Programa : ProgramaService.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/01/2012
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
package mx.gob.segob.nsjp.service.niveles;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.niveles.CatTipoNivelAcademicoDTO;
import mx.gob.segob.nsjp.dto.niveles.NivelAcademicoDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public interface NivelAcademicoService {

	/**
	 * M&eacute;todo que consulta todos los niveles acad&eacute;micos
	 * @return Lista de NivelAcademicoDTO
	 * @throws NSJPNegocioException
	 */
	public List<NivelAcademicoDTO> consultarNivelesAcademicos()throws NSJPNegocioException;

	/**
	 * M&eacute;todo que consulta todos los tipos de nivel acad&eacute;mico
	 * @return Lista de CatTipoNivelAcademicoDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatTipoNivelAcademicoDTO> consultarCatTipoNivelAcademico()throws NSJPNegocioException;
		
}
