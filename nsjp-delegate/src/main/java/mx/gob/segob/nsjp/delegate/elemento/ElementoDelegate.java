/**
 * Nombre del Programa : ElementoDelegate.java
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
package mx.gob.segob.nsjp.delegate.elemento;


import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.elemento.RelacionReincidenciaDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDTO;

/**
 * Contrato del delegate para los metodos de comunicacion de Elemento
 * entre la vista y los servicios.
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ElementoDelegate {

    /**
     * Consulta los Elementos asociados al expediente de la actividad con id
     * {@code idActividad}
     * @param idActividad El id de la activad de la que se obtendra su
     * expediente y a su vez de este se obtendran los elementos asociados.
     * @return Una lista que contiene los elementos del expediente asociado
     * a la actividad. Regresa la lista vacia en caso que no existan elementos
     * asociados al expediente.
     * @throws NSJPNegocioException En caso que {@code idActividad} sea nulo.
     */
    List<ElementoDTO> consultarElementosXActividad(Long idActividad)
            throws NSJPNegocioException;

    /**
     * Consulta el tipo de elemento de un elemento.
     * @param idElemento El id del elemento a consultar.
     * @return Una cadena que representa el tipo de elemento.
     */
    String consultarCatElemento(Long idElemento) throws NSJPNegocioException;

    /**
     * Consultar los elementos asociados al expediente y de acuerdo
	 * a la categoria de relacion que es solicitada.
	 * El parametro esSujeto permite determinar cual de los dos valores
	 * del CatCategoriaRelacion (Persona - Objeto, Persona - Organización)
	 * se debe de efectuar la consulta:
	 *  esSujeto: true --> Primer valor
	 *  esSujeto: false --> Segundo valor
	 * 
     * @param idExpediente
     * @param idCatCategoriaRelacion
     * @param esSujeto
     * @return
     * @throws NSJPNegocioException
     */
    List<ElementoDTO> consultarElementosXIdExpedienteCatRelacion(Long idExpediente, Long idCatCategoriaRelacion, Boolean esSujeto)
    throws NSJPNegocioException ;
    
    public void adjuntarArchivoAElemento(ElementoDTO elemento,
			ArchivoDigitalDTO adjunto) throws NSJPNegocioException;
    
	/**
	 * Servicio utilizado para consultar los elementos asociados a un expediente, regresando 
	 * la relacion con otros elementos, el tipo de relación y la Categoría de la Relación
	 * 
	 * @param idExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<RelacionDTO> consultarElementosXIdExpediente(String numeroExpediente)
	   		throws NSJPNegocioException;
	
    /**
     * Permite buscar las reincidencias de un elemnto:
     * Si es un Automovil, permitira filtrar por el numero de serie y por el numero de placas
     * Si es un Arma, permitira filtrar por el numero de serie
     * Se es una persona, permitira filtrar por el nombre y apellidos
     * @param elementoDTO
     * @return List<CasoDTO> 
     * @throws NSJPNegocioException
     */
    public List<CasoDTO> buscarReincidenciaDeElementos(ElementoDTO elementoDTO) throws NSJPNegocioException;
    
    /**
     * Permite registrar reincidencias de un elemento
     * @param idElemento
     * @param idCasos
     * @param idFuncionario
     * @return List<RelacionReincidenciaDTO>: Devuelve el id de las relaciones registradas
     * @throws NSJPNegocioException
     */
    public List<RelacionReincidenciaDTO> registrarReinicidencias(Long idElemento,
			List<Long> idCasos, Long idFuncionario) throws NSJPNegocioException;
    
	/**
	 * Permite consultar las reincidencias de un Elemento(Vehiculo o persona)
	 * @param idElemento
	 * @return List<RelacionReincidenciaDTO> 
	 */
	public List<RelacionReincidenciaDTO> consultarReincidenciasXElemento(Long idElemento) throws NSJPNegocioException;
	
	/**
	 * Metodo para para Anular el Elemento de acuerdo al idElemento,
	 * considerando las relaciones asociadas a él, de acuerdo a:
	 * -Si el elemento es un Objeto se elimina:
	 * 		-Evidencia
	 * -Si el elemento es un Involucrado se elimina:
	 * 		-Relaciones con otros involucrados
	 * 		-Relaciones con Delito Persona.  
	 * 
	 * @param idElemento
	 * @throws NSJPNegocioException
	 */
	public Boolean anularElemento(Long idElemento) throws NSJPNegocioException;

	/**
	 * Servicio que se encarga de consultar las  
	 * relaciones asociadas al elemento ya sea para:
	 * -Si el elemento es un Objeto se consulta:
	 * 		-Evidencia
	 * -Si el elemento es un Involucrado se consulta:
	 * 		-Relaciones con otros involucrados
	 * 		-Relaciones con Delito Persona. 
	 * 
	 * Regresa una lista de cadenas, indicando :
	 * -Si el elemento es un Objeto indica:
	 * 		-Evidencia
	 * -Si el elemento es un Involucrado indica:
	 * 		-La calidad del elemento relacionado
	 * 		-El número de delitos relacionados
	 * tipo de calidad de los elementos  
	 * @param idElemento
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<String> consultarRelacionesElemento(Long idElemento) throws NSJPNegocioException;
	
    
}
