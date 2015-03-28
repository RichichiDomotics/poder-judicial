/**
 * Nombre del Programa : ConsultarCompromisoPeriodicoServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación del servicio para consultar las fechas compromiso.
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
package mx.gob.segob.nsjp.service.compromiso.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.medida.FechaCompromisoDAO;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.dto.medida.FiltroBusquedaCompromisosDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.model.FechaCompromiso;
import mx.gob.segob.nsjp.service.compromiso.ConsultarCompromisoPeriodicoService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.FechaCompromisoTransformer;

/**
 * Implementación del servicio para consultar las fechas compromiso.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional
public class ConsultarCompromisoPeriodicoServiceImpl
        implements
            ConsultarCompromisoPeriodicoService {
    @Autowired
    private FechaCompromisoDAO fechasDao;
    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.compromiso.ConsultarCompromisoPeriodicoService
     * #obtenerCompromisoPeriodico(mx.gob.segob.nsjp.dto.medida.MedidaDTO)
     */
    @Override
    public CompromisoPeriodicoDTO obtenerCompromisoPeriodico(MedidaDTO medInput)
            throws NSJPNegocioException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.compromiso.ConsultarCompromisoPeriodicoService
     * #buscarFechasComprimiso(java.util.Date, java.util.Date,
     * java.lang.Boolean)
     */
    @Override
    public List<FechaCompromisoDTO> buscarFechasComprimiso(FiltroBusquedaCompromisosDTO filtro)
            throws NSJPNegocioException {
        List<FechaCompromiso> fechas = fechasDao.buscarFechas(filtro.getFechaInicio(),
                filtro.getFechaFin(), filtro.getIsIncumplimiento());
        List<FechaCompromisoDTO> fechasCompromiso = new ArrayList<FechaCompromisoDTO>();
        if (fechas.size() > 0) {
            for (FechaCompromiso row : fechas) {
                FechaCompromisoDTO fc = new FechaCompromisoDTO();
                fc = FechaCompromisoTransformer.transformarFechaCompromisoMaestro(row);
                fechasCompromiso.add(fc);
            }
        }
        return fechasCompromiso;
    }

}
