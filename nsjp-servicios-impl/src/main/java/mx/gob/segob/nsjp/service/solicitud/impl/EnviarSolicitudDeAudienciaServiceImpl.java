/**
 * Nombre del Programa : EnviarSolicitudDeAudienciaServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 14-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.solicitud.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.organizacion.OrganizacionDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Organizacion;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.infra.PJClienteService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.organizacion.impl.transform.OrganizacionTransformer;
import mx.gob.segob.nsjp.service.solicitud.EnviarSolicitudDeAudienciaService;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class EnviarSolicitudDeAudienciaServiceImpl implements
        EnviarSolicitudDeAudienciaService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(EnviarSolicitudDeAudienciaServiceImpl.class);

    @Autowired
    private PJClienteService clientePJWebService;

    @Autowired
    private RegistrarSolicitudService registrarSolicitudService;

    @Autowired
    private ExpedienteDAO expDao;
    @Autowired
    private BuscarExpedienteService expedienteService;
    @Autowired
    private NumeroExpedienteDAO numExoDao;
    
    @Override
    @Transactional
    public SolicitudAudienciaDTO enviarSolicitudDeAudiencia(SolicitudAudienciaDTO
            solicitudAudienciaDto, ExpedienteDTO expedienteInput,
            Long idDistrito, Long idTribunal, Long idClaveFuncionario ) throws NSJPNegocioException {
        logger.info("Inicia - enviarSolicitudDeAudiencia(...)");
    	if(solicitudAudienciaDto==null || expedienteInput==null || idDistrito==null || idTribunal==null || idClaveFuncionario==null )
    		throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
    	
    	 Expediente exp = this.numExoDao.read(expedienteInput.getNumeroExpedienteId()).getExpediente();

         ExpedienteDTO expParam = ExpedienteTransformer
                 .transformarExpedienteBasico(exp);
    	logger.debug("Expediente localizado");

    	expParam.setInvolucradosSolicitados(true);
    	expParam.setDomicliosInvolucradoSolicitados(true);
    	expParam.setObjetosSolicitados(true);
    	
    	final ExpedienteDTO expComplete = this.expedienteService.obtenerExpediente(expParam);
    	expComplete.setUsuario(expedienteInput.getUsuario());
    	solicitudAudienciaDto.setExpedienteDTO(expedienteInput);
        registrarSolicitudService.registrarSolicitud(solicitudAudienciaDto);
        logger.info(" ENVIAR LA SOLICITUD: " + 
        			" idDistrito:" + idDistrito + 
        			" idTribunal:" +idTribunal + 
        			" idClaveFuncionario:" + idClaveFuncionario);
        SolicitudAudienciaDTO solicitudAudiencia = clientePJWebService.
                enviarSolicitudAudiencia(solicitudAudienciaDto, expComplete, idDistrito, idTribunal, idClaveFuncionario);
        
        logger.info("Fin - enviarSolicitudDeAudiencia(...)");
        return solicitudAudiencia;
    }

   
}
