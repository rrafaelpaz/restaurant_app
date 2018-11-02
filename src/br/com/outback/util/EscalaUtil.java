package br.com.outback.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.outback.dto.EscalaFuncDTO;
import br.com.outback.dto.RelEscalaFuncDTO;

public class EscalaUtil {
	
	public static  void preencheEscalaFuncionario(EscalaFuncDTO escala, Date dataTurno, Timestamp horaAbertura,Timestamp horaFechamento,String cor){  
		
		Calendar dataTurnoCalendar = Calendar.getInstance();
        dataTurnoCalendar.setTime(dataTurno);
        
        int diaDaSemana = dataTurnoCalendar.get(Calendar.DAY_OF_WEEK);
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        String horaAberturaStr = sdf.format(horaAbertura);
        String horaFechamentoStr = sdf.format(horaFechamento);
		  
		  switch (diaDaSemana)  {  
		  
			    case 1:{  
			      // "DOMINGO"; 
			      escala.setHorarioDom(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
			      escala.setCorTurnoDom(cor);
			      break;  
			    }  
			    case 2:{  
			      // "SEGUNDA";  
			      escala.setHorarioSeg(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
			      escala.setCorTurnoSeg(cor);
			      break;  
			    }  
			    case 3:{  
			      //"TERÇA";
			      escala.setHorarioTer(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
				  escala.setCorTurnoTer(cor);
			      break;  
			    }  
			    case 4:{  
			      //"QUARTA";  
			    	escala.setHorarioQua(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
				    escala.setCorTurnoQua(cor);
			      break;  
			    }  
			    case 5:{  
			      //"QUINTA";
			      escala.setHorarioQui(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
			      escala.setCorTurnoQui(cor);
			      break;  
			    }  
			    case 6:{  
			      //"SEXTA";  
			      escala.setHorarioSex(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
			      escala.setCorTurnoSex(cor);
			      break;  
			    }  
			    case 7:{  
			       //"SÁBADO";  
			      escala.setHorarioSab(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
			      escala.setCorTurnoSab(cor);
			      break;  
			    }  
		  
		    } 
		    
		  
		  }
	
	
	
	public static  void preencheRelEscalaFuncionario(RelEscalaFuncDTO escala, Date dataTurno, Timestamp horaAbertura,
			Timestamp horaFechamento,Timestamp interInicio,Timestamp interFim, String cor){  
		
		Calendar dataTurnoCalendar = Calendar.getInstance();
        dataTurnoCalendar.setTime(dataTurno);
        
        int diaDaSemana = dataTurnoCalendar.get(Calendar.DAY_OF_WEEK);
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        String horaAberturaStr = sdf.format(horaAbertura);
        String horaFechamentoStr = sdf.format(horaFechamento);
        
        String interInicioStr = sdf.format(interInicio);
        String interFimStr = sdf.format(interFim);
		  
		  switch (diaDaSemana)  {  
		  
			    case 1:{  
			      // "DOMINGO"; 
			      escala.setDomingo(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
			      escala.setInterDom(interInicioStr.concat(" - ".concat(interFimStr)));
			      escala.setCorTurnoDom(cor);
			      break;  
			    }  
			    case 2:{  
			      // "SEGUNDA";  
			      escala.setSegunda(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
			      escala.setInterSeg(interInicioStr.concat(" - ".concat(interFimStr)));
			      escala.setCorTurnoSeg(cor);
			      break;  
			    }  
			    case 3:{  
			      //"TERÇA";
			      escala.setTerca(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
			      escala.setInterTer(interInicioStr.concat(" - ".concat(interFimStr)));
			      escala.setCorTurnoTer(cor);
			      break;  
			    }  
			    case 4:{  
			      //"QUARTA";  
			    	escala.setQuarta(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
			        escala.setInterQua(interInicioStr.concat(" - ".concat(interFimStr)));
			        escala.setCorTurnoQua(cor);
				  break;  
			    }  
			    case 5:{  
			      //"QUINTA";
			      escala.setQuinta(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
			      escala.setInterQui(interInicioStr.concat(" - ".concat(interFimStr)));
			      escala.setCorTurnoQui(cor);
			      break;  
			    }  
			    case 6:{  
			      //"SEXTA";  
			      escala.setSexta(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
			      escala.setInterSex(interInicioStr.concat(" - ".concat(interFimStr)));
			      escala.setCorTurnoSex(cor);
			      break;  
			    }  
			    case 7:{  
			       //"SÁBADO";  
			      escala.setSabado(horaAberturaStr.concat(" - ".concat(horaFechamentoStr)));
			      escala.setInterSab(interInicioStr.concat(" - ".concat(interFimStr)));
			      escala.setCorTurnoSab(cor);
			      break;  
			    }  
		  
		    } 
		    
		  
		  }

}
