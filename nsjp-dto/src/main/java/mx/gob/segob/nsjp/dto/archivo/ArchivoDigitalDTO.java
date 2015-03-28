/**
 * Nombre del Programa : ArchivoDTO.java                                    
 * Autor                            : Emigdio Hernandez                                              
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 30/05/2011
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

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * DTO para el archivo digital
 * 
 * @author Emigdio Hernández
 * 
 */
public class ArchivoDigitalDTO extends GenericDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * Identificador único
     */
    private Long archivoDigitalId;

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

    public ArchivoDigitalDTO() {

    }

    public ArchivoDigitalDTO(Long id) {
        this.archivoDigitalId = id;
    }

    /**
     * @return the archivoDigitalId
     */
    public Long getArchivoDigitalId() {
        return archivoDigitalId;
    }
    /**
     * @param archivoDigitalId
     *            the archivoDigitalId to set
     */
    public void setArchivoDigitalId(Long archivoDigitalId) {
        this.archivoDigitalId = archivoDigitalId;
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
