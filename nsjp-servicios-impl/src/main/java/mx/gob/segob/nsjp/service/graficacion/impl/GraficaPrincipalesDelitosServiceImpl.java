/**
* Nombre del Programa : GraficaPrincipalesDelitosServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para obtener la informacion requerida para la Grafica
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.comun.Meses;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.service.graficacion.GraficaPrincipalesDelitosService;

/**
 * Implementacion del servicio para obtener la informacion requerida para la Grafica.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class GraficaPrincipalesDelitosServiceImpl implements
		GraficaPrincipalesDelitosService {
	
	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(GraficaPrincipalesDelitosServiceImpl.class);
	
	@Autowired
	private DelitoDAO delitoDAO;
	
	@Override
	public List<Object[]> obtenerDelitoPorMes(Date fechaInicio, Date fechaFin,
			Long catDelito) throws NSJPNegocioException {
		
		List<Object[]> delitosMes = delitoDAO.obtenerDelitoPorMes(fechaInicio, fechaFin, catDelito);
		
		for (Object[] objects : delitosMes) {
			objects[0]=Meses.getNombre((Integer)objects[0]-1);
		}
		
		return delitosMes;
	}

}
