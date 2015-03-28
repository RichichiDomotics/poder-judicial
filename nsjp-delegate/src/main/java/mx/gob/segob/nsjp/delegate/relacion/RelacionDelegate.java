/**
 * Nombre del Programa : RelacionDelegate.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 12-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.delegate.relacion;


import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.relacion.CatCategoriaRelacionDTO;
import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDTO;

/**
 * Contrato del delegate para los metodos de comunicacion de Relacion
 * entre la vista y los servicios.
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface RelacionDelegate {

    /**
     * Consulta los CatRelacion de una CatCategoriaRelacionDTO.
     * Se espera que la "desCategoriaRelacion" sea una cadena conformada por
     * dos tipos de elementos con un guion en medio.</br>
     * Por ejemplo: "Persona - Persona", "Persona - Objeto", "Persona - Lugar",
     * etc.
     * @throws NSJPNegocioException En caso que se cumpla alguno de:
     * <ol>
     * <li> {@code catCategoriaRelacionDTO == null}
     * <li> {@code catCategoriaRelacionDTO.getDesCategoriaRelacion() == null}
     * </ol>
     */
    List<CatRelacionDTO> consultarCatRelacionesXCatCategoriaRelacion(
            CatCategoriaRelacionDTO catCategoriaRelacionDTO)
            throws NSJPNegocioException;

    /**
     * Verifica si existe un registro en la tabla Relacion que corresponda
     * con los parametros {@code idElementoSujeto, idElementoComplemento y
     * idCatRelacion}. 
     * @param idCatRelacion El tipo de relacion que se quiere verificar.
     * @param idElementoSujeto El id del elemento sujeto del que estamos
     * verificando en la tabla relacion.
     * @param idElementoComplemento El id del elemento complemento que estamos
     * verificando en la tabla relacion.
     * @return {@code true} en caso que exista un registro en la tabla relacion
     * que tenga alguna de las actividades asociadas que corresponda con el
     * {@code idActividad} que se le pasa como parametro. {@code false} en caso
     * que no exista un registro en la tabla relacion o que si existe, ninguna
     * de sus actividades relacionadas corresponda con el {@code idActividad}
     * pasado como parametro.
     * @throws NSJPNegocioException Si alguno de los parametros es null.
     */
    boolean validarRelacion(Long idCatRelacion,
            Long idElementoSujeto, Long idElementoComplemento)throws NSJPNegocioException;

    /**
     * Crea un nuevo registro en la tabla relacion con los parametros que se le
     * pasan.
     * @param idCatRelacion El id del tipo de relacion que estamos creando.
     * @param idElementoSujeto El id del elemento sujeto.
     * @param idElementoComplemento El id del elemento complemento.
     * @throws NSJPNegocioException En caso que alguno de los parametros sea
     * null.
     */
    void registrarRelacion(Long idCatRelacion,
            Long idElementoSujeto, Long idElementoComplemento)throws NSJPNegocioException;
    
    /**
     * Consulta todas las categorias de relación que sean o no documento
     * @param esDocumento: Boolean que indica si se quiere que sea o no documento
     * @return
     * @throws NSJPNegocioException
     */
    public List<CatCategoriaRelacionDTO> consultarCatCategoriaRelacionSiDocumento(Boolean esDocumento)throws NSJPNegocioException;
    
	/**
	 * Servicio que permite la actualizacion del estatus de EsActivo definido en la entidad
	 * Relacion. el objetivo es desactivar la relacion entre elementos. 
	 * 
	 * @param idRelaciones
	 * @throws NSJPNegocioException
	 */
	void actualizarEsActivoRelaciones(List<Long> idRelaciones ) throws NSJPNegocioException;
	
	/**
	 * Metodo que permite consultar las relaciones aosciadas a un identificador de complemento y un id de catRelacion
	 * @param idExpediente
	 * @param idCatCategoriaRelacion
	 * @param esSujeto
	 * @return
	 * @throws NSJPNegocioException
	 */
	   public List<RelacionDTO> consultarRelacionesByComplementoIdAndTipoRelacion(Long idComplemento, Long idCatRelacion)
		throws NSJPNegocioException;
	   
	   
	    /**
	     * Consulta los Parentescos asociados a una persona
	     * @return List<CatRelacionDTO>
	     * @throws NSJPNegocioException
	     */
	    List<CatRelacionDTO> consultarParentescos()throws NSJPNegocioException;
	
	
}
