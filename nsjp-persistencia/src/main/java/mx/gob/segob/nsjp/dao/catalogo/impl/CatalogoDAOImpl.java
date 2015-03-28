/**
 *
 * Nombre del Programa : NarrativaDAO.java                                    
 * Autor                            : Cesar Agustin                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 30/03/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Implementación para el DAO para obtener catalogos                      
 * Programa Dependiente  :N/A                                                      
 * Programa Subsecuente :N/A                                                      
 * Cond. de ejecucion        :N/A                                                      
 * Dias de ejecucion          :N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */
package mx.gob.segob.nsjp.dao.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.CatalogoDAO;
import mx.gob.segob.nsjp.model.Catalogo;
import mx.gob.segob.nsjp.model.Valor;

import org.springframework.stereotype.Repository;

/**
 * Implementación para el DAO para obtener catalogos.
 * 
 * @author vaguirre
 * @since 1.0
 * 
 */
@Repository
public class CatalogoDAOImpl extends GenericDaoHibernateImpl<Catalogo, Long>
		implements CatalogoDAO {

	@Override
	public List<Valor> recuperarCatalogoSencillo(Catalogos idCatalogo) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select new Valor(v.valorId, v.valor) from Valor v ");
		queryStr.append(" where v.campoCatalogo.catalogo.catalogoId = ");

		queryStr.append(idCatalogo.ordinal());
		queryStr.append(" and v.registro.esActivo = 1");
		queryStr.append(" and v.campoCatalogo.esLlave = 1");
		queryStr.append(" order by v.valor");
		return super.ejecutarQueryPaginado(queryStr,  PaginacionThreadHolder.get());
	}

	@Override
	public List<Valor> recuperarCatalogoDependiente(Catalogos idCatalogoHijo,
			Long idValorPadre) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select new Valor(v.valorId, v.valor) from Valor v ");
		queryStr.append(" where v.campoCatalogo.catalogo.catalogoId = ");
		queryStr.append(idCatalogoHijo.ordinal());
		queryStr.append(" and v.campoCatalogo.esLlave = 1");
		queryStr.append(" and v.registro.esActivo = 1");
		queryStr.append(" and v.registro.registroId IN (");
		queryStr.append(" select r.registroId from Registro r ");
		queryStr.append(" left join r.valors val ");
		queryStr.append(" where val.campoCatalogo.esRecursivo = 1 and val.valor = '");
		queryStr.append(idValorPadre);
		queryStr.append("' group by r.registroId )");
		queryStr.append(" order by v.valor");
		@SuppressWarnings("unchecked")
		List<Valor> resp = super.getHibernateTemplate().find(
				queryStr.toString());
		if (logger.isDebugEnabled()) {

			logger.debug("resp.size() :: " + resp.size());
		}
		return resp;
	}

	@Override
	public List<Valor> recuperarCatalogoCompleto(Catalogos idCatalogo) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select new Valor(v.valorId, v.valor, v.registro.registroId, v.campoCatalogo.nombreCampo, v.campoCatalogo.esLlave)");
		queryStr.append(" from Valor v ");
		queryStr.append(" where v.campoCatalogo.catalogo.catalogoId = ");

		queryStr.append(idCatalogo.ordinal());
		queryStr.append(" and v.registro.esActivo = 1");
		queryStr.append(" order by v.campoCatalogo.esLlave desc, v.valor");
		@SuppressWarnings("unchecked")
		List<Valor> resp = super.getHibernateTemplate().find(
				queryStr.toString());
		if (logger.isDebugEnabled()) {

			logger.debug("resp.size() :: " + resp.size());
		}
		return resp;
	}

    @Override
    public List<Catalogo> obtenerTodos() {
        return super.getHibernateTemplate().find("from Catalogo order by nombre");
    }

}
