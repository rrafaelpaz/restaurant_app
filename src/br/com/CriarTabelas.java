package br.com;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.outback.bean.Usuario;

public class CriarTabelas {

	public static void main(String[] args) {
		
		//http://stackoverflow.com/questions/12348170/what-is-wrong-with-my-config-severe-ioexception-while-loading-persisted-sessio
		
		//http://www.guj.com.br/8967-converter-para-selectonemenu-do-primefaces
		
		//String valueOfWell = String.valueOf(event.getComponent().getAttributes().get("value"));
		
		
		/*//* percorre cada um dos filhos 
		for(UIComponent children : tabViewEscala.getChildren()){
			
			if(children instanceof Tab) {
                
				Tab tab = (Tab)children;
				
				//* sempre habilita todas as abas, pois alguma pode estar desabilitados em uma pesquisa anterior 
			    tab.setDisabled(false);
				
				tab.setTitle("Entrou no for");
             }
		}*/
		
		
		//http://www.guj.com.br/6914-alinhamento-de-componentes-em-jsf-e-primefaces
		//login centralizado
		
		/*
		 * http://jgap.sourceforge.net/doc/tutorial.html#step1
		 * http://jcraane.blogspot.com.br/2009/02/introduction-to-genetic-algorithms-with.html
		 * http://www.demiurgo.com.br/blog/2009/06/14/algoritmos-geneticos-com-selecao-roulette-wheel/
		 * http://tisimples.wordpress.com/2009/08/13/algoritmos-geneticos-um-resumo-e-um-exemplo/
		 * http://www.codeproject.com/Articles/23111/Making-a-Class-Schedule-Using-a-Genetic-Algorithm
		 * */
		
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("OUTBACK");
	    EntityManager em = factory.createEntityManager();
		
		Usuario u = new Usuario();
		
		u.setNome("rafael");
		u.setSenha("123456");
		u.setIsFuncionario(false);
		
	    
		em.getTransaction().begin();

		em.persist(u);

		em.getTransaction().commit();
		
		
		
		
	}
	
}