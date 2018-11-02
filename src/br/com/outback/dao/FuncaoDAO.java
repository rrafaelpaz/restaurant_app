package br.com.outback.dao;

import br.com.outback.bean.Funcao;

public class FuncaoDAO  extends GenericDAO<Funcao>{
	
	private static final long serialVersionUID = 1L;
	
	public FuncaoDAO() {
		super(Funcao.class);
	}

	public void delete(Funcao funcao) {
		super.delete(funcao.getId(), Funcao.class);
	}
	
	public void insert(Funcao funcao){
		
		super.save(funcao);
	}
	

}
