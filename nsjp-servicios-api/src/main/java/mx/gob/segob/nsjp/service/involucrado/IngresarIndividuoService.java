/**
 * Nombre del Programa : IngresarIndividuoService.java                                    
 * Autor: Vladimir Aguirre Piedragil
 * Compania: Ultrasist                                                
 * Proyecto: NSJP 
 * Fecha: 29 Mar 2011 
 * Marca de cambio: N/A                                                     
 * Descripcion General: Interface para el servicio para registrar individuo.
 * Programa Dependiente: N/A                                                      
 * Programa Subsecuente: N/A                                                      
 * Cond. de ejecucion: N/A                                                      
 * Dias de ejecucion: N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */
package mx.gob.segob.nsjp.service.involucrado;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * Caso de Uso con la funcionalidad de ingresar los datos de un individuo de
 * acuerdo a su calidad: Denunciante, representante legal, víctima, testigo,
 * probable responsable, contacto organizacional, quien detuvo, tutor ó
 * traductor. El usuario podrá seleccionar el parentesco del individuo con la
 * víctima o probable responsable, tomar una foto del individuo o indicar si el
 * individuo es servidor público e ingresar los datos correspondientes si así lo
 * requiere.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface IngresarIndividuoService {
    /**
     * Método para insertar el endividuo.
     * 
     * @param individuo
     *            DTO con los valores a guardar.
     * @return El Identificador del indiduo registrado
     * @throws NSJPNegocioException
     *             En caso de que exista un error al guardar.
     */
    Long ingresarIndividuo(InvolucradoDTO individuo)
            throws NSJPNegocioException;
    
    /**
     * Metodo para registrar a la victima
     * 
     * @param victima
     * @return 
     * @throws NSJPNegocioException
     */
    Long ingresarVictima(InvolucradoDTO victima)
    		throws NSJPNegocioException;

    /**
	 * Operación que guarda un involucrado como Defensor y lo asigna por relación a un probable responsable involucrado
	 * @param involucradoDTO: Nuevo defensor
	 * @param probableResponsableId: Id Involucrado Existente
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarDefensorAsignadoInvolucrado(InvolucradoDTO involucradoDTO,
			Long probableResponsableId)throws NSJPNegocioException;

	
	/**
	 * Cuando se esta recibiendo una objeto involucrado que provien de otra institucion,
	 * este método revisa que el involucrado que se desea insertar no exista en la base,
	 * esto a traves del folio elemento del involucrado.
	 * @param involucradoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long ingresarIndividuoInterInstitucion(InvolucradoDTO involucradoDTO, boolean siExisteAcutaliza)
			throws NSJPNegocioException;

}

