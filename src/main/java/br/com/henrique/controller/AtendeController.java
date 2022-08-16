package br.com.henrique.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.henrique.model.Atende;
import br.com.henrique.service.AtendeFilialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Busca_CEP")
@ApiOperation(value = "CRUD - Atende")
@RestController
@RequestMapping(path = "/atende")
public class AtendeController {
    
//    @Autowired
//    private AtendeService atendeService;
    
    @Autowired
    private AtendeFilialService atendeFilialService;
    
//    // Busca pelo CEP do Cliente
//    @GetMapping(path = "{cepAtende}")
//    @ApiOperation(value = "Consulta de Filiais por CEP")
//    @ApiResponses(value = {
//    	    @ApiResponse(code = 200, message = "Retorna a Filial por CEP"),
//    	    @ApiResponse(code = 400, message = "CEP inválido"),
//    	    @ApiResponse(code = 404, message = "Não foi encontrada uma Filial próxima ao CEP informado"),
//    	    @ApiResponse(code = 500, message = "Houve um erro e não foi possível encontrar uma Filial próxima ao CEP informado")    	    
//    })  
//    public ResponseEntity<Atende> findById(@PathVariable Integer cepAtende) {
//        Atende filiaisAtendidas = atendeService.findById(cepAtende);
//        
//        return ResponseEntity.ok().body(filiaisAtendidas);
//    }

    // Busca pelas Filiais próximo ao CEP do Cliente
    @GetMapping(path = "/filial/{cepAtende}")
    @ApiOperation(value = "Consulta de Filiais por CEP")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna a Filial por CEP"),
    	    @ApiResponse(code = 400, message = "CEP inválido"),
    	    @ApiResponse(code = 404, message = "Não foi encontrada uma Filial próxima ao CEP informado"),
    	    @ApiResponse(code = 500, message = "Houve um erro e não foi possível encontrar uma Filial próxima ao CEP informado")    	    
    })  
    public ResponseEntity<Atende> findAllByAtendeFilial(@PathVariable Integer cepAtende) {
    	
        Atende atendeFiliais = atendeFilialService.addAtendeFilial(cepAtende);
        
        return ResponseEntity.ok().body(atendeFiliais);
    }
}
