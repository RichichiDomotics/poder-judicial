/**
* Nombre del Programa : RecibirExpedienteServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/09/2011
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.caso.EstatusCaso;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.catalogo.CatDiscriminateDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.service.caso.AsignarNumeroCasoService;
import mx.gob.segob.nsjp.service.catalogo.ConsultarDiscriminanteService;
import mx.gob.segob.nsjp.service.expediente.ActualizarCarpetaDeInvestigacionService;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.expediente.RecibirExpedienteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class RecibirExpedienteServiceImpl implements
		RecibirExpedienteService {
	private final static Logger logger = Logger.getLogger(RecibirExpedienteServiceImpl.class);
	
    @Autowired
    private AsignarNumeroCasoService casoService;
    @Autowired
    private AsignarNumeroExpedienteService asignarNumeroExpedienteService;
    @Autowired 
    private ActualizarCarpetaDeInvestigacionService actualizarCarpetaDeInvestigacionService;
    @Autowired 
    private CatDiscriminateDAO catDiscriminateDAO;
    @Autowired 
    private ConsultarDiscriminanteService consultarDiscriminanteService;
    
    
    
    
    public ExpedienteDTO guardarExpedienteRecibido(ExpedienteDTO expDTO, Long idAgencia)throws NSJPNegocioException {
    	
    	//Generar el Caso, Expediente y Numero de Expediente
    	ExpedienteDTO expNuevo = generarCasoExpediente(idAgencia);
    	
    	expDTO.setExpedienteId(expNuevo.getExpedienteId());
        expDTO.setCasoDTO(expNuevo.getCasoDTO());
        expDTO.setNumeroExpedienteId(expNuevo.getNumeroExpedienteId());
        expDTO.setNumeroExpediente(expNuevo.getNumeroExpediente());
       
        logger.info("**********+++++++ EXPEDIENTE GENERADO++++++**********************");
        logger.info(" ExpDTO: " + expDTO);
        logger.info(" ExpDTO - ExpedienteId: " + expDTO.getExpedienteId());
        logger.info(" ExpDTO - NumeroExpediente: " + expDTO.getNumeroExpediente());
        logger.info(" ExpDTO - NumeroExpedienteId: " + expDTO.getNumeroExpedienteId());
        logger.info(" ExpDTO - CasoDTO: " + expDTO.getCasoDTO());
		logger.info(" ExpDTO - idAgencia: "+ idAgencia);


        //Settear los ID de involucrados en 0
        List<InvolucradoDTO> involucradosDTO = new ArrayList<InvolucradoDTO>();
        for (InvolucradoDTO involucradoDTO : expDTO.getInvolucradosDTO()) {
			involucradoDTO.setElementoId(0L);
			involucradosDTO.add(involucradoDTO);
		}
        expDTO.setInvolucradosDTO(involucradosDTO);
        
        //TODO GBP  Verificar el delito como delito Principal
        
        //Se genera una actividad que se asocia a un Expediente
        expDTO = actualizarCarpetaDeInvestigacionService.actualizarExpedienteDeCarpetaInvestigacionDefensoria(expDTO);
        
    	return expDTO;
    }
    
	@Override
	public synchronized ExpedienteDTO generarCasoExpediente(Long idAgencia)
			throws NSJPNegocioException {
		//TODO GBP Preguntar si debería sincronizarse con el servicio AsignarNumeroExpedienteService -> asignarNumeroExpediente(TurnoDTO turno)
		
		//Se genera el caso para ser asociado al Expediente
        CasoDTO casoDTO = new CasoDTO();
        casoDTO.setFechaApertura(new Date());
        casoDTO.setEstatus(EstatusCaso.INVESTIGACION);
        casoDTO = casoService.asignarNumeroCaso(casoDTO,obtenerFuncionario());

        //Verificar el expediente que se recibe
        ExpedienteDTO expParam = new ExpedienteDTO();
        expParam.setFechaApertura(new Date());
        UsuarioDTO usuario = new UsuarioDTO(8L); //Se sette al usuario coordinadorAmp
        FuncionarioDTO funcionario = new FuncionarioDTO(8L); //mismo funcionario
        usuario.setFuncionario(funcionario);
        expParam.setUsuario(usuario);
        expParam.setArea(new AreaDTO(Areas.UNIDAD_INVESTIGACION) );
        expParam.setCasoDTO(casoDTO);
        
        expParam.setEstatus(new ValorDTO(EstatusExpediente.PENDIENTE_REVISION_COMO_IPH.getValorId()));
        //Se asocia el id de la agencia al expediente
        if(idAgencia != null && idAgencia > 0 && expParam.getUsuario() != null && expParam.getUsuario().getFuncionario() != null){
        	CatDiscriminanteDTO loCatDiscriminante = consultarDiscriminanteService.consultarDiscriminanteXId(idAgencia);
       		expParam.getUsuario().getFuncionario().setDiscriminante(loCatDiscriminante);
        }
        	
        // Es necesario para generar el Numero de Expediente
        ExpedienteDTO expNuevo = asignarNumeroExpedienteService.asignarNumeroExpediente(expParam);
        expNuevo.setCasoDTO(casoDTO);
		return expNuevo;
	}

	//Obtener funcionario con Area de Unidad de investigacion
    private FuncionarioDTO obtenerFuncionario() {     
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		DepartamentoDTO departamento = new DepartamentoDTO();
		AreaDTO area = new AreaDTO(Areas.PGJ ); 
		departamento.setArea(area);
		funcionarioDTO.setDepartamento(departamento);
		return funcionarioDTO;
	}
    
}
