package br.com.henrique.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import br.com.henrique.dto.EstadoDto;
import br.com.henrique.model.Estado;
import br.com.henrique.service.EstadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Estado")
@ApiOperation(value = "CRUD - Estado")
@RestController
@RequestMapping(path = "/estado")
public class EstadoController {
	
    // private final Translator translator = new Translator();
	
    @Autowired
    private EstadoService estadoService; 
    
    // Lista Estado - DTO
    @GetMapping
    @ApiOperation(value = "Lista todas os Estados")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna uma lista de todas as Estados")
    })  
    public ResponseEntity<List<EstadoDto>> findAll() {
        List<Estado> estados = estadoService.findAll(); 
        return ResponseEntity.ok().body(estados.stream().map(e -> e.converteToDto(e)).collect(Collectors.toList()));
    }
    
    // Lista de Estado com paginação
    @GetMapping(path = "page")
    @ApiOperation(value = "Lista todas os Estados - paginação")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna uma lista de todas as Estados")
    })  
    public ResponseEntity<Page<Estado>> findAllPage(Pageable pageable) {
        return ResponseEntity.ok().body(estadoService.findAllPage(pageable));
    }
    
    // Busca pelo Estado
    @GetMapping(path = "{sigla}")
    @ApiOperation(value = "Busca por um Estado")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna dados do Estado"),
    	    @ApiResponse(code = 404, message = "Estado não encontrado")    	    
    }) 
    public ResponseEntity<Estado> findById(@PathVariable String sigla) {
        Estado estado = estadoService.findById(sigla);
        return ResponseEntity.ok().body(estado);
    }
    
    // Inclui Estado - DTO
    @PostMapping
    @ApiOperation(value = "Inclui um Estado")
    @ApiResponses(value = {
    	    @ApiResponse(code = 201, message = "Estado criado com sucesso")
    }) 
    public ResponseEntity<Void> addEstado(@Valid @RequestBody EstadoDto estadoDto) {
        Estado estadoNovo = estadoService.addEstado(estadoDto);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{sigla}").buildAndExpand(estadoNovo.getSigla()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    // Altera Estado - DTO
    @PutMapping(path = "{sigla}")
    @ApiOperation(value = "Altera os dados de um Estado")    
    @ApiResponses(value = {
    	    @ApiResponse(code = 204, message = "Estado alterado com sucesso"),
    	    @ApiResponse(code = 400, message = "Dados inválidos"),
    	    @ApiResponse(code = 404, message = "Estado não encontrado")    	    
    }) 
    public ResponseEntity<Void> updateEstado(@Valid 
    		                                 @PathVariable String sigla, 
    		                                 @RequestBody EstadoDto estadoDto) {
//        String teste = translator.getText("NotEmpty.nome");
//        System.out.println(teste);
        
        estadoService.updateEstado(sigla, estadoDto);
        
        return ResponseEntity.noContent().build();
    }

    // Exclui Estado
    @DeleteMapping(path = "{sigla}")
    @ApiOperation(value = "Exclui um Estado")
    @ApiResponses(value = {
    	    @ApiResponse(code = 204, message = "Estado excluído"),
    	    @ApiResponse(code = 404, message = "Estado não encontrado"), 
    	    @ApiResponse(code = 500, message = "Houve um erro e não foi possível excluir o Estado")    	    
    })  
    public ResponseEntity<Void> deletaEstado(@PathVariable String sigla) {
        estadoService.deletaEstado(sigla);
        
        return ResponseEntity.noContent().build();
    }
    
    //----------------------------------------------------------------------------------------
    // Deleta estado e chama Lista de Estados
    // method Post (página)
    @PostMapping(path = "/remover/{sigla}")
    public ModelAndView deletaEstadoWeb(@PathVariable String sigla) {
        estadoService.deletaEstado(sigla);
        
        List<Estado> estados = estadoService.findAll();
        
        ModelAndView modelAndView = new ModelAndView("EstadoListar");
        modelAndView.addObject("estados", estados);
        
        return modelAndView;
    }     
    
    // Altera Estado
    // method Post (página)
    @GetMapping(path = "/editar/{sigla}")
    public ModelAndView editarEstadoWeb(@PathVariable String sigla) {
        ModelAndView modelAndView = new ModelAndView("EstadoFormulario");
        
        Estado estado = estadoService.findById(sigla);
        
        modelAndView.addObject("estado", estado);
        
        return modelAndView;
    }
    
//    public static String getMessage(String mensagem) {
//        if (messagesErrors.containsKey(mensagem)) {
//            return messagesErrors.getString(mensagem);
//        }
//        //Logar no console uma mensagem indicando que não achou o rotulo
//        return "";
//    }    

    
}
