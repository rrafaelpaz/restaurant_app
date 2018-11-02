package br.com.outback.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQuery(name = "Preferencia.findPreferenciaFuncionario", query = "select p from Preferencia p where p.funcionario= :funcionario and p.data= :data")
public class Preferencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Calendar data;
	private Date inicio;
	private Date fim;
	private Integer penalidade;
	private String descricao;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;
	
	@Transient
	Date dataDataTable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Integer getPenalidade() {
		return penalidade;
	}

	public void setPenalidade(Integer penalidade) {
		this.penalidade = penalidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataDataTable() {
		
		long milisegundos = getData().getTimeInMillis();
		
		Date data = new Date(milisegundos);
		
		dataDataTable = data;
	
		return dataDataTable;
	}
	
	
	
}
