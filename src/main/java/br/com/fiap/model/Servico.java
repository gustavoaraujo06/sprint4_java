package br.com.fiap.model;

import java.time.LocalDate;

public class Servico {
	private int id;
	private String descricao;
	private Double valor;
	private int duracao;
	private LocalDate dataCriacao;
	private Oficina oficina;
	public Servico() {
		super();
	}
	public Servico(int id, String descricao, Double valor, int duracao, LocalDate dataCriacao, Oficina oficina) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.duracao = duracao;
		this.dataCriacao = dataCriacao;
		this.oficina = oficina;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Oficina getOficina() {
		return oficina;
	}
	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}
	
	
	
	
}
