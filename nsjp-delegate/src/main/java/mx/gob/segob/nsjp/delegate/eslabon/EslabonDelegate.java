/**
 * 
 */
package mx.gob.segob.nsjp.delegate.eslabon;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;

/**
 * @author adrian
 *
 */
public interface EslabonDelegate {

	/**
	 * Operación que realiza la funcionalidad de guardar(actualizar) el eslabón con la asociación a la evidencia que le corresponde
	 * @param evidenciaDTO: idEvidencia
	 * @param eslabonDTO: Objeto
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long guardarEslabon(EvidenciaDTO evidenciaDTO,EslabonDTO eslabonDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que permite asociar un documento al eslabón
	 * @param eslabonDTO: idEslabon y idDocumento
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long asociarDocumentoAEslabon(EslabonDTO eslabonDTO)throws NSJPNegocioException;
	
	/**
	 * Consulta los eslabones asociados a una evidencia
	 * 
	 * @param evidenciaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<EslabonDTO> consultarEslabonesPorEvidencia(EvidenciaDTO evidenciaDTO) throws NSJPNegocioException;
	
	
	/**
	 * Servicio que consulta si un objeto, que sea evidencia y, que esté, o no, en un eslabon.
	 * 	
	 * @param idObjeto
	 * @return
	 * @throws NSJPNegocioException
	 */
	Boolean tieneEslabonPorEvidenciaYObjeto(Long idObjeto)  throws NSJPNegocioException;
	
	/**
	 * Servicio que consulta si un objeto, activo, es una evidencia y no tiene un eslabon asociado.
	 * 
	 * @param idObjeto
	 * @return
	 * @throws NSJPNegocioException
	 */
	Boolean esEvidenciaNoTieneEslabon(Long idObjeto)  throws NSJPNegocioException;
	
}
