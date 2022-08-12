package br.com.henrique.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.com.henrique.dto.AtendeDto;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Atende {

    @EmbeddedId
    @ApiModelProperty(value = "Chave para Atende", required = true)
    private AtendePK atendePK;
//	private String  cepRequisitado;  // "cepRequisitado: 29665000",
//	private Integer empresaAtende;   // "empresaAtende: 21",
//	private Integer filialAtende;    // "filialAtende: 1640",
	
	
	// "endereco": [
	private String  cep;             // "cep: 29665-000",
	private String  logradouro;      // "logradouro: ",
	private String  complemento;     // "complemento: ",
	private String  localidade;      // "localidade: São Roque do Canaã",
	private String  bairro;          // "bairro: ",
	private String  uf;              // "uf: ES",
	private String  ibge;            // "ibge: 3204955"

	//	"response": [
	private String  nomeFilial;      // "nomeFilial: SERRA-JD.LIMOEIRO - DEPOSITO",
	private String  cnpjFilial;      // "cnpjFilial: 33041260099195",
	private Integer microzona;       // "microzona: 40",
	private String  ufRota;          // "ufRota: ES",
	private Integer codigoRota;      // "codigoRota: 12",
	private Integer codigoMunicipio; // "codigoMunicipio: 27545",
	private String  municipio;       // "municipio: São Roque do Canaã",
	private String  estado;          // "estado: ES",
	private String  nomeEstado;      // "nomeEstado: Espirito Santo"
	
	
	public Atende() {
		super();
	}

    public Atende(AtendeDto atendeDto) {
		this.cep = null;
		this.logradouro = atendeDto.getLogradouro();
		this.complemento = atendeDto.getComplemento();
		this.localidade = atendeDto.getLocalidade();
		this.bairro = atendeDto.getBairro();
		this.uf = atendeDto.getUf();
		this.ibge = atendeDto.getIbge();
		this.atendePK = atendeDto.getAtendePK();
//		this.cepRequisitado = atendeDto.getCepRequisitado();
//		this.empresaAtende = atendeDto.getEmpresaAtende();
//		this.filialAtende = atendeDto.getFilialAtende();
		this.nomeFilial = atendeDto.getNomeFilial();
		this.cnpjFilial = atendeDto.getCnpjFilial();
		this.microzona = atendeDto.getMicrozona();
		this.ufRota = atendeDto.getUfRota();
		this.codigoRota = atendeDto.getCodigoRota();
		this.codigoMunicipio = atendeDto.getCodigoMunicipio();
		this.municipio = atendeDto.getMunicipio();
		this.estado = atendeDto.getEstado();
		this.nomeEstado = atendeDto.getNomeEstado();  
    } 


	public Atende(AtendePK atendePK, String cep, String logradouro, String complemento, String localidade,
			String bairro, String uf, String ibge, String nomeFilial, String cnpjFilial, Integer microzona,
			String ufRota, Integer codigoRota, Integer codigoMunicipio, String municipio, String estado,
			String nomeEstado) {
		super();
		this.atendePK = atendePK;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.localidade = localidade;
		this.bairro = bairro;
		this.uf = uf;
		this.ibge = ibge;
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


	public AtendePK getAtendePK() {
		return atendePK;
	}
	public void setAtendePK(AtendePK atendePK) {
		this.atendePK = atendePK;
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
	
}
