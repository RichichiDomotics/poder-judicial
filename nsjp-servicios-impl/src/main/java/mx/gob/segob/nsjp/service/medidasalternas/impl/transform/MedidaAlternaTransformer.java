/**
* Nombre del Programa : MedidaCautelarTransformer.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Realiza la conversion de MedidaAlterna a MedidaAlternaDTO y viceversa
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
package mx.gob.segob.nsjp.service.medidasalternas.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.model.MedidaAlterna;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

/**
 * Realiza la conversion de MedidaAlterna a MedidaAlternaDTO y viceversa.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class MedidaAlternaTransformer {

    public static List<MedidaAlternaDTO> transformarMedidaAlterna(
            List<MedidaAlterna> in) {
        List<MedidaAlternaDTO> resp = new ArrayList<MedidaAlternaDTO>();

        for (MedidaAlterna s : in) {
            resp.add(TransformaMedidaAlterna(s));
        }
        return resp;
    }
    
	public static MedidaAlternaDTO TransformaMedidaAlterna (MedidaAlterna medidaAlterna) {
		MedidaAlternaDTO medAlternaDTO = new MedidaAlternaDTO();
	
		//Medida Alterna
		medAlternaDTO.setAnios(medidaAlterna.getAnios());
		medAlternaDTO.setMeses(medidaAlterna.getMeses());
		
		//Medida
		medAlternaDTO.setFechaInicio(medidaAlterna.getFechaInicio());
		medAlternaDTO.setFechaFin(medidaAlterna.getFechaFin());
		medAlternaDTO.setDescripcionMedida(medidaAlterna.getDescripcionMedida());
		medAlternaDTO.setSeguimiento(medidaAlterna.getSeguimiento());
		medAlternaDTO.setNumeroCaso(medidaAlterna.getNumeroCaso());
		medAlternaDTO.setNumeroCarpetaEjecucion(medidaAlterna.getNumeroCarpetaEjecucion());
		medAlternaDTO.setNumeroCausa(medidaAlterna.getNumeroCausa());
		medAlternaDTO.setJuezOrdena(medidaAlterna.getJuezOrdena());
		medAlternaDTO.setFechaInicioStr(DateUtils.formatear(medidaAlterna.getFechaInicio()));
		medAlternaDTO.setFechaFinStr(DateUtils.formatear(medidaAlterna.getFechaFin()));
		
		if (medidaAlterna.getInvolucrado()!=null)
			medAlternaDTO.setInvolucrado(InvolucradoTransformer.transformarInvolucradoBasico(medidaAlterna.getInvolucrado()));
		if (medidaAlterna.getFuncionario()!=null)
			medAlternaDTO.setFuncionario(FuncionarioTransformer.transformarFuncionarioBasico(medidaAlterna.getFuncionario()));
		if (medidaAlterna.getValorPeriodicidad()!=null)
			medAlternaDTO.setValorPeriodicidad(new ValorDTO(medidaAlterna.getValorPeriodicidad().getValorId(), medidaAlterna.getValorPeriodicidad().getValor()));
		if (medidaAlterna.getValorTipoMedida()!=null)
			medAlternaDTO.setValorTipoMedida(new ValorDTO(medidaAlterna.getValorTipoMedida().getValorId(), medidaAlterna.getValorTipoMedida().getValor()));
		if (medidaAlterna.getDomicilio()!=null)
			medAlternaDTO.setDomicilio(DomicilioTransformer.transformarDomicilio(medidaAlterna.getDomicilio()));
		if (medidaAlterna.getNumeroExpediente()!=null)
			medAlternaDTO.setExpedienteDTO(ExpedienteTransformer.transformarExpedienteBasico(medidaAlterna.getNumeroExpediente()));
		
		//Documento
		medAlternaDTO.setDocumentoId(medidaAlterna.getDocumentoId());
		medAlternaDTO.setFolioDocumento(medidaAlterna.getFolioDocumento());
		medAlternaDTO.setNombreDocumento(medidaAlterna.getNombreDocumento());
		medAlternaDTO.setFechaCreacion(medidaAlterna.getFechaCreacion());
		medAlternaDTO.setVersion(medidaAlterna.getVersion());
		if (medidaAlterna.getTipoDocumento()!=null)
			medAlternaDTO.setTipoDocumentoDTO(new ValorDTO(medidaAlterna.getTipoDocumento().getValorId(), medidaAlterna.getTipoDocumento().getValor()));
		
		return medAlternaDTO;
	}
	
}
