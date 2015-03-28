/**
* Nombre del Programa : TurnoDelegateImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementación del Delegate para las acciones con el Implicado
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
package mx.gob.segob.nsjp.delegate.implicado.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.implicado.ImplicadoDelegate;
import mx.gob.segob.nsjp.dto.involucrado.ImplicadoDTO;
import mx.gob.segob.nsjp.service.implicado.ConsultarImplicadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del Delegate para las acciones con el Implicado.
 * @version 1.0
 * @author rgama
 *
 */
@Service
public class ImplicadoDelegateImpl implements ImplicadoDelegate {
    @Autowired
    private ConsultarImplicadoService consultarImplicadoService;

	@Override
	public ImplicadoDTO consultarMediosDeContactoImplicadoXId(ImplicadoDTO aoImplicado)
			throws NSJPNegocioException {
		return consultarImplicadoService.consultarImplicadoXId(aoImplicado);
	}
    
    
}

