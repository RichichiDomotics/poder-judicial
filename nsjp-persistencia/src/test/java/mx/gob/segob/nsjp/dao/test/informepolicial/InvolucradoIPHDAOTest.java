package mx.gob.segob.nsjp.dao.test.informepolicial;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.informepolicial.InvolucradoIPHDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.InformePolicialHomologado;


public class InvolucradoIPHDAOTest extends BaseTestPersistencia<InvolucradoIPHDAO>{
	
	public void testConsultarInformePorInvolucrado() throws NSJPNegocioException
	{

		
		String nombre = "Marco";
		Calendar cal =Calendar.getInstance();
		cal.set(2011, 8, 8);
		Date fechaInicio = cal.getTime();
		Date fechaFin = new Date();
		
		List<InformePolicialHomologado> iph = daoServcice.consultarFolioIPHPorFechaOPersona(fechaInicio, fechaFin, nombre);
		//iph.notify();
		logger.debug("Iph: " + iph);
		logger.debug("Tamaño iph: " + iph.size());
		for(InformePolicialHomologado row:iph)
		{
			logger.debug("Folio: " + row.getFolioIPH());
			logger.debug("Id Informe: " + row.getInformePolicialHomologadoId());
		}
	}
	
//	public void testRegistrarInvolucradoIPH(){
//		InvolucradoIph inv=new InvolucradoIph();
//		InformePolicialHomologado iforme=new InformePolicialHomologado();
//		iforme.setInformePolicialHomologadoId(1L);
//		
//		/*Propiospara iPH*/
//		inv.setInformePolicialHomologado(iforme);
//		inv.setFechaDictamenMedico(new Date());
//		inv.setSituacionPolicialIndividuo(new Valor(2266L));
//		inv.setGrupoEdad(new Valor(2272L));
//		inv.setGrupoEtnico(new Valor(1L));
//		inv.setEstatusResidencial(new Valor(1L));
//		
//		inv.setEsVivo(false);		
//		inv.setMotivoComparecencia("Por rata");		
//		inv.setExpediente(new Expediente(2L));
//		
//		 inv.setSituacionJuridica(new Valor(SituacionJuridica.INDICIADO.getValorId()));
//		 inv.setMotivoComparecencia("Lo dijo el capitan");
//		 inv.setEsServidor(false);
//		 inv.setEsDetenido(false);
//		 inv.setTipoPersona("Fisica");
//
//		 inv.setIdioma(new Valor(53L));
//		 inv.setReligion(new Valor(99L));
//		 inv.setEstadoCivil(new Valor(104L));
//		 inv.setEscolaridad(new Valor(125L));
//		 
//		 inv.setCalidad(new Calidad(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId()));
//		 inv.setFechaCreacionElemento(new Date());
//		 inv.setTipoElemento(new Valor(Elementos.PERSONA.getValorId()));
//		
//		Long idInvol = daoServcice.create(inv);
//		assertNotNull(idInvol);
//	}
	
	public void testObtenerCasosRemitidosPorMes() {
		try {
			List<Object[]> respuesta = daoServcice.obtenerCasosRemitidosPorMes(DateUtils.obtener("01/08/2011"), DateUtils.obtener("31/08/2011"));
			
			assertTrue("La lista debe tener minimo un registro", respuesta.size()>0);
			for (Object[] objects : respuesta) {
				logger.info("Mes :: " + objects[0] + " Registros :: " + objects[1]);
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
}
