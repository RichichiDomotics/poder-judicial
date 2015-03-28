package mx.gob.segob.nsjp.service.documento;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.AvisoDesignacionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

public interface AvisoDesignacionService {

	/**
	 * Consulta los avisos de Designacion por estatus y por clave de funcionario
	 * @param estado
	 * @param funcionario
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<AvisoDesignacionDTO> consultarAvisosDesignacion(EstatusNotificacion estado, FuncionarioDTO funcionario) throws NSJPNegocioException;
	
	/**
	 * Registra un nuevo aviso de designación.
	 * @param designacion
	 * @return
	 * @throws NSJPNegocioException
	 */
	public AvisoDesignacionDTO registrarAvisoDesignacion (AvisoDesignacionDTO designacion) throws NSJPNegocioException;

	/**
	 * Asocia un abogado defensor al aviso de Designación para que lo atienda.
	 * @param designacion
	 * @throws NSJPNegocioException
	 */
	void designarAbogadoDefensor(AvisoDesignacionDTO designacion)
			throws NSJPNegocioException;

	/**
	 * Cambia el funionario asociado al numero de expediente por el que se encuentra
	 * identificado por claveFuncionario
	 * @param idNumeroExpediente
	 * @param claveFuncionario
	 */
	public void cerrarAvisoDesignacion(Long idAvisoDesignacicon);

	/**
	 * Genera un nuevo aviso de designación para un expediente al cual se le está reasignando 
	 * un abogado defensor
	 * @param input
	 * @return
	 * @throws NSJPNegocioException
	 */
	public AvisoDesignacionDTO registrarAvisoDesignacionPorReasignacionDefensor(ExpedienteDTO input)
			throws NSJPNegocioException;

	
	/**
	 * Obtiene la información concerciente a un aviso de designación por su identificador.
	 * @param idAviso
	 * @return
	 * @throws NSJPNegocioException
	 */
	public AvisoDesignacionDTO consultarAvisoDesignacion(Long idAviso)throws NSJPNegocioException;
	
}
