/**
 * Nombre del Programa : ConsultarDetencionService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 05-jul-2011
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
package mx.gob.segob.nsjp.service.detencion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarDetencionService {

    /**
     * Consulta la detencion vigente para un involucrado.
     * Dado que en el caso de uso se pasa el expediente como cadena aqui
     * se respeta la firma, sin embargo esta no es requerida y puede usarse null.
     * @param idInvolucrado El id del involucrado del que se consultara la
     * detencion vigente, es decir aquella que no tenga asignada una fecha de
     * fin.
     * @param expediente Cadena que puede o no tener informacion ya que no se
     * utiliza en el servicio.
     * @return La detencion vigente para el involucrado o {@code null} es caso
     * que no exista una detencion vigente.
     * @throws NSJPNegocioException En caso que {@code idInvolucrado} sea nulo.
     */
    DetencionDTO consultarDetencion(Long idInvolucrado, String expediente)
            throws NSJPNegocioException;
}
