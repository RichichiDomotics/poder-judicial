/**
 * 
 */
package mx.gob.segob.nsjp.delegate.visitante.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.visitante.VisitanteDelegate;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.dto.visitante.VisitanteDTO;
import mx.gob.segob.nsjp.service.visitante.VisitanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LuisMG
 *
 */
@Service
@Transactional
public class VisitanteDelegateImpl implements VisitanteDelegate {
	
	@Autowired
	private VisitanteService visitanteService;
	
	
	/**
	 * Si no existe un visitante lo registra y si ya existe lo actualiza
	 * @param visitante
	 * @return
	 * @throws NSJPNegocioException
	 */
	@Override
    public boolean registrarVisitante(VisitanteDTO visitante) throws NSJPNegocioException{
    	return visitanteService.registrarVisitante(visitante);
    }
    
    /**
     * Elimina una visita dada una IP y regresa True en caso de existir el registro o false en caso de que no exista el registro
     * @param visitante
     * @return
     * @throws NSJPNegocioException
     */
	@Override
    public boolean eliminarVisitantePorIP (VisitanteDTO visitante)throws NSJPNegocioException{
    	return visitanteService.eliminarVisitantePorIP(visitante);
    }
    
    /**
     * Consulta un visitante con base en su dirección IP y regresa Null en caso de que el visitante no se encuentre registrado
     * @param visitante
     * @return
     * @throws NSJPNegocioException
     */
	@Override
    public VisitanteDTO consultarVisitantePorIP (VisitanteDTO visitante) throws NSJPNegocioException{
    	return visitanteService.consultarVisitantePorIP(visitante);
    }

}
