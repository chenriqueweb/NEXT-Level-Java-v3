package br.com.henrique.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.henrique.model.Estado;
import br.com.henrique.model.Microzona;
import br.com.henrique.model.Municipio;
import br.com.henrique.model.RotaEntrega;
import io.swagger.annotations.ApiModelProperty;

public class MicrozonaDto {
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Código da Microzona", required = true)
    private Integer codigo;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Size(min=5, max=50, message="Tamanho inválido! Digite no mínimo {min} e no máximo {max} caracteres")
    @ApiModelProperty(value = "Nome da Microzona", required = true)
    private String nome;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Column(columnDefinition = "Character(1)")
    @ApiModelProperty(value = "Status da Microzona", required = true)
    private String status;

    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Column(columnDefinition = "Character(1)")
    @ApiModelProperty(value = "Atendimento Diário", required = true)
    private String atendimentoDiario;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Column(columnDefinition = "Character(1)")
    @ApiModelProperty(value = "Atendimento - Segunda-feira", required = true)
    private String atendeSegunda;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Column(columnDefinition = "Character(1)")
    @ApiModelProperty(value = "Atendimento - Terça-feira", required = true)
    private String atendeTerca;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Column(columnDefinition = "Character(1)")
    @ApiModelProperty(value = "Atendimento - Quarta-feira", required = true)
    private String atendeQuarta;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Column(columnDefinition = "Character(1)")
    @ApiModelProperty(value = "Atendimento - Quinta-feira", required = true)
    private String atendeQuinta;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Column(columnDefinition = "Character(1)")
    @ApiModelProperty(value = "Atendimento - Sexta-feira", required = true)
    private String atendeSexta;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Column(columnDefinition = "Character(1)")
    @ApiModelProperty(value = "Atendimento - Sábado", required = true)
    private String atendeSabado;
    
    @NotNull(message = "O campo não pode ser nulo")
    @ApiModelProperty(value = "Código da Rota", required = true)
    private Integer codigoRota;
    
    // FK com Estado
    @ManyToOne
    @JoinColumn(name="sigla")    
    private Estado estadoRota;
    
    // FK com Municipio 
    @ManyToOne
    @JoinColumn(name="codigo_ID")
    private Municipio codigoMunicipio;
    
    // FK com Estado
    @ManyToOne
    @JoinColumn(name="rotaEntregaPK.siglaEstado", referencedColumnName="sigla") 
    private Estado estado; 
    
    // FK com Rota de Entrega
    @ManyToOne
    @JoinColumns({@JoinColumn(updatable = false, insertable = false, 
                                    name = "rotaEntregaPK.siglaEstado", referencedColumnName = "siglaEstado"), 
                  @JoinColumn(updatable = false, insertable = false,
                                    name = "rotaEntregaPK.codigoRota",  referencedColumnName = "codigoRota")
                 })  
    private RotaEntrega rotaEntrega;      


    public MicrozonaDto() {
        super();
    }

    public MicrozonaDto(Integer codigo, String nome, String status, String atendimentoDiario, String atendeSegunda, String atendeTerca,
                    String atendeQuarta, String atendeQuinta, String atendeSexta, String atendeSabado, EstadoDto estadoDto,
                    MunicipioDto municipioDto, Integer codigoRota) {
        super();
        this.codigo = codigo;
        this.nome = nome;
        this.status = status;
        this.atendimentoDiario = atendimentoDiario;
        this.atendeSegunda = atendeSegunda;
        this.atendeTerca = atendeTerca;
        this.atendeQuarta = atendeQuarta;
        this.atendeQuinta = atendeQuinta;
        this.atendeSexta = atendeSexta;
        this.atendeSabado = atendeSabado;
        this.estadoRota = estadoDto.converteToEntity();
        this.codigoMunicipio = municipioDto.converteToEntity();
        this.codigoRota = codigoRota;
    }

    public MicrozonaDto(Microzona microzona) {
    	super();
    	this.codigo = microzona.getCodigo();
    	this.nome = microzona.getNome();
    	this.status = microzona.getStatus();
    	this.atendimentoDiario = microzona.getAtendimentoDiario();
    	this.atendeSegunda = microzona.getAtendeSegunda();
    	this.atendeTerca = microzona.getAtendeTerca();
    	this.atendeQuarta = microzona.getAtendeQuarta();
    	this.atendeQuinta = microzona.getAtendeQuinta();
    	this.atendeSexta = microzona.getAtendeSexta();
    	this.atendeSabado = microzona.getAtendeSabado();
    	this.estadoRota = microzona.getEstadoRota();
    	this.codigoMunicipio = microzona.getCodigoMunicipio();
		this.codigoRota = microzona.getCodigoRota();
    }

//    // Método para identificar registro novo
//    public boolean isNovo() {
//        return nome == null;
//    }

    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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
    public String getAtendimentoDiario() {
        return atendimentoDiario;
    }
    public void setAtendimentoDiario(String atendimentoDiario) {
        this.atendimentoDiario = atendimentoDiario;
    }
    public String getAtendeSegunda() {
        return atendeSegunda;
    }
    public void setAtendeSegunda(String atendeSegunda) {
        this.atendeSegunda = atendeSegunda;
    }
    public String getAtendeTerca() {
        return atendeTerca;
    }
    public void setAtendeTerca(String atendeTerca) {
        this.atendeTerca = atendeTerca;
    }
    public String getAtendeQuarta() {
        return atendeQuarta;
    }
    public void setAtendeQuarta(String atendeQuarta) {
        this.atendeQuarta = atendeQuarta;
    }
    public String getAtendeQuinta() {
        return atendeQuinta;
    }
    public void setAtendeQuinta(String atendeQuinta) {
        this.atendeQuinta = atendeQuinta;
    }
    public String getAtendeSexta() {
        return atendeSexta;
    }
    public void setAtendeSexta(String atendeSexta) {
        this.atendeSexta = atendeSexta;
    }
    public String getAtendeSabado() {
        return atendeSabado;
    }
    public void setAtendeSabado(String atendeSabado) {
        this.atendeSabado = atendeSabado;
    }
    public Estado getEstadoRota() {
        return estadoRota;
    }
    public void setEstadoRota(Estado estadoRota) {
        this.estadoRota = estadoRota;
    }
    public Municipio getCodigoMunicipio() {
        return codigoMunicipio;
    }
    public void setCodigoMunicipio(Municipio codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }
    public Integer getCodigoRota() {
        return codigoRota;
    }
    public void setCodigoRota(Integer codigoRota) {
        this.codigoRota = codigoRota;
    }
    
    // Conversor para atualização da Entidade
    public Microzona converteToEntity() {
    	return new Microzona(this);
    }
}
