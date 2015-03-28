/**
 *  
 */
package mx.gob.segob.nsjp.administrarAudienciaJavs.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.audiencia.AudienciaDelegate;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author AlejandroGA
 *
 */
public class AdministrarAudienciaJavsAction extends GenericAction{
	
	/* Log de clase*/
	private static final Logger log  = Logger.getLogger(AdministrarAudienciaJavsAction.class);
	
	@Autowired 
	public AudienciaDelegate audienciaDelegate;
	
	//agendar consultar eliminar
	public ActionForward consultarAudienciaJavs (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try{
			
			log.info("EJECUTANDO ACTION AGENDAR AUDIENCIA JAVS");
			log.info("******************VERIFICANDO PARAMETROS*********************");
			log.info("audienciaId="+request.getParameter("idAudiencia"));
			
			Long idAudiencia= NumberUtils.toLong(request.getParameter("idAudiencia"), 0L);
			
			if( idAudiencia > 0){
		
				Long estatus = audienciaDelegate.consultarAudiencia(idAudiencia);
				log.info("ESTATUS*********************"+estatus);

				converter.alias("respuesta",Long.class);
				String respuesta = converter.toXML(estatus);
				escribirRespuesta(response, respuesta);
			}
			
			
		}catch(NSJPNegocioException e){
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ActionForward eliminarAudienciaJavs (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try{
			
			log.info("EJECUTANDO ACTION AGENDAR AUDIENCIA JAVS");
			log.info("******************VERIFICANDO PARAMETROS*********************");
			log.info("audienciaId="+request.getParameter("idAudiencia"));
			
			Long idAudiencia= NumberUtils.toLong(request.getParameter("idAudiencia"), 0L);
			
			if( idAudiencia > 0){
		
				Long estatus = audienciaDelegate.eliminarAudiencia(idAudiencia);
				log.info("ESTATUS*********************"+estatus);

				converter.alias("respuesta",Long.class);
				String respuesta = converter.toXML(estatus);
				escribirRespuesta(response, respuesta);
			}
			
			
		}catch(NSJPNegocioException e){
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}		
	
	public ActionForward cancelarAudiencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try{
			
			log.info("EJECUTANDO ACTION CANCELAR AUDIENCIA");
			log.info("******************VERIFICANDO PARAMETROS*********************");
			log.info("audienciaId="+request.getParameter("idAudiencia"));
			
			Long idAudiencia= NumberUtils.toLong(request.getParameter("idAudiencia"), 0L);
			Long Resultado= 0L;
			
			if( idAudiencia > 0){
		
				Resultado=audienciaDelegate.cancelarAudiencia(idAudiencia);
				
				String respuesta = converter.toXML(Resultado);
				escribirRespuesta(response, respuesta);
			}
			
			
		}catch(NSJPNegocioException ne){
			log.info(ne.getCause(), ne);
			if(ne.getCodigo().equals(CodigoError.AUDIENCIA_CANCELADA)){
				converter.alias("respuesta",String.class);
				escribirRespuesta(response,converter.toXML("fail_audiencia_cancelada"));
			}
			if(ne.getCodigo().equals(CodigoError.FAIL_ESTATUS_AUDIENCIA)){
				converter.alias("respuesta",String.class);
				escribirRespuesta(response,converter.toXML("fail_estatus_audiencia"));
			}
		}
		
		return null;
	}

	
	
	
	

}
