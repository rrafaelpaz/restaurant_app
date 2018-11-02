package selecao;

public class FuncionarioTurno {
	
	private Funcionario funcionario;
	private int numPreferencia;
	private int numRestricao;
	private int numResultado;
	private boolean estaEscalado;
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public int getNumPreferencia() {
		return numPreferencia;
	}
	public void setNumPreferencia(int numPreferencia) {
		this.numPreferencia = numPreferencia;
	}
	public int getNumRestricao() {
		return numRestricao;
	}
	public void setNumRestricao(int numRestricao) {
		this.numRestricao = numRestricao;
	}
	public int getNumResultado() {
		return numResultado;
	}
	public void setNumResultado(int numResultado) {
		this.numResultado = numResultado;
	}
	public boolean isEstaEscalado() {
		return estaEscalado;
	}
	public void setEstaEscalado(boolean estaEscalado) {
		this.estaEscalado = estaEscalado;
	}
	
	
	
	
}
