/**
 * Nombre del Programa : ConsultarEvidenciasResguardadasPorUsarioServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 Jul 2011
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
package mx.gob.segob.nsjp.service.evidencia.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasResguardadasPorUsuarioService;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional
public class ConsultarEvidenciasResguardadasPorUsuarioServiceImpl
        implements
            ConsultarEvidenciasResguardadasPorUsuarioService {

    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(ConsultarEvidenciasResguardadasPorUsuarioServiceImpl.class);

    @Autowired
    private EvidenciaDAO evidenciaDao;

    @Autowired
    private NumeroExpedienteDAO numExpDao;

    @Autowired
    private FuncionarioDAO funDao;

    @Override
    public List<EvidenciaDTO> consultarEvidenciasResguardadasPorUsuario(
            UsuarioDTO usuario) throws NSJPNegocioException {
        final List<EvidenciaDTO> resp = new ArrayList<EvidenciaDTO>();
        final List<Eslabon> fromBd = this.evidenciaDao
                .consultarEvidenciasResguardadasXUsario(usuario.getIdUsuario(),
                        null);
        EvidenciaDTO dto = null;
        EvidenciaDTO aux = null;
        NumeroExpediente noExpBD = null;
        final Map<String, EvidenciaDTO> eviMap = new HashMap<String, EvidenciaDTO>();
        if (fromBd != null && !fromBd.isEmpty()) {
            for (Eslabon row : fromBd) {
                dto = EvidenciaTransformer.tranformarBasico(row);
                aux = eviMap.get(dto.getCadenaDeCustodia().getFolio());
                if (aux != null) {
                    aux.setCantEvidenciasResguardadas(aux
                            .getCantEvidenciasResguardadas() + 1);
                } else {
                    noExpBD = this.numExpDao.obtenerNumeroExpediente(row
                            .getEvidencia().getObjeto().getExpediente()
                            .getExpedienteId(), new Long(
                            Areas.UNIDAD_INVESTIGACION.ordinal()));
                    if (noExpBD != null) {
                        dto.setDuenioEvidencia(funDao.read(
                                noExpBD.getFuncionario().getClaveFuncionario())
                                .getNombreCompleto());
                    }
                    dto.setCantEvidenciasResguardadas(1);
                    eviMap.put(dto.getCadenaDeCustodia().getFolio(), dto);
                }
            }
        }

        resp.addAll(eviMap.values());
        return resp;
    }

    @Override
    public List<EvidenciaDTO> consultarEvidenciasResguardadasPorUsuario(
            UsuarioDTO usuario, CadenaDeCustodiaDTO cadena)
            throws NSJPNegocioException {
        final List<EvidenciaDTO> resp = new ArrayList<EvidenciaDTO>();
        final List<Eslabon> fromBd = this.evidenciaDao
                .consultarEvidenciasResguardadasXUsario(usuario.getIdUsuario(),
                        cadena.getCadenaDeCustodiaId());
        EvidenciaDTO dto = null;
        NumeroExpediente noExpBD = null;
        if (fromBd != null && !fromBd.isEmpty()) {
            for (Eslabon row : fromBd) {
                dto = EvidenciaTransformer.transformarEvidencia(row
                        .getEvidencia(), true);
                noExpBD = this.numExpDao.obtenerNumeroExpediente(row
                        .getEvidencia().getObjeto().getExpediente()
                        .getExpedienteId(),
                        new Long(Areas.UNIDAD_INVESTIGACION.ordinal()));
                if (noExpBD != null) {
                    dto.setDuenioEvidencia(funDao.read(
                            noExpBD.getFuncionario().getClaveFuncionario())
                            .getNombreCompleto());
                    dto.setCantEvidenciasResguardadas(1);
                    resp.add(dto);
                }
            }
        }

        return resp;
    }

}
