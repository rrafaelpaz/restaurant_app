package br.com.outback.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.outback.bean.Funcionario;
import br.com.outback.bean.Preferencia;
import br.com.outback.bean.RestricaoObrigatoria;
import br.com.outback.facade.PreferenciaFacade;
import br.com.outback.facade.RestricaoObrigatoriaFacade;
import br.com.outback.restricao.RestricaoTrabalhista;

public class RestricaoUtil {
	
	//* Pra tornar a gera��o da escala mais r�pida, se alguma restri��o n�o estiver de acordo, j� retorna false
	public static boolean funcionarioPodeTrabalhar(Funcionario funcionario, Calendar dataPlantao){
		
		//* Verifica se o funcion�rio j� foi escalado para algum turno do dia
		boolean funcionarioJaEscaladoDia = RestricaoTrabalhista.funcionarioJaEscaladoParaDia(funcionario, dataPlantao);
		
		if(funcionarioJaEscaladoDia){
			return false;
		}
		
		//* Verficia se o funcion�rio j� folgou o m�nimo de 12 horas desde o �ltimo turno trabalhado
		boolean funcionarioJaFolgou12Horas = RestricaoTrabalhista.funcionarioJaFolgou12Horas(funcionario, dataPlantao);
				
		if(!funcionarioJaFolgou12Horas){
			return false;
		}
		
		//* Veririca a restri��o de dias de trabalhos seguidos
		boolean funcionarioTrabalhouSeisDiasSeguidos = RestricaoTrabalhista.funcionarioTrabalhouSeisDiasSeguidos(funcionario, dataPlantao);
		
		if(funcionarioTrabalhouSeisDiasSeguidos){
			return false;
		}
		
		//* Verifica se o funcion�rio j� folgou em algum domingo do m�s
		boolean funcionarioJaFolgouDomingo = RestricaoTrabalhista.funcionarioJaTrabalhouDomingoMes(funcionario, dataPlantao);
		
		if(funcionarioJaFolgouDomingo){
			return false;
		}
		
		return true;
		
	}
	
	public static boolean funcionarioTemRestricaoObrigatoria(Funcionario funcionario, Calendar dataPlantao, Date inicio, Date fim){
		
		RestricaoObrigatoriaFacade restricaoFacade = new RestricaoObrigatoriaFacade();
		
		//* Pega do dia do plant�o o dia da semana do mesmo
		String diaSemanaPlantao = DataUtil.retornaDiaSemana(dataPlantao.get(Calendar.DAY_OF_WEEK));
		
		//* faz uma busca no banco e verifica se existe alguma restri��o obrigat�ria para aquele dia da semana
		List<RestricaoObrigatoria> restricoes = restricaoFacade.retornaRestricaoPorFuncionario(funcionario, diaSemanaPlantao);
		
		//* se houve, ent�o continua...
		if(restricoes != null && !restricoes.isEmpty()){
			
			//* percorre, uma lista, pois o usu�rio pode ter mais de 1 restri��o ao dia...
			for(RestricaoObrigatoria rb : restricoes){
				
				//* Coloca os horarios da restri��o no mesmo dia do turno
				Calendar restricaoInicio =  DataUtil.retornaDataTurnoComHora(dataPlantao, rb.getInicio());
				Calendar restricaoFim =  DataUtil.retornaDataTurnoComHora(dataPlantao, rb.getFim());
				
				
				//* mescla o horario com o dia
				Calendar turnoInicio  =  DataUtil.retornaDataTurnoComHora(dataPlantao, inicio);
				Calendar turnoFim =  DataUtil.retornaDataTurnoComHora(dataPlantao, fim);
				
				//* verifica se o funcion�rio tem restri��es de trabalho naquele hor�rio de turno
				boolean temRestricao = HoraUtil.funcionarioTemPreferenciaOuRestricao(restricaoInicio, restricaoFim, turnoInicio, turnoFim);
			
				//* se tiver, j� retorna true pra n�o ficar dando loops desnecess�rios
				if(temRestricao){
					return true;
				}
				
			}	
			
		}
		
		//* retorna false, se n�o houver restri��es
		return false;
	}
	
	
	public static boolean funcionarioTemPreferecia(Funcionario funcionario, Calendar dataPlantao, Date inicio, Date fim){
		
		PreferenciaFacade preferenciaFacade = new PreferenciaFacade();
		
		
		//* faz uma busca no banco e verifica se existe alguma prefer�ncia para aquela data
		List<Preferencia> preferencias = preferenciaFacade.findPreferenciasFuncionario(funcionario, dataPlantao);
		
		//* se houve, ent�o continua...
		if(preferencias != null && !preferencias.isEmpty()){
			
			//* percorre, uma lista, pois o usu�rio pode ter mais de 1 restri��o ao dia...
			for(Preferencia p : preferencias){
				
				//* Coloca os horarios da restri��o no mesmo dia do turno
				Calendar preferenciaInicio =  DataUtil.retornaDataTurnoComHora(dataPlantao, p.getInicio());
				Calendar prefenciaFim =  DataUtil.retornaDataTurnoComHora(dataPlantao, p.getFim());
				
				
				//* mescla o horario com o dia
				Calendar turnoInicio  =  DataUtil.retornaDataTurnoComHora(dataPlantao, inicio);
				Calendar turnoFim =  DataUtil.retornaDataTurnoComHora(dataPlantao, fim);
					
				//* verifica se o funcion�rio tem restri��es de trabalho naquele hor�rio de turno
				boolean temPreferencia = HoraUtil.funcionarioTemPreferenciaOuRestricao(preferenciaInicio, prefenciaFim, turnoInicio, turnoFim);
			
				//* se tiver, j� retorna true pra n�o ficar dando loops desnecess�rios
				if(temPreferencia){
					//* aqui ver melhor as penalidades etc....
					return true;
				}
				
			}	
			
		}
		
		//* retorna false, se n�o houver restri��es
		return false;
	}
	
}
