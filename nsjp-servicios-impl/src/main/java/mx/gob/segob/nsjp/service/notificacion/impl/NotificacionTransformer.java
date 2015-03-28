/**
 * Nombre del Programa : NotificacionTransformer.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 19-jul-2011
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
package mx.gob.segob.nsjp.service.notificacion.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import mx.gob.segob.nsjp.dto.documento.IntentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.model.Intento;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Realiza las funciones de conversion entre Notificacion y NotificacionDTO.
 * @version 1.0
 * @author Jacob Lobaco
 */
public class NotificacionTransformer {

    /**
     * Transforma un Notificacion en un NotificacionDTO.
     * @param notificacion Un Notificacion basico a tranformar.
     * @return Un NotificacionDTO.
     */
    public static NotificacionDTO transformarNotificacion(Notificacion notificacion){
        NotificacionDTO notificacionDTO = new NotificacionDTO();
        notificacionDTO.setDocumentoId(notificacion.getDocumentoId());
        notificacionDTO.setConsecutivoNotificacion(notificacion.getConsecutivoNotificacion());
//        notificacionDTO.setDireccionCitado(notificacion.get);
        notificacionDTO.setDomicilio(notificacion.getDomicilio());
        notificacionDTO.setFechaCitado(notificacion.getFechaCitado());
//        notificacionDTO.setFechaRecepcion(notificacion.getfec);
        Set<Intento> intentos = notificacion.getIntentos();
        if (intentos != null) {
            List<IntentoDTO> intentosDto = new LinkedList<IntentoDTO>();
            for (Intento intento : intentos) {
                IntentoDTO intentoDto = new IntentoDTO();
                intentoDto.setConsecutivoIntento(intento.getConsecutivoIntento());
                intentoDto.setFechaIntento(intento.getFechaIntento());
                intentoDto.setIntentoId(intento.getIntentoId());
//                intentoDto.setUsuario(intento.get);
                intentosDto.add(intentoDto);
            }
            notificacionDTO.setIntentos(intentosDto);
        }
        notificacionDTO.setLugar(notificacion.getLugar());
        notificacionDTO.setLugarCitado(notificacion.getLugarCitado());
        notificacionDTO.setMotivo(notificacion.getMotivo());
        notificacionDTO.setPenalidades(notificacion.getPenalidades());
        notificacionDTO.setPersonaAutoriza(notificacion.getPersonaAutoriza());
        notificacionDTO.setPuestoAutoriza(notificacion.getPersonaAutoriza());
//        notificacionDTO.setTipo(Notificaciones.valueOf(notificacion.get));
//        notificacionDTO.set
        return notificacionDTO;
    }

    /**
     * Transforma un NotificacionDTO en un Notificacion basico.
     * @param notificacionDTO El DTO a transformar.
     * @return Un objeto de tipo Notificacion
     */
    public static Notificacion transformarNotificacion(NotificacionDTO notificacionDTO){
        Notificacion notificacion = new Notificacion();
        notificacion.setConsecutivoNotificacion(notificacionDTO.getConsecutivoNotificacion());
        notificacion.setDomicilio(notificacionDTO.getDomicilio());
        notificacion.setFechaCitado(notificacionDTO.getFechaCitado());
        List<IntentoDTO> intentosDto = notificacionDTO.getIntentos();
        if (intentosDto != null) {
            Set<Intento> intentos = new HashSet<Intento>();
            for (IntentoDTO intentoDTO : intentosDto) {
                Intento intento = new Intento();
//                intento.setConsecutivoIntento(intentoDTO.getIntentoId());
                intento.setFechaIntento(intentoDTO.getFechaIntento());
                intento.setIntentoId(intentoDTO.getIntentoId());
                intentos.add(intento);
            }
            notificacion.setIntentos(intentos);
        }
//        notificacion.setInvolucradoAudiencia(notificacionDTO.getin);
        notificacion.setLugar(notificacionDTO.getLugar());
        notificacion.setLugarCitado(notificacionDTO.getLugarCitado());
        notificacion.setMotivo(notificacionDTO.getMotivo());
        notificacion.setPenalidades(notificacionDTO.getPenalidades());
        notificacion.setPersonaAutoriza(notificacionDTO.getPersonaAutoriza());
        notificacion.setPuestoAutoriza(notificacionDTO.getPuestoAutoriza());
        
        notificacion.setFolioNotificacion(notificacionDTO.getFolioNotificacion());
        if(notificacionDTO.getEstatus()!=null)
        notificacion.setEstatus(new Valor(notificacionDTO.getEstatus().getIdCampo()));
        return notificacion;
    }
}
