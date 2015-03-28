/**

 * 
 */
package mx.gob.segob.nsjp.dao.test.visitante.impl;

import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.dao.visitante.VisitanteDAO;
import mx.gob.segob.nsjp.model.Visitante;

/**
 * @author LuisMG
 * 
 */
public class VisitanteDAOImplTest extends BaseTestPersistencia<VisitanteDAO> {

	public void testConsultarVisitasPorIP() {
		Visitante visitante = daoServcice.consultarVisitantePorIP("10.10.30.1 ");
		assertNotNull("IP no registrada: ", visitante);
		System.out.println("La IP: " + visitante.getcIp());
		System.out.println("Tiene " + visitante.getiIntentos() + " intentos");
	}

	public void testBorrarVisitasPorIP() {
		Visitante visitante = new Visitante();
		boolean resp = false;
		visitante.setcIp("0.0.0.1");
		visitante.setiIntentos(1);
		visitante.setcMac("");
		resp = daoServcice.borrarVisitantePorIP(visitante);
		if (resp) {
			System.out.println("Visitas elminadas");
		} else
			System.out.println("No hay registro de visitas");

	}

	public void testRegistrarVisitasPorIP() {
		Visitante visitante = new Visitante();
		boolean resp = false;
		visitante.setcIp("0.0.0.0");
		visitante.setiIntentos(1);
		visitante.setcMac("");
		resp = daoServcice.registrarVisitante(visitante);
		if (resp) {
			System.out.println("Visita registrada");
		} else
			System.out.println("Error al registrar la visita");
	}
	
	public void testActualizarVisitasPorIP(){
		Visitante visitante = new Visitante();
		boolean resp=false;
		visitante.setcIp("0.0.0.0");
		visitante.setiIntentos(daoServcice.consultarVisitantePorIP(visitante.getcIp()).getiIntentos()+1);
		resp= daoServcice.actualizarVisitantePorIP(visitante);
		if (resp)
			System.out.println("Visita actualizada");
		else
			System.out.println("Error al actualizar la visita");
	}

	// public boolean borrarVisitas(String IP);

	// public boolean registrarVisita(String IP);

}
