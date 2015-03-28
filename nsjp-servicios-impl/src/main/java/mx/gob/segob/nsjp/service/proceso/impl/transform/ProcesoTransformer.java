/**
* Nombre del Programa : ProcesoTransformer.java
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
package mx.gob.segob.nsjp.service.proceso.impl.transform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.dto.proceso.ProcesoDTO;
import mx.gob.segob.nsjp.dto.proceso.SubprocesoDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.model.Proceso;
import mx.gob.segob.nsjp.model.Rol;
import mx.gob.segob.nsjp.model.Subproceso;
import mx.gob.segob.nsjp.model.SubprocesoId;
import mx.gob.segob.nsjp.service.usuario.impl.transformer.RolTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class ProcesoTransformer {

	
	
	public static ProcesoDTO transformar(Proceso proceso){

		ProcesoDTO procesoDTO = new ProcesoDTO();
		List<RolDTO> roles = new ArrayList<RolDTO>();

		if(proceso != null){

			procesoDTO.setId(proceso.getId());
			procesoDTO.setcNombre(proceso.getCnombreProceso());
			procesoDTO.setcDescripcion(proceso.getCdescripcionProceso());
		
			for (Rol rol : proceso.getRoles()){
				roles.add(RolTransformer.transformar(rol));
			}
		
			procesoDTO.setLstRoles(roles);
		}
		
		return procesoDTO;
	}

	public static Proceso transformar(ProcesoDTO procesoDTO){
		Proceso proceso = new Proceso();
		Set<Rol> roles = new HashSet<Rol>(0);
		
		if(procesoDTO != null){
		
			proceso.setId(procesoDTO.getId());
			proceso.setCnombreProceso(procesoDTO.getcNombre());
			proceso.setCdescripcionProceso(procesoDTO.getcDescripcion());
			proceso.setId(proceso.getId());
		
			for (RolDTO rolDTO : procesoDTO.getLstRoles()) {
				roles.add(RolTransformer.transformar(rolDTO));
			}
		
			proceso.setRoles(roles);
		}
		return proceso;
	}

	public static SubprocesoDTO transformar(Subproceso subproceso){
		SubprocesoDTO subprocesoDTO = new SubprocesoDTO();
		
		if(subproceso!= null){
			subprocesoDTO.setcNombre(subproceso.getCnombreSubproceso());
			subprocesoDTO.setcDescripcion(subproceso.getCdescripcionSubroceso());
			subprocesoDTO.setProcesoDTO(transformar(subproceso.getProceso()));
			ProcesoDTO procesoDTO = new ProcesoDTO();
			procesoDTO.setId(subproceso.getId().getProcesoId());
			subprocesoDTO.setProcesoDTO(procesoDTO);			
			subprocesoDTO.setId(subproceso.getId().getSubprocesoId());
			subprocesoDTO.setcForward(subproceso.getCforward());

		}
		return subprocesoDTO;
	}

	public static Subproceso transformar(SubprocesoDTO subprocesoDTO){
		Subproceso subproceso = new Subproceso();
		if(subprocesoDTO != null){
			
			subproceso.setCnombreSubproceso(subprocesoDTO.getcNombre());
			subproceso.setCdescripcionSubroceso(subprocesoDTO.getcDescripcion());
			
			SubprocesoId subprocesoId = new SubprocesoId();
			
			if(subprocesoDTO.getProcesoDTO()!= null) {
				subprocesoId.setProcesoId(subprocesoDTO.getProcesoDTO().getId());
			}
			subprocesoId.setSubprocesoId(subprocesoDTO.getId());
			
			subproceso.setId(subprocesoId);
			
			subproceso.setCforward(subprocesoDTO.getcForward());
			
		}
		return subproceso;
	}
		
}

