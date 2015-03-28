/**
 * Nombre del Programa : ConsultarRegistroBitacoraServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 17 Oct 2011
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
package mx.gob.segob.nsjp.service.bitacora.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoMovimiento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.bitacora.RegistroBitacoraDAO;
import mx.gob.segob.nsjp.dao.catalogo.ValorDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.bitacora.RegistroBitacoraDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.RegistroBitacora;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.bitacora.ConsultarRegistroBitacoraService;

import org.apache.commons.lang.math.NumberUtils;
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
@Service
@Transactional
public class ConsultarRegistroBitacoraServiceImpl
        implements
            ConsultarRegistroBitacoraService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(ConsultarRegistroBitacoraServiceImpl.class);
    @Autowired
    private RegistroBitacoraDAO regBitDao;
    @Autowired
    private ValorDAO valDao;
    @Autowired
    private FuncionarioDAO funDao;
    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.bitacora.ConsultarRegistroBitacoraService#
     * consultarRegistrosByExpediente
     * (mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
     */
    @Override
    public List<RegistroBitacoraDTO> consultarRegistrosByExpediente(
            ExpedienteDTO expInput) throws NSJPNegocioException {

        if (expInput == null || expInput.getNumeroExpedienteId() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        logger.debug("expInput.getNumeroExpedienteId() :: "
                + expInput.getNumeroExpedienteId());
        final List<RegistroBitacoraDTO> resp = new ArrayList<RegistroBitacoraDTO>();
        final List<RegistroBitacora> fromBd = this.regBitDao
                .consultarByNumeroExpedienteId(expInput.getNumeroExpedienteId());
        RegistroBitacoraDTO dto = null;
        for (RegistroBitacora row : fromBd) {
            dto = new RegistroBitacoraDTO();
            dto.setRegistroBitacoraId(row.getRegistroBitacoraId());
            dto.setTipoMovimiento(new ValorDTO(row.getTipoMovimiento()
                    .getValorId(), row.getTipoMovimiento().getValor()));
            dto.setFechaInicio(row.getFechaInicio());
            dto.setConsecutivo(row.getConsecutivo());
            if (NumberUtils.isNumber(row.getNuevo())) {
                if (this.esTipoMovimiento(row,
                        TipoMovimiento.ASIGNACION_DE_EXPEDIENTE)) {
                    Funcionario fun = this.funDao.read(Long.parseLong(row
                            .getNuevo()));
                    dto.setNuevo(fun.getNombreCompleto());
                } else {
                    final Valor fromCat = this.valDao.read(Long.parseLong(row
                            .getNuevo()));
                    if (fromCat != null) {
                        dto.setNuevo(fromCat.getValor());
                    } else {
                        dto.setNuevo(row.getNuevo());
                    }
                }
            } else {
                dto.setNuevo(row.getNuevo());
            }
            resp.add(dto);
        } // for
        logger.debug("resp.size() :: " + resp.size());
        return resp;
    }
    

    @Override
    public List<EtapasExpediente> consultarEtapasPasadas(ExpedienteDTO input)
            throws NSJPNegocioException {
        List<EtapasExpediente> resp = new ArrayList<EtapasExpediente>();
        if (input == null || input.getNumeroExpedienteId() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        logger.debug("expInput.getNumeroExpedienteId() :: "
                + input.getNumeroExpedienteId());
        final List<RegistroBitacora> fromBd = this.regBitDao
                .consultarByNumeroExpedienteId(input.getNumeroExpedienteId());
        for (RegistroBitacora row : fromBd) {
            if (this.esTipoMovimiento(row,
                    TipoMovimiento.CAMBIO_DE_ETAPA_DE_EXPEDIENTE)) {
                if (NumberUtils.isNumber(row.getNuevo())) {
                    final Valor fromCat = this.valDao.read(Long.parseLong(row
                            .getNuevo()));
                    if (fromCat != null) {
                        resp.add(EtapasExpediente.getByValor(fromCat
                                .getValorId()));
                    }
                }
            }
        } // for
        logger.debug("resp.size() :: " + resp.size());
        return resp;
    }

    /**
     * Compara la enum contra el tipo de moviento
     * @param row
     * @param enumTM
     * @return
     */
    private boolean esTipoMovimiento(RegistroBitacora row, TipoMovimiento enumTM) {
        return (row.getTipoMovimiento().getValorId().longValue() == enumTM
                .getValorId().longValue());
    }
}
