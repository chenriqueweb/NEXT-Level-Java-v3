package br.com.henrique.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.henrique.model.Municipio;
import io.swagger.annotations.ApiModelProperty;

public class MunicipioDto {
	
    // @GeneratedValue(strategy = GenerationType.IDENTITY)  // Usando sequencia do DB2
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @NotNull(message = "O campo não pode ser nulo")
    @ApiModelProperty(value = "Código do Município", required = true)
    private Integer codigo_ID;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Size(min=4, max=50, message="Tamanho inválido! Digite no mínimo {min} e no máximo {max} caracteres")
    @ApiModelProperty(value = "Nome do Município", required = true)
    private String nome;
    
    // FK com Estado
    private EstadoDto estado;
    
    public MunicipioDto(Integer codigo_ID, String nome, EstadoDto estadoDto) {
        super();
        this.codigo_ID = codigo_ID;
        this.nome = nome;
        this.estado = estadoDto;
    }
    
    public MunicipioDto(Municipio municipio) {
        super();
        this.codigo_ID = municipio.getCodigo_ID();
        this.nome = municipio.getNome();
        this.estado = municipio.getEstado().converteToDto();
    }

//    // Método para identificar registro novo
//    public boolean isNovo() {
//        return nome == null;
//    }    
    
    public Integer getCodigo_ID() {
        return codigo_ID;
    }
    public void setCodigo_ID(Integer codigo_ID) {
        this.codigo_ID = codigo_ID;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public EstadoDto getEstado() {
        return estado;
    }
    public void setEstado(EstadoDto estado) {
        this.estado = estado;
    }

    // Conversor para atualização da Entidade
    public Municipio converteToEntity() {
    	return new Municipio(this);
    }
}
