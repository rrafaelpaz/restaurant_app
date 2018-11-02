package selecao;

import java.util.Scanner;

public class SelecaoEscala {

	public static void quicksort(FuncionarioTurno vet[], int ini, int fim) {

		int meio;

		if (ini < fim) {

			meio = partition(vet, ini, fim);

			quicksort(vet, ini, meio);

			quicksort(vet, meio + 1, fim);

		}

	}

	public static int partition(FuncionarioTurno vet[], int ini, int fim) {

		int topo, i;
		
		FuncionarioTurno pivo = vet[ini];

		topo = ini;

		for (i = ini + 1; i <= fim; i++) {

			//* se quiser ordenar crescente só mudar a comparação de ">" para "<"
			if (vet[i].getNumResultado() > pivo.getNumResultado()) {

				vet[topo] = vet[i];

				vet[i] = vet[topo + 1];

				topo++;

			}

		}
		
		vet[topo]= pivo ;

		return topo;

	}
	
	public static String retornoDiaSemana(int dia){
		
		switch (dia) {
		
			case 1:
				return "Segunda";
			case 2:
				return "Terça";
			case 3:
				return "Quarta";
			case 4:
				return "Quinta";
			case 5:
				return "Sexta";
			case 6:
				return "Sábado";
			default:
				return "Domingo";
			
		}
	}
	
	public static String estaEscalado(boolean estaEscalado){
		
		if(estaEscalado){
			return "SIM";
		}else{
			return "NÃO";
		}
	}

	/*public static void main(String[] args) {

		
		
		FuncionarioTurno[] arrayFuncionarioTurno = new FuncionarioTurno[4];
		
		FuncionarioTurno ft1 = new FuncionarioTurno();
		
		Funcionario f1 = new Funcionario();
		f1.setNome("Jade");
		
		ft1.setFuncionario(f1);
		ft1.setNumResultado(5);
		
		FuncionarioTurno ft2 = new FuncionarioTurno();
		
		Funcionario f2 = new Funcionario();
		f2.setNome("Kadu");
		
		ft2.setFuncionario(f2);
		ft2.setNumResultado(3);
		
		FuncionarioTurno ft3 = new FuncionarioTurno();
		
		Funcionario f3 = new Funcionario();
		f3.setNome("Rafael");
		
		ft3.setFuncionario(f3);
		ft3.setNumResultado(4);
		

		FuncionarioTurno ft4 = new FuncionarioTurno();
		Funcionario f4 = new Funcionario();
		f4.setNome("Jose");
		
		ft4.setFuncionario(f4);
		ft4.setNumResultado(1);
		
		arrayFuncionarioTurno[0] = ft1;
		arrayFuncionarioTurno[1] = ft2;
		arrayFuncionarioTurno[2] = ft3;
		arrayFuncionarioTurno[3] = ft4;
		

		quicksort(arrayFuncionarioTurno, 0, (arrayFuncionarioTurno.length - 1));

		for (int i = 0; i < arrayFuncionarioTurno.length; i++) {

			System.out.println("Funcionario " + arrayFuncionarioTurno[i].getFuncionario().getNome());
			System.out.println("Resultado " + arrayFuncionarioTurno[i].getNumResultado());
			System.out.println("***********************");


		}

	}*/
	
}