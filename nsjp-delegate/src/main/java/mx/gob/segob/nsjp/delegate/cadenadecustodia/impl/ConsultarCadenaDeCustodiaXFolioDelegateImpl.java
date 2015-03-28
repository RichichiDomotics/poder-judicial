/**
 * Nombre del Programa : ConsultarCadenaDeCustodiaXFolioDelegateImpl.java
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
package mx.gob.segob.nsjp.delegate.cadenadecustodia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.cadenadecustodia.ConsultarCadenaDeCustodiaXFolioDelegate;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.service.cadenadecustodia.ConsultarCadenaDeCustodiaXFolioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion del delegate ConsultarCadenaDeCustodiaDelegate
 * @author Jacob Lobaco
 */
@Service("consultarCadenaDeCustodiaDelegate")
public class ConsultarCadenaDeCustodiaXFolioDelegateImpl implements ConsultarCadenaDeCustodiaXFolioDelegate{

	@Autowired
	ConsultarCadenaDeCustodiaXFolioService consultarCadenaDeCustodiaXFolioService;
	
    @Override
    public CadenaDeCustodiaDTO consultarCadenaDeCustodiaXFolio(String folio) throws NSJPNegocioException {
        return consultarCadenaDeCustodiaXFolioService.consultarCadenaDeCustodiaXFolio(folio);
    }

}
