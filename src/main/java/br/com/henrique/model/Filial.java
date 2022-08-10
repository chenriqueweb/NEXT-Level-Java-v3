package br.com.henrique.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.com.henrique.dto.FilialDto;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Filial {
    
    @EmbeddedId
    @ApiModelProperty(value = "Chave para Filial", required = true)
    private FilialPK filialPK;
    
    @ApiModelProperty(value = "Nome da Filial", required = true)
    private String nome;
    
    @ApiModelProperty(value = "CNPJ da Filial", required = true)
    private String cnpj;
    
    @ApiModelProperty(value = "Número do Município", required = true)
    private Integer municipio;
    
    public Filial() {
        super();
    }
    
    public Filial(FilialDto filialDto) {
        this.filialPK = null;
        this.nome = filialDto.getNome();
        this.cnpj = filialDto.getCnpj();
        this.municipio = filialDto.getMunicipio();
    }

    public Filial(FilialPK filialPK, String nome, String cnpj, Integer municipio) {
        super();
        this.filialPK = filialPK;
        this.nome = nome;
        this.cnpj = cnpj;
        this.municipio = municipio;
    }
    
    // Método para identificar registro novo
    public boolean isNovo() {
        return nome == null;
    }     
    
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
        result = prime * result + ((filialPK == null) ? 0 : filialPK.hashCode());
        result = prime * result + ((municipio == null) ? 0 : municipio.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Filial other = (Filial) obj;
        if (cnpj == null) {
            if (other.cnpj != null)
                return false;
        } else if (!cnpj.equals(other.cnpj))
            return false;
        if (filialPK == null) {
            if (other.filialPK != null)
                return false;
        } else if (!filialPK.equals(other.filialPK))
            return false;
        if (municipio == null) {
            if (other.municipio != null)
                return false;
        } else if (!municipio.equals(other.municipio))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

}
