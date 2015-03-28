package mx.gob.segob.nsjp.dao.informepolicial.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.informepolicial.InformePolicialHomologadoDAO;
import mx.gob.segob.nsjp.dao.informepolicial.InvolucradoIPHDAO;
import mx.gob.segob.nsjp.model.InformePolicialHomologado;
import mx.gob.segob.nsjp.model.InvolucradoIph;
import mx.gob.segob.nsjp.model.NombreDemografico;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InvolucradoIPHDAOImpl 
	   extends GenericDaoHibernateImpl<InvolucradoIph,Long>			 
	   implements InvolucradoIPHDAO{

	@Autowired
	InformePolicialHomologadoDAO informeDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InformePolicialHomologado> consultarFolioIPHPorFechaOPersona(Date fechaInicio, Date fechaFin, String nombrePersona) throws NSJPNegocioException {
		Query query;
		Query query2;
		List<InformePolicialHomologado> informes = new ArrayList<InformePolicialHomologado>();										
		StringBuffer queryString = new StringBuffer();
		List<InvolucradoIph> iIPH = new ArrayList<InvolucradoIph>();		
		List<NombreDemografico> personas = new ArrayList<NombreDemografico>();
		
		DateFormat df = DateFormat.getDateInstance(3);

		if(nombrePersona != null)
		{		
			personas = new ArrayList<NombreDemografico>();
			queryString.append("FROM NombreDemografico nd ")
			.append("where nd.nombre + ' ' + nd.apellidoPaterno + ' ' + nd.apellidoMaterno like '%" + nombrePersona + "%'");
			query = super.getSession().createQuery(queryString.toString());
			personas = query.list();			
			
			if(personas.size()>0)
			{
				queryString = new StringBuffer();			
				queryString.append("FROM InvolucradoIph iph ")
				.append("where iph.involucrado.elementoId in (");
					
				for(NombreDemografico row:personas)
				{
					queryString.append(row.getPersona().getElementoId() + ",");
				}
				queryString.deleteCharAt(queryString.length()-1);
				queryString.append(")");
					
				query2 = super.getSession().createQuery(queryString.toString());		
				iIPH =  query2.list();
				
				if(iIPH.size()>0)
				{
					queryString = new StringBuffer();
					queryString.append("FROM InformePolicialHomologado iph ")
					.append("where iph.informePolicialHomologadoId in (");
					
					for(InvolucradoIph row:iIPH){				
						queryString.append(row.getInformePolicialHomologado() + ",");								
					}
					queryString.deleteCharAt(queryString.length()-1);
					queryString.append(")");
					
					if(fechaInicio!=null && fechaFin!=null)
					{
						queryString.append(" and iph.fechaCaptura between '" + df.format(fechaInicio) + "' and '" + df.format(fechaFin) + "'");
					}
					
					query2 = super.getSession().createQuery(queryString.toString());		
					informes =  query2.list();
				}
			}
		}						
		
		
		if((fechaInicio!=null && fechaFin!=null) && nombrePersona==null)
		{									
			
			queryString = new StringBuffer();
			queryString.append("FROM InformePolicialHomologado iph ")
			.append("where iph.fechaCaptura between '" + df.format(fechaInicio) + "' and '" + df.format(fechaFin) + "'");
			
			query2 = super.getSession().createQuery(queryString.toString());		
			informes =  query2.list();
		}
		

				
		return informes;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InvolucradoIph> consultarInvolucradosDeIPH(
			Long informePolicialHomologadoId) {
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" FROM InvolucradoIph iiph");
		queryString.append(" WHERE iiph.informePolicialHomologado.informePolicialHomologadoId="+informePolicialHomologadoId);
		
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerCasosRemitidosPorMes(Date fechaInicio,
			Date fechaFin) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT MONTH(inv.informePolicialHomologado.fechaCaptura), COUNT(*) FROM InvolucradoIph inv ")
					.append("WHERE inv.esDetenido=").append(true).append(" AND ")
					.append("CONVERT(VARCHAR, inv.informePolicialHomologado.fechaCaptura,112) BETWEEN ")
					.append(DateUtils.formatearBD(fechaInicio)).append(" AND ").append(DateUtils.formatearBD(fechaFin))
					.append(" GROUP BY MONTH(inv.informePolicialHomologado.fechaCaptura)");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}	

}
