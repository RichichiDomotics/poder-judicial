/**
 * Nombre del Programa : ActualizarFuncionarioServiceTest.java
 * Autor                            : jmartinez
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    
 * Marca de cambio        : N/A
 * Descripcion General    : Prueba unitaria para el servicio de Actualizar Funcionario
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
package mx.gob.segob.nsjp.service.test.funcionario.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.service.funcionario.ActualizarFuncionarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Servicio para actualziar los datos de un funcionario
 * @version 1.0
 * @author jmartinez
 * 
 */
public class ActualizarFuncionarioServiceTest extends
		BaseTestServicios<ActualizarFuncionarioService> {

	public void testModificarDefensor(){
		FuncionarioDTO defensorDto;
		try {
			//SE DEBEN DE SETEAR TODOS LOS DATOS YA EXISTENTES
			defensorDto = new FuncionarioDTO();
			defensorDto.setClaveFuncionario(101L);	
			defensorDto.setJerarquiaOrganizacional(new JerarquiaOrganizacionalDTO(2L));
			defensorDto.setNumeroEmpleado("01234QWE");

			defensorDto.setNombreFuncionario("Nombre Nuevo");
			defensorDto.setApellidoMaternoFuncionario("AMaterno Nuevo");
			defensorDto.setApellidoPaternoFuncionario("APaterno Nuevo");
			defensorDto.setSexo("H");
			defensorDto.setRfc("NOAA800405TOX");
			defensorDto.setCurp("NOAA800405HOXRR1");
			Calendar cal = Calendar.getInstance();
			cal.set(1980, 4, 5);
			defensorDto.setFechaNacimiento(cal.getTime());


//			DepartamentoDTO dep = new DepartamentoDTO();
//			AreaDTO area = new AreaDTO();
//			area.setAreaId(5L);
//			dep.setArea(area);		
//			defensorDto.setDepartamento(dep);
//			defensorDto.setPuesto(new ValorDTO(3L));
//			defensorDto.setFuncionarioJefe(new FuncionarioDTO(new Long(1)));

			PersonaDTO persona = new PersonaDTO();
			List<TelefonoDTO> telefonosDto = new ArrayList<TelefonoDTO>(0);
			TelefonoDTO telefono = new TelefonoDTO();
			telefono.setCodigoArea("55");
			telefono.setCodigoPais("1");
			telefono.setValorTipoTelefono(new ValorDTO(new Long(1)));
			//telefono.setMedioDeContadoId(new Long(13));
			telefono.setNumeroTelefonico("55443322");
			telefonosDto.add(telefono);
			telefono = new TelefonoDTO();
			//telefono.setMedioDeContadoId(new Long(13));
			telefono.setValorTipoTelefono(new ValorDTO(new Long(2)));
			telefono.setNumeroTelefonico("9988776655");
			telefonosDto.add(telefono);

			List<CorreoElectronicoDTO> correosDto = new ArrayList<CorreoElectronicoDTO>(0);
			CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
			correo.setMedioDeContadoId(new Long(13));
			correo.setDireccionElectronica("correoContactoNuevo@ultrasist.com.mx");

			persona.setTelefonosDTO(telefonosDto);
			persona.setCorreosDTO(correosDto);
			defensorDto.setPersona(persona);
			
			//Dato a Modificar
//			defensorDto.setEspecialidad(new ValorDTO(1L));
			defensorDto = service.modificarDefensor(defensorDto);
			
			System.out.println("Funcionario: " + defensorDto.getClaveFuncionario());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
    }
}
