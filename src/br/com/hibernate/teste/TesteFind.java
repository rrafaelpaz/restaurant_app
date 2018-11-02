package br.com.hibernate.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteFind {
	
	public static void main(String args[]){
		
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("OUTBACK");
		EntityManager em =  factory.createEntityManager();
		
		//* objeto no estado managed
		Pessoa p = em.find(Pessoa.class, 1L);
		
		System.out.println("id: "+ p.getId());
		System.out.println("nome: "+ p.getName());
		
		em.close();
		factory.close();
		
	}

}
