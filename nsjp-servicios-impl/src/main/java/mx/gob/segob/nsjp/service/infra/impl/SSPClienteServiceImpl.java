package mx.gob.segob.nsjp.service.infra.impl;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.institucion.ConfInstitucionDAO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Mandamiento;
import mx.gob.segob.nsjp.model.MedidaAlterna;
import mx.gob.segob.nsjp.model.MedidaCautelar;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.SolicitudTrasladoImputado;
import mx.gob.segob.nsjp.service.infra.SSPClienteService;
import mx.gob.segob.nsjp.service.infra.impl.transform.WsTransformer;
import mx.gob.segob.nsjp.ws.cliente.mandamiento.MandamientoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.mandamiento.RegistrarMandamientoExporter;
import mx.gob.segob.nsjp.ws.cliente.mandamiento.RegistrarMandamientoExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.medidaalterna.MedidaAlternaWSDTO;
import mx.gob.segob.nsjp.ws.cliente.medidaalterna.RegistrarMedidaAlternaServiceExporter;
import mx.gob.segob.nsjp.ws.cliente.medidaalterna.RegistrarMedidaAlternaServiceExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.medidacautelar.ArchivoDigitalWSDTO;
import mx.gob.segob.nsjp.ws.cliente.medidacautelar.MedidaCautelarWSDTO;
import mx.gob.segob.nsjp.ws.cliente.medidacautelar.NSJPNegocioException_Exception;
import mx.gob.segob.nsjp.ws.cliente.medidacautelar.RegistrarMedidaCautelarServiceExporter;
import mx.gob.segob.nsjp.ws.cliente.medidacautelar.RegistrarMedidaCautelarServiceExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.solicitudtransferenciaimputado.RegistrarSolicitudTransferenciaImputadoSSPExporter;
import mx.gob.segob.nsjp.ws.cliente.solicitudtransferenciaimputado.RegistrarSolicitudTransferenciaImputadoSSPExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.solicitudtransferenciaimputado.SolicitudTrasladoImputadoWSDTO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class SSPClienteServiceImpl implements SSPClienteService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(SSPClienteServiceImpl.class);

    @Autowired
    private ConfInstitucionDAO confInsDao;

    @Override
    public void enviarMedidaCautelar(MedidaCautelar input)
            throws NSJPNegocioException {

        if (input.getInvolucrado() == null
                || input.getInvolucrado().getNombreDemograficos() == null
                || input.getArchivoDigital() == null
                || input.getJuezOrdena() == null
                || input.getValorPeriodicidad() == null
                || input.getFechaCreacion() == null
                || input.getFechaInicio() == null
                || input.getFechaFin() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }

        try {
            URL url = new URL(confInsDao.read(Instituciones.SSP.getValorId())
                    .getUrlInst()
                    + "/RegistrarMedidaCautelarServiceExporterImplService?wsdl");

            final QName SERVICE_NAME = new QName(
                    "http://impl.ws.service.nsjp.segob.gob.mx/",
                    "RegistrarMedidaCautelarServiceExporterImplService");

            RegistrarMedidaCautelarServiceExporterImplService ss = new RegistrarMedidaCautelarServiceExporterImplService(
                    url, SERVICE_NAME);

            RegistrarMedidaCautelarServiceExporter port = ss
                    .getRegistrarMedidaCautelarServiceExporterImplPort();

            MedidaCautelarWSDTO toSend = new MedidaCautelarWSDTO();

            toSend.setActivo((input.getEsActivo() != null && input
                    .getEsActivo()));
            NombreDemografico nombre = null;
            if (input.getInvolucrado().getNombreDemograficos() != null) {
                for (NombreDemografico nd : input.getInvolucrado()
                        .getNombreDemograficos()) {
                    if (nd.getEsVerdadero()) {
                        nombre = nd;
                    }
                }
                if (nombre == null) {
                    nombre = input.getInvolucrado().getNombreDemograficos()
                            .iterator().next();
                }
                toSend.setNombreSujeto(nombre.getNombre());
                toSend.setAPaternoSujeto(nombre.getApellidoPaterno());
                toSend.setAMaternoSujeto(nombre.getApellidoMaterno());
            }

            toSend.setDescripcionMedida(input.getDescripcionMedida());
            toSend.setFechaCreacion(WsTransformer.transformFecha(input
                    .getFechaCreacion()));
            toSend.setFechaInicio(WsTransformer.transformFecha(input
                    .getFechaInicio()));
            toSend.setFechaFin(WsTransformer.transformFecha(input.getFechaFin()));
            toSend.setFolioDocumento(input.getFolioDocumento());
            toSend.setIdValorPeriodicidad(input.getValorPeriodicidad()
                    .getValorId());
            toSend.setIdValorTipoMedida(input.getValorTipoMedida().getValorId());
            toSend.setJuezOrdena(input.getJuezOrdena());
            toSend.setNumeroCarpetaEjecucion(input.getNumeroCarpetaEjecucion());
            toSend.setNumeroCaso(input.getNumeroCaso());
            toSend.setNumeroCausa(input.getNumeroCausa());
            toSend.setFormaId(input.getForma().getFormaId());
            toSend.setTipoDocumentoDTO(input.getTipoDocumento().getValorId());

            ArchivoDigitalWSDTO archivo = new ArchivoDigitalWSDTO();
            ArchivoDigital original = input.getArchivoDigital();

            archivo.setContenido(original.getContenido());
            archivo.setNombreArchivo(original.getNombreArchivo());
            archivo.setTipoArchivo(original.getTipoArchivo());
            toSend.setArchivoDigital(archivo);
            logger.debug("Enviando medida " + input.getFolioDocumento() + " a través de " + url);
            port.registrarMedidaCautelar(toSend);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        } catch (NSJPNegocioException_Exception e) {
            logger.error(e.getMessage(), e);
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        }

    }

    @Override
    public void enviarMedidaAlterna(MedidaAlterna input)
            throws NSJPNegocioException {
        try {

            if (input.getInvolucrado() == null
                    || input.getInvolucrado().getNombreDemograficos() == null
                    || input.getArchivoDigital() == null
                    || input.getNumeroCarpetaEjecucion() == null
                    || input.getJuezOrdena() == null
                    || input.getNumeroCaso() == null
                    || input.getNumeroCausa() == null
                    || input.getValorPeriodicidad() == null
                    || input.getFechaCreacion() == null
                    || input.getFechaInicio() == null
                    || input.getFechaFin() == null) {
                throw new NSJPNegocioException(
                        CodigoError.PARAMETROS_INSUFICIENTES);
            }

            URL url = new URL(confInsDao.read(Instituciones.SSP.getValorId())
                    .getUrlInst()
                    + "/RegistrarMedidaAlternaServiceExporterImplService?wsdl");

            final QName SERVICE_NAME = new QName(
                    "http://impl.ws.service.nsjp.segob.gob.mx/",
                    "RegistrarMedidaAlternaServiceExporterImplService");

            RegistrarMedidaAlternaServiceExporterImplService ss = new RegistrarMedidaAlternaServiceExporterImplService(
                    url, SERVICE_NAME);

            RegistrarMedidaAlternaServiceExporter port = ss
                    .getRegistrarMedidaAlternaServiceExporterImplPort();

            MedidaAlternaWSDTO toSend = new MedidaAlternaWSDTO();

            toSend.setAnios(input.getAnios());
            toSend.setMeses(input.getMeses());

            NombreDemografico nombre = null;
            if (input.getInvolucrado().getNombreDemograficos() != null) {
                for (NombreDemografico nd : input.getInvolucrado()
                        .getNombreDemograficos()) {
                    if (nd.getEsVerdadero()) {
                        nombre = nd;
                    }
                }
                if (nombre == null) {
                    nombre = input.getInvolucrado().getNombreDemograficos()
                            .iterator().next();
                }
                toSend.setNombreSujeto(nombre.getNombre());
                toSend.setAPaternoSujeto(nombre.getApellidoPaterno());
                toSend.setAMaternoSujeto(nombre.getApellidoMaterno());
            }

            toSend.setDescripcionMedida(input.getDescripcionMedida());
            toSend.setFechaCreacion(WsTransformer.transformFecha(input
                    .getFechaCreacion()));
            toSend.setFechaInicio(WsTransformer.transformFecha(input
                    .getFechaInicio()));
            toSend.setFechaFin(WsTransformer.transformFecha(input.getFechaFin()));
            toSend.setFolioDocumento(input.getFolioDocumento());
            toSend.setIdValorPeriodicidad(input.getValorPeriodicidad()
                    .getValorId());
            toSend.setIdValorTipoMedida(input.getValorTipoMedida().getValorId());
            toSend.setJuezOrdena(input.getJuezOrdena());
            toSend.setNumeroCarpetaEjecucion(input.getNumeroCarpetaEjecucion());
            toSend.setNumeroCaso(input.getNumeroCaso());
            toSend.setNumeroCausa(input.getNumeroCausa());
            toSend.setFormaId(input.getForma().getFormaId());
            toSend.setTipoDocumentoDTO(input.getTipoDocumento().getValorId());

            mx.gob.segob.nsjp.ws.cliente.medidaalterna.ArchivoDigitalWSDTO archivo = new mx.gob.segob.nsjp.ws.cliente.medidaalterna.ArchivoDigitalWSDTO();
            ArchivoDigital original = input.getArchivoDigital();

            archivo.setContenido(original.getContenido());
            archivo.setNombreArchivo(original.getNombreArchivo());
            archivo.setTipoArchivo(original.getTipoArchivo());
            toSend.setArchivoDigital(archivo);
            logger.debug("Enviando medida " + input.getFolioDocumento() + " a través de " + url);
            port.registrarMedidaAlterna(toSend);

        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        } catch (mx.gob.segob.nsjp.ws.cliente.medidaalterna.NSJPNegocioException_Exception e) {
            logger.error(e.getMessage(), e);
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        } catch(WebServiceException e){
        	logger.error(e.getMessage(), e);
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        } catch(Exception e){
        	logger.error(e.getMessage(), e);
        	throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        }
    }

    @Override
    public void enviarMandamiento(Mandamiento input)
            throws NSJPNegocioException {
        URL url;
        try {
            url = new URL(confInsDao.read(Instituciones.SSP.getValorId())
                    .getUrlInst()
                    + "/RegistrarMandamientoExporterImplService?wsdl");

            final QName SERVICE_NAME = new QName(
                    "http://impl.ws.service.nsjp.segob.gob.mx/",
                    "RegistrarMandamientoExporterImplService");
            RegistrarMandamientoExporterImplService ss = new RegistrarMandamientoExporterImplService(
                    url, SERVICE_NAME);
            RegistrarMandamientoExporter port = ss
                    .getRegistrarMandamientoExporterImplPort();

            MandamientoWSDTO toSend = new MandamientoWSDTO();
            
            toSend.setFechaCreacion(WsTransformer.transformFecha(input
                    .getFechaCreacion()));
            toSend.setFolioDocumento(input.getFolioDocumento());
            toSend.setFormaId(input.getForma().getFormaId());
            toSend.setTipoDocumentoDTO(input.getTipoDocumento().getValorId());
            toSend.setIdEstatus(input.getEstatus().getValorId());
            toSend.setIdTipoMandamiento(input.getTipoMandamiento().getValorId());
            toSend.setNombreDocumento(input.getNombreDocumento());
            mx.gob.segob.nsjp.ws.cliente.mandamiento.ArchivoDigitalWSDTO archivo = new mx.gob.segob.nsjp.ws.cliente.mandamiento.ArchivoDigitalWSDTO();
            ArchivoDigital original = input.getArchivoDigital();

            archivo.setContenido(original.getContenido());
            archivo.setNombreArchivo(original.getNombreArchivo());
            archivo.setTipoArchivo(original.getTipoArchivo());
            toSend.setArchivoDigital(archivo);
            logger.debug("Enviando mandamiento " + input.getFolioDocumento() + " a través de " + url);
            port.registrarMandamiento(toSend);
            
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        } catch (mx.gob.segob.nsjp.ws.cliente.mandamiento.NSJPNegocioException_Exception e) {
            logger.error(e.getMessage(), e);
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        }
    }

	@Override
	public void registrarSolicitudTrasladoImputado(
			SolicitudTrasladoImputado input) throws NSJPNegocioException {
		
		SolicitudTrasladoImputadoWSDTO toSend = new SolicitudTrasladoImputadoWSDTO();
		try {
			URL url = new URL(confInsDao.read(Instituciones.SSP.getValorId())
			        .getUrlInst()
			        + "/RegistrarSolicitudTransferenciaImputadoSSPExporterImplService?wsdl");
			final QName SERVICE_NAME = new QName(
                    "http://impl.ws.service.nsjp.segob.gob.mx/",
                    "RegistrarSolicitudTransferenciaImputadoSSPExporterImplService");
			RegistrarSolicitudTransferenciaImputadoSSPExporterImplService ss = new RegistrarSolicitudTransferenciaImputadoSSPExporterImplService(url, SERVICE_NAME);
			
			RegistrarSolicitudTransferenciaImputadoSSPExporter port = ss.getRegistrarSolicitudTransferenciaImputadoSSPExporterImplPort();
			
			toSend.setFolioAudiecia(input.getAudiencia().getFolioAudiencia());
			toSend.setFolioInvolucrado(input.getInvolucrado().getFolioElemento());
			
			toSend.setFolioSolicitud(input.getFolioSolicitud());
			toSend.setNumeroCasoAsociado(input.getNumeroCasoAsociado());
			toSend.setFechaLimite(WsTransformer.transformFecha(input.getFechaLimite()));
			toSend.setSolicitanteExternoId(input.getFuncionarioSolicitante().getClaveFuncionario());
			
			port.registrarSolicitudTransferenciaImputadoSSP(toSend);
		} catch (MalformedURLException e) {
			 logger.error(e.getMessage(), e);
	         throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		} catch (mx.gob.segob.nsjp.ws.cliente.solicitudtransferenciaimputado.NSJPNegocioException_Exception e) {
			logger.error(e.getMessage(), e);
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		}
	}
    
    

}
