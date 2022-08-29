package br.com.henrique.model;

import java.util.Objects;

public class AtendeFilial {
	
	private Integer empresaAtende;   // "empresaAtende: 21",
	private Integer filialAtende;    // "filialAtende: 1640",
	private String  nomeFilial;      // "nomeFilial: SERRA-JD.LIMOEIRO - DEPOSITO",
	private String  cnpjFilial;      // "cnpjFilial: 33041260099195",
	private String  cepFilial;       // "cepFilial: 29168074"
	private Integer microzona;       // "microzona: 40",
	private String  ufRota;          // "ufRota: ES",
	private Integer codigoRota;      // "codigoRota: 12",
	private Integer codigoMunicipio; // "codigoMunicipio: 27545",
	private String  municipio;       // "municipio: São Roque do Canaã",
	private String  estado;          // "estado: ES",
	private String  nomeEstado;      // "nomeEstado: Espirito Santo",
	
	public AtendeFilial() {
		super();
	}
	
	
	public AtendeFilial(Integer empresaAtende, Integer filialAtende, String nomeFilial,
			String cnpjFilial, Integer microzona, String ufRota, Integer codigoRota, Integer codigoMunicipio,
			String municipio, String estado, String nomeEstado, String cepFilial, Atende atende) {
		super();
		this.empresaAtende = empresaAtende;
		this.filialAtende = filialAtende;
		this.nomeFilial = nomeFilial;
		this.cnpjFilial = cnpjFilial;
		this.microzona = microzona;
		this.ufRota = ufRota;
		this.codigoRota = codigoRota;
		this.codigoMunicipio = codigoMunicipio;
		this.municipio = municipio;
		this.estado = estado;
		this.nomeEstado = nomeEstado;
		this.cepFilial = cepFilial;
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
	public String getNomeFilial() {
		return nomeFilial;
	}
	public void setNomeFilial(String nomeFilial) {
		this.nomeFilial = nomeFilial;
	}
	public String getCnpjFilial() {
		return cnpjFilial;
	}
	public void setCnpjFilial(String cnpjFilial) {
		this.cnpjFilial = cnpjFilial;
	}
	public String getCepFilial() {
		return cepFilial;
	}
	public void setCepFilial(String cepFilial) {
		this.cepFilial = cepFilial;
	}
	public Integer getMicrozona() {
		return microzona;
	}
	public void setMicrozona(Integer microzona) {
		this.microzona = microzona;
	}
	public String getUfRota() {
		return ufRota;
	}
	public void setUfRota(String ufRota) {
		this.ufRota = ufRota;
	}
	public Integer getCodigoRota() {
		return codigoRota;
	}
	public void setCodigoRota(Integer codigoRota) {
		this.codigoRota = codigoRota;
	}
	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}
	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNomeEstado() {
		return nomeEstado;
	}
	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cnpjFilial, codigoMunicipio, codigoRota, empresaAtende, estado,
				filialAtende, microzona, municipio, nomeEstado, nomeFilial, ufRota, cepFilial);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtendeFilial other = (AtendeFilial) obj;
		return Objects.equals(cnpjFilial, other.cnpjFilial)
				&& Objects.equals(codigoMunicipio, other.codigoMunicipio)
				&& Objects.equals(codigoRota, other.codigoRota) && Objects.equals(empresaAtende, other.empresaAtende)
				&& Objects.equals(estado, other.estado) && Objects.equals(filialAtende, other.filialAtende)
				&& Objects.equals(microzona, other.microzona) && Objects.equals(municipio, other.municipio)
				&& Objects.equals(nomeEstado, other.nomeEstado) && Objects.equals(nomeFilial, other.nomeFilial)
				&& Objects.equals(ufRota, other.ufRota) && Objects.equals(cepFilial, other.cepFilial);
	}

}
