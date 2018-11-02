package br.com.outback.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Turno")
@NamedQuery(name = "Turno.findTurnosAtivos", query = "select t from Turno t where t.isAtivo is true")
public class Turno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String codigo;
	
	private Date abertura;
	private Date fechamento;
	
	private Date intervaloInicio;
	private Date intervalorFim;
	
	private String plantao;

	private String cor;
	
	private Boolean isAtivo;
	
	@Transient
	private String estaAtivo;
	
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public Date getAbertura() {
		return abertura;
	}


	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}


	public Date getFechamento() {
		return fechamento;
	}


	public void setFechamento(Date fechamento) {
		this.fechamento = fechamento;
	}


	public Date getIntervaloInicio() {
		return intervaloInicio;
	}


	public void setIntervaloInicio(Date intervaloInicio) {
		this.intervaloInicio = intervaloInicio;
	}


	public Date getIntervalorFim() {
		return intervalorFim;
	}


	public void setIntervalorFim(Date intervalorFim) {
		this.intervalorFim = intervalorFim;
	}


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}
	

	public Boolean getIsAtivo() {
		return isAtivo;
	}


	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public String getPlantao() {
		return plantao;
	}


	public void setPlantao(String plantao) {
		this.plantao = plantao;
	}

	//* criado apenas pra mostrar no datatable de turno "SIM" ou "N�o"
	public String getEstaAtivo() {
		
		if(this.isAtivo){
			return "Sim";
		}else{
			return "Não";
		}
		
	}


	@Override
	public String toString() {
		return codigo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((abertura == null) ? 0 : abertura.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result
				+ ((estaAtivo == null) ? 0 : estaAtivo.hashCode());
		result = prime * result
				+ ((fechamento == null) ? 0 : fechamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((intervaloInicio == null) ? 0 : intervaloInicio.hashCode());
		result = prime * result
				+ ((intervalorFim == null) ? 0 : intervalorFim.hashCode());
		result = prime * result + ((isAtivo == null) ? 0 : isAtivo.hashCode());
		result = prime * result + ((plantao == null) ? 0 : plantao.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turno other = (Turno) obj;
		if (abertura == null) {
			if (other.abertura != null)
				return false;
		} else if (!abertura.equals(other.abertura))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (estaAtivo == null) {
			if (other.estaAtivo != null)
				return false;
		} else if (!estaAtivo.equals(other.estaAtivo))
			return false;
		if (fechamento == null) {
			if (other.fechamento != null)
				return false;
		} else if (!fechamento.equals(other.fechamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intervaloInicio == null) {
			if (other.intervaloInicio != null)
				return false;
		} else if (!intervaloInicio.equals(other.intervaloInicio))
			return false;
		if (intervalorFim == null) {
			if (other.intervalorFim != null)
				return false;
		} else if (!intervalorFim.equals(other.intervalorFim))
			return false;
		if (isAtivo == null) {
			if (other.isAtivo != null)
				return false;
		} else if (!isAtivo.equals(other.isAtivo))
			return false;
		if (plantao == null) {
			if (other.plantao != null)
				return false;
		} else if (!plantao.equals(other.plantao))
			return false;
		return true;
	}


	

	
}
