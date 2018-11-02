package br.com.outback.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;



import br.com.outback.bean.Funcionario;
import br.com.outback.bean.Plantao;
import br.com.outback.bean.Turno;
import br.com.outback.facade.FuncionarioFacade;
import br.com.outback.facade.PlantaoFacade;
import br.com.outback.facade.TurnoFacade;
import br.com.outback.util.DataUtil;
import br.com.outback.util.HoraUtil;
import br.com.outback.util.MessagesController;
import br.com.outback.util.RestricaoUtil;
import org.primefaces.context.RequestContext;

@ManagedBean(name="plantaoMB")
@ViewScoped
public class PlantaoMB implements Serializable{


	private static final long serialVersionUID = 1L;
	
	 //private ScheduleModel eventModel;
	 private ScheduleModel lazyEventModel;
     private ScheduleEvent event = new DefaultScheduleEvent();
     
     private TurnoFacade turnoFacade = new TurnoFacade();
     private PlantaoFacade plantaoFacade = new PlantaoFacade();
     private FuncionarioFacade funcionarioFacade = new FuncionarioFacade();
 	// private Turno turno = new Turno();
 	 private Plantao plantao = new Plantao();
 	 private String replicarCheckbox="m�s";//* variavel que replica o turno, pode ser mes ou semana
 	 private String[] selectedDias; 
     private List<String> dias;
     private Date dataTurno;//* necessario criar por causa do primefaces
    
     //* data utiliza para buscar os turnos daquele dia
     private Date dataDoCliqueMes;
     
     //* id utilizado para buscar os funcionarios daquele turno
     private Long idPlantao;
     
     //* datatable turno
     
     private DataModel<Plantao> listaPlantaoDataModel;
     
     private DataModel<Turno> listaTurnoDataModel;
     
     //* datatable funcionario
     private DataModel<Funcionario> listaFuncionarioDataModel;
     
     
     //* combo de turno
     //private Turno turno;
     private List<Turno> turnos;
     
	 
	    @PostConstruct
	    public void init() {
	    	
	    	//* feito por causa do nullpointer quando seleciona o turno no dialog
	    	plantao.setTurno(new Turno());
	    	 
	    	this.criarDiasSemanas();
	    	this.pesquisarPlantoes();
	    	
	    	//* combo
	    	turnos = turnoFacade.retornaTurnosAtivos();
	    	
	    }
	    
	   
	    
	    // metodo retorna funcionario aleatorio
	    public List<Funcionario> getAleatoriamente(List<Funcionario> funcionarios){  
	             Collections.shuffle(funcionarios);  
	             return funcionarios;  
	    }
	    
	    //* Gera a escala dos plant�es, ap�s os mesmo terem sido criados
	    public void gerarEscala(ActionEvent event){
	    	
	    	//* Pega a da inicio e final do mes atual
	    	
	    	// aqui so pega o valor do mes corrente, nao do mes do turno, por isso que quando troca o mes do calendario, o mesmo
	    	//nao mostra os funcionarios do turno
	    	Calendar[] datas = DataUtil.retornaDatasPrimeiroEUtimoDiaMesCorrente();
	    	
	    	Calendar dataInicio = datas[0];
	    	Calendar dataFim = datas[1];
	    
	    	List<Funcionario> funcionarios = funcionarioFacade.listAll();
			
			//* retorna os plantoes para a data incicio e final do mes atual
			List<Plantao> plantoes = plantaoFacade.retornaPorData(dataInicio, dataFim);
			
			//* contador que contrala o n�mero de funcion�rio adicionado ao plant�o
			int contNumFuncionarioAdicionadoAoTurno = 1;
			
			//* necessario para a progressBar
			int numPlantoes = plantoes.size();
			
			for(Plantao p : plantoes){
				
				  // List<Funcionario> funcionarios = this.getAleatoriamente(funcionarioFacade.listAll());
				
					//* cria uma lista de funcionarios que estar�o trabalhando no plant�o
					Set<Funcionario> funcionariosSet = new HashSet<Funcionario>();
				
					Calendar dataPlantao = Calendar.getInstance(); 
					dataPlantao.setTime(p.getData().getTime());
					
					//* Preciso para  criar uma data do plantao com hora para as validacoes trabalhistas
					dataPlantao =  DataUtil.retornaDataTurnoComHora(dataPlantao, p.getTurno().getAbertura());
					
					
					for(Funcionario func : funcionarios){
						
						//* se o n�mero de funcion�rios adicionado ao plant�o for menor que a demanada, ent�o continua...
						if(contNumFuncionarioAdicionadoAoTurno <= p.getDemanda()){
							
							
							//* Verifica todas as restri��es do funcion�rio antes de adicion�-lo a um plant�o
							if(RestricaoUtil.funcionarioPodeTrabalhar(func, dataPlantao)){
								
								funcionariosSet.add(func);
								
								//* atualiza contador..
								contNumFuncionarioAdicionadoAoTurno ++;
							}
						}else{
							break;
						}
					
					}
				
	
					//* adiciona ao plantao os funcionarios
					p.setFuncionarios(funcionariosSet);
					
					//* atualiza plantao
					plantaoFacade.update(p);
					
					//* configura o contador novamente para o valor inicial
					contNumFuncionarioAdicionadoAoTurno = 1;
			  }
			
			
			//MessagesController.msgOperacaoRealizadaComSucesso();

			//MessagesController.refresh();	    	
	    }
	    
		
		public void salvar(ActionEvent event){
			
			
			//* coloquei esse  < 1, porque n�o sei o porque, mas os novos registros vem com o id == 0
			if (this.plantao.getId() == null || this.plantao.getId() < 1) {
				
				
				List<Calendar> listaDatasTurnos = null; 
				
				//* transforma a data do plant�o em calendar
				Calendar cal = Calendar.getInstance();
				cal.setTime(this.getDataTurno());
				
				
				//* verifica se a replica��o do turno � para a semana ou m�s
				if(this.getReplicarCheckbox().equals("semana")){
					
					//* recupera o primeiro dia da semana(segunda) da data escolhida pelo usuario
					Calendar dataDoTurno = DataUtil.recuperaPrimeioDiaSemana(cal);
					
					//* desmembra o ano, mes e dia para criar novas datas de dia de semana
					int ano = dataDoTurno.get(Calendar.YEAR);
					int mes = dataDoTurno.get(Calendar.MONTH);
					int dia = dataDoTurno.get(Calendar.DAY_OF_MONTH);
					
					
					//* retorna as novas datas da semana, baseado no que o usuario escolheu
					listaDatasTurnos = DataUtil.retornaDiasSelecionadosUsuariosParaSemana(ano,mes,dia, this.getSelectedDias());
					
				}else{
					
					//* retorna as novas datas da semana, baseado no que o usuario escolheu
					listaDatasTurnos = DataUtil.retornaDiasSelecionadosUsuariosParaMes(cal ,this.getSelectedDias());
					
				}
				
				//* replica os turnos nas datas escolhidas pelo usuario
				for(Calendar dataTurno : listaDatasTurnos){
					
					//* estou forcando o id == nul
					plantao.setId(null);
					
					//* seta a nova data do turno
					plantao.setData(dataTurno);
					
					//* salva o novo plantao
					plantaoFacade.createPlantao(plantao);
				}
				
				
			} else {
				plantaoFacade.update(plantao);
			}
			
			//* zera os objetos 
			this.plantao = new Plantao();
			
			//this.turno = new Turno();
			
			this.criarDiasSemanas();
			
			MessagesController.msgOperacaoRealizadaComSucesso();

			MessagesController.refresh();
			
		}
	    
	 
	    @SuppressWarnings("serial")
		public void pesquisarPlantoes(){
	    	
	    	lazyEventModel = new LazyScheduleModel() {
	    		
	             
	            @Override
	            public void loadEvents(Date start, Date end) {

	            	//* Pega o mes da troca de m�s do calendario
	            	GregorianCalendar mesCalendario = new GregorianCalendar();
	            	mesCalendario.setTime(start);
	            	mesCalendario.add(Calendar.DAY_OF_MONTH, 12);
	            
	            	//* Faz a busca baseado no m�s do calend�rio selecionado pelo usu�rio
	    	    	Calendar[] datas = DataUtil.retornaDatasPrimeiroEUtimoDiaMes(mesCalendario);
			    	
			    	Calendar data1 = datas[0];
			    	Calendar data2 = datas[1];
			    	
			    	//* Retornoa todos os plant�es baseados na data de busca
		    		List<Plantao> lista = plantaoFacade.retornaPorData(data1, data2) ; 

	            	 
	            	 //* se lista vier null, na hora de entrar no for tava dando nullpointer, n�o sei o porque disso...
	    		    if(null != lista){
	    		    
	    			    for(Plantao p : lista){
	    			    	
	    			        //* recupera os horarios do turno
	    			        Date abertura = p.getTurno().getAbertura();
	    			        Date fechamento = p.getTurno().getFechamento();
	    			        
	    			         //* tem que deixar os horarios com a mesma data do turno, pois no momento os horarios Date vem com data padrao de 1970
	    					 abertura = HoraUtil.retornaHoraDoDiaPlantao(p.getData(), abertura);
	    					 fechamento = HoraUtil.retornaHoraDoDiaPlantao(p.getData(), fechamento);
	    						        
	    			        //* titulos do plant�o que aparecer� no calend�rio
	    			        String tituloPlantao = " Demanda: ".concat(p.getDemanda().toString());
	    			        
	    			        //* cria o evento que sera adicionado no calendario
	    			        DefaultScheduleEvent plantao = new DefaultScheduleEvent(tituloPlantao, abertura, fechamento);
	    			        
	    			        //* seta o id do plantao no evento, pois quando o usuario clicar no mesmo, esse id sera utilizado para mostrar
	    			        //* os funcionarios que estao naquele plantao
	    			        plantao.setDescription(p.getId().toString());
	    			       
	    			        //* joga os hor�rios dos plantao do dia no calend�rio
	    			        addEvent(plantao);
	    	                
	    			    }
	    		    }
	            }  
	        };
	        
	        
		   /* eventModel = new DefaultScheduleModel();
		    
		    //* se lista vier null, na hora de entrar no for tava dando nullpointer, n�o sei o porque disso...
		    if(null != lista){
		    
			    for(Plantao p : lista){
			    	
			    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				    
				    System.out.println("data do turno " + sdf.format(p.getDataTableDate()));
				    
			        
			        //* recupera os horarios do turno
			        Date abertura = p.getTurno().getAbertura();
			        Date fechamento = p.getTurno().getFechamento();
			        
			         //* tem que deixar os horarios com a mesma data do turno, pois no momento os horarios Date vem com data padrao de 1970
					 abertura = HoraUtil.retornaHoraDoDiaPlantao(p.getData(), abertura);
					 fechamento = HoraUtil.retornaHoraDoDiaPlantao(p.getData(), fechamento);
						        
			        //* titulos do plant�o que aparecer� no calend�rio
			        String tituloPlantao = " Demanda: ".concat(p.getDemanda().toString());
			        
			        //* cria o evento que sera adicionado no calendario
			        DefaultScheduleEvent plantao = new DefaultScheduleEvent(tituloPlantao, abertura, fechamento);
			        
			        //* seta o id do plantao no evento, pois quando o usuario clicar no mesmo, esse id sera utilizado para mostrar
			        //* os funcionarios que estao naquele plantao
			        plantao.setDescription(p.getId().toString());
			       
			        //* joga os hor�rios dos plantao do dia no calend�rio
			        eventModel.addEvent(plantao);
			 	       
			    }
		    }*/	    	
	    }
	    
	  	  	    
	    //* Ao clicar no plantao do Calend�rio, traz os funcion�rios relacionado ao mesmo  
	    public void onEventSelect(SelectEvent selectEvent) {
	    	//* aqui pega o plantao em si, seja da semana ou do dia. "Semana" e "Dia" do Calend�rio
	    	
	    	//* sempre zera quando da o clique, para n�o dar problemas de pegar datas no objetos de cliques anteriores
	    	this.setDataDoCliqueMes(null);
	    	listaTurnoDataModel = null;
	    	
	    	this.plantao = new Plantao();
	    	
	    	//* recupera no clique em cima do plantao, o seu id
	    	String idPlantao = ((ScheduleEvent) selectEvent.getObject()).getDescription();
	    	
	    	//* seta no objeto o nome id para busca pelo includeFuncionariosTurno.xhtml
	    	this.setIdPlantao(Long.parseLong(idPlantao));
	    	
	    	event = (ScheduleEvent) selectEvent.getObject();
	    }
	     
	    //* Ao clicar no dia do Calend�rio, traz os plantoes relacionados ao mesmo
	    public void onDateSelect(SelectEvent selectEvent) {
	    	
	    	//* sempre zera quando da o clique, para n�o dar problemas de pegar datas no objetos de cliques anteriores
	    	this.setDataDoCliqueMes(null);
	    	
	    	this.plantao = new Plantao();
	    	
	    	//* aqui pega todos os plantoes do dia. "M�s" do Calend�rio
	    	Date dataDoClique = (Date) selectEvent.getObject();
	    	
	    	
	    	//* prenche o objeto dataDoCliqueMes com um valor, assim, na busca do dataTable ser� feita por essa data
	    	//* para trazer apenas o resultado de plantoes por essa data
	    	this.setDataDoCliqueMes(dataDoClique);
	    	
	        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	    }
	    
	    public DataModel<Plantao> getPlantoes() {
	    	
	    	Date dataDoClique = this.getDataDoCliqueMes();
	    	
	    	//* Se o objeto n�o estiver null, � porque o usu�rio deu um clique no calend�rio em um dia espec�fico do m�s,
	    	//* assim sendo, a pesquisa � realizada apenas para essa data
	    	if(dataDoClique != null){
	    		
	    		Calendar calData1 = Calendar.getInstance();
	    		Calendar calData2 = Calendar.getInstance();
	    		calData1.setTime(dataDoClique);
	    		calData2.setTime(dataDoClique);
	    		
	    		calData1.set(Calendar.AM_PM, Calendar.AM);
	    		calData1.set(Calendar.HOUR, 0);
	    		calData1.set(Calendar.MINUTE, 0);
	    		calData1.set(Calendar.SECOND, 0);
		
	    		calData2.set(Calendar.AM_PM, Calendar.PM);
	    		calData2.set(Calendar.HOUR, 11);
	    		calData2.set(Calendar.MINUTE, 59);
	    		calData2.set(Calendar.SECOND, 59);
				
	    		List<Plantao> plantoesDia = plantaoFacade.retornaPorData(calData1, calData2) ; 
				
	    		listaPlantaoDataModel = new ListDataModel<Plantao>(plantoesDia);
	
	    	}
	    	
			return listaPlantaoDataModel;
		}
	  
	
	    public DataModel<Turno> getTurnosAtivos() {
	    	
	    			
	    	List<Turno> turnosAtivos = turnoFacade.retornaTurnosAtivos() ; 
				
		    listaTurnoDataModel = new ListDataModel<Turno>(turnosAtivos);
	    	
			return listaTurnoDataModel;
		}
	    
	    public List<Turno> getTurnoAtivo(){
	    	
	    	List<Turno> turnosAtivos = turnoFacade.retornaTurnosAtivos() ; 
	    	
	    	return turnosAtivos;
	    }
	    
	    public DataModel<Funcionario> getFuncionariosTurno() {
	    	
	    	Long idPlantao = this.getIdPlantao();
	    	
	    	//* Se o objeto n�o estiver null, � porque o usu�rio deu um clique no turno, assim sendo, ser� buscado os funcionarios relacionado aquele turno
	    	if(idPlantao != null){
	    		
	    		Plantao plantao = plantaoFacade.findPlantao(idPlantao);
	 	    	
	    		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	    		
	    		//* necess�rio fazer essa convers�o, pois o dataModel n�o aceita lista de Set, apenas List
	    		for(Funcionario f : plantao.getFuncionarios()){
	    			
	    			funcionarios.add(f);
	    		}
	    		
	    		//* se a lista n�o estiver vazia, ent�o joga a mesma no dataModel
	    		if(funcionarios != null){
	    			
	    			listaFuncionarioDataModel = new ListDataModel<Funcionario>(funcionarios);
	    		}
	    	}
	    	
			return listaFuncionarioDataModel;
		}
	    
	    //* metodo usado, quando o usuario seleciona um turno no includeTurno.xhtml
	    public void selecionarTurno(SelectEvent event) {
	    	
	    	Turno turnoSelecionado = ((Turno) event.getObject());
	    	plantao.setTurno(turnoSelecionado);
	    }
		
		public void remove(Turno turno) {
		    try {
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
		        dias.add("Ter�a");
		        dias.add("Quarta");
		        dias.add("Quinta");
		        dias.add("Sexta");
		        dias.add("S�bado");
		        dias.add("Domingo");
		 }
		  
	
		
		//* =============  Getter e Setter ========================
		
	   // public ScheduleModel getEventModel() {
	   //    return eventModel;
	//	}
		     
		public ScheduleEvent getEvent() {
		    return event;
		}
		
		
		public ScheduleModel getLazyEventModel() {
			return lazyEventModel;
		}

		

		public Plantao getPlantao() {
			return plantao;
		}


		public void setPlantao(Plantao plantao) {
			this.plantao = plantao;
		}


		//public Turno getTurno() {
	//		return turno;
	//	}

		//public void setTurno(Turno turno) {
		//	this.turno = turno;
		//}


		public String getReplicarCheckbox() {
			return replicarCheckbox;
		}


		public void setReplicarCheckbox(String replicarCheckbox) {
			this.replicarCheckbox = replicarCheckbox;
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


		public Date getDataTurno() {
			return dataTurno;
		}


		public void setDataTurno(Date dataTurno) {
			this.dataTurno = dataTurno;
		}


		public Date getDataDoCliqueMes() {
			return dataDoCliqueMes;
		}


		public void setDataDoCliqueMes(Date dataDoCliqueMes) {
			this.dataDoCliqueMes = dataDoCliqueMes;
		}


		public Long getIdPlantao() {
			return idPlantao;
		}


		public void setIdPlantao(Long idPlantao) {
			this.idPlantao = idPlantao;
		}



		public List<Turno> getTurnos() {
			return turnos;
		}



		public void setTurnos(List<Turno> turnos) {
			this.turnos = turnos;
		} 
		
		
	 
	}