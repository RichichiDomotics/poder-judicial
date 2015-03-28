/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.seguridad.Roles;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.usuario.UsuarioRolDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioWSDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.UsuarioRol;
import mx.gob.segob.nsjp.service.catalogo.ConsultarFuncionariosXTribunalService;
import mx.gob.segob.nsjp.service.infra.impl.transform.consultarcatalogo.FuncionarioWSDTOTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rgama
 *
 */
@Service("consultarFuncionariosXTribunalService")
@Transactional
public class ConsultarFuncionariosXTribunalServiceImpl implements
   ConsultarFuncionariosXTribunalService {
	
	public static final Logger logger = Logger.getLogger(ConsultarFuncionariosXTribunalServiceImpl.class);

	
	@Autowired 
	private FuncionarioDAO funcionarioDAO;
	@Autowired
	private UsuarioRolDAO usuarioRolDAO;

	public List<FuncionarioWSDTO> consultarFuncionariosXTribunal(Long catDiscriminanteId)throws NSJPNegocioException{

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR FUNCIONARIOS POR TRIBUNAL****/");
		
		if(catDiscriminanteId==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		//Permite consultar los funcionarios asociados a un Tribunal
		List<Funcionario> loFuncionarios = funcionarioDAO.consultarFuncionariosPorDiscriminante(catDiscriminanteId,null);
	
			List<Funcionario> listaFuncionario =  new ArrayList();
			siguiente:
			for (Funcionario funcionario : loFuncionarios) {
				String claveUsuario = funcionario.getUsuario()!= null? funcionario.getUsuario().getClaveUsuario():null;
			
				//Obtener la lista de roles del usuario
				if(claveUsuario!=null){
					List<UsuarioRol> listaRoles = usuarioRolDAO.consultarRolesDeUsuario(claveUsuario);
					
					//Buscar entre los roles del usuario(Funcionario) si se encuentra el rol indicado.
					for (UsuarioRol usuarioRol : listaRoles) {
						if( usuarioRol.getRol()!= null && usuarioRol.getRol().getRolId()!= null && 
								usuarioRol.getRol().getRolId().equals(Roles.ADMONPJ.getValorId())){
							//Encontrado
							listaFuncionario.add(funcionario);
							continue siguiente;
						}
					}
				}
			}
			
			loFuncionarios = listaFuncionario;
		
		
		List<FuncionarioWSDTO> loFuncionariosWSDTO = new ArrayList<FuncionarioWSDTO>();			
		for (Funcionario funcionario : loFuncionarios){
			loFuncionariosWSDTO.add(FuncionarioWSDTOTransformer.transformarFuncionario(funcionario));
		}
		return loFuncionariosWSDTO;
	}

}
