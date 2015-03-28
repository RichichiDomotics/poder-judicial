/**
 * Nombre del Programa : RecibirAvisoHDelictivoServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación para recibir el aviso de hecho delictivo
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
package mx.gob.segob.nsjp.service.documento.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoWSDTO;
import mx.gob.segob.nsjp.service.documento.GuardarAvisoHDelictivoService;
import mx.gob.segob.nsjp.service.documento.RecibirAvisoHDelictivoService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.AvisoHechoDelictivoTransformer;

/**
 * Implementación para recibir el aviso de hecho delictivo.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service("recibirAvisoHDelictivoService")
@Transactional
public class RecibirAvisoHDelictivoServiceImpl
        implements
            RecibirAvisoHDelictivoService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(RecibirAvisoHDelictivoServiceImpl.class);
    @Autowired
    private GuardarAvisoHDelictivoService guardarAHDService;
    
    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.documento.RecibirAvisoHDelictivoService#
     * recibirAvisoHDelictivoService
     * (mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoWSDTO)
     */
    @Override
    public void recibirAvisoHDelictivoService(AvisoHechoDelictivoWSDTO aviso)
            throws NSJPNegocioException {
        logger.info("Inicia - recibirAvisoHDelictivoService(...)");
        AvisoHechoDelictivoDTO avisoDTO = AvisoHechoDelictivoTransformer.transformarAvisoWS(aviso); 
        guardarAHDService.ingresarAvisoHechoDeictivoSSP(avisoDTO);
        
        logger.info("Fin - recibirAvisoHDelictivoService(...) [OK]");

    }

}
