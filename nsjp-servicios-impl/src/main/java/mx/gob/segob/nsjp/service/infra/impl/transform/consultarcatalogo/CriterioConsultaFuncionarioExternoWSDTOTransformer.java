/**
* Nombre del Programa 		: CriterioConsultaFuncionarioExternoWSDTOTransformer.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 								Fecha: 10 Apr 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Transformer para hacer consultas de funcionarios externos 
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 								Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 								Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.infra.impl.transform.consultarcatalogo;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;
import mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoDTO;
import mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoWSDTO;
import mx.gob.segob.nsjp.service.infra.impl.transform.enviarcarpetainvestigacion.ExpedienteWSDTOTransformer;

/**
 * Transformer que lleva a cabo las conversiones entre los dtos que se ocupan dentro 
 * del sistema y los ocupados por los web services.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class CriterioConsultaFuncionarioExternoWSDTOTransformer extends
		GenericWSDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4936579198937481570L;
	
	/**
	 * M&eacute;todo utilitario que lleva a cabo la conversi&oacute;n de 
	 * un CriterioConsultaFuncionarioExternoWSDTO a un CriterioConsultaFuncionarioExternoDTO.
	 * @param criterioConsultaFuncionarioExternoWSDTO - El WSDTO a transformar.
	 * @return criterioConsultaFuncionarioExternoDTO - El DTO transformado.
	 */
	public static CriterioConsultaFuncionarioExternoDTO transformar(
			CriterioConsultaFuncionarioExternoWSDTO criterioConsultaFuncionarioExternoWSDTO){
		CriterioConsultaFuncionarioExternoDTO criterio = null;
		if (criterioConsultaFuncionarioExternoWSDTO != null){
			criterio = new CriterioConsultaFuncionarioExternoDTO();
			criterio.setExpendienteDTO(ExpedienteWSDTOTransformer.transforma(criterioConsultaFuncionarioExternoWSDTO.getExpedienteWSDTO()));
			criterio.setFuncionarioDTO(FuncionarioWSDTOTransformer.transformarFuncionario(criterioConsultaFuncionarioExternoWSDTO.getFuncionarioWSDTO()));
			criterio.setRolDTO(RolWSDTOTransformer.transformar(criterioConsultaFuncionarioExternoWSDTO.getRolWSDTO()));
		}
		return criterio;

	}
	
	/**
	 * M&eacute;todo utilitario que lleva a cabo la conversi&oacute;n de 
	 * un CriterioConsultaFuncionarioExternoDTO a un CriterioConsultaFuncionarioExternoWSDTO.
	 * @param criterioConsultaFuncionarioExternoDTO - El DTO a transformar.
	 * @return criterioConsultaFuncionarioExternoWSDTO - El WSDTO transformado.
	 */
	public static CriterioConsultaFuncionarioExternoWSDTO transformar(
			CriterioConsultaFuncionarioExternoDTO criterioConsultaFuncionarioExternoDTO){
		CriterioConsultaFuncionarioExternoWSDTO criterio = null;
		if (criterioConsultaFuncionarioExternoDTO != null){
			criterio = new CriterioConsultaFuncionarioExternoWSDTO();
			criterio.setExpedienteWSDTO(ExpedienteWSDTOTransformer.transformaDTO(criterioConsultaFuncionarioExternoDTO.getExpendienteDTO()));
			criterio.setFuncionarioWSDTO(FuncionarioWSDTOTransformer.transformarFuncionario(criterioConsultaFuncionarioExternoDTO.getFuncionarioDTO()));
			criterio.setRolWSDTO(RolWSDTOTransformer.transformar(criterioConsultaFuncionarioExternoDTO.getRolDTO()));
		}
		return criterio;

	}
	
	/**
	 * M&eacute;todo utilitario que lleva a cabo la conversi&oacute;n de un
	 * CriterioConsultaFuncionarioExternoDTO a un
	 * CriterioConsultaFuncionarioExternoWSDTO.
	 * 
	 * @param criterioConsultaFuncionarioExternoDTO
	 *            - El DTO a transformar.
	 * @return criterioConsultaFuncionarioExternoWSDTO - El WSDTO transformado.
	 */
	public static mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno.CriterioConsultaFuncionarioExternoWSDTO transformarWSDTOCliente(
			CriterioConsultaFuncionarioExternoDTO criterioConsultaFuncionarioExternoDTO) {
		mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno.CriterioConsultaFuncionarioExternoWSDTO criterio = null;
		if (criterioConsultaFuncionarioExternoDTO != null) {
			criterio = new mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno.CriterioConsultaFuncionarioExternoWSDTO();
			
			criterio.setExpedienteWSDTO(ExpedienteWSDTOTransformer.transformaWSDTOCliente(
					criterioConsultaFuncionarioExternoDTO.getExpendienteDTO()));
			
			criterio.setFuncionarioWSDTO(FuncionarioWSDTOTransformer.transformarWSDTOCliente(
					criterioConsultaFuncionarioExternoDTO.getFuncionarioDTO()));
			
			criterio.setRolWSDTO(RolWSDTOTransformer.transformarWSDTOCliente(criterioConsultaFuncionarioExternoDTO.getRolDTO()));
		}
		return criterio;

	}

}
