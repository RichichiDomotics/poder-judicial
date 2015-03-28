/**
* Nombre del Programa : ConsultarBitacoraConsultaServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
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
package mx.gob.segob.nsjp.service.bitacora.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.bitacora.BitacoraConsultaDAO;
import mx.gob.segob.nsjp.dto.bitacora.BitacoraConsultaDTO;
import mx.gob.segob.nsjp.dto.bitacora.FiltroBitacoraConsultaDTO;
import mx.gob.segob.nsjp.model.BitacoraConsulta;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.bitacora.ConsultarBitacoraConsultaService;
import mx.gob.segob.nsjp.service.bitacora.impl.transform.BitacoraConsultaTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de los servicios que permiten consultar la
 * bitacora de Consultas permitidas INQ
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class ConsultarBitacoraConsultaServiceImpl implements ConsultarBitacoraConsultaService{
    
    @Autowired
    private  BitacoraConsultaDAO bitacoraConsultaDAO;
    
	public List<BitacoraConsultaDTO> consultarBitacoraConsultaPorFiltro(FiltroBitacoraConsultaDTO filtroBitacoraConsultaDTO) 
		throws NSJPNegocioException{
		if(filtroBitacoraConsultaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		List<BitacoraConsultaDTO> bitacorasDTO = new ArrayList<BitacoraConsultaDTO>();
		
		Funcionario funcionario = FuncionarioTransformer.transformarFuncionario(filtroBitacoraConsultaDTO.getFuncionario());
		
		List<BitacoraConsulta> bitacoras = 
			bitacoraConsultaDAO.consultarBitacoraConsultaPorFiltros(
					filtroBitacoraConsultaDTO.getNumeroExpediente(), 
					filtroBitacoraConsultaDTO.getFechaConsulta(), 
					filtroBitacoraConsultaDTO.getHoraConsulta(), 
					funcionario, 
					filtroBitacoraConsultaDTO.getEsPermitida());
		
		for (BitacoraConsulta bitacoraConsulta : bitacoras) {
			bitacorasDTO.add(BitacoraConsultaTransformer.transformarBitacoraConsulta(bitacoraConsulta));
		}
		
		return bitacorasDTO;
	}
} 
