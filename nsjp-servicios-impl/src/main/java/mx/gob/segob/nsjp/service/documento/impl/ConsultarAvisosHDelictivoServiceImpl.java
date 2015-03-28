/**
 * 
 */
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.documento.AvisoHechoDelictivoDAO;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.model.AvisoHechoDelictivo;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.service.documento.ConsultarAvisosHDelictivoService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.AvisoHechoDelictivoTransformer;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adrian
 * 
 */
@Service
@Transactional
public class ConsultarAvisosHDelictivoServiceImpl
        implements
            ConsultarAvisosHDelictivoService {
    private final static Logger logger = Logger
            .getLogger(ConsultarAvisosHDelictivoServiceImpl.class);

    @Autowired
    private AvisoHechoDelictivoDAO avisoHDDao;
    @Autowired
    private DomicilioDAO domicilioDAO;
    @Autowired
    private ExpedienteDAO expedienteDao;

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.documento.ConsultarAvisosHDelictivoService#
     * consultarAvisosHDelictivoPorEstatus(java.lang.Long)
     */
    @Override
    public List<AvisoHechoDelictivoDTO> consultarAvisosHDelictivoPorEstatus(
            Long estatusNotificacion) throws NSJPNegocioException {
        if (logger.isDebugEnabled())
            logger.debug("/**** SERVICIO PARA CONSULTAR AVISOS DE HECHOS DELICTIVOS POR ESTATUS ****/");

        if (estatusNotificacion == null)
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

        /* Operación */
        List<AvisoHechoDelictivo> avisos = avisoHDDao
                .consultarAvisosHDelictivoPorEstatus(estatusNotificacion);
        List<AvisoHechoDelictivoDTO> avisosDTO = new ArrayList<AvisoHechoDelictivoDTO>();
        for (AvisoHechoDelictivo avi : avisos) {
            if (avi.getHecho() != null && avi.getHecho().getLugar() != null) {
                Domicilio domic = domicilioDAO.read(avi.getHecho().getLugar()
                        .getElementoId());
                AvisoHechoDelictivoDTO avisoDTO = AvisoHechoDelictivoTransformer
                        .transformarAvisoDTO(avi);
                if (domic != null) {
                    HechoDTO hechoDTO = avisoDTO.getHechoDTO();
                    hechoDTO.setDomicilio(DomicilioTransformer
                            .transformarDomicilio(domic));
                    avisoDTO.setHechoDTO(hechoDTO);
                }

                avisosDTO.add(avisoDTO);
            }
        }

        return avisosDTO;
    }

    @Override
    public AvisoHechoDelictivoDTO consultarAvisoHDelictivo(
            AvisoHechoDelictivoDTO avisoDTO) throws NSJPNegocioException {
        if (logger.isDebugEnabled()) {
            logger.debug("/**** SERVICIO PARA CONSULTAR AVISOS DE HECHOS DELICTIVOS POR ESTATUS ****/");
        }
        if (avisoDTO == null || avisoDTO.getDocumentoId() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }

        /* Operación */
        AvisoHechoDelictivo avi = avisoHDDao.read(avisoDTO.getDocumentoId());
        avisoDTO = AvisoHechoDelictivoTransformer.transformarAvisoDTO(avi);
        if (avi.getHecho() != null && avi.getHecho().getLugar() != null) {
            Domicilio domic = domicilioDAO.read(avi.getHecho().getLugar()
                    .getElementoId());
            if (domic != null) {
                avisoDTO.getHechoDTO().setDomicilio(DomicilioTransformer
                        .transformarDomicilio(domic));
                avisoDTO.getHechoDTO().setLugar(avisoDTO.getHechoDTO().getDomicilio());
                logger.debug("Lugar :: "+avisoDTO.getHechoDTO().getLugar());
            }
        }
        return avisoDTO;
    }

    @Override
    public AvisoHechoDelictivoDTO consultarAvisoHechoXId(Long avisoId)
            throws NSJPNegocioException {

        if (logger.isDebugEnabled())
            logger.debug("/**** SERVICIO PARA CONSULTAR AVISOS DE HECHOS DELICTIVOS POR ID ****/");

        if (avisoId == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        AvisoHechoDelictivoDTO avisoDTO = new AvisoHechoDelictivoDTO();
        AvisoHechoDelictivo aviso = avisoHDDao.read(avisoId);
        avisoDTO = AvisoHechoDelictivoTransformer.transformarAvisoDTO(aviso);
        if(aviso.getHecho()!= null && aviso.getHecho().getLugar() != null && aviso.getHecho().getLugar()
                .getElementoId() != null){

            Domicilio domic = domicilioDAO.read(aviso.getHecho().getLugar()
                    .getElementoId());
            if (domic != null) {
                HechoDTO hechoDTO = avisoDTO.getHechoDTO();
                hechoDTO.setDomicilio(DomicilioTransformer
                        .transformarDomicilio(domic));
                avisoDTO.setHechoDTO(hechoDTO);
            }
        }        

        return avisoDTO;
    }

    @Override
    public AvisoHechoDelictivoDTO obtenerAvisoPorIdExpediente(Long idExpediente) {
        Expediente exp = this.expedienteDao.read(idExpediente);
        if (exp.getHecho() != null
                && exp.getHecho().getAvisoHechoDelictivo() != null) {
            ExpedienteDTO expDTO = ExpedienteTransformer
                    .transformarExpedienteBasico(exp);
            HechoDTO hechoDto = new HechoDTO();
            hechoDto.setHechoId(exp.getHecho().getHechoId());
            AvisoHechoDelictivoDTO avisoDto = new AvisoHechoDelictivoDTO();
            avisoDto.setDocumentoId(exp.getHecho().getAvisoHechoDelictivo()
                    .getDocumentoId());
            avisoDto.setFolioNotificacion(exp.getHecho()
                    .getAvisoHechoDelictivo().getFolioNotificacion());
            avisoDto.setFolioDocumento(exp.getHecho().getAvisoHechoDelictivo()
                    .getFolioDocumento());
            if (exp.getHecho().getAvisoHechoDelictivo().getCatDelito() != null) {
                CatDelitoDTO delDto = new CatDelitoDTO();
                delDto.setCatDelitoId(exp.getHecho().getAvisoHechoDelictivo()
                        .getCatDelito().getCatDelitoId());
                delDto.setNombre(exp.getHecho().getAvisoHechoDelictivo()
                        .getCatDelito().getNombre());
                avisoDto.setCatDelito(delDto);
            }
            hechoDto.setExpediente(expDTO);
            avisoDto.setHechoDTO(hechoDto);
            avisoDto.setFechaCreacion(exp.getHecho().getAvisoHechoDelictivo()
                    .getFechaCreacion());
            return avisoDto;
        }
        return null;
    }

}
