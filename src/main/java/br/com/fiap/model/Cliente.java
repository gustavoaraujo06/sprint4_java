package br.com.fiap.model;


public class Cliente {
	private int id;
	private String nome;
	private String cpf;
	private String rg;
	private int confiabilidade;
	
	public Cliente() {
		super();
	}

	public Cliente(int id, String nome, String cpf, String rg, int confiabilidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.confiabilidade = confiabilidade;
	}
	public Cliente(Cliente cliente) {
		this(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getRg(), cliente.getConfiabilidade());
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public int getConfiabilidade() {
		return confiabilidade;
	}

	public void setConfiabilidade(int confiabilidade) {
		this.confiabilidade = confiabilidade;
	}
	@Override
	public boolean equals(Object obj) {
		if(!obj.getClass().equals(this.getClass())) {
			return false;
		}
		Cliente otherCliente = (Cliente)obj;
		if(this.getId() == otherCliente.getId() || this.getCpf().equals(otherCliente.getCpf()) || this.getRg().equals(otherCliente.getRg())) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
