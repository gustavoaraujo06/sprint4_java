package br.com.fiap.model;

public class Veiculo {
	private int id;
	private String chassi;
	private int ano;
	private String marca;
	private String modelo;
	private String placa;
	private Cliente proprietario;
	
	public Veiculo() {
		super();
	}

	public Veiculo(int id, String chassi, int ano, String marca, String modelo, String placa, Cliente proprietario) {
		super();
		this.id = id;
		this.chassi = chassi;
		this.ano = ano;
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.proprietario = proprietario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Cliente getProprietario() {
		return proprietario;
	}

	public void setProprietario(Cliente proprietario) {
		this.proprietario = proprietario;
	}

	
	
	
}
