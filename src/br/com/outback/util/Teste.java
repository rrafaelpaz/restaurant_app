package br.com.outback.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
import javax.swing.JOptionPane;

import org.hibernate.mapping.Array;

import br.com.outback.bean.Funcionario;
import br.com.outback.facade.PreferenciaFacade;

public class Teste {
	
	public static void main(String args[]){
		
		
		PreferenciaFacade pf = new PreferenciaFacade();
		
		pf.findPreferenciasFuncionario(null, null);
		
		
		/*Calendar restriInicio = Calendar.getInstance();
		Calendar restriFim = Calendar.getInstance();
		Calendar turnoInicio = Calendar.getInstance();
		Calendar turnoFim = Calendar.getInstance();
		
		//restriInicio.set(Calendar.HOUR_OF_DAY,8);
		//restriFim.set(Calendar.HOUR_OF_DAY,14);
		
		turnoInicio.set(Calendar.HOUR_OF_DAY,8);
		turnoFim.set(Calendar.HOUR_OF_DAY,12);
		
		
		FuncionarioFacade fc = new FuncionarioFacade();
		
		Funcionario f = fc.listAll().get(0);
			
		boolean resul=  RestricaoUtil.funcionarioTemRestricaoObrigatoria(f, turnoInicio, turnoInicio.getTime(),turnoFim.getTime());
		
		if(resul){
			System.out.println("nao pode trabalhar");
		}else{
			System.out.println("pode trabalhar");
		}*/
		
		
		
		/*//* a princípio pega o primeiro e último dia do mês
		Calendar datas[] = DataUtil.retornaDatasConsultaMes();
		
		Calendar calDataInicio = datas[0];
		Calendar calDataFim = datas[1];
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
		
		//* faz um loop na data início da consulta, onde ira trazer a cada loop cada primeiro e ultimo dia daquela semana. Só irá parar quando a dataInicio
		//* incrementar até a dataFim
		do{
			
			  
		    Date primeiroDataSemana = DataUtil.retornaPrimeiroUltimo(calDataInicio.getTime(), true);  
	        Date ultimaDataSemana = DataUtil.retornaPrimeiroUltimo(calDataInicio.getTime(), false);
	        

	        String semana = sdf.format(primeiroDataSemana).concat(" - ").concat(sdf.format(ultimaDataSemana));

	        System.out.print(semana + "\n");
	        
	        calDataInicio.add(Calendar.DAY_OF_MONTH, 7);
		          
			
		}while(calDataInicio.before(calDataFim));*/
		
		
		int numTurnos = 80;
		
		
		
		//Um exemplo prático seria: se você possui R$ 2.000,00 em um banco e sacou R$ 300,00. Qual será o percentual do valor sacado em relação ao montante geral? 
		//Neste caso você poderá realizar o seguinte cálculo: R$ 300,00 ÷ R$ 2.000,00 = R$ 0,15. Agora multiplique o valor obtido por 100 da seguinte maneira: 
		//0,15 × 100 = 15%.
		
		
		/*for(int i = 1; i <= 80; i++){
			
			float cont=i;
	        float turnos= numTurnos;
	        
	        float divisao =cont/turnos;
			
			int resultado = (int) (divisao*100);  
			
			
	        System.out.println(resultado);
			
		}
		
		float cont=80;
        float turnos=80;
        
        float divisao =cont/turnos;
		
		float resultado = divisao*100;  */
		
        //System.out.println(resultado);
		   
		
		String[] vetorString = new String [5];
		
		List<String> lista = new ArrayList<String>();
		
		vetorString[0] = "Rafael";
		vetorString[1] = "Rafael";
		vetorString[2] = "Rafael";
		vetorString[3] = "Rafael";
		vetorString[4] = "Rafael";
		vetorString[6] = "Rafael";
		
		
		String[]  vetorx = new String[10];
		
		for(String vs : vetorString){

			
		}
		
		lista.get(3);
		
		lista.add("Rafael");

		lista.add("Rafael");
		lista.add("Rafael");
		lista.add("Rafael");
		lista.add("Rafael");
		lista.add("Rafael");
		lista.add("Rafael");
		lista.add("Rafael");
		lista.add("Rafael");
		lista.add("Rafael");
		lista.add("Rafael");
		lista.add("Rafael");
		lista.add("Rafael");
		lista.add("Rafael");
		lista.add("Rafael");
		
		
		
	}
		
}
	

	
	
	
	

