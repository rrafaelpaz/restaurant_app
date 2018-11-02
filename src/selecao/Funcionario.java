package selecao;

public class Funcionario {
	
	private String nome;
	private DomingosTrabalhados domingosTrabalhos;
	private UltimoDiaSemanaTrabalhado ultimoDiaSemanaTrabalho;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public DomingosTrabalhados getDomingosTrabalhos() {
		return domingosTrabalhos;
	}

	public void setDomingosTrabalhos(DomingosTrabalhados domingosTrabalhos) {
		this.domingosTrabalhos = domingosTrabalhos;
	}

	public UltimoDiaSemanaTrabalhado getUltimoDiaSemanaTrabalho() {
		return ultimoDiaSemanaTrabalho;
	}

	public void setUltimoDiaSemanaTrabalho(
			UltimoDiaSemanaTrabalhado ultimoDiaSemanaTrabalho) {
		this.ultimoDiaSemanaTrabalho = ultimoDiaSemanaTrabalho;
	}
	
	
	

}
