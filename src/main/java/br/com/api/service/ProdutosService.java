package br.com.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.api.model.Produtos;
import br.com.api.repository.ProdutosRepository;

@Service
public class ProdutosService {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
    public List<Produtos> findAll(){
        return produtosRepository.findAll();
    }
   
    public Produtos findById(Long id) {
    	Optional<Produtos> produto = produtosRepository.findById(id);
    	return produto.get();
    }
    
	public Produtos create(Produtos produto) {
		return produtosRepository.save(produto);
	}
	
	public Produtos update(Produtos produto, Long id) {
		produto.setId(id);
		return produtosRepository.save(produto);
	}
	
    public void deleteById(Long id){
        produtosRepository.deleteById(id);
    }
    
    public List<Produtos> findBySearch(@RequestParam(required = false ,value = "q") 
    String q, @RequestParam(required = false ,value = "minPrice") 
    Double minPrice, @RequestParam(required = false , value = "maxPrice") 
    Double maxPrice) {
    	return produtosRepository.findBySearch(q, minPrice, maxPrice);
    }

	public Produtos save(Produtos produto) {
		return produtosRepository.save(produto);
	}
}