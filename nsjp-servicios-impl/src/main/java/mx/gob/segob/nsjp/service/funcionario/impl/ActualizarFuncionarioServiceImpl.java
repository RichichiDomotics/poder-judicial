/**
* Nombre del Programa : ActualizarFuncionarioServiceImpl.java
* Autor                            : jmartinez
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para actualizar un funcinario
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
package mx.gob.segob.nsjp.service.funcionario.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.persona.CorreoElectronicoDAO;
import mx.gob.segob.nsjp.dao.persona.MedioDeContactoDAO;
import mx.gob.segob.nsjp.dao.persona.TelefonoDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.funcionario.ActualizarFuncionarioService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para ingresar un Perito.
 * @version 1.0
 * @author jmartinez
 *
 */
@Service
@Transactional
public class ActualizarFuncionarioServiceImpl implements ActualizarFuncionarioService{
	
private final static Logger logger = Logger.getLogger(ActualizarFuncionarioServiceImpl.class);
	
	@Autowired
	private FuncionarioDAO funDao;
	@Autowired
	private TelefonoDAO telefonoDAO;
	@Autowired
	private CorreoElectronicoDAO correoElectronicoDAO;
	@Autowired
	private MedioDeContactoDAO medioDeContactoDAO;
	
	@Override
	public FuncionarioDTO modificarDefensor(FuncionarioDTO defensorDTO)
			throws NSJPNegocioException {
			
		if(logger.isDebugEnabled()) {
			logger.debug("/SERVICIO PARA MODIFICAR UN DEFENSOR/");	
			logger.debug("defensorDTO :: " + defensorDTO);
		}
		
		//MEDIOS DE CONTACTO - Correo electronico - Telefono --Se borrran los que actualmente estan en BD
		List<Telefono> telefonos = telefonoDAO.consultarTelefonosByFuncionario(defensorDTO.getClaveFuncionario());
		for (Telefono telefono : telefonos) {
			logger.info(" Borrando MDC Tel : "+ telefono.getMedioDeContactoId());
			medioDeContactoDAO.delete(telefono);	
		}
		
		List<CorreoElectronico> correos = correoElectronicoDAO.consultarCorreosByFuncionario(defensorDTO.getClaveFuncionario());
		for (CorreoElectronico correoElectronico : correos) {
			logger.info(" Borrando MDC Correo : "+ correoElectronico.getMedioDeContactoId());
			medioDeContactoDAO.delete(correoElectronico);
		}
		
		Funcionario funcionarioBD=funDao.read(defensorDTO.getClaveFuncionario());
		//Se prepara la entidad para actualizar los datos en el funcionario que se ha leido de BD 
		Funcionario loPerito = FuncionarioTransformer.modificarDefensor(defensorDTO, funcionarioBD);
		
		if(funcionarioBD.getArchivoDigital()!=null && defensorDTO.getArchivoDigital()==null){
			ArchivoDigital archivo = funcionarioBD.getArchivoDigital();
			defensorDTO.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigital(archivo));
			loPerito.setArchivoDigital(archivo);
		}		
		
		//Al hacer update, se guardan los nuevos Medios de contacto
		funDao.update(loPerito);

		return defensorDTO;
	}

}
