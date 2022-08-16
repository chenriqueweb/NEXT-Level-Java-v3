package br.com.henrique.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.model.Atende;
import br.com.henrique.model.AtendeFilial;
import br.com.henrique.model.Estado;
import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.Filial;
import br.com.henrique.model.FilialPK;
import br.com.henrique.model.Microzona;
import br.com.henrique.model.RotaEntrega;
import br.com.henrique.model.RotaEntregaPK;
import br.com.henrique.repository.AtendeFilialRepository;
import br.com.henrique.repository.FilialRepository;
import br.com.henrique.repository.RotaEntregaRepository;

@Service
public class AtendeFilialService {

	@Autowired
	private static AtendeFilialRepository repositAtendeFilial;

	@Autowired
	private AtendeService atendeService;

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
	public AtendeFilial addAtendeFilial (Integer cepAtende) {
		
		AtendeFilial atendeFilial = new AtendeFilial();

		List<FaixasCEPMicrozona> faixasCEPMicrozona = faixasCEPMicrozonaService.findAll();
//	// JSONObject objetoJson = new JSONObject();
//
		// Procura por uma faixa de CEPs
		for (int x = 0; x < faixasCEPMicrozona.size(); x++) {
			if (cepAtende >= faixasCEPMicrozona.get(x).getCEPinicial()
					& cepAtende <= faixasCEPMicrozona.get(x).getCEPfinal()) {

				// Busca por informações da Localidade do Cliente
				Atende atendeBusca = atendeService.findById(cepAtende);
//				atendeFilial.setAtende(atendeBusca);
				
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

				// Chave Atende
//			AtendePK atendePK = new AtendePK();
//			atendePK.setCepRequisitado(cep.getCep());
//			atendePK.setEmpresaAtende(rotaEntregaBusca.get().getCodigoEmpresa());
//			atendePK.setFilialAtende(rotaEntregaBusca.get().getCodigoFilial());
//			atende.setAtendePK(atendePK);

				atendeFilial.setCepRequisitado(atendeBusca.getCep());
				atendeFilial.setEmpresaAtende(rotaEntregaBusca.get().getCodigoEmpresa());
				atendeFilial.setFilialAtende(rotaEntregaBusca.get().getCodigoFilial());

				// Bsuca por informações do Estado
				Estado estadoBusca = estadoService.findById(atendeBusca.getUf());
				atendeFilial.setNomeEstado(estadoBusca.getNome());

				atendeFilial.setMunicipio(atendeBusca.getLocalidade());
				atendeFilial.setEstado(atendeBusca.getUf());

				repositAtendeFilial.save(atendeFilial);
			}

		}
		return atendeFilial;  // filiaisAtendidas;

	}
}