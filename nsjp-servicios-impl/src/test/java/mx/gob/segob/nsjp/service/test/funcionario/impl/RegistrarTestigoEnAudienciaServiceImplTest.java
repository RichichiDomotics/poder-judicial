package mx.gob.segob.nsjp.service.test.funcionario.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.service.involucrado.RegistrarTestigoEnAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class RegistrarTestigoEnAudienciaServiceImplTest extends
		BaseTestServicios<RegistrarTestigoEnAudienciaService> {
	
	public void testRegistrarTestigoEnAudiencia() throws NSJPNegocioException{
		
		AudienciaDTO audiencia = new AudienciaDTO();
		DomicilioDTO domicilio = new DomicilioDTO();
		InvolucradoDTO testigo = new InvolucradoDTO();
		CalidadDTO calidadDomicilio = new CalidadDTO();
		NombreDemograficoDTO nd = new NombreDemograficoDTO();
		ConfInstitucionDTO institucionPresenta = new ConfInstitucionDTO();
		
		audiencia.setId(2L);
		
			nd.setNombre("Daniel Alejandro");
			nd.setApellidoPaterno("Jimenez");
			nd.setApellidoMaterno("Ventura");
		testigo.getNombresDemograficoDTO().add(nd);
		
			institucionPresenta.setConfInstitucionId(1L);
		testigo.setInstitucionPresenta(institucionPresenta);
		
			domicilio.setValorCalleId(new ValorDTO(96L));
			domicilio.setEntidadDTO(new EntidadFederativaDTO(23L));
			domicilio.setValorIdElemento(new ValorDTO(31L));
			domicilio.setCalle("calle1");
			domicilio.setDescripcion("descripción del domicilio de prueba");
			domicilio.setFechaCreacionElemento(new Date());
			domicilio.setEntreCalle1("entre calle 1");
			domicilio.setEntreCalle2("entre calle 2");
			domicilio.setAlias("Ranchito");
				calidadDomicilio.setDescripcionEstadoFisico("En buen estado");
				calidadDomicilio.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
				calidadDomicilio.setCalidades(Calidades.DOMICILIO);	
			domicilio.setCalidadDTO(calidadDomicilio);
			domicilio.setAsentamientoDTO(new AsentamientoDTO(20684L));
			domicilio.setCiudadDTO(new CiudadDTO(1L));
			domicilio.setMunicipioDTO(new MunicipioDTO(1L));
		testigo.setDomicilio(domicilio); 
		
		service.registrarTestigoEnAudiencia(audiencia, testigo);
	}

}
