package br.com.api.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.model.Produtos;
import br.com.api.service.ProdutosService;

@RestController
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;
    
    @GetMapping("/produtos")
    public List<Produtos> Get() {
        return produtosService.findAll();  	
    }
    
    @GetMapping("/produtos/{id}")
    public Produtos GetById(@Valid@PathVariable(value = "id") long id){
       return produtosService.findById(id);		   
    }
         
    @PostMapping("/produtos")
    @Transactional
    public Produtos Post(@Valid @RequestBody Produtos produto){
        return produtosService.create(produto);
    }
    
    @PutMapping("/produtos/{id}")
    @Transactional
    public Produtos Put(@PathVariable(value = "id") long id, @Valid @RequestBody AtualizacaoProdutos newProduto){
        Produtos produto = produtosService.findById(id);	
        	produto = newProduto.Put(id, produtosService);
            return produtosService.save(produto);
    }

    @DeleteMapping("/produtos/{id}")
    @Transactional
    public ResponseEntity<Produtos> Delete(@PathVariable(value = "id") long id){
    	produtosService.findById(id);
            produtosService.deleteById(id);
            return ResponseEntity.ok().build();
    }

    @GetMapping("/produtos/search")
    public List<Produtos> findBySearch(@RequestParam(required = false ,value = "q") 
    String q, @RequestParam(required = false ,value = "minPrice") 
    Double minPrice, @RequestParam(required = false , value = "maxPrice") 
    Double maxPrice){
        return produtosService.findBySearch(q, minPrice, maxPrice);
    }    	
}
