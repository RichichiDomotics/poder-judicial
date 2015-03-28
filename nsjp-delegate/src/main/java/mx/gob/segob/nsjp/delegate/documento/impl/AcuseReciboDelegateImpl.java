/**
 * Nombre del Programa : AcuseReciboDelegateImpl.java
 * Autor                            : Emigdio Hernández
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 22/Jun/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación del Delegate para manipulación de Acuses de recibo
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
package mx.gob.segob.nsjp.delegate.documento.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.documento.AcuseReciboDelegate;
import mx.gob.segob.nsjp.service.documento.GenerarNumeroDeAcuseDeAtencionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del Delegate para manipulación de Acuses de recibo
 * @author rgama
 *
 */
@Service("acuseReciboDelegate")
public class AcuseReciboDelegateImpl implements AcuseReciboDelegate {

	@Autowired
    private GenerarNumeroDeAcuseDeAtencionService generarNumeroDeAcuseDeAtencionService;

	/**
	 * Metodo que permite Generar un numero de acuse de atencion, ademas de permite
	 * asociar una instituacion al acuse
	 * @param idInstitucion
	 * @return numero de folio asignado al acuse registrado
	 * @throws NSJPNegocioException
	 */
	public Long generarNumeroDeAcuse(Long idInstitucion,Long idSolicitud) throws NSJPNegocioException{
		return generarNumeroDeAcuseDeAtencionService.generarNumeroDeAcuse(idInstitucion,idSolicitud);
	}
	
}
