package mx.gob.segob.nsjp.dao.audiencia;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.model.InvolucradoAudienciaId;

public interface InvolucradoAudienciaDAO extends GenericDao<InvolucradoAudiencia, InvolucradoAudienciaId> {
	/**
	 * Consulta una lista de las audiencias donde se encuentra involucrado la persona a buscar
	 * @param involucradoId Id del involucrado a buscar
	 * @author Emigdio Hernández
	 * @return Lista de audiencias donde participa el involucrado
	 */
	List<Audiencia> consultarAudienciasDeInvolucrado(Long involucradoId);
	/**
	 * Actualiza el campo que indica si el involucrado está presente o no en una audiencia
	 * @param involucradoId ID del involucrado de audiencia
	 * @param audienciaId ID de la audiencia
	 * @param presente Valor del campo que indica si está presente
	 * @author Emigdio Hernández
	 */
	void actualizarIndicadorPresenteInvolucrado(Long involucradoId, Long audienciaId,boolean presente);

	/**
	 *Consulta el maximo Id de una audiencia a la que pertenece el involucrado.
	 *
	 * @param idInvolucrado
	 * @return
	 */
	Long obtenerMaximoIdAudienciaInvolucrado(Long idInvolucrado);
	
	/**
	 * Consulta el campo que indica si el involucrado está presente o no en una audiencia
	 * @param involucradoId ID del involucrado de audiencia
	 * @param audienciaId ID de la audiencia
	 * @author Eduardo Alvarado
	 */
	InvolucradoAudiencia consultarIndicadorPresenteInvolucradoAudiencia(Long involucradoAudienciaId, Long audienciaId);
}
