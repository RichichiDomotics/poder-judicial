package mx.gob.segob.nsjp.dao.involucrado;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.DefensaInvolucrado;
import mx.gob.segob.nsjp.model.DefensaInvolucradoId;
import mx.gob.segob.nsjp.model.Involucrado;

public interface DefensaInvolucradoDAO extends GenericDao<DefensaInvolucrado, DefensaInvolucradoId>{

	List<DefensaInvolucrado> consultarInvolucradoExpedienteDefensoria(
			Long expedienteId, Long valorId)throws NSJPNegocioException;

	
	Involucrado consultarInvolucradoNumeroExpedienteDefensoria(
			Long expedienteId) throws NSJPNegocioException;


	
	Involucrado consultarInvolucradoPGNumeroExpedienteDefensoria(
			Long numeroExpedienteId) throws NSJPNegocioException;
	/**
	 * Permite consultar el involucrado asociado a defensoria en base al numero de Expediente
	 * @param numeroExpedienteId
	 * @return Involucrado
	 * @throws NSJPNegocioException
	 */
	public List<Involucrado> consultarInvolucradosDEFNumeroExpedienteDefensoriaPorCalidad(
			Long numeroExpedienteId) throws NSJPNegocioException;

}
