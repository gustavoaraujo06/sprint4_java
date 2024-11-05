package br.com.fiap.model;

import java.time.LocalDate;

public class Processo {
	private int id;
	private char status;
	private String descricao;
	private int prioridade;
	private LocalDate estimativaConclusao;
	private Veiculo veiculo;
	private Diagnostico diagnostico;
	private Agente responsavel;
	private Servico[] servicos;
	public Processo() {
		super();
	}
	public Processo(int id, char status, String descricao, int prioridade, LocalDate estimativaConclusao,
			Veiculo veiculo, Diagnostico diagnostico, Agente responsavel, Servico[] servicos) {
		super();
		this.id = id;
		this.status = status;
		this.descricao = descricao;
		this.prioridade = prioridade;
		this.estimativaConclusao = estimativaConclusao;
		this.veiculo = veiculo;
		this.diagnostico = diagnostico;
		this.responsavel = responsavel;
		this.servicos = servicos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	public LocalDate getEstimativaConclusao() {
		return estimativaConclusao;
	}
	public void setEstimativaConclusao(LocalDate estimativaConclusao) {
		this.estimativaConclusao = estimativaConclusao;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Diagnostico getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}
	public Agente getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Agente responsavel) {
		this.responsavel = responsavel;
	}
	public Servico[] getServicos() {
		return servicos;
	}
	public void setServicos(Servico[] servicos) {
		this.servicos = servicos;
	}
	
	
	
}
