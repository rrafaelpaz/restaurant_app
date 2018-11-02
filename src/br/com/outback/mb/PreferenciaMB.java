package br.com.outback.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.outback.bean.Funcionario;
import br.com.outback.bean.Preferencia;
import br.com.outback.facade.FuncionarioFacade;
import br.com.outback.facade.PreferenciaFacade;
import br.com.outback.util.MessagesController;

@ManagedBean
@SessionScoped
public class PreferenciaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PreferenciaFacade preferenciaFacade = new PreferenciaFacade();
	private FuncionarioFacade funcionarioFacade = new FuncionarioFacade();
	
	private Preferencia preferencia = new Preferencia();
	
	private Date dataPreferencia;//* necessario criar por causa do primefaces
	
	private String penalidade;
	
	//* datatable
	private List<Preferencia> preferencias;
    private List<Preferencia> filteredpreferencias;
    
  //* 2 variaveis abaixo referente ao combobox de funcionario
    private String funcionario;
	private List<SelectItem> funcionarioItens;
	
	
	public void salvar(){
		
		//* Recupera o supermercado informado pelo usuario 
		 Funcionario funcionarioBanco = this.recuperaFuncionarioBanco();
		
		 //* tem que converter a data pra Calendar por causa do primefaces
		 Calendar data = Calendar.getInstance();
		 data.setTime(dataPreferencia);
		 
		 //* converte a penalidade de String pra Integer
		 preferencia.setPenalidade(Integer.parseInt(penalidade));
		 
		 preferencia.setData(data);
		 preferencia.setFuncionario(funcionarioBanco);
		
		//* coloquei esse  < 1, porque não sei o porque, mas os novos registros vem com o id == 0
		if (this.preferencia.getId() == null || this.preferencia.getId() < 1) {
			
			//* estou forcando o id == nul
			preferencia.setId(null);
			
			preferenciaFacade.createPreferencia(preferencia);
		} else {
			preferenciaFacade.updatePreferencia(preferencia);
		}
		
		this.preferencia = new Preferencia();
		
		this.funcionario = null;
		this.penalidade = null;
	
		MessagesController.msgOperacaoRealizadaComSucesso();

		MessagesController.refresh();
	}
	
	public void remove(Preferencia preferencia) {
	    try {
	        String teste = "s";
	    	//actorService.remove(actor);
	        //actorList = actorService.searchAll();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//* editar o datatable
	 
	public void editar(Preferencia preferencia) {
        
		this.preferencia = preferencia;
    }
	     
	
	public Preferencia getpreferencia() {
		return preferencia;
	}

	public void setpreferencia(Preferencia preferencia) {
		this.preferencia = preferencia;
	}
	
	public Date getDataPreferencia() {
		return dataPreferencia;
	}

	public void setDataPreferencia(Date dataPreferencia) {
		this.dataPreferencia = dataPreferencia;
	}
	
	public Preferencia getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(Preferencia preferencia) {
		this.preferencia = preferencia;
	}

	public List<Preferencia> getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(List<Preferencia> preferencias) {
		this.preferencias = preferencias;
	}

	public String getPenalidade() {
		return penalidade;
	}

	public void setPenalidade(String penalidade) {
		this.penalidade = penalidade;
	}

	//* esses filteredpreferencia é necessário, porém não me ficou ainda claro o porque, sendo que é apenas um get e set
	public List<Preferencia> getFilteredpreferencias() {
		return filteredpreferencias;
	}

	public void setFilteredpreferencias(List<Preferencia> filteredpreferencias) {
		this.filteredpreferencias = filteredpreferencias;
	}

	public void setpreferencias(List<Preferencia> preferencias) {
		this.preferencias = preferencias;
	}
    
	//* recupera os itens e joga no datatable
	public List<Preferencia> getpreferencias() {
		preferencias = preferenciaFacade.listAll();
		return preferencias;
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
