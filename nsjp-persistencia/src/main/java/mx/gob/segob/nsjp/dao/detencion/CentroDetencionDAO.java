/**
*
* Nombre del Programa : CentroDetencionDAO                                    
* Autor                  : Cuauhtemoc Paredes Serrano                                              
 Compania               : Ultrasist                                                
* Proyecto               : NSJP                    Fecha: 22/09/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Interface para la entidad CentronDetencion.                      
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
package mx.gob.segob.nsjp.dao.detencion;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CentroDetencion;

public interface CentroDetencionDAO extends GenericDao<CentroDetencion, Long>{

	List<CentroDetencion> consultarCentrosDetencionPorTipo(
			Long tipoCentroDetencion);
	
	

}
