/**
 * 
 */
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ConvenioDAO;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dao.medida.CompromisoPeriodicoDAO;
import mx.gob.segob.nsjp.dao.medida.FechaCompromisoDAO;
import mx.gob.segob.nsjp.dto.expediente.ConvenioDTO;
import mx.gob.segob.nsjp.model.CompromisoPeriodico;
import mx.gob.segob.nsjp.model.Convenio;
import mx.gob.segob.nsjp.model.FechaCompromiso;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.ConsultarConvenioService;
import mx.gob.segob.nsjp.service.expediente.GuardarConvenioService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ConvenioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adrian
 * 
 */
@Service
@Transactional
public class GuardarConvenioServiceImpl implements GuardarConvenioService {

	public final static Logger logger = Logger
			.getLogger(GuardarConvenioServiceImpl.class);
	@Autowired
	private ConvenioDAO restaurativoDAO;
	@Autowired
	private CompromisoPeriodicoDAO compromisoPeriodicoDAO;
	@Autowired
	private FechaCompromisoDAO fechaCompromisoDAO;
	@Autowired
	private FormaDAO formaDAO;
	

	@Override
	public Long guardarConvenio(ConvenioDTO restaurativoDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR O MODIFICAR UN ACUERDO RESTAURATIVO ****/");

		/* Verificación de parámetros */
		if (restaurativoDTO != null && restaurativoDTO.getConvenioID() == null){
			if (restaurativoDTO == null || restaurativoDTO.getInvolucradoPR() == null
					|| restaurativoDTO.getFuncionario() == null 
					|| restaurativoDTO.getNumeroExpediente().getNumeroExpedienteId() == null)
				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}			
		else
			if(restaurativoDTO == null)
				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/* Operación */
		Convenio acuerdo = ConvenioTransformer
				.transformarConvenioDTO(restaurativoDTO);
		Long idAcuerdo = acuerdo.getDocumentoId();
		if (acuerdo.getDocumentoId() == null) {
			/* Obligatorios al documento */
			acuerdo.setFechaCreacion(new Date());
			acuerdo.setNombreDocumento("ACUERDO RESTAURATIVO");
			acuerdo.setTipoDocumento(new Valor(TipoDocumento.ACUERDO
					.getValorId()));
			acuerdo.setForma(formaDAO.consultarFormaPorNombre("Blanco"));// Es la plantilla en blanco
			/* Automático de numero convenio */
			List<Convenio> acuerdos = restaurativoDAO
					.consultarConveniosPorExpediente(acuerdo
							.getNumeroExpediente().getNumeroExpedienteId());
			acuerdo.setNumeroMediacion(acuerdos.size() + 1L);

			Long idCompromiso = null;

			/* COMPROMISO PERIODICO */
			if (acuerdo.getCompromisoPeriodico() != null) {
				List<FechaCompromiso> fechasCompromiso = acuerdo
						.getCompromisoPeriodico().getFechaCompromisos();
				CompromisoPeriodico copromiso = acuerdo
						.getCompromisoPeriodico();
				copromiso.setFechaCompromisos(null);
				if (copromiso.getCompromisoPeriodicoId() == null)
					idCompromiso = compromisoPeriodicoDAO.create(acuerdo
							.getCompromisoPeriodico());
				else {
					idCompromiso = acuerdo.getCompromisoPeriodico()
							.getCompromisoPeriodicoId();
					compromisoPeriodicoDAO.update(acuerdo
							.getCompromisoPeriodico());
				}
				/* FECHAS COMPROMISO */
				for (FechaCompromiso feCo : fechasCompromiso) {
					feCo.setCompromisoPeriodico(new CompromisoPeriodico(idCompromiso));
					if(feCo.getFechaCompromisoId()==null){
						fechaCompromisoDAO.create(feCo);
					}else{
						fechaCompromisoDAO.update(feCo);
					}
				}
			}
			acuerdo.setCompromisoPeriodico(new CompromisoPeriodico(idCompromiso));

			/* CONVENIO */
			idAcuerdo = restaurativoDAO.create(acuerdo);
		} else{
			Convenio bdConvenio = restaurativoDAO.read(acuerdo.getDocumentoId());
			if(restaurativoDTO.getTipoConvenio() != null && restaurativoDTO.getTipoConvenio().getIdCampo() != null)
				bdConvenio.setTipoConvenio(new Valor(restaurativoDTO.getTipoConvenio().getIdCampo()));			
			if(restaurativoDTO.getFechaInicio() != null)
				bdConvenio.setFechaInicio(restaurativoDTO.getFechaInicio());
			if(restaurativoDTO.getFechaFin() != null)
				bdConvenio.setFechaFin(restaurativoDTO.getFechaFin());
			if(restaurativoDTO.getCompromisoPeriodicoDTO() != null  && restaurativoDTO.getCompromisoPeriodicoDTO().getMonto() != null)
				bdConvenio.getCompromisoPeriodico().setMonto(restaurativoDTO.getCompromisoPeriodicoDTO().getMonto());
			if(restaurativoDTO.getInvolucradoPR() != null  && restaurativoDTO.getInvolucradoPR().getElementoId()!= null)
				bdConvenio.setInvolucradoPR(new Involucrado(restaurativoDTO.getInvolucradoPR().getElementoId()));
			if(restaurativoDTO.getInvolucradoVictima() != null  && restaurativoDTO.getInvolucradoVictima().getElementoId()!= null)
				bdConvenio.setInvolucradoVictima(new Involucrado(restaurativoDTO.getInvolucradoVictima().getElementoId()));
			
			restaurativoDAO.update(bdConvenio);
		}
			

		return idAcuerdo;
	}

}
