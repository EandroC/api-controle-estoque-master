package controlador;

import java.util.ArrayList;

import dao.VendasDAO;
import pojo.Vendas;

public class ControladorVenda {
	
	public ArrayList<Vendas> getListVendas() {
		return new VendasDAO().getListVendas();
	}
	
	public boolean vender(Vendas vendas) {
		return new VendasDAO().vender(vendas);
	}
	
	

}
