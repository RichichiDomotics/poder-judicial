/**
 * 
 */
package mx.gob.segob.nsjp.dao.documento.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.documento.AudienciaDocumentoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.AudienciaDocumento;
import mx.gob.segob.nsjp.model.AudienciaDocumentoId;
import mx.gob.segob.nsjp.model.Documento;

/**
 * @author AlejandroGA
 *
 */

@Repository
public class AudienciaDocumentoDAOImpl  extends GenericDaoHibernateImpl<AudienciaDocumento, AudienciaDocumentoId>
implements AudienciaDocumentoDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Documento> consultarDocumentosAudiencia(Long audienciaId)
			throws NSJPNegocioException {
		
		 StringBuffer queryString = new StringBuffer();
	        queryString.append("SELECT docAud.documento FROM AudienciaDocumento docAud WHERE docAud.audiencia.audienciaId = ").append(audienciaId);
	        
	        final PaginacionDTO pag = PaginacionThreadHolder.get();
	        logger.debug("pag :: " + pag);
	        return super.ejecutarQueryPaginado(queryString, pag);

	}
}
