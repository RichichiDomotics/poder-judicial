/**
* Nombre del Programa : MedidaDAOImpl.java
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
package mx.gob.segob.nsjp.dao.medida.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.medida.MedidaDAO;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Medida;
import mx.gob.segob.nsjp.model.Valor;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación para el DAO de Medida
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Repository
public class MedidaDAOImpl extends GenericDaoHibernateImpl<Medida, Long> 
	implements MedidaDAO {
	
	@SuppressWarnings("unchecked")
	public List<Involucrado> consultarIdInvolucradosMedidasTipoPorFechas(Long cFuncionario, Long esMedidaAlterna, Date fechaFin){
		SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT I FROM Medida M, Involucrado I, MedidaAlterna MA ");
		queryStr.append("WHERE M.involucrado.elementoId = I.elementoId ");
		if(esMedidaAlterna!=null && esMedidaAlterna.equals(0L))
			queryStr.append(" AND M.documentoId = MA.documentoId");
		
		if(cFuncionario != null && cFuncionario>0)
			queryStr.append(" AND  M.funcionario.claveFuncionario = "+ cFuncionario);
		if(fechaFin!= null)
			queryStr.append(" AND CONVERT (date,M.fechaFin) >= '"+ formato.format(fechaFin)+"'");
		
		logger.info("Query"+queryStr);
		Query query = super.getSession().createQuery(queryStr.toString());	
		return query.list();
	}

	@Override
	public void cambiarEstatusMedida(Long idMedida, Long idEstatus) throws NSJPNegocioException{
		Medida m = this.read(idMedida);
		m.setEstatus(new Valor(idEstatus));
		
		this.update(m);
	}

    @Override
    public Medida obtenerMedidaPorFolioDoc(String folioDocumento) {

        StringBuffer queryString = new StringBuffer();
        queryString.append(" FROM Medida m  ")
                .append(" WHERE m.folioDocumento = '").append(folioDocumento)
                .append("'");
        Query query = super.getSession().createQuery(queryString.toString());
        return (Medida) query.uniqueResult();

    }
    
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Medida> obtenerMedidasConFechaFinVencida() {		
		Date fecha = new Date();
		DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");		
		StringBuffer queryString = new StringBuffer();
		queryString.append("select m FROM Medida m").
		append(" WHERE m.fechaFin < '" + dfm.format(fecha) + "'");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();	
	}
	
	
    @Override
    public Medida obtenerMedidaPorId(Long idMedida) {

        StringBuffer queryString = new StringBuffer();
        queryString.append(" FROM Medida m  ")
                .append(" WHERE m.documentoId = '").append(idMedida)
                .append("'");
        Query query = super.getSession().createQuery(queryString.toString());
        return (Medida) query.uniqueResult();

    }
}

