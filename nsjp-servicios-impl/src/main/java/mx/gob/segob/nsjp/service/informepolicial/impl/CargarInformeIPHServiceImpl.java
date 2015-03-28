/**
 * 
 */
package mx.gob.segob.nsjp.service.informepolicial.impl;

import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.BODY_TAG_APERTURA;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.BODY_TAG_CIERRE;

import java.io.ByteArrayOutputStream;
import java.io.StringBufferInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import mx.gob.segob.nsjp.comun.constants.ConstantesGenerales;
import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.elemento.TipoElemento;
import mx.gob.segob.nsjp.comun.enums.elemento.TipoTelefono;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.enums.tiempo.TipoTiempo;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.HTMLUtils;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dao.hecho.HechoDAO;
import mx.gob.segob.nsjp.dao.informepolicial.InformePolicialHomologadoDAO;
import mx.gob.segob.nsjp.dao.objeto.AeronaveDAO;
import mx.gob.segob.nsjp.dao.objeto.ArmaDAO;
import mx.gob.segob.nsjp.dao.objeto.DocumentoOficialDAO;
import mx.gob.segob.nsjp.dao.objeto.EmbarcacionDAO;
import mx.gob.segob.nsjp.dao.objeto.EquipoTacticoDAO;
import mx.gob.segob.nsjp.dao.objeto.ExplosivoDAO;
import mx.gob.segob.nsjp.dao.objeto.InmuebleDAO;
import mx.gob.segob.nsjp.dao.objeto.NumerarioDAO;
import mx.gob.segob.nsjp.dao.objeto.SustanciaDAO;
import mx.gob.segob.nsjp.dao.objeto.VehiculoDAO;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.Aeronave;
import mx.gob.segob.nsjp.model.AliasInvolucrado;
import mx.gob.segob.nsjp.model.Arma;
import mx.gob.segob.nsjp.model.ArmaExplosivoInvolucrado;
import mx.gob.segob.nsjp.model.ArmaExplosivoInvolucradoId;
import mx.gob.segob.nsjp.model.CoordenadaGeografica;
import mx.gob.segob.nsjp.model.DelitoIph;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.model.DocumentoOficial;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Embarcacion;
import mx.gob.segob.nsjp.model.EquipoTactico;
import mx.gob.segob.nsjp.model.Explosivo;
import mx.gob.segob.nsjp.model.FaltaAdministrativaIph;
import mx.gob.segob.nsjp.model.Hecho;
import mx.gob.segob.nsjp.model.InformePolicialHomologado;
import mx.gob.segob.nsjp.model.Inmueble;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.model.MedioTransporte;
import mx.gob.segob.nsjp.model.MedioTransporteId;
import mx.gob.segob.nsjp.model.Multimedia;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Numerario;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.model.ObjetoAsegurado;
import mx.gob.segob.nsjp.model.ObjetoAseguradoId;
import mx.gob.segob.nsjp.model.PuntoCarretero;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.model.Sustancia;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.model.Vehiculo;
import mx.gob.segob.nsjp.service.actividad.RegistrarActividadService;
import mx.gob.segob.nsjp.service.documento.GuardarDocumentoService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.forma.impl.transform.FormaTransformer;
import mx.gob.segob.nsjp.service.informepolicial.CargarInformeIPHService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 * @author adrian
 *
 */
@Service
@Transactional
public class CargarInformeIPHServiceImpl implements CargarInformeIPHService {
	
	private static final Logger log  = Logger.getLogger(CargarInformeIPHServiceImpl.class);
	
	@Autowired
	private InformePolicialHomologadoDAO informePolicialHomologadoDAO;
	@Autowired
	private ActividadDAO actividadDAO;
	@Autowired
	private RelacionDAO relacionDAO;
	@Autowired
	private VehiculoDAO vehiculoDAO;
	@Autowired
	private AeronaveDAO aeronaveDAO;
	@Autowired
	private EmbarcacionDAO embarcacionDAO;
	@Autowired
	private ArmaDAO armaDAO;
	@Autowired
	private EquipoTacticoDAO equipoTacticoDAO;
	@Autowired
	private NumerarioDAO numerarioDAO;
	@Autowired
	private DocumentoOficialDAO documentoOficialDAO;
	@Autowired
	private InmuebleDAO inmuebleDAO;
	@Autowired
	private ExplosivoDAO explosivoDAO;
	@Autowired
	private SustanciaDAO sustanciaDAO;
	@Autowired
	private GuardarDocumentoService guardarDocumentoService;
	@Autowired
	private FormaDAO formaDAO;
	@Autowired
	private HechoDAO hechoDAO;
	@Autowired
	private RegistrarActividadService registrarActividadService;
	

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.informepolicial.CargarInformeIPHService#cargarInformeIPH(mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO)
	 */
	@Override
	public DocumentoDTO cargarInformeIPH(
			InformePolicialHomologadoDTO homologadoDTO)
			throws NSJPNegocioException {
		if (log.isDebugEnabled())
			log.debug("/**** SERVICIO PARA GENERAR EL HTML DE INFORME IPH ****/");
		
		/*Revisión de parámetros*/
		if(homologadoDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(homologadoDTO.getFolioIPH()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
	
		/*Obtener Id a partir de Folio*/
		InformePolicialHomologado informe = informePolicialHomologadoDAO.consultaInformePorFolio(homologadoDTO.getFolioIPH());
		if(informe==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		else if(informe.getInformePolicialHomologadoId()==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		
		/*Recupera Información*/
//		InformePolicialHomologado informe = informePolicialHomologadoDAO.consultarInformePorId(informeRegistrado.getInformePolicialHomologadoId());
//		Expediente expediente = expedienteDAO.read(homologadoDTO.getExpediente().getExpedienteId());
//		informe.setExpediente(expediente);
		Actividad actividadActual = actividadDAO.obtenerActividadActual(informe.getExpediente().getExpedienteId());
		
		DocumentoDTO documento = null;
		//buscar si en la actividadActual existe un documento con guardado parcial
		if(actividadActual != null && actividadActual.getDocumento() != null && StringUtils.isNotBlank(actividadActual.getDocumento().getTextoParcial())
				&& actividadActual.getDocumento().getArchivoDigital() == null){
			//ent
			documento = DocumentoTransformer.transformarDocumento(actividadActual.getDocumento());
		}
		//si no existe un documento con guardado parcial entonces se crea un documento con el cuerpo de la
		//forma  que se va a emitir en ese momento
		if(actividadActual==null){
			actividadActual=new Actividad();
			actividadActual.setActividadId(registrarActividadService.registrarActividad(new ExpedienteDTO(informe.getExpediente().getExpedienteId()), new FuncionarioDTO(informe.getFuncionarioElabora().getClaveFuncionario()), Actividades.ADJUNTAR_ARCHIVO_DIGITAL.getValorId()));
		}
		if(documento == null){
			documento = new DocumentoDTO();
			
			if(informe!=null){
				/*Obtiene el hecho*/
				Hecho hecho = hechoDAO.consultarHechoByExpediente(informe.getExpediente().getExpedienteId());
				informe.setHecho(hecho);
				/*Involucrados a InvolucradosIPH*/
				informe.setInvolucradosExp(obtenInvolucradosExp(informe.getExpediente().getElementos()));
				/*Medios de Transporte*/
				informe.setMedioTransportes(obtenerMediosTransporteExp(informe.getExpediente().getElementos(),informe.getInformePolicialHomologadoId()));
				/*Armas involucradas*/
				informe.setArmaExplosivoInvolucrados(obtenerArmasExp(informe.getExpediente().getElementos(),informe.getInformePolicialHomologadoId()));
				/*Asegurados*/
				informe.setObjetoAsegurados(obtenerObjetosAsegExp(informe.getExpediente().getElementos(),informe.getInformePolicialHomologadoId()));
				
				/*Llenar plantilla*/
				documento.setTextoParcial(reemplazarCamposIPH(informe));
				/*Genera PDF*/
				documento.setDocumentoId(guardadoDirecto(documento,informe));
			}
		}
		return documento;
	}
	
private Set<ObjetoAsegurado> obtenerObjetosAsegExp(Set<Elemento> elementos,
			Long informePolicialHomologadoId) {
		Set<ObjetoAsegurado> asegurados=new HashSet<ObjetoAsegurado>();
		
		for (Elemento ele : elementos) {
			if(ele.getTipoElemento().getValorId().equals(TipoElemento.OBJETO.getValorId())){
				Objeto obj = (Objeto) ele;
				if(obj.getValorByTipoObjetoVal().getValorId().equals(Objetos.NUMERARIO.getValorId())||
						obj.getValorByTipoObjetoVal().getValorId().equals(Objetos.EQUIPO_TACTICO.getValorId())||
						obj.getValorByTipoObjetoVal().getValorId().equals(Objetos.EXPLOSIVO.getValorId())||
						obj.getValorByTipoObjetoVal().getValorId().equals(Objetos.SUSTANCIA.getValorId())||
						obj.getValorByTipoObjetoVal().getValorId().equals(Objetos.INMUEBLE.getValorId())){
					InformePolicialHomologado infoPH=new InformePolicialHomologado();
					infoPH.setInformePolicialHomologadoId(informePolicialHomologadoId);
					
					ObjetoAseguradoId id=new ObjetoAseguradoId(informePolicialHomologadoId, ele.getElementoId());
					ObjetoAsegurado objAseg=new ObjetoAsegurado(id, (Objeto)ele, infoPH);
					asegurados.add(objAseg);
				}
			}
		}
		return asegurados;
	}

private Set<ArmaExplosivoInvolucrado> obtenerArmasExp(
			Set<Elemento> elementos, Long informePolicialHomologadoId) {
		Set<ArmaExplosivoInvolucrado> armas=new HashSet<ArmaExplosivoInvolucrado>();
		for (Elemento ele : elementos) {
			if(ele.getTipoElemento().getValorId().equals(TipoElemento.OBJETO.getValorId())){
				InformePolicialHomologado infoPH=new InformePolicialHomologado();
				infoPH.setInformePolicialHomologadoId(informePolicialHomologadoId);
				ArmaExplosivoInvolucradoId id=new ArmaExplosivoInvolucradoId(informePolicialHomologadoId, ele.getElementoId());
				ArmaExplosivoInvolucrado arma=new ArmaExplosivoInvolucrado(id, null, infoPH);
				Objeto obj = (Objeto) ele;
				if(obj.getValorByTipoObjetoVal().getValorId().equals(Objetos.ARMA.getValorId())){
					arma.setObjeto((Objeto) ele); 
					armas.add(arma);
				}
			}
		}
		return armas;
}

private Set<MedioTransporte> obtenerMediosTransporteExp(
			Set<Elemento> elementos, Long informePolicialHomologadoId) {
		Set<MedioTransporte> medios=new HashSet<MedioTransporte>();
		for (Elemento ele : elementos) {
			if(ele.getTipoElemento().getValorId().equals(TipoElemento.OBJETO.getValorId())){
				Objeto obj = (Objeto) ele;
				if(obj.getValorByTipoObjetoVal().getValorId().equals(Objetos.EMBARCACION.getValorId())||
						obj.getValorByTipoObjetoVal().getValorId().equals(Objetos.AERONAVE.getValorId())||
						obj.getValorByTipoObjetoVal().getValorId().equals(Objetos.VEHICULO.getValorId())){
					InformePolicialHomologado infoPH=new InformePolicialHomologado();
					infoPH.setInformePolicialHomologadoId(informePolicialHomologadoId);
					MedioTransporteId id=new MedioTransporteId(informePolicialHomologadoId, ele.getElementoId());
					MedioTransporte medio=new MedioTransporte(id, (Objeto)ele, infoPH);
					medios.add(medio);
				}
			}
		}
		return medios;
	}

private List<Involucrado> obtenInvolucradosExp(Set<Elemento> elementos) {
		List<Involucrado> invsIPH=new ArrayList<Involucrado>();
		for (Elemento ele : elementos) {
			if(ele.getTipoElemento().getValorId().equals(TipoElemento.PERSONA.getValorId())){
				invsIPH.add((Involucrado) ele);
			}
		}
		return invsIPH;
	}

private String reemplazarCamposIPH(InformePolicialHomologado informe) {
		
		if (log.isDebugEnabled())
			log.debug("/**** SERVICIO PARA RELLENAR LA PLANTILLA DE IPH ****/");
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df=new DecimalFormat("00");
		Boolean entrega1=false;
		
		String cuerpoIPH="";
		if(informe!=null){
			Locale loc	 = new Locale("es");
			Calendar ahora =Calendar.getInstance();
			Date mes=new Date(ahora.getTimeInMillis());
			String strMes=new SimpleDateFormat("MMMMMMMMMM",loc).format(mes);
//			Expediente expediente = informe.getExpediente();
			String strTurno="___________________";
			if(informe.getTurnoLaboralIphs().size()>0){
				strTurno=informe.getTurnoLaboralIphs().iterator().next().getTurnoLaboral().getNombreTurno();
			}
			String strOficio=((informe.getNumeroOficio()!=null)?String.valueOf(informe.getNumeroOficio()):"NO ASIGNADO");
			String strTipoPart=(informe.getTipoParticipacion()!=null)?informe.getTipoParticipacion().getValor():"NO ASIGNADO";
			String strAsunto=(informe.getAsunto()!=null)?informe.getAsunto():"NO ASIGNADO";
			cuerpoIPH ="<p><strong>Informe Policial Homologado</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Formato PF-IPH-09-1</p>"+
			"<p><strong>_______________________________________________________________________________________________________</strong></p>"+
			"<p><strong>DATOS GENERALES.</strong></p>"+
			"<table><tr><td>Folio:&nbsp;"+informe.getFolioIPH()+"</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td>"+
			"<td>No. de oficio:&nbsp;"+strOficio+"</td></tr>"+ 
			"<tr><td>Fecha del Evento:&nbsp;"+ahora.get(Calendar.DAY_OF_MONTH)+" de "+strMes+" de "+ahora.get(Calendar.YEAR)+"</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td>"+
			"<td>Hora del Evento:&nbsp;"+df.format(ahora.get(Calendar.HOUR_OF_DAY))+":"+df.format(ahora.get(Calendar.MINUTE))+"&nbsp;&nbsp;</td></tr></table>"+
			"<br/>"+
			"<p>No. Eco. Transporte(s) Oficial(es):&nbsp;"+informe.getNumEcoTransporte()+"&nbsp;&nbsp;</p>" +
			"<p>Asunto:&nbsp;"+(strAsunto!=null?strAsunto:"")+"&nbsp;&nbsp;</p>";
			if( informe.getFuncionarioDestinatario()!= null && informe.getFuncionarioDestinatario().getFuncionarioJefe()!=null){
				cuerpoIPH +="<p>Dirigido a:&nbsp;"+
						(informe.getFuncionarioDestinatario().getFuncionarioJefe().getNombreCompleto()!=null?informe.getFuncionarioDestinatario().getFuncionarioJefe().getNombreCompleto():"")
						+"&nbsp;&nbsp;</p>";
				cuerpoIPH +="<p>Oficial:&nbsp;"+
						(informe.getFuncionarioDestinatario().getNombreCompleto()!=null?informe.getFuncionarioDestinatario().getNombreCompleto(): "")
						+"&nbsp;&nbsp;</p>"+
				"<p>Sector: "+
						(informe.getFuncionarioDestinatario().getArea().getNombre()!=null?informe.getFuncionarioDestinatario().getArea().getNombre():"")
						+"</p><p>Turno: "+strTurno+"</p>"+
				"<p>Participaci&oacute;n:&nbsp;"+strTipoPart+"&nbsp;&nbsp;</p>";
			}
			if(informe.getOperativo()!=null){
				cuerpoIPH+="<p>Operativo:&nbsp;SI&nbsp;&nbsp;</p>"+
				"<p>&nbsp;&nbsp;&nbsp;&nbsp;Nombre del operativo:&nbsp;"
						+(informe.getOperativo().getNombre()!=null? informe.getOperativo().getNombre(): "")
						+"&nbsp;&nbsp;</p>"+
				"<p>&nbsp;&nbsp;&nbsp;&nbsp;Nombre del Comandante de Agrupamiento:&nbsp;"
						+(informe.getOperativo().getNombreComteAgrupto()!=null?informe.getOperativo().getNombreComteAgrupto(): "")
						+"&nbsp;&nbsp;</p>"+
				"<p>&nbsp;&nbsp;&nbsp;&nbsp;Nombre del Comandante del Operativo:&nbsp;"
						+(informe.getOperativo().getNombreComte()!=null? informe.getOperativo().getNombreComte():"")
						+"&nbsp;&nbsp;</p>";
			}else
				cuerpoIPH+="<p>Operativo:&nbsp;NO&nbsp;&nbsp;</p>";
			
			/*MOTIVO*/
			cuerpoIPH+="<p><strong>MOTIVO (Clasificaci&oacute;n del evento)</strong></p>";
				if(informe.getDelitoIphs().size()>0){
					cuerpoIPH+="<table><tr><td>Tipo de evento:&nbsp;Delito&nbsp;&nbsp;</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
							"<td>Subtipo de evento:&nbsp;"+informe.getDelitoIphs().iterator().next().getCatDelito().getNombre()+"&nbsp;&nbsp;</td></tr></table>";
				}else 
					if(informe.getFaltaAdministrativaIphs().size()>0){
						cuerpoIPH+="<table><tr><td>Tipo de evento:&nbsp;Falta Administrativa&nbsp;&nbsp;</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
						"<td>Subtipo de evento:&nbsp;"+informe.getFaltaAdministrativaIphs().iterator().next().getCatFaltaAdministrativa().getDescripcionFalta()+"&nbsp;&nbsp;</td></tr></table>";
					}else{
						cuerpoIPH+="<table><tr><td>Tipo de evento:&nbsp;NO REGISTRADO&nbsp;&nbsp;</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
								"<td>Subtipo de evento:&nbsp;"+"_________________"+"&nbsp;&nbsp;</td></tr></table>";
					}
			
			/*UBICACION*/
			
			if(informe.getHecho()!=null){
				Hecho hecho = informe.getHecho();
				cuerpoIPH+="<p><strong>LUGAR DE LOS HECHOS</strong></p>";
				/*Domicilio*/
				if(hecho.getLugar()!=null){
					if(hecho.getLugar().getElementoId()!=null){
						if(hecho.getLugar() instanceof Domicilio){
//							Domicilio domicilio = domicilioDAO.read(hecho.getLugar().getElementoId());
							Domicilio domicilio = (Domicilio) hecho.getLugar();
							String strTipoCalle=(domicilio.getTipoElemento().getValor()!=null)?domicilio.getTipoElemento().getValor():"";
							String strCalle=(domicilio.getCalle()!=null)?domicilio.getCalle():"";
							String strNumeroExt=(domicilio.getNumeroExterior()!=null)?domicilio.getNumeroExterior():"";
							String strNumeroInt=(domicilio.getNumeroInterior()!=null)?domicilio.getNumeroInterior():"";
							cuerpoIPH+="<p>Estado:&nbsp;"+(domicilio.getEntidadFederativa() != null &&
									domicilio.getEntidadFederativa().getNombre() != null? domicilio.getEntidadFederativa().getNombre(): "")+"&nbsp; Municipio:&nbsp;"+
									(domicilio.getMunicipio() != null && domicilio.getMunicipio().getNombreMunicipio() != null? domicilio.getMunicipio().getNombreMunicipio():"")
									+"</p>";
							String strLocalidad="";
							String strCodigoPost="";
							if(domicilio.getAsentamiento()!=null){
								strLocalidad=(domicilio.getAsentamiento()!=null)?domicilio.getAsentamiento().getNombreAsentamiento():"____________";
								strCodigoPost=(domicilio.getAsentamiento().getCodigoPostal()!=null)?domicilio.getAsentamiento().getCodigoPostal():"";
							}
							cuerpoIPH+="<p>Localidad o Colonia:&nbsp;"+strLocalidad+"</p>"+
							"<p>Calle:&nbsp;"+strTipoCalle+" "+strCalle+"&nbsp;&nbsp; No. Ext.:&nbsp;"+strNumeroExt+"&nbsp;&nbsp; No. Int.:&nbsp;"+strNumeroInt+"&nbsp;&nbsp; C.P.:&nbsp;"+strCodigoPost+"&nbsp;&nbsp;</p>"+
							"<p>Entre que calle:&nbsp;"
								+(domicilio.getEntreCalle1()!=null?domicilio.getEntreCalle1():"")
								+"&nbsp;&nbsp; y que calle:&nbsp;"
								+(domicilio.getEntreCalle2()!=null?domicilio.getEntreCalle2():"")
								+"&nbsp;&nbsp;</p>"+
							"<p>Referencia:&nbsp;"
								+(domicilio.getReferencias()!=null? domicilio.getReferencias():"")
								+"</p>";
						}else if(hecho.getLugar() instanceof PuntoCarretero){
							PuntoCarretero punto=(PuntoCarretero) hecho.getLugar();
							cuerpoIPH+="<p>Caminos: SI&nbsp;</p>"+
							"<p>Tramo carretero:&nbsp;"
								+(punto.getNombreTramo()!=null? punto.getNombreTramo():"")
								+"&nbsp;&nbsp; Kil&oacute;metro:"
								+(punto.getKilometro()!=null?punto.getKilometro():"")
								+"</p>";
						}
						if(hecho.getLugar().getCoordenadaGeograficas().size()>0){
							CoordenadaGeografica coord = hecho.getLugar().getCoordenadaGeograficas().iterator().next();
							String[] grados = coord.getLatitud().split("°");
							String[] minutos = grados[1].split("'");
							String[] segundos = minutos[1].split("'");
							cuerpoIPH+="<p>Latitud GMS:&nbsp;Grados: "+grados[0]+"&nbsp;Minutos: "+minutos[0]+"&nbsp;Segundos: "+segundos[0]+"</p>";
							grados = coord.getLongitud().split("°");
							minutos = grados[1].split("'");
							segundos = minutos[1].split("'");
							cuerpoIPH+="<p>Longitud GMS:&nbsp;Grados: "+grados[0]+"&nbsp;Minutos: "+minutos[0]+"&nbsp;Segundos: "+segundos[0]+"</p>";
						}
						if(hecho.getTiempo()!=null){
							Valor tipoReg = hecho.getTiempo().getTipoRegistro();
							if(tipoReg!=null){
								ahora.setTime(hecho.getTiempo().getFechaInicio());
								strMes=new SimpleDateFormat("MMMMMMMMMM",loc).format(hecho.getTiempo().getFechaInicio());
								if(tipoReg.getValorId().equals(TipoTiempo.ESPECIFICAMENTE_EN.getValorId())){
									cuerpoIPH+="<p>Fecha:&nbsp;"+ahora.get(Calendar.DAY_OF_MONTH)+" de "+strMes+" de "+ahora.get(Calendar.YEAR)+"</p>"+
									"<p>Hora:&nbsp;"+df.format(ahora.get(Calendar.HOUR_OF_DAY))+":"+df.format(ahora.get(Calendar.MINUTE))+"</p>";
								}else if(tipoReg.getValorId().equals(TipoTiempo.EVENTO_EN_EL_TIEMPO.getValorId())){
									String strDesTiempo=(hecho.getTiempo().getDescripcion()!=null)?hecho.getTiempo().getDescripcion():"NO REGISTRADA";
									cuerpoIPH+="<p>Descripci&oacute;n:&nbsp;"+strDesTiempo+"</p>";
								}else if(tipoReg.getValorId().equals(TipoTiempo.LAPSO.getValorId())){
									ahora.setTime(hecho.getTiempo().getFechaInicio());
									strMes=new SimpleDateFormat("MMMMMMMMMM",loc).format(hecho.getTiempo().getFechaInicio());
									cuerpoIPH+="<p>Fecha de Inicio:&nbsp;"+ahora.get(Calendar.DAY_OF_MONTH)+" de "+strMes+" de "+ahora.get(Calendar.YEAR)+"</p>"+
									"<p>Hora de Inicio:&nbsp;"+df.format(ahora.get(Calendar.HOUR_OF_DAY))+":"+df.format(ahora.get(Calendar.MINUTE))+"</p>";
									ahora.setTime(hecho.getTiempo().getFechaFin());
									strMes=new SimpleDateFormat("MMMMMMMMMM",loc).format(hecho.getTiempo().getFechaFin());
									cuerpoIPH+="<p>Fecha de Fin:&nbsp;"+ahora.get(Calendar.DAY_OF_MONTH)+" de "+strMes+" de "+ahora.get(Calendar.YEAR)+"</p>"+
									"<p>Hora de Fin:&nbsp;"+df.format(ahora.get(Calendar.HOUR_OF_DAY))+":"+df.format(ahora.get(Calendar.MINUTE))+"</p>";
								}
							}
						}
					}
				}
				cuerpoIPH+="<p><strong>DESCRIPCI&Oacute;N DE LOS HECHOS.</strong></p>"+hecho.getDescNarrativa();
			}//Fin Ubicacion y hecho
			
			/*INVOLUCRADOS*/
			cuerpoIPH+="<p><strong>_______________________________________________________________________________________________________</strong></p>"+
			"<p><strong>PERSONAS INVOLUCRADAS</strong></p>";
			int numPer=1;
			for (Involucrado invol : informe.getInvolucradosExp()) {
				cuerpoIPH+="<p><strong>** Persona "+numPer+" **</strong></p>";
				/*DATOS DEMOGRAFICOS*/
				Domicilio domicilioInvol=obtenRelacionDomicilio(invol.getElementoId());
				String strDomicilio="NO REGISTRADA";
				if(domicilioInvol!=null){
//					strDomicilio=domicilioInvol.getCalle()+" "+domicilioInvol.getNumeroExterior()+" "+domicilioInvol.getCiudad();
					strDomicilio=domicilioInvol.toString();
				}
				NombreDemografico demo = invol.getNombreDemograficos().iterator().next();
				cuerpoIPH+="<p>Apellido Paterno:&nbsp;"
					+(demo.getApellidoPaterno()!=null?demo.getApellidoPaterno(): "")
					+"&nbsp;&nbsp; Apellido Materno:&nbsp;"
					+(demo.getApellidoMaterno()!=null?demo.getApellidoMaterno():"")
					+"&nbsp;&nbsp; Nombre (s):&nbsp;"
					+(demo.getNombre()!=null?demo.getNombre():"")
					+"&nbsp;&nbsp;</p>";
				if(invol.getAliasInvolucrados().size()>0){
					AliasInvolucrado alia = invol.getAliasInvolucrados().iterator().next();
					cuerpoIPH+="<p>Alias:&nbsp;"
						+(alia.getAlias()!=null? alia.getAlias():"")
						+"&nbsp;&nbsp;</p>";
				}
				String strFechaNacim="NO REGISTRADA";
				String strMayorEdad="_____";
				if(demo.getEdadAproximada()!=null)
					strMayorEdad=(demo.getEdadAproximada()>=18)?"SÍ":"NO";
				if(demo.getFechaNacimiento()!=null)
					strFechaNacim=formato.format(demo.getFechaNacimiento());
//				cuerpoIPH+="<p>País de origen: "+demo.getPaisNacimiento().getValor()+"&nbsp; Grupo &Eacute;tnico:_______</p>"+
				cuerpoIPH+="<p>País de origen: "+demo.getPaisNacimiento().getValor()+"</p>"+
				"<table><tr><td>Fecha de Nacimiento:"+strFechaNacim+"</td>" +
				"<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
				"<td>Edad:"+demo.getEdadAproximada()+"</td></tr>" +
				"<tr><td>RFC:"
					+(demo.getRfc()!=null?demo.getRfc():"")
					+"</td>" +
				"<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
				"<td>Sexo:"
					+(demo.getSexo()!=null?demo.getSexo().toUpperCase():"")
					+"</td></tr></table>"+
				"<p>Domicilio: "+strDomicilio+"&nbsp;</p>"+
				"<p>En calidad de: "
					+(invol.getCalidad().getTipoCalidad()!=null? invol.getCalidad().getTipoCalidad().getValor(): "")
					+"&nbsp;&nbsp;</p>"+
				"<p>Mayor de edad:&nbsp;"+strMayorEdad+"&nbsp;&nbsp;</p>";
//				"<p>Situaci&oacute;n:&nbsp;"+invol.getCalidad().getTipoCalidad().getValor()+"&nbsp;&nbsp;</p>";
//				"<p>Estatus residencial: _________</p>"+
//				"<p>Origen:&nbsp;______________________&nbsp;&nbsp; Destino:&nbsp;_________________________&nbsp;&nbsp;</p>";
				
				if(invol.getCalidad().getTipoCalidad().getValorId().equals(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId())){
					/*DELITOS O FALTAS*/
					cuerpoIPH+="<p><strong>PROBABLES DELITOS O FALTAS ADMINISTRATIVAS</strong></p>";
					List<DelitoIph> delitosIP =new ArrayList<DelitoIph>(informe.getDelitoIphs());
					for (DelitoIph deli : delitosIP) {
						cuerpoIPH+="<p>"
							+(deli.getCatDelito()!=null && deli.getCatDelito().getNombre()!=null? deli.getCatDelito().getNombre():"")
							+"</p>";
						
					}
					List<FaltaAdministrativaIph> faltasIP=new ArrayList<FaltaAdministrativaIph>(informe.getFaltaAdministrativaIphs());
					for (FaltaAdministrativaIph falta : faltasIP) {
						cuerpoIPH+=
							(falta.getCatFaltaAdministrativa()!=null && falta.getCatFaltaAdministrativa().getNombreFalta()!=null? falta.getCatFaltaAdministrativa().getNombreFalta():"")
							+"&nbsp;&nbsp;";
					}
					
					/*Datos de detencion*/
					if(invol.getDetencions().size()>0){
						Detencion detenc = invol.getDetencions().iterator().next();
						String strQRecibe=(detenc.getFuncionarioByFuncionarioTraslada()!=null)?detenc.getFuncionarioByFuncionarioDetiene().getNombreCompleto()+", "+detenc.getFuncionarioByFuncionarioDetiene().getPuesto().getValor():"NO REGISTRADO";
						cuerpoIPH+="<p><strong>DATOS DE LA DETENCION.</strong></p>"+
						"<p>Motivo de la detenci&oacute;n:&nbsp;"
							+(detenc.getMotivoDetencion()!=null?detenc.getMotivoDetencion():"")
							+"</p>"+
						"<p>Ubicaci&oacute;n del detenido:&nbsp;"
							+(detenc.getLugarDetencionProvisional()!=null? detenc.getMotivoDetencion():"")
							+"</p>"+
						"<p>Autoridad a la que fue puesta a disposici&oacute;n: ___________________</p>"+
						"<p>Nombre y cargo de quien lo recibi&oacute;:&nbsp;"+strQRecibe+"</p>"+
						"<p>Turno: &nbsp;_____________________&nbsp;&nbsp; N&uacute;mero de Documento: &nbsp;___________________&nbsp;&nbsp;</p>";
					}
				}
				
				
				/*OTROS DATOS*/
				String strEscolaridad=(invol.getEscolaridad()!=null)?invol.getEscolaridad().getValor():"NO REGISTRADA";
				cuerpoIPH+="<p><strong>OTROS DATOS.</strong></p>"+
				"<p>Ocupaci&oacute;n:&nbsp;_______________________&nbsp;&nbsp; Escolaridad: "+strEscolaridad+"&nbsp;</p>";
				if(invol.getMedioDeContactos().size()>0){
					List<MedioDeContacto> medios=new ArrayList<MedioDeContacto>(invol.getMedioDeContactos());
					String strTelCasa="SIN REGISTRO";
					String strTelMovil="SIN REGISTRO";
					for (MedioDeContacto med : medios) {
						if(med instanceof Telefono){
							strTelCasa=((Telefono) med).getValor().getValorId().equals(TipoTelefono.CASA.getValorId())?((Telefono) med).getNumeroTelefonico():strTelCasa;
							strTelMovil=((Telefono) med).getValor().getValorId().equals(TipoTelefono.CELULAR.getValorId())?((Telefono) med).getNumeroTelefonico():strTelMovil;
						}
					}
					cuerpoIPH+="<p>Tel&eacute;fono particular: "+strTelCasa+"&nbsp;Tel&eacute;fono Celular: "+strTelMovil+"</p>";
				}
//				cuerpoIPH+="<p>Observaciones:&nbsp;__________________________;&nbsp;&nbsp;</p>";
				
//				String strDicamen="NO";
//				String strFechaDictamen="____";
////				if(invol.getFechaDictamenMedico()!=null){
////					strDicamen="SI";
////					strFechaDictamen=formato.format(invol.getFechaDictamenMedico());
////				}
//				cuerpoIPH+="<p><strong>DICATAMEN M&Eacute;DICO.</strong></p>"+
//				"<p>Dictamen m&eacute;dico: "+strDicamen+"&nbsp; Fecha del dictamen:"+strFechaDictamen+"</p>";
				
				numPer++;
			}//Fin Por cada Involucrado
			
			/*SCRZ Cuando se empleaba InvolucradoIph*/
//			if(informe.getInvolucradoIphs().size()>0){
//				ArrayList<InvolucradoIph> invoIPH = new ArrayList<InvolucradoIph>(informe.getInvolucradoIphs());
//				int numPerInv=1;
//				for (InvolucradoIph invol : invoIPH) {
////					cuerpoIPH+="<p>&nbsp;&nbsp;&nbsp;<strong>Persona Involucrada "+numPerInv+"</strong></p>";
//					
//					if(invol.getNombreDemograficos().size()>0){
//						Domicilio domicilioInvol=obtenRelacionDomicilio(invol.getElementoId());
//						String strDomicilio="";
//						if(domicilioInvol!=null)
//							strDomicilio=domicilioInvol.getCalle()+" "+domicilioInvol.getNumeroExterior()+" "+domicilioInvol.getCiudad();
//						NombreDemografico demo = invol.getNombreDemograficos().iterator().next();
//						cuerpoIPH+="<p>Apellido Paterno:&nbsp;"+demo.getApellidoPaterno()+"&nbsp;&nbsp; Apellido Materno:&nbsp;"+demo.getApellidoMaterno()+"&nbsp;&nbsp; Nombre (s):&nbsp;"+demo.getNombre()+"&nbsp;&nbsp;</p>";
//						if(invol.getAliasInvolucrados().size()>0){
//							AliasInvolucrado alia = invol.getAliasInvolucrados().iterator().next();
//							cuerpoIPH+="<p>Alias:&nbsp;"+alia.getAlias()+"&nbsp;&nbsp;";
//						}
//						String strFechaNacim="NO REGISTRADA";
//						if(demo.getFechaNacimiento()!=null)
//							strFechaNacim=formato.format(demo.getFechaNacimiento());
//						cuerpoIPH+="País de origen: "+demo.getPaisNacimiento().getValor()+"&nbsp; Grupo &Eacute;tnico:"+invol.getGrupoEtnico().getValor()+"&nbsp;</p>"+
//						"<p>Fecha de Nacimiento:"+strFechaNacim+"&nbsp; Edad:"+demo.getEdadAproximada()+"&nbsp; RFC:"+demo.getRfc()+"&nbsp; Sexo:"+demo.getSexo().toUpperCase()+"</p>"+
//						"<p>Domicilio:"+strDomicilio+"&nbsp;</p>"+
//						"<p>En calidad de: "+invol.getCalidad().getTipoCalidad().getValor()+"&nbsp;&nbsp;</p>"+
//						"<p>Mayor de edad:&nbsp;"+invol.getGrupoEdad().getValor()+"&nbsp;&nbsp;</p>"+
//						"<p>Situaci&oacute;n:&nbsp;"+invol.getSituacionPolicialIndividuo().getValor()+"&nbsp;&nbsp;</p>"+
//						"<p>Estatus residencial: "+invol.getEstatusResidencial().getValor()+"&nbsp;</p>"+
//						"<p>Origen:&nbsp;______________________&nbsp;&nbsp; Destino:&nbsp;_________________________&nbsp;&nbsp;</p>";
//					}//Datos demográficos
//					
//					if(invol.getCalidad().getTipoCalidad().getValorId().equals(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId())){
//						/*DELITOS O FALTAS*/
//						cuerpoIPH+="<p><strong>PROBABLES DELITOS O FALTAS ADMINISTRATIVAS</strong></p><p>";
//						List<DelitoIph> delitosIP =new ArrayList<DelitoIph>(informe.getDelitoIphs());
//						for (DelitoIph deli : delitosIP) {
//							cuerpoIPH+=deli.getCatDelito().getNombre()+"&nbsp;&nbsp;";
//							
//						}
//						List<FaltaAdministrativaIph> faltasIP=new ArrayList<FaltaAdministrativaIph>(informe.getFaltaAdministrativaIphs());
//						for (FaltaAdministrativaIph falta : faltasIP) {
//							cuerpoIPH+=falta.getCatFaltaAdministrativa().getNombreFalta()+"&nbsp;&nbsp;";
//						}
//					}
//					
//					/*OTROS DATOS*/
//					cuerpoIPH+="</p><p><strong>OTROS DATOS.</strong></p>"+
//					"<p>Ocupaci&oacute;n:&nbsp;_______________________&nbsp;&nbsp; Escolaridad:&nbsp;________________________&nbsp;</p>";
//					if(invol.getMedioDeContactos().size()>0){
//						List<MedioDeContacto> medios=new ArrayList<MedioDeContacto>(invol.getMedioDeContactos());
//						String strTelCasa="SIN REGISTRO";
//						String strTelMovil="SIN REGISTRO";
//						for (MedioDeContacto med : medios) {
//							if(med instanceof Telefono){
//								strTelCasa=((Telefono) med).getValor().getValorId().equals(TipoTelefono.CASA.getValorId())?((Telefono) med).getNumeroTelefonico():strTelCasa;
//								strTelMovil=((Telefono) med).getValor().getValorId().equals(TipoTelefono.CELULAR.getValorId())?((Telefono) med).getNumeroTelefonico():strTelMovil;
//							}
//						}
//						cuerpoIPH+="<p>Tel&eacute;fono particular: "+strTelCasa+"&nbsp;Tel&eacute;fono Celular: "+strTelMovil+"</p>";
//					}
//					cuerpoIPH+="<p>Observaciones:&nbsp;__________________________;&nbsp;&nbsp;</p>";
//					
//					/*Datos de detencion*/
//					cuerpoIPH+="<p><strong>DATOS DE LA DETENCION.</strong></p>";
//					if(invol.getDetencions().size()>0){
//						Detencion detenc = invol.getDetencions().iterator().next();
//						String strQRecibe=(detenc.getFuncionarioByFuncionarioTraslada()!=null)?detenc.getFuncionarioByFuncionarioDetiene().getNombreCompleto()+", "+detenc.getFuncionarioByFuncionarioDetiene().getPuesto().getValor():"NO REGISTRADO";
//						cuerpoIPH+="<p>Motivo de la detenci&oacute;n:&nbsp;"+detenc.getMotivoDetencion()+"</p>"+
//						"<p>Ubicaci&oacute;n del detenido:&nbsp;"+detenc.getLugarDetencionProvisional()+"</p>"+
//						"<p>Autoridad a la que fue puesta a disposici&oacute;n: ___________________</p>"+
//						"<p>Nombre y cargo de quien lo recibi&oacute;:&nbsp;"+strQRecibe+"</p>"+
//						"<p>Turno: &nbsp;_____________________&nbsp;&nbsp; N&uacute;mero de Documento: &nbsp;___________________&nbsp;&nbsp;</p>";
//					}
//					
//					String strDicamen="NO";
//					String strFechaDictamen="";
//					if(invol.getFechaDictamenMedico()!=null){
//						strDicamen="SI";
//						strFechaDictamen=formato.format(invol.getFechaDictamenMedico());
//					}
//					cuerpoIPH+="<p><strong>DICATAMEN M&Eacute;DICO.</strong></p>"+
//					"<p>Dictamen m&eacute;dico: "+strDicamen+"&nbsp; Fecha del dictamen:"+strFechaDictamen+"</p>";
//					
//					numPerInv++;
//				}
//			}//Fin Involucrado
			
			/*MEDIOS DE TRANSPORTE INVOLUCRADO*/
			cuerpoIPH+="<p><strong>_______________________________________________________________________________________________________</strong></p>"+
			"<p><strong>MEDIOS DE TRASPORTE INVOLUCRADOS</strong></p>";
			if(informe.getMedioTransportes().size()>0){
				List<MedioTransporte> mediosTrasporteIPH=new ArrayList<MedioTransporte>(informe.getMedioTransportes());
				int medios=1;
				for (MedioTransporte medioTrasp : mediosTrasporteIPH) {
					cuerpoIPH+="<p>** Medio de Transporte "+medios+"**</p>";
					Objeto objMedioT = medioTrasp.getObjeto();
					String strSitMed=(objMedioT.getValorByCondicionFisicaVal()!=null)?objMedioT.getValorByCondicionFisicaVal().getValor():"DESCONOCIDA";
//					if(medioTrasp.getSituacionMedioTransporte()!=null)
//						strSitMed=medioTrasp.getSituacionMedioTransporte().getValor();
					cuerpoIPH+="<p>Tipo:&nbsp;"+objMedioT.getValorByTipoObjetoVal().getValor()+"&nbsp;&nbsp;</p>"+
					"<p>Condici&oacute;n: "+strSitMed+"&nbsp;</p>";// Recuperado: Con detenido&nbsp; Sin detenido</p>";
					if(objMedioT.getValorByTipoObjetoVal().getValorId().equals(Objetos.EMBARCACION.getValorId())){
						Embarcacion objEsp=embarcacionDAO.read(objMedioT.getElementoId());
						cuerpoIPH+="<p>Marca:"
							+ (objEsp.getMarca()!= null && objEsp.getMarca().getValor()!=null ? objEsp.getMarca().getValor() : "")
							+"&nbsp; Submarca:"
							+ (objEsp.getSubMarca()!=null && objEsp.getSubMarca().getValor()!=null ? objEsp.getSubMarca().getValor() : "")
							+"&nbsp; Modelo/A&ntilde;o:____</p>"+
							
						"<p>Placa/Matr&iacute;cula:"
							+(objEsp.getMatricula()!=null? objEsp.getMatricula(): "")
							+"&nbsp; Color:"
							+(objEsp.getColor()!=null && objEsp.getColor().getValor()!=null? objEsp.getColor().getValor() : "")
							+"&nbsp;N&uacute;mero de motor:"
							+(objEsp.getNoMotor()!=null ? objEsp.getNoMotor(): "")
							+"&nbsp; Serie:"
							+(objEsp.getNoSerie()!=null ? objEsp.getNoSerie(): "")
							+"</p>"+
						"<p>Registro/NIV:______&nbsp; Capacidad de pasajeros:________&nbsp; </p>"+
						"<p>Procedencia:"
							+(objEsp.getPaisOrigen()!=null && objEsp.getPaisOrigen().getValor()!=null? objEsp.getPaisOrigen().getValor(): "")
							+"&nbsp; Empresa:"
							+(medioTrasp.getNombreEmpresa()!=null? medioTrasp.getNombreEmpresa(): "")
							+"</p>";
//						"<p>Capacidad de carga/ Tipo de Carga:&nbsp;______&nbsp;&nbsp; Origen:&nbsp;"+medioTrasp.getOrigen()+"&nbsp;&nbsp; </p>"+
//						"<p>Destino:&nbsp;"+medioTrasp.getDestino()+"&nbsp;&nbsp; </p>";
						
					}
					if(objMedioT.getValorByTipoObjetoVal().getValorId().equals(Objetos.AERONAVE.getValorId())){
						Aeronave objEsp=aeronaveDAO.read(objMedioT.getElementoId());
						String strColor=(objEsp.getColor()!=null)?objEsp.getColor().getValor():"NO REGISTRADO";
						cuerpoIPH+="<p>Marca:"
							+(objEsp.getMarca()!=null &&objEsp.getMarca().getValor()!=null? objEsp.getMarca().getValor(): "")
							+"&nbsp; Submarca:"
							+(objEsp.getSubMarca()!=null &&objEsp.getSubMarca().getValor()!=null? objEsp.getSubMarca().getValor(): "")
							+"&nbsp; Modelo/A&ntilde;o:____</p>"+
							
						"<p>Placa/Matr&iacute;cula:"
							+(objEsp.getMatricula()!=null? objEsp.getMatricula():"")
							+"&nbsp; Color:"
							+strColor
							+"&nbsp;N&uacute;mero de motor:"
							+(objEsp.getNoMotor()!=null?objEsp.getNoMotor():"")
							+"&nbsp; Serie:"
							+(objEsp.getNoSerie()!=null?objEsp.getNoSerie():"")
							+"</p>"+
						"<p>Capacidad de pasajeros:________&nbsp; </p>"+
						"<p>Procedencia:"
							+(objEsp.getPaisProcedencia()!=null && objEsp.getPaisProcedencia().getValor()!=null? objEsp.getPaisProcedencia().getValor(): "")
							+"&nbsp;</p>";
//						"<p>Capacidad de carga/ Tipo de Carga:&nbsp;______&nbsp;&nbsp; Origen:&nbsp;"+medioTrasp.getOrigen()+"&nbsp;&nbsp; </p>"+
//						"<p>Destino:&nbsp;"+medioTrasp.getDestino()+"&nbsp;&nbsp; </p>";
						
					}
					if(objMedioT.getValorByTipoObjetoVal().getValorId().equals(Objetos.VEHICULO.getValorId())){
						Vehiculo objEsp=vehiculoDAO.read(objMedioT.getElementoId());
						String strBlindado="DESCONOCIDO";
						if(objEsp.getEsBlindado()!=null)
							strBlindado=(objEsp.getEsBlindado())?"SI":"NO";
						String strProp=(objEsp.getPropietario()!=null)?objEsp.getPropietario():"DESCONOCIDO";
						String strRelacion=(objEsp.getRelacionHechoVal()!=null)?objEsp.getRelacionHechoVal().getValor():"DESCONOCIDO";
						String strMarca=(objEsp.getValorByMarcaVal()!=null)?objEsp.getValorByMarcaVal().getValor():"DESCONOCIDA";
						String strSubMarca=(objEsp.getValorBySubmarcaVal()!=null)?objEsp.getValorBySubmarcaVal().getValor():"DESCONOCIDA";
						String strColor=(objEsp.getValorByColorVal()!=null)?objEsp.getValorByColorVal().getValor():"NO REGISTRADO";
						String strPuertas=(objEsp.getNoPuertas()!=null)?objEsp.getNoPuertas().toString():"NO REGISTRADO";
						String strTipoVehiculo=(objEsp.getValorByTipoVehiculo()!=null)?objEsp.getValorByTipoVehiculo().getValor():"NO REGISTRADO";
						cuerpoIPH+="<p>Tipo de Vehiculo:&nbsp;"+strTipoVehiculo+"&nbsp;&nbsp;</p>"+
						"<p>Marca:"+strMarca+"&nbsp; Submarca: "+strSubMarca+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Modelo/A&ntilde;o: "+objEsp.getModelo()+"</p>"+
						"<p>Placa/Matr&iacute;cula: "
							+(objEsp.getPlaca()!=null? objEsp.getPlaca(): "")
							+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Color: "+strColor+"&nbsp;</p>" +
						"<p>N&uacute;mero de motor: "
							+(objEsp.getNoMotor()!=null?objEsp.getNoMotor():"")
							+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Serie: "
							+(objEsp.getNoSerie()!=null?objEsp.getNoSerie():"")
							+"</p>"+
						"<p>Registro/NIV: "
							+(objEsp.getNrfv() !=null? objEsp.getNrfv():"")
							+"&nbsp;</p>"+// Capacidad de pasajeros:________&nbsp; </p>"+
						"<p>Procedencia: "
							+(objEsp.getValorByPaisOrigenVal()!=null &&objEsp.getValorByPaisOrigenVal().getValor()!=null? objEsp.getValorByPaisOrigenVal().getValor():"")
							+"&nbsp;</p>"+// Empresa:"+medioTrasp.getNombreEmpresa()+"</p>";
//						"<p>Capacidad de carga/ Tipo de Carga:____&nbsp; Origen:&nbsp;"+medioTrasp.getOrigen()+"&nbsp;&nbsp; </p>"+
//						"<p>Destino:&nbsp;"+medioTrasp.getDestino()+"&nbsp;&nbsp; </p>";
						"<p>Es Blindado: "+strBlindado+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Propietario: "+strProp+"&nbsp;</p>"+
						"<p>No. de Puertas: "+strPuertas+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No. de Cilindros: "
							+(objEsp.getNoCilindros()!=null? objEsp.getNoCilindros():"")
							+"</p>"+
						"<p>Relación del Hecho: "+strRelacion+"</p>";
						
					}
					String strDesc=(medioTrasp.getDescripcion()!=null)?medioTrasp.getDescripcion():"";
					cuerpoIPH+="<p>Observaciones:&nbsp;"+strDesc+"&nbsp;&nbsp;</p>";
					
					medios++;
				}
			}

			/*DROGAS*/
			if(informe.getObjetoAsegurados().size()>0){
				List<ObjetoAsegurado> asegurados=new ArrayList<ObjetoAsegurado>(informe.getObjetoAsegurados());
				for (ObjetoAsegurado asegurado : asegurados) {
					Objeto objAseg = asegurado.getObjeto();
					if(objAseg.getValorByTipoObjetoVal().getValorId().equals(Objetos.SUSTANCIA.getValorId())){
						Sustancia sust=sustanciaDAO.read(objAseg.getElementoId());
						String strDesc=(sust.getDescripcion()!=null)?sust.getDescripcion():"";
						String strEmpaque=(sust.getEmpaque()!=null)?sust.getEmpaque().getValor():" NO REGISTRADO";
						String strCondic=(sust.getValorByCondicionFisicaVal()!=null)?sust.getValorByCondicionFisicaVal().getValor():"DESCONOCIDA";
						cuerpoIPH+="<p><strong>DROGAS INVOLUCRADAS</strong></p>"+
						"<table><tr><td>Tipo: "
							+(sust.getTipoSustancia()!=null &&sust.getTipoSustancia().getValor()!=null?sust.getTipoSustancia().getValor():"")
							+"</td>"+
						"<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
						"<td>Unidad de medida: "
							+(sust.getUnidadMedida()!=null && sust.getUnidadMedida().getValor()!=null? sust.getUnidadMedida().getValor(): "")
							+"</td>"+
						"<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
						"<td>Cantidad: "
							+(sust.getCantidad()!=null?sust.getCantidad():"")
							+"</td></tr></table>"+
						"<p>Tipo Empaque: "+strEmpaque+"&nbsp; Condici&oacute;n: "+strCondic+"</p>"+
						"<p>Observaciones: "+strDesc+"</p>";
					}
				}
			}
			
			
			/*ARMAS*/
			cuerpoIPH+="<p><strong>_______________________________________________________________________________________________________</strong></p>"+
			"<p><strong>ARMAS INVOLUCRADAS</strong></p>";
			if(informe.getArmaExplosivoInvolucrados().size()>0){
				List<ArmaExplosivoInvolucrado> armasInvolucradas=new ArrayList<ArmaExplosivoInvolucrado>(informe.getArmaExplosivoInvolucrados());
				int numArma=1;
				for (ArmaExplosivoInvolucrado armaInv : armasInvolucradas) {
					Arma objArma = (Arma) armaInv.getObjeto();
					
					String strTipo=(objArma.getTipoArma()!=null)?objArma.getTipoArma().getValor():"NO REGISTRADO";
					cuerpoIPH+="<p> ** Arma "+numArma+" **</p>"+
					"<p>Tipo: "+strTipo+"</p>"; 
					/*Arma*/
					if(objArma.getValorByTipoObjetoVal().getValorId().equals(Objetos.ARMA.getValorId())){
						Arma armEsp = armaDAO.read(objArma.getElementoId());
						cuerpoIPH+="<table><tr><td>Marca:&nbsp;"
							+(armEsp.getMarca()!=null && armEsp.getMarca().getValor()!=null? armEsp.getMarca().getValor():"")
							+"</td>" +
						"<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
						"<td>Modelo:&nbsp;"
							+(armEsp.getModelo()!=null? armEsp.getModelo(): "")
							+"</td>" +
						"<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
						"<td>Calibre:&nbsp;"
							+(armEsp.getCalibre()!=null? armEsp.getCalibre(): "")
							+"</td></tr></table>"+
						"<p>Matr&iacute;cula:&nbsp;"
							+(armEsp.getMatricula()!=null? armEsp.getMatricula(): "")
							+"&nbsp;</p>"+// Serie:_________&nbsp; Inventario:&nbsp;_____&nbsp;</p>";
						"<p>Descripci&oacute;n del arma: "
							+(objArma.getDescripcion()!=null? objArma.getDescripcion(): "")
							+"</p>";
						
//						cuerpoIPH+="<p><strong>CARGADOR/CARTUCHOS.</strong></p>"+
//						"<p>No. de cartuchos/Municiones:____&nbsp; Cartuchos: Utiles:____&nbsp; Percutidos:_____&nbsp; No de cargadores:____</p>";
						numArma++;
					}
					
				}
			}

			/*OBJETOS ASEGURADOS*/
			cuerpoIPH+="<p><strong>_______________________________________________________________________________________________________</strong></p>"+
			"<p><strong>OBJETOS ASEGURADOS</strong></p>";
			
			if(informe.getObjetoAsegurados().size()>0){
				List<ObjetoAsegurado> asegurados=new ArrayList<ObjetoAsegurado>(informe.getObjetoAsegurados());
				for (ObjetoAsegurado asegurado : asegurados) {
					Objeto objAseg = asegurado.getObjeto();
					if(objAseg.getValorByTipoObjetoVal().getValorId().equals(Objetos.NUMERARIO.getValorId())){
						Numerario moneda=numerarioDAO.read(objAseg.getElementoId());
						String strDesc=(moneda.getDescripcion()!=null)?moneda.getDescripcion():"";
//						String strTipo=(moneda.getTipoMoneda()!=null)?moneda.getTipoMoneda().getValor():"NO REGISTRADO";
//						String strAut="SE DESCONOCE";
//						if(moneda.getEsAutentico()!=null)
//							strAut=(moneda.getEsAutentico())?"SÍ":"NO";
						ahora.setTime(moneda.getFechaDeposito()); 
						strMes=new SimpleDateFormat("MMMMMMMMMM",loc).format(moneda.getFechaDeposito());
						String strCond=(moneda.getValorByCondicionFisicaVal()!=null)?moneda.getValorByCondicionFisicaVal().getValor():"NO REGISTRADA";
						cuerpoIPH+="<p><strong>NUMERARIO.</strong></p>"+
//						"<p>Tipo:"+strTipo+"&nbsp; Cantidad:"+moneda.getCantidad()+"&nbsp; Es Aut&eacute;ntico:&nbsp; "+strAut+"</p>"+
						"<table><tr><td>Fecha del Dep&oacute;sito:&nbsp;"+ahora.get(Calendar.DAY_OF_MONTH)+" de "+strMes+" de "+ahora.get(Calendar.YEAR)+"</td>"+
						"<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"+
						"<td>Hora del Dep&oacute;sito:&nbsp;"+df.format(ahora.get(Calendar.HOUR_OF_DAY))+":"+df.format(ahora.get(Calendar.MINUTE))+"&nbsp;</td></tr>"+
						"<tr><td>Cantidad: "
							+(moneda.getCantidad()!=null? moneda.getCantidad(): "")
							+"</td>"+
						"<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"+
						"<td>Moneda: "
							+(moneda.getMoneda()!=null? moneda.getMoneda():"")
							+"</td></tr></table>"+
						"<p>Cuenta Tesorer&iacute;a: "
							+(moneda.getCtaTesoreria()!=null? moneda.getCtaTesoreria():"")
							+"</p>"+
						"<p>Condici&oacute;n: "+strCond+"</p>"+
						"<p>Observaciones: "+strDesc+"&nbsp;</p>";
					}
					
					if(objAseg.getValorByTipoObjetoVal().getValorId().equals(Objetos.EQUIPO_TACTICO.getValorId())){
						EquipoTactico objEsp = equipoTacticoDAO.read(objAseg.getElementoId());
						String strDesc=(objEsp.getDescripcion()!=null)?objEsp.getDescripcion():"";
						cuerpoIPH+="<p><strong>EQUIPO T&Aacute;CTICO.</strong></p>"+
						"<p><strong>Datos del equipo t&aacute;ctico.</strong></p>"+
						"<p>Tipo:"
							+(objEsp.getTipoEquipoTactico()!=null && objEsp.getTipoEquipoTactico().getValor()!=null? objEsp.getTipoEquipoTactico().getValor():"")
							+"</p>"+
						"<p>Marca:&nbsp;"
							+(objEsp.getMarca()!=null && objEsp.getMarca().getValor()!=null ? objEsp.getMarca().getValor(): "")
							+"&nbsp;Modelo:&nbsp;"
							+(objEsp.getModelo()!=null && objEsp.getModelo().getValor()!=null? objEsp.getModelo().getValor(): "")
							+"&nbsp;Inventario:&nbsp;"
							+(objEsp.getNinventario()!=null? objEsp.getNinventario() : "")
							+"&nbsp; </p>"+
						"<p>Serie:"
							+(objEsp.getNserie() !=null? objEsp.getNserie():"")
							+"&nbsp; Unidad de medida:&nbsp;"
							+(objEsp.getUnidadMedida()!=null && objEsp.getUnidadMedida().getValor()!=null? objEsp.getUnidadMedida().getValor(): "")
							+"&nbsp;Cantidad:&nbsp;"
							+(objEsp.getCantidad()!=null? objEsp.getCantidad(): "")
							+"&nbsp;</p>"+
						"<p>Observaciones:"+strDesc+"&nbsp;</p>";
					}
					
					if(entrega1){
						cuerpoIPH+="<p><strong>PIRATER&Iacute;A.</strong></p>"+
						"<p><strong>Datos de los objetos</strong></p>"+
						"<p>Tipo:&nbsp;&nbsp;Descripci&oacute;n:&nbsp;&nbsp; </p>"+
						"<p>Cantidad:&nbsp;&nbsp;Unidad de medida:&nbsp;&nbsp; </p>"+
						"<p>Observaciones:&nbsp;&nbsp; </p>";
					}
					
					if(objAseg.getValorByTipoObjetoVal().getValorId().equals(Objetos.DOCUMENTO_OFICIAL.getValorId())){
						DocumentoOficial docOf=documentoOficialDAO.read(objAseg.getElementoId());
						String strDesc=(docOf.getDescripcion()!=null)?docOf.getDescripcion():"";
						cuerpoIPH+="<p><strong>DOCUMENTOS.</strong></p>"+
						"<p><strong>Datos del Documento</strong></p>"+
						"<p>Tipo:"
							+(docOf.getTipoDocumento()!=null && docOf.getTipoDocumento().getValor()!=null? docOf.getTipoDocumento().getValor():"")
							+"&nbsp;Descripci&oacute;n: Aut&eacute;ntico&nbsp;&nbsp; Ap&oacute;crifo&nbsp;&nbsp; Cantidad:"
							+(docOf.getCantidad()!=null? docOf.getCantidad(): "")
							+"</p>"+
						"<p>Observaciones:"+strDesc+"</p>";
					}
					
					if(entrega1){
						cuerpoIPH+="<p><strong>PLANT&Iacute;OS.</strong></p>"+
						"<p><strong>Datos del plant&iacute;o</strong></p>"+
						"<p>Tipo: Marihuana&nbsp; Amapola&nbsp; Opio&nbsp; Mixto&nbsp; Otros&nbsp; No. de plant&iacute;os:&nbsp;&nbsp; Kilogramos:&nbsp;</p>"+
						"<p>M&eacute;todo de destrucci&oacute;n:&nbsp;&nbsp; Manual&nbsp;&nbsp; Fumigaci&oacute;n&nbsp; Incineraci&oacute;n</p>"+
						"<p><strong>Datos de la Planta</strong></p>"+
						"<p>Superficie:&nbsp;&nbsp; Altura:&nbsp;&nbsp; Densidad:&nbsp;&nbsp; Peso por planta:&nbsp;&nbsp; </p>"+
						"<p><strong>Coordenadas del Plant&iacute;o</strong></p>"+
						"<p>Latitud: Grados&nbsp;&nbsp; Minutos&nbsp;&nbsp; Segundos&nbsp;&nbsp; Direcci&oacute;n: Norte&nbsp;&nbsp;&nbsp; Sur</p>"+
						"<p>Longitud: Grados&nbsp;&nbsp; Minutos&nbsp;&nbsp; Segundos&nbsp;&nbsp;Direcci&oacute;n: Este&nbsp;&nbsp;&nbsp; Oeste</p>"+
						"<p>Base a&eacute;rea:</p>";
					}
					
					if(entrega1){
						cuerpoIPH+="<p><strong>PISTAS DE ATERRIZAJE.</strong></p>"+
						"<p>Tipo:&nbsp;&nbsp; Autorizada&nbsp;&nbsp; Clandestina&nbsp;&nbsp; Terracer&iacute;a&nbsp;&nbsp; Pavimentada</p>"+
						"<p>Descripci&oacute;n:&nbsp;&nbsp;</p>"+
						"<p>Localizaci&oacute;n 1:&nbsp;&nbsp;</p>"+
						"<p>Localizaci&oacute;n 2:&nbsp;&nbsp;</p>"+
						"<p><strong>Coordenadas de la Pista</strong></p>"+
						"<p>Latitud: Grados&nbsp;&nbsp; Minutos&nbsp;&nbsp; Segundos&nbsp;&nbsp; Direcci&oacute;n: Norte&nbsp;&nbsp; Sur</p>"+
						"<p>Longitud: Grados Minutos&nbsp;&nbsp; Segundos&nbsp;&nbsp; Direcci&oacute;n: Este&nbsp;&nbsp; Oeste</p>"+
						"<p>Largo:&nbsp; Ancho:</p>";
					}
					
					if(entrega1){
						cuerpoIPH+="<p><strong>EQUIPO DE COMUNICACIONES.</strong></p>"+
						"<p>N&uacute;mero:&nbsp;&nbsp; Tipo:&nbsp;&nbsp; Serie:&nbsp;</p>";
					}
					
					if(objAseg.getValorByTipoObjetoVal().getValorId().equals(Objetos.INMUEBLE.getValorId())){
						Inmueble inmueble = inmuebleDAO.read(objAseg.getElementoId());
						Domicilio domicilioInm=obtenRelacionDomicilio(inmueble.getElementoId());
						
						String strDesc=(inmueble.getDescripcion()!=null)?inmueble.getDescripcion():"";
						cuerpoIPH+="<p><strong>INMUEBLES.</strong></p>"+
						"<p><strong>Datos del inmueble</strong></p>"+
						"<p>Zona:&nbsp;Urbana &nbsp;Rural</p>"+
						"<p>Tipo:&nbsp"
							+(inmueble.getTipoInmueble() !=null && inmueble.getTipoInmueble().getValor()!=null? inmueble.getTipoInmueble().getValor():"")
							+";&nbsp;</p>"+
						"<p> Otro Especifique:____</p>";
						if(domicilioInm!=null){
							String strLocalidad=(domicilioInm.getAsentamiento()!=null)?domicilioInm.getAsentamiento().getNombreAsentamiento():"____________";
							cuerpoIPH+="<p>Calle:"
								+(domicilioInm.getCalle()!=null?domicilioInm.getCalle():"")
								+" N. Ext:"
								+(domicilioInm.getNumeroExterior()!=null? domicilioInm.getNumeroExterior():"")
								+" N. Int:"
								+(domicilioInm.getNumeroInterior()!=null? domicilioInm.getNumeroInterior():"")
								+"&nbsp; C.P.:"
								+(domicilioInm.getAsentamiento()!=null&&domicilioInm.getAsentamiento().getCodigoPostal()!=null? domicilioInm.getAsentamiento().getCodigoPostal():"")
								+"</p>"+
							"<p>Colonia:&nbsp;______________&nbsp; Estado:&nbsp;"
								+(domicilioInm.getEntidadFederativa()!=null && domicilioInm.getEntidadFederativa().getNombre()!=null? domicilioInm.getEntidadFederativa().getNombre():"")
								+"&nbsp; Municipio:/Delegacion:"+strLocalidad+"</p>"+
							"<p>Registro P&uacute;blico de la Propiedad:__________</p>"+
							"<p>Calles colindantes:"
								+(domicilioInm.getEntreCalle1()!=null? domicilioInm.getEntreCalle1(): "")
								+" y "
								+(domicilioInm.getEntreCalle2()!=null? domicilioInm.getEntreCalle2(): "")
								+"</p>"+
							"<p>Propietario:__________</p>"+
							"<p>Observaciones:"+strDesc+"</p>";
						}
					}
					
					if(objAseg.getValorByTipoObjetoVal().getValorId().equals(Objetos.EXPLOSIVO.getValorId())){
						Explosivo explos=explosivoDAO.read(objAseg.getElementoId());
						String strDesc=(explos.getDescripcion()!=null)?explos.getDescripcion():"";
						String strTipoExp=(explos.getTipoExplosivo()!=null)?explos.getTipoExplosivo().getValor():"NO REGISTRADO";
						String strMedida=(explos.getUnidadMedida()!=null)?explos.getUnidadMedida().getValor():"NO REGISTRADA";
						String strCond=(explos.getValorByCondicionFisicaVal()!=null)?explos.getValorByCondicionFisicaVal().getValor():"DESCONOCIDA";
						cuerpoIPH+="<p><strong>ARTEFACTOS Y EXPLOSIVOS.</strong></p>"+
						"<p><strong>Datos principales</strong></p>"+
						"<p>Clasificaci&oacute;n: Artefacto&nbsp; Explosivo</p>"+
//						"<p>Contenido del artefacto:&nbsp;_____&nbsp; </p>"+
						"<p>Tipo: "+strTipoExp+"</p>"+
//						"<p>Contenido del explosivo:&nbsp;_______</p>"+
						"<table><tr><td>Cantidad: "
							+(explos.getCantidad()!=null?explos.getCantidad(): "")
							+"</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td>Unidad de Medida: "+strMedida+"</td></tr></table>"+//" Lugar de hallazgo:&nbsp;_____</p>"+
//						"<p>Origen:&nbsp;______&nbsp;</p>"+
						"<p>Condici&oacute;n: "+strCond+"</p>"+
						"<p>Observaciones: "+strDesc+" </p>";
					}
					
				}//Fin Ciclo Objetos Asegurados
			}//Fin objetos asegurados
			
			if(entrega1){
				/*INFO COMPLEMENTARIA*/
				cuerpoIPH+="<p><strong>_______________________________________________________________________________________________________</strong></p>"+
				"<p><strong>INFORMACI&Oacute;N COMPLEMENTARIA DEL EVENTO</strong></p>";
				if(informe.getMultimedias().size()>0){
					List<Multimedia> multimedias=new ArrayList<Multimedia>(informe.getMultimedias());
					for (Multimedia multim : multimedias) {
						String strDesc=(multim.getDescripcion()!=null)?multim.getDescripcion():"";
						cuerpoIPH+="<p><strong>MULTIMEDIA.</strong></p>"+
						"<p>Video&nbsp;&nbsp; Imagen &nbsp; &nbsp;Audio Otros</p>"+
						"<p>Descripci&oacute;n:&nbsp;"+strDesc+"&nbsp;</p>";
						
					}
				}//Fin Multimedias
				
				cuerpoIPH+="<p><strong>OTROS.</strong></p>"+
				"<p>Tipo:&nbsp;&nbsp; Unidad de medida:&nbsp;&nbsp; Cantidad:</p>"+
				"<p>Descripci&oacute;n:&nbsp;&nbsp;</p>";
			}
			
			/*FINAL*/
			String strObs=(informe.getObjetivosGenerales()!=null)?informe.getObjetivosGenerales():"";
			cuerpoIPH+="<p><strong>_______________________________________________________________________________________________________</strong></p>"+
			"<p><strong>OBSERVACIONES GENERALES DEL INFORME</strong></p>"+
			"<p>"+strObs+"</p>"+
			"<p align=\"center\">Nombre y firma del oficial</p>";
			if(informe.getFuncionarioDestinatario()!= null)
				cuerpoIPH+="<p align=\"center\">"
					+(informe.getFuncionarioDestinatario()!=null && informe.getFuncionarioDestinatario().getNombreCompleto()!=null? informe.getFuncionarioDestinatario().getNombreCompleto():"")
					+"</p>";

			
			
			
		}//Si existe informe
		
		return cuerpoIPH;
	}


private Domicilio obtenRelacionDomicilio(Long elementoId) {
	List<Relacion> relaciones=relacionDAO.consultarRelacionByElemento(elementoId);
	for (Relacion relacion : relaciones) {
			Elemento elemento = relacion.getElementoByComplementoId();
			if(elemento.getTipoElemento().getValorId().equals(TipoElemento.LUGAR.getValorId()))
				if(elemento instanceof Domicilio)
				return (Domicilio) elemento;
	}
	return null;
}


/**
 * Utiliza las librerías de XHTML y iText para generar
 * un reporte en PDF a partir de un archivo XHTML
 * @param xml
 * @return
 */
@SuppressWarnings("deprecation")
protected ByteArrayOutputStream generarReportePDFDeHTML(String xHTML){
	
	ByteArrayOutputStream archivoPDF = null;
	try{
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    Document doc = builder.parse(new StringBufferInputStream(
	    		HTMLUtils.encodeHtmlToXhtml(BODY_TAG_APERTURA 
        				+ xHTML +
        				BODY_TAG_CIERRE)));

	    ITextRenderer renderer = new ITextRenderer();
	    renderer.setDocument(doc, null);
	    
        archivoPDF = new ByteArrayOutputStream();
        
	    renderer.layout();
	    
	    renderer.createPDF(archivoPDF);
	}catch (Exception e) {
		log.error(e);
	}
	
    
    return archivoPDF;
	
}


private Long guardadoDirecto(DocumentoDTO documento, InformePolicialHomologado informe) throws NSJPNegocioException {
    Double version = 1.0;
    
	ArchivoDigitalDTO archivo = null;
	
	ByteArrayOutputStream archivoPDF = null;
	
	archivoPDF = generarReportePDFDeHTML(documento.getTextoParcial());
    //Crear el archivo digital
    archivo = new ArchivoDigitalDTO();
    archivo.setContenido(archivoPDF.toByteArray());
    archivo.setNombreArchivo("Reporte IPH "+informe.getFolioIPH());
    archivo.setTipoArchivo(ConstantesGenerales.EXTENSION_PDF);
    documento.setArchivoDigital(archivo);
    documento.setFechaCreacion(new Date());
    FormaDTO forma=FormaTransformer.transformarForma(formaDAO.consultarFormaPorNombre("Blanco"));
	documento.setFormaDTO(forma);//Plantilla en blanco
    documento.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.INFORME.getValorId()));
    documento.setNombreDocumento("Reporte IPH "+informe.getFolioIPH());
    documento.setVersion(version);
//    documento.setTextoParcial(null);
	return guardarDocumentoService.guardarDocumento(documento,
			new ExpedienteDTO(informe.getExpediente().getExpedienteId()));
}
}
