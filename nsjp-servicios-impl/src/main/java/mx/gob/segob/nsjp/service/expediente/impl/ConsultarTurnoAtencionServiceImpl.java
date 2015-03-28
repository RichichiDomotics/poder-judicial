/**
* Nombre del Programa : ConsultarTurnoAtencionServiceImpl.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Consulta los turnos pendientes por antecion por usuario firmado
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusTurno;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.PermisoExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.TurnoDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.organizacion.OrganizacionDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Organizacion;
import mx.gob.segob.nsjp.model.Turno;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;
import mx.gob.segob.nsjp.service.expediente.ConsultarTurnoAtencionService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.TurnoTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.organizacion.impl.transform.OrganizacionTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Consulta los turnos pendientes por antecion por usuario firmado
 * @version 1.0
 * @author Tattva-IT
 *
 */

@Service
@Transactional
public class ConsultarTurnoAtencionServiceImpl implements ConsultarTurnoAtencionService 
		{

    @Autowired
    private TurnoDAO turnoDAO;
    @Autowired
    private InvolucradoDAO involucradoDAO;
    @Autowired
    private DelitoDAO delitoDAO;
    
    @Autowired
    private NumeroExpedienteDAO numExpDao ;
    @Autowired
    private OrganizacionDAO organizacionDAO;
    
    @Autowired
    private ExpedienteDAO expedienteDAO;
    @Autowired
    private PermisoExpedienteDAO permisoExpedienteDAO;

    
    private final static Logger log = Logger
            .getLogger(ConsultarTurnoAtencionServiceImpl.class);
	@Override
	public List<TurnoDTO> consultarTurnosAtendidosPorUsuario(UsuarioDTO usuarioDto, Boolean turnosDelDia)
			throws NSJPNegocioException {
		List<TurnoDTO> listTurnosDTO = new ArrayList<TurnoDTO>(); 
	
	    if (log.isDebugEnabled()) 
            log.debug("usuarioDTO :: " + usuarioDto);
	    
	    TipoTurno tTurno = null;
	    
	    if (usuarioDto.getAreaActual()!=null && usuarioDto.getAreaActual().getAreaId()!=null) {
	        tTurno = TipoTurno.getByValor(Areas.values()[usuarioDto.getAreaActual().getAreaId().intValue()]);
	    }
	    
	    /*
	    * Usado para obtener el discriminante Id
	    */
	      long discriminanteId = 0L; 
	            
		
		if (usuarioDto != null
				&& usuarioDto.getFuncionario() != null
				&& usuarioDto.getFuncionario().getDiscriminante() != null
				&& usuarioDto.getFuncionario().getDiscriminante().getCatDiscriminanteId() != null) {

			discriminanteId = usuarioDto.getFuncionario().getDiscriminante()
					.getCatDiscriminanteId();
		}

	    List<Turno> reslt = null;
	    //Se considera el Tipo de Turno, que va de acuerdo al área a la que pertenece el usuario
	    if(turnosDelDia != null && turnosDelDia == true)
	    	reslt = this.turnoDAO.obtenerTurnosAtencionPorIdUsuario(
					usuarioDto.getIdUsuario(),new Date(), tTurno,discriminanteId);
	    else
	    	reslt = this.turnoDAO.obtenerTurnosAtencionPorIdUsuario(
					usuarioDto.getIdUsuario(),null, tTurno,discriminanteId);
		for (Turno turno : reslt) {
			TurnoDTO turnoDTO = TurnoTransformer.trannsformarTurno(turno);
			
			if (turno.getExpediente()!=null) {
				List<Involucrado> involucrados = new ArrayList<Involucrado>();
				List<Involucrado> temp = involucradoDAO.obtenerInvByIdExpAndCalidad(turno.getExpediente().getExpedienteId(),Calidades.DENUNCIANTE.getValorId(), null);
				if (temp!=null && !temp.isEmpty()) {
				    involucrados.addAll(temp);
				}
                temp = involucradoDAO.obtenerInvByIdExpAndCalidad(turno.getExpediente().getExpedienteId(),Calidades.DENUNCIANTE_ORGANIZACION.getValorId(), null);
                if (temp!=null && !temp.isEmpty()) {
                    involucrados.addAll(temp);
                }
				ExpedienteDTO expedienteDTO = ExpedienteTransformer.transformarExpedienteBasico(turno.getExpediente());
				
				for (Involucrado involucrado : involucrados) {
					InvolucradoDTO involucradoDTO = InvolucradoTransformer.transformarInvolucradoBasico(involucrado);					
					
					//Si es persona Moral Consultar Organizacion
	       			if(involucradoDTO.getTipoPersona().equals(0L)) {
	    				Organizacion organizacion = organizacionDAO.obtenerOrganizacionByRelacion(involucradoDTO.getElementoId(), new Long(Relaciones.ORGANIZACION_INVOLUCRADA.ordinal()));
	    				involucradoDTO.setOrganizacionDTO(OrganizacionTransformer.transformarOrganizacionBasico(organizacion) );
	    			}
	       			expedienteDTO.addInvolucradoDTO(involucradoDTO);
				}
				
				
				List<Delito> listDelitos = delitoDAO.obtenerDelitoPorExpediente(turno.getExpediente().getExpedienteId());
				
				for (Delito delito : listDelitos) {
					if (delito.getEsPrincipal()==true) {
						DelitoDTO delitoDTO = DelitoTransfromer.transformarDelito(delito); 
						expedienteDTO.setDelitoPrincipal(delitoDTO);
					}
				}	
				log.info("NUM_EXP_ji:: "+turno.getExpediente().getExpedienteId());
				log.info("AREA_ID_jo:: "+usuarioDto.getAreaActual().getAreaId());
				NumeroExpediente noExp = this.numExpDao.obtenerNumeroExpediente(turno.getExpediente().getExpedienteId(), usuarioDto.getAreaActual().getAreaId());
				if(noExp!=null)
				{
					expedienteDTO.setNumeroExpediente(noExp.getNumeroExpediente());
					expedienteDTO.setFechaApertura(noExp.getFechaApertura());
					expedienteDTO.setNumeroExpedienteId(noExp.getNumeroExpedienteId());
				}
				else
				{
					expedienteDTO.setNumeroExpediente("");
					expedienteDTO.setFechaApertura(null);
					expedienteDTO.setNumeroExpedienteId(0L);
				}
				turnoDTO.setExpediente(expedienteDTO);
				listTurnosDTO.add(turnoDTO);
			}						
		}
	    
	    return listTurnosDTO;
	}
	
	
	
	@Override
	public TurnoDTO obtenerTurnoParaAtencion(TurnoDTO turnoDTO) throws NSJPNegocioException {
		
		Turno res;
		Calendar calTemp = Calendar.getInstance();
		
		/*
         * Usado para setear la agencia y consultar turnos para agencias de PGJ
         */
        long discriminanteId = 0L; 
        
        ConfInstitucion confInstitucion = expedienteDAO.consultarInsitucionActual();
        if(confInstitucion.getConfInstitucionId().equals(Instituciones.PGJ.getValorId()) || confInstitucion.getConfInstitucionId().equals(Instituciones.PJ.getValorId()) ){
			if (turnoDTO.getUsuario() != null
					&& turnoDTO.getUsuario().getFuncionario() != null
					&& turnoDTO.getUsuario().getFuncionario().getDiscriminante() != null
					&& turnoDTO.getUsuario().getFuncionario().getDiscriminante()
							.getCatDiscriminanteId() != null) {
				
				discriminanteId = turnoDTO.getUsuario().getFuncionario().getDiscriminante().getCatDiscriminanteId();	
			    //turno.setAgencia(new Valor(turnoDTO.getUsuario().getFuncionario().getDiscriminante().getIdCampo(),turnoDTO.getUsuario().getFuncionario().getDiscriminante().getValor()));
			}
        }
		
		if (turnoDTO.getTipoTurno().equals(TipoTurno.PENAL)) {			
			res=this.turnoDAO.obtenerProximoTurnoAtencion(TipoTurno.PENAL.toString(), true, calTemp.getTime(), discriminanteId);					
			if (res==null)
				res=this.turnoDAO.obtenerProximoTurnoAtencion(TipoTurno.PENAL.toString(), false, calTemp.getTime(), discriminanteId);
		} else 
			res=this.turnoDAO.obtenerProximoTurnoAtencion(TipoTurno.ADMINISTRATIVO.toString(), false, calTemp.getTime(), discriminanteId);
		
		if(turnoDTO.getTipoTurno().equals(TipoTurno.ASESORIA_LEGAL)){
			res=this.turnoDAO.obtenerProximoTurnoAtencion(TipoTurno.ASESORIA_LEGAL.toString(), false, calTemp.getTime(), discriminanteId);			
		}
		
		if(turnoDTO.getTipoTurno().equals(TipoTurno.SOLICITUD_CIUDADANA)){
			res=this.turnoDAO.obtenerProximoTurnoAtencion(TipoTurno.SOLICITUD_CIUDADANA.toString(), false, calTemp.getTime(), discriminanteId);			
		}
				
		log.debug("Obteniendo Turno Proximo :: "+res);
		if (res!=null) {
			res.setEstatus(new Valor(EstatusTurno.ASIGNADO.getValorId()));
			turnoDAO.update(res);
			
			return TurnoTransformer.trannsformarTurno(res);
		}else {
			return null;
		}
	}


	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.expediente.ConsultarTurnoAtencionService#obtenerTurnosPendientesPorTipo(mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno)
	 */
	@Override
	public List<TurnoDTO> obtenerTurnosPendientesPorTipo(TipoTurno tipo) {
		List<Turno> turnosDB = turnoDAO.obtenerTurnosPendientesPorTipo(tipo);
		List<TurnoDTO> turnos = new ArrayList<TurnoDTO>();
		for(Turno turno:turnosDB){
			turnos.add(TurnoTransformer.trannsformarTurno(turno));
		}
		return turnos;
	}

	@Override
	public List<TurnoDTO> obtenerTurnosPorFiltro(TurnoDTO turnoFiltro) throws NSJPNegocioException{
		if(turnoFiltro==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		Turno turnoF = TurnoTransformer.transformarTurno(turnoFiltro); 
			
		List<Turno> turnosDB = turnoDAO.obtenerTurnosPorFiltro(turnoF);
		List<TurnoDTO> turnos = new ArrayList<TurnoDTO>();
		for(Turno turno:turnosDB){
			turnos.add(TurnoTransformer.trannsformarTurno(turno));
		}
		return turnos;
	}
	
	
	public List<TurnoDTO> consultarTurnosConPermisosFuncionario(UsuarioDTO usuarioDto)
			throws NSJPNegocioException {
		
		if(usuarioDto==null || usuarioDto.getFuncionario()==null || 
				usuarioDto.getFuncionario().getClaveFuncionario()==null ||
				usuarioDto.getFuncionario().getClaveFuncionario() < 0 ||
				usuarioDto.getFuncionario().getDiscriminante() ==null || 
				usuarioDto.getFuncionario().getDiscriminante().getCatDiscriminanteId()==null ||
				usuarioDto.getFuncionario().getDiscriminante().getCatDiscriminanteId() < 0
				)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			
	    if (log.isDebugEnabled()) 
            log.debug("usuarioDTO :: " + usuarioDto);
	    
	    log.info(" ClaveFuncionario:"+ usuarioDto.getFuncionario().getClaveFuncionario() );
	    log.info(" IdDiscriminante:"+ usuarioDto.getFuncionario().getDiscriminante().getCatDiscriminanteId() );
	    

	    //Se obtienen los expedientes a los que se tiene permiso el usuario, considerando la fecha de vencimiento.
	    List<NumeroExpediente> expedientesFuncionario = permisoExpedienteDAO
				.consultarExpedientescConPermisoFuncionario(
						usuarioDto.getFuncionario().getClaveFuncionario(),
						usuarioDto.getFuncionario().getDiscriminante().getCatDiscriminanteId());
	    
	    List<TurnoDTO> listTurnosDTO = new ArrayList<TurnoDTO>();
	    for (NumeroExpediente numeroExpediente : expedientesFuncionario) {
		
			TurnoDTO turnoDTO =  new TurnoDTO(); //TurnoTransformer.trannsformarTurno(turno);
			
			if (numeroExpediente.getExpediente()!=null && numeroExpediente.getExpediente().getExpedienteId()!=null ) {
				List<Involucrado> involucrados = new ArrayList<Involucrado>();
				List<Involucrado> temp = involucradoDAO.obtenerInvByIdExpAndCalidad(numeroExpediente.getExpediente().getExpedienteId(),Calidades.DENUNCIANTE.getValorId(), null);
				if (temp!=null && !temp.isEmpty()) {
				    involucrados.addAll(temp);
				}
                temp = involucradoDAO.obtenerInvByIdExpAndCalidad(numeroExpediente.getExpediente().getExpedienteId(),Calidades.DENUNCIANTE_ORGANIZACION.getValorId(), null);
                if (temp!=null && !temp.isEmpty()) {
                    involucrados.addAll(temp);
                }
				ExpedienteDTO expedienteDTO = ExpedienteTransformer.transformarExpedienteBasico(numeroExpediente.getExpediente());
				
				for (Involucrado involucrado : involucrados) {
					InvolucradoDTO involucradoDTO = InvolucradoTransformer.transformarInvolucradoBasico(involucrado);					
					
					//Si es persona Moral Consultar Organizacion
	       			if(involucradoDTO.getTipoPersona().equals(0L)) {
	    				Organizacion organizacion = organizacionDAO.obtenerOrganizacionByRelacion(involucradoDTO.getElementoId(), new Long(Relaciones.ORGANIZACION_INVOLUCRADA.ordinal()));
	    				involucradoDTO.setOrganizacionDTO(OrganizacionTransformer.transformarOrganizacionBasico(organizacion) );
	    			}
	       			expedienteDTO.addInvolucradoDTO(involucradoDTO);
				}
				
				
				List<Delito> listDelitos = delitoDAO.obtenerDelitoPorExpediente(numeroExpediente.getExpediente().getExpedienteId());
				
				for (Delito delito : listDelitos) {
					if (delito.getEsPrincipal()==true) {
						DelitoDTO delitoDTO = DelitoTransfromer.transformarDelito(delito); 
						expedienteDTO.setDelitoPrincipal(delitoDTO);
					}
				}	
				log.info("NUM_EXP_ji:: "+numeroExpediente.getExpediente().getExpedienteId());

				expedienteDTO.setNumeroExpediente(numeroExpediente.getNumeroExpediente());
				expedienteDTO.setFechaApertura(numeroExpediente.getFechaApertura());
				expedienteDTO.setNumeroExpedienteId(numeroExpediente.getNumeroExpedienteId());
				
				if(numeroExpediente.getExpediente().getOrigen()!=null && numeroExpediente.getExpediente().getOrigen().getValorId()!=null)
					expedienteDTO.setOrigen(new ValorDTO(numeroExpediente.getExpediente().getOrigen().getValorId()));
				
				//Si se trata de expedientes de procuraduria
				ConfInstitucion confInstitucion = this.expedienteDAO.consultarInsitucionActual();
	        	if(confInstitucion.getConfInstitucionId().equals(Instituciones.PGJ.getValorId()) ){
	        		if(numeroExpediente.getExpediente() != null && numeroExpediente.getExpediente().getEstatus() != null)
	      			expedienteDTO.setEstatusExpedientePadre(new ValorDTO(numeroExpediente.getExpediente().getEstatus().getValorId(),numeroExpediente.getExpediente().getEstatus().getValor()));
	        	}
				
				turnoDTO.setExpediente(expedienteDTO);
				listTurnosDTO.add(turnoDTO);
			}						
		}
	    return listTurnosDTO;
	}
}
