package br.com.henrique.dto;

import br.com.henrique.model.Atende;

public class AtendeDto {

	// "endereco": [
	private String  logradouro;      // "logradouro: ",
	private String  complemento;     // "complemento: ",
	private String  localidade;      // "localidade: São Roque do Canaã",
	private String  bairro;          // "bairro: ",
	private String  uf;              // "uf: ES",
	private String  ibge;            // "ibge: 3204955"

	//	"response": [
	private String  cepRequisitado;  // "cepRequisitado: 29665000",
	private Integer empresaAtende;   // "empresaAtende: 21",
	private Integer filialAtende;    // "filialAtende: 1640",
	private String  nomeFilial;      // "nomeFilial: SERRA-JD.LIMOEIRO - DEPOSITO",
	private String  cnpjFilial;      // "cnpjFilial: 33041260099195",
	private Integer microzona;       // "microzona: 40",
	private String  ufRota;          // "ufRota: ES",
	private Integer codigoRota;      // "codigoRota: 12",
	private Integer codigoMunicipio; // "codigoMunicipio: 27545",
	private String  municipio;       // "municipio: São Roque do Canaã",
	private String  estado;          // "estado: ES",
	private String  nomeEstado;      // "nomeEstado: Espirito Santo"
	
	
	public AtendeDto() {
		super();
	}


	public AtendeDto(String logradouro, String complemento, String localidade, String bairro, String uf,
			String ibge, String cepRequisitado, Integer empresaAtende, Integer filialAtende, String nomeFilial,
			String cnpjFilial, Integer microzona, String ufRota, Integer codigoRota, Integer codigoMunicipio,
			String municipio, String estado, String nomeEstado) {
		super();
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.localidade = localidade;
		this.bairro = bairro;
		this.uf = uf;
		this.ibge = ibge;
		this.cepRequisitado = cepRequisitado;
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
	public String getCepRequisitado() {
		return cepRequisitado;
	}
	public void setCepRequisitado(String string) {
		this.cepRequisitado = string;
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
	
    
    public Atende converteToEntity() {
    	return new Atende(this);
    }
	
}
