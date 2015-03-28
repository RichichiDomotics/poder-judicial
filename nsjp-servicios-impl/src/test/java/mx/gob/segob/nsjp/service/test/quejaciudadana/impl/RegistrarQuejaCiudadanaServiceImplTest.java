/**
 * 
 */
package mx.gob.segob.nsjp.service.test.quejaciudadana.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.elemento.TipoTelefono;
import mx.gob.segob.nsjp.comun.enums.quejaciudadana.MotivoRechazo;
import mx.gob.segob.nsjp.comun.enums.quejaciudadana.TipoQueja;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO;
import mx.gob.segob.nsjp.service.quejaciudadana.RegistrarQuejaCiudadanaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class RegistrarQuejaCiudadanaServiceImplTest extends
		BaseTestServicios<RegistrarQuejaCiudadanaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.quejaciudadana.impl.RegistrarQuejaCiudadanaServiceImpl#registrarQuejaCiudadana(mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO)}.
	 */
	public void testRegistrarQuejaCiudadana() {
		QuejaCiudadanaDTO dto=new QuejaCiudadanaDTO();
		List<MedioDeContactoDTO> mediosQuejoso = new ArrayList<MedioDeContactoDTO>();
		
		/*Obligatorios*/
		dto.setDescripcionQueja("Es una queja formal de prueba");
		dto.setTipoQuejaDTO(new ValorDTO(TipoQueja.ABUSO_DE_AUTORIDAD.getValorId()));
		/*Opcionales*/
		dto.setNombreAfectado("Afectado");
		dto.setApellidoPatAfectado("AP Afectado");
		dto.setApellidoMatAfectado("AM Afectado");
		dto.setCalidadAfectado(new ValorDTO(Calidades.VICTIMA_PERSONA.getValorId()));
		
		dto.setNombreDenunciado("Denunciado");
		dto.setApellidoPatDenunciado("AP Denunciado");
		dto.setApellidoMatDenunciado("AM Denunciado");
		dto.setCalidadDenunciado(new ValorDTO(Calidades.TUTOR.getValorId()));
		
		dto.setNombreQuejoso("Quejoso");
		dto.setApellidoPatQuejoso("AP Quejoso");
		dto.setApellidoMatQuejoso("AM Quejoso");
		dto.setCalidadQuejoso(new ValorDTO(Calidades.DENUNCIANTE.getValorId()));
		
		TelefonoDTO telefono = new TelefonoDTO("001", "001", "57678545", new ValorDTO(TipoTelefono.CELULAR.getValorId()));
		
		CorreoElectronicoDTO correoElectronico = new CorreoElectronicoDTO("hhhhh@hotmail.com");
		
		mediosQuejoso.add(telefono);
		mediosQuejoso.add(correoElectronico);
		dto.setMediosDenunciado(mediosQuejoso);
		
//		dto.setNombreInculpado("Inculpado");
//		dto.setNombreInvDenunciado("Denunciado");
		dto.setNumeroExpediente("NSJYUCPG20114433333");
		dto.setMotivoRechazoDTO(new ValorDTO(MotivoRechazo.SIN_ARGUMENTOS.getValorId()));
		try {
			Long idQueja = service.registrarQuejaCiudadana(dto);
			assertNotNull(idQueja);
			logger.info("Respuesta :: "+idQueja);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
