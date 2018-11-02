package br.com.outback.facade;

import java.io.Serializable;
import java.util.List;

import br.com.outback.bean.Turno;
import br.com.outback.dao.TurnoDAO;

public class TurnoFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private TurnoDAO turnoDAO= new TurnoDAO();

	
	public void createTurno(Turno turno) {
		turnoDAO.beginTransaction();
		turnoDAO.save(turno);
		turnoDAO.commitAndCloseTransaction();
	}

	public void updateTurno(Turno turno) {
		turnoDAO.beginTransaction();
		Turno persistedTurno = turnoDAO.find(turno.getId());
		//persistedTurno.setNome(turno.getNome());// implementar aqui para os atributos a serem atualizadso
		turnoDAO.update(persistedTurno);
		turnoDAO.commitAndCloseTransaction();
	}

	public Turno findTurno(long turnoId) {
		turnoDAO.beginTransaction();
		Turno turno = turnoDAO.find(turnoId);
		turnoDAO.closeTransaction();
		return turno;
	}

	public List<Turno> listAll() {
		turnoDAO.beginTransaction();
		List<Turno> result = (List<Turno>) turnoDAO.findAll();
		turnoDAO.closeTransaction();
		return result;
	}
	
	public List<Turno> retornaTurnosAtivos() {
		turnoDAO.beginTransaction();
		List<Turno> result = (List<Turno>) turnoDAO.retornaTurnosAtivos();
		turnoDAO.closeTransaction();
		return result;
	}

	public void deleteTurno(Turno turno) {
		turnoDAO.beginTransaction();
		Turno persistedTurno = turnoDAO.findReferenceOnly(turno.getId());
		turnoDAO.delete(persistedTurno);
		turnoDAO.commitAndCloseTransaction();
	}
}