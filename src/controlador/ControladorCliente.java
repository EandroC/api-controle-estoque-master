package controlador;
import java.util.ArrayList;
import dao.ClienteDAO;
import pojo.Cliente;

public class ControladorCliente {
	
	public ArrayList<Cliente> getListCliente() {
		return new ClienteDAO().getListCliente();		
	}
	public boolean addCliente(Cliente cliente) {
		return new ClienteDAO().addCliente(cliente);
	}
	public boolean deleteCliente(String cpf) {
		return new ClienteDAO().deleteCliente(cpf);
	}
	public Cliente getClienteByCpf(String cpf){
		return new ClienteDAO().getClienteByCpf(cpf);
	}
	
	public boolean updateCliente(Cliente cliente) {
		return new ClienteDAO().updateCliente(cliente);
	}
}
