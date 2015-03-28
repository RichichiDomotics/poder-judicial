/**
 * Nombre del Programa : AdministrarPertenenciaDetencionServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 11 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementaci&oacute;n para la administraci&oacute;n de pertenencias en la detenci&oacute;n
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
package mx.gob.segob.nsjp.service.detencion.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.detencion.PertenenciaDAO;
import mx.gob.segob.nsjp.dao.involucrado.DetencionDAO;
import mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.model.Pertenencia;
import mx.gob.segob.nsjp.service.detencion.AdministrarPertenenciaDetencionService;
import mx.gob.segob.nsjp.service.detencion.impl.transform.PertenenciaTransformer;

/**
 * Implementaci&oacute;n para la administraci&oacute;n de pertenencias en la detenci&oacute;n.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Transactional
@Service
public class AdministrarPertenenciaDetencionServiceImpl
        implements
            AdministrarPertenenciaDetencionService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(AdministrarPertenenciaDetencionServiceImpl.class);
    @Autowired
    private PertenenciaDAO pertenenciaDao;

    @Autowired
    private DetencionDAO detencionDao;

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.detencion.AdministrarPertenenciaDetencionService
     * #registrarPertenecia(mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO)
     */
    @Override
    public Long registrarPertenecia(PertenenciaDTO input)
            throws NSJPNegocioException {
        logger.info("Inicia - registrarPertenecia(...)");

        final Pertenencia perPjo = PertenenciaTransformer.transformarDto(input);

        this.pertenenciaDao.create(perPjo);

        logger.info("Fin - registrarPertenecia(...)");
        return null;
    }

    @Override
    public List<PertenenciaDTO> consultarPertenenciaByDetencion(Long idDetencion)
            throws NSJPNegocioException {
        logger.debug("idDetencion :: " + idDetencion);
        final List<PertenenciaDTO> resp = new ArrayList<PertenenciaDTO>();
        final Detencion det = this.detencionDao.read(idDetencion);

        for (Pertenencia per : det.getPertenencias()) {
            resp.add(PertenenciaTransformer.transformarEntity(per));
        }

        return resp;
    }

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.detencion.AdministrarPertenenciaDetencionService#actualizarPertenencia(mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO)
	 */
	@Override
	public void actualizarPertenencia(PertenenciaDTO pertenenciaDTO) {
		Pertenencia pertenencia = PertenenciaTransformer.transformarDto(pertenenciaDTO);
		pertenenciaDao.merge(pertenencia);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.detencion.AdministrarPertenenciaDetencionService#eliminarPertenencia(mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO)
	 */
	@Override
	public void eliminarPertenencia(PertenenciaDTO pertenenciaDTO) {
		Pertenencia pertenencia = PertenenciaTransformer.transformarDto(pertenenciaDTO);
		pertenenciaDao.delete(pertenencia);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.detencion.AdministrarPertenenciaDetencionService#consultarPertenencia(mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO)
	 */
	@Override
	public PertenenciaDTO consultarPertenenciaPorId(PertenenciaDTO pertenenciaDTO) {
		Pertenencia pertenencia = PertenenciaTransformer.transformarDto(pertenenciaDTO);
		pertenencia = pertenenciaDao.read(pertenencia.getPertenenciaId()); 
		return PertenenciaTransformer.transformarEntity(pertenencia);
	}

}
