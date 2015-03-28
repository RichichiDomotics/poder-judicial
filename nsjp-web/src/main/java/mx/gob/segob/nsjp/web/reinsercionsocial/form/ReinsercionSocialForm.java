/**
* Nombre del Programa : ReinsercionSocialForm.java
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
package mx.gob.segob.nsjp.web.reinsercionsocial.form;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.dto.niveles.CatTipoNivelAcademicoDTO;
import mx.gob.segob.nsjp.dto.proceso.ProcesoDTO;
import mx.gob.segob.nsjp.dto.programas.CatCategoriaCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoTrabajoExternoDTO;
import mx.gob.segob.nsjp.dto.programas.CatTrabajoDTO;
import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
@SuppressWarnings("rawtypes")
public class ReinsercionSocialForm extends GenericForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4552775083040206406L;

	// atributos finales de la forma
	
//	private CatProgramaDTO catProgramaDTO;
	
	private Long programaId;
	private String cnombre;
	private String descripcion;
	private String fechaInicioPrograma;
	private String fechaFinPrograma;
	private Long catTipoProgramaId;	
	
	private Long idCatTrabajo;
	private String nombreCatTrabajo;
	private String descripcionCatTrabajo;
	private Long besExternoCatTrabajo;
	private Long catTipoTrabajoExternoId;
	private List<CatTipoTrabajoExternoDTO> lstCatTipoTrabajoExternoDTO;
	private String numeroConvenioCatTrabajo;
	private Long puntosCatTrabajo;
	
	private List<CatTipoProgramaDTO> lstCatTipoProgramaDTO;
	private Long[] cursosSeleccionados;
	private Long[] trabajosSeleccionados;
	private Long[] ceresosSeleccionados;
	private Long[] cursos;
	private Long[] trabajos;
	private Long[] ceresos;	

	private List<CatCursoDTO> lstCatCursoDTO;
	private List<CatTrabajoDTO> lstCatTrabajoDTO;	
	private List<CentroDetencionDTO> ceresosDTO;

	//	// atributos genericos de la forma
	
	private String nuc;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String cereso;
	private String caso;
	private String causa;
	private String carpeta;
	
	private String proceso;
	
	private List<CasoDTO> casos;
	private List<ProcesoDTO> procesosDTO;
	
	private String ocultarBuscar;
	
	private String nivelInicial;
	
	private String desempenioInicial;
	private String calificacionDesempenio;
	
	private String observaciones;
		
	private String nivelCertificado; 
	private String fechaCertificado;
	
	private String empresaSTR;
	private String convenioSTR;
	private String internoSTR;
	private String horarioEntradaSTR;
	private String horarioSalidaSTR;
	private String periodoSTR;
	private String fechaInicioSTR;
	private String observacionesSTR;
	private String aprobacionSTR;
	
	private String motivoTMD;
	private String fechaTMD;
	private String horaTMD;
	private String descripcionTMD;
	
	private String tipoAnomaliaRAE;
	private String descripcionAnomaliaRAE;
	
	private String motivoEPS;
	private String descripcionEPS;
	private String fechaInicioPermisoEPS;
	private String fechaFinPermisoEPS;
	private String horaSalidaEPS;
	private String horaRegresoEPS;
	private String observacionesEPS;
	private String descicionEPS;
	
	private String ceresoActualTS;
	private String ceresoDestinoTS;
	private String motivoTS;
	private String observacionesTS;
	private String decisionTS;
	
	
	
	private String motivoFP;
	private String observacionesFP;
	private String fechaCumplimientoFP;
	private String fechaAudienciaFP;
	private String tribunalFP;
	private String juezFP;
	
	private String remitenteBPL;
	private String causaBPL;
	private String fechaInicioPenaBPL;
	private String argumentosBPL;
	private String fechaAudienciaBPL;
	private String decisionBPL;
	
	
	private String remitenteRRD;
	private String motivoRRD;
	private String fechaAudienciaRRD;
	private String decisionRRD;
	
	private ArrayList lstNiveles;
	private ArrayList lstDesempenios;
	private ArrayList lstCalificacionDesempenio;
	
	private ArrayList lstTrabajoInterno;
	private ArrayList lstAprobacion;
	
	private ArrayList lstMotivosTMD;
	
	private ArrayList lstTipoAnomaliasRAE;

	private ArrayList lstMotivosEPS;
	
	private ArrayList lstMotivosTS;
	
	private ArrayList lstMotivosFP;
	
	private ArrayList lstTribunales;
	private ArrayList lstJuez;
	
	private ArrayList lstCausasBPL;

	
	//Objetos de administraci&oacute;n de cursos
	private String idCurso;
	private String nombreCurso;
	private String descCurso;
	private String duracionCurso;
	private String puntosCurso;
	private List<CatTipoCursoDTO> lstCatTipoCursoDTO;
	private long tipoCursoId;
	private List<CatCategoriaCursoDTO> lstCatCategoriaCursoDTO;
	private long adminCursoCategoriaCursoId;
	private List<CatTipoNivelAcademicoDTO> lstCatTipoNivelAcademicoDTO;
	private long tipoNivelAcademicoId;

	/**
	 * Método de acceso al campo programaId.
	 * @return El valor del campo programaId
	 */
	public Long getProgramaId() {
		return programaId;
	}

	/**
	 * Asigna el valor al campo programaId.
	 * @param programaId el valor programaId a asignar
	 */
	public void setProgramaId(Long programaId) {
		this.programaId = programaId;
	}

	/**
	 * Método de acceso al campo cnombre.
	 * @return El valor del campo cnombre
	 */
	public String getCnombre() {
		return cnombre;
	}

	/**
	 * Asigna el valor al campo cnombre.
	 * @param cnombre el valor cnombre a asignar
	 */
	public void setCnombre(String cnombre) {
		this.cnombre = cnombre;
	}

	/**
	 * Método de acceso al campo descripcion.
	 * @return El valor del campo descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Asigna el valor al campo descripcion.
	 * @param descripcion el valor descripcion a asignar
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Método de acceso al campo fechaInicioPrograma.
	 * @return El valor del campo fechaInicioPrograma
	 */
	public String getFechaInicioPrograma() {
		return fechaInicioPrograma;
	}

	/**
	 * Asigna el valor al campo fechaInicioPrograma.
	 * @param fechaInicioPrograma el valor fechaInicioPrograma a asignar
	 */
	public void setFechaInicioPrograma(String fechaInicioPrograma) {
		this.fechaInicioPrograma = fechaInicioPrograma;
	}

	/**
	 * Método de acceso al campo fechaFinPrograma.
	 * @return El valor del campo fechaFinPrograma
	 */
	public String getFechaFinPrograma() {
		return fechaFinPrograma;
	}

	/**
	 * Asigna el valor al campo fechaFinPrograma.
	 * @param fechaFinPrograma el valor fechaFinPrograma a asignar
	 */
	public void setFechaFinPrograma(String fechaFinPrograma) {
		this.fechaFinPrograma = fechaFinPrograma;
	}

	/**
	 * Método de acceso al campo catTipoProgramaId.
	 * @return El valor del campo catTipoProgramaId
	 */
	public Long getCatTipoProgramaId() {
		return catTipoProgramaId;
	}

	/**
	 * Asigna el valor al campo catTipoProgramaId.
	 * @param catTipoProgramaId el valor catTipoProgramaId a asignar
	 */
	public void setCatTipoProgramaId(Long catTipoProgramaId) {
		this.catTipoProgramaId = catTipoProgramaId;
	}

	/**
	 * Método de acceso al campo lstCatTipoProgramaDTO.
	 * @return El valor del campo lstCatTipoProgramaDTO
	 */
	public List<CatTipoProgramaDTO> getLstCatTipoProgramaDTO() {
		return lstCatTipoProgramaDTO;
	}

	/**
	 * Asigna el valor al campo lstCatTipoProgramaDTO.
	 * @param lstCatTipoProgramaDTO el valor lstCatTipoProgramaDTO a asignar
	 */
	public void setLstCatTipoProgramaDTO(
			List<CatTipoProgramaDTO> lstCatTipoProgramaDTO) {
		this.lstCatTipoProgramaDTO = lstCatTipoProgramaDTO;
	}

	/**
	 * Método de acceso al campo cursosSeleccionados.
	 * @return El valor del campo cursosSeleccionados
	 */
	public Long[] getCursosSeleccionados() {
		return cursosSeleccionados;
	}

	/**
	 * Asigna el valor al campo cursosSeleccionados.
	 * @param cursosSeleccionados el valor cursosSeleccionados a asignar
	 */
	public void setCursosSeleccionados(Long[] cursosSeleccionados) {
		this.cursosSeleccionados = cursosSeleccionados;
	}

	/**
	 * Método de acceso al campo trabajosSeleccionados.
	 * @return El valor del campo trabajosSeleccionados
	 */
	public Long[] getTrabajosSeleccionados() {
		return trabajosSeleccionados;
	}

	/**
	 * Asigna el valor al campo trabajosSeleccionados.
	 * @param trabajosSeleccionados el valor trabajosSeleccionados a asignar
	 */
	public void setTrabajosSeleccionados(Long[] trabajosSeleccionados) {
		this.trabajosSeleccionados = trabajosSeleccionados;
	}

	/**
	 * Método de acceso al campo ceresosSeleccionados.
	 * @return El valor del campo ceresosSeleccionados
	 */
	public Long[] getCeresosSeleccionados() {
		return ceresosSeleccionados;
	}

	/**
	 * Asigna el valor al campo ceresosSeleccionados.
	 * @param ceresosSeleccionados el valor ceresosSeleccionados a asignar
	 */
	public void setCeresosSeleccionados(Long[] ceresosSeleccionados) {
		this.ceresosSeleccionados = ceresosSeleccionados;
	}

	/**
	 * Método de acceso al campo cursos.
	 * @return El valor del campo cursos
	 */
	public Long[] getCursos() {
		return cursos;
	}

	/**
	 * Asigna el valor al campo cursos.
	 * @param cursos el valor cursos a asignar
	 */
	public void setCursos(Long[] cursos) {
		this.cursos = cursos;
	}

	/**
	 * Método de acceso al campo trabajos.
	 * @return El valor del campo trabajos
	 */
	public Long[] getTrabajos() {
		return trabajos;
	}

	/**
	 * Asigna el valor al campo trabajos.
	 * @param trabajos el valor trabajos a asignar
	 */
	public void setTrabajos(Long[] trabajos) {
		this.trabajos = trabajos;
	}

	/**
	 * Método de acceso al campo ceresos.
	 * @return El valor del campo ceresos
	 */
	public Long[] getCeresos() {
		return ceresos;
	}

	/**
	 * Asigna el valor al campo ceresos.
	 * @param ceresos el valor ceresos a asignar
	 */
	public void setCeresos(Long[] ceresos) {
		this.ceresos = ceresos;
	}

	/**
	 * Método de acceso al campo lstCatCursoDTO.
	 * @return El valor del campo lstCatCursoDTO
	 */
	public List<CatCursoDTO> getLstCatCursoDTO() {
		return lstCatCursoDTO;
	}

	/**
	 * Asigna el valor al campo lstCatCursoDTO.
	 * @param lstCatCursoDTO el valor lstCatCursoDTO a asignar
	 */
	public void setLstCatCursoDTO(List<CatCursoDTO> lstCatCursoDTO) {
		this.lstCatCursoDTO = lstCatCursoDTO;
	}

	/**
	 * Método de acceso al campo lstCatTrabajoDTO.
	 * @return El valor del campo lstCatTrabajoDTO
	 */
	public List<CatTrabajoDTO> getLstCatTrabajoDTO() {
		return lstCatTrabajoDTO;
	}

	/**
	 * Asigna el valor al campo lstCatTrabajoDTO.
	 * @param lstCatTrabajoDTO el valor lstCatTrabajoDTO a asignar
	 */
	public void setLstCatTrabajoDTO(List<CatTrabajoDTO> lstCatTrabajoDTO) {
		this.lstCatTrabajoDTO = lstCatTrabajoDTO;
	}

	/**
	 * Método de acceso al campo ceresosDTO.
	 * @return El valor del campo ceresosDTO
	 */
	public List<CentroDetencionDTO> getCeresosDTO() {
		return ceresosDTO;
	}

	/**
	 * Asigna el valor al campo ceresosDTO.
	 * @param ceresosDTO el valor ceresosDTO a asignar
	 */
	public void setCeresosDTO(List<CentroDetencionDTO> ceresosDTO) {
		this.ceresosDTO = ceresosDTO;
	}

	/**
	 * Método de acceso al campo nuc.
	 * @return El valor del campo nuc
	 */
	public String getNuc() {
		return nuc;
	}

	/**
	 * Asigna el valor al campo nuc.
	 * @param nuc el valor nuc a asignar
	 */
	public void setNuc(String nuc) {
		this.nuc = nuc;
	}

	/**
	 * Método de acceso al campo nombre.
	 * @return El valor del campo nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna el valor al campo nombre.
	 * @param nombre el valor nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método de acceso al campo apellidoPaterno.
	 * @return El valor del campo apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * Asigna el valor al campo apellidoPaterno.
	 * @param apellidoPaterno el valor apellidoPaterno a asignar
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * Método de acceso al campo apellidoMaterno.
	 * @return El valor del campo apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * Asigna el valor al campo apellidoMaterno.
	 * @param apellidoMaterno el valor apellidoMaterno a asignar
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * Método de acceso al campo cereso.
	 * @return El valor del campo cereso
	 */
	public String getCereso() {
		return cereso;
	}

	/**
	 * Asigna el valor al campo cereso.
	 * @param cereso el valor cereso a asignar
	 */
	public void setCereso(String cereso) {
		this.cereso = cereso;
	}

	/**
	 * Método de acceso al campo caso.
	 * @return El valor del campo caso
	 */
	public String getCaso() {
		return caso;
	}

	/**
	 * Asigna el valor al campo caso.
	 * @param caso el valor caso a asignar
	 */
	public void setCaso(String caso) {
		this.caso = caso;
	}

	/**
	 * Método de acceso al campo causa.
	 * @return El valor del campo causa
	 */
	public String getCausa() {
		return causa;
	}

	/**
	 * Asigna el valor al campo causa.
	 * @param causa el valor causa a asignar
	 */
	public void setCausa(String causa) {
		this.causa = causa;
	}

	/**
	 * Método de acceso al campo carpeta.
	 * @return El valor del campo carpeta
	 */
	public String getCarpeta() {
		return carpeta;
	}

	/**
	 * Asigna el valor al campo carpeta.
	 * @param carpeta el valor carpeta a asignar
	 */
	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}

	/**
	 * Método de acceso al campo proceso.
	 * @return El valor del campo proceso
	 */
	public String getProceso() {
		return proceso;
	}

	/**
	 * Asigna el valor al campo proceso.
	 * @param proceso el valor proceso a asignar
	 */
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	/**
	 * Método de acceso al campo casos.
	 * @return El valor del campo casos
	 */
	public List<CasoDTO> getCasos() {
		return casos;
	}

	/**
	 * Asigna el valor al campo casos.
	 * @param casos el valor casos a asignar
	 */
	public void setCasos(List<CasoDTO> casos) {
		this.casos = casos;
	}

	/**
	 * Método de acceso al campo procesosDTO.
	 * @return El valor del campo procesosDTO
	 */
	public List<ProcesoDTO> getProcesosDTO() {
		return procesosDTO;
	}

	/**
	 * Asigna el valor al campo procesosDTO.
	 * @param procesosDTO el valor procesosDTO a asignar
	 */
	public void setProcesosDTO(List<ProcesoDTO> procesosDTO) {
		this.procesosDTO = procesosDTO;
	}

	/**
	 * Método de acceso al campo ocultarBuscar.
	 * @return El valor del campo ocultarBuscar
	 */
	public String getOcultarBuscar() {
		return ocultarBuscar;
	}

	/**
	 * Asigna el valor al campo ocultarBuscar.
	 * @param ocultarBuscar el valor ocultarBuscar a asignar
	 */
	public void setOcultarBuscar(String ocultarBuscar) {
		this.ocultarBuscar = ocultarBuscar;
	}

	/**
	 * Método de acceso al campo nivelInicial.
	 * @return El valor del campo nivelInicial
	 */
	public String getNivelInicial() {
		return nivelInicial;
	}

	/**
	 * Asigna el valor al campo nivelInicial.
	 * @param nivelInicial el valor nivelInicial a asignar
	 */
	public void setNivelInicial(String nivelInicial) {
		this.nivelInicial = nivelInicial;
	}

	/**
	 * Método de acceso al campo desempenioInicial.
	 * @return El valor del campo desempenioInicial
	 */
	public String getDesempenioInicial() {
		return desempenioInicial;
	}

	/**
	 * Asigna el valor al campo desempenioInicial.
	 * @param desempenioInicial el valor desempenioInicial a asignar
	 */
	public void setDesempenioInicial(String desempenioInicial) {
		this.desempenioInicial = desempenioInicial;
	}

	/**
	 * Método de acceso al campo calificacionDesempenio.
	 * @return El valor del campo calificacionDesempenio
	 */
	public String getCalificacionDesempenio() {
		return calificacionDesempenio;
	}

	/**
	 * Asigna el valor al campo calificacionDesempenio.
	 * @param calificacionDesempenio el valor calificacionDesempenio a asignar
	 */
	public void setCalificacionDesempenio(String calificacionDesempenio) {
		this.calificacionDesempenio = calificacionDesempenio;
	}

	/**
	 * Método de acceso al campo observaciones.
	 * @return El valor del campo observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Asigna el valor al campo observaciones.
	 * @param observaciones el valor observaciones a asignar
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Método de acceso al campo nivelCertificado.
	 * @return El valor del campo nivelCertificado
	 */
	public String getNivelCertificado() {
		return nivelCertificado;
	}

	/**
	 * Asigna el valor al campo nivelCertificado.
	 * @param nivelCertificado el valor nivelCertificado a asignar
	 */
	public void setNivelCertificado(String nivelCertificado) {
		this.nivelCertificado = nivelCertificado;
	}

	/**
	 * Método de acceso al campo fechaCertificado.
	 * @return El valor del campo fechaCertificado
	 */
	public String getFechaCertificado() {
		return fechaCertificado;
	}

	/**
	 * Asigna el valor al campo fechaCertificado.
	 * @param fechaCertificado el valor fechaCertificado a asignar
	 */
	public void setFechaCertificado(String fechaCertificado) {
		this.fechaCertificado = fechaCertificado;
	}

	/**
	 * Método de acceso al campo empresaSTR.
	 * @return El valor del campo empresaSTR
	 */
	public String getEmpresaSTR() {
		return empresaSTR;
	}

	/**
	 * Asigna el valor al campo empresaSTR.
	 * @param empresaSTR el valor empresaSTR a asignar
	 */
	public void setEmpresaSTR(String empresaSTR) {
		this.empresaSTR = empresaSTR;
	}

	/**
	 * Método de acceso al campo convenioSTR.
	 * @return El valor del campo convenioSTR
	 */
	public String getConvenioSTR() {
		return convenioSTR;
	}

	/**
	 * Asigna el valor al campo convenioSTR.
	 * @param convenioSTR el valor convenioSTR a asignar
	 */
	public void setConvenioSTR(String convenioSTR) {
		this.convenioSTR = convenioSTR;
	}

	/**
	 * Método de acceso al campo internoSTR.
	 * @return El valor del campo internoSTR
	 */
	public String getInternoSTR() {
		return internoSTR;
	}

	/**
	 * Asigna el valor al campo internoSTR.
	 * @param internoSTR el valor internoSTR a asignar
	 */
	public void setInternoSTR(String internoSTR) {
		this.internoSTR = internoSTR;
	}

	/**
	 * Método de acceso al campo horarioEntradaSTR.
	 * @return El valor del campo horarioEntradaSTR
	 */
	public String getHorarioEntradaSTR() {
		return horarioEntradaSTR;
	}

	/**
	 * Asigna el valor al campo horarioEntradaSTR.
	 * @param horarioEntradaSTR el valor horarioEntradaSTR a asignar
	 */
	public void setHorarioEntradaSTR(String horarioEntradaSTR) {
		this.horarioEntradaSTR = horarioEntradaSTR;
	}

	/**
	 * Método de acceso al campo horarioSalidaSTR.
	 * @return El valor del campo horarioSalidaSTR
	 */
	public String getHorarioSalidaSTR() {
		return horarioSalidaSTR;
	}

	/**
	 * Asigna el valor al campo horarioSalidaSTR.
	 * @param horarioSalidaSTR el valor horarioSalidaSTR a asignar
	 */
	public void setHorarioSalidaSTR(String horarioSalidaSTR) {
		this.horarioSalidaSTR = horarioSalidaSTR;
	}

	/**
	 * Método de acceso al campo periodoSTR.
	 * @return El valor del campo periodoSTR
	 */
	public String getPeriodoSTR() {
		return periodoSTR;
	}

	/**
	 * Asigna el valor al campo periodoSTR.
	 * @param periodoSTR el valor periodoSTR a asignar
	 */
	public void setPeriodoSTR(String periodoSTR) {
		this.periodoSTR = periodoSTR;
	}

	/**
	 * Método de acceso al campo fechaInicioSTR.
	 * @return El valor del campo fechaInicioSTR
	 */
	public String getFechaInicioSTR() {
		return fechaInicioSTR;
	}

	/**
	 * Asigna el valor al campo fechaInicioSTR.
	 * @param fechaInicioSTR el valor fechaInicioSTR a asignar
	 */
	public void setFechaInicioSTR(String fechaInicioSTR) {
		this.fechaInicioSTR = fechaInicioSTR;
	}

	/**
	 * Método de acceso al campo observacionesSTR.
	 * @return El valor del campo observacionesSTR
	 */
	public String getObservacionesSTR() {
		return observacionesSTR;
	}

	/**
	 * Asigna el valor al campo observacionesSTR.
	 * @param observacionesSTR el valor observacionesSTR a asignar
	 */
	public void setObservacionesSTR(String observacionesSTR) {
		this.observacionesSTR = observacionesSTR;
	}

	/**
	 * Método de acceso al campo aprobacionSTR.
	 * @return El valor del campo aprobacionSTR
	 */
	public String getAprobacionSTR() {
		return aprobacionSTR;
	}

	/**
	 * Asigna el valor al campo aprobacionSTR.
	 * @param aprobacionSTR el valor aprobacionSTR a asignar
	 */
	public void setAprobacionSTR(String aprobacionSTR) {
		this.aprobacionSTR = aprobacionSTR;
	}

	/**
	 * Método de acceso al campo motivoTMD.
	 * @return El valor del campo motivoTMD
	 */
	public String getMotivoTMD() {
		return motivoTMD;
	}

	/**
	 * Asigna el valor al campo motivoTMD.
	 * @param motivoTMD el valor motivoTMD a asignar
	 */
	public void setMotivoTMD(String motivoTMD) {
		this.motivoTMD = motivoTMD;
	}

	/**
	 * Método de acceso al campo fechaTMD.
	 * @return El valor del campo fechaTMD
	 */
	public String getFechaTMD() {
		return fechaTMD;
	}

	/**
	 * Asigna el valor al campo fechaTMD.
	 * @param fechaTMD el valor fechaTMD a asignar
	 */
	public void setFechaTMD(String fechaTMD) {
		this.fechaTMD = fechaTMD;
	}

	/**
	 * Método de acceso al campo horaTMD.
	 * @return El valor del campo horaTMD
	 */
	public String getHoraTMD() {
		return horaTMD;
	}

	/**
	 * Asigna el valor al campo horaTMD.
	 * @param horaTMD el valor horaTMD a asignar
	 */
	public void setHoraTMD(String horaTMD) {
		this.horaTMD = horaTMD;
	}

	/**
	 * Método de acceso al campo descripcionTMD.
	 * @return El valor del campo descripcionTMD
	 */
	public String getDescripcionTMD() {
		return descripcionTMD;
	}

	/**
	 * Asigna el valor al campo descripcionTMD.
	 * @param descripcionTMD el valor descripcionTMD a asignar
	 */
	public void setDescripcionTMD(String descripcionTMD) {
		this.descripcionTMD = descripcionTMD;
	}

	/**
	 * Método de acceso al campo tipoAnomaliaRAE.
	 * @return El valor del campo tipoAnomaliaRAE
	 */
	public String getTipoAnomaliaRAE() {
		return tipoAnomaliaRAE;
	}

	/**
	 * Asigna el valor al campo tipoAnomaliaRAE.
	 * @param tipoAnomaliaRAE el valor tipoAnomaliaRAE a asignar
	 */
	public void setTipoAnomaliaRAE(String tipoAnomaliaRAE) {
		this.tipoAnomaliaRAE = tipoAnomaliaRAE;
	}

	/**
	 * Método de acceso al campo descripcionAnomaliaRAE.
	 * @return El valor del campo descripcionAnomaliaRAE
	 */
	public String getDescripcionAnomaliaRAE() {
		return descripcionAnomaliaRAE;
	}

	/**
	 * Asigna el valor al campo descripcionAnomaliaRAE.
	 * @param descripcionAnomaliaRAE el valor descripcionAnomaliaRAE a asignar
	 */
	public void setDescripcionAnomaliaRAE(String descripcionAnomaliaRAE) {
		this.descripcionAnomaliaRAE = descripcionAnomaliaRAE;
	}

	/**
	 * Método de acceso al campo motivoEPS.
	 * @return El valor del campo motivoEPS
	 */
	public String getMotivoEPS() {
		return motivoEPS;
	}

	/**
	 * Asigna el valor al campo motivoEPS.
	 * @param motivoEPS el valor motivoEPS a asignar
	 */
	public void setMotivoEPS(String motivoEPS) {
		this.motivoEPS = motivoEPS;
	}

	/**
	 * Método de acceso al campo descripcionEPS.
	 * @return El valor del campo descripcionEPS
	 */
	public String getDescripcionEPS() {
		return descripcionEPS;
	}

	/**
	 * Asigna el valor al campo descripcionEPS.
	 * @param descripcionEPS el valor descripcionEPS a asignar
	 */
	public void setDescripcionEPS(String descripcionEPS) {
		this.descripcionEPS = descripcionEPS;
	}

	/**
	 * Método de acceso al campo fechaInicioPermisoEPS.
	 * @return El valor del campo fechaInicioPermisoEPS
	 */
	public String getFechaInicioPermisoEPS() {
		return fechaInicioPermisoEPS;
	}

	/**
	 * Asigna el valor al campo fechaInicioPermisoEPS.
	 * @param fechaInicioPermisoEPS el valor fechaInicioPermisoEPS a asignar
	 */
	public void setFechaInicioPermisoEPS(String fechaInicioPermisoEPS) {
		this.fechaInicioPermisoEPS = fechaInicioPermisoEPS;
	}

	/**
	 * Método de acceso al campo fechaFinPermisoEPS.
	 * @return El valor del campo fechaFinPermisoEPS
	 */
	public String getFechaFinPermisoEPS() {
		return fechaFinPermisoEPS;
	}

	/**
	 * Asigna el valor al campo fechaFinPermisoEPS.
	 * @param fechaFinPermisoEPS el valor fechaFinPermisoEPS a asignar
	 */
	public void setFechaFinPermisoEPS(String fechaFinPermisoEPS) {
		this.fechaFinPermisoEPS = fechaFinPermisoEPS;
	}

	/**
	 * Método de acceso al campo horaSalidaEPS.
	 * @return El valor del campo horaSalidaEPS
	 */
	public String getHoraSalidaEPS() {
		return horaSalidaEPS;
	}

	/**
	 * Asigna el valor al campo horaSalidaEPS.
	 * @param horaSalidaEPS el valor horaSalidaEPS a asignar
	 */
	public void setHoraSalidaEPS(String horaSalidaEPS) {
		this.horaSalidaEPS = horaSalidaEPS;
	}

	/**
	 * Método de acceso al campo horaRegresoEPS.
	 * @return El valor del campo horaRegresoEPS
	 */
	public String getHoraRegresoEPS() {
		return horaRegresoEPS;
	}

	/**
	 * Asigna el valor al campo horaRegresoEPS.
	 * @param horaRegresoEPS el valor horaRegresoEPS a asignar
	 */
	public void setHoraRegresoEPS(String horaRegresoEPS) {
		this.horaRegresoEPS = horaRegresoEPS;
	}

	/**
	 * Método de acceso al campo observacionesEPS.
	 * @return El valor del campo observacionesEPS
	 */
	public String getObservacionesEPS() {
		return observacionesEPS;
	}

	/**
	 * Asigna el valor al campo observacionesEPS.
	 * @param observacionesEPS el valor observacionesEPS a asignar
	 */
	public void setObservacionesEPS(String observacionesEPS) {
		this.observacionesEPS = observacionesEPS;
	}

	/**
	 * Método de acceso al campo descicionEPS.
	 * @return El valor del campo descicionEPS
	 */
	public String getDescicionEPS() {
		return descicionEPS;
	}

	/**
	 * Asigna el valor al campo descicionEPS.
	 * @param descicionEPS el valor descicionEPS a asignar
	 */
	public void setDescicionEPS(String descicionEPS) {
		this.descicionEPS = descicionEPS;
	}

	/**
	 * Método de acceso al campo ceresoActualTS.
	 * @return El valor del campo ceresoActualTS
	 */
	public String getCeresoActualTS() {
		return ceresoActualTS;
	}

	/**
	 * Asigna el valor al campo ceresoActualTS.
	 * @param ceresoActualTS el valor ceresoActualTS a asignar
	 */
	public void setCeresoActualTS(String ceresoActualTS) {
		this.ceresoActualTS = ceresoActualTS;
	}

	/**
	 * Método de acceso al campo ceresoDestinoTS.
	 * @return El valor del campo ceresoDestinoTS
	 */
	public String getCeresoDestinoTS() {
		return ceresoDestinoTS;
	}

	/**
	 * Asigna el valor al campo ceresoDestinoTS.
	 * @param ceresoDestinoTS el valor ceresoDestinoTS a asignar
	 */
	public void setCeresoDestinoTS(String ceresoDestinoTS) {
		this.ceresoDestinoTS = ceresoDestinoTS;
	}

	/**
	 * Método de acceso al campo motivoTS.
	 * @return El valor del campo motivoTS
	 */
	public String getMotivoTS() {
		return motivoTS;
	}

	/**
	 * Asigna el valor al campo motivoTS.
	 * @param motivoTS el valor motivoTS a asignar
	 */
	public void setMotivoTS(String motivoTS) {
		this.motivoTS = motivoTS;
	}

	/**
	 * Método de acceso al campo observacionesTS.
	 * @return El valor del campo observacionesTS
	 */
	public String getObservacionesTS() {
		return observacionesTS;
	}

	/**
	 * Asigna el valor al campo observacionesTS.
	 * @param observacionesTS el valor observacionesTS a asignar
	 */
	public void setObservacionesTS(String observacionesTS) {
		this.observacionesTS = observacionesTS;
	}

	/**
	 * Método de acceso al campo decisionTS.
	 * @return El valor del campo decisionTS
	 */
	public String getDecisionTS() {
		return decisionTS;
	}

	/**
	 * Asigna el valor al campo decisionTS.
	 * @param decisionTS el valor decisionTS a asignar
	 */
	public void setDecisionTS(String decisionTS) {
		this.decisionTS = decisionTS;
	}

	/**
	 * Método de acceso al campo motivoFP.
	 * @return El valor del campo motivoFP
	 */
	public String getMotivoFP() {
		return motivoFP;
	}

	/**
	 * Asigna el valor al campo motivoFP.
	 * @param motivoFP el valor motivoFP a asignar
	 */
	public void setMotivoFP(String motivoFP) {
		this.motivoFP = motivoFP;
	}

	/**
	 * Método de acceso al campo observacionesFP.
	 * @return El valor del campo observacionesFP
	 */
	public String getObservacionesFP() {
		return observacionesFP;
	}

	/**
	 * Asigna el valor al campo observacionesFP.
	 * @param observacionesFP el valor observacionesFP a asignar
	 */
	public void setObservacionesFP(String observacionesFP) {
		this.observacionesFP = observacionesFP;
	}

	/**
	 * Método de acceso al campo fechaCumplimientoFP.
	 * @return El valor del campo fechaCumplimientoFP
	 */
	public String getFechaCumplimientoFP() {
		return fechaCumplimientoFP;
	}

	/**
	 * Asigna el valor al campo fechaCumplimientoFP.
	 * @param fechaCumplimientoFP el valor fechaCumplimientoFP a asignar
	 */
	public void setFechaCumplimientoFP(String fechaCumplimientoFP) {
		this.fechaCumplimientoFP = fechaCumplimientoFP;
	}

	/**
	 * Método de acceso al campo fechaAudienciaFP.
	 * @return El valor del campo fechaAudienciaFP
	 */
	public String getFechaAudienciaFP() {
		return fechaAudienciaFP;
	}

	/**
	 * Asigna el valor al campo fechaAudienciaFP.
	 * @param fechaAudienciaFP el valor fechaAudienciaFP a asignar
	 */
	public void setFechaAudienciaFP(String fechaAudienciaFP) {
		this.fechaAudienciaFP = fechaAudienciaFP;
	}

	/**
	 * Método de acceso al campo tribunalFP.
	 * @return El valor del campo tribunalFP
	 */
	public String getTribunalFP() {
		return tribunalFP;
	}

	/**
	 * Asigna el valor al campo tribunalFP.
	 * @param tribunalFP el valor tribunalFP a asignar
	 */
	public void setTribunalFP(String tribunalFP) {
		this.tribunalFP = tribunalFP;
	}

	/**
	 * Método de acceso al campo juezFP.
	 * @return El valor del campo juezFP
	 */
	public String getJuezFP() {
		return juezFP;
	}

	/**
	 * Asigna el valor al campo juezFP.
	 * @param juezFP el valor juezFP a asignar
	 */
	public void setJuezFP(String juezFP) {
		this.juezFP = juezFP;
	}

	/**
	 * Método de acceso al campo remitenteBPL.
	 * @return El valor del campo remitenteBPL
	 */
	public String getRemitenteBPL() {
		return remitenteBPL;
	}

	/**
	 * Asigna el valor al campo remitenteBPL.
	 * @param remitenteBPL el valor remitenteBPL a asignar
	 */
	public void setRemitenteBPL(String remitenteBPL) {
		this.remitenteBPL = remitenteBPL;
	}

	/**
	 * Método de acceso al campo causaBPL.
	 * @return El valor del campo causaBPL
	 */
	public String getCausaBPL() {
		return causaBPL;
	}

	/**
	 * Asigna el valor al campo causaBPL.
	 * @param causaBPL el valor causaBPL a asignar
	 */
	public void setCausaBPL(String causaBPL) {
		this.causaBPL = causaBPL;
	}

	/**
	 * Método de acceso al campo fechaInicioPenaBPL.
	 * @return El valor del campo fechaInicioPenaBPL
	 */
	public String getFechaInicioPenaBPL() {
		return fechaInicioPenaBPL;
	}

	/**
	 * Asigna el valor al campo fechaInicioPenaBPL.
	 * @param fechaInicioPenaBPL el valor fechaInicioPenaBPL a asignar
	 */
	public void setFechaInicioPenaBPL(String fechaInicioPenaBPL) {
		this.fechaInicioPenaBPL = fechaInicioPenaBPL;
	}

	/**
	 * Método de acceso al campo argumentosBPL.
	 * @return El valor del campo argumentosBPL
	 */
	public String getArgumentosBPL() {
		return argumentosBPL;
	}

	/**
	 * Asigna el valor al campo argumentosBPL.
	 * @param argumentosBPL el valor argumentosBPL a asignar
	 */
	public void setArgumentosBPL(String argumentosBPL) {
		this.argumentosBPL = argumentosBPL;
	}

	/**
	 * Método de acceso al campo fechaAudienciaBPL.
	 * @return El valor del campo fechaAudienciaBPL
	 */
	public String getFechaAudienciaBPL() {
		return fechaAudienciaBPL;
	}

	/**
	 * Asigna el valor al campo fechaAudienciaBPL.
	 * @param fechaAudienciaBPL el valor fechaAudienciaBPL a asignar
	 */
	public void setFechaAudienciaBPL(String fechaAudienciaBPL) {
		this.fechaAudienciaBPL = fechaAudienciaBPL;
	}

	/**
	 * Método de acceso al campo decisionBPL.
	 * @return El valor del campo decisionBPL
	 */
	public String getDecisionBPL() {
		return decisionBPL;
	}

	/**
	 * Asigna el valor al campo decisionBPL.
	 * @param decisionBPL el valor decisionBPL a asignar
	 */
	public void setDecisionBPL(String decisionBPL) {
		this.decisionBPL = decisionBPL;
	}

	/**
	 * Método de acceso al campo remitenteRRD.
	 * @return El valor del campo remitenteRRD
	 */
	public String getRemitenteRRD() {
		return remitenteRRD;
	}

	/**
	 * Asigna el valor al campo remitenteRRD.
	 * @param remitenteRRD el valor remitenteRRD a asignar
	 */
	public void setRemitenteRRD(String remitenteRRD) {
		this.remitenteRRD = remitenteRRD;
	}

	/**
	 * Método de acceso al campo motivoRRD.
	 * @return El valor del campo motivoRRD
	 */
	public String getMotivoRRD() {
		return motivoRRD;
	}

	/**
	 * Asigna el valor al campo motivoRRD.
	 * @param motivoRRD el valor motivoRRD a asignar
	 */
	public void setMotivoRRD(String motivoRRD) {
		this.motivoRRD = motivoRRD;
	}

	/**
	 * Método de acceso al campo fechaAudienciaRRD.
	 * @return El valor del campo fechaAudienciaRRD
	 */
	public String getFechaAudienciaRRD() {
		return fechaAudienciaRRD;
	}

	/**
	 * Asigna el valor al campo fechaAudienciaRRD.
	 * @param fechaAudienciaRRD el valor fechaAudienciaRRD a asignar
	 */
	public void setFechaAudienciaRRD(String fechaAudienciaRRD) {
		this.fechaAudienciaRRD = fechaAudienciaRRD;
	}

	/**
	 * Método de acceso al campo decisionRRD.
	 * @return El valor del campo decisionRRD
	 */
	public String getDecisionRRD() {
		return decisionRRD;
	}

	/**
	 * Asigna el valor al campo decisionRRD.
	 * @param decisionRRD el valor decisionRRD a asignar
	 */
	public void setDecisionRRD(String decisionRRD) {
		this.decisionRRD = decisionRRD;
	}

	/**
	 * Método de acceso al campo lstNiveles.
	 * @return El valor del campo lstNiveles
	 */
	public ArrayList getLstNiveles() {
		return lstNiveles;
	}

	/**
	 * Asigna el valor al campo lstNiveles.
	 * @param lstNiveles el valor lstNiveles a asignar
	 */
	public void setLstNiveles(ArrayList lstNiveles) {
		this.lstNiveles = lstNiveles;
	}

	/**
	 * Método de acceso al campo lstDesempenios.
	 * @return El valor del campo lstDesempenios
	 */
	public ArrayList getLstDesempenios() {
		return lstDesempenios;
	}

	/**
	 * Asigna el valor al campo lstDesempenios.
	 * @param lstDesempenios el valor lstDesempenios a asignar
	 */
	public void setLstDesempenios(ArrayList lstDesempenios) {
		this.lstDesempenios = lstDesempenios;
	}

	/**
	 * Método de acceso al campo lstCalificacionDesempenio.
	 * @return El valor del campo lstCalificacionDesempenio
	 */
	public ArrayList getLstCalificacionDesempenio() {
		return lstCalificacionDesempenio;
	}

	/**
	 * Asigna el valor al campo lstCalificacionDesempenio.
	 * @param lstCalificacionDesempenio el valor lstCalificacionDesempenio a asignar
	 */
	public void setLstCalificacionDesempenio(ArrayList lstCalificacionDesempenio) {
		this.lstCalificacionDesempenio = lstCalificacionDesempenio;
	}

	/**
	 * Método de acceso al campo lstTrabajoInterno.
	 * @return El valor del campo lstTrabajoInterno
	 */
	public ArrayList getLstTrabajoInterno() {
		return lstTrabajoInterno;
	}

	/**
	 * Asigna el valor al campo lstTrabajoInterno.
	 * @param lstTrabajoInterno el valor lstTrabajoInterno a asignar
	 */
	public void setLstTrabajoInterno(ArrayList lstTrabajoInterno) {
		this.lstTrabajoInterno = lstTrabajoInterno;
	}

	/**
	 * Método de acceso al campo lstAprobacion.
	 * @return El valor del campo lstAprobacion
	 */
	public ArrayList getLstAprobacion() {
		return lstAprobacion;
	}

	/**
	 * Asigna el valor al campo lstAprobacion.
	 * @param lstAprobacion el valor lstAprobacion a asignar
	 */
	public void setLstAprobacion(ArrayList lstAprobacion) {
		this.lstAprobacion = lstAprobacion;
	}

	/**
	 * Método de acceso al campo lstMotivosTMD.
	 * @return El valor del campo lstMotivosTMD
	 */
	public ArrayList getLstMotivosTMD() {
		return lstMotivosTMD;
	}

	/**
	 * Asigna el valor al campo lstMotivosTMD.
	 * @param lstMotivosTMD el valor lstMotivosTMD a asignar
	 */
	public void setLstMotivosTMD(ArrayList lstMotivosTMD) {
		this.lstMotivosTMD = lstMotivosTMD;
	}

	/**
	 * Método de acceso al campo lstTipoAnomaliasRAE.
	 * @return El valor del campo lstTipoAnomaliasRAE
	 */
	public ArrayList getLstTipoAnomaliasRAE() {
		return lstTipoAnomaliasRAE;
	}

	/**
	 * Asigna el valor al campo lstTipoAnomaliasRAE.
	 * @param lstTipoAnomaliasRAE el valor lstTipoAnomaliasRAE a asignar
	 */
	public void setLstTipoAnomaliasRAE(ArrayList lstTipoAnomaliasRAE) {
		this.lstTipoAnomaliasRAE = lstTipoAnomaliasRAE;
	}

	/**
	 * Método de acceso al campo lstMotivosEPS.
	 * @return El valor del campo lstMotivosEPS
	 */
	public ArrayList getLstMotivosEPS() {
		return lstMotivosEPS;
	}

	/**
	 * Asigna el valor al campo lstMotivosEPS.
	 * @param lstMotivosEPS el valor lstMotivosEPS a asignar
	 */
	public void setLstMotivosEPS(ArrayList lstMotivosEPS) {
		this.lstMotivosEPS = lstMotivosEPS;
	}

	/**
	 * Método de acceso al campo lstMotivosTS.
	 * @return El valor del campo lstMotivosTS
	 */
	public ArrayList getLstMotivosTS() {
		return lstMotivosTS;
	}

	/**
	 * Asigna el valor al campo lstMotivosTS.
	 * @param lstMotivosTS el valor lstMotivosTS a asignar
	 */
	public void setLstMotivosTS(ArrayList lstMotivosTS) {
		this.lstMotivosTS = lstMotivosTS;
	}

	/**
	 * Método de acceso al campo lstMotivosFP.
	 * @return El valor del campo lstMotivosFP
	 */
	public ArrayList getLstMotivosFP() {
		return lstMotivosFP;
	}

	/**
	 * Asigna el valor al campo lstMotivosFP.
	 * @param lstMotivosFP el valor lstMotivosFP a asignar
	 */
	public void setLstMotivosFP(ArrayList lstMotivosFP) {
		this.lstMotivosFP = lstMotivosFP;
	}

	/**
	 * Método de acceso al campo lstTribunales.
	 * @return El valor del campo lstTribunales
	 */
	public ArrayList getLstTribunales() {
		return lstTribunales;
	}

	/**
	 * Asigna el valor al campo lstTribunales.
	 * @param lstTribunales el valor lstTribunales a asignar
	 */
	public void setLstTribunales(ArrayList lstTribunales) {
		this.lstTribunales = lstTribunales;
	}

	/**
	 * Método de acceso al campo lstJuez.
	 * @return El valor del campo lstJuez
	 */
	public ArrayList getLstJuez() {
		return lstJuez;
	}

	/**
	 * Asigna el valor al campo lstJuez.
	 * @param lstJuez el valor lstJuez a asignar
	 */
	public void setLstJuez(ArrayList lstJuez) {
		this.lstJuez = lstJuez;
	}

	/**
	 * Método de acceso al campo lstCausasBPL.
	 * @return El valor del campo lstCausasBPL
	 */
	public ArrayList getLstCausasBPL() {
		return lstCausasBPL;
	}

	/**
	 * Asigna el valor al campo lstCausasBPL.
	 * @param lstCausasBPL el valor lstCausasBPL a asignar
	 */
	public void setLstCausasBPL(ArrayList lstCausasBPL) {
		this.lstCausasBPL = lstCausasBPL;
	}

	/**
	 * Método de acceso al campo nombreCatTrabajo.
	 * @return El valor del campo nombreCatTrabajo
	 */
	public String getNombreCatTrabajo() {
		return nombreCatTrabajo;
	}

	/**
	 * Método de acceso al campo lstCatTipoCursoDTO.
	 * @return El valor del campo lstCatTipoCursoDTO
	 */
	public List<CatTipoCursoDTO> getLstCatTipoCursoDTO() {
		return lstCatTipoCursoDTO;
	}

	/**
	 * Asigna el valor al campo nombreCatTrabajo.
	 * @param nombreCatTrabajo el valor nombreCatTrabajo a asignar
	 */
	public void setNombreCatTrabajo(String nombreCatTrabajo) {
		this.nombreCatTrabajo = nombreCatTrabajo;
	}

	/**
	 * Método de acceso al campo descripcionCatTrabajo.
	 * @return El valor del campo descripcionCatTrabajo
	 */
	public String getDescripcionCatTrabajo() {
		return descripcionCatTrabajo;
	}

	/**
	 * Asigna el valor al campo descripcionCatTrabajo.
	 * @param descripcionCatTrabajo el valor descripcionCatTrabajo a asignar
	 */
	public void setDescripcionCatTrabajo(String descripcionCatTrabajo) {
		this.descripcionCatTrabajo = descripcionCatTrabajo;
	}

	/**
	 * Método de acceso al campo besExternoCatTrabajo.
	 * @return El valor del campo besExternoCatTrabajo
	 */
	public Long getBesExternoCatTrabajo() {
		return besExternoCatTrabajo;
	}

	/**
	 * Asigna el valor al campo besExternoCatTrabajo.
	 * @param besExternoCatTrabajo el valor besExternoCatTrabajo a asignar
	 */
	public void setBesExternoCatTrabajo(Long besExternoCatTrabajo) {
		this.besExternoCatTrabajo = besExternoCatTrabajo;
	}

	/**
	 * Método de acceso al campo catTipoTrabajoExternoId.
	 * @return El valor del campo catTipoTrabajoExternoId
	 */
	public Long getCatTipoTrabajoExternoId() {
		return catTipoTrabajoExternoId;
	}

	/**
	 * Asigna el valor al campo catTipoTrabajoExternoId.
	 * @param catTipoTrabajoExternoId el valor catTipoTrabajoExternoId a asignar
	 */
	public void setCatTipoTrabajoExternoId(Long catTipoTrabajoExternoId) {
		this.catTipoTrabajoExternoId = catTipoTrabajoExternoId;
	}

	/**
	 * Método de acceso al campo lstCatTipoTrabajoExternoDTO.
	 * @return El valor del campo lstCatTipoTrabajoExternoDTO
	 */
	public List<CatTipoTrabajoExternoDTO> getLstCatTipoTrabajoExternoDTO() {
		return lstCatTipoTrabajoExternoDTO;
	}

	/**
	 * Asigna el valor al campo lstCatTipoTrabajoExternoDTO.
	 * @param lstCatTipoTrabajoExternoDTO el valor lstCatTipoTrabajoExternoDTO a asignar
	 */
	public void setLstCatTipoTrabajoExternoDTO(
			List<CatTipoTrabajoExternoDTO> lstCatTipoTrabajoExternoDTO) {
		this.lstCatTipoTrabajoExternoDTO = lstCatTipoTrabajoExternoDTO;
	}

	/**
	 * Método de acceso al campo numeroConvenioCatTrabajo.
	 * @return El valor del campo numeroConvenioCatTrabajo
	 */
	public String getNumeroConvenioCatTrabajo() {
		return numeroConvenioCatTrabajo;
	}

	/**
	 * Asigna el valor al campo numeroConvenioCatTrabajo.
	 * @param numeroConvenioCatTrabajo el valor numeroConvenioCatTrabajo a asignar
	 */
	public void setNumeroConvenioCatTrabajo(String numeroConvenioCatTrabajo) {
		this.numeroConvenioCatTrabajo = numeroConvenioCatTrabajo;
	}

	/**
	 * Método de acceso al campo puntosCatTrabajo.
	 * @return El valor del campo puntosCatTrabajo
	 */
	public Long getPuntosCatTrabajo() {
		return puntosCatTrabajo;
	}

	/**
	 * Asigna el valor al campo puntosCatTrabajo.
	 * @param puntosCatTrabajo el valor puntosCatTrabajo a asignar
	 */
	public void setPuntosCatTrabajo(Long puntosCatTrabajo) {
		this.puntosCatTrabajo = puntosCatTrabajo;
	}

	/**
	 * Asigna el valor al campo lstCatTipoCursoDTO.
	 * @param lstCatTipoCursoDTO el valor lstCatTipoCursoDTO a asignar
	 */
	public void setLstCatTipoCursoDTO(List<CatTipoCursoDTO> lstCatTipoCursoDTO) {
		this.lstCatTipoCursoDTO = lstCatTipoCursoDTO;
	}

	/**
	 * Método de acceso al campo tipoCursoId.
	 * @return El valor del campo tipoCursoId
	 */
	public long getTipoCursoId() {
		return tipoCursoId;
	}

	/**
	 * Asigna el valor al campo tipoCursoId.
	 * @param tipoCursoId el valor tipoCursoId a asignar
	 */
	public void setTipoCursoId(long tipoCursoId) {
		this.tipoCursoId = tipoCursoId;
	}

	/**
	 * Método de acceso al campo lstCatCategoriaCursoDTO.
	 * @return El valor del campo lstCatCategoriaCursoDTO
	 */
	public List<CatCategoriaCursoDTO> getLstCatCategoriaCursoDTO() {
		return lstCatCategoriaCursoDTO;
	}

	/**
	 * Asigna el valor al campo lstCatCategoriaCursoDTO.
	 * @param lstCatCategoriaCursoDTO el valor lstCatCategoriaCursoDTO a asignar
	 */
	public void setLstCatCategoriaCursoDTO(
			List<CatCategoriaCursoDTO> lstCatCategoriaCursoDTO) {
		this.lstCatCategoriaCursoDTO = lstCatCategoriaCursoDTO;
	}

	/**
	 * Método de acceso al campo adminCursoCategoriaCursoId.
	 * @return El valor del campo adminCursoCategoriaCursoId
	 */
	public long getAdminCursoCategoriaCursoId() {
		return adminCursoCategoriaCursoId;
	}

	/**
	 * Asigna el valor al campo adminCursoCategoriaCursoId.
	 * @param adminCursoCategoriaCursoId el valor adminCursoCategoriaCursoId a asignar
	 */
	public void setAdminCursoCategoriaCursoId(long adminCursoCategoriaCursoId) {
		this.adminCursoCategoriaCursoId = adminCursoCategoriaCursoId;
	}

	/**
	 * Método de acceso al campo lstCatTipoNivelAcademicoDTO.
	 * @return El valor del campo lstCatTipoNivelAcademicoDTO
	 */
	public List<CatTipoNivelAcademicoDTO> getLstCatTipoNivelAcademicoDTO() {
		return lstCatTipoNivelAcademicoDTO;
	}

	/**
	 * Asigna el valor al campo lstCatTipoNivelAcademicoDTO.
	 * @param lstCatTipoNivelAcademicoDTO el valor lstCatTipoNivelAcademicoDTO a asignar
	 */
	public void setLstCatTipoNivelAcademicoDTO(
			List<CatTipoNivelAcademicoDTO> lstCatTipoNivelAcademicoDTO) {
		this.lstCatTipoNivelAcademicoDTO = lstCatTipoNivelAcademicoDTO;
	}

	/**
	 * Método de acceso al campo tipoNivelAcademicoId.
	 * @return El valor del campo tipoNivelAcademicoId
	 */
	public long getTipoNivelAcademicoId() {
		return tipoNivelAcademicoId;
	}

	/**
	 * Asigna el valor al campo tipoNivelAcademicoId.
	 * @param tipoNivelAcademicoId el valor tipoNivelAcademicoId a asignar
	 */
	public void setTipoNivelAcademicoId(long tipoNivelAcademicoId) {
		this.tipoNivelAcademicoId = tipoNivelAcademicoId;
	}

	/**
	 * Método de acceso al campo nombreCurso.
	 * @return El valor del campo nombreCurso
	 */
	public String getNombreCurso() {
		return nombreCurso;
	}

	/**
	 * Asigna el valor al campo nombreCurso.
	 * @param nombreCurso el valor nombreCurso a asignar
	 */
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	/**
	 * Método de acceso al campo descCurso.
	 * @return El valor del campo descCurso
	 */
	public String getDescCurso() {
		return descCurso;
	}

	/**
	 * Asigna el valor al campo descCurso.
	 * @param descCurso el valor descCurso a asignar
	 */
	public void setDescCurso(String descCurso) {
		this.descCurso = descCurso;
	}

	/**
	 * Método de acceso al campo duracionCurso.
	 * @return El valor del campo duracionCurso
	 */
	public String getDuracionCurso() {
		return duracionCurso;
	}

	/**
	 * Asigna el valor al campo duracionCurso.
	 * @param duracionCurso el valor duracionCurso a asignar
	 */
	public void setDuracionCurso(String duracionCurso) {
		this.duracionCurso = duracionCurso;
	}

	/**
	 * Método de acceso al campo puntosCurso.
	 * @return El valor del campo puntosCurso
	 */
	public String getPuntosCurso() {
		return puntosCurso;
	}

	/**
	 * Asigna el valor al campo puntosCurso.
	 * @param puntosCurso el valor puntosCurso a asignar
	 */
	public void setPuntosCurso(String puntosCurso) {
		this.puntosCurso = puntosCurso;
	}

	/**
	 * @return the idCatTrabajo
	 */
	public Long getIdCatTrabajo() {
		return idCatTrabajo;
	}

	/**
	 * @param idCatTrabajo the idCatTrabajo to set
	 */
	public void setIdCatTrabajo(Long idCatTrabajo) {
		this.idCatTrabajo = idCatTrabajo;
	}

	/**
	 * @return the idCurso
	 */
	public String getIdCurso() {
		return idCurso;
	}

	/**
	 * @param idCurso the idCurso to set
	 */
	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}

}