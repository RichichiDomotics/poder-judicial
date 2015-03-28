/**
 * Nombre del Programa : NotaDAOImpl.java
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
package mx.gob.segob.nsjp.dao.documento.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.documento.NotaDAO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Nota;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion del objeto de acceso a datos para la entidad Nota.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Repository
public class NotaDAOImpl extends GenericDaoHibernateImpl<Nota, Long> implements NotaDAO {

    /**
     * Operación que realiza la funcionalidad de consultar las notas asociadas a un documento.
     * Recibe el identificador del documento.
     *
     * @param idDocumento
     * @return Devuelve un listado de notas asociadas al documento
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Nota> consultarNotasPorDocumento(Long idDocumento) {
        StringBuffer queryString = new StringBuffer();
        queryString.append(" from Nota t where t.documento.documentoId = ");
        queryString.append(idDocumento);

        logger.debug("Query:" + queryString);
        Query query = super.getSession().createQuery(queryString.toString());

        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void asociarNotaADocumento(Nota nota, Documento documento) {
        nota.setDocumento(documento);
        create(nota);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizarNota(Nota nota) {
        StringBuilder sb = new StringBuilder();
        if (nota != null || nota.getIdNota() != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Ejecutando una actualizacion para la nota = "
                        + nota);
            }
            Nota modificable = read(nota.getIdNota());
            String descripcion = nota.getDescripcion();
            if (descripcion != null) {
                modificable.setDescripcion(descripcion);
            }
            Documento documento = nota.getDocumento();
            if (documento != null && documento.getDocumentoId() != null) {
                modificable.setDocumento(documento);
            }
            Date fechaActualizacion = nota.getFechaActualizacion();
            if (fechaActualizacion != null) {
                modificable.setFechaActualizacion(fechaActualizacion);
            }
            Date fechaCreacion = nota.getFechaCreacion();
            if (fechaCreacion != null) {
                modificable.setFechaCreacion(fechaCreacion);
            }
            String nombreNota = nota.getNombreNota();
            if (nombreNota != null) {
                modificable.setNombreNota(nombreNota);
            }
            update(modificable);
        }
    }
}
