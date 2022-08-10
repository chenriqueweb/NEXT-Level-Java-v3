package br.com.henrique.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.henrique.dto.MunicipioDto;
import br.com.henrique.model.Municipio;
import br.com.henrique.service.MunicipioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Municipio")
@ApiOperation(value = "CRUD - Municipio")
@RestController
@RequestMapping(path = "/municipio")
public class MunicipioController {
    
    @Autowired   
    private MunicipioService municipioService;
    
    // Lista Municipio
    @GetMapping
    @ApiOperation(value = "Lista todos os Municipios")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna uma lista de todas os Municipios")
    })  
    public ResponseEntity<List<Municipio>> findAll() {
        List<Municipio> municipios = municipioService.findAll();        
        return ResponseEntity.ok().body(municipios);
    }
    
    // Lista Municipio por Estado
    @GetMapping(path = "estado/{sigla}")
    @ApiOperation(value = "Lista todos os Municipios por Estado")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna uma lista de todas os Municipios por Estado")
    })
    public ResponseEntity<List<Municipio>> findAllByEstado(@PathVariable String sigla) {
        List<Municipio> municipios = municipioService.findAllByEstado(sigla);
        return ResponseEntity.ok().body(municipios);
    }

    // Lista de Municipios com paginação
    @GetMapping(path = "page")
    @ApiOperation(value = "Lista todos os Municipios - paginação")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna uma lista de todas os Municipios")
    })  
    public ResponseEntity<Page<Municipio>> findAllPage(Pageable pageable) {
        return ResponseEntity.ok().body(municipioService.findAllPage(pageable));
    } 
   
    // Busca pelo Municipio
    @GetMapping(path = "{codigo}")
    @ApiOperation(value = "Busca por um Municipio")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna dados do Municipio"),
    	    @ApiResponse(code = 404, message = "Municipio não encontrado")    	    
    })  
    public ResponseEntity<Municipio> findById(@PathVariable Integer codigo) {
        Municipio municipio = municipioService.findById(codigo);
        return ResponseEntity.ok().body(municipio);
    }    

    // Inclui Municipio - DTO
    @PostMapping
    @ApiOperation(value = "Inclui um Municipio")
    @ApiResponses(value = {
    	    @ApiResponse(code = 201, message = "Municipio criado com sucesso")
    }) 
    public ResponseEntity<Void> addMunicipio(@Valid @RequestBody MunicipioDto municipioDto) {
        Municipio municipioNovo = municipioService.addMunicipio(municipioDto);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(municipioNovo.getCodigo_ID()).toUri();
        return ResponseEntity.created(uri).build();
    }    
    
    // Altera Municipio - DTO
    @PutMapping(path = "{codigo}")
    @ApiOperation(value = "Altera os dados de um Municipio")
    @ApiResponses(value = {
    	    @ApiResponse(code = 204, message = "Municipio alterado com sucesso"),
    	    @ApiResponse(code = 400, message = "Dados inválidos"),
    	    @ApiResponse(code = 404, message = "Municipio não encontrado")    	    
    })  
    public ResponseEntity<Void> updateMunicipio(@Valid 
    		                                    @PathVariable Integer codigo, 
    		                                    @RequestBody MunicipioDto municipioDto) {
        municipioService.updateMunicipio(codigo, municipioDto);
        return ResponseEntity.noContent().build();
    }
    
    // Deleta Municipio = method Delete
    @DeleteMapping(path = "{codigo}")
    @ApiOperation(value = "Exclui um Municipio")
    @ApiResponses(value = {
    	    @ApiResponse(code = 204, message = "Municipio excluído"),
    	    @ApiResponse(code = 404, message = "Municipio não encontrado"), 
    	    @ApiResponse(code = 500, message = "Houve um erro e não foi possível excluir o Municipio")
    })  
    public ResponseEntity<Void> deletaMunicipio(@PathVariable Integer codigo) {
        municipioService.deletaMunicipio(codigo);
        return ResponseEntity.noContent().build();
    }
    
    //----------------------------------------------------------------------------------------
    // Altera Municipio
    // method Post (página)
    @GetMapping(path = "/editar/{codigo}")
    public ModelAndView editarMunicipioWeb(@PathVariable Integer codigo) {
        ModelAndView modelAndView = new ModelAndView("MunicipioFormulario");
        
        Municipio municipio = municipioService.findById(codigo);
        
        modelAndView.addObject("municipio", municipio);
        
        return modelAndView;
    }        

    
    // Deleta estado e chama Lista de Municipio
    // method Post (página)
//    @PostMapping(path = "/remover/{codigo}")
//    public ModelAndView deletaMunicipioWeb(@PathVariable Integer codigo) {
//        municipioService.deletaMunicipio(codigo);
//        
//        List<Municipio> municipios = municipioService.findAll();
//        
//        ModelAndView modelAndView = new ModelAndView("MunicipioListar");
//        modelAndView.addObject("municipios", municipios);
//        
//        return modelAndView;
//    } 
//    
}
