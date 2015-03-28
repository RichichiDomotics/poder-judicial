package mx.gob.segob.nsjp.dao.evidencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Evidencia;

public interface EvidenciaDAO extends GenericDao<Evidencia, Long> {

    /**
     * Consulta las evidencias asociadas a una cadena de custodia.
     * @param cadenaDeCustodia El número de folio de la cadena de custodia.
     * @return Devuelve el listado de evidencias asociadas a la cadena de
     * custodia.
     * En caso de no existir una cadena de custodia con el numero de
     * folio o en caso de no existir evidencias asociadas a la cade de custodia,
     * regresa la lista vacia.
     *
     * De cada evidencia se consultan los siguientes datos:
     * - Identificador de la evidencia
     * - Información de la evidencia
     * - Hora del levantamiento de la evidencia
     * - Origen de la evidencia
     * - Último eslabón asociado (Nota1)
     * - Número de eslabón
     * - Tipo de eslabón
     * - Almacén (Nota2)
     * - Tipo de baja de indicio o evidencia
     * @throws NSJPNegocioException
     */
    List<Evidencia> consultarEvidenciasXCadenaCustodia(CadenaDeCustodia cadenaDeCustodia);
    /**
     * 
     * @param surId
     * @param idCadenaCus
     * @return
     */
    List<Eslabon> consultarEvidenciasResguardadasXUsario(Long surId, Long idCadenaCus);
    /**
     * Operación que realiza la funcionalidad de consultar las evidencias asociadas al folio de una cadena de custodia en función de la disponibilidad solicitada(estatus).
     * @param cadenaDeCustodiaId
     * @param estatus
     * @return
     */
	List<Evidencia> consultarEvidenciasXCadenaYEstatus(Long cadenaDeCustodiaId,
			Long estatus);

        List<Evidencia> consultarEvidenciaPorAlmacen(Almacen almacen);
        
    List<Evidencia> consultarEvidenciasXCadena(Long cadenaId);
    
    Evidencia consultarEvidenciaXObjetoId(Long IdObjeto);
	
    /**
	 * Operación que permite consultar las evidencias por almacen, opcionalmente por estatus y opcionalmente por caso
	 * @param almacenDTO: idAlmacen
	 * @param estatusEv: null=sin importar estatus; -1=Solicitudes de AMP; valor=por estatus  
	 * @param casoDTO: idCaso
	 * @return
	 */
    List<Evidencia> consultarevidenciaXAlmacenXEstatus(
			Long identificadorAlmacen, Long estatusEv, Long idCaso);
    

}
