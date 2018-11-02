package br.com.hibernate.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestePersist {
	
	public static void main(String args[]){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("OUTBACK");
		
		EntityManager manager = factory.createEntityManager();
		
		
		//* abrindo a transacao
		manager.getTransaction().begin();
		
		//* objeto no estao new
		Pessoa p = new Pessoa();
		p.setName("Rafael Paz");
		
		//* objeto no estado managed
		manager.persist(p);

		//* sincronizando e confirmando a transacao
		manager.getTransaction().commit();
		
		System.out.println("Pessoa id : "+ p.getId());
		
		manager.close();
		
		factory.close();
	}

}
