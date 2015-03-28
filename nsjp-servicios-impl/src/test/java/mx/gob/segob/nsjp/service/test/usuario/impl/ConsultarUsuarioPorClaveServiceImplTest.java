/**
 * Nombre del Programa : ConsultarUsuarioPorClaveServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 23-jun-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.test.usuario.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioRolDTO;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.service.usuario.ConsultarUsuarioPorClaveService;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarUsuarioPorClaveServiceImplTest
    extends BaseTestServicios<ConsultarUsuarioPorClaveService> {

    public void testConsultarUsuarioPorClaveService(){
        try {
            logger.info("Probando el servicio de: ConsultarUsuarioPorClaveService");
            assert service != null;
            UsuarioDTO usuario = service.consultarUsuarioPorClaveService("atpenal");
            assertNotNull("El usuario no puede ser null", usuario);
            logger.info("Verificando las propiedades del usuario");
            assertNotNull("usuario.getAreaActual()", usuario.getAreaActual());
            assertNotNull("usuario.getClaveUsuario()", usuario.getClaveUsuario());
            assertNotNull("usuario.getFuncionario()", usuario.getFuncionario());
            assertNotNull("usuario.getIdUsuario()", usuario.getIdUsuario());
            if(usuario.getPassword()!=null)
            	assertNotNull("usuario.getPassword()", usuario.getPassword());
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
        }
    }
   
    public void testConsultarRolesDeUsuario(){
    	try {
			List<UsuarioRolDTO> res = service.consultarRolesDeUsuario("atPrenal");
			logger.debug("Tamaño de roles:" + res.size());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			fail();
		}
    }
}
