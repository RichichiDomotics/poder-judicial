package mx.gob.segob.nsjp.delegate.informepolicial.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.informepolicial.InformePolicialHomologadoDelegate;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.OperativoDTO;
import mx.gob.segob.nsjp.service.graficacion.GraficaInformesElaboradosService;
import mx.gob.segob.nsjp.service.informepolicial.CargarInformeIPHService;
import mx.gob.segob.nsjp.service.informepolicial.ConsultarEnviarInformePolicialHomologadoService;
import mx.gob.segob.nsjp.service.informepolicial.ConsultarInformePolicialHomologadoService;
import mx.gob.segob.nsjp.service.informepolicial.ConsultarInformePorNombreoFechaService;
import mx.gob.segob.nsjp.service.informepolicial.InformePolicialHomologadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("informePolicialHomologadoDelegate")
public class InformePolicialHomologadoDelegateImpl implements InformePolicialHomologadoDelegate {

	@Autowired
	InformePolicialHomologadoService informePolicialHomologadoService;		
	@Autowired
	ConsultarInformePorNombreoFechaService consultarInforme;
	@Autowired
	private CargarInformeIPHService cargarInformeIPHService;
	@Autowired
	private GraficaInformesElaboradosService graficaInformesElaboradosService;
	@Autowired 
	private ConsultarInformePolicialHomologadoService consultarInformePolicialHomologadoService; 
	@Autowired 
	private ConsultarEnviarInformePolicialHomologadoService consultarEnviarInformePolicialHomologadoService;
	
	
	
	@Override
	public Long ingresarDatosGenerales(InformePolicialHomologadoDTO iph, OperativoDTO operativo) throws NSJPNegocioException {
		return informePolicialHomologadoService.ingresarDatosGenerales(iph, operativo);
	}

	@Override
	public InformePolicialHomologadoDTO ObtenerFolioIPH(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		
			return informePolicialHomologadoService.ObtenerFolioIPH(expedienteDTO);		
	}

	@Override
	public InformePolicialHomologadoDTO consultarInformePorFolio(Long folio)
			throws NSJPNegocioException {		
			return informePolicialHomologadoService.consultarInformePorFolio(folio);		
	}

	@Override
	public List<InformePolicialHomologadoDTO> consultarInformePorNombreoFecha(
			Date fechaInicio, Date fechaFin, String nombre) throws NSJPNegocioException {
		
		return consultarInforme.ConsultarInformePorFechaOPersona(fechaInicio, fechaFin, nombre);
	}

	@Override
	public List<InformePolicialHomologadoDTO> consultarInformes(Boolean conDetenido)
			throws NSJPNegocioException {
		return informePolicialHomologadoService.consultarInformes(conDetenido);
	}

	@Override
	public DocumentoDTO cargarInformeIPH(
			InformePolicialHomologadoDTO homologadoDTO)
			throws NSJPNegocioException {
		return cargarInformeIPHService.cargarInformeIPH(homologadoDTO);
	}

	@Override
	public Long obtenerIPHPorFechas(Date fechaInicio, Date fechaFin,
			Boolean condicion) throws NSJPNegocioException {		
		return graficaInformesElaboradosService.obtenerIPHPorFechas(fechaInicio, fechaFin, condicion);
	}

	@Override
	public InformePolicialHomologadoDTO consultarInformePolicialHomologadoPorFolio(
			Long folioIPH) throws NSJPNegocioException {
		return consultarInformePolicialHomologadoService.consultarInformePolicialHomologadoPorFolio(folioIPH);
	}
	
	@Override
	public InformePolicialHomologadoDTO consultarEnviarInformePolicialHomologado(
			Long folioIPH, Long idAgencia)
			throws NSJPNegocioException {
		return consultarEnviarInformePolicialHomologadoService.consultarEnviarInformePolicialHomologado(folioIPH, idAgencia);
	}
}
