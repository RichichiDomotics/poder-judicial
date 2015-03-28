/**
 * 
 */
package mx.gob.segob.nsjp.comun.excepcion;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;

/**
 * @author vaguirre
 * 
 */
public class NSJPNegocioException extends Exception {

    private CodigoError codigo;

    /**
	 * 
	 */
    private static final long serialVersionUID = -3412665934629222024L;

    /**
     * Constructor.
     * 
     * @param codigoError
     *            codigo de error.
     * @param cause
     *            Causa.
     */
    public NSJPNegocioException(CodigoError codigoError, Throwable cause) {
        super(cause);
        codigo = codigoError;
    }

    /**
     * Constructor.
     * 
     * @param codigoError
     *            codigo de error.
     * @param cause
     *            Causa.
     */
    public NSJPNegocioException(CodigoError codigoError) {
        super(codigoError.name());
        codigo = codigoError;
        
    }

    /**
     * Método de acceso al campo codigo.
     * 
     * @return El valor del campo codigo
     */
    public CodigoError getCodigo() {
        return codigo;
    }

}
