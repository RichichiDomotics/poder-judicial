/**
* Nombre del Programa : ProcesoDelegateImpl.java
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
package mx.gob.segob.nsjp.delegate.proceso.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.proceso.ProcesoDelegate;
import mx.gob.segob.nsjp.dto.proceso.ProcesoDTO;
import mx.gob.segob.nsjp.dto.proceso.SubprocesoDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.service.proceso.ProcesoService;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
@Service
@Transactional
public class ProcesoDelegateImpl implements ProcesoDelegate {

	@Autowired
	ProcesoService procesoService;
	
	/**
	 * M&eacute;todo que consulta todos los procesos
	 * @return Lista de ProcesoDTO
	 * @throws NSJPNegocioException
	 */
	public List<ProcesoDTO> consultarProcesos()throws NSJPNegocioException{
		return procesoService.consultarProcesos();
	}

	/**
	 * M&eacute;todo que consulta todos los procesos pertenecientes a un Rol
	 * @param RolDTO: Id del rol a consultar 
	 * @return Lista de Procesos
	 * @throws NSJPNegocioException
	 */
	public List<ProcesoDTO> consultarProcesosPorRol(RolDTO rolDTO)throws NSJPNegocioException{
		return procesoService.consultarProcesosPorRol(rolDTO);
	}	

	/**
	 * M&eacute;todo que consulta todos los subprocesos
	 * @return Lista de Subprocesos
	 * @throws NSJPNegocioException
	 */	
	public List<SubprocesoDTO> consultarSubprocesos()throws NSJPNegocioException{
		return procesoService.consultarSubprocesos();
	}

	/**
	 * M&eacute;todo que consulta todos los subprocesos de un Proceso
	 * @param proceso: id del proceso  
	 * @return Lista de Subprocesos
	 * @throws NSJPNegocioException
	 */
	public List<SubprocesoDTO> consultarSubprocesosPorProceso(ProcesoDTO procesoDTO)throws NSJPNegocioException{
		return procesoService.consultarSubprocesosPorProceso(procesoDTO);	
	}
	
}
