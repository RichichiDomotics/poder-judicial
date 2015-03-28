/**
* Nombre del Programa : MedidaTransformer.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/08/2011
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
package mx.gob.segob.nsjp.service.medida.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.IncidenciaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Incidencia;
import mx.gob.segob.nsjp.model.Medida;
import mx.gob.segob.nsjp.model.MedidaAlterna;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.CompromisoPeriodicoTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

/**
 * Transforma el objeto de Medida a MedidaDTO, y viceversa.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class MedidaTransformer {
	
	public static MedidaDTO transformarMedida(Medida medida){
		MedidaDTO  medidaDTO = null;
		if(medida == null){
			return null;
		}
		DocumentoDTO documentoDTO = DocumentoTransformer.transformarDocumento(medida);
		if(documentoDTO instanceof MedidaAlternaDTO )
			medidaDTO = (MedidaAlternaDTO) documentoDTO;
		else
			medidaDTO = (MedidaDTO) documentoDTO;
		
		medidaDTO.setFechaInicio( medida.getFechaInicio());
		medidaDTO.setFechaFin( medida.getFechaFin());
		medidaDTO.setDescripcionMedida(medida.getDescripcionMedida());
		
		
		if(medida.getInvolucrado()!= null && medida.getInvolucrado().getElementoId()!= null)
			medidaDTO.setInvolucrado(new InvolucradoDTO(medida.getInvolucrado().getElementoId()));
		
		if(medida.getFuncionario()!= null && medida.getFuncionario().getClaveFuncionario()!= null)
			medidaDTO.setFuncionario( new FuncionarioDTO(medida.getFuncionario().getClaveFuncionario()));
		
		if(medida.getValorPeriodicidad()!= null  && medida.getValorPeriodicidad().getValorId()!= null )
			medidaDTO.setValorPeriodicidad(new ValorDTO( medida.getValorPeriodicidad().getValorId()));
		
		if(medida.getValorTipoMedida()!= null  && medida.getValorTipoMedida().getValorId()!= null )
			medidaDTO.setValorTipoMedida(new ValorDTO( medida.getValorTipoMedida().getValorId()));
		
		if (medida.getDomicilio()!= null){
			DomicilioDTO domicilioDTO = new DomicilioDTO();
			domicilioDTO.setElementoId(medida.getDomicilio().getElementoId());
			medidaDTO.setDomicilio(domicilioDTO);
		}
		
		if(medida.getCompromisoPeriodico() != null && medida.getCompromisoPeriodico().getCompromisoPeriodicoId()!= null){
			CompromisoPeriodicoDTO compromisoPeriodicoDTO = CompromisoPeriodicoTransformer.transformarCompromiso(medida.getCompromisoPeriodico());
			medidaDTO.setCompromisoPeriodico(compromisoPeriodicoDTO);
		}
		
		if(medida.getIncidencias()!= null && !medida.getIncidencias().isEmpty() ) {
			List<IncidenciaDTO> incidenciasDTO = new ArrayList<IncidenciaDTO>();
			for(Incidencia incidencia : medida.getIncidencias() ){
				IncidenciaDTO incidenciaDTO = IncidenciaTransformer.transformarIncidencia(incidencia);
				incidenciasDTO.add(incidenciaDTO);
			}
			medidaDTO.setIncidencias(incidenciasDTO);
		}
		
		//Campos nuevos
		if(medida.getEstatus()!=null)
			medidaDTO.setEstatus(new ValorDTO(medida.getEstatus().getValorId()));
		
		if(medida.getDescripcionMedida()!=null)
			medidaDTO.setDescripcionMedida(medida.getDescripcionMedida());
		
		if(medida.getNumeroCausa()!=null)
			medidaDTO.setNumeroCausa(medida.getNumeroCausa());
		if(medida.getNumeroCarpetaEjecucion()!=null)
			medidaDTO.setNumeroCarpetaEjecucion(medida.getNumeroCarpetaEjecucion());
		if(medida.getJuezOrdena()!=null)
			medidaDTO.setJuezOrdena(medida.getJuezOrdena());
		if(medida.getNumeroCaso()!=null)
			medidaDTO.setNumeroCaso(medida.getNumeroCaso());
		//Finalizan campos nuevos
		
		return medidaDTO;
		
	}

	
	public static Medida transformarMedida(MedidaDTO medidaDTO){
		Medida  medida = null; 
		
		Documento documento = DocumentoTransformer.transformarDocumentoDTO(medidaDTO);
		//Datos de documento - Este Casting 'hacia abajo' es posible porq en el método se crea la instancia adecuada.
		if (documento instanceof MedidaAlterna)
			medida = (MedidaAlterna) documento;
			
		medida.setDocumentoId(medidaDTO.getDocumentoId()); 
		medida.setFechaInicio( medidaDTO.getFechaInicio());
		medida.setFechaFin( medidaDTO.getFechaFin());
		medida.setDescripcionMedida(medidaDTO.getDescripcionMedida());
		medida.setSeguimiento(medidaDTO.getSeguimiento());
		
		if(medidaDTO.getInvolucrado()!= null){
			medida.setInvolucrado( InvolucradoTransformer.transformarInvolucrado(medidaDTO.getInvolucrado()));
		}
		
		if(medidaDTO.getFuncionario()!= null)
			medida.setFuncionario( FuncionarioTransformer.transformarFuncionario(medidaDTO.getFuncionario()));
		
		if(medidaDTO.getValorPeriodicidad()!= null  && medidaDTO.getValorPeriodicidad().getIdCampo()!= null )
			medida.setValorPeriodicidad(new Valor( medidaDTO.getValorPeriodicidad().getIdCampo()));
		
		if(medidaDTO.getValorTipoMedida()!= null  && medidaDTO.getValorTipoMedida().getIdCampo()!= null )
			medida.setValorTipoMedida(new Valor( medidaDTO.getValorTipoMedida().getIdCampo()));
		
		
		//Campos nuevos
		if(medidaDTO.getEstatus()!=null)
			medida.setEstatus(new Valor(medidaDTO.getEstatus().getIdCampo()));
		
		if(medidaDTO.getDescripcionMedida()!=null)
			medida.setDescripcionMedida(medidaDTO.getDescripcionMedida());
		
		if(medidaDTO.getNumeroCausa()!=null)
			medida.setNumeroCausa(medidaDTO.getNumeroCausa());
		if(medidaDTO.getNumeroCarpetaEjecucion()!=null)
			medida.setNumeroCarpetaEjecucion(medidaDTO.getNumeroCarpetaEjecucion());
		if(medidaDTO.getJuezOrdena()!=null)
			medida.setJuezOrdena(medidaDTO.getJuezOrdena());
		if(medidaDTO.getNumeroCaso()!=null)
			medida.setNumeroCaso(medidaDTO.getNumeroCaso());
		//Finalizan campos nuevos
		
		
//		if (medidaDTO.getDomicilio()!= null)
//			medida.setDomicilio(DomicilioTransformer.transformarDomicilio(medidaDTO.getDomicilio()));
//		if(medidaDTO.getIncidencias()!= null && !medidaDTO.getIncidencias().isEmpty() ) {
//			List<Incidencia> incidencias = new ArrayList<Incidencia>();
//			for(IncidenciaDTO incidenciaDTO : medidaDTO.getIncidencias() ){
//				Incidencia incidencia = IncidenciaTransformer.transformarIncidencia(incidenciaDTO);
//				incidencias.add(incidencia);
//			}
//			medida.setIncidencias(incidencias);
//		}
		return medida;
	}
}
