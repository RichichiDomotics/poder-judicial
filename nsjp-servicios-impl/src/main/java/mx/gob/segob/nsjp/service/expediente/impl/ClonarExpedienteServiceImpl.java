/**
 * Nombre del Programa : ClonarExpedienteServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 Nov 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación del servicio para clonar el expediene de defensoria al llegar el segundo defendido
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

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.elemento.ElementoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.AnimalDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;
import mx.gob.segob.nsjp.dto.objeto.DocumentoOficialDTO;
import mx.gob.segob.nsjp.dto.objeto.EmbarcacionDTO;
import mx.gob.segob.nsjp.dto.objeto.EquipoComputoDTO;
import mx.gob.segob.nsjp.dto.objeto.ExplosivoDTO;
import mx.gob.segob.nsjp.dto.objeto.JoyaDTO;
import mx.gob.segob.nsjp.dto.objeto.NumerarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.expediente.ClonarExpedienteService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.objeto.IngresarAnimalService;
import mx.gob.segob.nsjp.service.objeto.IngresarArmaService;
import mx.gob.segob.nsjp.service.objeto.IngresarDocumentoOficialService;
import mx.gob.segob.nsjp.service.objeto.IngresarEmbarcacionService;
import mx.gob.segob.nsjp.service.objeto.IngresarEquipoDeComputoService;
import mx.gob.segob.nsjp.service.objeto.IngresarExplosivoService;
import mx.gob.segob.nsjp.service.objeto.IngresarJoyaService;
import mx.gob.segob.nsjp.service.objeto.IngresarNumerarioService;
import mx.gob.segob.nsjp.service.objeto.IngresarObjetoService;
import mx.gob.segob.nsjp.service.objeto.IngresarObraArteService;
import mx.gob.segob.nsjp.service.objeto.IngresarSustanciaService;
import mx.gob.segob.nsjp.service.objeto.IngresarTelefonoService;
import mx.gob.segob.nsjp.service.objeto.IngresarVegetalService;
import mx.gob.segob.nsjp.service.objeto.IngresarVehiculoService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio para clonar el expediene de defensoria al llegar
 * el segundo defendido.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional
public class ClonarExpedienteServiceImpl implements ClonarExpedienteService {

    @Autowired
    private BuscarExpedienteService buscarExpService;
    @Autowired
    private AsignarNumeroExpedienteService aisgnarExpService;

    @Autowired
    private IngresarIndividuoService ingresarInvoService;

    @Autowired
    private ExpedienteDAO expDao;
    @Autowired
    private ElementoDAO eleDao;
    @Autowired
    private DelitoDAO delDao;
    @Autowired
    private NumeroExpedienteDAO noExpDao;
    @Autowired
    private InvolucradoDAO invoDao;
    @Autowired
    private DomicilioDAO domDAO;

    @Autowired
    private IngresarObjetoService ingresarObjetoService;

    public final static Logger logger = Logger
            .getLogger(ClonarExpedienteServiceImpl.class);
    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.expediente.ClonarExpedienteService#clonarExpediente
     * (java.lang.Long)
     */
    @Override
    public ExpedienteDTO clonarExpediente(Long expedienteIdOriginal)
            throws NSJPNegocioException {
        logger.info("Inicia - clonarExpediente(...)");
        final ExpedienteDTO expParamTemp = new ExpedienteDTO();
        expParamTemp.setExpedienteId(expedienteIdOriginal);
        expParamTemp.setInvolucradosSolicitados(true);
        expParamTemp.setObjetosSolicitados(true);

        final ExpedienteDTO expOri = this.buscarExpService
                .obtenerExpedientePorExpedienteId(expParamTemp);

        Expediente expDesPojo = new Expediente();
        if (expOri.getCasoDTO() != null) {
            expDesPojo.setCaso(new Caso(expOri.getCasoDTO().getCasoId()));
        }
        expDesPojo.setFechaCreacion(new Date());

        final ExpedienteDTO expDes = new ExpedienteDTO();
        expDes.setExpedienteId(this.expDao.create(expDesPojo));

        List<InvolucradoDTO> involucrados = expOri.getInvolucradosDTO();
        logger.debug("involucrados.size() :: " + involucrados.size());
        for (InvolucradoDTO invoOri : involucrados) {
            invoOri.setElementoId(this.ingresarIndividuo(expDes, invoOri));

        }

        List<ObjetoDTO> objetos = expOri.getObjetosDTO();
        logger.debug("objetos.size() :: " + objetos.size());
        for (ObjetoDTO objOri : objetos) {
            objOri.setElementoId(this.ingresarObjeto(expDes, objOri));

        }
        logger.debug("El nuevo expediente clonado tiene el ID :: " + expDes.getExpedienteId()); 
        logger.info("Fin - clonarExpediente(...)");
        return expDes;
    }

    /**
     * @param expDes
     * @param invoOri
     * @throws NSJPNegocioException
     */
    private Long ingresarIndividuo(final ExpedienteDTO expDes,
            InvolucradoDTO invoOri) throws NSJPNegocioException {
        Involucrado invoPojo;
        logger.debug("Elemento del tipo " + invoOri.getClass() + " con folio ["
                + invoOri.getFolioElemento() + "]");
        invoPojo = invoDao.read(invoOri.getElementoId());
        InvolucradoDTO invoNuevo = InvolucradoTransformer.clonarInvolucrado(
                invoPojo,
                Calidades.getByValor(invoOri.getCalidadDTO()
                        .getValorIdCalidad().getIdCampo()), true);
        Domicilio domicilio = domDAO.obtenerDomicilioByRelacion(
                invoPojo.getElementoId(),
                new Long(Relaciones.RESIDENCIA.ordinal()));
        Domicilio domicilioNotificacion = domDAO.obtenerDomicilioByRelacion(
                invoPojo.getElementoId(),
                new Long(Relaciones.NOTIFICACION.ordinal()));

        if (domicilio != null) {
            invoNuevo.setDomicilio(DomicilioTransformer
                    .transformarDomicilio(domicilio));
            invoNuevo.getDomicilio().setElementoId(null);
        }
        if (domicilioNotificacion != null) {
            invoNuevo.setDomicilioNotificacion(DomicilioTransformer
                    .transformarDomicilio(domicilioNotificacion));
            invoNuevo.getDomicilioNotificacion().setElementoId(null);
        }

        invoNuevo.setExpedienteDTO(expDes);
        invoNuevo.setElementoId(null);
        invoNuevo.setFechaCreacionElemento(new Date());

        return this.ingresarInvoService.ingresarIndividuo(invoNuevo);
    }

    /**
     * @param expDes
     * @param objOri
     * @throws NSJPNegocioException
     */
    private Long ingresarObjeto(final ExpedienteDTO expDes, ObjetoDTO objOri)
            throws NSJPNegocioException {
        logger.debug("Objeto del tipo " + objOri.getClass() + " con folio ["
                + objOri.getFolioElemento() + "]");

        objOri.setExpedienteDTO(expDes);
        objOri.setElementoId(null);
        objOri.setFechaCreacionElemento(new Date());

        return this.ingresarObjetoService.ingresarObjeto(objOri);

    }
}
