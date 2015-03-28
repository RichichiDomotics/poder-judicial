/**
* Nombre del Programa 			: ConsultarCatalogosAction.java
* Autor                         : AlejandroGA
* Compania                    	: Ultrasist
* Proyecto                      : NSJP                    Fecha: 28 Apr 2011
* Marca de cambio        		: N/A
* Descripcion General    		: Clase encargada de consultar todos los catalogos para objetos
* Programa Dependiente  		:N/A
* Programa Subsecuente 			:N/A
* Cond. de ejecucion        	:N/A
* Dias de ejecucion          	:N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       	:N/A
* Compania               		:N/A
* Proyecto                		:N/A                                 Fecha: N/A
* Modificacion          		:N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.catalogo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.enums.catalogo.TipoDiscriminante;
import mx.gob.segob.nsjp.comun.enums.funcionario.TipoEspecialidad;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.seguridad.Roles;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.audiencia.AudienciaDelegate;
import mx.gob.segob.nsjp.delegate.catalogo.CatDiscriminanteDelegate;
import mx.gob.segob.nsjp.delegate.catalogo.CatUIEspecializadaDelegate;
import mx.gob.segob.nsjp.delegate.catalogo.DistritoDelegate;
import mx.gob.segob.nsjp.delegate.configuracion.ConfiguracionDelegate;
import mx.gob.segob.nsjp.delegate.eventocita.EventoCitaDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.delegate.modulo.ModuloDelegate;
import mx.gob.segob.nsjp.delegate.rol.RolDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.dto.rol.FiltroRolesDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.usuario.ModuloDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.objeto.form.AeronaveForm;
import mx.gob.segob.nsjp.web.objeto.form.EmbarcacionForm;
import mx.gob.segob.nsjp.web.objeto.form.VehiculoForm;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Action encargada de consultar todos los catalogos.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class ConsultarCatalogosAction extends GenericAction{
	
	/** Log de clase */
	private static final Logger log = Logger
			.getLogger(ConsultarCatalogosAction.class);
	@Autowired
	private SolicitudPericialDelegate solicitudPericialDelegate;
	
	@Autowired
	private AudienciaDelegate audienciaDelegate;
	
	@Autowired
	private EventoCitaDelegate eventoCitaDelegate;
	
	@Autowired
	private DistritoDelegate distritoDelegate;
	
	@Autowired
	private FuncionarioDelegate funcionarioDelegate;
	
	@Autowired
	private CatDiscriminanteDelegate catDiscriminanteDelegate;
	
	@Autowired
	private CatUIEspecializadaDelegate catUIEspecializadaDelegate; 
	
	@Autowired
	private RolDelegate rolDelegate;
	
	@Autowired
	private ModuloDelegate moduloDelegate;
	
	@Autowired
	private ConfiguracionDelegate configDelegate;
	
	/**
	 * Método utilizado para realizar la consulta de tipos de objetos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposObjeto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de objetos");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.TIPO_OBJETO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoObjetos", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			//super.escribirRespuesta(response, xml);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * M?todo utilizado para realizar la consulta de tipos de vehiculos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposVehiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de vehiculo");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.TIPO_VEHICULO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposVehiculo", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			//super.escribirRespuesta(response, xml);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de marcas de vehiculos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarMarcasVehiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar marcas de vehiculo");
			
			VehiculoForm forma =(VehiculoForm) form;
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogoDependiente(Catalogos.MARCA_VEHICULO,forma.getGlTipoVehiculoId());
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catMarcasVehiculo", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de marcas de vehiculos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarSubMarcasVehiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			VehiculoForm forma =(VehiculoForm) form;
			
			log.info("ejecutando Action consultar tipo de vehiculo");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogoDependiente(Catalogos.SUBMARCA_VEHICULO,forma.getGlMarcaVehiculoId());
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catSubMarcasVehiculo", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de colores
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarColores(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR COLORES");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.COLOR);		
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catColores", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de colores
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCondicion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR CONDICION");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.CONDICION_FISICA_OBJETO);		
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catCondicion", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de tipos de empaque
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposEmpaque(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR TIPO DE EMPAQUE");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_EMPAQUE);		
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catEmpaque", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de unidades de medida
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarUnidadesMedida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR TIPO DE MEDIDA");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.UNIDAD_MEDIDA);		
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catUnidadMedida", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de tipos de vehiculos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposEquipoComputo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de equipo de computo");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.TIPO_EQUIPO_COMPUTO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposEquipoComputo", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de marcas de equipo de computo
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarMarcasEquipoComputo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar marca equipo de computo");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.MARCA_EQUIPO_COMPUTO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catMarcasEquipoComputo", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de tipos equipos telefonicos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposEquipoTel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de equipo telefonico");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.TIPO_EQUIPO_TELEFONICO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposEquipoTel", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	
	/**
	 * Método utilizado para realizar la consulta de marcas de equipos telefonicos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarMarcasEquipoTel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar marca equipo telefonico");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.MARCA_TELEFONO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catMarcasEquipoTel", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de tipos arma
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposArma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de arma");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.TIPO_ARMA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposArma", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	
	/**
	 * Método utilizado para realizar la consulta de marcas de equipos telefonicos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarMarcasArma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar marca de la arma");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.MARCA_ARMA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catMarcasArma", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de tipos explosivo
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposExplosivo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de explosivo");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.TIPO_EXPLOSIVO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposExplosivo", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de tipos sustancia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposSustancia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de sustancia");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.TIPO_SUSTANCIA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposSustancia", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
		

	/**
	 * Método utilizado para realizar la consulta de tipos de animal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposAnimal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de animal");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.TIPO_ANIMAL);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposAnimal", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de tipos de aeronave
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposAeronave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo aeronave");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.TIPO_AERONAVE);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposAeronave", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de marcas de vehiculos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarMarcasAeronave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar marca de aeronave");
			
			AeronaveForm forma =(AeronaveForm) form;
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogoDependiente(Catalogos.MARCA_AERONAVE,forma.getGlTipoAeronaveId());
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catMarcasAeronave", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de sub marcas de aeronave
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarSubMarcasAeronave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar sub marca de aeronave");
			
			AeronaveForm forma =(AeronaveForm) form;		
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogoDependiente(Catalogos.SUBMARCA_AERONAVE,forma.getGlMarcaAeronaveId());
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catSubMarcasAeronave", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de tipos de embarcacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposEmbarcacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo embarcacion");
			
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.TIPO_EMBARCACION);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposEmbarcacion", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de marcas de embarcacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarMarcasEmbarcacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar marca de embarcacion");
			
			EmbarcacionForm forma =(EmbarcacionForm) form;
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogoDependiente(Catalogos.MARCA_EMBARCACION,forma.getGlTipoEmbarcacionId());
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catMarcasEmbarcacion", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de sub marcas de la embarcacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarSubMarcasEmbarcacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar sub marca de embarcacion");
			
			EmbarcacionForm forma =(EmbarcacionForm) form;	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogoDependiente(Catalogos.SUBMARCA_EMBARCACION,forma.getGlMarcaEmbarcacionId());
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catSubMarcasEmbarcacion", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de tipos de inmueble
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposInmueble(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de inmueble");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_INMUEBLE);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposInmueble", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de tipos de construccion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposConstruccion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de construccion");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_CONSTRUCCION);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposConstruccion", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de tipos de vegetal
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposVegetal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de vegetal");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_VEGETAL);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposVegetal", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de tipos de documento oficial
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposDocOficial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de documento oficial");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_DOCUMENTO_OFICIAL);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposDocOficial", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de tipos de Joya
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposJoya(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de joya");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_JOYA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposJoya", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de tipos de obra de arte
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposObraArte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo de obra de arte");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_OBRA_ARTE);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTiposObraArte", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * Método utilizado para realizar la consulta de instituciones
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoInstituciones(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos Instituciones");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.INSTITUCION_CON_NSJP);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("instituciones", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * Método utilizado para realizar la consulta de areas
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoAreas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos de areas");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.AREA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("areas", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Método utilizado para realizar la consulta de puestos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoUIE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos de Unidades Investigacion Especializadas");	
			
			List<CatUIEspecializadaDTO> listaCatalogoUIE = catUIEspecializadaDelegate.consultarUnidadesIEspecializadas();
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("CatUIEspecializadaDTO", CatUIEspecializadaDTO.class);
			String xml = converter.toXML(listaCatalogoUIE);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * M?todo utilizado para realizar la consulta de puestos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoPuestos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos de puestos");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.PUESTO_SERVIDOR_PUBLICO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("puestos", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de tipo especialidad
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoTipoEspecialidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos de tipo especialidad");
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_ESPECIALIDAD_FUNCIONARIO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("tipoEspecialidad", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de funcionarios
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoFuncionarios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.debug("ejecutando Action consultar funcionarios");	
			
			FuncionarioDTO funcionario = new FuncionarioDTO();
			List<FuncionarioDTO> listaFuncionarios = solicitudPericialDelegate.consultarFuncionarioPorFiltro(funcionario);
			
			converter.alias("listaFuncionarios", java.util.List.class);
			converter.aliasAttribute("funcionarioJefe", "funcionarioJefe");
			String xml = converter.toXML(listaFuncionarios);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de roles
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoRoles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.debug("ejecutando Action consultar roles2 luigitel");
			final ConfInstitucionDTO ci = this.configDelegate.consultarInstitucionActual();
			List<RolDTO> lstRoles = rolDelegate.consultarRoles(new FiltroRolesDTO(ci.getConfInstitucionId()));
			//--- Grid XML ---//
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
			final PaginacionDTO pag = PaginacionThreadHolder.get();
			if (pag != null && pag.getTotalRegistros() != null) {
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }
			for (RolDTO rolDTO : lstRoles) {
				writer.print("<row id='"+rolDTO.getRolId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+rolDTO.getRolId()+" </div]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+rolDTO.getNombreRol()+" </div]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+rolDTO.getDescripcionRol()+" </div]]></cell>");
				writer.print("</row>");	
			
			}
			
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
			//--- FIN GRID XML ---//
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de roles
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoModulos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			String rolId = (String) request.getParameter("idRol");
			RolDTO rolDTO = rolDelegate.consultarRol(new RolDTO(Long.valueOf(rolId)));
			List<ModuloDTO> modulos = rolDTO.getModulos();
			List<ModuloDTO> catModulos = moduloDelegate.consultarModulos();
			for (int i=0;i<modulos.size();i++)
			{
				int j=0;
				while (catModulos.get(j).getModuloId()!=modulos.get(i).getModuloId() && j<catModulos.size()){
					j++;
				}
				if (j<catModulos.size()){
					catModulos.get(j).setEsSeleccionado(true);
				}
			}
			
			log.debug("ejecutando Action consultar modulos luigitel " + rolId);
			//--- Grid XML ---//
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
			final PaginacionDTO pag = PaginacionThreadHolder.get();
			if (pag != null && pag.getTotalRegistros() != null) {
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }
			
			for (ModuloDTO moduloDTO : catModulos) {
				writer.print("<row id='"+moduloDTO.getModuloId()+"'>");
				
				//writer.print("<cell><![CDATA[<div><input type='checkbox' name='idMod' checked='checked' id='"+moduloDTO.getModuloId()+"'></div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" +moduloDTO.getModuloId()+" </div>]]></cell>");
				//writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+moduloDTO.getModuloId()+"> </div>]]></cell>");
				
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+moduloDTO.getNombreModulo()+" </div]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+moduloDTO.getDescripcionModulo()+" </div]]></cell>");
				writer.print("<cell>" +moduloDTO.isEsSeleccionado()+"</cell>");
				writer.print("</row>");	
			
			}
			request.setAttribute("error", 0);
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
			//--- FIN GRID XML ---//
			
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	
	/**
	 * Método utilizado para realizar la consulta de especialidades por tipo de especialidad
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoEspecialidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar especialidad");
			String tipoEspecialidad = request.getParameter("tipoEspecialidadId");
			log.info("ejecutando Action consultar especialidad tipoEspecialidad - "+tipoEspecialidad);
			
			List<CatalogoDTO> listaCatalogoEspecialidades = catDelegate
					.recuperarCatalogoDependiente(Catalogos.ESPECIALIDAD_FUNCIONARIO,
							Long.parseLong(tipoEspecialidad));
			converter.alias("listaCatalogoEspecialidades", java.util.List.class);
			converter.alias("especialidad", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogoEspecialidades);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de estudios por especialidad
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoEstudiosXEspecialidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar Estuduis por especialidad");
			String especialidadId = request.getParameter("especialidadId");
			log.info("ejecutando Action consultar Estudio idEspecialidad - "+especialidadId);
			
			List<CatalogoDTO> listaCatalogoEspecialidades = catDelegate
					.recuperarCatalogoDependiente(Catalogos.ESTUDIO_PERICIAL,
							Long.parseLong(especialidadId));
			converter.alias("listaCatalogoEstudios", java.util.List.class);
			converter.alias("estudio", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogoEspecialidades);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de Estudios por tipo de especialidad
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	
	

	/**
	 * Método utilizado para realizar la consulta de departamentos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoDepartamentos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos departamentos");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.DEPARTAMENTO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("departamentos", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de departamentos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarInstitucionesExternas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos departamentos");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.INSTITUCION);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("departamentos", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);

			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de departamentos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarUnidadesEspecializadas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos departamentos");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_OBRA_ARTE);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("departamentos", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * Método utilizado para realizar la consulta de tipos de solicitudes
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTipoSolicitud(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipo solicitud");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_SOLICITUD);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("tipoSolicitud", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * Método utilizado para realizar la consulta de Instituciones
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarInstitucion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar Institucion");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.INSTITUCION_CON_NSJP);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("institucion", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * Método utilizado para realizar la consulta de tipos de audiencia
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoTipoAudiencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar Catalogo");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_AUDIENCIA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("institucion", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de tipos de audiencia
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoTipoAudienciaValidacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("EJECUTANDO ACTION COLSULTAR CATALOGO TIPO AUDIENCIA CON VALIDACION:::::::::::::::::::::::::::");
			
			Long tipoAudienciaActual = NumberUtils.toLong(request.getParameter("tipoAudiencia"),0L);
			log.info("TIPO AUDIENCIA ACTUAL:::::::::::::::::::::::::::"+tipoAudienciaActual);
			
			if( tipoAudienciaActual > 0){
			 
				List<CatalogoDTO> listaCatalogo = audienciaDelegate.consultarTipoSolicitudAudienciaSiguientes(TipoAudiencia.getByValor(tipoAudienciaActual));
				converter.alias("listaCatalogo", java.util.List.class);
				converter.alias("institucion", CatalogoDTO.class);
				String xml = converter.toXML(listaCatalogo);
				escribirRespuesta(response, xml);
			}
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de tipos de audiencia
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoTipoAudienciaGrid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar Catalogo tipo audiencia Grid");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_AUDIENCIA);
			log.info("Catalogo tipo solicitud"+listaCatalogo);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");
			
			int lTotalRegistros=listaCatalogo.size();
			
			writer.print("<records>" + lTotalRegistros + "</records>");
			for (CatalogoDTO catalogoDTO : listaCatalogo) {
			
			log.info("Solicitud"+catalogoDTO);
			
			writer.print("<row id='" + catalogoDTO.getClave()+ "'>");
			writer.print("<cell>" + catalogoDTO.getValor()+ "</cell>");
			writer.print("</row>");
			}			
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * Método utilizado para realizar la consulta de normas
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarNormas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar Normas");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_NORMA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("norma", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Método utilizado para realizar la consulta de medidas cautelares
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoMedidasCautelares(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar Catalogo MedidasCautelares");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_MEDIDA_CAUTELAR);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("medidasCautelares", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Método utilizado para realizar la consulta de encargados de seguimiento
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoEncargadosSeguimiento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar Catalogo Encargados Seguimiento");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_MEDIDA_CAUTELAR);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("encargadoSeguimiento", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Método utilizado para realizar la consulta de modos de participacion en delito
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarModoParticipacionDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar modo de participacion de delito");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.MODO_PARTICIPACION_DELITO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("ModoParticipacionDelito", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * M?todo utilizado para realizar la consulta de clasificacion en delito
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarClasificacionDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar Clasificacion de delito");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.CLASIFICACION_DELITO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("ModoParticipacionDelito", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * M?todo utilizado para realizar la consulta del Lugar en delito
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarLugarDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar Lugar de delito");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.LUGAR_DELITO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("ModoParticipacionDelito", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * M?todo utilizado para realizar la consulta de modalidad en delito
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarModalidadDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar Modalidad de delito");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.MODALIDAD_DELITO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("ModoParticipacionDelito", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * M?todo utilizado para realizar la consulta de modus en delito
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarModusDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar Modalidad de delito");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.MODUS_DELITO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("ModoParticipacionDelito", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * M?todo utilizado para realizar la consulta de la causa en delito
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCausaDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar Modalidad de delito");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.CAUSA_DELITO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("ModoParticipacionDelito", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar el tipo de especialidad pericial
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarCatalogoEspecialidadPericial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar modo de participacion de delito");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogoDependiente(Catalogos.ESPECIALIDAD_FUNCIONARIO,TipoEspecialidad.PERICIAL.getValorId());
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catEspecialidadPericial", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}	
	

	
	/**
	 * Metodo utilizado para consultar los tipos de diligencia
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarCatalogoTipoDiligencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar catalogo tipo de diligencia");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_DILIGENCIA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("tipoDiligencia", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar los tipos de diligencia
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarCatalogoTipoTarea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar catalogo tipo Tarea");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_TAREA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("tarea", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	

	/**
	 * Metodo utilizado para consultar las etapas del expediente
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarCatalogoEtapaExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar catalogo tipo de diligencia");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.ETAPA_EXPEDIENTE);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("etapaExpediente", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public ActionForward consultarCatalogoSituacionJuridica(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar catalogo situacion juridica");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.SITUACION_JURIDICA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("situacionJuridica", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}


	/**
	 * Metodo utilizado para consultar los tipos de formas
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarCatalogoTiposFormas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar catalogo tipos de forma");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPOS_FORMAS);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catDocumentos", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar los tipos de eslabon
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarCatalogoTiposEslabon(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultar catalogo tipos de eslabon");			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_ESLABON);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catEslabon", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de tipo participacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoTipoParticipacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultarCatalogoTipoParticipacion");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_PARTICIPACION);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("tipoParticipacion", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de tipo de mandamiento
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoTipoMandamiento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultaCatalogTipoMandamiento");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_MANDAMIENTO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("tipoMandamiento", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de tipo de mandamiento
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoTipoSentencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar Catalogo TipoSentencia");	
			
			List<CatalogoDTO> listaCatalogo =  catDelegate.recuperarCatalogo(Catalogos.TIPO_SENTENCIA);
//			List<CatalogoDTO> listaCatalogo =  catDelegate.recuperarCatalogo(TipoSentencia.ABSOLUTORIA.getCampoCatalogoId());			
			converter.alias("listaTipoSentencia", java.util.List.class);
			converter.alias("tipoSentencia", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
			
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de estatus de mandamiento
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author Emigdio
	 */
	public ActionForward consultarCatalogoEstatusMandamiento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultarCatalogoEstatusMandamiento");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.ESTATUS_MANDAMIENTO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("estatusMandamiento", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Método utilizado para realizar la consulta de colores
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarRelacionesHecho(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR RELACIONES HECHOS");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.RELACION_HECHOS);		
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catRelacionesHechos", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	

	/**
	 * Método utilizado para realizar la consulta de PERIODICIDAD
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoPeriodicidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR RELACIONES HECHOS");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.PERIODICIDAD);		
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catPeriodicidad", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	
	/**
	 * Método utilizado para realizar la consulta de medidas cautelares
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoMedidasAlternativa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar Catalogo MedidasCautelares");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.MEDIDA_ALTERNA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("medidasAlternativas", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			escribir(response, "", null);
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de funcionarios
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoFuncionariosPorTipoEspecialidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.debug("ejecutando Action consultar funcionarios por tipo de especialidad");	
			
			Long tipoEspecialidad = NumberUtils.toLong(request.getParameter("tipoEspecialidad"),0);
			
			log.debug("TIPO DE ESPECIALIDAD:::::::"+tipoEspecialidad);	
			FuncionarioDTO funcionario = new FuncionarioDTO();
			
			ValorDTO valorGenerico = new ValorDTO();
			if(tipoEspecialidad<1){
				tipoEspecialidad = null;
			}
			valorGenerico.setIdCampo(tipoEspecialidad);
			funcionario.setTipoEspecialidad(valorGenerico);			
					
			List<FuncionarioDTO> listaFuncionarios = solicitudPericialDelegate.consultarFuncionarioPorFiltro(funcionario);
			
			converter.alias("listaFuncionarios", java.util.List.class);
			converter.alias("funcionario", FuncionarioDTO.class);
			String xml = converter.toXML(listaFuncionarios);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Método utilizado para realizar la consulta de calidades
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoCalidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos de calidades");	
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.CALIDAD);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("calidades", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de areas de forma dependiente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoAreasDependiente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			String confInstId = request.getParameter("idInstitucion");
			
			List<CatalogoDTO> listaAreaCatalogo = null;
			
			log.info("ejecutando Action consultar catalogos de areas "+this.getUsuarioFirmado(request).getInstitucion().getConfInstitucionId());
			if (confInstId != null && !confInstId.isEmpty()) {
				Long confInstitucionId = Long.parseLong(confInstId);
				listaAreaCatalogo = catDelegate.recuperarCatalogoDependiente(Catalogos.AREA, confInstitucionId);
			} else {
				listaAreaCatalogo = catDelegate.recuperarCatalogoDependiente(Catalogos.AREA, this.getUsuarioFirmado(request).getInstitucion().getConfInstitucionId());
			}			
			
			List<CatalogoDTO> listaDepartamentoCatalogo = new ArrayList<CatalogoDTO>();
			for (CatalogoDTO areaDTO : listaAreaCatalogo) {
				listaDepartamentoCatalogo.addAll(catDelegate.recuperarCatalogoDependiente(Catalogos.DEPARTAMENTO, areaDTO.getClave()));
			}
			List<CatalogoDTO> listaCatalogo = new ArrayList<CatalogoDTO>();
			listaCatalogo.addAll(listaAreaCatalogo);
			listaCatalogo.addAll(listaDepartamentoCatalogo);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("areas", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de departamentos de forma dependiente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoDepartamentosDependiente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos de areas");	
			String idArea = request.getParameter("idArea");
			
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogoDependiente(Catalogos.DEPARTAMENTO, Long.parseLong(idArea));
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("departamentos", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de tipos de asesoria
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward cargarCatalogoTipoAsesoria(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos de tipos de asesoria");	
						
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_ASESORIA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("tipoAsesoria", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de tipos de nivel socioeconómico
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward cargarCatalogoNivelSocioeconomico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos de nivel socioeconómico");	
						
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogoCompleto(Catalogos.NIVEL_SOCIOECONOMICO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("nivel", CatalogoDTO.class);
			converter.alias("cuota", ValorDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
		
	/**
	 * Método utilizado para realizar la consulta de tipos de centros de dentencion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward cargarTipoLugarDetencion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos de tipos de centros de detencion");	
						
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogoCompleto(Catalogos.TIPO_CENTRO_DETENCION);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoLugarDetencion", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para consultar lineas de investigacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarLineasDeInvestigacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar lineas de investigacion");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.LINEAS_INVESTIGACION);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catLineasDeInvestigacion", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Método utilizado para consultar Tipos de Pertenencia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposDePertenencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar tipos de pertenencia");
			List<CatalogoDTO> listaCatalogo = catDelegate
					.recuperarCatalogo(Catalogos.TIPO_PERTENENCIA);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoDePertenencia", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar las actividades del usuario por fecha
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarActividadesUsuarioPorFechaActual(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("EJECUTADO ACTION CONSULTAR ACTIVIDADES USUARIO POR FECHA ACTUAL");

			UsuarioDTO usuario = getUsuarioFirmado(request);
					
			
			 //Obtener la fecha actual del sistema.
			Calendar c = Calendar.getInstance();
			//Colocar en cero las horas, minutos y segundos.
			DateUtils.setHoraMinutoSegundoCero(c);
			//Obtener como Date
			Date fechaInicio= c.getTime();

			//Sumar un dia
			DateUtils.sumarDias(c, 1);
			//Obtener como Date
			Date fechaFin = c.getTime();

			
			List<EventoCitaDTO> listaEventos = eventoCitaDelegate.consultarEventosCitasPorUsuario(usuario, fechaInicio, fechaFin);

			converter.alias("lista", java.util.List.class);
			converter.alias("actividad", EventoCitaDTO.class);
			String xml = converter.toXML(listaEventos);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para consultar Tipos de agencias
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoAgenciasPGJ(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("EJECUTANDO ACTION CONSULTAR CAT DISCRIMINANTE");
			String tipoDiscriminante = request.getParameter("tipoDiscriminante");
			log.info("TIPO DISCRIMINANTE SOLICITADO="+tipoDiscriminante);
			
			List<CatDiscriminanteDTO> listaCatalogo = new ArrayList<CatDiscriminanteDTO>();
			
			if(tipoDiscriminante.equalsIgnoreCase("agencia")){
				listaCatalogo = distritoDelegate.consultarDiscriminantes(TipoDiscriminante.AGENCIA);
			}
				
			if(tipoDiscriminante.equalsIgnoreCase("tribunal")){
				listaCatalogo = distritoDelegate.consultarDiscriminantes(TipoDiscriminante.TRIBUNAL);
			}
			
			if(tipoDiscriminante.equalsIgnoreCase("fantasma")){
				listaCatalogo = distritoDelegate.consultarDiscriminantes(TipoDiscriminante.FANTASMA);
			}
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catDiscriminante", CatDiscriminanteDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para consultar discriminantes por distrito
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarDiscriminantesXDistrito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("EJECUTANDO ACTION CONSULTAR DISCRIMINANTES X DISTRITO");
			
			Long distritoId = NumberUtils.toLong(request.getParameter("distritoId"), 0L);
			log.info("LLEGA distritoId="+distritoId);
			
			List<CatDiscriminanteDTO> listaCatalogo = new ArrayList<CatDiscriminanteDTO>();
			
			if(distritoId > 0L){
				listaCatalogo = catDiscriminanteDelegate.consultarTribunalesPorDistrito(distritoId,Instituciones.PJ);
			}			
			converter.alias("listaDiscriminantes", java.util.List.class);
			converter.alias("catDiscriminante", CatDiscriminanteDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * M?todo utilizado para consultar los tribunales de un distrito
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarDiscriminantesXDistritoSinWS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

			log.info("EJECUTANDO ACTION CONSULTAR AGENCIAS DE DISTRITOS");		
			try {
				
				Long distritoId = NumberUtils.toLong(request.getParameter("distritoId"), 0L);
				
				List<CatDiscriminanteDTO> listaCatalogo = distritoDelegate
						.consultarDiscriminantesXDistrito(distritoId,TipoDiscriminante.TRIBUNAL);
				
				converter.alias("CatDiscriminanteDTO", java.util.List.class);
				converter.alias("catDiscriminanteDTO", CatDiscriminanteDTO.class);
				String xml = converter.toXML(listaCatalogo);
				response.setContentType("text/xml");
				PrintWriter pw = response.getWriter();
				pw.print(xml);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			return null;
	}
	
	/**
	 * Método utilizado para consultar los tribunales de un distrito
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarDiscriminantesXDistritoSinWSInstitucion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			
			log.info("EJECUTANDO ACTION CONSULTAR AGENCIAS DE DISTRITOS");		
			try {
				
				Long distritoId = NumberUtils.toLong(request.getParameter("distritoId"), 0L);
				
				
			List<CatDiscriminanteDTO> listaCatalogo = distritoDelegate
					.consultarDiscriminantesXDistritoYTipoInstitucion(distritoId);
				
				converter.alias("CatDiscriminanteDTO", java.util.List.class);
				converter.alias("catDiscriminanteDTO", CatDiscriminanteDTO.class);
				String xml = converter.toXML(listaCatalogo);
				response.setContentType("text/xml");
				PrintWriter pw = response.getWriter();
				pw.print(xml);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			return null;
	}
			
	/**
	 * Método utilizado para consultar los funcionarios destinatarios x tribunalId
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarDestinararioXTribunalSinWS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

		
        try {
        	Long catDiscriminanteId = NumberUtils.toLong(request.getParameter("tribunal"),0L);
        	Long idRol = Roles.ENCARGADOSALA.getValorId();        
        	        	
        	List<FuncionarioDTO> funcionariosDTO=funcionarioDelegate.consultarFuncionariosPorDicriminanteYRol(catDiscriminanteId, idRol, 0L);
        	
        	converter.alias("funcionariosDTO", java.util.List.class);
        	converter.alias("funcionarioDTO", FuncionarioDTO.class);
			
			String xml = converter.toXML(funcionariosDTO);
			log.info("funcionarios xml :::" + xml);
			
			response.setContentType("text/xml");
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();	               	
        	
        } catch (Exception e) {
            log.error(e.getMessage(), e);               	        	
        }   
        
        return null;
    }

	
	/**
	 * Método utilizado para consultar los funcionarios destinatarios x tribunalId
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarDestinararioXTribunal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("EJECUTANDO ACTION CONSULTAR DESTINATARIO X TRIBUNAL");
			
			Long tribunalId = NumberUtils.toLong(request.getParameter("tribunalId"), 0L);
			log.info("LLEGA tribunalId="+tribunalId);
			
			List<FuncionarioDTO> listaFuncionarios = funcionarioDelegate.consultarFuncionariosXTribunal(tribunalId,Instituciones.PJ);
			
			converter.alias("listaFuncionarios", java.util.List.class);
			converter.alias("funcionario", FuncionarioDTO.class);
			converter.alias("persona", PersonaDTO.class);
			converter.alias("persona", PersonaDTO.class);
			converter.alias("persona", PersonaDTO.class);
			converter.alias("departamento", DepartamentoDTO.class);
			converter.alias("valor", ValorDTO.class);
			converter.alias("jerarquiaOrganizacional", JerarquiaOrganizacionalDTO.class);
			
			String xml = converter.toXML(listaFuncionarios);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para consultar los funcionarios x area
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarFuncionariosXArea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("EJECUTANDO ACTION CONSULTAR FUNCIONARIOS POR AREA");
			
			Long areaId = NumberUtils.toLong(request.getParameter("areaId"), 0L);
			log.info("LLEGA areaId="+areaId);
			
			//List<FuncionarioDTO> listaFuncionarios = funcionarioDelegate.consultarFuncionariosPorAreayPuesto(areaId,null);
			
			JerarquiaOrganizacionalDTO  jerarquiaOrganizacional = new JerarquiaOrganizacionalDTO();
			jerarquiaOrganizacional.setJerarquiaOrganizacionalId(areaId);
			
			FuncionarioDTO filtro = new FuncionarioDTO();
			filtro.setJerarquiaOrganizacional(jerarquiaOrganizacional);
			
			List<FuncionarioDTO> listaFuncionarios = solicitudPericialDelegate.consultarFuncionarioPorFiltro(filtro);
			converter.alias("listaFuncionarios",java.util.List.class);
			converter.alias("funcionario", FuncionarioDTO.class);
			converter.alias("persona", PersonaDTO.class);
			converter.alias("departamento", DepartamentoDTO.class);
			converter.alias("valor", ValorDTO.class);
			converter.alias("jerarquiaOrganizacional", JerarquiaOrganizacionalDTO.class);
			
			String xml = converter.toXML(listaFuncionarios);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	
	/**
	 * Método utilizado para consultar los tipos de atencion NO penal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoTipoAtencionAtNoPenal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
	
			log.info("EJECUTANDO ACTION CONSULTAR CATALOGO TIPO DE ATENCION NO PENAL");		
			try {
				
				log.info("ejecutando Action consultar tipo de objetos");
				List<CatalogoDTO> listaCatalogo = catDelegate
						.recuperarCatalogo(Catalogos.TIPO_ATENCION);
				converter.alias("listaCatalogo", java.util.List.class);
				converter.alias("catTipoAtencion", CatalogoDTO.class);
				String xml = converter.toXML(listaCatalogo);
				//super.escribirRespuesta(response, xml);
				response.setContentType("text/xml");
				PrintWriter pw = response.getWriter();
				pw.print(xml);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			return null;
	}
	
	
	/**
	 * M?todo utilizado para consultar Distritos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarDistritos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			List<CatDistritoDTO> listaCatalogo = new ArrayList<CatDistritoDTO>();
			listaCatalogo = distritoDelegate.consultarDistritos();
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catDistritoDTO", CatDistritoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
}
		return null;
	}

	/**
	 * M?todo utilizado para consultar Tipos de agencias en PGJ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarAgenciasDePGJxIdDistrito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("EJECUTANDO ACTION consultarAgenciasPGJ");
			
			Long distritoId = NumberUtils.toLong(request.getParameter("distritoId"), 0L);		
			List<CatDiscriminanteDTO> listaCatalogo = new ArrayList<CatDiscriminanteDTO>();			
			listaCatalogo = catDiscriminanteDelegate.consultarAgenciasPorDistrito(distritoId, Instituciones.PGJ);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catDiscriminanteDTO", CatDiscriminanteDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * M?todo utilizado para consultar los tipos de Visita
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoTipoVisita(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

			log.info("EJECUTANDO ACTION CONSULTAR CATALOGO TIPO DE VISITA");		
			try {
				
				log.info("ejecutando Action consultar tipo de objetos");
				List<CatalogoDTO> listaCatalogo = catDelegate
						.recuperarCatalogo(Catalogos.TIPO_VISITA);
				converter.alias("listaCatalogo", java.util.List.class);
				converter.alias("catTipoVisita", CatalogoDTO.class);
				String xml = converter.toXML(listaCatalogo);
				//super.escribirRespuesta(response, xml);
				response.setContentType("text/xml");
				PrintWriter pw = response.getWriter();
				pw.print(xml);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
}
			return null;
	}
}

