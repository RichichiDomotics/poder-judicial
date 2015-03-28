/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.segob.nsjp.dao.test.usuario.impl;

import mx.gob.segob.nsjp.dao.test.TestUtilPersitencia;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.dao.usuario.UsuarioDAO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Usuario;

/**
 *
 * @author sawbona
 */
public class UsuarioDAOImplTest extends BaseTestPersistencia<UsuarioDAO> {

    public void testConsultarUsuarioPorClaveService() {
        // TODO como insertar un usuario valido para probar la busqueda de
        // dicho usuario por clave
        Usuario usuario = daoServcice.consultarUsuarioPorClaveService("Administrador");
        assertNotNull("El usuario no puede ser null: ", usuario);
        assertNotNull("La clave del usuario no deberia ser null: ", usuario.getClaveUsuario());
        assertNotNull("El funcionario no deberia ser null: ", usuario.getFuncionario());
        assertNotNull("El nombre del funcionario no deberia ser null: ", usuario.getFuncionario().getNombreFuncionario());
        assertNotNull("El puesto del funcionario no deberia ser null: ", usuario.getFuncionario().getPuesto());
        assertNotNull("El area del funcionario no deberia ser null: ", usuario.getFuncionario().getArea());
        assertNotNull("La ip del usuario no puede ser null: ", usuario.getcIp());
        System.out.println("IP de usuario: " + usuario.getcIp());
        
        // TODO como borrar al usuario insertado en la base de datos.
    }
    
    
    public void testRegistrarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setClaveUsuario("gama1");
        usuario.setPasword("password");
        Funcionario loFuncionario = new Funcionario(56L);
        usuario.setFuncionario(loFuncionario);
        Usuario usuarioBD = daoServcice.registrarUsuario(usuario);
        assertNotNull(usuarioBD);
        System.out.println(usuarioBD.getUsuarioId());
        
    }
    
    
    public void testConsultarUsuario() {
        Usuario usuarioBD = daoServcice.login("gama2", "textoEnClaro");
        System.out.println(usuarioBD.getUsuarioId());
        assertNotNull(usuarioBD);
        
    }
    
    public void testActualizarPasswordUsuario() {
    	 Usuario usuario = new Usuario();
         usuario.setUsuarioId(106L);
         usuario.setPasword("newPassword1");

         daoServcice.actualizarPasswordUsuario(usuario);
        
    }
    
    public void testDesbloqueaUsuario(){
    	daoServcice.actualizarSesionUsuario();
    }
}
