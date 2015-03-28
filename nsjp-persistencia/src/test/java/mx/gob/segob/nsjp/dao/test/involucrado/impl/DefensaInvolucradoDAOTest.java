package mx.gob.segob.nsjp.dao.test.involucrado.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.involucrado.DefensaInvolucradoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Involucrado;

public class DefensaInvolucradoDAOTest extends BaseTestPersistencia<DefensaInvolucradoDAO> {

	public void testConsultarConveniosPorExpediente() {
		
		Long expedienteID = 89L;
		
		List<Involucrado> involucrados;
		try {
			System.out.println("El ordinal es: " + Areas.PGJ.ordinal());
			
			involucrados = daoServcice.consultarInvolucradosDEFNumeroExpedienteDefensoriaPorCalidad(expedienteID);
			System.out.println("Total: " + involucrados.size());
			for (Involucrado involucrado : involucrados) {
				logger.info("ID: "+involucrado.getElementoId());
				System.out.println("ID: "+involucrado.getElementoId());

			}
		} catch (NSJPNegocioException e) {

			e.printStackTrace();
		}
		
		
	}

}
