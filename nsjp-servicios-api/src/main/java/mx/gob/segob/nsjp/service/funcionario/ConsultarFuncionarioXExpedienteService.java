/**
 * Nombre del Programa : ConsultarFuncionarioXExpedienteService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-jun-2011
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

// import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarFuncionarioXExpedienteService {

    // Es importante que los metodos lancen excepcion
    // void todosLosMetodosDebenLanzarExcepcion() throws NSJPNegocioException;
    public FuncionarioDTO consultarFuncionarioXExpediente(String expediente) throws NSJPNegocioException;
}
