/**
 * Nombre del Programa : ObjetoTransformer.java
 * Autor                            : Emigdio Hernández
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13/06/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Clase de transformación para un Objeto del Expediente.
 * Esta clase tranforma un Objeto a su DTO correspondiente considerando el tipo de objeto
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
package mx.gob.segob.nsjp.service.objeto.impl.transform;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.Aeronave;
import mx.gob.segob.nsjp.model.Animal;
import mx.gob.segob.nsjp.model.Arma;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.DocumentoOficial;
import mx.gob.segob.nsjp.model.Embarcacion;
import mx.gob.segob.nsjp.model.EquipoComputo;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Explosivo;
import mx.gob.segob.nsjp.model.Inmueble;
import mx.gob.segob.nsjp.model.Joya;
import mx.gob.segob.nsjp.model.Numerario;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.model.ObraArte;
import mx.gob.segob.nsjp.model.Sustancia;
import mx.gob.segob.nsjp.model.Telefonia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.model.Vegetal;
import mx.gob.segob.nsjp.model.Vehiculo;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.ConfInstitucionTransformer;

/**
 * Clase de transformación para un Objeto del Expediente. Esta clase tranforma
 * un Objeto a su DTO correspondiente considerando el tipo de objeto
 * 
 * @version 1.0
 * @author Emigdio Hernández
 * 
 */
public class ObjetoTransformer {

    /**
     * Transforma un objeto del tipo
     * <code>Objeto</objeto> a su DTO correspondiente pero teniendo en cuenta
     * el tipo de objeto, por ejemplo, si el tipo de objeto es un arma se realiza un casto y luego otra
     * transoformación y se retorna como un <code>ObjetoDTO</code>
     * 
     * @param src
     *            Objeto fuente
     * @return Objeto transformado
     */
    public static ObjetoDTO transformarObjeto(Objeto src) {
        ObjetoDTO destino = new ObjetoDTO();
        if (src != null) {
        	if (src instanceof Vehiculo) {

                destino = VehiculoTransformer
                        .transformarVehiculo((Vehiculo) src);

            } else if (src instanceof EquipoComputo) {

                destino = EquipoComputoTransformer
                        .transformarEquipoComputo((EquipoComputo) src);

            } else if (src instanceof Telefonia) {

                destino = TelefoniaTransformer
                        .transformarTelefonia((Telefonia) src);

            } else if (src instanceof Arma) {

                destino = ArmaTransformer.transformarArma((Arma) src);

            } else if (src instanceof Explosivo) {

                destino = ExplosivoTransformer
                        .transformarExplosivo((Explosivo) src);

            } else if (src instanceof Aeronave) {

                destino = AeronaveTransformer
                        .transformarAeronave((Aeronave) src);

            } else if (src instanceof Animal) {

                destino = AnimalTransformer.transformarAnimal((Animal) src);

            } else if (src instanceof DocumentoOficial) {

                destino = DocumentoOficialTransformer
                        .transformarDocumentoOficial((DocumentoOficial) src);

            } else if (src instanceof Embarcacion) {

                destino = EmbarcacionTransformer
                        .transformarEmbarcacion((Embarcacion) src);

            } else if (src instanceof Inmueble) {

                destino = InmuebleTransformer
                        .transformarInmueble((Inmueble) src);

            } else if (src instanceof Joya) {

                destino = JoyaTransformer.transformarJoya((Joya) src);

            } else if (src instanceof Numerario) {

                destino = NumerarioTransformer
                        .transformarNumerario((Numerario) src);

            } else if (src instanceof ObraArte) {

                destino = ObraArteTransformer
                        .transformarObraArte((ObraArte) src);

            } else if (src instanceof Sustancia) {

                destino = SustanciaTransformer
                        .transformarSustancia((Sustancia) src);

            } else if (src instanceof Vegetal) {

                destino = VegetalTransformer.transformarVegetal((Vegetal) src);

            } else {

                destino = new ObjetoDTO();

            }

            destino.setDescripcion(src.getDescripcion());

            if(src.getValorByCondicionFisicaVal()!=null)
            {
            destino.setValorByCondicionFisicaVal(new ValorDTO(src
                    .getValorByCondicionFisicaVal().getValorId(), src
                    .getValorByCondicionFisicaVal().getValor()));
            }
            
            destino.setElementoId(src.getElementoId());
            destino.setFolioElemento(src.getFolioElemento());
            
            destino.setTipoObjeto(Objetos.getByValor(src
                    .getValorByTipoObjetoVal().getValorId()));
            destino.setFechaCreacionElemento(src.getFechaCreacionElemento());
            if (src.getCalidad() != null
                    && src.getCalidad().getTipoCalidad() != null) {
                Calidad calidad = src.getCalidad();
                CalidadDTO calidadDTO = new CalidadDTO();
                calidadDTO.setCalidadId(calidad.getCalidadId());
                calidadDTO.setValorIdCalidad(new ValorDTO(calidad
                        .getTipoCalidad().getValorId(), calidad
                        .getTipoCalidad().getValor()));
                calidadDTO.setDescripcionEstadoFisico(calidad
                        .getDescripcionEstadoFisico());
                calidadDTO.setCalidades(Calidades.getByValor(calidad
                        .getTipoCalidad().getValorId()));
                destino.setCalidadDTO(calidadDTO);
            }

            // Jacob Transformamos el almacen en caso que src lo tenga.
            if (src.getAlmacen() != null) {
                destino.setAlmacen(AlmacenTransformer.transformarAlmacen(src
                        .getAlmacen()));
            }
            if(src.getArchivoDigital() != null)        		
        		destino.setFotoDelElemento(ArchivoDigitalTransformer.transformarArchivoDigital(src.getArchivoDigital()));
            
            if(src.getInstitucionPresenta()!= null)
            	destino.setInstitucionPresenta( ConfInstitucionTransformer.transformarInstitucion(src.getInstitucionPresenta()));
            
            if(src.getNombreObjeto() != null)
                destino.setNombreObjeto(src.getNombreObjeto());

            destino.setEsActivo(src.getEsActivo());
        }

        return destino;
    }

    public static Objeto transformarObjeto(ObjetoDTO objeto) {

        Objeto object = new Objeto();
        object.setElementoId(objeto.getElementoId());

        object.setDescripcion(objeto.getDescripcion());
        if (objeto.getValorByCondicionFisicaVal() != null) {
            object.setValorByCondicionFisicaVal(new Valor(objeto
                    .getValorByCondicionFisicaVal().getIdCampo()));
        }
        if (objeto.getTipoObjeto() != null) {
            object.setValorByTipoObjetoVal(new Valor(objeto.getTipoObjeto()
                    .getValorId()));
        }
        object.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        object.setFechaCreacionElemento(objeto.getFechaCreacionElemento());
        if(objeto.getExpedienteDTO()!=null)
        object.setExpediente(ExpedienteTransformer.transformarExpediente(objeto
                .getExpedienteDTO()));
        object.setEsActivo(objeto.getEsActivo());
        
        if(objeto.getNombreObjeto()!=null)
        	object.setNombreObjeto(objeto.getNombreObjeto());
        	
        return object;
    }
 
    public static Objeto transformarEmptyObjeto(ObjetoDTO objeto) {
    	
        Objeto object = new Objeto();
        object.setElementoId(objeto.getElementoId());
        
        object.setDescripcion("");
        object.setValorByCondicionFisicaVal(new Valor());
        object.setValorByTipoObjetoVal(new Valor());
        
        object.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        object.setCalidad(new Calidad());
        object.setFechaCreacionElemento(new Date());
        object.setExpediente(new Expediente());
        
        if(objeto.getNombreObjeto()!=null)        	
        	object.setNombreObjeto(objeto.getNombreObjeto());
        	
        return object;
    }
    
    /**
     * 
     * @param input
     * @return
     */
    public static ObjetoDTO tranformarComoEvidenciaBasico(Objeto input) {
        ObjetoDTO resp = new ObjetoDTO();
        EvidenciaDTO evDto = new EvidenciaDTO();
        CadenaDeCustodiaDTO cadDto = new CadenaDeCustodiaDTO();

        cadDto.setFolio(input.getEvidencia().getCadenaDeCustodia().getFolio());
        evDto.setNumeroEvidencia(input.getEvidencia().getNumeroEvidencia());

        evDto.setCadenaDeCustodia(cadDto);
        resp.setEvidencia(evDto);
        
        if(input.getNombreObjeto()!=null)
        	resp.setNombreObjeto(input.getNombreObjeto());
        
        resp.setElementoId(input.getElementoId());
        if(input.getAlmacen()!=null)
        	resp.setAlmacen(AlmacenTransformer.transformarAlmacen(input.getAlmacen()));

        return resp;
    }
}
