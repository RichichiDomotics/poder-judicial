package mx.gob.segob.nsjp.service.test.expediente.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ActaCircunstanciadaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarActaCircunstanciadaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class ConsultarActaCircunstanciadaServiceImplTest extends
		BaseTestServicios<ConsultarActaCircunstanciadaService> {

	public void testConsultarActaCircunstaciada() {
		ExpedienteDTO dto = new ExpedienteDTO(22L);
		try {
			ActaCircunstanciadaDTO acta = service
					.consultarActaCircunstaciada(dto);
			assertNotNull(acta);
			if (acta.getHechoDTO() != null) {
				logger.info("idHecho: " + acta.getHechoDTO().getHechoId());
				logger.info("Desc: " + acta.getHechoDTO().getDescNarrativa());
			} else
				logger.info("No hay HECHO");
			if (acta.getInvolucradoDTO() != null) {
				logger.info("idInvol: "
						+ acta.getInvolucradoDTO().getElementoId());
				logger.info("nombre: "
						+ acta.getInvolucradoDTO().getNombreCompleto());
				if (acta.getInvolucradoDTO().getDomicilio() != null) {
					logger.info("**DomicilioID: "
							+ acta.getInvolucradoDTO().getDomicilio()
									.getElementoId());
					logger.info("**Domicilio: "
							+ acta.getInvolucradoDTO().getDomicilio()
									.getCalle());
				}
				if(acta.getInvolucradoDTO().getCorreosDTO()!=null){
					logger.info("$$Correos: "+acta.getInvolucradoDTO().getCorreosDTO().size());
					for (CorreoElectronicoDTO corr : acta.getInvolucradoDTO().getCorreosDTO()) {
						logger.info("correo: "+corr.getDireccionElectronica());
					}
					}
				if(acta.getInvolucradoDTO().getTelefonosDTO()!=null){
					logger.info("$$Telefonos: "+acta.getInvolucradoDTO().getTelefonosDTO().size());
					for (TelefonoDTO tel : acta.getInvolucradoDTO().getTelefonosDTO()) {
						logger.info("pais: "+tel.getCodigoPais());
						logger.info("area: "+tel.getCodigoArea());
						logger.info("telefono: "+tel.getNumeroTelefonico());
						logger.info("tipo: "+tel.getValorTipoTelefono().getValor());
					}
					}
			} else
				logger.info("No hay INVOLUCRADO");
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}
