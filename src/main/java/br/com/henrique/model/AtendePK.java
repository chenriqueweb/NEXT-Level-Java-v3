package br.com.henrique.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import io.swagger.annotations.ApiModelProperty;

@Embeddable
public class AtendePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Código do CEP Requisitado", required = true)
	private String cepRequisitado; // "cepRequisitado: 29665000",

	@ApiModelProperty(value = "Código da Empresa que Atende", required = true)
	private Integer empresaAtende; // "empresaAtende: 21",

	@ApiModelProperty(value = "Código da Filial que Atende", required = true)
	private Integer filialAtende; // "filialAtende: 1640",

	public AtendePK() {
		super();
	}

	public AtendePK(String cepRequisitado, Integer empresaAtende, Integer filialAtende) {
		super();
		this.cepRequisitado = cepRequisitado;
		this.empresaAtende = empresaAtende;
		this.filialAtende = filialAtende;
	}

	public String getCepRequisitado() {
		return cepRequisitado;
	}
	public void setCepRequisitado(String cepRequisitado) {
		this.cepRequisitado = cepRequisitado;
	}
	public Integer getEmpresaAtende() {
		return empresaAtende;
	}
	public void setEmpresaAtende(Integer empresaAtende) {
		this.empresaAtende = empresaAtende;
	}
	public Integer getFilialAtende() {
		return filialAtende;
	}
	public void setFilialAtende(Integer filialAtende) {
		this.filialAtende = filialAtende;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cepRequisitado, empresaAtende, filialAtende);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtendePK other = (AtendePK) obj;
		return Objects.equals(cepRequisitado, other.cepRequisitado)
				&& Objects.equals(empresaAtende, other.empresaAtende)
				&& Objects.equals(filialAtende, other.filialAtende);
	}
	
}
