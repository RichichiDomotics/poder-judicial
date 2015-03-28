/**
 * Nombre del Programa : FormaDelegateImpl.java
 * Autor                            : Escorza
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 22 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación del Delegate para manipulación de documentos
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
package mx.gob.segob.nsjp.delegate.forma.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.forma.FormaDelegate;
import mx.gob.segob.nsjp.dto.forma.CamposFormaDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.service.forma.ConsultarDetalleFormaService;
import mx.gob.segob.nsjp.service.forma.ConsultarFormaPlantillaService;
import mx.gob.segob.nsjp.service.forma.EliminarFormaService;
import mx.gob.segob.nsjp.service.forma.GuardarFormaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * Implementación del Delegate para manipulación de formas
 * @version 1.0
 * @author adrian
 *
 */
@Service("formaDelegate")
public class FormaDelegateImpl implements FormaDelegate {

	@Autowired
	private ConsultarFormaPlantillaService consultarFormaPlantillaService;
	@Autowired
	private ConsultarDetalleFormaService consultarDetalleFormaService;
	@Autowired
	private EliminarFormaService eliminarFormaService;
	@Autowired
	private GuardarFormaService guardarFormaService;
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.forma.FormaDelegate#consultarFormaPlantilla(java.lang.String)
	 */
	@Override
	public List<FormaDTO> consultarFormaPlantilla(String tipoDocumento)
			throws NSJPNegocioException {
		return consultarFormaPlantillaService.consultarFormaPlantilla(tipoDocumento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.forma.FormaDelegate#consultarDetalleForma(long)
	 */
	@Override
	public FormaDTO consultarDetalleForma(Long idForma)
			throws NSJPNegocioException {
		return consultarDetalleFormaService.consultarDetalleForma(idForma);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.forma.FormaDelegate#eliminarForma(long)
	 */
	@Override
	public void eliminarForma(Long idForma) throws NSJPNegocioException {
		 eliminarFormaService.eliminarForma(idForma);
	}

	
	@Override
	public List<FormaDTO> consultarPlantillaPorTipo(Long tipoForma)
			throws NSJPNegocioException {
		return consultarFormaPlantillaService.consultarPlantillaPorTipo(tipoForma);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.forma.FormaDelegate#guardarForma(mx.gob.segob.nsjp.dto.forma.FormaDTO)
	 */
	@Override
	public Long guardarForma(FormaDTO forma)  throws NSJPNegocioException{
		return guardarFormaService.guardarForma(forma);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.forma.FormaDelegate#consultarFormasPorTipoForma(java.lang.Long)
	 */
	@Override
	public List<FormaDTO> consultarFormasPorTipoForma(Long tipoFormaId)
			throws NSJPNegocioException {
		return consultarFormaPlantillaService.consultarPlantillaPorTipo(tipoFormaId);
		
	}

	@Override
	public List<CamposFormaDTO> consultarCamposForma()
			throws NSJPNegocioException {
		return consultarFormaPlantillaService.consultarCamposForma();
		
	}
}
