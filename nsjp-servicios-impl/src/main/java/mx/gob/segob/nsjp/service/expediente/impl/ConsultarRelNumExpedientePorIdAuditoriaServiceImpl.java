/**
 * Nombre del Programa : ConsultarDetalleExpedienteServiceImpl.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 05-jul-2011
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
package mx.gob.segob.nsjp.service.expediente.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.RelNumExpedienteAuditoriaDAO;
import mx.gob.segob.nsjp.dto.expediente.RelNumExpedienteAuditoriaDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarRelNumExpedientePorIdAuditoriaService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.RelNumExpedienteAuditoriaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author rgama
 */
@Repository
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Transactional
public class ConsultarRelNumExpedientePorIdAuditoriaServiceImpl implements
 ConsultarRelNumExpedientePorIdAuditoriaService {

    /**
     * Logger de la clase.
     */
    private final static Logger logger = Logger.getLogger(ConsultarRelNumExpedientePorIdAuditoriaServiceImpl.class);
    
    @Autowired
    private RelNumExpedienteAuditoriaDAO relNumExpedienteAuditoriaDAO;

    /**
     * {@inheritDoc}
     */
    public RelNumExpedienteAuditoriaDTO consultarRelacionPorIdAuditoria(Long idAuditoria) throws NSJPNegocioException {
        if (idAuditoria == null ) {
        	logger.info(":::idAuditoria:::" + idAuditoria);
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }   
        return RelNumExpedienteAuditoriaTransformer.transformar(relNumExpedienteAuditoriaDAO.buscarRelacionDeNumExpPorIdAuditoria(idAuditoria));
    }
}
