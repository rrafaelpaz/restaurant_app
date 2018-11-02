package br.com.outback.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.outback.bean.Funcionario;
import br.com.outback.bean.RestricaoObrigatoria;

public class RestricaoObrigatoriaDAO  extends GenericDAO<RestricaoObrigatoria>{
	
	private static final long serialVersionUID = 1L;
	private EntityManager em;
	private Query query;
	private static final String CONSULTA_LOGIN = "RestricaoObrigatoria.findRestricoesPorFuncionario";
	
	
	public RestricaoObrigatoriaDAO() {
		super(RestricaoObrigatoria.class);
	}

	public void delete(RestricaoObrigatoria restricaoObrigatoria) {
		super.delete(restricaoObrigatoria.getId(), RestricaoObrigatoria.class);
	}
	
	public void insert(RestricaoObrigatoria restricaoObrigatoria){
		
		super.save(restricaoObrigatoria);
	}
	
	@SuppressWarnings("unchecked")
	  public List<RestricaoObrigatoria> retornaRestricaoObrigatoriaPorFuncionario(Funcionario funcionario, String diaSemana){
			
			 query = super.getQuery();
	  		 em = super.getEm();
	  		 
		     query = em.createNamedQuery(CONSULTA_LOGIN);
		     query.setParameter("funcionario", funcionario);
		     query.setParameter("diaSemana",diaSemana);
		        
		     query.setHint("org.hibernate.cacheable", true);
		        
		     //Object resultado = query.getSingleResult();
		        
		     if(!query.getResultList().isEmpty()){
		        	return query.getResultList();
		     }else{
		        	return null;
		     }
			
		}
	

}
