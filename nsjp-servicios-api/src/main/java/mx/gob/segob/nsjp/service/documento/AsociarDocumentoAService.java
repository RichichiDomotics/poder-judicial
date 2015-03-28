package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
 
public interface AsociarDocumentoAService {

	public void asociarDocuementoA(ResolutivoDTO resolutivo, DocumentoDTO documento) throws NSJPNegocioException;
	
	/**
	 * Servicio que permite relacionar un documento a una audiencia en la tabla de cruce
	 * @param audienciaDTO
	 * @param documentoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public DocumentoDTO asociarDocumentoAAudiencia(AudienciaDTO audienciaDTO,
			DocumentoDTO documentoDTO) throws NSJPNegocioException;
	
}
