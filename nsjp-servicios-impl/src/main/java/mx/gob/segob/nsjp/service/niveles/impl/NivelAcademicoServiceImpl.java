/**
* Nombre del Programa : ProgramaServiceImpl.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/01/2012
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
package mx.gob.segob.nsjp.service.niveles.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.niveles.CatTipoNivelAcademicoDAO;
import mx.gob.segob.nsjp.dao.niveles.NivelAcademicoDAO;
import mx.gob.segob.nsjp.dto.niveles.CatTipoNivelAcademicoDTO;
import mx.gob.segob.nsjp.dto.niveles.NivelAcademicoDTO;
import mx.gob.segob.nsjp.model.CatTipoNivelAcademico;
import mx.gob.segob.nsjp.model.NivelAcademico;
import mx.gob.segob.nsjp.service.niveles.NivelAcademicoService;
import mx.gob.segob.nsjp.service.niveles.impl.transform.NivelAcademicoTransformer;
import mx.gob.segob.nsjp.service.programa.impl.transform.ProgramaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */

@Service
@Transactional
public class NivelAcademicoServiceImpl implements NivelAcademicoService {

	public static final Logger logger = Logger.getLogger(NivelAcademicoServiceImpl.class);
	
	@Autowired
	private NivelAcademicoDAO nivelAcademicoDAO;
	
	@Autowired
	private CatTipoNivelAcademicoDAO catTipoNivelAcademicoDAO;
		
	/**
	 * M&eacute;todo que consulta todos los niveles acad&eacute;micos
	 * @return Lista de NivelAcademicoDTO
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<NivelAcademicoDTO> consultarNivelesAcademicos()throws NSJPNegocioException {
		List<NivelAcademico> result = nivelAcademicoDAO.consultarNivelAcademico();
		List<NivelAcademicoDTO> lstNivelAcademicoDTO = new ArrayList<NivelAcademicoDTO>();
		for (NivelAcademico nivelAcademico : result) {
			lstNivelAcademicoDTO.add(NivelAcademicoTransformer.transformar(nivelAcademico));
		}

		return lstNivelAcademicoDTO;
	}

	/**
	 * M&eacute;todo que consulta todos los tipos de nivel acad&eacute;mico
	 * @return Lista de CatTipoNivelAcademicoDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatTipoNivelAcademicoDTO> consultarCatTipoNivelAcademico()throws NSJPNegocioException {
		List<CatTipoNivelAcademico> result = catTipoNivelAcademicoDAO.consultarCatTipoNivelAcademico();
		List<CatTipoNivelAcademicoDTO> lstCatTipoNivelAcademicoDTO = new ArrayList<CatTipoNivelAcademicoDTO>();
		for (CatTipoNivelAcademico catTipoNivelAcademico : result) {
			lstCatTipoNivelAcademicoDTO.add(ProgramaTransformer.transformar(catTipoNivelAcademico));
		}

		return lstCatTipoNivelAcademicoDTO;
	}

}