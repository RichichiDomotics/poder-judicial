/**
* Nombre del Programa : MedidaDAO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/08/2011
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
package mx.gob.segob.nsjp.dao.medida;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Medida;

/**
 * Contrato para el objeto de acceso a datos de la Medida.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface MedidaDAO  extends  GenericDao<Medida, Long> {
	
	/**
	 * Operación que realiza la funcionalidad de consultar los involucrados que 
	 * tengan alguna medida (alterna) cuya fecha fin de medida  
	 * sea mayor o igual a la fecha (proporcionada o actual del sistema), además de que 
	 * sean asociados a un funcionario particular
	 * 
	 * @param cFuncionario
	 * @param esMedidaAlterna
	 * @param fechaFin
	 * @return
	 */
	List<Involucrado> consultarIdInvolucradosMedidasTipoPorFechas(Long cFuncionario, Long esMedidaAlterna, Date fechaFin);

	/**
	 * Cambia el estatus de una Medida ya sea Cautelar o Alternativa  
	 * @param idMedida : Long el Id de la medida (Cautelar/Alternativa)
	 * @param idEstatus : Long el Id del estatus (Obtenido del Enum EstatusMedida) 
	 * @return void
	 * @throws NSJPNegocioException
	 */
	void cambiarEstatusMedida(Long idMedida, Long idEstatus) throws NSJPNegocioException;

    Medida obtenerMedidaPorFolioDoc(String folioDocumento);
    
    /**
     * Permite obtener Medidas que tengan la "Fecha Fin" vencida
     * @return List<Medida>
     */
	public List<Medida> obtenerMedidasConFechaFinVencida();
	
	/**
	 * Permite obtener una medida en base a su identificardor
	 * @param idMedida
	 * @return Medida
	 */
    public Medida obtenerMedidaPorId(Long idMedida);

    
}
