package br.com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
	
	@Query(value = "SELECT * FROM Produtos WHERE "
	+ "(:q IS NULL OR (UPPER(nome)"
    + "LIKE UPPER(CONCAT('%', :q, '%'))"
	+ "OR UPPER(descricao)"
    + "LIKE UPPER(CONCAT('%', :q, '%'))))"
    + "AND (:minPrice IS NULL OR preco >= :minPrice)"
    + "AND (:maxPrice IS NULL OR preco <= :maxPrice)", nativeQuery = true)
	
	List<Produtos> findBySearch(String q, Double minPrice, Double maxPrice);
	
	
}

