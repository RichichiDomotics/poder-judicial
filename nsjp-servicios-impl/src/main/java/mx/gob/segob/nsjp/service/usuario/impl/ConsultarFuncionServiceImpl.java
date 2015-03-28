/**
* Nombre del Programa : ConsultarFuncionServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar las consultar de Funcion
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
package mx.gob.segob.nsjp.service.usuario.impl;

import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.usuario.FuncionDAO;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.model.Funcion;
import mx.gob.segob.nsjp.service.usuario.ConsultarFuncionService;
import mx.gob.segob.nsjp.service.usuario.impl.transformer.FuncionTransformer;

/**
 * Implementacion del servicio para realizar las consultar de Funcion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ConsultarFuncionServiceImpl implements ConsultarFuncionService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(ConsultarFuncionServiceImpl.class);  
	
	@Autowired
	private FuncionDAO funcionDAO;
	
	@Override
	public List<FuncionDTO> consultarFunciones() throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR EL CATALOGO DE FUNCIONES ****/");
		
		List<Funcion> funciones = funcionDAO.consultarFunciones();
		
		List<FuncionDTO> funcionesDTO = new ArrayList<FuncionDTO>();
		for (Funcion funcion : funciones) {
			funcionesDTO.add(FuncionTransformer.transformarFuncion(funcion));
		}
		
		return funcionesDTO;
	}

	@Override
	public List<FuncionDTO> consultarFuncionesByRol(RolDTO rolDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LAS FUNCIONES DE UN ROL ****/");
		
		if (rolDTO.getRolId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Funcion> funciones = funcionDAO.consultarFuncionesByRol(rolDTO.getRolId());
		
		List<FuncionDTO> funcionesDTO = new ArrayList<FuncionDTO>();
		for (Funcion funcion : funciones) {
			funcionesDTO.add(FuncionTransformer.transformarFuncion(funcion));
		}
		
		return funcionesDTO;
	}
	
	@Override
	public FuncionDTO consultarFuncionXNombre (FuncionDTO fncDTO) throws NSJPNegocioException{
		Funcion resp=null;
		resp= funcionDAO.consultarFuncionXNombre(new Funcion(fncDTO.getNombreFuncion()));
		fncDTO= FuncionTransformer.transformarFuncion(resp);
		return fncDTO;
	}

}
