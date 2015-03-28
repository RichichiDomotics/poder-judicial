/**
 * Nombre del Programa : IngresarPeritoServiceImpl.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del servicio para designar un abogado defensor
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
package mx.gob.segob.nsjp.service.funcionario.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.tarea.AgendaFuncionarioDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.AgendaFuncionario;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.funcionario.IngresarFuncionarioService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para ingresar un Perito.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
@Service
@Transactional
public class IngresarFuncionarioServiceImpl
        implements
            IngresarFuncionarioService {

    private final static Logger logger = Logger
            .getLogger(IngresarFuncionarioServiceImpl.class);

    @Autowired
    private FuncionarioDAO funDao;

    @Autowired
    private AgendaFuncionarioDAO agendaDao;

    @Override
    public FuncionarioDTO ingresarFuncionario(FuncionarioDTO peritoDTO)
            throws NSJPNegocioException {

        if (logger.isDebugEnabled()) {
            logger.debug("/**** SERVICIO PARA ASIGNAR UN PERITO ****/");
            logger.debug("peritoDTO :: " + peritoDTO);
        }
        if (peritoDTO.getTipoEspecialidad() == null
                && peritoDTO.getEspecialidad() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        if (logger.isDebugEnabled()) {
        	//Para guardar funcionario
        	if(peritoDTO.getDiscriminante() != null && peritoDTO.getDiscriminante().getCatDiscriminanteId()!=null)
            logger.debug("FUNCIONARIO DISCRIMINANTE :: " + peritoDTO.getDiscriminante().getCatDiscriminanteId());
        }
        
        Funcionario loPerito = FuncionarioTransformer
                .transformarFuncionario(peritoDTO);
        final Long count = this.funDao.countFuncionarios();
        boolean esPar = (count % 2 == 0);
        loPerito.setEsPar(esPar);
        if (logger.isDebugEnabled()) {
        	//Para guardar funcionario
        	if(loPerito.getDiscriminante() != null && loPerito.getDiscriminante().getCatDiscriminanteId()!=null)
        		logger.debug("FUNCIONARIO DISCRIMINANTE AFTER TRANSFORM :: " + loPerito.getDiscriminante().getCatDiscriminanteId());
        }
        
        loPerito.setClaveFuncionario(funDao.create(loPerito));
        AgendaFuncionario agn = new AgendaFuncionario();
        agn.setDinicioAgenda(new Date());
        agn.setFuncionario(loPerito);
        this.agendaDao.create(agn);
        FuncionarioDTO loPeritoDTO = new FuncionarioDTO();
        loPeritoDTO.setClaveFuncionario(loPerito.getClaveFuncionario());
        return loPeritoDTO;
    }

}
