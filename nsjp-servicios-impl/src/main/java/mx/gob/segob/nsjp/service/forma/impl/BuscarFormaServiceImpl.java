/**
* Nombre del Programa : BuscarFormaServiceImpl.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/05/2011
* Marca de cambio        : N/A
* Descripcion General    : Implementación del servicio de negocio para la busqueda de formas de impresión de documentos
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
package mx.gob.segob.nsjp.service.forma.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.service.forma.BuscarFormaService;
import mx.gob.segob.nsjp.service.forma.impl.transform.FormaTransformer;

/**
 * Implementación del servicio de negocio para la busqueda de formas de impresión de documentos
 * @author Emigdio Hernández
 *
 */
@Service
@Transactional(readOnly=true)
public class BuscarFormaServiceImpl implements BuscarFormaService {

	@Autowired
	FormaDAO formaDAO;
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.forma.BuscarFormaService#buscarForma(java.lang.Long)
	 */
	@Override
	public FormaDTO buscarForma(Long formaId) throws NSJPNegocioException {
	
		return FormaTransformer.transformarForma(formaDAO.read(formaId));
	
	}

}
