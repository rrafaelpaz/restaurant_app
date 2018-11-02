package br.com.outback.restricao;

import java.util.Calendar;
import java.util.List;

import br.com.outback.bean.Funcionario;
import br.com.outback.jdbc.dao.EscalaJDBCDAO;
import br.com.outback.jdbc.dao.IEscalaJDBCDAO;
import br.com.outback.util.DataUtil;
import br.com.outback.util.HoraUtil;

public class RestricaoTrabalhista {
	
	//* nœmero m‡ximo de dias trabalhados seguido Ž de 6 dias
	private static int MAX_DIAS_TRABALHADOS_SEGUIDOS = 6;
	
	
	
	public static boolean funcionarioTrabalhouSeisDiasSeguidos(Funcionario funcionario, Calendar dataPlantao){
		
		IEscalaJDBCDAO escalaJDBC = new EscalaJDBCDAO();
		
		//* Cria uma data retroativa de seis dia baseado na data do plant‹o
		Calendar dataRetroativa = Calendar.getInstance();
		dataRetroativa.setTime(dataPlantao.getTime());
		dataRetroativa.add(Calendar.DAY_OF_MONTH, -6);//* "set" neste caso diminuia a data erroneamente 
		
		//* busca o numero de dias trabalhos entre as datas
		int numDiasTrabalhos = escalaJDBC.retornaNumDiasTrabalhosSeguidos(funcionario, dataRetroativa.getTime(), dataPlantao.getTime());
	
		
		//* se o resultado for menor que o permitido, ent‹o retorna false
		if(numDiasTrabalhos < MAX_DIAS_TRABALHADOS_SEGUIDOS){
			return false;
		}else{
			return true;
		}
		
	}
	
	//* um funcion‡rio s— pode trabalhar 1 turno por dia
	public static boolean funcionarioJaEscaladoParaDia(Funcionario funcionario, Calendar dataPlantao){
		
		IEscalaJDBCDAO escalaJDBC = new EscalaJDBCDAO();
		
		//* verifica se o funcion‡rio j‡ foi escalado para algum turno do dia (traz em num de turnos a resposta)
		int numTurnosDia = escalaJDBC.returnaNumTurnosFuncionarioNoDia(funcionario, dataPlantao.getTime());
		
		if(numTurnosDia == 0){
			return false;
		}else{
			return true;
		}
		
	}
	
	//* o funcion‡rio tem direito ao m’nimo de 1 domingo de falga no ms
	public static boolean funcionarioJaTrabalhouDomingoMes(Funcionario funcionario, Calendar dataPlantao){
		
		//* Pega o dia da semana da data do plant‹o
		String diaSemana = DataUtil.retornaDiaSemana(dataPlantao.get(Calendar.DAY_OF_WEEK));
		
		//* Se for domingo, faz valida›es
		if("Domingo".equals(diaSemana)){
			
			//* Recupera o œltimo dia do ms do ms do plant‹o
			Calendar ultimoDiaMes = DataUtil.retornaUltimoDomingoDoMes(dataPlantao);
			
			//* Se a data do plant‹o for igual ao œltimo dia do domingo do ms, tem que validar se o funcion‡rio j‡ trabalhou
			//* em algum domingo do mesmo ms
			if(ultimoDiaMes.equals(dataPlantao)){
				
				//* Recupera todos os domingos do ms para validar se o funcion‡rio j‡ trabalhou em algum deles
				List<Calendar> domingos = DataUtil.retornaTodosDomingosDoMes(dataPlantao);
				
				for(Calendar domingo : domingos){
					
					IEscalaJDBCDAO escalaJDBC = new EscalaJDBCDAO();
					
					//* Traz o nœmero de dias do funcion‡rio naquele dia
					int numTurnoTrabalhados = escalaJDBC.returnaNumTurnosFuncionarioNoDia(funcionario, domingo.getTime());
					
					//* se o nœmero de turnos trabalhos no dia for maior que zero, ent‹o o funcionŒrio j‡ folgou em algum domingo
					if(numTurnoTrabalhados > 0){
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	//* o funcion‡rio precisa ter um intevalo m’nimo de 12 horas entre um turno e outro
	public static boolean funcionarioJaFolgou12Horas(Funcionario funcionario, Calendar dataPlantao){
		
		IEscalaJDBCDAO esc = new EscalaJDBCDAO();
		
		//* Recupera a ultima data trabalhada pelo funcion‡rio
		Calendar ultimaDataTrabalhada = esc.retornaUltimaDataEHoraTrabalhadoPeloFuncionario(funcionario);
		
		//* se entrar no if Ž porque o funcion‡rio ainda n‹o trabalhou em nenhum turno
		if(ultimaDataTrabalhada == null){
			return true;
		}
		
		
		long numHorasSemTrabalharFuncionario = HoraUtil.retornaDiferencaHorasEntreDuasDatas(ultimaDataTrabalhada, dataPlantao);
		
		//* Se o resultado for maior que 12 horas, entao ja folgou o minimo possivel
		if(numHorasSemTrabalharFuncionario >= 12){
			return true;
		}else{
			return false;
		}
		
	}
	

}
