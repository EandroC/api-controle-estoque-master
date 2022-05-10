package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.ConnectionFactory;
import pojo.Cliente;

public class ClienteDAO {
	private Connection connection;

	//Adicionar
	public boolean addCliente(Cliente cliente) {
		String sql = "INSERT INTO cliente(cpf,nome) VALUES (?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, cliente.getNome());


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
	public ArrayList<Cliente> getListCliente() {
		String sql = "SELECT * FROM cliente;";
		ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();

		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String cpf = rs.getString("CPF");
				String nome = rs.getString("nome");


				Cliente cliente = new Cliente(cpf, nome);

				listaCliente.add(cliente);

			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaCliente;
	}
	//Deletar
	public boolean deleteCliente(String cpf) {
		String sql = "DELETE FROM cliente WHERE cpf = ?";

		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1,cpf);

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

	public Cliente getClienteByCpf(String cpf){
		String sql = "SELECT * FROM cliente WHERE cpf = ?;";
		
		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, cpf);

			// executa
			//stmt.execute();

			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {

				Cliente cliente = new Cliente(cpf, rs.getString("nome"));

				stmt.close();
				return cliente;
			}
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

	//Adicionar
	public boolean updateCliente(Cliente cliente) {
		String sql = "UPDATE cliente SET nome = ? WHERE cpf = ?";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);


			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());


			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;

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
