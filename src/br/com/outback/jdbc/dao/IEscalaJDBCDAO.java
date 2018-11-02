package br.com.outback.jdbc.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.outback.bean.Funcionario;
import br.com.outback.dto.EscalaFuncDTO;
import br.com.outback.dto.RelEscalaFuncDTO;

public interface IEscalaJDBCDAO {

	
	public EscalaFuncDTO retornaEscalaSemalFuncionario(Funcionario funcionario, Date dataInicio, Date dataFim);
	
	public List<RelEscalaFuncDTO> retornaRelEscalaFuncionario(Funcionario funcionario, Date dataInicio, Date dataFim);
	
	public int retornaNumDiasTrabalhosSeguidos(Funcionario funcionario, Date dataInicio, Date dataFim);
	
	public int returnaNumTurnosFuncionarioNoDia(Funcionario funcionario, Date data);
	
	public Calendar retornaUltimaDataEHoraTrabalhadoPeloFuncionario(Funcionario funcionario);
}
