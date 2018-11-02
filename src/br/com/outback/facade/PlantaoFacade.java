package br.com.outback.facade;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import br.com.outback.bean.Plantao;
import br.com.outback.dao.PlantaoDAO;


public class PlantaoFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private PlantaoDAO plantaoDAO= new PlantaoDAO();

	
	public void createPlantao(Plantao plantao) {
		plantaoDAO.beginTransaction();
		plantaoDAO.save(plantao);
		plantaoDAO.commitAndCloseTransaction();
	}

	public void update(Plantao plantao) {
		
		plantaoDAO.beginTransaction();
	
		Plantao persistedPlantao = plantaoDAO.find(plantao.getId());
		persistedPlantao.setData(plantao.getData());
		persistedPlantao.setDemanda(plantao.getDemanda());
		persistedPlantao.setTurno(plantao.getTurno());
		persistedPlantao.setFuncionarios(plantao.getFuncionarios());
		
		plantaoDAO.update(persistedPlantao);
		plantaoDAO.commitAndCloseTransaction();
	}

	public Plantao findPlantao(long plantaoId) {
		plantaoDAO.beginTransaction();
		Plantao plantao = plantaoDAO.find(plantaoId);
		plantaoDAO.closeTransaction();
		return plantao;
	}

	public List<Plantao> listAll() {
		plantaoDAO.beginTransaction();
		List<Plantao> result = (List<Plantao>) plantaoDAO.findAll();
		plantaoDAO.closeTransaction();
		return result;
	}
	
	public List<Plantao> retornaPorData(Calendar dataInicio, Calendar dataFim) {
		plantaoDAO.beginTransaction();
		List<Plantao> result = (List<Plantao>) plantaoDAO.retornaPlantoes(dataInicio, dataFim);
		plantaoDAO.closeTransaction();
		return result;
	}

	public void deletePlantao(Plantao plantao) {
		plantaoDAO.beginTransaction();
		Plantao persistedPlantao = plantaoDAO.findReferenceOnly(plantao.getId());
		plantaoDAO.delete(persistedPlantao);
		plantaoDAO.commitAndCloseTransaction();
	}
}