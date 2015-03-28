/**
* Nombre del Programa : IngresarMedidaServiceTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/08/2011
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
package mx.gob.segob.nsjp.service.test.medida.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.service.medida.IngresarMedidaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class IngresarMedidaServiceImplTest extends
	BaseTestServicios<IngresarMedidaService> {
	
	public void testIngresarMedidaALterna(){
		MedidaAlternaDTO medidaAlternaDTO = new MedidaAlternaDTO();
		
		Long involucradoId= 43L;
		Long expedienteId = 1L; 
		//Long sentenciaId = 3L;
		Long funcionarioId = 2L;
		Long tipoMedida = 2080L;				
		//TIPOS DE MEDIDA
		//2080	Prisión preventiva
		
		//Datos necesarios para Documento
		medidaAlternaDTO.setNombreDocumento("Nombre de la Medida ");
		medidaAlternaDTO.setFechaCreacion(new Date());
		
		//Tipo Documento "Medida".  2384	Medida
//		medidaAlternaDTO.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.MEDIDA.getValorId()));
		//Forma Asociada al Tipo Documento de "Medida". 28	
//		medidaAlternaDTO.setFormaDTO(new FormaDTO(28L));

		//Datos necesario de Medida
		medidaAlternaDTO.setFechaInicio(new Date());
		medidaAlternaDTO.setInvolucrado(new InvolucradoDTO(involucradoId));
		
		//Datos de Medida
		medidaAlternaDTO.setDescripcionMedida("Descripcion de la medida.."+ tipoMedida);
		medidaAlternaDTO.setValorTipoMedida(new ValorDTO(tipoMedida));
		medidaAlternaDTO.setFuncionario(new FuncionarioDTO(funcionarioId));
		medidaAlternaDTO.setDomicilio(obtenerDomicilioDTO(new ExpedienteDTO(expedienteId )) );
		
		//Datos de Medida Alterna
		//medidaAlternaDTO.setSentenciaId(sentenciaId);
		medidaAlternaDTO.setAnios((short) 3);
		medidaAlternaDTO.setMeses((short) 10);
		
		try {
			Long medidaId = service.ingresarMedida(medidaAlternaDTO);
			logger.info("Valor de la medida:" + medidaId);
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
	
	private DomicilioDTO obtenerDomicilioDTO(ExpedienteDTO expedienteDTO){
		DomicilioDTO domicilio = new DomicilioDTO();
		domicilio.setCalle("calle1");
		domicilio.setNumeroExterior("10");
		domicilio.setNumeroInterior("201");
		domicilio.setNumeroLote("Lote");
		domicilio.setReferencias("Ref MOD");
		domicilio.setEntreCalle1("entre calle 1");
		domicilio.setEntreCalle2("entre calle 2");
		domicilio.setAlias("Ranchito");
		domicilio.setEdificio("C");
		domicilio.setAsentamientoDTO(new AsentamientoDTO(20684L));
		domicilio.setEntidadDTO(new EntidadFederativaDTO(23L));
		domicilio.setMunicipioDTO(new MunicipioDTO(1L));
		domicilio.setCiudadDTO(new CiudadDTO(1L));
		domicilio.setValorCalleId(new ValorDTO(72L)); //Tipo Calle
		domicilio.setFechaCreacionElemento(new Date());
		//Lugar -> Elemento
		domicilio.setDescripcion("descripcion domicilio 1");
		//CoordenadaGeografica - Lugar
		domicilio.setLatitud("100");
		domicilio.setLongitud("101");
		//Calidad de Domicilio
		CalidadDTO calidadDomicilio = new CalidadDTO();
		calidadDomicilio.setDescripcionEstadoFisico("En buen estado");
		calidadDomicilio.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
		calidadDomicilio.setCalidades(Calidades.DOMICILIO);				
		domicilio.setCalidadDTO(calidadDomicilio);
		domicilio.setExpedienteDTO(expedienteDTO);
		return domicilio;
	}
}

