/**
 * Nombre del Programa : RegistrarCedulaDeIdentificacionServiceImplTest.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Jul 2011
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
package mx.gob.segob.nsjp.service.test.expediente.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.MediaFiliacionDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.service.expediente.RegistrarCedulaDeIdentificacionService;
import mx.gob.segob.nsjp.service.solicitud.RecibirSolicitudCiudadanaDefensoriaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class RegistrarCedulaDeIdentificacionServiceImplTest extends
		BaseTestServicios<RegistrarCedulaDeIdentificacionService> {

	
	public void testRegistrarCedula_DatosGenerales(){
		ExpedienteDTO expDTO = new ExpedienteDTO();
		expDTO.setExpedienteId(1L);
		InvolucradoDTO imputado = new InvolucradoDTO();
		imputado.setExpedienteDTO(expDTO);
		
		/* SECCION DATOS GENERALES DEL IMPUTADO */
		//Se registra el nombre completo del imputado
		List<NombreDemograficoDTO> nombresDemograficoDTO = new ArrayList<NombreDemograficoDTO>();
		NombreDemograficoDTO demografico =  new NombreDemograficoDTO();
		demografico.setNombre("Nombre_Imputado");
		demografico.setApellidoMaterno("ApeMat_Imputado");
		demografico.setApellidoPaterno("ApePat_Imputado");
		demografico.setCurp("CURP_IMPUTADO");
		demografico.setRfc("RFC_IMPUTADO");
		demografico.setSexo("M");
		demografico.setFechaNacimiento(new Date());
		nombresDemograficoDTO.add(demografico);		
		imputado.setNombresDemograficoDTO(nombresDemograficoDTO );	

		
		imputado.setValorIdIdioma(new ValorDTO(54L));
		imputado.setValorIdEscolaridad(new ValorDTO(109L));
		imputado.setValorIdEstadoCivil(new ValorDTO(107L));
		
		//Se agregan ocupaciones al involucrado
		List<ValorDTO> ocupaciones = new ArrayList<ValorDTO>();
		ocupaciones.add(new ValorDTO(132L));
		ocupaciones.add(new ValorDTO(139L));
		ocupaciones.add(new ValorDTO(148L));
		imputado.setValorIdOcupacion(ocupaciones);
		
		//Se agregan alias al involucrado
		List<AliasInvolucradoDTO> aliasInvolucradoDTO = new ArrayList<AliasInvolucradoDTO>();
		AliasInvolucradoDTO alias1 = new AliasInvolucradoDTO();
		alias1.setAlias("perro");
		aliasInvolucradoDTO.add(alias1);
		AliasInvolucradoDTO alias2 = new AliasInvolucradoDTO();
		alias2.setAlias("tarzan");
		aliasInvolucradoDTO.add(alias2);
		imputado.setAliasInvolucradoDTO(aliasInvolucradoDTO);
		
		//Se agregan nacionalidades		
		List<ValorDTO> nacionalidades = new ArrayList<ValorDTO>();
		nacionalidades.add(new ValorDTO(176L));
		nacionalidades.add(new ValorDTO(181L));
		nacionalidades.add(new ValorDTO(191L));
		imputado.setValorIdNacionalidad(nacionalidades);			
		imputado.setEsDetenido(true);
		
		try{
			
			InvolucradoDTO respuesta =  this.service.registrarCedula(imputado);
			assertTrue("El id generado no puede ser menor a cero ", respuesta.getElementoId()>=0);
			
		}catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}	
	

	public void testRegistrarCedula_MediaFiliacion(){
		ExpedienteDTO expDTO = new ExpedienteDTO();
		expDTO.setExpedienteId(1L);
		InvolucradoDTO imputado = new InvolucradoDTO();
		imputado.setExpedienteDTO(expDTO);
	
		MediaFiliacionDTO mediaFiliacionDTO = new MediaFiliacionDTO();
		mediaFiliacionDTO.setPeso(180.5D);
		mediaFiliacionDTO.setEstatura(100D);
		/* SECCION MEDIA FILIACION */
		imputado.setMediaFiliacionDTO(mediaFiliacionDTO );
		
		try{
			
			InvolucradoDTO respuesta =  this.service.registrarCedula(imputado);
			assertTrue("El id generado no puede ser menor a cero ", respuesta.getElementoId()>=0);
			
		}catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}	
	

	public void testRegistrarCedula_Delitos(){
		ExpedienteDTO expDTO = new ExpedienteDTO();
		expDTO.setExpedienteId(1L);
		InvolucradoDTO imputado = new InvolucradoDTO();
		imputado.setExpedienteDTO(expDTO);
		
		/* SECCION DELITOS DEL IMPUTADO */		
		List<DelitoDTO> delitosCometidos = new ArrayList<DelitoDTO>();
		DelitoDTO delito1 = new DelitoDTO();
		delito1.setDelitoId(1L);		
		DelitoDTO delito2 = new DelitoDTO();
		delito2.setDelitoId(3L);
		delitosCometidos.add(delito1);
		delitosCometidos.add(delito2);		
		imputado.setDelitosCometidos(delitosCometidos);
		
		try{
			
			InvolucradoDTO respuesta =  this.service.registrarCedula(imputado);
			assertTrue("El id generado no puede ser menor a cero ", respuesta.getElementoId()>=0);
			
		}catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}	
	

	public void testRegistrarCedula_Hechos(){
		ExpedienteDTO expDTO = new ExpedienteDTO();
		expDTO.setExpedienteId(1L);
		InvolucradoDTO imputado = new InvolucradoDTO();
		imputado.setExpedienteDTO(expDTO);
		
		/* SECCION DELITOS DEL IMPUTADO */		
		List<DelitoDTO> delitosCometidos = new ArrayList<DelitoDTO>();
		DelitoDTO delito1 = new DelitoDTO();
		delito1.setDelitoId(1L);		
		DelitoDTO delito2 = new DelitoDTO();
		delito2.setDelitoId(3L);
		delitosCometidos.add(delito1);
		delitosCometidos.add(delito2);		
		imputado.setDelitosCometidos(delitosCometidos);
		
		try{
			
			InvolucradoDTO respuesta =  this.service.registrarCedula(imputado);
			assertTrue("El id generado no puede ser menor a cero ", respuesta.getElementoId()>=0);
			
		}catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}	
		

}
