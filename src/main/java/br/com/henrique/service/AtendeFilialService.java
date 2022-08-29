package br.com.henrique.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.ViaCepClient;
import br.com.henrique.model.Atende;
import br.com.henrique.model.AtendeFilial;
import br.com.henrique.model.Cep;
import br.com.henrique.model.Estado;
import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.Filial;
import br.com.henrique.model.FilialPK;
import br.com.henrique.model.Microzona;
import br.com.henrique.model.RotaEntrega;
import br.com.henrique.model.RotaEntregaPK;
import br.com.henrique.repository.FilialRepository;
import br.com.henrique.repository.RotaEntregaRepository;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class AtendeFilialService {

	@Autowired
	private FaixasCEPMicrozonaService faixasCEPMicrozonaService;

	@Autowired
	private MicrozonaService microzonaService;

	@Autowired
	private RotaEntregaRepository repositRotaEntrega;

	@Autowired
	private FilialRepository repositFilial;

	@Autowired
	private EstadoService estadoService;

	// Lista Filiais que são Atendidas
	public Atende addAtendeFilial (Integer cepAtende) {
		
		Atende atende = new Atende();
		
		Boolean CEPcomFiliais = false;
		
		// Chamada API publica para complementar informações do CEP 
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

		// Lista de Microzonas por CEP
		List<FaixasCEPMicrozona> faixasCEPMicrozona = faixasCEPMicrozonaService.findAll();
		
		// Procura por uma faixa de CEPs
		for (int x = 0; x < faixasCEPMicrozona.size(); x++) {
			if (cepAtende >= faixasCEPMicrozona.get(x).getCEPinicial()  &
		  	    cepAtende <= faixasCEPMicrozona.get(x).getCEPfinal()) {
				
				AtendeFilial atendeFilial = new AtendeFilial();
				
				// Busca de Informações da Microzona
				Microzona microzona = microzonaService.findById(faixasCEPMicrozona.get(x).getFaixasCEPMicrozonaPK().getCodigoMicrozona());
				atendeFilial.setMicrozona(microzona.getCodigo());
				atendeFilial.setUfRota(microzona.getEstadoRota().getSigla());
				atendeFilial.setCodigoRota(microzona.getCodigoRota());
				atendeFilial.setCodigoMunicipio(microzona.getCodigoMunicipio().getCodigo_ID());

				// Busca por informações da Rota de Entrega
				RotaEntregaPK rotaEntregaPK = new RotaEntregaPK();
				rotaEntregaPK.setSiglaEstado(microzona.getEstadoRota().getSigla());
				rotaEntregaPK.setCodigoRota(microzona.getCodigoRota());

				Optional<RotaEntrega> rotaEntregaBusca = repositRotaEntrega.findById(rotaEntregaPK);

				// Busca por informações da Filial
				FilialPK filialPK = new FilialPK();
				filialPK.setCodigoEmpresa(rotaEntregaBusca.get().getCodigoEmpresa());
				filialPK.setCodigoFilial(rotaEntregaBusca.get().getCodigoFilial());

				Optional<Filial> filialBusca = repositFilial.findById(filialPK);
				atendeFilial.setNomeFilial(filialBusca.get().getNome());
				atendeFilial.setCnpjFilial(filialBusca.get().getCnpj());
				atendeFilial.setCepFilial(filialBusca.get().getCepFilial());

				atendeFilial.setEmpresaAtende(rotaEntregaBusca.get().getCodigoEmpresa());
				atendeFilial.setFilialAtende(rotaEntregaBusca.get().getCodigoFilial());

				// Bsuca por informações do Estado
				Estado estadoBusca = estadoService.findById(atende.getUf());
				atendeFilial.setNomeEstado(estadoBusca.getNome());

				atendeFilial.setMunicipio(atende.getLocalidade());
				atendeFilial.setEstado(atende.getUf());

				// Seta informações da Filial na Lista
				atende.getAtendeFilial().add(atendeFilial);
				CEPcomFiliais = true;
			}

		}
		
		// Caso não encontre nenhum CEP
		if (!CEPcomFiliais) {
			throw new ObjectNotFoundException("Não foi encontrada uma Filial próxima ao CEP informado");
		}

		return atende;

	}
}