package br.com.api.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
    
    @RequestMapping(value = "/produto", method = RequestMethod.GET, produces="application/json")
    public List<Produtos> Get() {
        return produtosRepository.findAll();
    }

    @RequestMapping(value = "/produto", method =  RequestMethod.POST, produces="application/json", consumes="application/json")
    public Produtos Post( @RequestBody Produtos produto)
    {
        return produtosRepository.save(produto);
    }
    
    
    
   
}


