/**
 * Nombre del Programa : ConsultarMovimientosDeObjetosAlmacenServiceImpl.java
 * Autor                            : GustavoBP
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30/08/2011
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
package mx.gob.segob.nsjp.service.almacen.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.cadenadecustodia.CadenaDeCustodiaDAO;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.service.almacen.ConsultarMovimientosDeObjetosAlmacenService;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para consultar los Movimientos de Objetos del
 * Almacen Por medio de la evidencias asociadas a la Cadena Custodia, que a su
 * vez, esta relacionada a un Expediente (Número de Expediente).
 * 
 * @version 1.0
 * @author GustavoBP
 */
@Service
@Transactional
public class ConsultarMovimientosDeObjetosAlmacenServiceImpl
        implements
            ConsultarMovimientosDeObjetosAlmacenService {

    @Autowired
    private BuscarExpedienteService buscarExpedienteService;
    @Autowired
    private CadenaDeCustodiaDAO cadenaDeCustodiaDAO;
    @Autowired
    private EvidenciaDAO evidenciaDAO;

    private final static Logger logger = Logger
            .getLogger(ConsultarMovimientosDeObjetosAlmacenServiceImpl.class);

    public List<EvidenciaDTO> consultarEvidenciasAsociadasCadenaCustodiaPorNumeroExpediente(
            String numeroExpediente) throws NSJPNegocioException {
        List<EvidenciaDTO> evidenciasDTO = new ArrayList<EvidenciaDTO>();

        logger.info(" Servicio 'consultarEvidenciasAsociadasCadenaCustodiaPorNumeroExpediente' - numeroExpediente: "
                + numeroExpediente);

        if (numeroExpediente == null || numeroExpediente.trim().isEmpty())
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

        // 1 consultar el Id del expediente asociado al numero de Expediente
        ExpedienteDTO expedienteDTO = buscarExpedienteService
                .obtenerExpedientePorNumeroExpediente(numeroExpediente);

        if (expedienteDTO == null || expedienteDTO.getExpedienteId() == null)
            throw new NSJPNegocioException(
                    CodigoError.INFORMACION_PARAMETROS_ERRONEA);

        logger.info(" *ExpedienteId:" + expedienteDTO.getExpedienteId());

        // 2 consultar las cadenas de custodia asociadas al expediente
        List<CadenaDeCustodia> cadenasDeCustodia = cadenaDeCustodiaDAO
                .consultarCadenaCustodiaXExpediente(new Expediente(
                        expedienteDTO.getExpedienteId()));
        logger.info(" Cadenas de Custodia Asociadas al espediente ("
                + expedienteDTO.getExpedienteId() + ") :"
                + cadenasDeCustodia.size());

        // 3 consultar las evidencias asociadas a las cadenas de custodia
        List<Evidencia> evidencias = new ArrayList<Evidencia>();
        for (CadenaDeCustodia cadenaDeCustodia : cadenasDeCustodia) {
            List<Evidencia> evidenciaTemp = evidenciaDAO
                    .consultarEvidenciasXCadenaCustodia(cadenaDeCustodia);
            logger.info(" Evidencias Asociadas a la CDC("
                    + cadenaDeCustodia.getCadenaDeCustodiaId() + ") :"
                    + evidenciaTemp.size());

            evidencias.addAll(evidenciaTemp);
        }
        logger.info(" *Evidencias Asociadas : " + evidencias.size());

        // 4 parsear la descripcion del objeto

        // 5 hacer el tranforme de las Evidencias y Objetos
        for (Evidencia evidencia : evidencias) {
            logger.info(" Evidencias A Transformar : "
                    + evidencia.getEvidenciaId());
            EvidenciaDTO evidenciaDTO = EvidenciaTransformer
                    .transformarEvidencia(evidencia, true);

            evidenciasDTO.add(evidenciaDTO);
        }

        return evidenciasDTO;
    }

}
