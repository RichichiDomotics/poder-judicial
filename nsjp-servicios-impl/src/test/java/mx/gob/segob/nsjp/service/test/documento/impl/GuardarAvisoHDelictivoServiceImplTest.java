/**
 * 
 */
package mx.gob.segob.nsjp.service.test.documento.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.documento.GuardarAvisoHDelictivoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.service.usuario.UsuarioService;

/**
 * @author adrian
 *
 */
public class GuardarAvisoHDelictivoServiceImplTest extends BaseTestServicios<GuardarAvisoHDelictivoService> {
    @Autowired
    private UsuarioService usrService;
	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.documento.impl.GuardarAvisoHDelictivoServiceImpl#guardarAvisoHDelictivo(mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO)}.
	 */
	public void testGuardarAvisoHDelictivo() {
		AvisoHechoDelictivoDTO avisoDTO=new AvisoHechoDelictivoDTO();
//		avisoDTO.setDocumentoId();
		avisoDTO.setFechaAtencion(new Date());
		avisoDTO.setConsecutivoNotificacion("Not06");
		avisoDTO.setEstatus(new ValorDTO(EstatusNotificacion.NO_ATENDIDA.getValorId()));
		avisoDTO.setHechoDTO(new HechoDTO(6L));
		avisoDTO.setFormaDTO(new FormaDTO(1L));
		avisoDTO.setNombreDocumento("Notificacion AvisoHD");
		avisoDTO.setTipoDocumentoDTO(new ValorDTO(88L));
		avisoDTO.setCatDelito(new CatDelitoDTO(1L));
		avisoDTO.setFechaCreacion(new Date());
		Long idAviso;
		try {
			idAviso = service.guardarAvisoHDelictivo(avisoDTO);
			assertNotNull("Exito con el aviso", idAviso);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
	}
	
	public void testIngresarAvisoHechoDeictivoSSP(){
		AvisoHechoDelictivoDTO avisoDTO=new AvisoHechoDelictivoDTO();
		ExpedienteDTO expDTO = null;
		HechoDTO hechoDTO = new HechoDTO();
		hechoDTO.setDescNarrativa("Prueba AHDSSP");
		hechoDTO.setLugar(generarDomicilio(expDTO));
		avisoDTO.setHechoDTO(hechoDTO);
		

		avisoDTO.setConsecutivoNotificacion("SCRZ1");
		avisoDTO.setDomicilio("domicilio");
		avisoDTO.setEsAnonimo(true);
		avisoDTO.setFechaCitado(new Date());
//		(Audiencia_id
		avisoDTO.setFolioNotificacion("SCRZNot1");
		avisoDTO.setNombreImplicado("Implicado");
		avisoDTO.setApellidoPatImplicado("Apellido P");
		avisoDTO.setLugar("lugar");
		avisoDTO.setLugarCitado("lugarCitado");
		avisoDTO.setMotivo("motivo");
//		iClaveFuncionario
		avisoDTO.setNumeroCasoAsociado("numeroCasoAsociado");
		avisoDTO.setPenalidades("penalidades");
		avisoDTO.setPersonaAutoriza("personaAutoriza");
		avisoDTO.setPuestoAutoriza("puestoAutoriza");
//		 Notificacion_id
		try {
			avisoDTO = service.ingresarAvisoHechoDeictivoSSP(avisoDTO);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private DomicilioDTO generarDomicilio(ExpedienteDTO expedienteDTO){
		DomicilioDTO domicilio = new DomicilioDTO();
		domicilio.setCalle("calle1N");
		domicilio.setNumeroExterior("10N");
		domicilio.setNumeroInterior("201N");
		domicilio.setNumeroLote("LoteN");
		domicilio.setReferencias("Ref MODN");
		domicilio.setEntreCalle1("entre calle 1N");
		domicilio.setEntreCalle2("entre calle 2N");
		domicilio.setAlias("RanchitoN");
		domicilio.setEdificio("CN");
		domicilio.setAsentamientoDTO(new AsentamientoDTO(20684L));
		domicilio.setEntidadDTO(new EntidadFederativaDTO(23L));
		domicilio.setMunicipioDTO(new MunicipioDTO(1L));
		domicilio.setCiudadDTO(new CiudadDTO(1L));
		domicilio.setValorCalleId(new ValorDTO(72L)); //Tipo Calle
		
		domicilio.setFechaCreacionElemento(new Date());
		//Lugar -> Elemento
		domicilio.setDescripcion("descripcion domicilio 1N");
		//CoordenadaGeografica - Lugar
		domicilio.setLatitud("100");
		domicilio.setLongitud("101");
		//Calidad de Domicilio
		CalidadDTO calidadDomicilio = new CalidadDTO();
		calidadDomicilio.setDescripcionEstadoFisico("En buen estado");
		calidadDomicilio.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
		calidadDomicilio.setCalidades(Calidades.DOMICILIO);				
		domicilio.setCalidadDTO(calidadDomicilio);
		domicilio.setExpedienteDTO(expedienteDTO);
		return domicilio;
	}


    public void testAtenderAvisoHechoDelictivo() {
        try {
            AvisoHechoDelictivoDTO input = new AvisoHechoDelictivoDTO();
            input.setDocumentoId(67L);
            UsuarioDTO usrParam = new UsuarioDTO();
            usrParam.setClaveUsuario("sspPolicia");
            usrParam.setPassword("password");
            input.setUsuario(usrService.login(usrParam));
            
            service.atenderAvisoHechoDelictivo(input);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

}
