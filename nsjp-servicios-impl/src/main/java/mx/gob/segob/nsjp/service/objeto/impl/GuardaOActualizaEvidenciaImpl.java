package mx.gob.segob.nsjp.service.objeto.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.service.cadenadecustodia.impl.transform.CadenaDeCustodiaTransformer;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetoTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jacob Lobaco
 */
@Service
@Transactional
public class GuardaOActualizaEvidenciaImpl implements GuardaOActualizaEvidencia {

    @Autowired
    private EvidenciaDAO evidenciaDao;

    private Long registraEvidencia(Evidencia evidencia){
    	//Calcular el consecutivo de la evidencia con referencia a la Cadena de Custodia 
    	
    	List<Evidencia> listaEvidencias = evidenciaDao.consultarEvidenciasXCadena(evidencia.getCadenaDeCustodia().getCadenaDeCustodiaId());
    	if(listaEvidencias==null || listaEvidencias.isEmpty())
    		evidencia.setNumeroEvidencia(1L);
    	else
    		evidencia.setNumeroEvidencia(new Long(listaEvidencias.size()+1));
    	
        return evidenciaDao.create(evidencia);
    }

    private Long actualizaEvidencia(Evidencia evidencia){
        Evidencia actualizadora = evidenciaDao.read(evidencia.getEvidenciaId());
        IngresarVehiculoServiceImpl.actualizaObjeto(evidencia, actualizadora);
        return evidencia.getEvidenciaId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardaOActualizaEvidencia(ObjetoDTO objetoDto)
            throws NSJPNegocioException {
        if (objetoDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        CadenaDeCustodiaDTO cadenaDeCustodiaDTO = objetoDto.getCadenaDeCustodia();
        if (cadenaDeCustodiaDTO == null || cadenaDeCustodiaDTO.getCadenaDeCustodiaId() == null
                || cadenaDeCustodiaDTO.getEvidencia() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        CadenaDeCustodia cadenaDeCustodia =
                CadenaDeCustodiaTransformer.transformarCadenaDeCustodia(cadenaDeCustodiaDTO);
        EvidenciaDTO evidenciaDto = cadenaDeCustodiaDTO.getEvidencia();
        Evidencia evidencia = EvidenciaTransformer.transformarEvidencia(evidenciaDto);
        evidencia.setCadenaDeCustodia(cadenaDeCustodia);
        evidencia.setObjeto(ObjetoTransformer.transformarObjeto(objetoDto));
        if (evidencia.getEvidenciaId() == null || evidencia.getEvidenciaId() == 0) {
            registraEvidencia(evidencia);
        }else{
            actualizaEvidencia(evidencia);
        }

    }
}
