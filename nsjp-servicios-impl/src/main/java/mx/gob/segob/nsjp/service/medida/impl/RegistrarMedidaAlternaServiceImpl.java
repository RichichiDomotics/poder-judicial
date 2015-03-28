/**
 * Nombre del Programa  : RecibirMedidaCautelarServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 29 Ago 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Implementación del servicio encargado de recibir y 
 * registrar en la BDD objetos de tipo Medida Cautelar
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.medida.impl;

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.medida.MedidaAlternaDAO;
import mx.gob.segob.nsjp.dao.parametro.ParametroDAO;
import mx.gob.segob.nsjp.dao.usuario.UsuarioDAO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaWSDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.MedidaAlterna;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.medida.RegistrarMedidaAlternaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("registrarMedidaAlternaService")
@Transactional
public class RegistrarMedidaAlternaServiceImpl implements
		RegistrarMedidaAlternaService {
    /**
     * Logger.
     */
	public static final Logger logger = Logger.getLogger(RegistrarMedidaAlternaServiceImpl.class);
	
	@Autowired
	private IngresarIndividuoService ingresarIndividuoService;
	@Autowired
	private ExpedienteDAO expedienteDAO;
	@Autowired
	private MedidaAlternaDAO medidaAlternaDao; 
	@Autowired
	private ArchivoDigitalDAO archivoDigitalDAO;
	@Autowired
	private AsignarNumeroExpedienteService asignarNumeroExpedienteService;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private ParametroDAO paramDao;
	@Autowired
	private UsuarioDAO usrDao; 
	
	@Override
	public void registrarMedidaAlterna(MedidaAlternaWSDTO medidaCautelar) throws NSJPNegocioException {
			
        MedidaAlterna medida = new MedidaAlterna();
        	
        	if (logger.isDebugEnabled()) {
        		logger.debug("/**** SERVICIO PARA REGISTRAR MEDIDA ALTERNA EN SSP ****/");
        	}
        
			InvolucradoDTO sujeto = new InvolucradoDTO();
			NombreDemograficoDTO ndDTO = new NombreDemograficoDTO();
			ndDTO.setNombre(medidaCautelar.getNombreSujeto());
			ndDTO.setApellidoPaterno(medidaCautelar.getaPaternoSujeto());
			ndDTO.setApellidoMaterno(medidaCautelar.getaMaternoSujeto());
			sujeto.getNombresDemograficoDTO().add(ndDTO);
			CalidadDTO calidad = new CalidadDTO();
			calidad.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
			sujeto.setCalidadDTO(calidad);
			
			ExpedienteDTO input = new ExpedienteDTO();
			input.setArea(new AreaDTO(Areas.COORDINACION_SEGUIMIENTO_SSP));
			Usuario usrRobot = this.usrDao.read(this.paramDao.obtenerPorClave(Parametros.ID_USUARIO_ROBOT_SISTEMA).getValorAsLong());
			input.setUsuario(UsuarioTransformer.transformarUsuario(usrRobot));
			ExpedienteDTO expGenerado = asignarNumeroExpedienteService.asignarNumeroExpediente(input);
			
			logger.debug("/**** SE GENRO EL EXPEDINTE CON ID :: "+ expGenerado.getExpedienteId() + "****/");
			
			Expediente expediente = expedienteDAO.read(expGenerado.getExpedienteId());
			expediente.setFechaCreacion(Calendar.getInstance().getTime());
			expediente.setPertenceConfInst(new ConfInstitucion(Instituciones.SSP.getValorId()));			
			expedienteDAO.saveOrUpdate(expediente);
			sujeto.setExpedienteDTO(new ExpedienteDTO(expediente.getExpedienteId()));
			sujeto.setElementoId(ingresarIndividuoService.ingresarIndividuo(sujeto));
		
			NumeroExpediente numExp = numeroExpedienteDAO.read(expGenerado.getNumeroExpedienteId());
			
			logger.debug("/**** CON EL NUMERO DE EXPEDIENTE ID :: "+ numExp.getNumeroExpedienteId() + "****/");
			
	        ArchivoDigital archivo = new ArchivoDigital();
	    	archivo.setNombreArchivo(medidaCautelar.getArchivoDigital().getNombreArchivo());
	    	archivo.setTipoArchivo(medidaCautelar.getArchivoDigital().getTipoArchivo());
	    	archivo.setContenido(medidaCautelar.getArchivoDigital().getContenido());
	    	archivo.setArchivoDigitalId(archivoDigitalDAO.create(archivo));
			
		medida.setInvolucrado(new Involucrado(sujeto.getElementoId()));
	    medida.setArchivoDigital(archivo);
        medida.setTextoParcial(medidaCautelar.getTextoParcial());
		medida.setFolioDocumento(medidaCautelar.getFolioDocumento());
        medida.setValorPeriodicidad(new Valor(medidaCautelar.getIdValorPeriodicidad()));
        medida.setValorTipoMedida(new Valor(medidaCautelar.getIdValorTipoMedida()));
        medida.setFechaInicio(medidaCautelar.getFechaInicio());
        medida.setFechaFin(medidaCautelar.getFechaFin());
        medida.setDescripcionMedida(medidaCautelar.getDescripcionMedida());
        medida.setAnios(medidaCautelar.getAnios());
        medida.setMeses(medidaCautelar.getMeses());
        medida.setJuezOrdena(medidaCautelar.getJuezOrdena());
        medida.setNumeroCarpetaEjecucion(medidaCautelar.getNumeroCarpetaEjecucion());
        medida.setNumeroCausa(medidaCautelar.getNumeroCausa());
        medida.setNumeroCaso(medidaCautelar.getNumeroCaso());
        medida.setForma(new Forma(medidaCautelar.getFormaId()));
        medida.setTipoDocumento(new Valor(medidaCautelar.getTipoDocumentoDTO()));
        
        medida.setFechaCreacion(medidaCautelar.getFechaCreacion());
        
        medida.setEstatus(new Valor(EstatusMedida.NO_ATENDIDA.getValorId()));
        medida.setNombreDocumento("Medida alterna "+medida.getFolioDocumento());
        medida.setConfInstitucion(new ConfInstitucion(Instituciones.PJ.getValorId()));
        medida.setNumeroExpediente(numExp);
        
        medida.setDocumentoId(medidaAlternaDao.create(medida));
        
        logger.debug("/**** SE REGISTRO LA MEDIDA ALTERNA EN SSP EXITOSAMENTE ****/");
	}
}
