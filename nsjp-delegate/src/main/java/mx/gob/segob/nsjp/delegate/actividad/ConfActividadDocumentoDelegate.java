/**
* Nombre del Programa : ConfActividadDocumentoDelegate.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17/08/2011
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
package mx.gob.segob.nsjp.delegate.actividad;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.ConfActividadDocumentoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ConfActividadDocumentoDelegate {
	
    /**
     * Consulta las configuraciones asociadas a un usuario, el area a la que
     * pertenec, el estado del numero de expediente y, opcionalmente, la 
     * categoría a la que pertenece dicha actividad,  
     * que se le pasa como parametro.<br/>
     * Los campos requeridos son: {@code usuario.IdUsuario} y {@code
     * expediente.numeroExpediente}, opcionalmente el usuario deberia
     * venir asociado con su funcionario pero si esto no ocurre el sistema
     * lo consulta automaticamente.
     * @param usuarioDto El usuario de donde se obtendra el funcionario para
     * buscar el area de trabajo.
     * @param expedienteDto El numero de expediente del cual buscamos su
     * estatus.
     * @param idCategoriaActividad El identificador de la Categoría de Actividada 
     * con la cual se hace un filtro en la busqueda.
     * @return Los registros de la base que contienen los tipo de actividades
     * y tipos de documentos configurados en el sistema para el usuario y
     * numero de expediente dados. Regresa la lista vacia en caso de no
     * existir registros en la base asociados a los parametros de consulta.
     * @throws NSJPNegocioException Si se cumple alguna de las siguientes
     * condiciones:
     * <ol>
     * <li> {@code usuarioDto == null}
     * <li> {@code usuarioDto.getIdUsuario() == null}
     * <li> {@code expedienteDto == null}
     * <li> {@code expedienteDto.getNumeroExpediente() == null}
     * <li> Si la cadena pasada como numeroExpediente no existe en la base de
     * datos.
     * </ol>
     */
    List<ConfActividadDocumentoDTO> consultarConfActividadDocumento(
            UsuarioDTO usuario, ExpedienteDTO expedienteDto, Long idCategoriaActividad )
            throws NSJPNegocioException;
    
    /**
     * Consulta la Configuracion del la Actividad de Documento de acuerdo
     * al id
     * 
     * @param idConfActividadDocumento
     * @return
     */
    ConfActividadDocumentoDTO consultaConfActividadDocumentoPorId(Long idConfActividadDocumento) throws NSJPNegocioException;

    /**
     * Consulta los estatus asociados a una Jerarquia Organizacional dentro
     * de la tabla de Configuracion de Actividades
     * 
     * @param idJerarquiaOrganizacional
     * @return
     * @throws NSJPNegocioException
     */
	List<ValorDTO> consultarEstadosPorJerarquiaOrganizacional(Long idJerarquiaOrganizacional) throws NSJPNegocioException;
    
}
