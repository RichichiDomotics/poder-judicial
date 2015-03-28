/**
* Nombre del Programa : ConsultarInvolucradosMedidaService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 09/08/2011
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
package mx.gob.segob.nsjp.service.involucrado;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * Contrato del service para consultar los involucrados y sus las Medidas.  
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ConsultarInvolucradosMedidaService {
	
	/**
	 * Servicio que realiza la funcionalidad de consultar los involucrados que 
	 * tengan alguna medida (alterna) cuya fecha fin de medida  
	 * sea mayor o igual a la fecha (proporcionada o actual del sistema), además de que 
	 * sean asociados a un funcionario particular
	 * 
	 * Consulta la medida con las relaciones de
	 *  -número de expediente de carpeta de ejecución, - ***Asociada al involucrado.
	 *  -número de causa asociado al expediente de carpeta de ejecución, *** Asociada al involucrado.
	 *  -nombre completo del sentenciado (nombre, apellido paterno, apellido materno),	 *   

	 *  DATOS DE LA MEDIDA: **Actualmente esta definida para medida Alterna
	 *  -tipo de medida, 
	 *  -descripción de la medida,
	 *  -fecha inicio de medida ,
	 *  -fecha fin de medida 
	 *  -periodicidad de la medida (si existe)
	 *  -Conjunto de incidencias 
	 *  -Domicilio
	 *  -Involucrado
	 * 
	 * @param cFuncionario  clave del funcionario 
	 * @param fechaActual  la fecha actual del sistema
	 * @param esMedidaAlterna  determina si la medida es Alterna
	 * @return Lista de involucrados con Medida Alterna
	 * @throws NSJPNegocioException
	 */
	List<InvolucradoDTO> consultarInvolucradosMedidasPorFecha(Long cFuncionario, Date fechaFin, Boolean esMedidaAlterna ) throws NSJPNegocioException;
	
}
