package sistema_banco;

public class ContaEspecial extends Conta{
	private double limite;
	
	public ContaEspecial(Pessoa cliente, int nrConta, double saldo,double limite) {
		super(cliente, nrConta, saldo);
		this.limite = limite;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
	
	public void sacarLimite(double qtdSaqueLimite) {
		if (this.limite >= qtdSaqueLimite) {
			this.depositar(qtdSaqueLimite);
			this.limite -= qtdSaqueLimite;
		}else {
			System.out.println("Limite não disponível para saque, limite total disponível atualmente:");
		}
	}
	
	

}
