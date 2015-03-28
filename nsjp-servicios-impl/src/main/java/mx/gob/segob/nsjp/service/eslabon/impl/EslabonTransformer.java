/**
 * Nombre del Programa : EslabonTransformer.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 30-jun-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.eslabon.impl;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonWSDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;

import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.enviarcarpetainvestigacion.ExpedienteWSDTOTransformer;

/**
 * Realiza las funciones de conversion entre Eslabon y EslabonDTO.
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public class EslabonTransformer {

	/**
	 * Transforma un Eslabon en un EslabonDTO.
	 * 
	 * @param eslabon
	 *            Un Eslabon basico a tranformar.
	 * @return Un EslabonDTO.
	 */
	public static EslabonDTO transformarEslabon(Eslabon eslabon) {
		EslabonDTO eslabonDTO = new EslabonDTO();
		eslabonDTO.setEslabonId(eslabon.getEslabonId());
		
		eslabonDTO.setFechaInicioMovimiento(eslabon.getFechaEntrega());
		eslabonDTO.setFechaFinMovimiento(eslabon.getFechaRecibe());
		eslabonDTO.setEslabonId(eslabon.getEslabonId());
		eslabonDTO.setNumeroEslabon(eslabon.getNumeroEslabon());
		
		eslabonDTO.setInstitucionQueEntrega(eslabon.getInstitucionQueEntrega());
		eslabonDTO.setInstitucionQueRecibe(eslabon.getInstitucionQueRecibe());
		
		Valor tipoEslabonDeRecepcion = eslabon.getTipoEslabonDeRecepcion();
		if (tipoEslabonDeRecepcion != null) {
			ValorDTO valorDTODeRecepcion = new ValorDTO(tipoEslabonDeRecepcion.getValorId());
			valorDTODeRecepcion.setValor(tipoEslabonDeRecepcion.getValor());
			eslabonDTO.setTipoEslabonDeRecepcion(valorDTODeRecepcion);
		}
				
		Valor tipoEslabon = eslabon.getTipoEslabon();
		if (tipoEslabon != null) {
			ValorDTO valorDTO = new ValorDTO(tipoEslabon.getValorId());
			valorDTO.setValor(tipoEslabon.getValor());
			eslabonDTO.setTipoEslabon(valorDTO);
		}
		eslabonDTO.setUbicacionFisica(eslabon.getUbicacionFisica());
		eslabonDTO.setPosicion(eslabon.getPosicion());
		if(eslabon.getDocumento()!=null)
			eslabonDTO.setDocumentoDTO(DocumentoTransformer.transformarDatosLista(eslabon.getDocumento()));
		
		
		if (eslabon.getFuncionarioEntrega()!= null)
			eslabonDTO.setFuncionariEntrega( FuncionarioTransformer.transformarFuncionarioBasico(eslabon.getFuncionarioEntrega()));
		
		if (eslabon.getFuncionarioRecibe()!= null)
			eslabonDTO.setFuncionariRecibe( FuncionarioTransformer.transformarFuncionarioBasico(eslabon.getFuncionarioRecibe()));
    	if (eslabon.getNumeroEslabon() != null)
			eslabonDTO.setNumeroEslabon(eslabon.getNumeroEslabon());
		eslabonDTO.setNumeroEslabon(eslabon.getNumeroEslabon());

		eslabonDTO.setQuienEntrega(eslabon.getQuienEntrega());
		eslabonDTO.setQuienRecibe(eslabon.getQuienRecibe());
    	

    	eslabonDTO.setObservacion(eslabon.getObservacion());
    	
    	eslabonDTO.setFechaInicioPrestamo(eslabon.getFechaInicioPrestamo());
    	eslabonDTO.setFechaFinPrestamo(eslabon.getFechaFinPrestamo());
    	
    	eslabonDTO.setStrFechaInicioPrestamo(eslabon.getFechaInicioPrestamo()!= null ? (DateUtils.formatear(eslabon.getFechaInicioPrestamo())+" "+DateUtils.formatearHoraAm(eslabon.getFechaInicioPrestamo())) : null);
    	eslabonDTO.setStrFechaFinPrestamo(eslabon.getFechaFinPrestamo()!= null ? (DateUtils.formatear(eslabon.getFechaFinPrestamo())+" "+DateUtils.formatearHoraAm(eslabon.getFechaFinPrestamo())) : null);
    	
    	eslabonDTO.setStrFechaEntrega(eslabon.getFechaEntrega()!= null ? (DateUtils.formatear(eslabon.getFechaEntrega())+" "+DateUtils.formatearHoraAm(eslabon.getFechaEntrega())) : null);
    	eslabonDTO.setStrFechaRecepcion(eslabon.getFechaRecibe()!= null ? (DateUtils.formatear(eslabon.getFechaRecibe())+" "+DateUtils.formatearHoraAm(eslabon.getFechaRecibe())) : null);
    	
		return eslabonDTO;
	}

	/**
	 * Transforma un EslabonDTO en un Eslabon basico.
	 * 
	 * @param eslabonDTO
	 *            El DTO a transformar.
	 * @return Un objeto de tipo Eslabon
	 */
	public static Eslabon transformarEslabon(EslabonDTO eslabonDTO) {
		Eslabon eslabon = new Eslabon();
		eslabon.setFechaEntrega(eslabonDTO.getFechaInicioMovimiento());
		eslabon.setFechaRecibe(eslabonDTO.getFechaFinMovimiento());

		if (eslabonDTO.getFuncionariEntrega() != null) {
			eslabon.setQuienEntrega(eslabonDTO.getFuncionariEntrega()
					.getNombreCompleto());
			if (eslabonDTO.getFuncionariEntrega().getClaveFuncionario() != null)
				eslabon.setFuncionarioEntrega(new Funcionario(eslabonDTO
						.getFuncionariEntrega().getClaveFuncionario()));
		}
		if (eslabonDTO.getFuncionariRecibe() != null) {
			eslabon.setQuienRecibe(eslabonDTO.getFuncionariRecibe()
					.getNombreCompleto());
			if (eslabonDTO.getFuncionariRecibe().getClaveFuncionario() != null)
				eslabon.setFuncionarioRecibe(new Funcionario(eslabonDTO
						.getFuncionariRecibe().getClaveFuncionario()));
		}

		eslabon.setInstitucionQueEntrega(eslabonDTO.getInstitucionQueEntrega());
		eslabon.setInstitucionQueRecibe(eslabonDTO.getInstitucionQueRecibe());

		if (eslabonDTO.getTipoEslabon() != null)
			eslabon.setTipoEslabon(new Valor(eslabonDTO.getTipoEslabon()
					.getIdCampo()));
		
		if (eslabonDTO.getTipoEslabonDeRecepcion() != null)
			eslabon.setTipoEslabonDeRecepcion(new Valor(eslabonDTO.getTipoEslabonDeRecepcion()
					.getIdCampo()));

		if (eslabonDTO.getNumeroEslabon() != null)
			eslabon.setNumeroEslabon(eslabonDTO.getNumeroEslabon());

		if (eslabonDTO.getObservacion() != null)
			eslabon.setObservacion(eslabonDTO.getObservacion());
		
		if(eslabonDTO.getDocumentoDTO()!=null){
			Documento docu=new Documento();
			docu.setDocumentoId(eslabonDTO.getDocumentoDTO().getDocumentoId());
			eslabon.setDocumento(docu);
		}
		eslabon.setUbicacionFisica(eslabonDTO.getUbicacionFisica());
		eslabon.setPosicion(eslabonDTO.getPosicion());
		eslabon.setEslabonId(eslabonDTO.getEslabonId());
		eslabon.setQuienEntrega(eslabonDTO.getQuienEntrega());
		eslabon.setQuienRecibe(eslabonDTO.getQuienRecibe());
		
		eslabon.setFechaInicioPrestamo(eslabonDTO.getFechaInicioPrestamo());
		eslabon.setFechaFinPrestamo(eslabonDTO.getFechaFinPrestamo());
		
		return eslabon;
	}

	
	public static EslabonDTO transformarEslabon(EslabonWSDTO input) {
		EslabonDTO eslabon = new EslabonDTO();
		
		eslabon.setDocumentoDTO(ExpedienteWSDTOTransformer.transforma(input.getDocumento()));
		eslabon.setFechaFinMovimiento(input.getFechaRecibe());
		eslabon.setFechaInicioMovimiento(input.getFechaEntrega());
		eslabon.setNumeroEslabon(input.getNumeroEslabon());
		eslabon.setObservacion(input.getObservacion());
		eslabon.setPosicion(input.getPosicion());
		eslabon.setTipoEslabon(new ValorDTO(input.getIdTpoEslabon()));
		eslabon.setUbicacionFisica(input.getUbicacionFisica());
		eslabon.setQuienEntrega(input.getQuienEntrega());
		eslabon.setQuienRecibe(input.getQuienRecibe());
		return eslabon;
	}
}
