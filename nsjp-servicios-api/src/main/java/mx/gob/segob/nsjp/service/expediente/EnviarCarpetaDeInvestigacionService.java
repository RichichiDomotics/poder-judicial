/**
 * Nombre del Programa : EnviarCarpetaDeInvestigacionService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-jul-2011
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
package mx.gob.segob.nsjp.service.expediente;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteWSDTO;

/**
 * Esta es la interfaz que se publica como Webservice
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface EnviarCarpetaDeInvestigacionService {

    Long enviarCarpetaDeInvestigacion(ExpedienteWSDTO expedienteWSDTO,
            String numeroGeneralCaso, String folioSolicitud) throws NSJPNegocioException;
    
   
}
