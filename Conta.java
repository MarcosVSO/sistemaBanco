package sistema_banco;

public class Conta {
	private Pessoa cliente;
	private int nrConta;
	private double saldo;
	
	public Conta(Pessoa cliente, int nrConta, double saldo) {
		this.cliente = cliente;
		this.nrConta = nrConta;
		this.saldo = saldo;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public int getNrConta() {
		return nrConta;
	}

	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void sacar(double saque) {
		if (temSaldo(saque)) {
			this.saldo = this.saldo - saque;
		}else {
			System.out.println("Saldo para saque insuficiente!!");
		}
	}
	
	private void debitar(double debito) {
		if (temSaldo(debito)) {
			this.saldo = this.saldo - debito;
		}else {
			System.out.println("Saldo para debitar insuficiente!!");
		}
	}
	
	protected boolean temSaldo(double saldoSuficiente) {
		if (this.saldo >= saldoSuficiente) {
			return true;
		}else {
			return false;
		}
	}
	
	public void depositar(double valorDeposito) {
		this.saldo += valorDeposito;
	}
	
	public void transferir(Conta contaAlvo,double valorTransferencia) {
		if (this.temSaldo(valorTransferencia)){
			contaAlvo.saldo += valorTransferencia;
			this.saldo -= valorTransferencia;
		}else {
			System.out.println("Saldo para transferência insuficiente!!");
		}
	}

	@Override
	public String toString() {
		return "Conta [cliente=" + cliente.getNome() + ", nrConta=" + nrConta + ", saldo=" + saldo + "]";
	}
	
	
	
}
