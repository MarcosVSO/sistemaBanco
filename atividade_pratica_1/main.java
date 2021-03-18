package sistema_banco;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class main {
	public static void main(String[] args) throws ParseException {
		ArrayList<Conta> contas = new ArrayList<Conta>(); // Cria��o da lista de contas pertencentes ao Banco

		// Cria��o da Pessoa F�sica Jo�o
		String dataStringJao="12/12/1994";  
		Date dataNascimentoJoao = new SimpleDateFormat("dd/MM/yyyy").parse(dataStringJao);
		PessoaFisica joao = new PessoaFisica(1,"Jo�o","Rua 3, Setor Central", "11111111111",dataNascimentoJoao,"Masculino");
		System.out.println(joao.toString());
		
		//Cria��o da Pessoa Jur�dica Autos Pe�as inc.
		PessoaJuridica autoPecas = new PessoaJuridica(2,"Autos Pe�as inc.","Rua 37, Setor Norte", "22222222222","Venda de Pe�as para Autom�veis");
		System.out.println(autoPecas.toString());
		
		//Cria��o de uma conta poupan�a do Jo�o
		ContaPoupanca contaJoao = new ContaPoupanca(joao,1,0,1.03);
		contas.add(contaJoao); // Conta do jo�o � adicionada a lista de contas do Banco
		contaJoao.depositar(1000); // Ele deposita 1000 reais
		System.out.println(contaJoao.toString());
		contaJoao.sacar(698); // Ele saca 698 reais
		System.out.println(contaJoao.toString());
		contaJoao.depositar(347);// Ele deposita + 347 reais
		System.out.println(contaJoao.toString());
		
		//Cria��o de uma conta especial para a Autos Pe�as
		ContaEspecial contaAutoPecas = new ContaEspecial(autoPecas,2,0,10000);
		contas.add(contaAutoPecas); // contaAutoPecas adicionada a lista de contas do banco
		contaAutoPecas.depositar(2000); // Autos Pe�as deposita 2000
		System.out.println(contaAutoPecas.toString());
		contaAutoPecas.sacar(2200);// Autos Pe�as tenta sacar 2200 reais mas n�o tem saldo suficiente
		System.out.println(contaAutoPecas.toString());
		contaAutoPecas.sacarLimite(200); // Autos Pe�as ent�o saca 200 reais do limite da conta especial
		System.out.println(contaAutoPecas.toString());
		contaAutoPecas.sacar(2200); // Autos Pe�as agora possui salvo suficiente para sacar os 2200 reais
		System.out.println(contaAutoPecas.toString());

		contaJoao.transferir(contaAutoPecas,500); // Jo�o transfere de sua conta para a conta da Auto Pe�as 500 reais
		System.out.println(contaJoao.toString());
		System.out.println(contaAutoPecas.toString());
		
		contaJoao.depositar(10000); // Jo�o deposita 10000 em sua conta
		System.out.println(contaJoao.toString());
		contaJoao.atualizaSaldoRendimento(); // O tempo passa e o rendimento da conta � atualizada pela fun��o atualizaSaldoRendimento a uma taxa de 1.03
		contaJoao.atualizaSaldoRendimento();
		contaJoao.atualizaSaldoRendimento();
		contaJoao.atualizaSaldoRendimento();
		System.out.println(contaJoao.toString());
		
		double somaTotal = 0;
		for (Conta contaCliente: contas) { // itera entre as contas do banco, da print nas informa��es e soma o saldo para se ter um saldo total do Banco
			System.out.println(contaCliente.toString());
			somaTotal += contaCliente.getSaldo();
		}
		System.out.println("Total do saldo de todas as contas do Banco:"+somaTotal);
	}
	
}
