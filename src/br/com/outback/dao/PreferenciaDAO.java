package br.com.outback.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import br.com.outback.bean.Funcionario;
import br.com.outback.bean.Preferencia;

public class PreferenciaDAO  extends GenericDAO<Preferencia>{
	
	private static final long serialVersionUID = 1L;
	private EntityManager em;
	private Query query;
	private static final String CONSULTA_LOGIN = "Preferencia.findPreferenciaFuncionario";
	
	
	public PreferenciaDAO() {
		super(Preferencia.class);
	}

	public void delete(Preferencia preferencia) {
		super.delete(preferencia.getId(), Preferencia.class);
	}
	
	public void insert(Preferencia preferencia){
		
		super.save(preferencia);
	}
	
	@SuppressWarnings("unchecked")
	  public List<Preferencia> retornaPreferenciaFuncionario(Funcionario funcionario, Calendar data){
			
			 query = super.getQuery();
	  		 em = super.getEm();
	  		 	  		 
		     query = em.createNamedQuery(CONSULTA_LOGIN);
		     query.setParameter("funcionario", funcionario);
		     query.setParameter("data", data, TemporalType.DATE);//* assim n‹o consulta a data com timestamp
		        
		     query.setHint("org.hibernate.cacheable", true);
		        
		     //Object resultado = query.getSingleResult();
		        
		     if(!query.getResultList().isEmpty()){
		        	return query.getResultList();
		     }else{
		        	return null;
		     }
			
		}
	

}
