/**
 * Nombre del Programa : RegistrarTranscripcionAudienciaServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Oct 2011
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
package mx.gob.segob.nsjp.service.documento.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.service.documento.GuardarDocumentoService;
import mx.gob.segob.nsjp.service.documento.RegistrarTranscripcionAudienciaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service("registrarTranscripcionAudienciaService")
@Transactional
public class RegistrarTranscripcionAudienciaServiceImpl
        implements
            RegistrarTranscripcionAudienciaService {
    @Autowired
    private GuardarDocumentoService guardarDocService;
    
    @Autowired
    private SolicitudDAO solDao;
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(RegistrarTranscripcionAudienciaServiceImpl.class);
    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.documento.RegistrarTranscripcionAudienciaService
     * #registrarTranscripcionAudiencia(java.lang.String,
     * mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO)
     */
    @Override
    public void registrarTranscripcionAudiencia(String folioSolicitud,
            DocumentoWSDTO transcripcion) throws NSJPNegocioException {
        logger.info("Inicia - registrarTranscripcionAudiencia(...)");
        Solicitud solPjo = this.solDao.consultarSolicitudPorFolio(folioSolicitud);
        
        final DocumentoDTO docDto = new DocumentoDTO();
        docDto.setExpedienteDTO(new ExpedienteDTO(solPjo.getNumeroExpediente().getExpediente().getExpedienteId()));
        docDto.setFolioDocumento(transcripcion.getFolioDocumento());
        docDto.setFormaDTO(new FormaDTO(transcripcion.getFormaId()));
        docDto.setNombreDocumento(transcripcion.getNombreDocumento());
        docDto.setNumeroFojas(transcripcion.getNumeroFojas());
        docDto.setOrigenDocumento(transcripcion.getOrigenDocumento());
        docDto.setTipoDocumentoDTO(new ValorDTO(transcripcion.getTipoDocumentoDTO()));
        docDto.setVersion(transcripcion.getVersion());
        final ArchivoDigitalDTO archDigDto = new ArchivoDigitalDTO();
        archDigDto.setContenido(transcripcion.getArchivoDigital().getContenido());
        archDigDto.setNombreArchivo(transcripcion.getArchivoDigital().getNombreArchivo());
        archDigDto.setTipoArchivo(transcripcion.getArchivoDigital().getTipoArchivo());
        docDto.setArchivoDigital(archDigDto);
        
        guardarDocService.guardarDocumentoTranscripcion(docDto, solPjo.getDocumentoId());
        logger.info("Fin - registrarTranscripcionAudiencia(...)");

    }

}
