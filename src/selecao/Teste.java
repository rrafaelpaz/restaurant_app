package selecao;


public class Teste {

	public static void main(String args[]) {

		LePlanilhaExcel lp = new LePlanilhaExcel();

		DiaEscala[] arrayDiaEscala = lp.leXLS();
		
		
		   for(DiaEscala de: arrayDiaEscala){
	        	
	        	System.out.println(de.getNum());
	            	
	        	for (TurnoDia td : de.getArrayTurnoDia()){
	        		System.out.println(td.getTurno().getHorario());
	        		
	        		for(FuncionarioTurno ft: td.getArrayFuncionarioTurno()){
	        			
	        			System.out.println(ft.getFuncionario().getNome() + " " + ft.getNumPreferencia() + " " + ft.getNumRestricao());
	        			
	        		}
	        		//System.out.println(td.getTurno().getDemanda());
	        	}
	        	
	        	System.out.println("--------------------");
	        }
	}

}
