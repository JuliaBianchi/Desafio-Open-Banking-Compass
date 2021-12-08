package br.com.api.demo.controller;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.demo.modelo.Produtos;
import br.com.api.demo.repository.ProdutosRepository;

@RestController
public class ProdutosController {
    
    @Autowired
    private ProdutosRepository produtosRepository;
    
    @RequestMapping(value = "/produtos", method = RequestMethod.GET, produces="application/json")
    public List<Produtos> Get() {
        return produtosRepository.findAll();
    }
    
    @RequestMapping(value = "/produtos/{id}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Produtos> GetById(@PathVariable(value = "id") long id){
        Optional<Produtos> produto = produtosRepository.findById(id);
        if(produto.isPresent()) {
            return new ResponseEntity<Produtos>(produto.get(), HttpStatus.OK);
        }
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
         
  
    @RequestMapping(value = "/produtos", method =  RequestMethod.POST, produces="application/json", consumes="application/json")
    @Transactional
    public Produtos Post(@Valid @RequestBody Produtos produto){
        return produtosRepository.save(produto);
    }
    

    @RequestMapping(value = "/produtos/{id}", method =  RequestMethod.PUT, produces="application/json", consumes="application/json")
    @Transactional
    public ResponseEntity<Produtos> Put(@PathVariable(value = "id") long id, @Valid @RequestBody AtualizacaoProdutos newProduto){
        Optional<Produtos> oldProduto = produtosRepository.findById(id);
        if(oldProduto.isPresent()) {
        	Produtos produto = newProduto.Put(id, produtosRepository);
            return new ResponseEntity<Produtos>(produto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    @RequestMapping(value = "/produtos/{id}", method = RequestMethod.DELETE, produces="application/json")
    @Transactional
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id){
        Optional<Produtos> produto = produtosRepository.findById(id);
        if(produto.isPresent()){
            produtosRepository.delete(produto.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   
}


