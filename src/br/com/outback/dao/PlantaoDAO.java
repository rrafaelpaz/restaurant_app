package br.com.outback.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.outback.bean.Plantao;


public class PlantaoDAO  extends GenericDAO<Plantao>{
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager em;
	private Query query;
	
	
	private static final String CONSULTA_LOGIN = "Plantao.findPlantoesPorData";
	
	public PlantaoDAO() {
		super(Plantao.class);
	}

	public void delete(Plantao Plantao) {
		super.delete(Plantao.getId(), Plantao.class);
	}
	
	public void insert(Plantao Plantao){
		
		super.save(Plantao);
	}
	
  @SuppressWarnings("unchecked")
  public List<Plantao> retornaPlantoes(Calendar dataInicio, Calendar dataFim){
		
		 query = super.getQuery();
  		 em = super.getEm();
  		 
	     query = em.createNamedQuery(CONSULTA_LOGIN);
	        
	     query.setParameter("dataInicio", dataInicio);
	     query.setParameter("dataFim", dataFim);
	       
	     query.setHint("org.hibernate.cacheable", true);
	        
	     if(!query.getResultList().isEmpty()){
	        	return query.getResultList();
	     }else{
	        	return null;
	     }
		
	}
	

}
