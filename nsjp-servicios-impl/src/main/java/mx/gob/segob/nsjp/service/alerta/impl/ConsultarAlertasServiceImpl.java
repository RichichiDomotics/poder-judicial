/**
 * 
 */
package mx.gob.segob.nsjp.service.alerta.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.enums.documento.Notificaciones;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.alarma.AlarmaDAO;
import mx.gob.segob.nsjp.dao.alerta.AlertaDAO;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO;
import mx.gob.segob.nsjp.dto.alarmas.AlertaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Alarma;
import mx.gob.segob.nsjp.model.Alerta;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.service.alarma.ConsultarAlarmaService;
import mx.gob.segob.nsjp.service.alarma.impl.transform.AlarmaTransformer;
import mx.gob.segob.nsjp.service.alarma.impl.transform.AlertaTransformer;
import mx.gob.segob.nsjp.service.alerta.ConsultarAlertasService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adrian
 * 
 */
@Service
@Transactional
public class ConsultarAlertasServiceImpl implements ConsultarAlertasService {

    public final static Logger logger = Logger
            .getLogger(ConsultarAlertasServiceImpl.class);
    @Autowired
    private AlertaDAO alertaDAO;

    @Autowired
    private NotificacionDAO notificacionDao;
    @Override
    public List<AlertaDTO> consultarAlertasXUsuario(UsuarioDTO usuario,
            EstatusAlarmaAlerta estatusAlerta) throws NSJPNegocioException {
        if (logger.isDebugEnabled()) {
            logger.debug("/**** SERVICIO PARA CONSULTAR ALERTAS POR USUARIO ****/");
        }
        if (usuario == null || usuario.getIdUsuario() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        List<Alerta> alertas = alertaDAO.consultarAlertasXUsuario(
                UsuarioTransformer.transformarDTO(usuario), estatusAlerta);

        List<AlertaDTO> alertasDTO = new ArrayList<AlertaDTO>();
        logger.debug("TOTAL DE ALERTAS ENCONTRADAS: " + alertasDTO.size());

        for (Alerta ale : alertas) {
            alertasDTO.add(AlertaTransformer.transformarAlerta(ale));
        }
        List<Notificacion> notifiaciones = notificacionDao
                .consultarNotificacionesXFuncionario(usuario
                        .getFuncionario().getClaveFuncionario(),
                        EstatusNotificacion.NO_LEIDA.getValorId());

        if (notifiaciones != null) {
            for (Notificacion noti : notifiaciones) {
                alertasDTO.add(AlertaTransformer.transformarNotificacion(noti));
            }
        }

        return alertasDTO;
    }
}
