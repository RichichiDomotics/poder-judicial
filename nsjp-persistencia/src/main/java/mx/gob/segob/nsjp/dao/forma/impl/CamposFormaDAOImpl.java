/**
* Nombre del Programa : CamposFormaDAOImpl.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 02/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Implementación del objeto de acceso a datos para la manipulación
* de los campos que se llenarán automáticamente al emitir un documento de cierto tipo de forma
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
package mx.gob.segob.nsjp.dao.forma.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.forma.CamposFormaDAO;
import mx.gob.segob.nsjp.model.CamposForma;

/**
 * Implementación del objeto de acceso a datos para la manipulación
 * de los campos que se llenarán automáticamente al emitir un documento de cierto tipo de forma
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
@Repository
public class CamposFormaDAOImpl extends GenericDaoHibernateImpl<CamposForma, Long> implements CamposFormaDAO {
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.forma.CamposFormaDAO#obtenerCamposForma()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CamposForma> obtenerCamposForma() {
		return getHibernateTemplate().find("from CamposForma c order by c.camposFormaId");
	}

	

}
