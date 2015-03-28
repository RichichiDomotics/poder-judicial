/**
 * Nombre del Programa : GenericDaoHibernateImpl.java                                    
 * Autor: Vladimir Aguirre Piedragil
 * Compania: Ultrasist                                                
 * Proyecto: NSJP 
 * Fecha: 29 Mar 2011 
 * Marca de cambio: N/A                                                     
 * Descripcion General: Implementación de DAO genérica de la que deben extender todas las implementaciones de los DAO's de la aplicación y poder agrupar funcionalidad común (DRY).
 * Programa Dependiente: N/A                                                      
 * Programa Subsecuente: N/A                                                      
 * Cond. de ejecucion: N/A                                                      
 * Dias de ejecucion: N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */
package mx.gob.segob.nsjp.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Elemento;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * Implementación de métodos genericos de persistencia.
 * 
 * @author vaguirre
 * 
 * @param <T>
 *            Clase persistente.
 * @param <PK>
 *            Llave primaria de la clase persistente.
 */
@SuppressWarnings(value = "unchecked")
public abstract class GenericDaoHibernateImpl<T, PK extends Serializable>
        extends
            HibernateDaoSupport implements GenericDao<T, PK> {
    /**
     * Tipo del Entity.
     */
    private Class<T> type = null;

    /**
     * Objeo parametrizado.
     */
    private T object;

    /**
     * @param type
     *            the type to set
     */
    public void setType(Class<T> type) {
        this.type = type;
    }

    /**
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#create(java.lang.Object)
     */
    public PK create(T o) {
        if (logger.isDebugEnabled()) {
            logger.debug("Guardando el objeto " + o);
        }

        PK result;
        try {
            result = (PK) this.getHibernateTemplate().save(o);

            if (o instanceof Elemento) {
                flush();
                if (StringUtils.isBlank(((Elemento) o).getFolioElemento())) {
                    logger.debug("Calculando folioElemento..." + result);
                    ((Elemento) o).setFolioElemento(this
                            .consultarInsitucionActual().getMonograma()
                            + String.valueOf(result));
                    this.update(o);
                }
            }
        } catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Se guardo el objeto con el ID: " + result);
        }
        return result;
    }

    /**
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#createAll(List)
     */
    public void createAll(final List<T> lista) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persistiendo: " + lista.size() + " elementos.");
        }
        getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                int cont = 0;
                for (T object : lista) {
                    session.save(object);
                    cont++;
                    if (cont == 10) {
                        session.flush();
                        cont = 0;
                    }
                }
                return null;
            }
        });
    }

    /**
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#saveOrUpdate(java.lang.Object)
     */
    public void saveOrUpdate(T o) {
        if (logger.isDebugEnabled()) {
            logger.debug("Guardando/Actualizando el objeto " + o);
        }

        this.getHibernateTemplate().saveOrUpdate(o);
        flush();
        logger.debug("Se guardo/actualizo el objeto");
    }

    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#read(java.io.Serializable)
     */
    public T read(PK id) {
        if (logger.isDebugEnabled()) {
            logger.debug("Buscando el objeto con Id: " + id + " del "
                    + getType());
        }
        T result = (T) this.getHibernateTemplate().get(getType(), id);
        flush();
        if (logger.isDebugEnabled()) {
            logger.debug("Se obtuvo el objeto: " + result);
        }
        return result;
    }

    /**
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#update(java.lang.Object)
     */
    public void update(T o) {
        if (logger.isDebugEnabled()) {
            logger.debug("Actualizando el objeto " + o);
        }

        this.getHibernateTemplate().update(o);
        flush();
        if (logger.isDebugEnabled()) {
            logger.debug("Se actualizo el objeto.");
        }
    }

    /**
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#delete(java.lang.Object)
     */
    public void delete(T o) {
        if (logger.isDebugEnabled()) {
            // logger.debug("Eliminando el objeto " + o);
        }
        this.getHibernateTemplate().delete(o);
        flush();
        if (logger.isDebugEnabled()) {
            logger.debug("Se elimino el objeto.");
        }
    }

    /**
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#deleteAll()
     */
    public void deleteAll() {
        if (logger.isDebugEnabled()) {
            logger.debug("Eliminandos todos los registros de tipo: "
                    + getType().getSimpleName());
        }
        getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                return session.createQuery(
                        "delete from " + getType().getSimpleName())
                        .executeUpdate();
            }
        });
    }

    /**
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#deleteAll(List<T>)
     */
    public void deleteAll(List<T> list2Delete) {
        if (logger.isDebugEnabled()) {
            logger.debug("Eliminandos todos los registros de tipo: "
                    + getType().getSimpleName());
        }
        getHibernateTemplate().deleteAll(list2Delete);
    }

    /**
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#deleteAll(java.io.Serializable[])
     */
    public void deleteAll(PK[] id) {
        for (PK pk : id) {
            delete(read(pk));
        }
    }

    /**
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#merge(java.lang.Object)
     */
    public void merge(T newInstance) {
        if (logger.isDebugEnabled()) {
            logger.debug("Guardando el objeto " + newInstance);
        }
        this.getHibernateTemplate().merge(newInstance);
        flush();
        logger.debug("Se guardo el objeto.");
    }

    /**
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#findAllId()
     */
    public List<PK> findAllId() {

        // getHibernateTemplate().setAlwaysUseNewSession(true);
        if (logger.isDebugEnabled()) {
            logger.debug("Obteniendo todos los elementos de tipo: " + getType());
        }
        // List<T> result = this.getHibernateTemplate().loadAll(getType());
        List<PK> result = getHibernateTemplate().executeFind(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Criteria criteria = session.createCriteria(getType());
                        criteria.setProjection(Projections.id());
                        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                        return criteria.list();
                    }
                });
        if (logger.isDebugEnabled()) {
            logger.debug("Se obtuvieron " + result.size() + " elementos.");
        }

        return result;
    }

    /**
     * Devuelve el objeto unico de la consulta
     * 
     * @param query
     * @return
     */
    protected T uniqueResult(Query query) {
        Object object = query.uniqueResult();
        if (object instanceof HibernateProxy) {
            HibernateProxy hibernateProxy = (HibernateProxy) object;
            LazyInitializer lazyInitializer = hibernateProxy
                    .getHibernateLazyInitializer();
            if (lazyInitializer != null) {
                object = lazyInitializer.getImplementation();
            }
        }
        return (T) object;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#flush()
     */
    public void flush() {
        this.getHibernateTemplate().flush();
    }

    /**
     * Obtiene por reflexion el tipo del DomainObject que usa este DAO
     * 
     * @return the type
     */
    protected Class<T> getType() {
        if (type == null) {
            Class clazz = getClass();

            while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)) {
                clazz = clazz.getSuperclass();
            }

            type = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass())
                    .getActualTypeArguments()[0];
        }

        return type;
    }

    /**
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#initialize(java.lang.Object)
     */
    public void initialize(Object proxy) {
        if (proxy != null) {
            super.getHibernateTemplate().initialize(proxy);
        }
    }

    /**
     * @see mx.gob.segob.nsjp.dao.base.GenericDao#saveOrUpdateAll(java.util.List)
     */
    public void saveOrUpdateAll(List<T> list2Update) {
        if (list2Update == null || list2Update.isEmpty()) {
            return;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Guardando/Actualizando " + list2Update.size()
                    + " objetos");
        }
        this.getHibernateTemplate().saveOrUpdateAll(list2Update);
        flush();
        logger.debug("Se guardo/actualizo el objeto");
    }

    /**
     * Regresa la instancia del objeto.
     * 
     * @return
     */
    protected T getObject() {
        return object;
    }

    /**
     * Asigna el objeto
     * 
     * @param o
     * @return
     */
    protected T setObject(T o) {
        object = o;
        return object;
    }

    /**
     * Asigna el la fabrica se sesiones de Hibernate. <br>
     * Usado para la inyección de dependecias.
     * 
     * @param sessionFactory
     *            Fabrica de sesiones.
     */
    @Autowired
    public void setHibernateSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    public ConfInstitucion consultarInsitucionActual() {
        final StringBuffer query = new StringBuffer();
        query.append("FROM ConfInstitucion c ");
        query.append("WHERE c.esInstalacionActual = true ");
        logger.debug("query :: " + query);
        Query hbq = super.getSession().createQuery(query.toString());
        return (ConfInstitucion) hbq.uniqueResult();
    }

    /**
     * Método para ejecutar el <code>query</code> aplicando los ajustes de la
     * paginación y recuperar el <b>total de registros</b>, metiendo este al
     * <code>PaginacionThreadHolder</code>.
     * 
     * @param queryStr
     *            Consulta a ejecutar
     * @param pag
     *            Objeto obtenido de <code>PaginacionThreadHolder</code>
     * @return El resultado de la consulta.
     */
    protected List ejecutarQueryPaginado(final StringBuffer queryStr,
            final PaginacionDTO pag) {
        final Query query = super.getSession().createQuery(queryStr.toString());
        if (pag != null && pag.getPage() != null && !pag.isPaginacionHecha()) {
            query.setFirstResult(pag.getFirstResult());
            if (pag.getRows() != null & pag.getRows() > 0) {
                query.setMaxResults(pag.getRows());
            } else {
                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
            }
        }
        logger.debug("query :: " + query);
        final List resp = query.list();
        if (logger.isDebugEnabled()) {
            logger.debug("resp.size() :: " + resp.size());
        }
		if (pag != null && pag.getPage() != null) {
			if (!pag.isPaginacionHecha()) {
				pag.setPaginacionHecha(true);
				query.setFirstResult(0);
				query.setMaxResults(-1);
				final List temp = query.list();
				logger.debug("temp.size() :: " + temp.size());
				pag.setTotalRegistros(new Long(temp.size()));
			}
			PaginacionThreadHolder.set(pag);
		}
        return resp;
    }

    public List<T> findAll(final String order, final boolean asc) {
        final StringBuffer query = new StringBuffer();
        query.append("FROM ");
        query.append(getType().getSimpleName());
        
        if (StringUtils.isNotBlank(order)){
            query .append(" order by ");
            query.append(order);
            if (asc) {
                query.append(" asc ");
            } else {
                query.append(" desc ");
            }
        }
        logger.debug("query :: " + query);
        return this.ejecutarQueryPaginado(query,  PaginacionThreadHolder.get());
    }

}
