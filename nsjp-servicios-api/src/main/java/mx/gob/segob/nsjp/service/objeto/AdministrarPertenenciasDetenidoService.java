/**
 * Nombre del Programa  : AdministrarPertenenciasDetenidoService.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 15 Jul 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Permite realizar consultas, inserciones y eliminaciones de objetos que pertenecen a un detenido.
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */

package mx.gob.segob.nsjp.service.objeto;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;

/**
 * Permite realizar consultas, inserciones y eliminaciones de objetos que pertenecen a un detenido..
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
public interface AdministrarPertenenciasDetenidoService {


	/**
	 * Consulta los objetos que pertenencen al <code>detenido</code>
	 * @param detenido Involucrado a quien pertenecen los objetos consultados
	 * @return Lista de objetos que pertenecen al detenido
	 * @throws NSJPNegocioException
	 */
	public List<ObjetoDTO> consultarInventarioPertenenciasDetenido(InvolucradoDTO detenido) throws NSJPNegocioException;
	
	/**
	 * Regrista un objeto en la base y lo asocioa al detenido a traves de la relacion pertenece
	 * @param objeto que pertenece al <code>detendio</code> y será agregado
	 * @param detenido dueño del <code>objeto</code>
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long registrarPertenenciaDetenido(ObjetoDTO objeto, InvolucradoDTO detenido) throws NSJPNegocioException;
	
	/**
	 * Elimina la relación de un objeto con un involucrado y posteriormente elimina el objeto.
	 * @param objeto se eliminaroa del sistea
	 * @param detenido para eliminar la relacicíon con el objeto
	 * @throws NSJPNegocioException
	 */
	public void eliminarPertenenciaDeteneido(ObjetoDTO objeto, InvolucradoDTO detenido) throws NSJPNegocioException;
	
	
}
