package br.com.outback.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.outback.bean.Funcao;
import br.com.outback.bean.Funcionario;
import br.com.outback.facade.FuncaoFacade;
import br.com.outback.facade.FuncionarioFacade;
import br.com.outback.util.MessagesController;

@ManagedBean
@ViewScoped
public class FuncionarioMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FuncionarioFacade funcionarioFacade = new FuncionarioFacade();
	private FuncaoFacade funcaoFacade = new FuncaoFacade();
	private Funcionario funcionario = new Funcionario();
	
	private List<Funcionario> filteredfuncionarios;
    
	//* combobox
	private List<Funcao> funcoes;
	
	
	@PostConstruct
	private void init(){
		funcoes = funcaoFacade.listAll();
	}
    
   
	
	public void salvar(){
		 
		
		//* coloquei esse  < 1, porque n‹o sei o porque, mas os novos registros vem com o id == 0
		if (this.funcionario.getId() == null || this.funcionario.getId() < 1) {
			//* estou forcando o id == nul
			funcionario.setId(null);
			funcionarioFacade.createFuncionario(funcionario);
		} else {
			funcionarioFacade.updateFuncionario(funcionario);
		}
		
		//* limpa o objeto
		this.funcionario = new Funcionario();
		
		//* limpa o combo
		//this.setFuncao(null);
	
		MessagesController.msgOperacaoRealizadaComSucesso();

		MessagesController.refresh();
	}
	
	public void remove(Funcionario Funcionario) {
	    try {
	        String teste = "s";
	    	//actorService.remove(actor);
	        //actorList = actorService.searchAll();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//* editar o datatable
	 
	public void editar(Funcionario funcionario) {
        
		//funcaoItens.add(new SelectItem(funcionario.getFuncao().getId(), funcionario.getFuncao().getNome()));
		//funcaoItens.add(0, new SelectItem(funcionario.getFuncao().getId(), funcionario.getFuncao().getNome()));
		 
    }
	     
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	//* esses filteredFuncionario Ž necess‡rio, porŽm n‹o me ficou ainda claro o porque, sendo que Ž apenas um get e set
	public List<Funcionario> getFilteredfuncionarios() {
		return filteredfuncionarios;
	}

	public void setFilteredfuncionarios(List<Funcionario> filteredfuncionarios) {
		this.filteredfuncionarios = filteredfuncionarios;
	}
	
	//* recupera os itens e joga no datatable
	public List<Funcionario> getFuncionarios() {
		List<Funcionario> funcionarios = funcionarioFacade.listAll();
		return funcionarios;
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}	
	
	
	
	
	
		
}
