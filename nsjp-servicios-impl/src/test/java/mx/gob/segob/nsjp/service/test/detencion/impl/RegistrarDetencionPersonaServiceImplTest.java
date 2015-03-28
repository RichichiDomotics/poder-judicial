package mx.gob.segob.nsjp.service.test.detencion.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.MediaFiliacionDTO;
import mx.gob.segob.nsjp.dto.involucrado.SeniaParticularDTO;
import mx.gob.segob.nsjp.dto.involucrado.ServidorPublicoDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.service.detencion.RegistrarDetencionPersonaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class RegistrarDetencionPersonaServiceImplTest extends
		BaseTestServicios<RegistrarDetencionPersonaService> {

	
	public void testRecibirDetenido(){

		DetencionDTO detencion = new DetencionDTO();
		InvolucradoDTO DETENIDO = new InvolucradoDTO();
		
		List<CorreoElectronicoDTO> correosElectronicosDTO = new ArrayList<CorreoElectronicoDTO>();		
		List<TelefonoDTO> telefonosDTO = new ArrayList<TelefonoDTO>();	
		ExpedienteDTO expediente = new ExpedienteDTO();
		CalidadDTO calidad = new CalidadDTO();
		CalidadDTO calidadOrganizacion = new CalidadDTO();
		CalidadDTO calidadDomicilio = new CalidadDTO();
		CalidadDTO calidadLugarDet = new CalidadDTO();		
		DomicilioDTO domicilio = new DomicilioDTO();
		DomicilioDTO lugarDetencion = new DomicilioDTO();
		DomicilioDTO domicilioOrganizacion = new DomicilioDTO();
		List<NombreDemograficoDTO> nombres = new ArrayList<NombreDemograficoDTO>();
		NombreDemograficoDTO nombre = new NombreDemograficoDTO();
		List<AliasInvolucradoDTO> aliasDTO = new ArrayList<AliasInvolucradoDTO>();
		AliasInvolucradoDTO alias = new AliasInvolucradoDTO();		
		OrganizacionDTO organizacion = new OrganizacionDTO();	
		ServidorPublicoDTO servidorPublico = new ServidorPublicoDTO();
		MediaFiliacionDTO mediaFiliacionDTO = new MediaFiliacionDTO();
		SeniaParticularDTO seniaParticularDTO = new SeniaParticularDTO(); 

		FuncionarioDTO funcionario = new FuncionarioDTO();
		funcionario.setClaveFuncionario(new Long(1));
		
		nombre.setNombre("Daniel Alejandro");
		nombre.setApellidoPaterno("Jiménez");
		nombre.setApellidoMaterno("Ventura");
		nombre.setCurp("JIVD820409HGRMNN05");
		nombre.setRfc("JIVD8204092XA");
		nombre.setFechaNacimiento(new Date());
		nombre.setEsVerdadero(false);
		nombre.setEdadAproximada((short)29);
		nombre.setLugarNacimiento("DF Mexico");
		nombre.setSexo("h");
		nombre.setPaisValorDTO(new ValorDTO(10L));
		nombre.setMunicipioDTO(new MunicipioDTO(604L));
		nombre.setEntidadFederativaDTO(new EntidadFederativaDTO(31L));
		nombres.add(nombre);
		DETENIDO.setNombresDemograficoDTO(nombres);
		
		Long idExpediente = 24L;  //Asocia a Expediente
		String numeroExpediente = "NSJYUCPROC20113333Z";
		Long tipoPersona = 1L; //0 Moral 1 Fisica
				
		expediente.setFechaApertura(new Date());
		expediente.setFechaCierre(new Date());		
		expediente.setNumeroExpediente(numeroExpediente);
		DETENIDO.setExpedienteDTO(expediente);		
			
		telefonosDTO.add(new TelefonoDTO("01", "55", "57675609", new ValorDTO(233L)));
		telefonosDTO.add(new TelefonoDTO("T04L", "55", "57678676", new ValorDTO(233L)));
		DETENIDO.setTelefonosDTO(telefonosDTO);
		
		correosElectronicosDTO.add(new CorreoElectronicoDTO("salinas@hotmail.com"));
		correosElectronicosDTO.add(new CorreoElectronicoDTO("csalinas@yahoo.com"));
		DETENIDO.setCorreosDTO(correosElectronicosDTO);
		
		DETENIDO.setValorIdIdioma(new ValorDTO(53L));
		DETENIDO.setValorIdEscolaridad(new ValorDTO(108L));
		DETENIDO.setValorIdEstadoCivil(new ValorDTO(106L));
		
//		Se agregan ocupaciones al involucrado
		List<ValorDTO> ocupaciones = new ArrayList<ValorDTO>();
		ocupaciones.add(new ValorDTO(131L));
		ocupaciones.add(new ValorDTO(138L));
		ocupaciones.add(new ValorDTO(147L));
		DETENIDO.setValorIdOcupacion(ocupaciones);
		
//		Se agregan alias al involucrado
		alias.setAlias("El flaco");
		aliasDTO.add(alias);
		DETENIDO.setAliasInvolucradoDTO(aliasDTO);						
	
		List<AliasInvolucradoDTO> aliasInvolucradoDTO = new ArrayList<AliasInvolucradoDTO>();
		AliasInvolucradoDTO alias1 = new AliasInvolucradoDTO();
		alias1.setAlias("guayumin2");
		alias1.setInvolucradoDTO(DETENIDO);		
		aliasInvolucradoDTO.add(alias1);
		AliasInvolucradoDTO alias2 = new AliasInvolucradoDTO();
		alias2.setAlias("guayusei2");
		alias2.setInvolucradoDTO(DETENIDO);
		aliasInvolucradoDTO.add(alias2);
		DETENIDO.setAliasInvolucradoDTO(aliasInvolucradoDTO);
		
//		Se agregan nacionalidades		
		List<ValorDTO> nacionalidades = new ArrayList<ValorDTO>();
		nacionalidades.add(new ValorDTO(175L));
		nacionalidades.add(new ValorDTO(180L));
		nacionalidades.add(new ValorDTO(190L));
		DETENIDO.setValorIdNacionalidad(nacionalidades);

//		Se agrega el lugar de la detencion
		lugarDetencion.setValorCalleId(new ValorDTO(96L));
		lugarDetencion.setEntidadDTO(new EntidadFederativaDTO(23L));
		lugarDetencion.setValorIdElemento(new ValorDTO(31L));
		lugarDetencion.setCalle("calle  1");
		lugarDetencion.setDescripcion("descripcion domicilio 1");
		lugarDetencion.setFechaCreacionElemento(new Date());
		lugarDetencion.setEntreCalle1("entre calle 1");
		lugarDetencion.setEntreCalle2("entre calle 2");						
		lugarDetencion.setAlias("Hacienda");
		lugarDetencion.setExpedienteDTO(expediente);
		lugarDetencion.setAsentamientoDTO(new AsentamientoDTO(20684L));
		lugarDetencion.setCiudadDTO(new CiudadDTO(2L));
		lugarDetencion.setMunicipioDTO(new MunicipioDTO(2L));
		lugarDetencion.setLatitud("300.0");
		lugarDetencion.setLongitud("301.0");
			calidadLugarDet.setDescripcionEstadoFisico("En buen estado");
			calidadLugarDet.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
			calidadLugarDet.setCalidades(Calidades.DOMICILIO);				
		lugarDetencion.setCalidadDTO(calidadLugarDet);
		
		detencion.setLugarDetencionDTO(lugarDetencion);
		detencion.setInvolucradoDTO(DETENIDO);
		detencion.setFechaInicioDetencion(Calendar.getInstance().getTime());
		detencion.setFechaRecepcionDetencion(Calendar.getInstance().getTime());
		detencion.setFuncionarioByFuncionarioDetiene(funcionario);
		detencion.setMotivoDetencion("");
		detencion.setObservaciones("");
		detencion.setLugarDetencionProvicional("");
	}
	
}
