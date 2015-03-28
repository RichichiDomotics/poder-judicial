/**
* Nombre del Programa : GraficaSeguimientoMedidasCautelaresServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para obtener la informacion requerida para la Grafica
* 							Seguimiento a medidas cautelares
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
package mx.gob.segob.nsjp.service.graficacion.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.medida.TipoMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.MedidaCautelarDAO;
import mx.gob.segob.nsjp.service.graficacion.GraficaSeguimientoMedidasCautelaresService;

/**
 * Implementacion del servicio para obtener la informacion requerida para la Grafica
 * Seguimiento a medidas cautelares.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class GraficaSeguimientoMedidasCautelaresServiceImpl implements
		GraficaSeguimientoMedidasCautelaresService {

	@Autowired
	private MedidaCautelarDAO medidaCautelarDAO;
	
	@Override
	public Long obtenerMedCauPorFechasYTipoNedida(Date fechaInicio,
			Date fechaFin, TipoMedida tipoMedida)
			throws NSJPNegocioException {
		
		Long numMedidasCautelares = medidaCautelarDAO.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, tipoMedida.getValorId());
		
		return numMedidasCautelares;
	}

}
