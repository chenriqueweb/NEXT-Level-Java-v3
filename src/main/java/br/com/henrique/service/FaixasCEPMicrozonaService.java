package br.com.henrique.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.henrique.dto.FaixasCEPMicrozonaDto;
import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.FaixasCEPMicrozonaPK;
import br.com.henrique.repository.FaixasCEPMicrozonaRepository;
import br.com.henrique.service.exception.ObjectFoundException;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class FaixasCEPMicrozonaService {

    @Autowired
    private FaixasCEPMicrozonaRepository repositFaixasCEPMicrozona;
    
    // Lista Faixas de CEPs da Microzona
    public List<FaixasCEPMicrozona> findAll() {
        List<FaixasCEPMicrozona> faixasCEPMicrozona = repositFaixasCEPMicrozona.findAll();
         
        return faixasCEPMicrozona;
    }
    
    // Lista de Faixas de CEP Microzona com Paginação
    public Page<FaixasCEPMicrozona> findAllPage(Pageable pageable) {
        return repositFaixasCEPMicrozona.findAll(pageable);
    }      
    
    // Busca por Faixas de CEP da Microzona
    public FaixasCEPMicrozona findById(FaixasCEPMicrozonaPK faixasCEPMicrozonaPK) {
        FaixasCEPMicrozona faixasCEPMicrozonaBusca2 = repositFaixasCEPMicrozona.findById(faixasCEPMicrozonaPK).orElse(null);
        if (faixasCEPMicrozonaBusca2 == null) {
            throw new ObjectNotFoundException("Faixa de CEP nao encontrada !");
        }
        return faixasCEPMicrozonaBusca2;
    }    
    
    
    // Inclui Faixas de CEP da Microzona - DTO
    public FaixasCEPMicrozona addFaixasCEPMicrozona(FaixasCEPMicrozonaDto faixasCEPMicrozonaDto) {
        FaixasCEPMicrozona faixasCEPMicrozonaBuscaID = repositFaixasCEPMicrozona.findById(faixasCEPMicrozonaDto.getFaixasCEPMicrozonaPK()).orElse(null);
        if (faixasCEPMicrozonaBuscaID != null) {
            throw new ObjectFoundException("Faixa de CEP já cadastrada !");
        }    	
        
//        List<FaixasCEPMicrozona> faixasCEPMicrozonaBuscaCEPInicial = repositFaixasCEPMicrozona.findByCEPinicialGreaterThanEqualAndCEPfinalLessThanEqual(faixasCEPMicrozona.getCEPinicial(), faixasCEPMicrozona.getCEPinicial());
//        if (faixasCEPMicrozonaBuscaCEPInicial != null) {
//            throw new ObjectFoundException("Faixa de CEP já cadastrada !");
//        }    	
//        
//        List<FaixasCEPMicrozona> faixasCEPMicrozonaBuscaCEPFinal = repositFaixasCEPMicrozona.findByCEPinicialGreaterThanEqualAndCEPfinalLessThanEqual(faixasCEPMicrozona.getCEPfinal(), faixasCEPMicrozona.getCEPfinal());
//        if (faixasCEPMicrozonaBuscaCEPFinal != null) {
//            throw new ObjectFoundException("Faixa de CEP já cadastrada !");
//        }            
        
        return repositFaixasCEPMicrozona.save(faixasCEPMicrozonaDto.converteToEntity());
    }

    
    // Atualiza Faixas de CEP da Microzona - DTO
    public void updateFaixasCEPMicrozona(FaixasCEPMicrozonaPK faixasCEPMicrozonaPK, 
                                         FaixasCEPMicrozonaDto faixasCEPMicrozonaDto) {
        FaixasCEPMicrozona faixasCEPMicrozonaAtualizado = findById(faixasCEPMicrozonaPK);
        if (faixasCEPMicrozonaAtualizado == null) {
            throw new ObjectNotFoundException("Faixa de CEP nao encontrada !");
        }
        
        faixasCEPMicrozonaAtualizado.setCEPinicial(faixasCEPMicrozonaDto.getCEPinicial());
        faixasCEPMicrozonaAtualizado.setCEPfinal(faixasCEPMicrozonaDto.getCEPfinal());
        
        repositFaixasCEPMicrozona.save(faixasCEPMicrozonaAtualizado);
    }
    
    // Exclusão da Faixa de CEP da Microzona
    public void deletaFaixasCEPMicrozona(FaixasCEPMicrozonaPK faixasCEPMicrozonaPK) {
        FaixasCEPMicrozona faixasCEPMicrozonaExcluir = findById(faixasCEPMicrozonaPK);
        if (faixasCEPMicrozonaExcluir == null) {
            throw new ObjectNotFoundException("Faixa de CEP nao encontrada !");
        }

        repositFaixasCEPMicrozona.deleteById(faixasCEPMicrozonaPK);
    }
}
