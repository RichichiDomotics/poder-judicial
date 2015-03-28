/**
 * Nombre del Programa : AdministrarInspeccionServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 Oct 2011
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
package mx.gob.segob.nsjp.service.insepeccion.impl;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import mx.gob.segob.nsjp.comun.enums.inspeccion.EstatusInspeccion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.inspeccion.InspeccionDAO;
import mx.gob.segob.nsjp.dao.inspeccion.MultaSancionDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.inspeccion.InspeccionDTO;
import mx.gob.segob.nsjp.model.Inspeccion;
import mx.gob.segob.nsjp.model.MultaSancion;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.insepeccion.AdministrarInspeccionService;
import mx.gob.segob.nsjp.service.insepeccion.impl.transform.InspeccionTransformer;

import org.apache.commons.lang.StringUtils;
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
public class AdministrarInspeccionServiceImpl
        implements
            AdministrarInspeccionService {
    /**
     * Logger
     */
    private final static Logger logger = Logger
            .getLogger(AdministrarInspeccionServiceImpl.class);
    @Autowired
    private InspeccionDAO inspeccionDao;
    @Autowired
    private MultaSancionDAO msDao ;

    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.insepeccion.AdministrarInspeccionService#
     * consultarInspecciones(mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO,
     * mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
     */
    @Override
    public List<InspeccionDTO> consultarInspecciones(
            FuncionarioDTO funInspeccionado, ExpedienteDTO expInspeccionado)
            throws NSJPNegocioException {
        logger.info("Inicia - consultarInspecciones(...)");
        final List<Inspeccion> entities = this.inspeccionDao
                .consultarInspecciones(
                        funInspeccionado.getClaveFuncionario(),
                        expInspeccionado != null ? expInspeccionado
                                .getNumeroExpedienteId() : null);

        List<InspeccionDTO> resp = new ArrayList<InspeccionDTO>();

        for (Inspeccion pojo : entities) {
            resp.add(InspeccionTransformer.transformarDTO(pojo));
        }

        return resp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.insepeccion.AdministrarInspeccionService#
     * registrarInspeccion(mx.gob.segob.nsjp.dto.inspeccion.InspeccionDTO)
     */
    @Override
    public Long registrarInspeccion(InspeccionDTO input)
            throws NSJPNegocioException {
        logger.info("Inicia - registrarInspeccion(...)");
        Inspeccion pojo = InspeccionTransformer.transformarEntity(input);
        final String ulltimoFolio = this.inspeccionDao.obtenerUltimoFolio();
        pojo.setFolioInspeccion(this.generarFolio(ulltimoFolio));
        
        final Long idInspecc = this.inspeccionDao.create(pojo);
        
        if (pojo.getMultaSancion()!=null) {
            MultaSancion ms = this.msDao.read(pojo.getMultaSancion().getMultaSancionId());
            ms.setInspeccion(pojo);
            this.msDao.update(ms);
        }
        
        return idInspecc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.insepeccion.AdministrarInspeccionService#
     * actualizarDescripcion(mx.gob.segob.nsjp.dto.inspeccion.InspeccionDTO)
     */
    @Override
    public void actualizarDescripcion(InspeccionDTO input)
            throws NSJPNegocioException {
        logger.info("Inicia - actualizarDescripcion(...)");
        Inspeccion pojo = this.inspeccionDao.read(input.getInspeccionId());
        pojo.setDescripcion(input.getDescripcion());
        this.inspeccionDao.update(pojo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.insepeccion.AdministrarInspeccionService#
     * concluirInspeccion(mx.gob.segob.nsjp.dto.inspeccion.InspeccionDTO)
     */
    @Override
    public void concluirInspeccion(InspeccionDTO input)
            throws NSJPNegocioException {
        logger.info("Inicia - concluirInspeccion(...)");
        Inspeccion pojo = this.inspeccionDao.read(input.getInspeccionId());
        pojo.setEstatus(new Valor(EstatusInspeccion.CONCLUIDA.getValorId()));
        this.inspeccionDao.update(pojo);

    }

    @Override
    public InspeccionDTO obtenerInspeccion(Long idInspeccion)
            throws NSJPNegocioException {
        logger.info("Inicia - obtenerInspeccion(...)");
        Inspeccion pojo = this.inspeccionDao.read(idInspeccion);
        return InspeccionTransformer.transformarDTO(pojo);
    }
    /**
     * Genera el siguiente folio.
     * 
     * @param folioUltimo
     * @return
     * @throws NSJPNegocioException
     */
    private String generarFolio(String folioUltimo)
            throws NSJPNegocioException {
        Long consecutivo = null;
        if (StringUtils.isBlank(folioUltimo)) {
            consecutivo = 1L;
        } else {
            Long temp = Long.parseLong(folioUltimo.substring(3, 7));
            consecutivo = temp++;
        }
        String format1 = "0000";
        DecimalFormat fm1 = new DecimalFormat(format1,
                new DecimalFormatSymbols(Locale.US));

        return this.inspeccionDao.consultarInsitucionActual().getMonograma()
                + "I" + Calendar.getInstance().get(Calendar.YEAR)
                + fm1.format(consecutivo);
    }

}
