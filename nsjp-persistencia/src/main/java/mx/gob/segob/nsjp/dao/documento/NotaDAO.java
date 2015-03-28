/**
* Nombre del Programa : NotaDAO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01/07/2011
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

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Nota;


/**
 * Contrato del objeto de acceso a datos para la entidad de Nota.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface NotaDAO extends GenericDao<Nota, Long>{

	/**
	 * Operación que realiza la funcionalidad de consultar las notas asociadas a un documento.
	 * Recibe el identificador del documento.
	 * 
	 * @param idDocumento
	 * @return Devuelve un listado de notas asociadas al documento
	 */
	public List<Nota> consultarNotasPorDocumento(Long idDocumento);

    /**
     * Asocia un nota a un documento. Para ser utilizada correctamente, la nota
     * no debe tener un id ya que el sistema le asigna uno automaticamente
     * y el documento debe tener un id asociado.
     * @param nota La nueva nota que sera asociada al documento.
     * @param documento El documento al que se le asociara la nota.
     */
    void asociarNotaADocumento(Nota nota, Documento documento);

    /**
     * Actualiza una nota existente con los datos distintos de null dentro del
     * objeto {@code nota}
     * @param nota La nota que contiene los datos a actualizar. Se requiere
     * distinta de {@code null} y tener un id asignado.
     */
    void actualizarNota(Nota nota);

}
