/**
 * Nombre del Programa : RelacionDelegateImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 12-jul-2011
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
package mx.gob.segob.nsjp.delegate.relacion.impl;

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.relacion.RelacionDelegate;
import mx.gob.segob.nsjp.dto.relacion.CatCategoriaRelacionDTO;
import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDTO;
import mx.gob.segob.nsjp.service.elemento.ConsultarElementosXIdExpedienteCatRelacionService;
import mx.gob.segob.nsjp.service.relacion.ActualizarRelacionService;
import mx.gob.segob.nsjp.service.relacion.ConsultarCatCategoriaRelacionService;
import mx.gob.segob.nsjp.service.relacion.ConsultarCatRelacionesXCatCategoriaRelacionService;
import mx.gob.segob.nsjp.service.relacion.ConsultarParentescosService;
import mx.gob.segob.nsjp.service.relacion.RegistrarRelacionService;
import mx.gob.segob.nsjp.service.relacion.ValidarRelacionXActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service("relacionDelegate")
public class RelacionDelegateImpl implements RelacionDelegate {

    @Autowired
    private ConsultarCatRelacionesXCatCategoriaRelacionService
            consultarRelacionesXCategoriaService;
    @Autowired
    private ValidarRelacionXActividadService validarRelacionXActividadService;
    @Autowired
    private RegistrarRelacionService registrarRelacionService;
    @Autowired
    private ConsultarCatCategoriaRelacionService consultarCatCategoriaRelacionService;
    @Autowired
    private ActualizarRelacionService actualizarRelacionService;
    @Autowired
    private ConsultarElementosXIdExpedienteCatRelacionService consultarElementosXIdExpedienteCatRelacionService;
    @Autowired
    private ConsultarParentescosService consultarParentescosService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CatRelacionDTO> consultarCatRelacionesXCatCategoriaRelacion(
            CatCategoriaRelacionDTO catCategoriaRelacionDTO) throws NSJPNegocioException {
        return consultarRelacionesXCategoriaService.
                consultarCatRelacionesXCatCategoriaRelacion(catCategoriaRelacionDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validarRelacion(Long idCatRelacion, Long idElementoSujeto, Long idElementoComplemento)
            throws NSJPNegocioException {
        return validarRelacionXActividadService.validarRelacion(idCatRelacion, idElementoSujeto, idElementoComplemento);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registrarRelacion(Long idCatRelacion,
            Long idElementoSujeto, Long idElementoComplemento)
            throws NSJPNegocioException {
        registrarRelacionService.registrarRelacion(idCatRelacion,
                idElementoSujeto, idElementoComplemento);
    }

	@Override
	public List<CatCategoriaRelacionDTO> consultarCatCategoriaRelacionSiDocumento(
			Boolean esDocumento) throws NSJPNegocioException {
		return consultarCatCategoriaRelacionService.consultarCatCategoriaRelacionSiDocumento(esDocumento);
	}
	
	@Override
	public void actualizarEsActivoRelaciones(List<Long> idRelaciones ) throws NSJPNegocioException{
		actualizarRelacionService.actualizarEsActivoRelaciones(idRelaciones);
	}
	
	
	/**
	 * Metodo que permite consultar las relaciones aosciadas a un identificador de un complemento y un id de catRelacion
	 * @param idExpediente
	 * @param idCatCategoriaRelacion
	 * @param esSujeto
	 * @return RelacionDTO.elementoBySujetoId.elementoId
	 * @throws NSJPNegocioException
	 */
	   public List<RelacionDTO> consultarRelacionesByComplementoIdAndTipoRelacion(Long idComplemento, Long idCatRelacion)
		throws NSJPNegocioException{
		   return consultarElementosXIdExpedienteCatRelacionService.consultarRelacionesByComplementoIdAndTipoRelacion(idComplemento, idCatRelacion);
	   }

	@Override
	public List<CatRelacionDTO> consultarParentescos()
			throws NSJPNegocioException {
		return consultarParentescosService.consultarParentescos();
	}
}
