/**
* Nombre del Programa : PruebaDelegateImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/09/2011
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
package mx.gob.segob.nsjp.delegate.prueba.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.prueba.PruebaDelegate;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.MedioPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionDatoMedioPruebaDTO;
import mx.gob.segob.nsjp.service.prueba.AdministrarDatoPruebaService;
import mx.gob.segob.nsjp.service.prueba.AdministrarMedioPruebaService;

/**
 * Implementación del delegate para exponer los servicios relacionados con Pruebas:
 * Dato Prueba, Medio Prueba y Pruebas.
 * 
 * @version 1.0
 * @author GustavoBP
 */
@Service ("pruebaDelegate")
public class PruebaDelegateImpl implements PruebaDelegate{

	@Autowired
	private AdministrarDatoPruebaService  administrarDatoPruebaService ;
	
	@Autowired
	private AdministrarMedioPruebaService  administrarMedioPruebaService ; 
	
	@Override
	public DatoPruebaDTO registrarActualizarDatoPrueba(DatoPruebaDTO datoPruebaDTO, String numeroExpediente) throws NSJPNegocioException{
		return administrarDatoPruebaService.registrarActualizarDatoPrueba(datoPruebaDTO, numeroExpediente);
	}
	
	@Override
	public List<DatoPruebaDTO> consultarDatosPruebaPorFiltro(DatoPruebaDTO filtroDTO, String numeroExpediente) throws NSJPNegocioException{
		return administrarDatoPruebaService.consultarDatosPruebaPorFiltro(filtroDTO, numeroExpediente);
	}
	
	@Override
	public DatoPruebaDTO aceptarCancelarDatoPrueba(DatoPruebaDTO datoPruebaDTO) throws NSJPNegocioException{
		return administrarDatoPruebaService.aceptarCancelarDatoPrueba(datoPruebaDTO);
	}

	@Override
	public MedioPruebaDTO registrarMedioPruebaConRelacionADatoPrueba(
			MedioPruebaDTO medioPruebaDTO, DatoPruebaDTO datoPruebaDTO)
			throws NSJPNegocioException {
		return administrarMedioPruebaService.registrarMedioPruebaConRelacionADatoPrueba(medioPruebaDTO, datoPruebaDTO);
	}

	@Override
	public Long aceptarCarncelarRelacionMedioPrueba(
			RelacionDatoMedioPruebaDTO relacionDatoMedioPruebaDTO)
			throws NSJPNegocioException {
		return administrarMedioPruebaService.aceptarCarncelarRelacionMedioPrueba(relacionDatoMedioPruebaDTO);
	}

	@Override
	public List<MedioPruebaDTO> relacionarMedioPruebaConDatoPrueba(
			DatoPruebaDTO datoPruebaDTO,
			List<MedioPruebaDTO> listaMediosPruebaDTO)
			throws NSJPNegocioException {
		return administrarMedioPruebaService.relacionarMedioPruebaConDatoPrueba(datoPruebaDTO, listaMediosPruebaDTO);
	}

	@Override
	public List<MedioPruebaDTO> consultarMediosPruebaXDatoPrueba(
			DatoPruebaDTO datoPruebaDTO, Boolean relacionados)
			throws NSJPNegocioException {
		return administrarMedioPruebaService.consultarMediosPruebaPorDatoPrueba(datoPruebaDTO,relacionados);
	}

	@Override
	public Long relacionarPruebaAInvolucrado(DatoPruebaDTO datoPruebaDTO,
			List<InvolucradoDTO> listaResponsables)
			throws NSJPNegocioException {
		return administrarDatoPruebaService.relacionarPruebaAInvolucrado(datoPruebaDTO, listaResponsables);
	}

	@Override
	public List<DatoPruebaDTO> consultarDatosPruebaXAudiencia(
			List<InvolucradoDTO> listaResponsables, String numeroExpediente)
			throws NSJPNegocioException {
		return administrarDatoPruebaService.consultarDatosPruebaXAudiencia(listaResponsables,numeroExpediente);
	}

	@Override
	public DatoPruebaDTO consultarDatoPrueba(Long datoPruebaId)
			throws NSJPNegocioException {
		return administrarDatoPruebaService.consultarDatoPrueba( datoPruebaId);
	}

	@Override
	public MedioPruebaDTO consultarMedioPrueba(Long medioPruebaId)
			throws NSJPNegocioException {
		return administrarMedioPruebaService.consultarMedioPrueba( medioPruebaId);
	}

	@Override
	public List<DatoPruebaDTO> consultarDatosPruebaXMedioPrueba(
			Long medioPruebaId, Boolean relacionados) throws NSJPNegocioException {
		return administrarMedioPruebaService.consultarDatosPruebaXMedioPrueba(medioPruebaId, relacionados);
	}
	
	@Override
	public List<InvolucradoViewDTO> obtenerImputadosAudienciaSinRelacionConPrueba(
			AudienciaDTO audienciaDTO, DatoPruebaDTO datoPruebaDTO, String numeroExpediente) throws NSJPNegocioException {
		return administrarDatoPruebaService.obtenerImputadosAudienciaSinRelacionConPrueba(audienciaDTO, datoPruebaDTO, numeroExpediente);
	}
	
	public List<DatoPruebaDTO> consultarPruebasPorNumeroExpediente(String numeroExpediente) throws NSJPNegocioException {
		return administrarDatoPruebaService.consultarPruebasPorNumeroExpediente(numeroExpediente);
	}
}
