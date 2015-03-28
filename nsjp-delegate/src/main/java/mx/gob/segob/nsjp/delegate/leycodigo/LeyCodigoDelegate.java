/**
 * Nombre del Programa  : LeyCodigoDelegate.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Repositorio de servicios para LeyCodigo
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
package mx.gob.segob.nsjp.delegate.leycodigo;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.LeyCodigoDTO;

public interface LeyCodigoDelegate {
	
	public List<LeyCodigoDTO> obtenerNormasCategoria(Long id) throws NSJPNegocioException;
	
	public ByteArrayOutputStream leerLeyCodigo(Long id) throws NSJPNegocioException;
}
