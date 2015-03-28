/**
* Nombre del Programa : consultarDefensoresServiceImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos del servicio de busqueda de abogados defensores
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


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.persona.CorreoElectronicoDAO;
import mx.gob.segob.nsjp.dao.persona.TelefonoDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.service.funcionario.ConsultarDefensoresService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion de metodos del servicio de busqueda de abogados defensores.
 * @version 1.0
 * @author rgama
 *
 */
@Service
@Transactional
public class ConsultarDefensoresServiceImpl implements ConsultarDefensoresService {

	@Autowired
	private FuncionarioDAO funcionarioDAO;
	@Autowired
	private TelefonoDAO telefonoDAO;
	@Autowired
	private CorreoElectronicoDAO correoElectronicoDAO;
	
	private final static Logger logger = Logger.getLogger(ConsultarDefensoresServiceImpl.class);

	/**
	 * Metodo que permite consultar los defensores
	 * @return Devuelve un listado de defensores
	 * @throws NSJPNegocioException
     * @author ricardo
	 */
	@Override
	public List<FuncionarioDTO> consultarDefensores() throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("SERVICIO PARA LA CONSULTA ABOGADOS DEFENSORES");
		
		List<FuncionarioDTO> lsFunDto = new ArrayList<FuncionarioDTO>();		
		
		List<Funcionario> lsFun =  this.funcionarioDAO.consultarDefensores(); 
					
		logger.debug("Defensores encontrados -->"+ lsFun.size());
		
		lsFunDto = FuncionarioTransformer.transformarFuncionarios(lsFun);
		
		return lsFunDto;
	}
	
    /**
     * Metodo que permite consultar los defensores de acuerdo a un tipo de Defensa
     * @param idTipoDefensa Recibe el tipo de defensa que se va a consultar.
     * @return Devuelve un listado de defensores que ejercen ese tipo de Defensa.
     * @author ricardo
     */
	@Override
	public List<FuncionarioDTO> consultarDefensorPorTipoDefensa(Long idTipoDefensa) throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("SERVICIO PARA LA CONSULTA ABOGADOS DEFENSORES POR TIPO DE DEFENSA");
		
		List<FuncionarioDTO> lsFunDto = new ArrayList<FuncionarioDTO>();		
		
		List<Funcionario> lsFun =  this.funcionarioDAO.consultarDefensorPorTipoDefensa(idTipoDefensa); 
					
		logger.debug("Defensores encontrados -->"+ lsFun.size());
		
		lsFunDto = FuncionarioTransformer.transformarFuncionarios(lsFun);
		
		return lsFunDto;
	}
	
	@Override
	public FuncionarioDTO obtenerInformacionDefensor(FuncionarioDTO defensor)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("SERVICIO PARA LA CONSULTA DE LA INFORMACION DEL DEFENSOR");
		
		Funcionario funcionario = funcionarioDAO.obtenerInformacionDefensorPorId(defensor.getClaveFuncionario());
		
		Set<MedioDeContacto> mediosDeContacto = new HashSet<MedioDeContacto>();
		List<Telefono> telefonos = telefonoDAO.consultarTelefonosByFuncionario(defensor.getClaveFuncionario());
		for (Telefono telefono : telefonos) 
			mediosDeContacto.add((MedioDeContacto) telefono);	
		
		List<CorreoElectronico> correos = correoElectronicoDAO.consultarCorreosByFuncionario(defensor.getClaveFuncionario());
		for (CorreoElectronico correoElectronico : correos) 
			mediosDeContacto.add((MedioDeContacto) correoElectronico);
		
		funcionario.setMedioDeContactos(mediosDeContacto );
		
		FuncionarioDTO fdef = FuncionarioTransformer.transformarFuncionario(funcionario);
	
		return fdef;
	}


}
