package br.com.henrique.dto;

import javax.persistence.EmbeddedId;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.henrique.model.Filial;
import br.com.henrique.model.FilialPK;
import io.swagger.annotations.ApiModelProperty;

public class FilialDto {
	
    @EmbeddedId
    @ApiModelProperty(value = "Chave para Filial", required = true)
    private FilialPK filialPK;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Size(min=5, max=50, message="Tamanho inválido! Digite no mínimo {min} e no máximo {max} caracteres")
    @ApiModelProperty(value = "Nome da Filial", required = true)
    private String nome;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Size(min=14, max=14, message="Tamanho inválido! Digite no mínimo {min} e no máximo {max} caracteres")
    @ApiModelProperty(value = "CNPJ da Filial", required = true)
    private String cnpj;
    
    @NotNull(message = "O campo não pode ser nulo")
    @ApiModelProperty(value = "Número do Município", required = true)
    private Integer municipio;
    
    @NotNull(message = "O campo não pode ser nulo")
    @ApiModelProperty(value = "CEP da Filial", required = true)
    private String cepFilial;
    
    public FilialDto() {
        super();
    }

    public FilialDto(FilialPK filialPK, String nome, String cnpj, Integer municipio, String cepFilial) {
        super();
        this.filialPK = filialPK;
        this.nome = nome;
        this.cnpj = cnpj;
        this.municipio = municipio;
        this.cepFilial = cepFilial;
    }
    
    public FilialDto(Filial filial) {
        super();
        this.filialPK = filial.getFilialPK();
        this.nome = filial.getNome();
        this.cnpj = filial.getCnpj();
        this.municipio = filial.getMunicipio();
        this.cepFilial = filial.getCepFilial();
    }
    
//    // Método para identificar registro novo
//    public boolean isNovo() {
//        return nome == null;
//    }     
    
    public FilialPK getFilialPK() {
        return filialPK;
    }
    public void setFilialPK(FilialPK filialPK) {
        this.filialPK = filialPK;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public Integer getMunicipio() {
        return municipio;
    }
    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }
    public String getCepFilial() {
        return cepFilial;
    }
    public void setCepFilial(String cepFilial) {
        this.cepFilial = cepFilial;
    }
    
    // Conversor para atualização do DTO
    public Filial converteToEntity() {
    	return new Filial(this);
    }
}
