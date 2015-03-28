/**
 * Nombre del Programa 		: IngresarObjetoAction.java
 * Autor                     : Cuauhtemoc Paredes
 * Compania                  : Ultrasist
 * Proyecto                  : NSJP                    Fecha: 2 May 2011
 * Marca de cambio        	: N/A
 * Descripcion General    	: Clase Action para ingresar objetos
 * Programa Dependiente  	: N/A
 * Programa Subsecuente 		: N/A
 * Cond. de ejecucion        : N/A
 * Dias de ejecucion         : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                     : N/A
 * Compania              	: N/A
 * Proyecto                 	: N/A                                 Fecha: N/A
 * Modificacion           	: N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.web.objeto.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.objeto.ObjetoDelegate;
import mx.gob.segob.nsjp.dto.catalogo.DiaInhabilDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase Action para ingresar pertenencias
 * 
 * @version 1.0
 * @author Cuauhtemoc Paredes
 * 
 */
public class ConsultarPertenenciasAction extends GenericAction {

	/* Log de clase */
	private static final Logger log = Logger
			.getLogger(ConsultarPertenenciasAction.class);

	@Autowired
	private ObjetoDelegate objetoDelegate;
	
	/**
	 * Método para registar las pertenencias de los detenidos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 */
	public ActionForward ingresarPertenencias(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, NSJPNegocioException {
		
		log.debug("ENTRA A INGRESAR PERTENENCIA");
		
		String expedienteId = request.getParameter("expedienteId");
		log.debug("expedienteId" + expedienteId);
				 
		String idDetenido = request.getParameter("idDetenido");
		log.debug("idDetenido" + idDetenido);
		
		String categoria = request.getParameter("categoria");
		log.debug("Categoria" + categoria);
		
		String condicion = request.getParameter("condicion");
		log.debug("Condicion" + condicion);
		
		String descripcion = request.getParameter("descripcion");
		log.debug("Descripcion" + descripcion);
		
		ValorDTO valorDTO = new ValorDTO();
		valorDTO.setIdCampo(Long.parseLong(condicion));
		
		ExpedienteDTO exp = new ExpedienteDTO();
		exp.setExpedienteId(Long.parseLong(expedienteId));
		
		InvolucradoDTO involucradoDTO = new InvolucradoDTO();
		ObjetoDTO objetoDTO = new ObjetoDTO();
		objetoDTO.setDescripcion(descripcion);		
		objetoDTO.setValorByCondicionFisicaVal(valorDTO);
		objetoDTO.setFechaCreacionElemento(new Date());
		objetoDTO.setExpedienteDTO(exp);
		
		if (Objetos.ARMA.equals(categoria)){
			objetoDTO.setTipoObjeto(Objetos.ARMA);
		}
		if (Objetos.DOCUMENTO_OFICIAL.equals(categoria)){
			objetoDTO.setTipoObjeto(Objetos.DOCUMENTO_OFICIAL);
		}
		if (Objetos.EQUIPO_DE_COMPUTO.equals(categoria)){
			objetoDTO.setTipoObjeto(Objetos.EQUIPO_DE_COMPUTO);
		}
		if (Objetos.EQUIPO_TELEFONICO.equals(categoria)){
			objetoDTO.setTipoObjeto(Objetos.EQUIPO_TELEFONICO);
		}
		if (Objetos.EXPLOSIVO.equals(categoria)){
			objetoDTO.setTipoObjeto(Objetos.EXPLOSIVO);
		}
		if (Objetos.JOYA.equals(categoria)){
			objetoDTO.setTipoObjeto(Objetos.JOYA);
		}
		if (Objetos.OTRO.equals(categoria)){
			objetoDTO.setTipoObjeto(Objetos.OTRO);
		}
		if (Objetos.SUSTANCIA.equals(categoria)){
			objetoDTO.setTipoObjeto(Objetos.SUSTANCIA);
		}
		if (Objetos.VEGETAL.equals(categoria)){
			objetoDTO.setTipoObjeto(Objetos.VEGETAL);
		}
		
		involucradoDTO.setElementoId(Long.parseLong(idDetenido));			
	
		Long guardaExito = objetoDelegate.registrarPertenenciaDetenido(objetoDTO, involucradoDTO);
		log.debug("Regresa Guardado" + guardaExito );

		return null;
	}
			
	/**
	 * Método utilizado para realizar la consulta de las pertenencias 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarPertenencias(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar pertenencia");
			
			String idDetenido = request.getParameter("idDetenido");
			
			InvolucradoDTO involucradoDTo = new InvolucradoDTO();
			involucradoDTo.setElementoId(Long.parseLong(idDetenido));
			
			List<ObjetoDTO> objetoDTOs = objetoDelegate.consultarInventarioPertenenciasDetenido(involucradoDTo);
						
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");
		
			writer.print("<records>" + objetoDTOs.size() + "</records>");
	
			for (ObjetoDTO objetoDTO : objetoDTOs) {
				
				writer.print("<row id='" + objetoDTO.getElementoId()+ "'>");
				writer.print("<cell>" + objetoDTO.getTipoObjeto().getNombreEntity()+ "</cell>");
				writer.print("<cell>" + objetoDTO.getDescripcion()+ "</cell>");	
				writer.print("<cell>" + objetoDTO.getValorByCondicionFisicaVal().getValor()+ "</cell>");	
				writer.print("</row>");
			}			
			
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	

	/**
	 * Método para eliminar las pertenencias
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 */
	public ActionForward eliminarPertenencias(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, NSJPNegocioException {
		
		log.debug("ENTRA A ELIMINAR PERTENENCIA");
		 
		String idPertenencia = request.getParameter("idPertenencia");
		log.debug("id Pertenencia" + idPertenencia);
		
		String idDetenido = request.getParameter("idDetenido");
		log.debug("idDetenido" + idDetenido);
						
		InvolucradoDTO involucradoDTO = new InvolucradoDTO();
		involucradoDTO.setElementoId(Long.parseLong(idDetenido));
		ObjetoDTO objetoDTO = new ObjetoDTO();
		objetoDTO.setElementoId(Long.parseLong(idPertenencia));
				
		objetoDelegate.eliminarPertenenciaDetenido(objetoDTO, involucradoDTO);

		return null;
	}
	
	
}
