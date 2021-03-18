package aula3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

import dominio.Categorias;
import dominio.Conta;
import dominio.Pessoa;
import dominio.PessoaFisica;
import dominio.PessoaJuridica;

public class Menu {

	public void menuPrincipal(Scanner sc) {
		Integer escolha = 1;
		Conta conta;
		do {
			this.showMenuPrincipal();
			try {
				escolha = sc.nextInt();
				switch (escolha) {
				case 1:
					this.cadatrarCliente(sc);
					break;

				case 2:
					conta = this.buscarConta(sc);
					this.menuConta(sc, conta);
					break;
				case 3:
					this.cadatrarCliente(sc);
					break;

				case 4:
					this.relatorioContas();
					break;

				case 5:
					System.out.println("5 – Sair");
					break;

				default:
					System.out.println("Opção Incorreta");
				}
			} catch (Exception e) {
				System.out.println("Opção Incorreta, sair.");
				escolha = 5;
			}
		} while (escolha != 5);

	}

	private Pessoa cadatrarCliente(Scanner sc) throws ParseException {

		System.out.println("--- Novo Cliente-----");
		System.out.println("Selecione o tipo de Pessoa");
		System.out.println("1 - Pessoa Física");
		System.out.println("2 - Pessoa Jurídica");
		
		Integer tipo = sc.nextInt();
		
		
		System.out.println("--- Informe o ID -----");
		Integer id = sc.nextInt();
		try {
			for (Conta c : Main.contas) {
				if (id == c.getNrConta()) {
					throw new MinhaException("---ID de conta já utilizado!Digite um número diferente de: "+id);
				}
			}
		}catch(MinhaException e) {
			System.out.println(e);
			System.exit(0);
		}
		sc.nextLine();
		System.out.println("--- Informe o Nome -----");
		String nome = sc.nextLine();
		
		
		System.out.println("--- Informe o Endereço -----");
		String endereco = sc.nextLine();
		try {
			if (endereco.length() < 10) {
				throw new MinhaException("---Tamanho do Endereço muito pequeno!---");
			}
		}catch(MinhaException e) {
			System.out.println(e);
		}	
		
		if(tipo == 1) {
			System.out.println("--- Informe o CPF -----");
			String cpf = sc.nextLine();
			try {
				if (cpf.length() > 11) {
					throw new MinhaException("---CPF só possuem 11 caracacteres!!!---");
				}
			}catch(MinhaException e) {
					System.out.print(e);
					System.exit(0);
			}
			System.out.println("--- Informe a Data de Nascimento -----");
			//String[] dtAux = sc.next().trim().split("/");
			
			String dtAux = sc.next();
			Date dtNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dtAux);
			
			//Date dtNascimento = Date.from(Instant.parse(dtAux[2]+"-"+dtAux[2]+"-"+dtAux[0]+"T00:00:00Z"));

			
					
			System.out.println("--- Informe o Genero (M/F) -----");
			String genero = sc.next();
			
			PessoaFisica clienteNovo = new PessoaFisica(id, nome, endereco, cpf, dtNascimento, genero);
			
			Main.clientes.add(clienteNovo);
			System.out.println(clienteNovo.toString());
			
			return clienteNovo;
			
		}else {
			System.out.println("--- Informe o CNPJ -----");
			String cnpj = sc.nextLine();
			
			System.out.println("--- Informe a Atividade -----");
			String atividade = sc.nextLine();
			
			PessoaJuridica clienteNovo = new PessoaJuridica(id, nome, endereco,cnpj, atividade);
			Main.clientes.add(clienteNovo);
			return clienteNovo;
		}

	}

	private void menuConta(Scanner sc, Conta conta) {
		
		Integer escolha = 1;
		do {
			this.showMenuConta(conta);
			try {
				escolha = sc.nextInt();
				Double vr;
				switch (escolha) {
				case 1: 
					System.out.println("Qual informação da sua conta deseja alterar?");
					System.out.println("1 - Cliente da conta?");
					System.out.println("2 - Número da conta?");
					System.out.println("3 - Categoria da conta?");
					int opcaoMenu;
					opcaoMenu = sc.nextInt();
					switch (opcaoMenu) {
						case 1:
							System.out.println("Informe o ID do Novo Cliente da Conta");
							int idClienteNovo = sc.nextInt();
							Pessoa clienteNovo = null;
							try {
								for (Pessoa cliente : Main.clientes) {
									if (cliente.getId().equals(idClienteNovo)) {
										clienteNovo = cliente;
										break;
									}
								}
								if (clienteNovo == null) {
									throw new MinhaException("---Cliente para alteração não encontrado! Cadastre Novo Cliente!---");
								}
							}catch(MinhaException e) {
								System.out.println(e);
								clienteNovo = this.cadatrarCliente(sc);
							}
							conta.setCliente(clienteNovo);
							System.out.println("Cliente alterado com Sucesso");
							System.out.println(conta.toString());
						break;
						case 2:
							System.out.println("Informe o Novo número da Conta");
							int novoNumero = sc.nextInt();
							conta.setNrConta(novoNumero);
						break;
						case 3:
							System.out.println("Informe a nova Categoria da Conta");
							System.out.println("1 - SIMPLES");
							System.out.println("2 - EXECUTIVA");
							System.out.println("3 - PREMIUM");
							System.out.println("4 - PERSONALITE");
							int categoriaNova = sc.nextInt();
							if (categoriaNova == 1) {
								conta.setCategoriaConta(Categorias.SIMPLES);
							}else if(categoriaNova == 2) {
								conta.setCategoriaConta(Categorias.EXECUTIVA);
							}else if (categoriaNova == 3) {
								conta.setCategoriaConta(Categorias.PREMIUM);
							}else if (categoriaNova == 4) {
								conta.setCategoriaConta(Categorias.PERSONALITE);
							}
						break;
					}
				break;
				case 2: 
					System.out.println("Informe o Valor do Depósito");
					vr = sc.nextDouble();
					conta.depositar(vr);
					break;
				case 3: 
					System.out.println("Informe o Valor para Saque");
					vr = sc.nextDouble();
					conta.sacar(vr);
					
					break;
				case 4: 
					Conta dest = this.buscarConta(sc);
					System.out.println("Informe o Valor para Transferência");
					vr = sc.nextDouble();
					conta.transferir(vr, dest);
					break;
				case 5:
					System.out.println("-------------------------");
					System.out.println("--- SALDO: R$ "+conta.getSaldo());
					System.out.println("-------------------------");
					
					break;
				}

			}  catch (Exception e) {
				System.out.println("Opção Incorreta, sair.");
				escolha = 6;
			}
		}while (escolha != 6);
	}

	private void showMenuPrincipal() {
		System.out.println("-------------------------");
		System.out.println("---Selecione Uma Opção---");
		System.out.println("-------------------------");
		System.out.println("1 – Abrir Nova Conta");
		System.out.println("2 – Selecionar Conta");
		System.out.println("3 – Cadastrar Cliente");
		System.out.println("4 – Relatórios");
		System.out.println("5 – Sair");
		System.out.println("-------------------------");
	}

	private void showMenuConta(Conta conta) {
		System.out.println("-------------------------");
		System.out.println("Cliente: " + conta.getCliente().getNome());
		System.out.println("Nr Conta: " + conta.getNrConta());
		System.out.println("-------------------------");
		System.out.println("---Selecione Uma Opção---");
		System.out.println("-------------------------");
		System.out.println("1 – Alterar Conta");
		System.out.println("2 – Deposito");
		System.out.println("3 – Saque");
		System.out.println("4 – Transferência");
		System.out.println("5 – Saldo");
		System.out.println("6 – Sair");
		System.out.println("-------------------------");
	}

	public Conta buscarConta(Scanner sc) {

		Conta conta = null;
		do {
			System.out.println("------------------------------");
			System.out.println("---Digite o número da Conta---");
			System.out.println("------------------------------");
			Integer escolha = sc.nextInt();
			for (Conta c : Main.contas) {

				if (c.getNrConta().equals(escolha)) {
					conta = c;
					break;
				}
			}
			if (conta == null) {
				System.out.println("------------------------------");
				System.out.println("-----Conta Não Encontrada-----");
				System.out.println("------------------------------");				
			}

		} while (conta == null);

		return conta;
	}
	
	public void relatorioContas() {
		double totalSaldoContas = 0.0;
		for (Conta c : Main.contas) {
			System.out.println(c.toString());
			totalSaldoContas += c.getSaldo();
		}
		System.out.println("Saldo total das Contas: "+totalSaldoContas);
	}
}
