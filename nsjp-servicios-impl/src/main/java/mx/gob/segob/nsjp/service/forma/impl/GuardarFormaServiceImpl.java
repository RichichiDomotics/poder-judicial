/**
* Nombre del Programa : GuardarFormaServiceImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/07/2011
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
package mx.gob.segob.nsjp.service.forma.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.service.forma.GuardarFormaService;
import mx.gob.segob.nsjp.service.forma.impl.transform.FormaTransformer;

/**
 * Implementación del servicio de negocio para el almacenamiento de una forma
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
@Service
@Transactional
public class GuardarFormaServiceImpl implements GuardarFormaService {
	@Autowired
	FormaDAO formaDAO;
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.forma.GuardarFormaService#guardarForma(mx.gob.segob.nsjp.dto.forma.FormaDTO)
	 */
	@Override
	public Long guardarForma(FormaDTO forma) throws NSJPNegocioException {

		Forma formaBD =  FormaTransformer.transformarFormaDTO(forma);
		
		if(forma.getFormaId() == null && formaDAO.consultarFormaPorNombre(forma.getNombre())!=null){
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		}
		
		formaDAO.saveOrUpdate(formaBD);
		
		return formaBD.getFormaId();
	}

}
