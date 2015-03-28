/**
* Nombre del Programa : IngresarIndividuoMenorServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de servicio para realizar el registro de un involucrado menor de edad y su tutor
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
package mx.gob.segob.nsjp.service.involucrado.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.relacion.TipoRelacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoMenorService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.relacion.GenerarRelacionService;

/**
 * Implementacion de servicio para realizar el registro de un involucrado menor de edad y su tutor.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class IngresarIndividuoMenorServiceImpl implements
		IngresarIndividuoMenorService {

	@Autowired
	private IngresarIndividuoService ingresarIndividuoService;
	
	@Autowired
	private GenerarRelacionService generarRelacionService;
	
	@Override
	public List<InvolucradoDTO> ingresarIndividuoMenor(
			InvolucradoDTO involucradoDTO, InvolucradoDTO tutorDTO)
			throws NSJPNegocioException {
		List<InvolucradoDTO> listInvolucradoDTO = new ArrayList<InvolucradoDTO>();
		
		Long idInvolucrado = ingresarIndividuoService.ingresarIndividuo(involucradoDTO);
		Long idTutor = ingresarIndividuoService.ingresarIndividuo(tutorDTO);
		
		if ((idInvolucrado!=null && idInvolucrado>0) && (idTutor!=null && idTutor>0))
			generarRelacionService.generarRelacion(idInvolucrado, idTutor, Relaciones.TUTOR, TipoRelacion.IMPLICITA.getValorId().shortValue());		
		
		InvolucradoDTO invDTOGen = new InvolucradoDTO(idInvolucrado);
		invDTOGen.setCalidadDTO(involucradoDTO.getCalidadDTO());
		listInvolucradoDTO.add(invDTOGen);
		
		InvolucradoDTO tutorDTOGen = new InvolucradoDTO(idTutor);
		tutorDTOGen.setCalidadDTO(tutorDTO.getCalidadDTO());
		listInvolucradoDTO.add(tutorDTOGen);		
		
		return listInvolucradoDTO;
	}

}
