/**
 * Nombre del Programa : ConsultarPeritoPorNombreService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 27-jun-2011
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
package mx.gob.segob.nsjp.service.funcionario;

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarPeritoPorNombreService {

    /**
     * Devuelve un listado de PERITOS que coinciden con el nombre, apellido
     * paterno y apellido materno que se le pasan dentro del objeto
     * @{code criterios}
     * @return Una lista que contiene los peritos que coinciden con el nombre
     * del perito que se le pasa como parametro o la lista vacia en caso de
     * no encontrar coincidencias.
     * @throws NSJPNegocioException
     */
    public List<FuncionarioDTO> consultarPeritosPorNombre(FuncionarioDTO criterios)
            throws NSJPNegocioException;

}
