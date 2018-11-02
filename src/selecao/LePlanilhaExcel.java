package selecao;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LePlanilhaExcel {
	
	public DiaEscala[] leXLS() {
		 
	 	int numFuncionario = 10;
		
		Funcionario[] funcionarios = new Funcionario[numFuncionario];
		Turno turno = null;
		String diaSemana = "";
		
		DiaEscala[] arrayDiaEscala = new DiaEscala[7];
     	DiaEscala diaEscala = null;
	        
	    	 try {
	            // Leitura
	            FileInputStream fi = new FileInputStream(new File("/Users/rafaelpaz/Documents/workspace/web/outback/novaplanilha.xlsx"));
	 
	            // Carregando workbook
	            XSSFWorkbook wb = new XSSFWorkbook(fi);
	 
	            // Selecionando a primeira aba
	            XSSFSheet s = wb.getSheetAt(0);
	            
	            int cont = 0;
	            
	        	//* apenas um tamanho ficticio, o array mudara de tamanho a medida que ele for visualizando novos turnos
	            TurnoDia[] arrayTurnoDia = null;
	            
	            TurnoDia turnoDia = null;
	            
	            //* arrayFuncionarioTurno
	            FuncionarioTurno[] arrayFuncionarioTurno = null;
	            
	            int contArrayFuncionarioTurno = 0;
	          
	            // Fazendo um loop em todas as linhas
	            for (Row rowFor : s) {
	            	
	            	cont ++;
	            	
	            	if(cont < 5){
	            		continue;
	            	}
	            	
	            	if(cont < numFuncionario + 5){
	            		
	            		//* nome funcionario
	            		String nome = rowFor.getCell(0).toString();
	            		
	            		//* domingos trablados
	            		Double valueDomingo = rowFor.getCell(1).getNumericCellValue();
                    	int valorDomingo = valueDomingo.intValue();
                    	
                    	//* ultima folta
                    	Double valueUltimaFolga = rowFor.getCell(2).getNumericCellValue();
                    	int valorUltimaFolga = valueUltimaFolga.intValue();
	            		
	            		DomingosTrabalhados domingosTrabalhados = new DomingosTrabalhados();
	        			UltimoDiaSemanaTrabalhado ultimaFolga = new UltimoDiaSemanaTrabalhado();
	        			
	        			domingosTrabalhados.setTotalDomingos(valorDomingo);
	        			ultimaFolga.setDia(valorUltimaFolga);
	        		
	        			Funcionario funcionario = new Funcionario();
		            	
	        			funcionario.setNome(nome);
	            		funcionario.setUltimoDiaSemanaTrabalho(ultimaFolga);
	        			funcionario.setDomingosTrabalhos(domingosTrabalhados);
	            		
	            		funcionarios[cont-5] = funcionario;
	          
	            	}else{
	            		
	            		String nome = rowFor.getCell(0).toString();
	            		
	            		if(nome.equals("Segunda") || nome.equals("Tera") || nome.equals("Quarta") || nome.equals("Quinta") ||
	            				nome.equals("Sexta") || nome.equals("S‡bado") || nome.equals("Domingo") ){
	            		
	            			if(!nome.equals(diaSemana)){
	            				
	            				diaSemana = nome;
	            				
	            				//* atraves do nome recupera o numero da semana
	            				int numDiaSemana = Utils.retornaDiaSemanaEmNumero(diaSemana);
	            				
	            				//* a cada novo dia da semana, um novo objeto diaEscala
	            				diaEscala = new DiaEscala();
	            				
	            				//* seta o numero do dia
	            				diaEscala.setNum(numDiaSemana);
	            				
	            				//* atualiza o vetor de DiaEscala
	            				arrayDiaEscala[numDiaSemana - 1] = diaEscala;
		            		}
	            			
	            		}else{
		            		
		            		if(nome.equals("fim")){
		            			
		            			//* atualiza o diaEscala com os arrrayTurnoDia preenchidos
	            				diaEscala.setArrayTurnoDia(arrayTurnoDia);
	            				
	            				//* zera para novo arrayTurnoDia
	            				arrayTurnoDia = null;
		            			
		            		}else if(nome.startsWith("Turno")){
		            			
		            			
		            			Double demandaDouble = rowFor.getCell(1).getNumericCellValue();
		            			int demanda = demandaDouble.intValue();
		            			
		            			turno = new Turno();
		            			turno.setDiaSemana(diaSemana);
		            			turno.setHorario(nome);
		            			turno.setDemanda(demanda);
		            			
		            			//* cria novo objeto TurnoDia e atrela ao turno novo
		            			turnoDia = new TurnoDia();
		            			turnoDia.setTurno(turno);
		            			
		            			arrayFuncionarioTurno = new FuncionarioTurno[numFuncionario];
		            			
		            			//* ao fim de cada turno atualiza o turnoDia com os seus respectivos funcionarioTurno
		            			turnoDia.setArrayFuncionarioTurno(arrayFuncionarioTurno);
		      
		            			//* zera o contador
		            			contArrayFuncionarioTurno = 0;
		            			
		            			//* verifica se o array esta vazio
		            			if(arrayTurnoDia  == null){
		            				arrayTurnoDia = new TurnoDia[1];
		            				arrayTurnoDia[0] = turnoDia;
		            			}else{
		            				
		            				TurnoDia[] novoArrayTurnoDia = new TurnoDia[arrayTurnoDia.length + 1];
		            				
		            				//* passa as variaves do arrayTurnoDia para o novo array
		            				for(int i = 0 ; i < arrayTurnoDia.length; i ++){
		            					
		            					novoArrayTurnoDia[i] = arrayTurnoDia[i];
		            				
		            				}
		            				
		            				//* jogo no novo array o novo turno
		            				novoArrayTurnoDia[arrayTurnoDia.length] = turnoDia;
		            				
		            				//* cria novo array com o novo tamanho
		            				arrayTurnoDia = new TurnoDia[novoArrayTurnoDia.length];
		            				
		            				//* recupera os turnos + o novo turno
		            				for(int i = 0; i < arrayTurnoDia.length; i++){
		            					
		            					arrayTurnoDia[i] = novoArrayTurnoDia[i];
		            				}
		            				
		            			}
		          
		            		}else{
		            			
		            			//* recupera a preferencia
		            			Double preferenciaDouble = rowFor.getCell(1).getNumericCellValue();
		            			int preferencia = preferenciaDouble.intValue();
		            			
		            			//* recupera a restricao
		            			Double restricaoDouble = rowFor.getCell(2).getNumericCellValue();
		            			int restricao = restricaoDouble.intValue();
		            			
		            			//* encontra o funcionario do array e o atualiza com suas restricoes e preferencias
		            			for(Funcionario funcionario : funcionarios){
		            				
		            				if(funcionario.getNome().equals(nome)){
		            					
		            					FuncionarioTurno funcionarioTurno = new FuncionarioTurno();
		            					
		            					funcionarioTurno.setFuncionario(funcionario);
		            					funcionarioTurno.setNumPreferencia(preferencia);
		            					funcionarioTurno.setNumRestricao(restricao);
		            					
		            					arrayFuncionarioTurno[contArrayFuncionarioTurno] = funcionarioTurno;
		            					
		            					contArrayFuncionarioTurno ++;
		            					
		            					break;
		            				}
		            			}
		            			
		            			
		            		}
	            		}
	            		
	            	}
	            }
	            
	        	//* atualiza o ultimo dia, que Ž domingo n‹o tem mais lao
    			turnoDia.setArrayFuncionarioTurno(arrayFuncionarioTurno);
    		
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	 
	    	 return arrayDiaEscala;
	    }

}
