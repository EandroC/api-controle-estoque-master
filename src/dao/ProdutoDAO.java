package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.ConnectionFactory;
import pojo.Produto;

public class ProdutoDAO {
	private Connection connection;

	public boolean addProduto(Produto produto) {
		String sql = "INSERT INTO produto(id_produto, preco, nome_produto, quantidade) VALUES (?, ?, ?, ?)";
		this.connection = new ConnectionFactory().getConnection();
	try {
		// prepared statement para inserção
					PreparedStatement stmt = connection.prepareStatement(sql);

					// seta os valores
      				stmt.setInt(1, produto.getId_produto());
					stmt.setDouble(2, produto.getPreco());
					stmt.setString(3, produto.getNome_produto());
					stmt.setInt(4, produto.getQuantidade());

					int qtdRowsAffected = stmt.executeUpdate();
					stmt.close();
					if (qtdRowsAffected > 0)
						return true;
					return false;
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				} finally {
					try {
						this.connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return false;
		}	
	// listar
	public ArrayList<Produto> getListaProduto() {
		String sql = "SELECT * FROM produto;";
		ArrayList<Produto> listaProduto = new ArrayList<Produto>();
		
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id_produto = rs.getInt("id_produto");
				String nome_produto = rs.getString("nome_produto");
				double preco = rs.getDouble("preco");
				int quantidade = rs.getInt("quantidade");
				
				
				Produto produto = new Produto(id_produto, preco,nome_produto, quantidade);
				
				listaProduto.add(produto);
				
			}

			stmt.close();
			
			return listaProduto;
			
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public Produto getProdutoById(int id){
		String sql = "SELECT * FROM produto WHERE id_produto = ?;";
		
		this.connection = new ConnectionFactory().getConnection();
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, id);

			// executa
			//stmt.execute();
			
			ResultSet rs = stmt.executeQuery();
			Produto produto = null;
			if(rs.next())
				produto = new Produto(id, rs.getDouble("preco"), rs.getString("nome_produto"), rs.getInt("quantidade"));
			
			stmt.close();
			
			return produto;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean deleteProduto(int id_produto) {
		String sql = "DELETE FROM produto WHERE id_produto = ?";
		
		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, id_produto);

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
	public boolean updateProduto(Produto produto) {
		String sql = "UPDATE produto SET preco = ?, nome_produto = ?, quantidade = ? WHERE id_produto = ?";
		this.connection = new ConnectionFactory().getConnection();
	try {
		// prepared statement para inserção
					PreparedStatement stmt = connection.prepareStatement(sql);

					// seta os valores
					stmt.setDouble(1, produto.getPreco());
					stmt.setString(2, produto.getNome_produto());
					stmt.setInt(3, produto.getQuantidade());
					stmt.setInt(4, produto.getId_produto());
      				

					int qtdRowsAffected = stmt.executeUpdate();
					stmt.close();
					if (qtdRowsAffected > 0)
						return true;
					return false;
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				} finally {
					try {
						this.connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return false;
	}
}