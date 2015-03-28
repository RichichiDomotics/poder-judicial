package mx.gob.segob.nsjp.service.documento.impl.tranform;

import static mx.gob.segob.nsjp.comun.enums.calidad.Calidades.DENUNCIANTE;
import static mx.gob.segob.nsjp.comun.enums.calidad.Calidades.PROBABLE_RESPONSABLE_ORGANIZACION;
import static mx.gob.segob.nsjp.comun.enums.calidad.Calidades.PROBABLE_RESPONSABLE_PERSONA;
import static mx.gob.segob.nsjp.comun.enums.calidad.Calidades.QUIEN_DETUVO;
import static mx.gob.segob.nsjp.comun.enums.calidad.Calidades.TESTIGO;
import static mx.gob.segob.nsjp.comun.enums.calidad.Calidades.TRADUCTOR;
import static mx.gob.segob.nsjp.comun.enums.calidad.Calidades.DENUNCIANTE_ORGANIZACION;
import static mx.gob.segob.nsjp.comun.enums.calidad.Calidades.VICTIMA_PERSONA;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.AERONAVE;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.ANIMAL;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.ARMA;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.DOCUMENTO_OFICIAL;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.EMBARCACION;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.EQUIPO_DE_COMPUTO;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.EQUIPO_TELEFONICO;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.EXPLOSIVO;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.INMUEBLE;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.JOYA;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.NUMERARIO;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.OBRA_DE_ARTE;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.OTRO;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.SUSTANCIA;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.VEGETAL;
import static mx.gob.segob.nsjp.comun.enums.objeto.Objetos.VEHICULO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.comun.Meses;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.documento.GrupoObjetosExpedienteDTO;
import mx.gob.segob.nsjp.dto.documento.ObjetoResumenDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.ParametrosDocumentoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.AeronaveDTO;
import mx.gob.segob.nsjp.dto.objeto.AnimalDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;
import mx.gob.segob.nsjp.dto.objeto.DocumentoOficialDTO;
import mx.gob.segob.nsjp.dto.objeto.EmbarcacionDTO;
import mx.gob.segob.nsjp.dto.objeto.EquipoComputoDTO;
import mx.gob.segob.nsjp.dto.objeto.ExplosivoDTO;
import mx.gob.segob.nsjp.dto.objeto.JoyaDTO;
import mx.gob.segob.nsjp.dto.objeto.NumerarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObraArteDTO;
import mx.gob.segob.nsjp.dto.objeto.SustanciaDTO;
import mx.gob.segob.nsjp.dto.objeto.TelefoniaDTO;
import mx.gob.segob.nsjp.dto.objeto.VegetalDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;

public class ParametrosDocumentoTransformer {

	public static ParametrosDocumentoDTO cargarParametrosDocumentoFrom(ExpedienteDTO expediente){
		if(expediente == null){
			return null;
		}
		
		ParametrosDocumentoDTO parametrosDocumento = new ParametrosDocumentoDTO();
		Date fechaActual = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);
		parametrosDocumento.setExpedienteId(expediente.getExpedienteId());
		parametrosDocumento.setNumeroExpediente(expediente.getNumeroExpediente());
		parametrosDocumento.setFechaApertura(expediente.getFechaApertura());
		parametrosDocumento.setFechaCierre(expediente.getFechaCierre());
		parametrosDocumento.setNarrativa(expediente.getHechoDTO()!=null?expediente.getHechoDTO().getDescNarrativa():null);
		parametrosDocumento.setStrFechaApertura(expediente.getStrFechaApertura());
		parametrosDocumento.setStrHoraApertura(expediente.getStrHoraApertura());
		parametrosDocumento.setStrFechaCierre(expediente.getStrFechaCierre());
		parametrosDocumento.setStrHoraCierre(expediente.getStrHoraCierre());
		parametrosDocumento.setDelitoPrincipal(expediente.getDelitoPrincipal());
		parametrosDocumento.setDelitos(expediente.getDelitos());
		
		parametrosDocumento.setDenunciantes(expediente.getInvolucradoByCalidad(DENUNCIANTE));
		parametrosDocumento.setVictimasPersona(expediente.getInvolucradoByCalidad(VICTIMA_PERSONA));
		parametrosDocumento.setVictimasOrganizaciones(expediente.getInvolucradoByCalidad(DENUNCIANTE_ORGANIZACION));
		parametrosDocumento.setProbablesResponsablesPersona(expediente.getInvolucradoByCalidad(PROBABLE_RESPONSABLE_PERSONA));
		parametrosDocumento.setProbablesResponsablesOrganizacion(expediente.getInvolucradoByCalidad(PROBABLE_RESPONSABLE_ORGANIZACION));
		parametrosDocumento.setTestigos(expediente.getInvolucradoByCalidad(TESTIGO));
		parametrosDocumento.setTraductores(expediente.getInvolucradoByCalidad(TRADUCTOR));
		parametrosDocumento.setQuienDetuvo(expediente.getInvolucradoByCalidad(QUIEN_DETUVO));
		
		parametrosDocumento.setStrFechaActual(DateUtils.formatear(fechaActual));
		parametrosDocumento.setStrHoraActual(DateUtils.formatearHora(fechaActual));
		parametrosDocumento.setDiaActual(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		parametrosDocumento.setNombreMesActual(Meses.getNombre(cal.get(Calendar.MONTH)));
		parametrosDocumento.setAnioActual(String.valueOf(cal.get(Calendar.YEAR)));
		
		//recuperar las organizaciones denunciantes
		//aquellas donde las organizaciones víctimas tienen un representante legal  con condición diferente de 0
		parametrosDocumento.setDenunciantesOrganizacion(new ArrayList<InvolucradoDTO>());
		for(InvolucradoDTO victima:expediente.getInvolucradoByCalidad(DENUNCIANTE_ORGANIZACION)){
			if(victima.getCondicion() != null && victima.getCondicion().intValue() != 0){
				//agregar a los denunciantes
				parametrosDocumento.getDenunciantesOrganizacion().add(victima);
			}
		}
		
		//llenar objetos
		parametrosDocumento.setGrupoObjetosExpediente(new ArrayList<GrupoObjetosExpedienteDTO>());
		llenarGruposDeObjetos(parametrosDocumento,expediente);
			
		return parametrosDocumento;
	}
	
	public static ParametrosDocumentoDTO cargarParametrosDocumentoFrom(AudienciaDTO audiencia){
		ParametrosDocumentoDTO parametrosDocumento = null; 
		parametrosDocumento = cargarParametrosDocumentoFrom(audiencia.getExpediente());
		parametrosDocumento.setAudiencia(audiencia);
		return parametrosDocumento;
	}
	
	
	/**
	 * Llena los grupos de objetos por cada tipo de objeto que exista en el expediente
	 * @param resumen DTO destino
	 * @param expediente Expediente con la información de sus objetos
	 */
	private static void llenarGruposDeObjetos(ParametrosDocumentoDTO resumen,
			ExpedienteDTO expediente) {

		Objetos[] listado = new Objetos[]{VEHICULO, EQUIPO_DE_COMPUTO, EQUIPO_TELEFONICO,
										  ARMA, EXPLOSIVO, AERONAVE, ANIMAL, 
										  DOCUMENTO_OFICIAL,EMBARCACION,INMUEBLE,
										  JOYA,NUMERARIO,OBRA_DE_ARTE,SUSTANCIA,
										  VEGETAL,OTRO};
		
		GrupoObjetosExpedienteDTO grupo = null;
		
		for(Objetos tipoActual:listado){
			grupo = new GrupoObjetosExpedienteDTO();
			grupo.setTipoObjeto(tipoActual);
			grupo.setDescripcionGrupo(tipoActual.getNombreEntity());
			grupo.setObjetos(obtenerResumenObjetos(tipoActual,expediente));
			resumen.getGrupoObjetosExpediente().add(grupo);
			
		}		
	}

	/**
	 * Llena una lista de resumen de objetos de cierto tipo
	 * @param tipo Tipo de objeto a consultar
	 * @param expediente Expediente con los datos
	 * @return
	 */
	private static List<ObjetoResumenDTO> obtenerResumenObjetos(Objetos tipo,
			ExpedienteDTO expediente) {
		List<ObjetoDTO> objetos = expediente.getObjetosByTipo(tipo);
		List<ObjetoResumenDTO> objetosResumen = new ArrayList<ObjetoResumenDTO>();
		ObjetoResumenDTO resumenActual = null;
		for(ObjetoDTO objeto:objetos){
			resumenActual = new ObjetoResumenDTO();
			resumenActual.setElementoId(objeto.getElementoId());
			resumenActual.setCalidadDTO(objeto.getCalidadDTO());
			resumenActual.setFechaCreacionElemento(objeto.getFechaCreacionElemento());
			
			if(tipo.equals(VEHICULO)){
			    VehiculoDTO temp = (VehiculoDTO)objeto;
				resumenActual.setDescripcionResumen(
						temp.getValorByTipoVehiculo()!=null?temp.getValorByTipoVehiculo().getValor():""+ " "+
						temp.getPlaca()
						);
			}
			if(tipo.equals(EQUIPO_DE_COMPUTO)){
			    EquipoComputoDTO temp = (EquipoComputoDTO)objeto;
				resumenActual.setDescripcionResumen(
						temp.getTipoEquipo()!=null?temp.getTipoEquipo().getValor():""
						);
			}
			if(tipo.equals(EQUIPO_TELEFONICO)){
				resumenActual.setDescripcionResumen(
						((TelefoniaDTO)objeto).getModelo()
						);
			}
			if(tipo.equals(ARMA)){
			    ArmaDTO temp = (ArmaDTO)objeto;
				resumenActual.setDescripcionResumen(temp.getTipoArma()!=null?temp.getTipoArma().getValor():""
						);
			}
			if(tipo.equals(EXPLOSIVO)){
			    ExplosivoDTO temp = (ExplosivoDTO)objeto;
				resumenActual.setDescripcionResumen(
				        temp.getTipoExplosivo()!=null?temp.getTipoExplosivo().getValor():""
						);
			}
			if(tipo.equals(AERONAVE)){
			    AeronaveDTO temp = (AeronaveDTO)objeto;
				resumenActual.setDescripcionResumen(
				        temp.getTipoAeroNave()!=null?temp.getTipoAeroNave().getValor():""
						);
			}
			if(tipo.equals(ANIMAL)){
			    AnimalDTO temp = (AnimalDTO)objeto;
				resumenActual.setDescripcionResumen(
				        temp.getTipoAnimal()!=null?temp.getTipoAnimal().getValor():""
						);
			}
			if(tipo.equals(DOCUMENTO_OFICIAL)){
			    DocumentoOficialDTO temp = (DocumentoOficialDTO)objeto;
				resumenActual.setDescripcionResumen(
				        temp.getTipoDocumento()!=null?temp.getTipoDocumento().getValor():""
						);
			}
			if(tipo.equals(EMBARCACION)){
			    EmbarcacionDTO temp = (EmbarcacionDTO)objeto;
				resumenActual.setDescripcionResumen(
				        temp.getTipoEmbarcacion()!=null?temp.getTipoEmbarcacion().getValor():""
						);
			}
			/*if(tipo.equals(INMUEBLE)){
				resumenActual.setDescripcionResumen(
						((Innm)objeto).getTipoEmbarcacion().getValor()
						);
			}*/
			if(tipo.equals(JOYA)){
			    JoyaDTO temp = (JoyaDTO)objeto;
				resumenActual.setDescripcionResumen(
				        temp.getTipoJoya()!=null?temp.getTipoJoya().getValor():""
						);
			}
			if(tipo.equals(NUMERARIO)){
				resumenActual.setDescripcionResumen(
						((NumerarioDTO)objeto).getMoneda()
						);
			}
			if(tipo.equals(OBRA_DE_ARTE)){
			    ObraArteDTO temp = (ObraArteDTO)objeto;
				resumenActual.setDescripcionResumen(
				        temp.getTipoObraArte()!=null?temp.getTipoObraArte().getValor():""
						);
			}
			if(tipo.equals(SUSTANCIA)){
			    SustanciaDTO temp = (SustanciaDTO)objeto;
				resumenActual.setDescripcionResumen(
				        temp.getTipoSustancia()!=null?temp.getTipoSustancia().getValor():""
						);
			}
			if(tipo.equals(VEGETAL)){
			    VegetalDTO temp = (VegetalDTO)objeto;
				resumenActual.setDescripcionResumen(
				        temp.getTipoVegetal()!=null?temp.getTipoVegetal().getValor():""
						);
			}
			if(tipo.equals(OTRO)){
				resumenActual.setDescripcionResumen(
						((ObjetoDTO)objeto).getDescripcion()
						);
			}
			
			objetosResumen.add(resumenActual);
		}
		return objetosResumen;
	}

	
	
}
