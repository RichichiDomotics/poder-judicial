/**
 * Nombre del Programa : ProcuraduriaClienteService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 9 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Cliente para conectarse a los web services de Procuraduria
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;

/**
 * Cliente para conectarse a los web services de Procuraduria.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface ProcuraduriaClienteService {
    /**
     * Se conecta a PJ para enviar la solicitud del audiencia.
     * 
     * @param input
     * @param noCaso
     *            Solo el Número General Caso (<code>numeroGeneralCaso</code>).
     * @return
     * @throws NSJPNegocioException
     */
    SolicitudAudienciaDTO enviarSolicitudAudiencia(SolicitudDTO input,
            CasoDTO noCaso) throws NSJPNegocioException;

    /**
     * Se conecta a PJ y realiza el registro de la solicitude la carpeta de
     * investigacion
     * 
     * @author cesarAgustin
     * @param solicitudAudienciaDTO
     * @return
     * @throws NSJPNegocioException
     */
    SolicitudDTO solicitarCopiaCarpetaDeInvestigacion(
            SolicitudDTO solicitudAudienciaDTO,Long CatDiscriminanteOrigen) throws NSJPNegocioException;
    /**
     * 
     * @param input
     * @param aviso
     * @param noCaso
     * @return
     * @throws NSJPNegocioException
     */
    AvisoDetencionDTO enviarAvisoDetencion(DetencionDTO input,
            AvisoDetencionDTO aviso, CasoDTO noCaso,Long idAgencia, String claveAgencia,Long idFuncionarioSolicitante)
            throws NSJPNegocioException;
    /**
     * 
     * @param iPHomologadoDTO
  	 * @param idAgencia Agencia a la cual se enviara el IPH
     * @return
     * @throws NSJPNegocioException
     */
    InformePolicialHomologadoDTO enviarInformePolicialHomologado(
            InformePolicialHomologadoDTO iPHomologadoDTO, Long idAgencia)
            throws NSJPNegocioException;
    /**
     * 
     * @param avisoDto
     * @return
     * @throws NSJPNegocioException
     */
    AvisoHechoDelictivoDTO enviarAvisoHechoDelictivo(
            AvisoHechoDelictivoDTO avisoDto) throws NSJPNegocioException;

}
