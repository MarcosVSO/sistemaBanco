package sistema_banco;
import java.util.Date;

public class PessoaFisica extends Pessoa{
	private String cpf;
	private Date dtNascimento;
	private String genero;
	
	public PessoaFisica(int id, String nome, String endereco,String cpf,Date dtNascimento,String genero) {
		super(id, nome, endereco);
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.genero = genero;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "PessoaFisica [cpf=" + cpf + ", dtNascimento=" + dtNascimento + ", genero=" + genero + "]";
	}
	
}
