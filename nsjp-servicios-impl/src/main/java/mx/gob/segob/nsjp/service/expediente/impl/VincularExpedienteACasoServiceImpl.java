/**
 * Nombre del Programa : VincularExpedienteACasoServiceImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 6 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Inmplementacion de metodos del servicio de asignacion de un expediente a un caso
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

import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.DefensaInvolucradoDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.DefensaInvolucrado;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.service.caso.impl.transform.CasoTransformer;
import mx.gob.segob.nsjp.service.expediente.VincularExpedienteACasoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Inmplementacion de metodos del servicio de asignacion de un expediente a un
 * caso.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Service
@Transactional
public class VincularExpedienteACasoServiceImpl
        implements
            VincularExpedienteACasoService {

    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(VincularExpedienteACasoServiceImpl.class);

    @Autowired
    private CasoDAO casoDAO;

    @Autowired
    private ExpedienteDAO expedienteDAO;
    @Autowired
    private DefensaInvolucradoDAO defensaInvDao;

    @Override
    public List<CasoDTO> consultarCasos(String numeroGeneralCaso)
            throws NSJPNegocioException {
        List<Caso> casos = casoDAO.consultarCasosPorNumero(numeroGeneralCaso);
        List<CasoDTO> casosDTO = new LinkedList<CasoDTO>();
        for (Caso caso : casos) {
            casosDTO.add(CasoTransformer.transformarCasoBasico(caso));
        }
        return casosDTO;
    }

    @Override
    public void vincularExpedienteACaso(ExpedienteDTO expediente, CasoDTO caso,
            InvolucradoDTO involucradoPG) throws NSJPNegocioException {
        if (logger.isDebugEnabled())
            logger.debug("/**** SERVICIO PARA RALACIONAR UN EXPEDIENTE A UN CASO ****/");

        if (!(expediente.getNumeroExpedienteId() != null)
                || !(caso.getCasoId() != null))
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

        Expediente exp = expedienteDAO
                .consultarExpedientePorIdNumerExpediente(expediente
                        .getNumeroExpedienteId());
        exp.setCaso(new Caso(caso.getCasoId()));

        expedienteDAO.update(exp);

        if (involucradoPG != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("involucradoPG.getElementoId() :: "
                        + involucradoPG.getElementoId());
            }
            if (exp.getNumeroExpedientes() != null
                    && exp.getNumeroExpedientes().size() == 1
                    && exp.getNumeroExpedientes().iterator().next()
                            .getDefensaInvolucrado() != null) {
                DefensaInvolucrado di = exp.getNumeroExpedientes().iterator()
                        .next().getDefensaInvolucrado();
                di.setInvolucradoPg(new Involucrado(involucradoPG
                        .getElementoId()));
                defensaInvDao.update(di);
            }
        }

        if (logger.isDebugEnabled())
            logger.debug("/**** LA RELACION DEL EXPEDIENTE CON EL CASO SE REALIAZO SATISFACTORIAMENTE ****/");
    }

}
