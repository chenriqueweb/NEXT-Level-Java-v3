package br.com.henrique.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// @Entity
public class Atende {

	// "endereco": [
	private String  cep;             // "cep: 29665-000",
	private String  logradouro;      // "logradouro: ",
	private String  complemento;     // "complemento: ",
	private String  localidade;      // "localidade: São Roque do Canaã",
	private String  bairro;          // "bairro: ",
	private String  uf;              // "uf: ES",
	private String  ibge;            // "ibge: 3204955"

    private List<AtendeFilial> atendeFilial = new ArrayList<>();

	public Atende() {
		super();
	}

	public Atende(String cep, String logradouro, String complemento, String localidade, String bairro, String uf,
			String ibge, List<AtendeFilial> atendeFilial) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.localidade = localidade;
		this.bairro = bairro;
		this.uf = uf;
		this.ibge = ibge;
		this.atendeFilial = atendeFilial;
	}

	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getIbge() {
		return ibge;
	}
	public void setIbge(String ibge) {
		this.ibge = ibge;
	}
	public List<AtendeFilial> getAtendeFilial() {
		return atendeFilial;
	}
	public void setAtendeFilial(List<AtendeFilial> atendeFilial) {
		this.atendeFilial = atendeFilial;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(atendeFilial, bairro, cep, complemento, ibge, localidade, logradouro, uf);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atende other = (Atende) obj;
		return Objects.equals(atendeFilial, other.atendeFilial) && Objects.equals(bairro, other.bairro)
				&& Objects.equals(cep, other.cep) && Objects.equals(complemento, other.complemento)
				&& Objects.equals(ibge, other.ibge) && Objects.equals(localidade, other.localidade)
				&& Objects.equals(logradouro, other.logradouro) && Objects.equals(uf, other.uf);
	}
	
}