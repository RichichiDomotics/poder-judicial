package mx.gob.segob.nsjp.dao.informepolicial;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.DelitoIph;
import mx.gob.segob.nsjp.model.FaltaAdministrativaIph;
import mx.gob.segob.nsjp.model.InformePolicialHomologado;

public interface InformePolicialHomologadoDAO 
	   extends
	             GenericDao<InformePolicialHomologado,Long> {	
	public List<Object[]> ObtenerFolioIPH();
	public InformePolicialHomologado consultaInformePorFolio(Long folio);
	public InformePolicialHomologado consultarInformePorId(Long Id);
	public List<DelitoIph> consultarDelitosDeIPH(Long idInforme);
	public List<FaltaAdministrativaIph> consultarFaltaAdministrativaDeIPH(Long idInforme);
	public List<InformePolicialHomologado> consultarInformes();
	
	/**
	 * Obtiene el numero de IPH o IP resgistrados dentro de un rango de fechas.
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @param condicion
	 * @return
	 */
	public Long obtenerIPHPorFechas(Date fechaInicio, Date fechaFin, Boolean condicion);
	
}
