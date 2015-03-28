/**
 * Nombre del Programa : ConsultarAbogadosServiceImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 May 2011
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
import java.util.Collections;
import java.util.List;
import java.util.Random;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.DefensorDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.funcionario.ConsultarAbogadosService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion de metodos del servicio de busqueda de abogados defensores.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Service
@Transactional
public class ConsultarAbogadosServiceImpl implements ConsultarAbogadosService {

	@Autowired
	private DefensorDAO defensorDAO;

	private final static Logger logger = Logger
			.getLogger(ConsultarAbogadosServiceImpl.class);

	/**
	 * @deprecated
	 */
	@Override
	public List<FuncionarioDTO> consultarDefensoresActivos()
			throws NSJPNegocioException {

		return null;
	}

	@Override
	public List<FuncionarioDTO> consultarDefensoresActivosPorTipoDefensa(
			TipoDefensoria tipodefensoria) throws NSJPNegocioException {

		if (logger.isDebugEnabled()) 
			logger.debug("SERVICIO PARA LA CONSULTA DE FUNCIONARIOS DEFENSORES");

		List<FuncionarioDTO> lsFunDto = new ArrayList<FuncionarioDTO>();

		Long defensa = null;

		if (tipodefensoria != null) {
			defensa = tipodefensoria.getValorId();
		}

		List<Funcionario> lsFun = this.defensorDAO
				.consultarDefensoresActivosPorTipoDefensa(defensa);

		logger.debug("Defensores encontrados -->" + lsFun.size());
		FuncionarioDTO func = null;
		FuncionarioDTO funcionario;
		for (Funcionario f : lsFun) {
			funcionario = FuncionarioTransformer.transformarFuncionario(f);
			if (func == null
            || (func != null && funcionario.getCargaTrabajo()!= null 
            && funcionario.getCargaTrabajo().doubleValue() < func.getCargaTrabajo().doubleValue())) {
				func = funcionario;
			}
			lsFunDto.add(funcionario);
		}
		if(func != null){
			func.setMejorDisponibilidad(true);
		}
		return lsFunDto;
	}

	@Override
	public List<FuncionarioDTO> obtenerDefensoresConCargaMenor(
			List<FuncionarioDTO> ldefensoresDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("Servicio para obtener un subconjunto de funcionarios con carga de trabajo menor.");

		if (ldefensoresDTO == null || ldefensoresDTO.isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		// La solucion es hacer q la clase de FuncionarioDTO implemente la
		// interfaz Comparable e implementar el metodo de compareTo
		// por medio de atributo "cargaTrabajo" y hacer uso de
		// Collections.sort(ldefensoresDTO). No se modifico la clase de
		// Funcionario
		Collections.sort(ldefensoresDTO);

		// Para obtener un subconjunto se tomará bajo una promedio de las Carga
		// de Trabajo
		double suma = 0d;
		for (FuncionarioDTO funcionarioDTO : ldefensoresDTO) {
			suma += funcionarioDTO.getCargaTrabajo();
		}
		double promedio = suma / ldefensoresDTO.size();

		for (int i = 0; i < ldefensoresDTO.size(); i++) {
			FuncionarioDTO funcionario = ldefensoresDTO.get(i);
			if (funcionario.getCargaTrabajo() > promedio) {
				ldefensoresDTO.remove(funcionario);
				i--;
			}
		}

		return ldefensoresDTO;
	}

	@Override
	public Object asignarAleatoriamenteElemento(Object[] lista)
			throws NSJPNegocioException {
		Random aleatorio = new Random();
		if (logger.isDebugEnabled())
			logger.debug("Servicio para obtener un objeto aelatoriamente de una lista de objetos.");

		if (lista == null || lista.length == 0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		return (lista.length == 1 ? lista[0] : lista[aleatorio
				.nextInt(lista.length - 1)]);
	}
}
