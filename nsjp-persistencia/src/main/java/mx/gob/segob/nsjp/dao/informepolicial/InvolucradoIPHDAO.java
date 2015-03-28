package mx.gob.segob.nsjp.dao.informepolicial;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.InformePolicialHomologado;
import mx.gob.segob.nsjp.model.InvolucradoIph;

public interface InvolucradoIPHDAO extends GenericDao<InvolucradoIph,Long>{
	public List<InformePolicialHomologado> consultarFolioIPHPorFechaOPersona(Date fechaInicio, Date fechaFin, String nombrePersona) throws NSJPNegocioException;
	/**
	 * Operación que consulta los involucrados para un IPH
	 * @param informePolicialHomologadoId
	 * @return
	 */
	public List<InvolucradoIph> consultarInvolucradosDeIPH(
			Long informePolicialHomologadoId);
	/**
	 * Obtiene el numero de remitidos por mes, dentro de un rango de fechas
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public List<Object[]> obtenerCasosRemitidosPorMes(Date fechaInicio,
			Date fechaFin);
}
