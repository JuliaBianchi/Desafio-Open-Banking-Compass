package br.com.api.demo.controller.dto;

import br.com.api.demo.modelo.Produtos;

public class ProdutosDto {
	
	private String descricao;
	private Long id;
	private String nome;
	private Double preco;
	
	public ProdutosDto(Produtos produtos) {
		super();
		this.descricao = produtos.getDescricao();
		this.id = produtos.getId();
		this.nome = produtos.getNome();
		this.preco = produtos.getPreco();
	}
	
	public String getDescricao() {
		return descricao;
	}
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Double getPreco() {
		return preco;
	}
	
}
