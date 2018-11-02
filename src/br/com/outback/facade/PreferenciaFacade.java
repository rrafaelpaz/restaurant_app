package br.com.outback.facade;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import br.com.outback.bean.Funcionario;
import br.com.outback.bean.Preferencia;
import br.com.outback.dao.PreferenciaDAO;

public class PreferenciaFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private PreferenciaDAO preferenciaDAO = new PreferenciaDAO();

	
	public void createPreferencia(Preferencia preferencia) {
		preferenciaDAO.beginTransaction();
		preferenciaDAO.save(preferencia);
		preferenciaDAO.commitAndCloseTransaction();
	}

	public void updatePreferencia(Preferencia preferencia) {

		preferenciaDAO.beginTransaction();
		
		Preferencia persistedPreferencia = preferenciaDAO.find(preferencia.getId());
		persistedPreferencia.setData(preferencia.getData());
		persistedPreferencia.setInicio(preferencia.getInicio());
		persistedPreferencia.setFim(preferencia.getFim());
		persistedPreferencia.setPenalidade(preferencia.getPenalidade());
		persistedPreferencia.setDescricao(preferencia.getDescricao());
		persistedPreferencia.setFuncionario(preferencia.getFuncionario());

		preferenciaDAO.update(persistedPreferencia);
		preferenciaDAO.commitAndCloseTransaction();
	}

	public Preferencia findPreferencia(long preferenciaId) {
		preferenciaDAO.beginTransaction();
		Preferencia preferencia = preferenciaDAO.find(preferenciaId);
		preferenciaDAO.closeTransaction();
		return preferencia;
	}

	public List<Preferencia> listAll() {
		preferenciaDAO.beginTransaction();
		List<Preferencia> result = (List<Preferencia>) preferenciaDAO.findAll();
		preferenciaDAO.closeTransaction();
		return result;
	}
	
	public List<Preferencia> findPreferenciasFuncionario(Funcionario funcionario, Calendar data) {
		preferenciaDAO.beginTransaction();
		List<Preferencia> result = preferenciaDAO.retornaPreferenciaFuncionario(funcionario, data);
		preferenciaDAO.closeTransaction();
		return result;
	}

	public void deletePreferencia(Preferencia preferencia) {
		preferenciaDAO.beginTransaction();
		Preferencia persistedPreferencia = preferenciaDAO.findReferenceOnly(preferencia.getId());
		preferenciaDAO.delete(persistedPreferencia);
		preferenciaDAO.commitAndCloseTransaction();
	}
}