package br.com.henrique.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.henrique.model.Filial;
import io.swagger.annotations.ApiModelProperty;

public class FilialDto {
    
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
    
    public FilialDto() {
        super();
    }

    public FilialDto(String nome, String cnpj, Integer municipio) {
        super();
        this.nome = nome;
        this.cnpj = cnpj;
        this.municipio = municipio;
    }
    
    // Método para identificar registro novo
    public boolean isNovo() {
        return nome == null;
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
    
    public Filial converteToEntity() {
    	return new Filial(this);
    }
}
