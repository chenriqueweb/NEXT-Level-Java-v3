package br.com.henrique.dto;

import javax.persistence.EmbeddedId;
import javax.validation.constraints.NotNull;

import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.FaixasCEPMicrozonaPK;
import io.swagger.annotations.ApiModelProperty;

public class FaixasCEPMicrozonaDto {
	
    @EmbeddedId
    @ApiModelProperty(value = "Chave para Rota Faixa de CEP Microzona", required = true)
    private FaixasCEPMicrozonaPK faixasCEPMicrozonaPK;

    @NotNull(message = "O campo não pode ser nulo")
    //@Pattern(regexp = "\\d{5}-\\d{3})")
    @ApiModelProperty(value = "CEP Inicial", required = true)
    private Integer CEPinicial;
    
    @NotNull(message = "O campo não pode ser nulo")
    //@Pattern(regexp = "\\d{5}-\\d{3})")
    @ApiModelProperty(value = "CEP Final", required = true)
    private Integer CEPfinal;
    
    // Método para identificar registro novo
    public boolean isNovo() {
        return CEPinicial == 0;
    }

    // Construtor
    public FaixasCEPMicrozonaDto() {
        super();
    }

	public FaixasCEPMicrozonaDto(FaixasCEPMicrozonaPK faixasCEPMicrozonaPK, Integer cEPinicial, Integer cEPfinal) {
		super();
		this.faixasCEPMicrozonaPK = faixasCEPMicrozonaPK;
		this.CEPinicial = cEPinicial;
		this.CEPfinal = cEPfinal;
	}
	
	public FaixasCEPMicrozonaPK getFaixasCEPMicrozonaPK() {
		return faixasCEPMicrozonaPK;
	}
	public void setFaixasCEPMicrozonaPK(FaixasCEPMicrozonaPK faixasCEPMicrozonaPK) {
		this.faixasCEPMicrozonaPK = faixasCEPMicrozonaPK;
	}
	public Integer getCEPinicial() {
		return CEPinicial;
	}
	public void setCEPinicial(Integer cEPinicial) {
		CEPinicial = cEPinicial;
	}
	public Integer getCEPfinal() {
		return CEPfinal;
	}
	public void setCEPfinal(Integer cEPfinal) {
		CEPfinal = cEPfinal;
	}
	
    public FaixasCEPMicrozona converteToEntity() {
    	return new FaixasCEPMicrozona(this);
    }
}
