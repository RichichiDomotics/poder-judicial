/**
 * Nombre del Programa : ActualizarEtapaCasoServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 02-ago-2011
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
package mx.gob.segob.nsjp.service.caso.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.caso.ActualizarEtapaCasoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ActualizarEtapaCasoServiceImpl implements ActualizarEtapaCasoService {

    /**
     * Logger de la clase.
     */
    private final static Logger logger = Logger.getLogger(ActualizarEtapaCasoServiceImpl.class);
    @Autowired
    private CasoDAO casoDAO;

    @Override
    public void actualizarEtapaCaso(CasoDTO casoDto, ValorDTO nuevaEtapaDto)
            throws NSJPNegocioException {
    	if (logger.isDebugEnabled())
			logger.debug("SERVICIO PARA ACTUALIZAR ETAPA DEL CASO");
    	
    	
        if (casoDto == null || casoDto.getCasoId() == null
                || nuevaEtapaDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Caso caso = casoDAO.read(casoDto.getCasoId());
        Valor valor = new Valor(nuevaEtapaDto.getIdCampo());
        caso.setEtapa(valor);
    }
}
