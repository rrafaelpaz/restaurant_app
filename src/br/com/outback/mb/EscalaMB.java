package br.com.outback.mb;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;

import br.com.outback.bean.Funcionario;
import br.com.outback.dto.EscalaFuncDTO;
import br.com.outback.dto.RelEscalaFuncDTO;
import br.com.outback.facade.FuncionarioFacade;
import br.com.outback.jdbc.dao.EscalaJDBCDAO;
import br.com.outback.jdbc.dao.IEscalaJDBCDAO;
import br.com.outback.util.DataUtil;

@ManagedBean
public class EscalaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date dataEscalaPesquisa;

	private String stringDataPesquisaSemana1;
	private String stringDataPesquisaSemana2;
	private String stringDataPesquisaSemana3;
	private String stringDataPesquisaSemana4;
	private String stringDataPesquisaSemana5;
	
	//* datatable
	private List<EscalaFuncDTO> escalaSemana1;
	private List<EscalaFuncDTO> escalaSemana2;
	private List<EscalaFuncDTO> escalaSemana3;
	private List<EscalaFuncDTO> escalaSemana4;
	private List<EscalaFuncDTO> escalaSemana5;
	private List<EscalaFuncDTO> filteredescalasSemana1;
	private List<EscalaFuncDTO> filteredescalasSemana2;
	private List<EscalaFuncDTO> filteredescalasSemana3;
	private List<EscalaFuncDTO> filteredescalasSemana4;
	private List<EscalaFuncDTO> filteredescalasSemana5;


	public void pesquisar(ActionEvent event){
		
		//* transforma a data de pesquisa da tela em Calendar
		Calendar calDataPesquisa = Calendar.getInstance();
		calDataPesquisa.setTime(dataEscalaPesquisa);
		
		//* retorna o primeiro e ultimo dia de todas as semanas do mês
		Calendar[] datas = DataUtil.retornaDatasPrimeiroEUtimoDiaMes(calDataPesquisa);
		
		Calendar calDataInicio = datas[0];
		Calendar calDataFim = datas[1];
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		
		//* contador que vai verificar em qual semana está o loop
		int cont = 0;
        
		
		//* faz um loop na data início da consulta, onde ira trazer a cada loop cada primeiro e ultimo dia daquela semana. Só irá parar quando a dataInicio
		//* incrementar até a dataFim
		do{
			
		    //* recupera o primeiro e ultimo dia da respectiva semana escolhido pelo usuario para pesquisa de escala no banco
			 Date primeiroDataSemana = DataUtil.retornaPrimeiroUltimoSemana(calDataInicio.getTime(), true);  
		     Date ultimaDataSemana = DataUtil.retornaPrimeiroUltimoSemana(calDataInicio.getTime(), false);
		      
		    List<EscalaFuncDTO> escala = this.retornaEscalasFunc(primeiroDataSemana, ultimaDataSemana);
		    
			//* verifica qual semana é a consulta e populariza o datatable de cada semana
			if(cont == 0){
		    	this.escalaSemana1 = escala;
		    }else if(cont ==1){
		        this.escalaSemana2 = escala;
		    }else if(cont == 2){
		        this.escalaSemana3 = escala;
		    }else if(cont == 3){
		        this.escalaSemana4 = escala;
		    }else{
		    	this.escalaSemana5 = escala;
		    }
		    
		    //* retorna em String a data inicio e final da escala na semana
			String semanaPesquisa = sdf.format(primeiroDataSemana).concat(" - ").concat(sdf.format(ultimaDataSemana));

			//* recupera o componente TabView 
			TabView tabViewEscala = ((TabView) FacesContext.getCurrentInstance().getViewRoot().findComponent("idDatatableEscala:tabEscalaMes"));
		   
		    Tab tab = (Tab) tabViewEscala.getChildren().get(cont);
			
		    //* sempre habilita todas as abas, pois alguma pode estar desabilitados em uma pesquisa anterior 
		    tab.setDisabled(false);
			
		    //* seta o título do tab como a data de pesquisa da semana do respectivo mês de consulta
			tab.setTitle(semanaPesquisa);
		    
	        //* para não perder tempo com loops desnecessarios, ja passa pra proxima semana
	        calDataInicio.add(Calendar.DAY_OF_MONTH, 7);
		          
	        //* atualiza o contador
			cont ++;
	        
		}while(calDataInicio.before(calDataFim));
		
		
	     
	}
	
	
	
	//* cria a escala baseada em cada funcionario
	private List<EscalaFuncDTO> retornaEscalasFunc(Date dataInicio, Date dataFim){
		
		List<EscalaFuncDTO> listaEscala = new ArrayList<EscalaFuncDTO>(); 
		
		IEscalaJDBCDAO escalaDAO = new EscalaJDBCDAO();
		
		FuncionarioFacade funcionarioFacade = new FuncionarioFacade();
		 
		//* recupera todo os funcionarios do banco
		List<Funcionario> funcionarios = funcionarioFacade.listAll();
		
		//* faz um loop e onde encontrar alguma escala para o mesmo adiciona a lista
		for(Funcionario f : funcionarios){
			
			EscalaFuncDTO escalaFunc = escalaDAO.retornaEscalaSemalFuncionario(f, dataInicio, dataFim);
			
			if(escalaFunc.getNomeFunc() != null && !escalaFunc.getNomeFunc().isEmpty()){
				//* ao adicionar na lista de escala, o objeto EscalaFuncDTO vai sendo alterado e mudando os status
				//* de "FOLGA" para seu respectivo turno
				listaEscala.add(escalaFunc);
			}
			
		}
		
		return listaEscala;
	}
	
	public Date getDataEscalaPesquisa() {
		return dataEscalaPesquisa;
	}
	public void setDataEscalaPesquisa(Date dataEscalaPesquisa) {
		this.dataEscalaPesquisa = dataEscalaPesquisa;
	}
	public String getStringDataPesquisaSemana1() {
		return stringDataPesquisaSemana1;
	}
	public void setStringDataPesquisaSemana1(String stringDataPesquisaSemana1) {
		this.stringDataPesquisaSemana1 = stringDataPesquisaSemana1;
	}
	public String getStringDataPesquisaSemana2() {
		return stringDataPesquisaSemana2;
	}
	public void setStringDataPesquisaSemana2(String stringDataPesquisaSemana2) {
		this.stringDataPesquisaSemana2 = stringDataPesquisaSemana2;
	}
	public String getStringDataPesquisaSemana3() {
		return stringDataPesquisaSemana3;
	}
	public void setStringDataPesquisaSemana3(String stringDataPesquisaSemana3) {
		this.stringDataPesquisaSemana3 = stringDataPesquisaSemana3;
	}
	public String getStringDataPesquisaSemana4() {
		return stringDataPesquisaSemana4;
	}
	public void setStringDataPesquisaSemana4(String stringDataPesquisaSemana4) {
		this.stringDataPesquisaSemana4 = stringDataPesquisaSemana4;
	}
	public String getStringDataPesquisaSemana5() {
		return stringDataPesquisaSemana5;
	}
	public void setStringDataPesquisaSemana5(String stringDataPesquisaSemana5) {
		this.stringDataPesquisaSemana5 = stringDataPesquisaSemana5;
	}

		// ============== datatable ============================ 
		public List<EscalaFuncDTO> getEscalaSemana1() {
			return escalaSemana1;
		}
		public void setEscalaSemana1(List<EscalaFuncDTO> escalaSemana1) {
			this.escalaSemana1 = escalaSemana1;
		}
		public List<EscalaFuncDTO> getEscalaSemana2() {
			return escalaSemana2;
		}
		public void setEscalaSemana2(List<EscalaFuncDTO> escalaSemana2) {
			this.escalaSemana2 = escalaSemana2;
		}
		public List<EscalaFuncDTO> getEscalaSemana3() {
			return escalaSemana3;
		}
		public void setEscalaSemana3(List<EscalaFuncDTO> escalaSemana3) {
			this.escalaSemana3 = escalaSemana3;
		}
		public List<EscalaFuncDTO> getEscalaSemana4() {
			return escalaSemana4;
		}
		public void setEscalaSemana4(List<EscalaFuncDTO> escalaSemana4) {
			this.escalaSemana4 = escalaSemana4;
		}
		public List<EscalaFuncDTO> getEscalaSemana5() {
			return escalaSemana5;
		}
		public void setEscalaSemana5(List<EscalaFuncDTO> escalaSemana5) {
			this.escalaSemana5 = escalaSemana5;
		}
		public List<EscalaFuncDTO> getFilteredescalasSemana1() {
			return filteredescalasSemana1;
		}
		public void setFilteredescalasSemana1(List<EscalaFuncDTO> filteredescalasSemana1) {
			this.filteredescalasSemana1 = filteredescalasSemana1;
		}
		public List<EscalaFuncDTO> getFilteredescalasSemana2() {
			return filteredescalasSemana2;
		}
		public void setFilteredescalasSemana2(List<EscalaFuncDTO> filteredescalasSemana2) {
			this.filteredescalasSemana2 = filteredescalasSemana2;
		}
		public List<EscalaFuncDTO> getFilteredescalasSemana3() {
			return filteredescalasSemana3;
		}
		public void setFilteredescalasSemana3(List<EscalaFuncDTO> filteredescalasSemana3) {
			this.filteredescalasSemana3 = filteredescalasSemana3;
		}
		public List<EscalaFuncDTO> getFilteredescalasSemana4() {
			return filteredescalasSemana4;
		}
		public void setFilteredescalasSemana4(List<EscalaFuncDTO> filteredescalasSemana4) {
			this.filteredescalasSemana4 = filteredescalasSemana4;
		}
		public List<EscalaFuncDTO> getFilteredescalasSemana5() {
			return filteredescalasSemana5;
		}
		public void setFilteredescalasSemana5(List<EscalaFuncDTO> filteredescalasSemana5) {
			this.filteredescalasSemana5 = filteredescalasSemana5;
		}
		// ====================================
	
	

}
