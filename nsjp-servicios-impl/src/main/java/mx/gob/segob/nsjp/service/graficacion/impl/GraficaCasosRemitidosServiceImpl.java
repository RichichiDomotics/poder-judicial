/**
* Nombre del Programa : GraficaCasosRemitidosServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para obtener informacion requerida para grafica casos remitidos
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

import mx.gob.segob.nsjp.comun.enums.comun.Meses;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.informepolicial.InvolucradoIPHDAO;
import mx.gob.segob.nsjp.service.graficacion.GraficaCasosRemitidosService;

/**
 * Implementacion del servicio para obtener informacion requerida para grafica casos remitidos.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class GraficaCasosRemitidosServiceImpl implements
		GraficaCasosRemitidosService {

	@Autowired
	private InvolucradoIPHDAO involucradoIPHDAO;
	
	@Override
	public List<Object[]> obtenerCasosRemitidosPorMes(Date fechaInicio,
			Date fechaFin) throws NSJPNegocioException {

		List<Object[]> casosRemitidos = involucradoIPHDAO.obtenerCasosRemitidosPorMes(fechaInicio, fechaFin);
		
		for (Object[] objects : casosRemitidos) {
			objects[0]=Meses.getNombre((Integer)objects[0]);
		}
		
		return casosRemitidos;
	}

}
