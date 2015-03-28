/**
 * 
 */
package mx.gob.segob.nsjp.dao.medida;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.FechaCompromiso;

/**
 * @author adrian
 * 
 */
public interface FechaCompromisoDAO extends GenericDao<FechaCompromiso, Long> {
    List<FechaCompromiso> consultarFechaCompromisoPorMedidaId(Long idMedida);
    List<FechaCompromiso> consultarCalendarizacionPorMedidaIdReducido(
            Long idMedida) throws NSJPNegocioException;
    /**
     * 
     * @param fechaInicio
     * @param fechaFin
     * @param isIncumplimiento
     * @return
     */
    List<FechaCompromiso> buscarFechas(Date fechaInicio, Date fechaFin,
            Boolean isIncumplimiento);
    /**
     * Permite obtener un pago en base al Identificador
     * @param aoFechaCompromiso
     * @return
     */
    public FechaCompromiso consultarPagoPorId(FechaCompromiso aoFechaCompromiso);
}
