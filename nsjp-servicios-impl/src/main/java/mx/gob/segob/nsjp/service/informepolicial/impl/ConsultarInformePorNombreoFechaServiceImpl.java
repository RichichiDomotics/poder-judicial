package mx.gob.segob.nsjp.service.informepolicial.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.informepolicial.InformePolicialHomologadoDAO;
import mx.gob.segob.nsjp.dao.informepolicial.InvolucradoIPHDAO;
import mx.gob.segob.nsjp.dto.informepolicial.DelitoIphDTO;
import mx.gob.segob.nsjp.dto.informepolicial.FaltaAdministrativaIphDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.service.informepolicial.ConsultarInformePorNombreoFechaService;
import mx.gob.segob.nsjp.service.informepolicial.impl.transform.DelitoIphTransformer;
import mx.gob.segob.nsjp.service.informepolicial.impl.transform.FaltaAdministrativaIphTransformer;
import mx.gob.segob.nsjp.service.informepolicial.impl.transform.InformePolicialHomologadoTransformer;
import mx.gob.segob.nsjp.model.DelitoIph;
import mx.gob.segob.nsjp.model.FaltaAdministrativaIph;
import mx.gob.segob.nsjp.model.InformePolicialHomologado;

@Service
public class ConsultarInformePorNombreoFechaServiceImpl implements
		ConsultarInformePorNombreoFechaService {
	
	static private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConsultarInformePorNombreoFechaServiceImpl.class);
	
	@Autowired
	InvolucradoIPHDAO involucradoDAO;
	@Autowired
	InformePolicialHomologadoDAO informeDAO;
	@Override
	public List<InformePolicialHomologadoDTO> ConsultarInformePorFechaOPersona(
			Date fechaInicio, Date fechaFin, String nombreCompleto)
			throws NSJPNegocioException {
		
		List<InformePolicialHomologadoDTO> resp = new ArrayList<InformePolicialHomologadoDTO>();
		List<InformePolicialHomologado> informes =
			involucradoDAO.consultarFolioIPHPorFechaOPersona(fechaInicio, fechaFin, nombreCompleto);
		if(informes.size()>0)
		{
			try
			{
				for(InformePolicialHomologado row: informes)
				{
					InformePolicialHomologadoDTO _row =
						InformePolicialHomologadoTransformer.tranformIPH(row);					
					List<DelitoIph> delitos = informeDAO.consultarDelitosDeIPH(_row.getInformePolicialHomologadoId());
					List<FaltaAdministrativaIph> faltaAdmin = informeDAO.consultarFaltaAdministrativaDeIPH(_row.getInformePolicialHomologadoId());
					if(delitos.size()>0)
					{
						List<DelitoIphDTO> delitoDTO = new ArrayList<DelitoIphDTO>();
						for(DelitoIph rowDelito:delitos)
						{						
							delitoDTO.add(DelitoIphTransformer.transformarDelitoIph(rowDelito));
						}
						_row.setDelitoIph(delitoDTO);
					}
					if(faltaAdmin.size()>0)
					{
						List<FaltaAdministrativaIphDTO> faltaDTO = new ArrayList<FaltaAdministrativaIphDTO>();
						for(FaltaAdministrativaIph rowFalta:faltaAdmin)
						{
							faltaDTO.add(FaltaAdministrativaIphTransformer.transformarFaltaAdministrativaIph(rowFalta));
						}
						_row.setFaltaIph(faltaDTO);
					}
					resp.add(_row);					
					logger.debug("RESP::" + resp);
				}
			}catch(Exception ex)
			{
				ex.getMessage();
				ex.printStackTrace();
			}
		}
		logger.debug("los informes (IPH) son::" + resp.size());
		return resp;
	}

}
