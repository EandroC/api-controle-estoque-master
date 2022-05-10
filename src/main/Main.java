package main;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import controlador.ControladorCliente;
import controlador.ControladorProduto;
import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendasDAO;
import pojo.Cliente;
import pojo.Produto;
import pojo.Vendas;

import java.util.Locale; 

public class Main {

	public static void main(String[] args) {
		ClienteDAO clienteDAO = new ClienteDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		VendasDAO vendaDAO = new VendasDAO();

		int option;
		Scanner scanner = new Scanner(System.in);
		scanner.useLocale(Locale.ENGLISH);
		boolean end = false;

		while(!end) {
			System.out.println("| 1 | Cadastrar cliente");
			System.out.println("| 2 | Listar clientes");
			System.out.println("| 3 | Apagar um cliente");
			System.out.println("| 4 | Editar cliente");
			System.out.println("| 5 | Cadastar produto");
			System.out.println("| 6 | Apagar produto");
			System.out.println("| 7 | Listar produtos");
			System.out.println("| 8 | Editar produto");
			System.out.println("| 9 | Cadastrar venda");
			System.out.println("| 10 | Listar venda");
			System.out.println("| 0 | Sair");

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option){
			case 1:{ // cadastrar cliente
				String cpf, nome;
				System.out.println("Digite o CPF do cliente:");
				cpf = scanner.nextLine();
				System.out.println("Digite o nome do cliente:");
				nome = scanner.nextLine();
				
				if(new ControladorCliente().addCliente(new Cliente(cpf, nome))) {
					System.out.println("Inserido com sucesso!");
				}else {
					System.err.println("Erro ao inserir o cliente!");
				}
				break;
			}case 2:{ // listar clientes
				ArrayList<Cliente> clienteList = new ControladorCliente().getListCliente();
				for(Cliente cliente : clienteList){
					System.out.println(cliente.toString());
				}
				break;
			}case 3:{ // apagar cliente
				System.out.println("Digite o CPF do cliente que deseja apagar:");
				String cpf = scanner.nextLine();
				
				if(new ControladorCliente().deleteCliente(cpf)) {
					System.out.println("Deletado com sucesso!");
				}else {
					System.err.println("Erro ao deletar o cliente!");
				}
				
				break;
			}case 4:{ // editar cliente
				String cpf, nome;
				System.out.println("Digite o CPF do cliente:");
				cpf = scanner.nextLine();
				System.out.println("Digite o nome do cliente:");
				nome = scanner.nextLine();
				
				if(new ControladorCliente().updateCliente(new Cliente(cpf, nome))) {
					System.out.println("Atualizado com sucesso!");
				}else {
					System.err.println("Erro a atualizar o cliente!");
				}
				break;
			}
	
			case 5:{ //cadatrar produto
				int id_produto;
				double preco;
				String nome_produto;
				int quantidade;
				
				System.out.println("Digite o ID do produto:");
				id_produto = scanner.nextInt();
				System.out.println("Digite o preço do produto:");
				preco = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Digite o nome do produto:");
				nome_produto = scanner.nextLine();
				System.out.println("Digite a quantidade do produto:");
				quantidade = scanner.nextInt();
				
				
				if(new ControladorProduto().addProduto(new Produto(id_produto, preco, nome_produto, quantidade))) {
					System.out.println("Inserido com sucesso!");
				}else {
					System.err.println("Erro ao inserir o produto!");
				}
				break;
			} case 6: //apagar produto
				System.out.println("Digite o ID do produto que deseja apagar:");
				int id_produto = scanner.nextInt();
				
				if(new ControladorProduto().deleteProduto(id_produto)) {
					System.out.println("Deletado com sucesso!");
				}else {
					System.err.println("Erro ao deletar o produto!");
				}				
				break;
			
			case 7: //listar produtos
				ArrayList<Produto> produtoList = new ControladorProduto().getListProduto();
				for(Produto produto : produtoList){
					System.out.println(produto.toString());
				}
				break;
				
			case 8: // editar produto
				double preco_u;
				String nome_produto;
				int quantidade; 
				
				System.out.println("Digite o ID do produto:");
				id_produto = scanner.nextInt();
				System.out.println("Digite o preço do produto:");
				preco_u = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Digite o nome do produto:");
				nome_produto = scanner.nextLine();
				System.out.println("Digite a quantidade do produto:");
				quantidade = scanner.nextInt();
				
				
				
				if(new ControladorProduto().updateProduto(new Produto(id_produto, preco_u, nome_produto, quantidade))) {
					System.out.println("Atualizado com sucesso!");
				}else {
					System.err.println("Erro a atualizar o produto!");
				}				
				break;
			
			case 9: //cadastrar venda
				
				int idProduto;
				System.out.println("Digite o ID do produto:");
				idProduto = scanner.nextInt();
				
				String cpf;
				System.out.println("Digite o CPF do cliente:");
				cpf = scanner.next();
				
				int qtd;
				System.out.println("Digite a quantidade do produto:");
				qtd = scanner.nextInt();
				
				double preco;
				System.out.println("Digite o preço do produto:");
				preco = scanner.nextDouble();
				
				Cliente cliente = clienteDAO.getClienteByCpf(cpf);
				Produto produto_c = produtoDAO.getProdutoById(idProduto);
				
				if( cliente != null && produto_c != null ) {
					Date data = new Date();
					Vendas vendas = new Vendas(produto_c.getId_produto(), preco, new Timestamp(data.getTime()), qtd, cliente.getCpf());
					VendasDAO daoVenda = new VendasDAO();
					daoVenda.vender(vendas);
					
					System.out.println("Vendeu!");
				}
			
			case 10: //listar vendas
				ArrayList<Vendas> listVendas = vendaDAO.getListVendas();
				for(Vendas vendas : listVendas) {
					System.out.println (vendas.getCpf() +  " comprou o produto "  + vendas.getId_produto());
				}
			break;
				
			
			case 0: // sair
				end = true;
				break;
			
		
		}
	
	}
	}
}
			


