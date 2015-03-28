/**
 * Nombre del Programa : CentroDetencionDelegate.java
 * Autor                            : Cuauhtemoc Paredes Serrano
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-Sept-2011
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
package mx.gob.segob.nsjp.delegate.detencion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;

/**
 * @author CuauhtemocPS
 *
 */
public interface CentroDetencionDelegate {

	/**
	 * Consulta el centro de detencion dependiendo el Id
	 * @param centroDetencionId
	 * @return
	 * @throws NSJPNegocioException
	 */
	public CentroDetencionDTO consultarCentroDetencion(Long centroDetencionId)
			throws NSJPNegocioException;
	
	/**
	 * ConsultaCentro detencion dependiendo el tipo de centro
	 * @param tipoCentroDetencion
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<CentroDetencionDTO> consultarCentrosDetencionPorTipo(Long tipoCentroDetencion)
			throws NSJPNegocioException;		
	

}
