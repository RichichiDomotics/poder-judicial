package mx.gob.segob.nsjp.service.test.objeto.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.service.objeto.AdministrarPertenenciasDetenidoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class AdministrarPertenenciasDetenidoServiceImplTest extends
		BaseTestServicios<AdministrarPertenenciasDetenidoService> {
	
	public void testRegistrarPertenenciaDetenido(){
		Long expedienteId = 14L;
		Long involucradoId = 56L;
		
		ExpedienteDTO exp = new ExpedienteDTO();
		exp.setExpedienteId(expedienteId);
		
		ObjetoDTO objeto = new ObjetoDTO();
		objeto.setDescripcion("lapastroza 2");
		objeto.setValorByCondicionFisicaVal(new ValorDTO(431L));
		objeto.setTipoObjeto(Objetos.EQUIPO_DE_COMPUTO);
		objeto.setFechaCreacionElemento(new Date());
		objeto.setExpedienteDTO(exp);
		
		InvolucradoDTO detenido = new InvolucradoDTO();
		detenido.setElementoId(involucradoId);
		
		Long id;
		try {
			id = service.registrarPertenenciaDetenido(objeto, detenido);
			logger.info("ID: "+ id);	
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void testEliminarPertenenciaDetenido() throws NSJPNegocioException{
		Long objetoId = 562L;
		Long involucradoId = 56L;
		
		InvolucradoDTO detenido = new InvolucradoDTO();
		detenido.setElementoId(involucradoId);
		ObjetoDTO objeto = new ObjetoDTO();
		objeto.setElementoId(objetoId);
		service.eliminarPertenenciaDeteneido(objeto, detenido);
	}
	
	public void testConsultarInventarioPertenenciasDetenido() throws NSJPNegocioException{
		Long involucradoId = 56L;
		
		InvolucradoDTO detenido = new InvolucradoDTO();
		detenido.setElementoId(involucradoId);
		List<ObjetoDTO> lista = service.consultarInventarioPertenenciasDetenido(detenido);
		for(ObjetoDTO objeto : lista){
			logger.info("["+objeto.getElementoId()+" : "+objeto.getTipoObjeto().name()+" "+objeto.getDescripcion()+"]");
		}
	}
	
}
