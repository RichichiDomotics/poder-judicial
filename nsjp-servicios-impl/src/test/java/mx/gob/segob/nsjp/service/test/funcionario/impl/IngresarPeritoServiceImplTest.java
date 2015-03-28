/**
 * Nombre del Programa : IngresarPeritoServiceImplTest.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    
 * Marca de cambio        : N/A
 * Descripcion General    : Prueba unitaria para el servicio de Registrar Perito
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.service.funcionario.IngresarFuncionarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Prueba unitaria para el servicio de Registrar Perito
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class IngresarPeritoServiceImplTest extends
		BaseTestServicios<IngresarFuncionarioService> {
	/**
	 * Metodo que permite insertar un nuevo Funcionario
	 */
	public void testRegistrarFuncionario() {
		FuncionarioDTO loPeritoDTO = new FuncionarioDTO();
		FuncionarioDTO respuesta = new FuncionarioDTO();

		loPeritoDTO.setNumeroEmpleado("01234QWE");
		loPeritoDTO.setNombreFuncionario("Vladimir");
		loPeritoDTO.setApellidoMaternoFuncionario("Aguirre");
		loPeritoDTO.setApellidoPaternoFuncionario("Piedragil");
		loPeritoDTO.setSexo("H");
		loPeritoDTO.setRfc("AUPV810209HE3");
		loPeritoDTO.setCurp("AUPV810209HMSGDL0");
		/*Calendar cal = Calendar.getInstance();
		cal.set(80, 4, 5);
		loPeritoDTO.setFechaNacimiento(cal.getTime());*/
		DepartamentoDTO dep = new DepartamentoDTO();
		AreaDTO area = new AreaDTO();
		Long valor = (long) (Math.random() * 4) + 1;
		area.setAreaId(new Long(valor));
		logger.debug("El valor del área es " + valor);
		dep.setArea(area);		
		loPeritoDTO.setDepartamento(dep);
		valor = (long) (Math.random() * 4) + 1;
		logger.debug("El valor del puesto es " + valor);
		loPeritoDTO.setPuesto(new ValorDTO(valor));		
		valor = (long) (Math.random() * 4) + 1;
		logger.debug("El valor de la institucion es " + valor);
		loPeritoDTO.setEspecialidad(new ValorDTO(valor));
		loPeritoDTO.setFuncionarioJefe(new FuncionarioDTO(new Long(1)));

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
		loPeritoDTO.setPersona(persona);
		try {
			respuesta = service.ingresarFuncionario(loPeritoDTO);
			assertTrue("El id de la respuesta no puede ser menor a 0 : ",
					respuesta.getClaveFuncionario() >= 0);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Metodo que permite insertar 20 Peritos asociados a diferentes
	 * especialidades e Instituciones
	 */
	/*public void testRegistrar20Peritos() {

		for (int i = 1; i <= 20; i++) {
			FuncionarioDTO loPeritoDTO = new FuncionarioDTO();
			FuncionarioDTO respuesta = new FuncionarioDTO();

			loPeritoDTO.setNombreFuncionario("Nombre " + i);
			loPeritoDTO.setApellidoMaternoFuncionario("AMaterno " + i);
			loPeritoDTO.setApellidoPaternoFuncionario("APaterno " + i);
			loPeritoDTO.setEmail("correo" + i + "@ultrasist.com.mx");
			Long valor = (long) (Math.random() * 4) + 1;
			logger.debug("El valor de la institucion es " + valor);
			loPeritoDTO.setEspecialidad(new ValorDTO(1728L + i));
			try {
				respuesta = service.ingresarFuncionario(loPeritoDTO);
				assertTrue("El id de la respuesta no puede ser menor a 0 : ",
						respuesta.getClaveFuncionario() >= 0);
			} catch (NSJPNegocioException e) {
				logger.error(e.getMessage());
			}
		}
	}*/

}
