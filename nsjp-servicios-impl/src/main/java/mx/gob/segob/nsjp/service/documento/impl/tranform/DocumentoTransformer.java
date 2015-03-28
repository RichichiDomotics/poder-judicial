/**
 * Nombre del Programa : DocumentoTransformer.java
 * Autor                            : Emigdio Hern�ndez
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 31/05/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Clase de transformaci�n del Documento
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

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.dto.medida.IncidenciaDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.CartaCumplimiento;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Medida;
import mx.gob.segob.nsjp.model.Incidencia;
import mx.gob.segob.nsjp.model.MedidaAlterna;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.OficioEstructurado;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ActividadTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.forma.impl.transform.FormaTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.ConfInstitucionTransformer;

/**
 * Clase de transformaci�n del Documento
 * 
 * @version 1.0
 * @author Emigdio Hern�ndez
 * 
 */
public class DocumentoTransformer {

	/**
	 * Crea un objeto del tipo <code>DocumentoDTO</code> a partir de los datos
	 * de un objeto <code>Documento</code>
	 * 
	 * @param src
	 *            Datos fuente
	 * @return Objeto con los datos del objeto fuente
	 */
	public static DocumentoDTO transformarDocumento(Documento src) {
		DocumentoDTO dto = null;
		if (src != null) {
			// TODO GBP Revisar si no afecta en otros servicios - Utilizado en
			// MedidaTransformer
			// Depende de que instancia es la que se desea transformar es como
			// debe de
			// castearse en en la especializacion
			if (src instanceof Incidencia)
				dto = new IncidenciaDTO();
			else if (src instanceof MedidaAlterna)
				dto = new MedidaAlternaDTO();
			else if (src instanceof Medida)
				dto = new MedidaDTO();
			else
				dto = new DocumentoDTO();
			if (src.getActividad() != null) {
				dto.setActividadDTO(ActividadTransformer
						.transformarActividad(src.getActividad()));
				if (src.getActividad().getExpediente() != null)
					dto.setExpedienteDTO(ExpedienteTransformer
							.transformarExpedienteBasico(src.getActividad()
									.getExpediente()));
			}
			dto.setDocumentoId(src.getDocumentoId());
			dto.setStrFechaCreacion(DateUtils.formatear(src.getFechaCreacion()));
			dto.setFechaCreacion(src.getFechaCreacion());
			dto.setFolioDocumento(src.getFolioDocumento());
			dto.setFormaDTO(FormaTransformer.transformarForma(src.getForma()));
			dto.setNombreDocumento(src.getNombreDocumento());
			dto.setNumeroFojas(src.getNumeroFojas());
			dto.setOrigenDocumento(src.getOrigenDocumento());
			Funcionario responsableDocumento = src.getResponsableDocumento();
			if (responsableDocumento != null) {
				FuncionarioDTO responsableDto = FuncionarioTransformer
						.transformarFuncionario(responsableDocumento);
				dto.setResponsableDocumento(responsableDto);
			}
			dto.setTextoParcial(src.getTextoParcial());

			if (src.getTipoDocumento() != null)
				dto.setTipoDocumentoDTO(new ValorDTO(src.getTipoDocumento()
						.getValorId(), src.getTipoDocumento().getValor()));
			dto.setVersion(src.getVersion());
			
			if(src.getEsGuardadoParcial()!=null)
				dto.setEsGuardadoParcial(src.getEsGuardadoParcial());

			if (src.getConfInstitucion() != null){
				ConfInstitucionDTO confInstitucionDTO = ConfInstitucionTransformer.transformarInstitucion(src.getConfInstitucion());
				dto.setConfInstitucion(confInstitucionDTO);
				
			}
			
		}
		return dto;
	}

	/**
	 * Crea un objeto del tipo <code>Documento</code> a partir de los datos de
	 * un objeto <code>DocumentoDTO</code>
	 * 
	 * @param src
	 *            Datos fuente
	 * @return Objeto con los datos del objeto fuente
	 */
	public static Documento transformarDocumentoDTO(DocumentoDTO src) {
		Documento doc = null;
		if (src != null) {
			// TODO GBP Revisar si no afecta en otros servicios - Utilizado en
			// MedidaTransformer
			// Depende de que instancia es la que se desea transformar es como
			// debe de
			// castearse en en la especializacion
			if (src instanceof MedidaAlternaDTO)
				doc = new MedidaAlterna();
			else
				doc = new Documento();

			doc.setDocumentoId(src.getDocumentoId());
			doc.setArchivoDigital(ArchivoDigitalTransformer
					.transformarArchivoDigitalDTO(src.getArchivoDigital()));
			doc.setFechaCreacion(src.getFechaCreacion());
			doc.setFolioDocumento(src.getFolioDocumento());
			doc.setForma(FormaTransformer.transformarFormaDTO(src.getFormaDTO()));
			doc.setNombreDocumento(src.getNombreDocumento());
			doc.setNumeroFojas(src.getNumeroFojas());
			doc.setOrigenDocumento(src.getOrigenDocumento());
			src.getResponsableDocumento();
			FuncionarioDTO responsableDocumentoDto = src
					.getResponsableDocumento();
			if (responsableDocumentoDto != null) {
				Funcionario funcionario = FuncionarioTransformer
						.transformarFuncionario(responsableDocumentoDto);
				doc.setResponsableDocumento(funcionario);
			}
			doc.setTextoParcial(src.getTextoParcial());
			ValorDTO tipoDocumentoDTO = src.getTipoDocumentoDTO();
			if (tipoDocumentoDTO != null) {
				doc.setTipoDocumento(new Valor(tipoDocumentoDTO.getIdCampo()));
			}
			doc.setVersion(src.getVersion());

			if(src.getEsGuardadoParcial()!=null)
				doc.setEsGuardadoParcial(src.getEsGuardadoParcial());
			
			if (src.getOficioEstructuradoDTO() != null)
				doc.setOficioEstructurado(new OficioEstructurado(src
						.getOficioEstructuradoDTO().getOficioEstructuradoId()));
			doc.setDescripcion(src.getDescripcion());

			if (src.getConfInstitucion() != null ) {
				ConfInstitucion confInstitucion = new ConfInstitucion();
				confInstitucion.setConfInstitucionId(src.getConfInstitucion().getConfInstitucionId());
				doc.setConfInstitucion(confInstitucion);
			}
						
		}
		return doc;

	}

	/**
	 * Actualiza un objeto de entidad de <code>Documento</code> en base a los
	 * datos de un <code>DocumentoDTO</code>
	 * 
	 * @param destino
	 *            objeto a actualizar
	 * @param src
	 *            Datos fuente
	 */
	public static void tranformarDocumentoUpdate(Documento destino,
			DocumentoDTO src) {
		if (destino != null) {

			if (src.getArchivoDigital() != null) {
				if (destino.getArchivoDigital() != null) { // actualizar el
															// archivo digital
					ArchivoDigitalTransformer.transformarArchivoDigitalUpdate(
							destino.getArchivoDigital(),
							src.getArchivoDigital());
				} else {
					// crear archivo digital
					destino.setArchivoDigital(ArchivoDigitalTransformer
							.transformarArchivoDigitalDTO(src
									.getArchivoDigital()));
				}
			}

			destino.setFechaCreacion(src.getFechaCreacion());
			destino.setFolioDocumento(src.getFolioDocumento());
			destino.setForma(FormaTransformer.transformarFormaDTO(src
					.getFormaDTO()));
			destino.setNombreDocumento(src.getNombreDocumento());
			destino.setNumeroFojas(src.getNumeroFojas());
			destino.setOrigenDocumento(src.getOrigenDocumento());
			// TODO DAJV Revisar si la propiedad responsableDocumento es util o
			// no
			// FuncionarioDTO responsableDocumentoDto =
			// src.getResponsableDocumento();
			// if(responsableDocumentoDto != null){
			// Funcionario funcionario =
			// FuncionarioTransformer.transformarFuncionario(responsableDocumentoDto);
			// destino.setResponsableDocumento(funcionario);
			// }
			destino.setTextoParcial(src.getTextoParcial());
			if (src.getTipoDocumentoDTO() != null) {
				destino.setTipoDocumento(new Valor(src.getTipoDocumentoDTO()
						.getIdCampo()));
			}
			
			if(src.getEsGuardadoParcial()!=null)
				destino.setEsGuardadoParcial(src.getEsGuardadoParcial());

			destino.setVersion(src.getVersion());
			if (src.getOficioEstructuradoDTO() != null) {
				destino.setOficioEstructurado(new OficioEstructurado(src
						.getOficioEstructuradoDTO().getOficioEstructuradoId()));
			}
			
			if (src.getConfInstitucion() != null ) {
				ConfInstitucion confInstitucion = new ConfInstitucion();
				confInstitucion.setConfInstitucionId(src.getConfInstitucion().getConfInstitucionId());
				destino.setConfInstitucion(confInstitucion);
			}
		}
	}

	public static DocumentoDTO transformarDatosLista(Documento doc) {
		DocumentoDTO dto = null;
		if (doc != null) {
			dto = new DocumentoDTO();
			dto.setDocumentoId(doc.getDocumentoId());
			dto.setNombreDocumento(doc.getNombreDocumento());
			// - N�mero de Expediente
			// - N�mero de Caso
			Expediente expediente = doc.getExpediente();
			if (expediente != null) {
				dto.setExpedienteDTO(ExpedienteTransformer
						.transformaExpediente(expediente));
			}

			// - Tipo de Documento
			if (doc.getTipoDocumento() != null)
				dto.setTipoDocumentoDTO(new ValorDTO(doc.getTipoDocumento()
						.getValorId(), doc.getTipoDocumento().getValor()));
			// - Nombre del perito
			// - Especialidad
			// - Fecha de Recepci�n
			dto.setFechaCreacion(doc.getFechaCreacion());
			
			if(doc.getEsGuardadoParcial()!=null)
				dto.setEsGuardadoParcial(doc.getEsGuardadoParcial());

			if (doc.getOficioEstructurado() != null) {
				dto.setOficioEstructuradoDTO(OficioEstructuradoTransformer
						.transformarOficio(doc.getOficioEstructurado()));
			}
			dto.setDescripcion(doc.getDescripcion());
			dto.setFolioDocumento(doc.getFolioDocumento());

		}
		return dto;
	}

	public static CartaCumplimientoDTO transformarControversia(
			CartaCumplimiento doc) {
		CartaCumplimientoDTO dto = new CartaCumplimientoDTO();

		dto.setDocumentoId(doc.getDocumentoId());
		if (doc.getActividad() != null) {
			dto.setActividadDTO(ActividadTransformer.transformarActividad(doc
					.getActividad()));
			if (doc.getActividad().getExpediente() != null)
				dto.setExpedienteDTO(ExpedienteTransformer
						.transformaExpediente(doc.getActividad()
								.getExpediente()));

		}
		dto.setDocumentoId(doc.getDocumentoId());
		dto.setStrFechaCreacion(DateUtils.formatear(doc.getFechaCreacion()));
		dto.setFechaCreacion(doc.getFechaCreacion());
		dto.setFolioDocumento(doc.getFolioDocumento());
		dto.setFormaDTO(FormaTransformer.transformarForma(doc.getForma()));
		dto.setNombreDocumento(doc.getNombreDocumento());
		dto.setNumeroFojas(doc.getNumeroFojas());
		dto.setOrigenDocumento(doc.getOrigenDocumento());
		Funcionario responsableDocumento = doc.getResponsableDocumento();
		if (responsableDocumento != null) {
			FuncionarioDTO responsableDto = FuncionarioTransformer
					.transformarFuncionario(responsableDocumento);
			dto.setResponsableDocumento(responsableDto);
		}
		dto.setTextoParcial(doc.getTextoParcial());

		if (doc.getTipoDocumento() != null)
			dto.setTipoDocumentoDTO(new ValorDTO(doc.getTipoDocumento()
					.getValorId(), doc.getTipoDocumento().getValor()));
		dto.setVersion(doc.getVersion());
		dto.setArchivoDigital(ArchivoDigitalTransformer
				.transformarArchivoDigital(doc.getArchivoDigital()));

		dto.setEsLeido(false);
		if (doc != null) {
			if (doc.getEsLeido() != null)
				dto.setEsLeido(doc.getEsLeido());
			dto.setFechaLectura(doc.getFechaLectura());
			if (doc.getFuncionario() != null)
				dto.setFuncionario(FuncionarioTransformer
						.transformarFuncionario(doc.getFuncionario()));
		}
		return dto;
	}
}
