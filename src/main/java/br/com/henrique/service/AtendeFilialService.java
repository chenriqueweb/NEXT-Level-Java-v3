package br.com.henrique.service;

import org.springframework.stereotype.Service;

@Service
public class AtendeFilialService {
    
//    @Autowired
//    private static AtendeFilialRepository repositAtendeFilial;
//    
//    @Autowired
//    private static AtendeService atendeService;
//    
//    
//	@Autowired
//	private AtendeFilialRepository repositAtendeFilial;
//
//	@Autowired
//	private FaixasCEPMicrozonaService faixasCEPMicrozonaService;
//
//	@Autowired
//	private MicrozonaService microzonaService;
//
//	@Autowired
//	private RotaEntregaRepository repositRotaEntrega;
//
//	@Autowired
//	private FilialRepository repositFilial;
//
//	@Autowired
//	private EstadoService estadoService;
//
//    
//    // Lista Filiais que são Atendidas
//    public static List<AtendeFilial> findAllByAtendeFilial(Integer cepAtende) {
//        List<AtendeFilial> filiaisAtendidas = repositAtendeFilial.findByAtendeFilial(atendeService.retornaCEP(cepAtende));        
//        
//        return filiaisAtendidas ;
//    }
//    
//    
//	List<FaixasCEPMicrozona> faixasCEPMicrozona = faixasCEPMicrozonaService.findAll();
//	// JSONObject objetoJson = new JSONObject();
//
//	// Procura por uma faixa de CEPs
//	for (int x = 0; x < faixasCEPMicrozona.size(); x++) {
//		if (cepAtende >= faixasCEPMicrozona.get(x).getCEPinicial()  &
//		    cepAtende <= faixasCEPMicrozona.get(x).getCEPfinal()) {
//
//			// Busca de Informações da Microzona
//			Microzona microzona = microzonaService.findById(faixasCEPMicrozona.get(x).getFaixasCEPMicrozonaPK().getCodigoMicrozona());
//			atendeFilial.setMicrozona(microzona.getCodigo());
//			atendeFilial.setUfRota(microzona.getEstadoRota().getSigla());
//			atendeFilial.setCodigoRota(microzona.getCodigoRota());
//			atendeFilial.setCodigoMunicipio(microzona.getCodigoMunicipio().getCodigo_ID());
//
//			// Busca por informações da Rota de Entrega
//			RotaEntregaPK rotaEntregaPK = new RotaEntregaPK();
//			rotaEntregaPK.setSiglaEstado(microzona.getEstadoRota().getSigla());
//			rotaEntregaPK.setCodigoRota(microzona.getCodigoRota());
//
//			Optional<RotaEntrega> rotaEntregaBusca = repositRotaEntrega.findById(rotaEntregaPK);
//
//			// Busca por informações da Filial
//			FilialPK filialPK = new FilialPK();
//			filialPK.setCodigoEmpresa(rotaEntregaBusca.get().getCodigoEmpresa());
//			filialPK.setCodigoFilial(rotaEntregaBusca.get().getCodigoFilial());
//
//			Optional<Filial> filialBusca = repositFilial.findById(filialPK);
//			atendeFilial.setNomeFilial(filialBusca.get().getNome());
//			atendeFilial.setCnpjFilial(filialBusca.get().getCnpj());
//
//			// Bsuca por informações do Estado
//			Estado estadoBusca = estadoService.findById(cep.getUf());
//			atendeFilial.setNomeEstado(estadoBusca.getNome());
//
//			// Chave Atende
////			AtendePK atendePK = new AtendePK();
////			atendePK.setCepRequisitado(cep.getCep());
////			atendePK.setEmpresaAtende(rotaEntregaBusca.get().getCodigoEmpresa());
////			atendePK.setFilialAtende(rotaEntregaBusca.get().getCodigoFilial());
////			atende.setAtendePK(atendePK);
////
//			atendeFilial.setCepRequisitado(cep.getCep());
//			atendeFilial.setEmpresaAtende(rotaEntregaBusca.get().getCodigoEmpresa());
//			atendeFilial.setFilialAtende(rotaEntregaBusca.get().getCodigoFilial());
//			
//			atendeFilial.setMunicipio(cep.getLocalidade());
//			atendeFilial.setEstado(cep.getUf());
//	
//				repositAtende.save(atende);
//				repositAtendeFilial.save(atendeFilial);
//		}
//	}
//
//    
    
}