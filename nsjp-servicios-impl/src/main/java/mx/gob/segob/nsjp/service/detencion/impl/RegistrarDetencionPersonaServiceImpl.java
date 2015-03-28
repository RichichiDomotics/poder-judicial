package mx.gob.segob.nsjp.service.detencion.impl;

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.involucrado.DetencionDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Lugar;
import mx.gob.segob.nsjp.service.detencion.RegistrarDetencionPersonaService;
import mx.gob.segob.nsjp.service.domicilio.IngresarDomicilioService;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.infra.DefensoriaClienteService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.DetencionTransformer;
import mx.gob.segob.nsjp.service.lugar.IngresarLugarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrarDetencionPersonaServiceImpl implements
		RegistrarDetencionPersonaService {

	@Autowired
	private DetencionDAO detencionDAO;
	@Autowired
	private IngresarIndividuoService ingresarIndividuoService;
	@Autowired
	private IngresarLugarService ingresarLugarService;
	@Autowired
	private AsignarNumeroExpedienteService asignarNumeroExpedienteService;
	@Autowired
	private DefensoriaClienteService defensoriaClienteService;
	@Autowired
	private InvolucradoDAO invoDao;
	@Autowired
	private IngresarDomicilioService ingresarDomService;
	
	@SuppressWarnings("unused")
	public Long registrarDetencionPersonaService(DetencionDTO detencion,
			CasoDTO caso) throws NSJPNegocioException {

		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setFechaApertura(Calendar.getInstance().getTime());
		expedienteDTO.setCasoDTO(caso);
		expedienteDTO = asignarNumeroExpedienteService
				.asignarNumeroExpediente(expedienteDTO);

		detencion.getInvolucradoDTO().setExpedienteDTO(expedienteDTO);
		Long idIndividuo = ingresarIndividuoService.ingresarIndividuo(detencion
				.getInvolucradoDTO());
		detencion.getInvolucradoDTO().setElementoId(idIndividuo);

		if (false /* aqui se valida el IPH */) {
			// se da de alta el iph
		} else {
			if (detencion.getLugarDetencionDTO() != null) {
				LugarDTO lugar = ingresarLugarService.ingresarLugar(detencion
						.getLugarDetencionDTO());
				detencion.setLugarDetencionDTO(lugar);
			} else {

			}
		}
		Long idDetencion = detencionDAO.create(DetencionTransformer
				.transformarDetencion(detencion));
		defensoriaClienteService.enviarAvisoDetencion(detencion, null, caso,null,null,null);

		return idDetencion;
	}

	public DetencionDTO recibirDetenido(DetencionDTO detencion, CasoDTO caso)
			throws NSJPNegocioException {

		if(detencion.getInvolucradoDTO().getElementoId() != null){
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			expedienteDTO.setFechaApertura(Calendar.getInstance().getTime());
			expedienteDTO.setCasoDTO(caso);
			expedienteDTO = asignarNumeroExpedienteService.asignarNumeroExpediente(expedienteDTO);
			detencion.getInvolucradoDTO().setExpedienteDTO(expedienteDTO);
			
			Long idIndividuo = ingresarIndividuoService.ingresarIndividuo(detencion.getInvolucradoDTO());
			detencion.getInvolucradoDTO().setElementoId(idIndividuo);
		}else{
			
		}

		if (detencion.getLugarDetencionDTO() != null) {
			LugarDTO lugar = ingresarLugarService.ingresarLugar(detencion.getLugarDetencionDTO());
			detencion.setLugarDetencionDTO(lugar);
		} else {

		}

		Long idDetencion = detencionDAO.create(DetencionTransformer.transformarDetencion(detencion));

		
		defensoriaClienteService.enviarAvisoDetencion(detencion, null, caso,null,null,null);

		return 	DetencionTransformer.transformarDetencion(detencionDAO.read(idDetencion));
	}

    @Override
    public void eliminarInvolucrado(InvolucradoDTO invo2Del)
            throws NSJPNegocioException {
        if (invo2Del.getElementoId()==null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
       Involucrado invoPojo = this.invoDao.read(invo2Del.getElementoId());
       invoPojo.setEsActivo(Boolean.FALSE);
       this.invoDao.update(invoPojo);
        
    }

    @Override
    public Long registrarLugarDetencion(DetencionDTO detencion)
            throws NSJPNegocioException {
        
        Detencion detFromBD = this.detencionDAO.read(detencion.getDetencionId());
        Long idLugar = null;
        if (detencion.getLugarDetencionDTO() instanceof DomicilioDTO) {
            detencion.getLugarDetencionDTO().setExpedienteDTO(new ExpedienteDTO(detFromBD.getInvolucrado().getExpediente().getExpedienteId()));
            idLugar = ingresarDomService.ingresarDomicilio((DomicilioDTO) detencion
                    .getLugarDetencionDTO());
        }
        detFromBD.setLugar(new Lugar(idLugar));
        this.detencionDAO.update(detFromBD);
        return idLugar;
    }

    
    
}
