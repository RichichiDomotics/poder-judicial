/**
  * Nombre del Programa : MedidaCautelarTransformer.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 6 Jul 2011
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
package mx.gob.segob.nsjp.service.medidascautelares.impl.transform;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.MedidaCautelar;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

import org.apache.commons.lang.StringUtils;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author Tattva-IT
 * 
 */
public class MedidaCautelarTransformer {

    public static List<MedidaCautelarDTO> transformarMedidaCautelar(
            List<MedidaCautelar> in) {
        List<MedidaCautelarDTO> resp = new ArrayList<MedidaCautelarDTO>();

        for (MedidaCautelar s : in) {
            resp.add(transformarMedidaCautelar(s));
        }

        return resp;

    }

    public static MedidaCautelarDTO transformarMedidaCautelar(MedidaCautelar in) {
        MedidaCautelarDTO med = new MedidaCautelarDTO();

        med.setDocumentoId(in.getDocumentoId());

        if (in.getFuncionario() != null)
            med.setFuncionario(FuncionarioTransformer.transformarFuncionario(in
                    .getFuncionario()));
        if (in.getExpediente() != null)
            med.setExpedienteDTO(ExpedienteTransformer
                    .transformarExpedienteBasico(in.getExpediente()));
        if (in.getInvolucrado() != null)
            med.setInvolucrado(InvolucradoTransformer.transformarInvolucradoBasico(in
                    .getInvolucrado()));
        if (in.getDomicilio() != null)
            med.setDomicilio(DomicilioTransformer.transformarDomicilio(in
                    .getDomicilio()));

        if (in.getValorPeriodicidad() != null) {
            med.setValorPeriodicidad(new ValorDTO(in.getValorPeriodicidad()
                    .getValorId(), in.getValorPeriodicidad().getValor()));
        }
        if (in.getValorTipoMedida() != null) {
            med.setValorTipoMedida(new ValorDTO(in.getValorTipoMedida()
                    .getValorId(), in.getValorTipoMedida().getValor()));
        }

        if (StringUtils.isBlank(in.getTextoParcial())) {
            med.setGuardadoDefinitivo(true);
        }
        if(in.getFolioDocumento()!=null)
        	med.setFolioDocumento(in.getFolioDocumento());
        
        if(in.getEstatus()!=null)
        	med.setEstatus(new ValorDTO(in.getEstatus().getValorId()));
        
        med.setFechaInicio(in.getFechaInicio());
        med.setFechaFin(in.getFechaFin());
        med.setSeguimiento(in.getSeguimiento());
        med.setEsActivo(in.getEsActivo());
        med.setDescripcionMedida(in.getDescripcionMedida());

        //Campos Nuevos
		if(in.getEstatus()!=null)
			med.setEstatus(new ValorDTO(in.getEstatus().getValorId()));
		
		if(in.getDescripcionMedida()!=null)
			med.setDescripcionMedida(in.getDescripcionMedida());
		
		if(in.getNumeroCausa()!=null)
			med.setNumeroCausa(in.getNumeroCausa());
		if(in.getNumeroCarpetaEjecucion()!=null)
			med.setNumeroCarpetaEjecucion(in.getNumeroCarpetaEjecucion());
		if(in.getJuezOrdena()!=null)
			med.setJuezOrdena(in.getJuezOrdena());
		if(in.getNumeroCaso()!=null)
			med.setNumeroCaso(in.getNumeroCaso());
		if (in.getNumeroExpediente()!=null)
			med.setExpedienteDTO(ExpedienteTransformer.transformarExpedienteBasico(in.getNumeroExpediente()));
		//Fin de campos nuevos
        
        
        return med;
    }

    public static MedidaCautelar transformarMedidaCautelar(MedidaCautelarDTO in) {
        MedidaCautelar med = new MedidaCautelar();

        med.setDocumentoId(in.getDocumentoId());

        if (in.getExpedienteDTO() != null) {
            med.setNumeroExpediente(new NumeroExpediente(in.getExpedienteDTO()
                    .getNumeroExpedienteId()));
        }
        if (in.getFuncionario() != null) {
            med.setFuncionario(new Funcionario(in.getFuncionario()
                    .getClaveFuncionario()));
        }
        if (in.getInvolucrado() != null) {
            med.setInvolucrado(new Involucrado(in.getInvolucrado()
                    .getElementoId()));
        }


        if (in.getValorPeriodicidad() != null) {
            med.setValorPeriodicidad(new Valor(in.getValorPeriodicidad()
                    .getIdCampo()));
        }

        if (in.getValorTipoMedida() != null) {
            med.setValorTipoMedida(new Valor(in.getValorTipoMedida()
                    .getIdCampo()));
        }

        med.setFechaInicio(in.getFechaInicio());
        med.setFechaFin(in.getFechaFin());
        med.setSeguimiento(in.getSeguimiento());
        med.setEsActivo(in.getEsActivo());
        med.setFechaCreacion(new Date());
        med.setTipoDocumento(new Valor(1L));
        med.setNombreDocumento("Medida Cautelar");
        med.setEsActivo(Boolean.TRUE);
        med.setDescripcionMedida(in.getDescripcionMedida());
       
        // TODO recibir de acuerdo al cat de MC
        med.setForma(new Forma(Formas.MEDIDA_CAUTELAR.getValorId()));

        
        //Campos Nuevos
		if(in.getEstatus()!=null)
			med.setEstatus(new Valor(in.getEstatus().getIdCampo()));
		
		if(in.getDescripcionMedida()!=null)
			med.setDescripcionMedida(in.getDescripcionMedida());
		
		if(in.getNumeroCausa()!=null)
			med.setNumeroCausa(in.getNumeroCausa());
		if(in.getNumeroCarpetaEjecucion()!=null)
			med.setNumeroCarpetaEjecucion(in.getNumeroCarpetaEjecucion());
		if(in.getJuezOrdena()!=null)
			med.setJuezOrdena(in.getJuezOrdena());
		if(in.getNumeroCaso()!=null)
			med.setNumeroCaso(in.getNumeroCaso());
		
		//Fin de campos nuevos
        return med;
    }

}
