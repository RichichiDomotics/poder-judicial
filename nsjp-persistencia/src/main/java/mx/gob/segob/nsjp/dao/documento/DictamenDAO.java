/**
 * Nombre del Programa : DictamenDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 Jul 2011
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
package mx.gob.segob.nsjp.dao.documento;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Dictamen;
import mx.gob.segob.nsjp.model.Funcionario;

/**
 * Contrato del dao para Dictamen.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface DictamenDAO extends GenericDao<Dictamen, Long> {

    Long existeDocumentoDefinitivo(Long idSolicitudPericial);
    /**
     * Consulta los dictamenes recibidos de servicios periciales
     * @param funcionario Funcionario que recibe
     * @param tipoDocumento tipo de documento a filtrar, generalmente dictamen o informe
     * @return Lista de dictamenes recibidos
     */
    List<Dictamen> consultarDictamenesRecibidosParaUsuario(Formas tipoDocumento,Funcionario funcionario);
}
