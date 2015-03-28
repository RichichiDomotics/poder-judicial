/**
* Nombre del Programa : ReinsercionSocialAction.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/01/2012
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.web.reinsercionsocial.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.centrosdetencion.TipoCentroDetencion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.detencion.CentroDetencionDelegate;
import mx.gob.segob.nsjp.delegate.proceso.ProcesoDelegate;
import mx.gob.segob.nsjp.delegate.programa.ProgramaDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.dto.niveles.CatTipoNivelAcademicoDTO;
import mx.gob.segob.nsjp.dto.proceso.ProcesoDTO;
import mx.gob.segob.nsjp.dto.proceso.SubprocesoDTO;
import mx.gob.segob.nsjp.dto.programas.CatCategoriaCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoTrabajoExternoDTO;
import mx.gob.segob.nsjp.dto.programas.CatTrabajoDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.reinsercionsocial.form.ReinsercionSocialForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
@SuppressWarnings("unused")
public class ReinsercionSocialAction extends GenericAction{
	
	/* Log de clase*/
	private static final Logger LOG  = Logger.getLogger(ReinsercionSocialAction.class);
	
	@Autowired
	public ProcesoDelegate procesoDelegate;
	
	@Autowired
	public CentroDetencionDelegate centroDetencionDelegate;	

	@Autowired
	public ProgramaDelegate programaDelegate;	
		
	
	private final static String RESUMEN = "resumenRS";
	private final static String OTRO = "otro";
	private final static String CONSULTARS = "consultaRS";
	private final static String INITADMINPROGRAMAS = "init.admin.programas.do";
	private final static String ADMINPROGRAMAS = "admin.programas.page";
	private final static String REGPROGRAMASTAB = "apTabs-1";
	private final static String REGCURSOSTAB = "apTabs-2";
	private final static String REGTRABAJOSTAB = "apTabs-3";
	private final static String ACTUALIZAR = "Actualizado";
	private final static String ELIMINAR = "Eliminado";
	
	private final static String PROGASOCIADOS = "ProgError";
	private final static String CURTRABASOCIADOS = "CurTrabError";
	
	private final static String MOSTRAR = "MOSTRAR";
	private final static String OCUALTAR = "OCUALTAR";
		
	private final static String EXTERNO = "Externo";
	private final static String INTERNO = "Interno";
		
	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward inicioRS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			
			ReinsercionSocialForm reinsercionSocialForm = (ReinsercionSocialForm)form;
			
			reinsercionSocialForm = llenarListas(reinsercionSocialForm);
			//reinsercionSocialForm.setOcultarBuscar(MOSTRAR);
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return mapping.findForward(RESUMEN);
	}
	
	public ActionForward consultarExpedientesRS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {

			ReinsercionSocialForm reinsercionSocialForm = (ReinsercionSocialForm)form;
			UsuarioDTO usuarioFirmado = getUsuarioFirmado(request);
			RolDTO rolDTO = usuarioFirmado.getRolPrincipal().getRol();

			List<ProcesoDTO> procesosDTO = procesoDelegate.consultarProcesosPorRol(rolDTO);
			
			for (ProcesoDTO procesoDTO : procesosDTO) {
				List<SubprocesoDTO> subprocesosDTO = procesoDelegate.consultarSubprocesosPorProceso(procesoDTO);
				procesoDTO.setLstSubprocesos(subprocesosDTO);
			}

//			reinsercionSocialForm.setProcesosDTO(procesosDTO);

//			reinsercionSocialForm.setOcultarBuscar(OCUALTAR);
			
			reinsercionSocialForm = llenarListas(reinsercionSocialForm);
			
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return mapping.findForward(CONSULTARS);
	}
	

	public ActionForward consultarContenidoMenuRS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {

			String customForward = request.getParameter("customForward");

			if(customForward!= null && !customForward.isEmpty()){
				return mapping.findForward(customForward);
			}else {
				return mapping.findForward(OTRO);
			}
			
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return mapping.findForward(OTRO);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ReinsercionSocialForm llenarListas(ReinsercionSocialForm form){
		List<CentroDetencionDTO> ceresosDTO = null;
		try {
			ceresosDTO = centroDetencionDelegate.consultarCentrosDetencionPorTipo(TipoCentroDetencion.CERESO.getId());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
		ArrayList lstNiveles = new ArrayList();
		ArrayList lstDesempenios = new ArrayList();
		ArrayList lstCalificacionDesempenio = new ArrayList();
		ArrayList lstTrabajoInterno = new ArrayList();
		ArrayList lstAprobacion = new ArrayList();
		ArrayList lstMotivosTMD = new ArrayList();
		ArrayList lstTipoAnomaliasRAE = new ArrayList();
		ArrayList lstMotivosEPS = new ArrayList();
		ArrayList lstMotivosTS = new ArrayList();
		ArrayList lstMotivosFP = new ArrayList();
		ArrayList lstJuzgados = new ArrayList();
		ArrayList lstJuez = new ArrayList();
		ArrayList lstCausasBPL = new ArrayList();
		
			
		lstNiveles.add(new LabelValueBean("Primaria","1"));
		lstNiveles.add(new LabelValueBean("Secundaria","2"));
		lstNiveles.add(new LabelValueBean("Preparatoria","3"));
		
		lstDesempenios.add(new LabelValueBean("Trabajo","1"));
		lstDesempenios.add(new LabelValueBean("Capacitación","2"));
		lstDesempenios.add(new LabelValueBean("Programa","3"));
		
		lstCalificacionDesempenio.add(new LabelValueBean("Excelente","1"));
		lstCalificacionDesempenio.add(new LabelValueBean("Muy Bueno","2"));
		lstCalificacionDesempenio.add(new LabelValueBean("Bueno","3"));
		lstCalificacionDesempenio.add(new LabelValueBean("Regular","4"));
		lstCalificacionDesempenio.add(new LabelValueBean("Malo","5"));
		
		lstTrabajoInterno.add(new LabelValueBean("Biblioteca","1"));
		lstTrabajoInterno.add(new LabelValueBean("Baños","2"));
		lstTrabajoInterno.add(new LabelValueBean("Cafeteria","3"));
		lstTrabajoInterno.add(new LabelValueBean("Cocina","4"));
		lstTrabajoInterno.add(new LabelValueBean("Patios","5"));
		
		
		lstAprobacion.add(new LabelValueBean("Aceptar","1"));
		lstAprobacion.add(new LabelValueBean("Recazar","2")); 
		
		lstMotivosTMD.add(new LabelValueBean("Mala conducta","1"));
		lstMotivosTMD.add(new LabelValueBean("No sigue las reglas","2"));
		lstMotivosTMD.add(new LabelValueBean("Amenaza a compañeros","3"));
		lstMotivosTMD.add(new LabelValueBean("Otro","4"));
		
		lstTipoAnomaliasRAE.add(new LabelValueBean("Reportes Tendenciosos","1"));
		lstTipoAnomaliasRAE.add(new LabelValueBean("Problema Puntual","1"));
		lstTipoAnomaliasRAE.add(new LabelValueBean("Otro","1"));

		lstMotivosEPS.add(new LabelValueBean("Enfermedad del sentenciado","1"));
		lstMotivosEPS.add(new LabelValueBean("Deceso","2"));

		lstMotivosTS.add(new LabelValueBean("Solicitud de cercanía de residencia","1"));
		lstMotivosTS.add(new LabelValueBean("Ajustes de nivel del centro de detención","2"));
		lstMotivosTS.add(new LabelValueBean("Audiencia programada","3"));
		lstMotivosTS.add(new LabelValueBean("Administración de los centros","4"));
		
		lstMotivosFP.add(new LabelValueBean("Cumplimiento de pena","1"));
		lstMotivosFP.add(new LabelValueBean("Remision de pena","2"));
		
		lstJuzgados.add(new LabelValueBean("Juzgado 1","1"));
		lstJuzgados.add(new LabelValueBean("Juzgado 2","2"));
		lstJuzgados.add(new LabelValueBean("Juzgado 3","3"));
		
		lstJuez.add(new LabelValueBean("Juez 1","1"));
		lstJuez.add(new LabelValueBean("Juez 2","2"));
		lstJuez.add(new LabelValueBean("Juez 3","3"));
		
		lstCausasBPL.add(new LabelValueBean("Decisión defensor","1"));
		lstCausasBPL.add(new LabelValueBean("2/3 partes de su pena","2"));
		
		form.setCeresosDTO(ceresosDTO);
		form.setLstCalificacionDesempenio(lstCalificacionDesempenio);
		form.setLstDesempenios(lstDesempenios);
		form.setLstNiveles(lstNiveles);
		form.setLstTrabajoInterno(lstTrabajoInterno);
		form.setLstAprobacion(lstAprobacion);
		form.setLstMotivosTMD(lstMotivosTMD);
		form.setLstTipoAnomaliasRAE(lstTipoAnomaliasRAE);
		form.setLstMotivosEPS(lstMotivosEPS);
		form.setLstMotivosTS(lstMotivosTS);
		form.setLstMotivosFP(lstMotivosFP);
		form.setLstTribunales(lstJuzgados);
		form.setLstJuez(lstJuez);
		form.setLstCausasBPL(lstCausasBPL);

		return form;
	}
	
	public ActionForward administrarProgramas(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			ReinsercionSocialForm reinsercionSocialForm = (ReinsercionSocialForm)form;
			
			reinsercionSocialForm.reset(mapping, request);
			
			CatProgramaDTO catProgramaDTO = new CatProgramaDTO();
			CatTipoProgramaDTO catTipoProgramaDTO = new CatTipoProgramaDTO();
			
			catProgramaDTO.setCatTipoProgramaDTO(catTipoProgramaDTO);
			catProgramaDTO.setLstCatCursoDTO(new ArrayList<CatCursoDTO>());
			catProgramaDTO.setLstCatTrabajoDTO(new ArrayList<CatTrabajoDTO>());
			catProgramaDTO.setLstCentroDetencionesDTO(new ArrayList<CentroDetencionDTO>());
//			reinsercionSocialForm.setCatProgramaDTO(catProgramaDTO);
			
			final PaginacionDTO pag = PaginacionThreadHolder.get();

			//Caso particular para considerar el total de registros
			pag.setTotalRegistros(0L);
			pag.setCampoOrd("");
			
			reinsercionSocialForm.setLstCatTipoProgramaDTO(programaDelegate.consultarCatTipoPrograma());
			
			reinsercionSocialForm.setLstCatCursoDTO(programaDelegate.consultarCatCurso());
			reinsercionSocialForm.setCursos( new Long [reinsercionSocialForm.getLstCatCursoDTO().size()]);
			for (int i =0; i< reinsercionSocialForm.getLstCatCursoDTO().size();i++){
				CatCursoDTO catCursoDTO = reinsercionSocialForm.getLstCatCursoDTO().get(i); 
				reinsercionSocialForm.getCursos()[i]=catCursoDTO.getCatCursoId(); 
}

			reinsercionSocialForm.setLstCatTrabajoDTO(programaDelegate.consultarCatTrabajo());
			
			reinsercionSocialForm.setTrabajos( new Long [reinsercionSocialForm.getLstCatTrabajoDTO().size()]);
			for (int i =0; i< reinsercionSocialForm.getLstCatTrabajoDTO().size();i++){
				CatTrabajoDTO catTrabajoDTO = reinsercionSocialForm.getLstCatTrabajoDTO().get(i); 
				reinsercionSocialForm.getTrabajos()[i]=catTrabajoDTO.getCatTrabajoId(); 
			}			
			
			reinsercionSocialForm.setCeresosDTO(centroDetencionDelegate.consultarCentrosDetencionPorTipo(TipoCentroDetencion.CERESO.getId()));
			reinsercionSocialForm.setCeresos( new Long [reinsercionSocialForm.getCeresosDTO().size()]);
			for (int i =0; i< reinsercionSocialForm.getCeresosDTO().size();i++){
				CentroDetencionDTO catTrabajoDTO = reinsercionSocialForm.getCeresosDTO().get(i); 
				reinsercionSocialForm.getCeresos()[i]=catTrabajoDTO.getCentroDetencionId(); 
			}
			
			reinsercionSocialForm.setLstCatTipoTrabajoExternoDTO(programaDelegate.consultarCatTipoTrabajoExterno());
			
			//Se crean las listas correspondientes a la administración de cursos.
			reinsercionSocialForm.setLstCatCategoriaCursoDTO(programaDelegate.consultarCatCategoriaCurso());
			reinsercionSocialForm.setLstCatTipoCursoDTO(programaDelegate.consultarCatTipoCurso());
			reinsercionSocialForm.setLstCatTipoNivelAcademicoDTO(programaDelegate.consultarCatTipoNivelAcademico());
			
			//Se resetean las variables en donde se almacenan los programas, cursos, trabajos en el caso de que se redespliegue la ventana

			reinsercionSocialForm.setCnombre("");
			reinsercionSocialForm.setCatTipoProgramaId(-1L);
			reinsercionSocialForm.setDescripcion("");
			reinsercionSocialForm.setFechaInicioPrograma("");
			reinsercionSocialForm.setFechaFinPrograma("");
			
			reinsercionSocialForm.setNombreCatTrabajo("");
			reinsercionSocialForm.setDescripcionCatTrabajo("");
			reinsercionSocialForm.setPuntosCatTrabajo(0L);
			reinsercionSocialForm.setNumeroConvenioCatTrabajo("");
			reinsercionSocialForm.setCatTipoTrabajoExternoId(-1L);
			reinsercionSocialForm.setBesExternoCatTrabajo(0L);
			
			reinsercionSocialForm.setNombreCurso("");
			reinsercionSocialForm.setDescCurso("");
			reinsercionSocialForm.setTipoCursoId(-1L);
			reinsercionSocialForm.setAdminCursoCategoriaCursoId(-1L);
			reinsercionSocialForm.setTipoNivelAcademicoId(-1L);
			reinsercionSocialForm.setPuntosCurso("");
			reinsercionSocialForm.setDuracionCurso("");
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return mapping.findForward(ADMINPROGRAMAS);
	}	

	public ActionForward guardarPrograma(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			ReinsercionSocialForm reinsercionSocialForm = (ReinsercionSocialForm)form;
				
			CatProgramaDTO catProgramaDTO = obtenerDatosPrograma(reinsercionSocialForm);
			catProgramaDTO.setProgramaId(null);
			programaDelegate.insertarCatPrograma(catProgramaDTO);
			request.setAttribute("regInsertado", REGPROGRAMASTAB);
				
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			request.setAttribute("regInsertado", "false");
		}
		return mapping.findForward(INITADMINPROGRAMAS);
	}
				
	/**
	 * @param reinsercionSocialForm
	 * @return
	 */
	private CatProgramaDTO obtenerDatosPrograma(
			ReinsercionSocialForm reinsercionSocialForm) {
				Date fechaInicioPrograma =null, fechaFinPrograma =null;
				
				try {
					fechaInicioPrograma = DateUtils.obtener(reinsercionSocialForm.getFechaInicioPrograma());
					fechaFinPrograma= DateUtils.obtener(reinsercionSocialForm.getFechaFinPrograma());
					
				} catch (NSJPNegocioException pe) {
					pe.printStackTrace();
				}
				
				CatProgramaDTO catProgramaDTO = new CatProgramaDTO();
				catProgramaDTO.setCatTipoProgramaDTO(new CatTipoProgramaDTO());
				
		catProgramaDTO.setProgramaId(reinsercionSocialForm.getProgramaId());
				catProgramaDTO.getCatTipoProgramaDTO().setCatTipoProgramaId(reinsercionSocialForm.getCatTipoProgramaId());
				catProgramaDTO.setDescripcion(reinsercionSocialForm.getDescripcion());
				catProgramaDTO.setFechaFinPrograma( fechaFinPrograma);
				catProgramaDTO.setFechaInicioPrograma(fechaInicioPrograma);
				catProgramaDTO.setNombre(reinsercionSocialForm.getCnombre());
		catProgramaDTO.setbActivo(Boolean.TRUE);
				
				List<CentroDetencionDTO> lstCentroDetencionDTO = new ArrayList<CentroDetencionDTO>();
				for (Long cd: reinsercionSocialForm.getCeresosSeleccionados()){
					CentroDetencionDTO centroDetencionDTO = new CentroDetencionDTO();
					centroDetencionDTO.setCentroDetencionId(cd);
					lstCentroDetencionDTO.add(centroDetencionDTO);
				}
				
				if (reinsercionSocialForm.getCursosSeleccionados() != null && 
						reinsercionSocialForm.getCursosSeleccionados().length > 0){
					List<CatCursoDTO> lstCatCursoDTO = new ArrayList<CatCursoDTO>();
					for (Long cc: reinsercionSocialForm.getCursosSeleccionados()){
						CatCursoDTO obj = new CatCursoDTO();
						obj.setCatCursoId(cc);
						lstCatCursoDTO.add(obj);
					}					
					catProgramaDTO.setLstCatCursoDTO(lstCatCursoDTO);
				}
				
				if (reinsercionSocialForm.getTrabajosSeleccionados() != null && 
						reinsercionSocialForm.getTrabajosSeleccionados().length > 0){
					List<CatTrabajoDTO> lstCatTrabajoDTO = new ArrayList<CatTrabajoDTO>();
					for (Long cc: reinsercionSocialForm.getTrabajosSeleccionados()){
						CatTrabajoDTO obj = new CatTrabajoDTO();
						obj.setCatTrabajoId(cc);
						lstCatTrabajoDTO.add(obj);
					}								
					catProgramaDTO.setLstCatTrabajoDTO(lstCatTrabajoDTO);
				}
				
				catProgramaDTO.setLstCentroDetencionesDTO(lstCentroDetencionDTO);
		return catProgramaDTO;
	}	
				
				
	public ActionForward guardarCurso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			
			ReinsercionSocialForm reinsercionSocialForm = (ReinsercionSocialForm)form;
		
			CatCursoDTO cursoAGuardar = obtenerCursoParaGuardar(reinsercionSocialForm);
			cursoAGuardar.setCatCursoId(null);
			programaDelegate.insertarCatCurso(cursoAGuardar);
		
			request.setAttribute("regInsertado", REGCURSOSTAB);
			
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
			request.setAttribute("regInsertado", "false");
			}
		
		return mapping.findForward(INITADMINPROGRAMAS);
	}	
	
	public ActionForward guardarTrabajo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			
			ReinsercionSocialForm reinsercionSocialForm = (ReinsercionSocialForm)form;
				
			CatTrabajoDTO catTrabajoDTO = obtenerDatosTrabajo(reinsercionSocialForm);			
			catTrabajoDTO.setCatTrabajoId(null);	
			programaDelegate.insertarCatTrabajo(catTrabajoDTO);
			
			request.setAttribute("regInsertado", REGTRABAJOSTAB);
			
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
			request.setAttribute("regInsertado", "false");
		}
		return mapping.findForward(INITADMINPROGRAMAS);
	}	
	
	/**
	 * @param reinsercionSocialForm
	 * @return
	 */
	private CatTrabajoDTO obtenerDatosTrabajo(
			ReinsercionSocialForm reinsercionSocialForm) {
		Long besExterno = reinsercionSocialForm.getBesExternoCatTrabajo();
		
		CatTrabajoDTO catTrabajoDTO = new CatTrabajoDTO();
		CatTipoTrabajoExternoDTO catTipoTrabajoExternoDTO = new CatTipoTrabajoExternoDTO();
		
		if(besExterno.compareTo(1L)==0){
			catTrabajoDTO.setBesExterno(Boolean.TRUE);
			catTipoTrabajoExternoDTO.setCatTipoExternoId(reinsercionSocialForm.getCatTipoTrabajoExternoId());
			catTrabajoDTO.setCatTipoTrabajoExterno(catTipoTrabajoExternoDTO);
			catTrabajoDTO.setCnumeroConvenio(reinsercionSocialForm.getNumeroConvenioCatTrabajo());
		}else{
			catTrabajoDTO.setBesExterno(Boolean.FALSE);
		}
		catTrabajoDTO.setbActivo(Boolean.TRUE);
		catTrabajoDTO.setCatTrabajoId(reinsercionSocialForm.getIdCatTrabajo());
		catTrabajoDTO.setCdescripcion(reinsercionSocialForm.getDescripcionCatTrabajo());
		catTrabajoDTO.setCnombre(reinsercionSocialForm.getNombreCatTrabajo());
		catTrabajoDTO.setIpuntos(reinsercionSocialForm.getPuntosCatTrabajo());
		return catTrabajoDTO;
	}	

	private CatCursoDTO obtenerCursoParaGuardar(ReinsercionSocialForm forma){
		CatCursoDTO curso = new CatCursoDTO();
		if(forma.getIdCurso()!= null && !forma.getIdCurso().equals("")){
			curso.setCatCursoId(Long.parseLong(forma.getIdCurso()));
		}
		curso.setCnombre(forma.getNombreCurso());
		curso.setCdescripcion(forma.getDescCurso());
		curso.setCduracion(forma.getDuracionCurso());
		curso.setIpuntos(Long.parseLong(forma.getPuntosCurso()));
		curso.setbActivo(Boolean.TRUE);
		if(forma.getTipoCursoId() > 0L){
			CatTipoCursoDTO tipoCurso = new CatTipoCursoDTO();
			tipoCurso.setCatTipoCursoId(forma.getTipoCursoId());
			curso.setCatTipoCursoDTO(tipoCurso);
		}
		
		if (forma.getAdminCursoCategoriaCursoId() > 0L){
			CatCategoriaCursoDTO categoria = new CatCategoriaCursoDTO();
			categoria.setCatCategoriaCursoId(forma.getAdminCursoCategoriaCursoId());
			curso.setCatCategoriaCursoDTO(categoria);
		}
		
		if(forma.getTipoNivelAcademicoId() > 0L){
			CatTipoNivelAcademicoDTO tipoNivelAcademico = new CatTipoNivelAcademicoDTO();
			tipoNivelAcademico.setCatTipoNivelAcademicoId(forma.getTipoNivelAcademicoId());
			curso.setCatTipoNivelAcademicoDTO(tipoNivelAcademico);
		}
		return curso;
	}

	@SuppressWarnings("rawtypes")
	public ActionForward llenarGrid (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			LOG.info("ejecutando Action ReinsercionSocialAction método llenarGrid");
			try {
				
				String grid = request.getParameter("gridID");
				List listaObjetos = null;
				
				final PaginacionDTO pag = PaginacionThreadHolder.get();
				if (pag != null){
				
					if(grid.equals(REGPROGRAMASTAB)){
						pag.setCampoOrd("CatPrograma_id");
						PaginacionThreadHolder.set(pag);
						listaObjetos = programaDelegate.consultarProgramas();	
					}else if(grid.equals(REGCURSOSTAB)){
						pag.setCampoOrd("CatCurso_id");
						PaginacionThreadHolder.set(pag);
						listaObjetos = programaDelegate.consultarCatCurso();
					}else if(grid.equals(REGTRABAJOSTAB)){
						pag.setCampoOrd("CatTrabajo_id");
						PaginacionThreadHolder.set(pag);
						listaObjetos = programaDelegate.consultarCatTrabajo();
}
				}

				if (LOG.isDebugEnabled()) {
					LOG.debug("################## Resultados :::::::::" + listaObjetos.size());
				}
				
				response.setContentType("text/xml; charset=ISO-8859-1");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				final PaginacionDTO pag2 = PaginacionThreadHolder.get();
				if (pag2 != null){
					//Caso particular para considerar el total de registros
					//pag2.setTotalRegistros(new Long(listaObjetos.size()));
	                LOG.debug("pag :: " + pag2);
	                if (pag2 != null && pag2.getTotalRegistros() != null) {
	                    writer.print("<total>" + pag2.getTotalPaginas() + "</total>");
	                    writer.print("<records>" + pag2.getTotalRegistros() + "</records>");
	                } else {
	                    writer.print("<total>0</total>");
	                    writer.print("<records>0</records>");
	                }
				}				
				for (Object object : listaObjetos) {
					String datos = datosGrid(object);
					writer.print(datos);
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
			
		}
		return null;
	}

	/**
	 * @param writer
	 */
	private String datosGrid(Object object) {
		
		StringBuffer buffer = new StringBuffer();
		
		if(object instanceof CatProgramaDTO){
			
			CatProgramaDTO catProgramaDTO = (CatProgramaDTO)object;
			CatTipoProgramaDTO catTipoProgramaDTO = catProgramaDTO.getCatTipoProgramaDTO();
			Long totalPuntos = 0L;
			for (CatTrabajoDTO catTrabajoDTO: catProgramaDTO.getLstCatTrabajoDTO()){
				if(catTrabajoDTO != null && catTrabajoDTO.getIpuntos()!= null){
					totalPuntos+=catTrabajoDTO.getIpuntos();
				}
			}
			for (CatCursoDTO catCursoDTO: catProgramaDTO.getLstCatCursoDTO()){
				if(catCursoDTO != null && catCursoDTO.getIpuntos()!= null){
					totalPuntos+=catCursoDTO.getIpuntos();
				}
			}
			buffer.append("<row id='"+ catProgramaDTO.getProgramaId() +"'>");
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			buffer.append(catProgramaDTO.getNombre());
			buffer.append("</div>]]></cell>");
			
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			if(catTipoProgramaDTO!=null){
				buffer.append(catTipoProgramaDTO.getDescripcion());
			}else{
				buffer.append("&nbsp;");	
			}
			buffer.append("</div>]]></cell>");
			
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			buffer.append(catProgramaDTO.getDescripcion()); 
			buffer.append("</div>]]></cell>");			
			
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			buffer.append(totalPuntos);
			buffer.append("</div>]]></cell>");
			
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"); 
			buffer.append(DateUtils.formatear(catProgramaDTO.getFechaInicioPrograma())); 
			buffer.append("</div>]]></cell>");
			
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"); 
			buffer.append(DateUtils.formatear(catProgramaDTO.getFechaFinPrograma()));
			buffer.append("</div>]]></cell>");
		
		}else if(object instanceof CatCursoDTO){
			
			CatCursoDTO catCursoDTO = (CatCursoDTO)object;
			CatTipoCursoDTO catTipoCursoDTO = catCursoDTO.getCatTipoCursoDTO();
			CatCategoriaCursoDTO catCategoriaCursoDTO = catCursoDTO.getCatCategoriaCursoDTO();
			CatTipoNivelAcademicoDTO catTipoNivelAcademicoDTO = catCursoDTO.getCatTipoNivelAcademicoDTO();
			
			buffer.append("<row id='" + catCursoDTO.getCatCursoId() + "'>");
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			buffer.append(catCursoDTO.getCnombre());
			buffer.append("</div>]]></cell>");

			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			buffer.append(catCursoDTO.getCdescripcion());
			buffer.append("</div>]]></cell>");
			
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			if(catTipoCursoDTO!=null){
				buffer.append(catTipoCursoDTO.getDescripcion());
			}else{
				buffer.append("&nbsp;");
			}
			buffer.append("</div>]]></cell>");
			
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			if(catCategoriaCursoDTO!=null){
				buffer.append(catCategoriaCursoDTO.getDescripcion());
			}else{
				buffer.append("&nbsp;");
			}
			buffer.append("</div>]]></cell>");
			
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			if(catTipoNivelAcademicoDTO!=null){
				buffer.append(catTipoNivelAcademicoDTO.getDescripcion());
			}else{
				buffer.append("&nbsp;");
			}
			buffer.append("</div>]]></cell>");
			
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			buffer.append(catCursoDTO.getIpuntos());
			buffer.append("</div>]]></cell>");

			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			buffer.append(catCursoDTO.getCduracion());
			buffer.append("</div>]]></cell>");
			
		}else if(object instanceof CatTrabajoDTO){

			CatTrabajoDTO catTrabajoDTO = (CatTrabajoDTO)object;
			CatTipoTrabajoExternoDTO catTipoTrabajoExternoDTO = catTrabajoDTO.getCatTipoTrabajoExterno();	
			
			buffer.append("<row id='"+catTrabajoDTO.getCatTrabajoId()+"'>");
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			buffer.append(catTrabajoDTO.getCnombre());
			buffer.append("</div>]]></cell>");
			
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			buffer.append(catTrabajoDTO.getCdescripcion());
			buffer.append("</div>]]></cell>");
			
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			
			if(catTrabajoDTO.getBesExterno()){
				buffer.append(EXTERNO);
			}else{
				buffer.append(INTERNO);
			}
			buffer.append("</div>]]></cell>");

			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");			
			if(catTrabajoDTO.getBesExterno()){
				if(catTipoTrabajoExternoDTO != null){
					buffer.append(catTipoTrabajoExternoDTO.getDescripcion());
				}else{
					buffer.append("&nbsp;");	
				}
			}else{
				buffer.append("&nbsp;");
			}
			buffer.append("</div>]]></cell>");

			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			if(catTrabajoDTO.getBesExterno()){
				buffer.append(catTrabajoDTO.getCnumeroConvenio());	
			}else{
				buffer.append("&nbsp;");
			}
			buffer.append("</div>]]></cell>");						
			
			buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
			buffer.append(catTrabajoDTO.getIpuntos());
			buffer.append("</div>]]></cell>");
		}
		
		buffer.append("</row>");
		
		return buffer.toString();
	}
	
	
	public ActionForward obtenerCatalogoPorId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			LOG.info("ejecutando Action ReinsercionSocialAction método llenarGrid");
		try {
				
			String catalogo = request.getParameter("catalogo");
			String id = request.getParameter("id");
			
			if(id!=null){
				Object objeto = null;
				if(catalogo.equals(REGPROGRAMASTAB)){
					CatProgramaDTO catProgramaDTO = new CatProgramaDTO();
					catProgramaDTO.setProgramaId(Long.parseLong(id));
					objeto = programaDelegate.consultarProgramaPorId(catProgramaDTO);	
				}else if(catalogo.equals(REGCURSOSTAB)){
					CatCursoDTO catCursoDTO = new CatCursoDTO();
					catCursoDTO.setCatCursoId(Long.parseLong(id));
					objeto = programaDelegate.consultarCursoPorId(catCursoDTO);
				}else if(catalogo.equals(REGTRABAJOSTAB)){
					CatTrabajoDTO catTrabajoDTO = new CatTrabajoDTO();
					catTrabajoDTO.setCatTrabajoId(Long.parseLong(id));
					objeto = programaDelegate.consultarTrabajoPorId(catTrabajoDTO);
				}
				
				
				response.setContentType("text/javascript; charset=ISO-8859-1");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print(dto2Json(objeto));
				writer.flush();
				writer.close();
			}
		
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
			
		}
		return null;
	}	

	/**
	 * @param writer
	 */
	@SuppressWarnings("unchecked")
	private String dto2Json(Object object) {
		JSONObject jsonObject = null;
		StringWriter out = null;
		String jsonText = "";
		try {
			jsonObject = new JSONObject();
			
			if(object instanceof CatProgramaDTO){
				
				CatProgramaDTO catProgramaDTO = (CatProgramaDTO)object;
				CatTipoProgramaDTO catTipoProgramaDTO = catProgramaDTO.getCatTipoProgramaDTO();
				Long totalPuntos = 0L;
				
				JSONArray jsonArrayTrabajos = new JSONArray(); 
				JSONArray jsonArrayCursos = new JSONArray();
				JSONArray jsonArrayCeresos = new JSONArray();
				
				for (CatTrabajoDTO catTrabajoDTO: catProgramaDTO.getLstCatTrabajoDTO()){
					jsonArrayTrabajos.add(catTrabajoDTO.getCatTrabajoId());				
					if(catTrabajoDTO != null && catTrabajoDTO.getIpuntos()!= null){
						totalPuntos+=catTrabajoDTO.getIpuntos();
					}
				}
	
				for (CatCursoDTO catCursoDTO: catProgramaDTO.getLstCatCursoDTO()){
					jsonArrayCursos.add(catCursoDTO.getCatCursoId());
					if(catCursoDTO.getIpuntos()!= null){
						totalPuntos+=catCursoDTO.getIpuntos();
					}
				}
	
				for (CentroDetencionDTO centroDetencionDTO : catProgramaDTO.getLstCentroDetencionesDTO()){
					jsonArrayCeresos.add(centroDetencionDTO.getCentroDetencionId());
				}			
				
				jsonObject.put("CatProgramaId",catProgramaDTO.getProgramaId());
				jsonObject.put("CatProgramaNombre",catProgramaDTO.getNombre());
				
				if(catTipoProgramaDTO!=null){
					jsonObject.put("CatTipoProgramaId",catTipoProgramaDTO.getCatTipoProgramaId());
				}else{
					jsonObject.put("CatTipoProgramaId",-1L);	
				}
	
				jsonObject.put("CatProgramaDescripcion", catProgramaDTO.getDescripcion()); 
				jsonObject.put("CatProgramaPuntos", totalPuntos);
				jsonObject.put("CatProgramaFechaInicio", DateUtils.formatear(catProgramaDTO.getFechaInicioPrograma())); 
				jsonObject.put("CatProgramaFechaInicio", DateUtils.formatear(catProgramaDTO.getFechaFinPrograma()));
				jsonObject.put("CatProgramaCursos", jsonArrayCursos);
				jsonObject.put("CatProgramaTrabajos", jsonArrayTrabajos);
				jsonObject.put("CatProgramaCeresos", jsonArrayCeresos);
			
			}else if(object instanceof CatCursoDTO){
				
				CatCursoDTO catCursoDTO = (CatCursoDTO)object;
				CatTipoCursoDTO catTipoCursoDTO = catCursoDTO.getCatTipoCursoDTO();
				CatCategoriaCursoDTO catCategoriaCursoDTO = catCursoDTO.getCatCategoriaCursoDTO();
				CatTipoNivelAcademicoDTO catTipoNivelAcademicoDTO = catCursoDTO.getCatTipoNivelAcademicoDTO();
				
				jsonObject.put("CatCursoId", catCursoDTO.getCatCursoId());
				jsonObject.put("CatCursoNombre",catCursoDTO.getCnombre());
				jsonObject.put("CatCursoDescripcion",catCursoDTO.getCdescripcion());
				if(catTipoCursoDTO!=null){
					jsonObject.put("CatTipoCursoId",catTipoCursoDTO.getCatTipoCursoId());
				}else{
					jsonObject.put("CatTipoCursoId",-1L);
				}
	
				if(catCategoriaCursoDTO!=null){
					jsonObject.put("CatCategoriaCursoId",catCategoriaCursoDTO.getCatCategoriaCursoId());
				}else{
					jsonObject.put("CatCategoriaCursoId",-1L);
				}
				
				if(catTipoNivelAcademicoDTO!=null){
					jsonObject.put("CatTipoNivelAcademico",catTipoNivelAcademicoDTO.getCatTipoNivelAcademicoId());
				}else{
					jsonObject.put("CattipoNivelAcademico",-1L);
				}			
				
				jsonObject.put("CatCursoPuntos",catCursoDTO.getIpuntos());
				jsonObject.put("CatCursoDuracion",catCursoDTO.getCduracion());
				
				
			}else if(object instanceof CatTrabajoDTO){
	
				CatTrabajoDTO catTrabajoDTO = (CatTrabajoDTO)object;
				CatTipoTrabajoExternoDTO catTipoTrabajoExternoDTO = catTrabajoDTO.getCatTipoTrabajoExterno();	
				
				jsonObject.put("CatTrabajoId",catTrabajoDTO.getCatTrabajoId());
				jsonObject.put("CatTrabajoNombre",catTrabajoDTO.getCnombre());
				jsonObject.put("CatTrabajoDescripcion",catTrabajoDTO.getCdescripcion());
	
				if(catTrabajoDTO.getBesExterno()){
					jsonObject.put("CatTrabajoBesExterno", 1);
				}else{
					jsonObject.put("CatTrabajoBesExterno", 0);
				}
				
				if(catTrabajoDTO.getBesExterno()){
					if(catTipoTrabajoExternoDTO != null){
						jsonObject.put("CatTipoTrabajoExternoId",catTipoTrabajoExternoDTO.getCatTipoExternoId());
					}else{
						jsonObject.put("CatTipoTrabajoExternoId",-1L);	
					}
					jsonObject.put("CatTrabajoNoConvenio",catTrabajoDTO.getCnumeroConvenio());		
				}else{
					jsonObject.put("CatTipoTrabajoExternoId",-1L);
					jsonObject.put("CatTrabajoNoConvenio","");
				}			
				jsonObject.put("CatTrabajoPuntos",catTrabajoDTO.getIpuntos());
				
			}
			
			out = new StringWriter();
			jsonObject.writeJSONString(out);
			jsonText = out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonText;		
	}
	
	
	public ActionForward actualizarPrograma(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			ReinsercionSocialForm reinsercionSocialForm = (ReinsercionSocialForm)form;
	
			CatProgramaDTO catProgramaDTO = obtenerDatosPrograma(reinsercionSocialForm);
			programaDelegate.actualizarPrograma(catProgramaDTO);
			request.setAttribute("regInsertado", REGPROGRAMASTAB);
			request.setAttribute("accion", ACTUALIZAR);
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			request.setAttribute("regInsertado", "false");
		}
		return mapping.findForward(INITADMINPROGRAMAS);
	}

	public ActionForward actualizarCurso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			
			ReinsercionSocialForm reinsercionSocialForm = (ReinsercionSocialForm)form;
		
			CatCursoDTO cursoAGuardar = obtenerCursoParaGuardar(reinsercionSocialForm);
			programaDelegate.actualizarCurso(cursoAGuardar);
		
			request.setAttribute("regInsertado", REGCURSOSTAB);
			request.setAttribute("accion", ACTUALIZAR);
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
			request.setAttribute("regInsertado", "false");
		}
		
		return mapping.findForward(INITADMINPROGRAMAS);
	}	
	
	public ActionForward actualizarTrabajo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			
			ReinsercionSocialForm reinsercionSocialForm = (ReinsercionSocialForm)form;
				
			CatTrabajoDTO catTrabajoDTO = obtenerDatosTrabajo(reinsercionSocialForm);					
			programaDelegate.actualizarTrabajo(catTrabajoDTO);
			
			request.setAttribute("regInsertado", REGTRABAJOSTAB);
			request.setAttribute("accion", ACTUALIZAR);
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
			request.setAttribute("regInsertado", "false");
		}
		return mapping.findForward(INITADMINPROGRAMAS);
	}	

	public ActionForward eliminarPrograma(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			ReinsercionSocialForm reinsercionSocialForm = (ReinsercionSocialForm)form;
	
			CatProgramaDTO catProgramaDTO = obtenerDatosPrograma(reinsercionSocialForm);
			boolean resultado = programaDelegate.eliminarPrograma(catProgramaDTO);
			
			request.setAttribute("regInsertado", REGPROGRAMASTAB);
			request.setAttribute("accion", ELIMINAR);
//			if(!resultado){
//				request.setAttribute("regInsertado", CURTRABASOCIADOS);
//			}
						
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			request.setAttribute("regInsertado", "false");
		}
		return mapping.findForward(INITADMINPROGRAMAS);
	}

	public ActionForward eliminarCurso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			
			ReinsercionSocialForm reinsercionSocialForm = (ReinsercionSocialForm)form;
		
			CatCursoDTO cursoAGuardar = obtenerCursoParaGuardar(reinsercionSocialForm);
			boolean resultado = programaDelegate.eliminarCurso(cursoAGuardar);		
			
			request.setAttribute("regInsertado", REGCURSOSTAB);
			request.setAttribute("accion", ELIMINAR);
//			if(!resultado){
//				request.setAttribute("regInsertado", PROGASOCIADOS);
//			}
						
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
			request.setAttribute("regInsertado", "false");
		}
		
		return mapping.findForward(INITADMINPROGRAMAS);
	}	
	
	public ActionForward eliminarTrabajo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			
			ReinsercionSocialForm reinsercionSocialForm = (ReinsercionSocialForm)form;
				
			CatTrabajoDTO catTrabajoDTO = obtenerDatosTrabajo(reinsercionSocialForm);
			
			//boolean resultado = 
			programaDelegate.eliminarTrabajo(catTrabajoDTO);
			
			request.setAttribute("regInsertado", REGTRABAJOSTAB);
			request.setAttribute("accion", ELIMINAR);
//			if(!resultado){
//				request.setAttribute("regInsertado", PROGASOCIADOS);
//			}
//			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
			request.setAttribute("regInsertado", "false");
		}
		return mapping.findForward(INITADMINPROGRAMAS);
	}		
}