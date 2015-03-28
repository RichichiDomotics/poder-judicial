/**
* Nombre del Programa : DictamenTransformer.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/07/2011
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
package mx.gob.segob.nsjp.service.documento.impl.tranform;

import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.model.Dictamen;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatalogoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.forma.impl.transform.FormaTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudPericialTransformer;

/**
 * Clase de transformación de la entidad Dictamen
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class DictamenTransformer {

	/**
	 * Transforma un objeto <code>Dictamen</code> a un objeto <code>DictamenDTO</code>
	 * con los datos básiscos de su solicitud pericial y su documento (sin contenido archivo digital)
	 * @param src Datos fuente
	 * @return DTO resultado
	 */
	public static DictamenDTO transformarDictamenBasico(Dictamen src){
		if(src == null)
			return null;
		
		DictamenDTO dest = new DictamenDTO();
		dest.setDocumentoId(src.getDocumentoId());
		dest.setFormaDTO(FormaTransformer.transformarForma(src.getForma()));
		dest.setFechaCreacion(src.getFechaCreacion());
		if(src.getArchivoDigital() != null){
			dest.setArchivoDigital(new ArchivoDigitalDTO(src.getArchivoDigital().getArchivoDigitalId()));
		}
		dest.setSolicitudPericial(new SolicitudPericialDTO(src.getSolicitudPericial().getDocumentoId()));
		dest.getSolicitudPericial().setTipoSolicitudDTO(
				CatalogoTransformer.transformValor(src.getSolicitudPericial().getTipoSolicitud())
				);
		dest.getSolicitudPericial().setEstatus(
				CatalogoTransformer.transformValor(src.getSolicitudPericial().getEstatus())
		);
		dest.getSolicitudPericial().setDestinatario
		(FuncionarioTransformer.transformarFuncionario(src.getSolicitudPericial().getDestinatario()));
		dest.setExpedienteDTO(ExpedienteTransformer.transformarExpedienteBasico(src.getSolicitudPericial().getNumeroExpediente()));
		return dest;
	}
	
}
