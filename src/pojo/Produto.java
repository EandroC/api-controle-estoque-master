package pojo;

public class Produto {
	private int id_produto;
	private double preco;
	private String nome_produto;
	private int quantidade;
	
	public Produto(Object id_produto2, double preco, String nome_produto, int quantidade) {
		this.id_produto = (int) id_produto2;
		this.preco = preco;
		this.nome_produto = nome_produto;
		this.quantidade = quantidade;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getNome_produto() {
		return nome_produto;
	}
	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}
	public String toString() {
		return "Produto: \n  id_produto = "+ id_produto +" \n Preço = "+preco+" \n Nome do Produto = "+nome_produto + " \n Quantidade = " + quantidade;
	}
	
	
	
}
