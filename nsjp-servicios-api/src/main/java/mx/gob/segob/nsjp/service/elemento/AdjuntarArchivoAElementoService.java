package mx.gob.segob.nsjp.service.elemento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;

public interface AdjuntarArchivoAElementoService {

	
	public void adjuntarArchivoAElemento(ElementoDTO elemento,
			ArchivoDigitalDTO adjunto) throws NSJPNegocioException;
}
