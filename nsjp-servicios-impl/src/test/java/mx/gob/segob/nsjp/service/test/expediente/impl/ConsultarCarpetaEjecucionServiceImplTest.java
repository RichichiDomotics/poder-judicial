/**
 * Nombre del Programa : ConsultarCarpetaEjecucionServiceImplTest.java
 * Autor                            : Emigdio
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 07/07/2011
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

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarCarpetaEjecucionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de pruebas unitarias del servicio de consulta de datos de una carpeta
 * de ejecución
 * 
 * @version 1.0
 * @author Emigdio Hernández
 * 
 */
public class ConsultarCarpetaEjecucionServiceImplTest extends
		BaseTestServicios<ConsultarCarpetaEjecucionService> {

	public void testConsultarDatosGenerales() {

		try {
			ExpedienteDTO exp = service
					.consultarDatosGeneralesExpedienteCarpetaDeEjecucion(20L);
			if (exp != null) {

				List<InvolucradoDTO> invOrg = exp
						.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
				List<InvolucradoDTO> invPer = exp
						.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);
				// logger.debug("Expediente + " + exp);
				logger.debug("No. Expediente: " + exp.getExpedienteId());
				if (invOrg != null) {
					for (int i = 0; i < invOrg.size(); i++) {
						logger.debug("Involucrado Organización No: " + i + ". "
								+ invOrg.get(i).getNombreCompleto());
					}
				}
				if (invPer != null) {
					for (int i = 0; i < invPer.size(); i++) {
						logger.debug("Involucrado Persona No: " + i + ". "
								+ invPer.get(i).getNombreCompleto());
					}
				}
				logger.debug("Fecha de Creación de Carpeta: "
						+ exp.getFechaApertura());

				logger.debug("Duración de la sentencia: "
						+ exp.getFechaApertura());
				if (exp.getAudiencias()!= null) {
					logger.debug("Cantidad de Audiencias: " + exp.getAudiencias().size());
					for (int i = 0; i < exp.getAudiencias().size(); i++) {
						if (exp.getAudiencias().get(i).getResolutivos()!=null){
							logger.debug("Cantidad de Resolutivos: " + exp.getAudiencias().get(i)
									.getResolutivos().size());
						
						for (int j = 0; j < exp.getAudiencias().get(i)
								.getResolutivos().size(); j++) {
							logger.debug("Audiencia "
									+ i
									+ " Resolutivo "
									+ j
									+ ": "
									+ exp.getAudiencias().get(i)
											.getResolutivos().get(j)
											.getDetalle());
						}
						}else
						{
							logger.debug("No hay resolutivos relacionadas a la audiencia " + exp.getAudiencias().get(i).getId());
						}
					}
				} else {
					logger.debug("No hay audiencias relacionadas al expediente " + exp.getExpedienteId());
				}

			}
			assertNotNull(exp);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertNull(e);

		}

	}
	
	public void testConsultarCarpetasEjecucionPorEstatus(){
		
		List<ValorDTO> estatusExpediente = new ArrayList<ValorDTO>();
		estatusExpediente.add(new ValorDTO(EstatusExpediente.ABIERTO.getValorId()));
		
		UsuarioDTO usuario = new UsuarioDTO();
		FuncionarioDTO funcionario = new FuncionarioDTO();
		CatDiscriminanteDTO catDis = new CatDiscriminanteDTO();
		
		catDis.setCatDiscriminanteId(26L);
		funcionario.setDiscriminante(catDis);
		usuario.setFuncionario(funcionario);
		
		try {
			List<ExpedienteDTO>  carpetas = service.consultarCarpetasEjecucionPorEstatus(estatusExpediente,usuario);
			assertFalse("La lista no debe ser vacia ", carpetas.isEmpty());
			logger.debug("Numero de expedientes:" + carpetas.size());
			for (ExpedienteDTO expedienteDTO : carpetas) {
				logger.info("******");
				logger.info(" ExpedienteDTO:"+expedienteDTO.getExpedienteId());
				logger.info(" ExpedienteDTO - Caso:"+expedienteDTO.getCasoDTO());
				logger.info(" ExpedienteDTO - FechaApertura:"+expedienteDTO.getFechaApertura());
				logger.info(" ExpedienteDTO - FechaApertura:"+expedienteDTO.getStrFechaApertura());
				logger.info(" ExpedienteDTO - NEX:"+expedienteDTO.getNumeroExpediente());
				logger.info(" ExpedienteDTO CAUSA PADRE:"+expedienteDTO.getCausaPadre());
				if(expedienteDTO.getCausaPadre()!= null){
					logger.info("CAUSA PADRE:"+expedienteDTO.getCausaPadre().getNumeroExpediente());
				}
				logger.info(" ExpedienteDTO IMPUTADO?:"+expedienteDTO.getInputado());
			}
			
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
			
		} 
	}
	
}
