/**
* Nombre del Programa : GraficacionDelegateImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos para el acceso a los servicios de Graficacion
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
package mx.gob.segob.nsjp.delegate.graficacion.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.enums.medida.TipoMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.graficacion.GraficacionDelegate;
import mx.gob.segob.nsjp.service.graficacion.GraficaCasosRemitidosService;
import mx.gob.segob.nsjp.service.graficacion.GraficaSeguimientoMedidasAlternasService;
import mx.gob.segob.nsjp.service.graficacion.GraficaSeguimientoMedidasCautelaresService;

/**
 * Implementacion de metodos para el acceso a los servicios de Graficacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service("graficacionDelegate")
public class GraficacionDelegateImpl implements GraficacionDelegate {

	@Autowired
	private GraficaCasosRemitidosService graficaCasosRemitidosService;
	@Autowired
	private GraficaSeguimientoMedidasCautelaresService graficaSeguimientoMedidasCautelaresService;
	@Autowired
	private GraficaSeguimientoMedidasAlternasService graficaSeguimientoMedidasAlternasService;
	
	@Override
	public List<Object[]> obtenerCasosRemitidos(Date fechaInicio, Date fechaFin)
			throws NSJPNegocioException {		
		return graficaCasosRemitidosService.obtenerCasosRemitidosPorMes(fechaInicio, fechaFin);
	}

	@Override
	public Long obtenerMedCauPorFechasYTipoNedida(Date fechaInicio,
			Date fechaFin, TipoMedida tipoMedida)
			throws NSJPNegocioException {		
		return graficaSeguimientoMedidasCautelaresService.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, tipoMedida);
	}
	
	@Override
	public Long obtenerMedAltPorFechasYTipoNedida(Date fechaInicio,
			Date fechaFin, TipoMedida tipoMedida)
			throws NSJPNegocioException {		
		return graficaSeguimientoMedidasAlternasService.obtenerMedAltPorFechasYTipoNedida(fechaInicio, fechaFin, tipoMedida);
	}

}
