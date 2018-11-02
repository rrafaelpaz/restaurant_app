package br.com.outback.facade;

import java.io.Serializable;
import java.util.List;

import br.com.outback.bean.Funcao;
import br.com.outback.dao.FuncaoDAO;

public class FuncaoFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private FuncaoDAO funcaoDAO = new FuncaoDAO();

	
	public void createFuncao(Funcao funcao) {
		funcaoDAO.beginTransaction();
		funcaoDAO.save(funcao);
		funcaoDAO.commitAndCloseTransaction();
	}

	public void updateFuncao(Funcao funcao) {
		funcaoDAO.beginTransaction();
		Funcao persistedFuncao = funcaoDAO.find(funcao.getId());
		persistedFuncao.setNome(funcao.getNome());
		funcaoDAO.update(persistedFuncao);
		funcaoDAO.commitAndCloseTransaction();
	}

	public Funcao findFuncao(long funcaoId) {
		funcaoDAO.beginTransaction();
		Funcao funcao = funcaoDAO.find(funcaoId);
		funcaoDAO.closeTransaction();
		return funcao;
	}

	public List<Funcao> listAll() {
		funcaoDAO.beginTransaction();
		List<Funcao> result = (List<Funcao>) funcaoDAO.findAll();
		funcaoDAO.closeTransaction();
		return result;
	}

	public void deleteFuncao(Funcao funcao) {
		funcaoDAO.beginTransaction();
		Funcao persistedFuncao = funcaoDAO.findReferenceOnly(funcao.getId());
		funcaoDAO.delete(persistedFuncao);
		funcaoDAO.commitAndCloseTransaction();
	}
}