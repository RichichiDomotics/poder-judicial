/**
 * Nombre del Programa : DatosGeneralesExpedienteTransformer.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 04 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Clase para convertir objetos DatosGeneralesExpediente a DatosGeneralesExpedienteTransformerDTO y viceversa
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
package mx.gob.segob.nsjp.service.expediente.impl.transform;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.dto.expediente.DatosGeneralesExpedienteDTO;
import mx.gob.segob.nsjp.model.Objeto;

/**
 * Clase para convertir objetos Expediente a ExpedienteDTO y viceversa.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class DatosGeneralesExpedienteTransformer {
	   
	/**
	 * Convierte un objeto Expediente a un objeto ExpedienteDTO.
	 * 
	 * @param expediente
	 * @return objeto ExpedienteDTO
	 */
	public static DatosGeneralesExpedienteDTO transformaDatosGeneralesExpedienteDTO(List<Objeto> objetosExpediente,DatosGeneralesExpedienteDTO expedienteRetorno) {

        if(objetosExpediente != null){
        	expedienteRetorno.setTotalVehiculos(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.VEHICULO));
            expedienteRetorno.setTotalEquiposComputo(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.EQUIPO_DE_COMPUTO));
            expedienteRetorno.setTotalEquiposTelefonicos(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.EQUIPO_TELEFONICO));
            expedienteRetorno.setTotalArmas(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.ARMA));
            expedienteRetorno.setTotalExplosivos(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.EXPLOSIVO));
            expedienteRetorno.setTotalSustancias(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.SUSTANCIA));
            expedienteRetorno.setTotalAnimales(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.ANIMAL));
            expedienteRetorno.setTotalAeronaves(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.AERONAVE));
            expedienteRetorno.setTotalEmbarcaciones(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.EMBARCACION));
            expedienteRetorno.setTotalInmuelbes(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.INMUEBLE));
            expedienteRetorno.setTotalNumerarios(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.NUMERARIO));
            expedienteRetorno.setTotalVegetales(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.VEGETAL));
            expedienteRetorno.setTotalDocumentosOficiales(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.DOCUMENTO_OFICIAL));
            expedienteRetorno.setTotalJoyas(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.JOYA));
            expedienteRetorno.setTotalObrasDeArte(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.OBRA_DE_ARTE));
            expedienteRetorno.setTotalOtrosObjestos(obtenNumeroTotalDeObjetoPorTipo(objetosExpediente,Objetos.OTRO));                  
        }
		return expedienteRetorno;
	}

	/**
	 * Metodo que permite contabilizar cuantos objetos por tipo existen en una lista de Objetos
	 * @param objetosExpediente Lista de objetos
	 * @param tipoObjeto Tipo de objeto, ver el Enum de Objetos
	 * @return
	 */
		private static Integer obtenNumeroTotalDeObjetoPorTipo(List<Objeto> objetosExpediente,
				Objetos tipoObjeto) {
			
			Integer numTotal = 0;
			
			for (Objeto objeto : objetosExpediente) {
				if(objeto.getValorByTipoObjetoVal().getValorId().equals(tipoObjeto.getValorId())){
					numTotal = numTotal + 1;							
				}
			}
			return numTotal;
		}		
}
