/**
* Nombre del Programa : IngresarPersonaServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 May 2011
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
package mx.gob.segob.nsjp.service.test.persona.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.service.persona.IngresarPersonaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
public class IngresarPersonaServiceImplTest extends BaseTestServicios<IngresarPersonaService> {

	public void testIngresarPersona () {
		PersonaDTO persona = new PersonaDTO();
		CalidadDTO calidad = new CalidadDTO();
		NombreDemograficoDTO nomDemografico = new NombreDemograficoDTO();
		List<NombreDemograficoDTO> listNomDemografico = new ArrayList<NombreDemograficoDTO>();
		List<CorreoElectronicoDTO> correosElectronicosDTO = new ArrayList<CorreoElectronicoDTO>();		
		List<TelefonoDTO> telefonosDTO = new ArrayList<TelefonoDTO>();	
		
		//persona.setFolioIdentificacion("ingper001");
		persona.setEsVivo(true);
		
		calidad.setDescripcionEstadoFisico("Desc prueba ingPersona");
		calidad.setValorIdCalidad(new ValorDTO(1L));
				
		persona.setCalidadDTO(calidad);
		persona.setValorIdElemento(new ValorDTO(2L));
		persona.setExpedienteDTO(new ExpedienteDTO(2L));
		persona.setFechaCreacionElemento(new Date());
		
		nomDemografico.setApellidoMaterno("ap ingPer");
		nomDemografico.setApellidoPaterno("am ingPer");
		nomDemografico.setCurp("curpingPer");
		nomDemografico.setRfc("rfcingPer");
		nomDemografico.setFechaNacimiento(new Date());
		nomDemografico.setEsVerdadero(false);
		nomDemografico.setEdadAproximada((short)25);
		nomDemografico.setLugarNacimiento("Guanajuato Mexico");
		nomDemografico.setNombre("nom ingPer");
		nomDemografico.setSexo("m");
		listNomDemografico.add(nomDemografico);
		persona.setNombresDemograficoDTO(listNomDemografico);
		
		telefonosDTO.add(new TelefonoDTO("ingper", "55", "57675609", new ValorDTO(233L)));
		telefonosDTO.add(new TelefonoDTO("ingper", "55", "57678676", new ValorDTO(233L)));
		persona.setTelefonosDTO(telefonosDTO);
		
		correosElectronicosDTO.add(new CorreoElectronicoDTO("ingper@hotmail.com"));
		correosElectronicosDTO.add(new CorreoElectronicoDTO("ingper@yahoo.com"));	
		persona.setCorreosDTO(correosElectronicosDTO);
				
		try {
			Long respuesta = service.ingresarPersona(persona);
			assertTrue("Id de repuesta debe ser mayor a cero", respuesta>0);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * 
	 */
	public void testIngresarPersonaOrganizacion () {
		PersonaDTO persona = new PersonaDTO();
		CalidadDTO calidad = new CalidadDTO();
		NombreDemograficoDTO nomDemografico = new NombreDemograficoDTO();
		List<NombreDemograficoDTO> listNomDemografico = new ArrayList<NombreDemograficoDTO>();
		List<CorreoElectronicoDTO> correosElectronicosDTO = new ArrayList<CorreoElectronicoDTO>();		
		List<TelefonoDTO> telefonosDTO = new ArrayList<TelefonoDTO>();	
		
		//persona.setFolioIdentificacion("ingper001");
		persona.setEsVivo(true);
		
		calidad.setCalidades(Calidades.REPRESENTANTE_LEGAL);
		calidad.setDescripcionEstadoFisico("Desc prueba ingPersona");
		calidad.setValorIdCalidad(new ValorDTO(1L));
				
		persona.setCalidadDTO(calidad);
		persona.setValorIdElemento(new ValorDTO(2L));
		persona.setExpedienteDTO(new ExpedienteDTO(2L));
		persona.setFechaCreacionElemento(new Date());
		
		nomDemografico.setApellidoMaterno("ap ingPerOr");
		nomDemografico.setApellidoPaterno("am ingPerOr");
		nomDemografico.setCurp("curpingPerOr");
		nomDemografico.setRfc("rfcingPerOr");
		nomDemografico.setFechaNacimiento(new Date());
		nomDemografico.setEsVerdadero(false);
		nomDemografico.setEdadAproximada((short)25);
		nomDemografico.setLugarNacimiento("Guanajuato Mexico");
		nomDemografico.setNombre("nom ingPerOr");
		nomDemografico.setSexo("m");
		listNomDemografico.add(nomDemografico);
		persona.setNombresDemograficoDTO(listNomDemografico);
		
		telefonosDTO.add(new TelefonoDTO("ingperOr", "55", "57675609", new ValorDTO(233L)));
		telefonosDTO.add(new TelefonoDTO("ingperOr", "55", "57678676", new ValorDTO(233L)));
		persona.setTelefonosDTO(telefonosDTO);
		
		correosElectronicosDTO.add(new CorreoElectronicoDTO("ingperOrg@hotmail.com"));
		correosElectronicosDTO.add(new CorreoElectronicoDTO("ingperOrg@yahoo.com"));	
		persona.setCorreosDTO(correosElectronicosDTO);
				
		try {
			PersonaDTO respuesta = service.ingresarPersonaOrganizacion(persona, new OrganizacionDTO(133L));
			assertTrue("Id de repuesta debe ser mayor a cero", respuesta.getElementoId()>0);
			logger.info("Nombre:"+respuesta.getNombreCompleto());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}		
	}
}
