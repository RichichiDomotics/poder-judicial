/**
* Nombre del Programa : MedidasCautelaresDelegateImpl.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 Jul 2011
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
package mx.gob.segob.nsjp.delegate.medidascautelares.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.medidascautelares.MedidasCautelaresDelegate;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.medida.AsociarArchivoDigitalAMedidaService;
import mx.gob.segob.nsjp.service.medida.CambiarEstatusMedidaService;
import mx.gob.segob.nsjp.service.medidascautelares.IngresarMedidasCautelaresService;
import mx.gob.segob.nsjp.service.medidascautelares.ObtenerMedidasCautelaresService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion del contrato Delegate de las medidas Cautelares
 * @version 1.0
 * @author Tattva-IT
 *
 */

@Service("medidasCautelaresDelegate")
public class MedidasCautelaresDelegateImpl implements MedidasCautelaresDelegate {

	@Autowired
	private ObtenerMedidasCautelaresService obtenerMedidasCautelaresService;
	
	@Autowired
	private IngresarMedidasCautelaresService ingresarMedidasCautelaresService;
	
	@Autowired
	private AsociarArchivoDigitalAMedidaService asociarArchivoDigitalAMedidaService;	
	
	@Autowired
	CambiarEstatusMedidaService cambiarEstatusMedida;
	
	@Override
	public List<MedidaCautelarDTO> obtenerMedidasCautelaresPorInvolucrado(
			Long involucradoId) throws NSJPNegocioException {
		 
		return obtenerMedidasCautelaresService.obtenerMedidasCautelaresPorInvolucrado(involucradoId);

	}

	@Override
	public MedidaCautelarDTO obtenerDetalleMedidaCautelar(
			Long idMedidaCautelar, Long idInvolucrado)
			throws NSJPNegocioException {
		
		return obtenerMedidasCautelaresService.obtenerDetalleMedidaCautelar(idMedidaCautelar,idInvolucrado);
	}

	@Override
	public Long ingresarMedidaCautelar(MedidaCautelarDTO medidaCautelar)
			throws NSJPNegocioException {
		
		return ingresarMedidasCautelaresService.ingresarMedidaCautelar(medidaCautelar);
	}

    @Override
    public void desactivarMedidaCautelar(MedidaCautelarDTO medidaCautelar)
            throws NSJPNegocioException {
        ingresarMedidasCautelaresService.desactivarMedidaCautelar(medidaCautelar);
    }
    /*
     * (non-Javadoc)
     * @see mx.gob.segob.nsjp.delegate.medidascautelares.MedidasCautelaresDelegate#obtenerMedidasCautelaresPorExpediente(java.lang.Long)
     */
	@Override
	public List<MedidaCautelarDTO> obtenerMedidasCautelaresPorExpediente(
			Long numeroExpedienteId) throws NSJPNegocioException {
		return obtenerMedidasCautelaresService.obtenerMedidasCautelaresPorExpediente(numeroExpedienteId);
	}

	@Override
	public List<MedidaCautelarDTO> consultaMedidasCautelaresPorEstatus(
			MedidaCautelarDTO medidaCautelar) throws NSJPNegocioException {
		return obtenerMedidasCautelaresService.consultaMedidasCautelaresPorEstatus(medidaCautelar);
		
	}

	@Override
	public void asociarArchivoDigitalAMedida(ArchivoDigitalDTO archivoDigitalDTO, MedidaDTO medidaDTO)
			throws NSJPNegocioException {
		asociarArchivoDigitalAMedidaService.asociarArchivoDigitalAMedida(archivoDigitalDTO, medidaDTO);
	}

	@Override
	public void cambiarEstatusMedida(Long idMedida, Long idEstatus)
			throws NSJPNegocioException {
		
		cambiarEstatusMedida.cambiarEstatusMedida(idMedida, idEstatus);
		
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.medidascautelares.MedidasCautelaresDelegate#consultarInvolucradosConMedidasCautelaresPorNumeroExpedienteOCausa(java.lang.String)
	 */
	@Override
	public List<InvolucradoDTO> consultarInvolucradosConMedidasCautelaresPorNumeroExpedienteOCausa(
			String numeroExpediente) throws NSJPNegocioException {
		return obtenerMedidasCautelaresService.consultarInvolucradosConMedidasCautelaresPorNumeroExpedienteOCausa(numeroExpediente);
		
	}

	@Override
	public MedidaCautelarDTO consultarMedidasCautelaresPorId(
			Long idMedidaCautelar) throws NSJPNegocioException {
		return obtenerMedidasCautelaresService.consultarMedidasCautelaresPorId(idMedidaCautelar);
	}

	@Override
	public void asociarDocumentoConMedidaCautelar(DocumentoDTO documentoDTO,
			MedidaCautelarDTO medidaCautelarDTO) throws NSJPNegocioException {
		asociarArchivoDigitalAMedidaService.asociarDocumentoConMedidaCautelar(documentoDTO, medidaCautelarDTO);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.medidascautelares.MedidasCautelaresDelegate#enviarMedidaCautelarSSP(java.lang.Long)
	 */
	@Override
	public void enviarMedidaCautelarSSP(Long medidaId) {
		ingresarMedidasCautelaresService.enviarMedidaCautelarSSP(medidaId);
	}

	@Override
	public List<MedidaCautelarDTO> obtenerMedidasCautelaresPorFechaIncumplientoDelDiaAnterior()
			throws NSJPNegocioException {
		return obtenerMedidasCautelaresService.obtenerMedidasCautelaresPorFechaIncumplientoDelDiaAnterior();
	}

    @Override
    public List<MedidaCautelarDTO> consultarMedidasCautelaresPorNumeroExpedienteOCausa(
            String numeroExpediente,UsuarioDTO usuario) throws NSJPNegocioException {
        return obtenerMedidasCautelaresService.consultarMedidasCautelaresPorNumeroExpedienteOCausa(numeroExpediente,usuario);
    }

	
}
