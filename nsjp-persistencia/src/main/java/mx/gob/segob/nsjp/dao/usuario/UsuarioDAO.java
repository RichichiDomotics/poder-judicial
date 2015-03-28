/*

 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.segob.nsjp.dao.usuario;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Usuario;

/**
 *
 * @author sawbona
 */
public interface UsuarioDAO extends GenericDao<Usuario, Long> {

    public Usuario consultarUsuarioPorClaveService(String claveUsuario);

	public Usuario consultarUsuarioPorClaveFuncionario(Long claveFuncionario);
	
	public Usuario login(String claveUsuario, String pwd);
	
	public Usuario registrarUsuario(Usuario usuario);
	
	public void actualizarPasswordUsuario(Usuario usuario);
	
	public String consultarPasswordXClaveUsuario(String claveUsuario);
	
	public void actualizarSesionUsuario();

}
