/**
 * Nombre del Programa 		: AdjuntarFotoDeElementoAction.java
 * Autor                     : rgama
 * Compania                  : Ultrasist
 * Proyecto                  : NSJP                    Fecha: 24 08 2011
 * Marca de cambio        	: N/A
 * Descripcion General    	: Clase Action que permite adjuntar una fotografia a un elemento
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
package mx.gob.segob.nsjp.web.elemento.action;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.elemento.ElementoDelegate;
import mx.gob.segob.nsjp.delegate.objeto.ObjetoDelegate;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.elemento.form.ElementoForm;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase Action para ingresar objetos.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class AdjuntarFotoDeElementoAction extends GenericAction {
	
	@Autowired
	public ElementoDelegate elementoDelegate;
	
	/* Log de clase */
	private static final Logger log = Logger
			.getLogger(AdjuntarFotoDeElementoAction.class);
	
	@Autowired
	private DocumentoDelegate documentoDelegate;

	/**
	 * Metodo utilizado para guardar la imagen asociada a un Elemento
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward ingresarImagenDelElemento(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long elementoID = null;
		String numeroExpediente= null;
		String lpCambiarEtiquetaAProbableParticipe = request.getParameter("cambiarEtiquetaAProbableParticipe");
		log.info("Valor de la variable cambiarEtiquetaAProbableParticipe: " + lpCambiarEtiquetaAProbableParticipe);
		
		try {
			
			log.info("Ejecutando action AdjuntarFotoDelElemeto - ingresarImagenDelElemento  con Id de objeto:: "+request.getParameter("elementoID"));
			ElementoForm forma = (ElementoForm) form;
			elementoID=NumberUtils.toLong(request.getParameter("elementoID"),0);
			String idIndividuo=request.getParameter("idIndividuo");
			numeroExpediente = request.getParameter("numeroExpediente");
			log.info("Imagen="+forma.getArchivo()+" :: "+ forma.getArchivo().getFileSize());		
			log.info("IDINdividuo="+idIndividuo);		
			log.info("******forma muestra detenido modificar " + forma.getMuestraDetenidoModificar());		
			request.setAttribute("idIndividuoProp", idIndividuo);
			if(forma.getMuestraDetenidoModificar() != null){
				request.setAttribute("detenido", forma.getMuestraDetenidoModificar());
				log.info("****** " + forma.getMuestraDetenidoModificar());
			}
			//Permite guardar la foto de un elemento
			if(forma.getArchivo() != null && forma.getArchivo().getFileSize()>0){				
				ArchivoDigitalDTO adjunto = new ArchivoDigitalDTO();

				FormFile archivo = forma.getArchivo();
		        String contentType = archivo.getContentType();
		        String fileName    = archivo.getFileName();
		        log.debug(" EL ADJUNTO GAMASOFT: El nombre del archivo es" + archivo.getFileName());
		        int fileSize       = archivo.getFileSize();
		        byte[] fileData    = archivo.getFileData();
				adjunto.setContenido(fileData);
				adjunto.setNombreArchivo(fileName);
				adjunto.setTipoArchivo(contentType);
				adjunto.setUsuario(super.getUsuarioFirmado(request));
				ElementoDTO elemento = new ElementoDTO();
				elemento.setElementoId(elementoID);				
				elementoDelegate.adjuntarArchivoAElemento(elemento, adjunto);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		if(lpCambiarEtiquetaAProbableParticipe != null && !lpCambiarEtiquetaAProbableParticipe.equals(""))
			return new ActionForward(mapping.findForward("success").getPath()+"?idProbableResponsable=" + elementoID +"&calidadInv=PROBABLE_RESPONSABLE&numeroExpediente=" + numeroExpediente + "&cambiarEtiquetaAProbableParticipe=" + lpCambiarEtiquetaAProbableParticipe, false);
		else
			return new ActionForward(mapping.findForward("success").getPath()+"?idProbableResponsable=" + elementoID +"&calidadInv=PROBABLE_RESPONSABLE&numeroExpediente=" + numeroExpediente, false);
		
	}
	
	
	/**
	 * Metodo utilizado para obtener la imagen de un elemento 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward obtenImagenDeElemento(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			String idObjeto = request.getParameter("elementoID");

			log.info("$$$$ ID del Elemento solicitado $$$ : " + idObjeto);
		
			response.setContentType("image/gif");
			byte [] imag = documentoDelegate.consultarArchivoDigitalXElementoId(Long.parseLong(idObjeto));
			if(imag != null) {
				   ServletOutputStream out = response.getOutputStream();
				   out.write(imag);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
}
