/**
 * Nombre del Programa : AcuseReciboDelegate.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 22 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato de metodos para operar los servicios relativos a un Acuse de recibo
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
package mx.gob.segob.nsjp.delegate.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;


/**
 * Contrato de metodos para operar los servicios relativos a un Acuse de Recibo
 * @author rgama
 *
 */
public interface AcuseReciboDelegate {
	
	/**
	 * Metodo que permite Generar un numero de acuse de atencion, ademas de permite
	 * asociar una instituacion al acuse
	 * @param idInstitucion
	 * @return numero de folio asignado al acuse registrado
	 * @throws NSJPNegocioException
	 */
	public Long generarNumeroDeAcuse(Long idInstitucion, Long idSolicitud) throws NSJPNegocioException;
	
}
