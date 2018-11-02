package selecao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
	
public static int retornaDiaSemanaEmNumero(String diaSemana){
	
	if("Segunda".equals(diaSemana)){
		
		return 1;
		
	}else if("Ter�a".equals(diaSemana)){
		
		return 2;
		
	}else if("Quarta".equals(diaSemana)){
		
		return 3;
		
	}else if("Quinta".equals(diaSemana)){
		
		
		return 4;
		
	}else if("Sexta".equals(diaSemana)){
		
		
		return 5;
		
	}else if("S�bado".equals(diaSemana)){
		
		
		return 6;
	}else{
		
		return 7;
		
	}
	
}	
	
public static boolean estaEscaladoOuFolgaDia(String nome, String diaSemana, Map<String, List<String>> mapaFuncionarioAlocadoTurnoDia){
		
		boolean estaEscalado = false;
		
		if("Segunda".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Segunda");
			
			for(String funcionario : lista){
				
				if(funcionario.equals(nome)){
					return true;
				}
			}
			
		}else if("Ter�a".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Ter�a");
			
			for(String funcionario : lista){
				
				if(funcionario.equals(nome)){
					return true;
				}
			}
			
		}else if("Quarta".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Quarta");
			
			for(String funcionario : lista){
				
				if(funcionario.equals(nome)){
					return true;
				}
			}
			
		}else if("Quinta".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Quinta");
			
			for(String funcionario : lista){
				
				if(funcionario.equals(nome)){
					return true;
				}
			}
			
		}else if("Sexta".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Sexta");
			
			for(String funcionario : lista){
				
				if(funcionario.equals(nome)){
					return true;
				}
			}
			
		}else if("S�bado".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("S�bado");
			
			for(String funcionario : lista){
				
				if(funcionario.equals(nome)){
					return true;
				}
			}
			
		}else{
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Domingo");
			
			for(String funcionario : lista){
				
				if(funcionario.equals(nome)){
					return true;
				}
			}
			
		}
		
		return estaEscalado;
	}

	public static void alimentaMapComFuncionariosAlocadoTurnoDia(String nome, String diaSemana, Map<String, List<String>> mapaFuncionarioAlocadoTurnoDia){
		
		if("Segunda".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Segunda");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("Segunda", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("Segunda", lista);
				
			}
			
		}else if("Ter�a".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Ter�a");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("Ter�a", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("Ter�a", lista);
				
			}
			
		}else if("Quarta".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Quarta");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("Quarta", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("Quarta", lista);
				
			}
			
		}else if("Quinta".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Quinta");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("Quinta", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("Quinta", lista);
				
			}
			
		}else if("Sexta".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Sexta");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("Sexta", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("Sexta", lista);
				
			}
			
		}else if("S�bado".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("S�bado");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("S�bado", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("S�bado", lista);
				
			}
			
		}else{
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Domingo");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("Domingo", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("Domingo", lista);
				
			}
			
		}
		
	}
	
	
public static void alimentaMapComFuncionariosComFolgaDia(String nome, String diaSemana, Map<String, List<String>> mapaFuncionarioAlocadoTurnoDia){
		
		if("Segunda".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Segunda");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("Segunda", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("Segunda", lista);
				
			}
			
		}else if("Ter�a".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Ter�a");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("Ter�a", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("Ter�a", lista);
				
			}
			
		}else if("Quarta".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Quarta");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("Quarta", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("Quarta", lista);
				
			}
			
		}else if("Quinta".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Quinta");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("Quinta", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("Quinta", lista);
				
			}
			
		}else if("Sexta".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Sexta");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("Sexta", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("Sexta", lista);
				
			}
			
		}else if("S�bado".equals(diaSemana)){
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("S�bado");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("S�bado", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("S�bado", lista);
				
			}
			
		}else{
			
			List<String> lista = mapaFuncionarioAlocadoTurnoDia.get("Domingo");
			
			if(lista == null){
				
				List<String> listaFuncionarioJaAlocadoTurnoDia = new ArrayList<String>();
				
				listaFuncionarioJaAlocadoTurnoDia.add(nome);
				
				mapaFuncionarioAlocadoTurnoDia.put("Domingo", listaFuncionarioJaAlocadoTurnoDia);
				
			}else{
				
				lista.add(nome);
				mapaFuncionarioAlocadoTurnoDia.put("Domingo", lista);
				
			}
			
		}
		
	}
	
	
	public static Map<String, List<String>> alimentaMapa(){
		

	    Map<String, List<String>> mapaFuncionarioAlocadoTurnoDia = new HashMap<String,List<String>>();
	    
	    mapaFuncionarioAlocadoTurnoDia.put("Segunda", new ArrayList<String>());
	    mapaFuncionarioAlocadoTurnoDia.put("Ter�a", new ArrayList<String>());
	    mapaFuncionarioAlocadoTurnoDia.put("Quarta", new ArrayList<String>());
		mapaFuncionarioAlocadoTurnoDia.put("Quinta", new ArrayList<String>());
		mapaFuncionarioAlocadoTurnoDia.put("Sexta", new ArrayList<String>());
		mapaFuncionarioAlocadoTurnoDia.put("S�bado", new ArrayList<String>());
		mapaFuncionarioAlocadoTurnoDia.put("Domingo", new ArrayList<String>());
				
		return mapaFuncionarioAlocadoTurnoDia;
		
	}

}
