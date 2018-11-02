package selecao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Principal3 {
	
	public static Map<String, List<String>> mapaFuncionarioAlocadoTurnoDia;
	public static Map<String, List<String>> mapaFuncionarioComFolgaDia;
	
	public static void main(String args[]){
			
		mapaFuncionarioAlocadoTurnoDia = Utils.alimentaMapa();
		mapaFuncionarioComFolgaDia = Utils.alimentaMapa();
		
		
		
		LePlanilhaExcel lp = new LePlanilhaExcel();

		DiaEscala[] arrayDiaEscala = lp.leXLS();
		
		
		List<TurnoDia> listaTurnoDia = new ArrayList<TurnoDia>();
		
		for(int contDias = 0; contDias < arrayDiaEscala.length; contDias++){
			
			
			//* recupera o array com todos os TurnoDia que contem todos os turnos do dia
			TurnoDia[] arrayTurnoDia = arrayDiaEscala[contDias].getArrayTurnoDia();
			
			int contFuncDisponiveis = 0;
			
			for(int contTurnoDia = 0; contTurnoDia < arrayTurnoDia.length; contTurnoDia++){
				
				//* recupera o turno do array de todos os turnos do dia
				Turno turno = arrayTurnoDia[contTurnoDia].getTurno();
				
				int demanda = turno.getDemanda();
				
				//* recupera o array que contem todos os funcionarios e seus valores de preferencia e restricao para
				// o turno em questao
				FuncionarioTurno[] arrayFuncionarioTurno = arrayTurnoDia[contTurnoDia].getArrayFuncionarioTurno();
				
				//* contador que vai calcular a demanda critica
				int contRestricao = 0;
				//* recupera o numero de funcionarios atrelados ao turno
				int numFuncionario = arrayFuncionarioTurno.length;
				
				for(int contFuncTurno = 0 ; contFuncTurno < arrayFuncionarioTurno.length; contFuncTurno ++){
				
					//* recupera os valores da preferencia e restricao do funcionario para aquele turno
					int numPrefencia = arrayFuncionarioTurno[contFuncTurno].getNumPreferencia();
					int numRestricao = arrayFuncionarioTurno[contFuncTurno].getNumRestricao();
					
					String funcNome = arrayFuncionarioTurno[contFuncTurno].getFuncionario().getNome();
					
					numPrefencia = arrayFuncionarioTurno[contFuncTurno].getNumPreferencia();
					
					numRestricao = arrayFuncionarioTurno[contFuncTurno].getNumRestricao();
					
					//**** CALCULO DAS PREFERENCIAS E RESTRICOES ******
					
					//* adiciona ao calculo da demanda critica
					contRestricao = contRestricao + numRestricao;
					
					//* realiza o calculo
					int numResultado = numPrefencia * numRestricao;
					
					if(numResultado > 0){
						
						contFuncDisponiveis ++;
					}
					
					//-- nao pode trabalhar domingo coloque 0
					
					//System.out.println("Resultado : " + numResultado);
				
					//* atualiza o Objeto FuncionarioTurno com o resultado do cruzamento da preferencia com a restricao
					arrayFuncionarioTurno[contFuncTurno].setNumResultado(numResultado);
					
					//System.out.println("Resultado : " + numResultado);
				
				}
				
				//* calcula a demanda critica. Quanto menor o numero mais critica Ž a demanda
				int demandaCritica =  contRestricao - demanda;
				
				int numDemanda = arrayTurnoDia[contTurnoDia].getTurno().getDemanda();
				
				if(contFuncDisponiveis < numDemanda){
					
					System.out.println("O nœmero de funcion‡rios dispon’veis Ž menor que a demanda");
					System.out.println("Demanda:"+ numDemanda);
					System.out.println("Funcion‡rios dispon’veis : " + contFuncDisponiveis);
				}
				
				//* atualiza o objeto que contem o turno com a demanda critica
				arrayTurnoDia[contTurnoDia].setDemandaCritica(demandaCritica);
				
				//* adiciona a lista de turnos do dia
				listaTurnoDia.add(arrayTurnoDia[contTurnoDia]);
				
			}
			
		}
		
		System.out.println("===================================== Resultado ==================================================");

		//*** ORDENA AQUI OS TURNOS POR DEMANDA CRITICA ***
		Object[] arrayTurnoDoDia = listaTurnoDia.toArray();
		
		SelecaoDemandaCritica.quicksort(arrayTurnoDoDia, 0, (arrayTurnoDoDia.length - 1));
	
		//*** PREENCHE OS TURNOS ****
		for(int contTurnoDia = 0; contTurnoDia < arrayTurnoDoDia.length; contTurnoDia++){
				
				//* recupera o turno do array de todos os turnos do dia
				TurnoDia turnoDia = (TurnoDia)arrayTurnoDoDia[contTurnoDia];
				Turno turno = turnoDia.getTurno();
				int demanda = turno.getDemanda();
				String diaSemana = turno.getDiaSemana();
				
				//* recupera o array que contem todos os funcionarios e seus valores de preferencia e restricao para
				// o turno em questao
				FuncionarioTurno[] arrayFuncionarioTurno = turnoDia.getArrayFuncionarioTurno();
				
				//*** ORDENA AQUI A SELECAO DE QUAIS OS MELHORES FUNCIONARIOS PARA AQUELE TURNO ***
				SelecaoEscala.quicksort(arrayFuncionarioTurno, 0, (arrayFuncionarioTurno.length - 1));
				
				int contadorFuncionarioAdicionadosAoTurno = 0;
				
				//* selecionar os funcionarios pro turno de acordo com a demanada
				for(int contFuncTurno = 0 ; contFuncTurno < arrayFuncionarioTurno.length; contFuncTurno ++){
					
					Funcionario funcionario = arrayFuncionarioTurno[contFuncTurno].getFuncionario();
					
					String nomeFuncionario = funcionario.getNome();
					int resultado = arrayFuncionarioTurno[contFuncTurno].getNumResultado();
				
					//* procura na lista de funcionarios se o mesmo j‡ est‡ escalado no dia para algum turno
					boolean funcionarioJaEscaladoDia = Utils.estaEscaladoOuFolgaDia(nomeFuncionario, diaSemana, mapaFuncionarioAlocadoTurnoDia);
					
					//* verifica se o funcionario est‡ com folga para aquele dia
					boolean funciorioEstaComFolgaDia = Utils.estaEscaladoOuFolgaDia(nomeFuncionario, diaSemana, mapaFuncionarioComFolgaDia);
					
					//recupera o numero ultimos domingos trabalhados
					int numDomingo = funcionario.getDomingosTrabalhos().getTotalDomingos();
					
					//* Se o contador de funcionarios adicionado ao turno for menos que a demanda e o funcionario em quest‹o n‹o foi adicionar a algum turno 
					//* do dia ainda, ent‹o adiciona ao turno em quest‹o. O resultado tem que ser maior que zero
					if(contadorFuncionarioAdicionadosAoTurno < demanda && !funcionarioJaEscaladoDia && !funciorioEstaComFolgaDia && resultado > 0){
							
						if("Domingo".equals(turno.getDiaSemana())){
							
							if(numDomingo > 2){
								
								//* d‡ folga pro funcionario
								arrayFuncionarioTurno[contFuncTurno].setEstaEscalado(false);
								
								//* zera o numero de domingos trabalhados
								arrayFuncionarioTurno[contFuncTurno].getFuncionario().getDomingosTrabalhos().setTotalDomingos(0);
								
								//* atualiza o mapa com o funcionario com folga do dia, isso para n‹o jogar o funcionario em um turno posterior
								Utils.alimentaMapComFuncionariosComFolgaDia(nomeFuncionario, diaSemana, mapaFuncionarioComFolgaDia );
							
							}else{
								//* escala o funcionario para aquele turno
								arrayFuncionarioTurno[contFuncTurno].setEstaEscalado(true);
								//listaFuncionariosEscaladosTurnoDoDia.add(funcionario);
								
								Utils.alimentaMapComFuncionariosAlocadoTurnoDia(nomeFuncionario, diaSemana, mapaFuncionarioAlocadoTurnoDia);
								//* atualiza o contador que informa que foi adicionado um funcionario ao turno
								contadorFuncionarioAdicionadosAoTurno++;
							}
							
						}else{
							
							int diaSemanaNumero = Utils.retornaDiaSemanaEmNumero(turno.getDiaSemana());
							int ultimoDiaTrabalho =  funcionario.getUltimoDiaSemanaTrabalho().getDia();
							
							if(diaSemanaNumero == ultimoDiaTrabalho ){
								
								//* d‡ folga pro funcionario
								arrayFuncionarioTurno[contFuncTurno].setEstaEscalado(false);
								
								int proxDiaATrabalhar = 1;
								
								if(ultimoDiaTrabalho < 7){
									proxDiaATrabalhar = ultimoDiaTrabalho + 1; 
								}
								
								//* j‡ que deu folga, seta o pr—ximo dia que o funcionario comea a trabalhar
								arrayFuncionarioTurno[contFuncTurno].getFuncionario().getUltimoDiaSemanaTrabalho().setDia(proxDiaATrabalhar);
								//* atualizar a tabela de domingo,caso for sabado
								
								//* atualiza o mapa com o funcionario com folga do dia, isso para n‹o jogar o funcionario em um turno posterior
								Utils.alimentaMapComFuncionariosComFolgaDia(nomeFuncionario, diaSemana, mapaFuncionarioComFolgaDia );
						
							}else{
								
								//* escala o funcionario para aquele turno
								arrayFuncionarioTurno[contFuncTurno].setEstaEscalado(true);
								//listaFuncionariosEscaladosTurnoDoDia.add(funcionario);
								
								Utils.alimentaMapComFuncionariosAlocadoTurnoDia(nomeFuncionario, diaSemana, mapaFuncionarioAlocadoTurnoDia);
								//* atualiza o contador que informa que foi adicionado um funcionario ao turno
								contadorFuncionarioAdicionadosAoTurno++;
							}
						}
						
					} else{
						//* informa que o funcionario n‹o est‡ escalado para aquele turno
						arrayFuncionarioTurno[contFuncTurno].setEstaEscalado(false);
					}
					
				}
						
			}
		
		
		//*** MOSTRA O RESULTADO ***
		
		for(int contDias = 0; contDias < arrayDiaEscala.length; contDias++){
			
			int numDia = arrayDiaEscala[contDias].getNum();
			
			System.out.println("Dia da Semana: " + SelecaoEscala.retornoDiaSemana(numDia));
			
			//* recupera o array com todos os TurnoDia que contem todos os turnos do dia
			TurnoDia[] arrayTurnoDia = arrayDiaEscala[contDias].getArrayTurnoDia();
			
			for(int contTurnoDia = 0; contTurnoDia < arrayTurnoDia.length; contTurnoDia++){
				
				//* recupera o turno do array de todos os turnos do dia
				Turno turno = arrayTurnoDia[contTurnoDia].getTurno();
				
				int demanda = turno.getDemanda();
				
				System.out.println("Turno : " + turno.getHorario());
				System.out.println("Demanda : "+ demanda);
				
				//* recupera o array que contem todos os funcionarios e seus valores de preferencia e restricao para
				// o turno em questao
				FuncionarioTurno[] arrayFuncionarioTurno = arrayTurnoDia[contTurnoDia].getArrayFuncionarioTurno();
				
				//* selecionar os funcionarios pro turno de acordo com a demanada
				for(int contFuncTurno = 0 ; contFuncTurno < arrayFuncionarioTurno.length; contFuncTurno ++){
					
					String funcionario = arrayFuncionarioTurno[contFuncTurno].getFuncionario().getNome();
					int resultado = arrayFuncionarioTurno[contFuncTurno].getNumResultado();
					
					String estaEscalado = SelecaoEscala.estaEscalado(arrayFuncionarioTurno[contFuncTurno].isEstaEscalado());
					
					System.out.println(funcionario + " " + "Escalado: "+ estaEscalado + " Resultado: "+ resultado );
					
				}
				
				System.out.println("=============================");
				
			}
		}
		
	}
	

}
