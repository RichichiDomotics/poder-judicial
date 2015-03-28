/**
* Nombre del Programa : JerarquiaOrganizacionalService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/06/2011
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
package mx.gob.segob.nsjp.service.institucion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.institucion.InstitucionDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;

/**
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface JerarquiaOrganizacionalService {
	
	/**
	 * Operación que realiza la funcionalidad de consultar el prefijo definido para la institución- área.
	 * Devuelve una cadena asignada al prefijo de acuerdo a lo siguiente:
	 * 		LLLL/II/DD/UU   {13 caracteres de longitud máxima}
	 * 	En donde:		
	 * 		L = Carácter Libre de relleno {3 caracteres}
	 * 		I = Prefijo Institución {2 caracteres}
	 * 		D = Prefijo Distrito {2 caracteres}
	 * 		U = Prefijo Unidad {3 caracteres}
	 * 
	 * Notas: 
	 * 	1.- En caso de que el área no este asociado a un distrito se completara con -- Ejemplo:  000/PR/--/RBP 
	 *  2.- El carácter libre permite completar la longitud del prefijo.
	 *  3.- El carácter libre, en una futura implementación será capturado por el usuario.
	 *  
	 * @param institucionDTO
	 * @return
	 */
	public String consultarPrefijo(InstitucionDTO institucionDTO) throws NSJPNegocioException;
	
	
	/**
	 * Consulta de los Tipos de solicitudes de acuerdo al ID de la Jerarquia
	 * Organizacional. La recuperación de la información se realiza mediante 
	 * la tabla de cruce de JerarquiaOrgTipoSolicitud
	 * 
	 * @param idJerarquiaOrganizacional
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ValorDTO> consultarTipoSolictudesPorJerarquiaOrganizacional(Long idJerarquiaOrganizacional)throws NSJPNegocioException;
	
	
	/**
     * Se consulta las Áreas de acuerdo a un jerarquiaResponsableId y, opcionalmente, una 
     * lista de ids de Áreas y Departamentos que no van a ser considerados.
     * En caso de que no se pase el ID de la JerarquiaResponsable
     * se toma la institucion actual del sistema.
     * 
	 * @param jerarquiaResponsableId
	 * @param idsJerarquiaOrgADescartar
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<JerarquiaOrganizacionalDTO> consultarDepartamentosExceptoAreasYDepartamentos(Long jerarquiaResponsableId, 
			List<Long> idsAreasDescartar, List<Long> idsDepartamentoDescartar)	throws NSJPNegocioException ;
				
}
