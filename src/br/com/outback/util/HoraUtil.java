package br.com.outback.util;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Hours;

public class HoraUtil {
	

	public static Date retornaHoraDoDiaPlantao(Calendar diaPlantao, Date hora){
		
		//* Cria um calendar passando o horario 
		Calendar horaDoTurno = Calendar.getInstance();
		horaDoTurno.setTime(hora);
		
		//* agora passa para o calendar o ano, mes e dia, assim sendo, tem-se o dia e hora do plancao
		horaDoTurno.set(Calendar.YEAR, diaPlantao.get(Calendar.YEAR));
		horaDoTurno.set(Calendar.MONTH, diaPlantao.get(Calendar.MONTH));
		horaDoTurno.set(Calendar.DAY_OF_MONTH, diaPlantao.get(Calendar.DAY_OF_MONTH));
		
		
		return horaDoTurno.getTime();
		
	}


	public static long retornaDiferencaHorasEntreDuasDatas(Calendar data1, Calendar data2){
		
		long numHoras = 0;
	 
		try {
			
			DateTime dt1 = new DateTime(data1.getTime());
			DateTime dt2 = new DateTime(data2.getTime());
			
			
			//System.out.print(Days.daysBetween(dt1, dt2).getDays() + " days, ");
			//System.out.print(Hours.hoursBetween(dt1, dt2).getHours() % 24 + " hours, ");
			//System.out.print(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " minutes, ");
			//System.out.print(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 + " seconds.");
			
			numHoras = Hours.hoursBetween(dt1, dt2).getHours() % 24 ;
	 
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		
		return numHoras;
		
	}
	
	//* Converte objetos Date para Date.sql, para realizar consultas no Banco
	public static java.sql.Date converteDataParaSQLDate(Date data) {
		    
		return new java.sql.Date(data.getTime());
	}
	
	//* Verifica se a pessoa 
	
	public static boolean funcionarioTemPreferenciaOuRestricao(Calendar restricaoInicio,Calendar restricaoFim,
																Calendar turnoInicio,Calendar turnoFim ){
		
		if(( isMaiorOuIgual(turnoInicio,restricaoInicio)  && isMenorOuIgual(turnoInicio, restricaoFim) ) ||
				(isMaiorOuIgual(turnoFim, restricaoFim)  && isMenorOuIgual(turnoFim, restricaoFim) )){
			
			//System.out.println("n‹o pode trabalhar");
			return true;
		
		}else{
			
			if((isMaiorOuIgual(restricaoInicio, turnoInicio) && isMenorOuIgual(restricaoInicio, turnoFim)) ||
					(isMaiorOuIgual(restricaoFim, turnoInicio)  && isMenorOuIgual(restricaoFim, turnoFim))){

				//System.out.println("n‹o pode trabalhar");
				return true;
				
			}else{

				//System.out.println("pode trabalhar");
				return false;
			}
		}
	}
	
	private static boolean isMaiorOuIgual(Calendar data1, Calendar data2){
		if(data1.after(data2) || data1.equals(data2)){
			return true;
		}else{
			return false;
		}
	}
	
	private static boolean isMenorOuIgual(Calendar data1, Calendar data2){
		if(data1.before(data2) || data1.equals(data2)){
			return true;
		}else{
			return false;
		}
	}
	
}
