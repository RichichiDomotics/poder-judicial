/**
 * Nombre del Programa : DefensoriaClienteService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 9 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Cliente para conectarse a los web services de Defensoria
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

import javax.xml.datatype.DatatypeConfigurationException;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;

/**
 * Cliente para conectarse a los web services de Defensoria.
 * @author vaguirre
 * 
 */
public interface DefensoriaClienteService {

    /**
     * Se conecta a defensoria para enviar la solicitud del defensor.
     * 
     * @param input
     * @return
     * @throws NSJPNegocioException
     */
    SolicitudDefensorDTO enviarSolicitudDefensor(SolicitudDefensorDTO input)
            throws NSJPNegocioException;
    /**
     * Se conecta a defensoria para enviar el aviso de detención de un imputado.
     * 
     * @param input
     *            Obligatorio los datos de la detención y los delitos.
     * @param noCaso
     *            Solo el Número General Caso (<code>numeroGeneralCaso</code>).
     * @return
     * @throws NSJPNegocioException
     * @throws DatatypeConfigurationException 
     */
    AvisoDetencionDTO enviarAvisoDetencion(DetencionDTO input, AvisoDetencionDTO aviso,
            CasoDTO noCaso,Long idAgencia, String claveAgencia,Long idFuncionarioSolicitante) throws NSJPNegocioException;


    /**
     * Implementacion de WS para, de acuerdo a: 
     * numeroGeneralCaso. Para consultar los expedientes asociados al caso, en Procuraduria.
     * folioSolicitud: Folio de la solicitud a la cual se va a guardar la informacion del lado de Defensoria.
     * 
     * @param numeroGeneralCaso  Del caso asociado al expediente
     * @param folioSolicitud  De defensoria en donde se guradará la información solicitada del expediente de procuraduria
     * @return  expedienteDTO asociado a la consulta obtenida, incluyendo los involucrados y objetos del expediente
     * @throws NSJPNegocioException
     */
    ExpedienteDTO enviarCarpetaDeInvestigacion(
    		String numeroGeneralCaso, String folioSolicitud)
            throws NSJPNegocioException;

}

    