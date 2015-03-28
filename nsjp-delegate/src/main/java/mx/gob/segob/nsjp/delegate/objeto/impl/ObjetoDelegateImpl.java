/**
* Nombre del Programa : ObjetoDelegateImpl.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 2 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Implementacion de Delaegate para lops objetos                   
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.delegate.objeto.impl;


import java.util.List;

import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.objeto.ObjetoDelegate;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.AeronaveDTO;
import mx.gob.segob.nsjp.dto.objeto.AnimalDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;
import mx.gob.segob.nsjp.dto.objeto.DocumentoOficialDTO;
import mx.gob.segob.nsjp.dto.objeto.EmbarcacionDTO;
import mx.gob.segob.nsjp.dto.objeto.EquipoComputoDTO;
import mx.gob.segob.nsjp.dto.objeto.ExplosivoDTO;
import mx.gob.segob.nsjp.dto.objeto.JoyaDTO;
import mx.gob.segob.nsjp.dto.objeto.NumerarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObraArteDTO;
import mx.gob.segob.nsjp.dto.objeto.SustanciaDTO;
import mx.gob.segob.nsjp.dto.objeto.TelefoniaDTO;
import mx.gob.segob.nsjp.dto.objeto.VegetalDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.service.objeto.AdministrarPertenenciasDetenidoService;
import mx.gob.segob.nsjp.service.objeto.AsociarObjetoAlmacenService;
import mx.gob.segob.nsjp.service.objeto.ConsultarObjetosNoEvidenciaService;
import mx.gob.segob.nsjp.service.objeto.ConsultarObjetosPorTipoConFolioDeCustodiaService;
import mx.gob.segob.nsjp.service.objeto.IngresarAeronaveService;
import mx.gob.segob.nsjp.service.objeto.IngresarAnimalService;
import mx.gob.segob.nsjp.service.objeto.IngresarArmaService;
import mx.gob.segob.nsjp.service.objeto.IngresarDocumentoOficialService;
import mx.gob.segob.nsjp.service.objeto.IngresarEmbarcacionService;
import mx.gob.segob.nsjp.service.objeto.IngresarEquipoDeComputoService;
import mx.gob.segob.nsjp.service.objeto.IngresarExplosivoService;
import mx.gob.segob.nsjp.service.objeto.IngresarJoyaService;
import mx.gob.segob.nsjp.service.objeto.IngresarNumerarioService;
import mx.gob.segob.nsjp.service.objeto.IngresarObraArteService;
import mx.gob.segob.nsjp.service.objeto.IngresarOtroObjetoService;
import mx.gob.segob.nsjp.service.objeto.IngresarSustanciaService;
import mx.gob.segob.nsjp.service.objeto.IngresarTelefonoService;
import mx.gob.segob.nsjp.service.objeto.IngresarVegetalService;
import mx.gob.segob.nsjp.service.objeto.IngresarVehiculoService;
import mx.gob.segob.nsjp.service.objeto.ObtenerObjetoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementacion del Delegate para objetos 
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
public class ObjetoDelegateImpl implements ObjetoDelegate {

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.objeto.ObjetoDelegate#ingresarVehiculo(mx.gob.segob.nsjp.dto.objeto.VehiculoDTO)
	 */
	@Autowired
	private IngresarVehiculoService ingresarVehiculoService;
	@Autowired
	private IngresarEquipoDeComputoService ingresarEquipoDeComputoService;
	@Autowired
	private IngresarTelefonoService ingresarTelefonoService;
	@Autowired
	private IngresarArmaService ingresarArmaService;
	@Autowired
	private IngresarExplosivoService ingresarExplosivoService;
	@Autowired
	private IngresarSustanciaService ingresarSustanciaService;
	@Autowired
	private IngresarAnimalService ingresarAnimalService;
	@Autowired
	private IngresarAeronaveService ingresarAeronaveService;
	@Autowired
	private IngresarEmbarcacionService ingresarEmbarcacionService;
	@Autowired
	private IngresarNumerarioService ingresarNumerarioService;
	@Autowired
	private IngresarVegetalService ingresarVegetalService;
	@Autowired
	private IngresarDocumentoOficialService ingresarDocumentoOficialService;
	@Autowired
	private IngresarJoyaService ingresarJoyaService;
	@Autowired
	private IngresarObraArteService ingresarObraArteService;
	@Autowired
	private IngresarOtroObjetoService ingresarOtroObjetoService;
	@Autowired
	private ObtenerObjetoService obtenerObjetoService;
	@Autowired
	private ConsultarObjetosNoEvidenciaService consultarObjetosNoEvidenciaService;
	@Autowired 
	private AdministrarPertenenciasDetenidoService administrarPertenenciasDetenidoService;
	@Autowired
	private AsociarObjetoAlmacenService asociarObjetoAlmacenService;
	@Autowired
	private ConsultarObjetosPorTipoConFolioDeCustodiaService conFolioDeCustodiaService;
	
	
	
	@Override
	public Long ingresarVehiculo(VehiculoDTO vehiculoDto)
			throws NSJPNegocioException {
		
		return this.ingresarVehiculoService.ingresarVehiculo(vehiculoDto);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.objeto.ObjetoDelegate#ingresarEquipoDeComputo(mx.gob.segob.nsjp.dto.objeto.EquipoComputoDTO)
	 */
	@Override
	public Long ingresarEquipoDeComputo(EquipoComputoDTO equipoComputoDto)
			throws NSJPNegocioException {
		
		return ingresarEquipoDeComputoService.ingresarEquipoComputo(equipoComputoDto);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.objeto.ObjetoDelegate#ingresarTelefono(mx.gob.segob.nsjp.dto.persona.TelefonoDTO)
	 */
	@Override
	public Long ingresarTelefono(TelefoniaDTO telefoniaDTO)
			throws NSJPNegocioException {
		
		return ingresarTelefonoService.ingresarTelefono(telefoniaDTO);
		
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.objeto.ObjetoDelegate#ingresarArma(mx.gob.segob.nsjp.dto.objeto.ArmaDTO)
	 */
	@Override
	public Long ingresarArma(ArmaDTO armaDto) throws NSJPNegocioException {
		
		return this.ingresarArmaService.ingresarArma(armaDto);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.objeto.ObjetoDelegate#ingresarExplosivo(mx.gob.segob.nsjp.dto.objeto.ExplosivoDTO)
	 */
	@Override
	public Long ingresarExplosivo(ExplosivoDTO explosivoDto)
			throws NSJPNegocioException {
		
		return ingresarExplosivoService.ingresarExplosivo(explosivoDto);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.objeto.ObjetoDelegate#ingresarSustancia(mx.gob.segob.nsjp.dto.objeto.SustanciaDTO)
	 */
	@Override
	public Long ingresarSustancia(SustanciaDTO sustanciaDto)
			throws NSJPNegocioException {
		
		return ingresarSustanciaService.ingresarSustancia(sustanciaDto);
	}

	@Override
	public Long ingresarAnimal(AnimalDTO animalDto) throws NSJPNegocioException {
		
		return this.ingresarAnimalService.ingresarAnimal(animalDto);
	}

	@Override
	public Long ingresarAeronave(AeronaveDTO aeronaveDto)
			throws NSJPNegocioException {
		
		return ingresarAeronaveService.ingresarAeronave(aeronaveDto);
	}

	@Override
	public Long ingresarEmbarcacion(EmbarcacionDTO embarcacionDto)
			throws NSJPNegocioException {
		
		return this.ingresarEmbarcacionService.ingresarEmbarcacion(embarcacionDto);
	}

	@Override
	public Long ingresarNumerario(NumerarioDTO numerarioDTO)
			throws NSJPNegocioException {
		
		return this.ingresarNumerarioService.ingresarNumerario(numerarioDTO);
	}

	@Override
	public Long ingresarVegetal(VegetalDTO vegetalDTO)
			throws NSJPNegocioException {
		
		return this.ingresarVegetalService.ingresarVegetalService(vegetalDTO);
	}

	@Override
	public Long ingresarDocumentoOficial(
			DocumentoOficialDTO documentoOdificalDto)
			throws NSJPNegocioException {
		
		return this.ingresarDocumentoOficialService.ingresarDocumentoOficial(documentoOdificalDto);
	}

	@Override
	public Long ingresarJoya(JoyaDTO joyaDto) throws NSJPNegocioException {
		
		return  this.ingresarJoyaService.ingresarJoya(joyaDto);
	}

	@Override
	public Long ingresarObraArte(ObraArteDTO obraArteDto)
			throws NSJPNegocioException {
		return this.ingresarObraArteService.ingresarObraArte(obraArteDto); //this.ingresarObraArteService;
	}
	
	@Override
	public Long ingresarOtroObjeto(ObjetoDTO objetoDto) throws NSJPNegocioException{
		return this.ingresarOtroObjetoService.ingresarOtroObjeto(objetoDto);
	}

	@Override
	public List<ObjetoDTO> consultarInventarioPertenenciasDetenido(
			InvolucradoDTO detenido) throws NSJPNegocioException {
		return administrarPertenenciasDetenidoService.consultarInventarioPertenenciasDetenido(detenido);
	}

	@Override
	public Long registrarPertenenciaDetenido(ObjetoDTO objeto,
			InvolucradoDTO detenido) throws NSJPNegocioException {
		return administrarPertenenciasDetenidoService.registrarPertenenciaDetenido(objeto, detenido);
	}

	@Override
	public void eliminarPertenenciaDetenido(ObjetoDTO objeto,
			InvolucradoDTO detenido) throws NSJPNegocioException {
		administrarPertenenciasDetenidoService.eliminarPertenenciaDeteneido(objeto, detenido);
	}

    @Override
    public ObjetoDTO obtenerObjeto(ObjetoDTO input) throws NSJPNegocioException {
        return this.obtenerObjetoService.obtenerObjeto(input);
    }

	@Override
	public List<ObjetoDTO> consultarObjetosNoEvidencia(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		return consultarObjetosNoEvidenciaService.consultarObjetosNoEvidencia(expedienteDTO);
	}

	@Override
	public ObjetoDTO asociarObjetoAlmacen(ObjetoDTO objetoDTO,
			AlmacenDTO almacenDTO) throws NSJPNegocioException {
		return asociarObjetoAlmacenService.asociarObjetoAlmacen(objetoDTO, almacenDTO);
	}

	@Override
	public List<ObjetoDTO> consultarObjetosPorTipoConFolioDeCustodia(
			Objetos tipoObjeto, ExpedienteDTO expediente) throws NSJPNegocioException {
		return conFolioDeCustodiaService.obtenerObjetosPorTipo(tipoObjeto, expediente);
	}
}
