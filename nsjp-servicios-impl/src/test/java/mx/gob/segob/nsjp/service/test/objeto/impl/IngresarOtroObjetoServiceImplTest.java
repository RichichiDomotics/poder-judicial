/**
 * 
 */
package mx.gob.segob.nsjp.service.test.objeto.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.service.objeto.IngresarOtroObjetoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author GustavoBP
 *
 */
public class IngresarOtroObjetoServiceImplTest 
	extends BaseTestServicios<IngresarOtroObjetoService>  {

	public void testIngresarOtroObjeto(){
		
		//Crear un nuevo Objeto
		//ObjetoDTO objetoDTO = new ObjetoDTO();
		//Actualizar los datos del nuevo objeto
		Long elementoId = 4859L;
		ObjetoDTO objetoDTO = new ObjetoDTO(elementoId);
		
		objetoDTO.setDescripcion("Descripción del Objeto Otro---");
		
		CalidadDTO calidadDTO = new CalidadDTO();
		calidadDTO.setCalidades(Calidades.EVIDENCIA);
		objetoDTO.setCalidadDTO(calidadDTO );
		
		//Tipo de objeto
        objetoDTO.setTipoObjeto(Objetos.OTRO);
        
		Long expedienteId = 1L;
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setExpedienteId(expedienteId);
		objetoDTO.setExpedienteDTO(expedienteDTO );
		
		objetoDTO.setValorByCondicionFisicaVal(new ValorDTO( 431L )); //431	Buen estado
		objetoDTO.setFechaCreacionElemento(new Date());
		
		Long idArchivoDigital = 1L;;
		ArchivoDigitalDTO fotoDelElemento = new ArchivoDigitalDTO(idArchivoDigital );
		objetoDTO.setFotoDelElemento(fotoDelElemento);
		
		
		try{
			Long idObjeto = service.ingresarOtroObjeto(objetoDTO);
			assertNotNull("El objeto no se ingreso correctamente", idObjeto);
			logger.info(" El objeto de Tipo Otro se ingreso correctamente. IdObjeto:"+ idObjeto);
		}catch(NSJPNegocioException ex){
			 fail("Ocurrio una excepcion al ejecutar el testIngresarOtroObjeto");
		}
			
	}
}
