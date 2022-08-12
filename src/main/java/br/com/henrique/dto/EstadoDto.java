package br.com.henrique.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.henrique.model.Estado;
import io.swagger.annotations.ApiModelProperty;

public class EstadoDto {
	
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @NotNull(message = "O campo não pode ser nulo")
    @ApiModelProperty(value = "Sigla da Unidade Federativa", required = true)
    private String sigla;
	
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @NotNull(message = "O campo não pode ser nulo")
    @Size(min=4, max=50, message="Tamanho inválido! Digite no mínimo {min} e no máximo {max} caracteres")
    @ApiModelProperty(value = "Nome do Estado", required = true)
    private String nome;

    // Construtores da Class
    public EstadoDto() {
        super();
    }
    
    public EstadoDto(String sigla, String nome) {
        super();
        this.sigla = sigla;
        this.nome = nome;
    }
    
    public EstadoDto(Estado estado) {
        this.sigla = estado.getSigla();
        this.nome = estado.getNome();
    }
    
//    // Método para identificar registro novo
//    public boolean isNovo() {
//        return nome == null;
//    }
     
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
 // Conversor para atualização da Entidade
    public Estado converteToEntity() {
    	return new Estado(this);
    }
}
