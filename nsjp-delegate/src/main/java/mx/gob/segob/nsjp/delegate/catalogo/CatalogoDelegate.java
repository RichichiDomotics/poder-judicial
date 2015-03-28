/**
 * 
 */
package mx.gob.segob.nsjp.delegate.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatFaltaAdministrativaDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.DiaInhabilDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.rol.FiltroRolesDTO;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;

/**
 * @author vaguirre
 * 
 */
public interface CatalogoDelegate {
    /**
     * Recupera un catalogo.
     * 
     * @param cat
     *            Identificador del catalogo a recuperar.
     * @return Los registros del catalogo
     * @throws NSJPNegocioException
     *             En caso de no poder recuperar el catalogo.
     * 
     */
    List<CatalogoDTO> recuperarCatalogo(Catalogos cat)
            throws NSJPNegocioException;
    /**
     * Recupera un catalogo con todas las columnas del catalogo.
     * 
     * @param cat
     *            Identificador del catalogo a recuperar.
     * @return Los registros del catalogo (llave, valor y dem�s columnas).
     * @throws NSJPNegocioException
     *             En caso de no poder recuperar el catalogo.
     * 
     */
    List<CatalogoDTO> recuperarCatalogoCompleto(Catalogos cat)
            throws NSJPNegocioException;
    /**
     * Recupera un catalogo dependiente
     * 
     * @param catHijo
     *            Identificador del catalogo a recuperar.
     * @param idValorPadre
     *            Valor de la llave del registro del catalogo padre
     * @return Los registros del catalogo asociado al catalogo padre
     * @throws NSJPNegocioException
     *             En caso de no poder recuperar el catalogo.
     */
    List<CatalogoDTO> recuperarCatalogoDependiente(Catalogos catHijo,
            Long idValorPadre) throws NSJPNegocioException;

    /**
     * Recupera la lista de asentamientos que cumplan con los criterios. <br>
     * Si alg�n criterio es <code>null</code> no se tomar� en cuenta para el
     * filtrado. <br>
     * Al menos un filtro tiene que ser diferente de <code>null</code>.
     * 
     * @param idMpio
     * @param idCiudad
     * @param idTipoAsentamiento
     * @return
     * @throws NSJPNegocioException
     *             Lanzada en caso de que los tres filtros sean
     *             <code>null</code>.
     */
    List<CatalogoDTO> consultarAsentamiento(Long idMpio, Long idCiudad,
            Long idTipoAsentamiento) throws NSJPNegocioException;
    
    /**
     * COnsulta catalogo de delitos.
     * @return Lista de delitos
     * @throws NSJPNegocioException
     */
    List<CatDelitoDTO> consultarDelito() throws NSJPNegocioException;
    
    /**
     * Servicio que permite almacenar un nuevo d�a inhabil de acuerdo a los atributos recibidos en el dto
     * @param dto informaci�n del d�a inhabil que se va a almacenar
     * @return identificador del nuevo d�a habil
     * @throws NSJPNegocioException
     */
	public Long guardarDiaInhabil(DiaInhabilDTO dto) throws NSJPNegocioException;
	
	/**
	 * Servicio que permite eliminar un d�a inhabil previamente existene en el sistema, denominado por dto
	 * @param dto d�a inhabil a eliminar del sistema
	 * @throws NSJPNegocioException
	 */
	public void eliminarDiaInhabil(DiaInhabilDTO dto) throws NSJPNegocioException;
	
	/**
	 * Consulta los d�as inhabiles registrados en el sistema
	 * @return lista con los d�as inhabiles mapeados en obejtos D�aInhabilDTO;
	 * @throws NSJPNegocioException
	 */
	public List<DiaInhabilDTO> consultarDiasInhabiles() throws NSJPNegocioException;
	
	/**
	 * Consulta los d�as inhabiles registrados en el sistema por Mes
	 * @return lista con los d�as inhabiles mapeados en obejtos D�aInhabilDTO;
	 * @throws NSJPNegocioException
	 */
	public List<DiaInhabilDTO> consultarDiasInhabilesPorMes(Short mes) throws NSJPNegocioException;
    
    /**
     * Consulta los roles del sistema.
     * @return Lista de roles del sistema
     * @throws NSJPNegocioException
     */
	public List<RolDTO> consultarRoles(FiltroRolesDTO filtroRolesDTO) throws NSJPNegocioException;
	
	/**
	 * Consultar las funciones de los roles del sistema
	 * @author cesarAgustin
	 * @return Lista de funciones
	 * @throws NSJPNegocioException
	 */
	public List<FuncionDTO> consultarFunciones() throws NSJPNegocioException;
	
	/**
	 * Consultar catalogo de faltas administrativas
	 * @author mgallardo	
	 * @return Lista de Faltas administrativas
	 * @throws NSJPNegocioException
	 */
	public List<CatFaltaAdministrativaDTO> consultarCatalogoFaltaAdministrativa() throws NSJPNegocioException;
	
	/**
     * Se consulta las �reas de acuerdo a un jerarquiaResponsableId y, opcionalmente, una 
     * lista de ids de �reas y Departamentos que no van a ser considerados.
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
	
    /**
     * Servicio que se encarga de recuperar el cat�logo de delitos por cualquiera, 
     * o combinaci�n, de los siguientes filtros:
     * -catDelitoId
     * -claveDelito
     * -nombre-esGrave
     * -esAccionPenPriv
     * 
     * @param catDelitoFiltro
     * @return
     * @throws NSJPNegocioException
     */
    List<CatDelitoDTO> consultarCatDelitoPorFilro(CatDelitoDTO catDelitoFiltroDTO) throws NSJPNegocioException;

    /**
     * Servicio que se encarga de recuperar el cat�logo de delitos exceptuando
     * los que est�n agregados al grid de delitos denunciados
     * 
     * @param idsGrid
     * @return
     * @throws NSJPNegocioException
     */    
    List<CatDelitoDTO> consultarDelitosSeleccionables(String idsGrid) throws NSJPNegocioException;
    
    /**
	 * Consulta un catDelito mediante su id
	 * @param catDelitoId
	 * @return
	 * @throws NSJPNegocioException
	 */
	public CatDelitoDTO consultarCatDelitoPorId(Long catDelitoId) throws NSJPNegocioException;
	
	/**
	 * Permite guardar o actualizar un objeto de tipo CatDelitoDTO
	 * @param catDelitoDto
	 * @return el objeto con su id, si ocurre un error devuelve 
	 * @throws NSJPNegocioException
	 */
	public CatDelitoDTO guardarActualizarCatDelito(CatDelitoDTO catDelitoDto) throws NSJPNegocioException;
	
	/**
	 * Permite eliminar un objeto de tipo catDelito 
	 * @param catDelitoId
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long eliminarCatDelito(Long catDelitoId) throws NSJPNegocioException;

	/**
	 * Servicio para consultar una Sala Audiencia por medio de SalaAudienciaId
	 * 
	 * @param salaAudienciaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	SalaAudienciaDTO consultarSalaAudiencia(
			SalaAudienciaDTO salaAudienciaDTO) throws NSJPNegocioException;

	/**
	 * Servicio que se encarga de actualizar o modificar la sala de Audiencia (Con o sin Sala JAVS)
	 * donde se muestran los siguientes casos:
	 * Si NO existe el Id (null) de Sala Audiencia: Guardar la Sala Audiencia con la Sala JAVS
	 * Si existe el ID de Sala Auidiencia: se modifica la Sala Audiencia con la Sala JAVS.
	 * 
	 * El servicio que permite la modificaci�n de la SalaAudiencia, considerando la 
	 * modificaci�n de la SalaJavs
	 * En caso de que se cambie el estatus de Inactivo de la SalaAudiencia, 
	 * la SalaJAvs se elimina fisicamente.
	 * 
	 * @param salaAudienciaDTO
	 * @throws NSJPNegocioException
	 */
	SalaAudienciaDTO guardarActualizarSalaAudiencia(SalaAudienciaDTO salaAudienciaDTO) throws NSJPNegocioException;
}
