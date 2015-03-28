/**
 * Nombre del Programa : EnviarAvisoDetencionServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Aug 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Servicio par aenviar el aviso de detención.
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

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.avisodetencion.AvisoDetencionDAO;
import mx.gob.segob.nsjp.dao.documento.AvisoDesignacionDAO;
import mx.gob.segob.nsjp.dao.institucion.ConfInstitucionDAO;
import mx.gob.segob.nsjp.dao.involucrado.DetencionDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.model.AvisoDetencion;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.avisodetencion.EnviarAvisoDetencionService;
import mx.gob.segob.nsjp.service.infra.DefensoriaClienteService;
import mx.gob.segob.nsjp.service.infra.ProcuraduriaClienteService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio par aenviar el aviso de detención.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional
public class EnviarAvisoDetencionServiceImpl
        implements
            EnviarAvisoDetencionService {

    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(EnviarAvisoDetencionServiceImpl.class);

    
    @Autowired
    private ConfInstitucionDAO confInsDao;
    
    @Autowired
    private DetencionDAO detencionDao;
    
    @Autowired
    private InvolucradoDAO involucradoDAO;

    @Autowired
    private DefensoriaClienteService clienteWs;
    
    @Autowired
    private ProcuraduriaClienteService clienteProcu;
    
    @Autowired
    private GenerarFolioSolicitudService generarFolioSolicitudService;
    @Autowired
    private AvisoDetencionDAO avisoDao;
    
    @Override
    public void enviarAvisoDetencion(DetencionDTO input,Long idAgencia, String claveAgencia, Long idFuncionarioSolicitante)
            throws NSJPNegocioException {
        logger.info("Inicia - enviarAvisoDetencion(...)");
        logger.debug("input.getFechaRecepcionDetencion() :: " + input.getFechaRecepcionDetencion());

        if (input == null || input.getInvolucradoDTO() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }

        Detencion detencion = new Detencion();
        detencion.setFechaInicioDetencion(input.getFechaInicioDetencion());
        detencion.setFechaFinDetencion(input.getFechaFinDetencion());
        detencion.setFechaRecepcionDetencion(input.getFechaRecepcionDetencion());
        Involucrado inv = involucradoDAO.read(input.getInvolucradoDTO().getElementoId());
        input.setInvolucradoDTO(InvolucradoTransformer.transformarInvolucrado(inv));
        detencion.setInvolucrado(inv);
	        AvisoDetencion aviso = new AvisoDetencion();
	        if (input.getDetencionId()!=null) {
	        	aviso.setDetencion(new Detencion(input.getDetencionId()));
	        }
	        aviso.setNombreDocumento("Aviso detencion de"
	                + input.getInvolucradoDTO().getNombreCompleto());
	        aviso.setDetenido(input.getInvolucradoDTO().getNombreCompleto());
	        aviso.setFechaCreacion(new Date());
	        // TODO VAP definir forma y tipo de documento
	        aviso.setForma(new Forma(Formas.AUDIENCIA.getValorId()));
	        aviso.setTipoDocumento(new Valor(TipoDocumento.ACUSE
	                .getValorId()));
	        aviso.setFolioNotificacion(generarFolioSolicitudService
	                .generarFolioNotificacion());
	        ConfInstitucion confI = confInsDao.consultarInsitucionActual();
	        aviso.setConfInstitucion(confI);
	        aviso.setConsecutivoNotificacion("1");
	        aviso.setEstatus(new Valor(EstatusNotificacion.EN_PROCESO.getValorId()));
	        this.avisoDao.create(aviso);
        detencion.setAvisoDetencion(aviso);

        AvisoDetencionDTO avisoDto = new AvisoDetencionDTO();
        avisoDto.setFolioNotificacion(aviso.getFolioNotificacion());
        CasoDTO caso = input.getInvolucradoDTO().getExpedienteDTO().getCasoDTO();
        avisoDto.setNumeroCasoAsociado(caso.getNumeroGeneralCaso());
        this.transmitir(input, avisoDto, caso,idAgencia,claveAgencia,idFuncionarioSolicitante);
        if (input.getDetencionId()==null) {
        	this.detencionDao.create(detencion);
        }
        logger.debug("guardado local [OK]");
        logger.debug("transmision [OK]");
    }
    

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    private void transmitir(DetencionDTO input, AvisoDetencionDTO avisoDto,
            CasoDTO caso,Long idAgencia, String claveAgencia,Long idFuncionarioSolicitante) throws NSJPNegocioException {
        logger.debug("Inicia transimision...");
        ConfInstitucion confI = confInsDao.consultarInsitucionActual();
        
        if(confI.getConfInstitucionId()==Instituciones.SSP.getValorId())
        {	
        	clienteProcu.enviarAvisoDetencion(input, avisoDto, caso,idAgencia,claveAgencia,idFuncionarioSolicitante);
        }
        
        clienteWs.enviarAvisoDetencion(input, avisoDto, caso,idAgencia,claveAgencia, idFuncionarioSolicitante);
        logger.debug("transmision [OK]");
    }
}
