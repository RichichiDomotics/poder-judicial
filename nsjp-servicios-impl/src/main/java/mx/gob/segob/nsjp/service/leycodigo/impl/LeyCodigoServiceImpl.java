package mx.gob.segob.nsjp.service.leycodigo.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.leycodigo.LeyCodigoDAO;
import mx.gob.segob.nsjp.dto.LeyCodigoDTO;
import mx.gob.segob.nsjp.model.LeyCodigo;
import mx.gob.segob.nsjp.service.leycodigo.LeyCodigoService;
import mx.gob.segob.nsjp.service.leycodigo.impl.transform.LeyCodigoTransform;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LeyCodigoServiceImpl implements LeyCodigoService {

	private final static Logger logger = Logger.getLogger(LeyCodigoServiceImpl.class);
	
	@Autowired
	private LeyCodigoDAO leyCodigoDAO;
	
	@Override
	public List<LeyCodigoDTO> obtenerNormasCategoria(Long id)
			throws NSJPNegocioException {
		List<LeyCodigo> leyes = leyCodigoDAO.obtenerNormasCategoria(id);
		List<LeyCodigoDTO> result = new LinkedList<LeyCodigoDTO>(); 
		for (LeyCodigo leyCodigo : leyes) {
			result.add(LeyCodigoTransform.tranformar(leyCodigo));
		}
		return result;
	}

	@Override
	public ByteArrayOutputStream leerLeyCodigo(Long id) throws NSJPNegocioException {
		ByteArrayOutputStream archivo  = new ByteArrayOutputStream();
		final LeyCodigo leyCodigo = leyCodigoDAO.read(id);
		final File file = new File(leyCodigo.getUrl());
		try{
			final byte[] bytes = getBytesFromFile(file);
			if(bytes == null){
				return null;
			}
			archivo.write(bytes);
		}catch(IOException ioe){
			logger.info("ERROR AL LEER EL ARCHIVO "+leyCodigo.getUrl());
		}
		return archivo;
	}
	
	private byte[] getBytesFromFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);
	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        return null;
	    }

	    byte[] bytes = new byte[(int)length];

	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }
	   
	    if (offset < bytes.length) {
		    is.close();
	    	return null;
	    }
	    is.close();
	    return bytes;
	}

}
