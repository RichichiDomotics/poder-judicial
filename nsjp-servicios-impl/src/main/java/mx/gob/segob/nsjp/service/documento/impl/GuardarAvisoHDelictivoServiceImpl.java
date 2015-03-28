/**
 * 
 */
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.catalogo.CatDelitoDAO;
import mx.gob.segob.nsjp.dao.documento.AvisoHechoDelictivoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.hecho.HechoDAO;
import mx.gob.segob.nsjp.dao.persona.CorreoElectronicoDAO;
import mx.gob.segob.nsjp.dao.persona.TelefonoDAO;
import mx.gob.segob.nsjp.dao.quejaciudadana.ImplicadoDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.hecho.TiempoDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.model.AvisoHechoDelictivo;
import mx.gob.segob.nsjp.model.CatDelito;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Hecho;
import mx.gob.segob.nsjp.model.Implicado;
import mx.gob.segob.nsjp.model.Lugar;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.model.Tiempo;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.ConsultarAvisosHDelictivoService;
import mx.gob.segob.nsjp.service.documento.GuardarAvisoHDelictivoService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.AvisoHechoDelictivoTransformer;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.hecho.IngresarTiempoService;
import mx.gob.segob.nsjp.service.hecho.impl.transform.HechoTransformer;
import mx.gob.segob.nsjp.service.infra.ProcuraduriaClienteService;
import mx.gob.segob.nsjp.service.lugar.IngresarLugarService;
import mx.gob.segob.nsjp.service.persona.impl.transform.MedioDeContactoTransformer;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
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
public class GuardarAvisoHDelictivoServiceImpl implements
		GuardarAvisoHDelictivoService {
	
	public final static Logger logger = 
		Logger.getLogger(GuardarAvisoHDelictivoServiceImpl.class);
	
	@Autowired
	private AvisoHechoDelictivoDAO avisoHDDao;
	@Autowired
	private ImplicadoDAO implicadoDAO;
	@Autowired
	private HechoDAO hechoDAO;
	@Autowired
	private ExpedienteDAO expedienteDAO;
	@Autowired
	private CatDelitoDAO catDelitoDAO;
	@Autowired
	private IngresarLugarService ingresarLugarService;
	@Autowired
	private IngresarTiempoService ingresarTiempoService;
	@Autowired 
	private CorreoElectronicoDAO coreoElectronicoDAO;
	@Autowired
	private TelefonoDAO telefonoDAO;
    @Autowired
    private AsignarNumeroExpedienteService asignarNumExpService;
    @Autowired
    private ConsultarAvisosHDelictivoService consultaAHDService;
    
    @Autowired
    private ProcuraduriaClienteService pgClienteWebService;
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.GuardarAvisoHDelictivoService#guardarAvisoHDelictivo(mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO)
	 */
	@Override
	public Long guardarAvisoHDelictivo(AvisoHechoDelictivoDTO avisoDTO)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR/ACTUALIZAR AVISO DE HECHO DELICTIVO ****/");
		
		/*Verificaci�n de par�metros*/
		if(avisoDTO==null)
			logger.debug("/**** SERVICIO PARA CONSULTAR PLANTILLAS POR TIPO DE DOCUMENTO ****/");
		
		/*Operaci�n*/
		AvisoHechoDelictivo aviso=AvisoHechoDelictivoTransformer.transformarAviso(avisoDTO);
		Long idAviso=aviso.getDocumentoId();
		if(aviso.getDocumentoId()==null){
			idAviso=avisoHDDao.create(aviso);
		}else{
			avisoHDDao.update(aviso);
		}
		
		return idAviso;
	}


	@Override
	public AvisoHechoDelictivoDTO ingresarAvisoHechoDeictivoSSP(
            AvisoHechoDelictivoDTO avisoHechoDelictivoDTO)
            throws NSJPNegocioException {

        if (logger.isDebugEnabled())
            logger.debug("/**** SERVICIO PARA REGITRAR EN SSP UN AVISO HECHO DELICTIVO ****/");

        Long idAvisoHecho = new Long(0);

        AvisoHechoDelictivo avisoHechoDelictivo = AvisoHechoDelictivoTransformer
                .transformarAviso(avisoHechoDelictivoDTO);
        Expediente expediente = new Expediente();

        expediente.setFechaCreacion(new Date());
        expediente.setExpedienteId(expedienteDAO.create(expediente));
        if (avisoHechoDelictivoDTO.getFechaAtencion() == null) {
            avisoHechoDelictivo.setFechaAtencion(new Date());// registrando
                                                             // desde pantalla
        } else {
            avisoHechoDelictivo.setFechaAtencion(avisoHechoDelictivoDTO
                    .getFechaAtencion()); // registrando via WS
        }
        avisoHechoDelictivo.setEstatus(new Valor(
                EstatusNotificacion.NO_ATENDIDA.getValorId()));
        avisoHechoDelictivo.setFechaCreacion(new Date());
        avisoHechoDelictivo.setTipoDocumento(new Valor(
                TipoDocumento.NOTIFICACION.getValorId()));
        avisoHechoDelictivo.setNombreDocumento(TipoDocumento.NOTIFICACION
                .toString());
        avisoHechoDelictivo.setForma(new Forma(Formas.RECEPCION_LLAMADA_AUXILIO
                .getValorId()));

        if (avisoHechoDelictivoDTO.getCatDelito() != null) {
            CatDelito catDelito = catDelitoDAO.read(avisoHechoDelictivoDTO
                    .getCatDelito().getCatDelitoId());
            avisoHechoDelictivo.setCatDelito(catDelito);
        }

        Calendar calTemp = Calendar.getInstance();
        String año = String.valueOf(calTemp.get(Calendar.YEAR));
        if (StringUtils.isBlank(avisoHechoDelictivoDTO.getFolioNotificacion())) {
            String ultimoFolio = avisoHDDao.obtenerUltimoFolio();
            logger.debug("ultimoFolio :: " + ultimoFolio);
            String numFolio = "";
            if (ultimoFolio != null) {
                numFolio = ultimoFolio.substring(ultimoFolio.length() - 6,
                        ultimoFolio.length());
            } else {
                numFolio = "0";
            }
            Long numFolioLong = new Long(numFolio) + 1L;
            numFolio = numFolioLong.toString();
            numFolio = StringUtils.leftPad(numFolio, 6, "0");

            String folioGen = "AA-" + Instituciones.SSP.toString() + "-" + año
                    + numFolio;

            avisoHechoDelictivo.setConsecutivoNotificacion("1");
            avisoHechoDelictivo.setFolioNotificacion(folioGen);
        } else {
            avisoHechoDelictivo.setConsecutivoNotificacion(avisoHechoDelictivoDTO.getConsecutivoNotificacion());
            avisoHechoDelictivo.setFolioNotificacion(avisoHechoDelictivoDTO
                    .getFolioNotificacion());
        }
        if (BooleanUtils.isFalse(avisoHechoDelictivoDTO.getEsAnonimo())
                || StringUtils.isNotBlank(avisoHechoDelictivoDTO
                        .getNombreImplicado())) {
            Implicado implicado = new Implicado();

            if (avisoHechoDelictivoDTO.getNombreImplicado() != null)
                implicado
                        .setNombre(avisoHechoDelictivoDTO.getNombreImplicado());
            if (avisoHechoDelictivoDTO.getApellidoPatImplicado() != null)
                implicado.setApellidoPaterno(avisoHechoDelictivoDTO
                        .getApellidoPatImplicado());
            if (avisoHechoDelictivoDTO.getApellidoMatImplicado() != null)
                implicado.setApellidoMaterno(avisoHechoDelictivoDTO
                        .getApellidoMatImplicado());
            if (avisoHechoDelictivoDTO.getCalidadImplicado() != null)
                implicado.setTipoCalidad(new Valor(avisoHechoDelictivoDTO
                        .getCalidadImplicado().getIdCampo()));

            Long idImplicado = implicadoDAO.create(implicado);
            Telefono telefono = null;
            CorreoElectronico correo = null;
            if (avisoHechoDelictivoDTO.getMediosImplicado()!=null &&!avisoHechoDelictivoDTO.getMediosImplicado().isEmpty()) {
                Set<MedioDeContacto> mediosImplicado = new HashSet<MedioDeContacto>();
                for (MedioDeContactoDTO medioDTO : avisoHechoDelictivoDTO
                        .getMediosImplicado()) {
                    if (medioDTO instanceof TelefonoDTO) {
                        telefono = MedioDeContactoTransformer
                                .transformarTelefono((TelefonoDTO) medioDTO);
                        telefono.setImplicado(new Implicado(idImplicado));
                        telefono.setMedioDeContactoId(telefonoDAO
                                .create(telefono));
                        mediosImplicado.add(telefono);
                    }
                    if (medioDTO instanceof CorreoElectronicoDTO) {
                        correo = MedioDeContactoTransformer
                                .transformarCorreo((CorreoElectronicoDTO) medioDTO);
                        correo.setImplicado(new Implicado(idImplicado));
                        correo.setMedioDeContactoId(coreoElectronicoDAO
                                .create(correo));
                        mediosImplicado.add(correo);
                    }
                }
                implicado.setMedioDeContactos(mediosImplicado);
            }
            avisoHechoDelictivo.setImplicado(implicado);
        }

        if (avisoHechoDelictivoDTO.getHechoDTO() != null) {

            Hecho hecho = HechoTransformer
                    .transformarHecho(avisoHechoDelictivoDTO.getHechoDTO());
            hecho.setExpediente(expediente);

            HechoDTO hechoDTO = avisoHechoDelictivoDTO.getHechoDTO();
            if (hechoDTO.getLugar() != null) {
                LugarDTO lugarDTO = hechoDTO.getLugar();
                lugarDTO.setExpedienteDTO(new ExpedienteDTO(expediente
                        .getExpedienteId()));
                lugarDTO = ingresarLugarService.ingresarLugar(lugarDTO);
                hecho.setLugar(new Lugar(lugarDTO.getElementoId()));
            } else
                hecho.setLugar(null);
            logger.debug("hecho.getDescNarrativa() :: "
                    + hecho.getDescNarrativa());
            if (hechoDTO.getTiempo() != null) {
                TiempoDTO tiempoDTO = ingresarTiempoService
                        .ingresarTiempo(hechoDTO.getTiempo());
                hecho.setTiempo(new Tiempo(tiempoDTO.getTiempoId()));
            } else
                hecho.setTiempo(null);

            hechoDAO.create(hecho);
            avisoHechoDelictivo.setHecho(hecho);
        }

        idAvisoHecho = avisoHDDao.create(avisoHechoDelictivo);

        return new AvisoHechoDelictivoDTO(idAvisoHecho);
    }

	@Override
	public void asignarMotivoRechazoHD(Long avisoId, Long motivoRechazo)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ASIGNAR UN MOTIVO RECHAZO Y CAMBIAR ESTATUS DE UN AVISO HD****/");
		
		if(avisoId==null||motivoRechazo==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		AvisoHechoDelictivo aviso = avisoHDDao.read(avisoId);
		aviso.setMotivoRechazo(new Valor(motivoRechazo));
		aviso.setEstatus(new Valor(EstatusNotificacion.ATENDIDA.getValorId()));
		
		avisoHDDao.update(aviso);

	}


    @Override
    public void atenderAvisoHechoDelictivo(AvisoHechoDelictivoDTO input) throws NSJPNegocioException{
        logger.info("Incia - atenderAvisoHechoDelictivo(...)");
        if (input==null  || input.getDocumentoId()==null || input.getUsuario()==null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        AvisoHechoDelictivo avisoPojo = this.avisoHDDao.read(input.getDocumentoId());
        ExpedienteDTO expedienteParam = new ExpedienteDTO();
        expedienteParam.setExpedienteId(avisoPojo.getHecho().getExpediente().getExpedienteId());
        expedienteParam.setFechaApertura(new Date());
        expedienteParam.setArea(input.getUsuario().getAreaActual());
        expedienteParam.setUsuario(input.getUsuario());
        expedienteParam.setEstatus(new ValorDTO(EstatusExpediente.NO_ATENDIDO.getValorId()));
        
        this.asignarNumExpService.asignarNumeroExpediente(expedienteParam);
        
        avisoPojo.setEstatus(new Valor(EstatusNotificacion.ATENDIDA.getValorId()));
        logger.info("Fin - atenderAvisoHechoDelictivo(...)");
    }


    @Override
    public void enviarAvisoHDelictivo(AvisoHechoDelictivoDTO avisoDTO)
            throws NSJPNegocioException {
        AvisoHechoDelictivoDTO avisoToSend = this.consultaAHDService.consultarAvisoHDelictivo(avisoDTO);
        this.pgClienteWebService.enviarAvisoHechoDelictivo(avisoToSend);
        
    }

    
    
}
