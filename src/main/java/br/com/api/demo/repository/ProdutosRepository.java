package br.com.api.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.demo.modelo.Produtos;



public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
}
