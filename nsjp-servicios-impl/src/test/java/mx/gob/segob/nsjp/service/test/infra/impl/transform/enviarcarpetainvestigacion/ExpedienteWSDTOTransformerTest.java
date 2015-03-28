/**
 * Nombre del Programa : ExpedienteWSDTOTransformerTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 14 Nov 2011
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
package mx.gob.segob.nsjp.service.test.infra.impl.transform.enviarcarpetainvestigacion;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaWSDTO;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedientePorCasoImputadoService;
import mx.gob.segob.nsjp.service.infra.impl.transform.enviarcarpetainvestigacion.ExpedienteWSDTOTransformer;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ExpedienteWSDTOTransformerTest
        extends
            BaseTestServicios<BuscarExpedientePorCasoImputadoService> {

    public void testTranformArmaWSDTO2ArmaDTO() {
        ArmaWSDTO origen = new ArmaWSDTO();
        origen.setMarca(22L);
        origen.setCalibre("9m");
        origen.setEsPertenencia(Boolean.TRUE);
        ArmaDTO destino = new ArmaDTO();
        ExpedienteWSDTOTransformer.transforma(origen, destino);
        logger.debug("destino.getMarca() :: " + destino.getMarca());
        logger.debug("destino.getCalibre() :: " + destino.getCalibre());
        logger.debug("destino.getEsPertenencia() :: "
                + destino.getEsPertenencia());

    }
    public void testTranformArmaDTO2ArmaWSDTO() {
        ArmaDTO origen = new ArmaDTO();
        origen.setMarca(new ValorDTO(22L));
        origen.setCalibre("9m");
        origen.setEsPertenencia(Boolean.TRUE);
        ArmaWSDTO destino = new ArmaWSDTO();
        ExpedienteWSDTOTransformer.transforma(origen, destino);
        logger.debug("destino.getMarca() :: " + destino.getMarca());
        logger.debug("destino.getCalibre() :: " + destino.getCalibre());
        logger.debug("destino.getEsPertenencia() :: "
                + destino.getEsPertenencia());

    }
}
