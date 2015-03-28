/**
 * Nombre del Programa : ElementoDelegateImpl.java
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
package mx.gob.segob.nsjp.delegate.elemento.impl;

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.elemento.ElementoDelegate;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.elemento.RelacionReincidenciaDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDTO;
import mx.gob.segob.nsjp.service.caso.BuscarCasoService;
import mx.gob.segob.nsjp.service.elemento.AdjuntarArchivoAElementoService;
import mx.gob.segob.nsjp.service.elemento.AnularElementoService;
import mx.gob.segob.nsjp.service.elemento.ConsultarCatElementoService;
import mx.gob.segob.nsjp.service.elemento.ConsultarElementosXActividadService;
import mx.gob.segob.nsjp.service.elemento.ConsultarElementosXIdExpedienteCatRelacionService;
import mx.gob.segob.nsjp.service.elemento.ConsultarRelacionesDeReincidenciaXElementoService;
import mx.gob.segob.nsjp.service.elemento.RegistrarRelacionDeReincidenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service("elementoDelegate")
public class ElementoDelegateImpl implements ElementoDelegate {

    @Autowired
    private ConsultarElementosXActividadService
            consultarElementosXActividadService;

    @Autowired
    private ConsultarCatElementoService consultarCatElementoService;
    @Autowired
    private ConsultarElementosXIdExpedienteCatRelacionService consultarElementosXIdExpedienteCatRelacionService;
    
    @Autowired
    private AdjuntarArchivoAElementoService adjuntarArchivoAElementoService;
    
    @Autowired
    private BuscarCasoService buscarCasoService;
    
    @Autowired
    private RegistrarRelacionDeReincidenciaService relacionDeReincidenciaService;
    
    @Autowired
    private ConsultarRelacionesDeReincidenciaXElementoService relacionesDeReincidenciaXElementoService;
    
    @Autowired
    private AnularElementoService anularElementoService;
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ElementoDTO> consultarElementosXActividad(Long idActividad)
            throws NSJPNegocioException {
        return consultarElementosXActividadService.consultarElementosXActividad(idActividad);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String consultarCatElemento(Long idElemento) throws NSJPNegocioException{
        return consultarCatElementoService.consultarCatElemento(idElemento);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ElementoDTO> consultarElementosXIdExpedienteCatRelacion(Long idExpediente, Long idCatCategoriaRelacion, Boolean esSujeto)
            throws NSJPNegocioException {
        return consultarElementosXIdExpedienteCatRelacionService.consultarElementosXIdExpedienteCatRelacion(idExpediente, idCatCategoriaRelacion, esSujeto);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public List<RelacionDTO> consultarElementosXIdExpediente(String numeroExpediente)
	   		throws NSJPNegocioException{
		return consultarElementosXIdExpedienteCatRelacionService.consultarElementosXIdExpediente(numeroExpediente);
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
    public void adjuntarArchivoAElemento(ElementoDTO elemento,
			ArchivoDigitalDTO adjunto) throws NSJPNegocioException {
    	adjuntarArchivoAElementoService.adjuntarArchivoAElemento(elemento,adjunto);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CasoDTO> buscarReincidenciaDeElementos(ElementoDTO elementoDTO) throws NSJPNegocioException{
    	return buscarCasoService.buscarReincidenciaDeElementos(elementoDTO);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public List<RelacionReincidenciaDTO> registrarReinicidencias(Long idElemento,
			List<Long> idCasos, Long idFuncionario) throws NSJPNegocioException{
		return relacionDeReincidenciaService.registrarReinicidencias(idElemento, idCasos, idFuncionario);
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
	public List<RelacionReincidenciaDTO> consultarReincidenciasXElemento(Long idElemento) throws NSJPNegocioException{
		return relacionesDeReincidenciaXElementoService.consultarReincidenciasXElemento(idElemento);
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
	public Boolean anularElemento(Long idElemento) throws NSJPNegocioException {
		return anularElementoService.anularElemento(idElemento);		
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public List<String> consultarRelacionesElemento(Long idElemento)
			throws NSJPNegocioException {
		return anularElementoService.consultarRelacionesElemento(idElemento); 
	}
}
