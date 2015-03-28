/**
* Nombre del Programa : RegistrarCedulaDeIdentificacionServiceImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementación para el servicio Recibir una solicitud de defensoria por un ciudadano
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
package mx.gob.segob.nsjp.service.expediente.impl;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDefensorDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.expediente.RegistrarCedulaDeIdentificacionService;
import mx.gob.segob.nsjp.service.hecho.IngresarHechoService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.relacion.GenerarRelacionService;
import mx.gob.segob.nsjp.service.solicitud.impl.RecibirSolicitudCiudadanaDefensoriaServImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación para el servicio Ingresar cédula de identificación
 * Servicio que permite ingresar los siguientes datos de una persona detenida para
 *  un expediente en etapa de integración:
 *    - Datos generales
 *    - Media filiación.
 *    - Delito(s)
 *    - Hechos.
 * @version 1.0
 * @author rgama
 *
 */
@Service
@Transactional
public class RegistrarCedulaDeIdentificacionServiceImpl implements
	RegistrarCedulaDeIdentificacionService {

	
	@Autowired
	private SolicitudDefensorDAO solicitudDefensorDAO;
	
	@Autowired
	private IngresarIndividuoService ingresarIndividuoService;	
	@Autowired
	private GenerarRelacionService generarRelacionService;
	@Autowired
	private IngresarHechoService ingresarHechoService; 

	
	/**
	 * Modificacion para el registro de solicitudes de defensoria.
	 */
	private final static Logger logger = Logger.getLogger(RecibirSolicitudCiudadanaDefensoriaServImpl.class);
	

	@SuppressWarnings("null")
	@Override
	public InvolucradoDTO registrarCedula(InvolucradoDTO aoInvoludrado) throws NSJPNegocioException {
		ExpedienteDTO expedienteDTO= aoInvoludrado.getExpedienteDTO();
		
		if (logger.isDebugEnabled())
			logger.debug("SERVICIO PARA REGISTRAR UNA CEDULA DE IDENTIFICACION ASOCIADA A UN DETENIDO");

		if (aoInvoludrado==null || expedienteDTO == null || expedienteDTO.getExpedienteId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		//Se asigna la calidad del imputado		
		CalidadDTO loCalidadInvoludrado = new CalidadDTO();
		loCalidadInvoludrado.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
		loCalidadInvoludrado.setValorIdCalidad(new ValorDTO(Calidades.SOLICITANTE.getValorId()));		
		aoInvoludrado.setCalidadDTO(loCalidadInvoludrado);
		
		aoInvoludrado.setEsServidor(false);
		aoInvoludrado.setEsVivo(true);
		
		Long idImputado = ingresarIndividuoService.ingresarIndividuo(aoInvoludrado);
		
		InvolucradoDTO impt = new InvolucradoDTO(idImputado);
		impt.setExpedienteDTO(expedienteDTO);
		
		return impt;

	}	



}
