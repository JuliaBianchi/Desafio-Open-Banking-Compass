package br.com.api.controller;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.api.model.Produtos;
import br.com.api.service.ProdutosService;
import lombok.Data;

@Data
public class AtualizacaoProdutos {
	
	@NotBlank(message = "Este campo não pode estar em branco")
	private String descricao;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Este campo não pode estar em branco")
	private String nome;
	@NotNull(message = "Este campo não pode ser nulo")
	private Double preco;
	
	public Produtos Put(long id, ProdutosService produtosService) {
		Produtos produto = produtosService.findById(id);
		produto.setDescricao(this.descricao);
		produto.setId(this.id);
		produto.setNome(this.nome);
		produto.setPreco(this.preco);
		return produto;
	}

}