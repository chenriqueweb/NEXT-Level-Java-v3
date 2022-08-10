package br.com.henrique.dto;

import javax.validation.constraints.NotNull;

import br.com.henrique.model.FaixasCEPMicrozona;
import io.swagger.annotations.ApiModelProperty;

public class FaixasCEPMicrozonaDto {

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

	public FaixasCEPMicrozonaDto(Integer cEPinicial, Integer cEPfinal) {
		super();
		CEPinicial = cEPinicial;
		CEPfinal = cEPfinal;
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
