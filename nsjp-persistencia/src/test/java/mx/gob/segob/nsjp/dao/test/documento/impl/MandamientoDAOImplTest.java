package mx.gob.segob.nsjp.dao.test.documento.impl;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import java.lang.Object;

import java.util.ArrayList;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusMandamiento;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.documento.MandamientoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.AvisoHechoDelictivo;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Mandamiento;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Valor;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

public class MandamientoDAOImplTest extends BaseTestPersistencia<MandamientoDAO>  {

	public void testConsultarMandamientoPorFiltro() {
		String numeroExpediente="NSJYUCPJ20112533339";
		//String numeroExpediente="";
		Mandamiento mandamiento=new Mandamiento();
		
		Long estado=EstatusMandamiento.EN_PROCESO.getValorId();		
		mandamiento.setEstatus(new Valor(estado));
				
		Date fechaInicial= new Date();
		fechaInicial.setTime(Date.parse("Dec 20, 2011"));	
		mandamiento.setFechaInicial(fechaInicial);		
				
		Date fechaFinal= new Date();		
		fechaFinal.setTime(Date.parse("Dec 20, 2011"));	
		mandamiento.setFechaFinal(fechaFinal);		
		
		List<Mandamiento> Mandamientos = daoServcice.consultarMandamientoPorFiltro(mandamiento, numeroExpediente);
		
		logger.info("Existen " + Mandamientos.size()+ " Mandamientos");
		
		for (Mandamiento Md :Mandamientos) {
			logger.info("----------------------");
			logger.info("Mandamiento_id: " + Md.getFechaInicial());
			logger.info("Mandamiento_id: " + Md.getFechaFinal());			
		}
	}
	
	/*public void testConsultarMandamientoPorNumeroExpediente() {
		String numeroExpediente="NSJYUCPJ201126333AK"; 
		List<Mandamiento> Mandamientos = daoServcice.consultarMandamientosPorNumeroExpediente(numeroExpediente);
		logger.info("Existen " + Mandamientos.size()+ " Mandamientos");
		
		for (Mandamiento Md :Mandamientos) {
			logger.info("----------------------");
			logger.info("Estado: " + Md.getEstatus());
			logger.info("Fecha Inicial: " + Md.getFechaInicial());
			logger.info("Fecha Final: " + Md.getFechaFinal());			
			logger.info("Detalle del Resolutivo: " + Md.getResolutivo().getDetalle());
		}
	}*/

}
