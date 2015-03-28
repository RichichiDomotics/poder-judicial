/**
 * Nombre del Programa : PJClienteService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 9 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Cliente para conectarse a los web services de PJ
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
package mx.gob.segob.nsjp.service.infra;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;

/**
 * Cliente para conectarse a los web services de PJ.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface PJClienteService {
    
	
	
	/**
     * Se conecta a PJ para enviar la solicitud del audiencia.
     * 
	 * @param input
	 * @param expediente
	 * @param idDistrito
	 * @param idTribunal
	 * @param idClaveFuncionario
	 * @return
	 * @throws NSJPNegocioException
	 */
    SolicitudAudienciaDTO enviarSolicitudAudiencia(SolicitudAudienciaDTO input,
            ExpedienteDTO expediente, 
            Long idDistrito, Long idTribunal, Long idClaveFuncionario) throws NSJPNegocioException;

    /**
     * Se conecta a PJ y realiza la consulta de audiencia de acuerdo a un
     * estatus y un rango de fechas.
     * 
     * @author cesarAgustin
     * @param input
     * @return
     * @throws NSJPNegocioException
     */
    List<AudienciaDTO> consultarAudienciasByFechasYEstatus(AudienciaDTO input)
            throws NSJPNegocioException;

    AudienciaDTO consultarAudienciasById(AudienciaDTO input)
            throws NSJPNegocioException;
    /**
     * 
     * @param docBase
     *            <code>folioDocumento</code>
     * @param adjunto
     *            <code>nombreArchivo</code>, <code>tipoArchivo</code>,
     *            <code>contenido</code>
     * @param estatus
     *            <code>idCampo</code>
     * @param tipoOperacion
     *            <code>SeguimientoMandamientoMedidaWSDTO.TIPO_OPERACION_MANDAMIENTO,
     *            SeguimientoMandamientoMedidaWSDTO.TIPO_OPERACION_MEDIDA_CAUTELAR ,
     *            SeguimientoMandamientoMedidaWSDTO.TIPO_OPERACION_MEDIDA_ALTERNA</code>
     * @throws NSJPNegocioException
     */
    void enviarActualizacionSeguimiento(DocumentoDTO docBase,
            ArchivoDigitalDTO adjunto, ValorDTO estatus, Long tipoOperacion)
            throws NSJPNegocioException;
    /**
     * Envía la actualización del estatus y el documento generado para una solicitud de transcripción de audiencia
     * desde PJ hacia el área de donde fue solicitada la solicitud de transcripción
     * @param docBase Documento/solicitud
     * @param adjunto Archivo digital generado
     * @param estatus Estatus destino
     * @throws NSJPNegocioException
     * @author Emigdio Hernández
     */
    void envarActualizacionSeguimientoSolicitudTranscripcion(SolicitudDTO docBase,
            ArchivoDigitalDTO adjunto, ValorDTO estatus) throws NSJPNegocioException;
    
    /**
     * Se conecta a defensoria para enviar la solicitud de Transcripcion.
     * 
     * @param input
     * @return
     * @throws NSJPNegocioException
     */
    public SolicitudTranscripcionAudienciaDTO enviarSolicitudTranscripcion(
    		SolicitudTranscripcionAudienciaDTO input) throws NSJPNegocioException;

    /**
     * Se conceta al Ws de PJ para enviar el documento de incumplimiento de la Medida
     * considerando el Número de Causa para Medida Cautelar
     * y Número de Carpeta de Ejecución para Medida Alterna
     * 
     * @param documento
     * @param numeroCausa
     * @param numeroCarpetaEjecucion
     * @return
     * @throws NSJPNegocioException
     */
    Long enviarDocumentoIncumplimientoMedidaPJ(DocumentoDTO documento, String numeroCausa,
			String numeroCarpetaEjecucion) throws NSJPNegocioException; 

}
