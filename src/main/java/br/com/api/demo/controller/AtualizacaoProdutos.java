package br.com.api.demo.controller;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.api.demo.modelo.Produtos;
import br.com.api.demo.repository.ProdutosRepository;


public class AtualizacaoProdutos {
	
	@NotNull @NotEmpty
	private String descricao;
	private Long id;
	private String nome;
	private Double preco;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Produtos Put(long id, ProdutosRepository produtosRepository) {
		Produtos produto = produtosRepository.getById(id);
		produto.setDescricao(this.descricao);
		produto.setId(this.id);
		produto.setNome(this.nome);
		produto.setPreco(this.preco);
		
		return produto;
	}

}
