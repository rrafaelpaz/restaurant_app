package selecao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
	
	public static void main(String args[]){
		
		CriarDados criarDados = new CriarDados();

		// * popula os objetos funcinarios com nome
		Funcionario[] funcionarios = criarDados.criarFuncionarios();
		
		// * popula os funcionarios com suas respectivas preferencia e restricao
		DiaEscala[] arrayDiaEscala = criarDados.criarTurnosComPreferenciaEREstricao(funcionarios);
		
		for(int contDias = 0; contDias < arrayDiaEscala.length; contDias++){
			
			int numDia = arrayDiaEscala[contDias].getNum();
			
			System.out.println("Dia da Semana: " + SelecaoEscala.retornoDiaSemana(numDia));
			
			List<String> listaFuncionariosEscaladosTurnoDoDia = new ArrayList<String>();
			
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
				
				//* contador que vai calcular a demanda critica
				int contRestricao = 0;
				//* recupera o numero de funcionarios atrelados ao turno
				int numFuncionario = arrayFuncionarioTurno.length;
				
				Scanner in = new Scanner(System.in);
				
				for(int contFuncTurno = 0 ; contFuncTurno < arrayFuncionarioTurno.length; contFuncTurno ++){
				
					//* recupera os valores da preferencia e restricao do funcionario para aquele turno
					int numPrefencia = arrayFuncionarioTurno[contFuncTurno].getNumPreferencia();
					int numRestricao = arrayFuncionarioTurno[contFuncTurno].getNumRestricao();
					
					String funcNome = arrayFuncionarioTurno[contFuncTurno].getFuncionario().getNome();
					
					System.out.print(funcNome + "\n");
					System.out.print("Digite o nœmero de preferncia...");
					numPrefencia = in.nextInt();
					System.out.print("Digite o nœmero de restri‹o...");
					numRestricao = in.nextInt();
					
					//**** CALCULO DAS PREFERENCIAS E RESTRICOES ******
					
					//* adiciona ao calculo da demanda critica
					contRestricao = contRestricao + numRestricao;
					
					//* realiza o calculo
					int numResultado = numPrefencia * numRestricao;
					
					//-- nao pode trabalhar domingo coloque 0
					
					//System.out.println("Resultado : " + numResultado);
				
					//* atualiza o Objeto FuncionarioTurno com o resultado do cruzamento da preferencia com a restricao
					arrayFuncionarioTurno[contFuncTurno].setNumResultado(numResultado);
					
					//System.out.println("Resultado : " + numResultado);
				
				}
				
				//* calcula a demanda critica. Quanto menor o numero mais critica Ž a demanda
				int demandaCritica = (numFuncionario/ demanda) * contRestricao;
				
				//* atualiza o objeto que contem o turno com a demanda critica
				arrayTurnoDia[contTurnoDia].setDemandaCritica(demandaCritica);
				
				//*** ORDENA AQUI OS TURNOS POR DEMANDA CRITICA ***
				
				//*** ORDENA AQUI A SELECAO ***
				SelecaoEscala.quicksort(arrayFuncionarioTurno, 0, (arrayFuncionarioTurno.length - 1));
				
				int contadorFuncionarioAdicionadosAoTurno = 0;
				
				//* selecionar os funcionarios pro turno de acordo com a demanada
				for(int contFuncTurno = 0 ; contFuncTurno < arrayFuncionarioTurno.length; contFuncTurno ++){
					
					String funcionario = arrayFuncionarioTurno[contFuncTurno].getFuncionario().getNome();
					int resultado = arrayFuncionarioTurno[contFuncTurno].getNumResultado();
					boolean funcionarioJaEscaladoDia = false;
					
					//* procura na lista de funcionarios se o mesmo j‡ est‡ escalado no dia para algum turno
					for(String f : listaFuncionariosEscaladosTurnoDoDia){
						
						if(f.equals(funcionario)){
							funcionarioJaEscaladoDia = true;
							break;
						}
					}
				
					//* Se o contador de funcionarios adicionado ao turno for menos que a demanda e o funcionario em quest‹o n‹o foi adicionar a algum turno 
					//* do dia ainda, ent‹o adiciona ao turno em quest‹o. O resultado tem que ser maior que zero
					if(contadorFuncionarioAdicionadosAoTurno < demanda && !funcionarioJaEscaladoDia && resultado > 0){
						//* escala o funcionario para aquele turno
						arrayFuncionarioTurno[contFuncTurno].setEstaEscalado(true);
						listaFuncionariosEscaladosTurnoDoDia.add(funcionario);
						
						//* atualiza o contador que informa que foi adicionado um funcionario ao turno
						contadorFuncionarioAdicionadosAoTurno++;
					} else{
						//* informa que o funcionario n‹o est‡ escalado para aquele turno
						arrayFuncionarioTurno[contFuncTurno].setEstaEscalado(false);
					}
					
					
					String estaEscalado = SelecaoEscala.estaEscalado(arrayFuncionarioTurno[contFuncTurno].isEstaEscalado());
					
					System.out.println(funcionario + " " + "Escalado: "+ estaEscalado + " Resultado: "+ resultado );
					
					
				}
				
				System.out.println("=============================");
				
			}
		}

		
	}

}
