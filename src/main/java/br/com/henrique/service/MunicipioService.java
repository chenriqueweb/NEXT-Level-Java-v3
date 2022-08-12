package br.com.henrique.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.henrique.dto.MunicipioDto;
import br.com.henrique.model.Municipio;
import br.com.henrique.repository.MunicipioRepository;
import br.com.henrique.service.exception.ObjectFoundException;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class MunicipioService {
    
    @Autowired
    private MunicipioRepository repositMunicipio;
    
    @Autowired
    private EstadoService estadoService;
    
    // Lista Municipio
    public List<Municipio> findAll() {
        List<Municipio> municipios = repositMunicipio.findAll();
        
        return municipios;
    }
    
    // Lista Municipio por Estado
    public List<Municipio> findAllByEstado(String sigla) {
        List<Municipio> municipios = repositMunicipio.findByEstado(estadoService.findById(sigla));        
        
        return municipios;
    }    
    
    // Lista Municipios com Paginação
    public Page<Municipio> findAllPage(Pageable pageable) {
        return repositMunicipio.findAll(pageable);
    }    

    // Busca pelo Municipio
    public Municipio findById(Integer codigo) {
        Municipio municipio = repositMunicipio.findById(codigo).orElse(null);
        if (municipio == null) {
            throw new ObjectNotFoundException("Municipio nao encontrado !");
        }
        return municipio;
    }
    
    // Inclui Municipio - DTO
    public Municipio addMunicipio(MunicipioDto municipioDto) {
        Municipio municipioBuscaID = repositMunicipio.findById(municipioDto.getCodigo_ID()).orElse(null);
        if (municipioBuscaID != null) {
            throw new ObjectFoundException("Municipio já cadastrado !");
        }
        
        return repositMunicipio.save(municipioDto.converteToEntity());
    }
    
    // Atualiza um Municipio - DTO
    public void updateMunicipio(Integer codigo, MunicipioDto municipioDto) {
        Municipio municipioAtualizado = this.findById(codigo);
        if (municipioAtualizado == null) {
            throw new ObjectNotFoundException("Municipio nao encontrado !");
        }        
        municipioAtualizado.setNome(municipioDto.getNome());
        municipioAtualizado.setEstado(municipioDto.getEstado().converteToEntity());
        
        repositMunicipio.save(municipioAtualizado);
    }    
    
    // Exclusão de Municipio
    public void deletaMunicipio(Integer codigo) {
        Municipio municipioExcluir = this.findById(codigo);
        if (municipioExcluir == null) {
            throw new ObjectNotFoundException("Municipio nao encontrado !");
        }          
        
        repositMunicipio.deleteById(codigo);
    }
}
