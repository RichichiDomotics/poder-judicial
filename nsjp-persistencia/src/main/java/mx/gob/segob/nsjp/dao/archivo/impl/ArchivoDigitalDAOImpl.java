/**
 * Nombre del Programa : ArchivoDigitalDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación para accesar a la entidad archivo digital
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
package mx.gob.segob.nsjp.dao.archivo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.ArchivoDigital;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación para accesar a la entidad archivo digital.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class ArchivoDigitalDAOImpl
        extends
            GenericDaoHibernateImpl<ArchivoDigital, Long>
        implements
            ArchivoDigitalDAO {
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO#consultarContenidoPorDocumentoOArchivo(java.lang.Long, boolean)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public byte[] consultarContenidoPorDocumentoOArchivo(Long id,
			boolean esArchivo) throws NSJPNegocioException{
		String query = null;
		byte []contenido = null;		
		if(esArchivo){
			query = "select archivo.contenido from ArchivoDigital archivo where archivo.archivoDigitalId = ?";
		}else{
			query = "select doc.archivoDigital.contenido from Documento doc where doc.documentoId = ?";
		}
		
		List res = getHibernateTemplate().find(query,id);
		if(res != null && !res.isEmpty() && res.get(0)!=null){
			contenido = (byte[])res.get(0);
		}
		
		return contenido;
	}

	@Override
	public ArchivoDigital consultarArchivoDigitalPorDocumento(Long idDocumento) throws NSJPNegocioException{
	
		final StringBuffer hqlQuery = new StringBuffer();
		
		hqlQuery.append(" SELECT new ArchivoDigital(d.archivoDigital.archivoDigitalId, d.archivoDigital.nombreArchivo, d.archivoDigital.tipoArchivo, d.archivoDigital.contenido) ");
		hqlQuery.append(" FROM Documento d");
		hqlQuery.append(" WHERE d.documentoId="+idDocumento);

		Query hbq = super.getSession().createQuery(hqlQuery.toString());
		return (ArchivoDigital) hbq.uniqueResult();
	}

	public ArchivoDigital consultarArchivoDitalSinContenidoPorActividad(Long idExpediente,
			final Actividades actividad) throws NSJPNegocioException {
		final StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append("select new ArchivoDigital(a.archivoDigitalId, a.nombreArchivo, a.tipoArchivo) ");
		hqlQuery.append("from ArchivoDigital a ");
		hqlQuery.append("where a.documento.actividads.tipoActividad = "+actividad.getValorId()+" ");
		hqlQuery.append("and a.documento.expediente.expedienteId= "+idExpediente);

		Query hbq = super.getSession().createQuery(hqlQuery.toString());
		return (ArchivoDigital) hbq.uniqueResult();

	}	
	
	public byte[] consultarArchivoDigitalXElementoId(Long idElemento) throws NSJPNegocioException{		
		final StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append("select a.contenido ");
		hqlQuery.append(" from ArchivoDigital a");
		hqlQuery.append(" where a.archivoDigitalId = ");
		hqlQuery.append("(select e.archivoDigital.archivoDigitalId FROM Elemento e where e.elementoId= "+idElemento+")");

		Query hbq = super.getSession().createQuery(hqlQuery.toString());
		return (byte[])hbq.uniqueResult();
	}
	
}
