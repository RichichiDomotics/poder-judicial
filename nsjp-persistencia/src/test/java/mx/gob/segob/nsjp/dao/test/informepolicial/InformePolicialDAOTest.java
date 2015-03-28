package mx.gob.segob.nsjp.dao.test.informepolicial;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.informepolicial.InformePolicialHomologadoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.InformePolicialHomologado;

public class InformePolicialDAOTest  extends BaseTestPersistencia<InformePolicialHomologadoDAO>{
	public void testObtenerFolioIPH()
	{
		logger.debug("Prueba para obtener el folio de IPH");
		List<Object[]> folio = daoServcice.ObtenerFolioIPH();
		int regs = Integer.parseInt(folio.get(0)[0].toString());
		assertTrue("Debe obtener un folio ", regs>0);
		logger.info("El nuevo folio es: ");
	}
	
	public void consultaInformePorFolio ()
	{
		logger.debug("Prueba para obtener el Informe por Folio");
		InformePolicialHomologado iph = daoServcice.consultaInformePorFolio((long)2011000001);
		if(iph!=null)
		{
			logger.debug("ID de informe: " + iph.getInformePolicialHomologadoId());
			logger.debug("Folio de informe: " + iph.getFolioIPH());
			logger.debug("Id de expediente: " + iph.getExpediente().getExpedienteId());
		}else
		{
			logger.debug("No se encontro nngun informe con ese folio");
		}
	}
	
	public void testConsultarInformes()
	{
		logger.debug("Prueba para obtener todos los Informes registrados");
		List<InformePolicialHomologado> iph = daoServcice.consultarInformes();
		if(iph!=null)
		{
			if (iph.size()>0){
				for (int i=0;i<iph.size();i++){
					logger.debug("ID de informe: " + i + " " + iph.get(i).getInformePolicialHomologadoId());
					logger.debug("Folio de informe: "+ i + " "  + iph.get(i).getFolioIPH());
					logger.debug("Id de expediente: "+ i + " "  + iph.get(i).getExpediente().getExpedienteId());		
				}
			}
			
		}else
		{
			logger.debug("No se encontro ningun informe con ese folio");
		}
	}
	
	
	public void testObtenerIPHPorFechas() {
		try {
			Long respuesta = daoServcice.obtenerIPHPorFechas(DateUtils.obtener("01/07/2011"), DateUtils.obtener("31/08/2011"), false);
			
			assertNotNull(respuesta);
			logger.info("Numero de registros : "+respuesta);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
		
	}
}
