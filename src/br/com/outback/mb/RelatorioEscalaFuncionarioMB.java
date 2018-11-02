package br.com.outback.mb;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import br.com.outback.bean.Funcionario;
import br.com.outback.dto.RelEscalaFuncDTO;
import br.com.outback.facade.FuncionarioFacade;
import br.com.outback.jdbc.dao.EscalaJDBCDAO;
import br.com.outback.jdbc.dao.IEscalaJDBCDAO;
import br.com.outback.util.RelatorioUtils;

@SessionScoped
@ManagedBean
public class RelatorioEscalaFuncionarioMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date dataInicio;
	private Date dataFim;
	
	private FuncionarioFacade funcionarioFacade = new FuncionarioFacade();
	
	//* datatable
	private List<RelEscalaFuncDTO> escalas;
	
	//* 2 variaveis abaixo referente ao combobox de funcionario
    private String funcionario;
	private List<SelectItem> funcionarioItens;
    
	    
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void gerarRelatorioEsalaFuncionario(ActionEvent event) {
		
		//* Recupera o funcionario informado pelo usuario 
	    Funcionario funcionarioBanco = this.recuperaFuncionarioBanco();
		
		IEscalaJDBCDAO dao = new EscalaJDBCDAO();
		
		List<RelEscalaFuncDTO> lista = dao.retornaRelEscalaFuncionario(funcionarioBanco, dataInicio, dataFim);
		
		String nomeRelatorio = "attachment; filename=\"relatorio de escala do funcionário.pdf\"";
	
		if (lista != null && !lista.isEmpty()) {
			
			//* recupera a logo do relatório do caminho da aplicação
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();  
		  
			File f = new File(ctx.getRealPath("/resources/images/logo_report_outback.jpg")); 
			BufferedImage logo = null;
			try {
				logo = ImageIO.read(f);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
		            
			
			HashMap map = new HashMap();
	        
	        map.put("TITULO_RELATORIO", "RELATÓRIO DE ESCALA DO FUNCIONARIO");
	        map.put("NOME_FUNCIONARIO", funcionarioBanco.getNome());
	        map.put("LOGO_RELATORIO", logo);
	    	

			String path = "report/reportEscalaFuncionario.jrxml";

			try {
				RelatorioUtils.gerarRelatorio(path, lista, nomeRelatorio, map);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	//* Esse método é para popular o datatable
	public void pesquisarEscalaFuncionario(ActionEvent event){
		
		//* quando abrir a tela, ele não vai buscar nada, pois tudo estará null no começo
	    if(funcionario != null && dataInicio != null && dataFim != null){
	    	
	    	IEscalaJDBCDAO dao = new EscalaJDBCDAO();
		    
	    	//* Recupera o funcionario informado pelo usuario 
	    	Funcionario funcionarioBanco = this.recuperaFuncionarioBanco();
		 
			escalas = dao.retornaRelEscalaFuncionario(funcionarioBanco, dataInicio, dataFim);
		}
	}

	

	private Funcionario recuperaFuncionarioBanco(){
		
		return funcionarioFacade.findFuncionario(Long.parseLong(getFuncionario()));
	}


	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}



	public List<RelEscalaFuncDTO> getEscalas() {
		
		return escalas;
	}



	public void setEscalas(List<RelEscalaFuncDTO> escalas) {
		this.escalas = escalas;
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
