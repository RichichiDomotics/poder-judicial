/**
* Nombre del Programa : ConsultarMedidaService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11/08/2011
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
package mx.gob.segob.nsjp.service.medida;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;

/**
 * Contrato del service para consultar Medidas
 * Es necesario saber el tipo de medida que se debera de consultar: Medida Cautelar o Medida Alterna
 * Actualmente se encuentra implimentado para Medida Alterna
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ConsultarMedidaService {

	/**
	 *  --DATOS DE LA MEDIDA: **Actualmente esta definida para Medida Alterna
	 *  -tipo de medida, 
	 *  -descripción de la medida,
	 *  -fecha inicio de medida ,
	 *  -fecha fin de medida 
	 *  -periodicidad de la medida (si existe)
	 *  -Conjunto de incidencias 
	 *  -Domicilio
	 *  -Involucrado
	 *   
	 * @param medidaDTO
	 * @param esMedidaAlterna  determina si la medida es Alterna 
	 * @return
	 * @throws NSJPNegocioException
	 */
	MedidaDTO consultarMedida(Long idMedida, Boolean esMedidaAlterna) throws NSJPNegocioException;
	
}
