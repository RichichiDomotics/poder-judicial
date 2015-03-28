/**
* Nombre del Programa : HechoDelegateImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del Delegate para los servicios relacionados con Hecho
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
package mx.gob.segob.nsjp.delegate.hecho.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.hecho.HechoDelegate;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.service.hecho.ConsultarHechosService;
import mx.gob.segob.nsjp.service.hecho.IngresarHechoService;
import mx.gob.segob.nsjp.service.hecho.ModificarHechoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion del Delegate para los servicios relacionados con Hecho.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service("hechoDelegate")
public class HechoDelegateImpl implements HechoDelegate {
	
	@Autowired
	private IngresarHechoService ingresarHechoService;
	@Autowired
	private ModificarHechoService modificarHechoService;
	@Autowired
	private ConsultarHechosService consultarHechosService;
	
	@Override
	public HechoDTO ingresarHecho(HechoDTO hechoDTO)
			throws NSJPNegocioException {
		
		return this.ingresarHechoService.ingresarHecho(hechoDTO);
	}

	@Override
	public HechoDTO modificarHecho(HechoDTO hechoDTO)
			throws NSJPNegocioException {
		return modificarHechoService.modificarHecho(hechoDTO);
	}

	@Override
	public List<HechoDTO> consultarHechos(HechoDTO hechoDTO)
			throws NSJPNegocioException {
		return consultarHechosService.consultarHechos(hechoDTO);
	}


}
