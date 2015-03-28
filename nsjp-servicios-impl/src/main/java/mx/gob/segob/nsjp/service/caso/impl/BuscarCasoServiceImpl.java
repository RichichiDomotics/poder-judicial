/**
* Nombre del Programa : ConsultarCasoServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Implentacion de metodos del servicio de busquedas de caso
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
package mx.gob.segob.nsjp.service.caso.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.persona.NombreDemograficoDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.caso.FiltroCasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.FiltroExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.model.Vehiculo;
import mx.gob.segob.nsjp.service.caso.BuscarCasoService;
import mx.gob.segob.nsjp.service.caso.impl.transform.CasoTransformer;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.objeto.impl.transform.VehiculoTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.NombreDemograficoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implentacion de metodos del servicio de busquedas de caso.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class BuscarCasoServiceImpl implements BuscarCasoService {

	 /**
     * Logger.
     */
    private final static Logger logger = Logger.getLogger(BuscarCasoServiceImpl.class);
	
	@Autowired
	private CasoDAO casoDAO;
	
	@Autowired
	private InvolucradoDAO involucradoDAO;	
	
	@Autowired
	private BuscarExpedienteService buscarExpedienteService;
	
	@Autowired
	private NombreDemograficoDAO nombreDemograficoDAO;
	
	
	@Override
	public List<CasoDTO> buscarCasoPorNumero(CasoDTO casoDTO)
			throws NSJPNegocioException {
		
		List<CasoDTO> casosDTO = new ArrayList<CasoDTO>();
		
		if (casoDTO.getNumeroGeneralCaso()!=null) {
			List<Caso> casos = casoDAO.consultarCasosPorNumero(casoDTO.getNumeroGeneralCaso());
			
			casosDTO = CasoTransformer.transformarCasoBasico(casos);
			
			for (CasoDTO casoIterador : casosDTO) {
				
				List<ExpedienteDTO> expedientesDTO = buscarExpedienteService.consultarExpedientesPorIdCaso(new CasoDTO(casoIterador.getCasoId(),casoDTO.getUsuario()));
				
				
				for (ExpedienteDTO expedienteDTO : expedientesDTO) {					
					List<Involucrado> involucrados = involucradoDAO.consultarInvolucradosByExpediente(expedienteDTO.getExpedienteId());
					List<InvolucradoDTO> involucradosDTO = new ArrayList<InvolucradoDTO>(); 
					for (Involucrado involucrado : involucrados) {
						involucradosDTO.add(InvolucradoTransformer.transformarInvolucrado(involucrado));
					}
					
					for (InvolucradoDTO involucradoDTO : involucradosDTO) {
						List<NombreDemografico> nombres = nombreDemograficoDAO.consutarNombresByInvolucrado(involucradoDTO.getElementoId());       			       			
						involucradoDTO.setNombresDemograficoDTO(NombreDemograficoTransformer.transformarNombreDemografico(nombres));
					}
					
					expedienteDTO.setInvolucradosDTO(involucradosDTO);
				} 
				casoIterador.setExpedintesDTO(expedientesDTO);
			}
		} else
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		return casosDTO;
	}
	
	@Override
	public List<InvolucradoDTO> buscarCasoPorFiltros(FiltroExpedienteDTO filtrosDTO) throws NSJPNegocioException {

		List<InvolucradoDTO> involucradosDTO = new ArrayList<InvolucradoDTO>();
		
		if (filtrosDTO.getAlias()!=null) {			
			involucradosDTO = buscarExpedienteService.buscarExpedientesPorFiltros(filtrosDTO);
			logger.info("/***** Lista involucrados DTO " + involucradosDTO.size());
		} else if (filtrosDTO.getNombre()!=null || filtrosDTO.getApellidos()!=null) {
			involucradosDTO = buscarExpedienteService.buscarExpedientesPorFiltros(filtrosDTO);
		} else {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);		
		}
		return involucradosDTO;
	}
	
	
	@Override
	public List<CasoDTO> buscarCasoPorFiltros(FiltroCasoDTO filtrosDTO) throws NSJPNegocioException {

		List<CasoDTO> casosDTO = new ArrayList<CasoDTO>();
		List<Caso> casos = new ArrayList<Caso>();
		
		if (filtrosDTO.getDelito()!=null) {
			casos = casoDAO.consultarCasosPorDelito(""+filtrosDTO.getDelito()+"");
		} else if (filtrosDTO.getFechaCreacionInicio()!=null && filtrosDTO.getFechaCreacionFin()!=null) {
			casos = casoDAO.consultarCasosPorFecha(filtrosDTO.getFechaCreacionInicio(), filtrosDTO.getFechaCreacionFin());
		} else 
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		casosDTO = CasoTransformer.transformarCasoBasico(casos);
		
		return casosDTO;
	}

	
    public List<CasoDTO> consultarCasosPorUsuario(UsuarioDTO usr)
            throws NSJPNegocioException {
        if (usr==null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        List<Caso> fromBD = this.casoDAO.consultarCasos(usr.getIdUsuario());
        
        return CasoTransformer.transformarCasoBasico(fromBD);
    }
    /*
     * (non-Javadoc)
     * @see mx.gob.segob.nsjp.service.caso.BuscarCasoService#consultarCasosConEventosHistoricos(mx.gob.segob.nsjp.dto.usuario.UsuarioDTO)
     */
	@Override
	public List<CasoDTO> consultarCasosConEventosHistoricos(UsuarioDTO usr)
			throws NSJPNegocioException {
		List<CasoDTO> resultado = new ArrayList<CasoDTO>();
		List<Caso> casos = casoDAO.consultarCasosConEventosHistoricos(usr.getIdUsuario());
		for(Caso caso:casos){
			resultado.add(CasoTransformer.transformarCasoBasico(caso));
		}
		
		
		
		
		return resultado;
		
	}

	@Override
	public CasoDTO consultarCasoPorExpediente(ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR CASO POR UN EXPEDIENTE DADO****/");
		
		/*Verificación de parámetros*/
		if (expedienteDTO == null|| expedienteDTO.getExpedienteId() == null) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		/*Operacion*/
		Caso caso=casoDAO.consultarCasoPorExpediente(expedienteDTO.getExpedienteId());
		CasoDTO casoDTO=null;
		if(caso!=null)
			casoDTO=CasoTransformer.transformarCasoBasico(caso);
		return casoDTO;
	}

    @Override
    public ValorDTO consultarEtapaAnteriorCaso(CasoDTO casoDto)
            throws NSJPNegocioException {
        if (casoDto == null || casoDto.getCasoId() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Caso caso = casoDAO.read(casoDto.getCasoId());
        ValorDTO valorDto = null;
        Valor etapa = caso.getEtapa();
        if (etapa != null) {
            valorDto = new ValorDTO();
            valorDto.setIdCampo(etapa.getValorId());
            valorDto.setValor(etapa.getValor());
        }
        return valorDto;
    }
    
    
    @Override
	public List<CasoDTO> buscarReincidenciaDeElementos(ElementoDTO elementoDTO)
			throws NSJPNegocioException {
    	List<CasoDTO> casosDTO = new ArrayList<CasoDTO>();
    	List<Caso> casos = null;
    	
    	Vehiculo vehiculo = null;
    	Involucrado involucrado = null;

    	
		if (elementoDTO !=null){
			//Permite obtener los casos en las que un vehiculo es reincidente
			if(elementoDTO instanceof VehiculoDTO){
		   		
				VehiculoDTO vehiculoDTO = (VehiculoDTO)elementoDTO;
				logger.debug("getNoSerie" + vehiculoDTO.getNoSerie());
	    		logger.debug("getPlaca" + vehiculoDTO.getPlaca());
	    		vehiculo = VehiculoTransformer.transformarVehiculo(vehiculoDTO);
	    		logger.debug("DTO getNoSerie" + vehiculo.getNoSerie());
	    		logger.debug("DTO getPlaca" + vehiculo.getPlaca());
	    		if(vehiculoDTO.getNoSerie() != null && vehiculoDTO.getNoSerie().equals("null"))
	    			vehiculo.setNoSerie(null);
	    		if(vehiculoDTO.getPlaca() != null && vehiculoDTO.getPlaca().equals("null"))
	    			vehiculo.setPlaca(null);
	    		casos = casoDAO.consultarReincidenciasDeElementos(vehiculo);
	    	}
			if(elementoDTO instanceof InvolucradoDTO){
		   		
				InvolucradoDTO involucradoDTO = (InvolucradoDTO)elementoDTO;
				involucrado  = InvolucradoTransformer.transformarInvolucrado(involucradoDTO);
				NombreDemografico loNomDem = new NombreDemografico();
				loNomDem = involucrado.getNombreDemograficos().iterator().hasNext()? involucrado.getNombreDemograficos().iterator().next():null;
				
				logger.debug("DTO getNombre" + loNomDem.getNombre());
				logger.debug("DTO getApellidoPaterno" + loNomDem.getApellidoPaterno());
	    		logger.debug("DTO getApellidoMaterno" + loNomDem.getApellidoMaterno());

	    		if(loNomDem.getNombre().equals("null"))
	    			loNomDem.setNombre(null);
	    		if(loNomDem.getApellidoPaterno().equals("null"))
	    			loNomDem.setApellidoPaterno(null);
	    		if(loNomDem.getApellidoMaterno().equals("null"))
	    			loNomDem.setApellidoMaterno(null);
	    		casos = casoDAO.consultarReincidenciasDeElementos(involucrado);
	    	}	
			casosDTO = CasoTransformer.transformarCasoBasico(casos);
		} else
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		return casosDTO;
	}
    
}
