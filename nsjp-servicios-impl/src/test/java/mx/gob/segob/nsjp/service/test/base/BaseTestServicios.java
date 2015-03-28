/**
 * 
 */
package mx.gob.segob.nsjp.service.test.base;

import java.io.FileNotFoundException;
import java.lang.reflect.ParameterizedType;

import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;
import org.springframework.util.Log4jConfigurer;

/**
 * @author vaguirre
 * 
 */
@ContextConfiguration(locations = { "/dataSource-test-context.xml",
		"/sessionFactory-nsjp-context.xml", "/daos-nsjp-context.xml",
		"/services-nsjp-context.xml", "/tx-nsjp-context.xml" })
public abstract class BaseTestServicios<T> extends
		AbstractJUnit38SpringContextTests {
	/**
	 * Clase bajo prueba.
	 */
	protected T service;
	/**
	 * Tipo del service.
	 */
	private Class<T> type = null;

	static{
		try {
			Log4jConfigurer.initLogging("../nsjp-persistencia/src/test/resources/log4j-config-unitTesting.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
}

	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		try {
			service = super.applicationContext.getBean(getType());
		} catch (Exception e) {
			logger.error("Error al recuperar el Servicio del contexto de Spring");
			logger.error(e.getMessage());
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getType() {
		if (type == null) {
			Class clazz = getClass();

			while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)) {
				clazz = clazz.getSuperclass();
			}

			type = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass())
					.getActualTypeArguments()[0];
		}

		return type;
	}
	
	protected UsuarioDTO getUsuario(){
        final UsuarioDTO usrHardCode = new UsuarioDTO();
        final FuncionarioDTO funcionario = new FuncionarioDTO(1L);
        usrHardCode.setFuncionario(funcionario);
        usrHardCode.setIdUsuario(1L);
        usrHardCode.setClaveUsuario("hardCode");
        usrHardCode.setAreaActual(new AreaDTO(Areas.UNIDAD_INVESTIGACION));
        return usrHardCode;
	}
}
