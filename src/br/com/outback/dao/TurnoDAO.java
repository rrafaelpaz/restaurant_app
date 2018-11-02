package br.com.outback.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.outback.bean.Turno;

public class TurnoDAO  extends GenericDAO<Turno>{
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager em;
	private Query query;
	
	
	private static final String CONSULTA_LOGIN = "Turno.findTurnosAtivos";
	
	public TurnoDAO() {
		super(Turno.class);
	}

	public void delete(Turno turno) {
		super.delete(turno.getId(), Turno.class);
	}
	
	public void insert(Turno turno){
		
		super.save(turno);
	}
	
  @SuppressWarnings("unchecked")
  public List<Turno> retornaTurnosAtivos(){
		
		 query = super.getQuery();
  		 em = super.getEm();
  		 
	     query = em.createNamedQuery(CONSULTA_LOGIN);
	        
	     query.setHint("org.hibernate.cacheable", true);
	        
	     //Object resultado = query.getSingleResult();
	        
	     if(!query.getResultList().isEmpty()){
	        	return query.getResultList();
	     }else{
	        	return null;
	     }
		
	}
  

	

}
