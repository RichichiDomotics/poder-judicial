/**
* Nombre del Programa : ConsultarTipoSolicitudAudienciaServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20/10/2011
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
package mx.gob.segob.nsjp.service.solicitud.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.catalogo.ValorDAO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.solicitud.ConsultarTipoSolicitudAudienciaService;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
public class ConsultarTipoSolicitudAudienciaServiceImpl implements
		ConsultarTipoSolicitudAudienciaService {
	
	private final static Logger logger = Logger
       .getLogger(ConsultarTipoSolicitudAudienciaServiceImpl.class);
    
	@Autowired
	private ValorDAO valorDAO;
	   
	private TipoAudiencia[] auGrupoA = {TipoAudiencia.CONTROL, TipoAudiencia.IMPUTACION, TipoAudiencia.VINCULACION};
	private List<TipoAudiencia>  audienciaGrupoA = Arrays.asList(auGrupoA);
	
	private TipoAudiencia[] auGrupoB = {TipoAudiencia.ABREVIADO};
	private List<TipoAudiencia>  audienciaGrupoB = Arrays.asList(auGrupoB);
	
	private TipoAudiencia[] auGrupoC = {TipoAudiencia.INTERMEDIA};
	private List<TipoAudiencia>  audienciaGrupoC = Arrays.asList(auGrupoC);
	
	private TipoAudiencia[] auGrupoD = {TipoAudiencia.JUICIO_ORAL, TipoAudiencia.INDIVIDUALIZACION_DE_SANCION};
	private List<TipoAudiencia>  audienciaGrupoD = Arrays.asList(auGrupoD);
	
	private TipoAudiencia[] auGrupoE = {TipoAudiencia.LECTURA, TipoAudiencia.SSP, TipoAudiencia.VERIFICACION_SSP, TipoAudiencia.MASC, TipoAudiencia.VERIFICACION_MASC };
	private List<TipoAudiencia>  audienciaGrupoE = Arrays.asList(auGrupoE);
	
	private TipoAudiencia[] auGrupoF = {TipoAudiencia.EJECUCION};
	private List<TipoAudiencia>  audienciaGrupoF = Arrays.asList(auGrupoF);
	
	private TipoAudiencia[] auGrupoG = {TipoAudiencia.CATEO, TipoAudiencia.APREHENSION};
	private List<TipoAudiencia>  audienciaGrupoG = Arrays.asList(auGrupoG);
	

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.solicitud.ConsultarTipoSolicitudAudienciaService#consultarTipoSolicitudAudienciaSiguientes(mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CatalogoDTO> consultarTipoSolicitudAudienciaSiguientes(
			TipoAudiencia audienciaActual) throws NSJPNegocioException {
		
		logger.info(" Servicio :: consultarTipoSolicitudAudienciaSiguientes - AudienciaActual:"+ audienciaActual);
		
		if(audienciaActual==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Set<CatalogoDTO> tiposSolicitudes = new HashSet<CatalogoDTO>();
		
		//Al Grupo A  => A, C, E, G  Excepto Acutal
		if ( audienciaGrupoA.contains(audienciaActual)){
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoA));
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoC));
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoE));
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoG));
			tiposSolicitudes.remove(new CatalogoDTO(audienciaActual.getValorId(), audienciaActual.toString()));
			return new ArrayList<CatalogoDTO>(tiposSolicitudes);
		}
		
		//Al Grupo B  => B, E, F  Excepto Acutal
		if ( audienciaGrupoB.contains(audienciaActual)){
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoB));
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoE));
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoF));
			tiposSolicitudes.remove(new CatalogoDTO(audienciaActual.getValorId(), audienciaActual.toString()));
			return new ArrayList<CatalogoDTO>(tiposSolicitudes);
		}
		
		//Al Grupo C  => C, D  Excepto Acutal
		if ( audienciaGrupoC.contains(audienciaActual)){
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoC));
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoD));
			tiposSolicitudes.remove(new CatalogoDTO(audienciaActual.getValorId(), audienciaActual.toString()));
			return new ArrayList<CatalogoDTO>(tiposSolicitudes);
		}
		
		//Al Grupo D  => D, E, F  Excepto Acutal		
		if ( audienciaGrupoD.contains(audienciaActual)){
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoD));
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoE));
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoF));
			tiposSolicitudes.remove(new CatalogoDTO(audienciaActual.getValorId(), audienciaActual.toString()));
			return new ArrayList<CatalogoDTO>(tiposSolicitudes);
		}
		
		//Al Grupo E  => E, F  Excepto Acutal		
		if ( audienciaGrupoE.contains(audienciaActual)){
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoE));
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoF));
			tiposSolicitudes.remove(new CatalogoDTO(audienciaActual.getValorId(), audienciaActual.toString()));
			return new ArrayList<CatalogoDTO>(tiposSolicitudes);
		}

		//Al Grupo F  => F  Excepto Acutal		
		if ( audienciaGrupoF.contains(audienciaActual)){
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoF));
			tiposSolicitudes.remove(new CatalogoDTO(audienciaActual.getValorId(), audienciaActual.toString()));
			return new ArrayList<CatalogoDTO>(tiposSolicitudes);
		}

		//Al Grupo G  => G  Excepto Acutal		
		if ( audienciaGrupoG.contains(audienciaActual)){
			tiposSolicitudes.addAll(this.convertEnumToCatalogo(audienciaGrupoG));
			tiposSolicitudes.remove(new CatalogoDTO(audienciaActual.getValorId(), audienciaActual.toString()));
			return new ArrayList<CatalogoDTO>(tiposSolicitudes);
		}
		
		return new ArrayList<CatalogoDTO>(tiposSolicitudes);
	}

	private Set<CatalogoDTO> convertEnumToCatalogo(List<TipoAudiencia>  listaAudiencias){
		Set<CatalogoDTO> listaCatalogo = new HashSet<CatalogoDTO>();
		for (TipoAudiencia tipoAudiencia : listaAudiencias) {
			Valor valor = valorDAO.read(tipoAudiencia.getValorId());
			listaCatalogo.add( new CatalogoDTO(valor.getValorId(), valor.getValor()));
		}
		return listaCatalogo;
	}
}
