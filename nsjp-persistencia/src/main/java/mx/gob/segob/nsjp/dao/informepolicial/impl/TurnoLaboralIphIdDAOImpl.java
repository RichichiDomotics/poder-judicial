package mx.gob.segob.nsjp.dao.informepolicial.impl;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.informepolicial.TurnoLaboralIphIdDAO;
import mx.gob.segob.nsjp.model.TurnoLaboralIphId;

import org.springframework.stereotype.Repository;

@Repository
public class TurnoLaboralIphIdDAOImpl 
	   extends GenericDaoHibernateImpl<TurnoLaboralIphId,Long>
	   implements TurnoLaboralIphIdDAO {

}
