/**
 * Nombre del Programa : ConsultarEvidenciaPorAlmacenServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 29-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.almacen.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.evidencia.TiposEslabon;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.service.almacen.ConsultarEvidenciaPorAlmacenService;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;
import mx.gob.segob.nsjp.service.objeto.impl.transform.AlmacenTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultarEvidenciaPorAlmacenServiceImpl implements ConsultarEvidenciaPorAlmacenService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ConsultarEvidenciaPorAlmacenServiceImpl.class);

    @Autowired
    private EvidenciaDAO evidenciaDao;
    @Autowired
    private CasoDAO casoDAO;
    @Autowired
    private FuncionarioDAO funcionarioDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EvidenciaDTO> consultarEvidenciaPorAlmacen(AlmacenDTO almacenDto)
            throws NSJPNegocioException {
        if (almacenDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Almacen almacen = AlmacenTransformer.transformarAlmacen(almacenDto);
        List<Evidencia> evidencias = evidenciaDao.consultarEvidenciaPorAlmacen(almacen);
        List<EvidenciaDTO> evidenciasDto = Collections.EMPTY_LIST;
        if (!evidencias.isEmpty()) {
            evidenciasDto = new LinkedList<EvidenciaDTO>();
            for (Evidencia evidencia : evidencias) {
                EvidenciaDTO evidenciaDto = EvidenciaTransformer.transformarEvidencia(evidencia, true);
                evidenciasDto.add(evidenciaDto);
            }
        }
        return evidenciasDto;
    }

	@Override
	public List<EvidenciaDTO> consultarEvidenciasXAlmacenXEstatus(
			UsuarioDTO usuarioDTO, Long estatusEv, CasoDTO casoDTO, Long idAmacen)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR EVIDENCIAS POR ALMACEN OPCIONALMENTE(ESTATUS Y CASO) ****/");
		
		if(usuarioDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(usuarioDTO.getFuncionario()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(usuarioDTO.getFuncionario().getClaveFuncionario()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Long idCaso=null;
		if(casoDTO!=null){
			if(casoDTO.getNumeroGeneralCaso()!=null)
				idCaso=casoDAO.consultarIdXNumeroCaso(casoDTO.getNumeroGeneralCaso());
			else if(casoDTO.getCasoId()!=null)
				idCaso=casoDTO.getCasoId();
		}
		Almacen almacen= null;
		if(idAmacen != null && idAmacen >0)
			almacen = new Almacen(idAmacen);
		else///*Busca a que almacen le corresponde al Funcionario*/	
			almacen = funcionarioDAO.obtenerAlmacenXFuncionario(usuarioDTO.getFuncionario().getClaveFuncionario());
		
		if(almacen==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		else if(almacen.getIdentificadorAlmacen()==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
			
		List<Evidencia> evidencias = evidenciaDao.consultarevidenciaXAlmacenXEstatus(almacen.getIdentificadorAlmacen(),estatusEv, idCaso);
		
		if(estatusEv==null)
			estatusEv=99L;
		
		List<EvidenciaDTO> evidenciasDto = new ArrayList<EvidenciaDTO>();
        if (!evidencias.isEmpty()) {
            for (Evidencia evidencia : evidencias) {
            	boolean esSolicitud=false;
            	if(evidencia.getEslabones().size()>0){
    				Eslabon eslab = ultimoEslabon(evidencia.getEslabones().iterator());
    				if(eslab!=null){
    					if(estatusEv.equals(-1L) && eslab.getTipoEslabon().getValorId().equals(TiposEslabon.SOLICITUD.getValorId())){
        					esSolicitud = true;
        				}
        				Set<Eslabon> eslabon = new HashSet<Eslabon>(0);
        				eslabon.add(eslab);
        				evidencia.setEslabones(eslabon);
    				}else
    					continue;
    			}
            	if(estatusEv.equals(-1L)){
            		if(esSolicitud)
            			evidenciasDto.add(EvidenciaTransformer.transformarEvidencia(evidencia, true));
            	}else{
            		evidenciasDto.add(EvidenciaTransformer.transformarEvidencia(evidencia, true));
            	}
            }
        }
        return evidenciasDto;
	}
	
	private Eslabon ultimoEslabon(Iterator<Eslabon> iterator) {
		Eslabon resp=null;
		Long id = -1L;
		while (iterator.hasNext()) {
			Eslabon eslabon = (Eslabon) iterator.next();
			if (eslabon.getEslabonId() > id)
				resp = eslabon;
		}
		return resp;
	}

   
}
