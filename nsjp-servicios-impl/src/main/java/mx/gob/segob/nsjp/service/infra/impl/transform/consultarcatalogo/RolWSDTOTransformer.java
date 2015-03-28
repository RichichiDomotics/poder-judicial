/**
* Nombre del Programa 		: RolWSDTOTransformer.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP                    		Fecha: 10 Apr 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Describir el objetivo de la clase de manera breve
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 							Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.infra.impl.transform.consultarcatalogo;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.dto.usuario.RolWSDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class RolWSDTOTransformer extends GenericWSDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8030457607388013867L;
	
	/**
	 * @param rolWSDTO
	 * @return
	 */
	public static RolDTO transformar (RolWSDTO rolWSDTO){
		RolDTO rol = null;
		if (rolWSDTO != null){
			rol = new RolDTO();
			rol.setRolId(rolWSDTO.getRolId());
			rol.setNombreRol(rolWSDTO.getNombreRol());
			rol.setDescripcionRol(rolWSDTO.getDescripcionRol());
		}
		return rol;
	}
	
	/**
	 * @param rolWSDTO
	 * @return
	 */
	public static RolWSDTO transformar (RolDTO rolDTO){
		RolWSDTO rol = null;
		if (rolDTO != null){
			rol = new RolWSDTO();
			rol.setRolId(rolDTO.getRolId());
			rol.setNombreRol(rolDTO.getNombreRol());
			rol.setDescripcionRol(rolDTO.getDescripcionRol());
		}
		return rol;
	}
	
	/**
	 * @param rolWSDTO
	 * @return
	 */
	public static mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno.RolWSDTO transformarWSDTOCliente (RolDTO rolDTO){
		mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno.RolWSDTO rol = null;
		if (rolDTO != null){
			rol = new mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno.RolWSDTO();
			rol.setRolId(rolDTO.getRolId());
			rol.setNombreRol(rolDTO.getNombreRol());
			rol.setDescripcionRol(rolDTO.getDescripcionRol());
		}
		return rol;
	}

}
