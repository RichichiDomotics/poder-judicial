/**
 * Nombre del Programa : ConsultarCadenaCustodiaXExpedienteServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 01-jul-2011
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
package mx.gob.segob.nsjp.service.test.cadenadecustodia.impl;

import java.util.Iterator;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.service.cadenadecustodia.ConsultarCadenaCustodiaXExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarCadenaCustodiaXExpedienteServiceImplTest extends
		BaseTestServicios<ConsultarCadenaCustodiaXExpedienteService> {

	public void testConsultarCadenaCustodiaXExpedienteService() {
		try {
			logger.info("Probando el servicio de: ConsultarCadenaCustodiaXExpedienteService");
			assert service != null;
			ExpedienteDTO expedienteDto = new ExpedienteDTO(8L);
			List<CadenaDeCustodiaDTO> cadenas = service
					.consultarCadenaCustodiaXExpediente(expedienteDto);
			assertNotNull("cadenaDeCustodia", cadenas);
			logger.info("Existen " + cadenas.size() + " cadenas");
			for (CadenaDeCustodiaDTO dto : cadenas) {
				logger.info("----------------------------");
				logger.info("ID: " + dto.getCadenaDeCustodiaId());
				if(dto.getExpedienteDTO()!=null){
					logger.info("Expediente: " + dto.getExpedienteDTO().getExpedienteId());
					logger.info("NumExp: " + dto.getExpedienteDTO().getNumeroExpedienteId()+" ("+dto.getExpedienteDTO().getNumeroExpediente()+")");
					logger.info("Caso: " + dto.getExpedienteDTO().getCasoDTO().getCasoId());
					logger.info("Caso: " + dto.getFolio());
				}
				assertNotNull("cadenaDeCustodia.getFolio()", dto.getFolio());
				logger.debug("cadenaDeCustodia.getFolio() = " + dto.getFolio());
				if (dto.getEvidencias() != null) {
					logger.info("Evidencias" + dto.getEvidencias().size());
					for (EvidenciaDTO evi : dto.getEvidencias()) {
						logger.info("*********************************");
						logger.info("Evidencia id"+evi.getEvidenciaId());
						logger.info("estatus Evidencia: "+evi.getEstatus());
						logger.info("Cod Barras"+ evi.getCodigoBarras());
						if (evi.getObjeto() != null){
							ObjetoDTO obj = evi.getObjeto();
							logger.info("Objeto id: "
									+ obj.getElementoId());
							logger.info("Objeto id: "
									+ obj.getCalidadDTO());
							logger.info("Almacen id: "+obj.getAlmacen());
						}
						if(evi.getEslabones()!=null){
							logger.info("Existen "+evi.getEslabones().size()+ " eslabones");
							Iterator<EslabonDTO> itEslab = evi.getEslabones().iterator();
							while (itEslab.hasNext()) {
								EslabonDTO eslabonDTO = (EslabonDTO) itEslab.next();
								logger.info("Eslabon: "+eslabonDTO);
							}
							
						}
					}
				}
			}

		} catch (NSJPNegocioException ex) {
			if (logger.isDebugEnabled()) {
				logger.debug(ex);
			}
			fail("Ocurrio una excepcion al ejecutar el test ConsultarCadenaCustodiaXExpedienteService");
		}
	}

	public void testConsultarCadenaCustodia() {
		try {
			CadenaDeCustodiaDTO cadena = service
					.consultarCadenaCustodia(new CadenaDeCustodiaDTO(217L));
			assertNotNull("Cadena de custodia", cadena);
			logger.info("ID: " + cadena.getCadenaDeCustodiaId());
			logger.info("Foio: " + cadena.getFolio());
			logger.info("Objeto: " + cadena);
			List<EvidenciaDTO> evidencias = cadena.getEvidencias();
			for (EvidenciaDTO evidenciaDTO : evidencias) {
				logger.info("Objeto: " + evidenciaDTO);
				logger.info("Objeto EvidenciaId: " + evidenciaDTO.getEvidenciaId());
				logger.info("Objeto NumeroEvidencia: " + evidenciaDTO.getNumeroEvidencia());
				logger.info("Objeto: " + evidenciaDTO.getObjeto());
				logger.info("Objeto: " + evidenciaDTO.getObjeto().getTipoObjeto());
				logger.info("Objeto: " + evidenciaDTO.getObjeto().getTipoObjeto().getValorId());
				logger.info("Objeto: " + evidenciaDTO.getObjeto().getEsActivo());
				
				if(Objetos.EQUIPO_TELEFONICO.getValorId().equals(evidenciaDTO.getObjeto().getTipoObjeto().getValorId())){
					logger.info("Objeto: " + evidenciaDTO.getObjeto().getTipoObjeto().getValorId());
				}
			}
					
			logger.info("Quien Recibe: " + cadena.getQuienRecibe());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

	public void testConsultarCadenaCustodiaXExpedienteYFolio() {
		// List<CadenaDeCustodiaDTO> cadenas =
		// service.consultarCadenaCustodiaXExpedienteYFolio(65L,
		// "CadenaDeCustodia_001");
		List<CadenaDeCustodiaDTO> cadenas;
		try {
			cadenas = service.consultarCadenaCustodiaXExpedienteYFolio(65L,
					null);

			assertNotNull("cadenaDeCustodia", cadenas);
			logger.info("Existen " + cadenas.size() + " cadenas");
			for (CadenaDeCustodiaDTO dto : cadenas) {
				logger.info("----------------------------");
				logger.info("ID: " + dto.getCadenaDeCustodiaId());
				assertNotNull("cadenaDeCustodia.getFolio()", dto.getFolio());
				logger.debug("cadenaDeCustodia.getFolio() = " + dto.getFolio());
				if (dto.getEvidencias() != null) {
					logger.info("Evidencias" + dto.getEvidencias().size());
					for (EvidenciaDTO evi : dto.getEvidencias()) {
						if (evi.getObjeto() != null)
							logger.info("Objeto id: "
									+ evi.getObjeto().getElementoId());
					}
				}
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
