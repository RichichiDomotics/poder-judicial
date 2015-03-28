/**
* Nombre del Programa 		: FuncionarioExternoTransformer.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 							Fecha: 13 Apr 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Describir el objetivo de la clase de manera breve
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 							Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.funcionarioexterno.impl.transform;

import mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.FuncionarioExterno;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.ConfInstitucionTransformer;

/**
 * Transformer que lleva a cabo las conversiones de objetos entre entities y DTO's 
 * para los funcionarios Externos.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class FuncionarioExternoTransformer {

	/**
	 * M&eacute;todo que lleva a cabo la conversi&oacute;n de un objeto de tipo entidad 
	 * a un objeto de transferencia de datos para el funcionario externo.
	 * @param funcionarioExterno - El entity a transformar.
	 * @return funcionario - El funcionarioExternoDTO transformado.
	 */
	public static FuncionarioExternoDTO transformar (FuncionarioExterno funcionarioExterno){
		FuncionarioExternoDTO funcionario = null;
		if (funcionarioExterno != null){
			funcionario = new FuncionarioExternoDTO();
			funcionario.setApellidoMaterno(funcionarioExterno.getApellidoMaterno());
			funcionario.setApellidoPaterno(funcionarioExterno.getApellidoPaterno());
			funcionario.setArea(funcionarioExterno.getArea());
			funcionario.setConfInstitucionDTO(ConfInstitucionTransformer.transformarInstitucion(funcionarioExterno.getConfInstitucion()));
			funcionario.setCveFuncionarioInstExt(funcionarioExterno.getCveFuncionarioInstExt());
			funcionario.setEmail(funcionarioExterno.getEmail());
			funcionario.setFuncionarioExternoId(funcionarioExterno.getFuncionarioExternoId());
			funcionario.setNombre(funcionarioExterno.getNombre());
			funcionario.setPuesto(funcionarioExterno.getPuesto());
		}
		return funcionario;
	}
	
	/**
	 * M&eacute; que lleva a cabo la transformaci&oacute;n de un objeto de transferencia de
	 * datos a un objeto de tipo entidad.
	 * @param funcionarioExternoDTO - El DTO a transformar
	 * @return funcionario - La entidad FuncionarioExterno transformada.
	 */
	public static FuncionarioExterno transformar (FuncionarioExternoDTO funcionarioExternoDTO){
		FuncionarioExterno funcionario = null;
		if (funcionarioExternoDTO != null){
			funcionario = new FuncionarioExterno();
			funcionario.setApellidoMaterno(funcionarioExternoDTO.getApellidoMaterno());
			funcionario.setApellidoPaterno(funcionarioExternoDTO.getApellidoPaterno());
			funcionario.setArea(funcionarioExternoDTO.getArea());
			
			if (funcionarioExternoDTO.getConfInstitucionDTO() != null && funcionarioExternoDTO.getConfInstitucionDTO().getConfInstitucionId() != null){
				ConfInstitucion confInstitucion = new ConfInstitucion();
				confInstitucion.setConfInstitucionId(funcionarioExternoDTO.getConfInstitucionDTO().getConfInstitucionId());
				funcionario.setConfInstitucion(confInstitucion);
			}
			
			funcionario.setCveFuncionarioInstExt(funcionarioExternoDTO.getCveFuncionarioInstExt());
			funcionario.setEmail(funcionarioExternoDTO.getEmail());
			funcionario.setFuncionarioExternoId(funcionarioExternoDTO.getFuncionarioExternoId());
			funcionario.setNombre(funcionarioExternoDTO.getNombre());
			funcionario.setPuesto(funcionarioExternoDTO.getPuesto());
		}
		return funcionario;
	}
}
