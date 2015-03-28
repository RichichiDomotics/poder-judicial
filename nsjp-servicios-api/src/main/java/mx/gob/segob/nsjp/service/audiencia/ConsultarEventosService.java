/**
* Nombre del Programa : ConsultarEventos.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para consultar los eventos
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
package mx.gob.segob.nsjp.service.audiencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.EventoDTO;

/**
 * Contrato para consultar los eventos.
 * @version 1.0
 * @author vaguirre
 *
 */
public interface ConsultarEventosService {
    /**
     * 
     * @param filtro
     * @return
     * @throws NSJPNegocioException
     */
    List<EventoDTO> consultarEventos(EventoDTO filtro) throws NSJPNegocioException;
    
    /**
     * Obtiene un evento.
     * 
     * @param input
     *            requerido: <b>id</b>.
     * @return
     * @throws NSJPNegocioException
     */
    EventoDTO obtenerEvento(EventoDTO input) throws NSJPNegocioException;
    
}
