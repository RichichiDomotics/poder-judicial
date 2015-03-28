/**
 * 
 */
package mx.gob.segob.nsjp.service.test.implicado.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.ImplicadoDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.service.implicado.ConsultarImplicadoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author rgama
 *
 */
public class ConsultarImplicadoXIdServiceImplTest extends
		BaseTestServicios<ConsultarImplicadoService> {

	/**
	 */
	public void testConsultarImplicadoXId() {
		try {
			ImplicadoDTO implicado = service.consultarImplicadoXId(new ImplicadoDTO(121L));
			assertNotNull("Exito", implicado);
			
			logger.info("---------------------------");
			logger.info("ID: " + implicado.getImplicadoId());
			
			if(implicado.getMedioDeContactos() != null && implicado.getMedioDeContactos().size() >0)
				for (MedioDeContactoDTO contacto : implicado.getMedioDeContactos()) {				
					if(contacto instanceof TelefonoDTO){
						TelefonoDTO telefonoDTO = (TelefonoDTO)contacto;
						logger.info(" TELEFONO");
						logger.info(" Numero: " + telefonoDTO.getNumeroTelefonico());
						logger.info(" Area: " + telefonoDTO.getCodigoArea());
						logger.info(" Pais: " + telefonoDTO.getCodigoPais());
					}
					if(contacto instanceof CorreoElectronicoDTO){
						logger.info(" CORREO");
						CorreoElectronicoDTO correoDTO = (CorreoElectronicoDTO)contacto;
						logger.info(" Id: " + correoDTO.getCorreoElectronicoId());
						logger.info(" Mail: " + correoDTO.getDireccionElectronica());
						logger.info(" Mail: " + correoDTO.toString());
					}
				}
			else
				logger.info(" El involucrado no tiene medios de contacto");
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}
