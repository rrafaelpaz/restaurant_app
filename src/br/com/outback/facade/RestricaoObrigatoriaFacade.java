package br.com.outback.facade;

import java.io.Serializable;
import java.util.List;

import br.com.outback.bean.Funcionario;
import br.com.outback.bean.RestricaoObrigatoria;
import br.com.outback.dao.RestricaoObrigatoriaDAO;

public class RestricaoObrigatoriaFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private RestricaoObrigatoriaDAO restricaoObrigatoriaDAO = new RestricaoObrigatoriaDAO();

	
	public void createRestricaoObrigatoria(RestricaoObrigatoria restricaoObrigatoria) {
		restricaoObrigatoriaDAO.beginTransaction();
		restricaoObrigatoriaDAO.save(restricaoObrigatoria);
		restricaoObrigatoriaDAO.commitAndCloseTransaction();
	}

	public void updateRestricaoObrigatoria(RestricaoObrigatoria restricaoObrigatoria) {
		restricaoObrigatoriaDAO.beginTransaction();
		RestricaoObrigatoria persistedRestricaoObrigatoria = restricaoObrigatoriaDAO.find(restricaoObrigatoria.getId());
		//persistedRestricaoObrigatoria.setNome(restricaoObrigatoria.getNome());
		restricaoObrigatoriaDAO.update(persistedRestricaoObrigatoria);
		restricaoObrigatoriaDAO.commitAndCloseTransaction();
	}

	public RestricaoObrigatoria findRestricaoObrigatoria(long restricaoObrigatoriaId) {
		restricaoObrigatoriaDAO.beginTransaction();
		RestricaoObrigatoria restricaoObrigatoria = restricaoObrigatoriaDAO.find(restricaoObrigatoriaId);
		restricaoObrigatoriaDAO.closeTransaction();
		return restricaoObrigatoria;
	}

	public List<RestricaoObrigatoria> listAll() {
		restricaoObrigatoriaDAO.beginTransaction();
		List<RestricaoObrigatoria> result = (List<RestricaoObrigatoria>) restricaoObrigatoriaDAO.findAll();
		restricaoObrigatoriaDAO.closeTransaction();
		return result;
	}
	
	public List<RestricaoObrigatoria> retornaRestricaoPorFuncionario(Funcionario funcionario, String diaSemana){
		restricaoObrigatoriaDAO.beginTransaction();
		List<RestricaoObrigatoria> result =  restricaoObrigatoriaDAO.retornaRestricaoObrigatoriaPorFuncionario(funcionario, diaSemana);
		restricaoObrigatoriaDAO.closeTransaction();
		return result;
		
	}

	public void deleteRestricaoObrigatoria(RestricaoObrigatoria restricaoObrigatoria) {
		restricaoObrigatoriaDAO.beginTransaction();
		RestricaoObrigatoria persistedRestricaoObrigatoria = restricaoObrigatoriaDAO.findReferenceOnly(restricaoObrigatoria.getId());
		restricaoObrigatoriaDAO.delete(persistedRestricaoObrigatoria);
		restricaoObrigatoriaDAO.commitAndCloseTransaction();
	}
}