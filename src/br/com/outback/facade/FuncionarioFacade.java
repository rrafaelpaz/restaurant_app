package br.com.outback.facade;

import java.io.Serializable;
import java.util.List;

import br.com.outback.bean.Funcionario;
import br.com.outback.dao.FuncionarioDAO;

public class FuncionarioFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

	
	public void createFuncionario(Funcionario funcionario) {
		funcionarioDAO.beginTransaction();
		funcionarioDAO.save(funcionario);
		funcionarioDAO.commitAndCloseTransaction();
	}

	public void updateFuncionario(Funcionario funcionario) {
		funcionarioDAO.beginTransaction();
		Funcionario persistedFuncionario = funcionarioDAO.find(funcionario.getId());
		persistedFuncionario.setNome(funcionario.getNome());
		persistedFuncionario.setCpf(funcionario.getCpf());
		persistedFuncionario.setFuncao(funcionario.getFuncao());
		funcionarioDAO.update(persistedFuncionario);
		funcionarioDAO.commitAndCloseTransaction();
	}

	public Funcionario findFuncionario(long funcionarioId) {
		funcionarioDAO.beginTransaction();
		Funcionario funcionario = funcionarioDAO.find(funcionarioId);
		funcionarioDAO.closeTransaction();
		return funcionario;
	}

	public List<Funcionario> listAll() {
		funcionarioDAO.beginTransaction();
		List<Funcionario> result = (List<Funcionario>) funcionarioDAO.findAll();
		funcionarioDAO.closeTransaction();
		return result;
	}

	public void deleteFuncionario(Funcionario funcionario) {
		funcionarioDAO.beginTransaction();
		Funcionario persistedFuncionario = funcionarioDAO.findReferenceOnly(funcionario.getId());
		funcionarioDAO.delete(persistedFuncionario);
		funcionarioDAO.commitAndCloseTransaction();
	}
}