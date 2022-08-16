package br.com.henrique.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.ViaCepClient;
import br.com.henrique.model.Atende;
import br.com.henrique.model.Cep;
import br.com.henrique.repository.AtendeRepository;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class AtendeService {

	@Autowired
	private AtendeRepository repositAtende;

	// Busca CEP de Atendimento de Filial
	public Atende findById(Integer cepAtende) {

		Atende atende = new Atende();
		
		Cep cep = ViaCepClient.findCep(cepAtende.toString()); // 14620000

		// Dados do CEP informado
		if (cep.getCep() != null) {
			atende.setCep(cep.getCep());
			atende.setLogradouro(cep.getLogradouro());
			atende.setComplemento(cep.getComplemento());
			atende.setLocalidade(cep.getLocalidade()); // Cidade - Municipio
			atende.setBairro(cep.getBairro());
			atende.setUf(cep.getUf());
			atende.setIbge(cep.getIbge());
		} else {
   		    // Caso não encontre nenhum CEP
			throw new ObjectNotFoundException("Não foi encontrada uma Filial próxima ao CEP informado");
		}
		
		return repositAtende.save(atende);
	}

}