package mx.gob.segob.nsjp.dao.avisodetencion.impl;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.avisodetencion.AvisoDetencionDelitoDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.AvisoDetencionDelito;
import mx.gob.segob.nsjp.model.AvisoDetencionDelitoId;

@Repository
public class AvisoDetencionDelitoDAOImpl extends GenericDaoHibernateImpl<AvisoDetencionDelito, AvisoDetencionDelitoId>
		implements AvisoDetencionDelitoDAO {
}
