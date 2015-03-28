/**
 *
 * Nombre del Programa : ConsultarObjetosPorTipoConFolioDeCustodiaServiceImpl.java                                    
 * Autor                            : Cesar Agustin                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 14/09/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Interface del servicio para obtener objetos por tipo                      
 * Programa Dependiente  :N/A                                                      
 * Programa Subsecuente :N/A                                                      
 * Cond. de ejecucion        :N/A                                                      
 * Dias de ejecucion          :N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */

package mx.gob.segob.nsjp.service.objeto;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;

/**
 * 
 * @version 1.0
 * @author rgama
 * @version 1.0
 */
public interface ConsultarObjetosPorTipoConFolioDeCustodiaService {
	
	/**
	 * Metodo que permite obtener objetos de cierto tipo aosicados a un expediente
	 * @param tipoObjeto
	 * @param input
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ObjetoDTO> obtenerObjetosPorTipo(Objetos tipoObjeto, ExpedienteDTO input)
	throws NSJPNegocioException;
}
