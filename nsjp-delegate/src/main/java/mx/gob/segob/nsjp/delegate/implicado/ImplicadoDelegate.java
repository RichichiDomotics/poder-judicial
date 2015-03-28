/**
 * Nombre del Programa : ImplicadoDelegate.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 31 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Delegate para las acciones con el turno
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
package mx.gob.segob.nsjp.delegate.implicado;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.ImplicadoDTO;

/**
 * Delegate para las acciones con el implicado.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public interface ImplicadoDelegate {
   
    /**
	 * Operación que permite consultar un implicado por id
	 * @param implicadoId (Obligatorio)
	 * @return ImplicadoDTO
	 * @throws NSJPNegocioException
	 */
    ImplicadoDTO consultarMediosDeContactoImplicadoXId(ImplicadoDTO aoImplicado)throws NSJPNegocioException;

    
}
