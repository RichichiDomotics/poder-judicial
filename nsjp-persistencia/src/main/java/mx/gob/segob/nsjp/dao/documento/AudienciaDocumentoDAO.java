/**
 * 
 */
package mx.gob.segob.nsjp.dao.documento;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.AudienciaDocumento;
import mx.gob.segob.nsjp.model.AudienciaDocumentoId;
import mx.gob.segob.nsjp.model.Documento;

/**
 * @author AlejandroGA
 *
 */
public interface AudienciaDocumentoDAO extends GenericDao<AudienciaDocumento, AudienciaDocumentoId> {
	
	/**
	 * @param audienciaId
	 * @param tipoforma
	 * @return
	 */
	public List<Documento> consultarDocumentosAudiencia(Long audienciaId)throws NSJPNegocioException;

}
