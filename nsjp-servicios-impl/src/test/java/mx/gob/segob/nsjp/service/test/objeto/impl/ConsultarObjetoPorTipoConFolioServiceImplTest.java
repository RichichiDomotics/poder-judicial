/**
 * 
 */
package mx.gob.segob.nsjp.service.test.objeto.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;
import mx.gob.segob.nsjp.dto.objeto.EquipoComputoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.service.objeto.ConsultarObjetosPorTipoConFolioDeCustodiaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author rgama
 *
 */
public class ConsultarObjetoPorTipoConFolioServiceImplTest extends
		BaseTestServicios<ConsultarObjetosPorTipoConFolioDeCustodiaService> {

	public void testConsultarObjetosPorTipo() {
		try {
			ExpedienteDTO loExp = new ExpedienteDTO();
			loExp.setExpedienteId(12L);
			loExp.setNumeroExpedienteId(13L);			
			
			List<ObjetoDTO> objetos = service.obtenerObjetosPorTipo(Objetos.VEHICULO, loExp);
			assertNotNull(objetos);
			logger.info("Existen "+objetos.size()+" objetos sin asignar");
			for (ObjetoDTO obj : objetos) {
				if(obj instanceof VehiculoDTO){
					VehiculoDTO loVehiculo = (VehiculoDTO)obj;
					logger.info("--------------------");	
					logger.info("Id: "+loVehiculo.getElementoId());	
					logger.info("Tipo Objeto: "+loVehiculo.getValorByTipoVehiculo().getValor());
					logger.info("Placa: "+loVehiculo.getPlaca());
				}
				if(obj instanceof ArmaDTO){
					ArmaDTO loArmaDTO = (ArmaDTO)obj;
					logger.info("--------------------");	
					logger.info("Id: "+loArmaDTO.getElementoId());	
					logger.info("Tipo Arma: "+loArmaDTO.getTipoArma().getValor());
					logger.info("Matricula: "+loArmaDTO.getMatricula());
				}
				
				if(obj instanceof EquipoComputoDTO){
					EquipoComputoDTO loArmaDTO = (EquipoComputoDTO)obj;
					logger.info("--------------------");	
					logger.info("Id: "+loArmaDTO.getElementoId());	
					logger.info("Tipo Equipo: "+loArmaDTO.getTipoEquipo().getValor());
					logger.info("No Serie: "+loArmaDTO.getNoSerie());
				}
				
				if(obj.getCadenaDeCustodia() != null && obj.getCadenaDeCustodia().getFolio() != null)
					logger.info("Folio: "+obj.getCadenaDeCustodia().getFolio());
				else
					logger.info("Folio: -");
				
				for(String numExp: obj.getNumerosDeCasos()){
					logger.info("Numero de expediente relacionado: " + numExp);
				}
			}
			logger.info("Existen "+objetos.size()+" objetos en el expediente");
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}
