/**
 * Nombre del Programa : ActualizaEtapaExpedienteServiceImpl.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 29 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Servicio para la actualziacion de la etapa de un expediente
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
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoMovimiento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RegistroBitacora;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.bitacora.RegistrarBitacoraService;
import mx.gob.segob.nsjp.service.expediente.ActualizarEtapaExpedienteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para la actualziacion de la etapa de un expediente
 * 
 * @version 1.0
 * @author Tattva-IT
 * 
 */

@Service
@Transactional
public class ActualizaEtapaExpedienteServiceImpl
        implements
            ActualizarEtapaExpedienteService {

    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDAO;
    @Autowired
    private RegistrarBitacoraService registrarBitacoraService;
    private static final Logger logger = Logger
            .getLogger(ActualizaEtapaExpedienteServiceImpl.class);

    @Override
    public void actualizaEtapaExpediente(ExpedienteDTO expDTO,
            EtapasExpediente etExp) throws NSJPNegocioException {
        logger.debug("SERVICIO QUE ACTUALIZA LA ETAPA DE UN EXPEDIENTE");
        if (expDTO == null || expDTO.getNumeroExpedienteId() == null)
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

        NumeroExpediente in = this.numeroExpedienteDAO.read(expDTO
                .getNumeroExpedienteId());

        logger.debug("ACTUALIZANDO LA ETAPA DEL EXPEDIENTE NUMERO ID "
                + in.getNumeroExpedienteId() + " CON LA ETAPA " + etExp);

        in.setEtapa(new Valor(etExp.getValorId()));
        this.numeroExpedienteDAO.update(in);

        RegistroBitacora regBitEta = new RegistroBitacora();

        regBitEta.setFechaInicio(new Date());
        regBitEta.setTipoMovimiento(new Valor(
                TipoMovimiento.CAMBIO_DE_ETAPA_DE_EXPEDIENTE.getValorId()));
        regBitEta.setNuevo(String.valueOf(etExp.getValorId()));
        regBitEta.setNumeroExpediente(in);
        registrarBitacoraService
                .registrarMovimientoDeExpedienteEnBitacora(regBitEta);

    }

}
