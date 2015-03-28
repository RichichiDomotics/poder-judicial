/**
* Nombre del Programa : ConsultarDocumentosDictamenPorTipoServiceImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/07/2011
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
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.DictamenDAO;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Dictamen;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentosDictamenPorTipoService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DictamenTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para la atención de las consultas
 * de documentos recibidos para un funcionario de AMP
 * @version 1.0
 * @author Emigdio
 *
 */
@Service
@Transactional
public class ConsultarDocumentosDictamenPorTipoServiceImpl implements
		ConsultarDocumentosDictamenPorTipoService {
	
	@Autowired
	DictamenDAO dictamenDAO;
	
	@Override
	public List<DictamenDTO> consultarDocumentosRecibidosAMP(
			Formas tipoDocumento, FuncionarioDTO funcionarioQueRecibe)
			throws NSJPNegocioException {
		List<DictamenDTO> resultado = new ArrayList<DictamenDTO>();
		List<Dictamen> dictamenesBD = dictamenDAO.
		consultarDictamenesRecibidosParaUsuario(tipoDocumento,new Funcionario(funcionarioQueRecibe.getClaveFuncionario()));
		for(Dictamen dictamen:dictamenesBD){
			resultado.add(DictamenTransformer.transformarDictamenBasico(dictamen));
		}
		return resultado;
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.ConsultarDocumentosDictamenPorTipoService#consultarDetalleDictamenPorId(java.lang.Long)
	 */
	@Override
	public DictamenDTO consultarDetalleDictamenPorId(Long dictamenId) {
		return DictamenTransformer.transformarDictamenBasico(dictamenDAO.read(dictamenId));
	}

}
