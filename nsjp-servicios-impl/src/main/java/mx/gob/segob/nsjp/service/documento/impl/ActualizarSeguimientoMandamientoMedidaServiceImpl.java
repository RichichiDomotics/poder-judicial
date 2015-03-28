/**
 * Nombre del Programa : ActualizarSeguimientoMandamientoMedidaServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación para registrar el seguimiento
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
package mx.gob.segob.nsjp.service.documento.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.documento.MandamientoAdjuntosDAO;
import mx.gob.segob.nsjp.dao.documento.MandamientoDAO;
import mx.gob.segob.nsjp.dao.medida.MedidaAdjuntosDAO;
import mx.gob.segob.nsjp.dao.medida.MedidaDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudTranscricpionAudienciaDAO;
import mx.gob.segob.nsjp.dto.documento.SeguimientoMandamientoMedidaWSDTO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Mandamiento;
import mx.gob.segob.nsjp.model.MandamientoAdjuntos;
import mx.gob.segob.nsjp.model.MandamientoAdjuntosId;
import mx.gob.segob.nsjp.model.Medida;
import mx.gob.segob.nsjp.model.MedidaAdjuntos;
import mx.gob.segob.nsjp.model.MedidaAdjuntosId;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.ActualizarSeguimientoMandamientoMedidaService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación para registrar el seguimiento.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service("actualizarSeguimientoMandamientoMedidaService")
@Transactional
public class ActualizarSeguimientoMandamientoMedidaServiceImpl
        implements
            ActualizarSeguimientoMandamientoMedidaService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(ActualizarSeguimientoMandamientoMedidaServiceImpl.class);
    @Autowired
    private MedidaDAO medidaDao;
    @Autowired
    private MandamientoDAO mandamientoDao;
    @Autowired
    private ArchivoDigitalDAO archDigDao;
    @Autowired
    private MandamientoAdjuntosDAO mandaAdjuntoDao;
    @Autowired
    private MedidaAdjuntosDAO medidaAdjuntoDao;
    
    @Autowired
    SolicitudTranscricpionAudienciaDAO solicitudTranscripcionDAO;
    
    @Autowired
    SolicitudDAO solicitudDAO;
    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.documento.
     * ActualizarSeguimientoMandamientoMedidaService
     * #actualizarSeguimiento(mx.gob
     * .segob.nsjp.dto.documento.SeguimientoMandamientoMedidaWSDTO)
     */
    @Override
    public void actualizarSeguimiento(SeguimientoMandamientoMedidaWSDTO input)
            throws NSJPNegocioException {
        logger.debug("Actualizando :: " + input.getFolioDocumento());

        if (StringUtils.isBlank(input.getFolioDocumento())
                || input.getTipoOperacion() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }

        if (input.getTipoOperacion().equals(
                SeguimientoMandamientoMedidaWSDTO.TIPO_OPERACION_MANDAMIENTO)) {
            final Mandamiento man = this.mandamientoDao
                    .obtenerMandamientoPorFolioDoc(input.getFolioDocumento());
            if (man == null) {
                return;
            }
            if (input.getArchivoDigital() != null) {
                ArchivoDigital ad = guardarArchivoDigital(input);
                MandamientoAdjuntos ma = new MandamientoAdjuntos();
                MandamientoAdjuntosId maId = new MandamientoAdjuntosId();
                maId.setArchivoDigitalId(ad.getArchivoDigitalId());
                maId.setMandamientoId(man.getDocumentoId());
                this.mandaAdjuntoDao.create(ma);
            }

            if (input.getIdEstatus() != null) {
                man.setEstatus(new Valor(input.getIdEstatus()));
                this.mandamientoDao.update(man);
            }

        } else if(input.getTipoOperacion().equals(
                SeguimientoMandamientoMedidaWSDTO.TIPO_OPERACION_SOLICITUD_TRANSCRIPCION)){
            //Actualizar estatus y documento de solicitud de transcripción
        	final Solicitud solicitud = 
        		solicitudDAO.consultarSolicitudPorFolio(input.getFolioDocumento());
        	if(solicitud != null){
        		if(input.getIdEstatus() != null){
        			solicitud.setEstatus(new Valor(input.getIdEstatus()));
        		}
        		if(input.getArchivoDigital() != null){
        			solicitud.setArchivoDigital(guardarArchivoDigital(input));
        		}
        		solicitudDAO.saveOrUpdate(solicitud);
        	}
        }else{
        	final Medida med = this.medidaDao.obtenerMedidaPorFolioDoc(input
                    .getFolioDocumento());

            if (med == null) {
                return;
            }
            if (input.getArchivoDigital() != null) {
                ArchivoDigital ad = guardarArchivoDigital(input);
                MedidaAdjuntos ma = new MedidaAdjuntos();
                MedidaAdjuntosId maId = new MedidaAdjuntosId();
                maId.setArchivoDigitalId(ad.getArchivoDigitalId());
                maId.setMedidaId(med.getDocumentoId());
                this.medidaAdjuntoDao.create(ma);
            }

            if (input.getIdEstatus() != null) {
                med.setEstatus(new Valor(input.getIdEstatus()));
                this.medidaDao.update(med);
            }
        }
        logger.debug("Actualizacion [OK]");
    }

    /**
     * @param input
     * @return
     */
    private ArchivoDigital guardarArchivoDigital(
            SeguimientoMandamientoMedidaWSDTO input) {
        ArchivoDigital ad = new ArchivoDigital();
        ad.setContenido(input.getArchivoDigital().getContenido());
        ad.setNombreArchivo(input.getArchivoDigital().getNombreArchivo());
        ad.setTipoArchivo(input.getArchivoDigital().getTipoArchivo());
        ad.setArchivoDigitalId(this.archDigDao.create(ad));
        return ad;
    }

}
