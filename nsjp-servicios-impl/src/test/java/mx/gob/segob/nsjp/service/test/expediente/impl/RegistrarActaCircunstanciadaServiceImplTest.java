/**
 * Nombre del Programa : RegistrarActaCircunstanciadaServiceImplTest.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13/07/2011
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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ActaCircunstanciadaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarActaCircunstanciadaService;
import mx.gob.segob.nsjp.service.expediente.RegistrarActaCircunstanciadaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */



public class RegistrarActaCircunstanciadaServiceImplTest extends
		BaseTestServicios<RegistrarActaCircunstanciadaService> {
	
	@Autowired
	ConsultarActaCircunstanciadaService consultarActaCircunstanciadaService;
	

	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.service.expediente.impl.RegistrarActaCircunstanciadaServiceImpl#registrarActaCircunstanciada(mx.gob.segob.nsjp.dto.expediente.ActaCircunstanciadaDTO, mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)}
	 * .
	 */
	public void testRegistrarActaCircunstanciada() {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO(23L);
		InvolucradoDTO involucradoDTO = involucradoParaActa();
		HechoDTO hechoDTO = hechoParaActa();
		ActaCircunstanciadaDTO actaDTO = new ActaCircunstanciadaDTO(
				involucradoDTO, hechoDTO);
		try {
			service.registrarActaCircunstanciada(actaDTO, expedienteDTO);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
	
	private HechoDTO hechoParaActa() {
		HechoDTO hecho = new HechoDTO();
		hecho.setDescNarrativa("El hecho es que quiero probar el servicio hoy 8 de agosto");
		return hecho;
	}
	
	
	
	private InvolucradoDTO involucradoParaActa() {
		Short condicion = 1;
		List<TelefonoDTO> telefonosDTO=new ArrayList<TelefonoDTO>();
		telefonosDTO.add(new TelefonoDTO("52", "55", "55933942", new ValorDTO(67L)));
		List<CorreoElectronicoDTO> correosDTO=new ArrayList<CorreoElectronicoDTO>();
		correosDTO.add(new CorreoElectronicoDTO("aescorzac@ultrasist.com.mx"));
		
		CalidadDTO cualid=new CalidadDTO();
		cualid.setCalidades(Calidades.DENUNCIANTE);
		cualid.setCalidadId(Calidades.DENUNCIANTE.getValorId());
		DomicilioDTO domicilio = new DomicilioDTO("Av Revolución", "1181",
				"Piso 8", null, "Frente a ISSTE", "Barranca del Muerto",
				"Periferico", "Ultra", "Edificio", true);
		CalidadDTO calidadDTO=new CalidadDTO();
		calidadDTO.setCalidades(Calidades.DOMICILIO);
		calidadDTO.setCalidadId(Calidades.DOMICILIO.getValorId());
		domicilio.setCalidadDTO(calidadDTO);
		
		
		InvolucradoDTO involuc = new InvolucradoDTO(null, "Prueba de servicio de registro acta circunstanciada",
				false, false, condicion, Calidades.DENUNCIANTE.getValorId());
		
		/*Datos Generales*/
		involuc.setCalidadDTO(cualid);
		involuc.setNombresDemograficoDTO(nombresDemograficosParaInvoluc());
		
		/*Domicilio*/
		involuc.setDomicilio(domicilio);
		
		/*Medios de Contacto*/
		involuc.setCorreosDTO(correosDTO);
		involuc.setTelefonosDTO(telefonosDTO);
		
		
		return involuc;
	}

	private List<NombreDemograficoDTO> nombresDemograficosParaInvoluc() {
		List<NombreDemograficoDTO> nomDemo=new ArrayList<NombreDemograficoDTO>();
		NombreDemograficoDTO demo=new NombreDemograficoDTO();
		demo.setNombre("RICACHARDDDDDDDDD.");
		demo.setApellidoPaterno("GAMAS.");
		demo.setApellidoMaterno("GARCIASAAAAA.");
		demo.setEdadAproximada((short) 500);
		nomDemo.add(demo);
		return nomDemo;
	}
	
	
	public void testActualizarActaCircunstanciada() {
		ExpedienteDTO dto = new ExpedienteDTO(23L);
		ActaCircunstanciadaDTO acta;
		try {
			acta = consultarActaCircunstanciadaService
					.consultarActaCircunstaciada(dto);
			muestraInfoActa(acta);
//			CalidadDTO calidadDTO=new CalidadDTO();
//			calidadDTO.setCalidades(Calidades.DOMICILIO);
//			calidadDTO.setCalidadId(Calidades.DOMICILIO.getValorId());
//			acta.getInvolucradoDTO().getDomicilio().setCalidadDTO(calidadDTO);

			acta.getHechoDTO().setDescNarrativa("NUEVA NARRATIVA BY GAMASOFT test.&&&&&&&&&&&&");
			acta.setInvolucradoDTO(involucradoParaActa());
			acta.getInvolucradoDTO().setElementoId(acta.getInvolucradoDTO().getElementoId());
			//service.registrarActaCircunstanciada(acta, acta.getHechoDTO().getExpediente());			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}		

	}

	private void muestraInfoActa(ActaCircunstanciadaDTO acta) {
		if (acta.getHechoDTO() != null) {
			logger.info("idHecho: " + acta.getHechoDTO().getHechoId());
			logger.info("Desc: " + acta.getHechoDTO().getDescNarrativa());
		} else
			logger.info("No hay HECHO");
		if (acta.getInvolucradoDTO() != null) {
			logger.info("idInvol: "
					+ acta.getInvolucradoDTO().getElementoId());
			logger.info("nombre: "
					+ acta.getInvolucradoDTO().getNombreCompleto());
			if (acta.getInvolucradoDTO().getDomicilio() != null) {
				logger.info("**DomicilioID: "
						+ acta.getInvolucradoDTO().getDomicilio()
								.getElementoId());
				logger.info("**Domicilio: "
						+ acta.getInvolucradoDTO().getDomicilio()
								.getCalle());
			}
			if(acta.getInvolucradoDTO().getCorreosDTO()!=null){
				logger.info("$$Correos: "+acta.getInvolucradoDTO().getCorreosDTO().size());
				for (CorreoElectronicoDTO corr : acta.getInvolucradoDTO().getCorreosDTO()) {
					logger.info("correo: "+corr.getDireccionElectronica());
				}
				}
			if(acta.getInvolucradoDTO().getTelefonosDTO()!=null){
				logger.info("$$Telefonos: "+acta.getInvolucradoDTO().getTelefonosDTO().size());
				for (TelefonoDTO tel : acta.getInvolucradoDTO().getTelefonosDTO()) {
					logger.info("pais: "+tel.getCodigoPais());
					logger.info("area: "+tel.getCodigoArea());
					logger.info("telefono: "+tel.getNumeroTelefonico());
					logger.info("tipo: "+tel.getValorTipoTelefono().getValor());
				}
				}
		} else
			logger.info("No hay INVOLUCRADO");
		
	}
		

}
