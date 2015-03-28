/**
 * Nombre del Programa : AdministrarMultaSancionServiceImpl.java
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

import mx.gob.segob.nsjp.comun.enums.inspeccion.EstatusMultaSancion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.inspeccion.MultaSancionDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.inspeccion.MultaSancionDTO;
import mx.gob.segob.nsjp.model.MultaSancion;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.insepeccion.AdministrarMultaSancionService;
import mx.gob.segob.nsjp.service.insepeccion.impl.transform.MultaSancionTransformer;

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
public class AdministrarMultaSancionServiceImpl
        implements
            AdministrarMultaSancionService {
    /**
     * Logger
     */
    private final static Logger logger = Logger
            .getLogger(AdministrarMultaSancionService.class);
    @Autowired
    private MultaSancionDAO multaDao;
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.insepeccion.AdministrarMultaSancionService#
     * registrarMulta(mx.gob.segob.nsjp.dto.inspeccion.MultaSancionDTO)
     */
    @Override
    public Long registrarMulta(MultaSancionDTO input)
            throws NSJPNegocioException {
        logger.info("Inicia - registrarMulta(...)");
        final MultaSancion pojo = MultaSancionTransformer.transformarEntity(input);
        pojo.setFolioMultaSancion(this.generarFolio(this.multaDao.obtenerUltimoFolio()));
        return multaDao.create(pojo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.insepeccion.AdministrarMultaSancionService#
     * consultarMultas(mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)
     */
    @Override
    public List<MultaSancionDTO> consultarMultas(FuncionarioDTO funMultado)
            throws NSJPNegocioException {
        logger.info("Inicia - consultarMultas(...)");
        
        final List<MultaSancion> entities = this.multaDao.consultarMultas(funMultado.getClaveFuncionario());
        final List<MultaSancionDTO> resp = new ArrayList<MultaSancionDTO>();

        for (MultaSancion pojo:entities) {
            resp.add(MultaSancionTransformer.transformarDTO(pojo));
        }
        
        return resp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.insepeccion.AdministrarMultaSancionService#
     * actualizarDescripcion(mx.gob.segob.nsjp.dto.inspeccion.MultaSancionDTO)
     */
    @Override
    public void actualizarDescripcion(MultaSancionDTO input)
            throws NSJPNegocioException {
        logger.info("Inicia - actualizarDescripcion(...)");
        MultaSancion pojo = this.multaDao.read(input.getMultaSancionId());
        pojo.setDescripcion(input.getDescripcion());
        this.multaDao.update(pojo);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.insepeccion.AdministrarMultaSancionService#
     * saldarInspeccion(mx.gob.segob.nsjp.dto.inspeccion.MultaSancionDTO)
     */
    @Override
    public void saldarMulta(MultaSancionDTO input) throws NSJPNegocioException {
        logger.info("Inicia - consultarInspecciones(...)");
        MultaSancion pojo = this.multaDao.read(input.getMultaSancionId());
        pojo.setEstatus(new Valor(EstatusMultaSancion.SALDADA.getValorId()));
        this.multaDao.update(pojo);

    }

    @Override
    public MultaSancionDTO obtenerMulta(Long idMulta)
            throws NSJPNegocioException {
        logger.info("Inicia - obtenerMulta(...)");
        MultaSancion pojo = this.multaDao.read(idMulta);
        return MultaSancionTransformer.transformarDTO(pojo);
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

        return this.multaDao.consultarInsitucionActual().getMonograma()
                + "M" + Calendar.getInstance().get(Calendar.YEAR)
                + fm1.format(consecutivo);
    }

}
