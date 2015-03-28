/**
* Nombre del Programa : CompromisoDelegateImpl.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementación del Delegate para lo relacionado a compromiso periodico
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
package mx.gob.segob.nsjp.delegate.compromiso.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.compromiso.CompromisoDelegate;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.dto.medida.FiltroBusquedaCompromisosDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.service.compromiso.ConsultarCompromisoPeriodicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del Delegate para lo relacionado a compromiso periodico.
 * @version 1.0
 * @author vaguirre
 *
 */
@Service
public class CompromisoDelegateImpl implements CompromisoDelegate {
    @Autowired
    private ConsultarCompromisoPeriodicoService consultaService;
    
    @Override
    public CompromisoPeriodicoDTO obtenerCompromisoPeriodico(MedidaDTO medInput)
            throws NSJPNegocioException {
        return consultaService.obtenerCompromisoPeriodico(medInput);
    }

    @Override
    public List<FechaCompromisoDTO> buscarFechasComprimiso(FiltroBusquedaCompromisosDTO filtro)
            throws NSJPNegocioException {
        return consultaService.buscarFechasComprimiso(filtro);
    }

}
