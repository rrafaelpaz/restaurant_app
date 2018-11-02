package br.com.outback.dao;

import br.com.outback.bean.Funcionario;

public class FuncionarioDAO  extends GenericDAO<Funcionario>{
	
	private static final long serialVersionUID = 1L;
	
	public FuncionarioDAO() {
		super(Funcionario.class);
	}

	public void delete(Funcionario funcionario) {
		super.delete(funcionario.getId(), Funcionario.class);
	}
	
	public void insert(Funcionario funcionario){
		
		super.save(funcionario);
	}
	

}
