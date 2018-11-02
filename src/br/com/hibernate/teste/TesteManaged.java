package br.com.hibernate.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteManaged {
	
	public static void main(String args[]){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("OUTBACK");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		//* objeto no estado manager
		Pessoa p = em.find(Pessoa.class, 1L);
		
		//* alterando o conteudo do objeto
		p.setName("Fernanda");
		
		//* sincronizando e confirmando a operacao
		em.getTransaction().commit();
		
		em.close();
		factory.close();
		
		
	}

}
