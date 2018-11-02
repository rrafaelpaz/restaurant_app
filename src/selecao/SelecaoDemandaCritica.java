package selecao;

public class SelecaoDemandaCritica {

	public static void quicksort(Object vet[], int ini, int fim) {

		int meio;

		if (ini < fim) {

			meio = partition(vet, ini, fim);

			quicksort(vet, ini, meio);

			quicksort(vet, meio + 1, fim);

		}

	}

	public static int partition(Object vet[], int ini, int fim) {

		int topo, i;
		
		TurnoDia pivo = (TurnoDia)vet[ini];

		topo = ini;

		for (i = ini + 1; i <= fim; i++) {
			
			int demandaCritica =  ((TurnoDia)vet[i]).getDemandaCritica();

			//* se quiser ordenar decrescente s— mudar a compara‹o de "<" para ">"
			if (demandaCritica < pivo.getDemandaCritica()) {

				vet[topo] = vet[i];

				vet[i] = vet[topo + 1];

				topo++;

			}

		}
		
		vet[topo]= pivo ;

		return topo;

	}

	
}