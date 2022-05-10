package pojo;
import java.sql.Timestamp;

public class Vendas {
	private int id_produto;
	private Timestamp data; 
	private int quantidade;
	private double preco;
	private String cpf;
	
	public Vendas(int id_produto, double preco, Timestamp data, int quantidade, String cpf) {
		this.id_produto = id_produto;
		this.data = data;
		this.quantidade = quantidade;		
		this.preco = preco;
		this.cpf = cpf;
	}
		
	public Vendas(String cpf, int id_produto) {
		// TODO Auto-generated constructor stb
		this.cpf = cpf;
		this.id_produto = id_produto;
	}

	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}

	
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
