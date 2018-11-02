package br.com.outback.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.outback.bean.Funcao;
import br.com.outback.facade.FuncaoFacade;
import br.com.outback.util.MessagesController;

@ManagedBean
@SessionScoped
public class FuncaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FuncaoFacade funcaoFacade = new FuncaoFacade();
	private Funcao funcao = new Funcao();
	
	//* datatable
	private List<Funcao> funcoes;
    private List<Funcao> filteredfuncoes;
	
	
	public void salvar(){
		//* coloquei esse  < 1, porque n‹o sei o porque, mas os novos registros vem com o id == 0
		if (this.funcao.getId() == null || this.funcao.getId() < 1) {
			//* estou forcando o id == nul
			funcao.setId(null);
			funcaoFacade.createFuncao(funcao);
		} else {
			funcaoFacade.updateFuncao(funcao);
		}
		this.funcao = new Funcao();
	
		MessagesController.msgOperacaoRealizadaComSucesso();

		MessagesController.refresh();
	}
	
	public void remove(Funcao funcao) {
	    try {
	        String teste = "s";
	    	//actorService.remove(actor);
	        //actorList = actorService.searchAll();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//* editar o datatable
	 
	public void editar(Funcao funcao) {
        
		this.funcao = funcao;
    }
	     
	
	public Funcao getfuncao() {
		return funcao;
	}

	public void setfuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	//* esses filteredfuncao Ž necess‡rio, porŽm n‹o me ficou ainda claro o porque, sendo que Ž apenas um get e set
	public List<Funcao> getFilteredfuncoes() {
		return filteredfuncoes;
	}

	public void setFilteredfuncoes(List<Funcao> filteredfuncoes) {
		this.filteredfuncoes = filteredfuncoes;
	}

	public void setfuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}
    
	//* recupera os itens e joga no datatable
	public List<Funcao> getfuncoes() {
		funcoes = funcaoFacade.listAll();
		return funcoes;
	}	
	
	
}
