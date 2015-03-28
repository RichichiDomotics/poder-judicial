/**
 * Nombre del Programa : ConsultarEvidenciaPorAlmacenService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 29-jul-2011
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

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarEvidenciaPorAlmacenService {

    /**
     *
     * @param almacenDto
     * @return
     * @throws NSJPNegocioException
     */
    public List<EvidenciaDTO> consultarEvidenciaPorAlmacen(AlmacenDTO almacenDto)
            throws NSJPNegocioException;
    
    /**
	 * Operación que permite consultar las evidencias por almacen, opcionalmente por estatus y opcionalmente por caso
	 * @param almacenDTO: idAlmacen
	 * @param estatusEv: null=sin importar estatus; -1=Solicitudes de AMP; valor=por estatus  
	 * @param casoDTO: idCaso
	 * @param idAmacen: idAmacen Parametro que permite consultar las evidencias asociadas al almacen, en caso de ser nulo 
	 * 					se usa el almacen al cuala esta asociado el usuario
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<EvidenciaDTO> consultarEvidenciasXAlmacenXEstatus(UsuarioDTO usuarioDTO,
			Long estatusEv, CasoDTO casoDTO, Long idAmacen)throws NSJPNegocioException;
}
