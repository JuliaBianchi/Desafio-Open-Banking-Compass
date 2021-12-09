package br.com.api.demo.controller;

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

import br.com.api.demo.modelo.Produtos;
import br.com.api.demo.repository.ProdutosRepository;
import br.com.api.demo.validation.NotFoundException;

@RestController
public class ProdutosController {
    
    @Autowired
    private ProdutosRepository produtosRepository;
    
    @GetMapping("/produtos")
    public List<Produtos> Get() {
        return this.produtosRepository.findAll();  	
    }
    
    @GetMapping("/produtos/{id}")
    public Produtos GetById(@Valid@PathVariable(value = "id") long id){
       return this.produtosRepository.findById(id)
    		   .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }
         
    @PostMapping("/produtos")
    @Transactional
    public Produtos Post(@Valid @RequestBody Produtos produto){
        return this.produtosRepository.save(produto);
    }
    
    @PutMapping("/produtos/{id}")
    @Transactional
    public Produtos Put(@PathVariable(value = "id") long id, @Valid @RequestBody AtualizacaoProdutos newProduto){
        Produtos produto = this.produtosRepository.findById(id)
        		.orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        	produto = newProduto.Put(id, produtosRepository);
            return this.produtosRepository.save(produto);
    }

    @DeleteMapping("/produtos/{id}")
    @Transactional
    public ResponseEntity<Produtos> Delete(@PathVariable(value = "id") long id){
    	Produtos produtoExistente = this.produtosRepository.findById(id)
        		.orElseThrow(() -> new NotFoundException("Produto não encontrado"));
            this.produtosRepository.delete(produtoExistente);
            return ResponseEntity.ok().build();
    	}

    @GetMapping("/produtos/search")
    public List<Produtos> findBySearch(@RequestParam(required = false ,value = "q") 
    String q, @RequestParam(required = false ,value = "minPrice") 
    Double minPrice, @RequestParam(required = false , value = "maxPrice") 
    Double maxPrice){
        return this.produtosRepository.findBySearch(q, minPrice, maxPrice);
    }    	
}


