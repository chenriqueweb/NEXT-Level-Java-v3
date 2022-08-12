package br.com.henrique.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.ViaCepClient;
import br.com.henrique.model.Atende;
import br.com.henrique.model.AtendePK;
import br.com.henrique.model.Cep;
import br.com.henrique.model.Estado;
import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.Filial;
import br.com.henrique.model.FilialPK;
import br.com.henrique.model.Microzona;
import br.com.henrique.model.RotaEntrega;
import br.com.henrique.model.RotaEntregaPK;
import br.com.henrique.repository.AtendeRepository;
import br.com.henrique.repository.FilialRepository;
import br.com.henrique.repository.RotaEntregaRepository;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class AtendeService {

	@Autowired
	private AtendeRepository repositAtende;

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

	// Busca CEP de Atendimento de Filial
	public Atende retornaCEP(Integer cepAtende) {

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
			atende.setMunicipio(cep.getLocalidade());
			atende.setEstado(cep.getUf());
		} else {
			throw new ObjectNotFoundException("Não foi encontrada uma Filial próxima ao CEP informado");
		}

		List<FaixasCEPMicrozona> faixasCEPMicrozona = faixasCEPMicrozonaService.findAll();
		// JSONObject objetoJson = new JSONObject();

		// Procura por uma faixa de CEPs
		for (int x = 0; x < faixasCEPMicrozona.size(); x++) {
			if (cepAtende >= faixasCEPMicrozona.get(x).getCEPinicial()  &
			    cepAtende <= faixasCEPMicrozona.get(x).getCEPfinal()) {

				// Busca de Informações da Microzona
				Microzona microzona = microzonaService.findById(faixasCEPMicrozona.get(x).getFaixasCEPMicrozonaPK().getCodigoMicrozona());
				atende.setMicrozona(microzona.getCodigo());
				atende.setUfRota(microzona.getEstadoRota().getSigla());
				atende.setCodigoRota(microzona.getCodigoRota());
				atende.setCodigoMunicipio(microzona.getCodigoMunicipio().getCodigo_ID());

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
				atende.setNomeFilial(filialBusca.get().getNome());
				atende.setCnpjFilial(filialBusca.get().getCnpj());

				// Bsuca por informações do Estado
				Estado estadoBusca = estadoService.findById(cep.getUf());
				atende.setNomeEstado(estadoBusca.getNome());

				// Chave Atende
				AtendePK atendePK = new AtendePK();
				atendePK.setCepRequisitado(cep.getCep());
				atendePK.setEmpresaAtende(rotaEntregaBusca.get().getCodigoEmpresa());
				atendePK.setFilialAtende(rotaEntregaBusca.get().getCodigoFilial());
				atende.setAtendePK(atendePK);

				repositAtende.save(atende);
			}
		}

		// Caso não encontre nenhum CEP
		if (atende.getCep() == null) {
			throw new ObjectNotFoundException("Não foi encontrada uma Filial próxima ao CEP informado");
		}

		// return repositAtende.save(atende);
		return atende;
	}
	
    // Lista Filiais Atendidas
    public List<Atende> findAll() {
        List<Atende> atendeFiliais = repositAtende.findAll();
        
        return atendeFiliais;
    }
}