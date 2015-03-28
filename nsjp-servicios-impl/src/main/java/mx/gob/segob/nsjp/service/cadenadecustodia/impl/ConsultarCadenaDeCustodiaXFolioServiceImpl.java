/**
 * Nombre del Programa : ConsultarCadenaDeCustodiaXFolioServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-jun-2011
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
package mx.gob.segob.nsjp.service.cadenadecustodia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.cadenadecustodia.CadenaDeCustodiaDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.service.cadenadecustodia.ConsultarCadenaDeCustodiaXFolioService;
import mx.gob.segob.nsjp.service.cadenadecustodia.impl.transform.CadenaDeCustodiaTransformer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jacob Lobaco
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class ConsultarCadenaDeCustodiaXFolioServiceImpl implements ConsultarCadenaDeCustodiaXFolioService{

    @Autowired
    private CadenaDeCustodiaDAO cadenaDeCustodiaDAO;

    private final static Logger logger = Logger
            .getLogger(ConsultarCadenaDeCustodiaXFolioServiceImpl.class);

    /**
     * Operación que realiza la funcionalidad de consultar la cadena de
     * custodia asociada al folio
     * @param folio El folio de la cadena de custodia.
     * @return
     * - Número de la evidencia
     * - Información de la evidencia
     * - Último eslabón asociado
     * - Número de eslabón
     * - Tipo de eslabón
     * - Almacén
     */
    @Override
    public CadenaDeCustodiaDTO consultarCadenaDeCustodiaXFolio(String folio) {
        CadenaDeCustodia cadenaDeCustodia = cadenaDeCustodiaDAO.consultarCadenaDeCustodiaXFolio(folio);
        return cadenaDeCustodia == null ? null :
            CadenaDeCustodiaTransformer.transformarCadenaDeCustodia(cadenaDeCustodia);
    }
    
    @Override
    public CadenaDeCustodiaDTO consultarCadenaDeCustodiaPorFolioExpediente(String folio, Long idExpediente) throws NSJPNegocioException {
        CadenaDeCustodia cadenaDeCustodia = cadenaDeCustodiaDAO.consultarCadenaDeCustodiaPorFolioExpediente(folio, idExpediente);
        return cadenaDeCustodia == null ? null :
            CadenaDeCustodiaTransformer.transformarCadenaDeCustodia(cadenaDeCustodia);
    }

}
