package mx.gob.segob.nsjp.service.test.informepolicial;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatFaltaAdministrativaDTO;
import mx.gob.segob.nsjp.dto.informepolicial.DelitoIphDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.model.DelitoIph;
import mx.gob.segob.nsjp.service.informepolicial.ConsultarInformePorNombreoFechaService;
import mx.gob.segob.nsjp.service.informepolicial.impl.transform.DelitoIphTransformer;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class ConsultarInformePorNombreOFechaTest extends BaseTestServicios<ConsultarInformePorNombreoFechaService>{

	
	public void consultarInformePorNombreOFechaTest() throws NSJPNegocioException
	{
		Date fechaInicio;
		Date fechaFin;
		
		List<InformePolicialHomologadoDTO> informes = new ArrayList<InformePolicialHomologadoDTO>();
		informes = service.ConsultarInformePorFechaOPersona(null, null, "jorge");
		logger.debug("Informes encontrados: " + informes.size());
		
		if(informes.size()>0)
		{					
			for(InformePolicialHomologadoDTO row: informes)
			{								
				logger.debug("Folio IPH: " + row.getFolioIPH());
				logger.debug("Expediente IPH: " + row.getExpediente().getExpedienteId());				
				for(DelitoIphDTO rowDelito:row.getDelitoIph())
				{
					logger.debug("Delitos de IPH: " + rowDelito.getCatDelito().getNombre());
				}
			}
		}
	}
}
