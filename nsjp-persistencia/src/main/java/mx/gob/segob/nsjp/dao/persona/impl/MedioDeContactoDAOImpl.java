/**
*
* Nombre del Programa : MedioDeContactoDAOImpl.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Implementacion para el DAO de la entidad Medio de contacto                      
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
package mx.gob.segob.nsjp.dao.persona.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.persona.MedioDeContactoDAO;
import mx.gob.segob.nsjp.model.MedioDeContacto;

/**
 * 
 * @author CesarAgustin
 * @version 1.0
 */
@Repository
public class MedioDeContactoDAOImpl extends GenericDaoHibernateImpl<MedioDeContacto, BigDecimal>
		implements MedioDeContactoDAO {

}
