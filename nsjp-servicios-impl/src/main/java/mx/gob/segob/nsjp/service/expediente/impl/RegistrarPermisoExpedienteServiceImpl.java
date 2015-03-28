/**
* Nombre del Programa : RegistrarPermisoExpedienteServiceImpl.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 4 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.PermisoExpedienteDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.PermisoExpediente;
import mx.gob.segob.nsjp.model.PermisoExpedienteId;
import mx.gob.segob.nsjp.service.expediente.RegistrarPermisoExpedienteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cesar
 *
 */
@Service
@Transactional
public class RegistrarPermisoExpedienteServiceImpl implements
		RegistrarPermisoExpedienteService {
	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(RegistrarPermisoExpedienteServiceImpl.class);
	
	@Autowired
	private PermisoExpedienteDAO permisoExpedienteDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private FuncionarioDAO funcionarioDAO;
	
	@Override
	public void registrarPermisoExpedienteFuncionario(Long claveFuncionario,
			Long numExpId, Date fechaLimite, Boolean permiso) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR LOS PERMISOS DE EXPEDIENTE ****/");
		
		PermisoExpediente permisoExpediente = new PermisoExpediente();
		NumeroExpediente numeroExpediente = new NumeroExpediente();
		Funcionario funcionario = new Funcionario();
		
		PermisoExpedienteId permisoExpedienteId = new PermisoExpedienteId();
		
		if (numExpId!=null) {
			numeroExpediente = numeroExpedienteDAO.read(numExpId);
			permisoExpediente.setNumeroExpediente(numeroExpediente);
			permisoExpedienteId.setNumeroExpedienteId(numExpId);
		}
		
		if (funcionario!=null) {
			funcionario = funcionarioDAO.read(claveFuncionario);
			permisoExpediente.setFuncionario(funcionario);
			permisoExpedienteId.setIclaveFuncionario(claveFuncionario);
		}
		
		if (fechaLimite!=null)
			permisoExpediente.setFechaLimite(fechaLimite);
		
		permisoExpediente.setEsEscritura(permiso);
		
//		permisoExpediente = permisoExpedienteDAO.read(permisoExpedienteId);
		permisoExpediente.setId(permisoExpedienteId);				
		
		permisoExpedienteDAO.saveOrUpdate(permisoExpediente);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** EL REGISTRO DEL PERMISO DEL FUNCIONARIO SOBRE EL EXPEDIENTE SE REALIZO CON EXITO ****/");
	}

	@Override
	public void eliminarPermisoExpedienteFuncionario(Long claveFuncionario,
			Long numExpId) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ELIMINAR LOS PERMISOS DE EXPEDIENTE ****/");
		
		if (claveFuncionario==null || numExpId==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		PermisoExpediente permisoExpediente = new PermisoExpediente();			
		PermisoExpedienteId permisoExpedienteId = new PermisoExpedienteId();
		
		permisoExpedienteId.setIclaveFuncionario(claveFuncionario);
		permisoExpedienteId.setNumeroExpedienteId(numExpId);
		
		permisoExpediente = permisoExpedienteDAO.read(permisoExpedienteId);
		
		permisoExpedienteDAO.delete(permisoExpediente);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SE ELIMINO CORRECTAMENTE EL PERMISO DEL EXPEDIENTE CON EXITO ****/");
	}

}
