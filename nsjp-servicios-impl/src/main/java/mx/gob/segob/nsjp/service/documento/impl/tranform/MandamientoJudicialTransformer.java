/**
* Nombre del Programa : MandamientoJudicialTransformer.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24/08/2011
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

import mx.gob.segob.nsjp.dto.documento.MandamientoDTO;
import mx.gob.segob.nsjp.model.Mandamiento;
import mx.gob.segob.nsjp.model.Resolutivo;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.ResolutivoTransformer;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatalogoTransformer;
import mx.gob.segob.nsjp.service.forma.impl.transform.FormaTransformer;
import mx.gob.segob.nsjp.service.medida.impl.transform.MedidaTransformer;

/**
 * Clase de transformaciones para el mandamiento judicial
 * @version 1.0
 * @author Emigdio
 *
 */
public class MandamientoJudicialTransformer {
	/**
	 * Tranforma un objeto del tipo MandamientoDTO en su equivalente
	 * objeto del modelo de entidades de BD
	 * @param src
	 * @return
	 */
	public static Mandamiento transformarMandamientoDTO(MandamientoDTO src){
		Mandamiento dest = null;
		if(src != null){
			dest = new Mandamiento();
			
			dest.setFechaInicial(src.getFechaInicial());
			dest.setFechaFinal(src.getFechaFinal());
			
			dest.setDocumentoId(src.getDocumentoId());
			dest.setForma(FormaTransformer.transformarFormaDTO(src.getFormaDTO()));
			dest.setNombreDocumento(src.getNombreDocumento());
			dest.setTipoDocumento(CatalogoTransformer.transformValor(src.getTipoDocumentoDTO()));
			dest.setFechaCreacion(src.getFechaCreacion());
			dest.setEstatus(CatalogoTransformer.transformValor(src.getEstatus()));
			dest.setTipoMandamiento(CatalogoTransformer.transformValor(src.getTipoMandamiento()));
			dest.setTipoSentencia(CatalogoTransformer.transformValor(src.getTipoSentencia()));
			dest.setTextoParcial(src.getTextoParcial());
			if(src.getResolutivo()!=null){
				dest.setResolutivo(new Resolutivo());
				dest.getResolutivo().setResolutivoId(src.getResolutivo().getResolutivoId());
			}
		}
		return dest;
	}
	/**
	 * Transforma un objeto del model de entidades de BD a su respectivo equivalente
	 * DTO
	 * @param src Datos fuente
	 * @return DTO equivalente
	 */
	public static MandamientoDTO transformarMandamiento(Mandamiento src){
		MandamientoDTO dest = null;
		if(src!=null){
			
			dest = new MandamientoDTO();
			dest.setDocumentoId(src.getDocumentoId());
			dest.setFormaDTO(FormaTransformer.transformarForma(src.getForma()));
			dest.setNombreDocumento(src.getNombreDocumento());
			dest.setFolioDocumento(src.getFolioDocumento());
			dest.setFechaCreacion(src.getFechaCreacion());
			dest.setEstatus(CatalogoTransformer.transformValor(src.getEstatus()));
			dest.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigitalBasico(src.getArchivoDigital()));
			dest.setResolutivo(ResolutivoTransformer.transformarResolutivo(src.getResolutivo()));
			dest.setTipoMandamiento(CatalogoTransformer.transformValor(src.getTipoMandamiento()));
			dest.setTipoSentencia(CatalogoTransformer.transformValor(src.getTipoSentencia()));
			dest.setMedida(MedidaTransformer.transformarMedida(src.getMedida()));
		}
		
		return dest;
		
		
	}

}
