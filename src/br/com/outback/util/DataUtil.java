package br.com.outback.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DataUtil {
	
	private static String[] diasSemanaArray = new String[]{"Segunda","Terça","Quarta","Quinta","Sexta","Sábado","Domingo"};
	
	
	  public static String retornaDiaSemana(int _diaSemana){  
		
		String diaSemana = null;  
	  
	    switch (_diaSemana)  {  
	  
		    case 1:{  
		      diaSemana = "Domingo";  
		      break;  
		    }  
		    case 2:{  
		      diaSemana = "Segunda";  
		      break;  
		    }  
		    case 3:{  
		      diaSemana = "Terça";  
		      break;  
		    }  
		    case 4:{  
		      diaSemana = "Quarta";  
		      break;  
		    }  
		    case 5:{  
		      diaSemana = "Quinta";  
		      break;  
		    }  
		    case 6:{  
		      diaSemana = "Sexta";  
		      break;  
		    }  
		    case 7:{  
		      diaSemana = "Sábado";  
		      break;  
		    }  
	  
	    } 
	    
	    return diaSemana;  
	  
	 }  
	  
	//* retorna em String a data inicio e final da escala na semana
	 public static String retornaInformacaoDataEscala(Date date){
		 	
		 	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		  
		    Date primeiro = DataUtil.retornaPrimeiroUltimoSemana(date, true);  
	        Date ultimo = DataUtil.retornaPrimeiroUltimoSemana(date, false);  
	        
	        String informacaoEscalaSemana = "Escala de trabalho do dia ".concat(sdf.format(primeiro).concat(" até ").concat(sdf.format(ultimo)));
	        
	        return informacaoEscalaSemana;
		
	 } 
	  
	 //* retorna o primeiro ou ultimo dia da semana, baseado na data passada pelo parametro
	 public static Date retornaPrimeiroUltimoSemana(Date data, boolean isPrimeiro){
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(data);
			 
			if(isPrimeiro){
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				calendar.set(Calendar.AM_PM, Calendar.AM);
				calendar.set(Calendar.HOUR, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
			}
			else{
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				calendar.set(Calendar.AM_PM, Calendar.PM);
				calendar.set(Calendar.HOUR, 11);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
			}
			 
			return calendar.getTime();
		}
	  
	  //* passa um int representando um dia da semana e retorna a nova data com dia da semana
	  public static Calendar recuperaPrimeioDiaSemana(Calendar data){
		  
		  //* pega o primeiro dia da semana baseado no dia escolhido pelo usuario
		  int diaSemana = data.get(Calendar.DAY_OF_WEEK);  
		  data.add (Calendar.DAY_OF_MONTH, Calendar.MONDAY - diaSemana); 
		  
		  return data; 
	  }
	  
	 
	public static List<Calendar> retornaDiasSelecionadosUsuariosParaSemana(int ano, int mes, int dia, String[] diasSelecionado){
		  
		  int cont = 0;
		  
		  List<Calendar> datas = new ArrayList<Calendar>();
		  
		  //* faz um loop no array contendo todos os dias da semana
		  for(String ds : diasSemanaArray){
			  
			  //* cria uma nova data com os valores da primeira dia da semana de domingo
			  Calendar novaData = Calendar.getInstance();
			  novaData.set(Calendar.YEAR, ano);
			  novaData.set(Calendar.MONTH, mes);
			  novaData.set(Calendar.DAY_OF_MONTH, dia);
			  //* a medida que vai passando pelo loop, incrementa para nao perder nenhum dia da semana
			  novaData.add(Calendar.DAY_OF_MONTH, + cont);  
			  
			   for(String dsSelecionado : diasSelecionado){
				   //* caso o dia da semana bata com o escolhido pelo usuario, adiciona na lista
				   if(ds.equals(dsSelecionado)){
					   datas.add(novaData);
				   }
			   }
			  
			   //* incrementa o contador, para pode criar os demais dias da semana
			   cont = cont + 1;
		  }
		  
		  return datas;
		  
	  } 
	
	
	//* recupera todos os dias do calendario de turnos/plantao
	public static List<Calendar> retornaDiasSelecionadosUsuariosParaMes(Calendar data, String[] diasSelecionado){
		 
		 
		 List<Calendar> datas = new ArrayList<Calendar>();
	     
		//* a princípio pega o primeiro e último dia do mês do plantão
		Calendar datasInicioFimMes[] = DataUtil.retornaDatasPrimeiroEUtimoDiaMes(data);
			
		Calendar calDataInicio = datasInicioFimMes[0];
		Calendar calDataFim = datasInicioFimMes[1];
			
		//* faz um loop na data início da consulta, onde ira trazer a cada loop cada primeiro e ultimo dia daquela semana. Só irá parar quando a dataInicio
		//* incrementar até a dataFim
		do{
		    
			//* retorna o primeiro dia da semana daquela data
		    Date primeiroDataSemana = DataUtil.retornaPrimeiroUltimoSemana(calDataInicio.getTime(), true);  
		    
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(primeiroDataSemana);
		      
		    
		    //* desmembra o ano, mes e dia para criar novas datas de dia de semana
			int ano = cal.get(Calendar.YEAR);
			int mes = cal.get(Calendar.MONTH);
			int dia = cal.get(Calendar.DAY_OF_MONTH);
			
			//* contador 
			int cont = 0;
			  
			  //* faz um loop no array contendo todos os dias da semana
			  for(String ds : diasSemanaArray){
				  
				  //* cria uma nova data com os valores da primeira dia da semana de domingo
				  Calendar novaData = Calendar.getInstance();
				  novaData.set(Calendar.YEAR, ano);
				  novaData.set(Calendar.MONTH, mes);
				  novaData.set(Calendar.DAY_OF_MONTH, dia);
				  //* a medida que vai passando pelo loop, incrementa para nao perder nenhum dia da semana
				  novaData.add(Calendar.DAY_OF_MONTH, + cont);  
				  
				  //* para aquela semana, faz um loop para encontrar os dias em Calendars dos dias em Semanas
				   for(String dsSelecionado : diasSelecionado){
					   //* caso o dia da semana bata com o escolhido pelo usuario, adiciona na lista
					   if(ds.equals(dsSelecionado)){
						   datas.add(novaData);
					   }
				   }
				  
				   //* incrementa o contador, para pode criar os demais dias da semana
				   cont = cont + 1;
			  }
		
			  //* pra não fazer loop desnecessários, a cada loop semanal adiciona 7 dia pra pular pra próxima semana
			 calDataInicio.add(Calendar.DAY_OF_MONTH, 7);
			  
			
		 }while(calDataInicio.before(calDataFim));  
		  
		  
		return datas;
			
	}
	
	public static Calendar[] retornaDatasPrimeiroEUtimoDiaMes(Calendar data) {

		//* Cria datas de inicio e fim do mes atual
		Calendar dataInicial = Calendar.getInstance();
		Calendar dataFinal = Calendar.getInstance();
		
		dataInicial.setTime(data.getTime());
		dataFinal.setTime(data.getTime());
		
		//* Cria uma data com o primeiro dia do mes
		dataInicial.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		//* Cria uma data com o ultimo dia do mes
		dataFinal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
		
		//* feito o código abaixo, pois nas consultas dos plantoes por "between" as vezes não trazia os turnos por causa
		//* do timestamp
		dataInicial.set(Calendar.AM_PM, Calendar.AM);
		dataInicial.set(Calendar.HOUR, 0);
		dataInicial.set(Calendar.MINUTE, 0);
		dataInicial.set(Calendar.SECOND, 0);
	
		dataFinal.set(Calendar.AM_PM, Calendar.PM);
		dataFinal.set(Calendar.HOUR, 11);
		dataFinal.set(Calendar.MINUTE, 59);
		dataFinal.set(Calendar.SECOND, 59);

		return new Calendar[] {dataInicial, dataFinal};
	}
	
	public static Calendar[] retornaDatasPrimeiroEUtimoDiaMesCorrente() {

		//* Cria datas de inicio e fim do mes atual
		Calendar dataInicial = Calendar.getInstance();
		Calendar dataFinal = Calendar.getInstance();
		
		//* Cria uma data com o primeiro dia do mes
		dataInicial.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		//* Cria uma data com o ultimo dia do mes
		dataFinal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
		
		//* feito o código abaixo, pois nas consultas dos plantoes por "between" as vezes não trazia os turnos por causa
		//* do timestamp
		dataInicial.set(Calendar.AM_PM, Calendar.AM);
		dataInicial.set(Calendar.HOUR, 0);
		dataInicial.set(Calendar.MINUTE, 0);
		dataInicial.set(Calendar.SECOND, 0);
	
		dataFinal.set(Calendar.AM_PM, Calendar.PM);
		dataFinal.set(Calendar.HOUR, 11);
		dataFinal.set(Calendar.MINUTE, 59);
		dataFinal.set(Calendar.SECOND, 59);

		return new Calendar[] {dataInicial, dataFinal};
	}
	
	public static Calendar[] retornaDatasConsultaMesCalendario(int ano, int mes) {

		//* Cria datas de inicio e fim do mes atual
		Calendar dataInicial = Calendar.getInstance();
		Calendar dataFinal = Calendar.getInstance();
		
		//* Cria nova data com mes e ano informados
		dataInicial.set(Calendar.YEAR, ano);
		dataInicial.set(Calendar.MONTH, mes);
		
		dataFinal.set(Calendar.YEAR, ano);
		dataFinal.set(Calendar.MONTH, mes);
		
		//* Cria uma data com o primeiro dia do mes
		dataInicial.set(Calendar.DAY_OF_MONTH,dataInicial.getActualMinimum(Calendar.DAY_OF_MONTH));
		//* Cria uma data com o ultimo dia do mes
		dataFinal.set(Calendar.DAY_OF_MONTH, dataFinal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		//* Zera os milisegundos etc.. por causa da consulta
		dataInicial.set(Calendar.HOUR, 0);
		dataInicial.set(Calendar.MINUTE, 0);
		dataInicial.set(Calendar.SECOND, 0);
		dataInicial.set(Calendar.MILLISECOND, 0);
		dataInicial.set(Calendar.HOUR_OF_DAY, 0);
				
		dataFinal.set(Calendar.HOUR, 0);
		dataFinal.set(Calendar.MINUTE, 0);
		dataFinal.set(Calendar.SECOND, 0);
		dataFinal.set(Calendar.MILLISECOND, 0);
		dataFinal.set(Calendar.HOUR_OF_DAY, 0);

		return new Calendar[] {dataInicial, dataFinal};
	}
	
	//* Recupera o ultimo domingo do mês
	public static Calendar retornaUltimoDomingoDoMes(Calendar data){
		
		Calendar calUltimoDomingoMes = Calendar.getInstance();
		
		calUltimoDomingoMes.setTime(data.getTime());
		
		calUltimoDomingoMes.set(GregorianCalendar.DAY_OF_WEEK,Calendar.SUNDAY);
		calUltimoDomingoMes.set(GregorianCalendar.DAY_OF_WEEK_IN_MONTH, -1);
		
		return calUltimoDomingoMes;
	}
	
	//* Recupera todos os domingos do mês
	public static List<Calendar> retornaTodosDomingosDoMes(Calendar data){
		
		List<Calendar> domingos = new ArrayList<Calendar>();
		
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(data.getTime());
	     cal.set(Calendar.DAY_OF_MONTH, 1);     
		 
		 // put the month you want
	     int month = cal.get(Calendar.MONTH);

	     do {
	        
	    	 int day = cal.get(Calendar.DAY_OF_WEEK);
	       
	        if (day == Calendar.SUNDAY) {
	        	
	        	Calendar novoDomingo = Calendar.getInstance();
	            novoDomingo.setTime(cal.getTime());
	        	domingos.add(novoDomingo);
	         }
	     
	        cal.add(Calendar.DAY_OF_YEAR, 1);
	    
	     }  while (cal.get(Calendar.MONTH) == month);
		
	    return domingos;
	}
	
	public static long calcularDiferencaDias(Calendar data1, Calendar data2) {

			//usar Joda aqui
		
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        String data1Formatada = sdf.format(data1.getTime());
	        String data2Formatada = sdf.format(data2.getTime());

	        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        df.setLenient(false);

	        java.util.Date d1 = null;
	        java.util.Date d2 = null;
	        
	        try {
				d1 = df.parse(data1Formatada);
			    d2 = df.parse(data2Formatada);
				       
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	        long dt = (d2.getTime() - d1.getTime()) + 3600000; // 1 hora para compensar horário de verão  
	        long numeroDias = dt / 86400000L; // passaram-se 67111 dias  

	        return numeroDias;
	 }
	
	//* criar uma nova data com o dia do calendar com o horario do date
	public static Calendar retornaDataTurnoComHora(Calendar cal, Date data){
		
		//* Cria uma nova data
		Calendar dataRetorno = Calendar.getInstance();
		
		//* Joga o Date que contém a hora 
		dataRetorno.setTime(data);
		
		//* Joga co Calendar que contém o dia, ano e mês
		dataRetorno.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		dataRetorno.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		dataRetorno.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
     	
		return dataRetorno;
		
		
	}
	
	

}
