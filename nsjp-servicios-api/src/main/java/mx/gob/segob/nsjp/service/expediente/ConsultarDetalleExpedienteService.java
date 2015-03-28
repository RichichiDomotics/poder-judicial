    /**
 * Nombre del Programa : ConsultarDetalleExpedienteService.java
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
package mx.gob.segob.nsjp.service.expediente;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarDetalleExpedienteService {

    /**
     * Consulta la siguiente informacion de un expediente, dado un expediente y
     * un usuario:
     * <ol>
     * <li> Numero de caso
     * <li> Numero de causa, que en Poder Judicial se cumple que el "numero de
     * causa" == "Numero de expediente"
     * <li> Etapa del expediente
     * <li> Nombre del imputado. Esta informacion se encuentra dentro de
     * ExpedienteDTO.involucrados[0] del expediente consultado.
     * <li> Delitos del imputado. Esta infomacion se encuentra dentro de
     * ExpedienteDTO.delitos
     * <li> Segun el CU "Enviar aviso de designación" se deberia consultar la
     * informacion "del individuo para el que se solicita el defensor" pero esta
     * informacion es la misma que el "Nombre del imputado"
     * </ol>
     * @param expedienteDto El expediente del cual se obtendra su detalle.
     * @param usuarioDto El usuario (funcionario) asociado al expediente
     * @return El detalle del expediente con la informacion requerida.
     * @throws NSJPNegocioException En caso que alguna de las siguientes
     * condiciones se cumpla:
     * <ol>
     * <li> {@code expedienteDto == null}
     * <li> {@code expedienteDto.ExpedienteId == null}
     * <li> {@code usuarioDto == null}
     * </ol>
     */
    ExpedienteDTO consultarDetalleExpediente(ExpedienteDTO expedienteDto,
            UsuarioDTO usuarioDto) throws NSJPNegocioException;

    /**
	 * Servicio que consulta en que area se ha iniciado un expediente
	 * @param expediente: expedienteId
	 * @return
	 * @throws NSJPNegocioException
	 */
	JerarquiaOrganizacionalDTO consultarOrigendeExpediente(
			ExpedienteDTO expediente)throws NSJPNegocioException;
}
