package controlador;
import java.util.ArrayList;
import dao.ProdutoDAO;
import pojo.Produto;

public class ControladorProduto {
	
	public ArrayList<Produto> getListProduto() {
		return new ProdutoDAO().getListaProduto();
	}
	public boolean deleteProduto(int id_produto) {
		return new ProdutoDAO().deleteProduto(id_produto);
	}
	public boolean addProduto(Produto produto) {
		return new ProdutoDAO().addProduto(produto);
	}
	public Produto getProdutoById(int id){
		return new ProdutoDAO().getProdutoById(id);
	}
	
	public boolean updateProduto(Produto produto) {
		return new ProdutoDAO().updateProduto(produto);
	}
	
	}

		

