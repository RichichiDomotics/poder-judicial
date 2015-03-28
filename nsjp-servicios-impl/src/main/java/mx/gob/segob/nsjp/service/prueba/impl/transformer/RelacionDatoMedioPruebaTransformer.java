/**
 * 
 */
package mx.gob.segob.nsjp.service.prueba.impl.transformer;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.MedioPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionDatoMedioPruebaDTO;
import mx.gob.segob.nsjp.model.RelacionDatoMedioPrueba;

/**
 * @author adrian
 *
 */
public class RelacionDatoMedioPruebaTransformer {

	public static RelacionDatoMedioPruebaDTO transformarRelacionDatoMedio(
			RelacionDatoMedioPrueba scr) {
		ValorDTO motivoCancelacion=null;
		DatoPruebaDTO datoPrueba=null;
		MedioPruebaDTO medioPrueba=null;
		
		if(scr.getMotivoCancelacion()!=null)
			motivoCancelacion=new ValorDTO(scr.getMotivoCancelacion().getValorId());
		if(scr.getDatoPrueba()!=null)
			datoPrueba=DatoPruebaTransformer.transformarDatoPruebaBasico(scr.getDatoPrueba());
		if(scr.getMedioPrueba()!=null)
			medioPrueba=MedioPruebaTransformer.transformarMedioPrueba(scr.getMedioPrueba());
		
		
		RelacionDatoMedioPruebaDTO dto = new RelacionDatoMedioPruebaDTO(
				scr.getRelacionDatoMedioPruebaId(), scr.getEsAceptado(),
				scr.getTiempoCancelacion(), motivoCancelacion, datoPrueba,
				medioPrueba);
		return dto;
	}
	
	public static RelacionDatoMedioPruebaDTO transformarRelacionDatoMedioBasico(
			RelacionDatoMedioPrueba scr) {
		ValorDTO motivoCancelacion=null;
		DatoPruebaDTO datoPrueba=null;
		MedioPruebaDTO medioPrueba=null;
		
		if(scr.getMotivoCancelacion()!=null)
			motivoCancelacion=new ValorDTO(scr.getMotivoCancelacion().getValorId());
		if(scr.getDatoPrueba()!=null){
			datoPrueba=new DatoPruebaDTO();
			datoPrueba.setDatoPruebaId(scr.getDatoPrueba().getDatoPruebaId());
		}
		if(scr.getMedioPrueba()!=null){
			medioPrueba=new MedioPruebaDTO();
			medioPrueba.setMedioPruebaId(scr.getMedioPrueba().getMedioPruebaId());
		}
		
		RelacionDatoMedioPruebaDTO dto = new RelacionDatoMedioPruebaDTO(
				scr.getRelacionDatoMedioPruebaId(), scr.getEsAceptado(),
				scr.getTiempoCancelacion(), motivoCancelacion, datoPrueba,
				medioPrueba);
		return dto;
	}

}
