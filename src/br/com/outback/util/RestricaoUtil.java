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
	
	//* Pra tornar a geração da escala mais rápida, se alguma restrição não estiver de acordo, já retorna false
	public static boolean funcionarioPodeTrabalhar(Funcionario funcionario, Calendar dataPlantao){
		
		//* Verifica se o funcionário já foi escalado para algum turno do dia
		boolean funcionarioJaEscaladoDia = RestricaoTrabalhista.funcionarioJaEscaladoParaDia(funcionario, dataPlantao);
		
		if(funcionarioJaEscaladoDia){
			return false;
		}
		
		//* Verficia se o funcionário já folgou o mínimo de 12 horas desde o último turno trabalhado
		boolean funcionarioJaFolgou12Horas = RestricaoTrabalhista.funcionarioJaFolgou12Horas(funcionario, dataPlantao);
				
		if(!funcionarioJaFolgou12Horas){
			return false;
		}
		
		//* Veririca a restrição de dias de trabalhos seguidos
		boolean funcionarioTrabalhouSeisDiasSeguidos = RestricaoTrabalhista.funcionarioTrabalhouSeisDiasSeguidos(funcionario, dataPlantao);
		
		if(funcionarioTrabalhouSeisDiasSeguidos){
			return false;
		}
		
		//* Verifica se o funcionário já folgou em algum domingo do mês
		boolean funcionarioJaFolgouDomingo = RestricaoTrabalhista.funcionarioJaTrabalhouDomingoMes(funcionario, dataPlantao);
		
		if(funcionarioJaFolgouDomingo){
			return false;
		}
		
		return true;
		
	}
	
	public static boolean funcionarioTemRestricaoObrigatoria(Funcionario funcionario, Calendar dataPlantao, Date inicio, Date fim){
		
		RestricaoObrigatoriaFacade restricaoFacade = new RestricaoObrigatoriaFacade();
		
		//* Pega do dia do plantão o dia da semana do mesmo
		String diaSemanaPlantao = DataUtil.retornaDiaSemana(dataPlantao.get(Calendar.DAY_OF_WEEK));
		
		//* faz uma busca no banco e verifica se existe alguma restrição obrigatória para aquele dia da semana
		List<RestricaoObrigatoria> restricoes = restricaoFacade.retornaRestricaoPorFuncionario(funcionario, diaSemanaPlantao);
		
		//* se houve, então continua...
		if(restricoes != null && !restricoes.isEmpty()){
			
			//* percorre, uma lista, pois o usuário pode ter mais de 1 restrição ao dia...
			for(RestricaoObrigatoria rb : restricoes){
				
				//* Coloca os horarios da restrição no mesmo dia do turno
				Calendar restricaoInicio =  DataUtil.retornaDataTurnoComHora(dataPlantao, rb.getInicio());
				Calendar restricaoFim =  DataUtil.retornaDataTurnoComHora(dataPlantao, rb.getFim());
				
				
				//* mescla o horario com o dia
				Calendar turnoInicio  =  DataUtil.retornaDataTurnoComHora(dataPlantao, inicio);
				Calendar turnoFim =  DataUtil.retornaDataTurnoComHora(dataPlantao, fim);
				
				//* verifica se o funcionário tem restrições de trabalho naquele horário de turno
				boolean temRestricao = HoraUtil.funcionarioTemPreferenciaOuRestricao(restricaoInicio, restricaoFim, turnoInicio, turnoFim);
			
				//* se tiver, já retorna true pra não ficar dando loops desnecessários
				if(temRestricao){
					return true;
				}
				
			}	
			
		}
		
		//* retorna false, se não houver restrições
		return false;
	}
	
	
	public static boolean funcionarioTemPreferecia(Funcionario funcionario, Calendar dataPlantao, Date inicio, Date fim){
		
		PreferenciaFacade preferenciaFacade = new PreferenciaFacade();
		
		
		//* faz uma busca no banco e verifica se existe alguma preferência para aquela data
		List<Preferencia> preferencias = preferenciaFacade.findPreferenciasFuncionario(funcionario, dataPlantao);
		
		//* se houve, então continua...
		if(preferencias != null && !preferencias.isEmpty()){
			
			//* percorre, uma lista, pois o usuário pode ter mais de 1 restrição ao dia...
			for(Preferencia p : preferencias){
				
				//* Coloca os horarios da restrição no mesmo dia do turno
				Calendar preferenciaInicio =  DataUtil.retornaDataTurnoComHora(dataPlantao, p.getInicio());
				Calendar prefenciaFim =  DataUtil.retornaDataTurnoComHora(dataPlantao, p.getFim());
				
				
				//* mescla o horario com o dia
				Calendar turnoInicio  =  DataUtil.retornaDataTurnoComHora(dataPlantao, inicio);
				Calendar turnoFim =  DataUtil.retornaDataTurnoComHora(dataPlantao, fim);
					
				//* verifica se o funcionário tem restrições de trabalho naquele horário de turno
				boolean temPreferencia = HoraUtil.funcionarioTemPreferenciaOuRestricao(preferenciaInicio, prefenciaFim, turnoInicio, turnoFim);
			
				//* se tiver, já retorna true pra não ficar dando loops desnecessários
				if(temPreferencia){
					//* aqui ver melhor as penalidades etc....
					return true;
				}
				
			}	
			
		}
		
		//* retorna false, se não houver restrições
		return false;
	}
	
}
