/**
* Nombre del Programa : ConsultarJuezServiceImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Implementaci�n del servicio de negocio para la consulta de jueces
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
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.constants.ConstantesGenerales;
import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.funcionario.TipoEspecialidad;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.expediente.CalcularParidadNumeroExpedienteService;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionariosJuecesDisponiblesParaAudienciaService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementaci�n del servicio de negocio para la consulta de jueces
 * @version 1.0
 * @author Emigdio Hern�ndez
 *
 */
@Service
@Transactional
public class ConsultarFuncionariosJuecesDisponiblesParaAudienciaServiceImpl implements ConsultarFuncionariosJuecesDisponiblesParaAudienciaService {

	@Autowired
	FuncionarioDAO funcionarioDAO;
	@Autowired
	CalcularParidadNumeroExpedienteService calcularParidadNumeroExpedienteService;
	@Autowired
	AudienciaDAO audienciaDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.juez.ConsultarJuezService#consultarJuecesDisponiblesParaFechaYHoraAudiencia(java.util.Date, java.lang.Integer)
	 */
	@Override
	public List<FuncionarioDTO> consultarJuecesDisponiblesParaFechaYHoraAudiencia(
			Date fecha, Integer duracionEstimada, FuncionarioDTO funcionarioDTO) throws NSJPNegocioException{
		
		Funcionario funcionario = FuncionarioTransformer.transformarFuncionario(funcionarioDTO);
		List<FuncionarioDTO> resultado = new ArrayList<FuncionarioDTO>();
		List<Funcionario> jueces = funcionarioDAO.
		consultarFuncionariosPorEspecialidadYPuestoDisponiblesParaFechaYHoraAudiencia
		(fecha, duracionEstimada, TipoEspecialidad.JUEZ, Puestos.JUEZ,null, funcionario);
		for(Funcionario juez:jueces){
			resultado.add(FuncionarioTransformer.transformarFuncionario(juez));
		}
		return resultado;
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionariosJuecesDisponiblesParaAudienciaService#consultarJuezAutomaticoADesignar(java.util.Date, java.lang.Integer)
	 */
	@Override
	public List<FuncionarioDTO> consultarJuezAutomaticoADesignar(Date fecha,
			Integer duracionEstimada,AudienciaDTO audiencia,boolean juezSustituto,
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException {
		int juecesNecesarios = ConstantesGenerales.JUECES_AUTOMATICOS_PREDETERMINADO;
		Boolean paridadJuezRequerida = null;
		Audiencia audienciaDB = audienciaDAO.read(audiencia.getId());
		if(TipoAudiencia.JUICIO_ORAL.getValorId().equals(audiencia.getTipoAudiencia().getIdCampo())){
			juecesNecesarios = juezSustituto?ConstantesGenerales.JUECES_AUTOMATICOS_JUICIO_ORAL+
					ConstantesGenerales.JUECES_AUTOMATICOS_SUSTITUTOS:ConstantesGenerales.JUECES_AUTOMATICOS_JUICIO_ORAL;
		}
		//Consultar paridad requerida de juez
		if(audienciaDB != null && audienciaDB.getNumeroExpediente() != null){
			//Consultar la paridad de la audiencia
			paridadJuezRequerida = calcularParidadNumeroExpedienteService.consultarParidadDeNumeroExpediente(audienciaDB.getNumeroExpediente().getNumeroExpedienteId());
			if(TipoAudiencia.JUICIO_ORAL.getValorId().equals(audiencia.getTipoAudiencia().getIdCampo())){
				
				paridadJuezRequerida = paridadJuezRequerida!=null?!paridadJuezRequerida.booleanValue():null;
			}else{
				paridadJuezRequerida = null;
			}
			
		}
		
		
		Funcionario funcionario = FuncionarioTransformer.transformarFuncionario(funcionarioDTO);
		
		List<FuncionarioDTO> juecesElegidos = new ArrayList<FuncionarioDTO>();
		List<Funcionario> jueces = funcionarioDAO.
		consultarFuncionariosPorEspecialidadYPuestoDisponiblesParaFechaYHoraAudiencia
		(fecha, duracionEstimada, TipoEspecialidad.JUEZ, Puestos.JUEZ,paridadJuezRequerida, funcionario);
		
		for(Funcionario unJuez:jueces){
			juecesElegidos.add(FuncionarioTransformer.transformarFuncionario(unJuez));
		}
		
		while(juecesElegidos.size()>juecesNecesarios){
			juecesElegidos.remove(juecesElegidos.size()-1);
		}
		
		
		return juecesElegidos;
	}

}
