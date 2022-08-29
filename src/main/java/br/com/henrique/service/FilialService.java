package br.com.henrique.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.henrique.dto.FilialDto;
import br.com.henrique.model.Filial;
import br.com.henrique.model.FilialPK;
import br.com.henrique.model.Municipio;
import br.com.henrique.repository.FilialRepository;
import br.com.henrique.repository.MunicipioRepository;
import br.com.henrique.service.exception.ObjectFoundException;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class FilialService {

    @Autowired
    private FilialRepository repositFilial;
    
    @Autowired
    private MunicipioRepository repositMunicipio;
    
    // Lista Filiais
    public List<Filial> findAll() {
        List<Filial> filiais = repositFilial.findAll();
        
        return filiais;
    }
    
    // Lista de Filiais com Paginação
    public Page<Filial> findAllPage(Pageable pageable) {
        return repositFilial.findAll(pageable);
    }    
    
    // Busca por Filial
    public Filial findById(FilialPK filialPK) {
        Filial filialBusca = repositFilial.findById(filialPK).orElse(null);
        if (filialBusca == null) {
            throw new ObjectNotFoundException("Filial nao encontrada !");
        }
        return filialBusca;
    }
    
    // Inclui Filial - DTO
    public Filial addFilial(FilialDto filialDto) {
        Filial filialBuscaID = repositFilial.findById(filialDto.getFilialPK()).orElse(null);
        if (filialBuscaID != null) {
            throw new ObjectFoundException("Filial já cadastrada !");
        } 	
        
        Filial filialBuscaCNPJ = repositFilial.findByCnpj(filialDto.getCnpj());
        if (filialBuscaCNPJ != null) {
            throw new ObjectFoundException("CNPJ informado já encontra-se cadastrado para outra Filial !");
        } 	        
        
        Municipio municipioBuscaID = repositMunicipio.findById(filialDto.getMunicipio()).orElse(null);
        if (municipioBuscaID == null) {
            throw new ObjectNotFoundException("Municipio nao encontrado !");
        }          
        
        return repositFilial.save(filialDto.converteToEntity());
    }
    
    // Atualiza Filial - DTO
	public void updateFilial(FilialPK filialPK, 
                             FilialDto filialDto) {
        Filial filialAtualizado = repositFilial.findById(filialPK).orElse(null);
        if (filialAtualizado == null) {
            throw new ObjectNotFoundException("Filial nao encontrada !");
        } 	   
        
        Filial filialBuscaCNPJ = repositFilial.findByCnpj(filialDto.getCnpj());
        if (filialBuscaCNPJ != null) {
            throw new ObjectFoundException("CNPJ informado já encontra-se cadastrado para outra Filial !");
        } 	         
        
        Municipio municipioBuscaID = repositMunicipio.findById(filialDto.getMunicipio()).orElse(null);
        if (municipioBuscaID == null) {
            throw new ObjectNotFoundException("Municipio nao encontrado !");
        }  
        
        filialAtualizado.setNome(filialDto.getNome());
        filialAtualizado.setCnpj(filialDto.getCnpj());
        filialAtualizado.setMunicipio(filialDto.getMunicipio());
        filialAtualizado.setCepFilial(filialDto.getCepFilial());
        
        repositFilial.save(filialAtualizado);
    }
    
    // Exclusão de Filial
    public void deletaFilial(FilialPK filialPK) {
        Filial filialExcluir = this.findById(filialPK);
        if (filialExcluir != null) {
            throw new ObjectNotFoundException("Filial nao encontrada !");
        } 	   
        
        repositFilial.deleteById(filialPK);
    }

}
