/**
 * Nombre del Programa : GenericDao.java                                    
 * Autor: Vladimir Aguirre Piedragil
 * Compania: Ultrasist                                                
 * Proyecto: NSJP 
 * Fecha: 29 Mar 2011 
 * Marca de cambio: N/A                                                     
 * Descripcion General: Interface de DAO genérica de la que deben extender todas las interfaces de los DAO's de la aplicación y poder agrupar funcionalidad común (DRY).
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
package mx.gob.segob.nsjp.dao.base;

import java.io.Serializable;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.model.ConfInstitucion;

/**
 * 
 * Interfase que define de manera generica las operaciones basicas de
 * persistencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 * @param <T>
 *            Clase persistente.
 * @param <PK>
 *            Llave primaria de la clase persistente.
 */
public interface GenericDao<T, PK extends Serializable> {
    /**
     * Persiste newInstance en la base de datos, posteriormente ejecuta un
     * <code>flush()</code>.
     * 
     * @param newInstance
     *            Instancia a persistir.
     * @return La PK recine generada
     */
    PK create(T newInstance);

    /**
     * Persiste una lista de registros.
     * 
     * @param lista
     *            Lista a persistir.
     */
    void createAll(List<T> lista);

    /**
     * Persiste o actualiza el objeto, posteriormente ejecuta un
     * <code>flush()</code>.
     * 
     * @param newInstance instancia a guardar o actualizar.
     */
    void saveOrUpdate(T newInstance);

    /**
     * Mezcla newInstance en la base de datos usando merge, posteriormente
     * ejecuta un <code>flush()</code>.
     * 
     * @param newInstance
     *            Instancia a mezclar
     */
    void merge(T newInstance);

    /**
     * Recupera un objeto usando el id indicado como llave primaria.
     * 
     * @param id PK
     * @return La insntacia del objeto, <code>null</code> en caso de que no
     *         exista ese ID.
     */
    T read(PK id);

    /**
     * Guarda los cambios hechos al objeto persistente.
     * 
     * @param transientObject
     *            Objeto a actualizar
     */
    void update(T transientObject);

    /**
     * Remueve el objeto de la base de datos.
     * 
     * @param persistentObject Instancia a borrar.
     */
    void delete(T persistentObject);

    /**
     * Remueve los objetos de la base de datos.
     * 
     * @return
     */
    void deleteAll();

    /**
     * Remueve los objetos de la base de datos.
     * 
     * @param list2Delete
     *            Lista con los objetos persistentes a eliminar.
     * @return
     */
    void deleteAll(List<T> list2Delete);

    /**
     * Remueve los objetos de la base de datos.
     * 
     * @param id
     *            Lista de Id de los objetos
     */
    void deleteAll(PK[] id);

    /**
     * Recupera las PK's.
     * 
     * @return
     */
    List<PK> findAllId();

    /**
     * Envía todos las sentencias pendientes a la base de datos.
     * 
     * @param persistentObject
     */
    void flush();

    /**
     * Actualiza una lista.
     * 
     * @param list2Update Lista de instancias a actualizar o insertar.
     */
    void saveOrUpdateAll(List<T> list2Update);
    
    public ConfInstitucion consultarInsitucionActual()
    throws NSJPNegocioException;
    
    /**
     * 
     * @param order
     * @param asc
     * @return
     */
    List<T> findAll(String order, boolean asc);
}
