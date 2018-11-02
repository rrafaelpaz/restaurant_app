package selecao;

public class Turno {

	private String diaSemana;
	private String horario;
	private int demanda = 3;// apenas pra teste
	
	public int getDemanda() {
		return demanda;
	}
	public void setDemanda(int demanda) {
		this.demanda = demanda;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	
}
