package br.com.outback.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class GenericDAO<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OUTBACK");
	private EntityManager em;
	private Query query;
	
	private Class<T> entityClass;
	
	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void beginTransaction() {
		em = emf.createEntityManager();

		em.getTransaction().begin();
	}

	public void commit() {
		em.getTransaction().commit();
	}

	public void rollback() {
		em.getTransaction().rollback();
	}

	public void closeTransaction() {
		em.close();
	}

	public void commitAndCloseTransaction() {
		commit();
		closeTransaction();
	}

	public void flush() {
		em.flush();
	}

	public void joinTransaction() {
		em = emf.createEntityManager();
		em.joinTransaction();
	}

	public void save(T entity) {
		em.persist(entity);
	}

	public void delete(Object id, Class<T> classe) {
		//* lazy
		T entityToBeRemoved = em.getReference(classe, id);
	    em.remove(entityToBeRemoved);
	}

	public T update(T entity) {
		return em.merge(entity);
	}

	public T find(long entityID) {
		return em.find(entityClass, entityID);
	}

	public T findReferenceOnly(long entityID) {
		return em.getReference(entityClass, entityID);
	}

	// Using the unchecked because JPA does not have a
	// em.getCriteriaBuilder().createQuery()<T> method
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}
	
	 public Object buscaGenerica(String consulta, Map<String, String> parametros) {

	        query = em.createNamedQuery(consulta);
	        
	        for (Map.Entry<String, String> obj : parametros.entrySet()) {
	    		//* passa a key como coluna, e value como o valor da coluna
	    		query.setParameter(obj.getKey(), obj.getValue());
	 	    }
	        
	        // query.setParameter("id", identificador);
	       
	        query.setHint("org.hibernate.cacheable", true);
	        
	        //Object resultado = query.getSingleResult();
	        
	        if(!query.getResultList().isEmpty()){
	        	return query.getResultList().get(0);
	        }else{
	        	return null;
	        } 
	 }

	 
	 @SuppressWarnings("unchecked")
	public List<Object> buscaGenericaLista(String consulta, Map<String, String> parametros) {

	        query = em.createNamedQuery(consulta);
	        
	        for (Map.Entry<String, String> obj : parametros.entrySet()) {
	    		//* passa a key como coluna, e value como o valor da coluna
	    		query.setParameter(obj.getKey(), obj.getValue());
	 	    }
	        
	        // query.setParameter("id", identificador);
	       
	        query.setHint("org.hibernate.cacheable", true);
	        
	        //Object resultado = query.getSingleResult();
	        
	        if(!query.getResultList().isEmpty()){
	        	return query.getResultList();
	        }else{
	        	return null;
	        } 
	 }

	//* pegar os atributos aqui para conexao nos respectivos DAO's..
	public EntityManager getEm() {
		return em;
	}
	
	public Query getQuery() {
		return query;
	}
	 
	 
}
