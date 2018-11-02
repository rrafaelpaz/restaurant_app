package br.com.outback.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.outback.bean.Funcionario;
import br.com.outback.dto.EscalaFuncDTO;
import br.com.outback.dto.RelEscalaFuncDTO;
import br.com.outback.jdbc.conexaodb.SingletonConexaoBanco;
import br.com.outback.util.DataUtil;
import br.com.outback.util.EscalaUtil;
import br.com.outback.util.HoraUtil;

public class EscalaJDBCDAO implements IEscalaJDBCDAO{
	
	private static Connection conn;

	
	@Override
	public EscalaFuncDTO retornaEscalaSemalFuncionario(Funcionario funcionario, Date dataInicio, Date dataFim) {
		
		
		EscalaFuncDTO escala = new EscalaFuncDTO();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT f.nome, t.id, p.data,t.abertura, t.fechamento, t.cor       ");
		sql.append(" FROM Turno t, Funcionario f, Plantao p, Plantao_Funcionario pf   ");
		sql.append(" 	WHERE f.id = ?												  ");  
		sql.append(" 	AND f.id = pf.funcionario_id								  ");
		sql.append(" 	AND p.id = pf.plantao_id									  "); 
		sql.append("    AND t.id = p.turno_id							   			  ");
		sql.append("    AND DATE(p.data) BETWEEN  ? AND ?							  ");
		
		
	    try {

	            conn = SingletonConexaoBanco.getInstance().getConnection();
	            
	            PreparedStatement ps = conn.prepareStatement(sql.toString());
	            
	            ps.setLong(1, funcionario.getId());
	            ps.setDate(2, HoraUtil.converteDataParaSQLDate(dataInicio));
	            ps.setDate(3, HoraUtil.converteDataParaSQLDate(dataFim));
	         
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	            	
	            	//* nome do funcionario
	            	escala.setNomeFunc(rs.getString(1));
	            	
	            	 //* data do turno
	                Date dataTurno = rs.getDate(3);
	                
	                //* abertura
	                Timestamp horaAbertura = rs.getTimestamp(4);
	                //* fechamanteo
	                Timestamp horaFechamento = rs.getTimestamp(5);
	                
	                //* cor do turno
	                String cor = rs.getString(6);
	            	
	                //* preenche o objeto lancamendo seus valores nos respectivos campos de acordo com o dia da semana
	            	EscalaUtil.preencheEscalaFuncionario(escala, dataTurno, horaAbertura, horaFechamento, cor);
	            
	            }
	            rs.close();
	            ps.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(EscalaJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
		return escala;
	}
	
	  

	@Override
	public int retornaNumDiasTrabalhosSeguidos(Funcionario funcionario, Date dataInicio, Date dataFim) {

		int numDias = 0;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT COUNT(*)  									              ");
		sql.append(" FROM Turno t, Funcionario f, Plantao p, Plantao_Funcionario pf   ");
		sql.append(" 	WHERE f.id = ?												  ");  
		sql.append(" 	AND f.id = pf.funcionario_id								  ");
		sql.append(" 	AND p.id = pf.plantao_id									  "); 
		sql.append("    AND t.id = p.turno_id							   			  ");
		sql.append("    AND DATE(p.data) BETWEEN  ? AND ?							  ");
		
		
	    try {

	            conn = SingletonConexaoBanco.getInstance().getConnection();
	            
	            PreparedStatement ps = conn.prepareStatement(sql.toString());
	            
	            ps.setLong(1, funcionario.getId());
	            ps.setDate(2, HoraUtil.converteDataParaSQLDate(dataInicio));
	            ps.setDate(3, HoraUtil.converteDataParaSQLDate(dataFim));
	         
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	            
	            	numDias = rs.getInt(1);
	            
	            }
	            rs.close();
	            ps.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(EscalaJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
		
		return numDias;
	}

	@Override
	public int returnaNumTurnosFuncionarioNoDia(Funcionario funcionario, Date data) {
		int numDias = 0;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT COUNT(*)  									              ");
		sql.append(" FROM Turno t, Funcionario f, Plantao p, Plantao_Funcionario pf   ");
		sql.append(" 	WHERE f.id = ?												  ");  
		sql.append(" 	AND f.id = pf.funcionario_id								  ");
		sql.append(" 	AND p.id = pf.plantao_id									  "); 
		sql.append("    AND t.id = p.turno_id							   			  ");
		sql.append("    AND DATE(p.data) = ?										  ");
		
		
	    try {

	            conn = SingletonConexaoBanco.getInstance().getConnection();
	            
	            PreparedStatement ps = conn.prepareStatement(sql.toString());
	            
	            ps.setLong(1, funcionario.getId());
	            ps.setDate(2, HoraUtil.converteDataParaSQLDate(data));
	         
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	            
	            	numDias = rs.getInt(1);
	            
	            }
	            rs.close();
	            ps.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(EscalaJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
		
		return numDias;
	}

	@Override
	public Calendar retornaUltimaDataEHoraTrabalhadoPeloFuncionario(Funcionario funcionario) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT  MAX(p.data),t.fechamento 									");
		sql.append("	FROM Turno t, Funcionario f, Plantao p, Plantao_Funcionario pf  ");
		sql.append(" WHERE f.id = ?														");
		sql.append(" AND f.id = pf.funcionario_id										");
	    sql.append(" AND p.id = pf.plantao_id											");
		sql.append(" AND t.id = p.turno_id												");
		
	    try {

	            conn = SingletonConexaoBanco.getInstance().getConnection();
	            
	            PreparedStatement ps = conn.prepareStatement(sql.toString());
	            
	            ps.setLong(1, funcionario.getId());
	         
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	            
	            	//* data do turno
	            	 Date dataTurno = rs.getDate(1);
	            	 
	            	 //* Precisa do if abaixo, porque quando o funcionario não tem turno, acaba entrando no while, trazendo objetos null
	            	 if(dataTurno != null){
			             Calendar calData = Calendar.getInstance();
			             calData.setTime(dataTurno);
			             
		            	 //* fechamanteo
			             Timestamp horaFechamento = rs.getTimestamp(2);
	 
			             //* cria uma nova data com o dia do turno junto com o horario do mesmo
			             Calendar calUltimaDataTrabalhada = DataUtil.retornaDataTurnoComHora(calData, horaFechamento);
			            
			             return calUltimaDataTrabalhada;
		             
	            	 }
		        
	            }
	            rs.close();
	            ps.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(EscalaJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
		return null;
	}

	@Override
	public List<RelEscalaFuncDTO> retornaRelEscalaFuncionario( Funcionario funcionario, Date dataInicio, Date dataFim) {
		
		List<RelEscalaFuncDTO> retorno = new ArrayList<RelEscalaFuncDTO>();
		
		Calendar calDataInicio = Calendar.getInstance();
		Calendar calDataFim = Calendar.getInstance();
		
		calDataInicio.setTime(dataInicio);
		calDataFim.setTime(dataFim);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
		
		//* faz um loop na data início da consulta, onde ira trazer a cada loop cada primeiro e ultimo dia daquela semana. Só irá parar quando a dataInicio
		//* incrementar até a dataFim
		do{
			
			  
		    Date primeiroDataSemana = DataUtil.retornaPrimeiroUltimoSemana(calDataInicio.getTime(), true);  
	        Date ultimaDataSemana = DataUtil.retornaPrimeiroUltimoSemana(calDataInicio.getTime(), false);
	        
	        //* A cada incremento do loop, traz os turnos daquela semana do funcionário
	        RelEscalaFuncDTO dto = this.retornaObjetoRelEscalaFuncionario(funcionario, primeiroDataSemana, ultimaDataSemana);
	        
	        if(dto != null){
	        
	        	String semana = sdf.format(primeiroDataSemana).concat(" - ").concat(sdf.format(ultimaDataSemana));

	        	dto.setSemana(semana);
	        	
	        	retorno.add(dto);
	        }
	        
	        calDataInicio.add(Calendar.DAY_OF_MONTH, 7);
		          
			
		}while(calDataInicio.before(calDataFim));
		
		    
		return retorno;

	}  
	
	
	private RelEscalaFuncDTO retornaObjetoRelEscalaFuncionario( Funcionario funcionario, Date dataInicio, Date dataFim) {
		
		RelEscalaFuncDTO escala = new RelEscalaFuncDTO();
		
		boolean existeEscalaFuncionario = false;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT f.nome,  p.data,t.abertura, t.fechamento, t.intervaloInicio, t.intervalorFim, t.cor ");
		sql.append(" FROM Turno t, Funcionario f, Plantao p, Plantao_Funcionario pf   ");
		sql.append(" 	WHERE f.id = ?												  ");  
		sql.append(" 	AND f.id = pf.funcionario_id								  ");
		sql.append(" 	AND p.id = pf.plantao_id									  "); 
		sql.append("    AND t.id = p.turno_id							   			  ");
		sql.append("    AND DATE(p.data) BETWEEN  ? AND ?							  ");
		
		
	    try {

	            conn = SingletonConexaoBanco.getInstance().getConnection();
	            
	            PreparedStatement ps = conn.prepareStatement(sql.toString());
	            
	            ps.setLong(1, funcionario.getId());
	            ps.setDate(2, HoraUtil.converteDataParaSQLDate(dataInicio));
	            ps.setDate(3, HoraUtil.converteDataParaSQLDate(dataFim));
	         
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	            	
	            	//* se existir horario de escala, seta true
	            	existeEscalaFuncionario = true;
	            	
	            	//* nome do funcionario
	            	escala.setNome(rs.getString(1));
	            	
	            	 //* data do turno
	                Date dataTurno = rs.getDate(2);
	                
	                //* abertura
	                Timestamp horaAbertura = rs.getTimestamp(3);
	                //* fechamanteo
	                Timestamp horaFechamento = rs.getTimestamp(4);
	                
	                //* intervalo de inicio
	                Timestamp interInicio = rs.getTimestamp(5);
	                
	                //* intervalo fim
	                Timestamp interFim = rs.getTimestamp(6);
	                
	                String cor = rs.getString(7);
	               
	                //* preenche o objeto lancamendo seus valores nos respectivos campos de acordo com o dia da semana
	            	EscalaUtil.preencheRelEscalaFuncionario(escala, dataTurno, horaAbertura, horaFechamento, interInicio, interFim, cor);
	            
	            }
	            rs.close();
	            ps.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(EscalaJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	    //* Feito isso para não ficar mostrando no relatório ou tabela, semanas com turnos "FOLGA", trazendo apenas
	    //* as semans com turnos reais
	    if(existeEscalaFuncionario){
	    	return escala;
	    }else{
	    	return null;
	    }

	}  	


}
