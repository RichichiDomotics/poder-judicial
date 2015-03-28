/**
* Nombre del Programa : ProcesoServiceImpl.java
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
package mx.gob.segob.nsjp.service.proceso.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.proceso.ProcesoDAO;
import mx.gob.segob.nsjp.dao.proceso.SubprocesoDAO;
import mx.gob.segob.nsjp.dto.proceso.ProcesoDTO;
import mx.gob.segob.nsjp.dto.proceso.SubprocesoDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.model.Proceso;
import mx.gob.segob.nsjp.model.Subproceso;
import mx.gob.segob.nsjp.service.proceso.ProcesoService;
import mx.gob.segob.nsjp.service.proceso.impl.transform.ProcesoTransformer;
import mx.gob.segob.nsjp.service.usuario.impl.transformer.RolTransformer;

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
public class ProcesoServiceImpl implements ProcesoService {

	public static final Logger logger = Logger.getLogger(ProcesoServiceImpl.class);
	
	@Autowired
	private ProcesoDAO procesoDAO;
	
	@Autowired
	private SubprocesoDAO subprocesoDAO;
	
	/**
	 * M&eacute;todo que consulta todos los procesos
	 * @return Lista de ProcesoDTO
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<ProcesoDTO> consultarProcesos() throws NSJPNegocioException {
		List<Proceso> result = procesoDAO.consultarProcesos();
		List<ProcesoDTO> procesos = new ArrayList<ProcesoDTO>();
		for (Proceso proceso : result) {
			procesos.add(ProcesoTransformer.transformar(proceso));
		}

		return procesos;
	}

	/**
	 * M&eacute;todo que consulta todos los procesos pertenecientes a un Rol
	 * @param RolDTO: Id del rol a consultar 
	 * @return Lista de Procesos
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<ProcesoDTO> consultarProcesosPorRol(RolDTO rolDTO)
			throws NSJPNegocioException {

		List<Proceso> result = procesoDAO.consultarProcesosPorRol(RolTransformer.transformar(rolDTO));
		List<ProcesoDTO> procesos = new ArrayList<ProcesoDTO>();
		for (Proceso proceso : result) {
			procesos.add(ProcesoTransformer.transformar(proceso));
		}

		return procesos;
	}

	/**
	 * M&eacute;todo que consulta todos los subprocesos
	 * @return Lista de Subprocesos
	 * @throws NSJPNegocioException
	 */	
	@Override
	public List<SubprocesoDTO> consultarSubprocesos() throws NSJPNegocioException {
		
		List<Subproceso> result = subprocesoDAO.consultarSubprocesos();
		List<SubprocesoDTO> subprocesos = new ArrayList<SubprocesoDTO>();
		for (Subproceso subproceso : result) {
			subprocesos.add(ProcesoTransformer.transformar(subproceso));
		}

		return subprocesos;
	}

	/**
	 * M&eacute;todo que consulta todos los subprocesos de un Proceso
	 * @param proceso: id del proceso  
	 * @return Lista de Subprocesos
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<SubprocesoDTO> consultarSubprocesosPorProceso(ProcesoDTO procesoDTO)
			throws NSJPNegocioException {
		List<Subproceso> result = subprocesoDAO.consultarSubprocesosPorProceso(
									ProcesoTransformer.transformar(procesoDTO));
		
		List<SubprocesoDTO> subprocesos = new ArrayList<SubprocesoDTO>();
		for (Subproceso subproceso : result) {
			subprocesos.add(ProcesoTransformer.transformar(subproceso));
		}

		return subprocesos;
	}

}
