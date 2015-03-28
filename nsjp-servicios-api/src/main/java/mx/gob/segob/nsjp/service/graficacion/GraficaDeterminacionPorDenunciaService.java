/**
* Nombre del Programa : GraficaDeterminacionPorDenuncia.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para obtener la informacion requerida para la grafica 
 * 							determinacion por denuncia.
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
package mx.gob.segob.nsjp.service.graficacion;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Contrato del servicio para obtener la informacion requerida para la grafica 
 * determinacion por denuncia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface GraficaDeterminacionPorDenunciaService {

	/**
	 * Obtiene las audienciad de un tipo determindo y de acuerdo a un rango de fechas de apertura del numero
	 * expediente relacionado
	 * @author cesarAgustin
	 * @param fechaInicial
	 * @param fechaFin
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<Object[]> obtenerAudienciasJudicializadasPorMes(
			Date fechaInicial, Date fechaFin) throws NSJPNegocioException;

	/**
	 * Obtiene el total por mes de los numeros de expediente que se encuentren en un estatus
	 * determinado y dentro de un rango de fechas.
	 * @author cesarAgustin
	 * @param inicial
	 * @param fin
	 * @param archivoTemporal
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<Object[]> obtenerNumExpPorEstatusYMes(Date inicial, Date fin, EstatusExpediente estatusExpediente);
}
