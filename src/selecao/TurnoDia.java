package selecao;

public class TurnoDia {
	
	private Turno turno;
	private FuncionarioTurno[] arrayFuncionarioTurno;
	private int demandaCritica;
	
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public FuncionarioTurno[] getArrayFuncionarioTurno() {
		return arrayFuncionarioTurno;
	}
	public void setArrayFuncionarioTurno(FuncionarioTurno[] arrayFuncionarioTurno) {
		this.arrayFuncionarioTurno = arrayFuncionarioTurno;
	}
	public int getDemandaCritica() {
		return demandaCritica;
	}
	public void setDemandaCritica(int demandaCritica) {
		this.demandaCritica = demandaCritica;
	}
	
	

}
