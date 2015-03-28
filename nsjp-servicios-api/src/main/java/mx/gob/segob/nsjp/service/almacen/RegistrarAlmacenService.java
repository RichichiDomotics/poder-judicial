/**
 * Nombre del Programa : RegistrarAlmacenService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 01-ago-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.almacen;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface RegistrarAlmacenService {

    Long registrarAlmacen(AlmacenDTO almacenDto) throws NSJPNegocioException;

    /**
     * Realiza la asociacion de el expediente a un almacen.
     * @author cesarAgustin
     * @param almacenDTO <li>identificadorAlmacen<li> Identificador del almacen al que se asociara el expediente
     * @param expedienteDTO Identificador del expediente ha asociar
     * @return El almacen al cual se asocio el expediente
     * @throws NSJPNegocioException
     */
	AlmacenDTO asociarExpedienteAlmacen(AlmacenDTO almacenDTO,
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
}
