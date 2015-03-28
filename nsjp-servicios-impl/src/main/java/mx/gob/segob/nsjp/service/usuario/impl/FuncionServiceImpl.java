/**
 * 
 */
package mx.gob.segob.nsjp.service.usuario.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.usuario.FuncionDAO;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.model.Funcion;
import mx.gob.segob.nsjp.service.usuario.FuncionService;

/**
 * @author LuisMG
 * 
 */
@Service
@Transactional
public class FuncionServiceImpl implements FuncionService {

	/**
	 * 
	 */
	public static final Logger logger = Logger
			.getLogger(FuncionServiceImpl.class);

	@Autowired
	private FuncionDAO funcionDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.usuario.FuncionService#inventariarFunciones()
	 */
	@SuppressWarnings("unused")
	@Override
	public List<FuncionDTO> inventariarFunciones(FuncionDTO url,
			List<String> ext) throws NSJPNegocioException {
		List<FuncionDTO> fncSrvDTO = null;
		List<Funcion> fncBD = null;
		List<Funcion> fncNuevas = null;
		List<FuncionDTO> fncFaltantesDTO = null;
		int j = 0;

		// TODO Auto-generated method stub
		if (ext != null) {
			fncBD = funcionDAO.consultarFunciones();
			fncSrvDTO = obtieneArchivos(url, ext);
			// Las nuevas funciones
			for (int i = 0; i < fncSrvDTO.size(); i++) {
				j = 0;
				while (j < fncBD.size()
						&& !fncSrvDTO.get(i).getNombreFuncion()
								.equals(fncBD.get(j).getNombreFuncion())) {
					j++;
				}
				if (j == fncBD.size()) {
					if (fncNuevas == null) {
						fncNuevas = new ArrayList<Funcion>();
					}
					fncNuevas.add(new Funcion(fncSrvDTO.get(i)
							.getNombreFuncion(), fncSrvDTO.get(i)
							.getNombreFuncion()));
				}
			}
			// Las funciones Faltantes
			for (int i = 0; i < fncBD.size(); i++) {
				j = 0;
				while (j < fncSrvDTO.size()
						&& !fncBD.get(i).getNombreFuncion()
								.equals(fncSrvDTO.get(j).getNombreFuncion())) {
					j++;
				}
				if (j == fncSrvDTO.size()) {
					if (fncFaltantesDTO == null) {
						fncFaltantesDTO = new ArrayList<FuncionDTO>();
					}
					fncFaltantesDTO.add(new FuncionDTO(fncBD.get(i)
							.getNombreFuncion(), fncBD.get(i)
							.getNombreFuncion()));
				}
			}
			if (fncNuevas != null) {
				funcionDAO.instertarFunciones(fncNuevas);
				for (int i = 0; i < fncNuevas.size(); i++) {
					logger.info("Funcion Nueva No. " + i
							+ " " + fncNuevas.get(i).getNombreFuncion());
				}
				logger.info("Total Funciones Nuevas: " + fncNuevas.size());
			} else {
				logger.info("Total Funciones Nuevas: 0");
			}
			if (fncFaltantesDTO != null) {
				for (int i = 0; i < fncFaltantesDTO.size(); i++) {
					fncSrvDTO.add(fncFaltantesDTO.get(i));
					logger.info("Faltante No. " + i
							+ " " + fncFaltantesDTO.get(i).getNombreFuncion());
				}
				logger.info("Total Funciones Faltantes: "
						+ fncFaltantesDTO.size());
			}else{
				logger.info("Total Funciones Faltantes: 0");
			}
				

		}
		return fncSrvDTO;
	}

	private List<FuncionDTO> obtieneArchivos(FuncionDTO url, List<String> ext) {
		File root = new File(url.getNombreFuncion());
		List<FuncionDTO> archivos = null;
		FuncionDTO archivo = null;

		List<File> fileList = getFileListing(root, ext);
		if (fileList != null) {
			if (fileList.size() > 0) {
				archivos = new ArrayList<FuncionDTO>();
				for (int i = 0; i < fileList.size(); i++) {
					if (fileList.get(i).getName().endsWith(".do")) {
						archivo = new FuncionDTO("/" + fileList.get(i).getName(),
								fileList.get(i).getName());
					} else {
						String string = fileList.get(i).getPath();
								//+ fileList.get(i).getName();
						String[] arrayString = string.split("WEB-INF");
						if (arrayString != null) {
							if (!arrayString[1].isEmpty()) {
								if (arrayString[1].length() > 200) {
									logger.info("Tamaño:"
											+ arrayString[1].length() + " : "
											+ arrayString[1]);
								}
								archivo = new FuncionDTO(arrayString[1],
										arrayString[1]);
								
							}
						}
					}
					archivos.add(archivo);
				}
			}
		}

		return archivos;
	}

	/**
	 * Recursively walk a directory tree and return a List of all Files found;
	 * the List is sorted using File.compareTo().
	 * 
	 * @param aStartingDir
	 *            is a valid directory, which can be read.
	 */
	@SuppressWarnings("unchecked")
	static public List<File> getFileListing(File aStartingDir, List<String> ext) {
		List<File> result = null;
		if (validateDirectory(aStartingDir)) {
			result = getFileListingNoSort(aStartingDir, ext);
			result = getXMLListingNoSort(aStartingDir, result);
			Collections.sort(result);
			// Eliminar duplicados de la lista
			@SuppressWarnings("rawtypes")
			HashSet hs = new HashSet();
			hs.addAll(result);
			result.clear();
			result.addAll(hs);
		}
		return result;
	}

	// PRIVATE //
	static private List<File> getFileListingNoSort(File aStartingDir,
			List<String> ext) {
		List<File> result = new ArrayList<File>();
		File[] filesAndDirs = aStartingDir.listFiles();
		List<File> filesDirs = Arrays.asList(filesAndDirs);
		for (File file : filesDirs) {
			if (!file.isDirectory()) {// add only files
				if (ext != null) {
					for (int i = 0; i < ext.size(); i++) {
						if (file.getName().endsWith(ext.get(i))) {
							result.add(file);
						}
					}
				} else {
					result.add(file);
				}
			}
			if (!file.isFile()) {
				// must be a directory
				// recursive call!
				List<File> deeperList = getFileListingNoSort(file, ext);
				result.addAll(deeperList);
			}
		}
		return result;
	}

	/**
	 * Directory is valid if it exists, does not represent a file, and can be
	 * read.
	 */
	static private boolean validateDirectory(File aDirectory) {
		boolean resp = true;
		if (aDirectory == null) {
			resp = false;
		}
		if (!aDirectory.exists()) {
			resp = false;
		}
		if (!aDirectory.isDirectory()) {
			resp = false;
		}
		if (!aDirectory.canRead()) {
			resp = false;
		}

		return resp;
	}

	static private List<File> getXMLListingNoSort(File aStartingDir,
			List<File> lstFiles) {
		try {
			File file = new File(aStartingDir + "/struts-config.xml");
			FileReader reader = new FileReader(file);
			BufferedReader in = new BufferedReader(reader);
			String string;
			String[] stringArray;
			while ((string = in.readLine()) != null) {
				string = string.trim();

				if (string.startsWith("<action path=\"")) {
					stringArray = string.split("\"");
					if (stringArray[1] != null) {
						if (!stringArray[1].isEmpty()) {
							lstFiles.add(new File(stringArray[1] + ".do"));
						}
					}
				}

			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lstFiles;
	}
}
