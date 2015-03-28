/**
 * Nombre del Programa : IngresarVehiculoServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 06-jul-2011
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
package mx.gob.segob.nsjp.service.test.objeto.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.involucrado.Condicion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.service.objeto.IngresarVehiculoService;
import mx.gob.segob.nsjp.service.objeto.ObtenerObjetoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class IngresarVehiculoServiceImplTest
    extends BaseTestServicios<IngresarVehiculoService> {

	@Autowired 
	private ObtenerObjetoService objetoService;
	
	public void testModificarVehiculoService(){
		 try {
			Long objetoID = 1047L;
			
			ObjetoDTO objeto = objetoService.obtenerObjeto(new ObjetoDTO(objetoID ));
			if(objeto!= null && objeto instanceof VehiculoDTO){
				VehiculoDTO vehiculoDTO = (VehiculoDTO) objeto;
				vehiculoDTO.setDescripcion(objeto.getDescripcion() + "*");
//				vehiculoDTO.setDescripcion(objeto.getDescripcion().replace("*", ""));
//				vehiculoDTO.setEsActivo(true);
				vehiculoDTO.setValorByCondicionFisicaVal(new ValorDTO(432L) );
				service.ingresarVehiculo(vehiculoDTO);
			}
			
		} catch (NSJPNegocioException e) {
			if (logger.isDebugEnabled()) {
                logger.debug(e);
            }
            fail("Ocurrio una excepcion al ejecutar el test ");
		}
	}
	
    public void testIngresarVehiculoService(){
        try {
            logger.info("Probando el servicio de: IngresarVehiculoService");
            assert service != null;
//            VehiculoDTO vehiculoDto = new VehiculoDTO();
//            vehiculoDto.setElementoId(159L);
//            vehiculoDto.setPlaca("CSMFLNTO" + (int)(Math.random() * 100));
//            vehiculoDto.setNoMotor("NUMERO_CFLNT");
//            vehiculoDto.setInstitucionPresenta(null);
//            vehiculoDto.setDescripcion("Descripcion modificada " + new Date());
//            vehiculoDto.setNoSerie("Numero de serie");
//            CadenaDeCustodiaDTO cadenaDeCustodiaDTO = new CadenaDeCustodiaDTO(1L);
//            EvidenciaDTO evidenciaDTO = new EvidenciaDTO();
//            evidenciaDTO.setFechaLevantamiento(new Date());
//            evidenciaDTO.setOrigenEvidencia("Origen nuevo de la evidencia");
//            evidenciaDTO.setCodigoBarras("1234");
//            evidenciaDTO.setDescripcion("Descripcion de la evidencia");
//            evidenciaDTO.setNumeroEvidencia(1234L);
//            cadenaDeCustodiaDTO.setEvidencia(evidenciaDTO);
//            vehiculoDto.setCadenaDeCustodia(cadenaDeCustodiaDTO);
//            service.ingresarVehiculo(vehiculoDto);
            // Guardado
            ExpedienteDTO expedienteDto = new ExpedienteDTO(187L);
            VehiculoDTO guardable = new VehiculoDTO();

//            guardable.setElementoId(161L);
            guardable.setExpedienteDTO(expedienteDto);
            guardable.setPlaca("EXCONV");
            CalidadDTO calidadDTO = new CalidadDTO();
            calidadDTO.setCalidades(Calidades.PRUEBA);
//            calidadDTO.setValorIdCalidad(new ValorDTO(215L));
            calidadDTO.setDescripcionEstadoFisico("Descripcion calidad nuevo vehiculo");
            guardable.setCalidadDTO(calidadDTO);
            guardable.setValorByCondicionFisicaVal(new ValorDTO(434L));
//            guardable.setDescripcion("Nueva descripcion CSMFULATINO " + new Date());
            CadenaDeCustodiaDTO cadenaDeCustodiaDTO = new CadenaDeCustodiaDTO(30L);
            EvidenciaDTO evidenciaDTO = new EvidenciaDTO();
//            evidenciaDTO.setEvidenciaId(15L);
            
			
//			Calendar mCal=Calendar.getInstance();
//			mCal.set(Calendar.HOUR_OF_DAY, 15);
//			mCal.set(Calendar.DAY_OF_MONTH,22);
//            evidenciaDTO.setFechaLevantamiento(mCal.getTime());
//            evidenciaDTO.setOrigenEvidencia("Debajo de una pinha");
//            evidenciaDTO.setCodigoBarras("1235");
//            evidenciaDTO.setDescripcion("Evidencia nueva para vehiculo existente");
//            evidenciaDTO.setNumeroEvidencia(1234L);
//            cadenaDeCustodiaDTO.setEvidencia(evidenciaDTO);
//            guardable.setCadenaDeCustodia(cadenaDeCustodiaDTO);
            
            Long idNuevoVehiculo = service.ingresarVehiculo(guardable);
            if (logger.isDebugEnabled()) {
                logger.debug("idNuevoVehiculo = " + idNuevoVehiculo);
            }
        } catch (NSJPNegocioException ex) {
        	ex.printStackTrace();
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test IngresarVehiculoService");
        }
    }
   
}
