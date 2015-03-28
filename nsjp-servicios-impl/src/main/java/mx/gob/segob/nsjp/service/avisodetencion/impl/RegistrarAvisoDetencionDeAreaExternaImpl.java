/**
* Nombre del Programa : RegistrarAvisoDetencionDeAreaExternaImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01/07/2011
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
package mx.gob.segob.nsjp.service.avisodetencion.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.involucrado.PersonalidadJuridica;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.avisodetencion.AvisoDetencionDAO;
import mx.gob.segob.nsjp.dao.avisodetencion.AvisoDetencionDelitoDAO;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.DetencionDAO;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionWSDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoWSDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.model.AvisoDetencion;
import mx.gob.segob.nsjp.model.AvisoDetencionDelito;
import mx.gob.segob.nsjp.model.AvisoDetencionDelitoId;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.CatDelito;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.avisodetencion.RegistrarAvisoDetencionDeAreaExterna;
import mx.gob.segob.nsjp.service.expediente.AdministrarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedientePorCasoImputadoService;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.expediente.ClonarExpedienteService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para el registro de un aviso de detención
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
@Service("registrarAvisoDetencionDeAreaExterna")
@Transactional
public class RegistrarAvisoDetencionDeAreaExternaImpl implements
		RegistrarAvisoDetencionDeAreaExterna {
	
	private final static Logger logger = Logger.getLogger(RegistrarAvisoDetencionDeAreaExternaImpl.class);
	
	@Autowired
	private AdministrarNumeroExpedienteService administrarNumeroExpedienteService;
	@Autowired
	private AvisoDetencionDelitoDAO avisoDetencionDelitoDAO;
	@Autowired
	private AvisoDetencionDAO avisoDetencionDAO;
	@Autowired
	private DetencionDAO detencionDAO;
	@Autowired
	private DelitoDAO delitoDAO;
	@Autowired
	private BuscarExpedientePorCasoImputadoService buscarExpedientePorCasoImputadoService;
	@Autowired
	private CasoDAO casoDAO;
	@Autowired
	private ExpedienteDAO expedienteDAO;
	@Autowired
	private IngresarIndividuoService ingresarIndividuoService;
    @Autowired
    private BuscarExpedienteService buscarExpService;
    @Autowired
    private ClonarExpedienteService clonarExpService;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.avisodetencion.RegistrarAvisoDetencionDeAreaExterna#registrarAvisoDetencion(mx.gob.segob.nsjp.dto.documento.AvisoDetencionWSDTO)
	 */
	@Override
	public AvisoDetencionWSDTO registrarAvisoDetencion(AvisoDetencionWSDTO aviso,Long idAgencia, String claveAgencia,Long idFuncionarioSolicitante) throws NSJPNegocioException{
		logger.debug("Recibiendo :: " + aviso);
		if(aviso != null){
			
			InvolucradoDTO imputado = new InvolucradoDTO();
			imputado.setFolioElemento(aviso.getDetencion().getFolioElemento()); 
			NombreDemograficoDTO ndDTO = new NombreDemograficoDTO();
			ndDTO.setNombre(aviso.getDetencion().getNombreDetenido());
			ndDTO.setApellidoPaterno(aviso.getDetencion().getApellidoPaternoDetenido());
			ndDTO.setApellidoMaterno(aviso.getDetencion().getApellidoMaternoDetenido());
			imputado.getNombresDemograficoDTO().add(ndDTO);
			CalidadDTO calidad = new CalidadDTO();
			calidad.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
			imputado.setCalidadDTO(calidad);
            // por defecto es detenido
			imputado.setEsDetenido(true);
			// por defecto es fisica   
			imputado.setTipoPersona(new Long(PersonalidadJuridica.FISICA.ordinal())); 
			
			ExpedienteDTO expedienteDTO = buscarExpedientePorCasoImputadoService.buscarExpedientePorCasoImputado(aviso.getNumeroCasoAsociado(), imputado);
//			TODO VAP si el expediente ya tiene un aviso clonarlo
            if (expedienteDTO != null) {

              List<AvisoDetencion> avisosExistentes =  avisoDetencionDAO.consultarAvisosDetencionPorExpediente(expedienteDTO.getExpedienteId());
                
                if (avisosExistentes != null && !avisosExistentes.isEmpty()) {
                    expedienteDTO = clonarExpService.clonarExpediente(expedienteDTO.getExpedienteId());
                }

            } else {
                Caso caso = casoDAO.obtenerCasoByNumeroGeneral(aviso
                        .getNumeroCasoAsociado());
                Expediente expediente = new Expediente();
                if (caso != null) {
                    expediente.setCaso(caso);
                }
                expediente.setFechaCreacion(Calendar.getInstance().getTime());
                expedienteDTO = new ExpedienteDTO();
                expediente.setPertenceConfInst(new ConfInstitucion(
                        Instituciones.DEF.getValorId()));
                expediente.setEstatus(new Valor(EstatusExpediente.ABIERTO
                        .getValorId()));
                expedienteDTO.setExpedienteId(expedienteDAO.create(expediente));

            }
			imputado.setExpedienteDTO(expedienteDTO);
			imputado.setElementoId(ingresarIndividuoService.ingresarIndividuoInterInstitucion(imputado,false));
			AvisoDetencion avisoInterno = new AvisoDetencion();
			avisoInterno.setConsecutivoNotificacion("1");
			avisoInterno.setDetenido(aviso.getDetencion().getDetenido());
			avisoInterno.setLugar(aviso.getDetencion().getLugarDetencion());
			avisoInterno.setMotivo(aviso.getDetencion().getMotivoDetencion());			
			avisoInterno.setEstatus(new Valor(EstatusNotificacion.NO_ATENDIDA.getValorId()));
			avisoInterno.setFechaCreacion(new Date());
			avisoInterno.setConfInstitucion(new ConfInstitucion(aviso.getConfInstitucionId()));
			avisoInterno.setForma(new Forma(1L));
			avisoInterno.setNombreDocumento("Aviso detención de " + aviso.getDetencion().getDetenido());
			avisoInterno.setTipoDocumento(new Valor(1L));
			avisoInterno.setFolioNotificacion(aviso.getFolioNotificacion());
			avisoInterno.setNumeroCasoAsociado(aviso.getNumeroCasoAsociado());
			avisoInterno.setConfInstitucion(new ConfInstitucion(aviso.getConfInstitucionId()));
			avisoInterno.setExpediente(new Expediente(expedienteDTO.getExpedienteId()));
			avisoInterno.setDocumentoId(avisoDetencionDAO.create(avisoInterno));
			
			//Crear la detención
				Detencion detencion = new Detencion();
				detencion.setAvisoDetencion(avisoInterno);
				detencion.setFechaInicioDetencion(aviso.getDetencion().getFechaInicioDetencion());
				detencion.setFechaFinDetencion(aviso.getDetencion().getFechaFinDetencion());
				detencion.setFechaRecepcionDetencion(aviso.getDetencion().getFechaRecepcionDetecion());
				detencion.setLugarDetencionProvisional(detencion.getLugarDetencionProvisional());
				detencion.setMotivoDetencion(aviso.getDetencion().getMotivoDetencion());
				detencion.setObservaciones(aviso.getDetencion().getObservaciones());
				detencion.setInvolucrado(InvolucradoTransformer.transformarInvolucrado(imputado));
				detencion.setDetencionId(detencionDAO.create(detencion));
				
				//Registrar Delitos de la solicitud defensor
	            AvisoDetencionDelito avisoDelitoDelito = null;
	            AvisoDetencionDelitoId id = null;
	            Delito del = null;
	            if (aviso.getDelitos() != null) {
	                for (DelitoWSDTO delito : aviso.getDelitos()) {
	                    avisoDelitoDelito = new AvisoDetencionDelito();
	                    avisoDelitoDelito.setAvisoDetencion(avisoInterno);
		                    del = new Delito();
		                    del.setEsPrincipal(delito.isEsPrincipal());
		                    del.setCatDelito(new CatDelito(delito.getIdCatDelito()));
		                    del.setEsProbable(delito.isEsProbable());
		                    del.setDelitoId(delitoDAO.create(del));
		                avisoDelitoDelito.setDelito(del);
		                    id = new AvisoDetencionDelitoId();
				            id.setAvisoDetencionId(avisoInterno.getDocumentoId());
			                id.setDelitoId(del.getDelitoId());
		                avisoDelitoDelito.setId(id);
			            avisoDetencionDelitoDAO.create(avisoDelitoDelito);
	                    avisoInterno.getAvisoDetencionDelitos().add(avisoDelitoDelito);
	                }
	            }
            avisoInterno.setDetencion(detencion);
			
			//Permite agregar el id de la agencia y su clave
			avisoInterno.setCatDiscriminanteOrigen(idAgencia);
			avisoInterno.setClaveDiscriminanteOrigen(claveAgencia);
			
			avisoInterno.setIdFuncionarioSolicitante(idFuncionarioSolicitante);
            
			avisoDetencionDAO.saveOrUpdate(avisoInterno);
			aviso.setAvisoDetencionId(avisoInterno.getDocumentoId());
		}
		
		return aviso;
	}

}
