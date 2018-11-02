package br.com.outback.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import br.com.outback.util.DataUtil;

@Entity
@NamedQuery(name = "Plantao.findPlantoesPorData", query = "select p from Plantao p where p.data between :dataInicio and :dataFim order by p.data asc")
public class Plantao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2747915828116673455L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Integer demanda;
    
    private Calendar data;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "turno_id")
	private Turno turno;
	
	@ManyToMany
    @JoinTable(name="Plantao_Funcionario", joinColumns={@JoinColumn(name="plantao_id")}, 
    inverseJoinColumns={@JoinColumn(name="funcionario_id")})
	private Set<Funcionario> funcionarios;
	
	//* Criada apenas para mostrar no formato dd/MM/yyyy no datatable, pois o mesmo nao aceita Calendar, apenas Date
	@Transient
	private Date dataTableDate;
	//* Criado para mostrar no dataTable o dia da semana
	@Transient
	private String diaSemana;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDemanda() {
		return demanda;
	}

	public void setDemanda(Integer demanda) {
		this.demanda = demanda;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Set<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Set<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public Date getDataTableDate() {
		
		long milisegundos = getData().getTimeInMillis();
		
		Date data = new Date(milisegundos);
		
		dataTableDate = data;
		
		return dataTableDate;
	}

	public String getDiaSemana() {
		//* busca o dia da semana
		diaSemana = DataUtil.retornaDiaSemana(getData().get(Calendar.DAY_OF_WEEK));
		return diaSemana;
	}

	
	

}
