/**
* Nombre del Programa : MedidasDelegateImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 03 Nov 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion para delegate de Medidas
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
package mx.gob.segob.nsjp.delegate.medidas.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.medidas.MedidasDelegate;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.service.documento.GenerarDocumentoDeMedidaIncumplidaManualmenteService;
import mx.gob.segob.nsjp.service.medida.CambiarEstatusAMedidasConFechaFinVencidaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion para delegate de Medidas.
 * @version 1.0
 * @author rgama
 *
 */
@Service("medidasDelegate")
public class MedidasDelegateImpl implements MedidasDelegate{

	@Autowired
	private CambiarEstatusAMedidasConFechaFinVencidaService cambiarEstatusAMedidasConFechaFinVencidaService;
	
	@Autowired
	private GenerarDocumentoDeMedidaIncumplidaManualmenteService medidaIncumplidaManualmenteService;

	@Override
	public List<MedidaDTO> cambiarEstatusAMedidasConFechaFinVencidaService()
			throws NSJPNegocioException {
		return cambiarEstatusAMedidasConFechaFinVencidaService.cambiarEstatusAMedidasConFechaFinVencidaService();
	}

	@Override
	public DocumentoDTO generarDocumentoDeMedidaIncumplida(Long idMedida)
			throws NSJPNegocioException {
		return medidaIncumplidaManualmenteService.generarDocumentoDeMedidaIncumplida(idMedida);
	}
	
	
}
