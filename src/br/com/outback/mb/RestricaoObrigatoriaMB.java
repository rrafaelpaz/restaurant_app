package br.com.outback.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.outback.bean.Funcionario;
import br.com.outback.bean.RestricaoObrigatoria;
import br.com.outback.facade.FuncionarioFacade;
import br.com.outback.facade.RestricaoObrigatoriaFacade;
import br.com.outback.util.MessagesController;

@ManagedBean
@ViewScoped
public class RestricaoObrigatoriaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RestricaoObrigatoriaFacade restricaoObrigatoriaFacade = new RestricaoObrigatoriaFacade();
	private FuncionarioFacade funcionarioFacade = new FuncionarioFacade();
	
	private RestricaoObrigatoria restricaoObrigatoria = new RestricaoObrigatoria();
	
	private String[] selectedDias; 
    private List<String> dias;
	
	//* datatable
	private List<RestricaoObrigatoria> restricaoObrigatorias;
    private List<RestricaoObrigatoria> filteredrestricaoObrigatorias;
    
  //* 2 variaveis abaixo referente ao combobox de funcionario
    private String funcionario;
	private List<SelectItem> funcionarioItens;
    
    @PostConstruct
    public void init() {
    	 
    	this.criarDiasSemanas();
    	
    }
    
	
	
	public void salvar(ActionEvent event){
		
		//* Recupera o supermercado informado pelo usuario 
		 Funcionario funcionarioBanco = this.recuperaFuncionarioBanco();
		 
		 //* atribui objeto função ao funcionário para salvá-lo
		 restricaoObrigatoria.setFuncionario(funcionarioBanco);
		
		
		//* coloquei esse  < 1, porque não sei o porque, mas os novos registros vem com o id == 0
		if (this.restricaoObrigatoria.getId() == null || this.restricaoObrigatoria.getId() < 1) {
			
			for(String diaSemana : getSelectedDias()){
				
				//* estou forcando o id == nul
				restricaoObrigatoria.setId(null);
				restricaoObrigatoria.setDiaSemana(diaSemana);
				restricaoObrigatoriaFacade.createRestricaoObrigatoria(restricaoObrigatoria);
			}
			
		} else {
			restricaoObrigatoriaFacade.updateRestricaoObrigatoria(restricaoObrigatoria);
		}
		
		//* zera os objetos
		this.restricaoObrigatoria = new RestricaoObrigatoria();
		this.funcionario = null;
		this.criarDiasSemanas();
	
		MessagesController.msgOperacaoRealizadaComSucesso();

		MessagesController.refresh();
	}
	
	public void remove(RestricaoObrigatoria restricaoObrigatoria) {
	    try {
	        String teste = "s";
	    	//actorService.remove(actor);
	        //actorList = actorService.searchAll();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	

	//* preenche os checkbox do Cadastro de Turno
	private void criarDiasSemanas(){
			  dias = new ArrayList<String>();
			  dias.add("Segunda");
			  dias.add("Terça");
			  dias.add("Quarta");
			  dias.add("Quinta");
			  dias.add("Sexta");
			  dias.add("Sábado");
			  dias.add("Domingo");
	}
	 
	public void editar(RestricaoObrigatoria restricaoObrigatoria) {
        
		this.restricaoObrigatoria = restricaoObrigatoria;
    }
	     
	
	public RestricaoObrigatoria getRestricaoObrigatoria() {
		return restricaoObrigatoria;
	}

	public void setRestricaoObrigatoria(RestricaoObrigatoria restricaoObrigatoria) {
		this.restricaoObrigatoria = restricaoObrigatoria;
	}

	//* esses filteredrestricaoObrigatoria é necessário, porém não me ficou ainda claro o porque, sendo que é apenas um get e set
	public List<RestricaoObrigatoria> getFilteredrestricaoObrigatorias() {
		return filteredrestricaoObrigatorias;
	}

	public void setFilteredrestricaoObrigatorias(List<RestricaoObrigatoria> filteredrestricaoObrigatorias) {
		this.filteredrestricaoObrigatorias = filteredrestricaoObrigatorias;
	}

	public void setRestricaoObrigatorias(List<RestricaoObrigatoria> restricaoObrigatorias) {
		this.restricaoObrigatorias = restricaoObrigatorias;
	}
    
	//* recupera os itens e joga no datatable
	public List<RestricaoObrigatoria> getRestricaoObrigatorias() {
		restricaoObrigatorias = restricaoObrigatoriaFacade.listAll();
		return restricaoObrigatorias;
	}	
	
	public String[] getSelectedDias() {
		return selectedDias;
	}


	public void setSelectedDias(String[] selectedDias) {
		this.selectedDias = selectedDias;
	}


	public List<String> getDias() {
		return dias;
	}


	public void setDias(List<String> dias) {
		this.dias = dias;
	}
	
	//* Refente ao combobox

		private Funcionario recuperaFuncionarioBanco(){
			
			return funcionarioFacade.findFuncionario(Long.parseLong(getFuncionario()));
		}


		public String getFuncionario() {
			return funcionario;
		}

		public void setFuncionario(String funcionario) {
			this.funcionario = funcionario;
		}
		
	
	//* Popula o combobox
	public List<SelectItem> getFuncionarioItens() {
			
		if (funcionarioItens == null){
				
			funcionarioItens = new ArrayList<SelectItem>();
	           
			//* recupera do banco os funcionários
	        List<Funcionario> funcionarioLista = this.getFuncionarios();
	            
	        for (Funcionario funcionario : funcionarioLista){
	            	
	            funcionarioItens.add(new SelectItem(funcionario.getId(), funcionario.getNome())); 
	        }
	  }
			
		return funcionarioItens;
	}
		
	public List<Funcionario> getFuncionarios(){
		return funcionarioFacade.listAll();
	}

	public void setFuncaoItens(List<SelectItem> funcaoItens) {
		this.funcionarioItens = funcaoItens;
	}



	
	
	
}
