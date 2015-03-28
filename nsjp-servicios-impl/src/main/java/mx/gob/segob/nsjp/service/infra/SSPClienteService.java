package mx.gob.segob.nsjp.service.infra;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.model.Mandamiento;
import mx.gob.segob.nsjp.model.MedidaAlterna;
import mx.gob.segob.nsjp.model.MedidaCautelar;
import mx.gob.segob.nsjp.model.SolicitudTrasladoImputado;
/**
 * Cliente para conectarse a los web services de SSP.
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public interface SSPClienteService {
    /**
     * Envia la Medida Cuatelar a SSP.
     * @param input
     * @throws NSJPNegocioException
     */
	public void enviarMedidaCautelar(MedidaCautelar input)throws NSJPNegocioException;
	/**
	 * Envia la Medida Alterna a SSP.
	 * @param input
	 * @throws NSJPNegocioException
	 */
	public void enviarMedidaAlterna(MedidaAlterna input)throws NSJPNegocioException;
	/**
	 *  Envia el mandamiento judicial a SSP.
	 * @param input
	 * @throws NSJPNegocioException
	 */
	public void enviarMandamiento(Mandamiento input)throws NSJPNegocioException;	
	/**
	 * Envia la solicitud de traslado de imputado a SSP.
	 * @param input
	 * @throws NSJPNegocioException
	 */
	public void registrarSolicitudTrasladoImputado(SolicitudTrasladoImputado input) throws NSJPNegocioException;
}
