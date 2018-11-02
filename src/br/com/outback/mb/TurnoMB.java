package br.com.outback.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.outback.bean.Turno;
import br.com.outback.facade.TurnoFacade;
import br.com.outback.util.MessagesController;

@ManagedBean
@RequestScoped
public class TurnoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TurnoFacade turnoFacade = new TurnoFacade();
	private Turno turno = new Turno();
	
    //* datatable
    private List<Turno> turnos;
    private List<Turno> filteredturnos;
   
	
	public void salvar(){
		
		//* coloquei esse  < 1, porque n‹o sei o porque, mas os novos registros vem com o id == 0
		if (this.turno.getId() == null || this.turno.getId() < 1) {
		
			//* estou forcando o id == nul
			turno.setId(null);
				
		   //* salva o novo turno
		   turnoFacade.createTurno(turno);
			
			
		} else {
			turnoFacade.updateTurno(turno);
		}
		this.turno = new Turno();
	
		MessagesController.msgOperacaoRealizadaComSucesso();

		MessagesController.refresh();
	}
	
	
	public void remove(Turno turno) {
	    try {
	        String teste = "s";
	    	//actorService.remove(actor);
	        //actorList = actorService.searchAll();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//* editar o datatable
	 
	public void editar(Turno turno) {
        
		this.turno = turno;
    }


	public Turno getTurno() {
		return turno;
	}


	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	
	//* datatable
	public List<Turno> getTurnos() {
		
		turnos = turnoFacade.listAll();
		return turnos;
	}


	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}


	public List<Turno> getFilteredturnos() {
		return filteredturnos;
	}


	public void setFilteredturnos(List<Turno> filteredturnos) {
		this.filteredturnos = filteredturnos;
	}
	
		
	
	
}
