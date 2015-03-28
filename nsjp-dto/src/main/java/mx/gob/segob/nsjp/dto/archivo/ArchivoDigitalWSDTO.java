/**
 * Nombre del Programa : ArchivoWSDTO.java                                    
 * Autor                            : GustavoBP                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 25/07/2012
 * Marca de cambio        : N/A
 * Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Archivo Digital.
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : DTO para el archivo digital                  
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
package mx.gob.segob.nsjp.dto.archivo;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Archivo Digital.
 * @author GustavoBP
 * @version 1.0
 */
public class ArchivoDigitalWSDTO extends GenericWSDTO{

    /**
     * Nombre del archivo creado
     */
    private String nombreArchivo;
    /**
     * Tipo de archivo
     */
    private String tipoArchivo;
    /**
     * Contenido del archivo
     */
    private byte[] contenido;

    public ArchivoDigitalWSDTO() {

    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    /**
     * @param nombreArchivo
     *            the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    /**
     * @return the tipoArchivo
     */
    public String getTipoArchivo() {
        return tipoArchivo;
    }
    /**
     * @param tipoArchivo
     *            the tipoArchivo to set
     */
    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }
    /**
     * @return the contenido
     */
    public byte[] getContenido() {
        return contenido;
    }
    /**
     * @param contenido
     *            the contenido to set
     */
    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }
}
