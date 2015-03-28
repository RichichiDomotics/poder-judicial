/**
* Nombre del Programa : MandamientoJudicialServiceImplTest.java
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
package mx.gob.segob.nsjp.service.test.documento.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.documento.EstatusMandamiento;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.solicitud.TipoMandamiento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.MandamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.documento.MandamientoJudicialService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de pruebas unitarias para el servicio de manipulación de un mandamiento judicial
 * @version 1.0
 * @author Emigdio
 *
 */
public class MandamientoJudicialServiceImplTest extends BaseTestServicios<MandamientoJudicialService> {

	public void testRegistrarMandamiento(){
		MandamientoDTO mandamiento = new MandamientoDTO();
		mandamiento.setResolutivo(new ResolutivoDTO());
		mandamiento.getResolutivo().setResolutivoId(26L);
		mandamiento.setFormaDTO(new FormaDTO(Formas.MEDIDA_CAUTELAR.getValorId()));
		mandamiento.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.DOCUMENTO.getValorId()));
		mandamiento.setTipoMandamiento(new ValorDTO(TipoMandamiento.ORDEN_DE_APREHENSION.getValorId()));
		mandamiento.setNombreDocumento("Mandamiento Judicial");
		mandamiento.setFechaCreacion(new Date());
		mandamiento.setEstatus(new ValorDTO(EstatusMandamiento.EN_PROCESO.getValorId()));
		
		mandamiento.setFechaInicial(new Date());
		mandamiento.setFechaFinal(new Date());
		mandamiento.setInvolucrado(new InvolucradoDTO(1L));
		
		DomicilioDTO domicilioDTO = new DomicilioDTO("Calle", "3", "4", "", "", "", "", "", "", false);
		domicilioDTO.setFechaCreacionElemento(new Date());
		CalidadDTO calidadDomicilio = new CalidadDTO();
		calidadDomicilio.setDescripcionEstadoFisico("En buen estado");
		calidadDomicilio.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
		calidadDomicilio.setCalidades(Calidades.DOMICILIO);				
		domicilioDTO.setCalidadDTO(calidadDomicilio);
		
		mandamiento.setDomicilio(domicilioDTO);
		
		try {
			mandamiento = service.registrarMandamientoJudicial(mandamiento);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
		logger.debug("Nuevo mandamiento : " + mandamiento.getDocumentoId());
		logger.debug("Numero expediente: " + mandamiento.getResolutivo().getAudiencia().getExpediente().getNumeroExpediente());
	}
	
	
	public void testConsultarMandamientosPorCausa(){
		List<MandamientoDTO> res;
		try {
			UsuarioDTO usuario = new UsuarioDTO();
			FuncionarioDTO funcionario = new FuncionarioDTO();
			CatDiscriminanteDTO catDis = new CatDiscriminanteDTO();
			
			funcionario.setDiscriminante(catDis);
			usuario.setFuncionario(funcionario);
			
			res = service.consultarMandamientosPorNumeroExpediente("NSJYUCPJ20111233333",usuario);
			logger.debug("Numero de mandamientos:" + res.size());
			for (MandamientoDTO mandamientoDTO : res) {
				logger.info(" Mandamiento Id:"+ mandamientoDTO.getDocumentoId());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
	}
	
	public void testConsultarMandamientosPorFiltro(){
		List<MandamientoDTO> res;
		try {
			MandamientoDTO mandamientoDTO= new MandamientoDTO();
			
			String numeroExpediente="NSJYUCPJ20112533339";
			//String numeroExpediente="";
			
			Long estado=EstatusMandamiento.EN_PROCESO.getValorId();			
			mandamientoDTO.setEstatus(new ValorDTO(estado));
			
			Date fechaInicial= new Date();
			Date fechaFinal= new Date();			
			
			//fechaInicial.setTime(Date.parse("Dec 20, 2011"));
			
			fechaInicial=DateUtils.obtener("10/12/2011");
			mandamientoDTO.setFechaInicial(fechaInicial);
			
			fechaFinal=DateUtils.obtener("20/12/2011");
			mandamientoDTO.setFechaFinal(fechaFinal);		
						
			res=service.consultarMandamientoPorFiltro(mandamientoDTO, numeroExpediente);			
			logger.debug("Numero de mandamientos:" + res.size());
			for (MandamientoDTO mandamientoDTO_i : res) {
				logger.info(" Mandamiento Id:"+ mandamientoDTO_i.getDocumentoId());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Prueba unitaria para probar el envío de mandamientos judiciales a SSP
	 * @author Emigdio Hernández
	 */
	public void testEnviarMandamientoJudicial(){
		try {
			service.enviarMandamientoJudicial(267L);
			logger.debug("Mandamiento enviado:" + 267);
		} catch (NSJPNegocioException e) {
			
			e.printStackTrace();
			fail();
		}
		
		
	}
}
