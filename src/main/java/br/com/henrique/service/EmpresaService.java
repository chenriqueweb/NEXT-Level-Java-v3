package br.com.henrique.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.henrique.dto.EmpresaDto;
import br.com.henrique.model.Empresa;
import br.com.henrique.repository.EmpresaRepository;
import br.com.henrique.service.exception.ObjectFoundException;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository repositEmpresa;

    // Lista Empresas
    public List<Empresa> findAll() {
        List<Empresa> empresas = repositEmpresa.findAll();
        
        return empresas;
    }
    
    // Lista Empresas com Paginação
    public Page<Empresa> findAllPage(Pageable pageable) {
        return repositEmpresa.findAll(pageable);
    }
 
    // Busca pela Empresa
    public Empresa findById(Integer codigo) {
        Empresa empresa = repositEmpresa.findById(codigo).orElse(null);
        if (empresa == null) {
            throw new ObjectNotFoundException("Empresa nao encontrada !");
        }
        return empresa;
    }

    // Inclui Empresa - DTO
    public Empresa addEmpresa(EmpresaDto empresaDto) {
        Empresa empresaBuscaID = repositEmpresa.findById(empresaDto.getCodigo()).orElse(null);
        if (empresaBuscaID != null) {
            throw new ObjectFoundException("Empresa já encontra-se cadastrada !");
        }         	
        
        Empresa empresaBuscaCNPJ = repositEmpresa.findByraizCNPJ(empresaDto.getRaizCNPJ());
        if (empresaBuscaCNPJ != null) {
            throw new ObjectFoundException("CNPJ informado já encontra-se cadastrado para outra Empresa !");
        }         
        return repositEmpresa.save(empresaDto.converteToEntity());
    }

    // Atualiza uma Empresa - DTO
    public void updateEmpresa(Integer codigo, EmpresaDto empresaDto) {
        Empresa empresaAtualizado = this.findById(codigo);
        if (empresaAtualizado == null) {
            throw new ObjectNotFoundException("Empresa nao encontrada !");
        }
        
        Empresa empresaBuscaCNPJ = repositEmpresa.findByraizCNPJ(empresaDto.getRaizCNPJ());
        if (empresaBuscaCNPJ != null) {
            throw new ObjectFoundException("CNPJ informado já encontra-se cadastrado para outra Empresa !");
        }        
        
        empresaAtualizado.setRazaoSocial(empresaDto.getRazaoSocial());
        empresaAtualizado.setRaizCNPJ(empresaDto.getRaizCNPJ());
        empresaAtualizado.setDataAbertura(empresaDto.getDataAbertura());
        
        repositEmpresa.save(empresaAtualizado);
    }

    // Exclusão de Empresa
    public void deletaEmpresa(Integer codigo) {
        Empresa empresaExcluir = this.findById(codigo);
        if (empresaExcluir == null) {
            throw new ObjectNotFoundException("Empresa nao encontrada !");
        }     

        repositEmpresa.deleteById(codigo);
    }
    
}
