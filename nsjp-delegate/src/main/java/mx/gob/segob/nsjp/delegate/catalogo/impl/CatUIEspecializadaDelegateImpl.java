/**
* Nombre del Programa : CatUIEspecializadaDelegateImpl.java
* Autor                            : AdrianECH
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/03/2012
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.delegate.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.catalogo.CatUIEspecializadaDelegate;
import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;
import mx.gob.segob.nsjp.service.catalogo.ConsultarUnidadeIEspecializadaService;
import mx.gob.segob.nsjp.service.catalogo.RegistrarUnidadIEspecializadaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Describir el objetivo de la clase con punto al final.
 * Fecha: 05/03/2012
 * @version 1.0
 * @author AdrianECH
 * 
 */
@Service ("CatUIEspecializadaDelegate")
public class CatUIEspecializadaDelegateImpl implements
		CatUIEspecializadaDelegate {
	
	@Autowired
	private RegistrarUnidadIEspecializadaService registrarUnidadIEspecializadaService;
	@Autowired
	private ConsultarUnidadeIEspecializadaService consultarUnidadeIEspecializadaService;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.catalogo.CatUIEspecializadaDelegate#registrarUnidadIEspecializada(mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO)
	 */
	@Override
	public CatUIEspecializadaDTO registrarUnidadIEspecializada(
			CatUIEspecializadaDTO especializadaDTO) throws NSJPNegocioException {
		return registrarUnidadIEspecializadaService.registrarUnidadIEspecializada(especializadaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.catalogo.CatUIEspecializadaDelegate#consultarUnidadesIEspecializadas()
	 */
	@Override
	public List<CatUIEspecializadaDTO> consultarUnidadesIEspecializadas()
			throws NSJPNegocioException {
		return consultarUnidadeIEspecializadaService.consultarUnidadesIEspecializadas();
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.catalogo.CatUIEspecializadaDelegate#consultarUnidadIEspecializada(java.lang.Long)
	 */
	@Override
	public CatUIEspecializadaDTO consultarUnidadIEspecializada(
			Long especializadaID) throws NSJPNegocioException {
		return consultarUnidadeIEspecializadaService.consultarUnidadIEspecializada(especializadaID);
	}

}
