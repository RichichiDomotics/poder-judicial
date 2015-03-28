/**
 * Nombre del Programa : ConsultarCadenaDeCustodiaXFolioServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 23-jun-2011
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
package mx.gob.segob.nsjp.service.test.cadenadecustodia.impl;

import java.sql.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.cadenadecustodia.impl.CadenaDeCustodiaDAOImpl;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.service.cadenadecustodia.ConsultarCadenaDeCustodiaXFolioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarCadenaDeCustodiaXFolioServiceImplTest
        extends BaseTestServicios<ConsultarCadenaDeCustodiaXFolioService> {

    @Autowired
    CadenaDeCustodiaDAOImpl cadenaDeCustodiaDAOImpl;

    public void testConsultarCadenaDeCustodiaXFolioService() {
        try {
        logger.info("Probando el servicio de: ConsultarCadenaDeCustodiaXFolioService");
        assert service != null;
        CadenaDeCustodia buscame = new CadenaDeCustodia();
        buscame.setCadenaDeCustodiaId(742L);
        buscame.setFechaIntercambio(new Date(System.currentTimeMillis()));
        buscame.setObservacion("observacion cosme");
//        buscame.setQuienEntrega(1);
//        buscame.setQuienRecibe(1);
        buscame.setFolio("FOLIOCOSME");
//        cadenaDeCustodiaDAOImpl.saveOrUpdate(buscame);
        CadenaDeCustodiaDTO cadenaDeCustodia = service.consultarCadenaDeCustodiaXFolio("CadenaDeCustodia_001");
        assertNotNull("cadenaDeCustodia", cadenaDeCustodia);
        assertNotNull("cadenaDeCustodia.getCadenaDeCustodiaId()", cadenaDeCustodia.getCadenaDeCustodiaId());
        cadenaDeCustodiaDAOImpl.delete(buscame);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
        }
    }
}
