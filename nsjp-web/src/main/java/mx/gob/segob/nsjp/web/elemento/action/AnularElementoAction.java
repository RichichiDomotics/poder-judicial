/**
 * 
 */
package mx.gob.segob.nsjp.web.elemento.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.elemento.ElementoDelegate;
import mx.gob.segob.nsjp.delegate.eslabon.EslabonDelegate;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author MelitonBC
 *
 */
public class AnularElementoAction extends GenericAction {
	/* Log de clase*/
	private static final Logger log  = Logger.getLogger(AnularElementoAction.class);
	
	@Autowired
	public ElementoDelegate elementoDelegate;
	
	@Autowired
	public EslabonDelegate eslabonDelegate;
	
	
	public ActionForward anularElemento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, NSJPNegocioException {
		try{
			log.info("::: EJECUTANDO action AnularElementoAction - anularElemento");
			log.info("	:::: PARAMETROS RECUPERADOS ::::::::::");
			String idIndividuo=request.getParameter("idIndividuo");
			Long idElemento;
			log.info("	:::: IDINDIVIDUO ::::::::::"+idIndividuo);
			if(idIndividuo!=null && !idIndividuo.equals("")){
				idElemento=Long.parseLong(idIndividuo);
				if(elementoDelegate.anularElemento(idElemento)){
					escribirRespuesta(response, "<banderaOp>1</banderaOp>");
					log.info("::::::::::: Se anulo el elemento exitosamente :::::::::::");
				}
				else{
					escribirRespuesta(response, "<banderaOp>0</banderaOp>");
					log.info("NO se pudo ANULAR el elemento probablemente por relaciones en la tablas.");
				}
			}
			else{
				escribirRespuesta(response, "<banderaOp>0</banderaOp>");
				log.info("NO se pudo ANULAR el elemnto dado que el ID del objeto no llego al Action: AnularElementoAction - anularElemento");
			}
			log.info("::: TERMINE de ejecutar el action AnularElementoAction - anularElemento");
		}
		catch (Exception e) {
			escribirRespuesta(response, "<banderaOp>-1</banderaOp>");
			log.info("Ocurrió una excepcion al tratar de anular el elemento");
			log.info(e.getMessage());
		}
		
		return null;		
	}
	
	/***
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws NSJPNegocioException
	 */
	public ActionForward anularObjetoPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, NSJPNegocioException {
		try{
			log.info("	:::: Action AnularElementoAction- anularElemento::::::::::");
			log.info("	IdObjeto_anular::"+request.getParameter("idObjeto"));
			String idElemento=request.getParameter("idObjeto");
			if(idElemento!=null && !idElemento.equals("")){
				
				Long idElementoAnular;
				idElementoAnular=Long.parseLong(idElemento);
				//validamos que el objeto no sea una evidencia y este en un eslabon
				if(!eslabonDelegate.tieneEslabonPorEvidenciaYObjeto(idElementoAnular)){
					if(elementoDelegate.anularElemento(idElementoAnular)){
						escribirRespuesta(response, "<bandera>1</bandera><mensajeOp>Se anuló el objeto de manera exitosa</mensajeOp>");
						log.info("::::::::::: Se anulo el objeto exitosamente :::::::::::");
					}
					else{
						escribirRespuesta(response, "<bandera>0</bandera><mensajeOp>No se pudo anular el elemento anular el elemento</mensajeOp>");
						log.info("NO se pudo ANULAR el objeto probablemente por relaciones en la tablas.");
					}
				}
				else{
					escribirRespuesta(response, "<bandera>0</bandera><mensajeOp>No se pudo anular el objeto <br/> dado ques una evidencia y forma parte de un eslabón</mensajeOp>");
					log.info("NO se pudo ANULAR el objeto porque es una evidencia y esta en un eslabon de cadena de custodia.");
				}
			}
			else{
				escribirRespuesta(response, "<bandera>0</bandera><mensajeOp>No se pudo anular el objeto, intente más tarde</mensajeOp>");
				log.info("NO se pudo ANULAR el objeto dado que el ID del objeto no llego al Action: AnularElementoAction - anularObjeto");
			}
			log.info("::::::::::: FIN AnularElementoAction - anularObjeto :::::::::::");
		}
		catch (Exception e) {
			escribirRespuesta(response, "<bandera>0</bandera><mensajeOp>Ocurrió un error al tratar de anular el objeto, favor de contactar a su administrador</mensajeOp>");
			log.info(e.getMessage());
		}
		return null;		
	}
	
	/***
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws NSJPNegocioException
	 */
	public ActionForward consultarRelacionesElemento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, NSJPNegocioException {
		try{
			log.info("	:::: Action AnularElementoAction- consultarRelacionesElemento::::::::::");
			log.info("	IdObjeto_anular::"+request.getParameter("idElemento"));
			String idElemento=request.getParameter("idElemento");
			if(idElemento!=null && !idElemento.equals("")){
				Long idElementoAnular;
				idElementoAnular=Long.parseLong(idElemento);
				
				List<String> listaRelaciones=elementoDelegate.consultarRelacionesElemento(idElementoAnular);
				if(listaRelaciones!=null)
				{
					if(listaRelaciones.size()>0){
					converter.alias("listaRelaciones", java.util.List.class);
					converter.alias("cadena", java.lang.String.class);
					String xml = converter.toXML(listaRelaciones);
					xml=xml+"<numRel>"+listaRelaciones.size()+"</numRel>";
					escribirRespuesta(response, xml);
					log.info("Lista relaciones:: "+xml);
					log.info("HAY relaciones SE debe preguntar si se desea eliminar las relaciones");
					}
					else{
						escribirRespuesta(response, "<numRel>0</numRel>");
						log.info("NO hay relaciones se puede borrar el elemento");
					}
				}
				else
				{
					escribirRespuesta(response, "<numRel>-1</numRel>");
					log.info("La lista con las relaciones es NULA - ERROR :::");
				}
			}
			else{
				escribirRespuesta(response, "<numRel>-2</numRel>");
				log.info("El ID del objeto NO llego al Action: AnularElementoAction - consultarRelacionesElemento");
			}
			log.info("::::::::::: FIN AnularElementoAction - consultarRelacionesElemento :::::::::::");
		}
		catch (Exception e) {
			escribirRespuesta(response, "<numRel>-3</numRel>");
			log.info("::::::::::: ERROR AnularElementoAction - consultarRelacionesElemento :::");
			log.info(e.getMessage());
		}
		return null;		
	}
}
