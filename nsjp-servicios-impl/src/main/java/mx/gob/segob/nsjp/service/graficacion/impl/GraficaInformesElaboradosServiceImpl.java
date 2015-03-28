/**
* Nombre del Programa : GraficaInformesElaboradosServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Informacion del servicio para obtener la informacion requerida para la grafica Informes elaborados
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.informepolicial.InformePolicialHomologadoDAO;
import mx.gob.segob.nsjp.service.graficacion.GraficaInformesElaboradosService;

/**
 * Informacion del servicio para obtener la informacion requerida para la grafica Informes elaborados.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class GraficaInformesElaboradosServiceImpl implements
		GraficaInformesElaboradosService {

	
	@Autowired
	private InformePolicialHomologadoDAO informePolicialHomologadoDAO;
	
	@Override
	public Long obtenerIPHPorFechas(Date fechaInicio, Date fechaFin,
			Boolean condicion) throws NSJPNegocioException {
		Long numRegistros = informePolicialHomologadoDAO.obtenerIPHPorFechas(fechaInicio, fechaFin, condicion);
		return numRegistros;
	}
}
