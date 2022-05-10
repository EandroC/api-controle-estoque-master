package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.ConnectionFactory;
import pojo.Cliente;
import pojo.Produto;
import pojo.Vendas;

public class VendasDAO {
	// a conexão com o banco de dados
		private Connection connection;

	public boolean vender(Vendas vendas) {
		String sql = "INSERT INTO Vendas(id_produto, cpf, data, quantidade, preco) VALUES (?, ?, ?, ? ,?)";
		this.connection = new ConnectionFactory().getConnection();
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
				// seta os valores
			stmt.setInt(1, vendas.getId_produto());
			stmt.setString(2, vendas.getCpf());
			stmt.setTimestamp(3, vendas.getData());
			stmt.setInt(4, vendas.getQuantidade());
			stmt.setDouble(5, vendas.getPreco());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return false;
	}
		
	public ArrayList<Vendas> getListVendas() {
		String sql = "SELECT * FROM vendas;";
		ArrayList<Vendas> listaVendas = new ArrayList<Vendas>();
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
				
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String cpf = rs.getString("CPF");
				ClienteDAO clienteDAO = new ClienteDAO();
					Cliente cliente = clienteDAO.getClienteByCpf(cpf);
					
					int idProduto = Integer.parseInt(rs.getString("id_produto"));
					ProdutoDAO produtoDAO = new ProdutoDAO();
					Produto produto = produtoDAO.getProdutoById(idProduto);
					
					Vendas venda = new Vendas(cliente.getCpf(), produto.getId_produto());
					
					listaVendas.add(venda);
				}
				stmt.close();
				return listaVendas;
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
}
