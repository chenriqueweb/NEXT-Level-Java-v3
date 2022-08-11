package br.com.henrique.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.henrique.dto.MicrozonaDto;
import br.com.henrique.model.Estado;
import br.com.henrique.model.Microzona;
import br.com.henrique.model.Municipio;
import br.com.henrique.model.RotaEntrega;
import br.com.henrique.model.RotaEntregaPK;
import br.com.henrique.repository.EstadoRepository;
import br.com.henrique.repository.MicrozonaRepository;
import br.com.henrique.repository.MunicipioRepository;
import br.com.henrique.repository.RotaEntregaRepository;
import br.com.henrique.service.exception.ObjectFoundException;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class MicrozonaService {
    
    @Autowired
    private MicrozonaRepository repositMicrozona;
    
    @Autowired
    private MunicipioRepository repositMunicipio;
    
    @Autowired
    private EstadoRepository repositEstado; 
    
    @Autowired
    private RotaEntregaRepository repositRotaEntrega;        

    // Lista Microzonas
    public List<Microzona> findAll() {
        List<Microzona> microzonas = repositMicrozona.findAll();
        
        return microzonas;
    }
    
    // Lista de Microzonas com Paginação
    public Page<Microzona> findAllPage(Pageable pageable) {
        return repositMicrozona.findAll(pageable);
    }         

    // Busca pela Microzona
    public Microzona findById(Integer codigo) {
        Microzona microzona = repositMicrozona.findById(codigo).orElse(null);
        if (microzona == null) {
            throw new ObjectNotFoundException("Microzona nao encontrada !");
        }
        return microzona;
    }    
    
    // Inclui Microzona - DTO
    public Microzona addMicrozona(MicrozonaDto microzonaDto) {
    	Integer codigoMicrozona = microzonaDto.getCodigo();
    	if (codigoMicrozona == null) { 
    		codigoMicrozona = 0;
    	}
    	
        Microzona microzonaBuscaID = repositMicrozona.findById(codigoMicrozona).orElse(null);
        if (microzonaBuscaID != null) {
            throw new ObjectFoundException("Microzona já cadastrada !");
        }
        
        Estado estado = repositEstado.findById(microzonaDto.getEstadoRota().getSigla()).orElse(null);
        if (estado == null) {
            throw new ObjectNotFoundException("Estado nao encontrado !");
        }            
        
        Municipio municipioBuscaID = repositMunicipio.findById(microzonaDto.getCodigoMunicipio().getCodigo_ID()).orElse(null);;
        if (municipioBuscaID == null) {
            throw new ObjectNotFoundException("Municipio nao encontrado !");
        }         
        
        RotaEntregaPK rotaEntregaPK = new RotaEntregaPK();
        rotaEntregaPK.setCodigoRota(microzonaDto.getCodigoRota());
        rotaEntregaPK.setSiglaEstado(microzonaDto.getEstadoRota().getSigla()); 
        
        RotaEntrega rotaEntregaBusca = repositRotaEntrega.findById(rotaEntregaPK).orElse(null);
        if (rotaEntregaBusca == null) {
            throw new ObjectNotFoundException("Rota de Entrega nao encontrada !"); 
        }        
        
        return repositMicrozona.save(microzonaDto.converteToEntity());
    }    
    
    // Atualiza uma Microzona - DTO
    public void updateMicrozona(Integer codigo, MicrozonaDto microzonaDto) {
        Microzona microzonaAtualizado = this.findById(codigo);
        if (microzonaAtualizado == null) {
            throw new ObjectFoundException("Microzona nao encontrada !");
        }        
        
        Estado estado = repositEstado.findById(microzonaDto.getEstadoRota().getSigla()).orElse(null);
        if (estado == null) {
            throw new ObjectNotFoundException("Estado nao encontrado !");
        }            
        
        Municipio municipioBuscaID = repositMunicipio.findById(microzonaDto.getCodigoMunicipio().getCodigo_ID()).orElse(null);;
        if (municipioBuscaID == null) {
            throw new ObjectNotFoundException("Municipio nao encontrado !");
        }         
        
        RotaEntregaPK rotaEntregaPK = new RotaEntregaPK();
        rotaEntregaPK.setCodigoRota(microzonaDto.getCodigoRota());
        rotaEntregaPK.setSiglaEstado(microzonaDto.getEstadoRota().getSigla()); 
        
        RotaEntrega rotaEntregaBusca = repositRotaEntrega.findById(rotaEntregaPK).orElse(null);
        if (rotaEntregaBusca == null) {
            throw new ObjectNotFoundException("Rota de Entrega nao encontrada !");
        }
        
        microzonaAtualizado.setNome(microzonaDto.getNome());
        microzonaAtualizado.setStatus(microzonaDto.getStatus());
        microzonaAtualizado.setAtendimentoDiario(microzonaDto.getAtendimentoDiario());
        
        microzonaAtualizado.setAtendeSegunda(microzonaDto.getAtendeSegunda());
        microzonaAtualizado.setAtendeTerca(microzonaDto.getAtendeTerca());
        microzonaAtualizado.setAtendeQuarta(microzonaDto.getAtendeQuarta());
        microzonaAtualizado.setAtendeQuinta(microzonaDto.getAtendeQuinta());
        microzonaAtualizado.setAtendeSexta(microzonaDto.getAtendeSexta());
        microzonaAtualizado.setAtendeSabado(microzonaDto.getAtendeSabado());
                
        microzonaAtualizado.setCodigoMunicipio(microzonaDto.getCodigoMunicipio());
        microzonaAtualizado.setEstadoRota(microzonaDto.getEstadoRota());
        microzonaAtualizado.setCodigoRota(microzonaDto.getCodigoRota());
        
        repositMicrozona.save(microzonaAtualizado);
    }    
    
    // Exclusão da Microzona
    public void deletaMicrozona(Integer codigo) {
        Microzona microzonaExcluir = this.findById(codigo);
        if (microzonaExcluir == null) {
            throw new ObjectFoundException("Microzona nao encontrada !");
        }              
        
        repositMicrozona.deleteById(codigo);
    }    
}
