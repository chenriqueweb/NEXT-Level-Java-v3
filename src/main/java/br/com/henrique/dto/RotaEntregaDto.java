package br.com.henrique.dto;

import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.henrique.model.Estado;
import br.com.henrique.model.Filial;
import br.com.henrique.model.RotaEntrega;
import br.com.henrique.model.RotaEntregaPK;
import io.swagger.annotations.ApiModelProperty;

public class RotaEntregaDto {
	
    @EmbeddedId
    @ApiModelProperty(value = "Chave para Rota de Entrega", required = true)
    private RotaEntregaPK rotaEntregaPK;   
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "Você precisa informar algo")
    @Size(min=5, max=50, message="Tamanho inválido! Digite no mínimo {min} e no máximo {max} caracteres")
    @ApiModelProperty(value = "Nome da Rota de Entrega", required = true)
    private String nome;

    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "Você precisa informar algo")
    @Size(min=1, max=1)
    @ApiModelProperty(value = "Status da Rota de Entrega", required = true)
    private String status;
    
    @NotNull(message = "O campo não pode ser nulo")
    @ApiModelProperty(value = "Código da Empresa", required = true)
    private Integer codigoEmpresa;
    
    @NotNull(message = "O campo não pode ser nulo")
    @ApiModelProperty(value = "Código da Filial", required = true)
    private Integer codigoFilial;
    
    @ApiModelProperty(value = "Prazo para Expedição", required = false)
    private Integer prazoExpedicao;
    
    // FK com Estado
    @ManyToOne
    @JoinColumn(name="rotaEntregaPK.siglaEstado", referencedColumnName="sigla") 
    private Estado estado; 
    
    // FK com Filial
    @ManyToOne
    @JoinColumns({@JoinColumn(updatable = false, insertable = false, 
                                    name = "filialPK.codigoEmpresa", referencedColumnName = "codigoEmpresa"), 
                  @JoinColumn(updatable = false, insertable = false,
                                    name = "filialPK.codigoFilial",  referencedColumnName = "codigoFilial")
                 })  
    private Filial filial;    
    
    
    public RotaEntregaDto() {
        super();
    }
    
    public RotaEntregaDto(RotaEntregaPK rotaEntregaPK, String nome, String status, Integer codigoEmpresa, Integer codigoFilial,
                    Integer prazoExpedicao) {
        super();
        this.rotaEntregaPK = rotaEntregaPK;
        this.nome = nome;
        this.status = status;
        this.codigoEmpresa = codigoEmpresa;
        this.codigoFilial = codigoFilial;
        this.prazoExpedicao = prazoExpedicao;
    }
    
    public RotaEntregaDto(RotaEntrega rotaEntrega) {
    	this.rotaEntregaPK = rotaEntrega.getRotaEntregaPK();
    	this.nome = rotaEntrega.getNome();
    	this.status = rotaEntrega.getStatus();
    	this.codigoEmpresa = rotaEntrega.getCodigoEmpresa();
    	this.codigoFilial = rotaEntrega.getCodigoFilial();
    	this.prazoExpedicao = rotaEntrega.getPrazoExpedicao();
    }

//    // Método para identificar registro novo
//    public boolean isNovo() {
//        return nome == null;
//    }

    public RotaEntregaPK getRotaEntregaPK() {
        return rotaEntregaPK;
    }
    public void setRotaEntregaPK(RotaEntregaPK rotaEntregaPK) {
        this.rotaEntregaPK = rotaEntregaPK;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getCodigoEmpresa() {
        return codigoEmpresa;
    }
    public void setCodigoEmpresa(Integer codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }
    public Integer getCodigoFilial() {
        return codigoFilial;
    }
    public void setCodigoFilial(Integer codigoFilial) {
        this.codigoFilial = codigoFilial;
    }
    public Integer getPrazoExpedicao() {
        return prazoExpedicao;
    }
    public void setPrazoExpedicao(Integer prazoExpedicao) {
        this.prazoExpedicao = prazoExpedicao;
    }

    // Conversor para atualização da Entidade
    public RotaEntrega converteToEntity() {
    	return new RotaEntrega(this);
    }
}
