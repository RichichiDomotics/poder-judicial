/**
* Nombre del Programa : TurnoDAOImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 4 May 2011
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
package mx.gob.segob.nsjp.dao.test.expediente.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusTurno;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.dao.expediente.TurnoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Turno;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class TurnoDAOImplTest extends BaseTestPersistencia<TurnoDAO> {

	/**
	 * 
	 */
	public void testCrearTurno() {
		Turno turno = new Turno();
		
		turno.setEstatus(new Valor(EstatusTurno.ESPERA.getValorId()));
		turno.setEsUrgente(false);
		turno.setFichaAtencion("Si");
		turno.setNumeroTurno("1");
		turno.setTipoTurno(TipoTurno.PENAL.toString());
		
		Long idTurno = daoServcice.create(turno);
		
		assertTrue("IdTurno debe ser mayor a cero : ", idTurno>0 );
		logger.info("Se genero el turno con id : " + idTurno);
	}
	
	/**
	 * 
	 */
	public void testObtnerUltimoNumero() {
		Calendar calTemp = Calendar.getInstance();
		String ultimoNumero = daoServcice.obtenerUltimoNumero(TipoTurno.PENAL.toString(), false, calTemp.getTime(),2969L);
		
		assertTrue("El numero no puede ser nulo ", ultimoNumero!=null);
		logger.info("El numero recuperado es : " + ultimoNumero);
	}
	
	public void testObtenerTurnosAtencionPorUsuario(){
		
		List<Turno> resp = this.daoServcice.obtenerTurnosAtencionPorIdUsuario(59L,null, null,2069L);
		assertNotNull("No puede ser nula la lista", resp);
		logger.debug("resp.size() :: "+resp.size());
		assertFalse("No puede estar vacía la lista", resp.isEmpty());
	}

	public void testobtenerProximoTurnoAdministrativo(){

		Turno resp = this.daoServcice.obtenerProximoTurnoAtencion(TipoTurno.ADMINISTRATIVO.toString(), true, new Date(),2629L);
		assertNotNull("No puede ser nula la lista", resp);
		logger.info("Turno ID : " + resp.getTurnoId() + " " + resp.getNumeroTurno());

	}
	
	Date mandoDate(){
		java.util.Calendar cal = Calendar.getInstance();
		cal.set(2011, 06, 03);
		return cal.getTime();
	}
	
	public void testObtenerTurnosPendientesPorTipoTurno(){
		
		List<Turno> turnos = daoServcice.obtenerTurnosPendientesPorTipo(TipoTurno.JUDICIAL);
		logger.info("Turnos recuperados:" + turnos.size());
	 
	}
	
	public void testObtenerTurnosPorFiltro(){
		Turno turnoFiltro = new Turno();
//		turnoFiltro.setTurnoId(1L);
//		turnoFiltro.setExpediente(new Expediente(1L));
//		turnoFiltro.setUsuario(new Usuario(3L));
//		turnoFiltro.setNumeroTurno("1");
//		turnoFiltro.setTipoTurno(TipoTurno.JUDICIAL.name());
//		turnoFiltro.setEsUrgente(true);
//		turnoFiltro.setFechaAtencion(new Date());
		turnoFiltro.setEstatus(new Valor(EstatusTurno.ATENDIDO.getValorId()));
		
		List<Turno> turnos = daoServcice.obtenerTurnosPorFiltro(turnoFiltro);
		logger.info("Turnos recuperados:" + turnos.size());
		for (Turno turno : turnos) {
			logger.info(" *******Turno:"+ turno.getTurnoId());
			logger.info(" Turno:"+ turno.getExpediente());
			logger.info(" Turno:"+ turno.getUsuario());
			logger.info(" Turno:"+ turno.getNumeroTurno());
			logger.info(" Turno:"+ turno.getExpediente());
		}
	 
	}
}
