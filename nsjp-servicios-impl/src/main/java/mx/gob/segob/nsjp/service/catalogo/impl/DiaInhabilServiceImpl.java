package mx.gob.segob.nsjp.service.catalogo.impl;

import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.catalogo.DiaInhabilDAO;
import mx.gob.segob.nsjp.dto.catalogo.DiaInhabilDTO;
import mx.gob.segob.nsjp.model.DiaInhabil;
import mx.gob.segob.nsjp.service.catalogo.DiaInhabilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiaInhabilServiceImpl implements DiaInhabilService {

	@Autowired
	private DiaInhabilDAO diaInhabilDAO;
	
	@Override
	public Long guardarDiaInhabil(DiaInhabilDTO dto) throws NSJPNegocioException {
		
		DiaInhabil dia = new DiaInhabil();
		dia.setDescripcion(dto.getDescripcion());
		dia.setDia(dto.getDia());
		dia.setMes(dto.getMes());
		return 	diaInhabilDAO.create(dia);
	}

	@Override
	public void eliminarDiaInhabil(DiaInhabilDTO dto) throws NSJPNegocioException {
		DiaInhabil dia = new DiaInhabil();
		dia.setDiaInhabilId(dto.getDiaInhabilId());
		dia.setDescripcion("");
		dia.setDia((short)1);
		dia.setMes((short)1);
		diaInhabilDAO.delete(dia);
	}

	@Override
	public List<DiaInhabilDTO> consultarDiasInhabiles()
			throws NSJPNegocioException {
		List<DiaInhabil> dias = diaInhabilDAO.consultarDiasInhabiles();
		DiaInhabilDTO dto = null;
		List<DiaInhabilDTO> diasDTO = new LinkedList<DiaInhabilDTO>();
		for(DiaInhabil dia : dias){
			dto = new DiaInhabilDTO();
			dto.setDiaInhabilId(dia.getDiaInhabilId());
			dto.setDescripcion(dia.getDescripcion());
			dto.setDia(dia.getDia());
			dto.setMes(dia.getMes());
			diasDTO.add(dto);
		}

		return diasDTO;
	}
	
	@Override
	public List<DiaInhabilDTO> consultarDiasInhabilesPorMes(Short mes)
			throws NSJPNegocioException {
		List<DiaInhabil> dias = diaInhabilDAO.consultarDiasInhabilesPorMes(mes);
		DiaInhabilDTO dto = null;
		List<DiaInhabilDTO> diasDTO = new LinkedList<DiaInhabilDTO>();
		for(DiaInhabil dia : dias){
			dto = new DiaInhabilDTO();
			dto.setDiaInhabilId(dia.getDiaInhabilId());
			dto.setDescripcion(dia.getDescripcion());
			dto.setDia(dia.getDia());
			dto.setMes(dia.getMes());
			diasDTO.add(dto);
		}

		return diasDTO;
	}

}
