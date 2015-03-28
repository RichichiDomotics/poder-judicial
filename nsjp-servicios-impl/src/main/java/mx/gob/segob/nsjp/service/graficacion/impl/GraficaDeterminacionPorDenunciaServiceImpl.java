/**
* Nombre del Programa : GraficaDeterminacionPorDenunciaServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para obtener la informacion requerida para la grafica 
 * 							determinacion por denuncia
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
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.service.graficacion.GraficaDeterminacionPorDenunciaService;

/**
 * Implementacion del servicio para obtener la informacion requerida para la grafica 
 * determinacion por denuncia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class GraficaDeterminacionPorDenunciaServiceImpl implements
		GraficaDeterminacionPorDenunciaService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(GraficaDeterminacionPorDenunciaServiceImpl.class);
	
	@Autowired
	private AudienciaDAO audienciaDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.graficacion.GraficaDeterminacionPorDenunciaService#obtenerAudienciasJudicializadasPorMes(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Object[]> obtenerAudienciasJudicializadasPorMes(
			Date fechaInicial, Date fechaFin) throws NSJPNegocioException {
		
		List<Object[]> audoenciasResult = audienciaDAO.obtenerAudienciasJudicializadasPorMes(fechaInicial, fechaFin);
		
		for (Object[] objects : audoenciasResult) {			
			objects[0]=Meses.getNombre((Integer)objects[0]-1);
		}
		
		return audoenciasResult;
	}

	@Override
	public List<Object[]> obtenerNumExpPorEstatusYMes(Date inicial, Date fin,
			EstatusExpediente estatusExpediente) {
		
		List<Object[]> expedientesResult = numeroExpedienteDAO.obtenerNumExpPorEstatusYMes(inicial, fin, estatusExpediente);
		for (Object[] objects : expedientesResult) {			
			objects[0]=Meses.getNombre((Integer)objects[0]-1);
		}
		
		return expedientesResult;
	}

}
