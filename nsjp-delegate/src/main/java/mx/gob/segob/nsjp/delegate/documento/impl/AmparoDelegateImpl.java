/**
 * Nombre del Programa : AmparoDelegateImpl.java
 * Autor                            : Emigdio Hernández
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 / feb / 12
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación del Delegate para manipulación de Amparo
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
package mx.gob.segob.nsjp.delegate.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.documento.AmparoDelegate;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.amparo.AmparoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del Delegate para manipulación de Amparo
 * @author rgama
 *
 */
@Service("amparoDelegate")
public class AmparoDelegateImpl implements AmparoDelegate{

	@Autowired
    private AmparoService amparoService;

	@Override
	public List<InvolucradoDTO> consultarInvolucradosXAmparo(Long idAmparo)
			throws NSJPNegocioException {
				return amparoService.consultarInvolucradosXAmparo(idAmparo);
	}
	
}
