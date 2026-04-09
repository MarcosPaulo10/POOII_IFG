
public class Conta {

	private String numero;
	private double saldo;
	
	public Conta(String numero, double saldo) {
		this.numero = numero;
		this.saldo = saldo;
	}

	public String getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}
	
	public void debitar(double valor) throws SaldoNegativoException {
		if (valor <= this.saldo)
			this.saldo -= valor;
		else 
			throw new SaldoNegativoException(this.numero);		
	}
	
	public void creditar(double valor) throws ValorNegativoException {
		if (valor > 0)
			this.saldo += valor;
		else 
			throw new ValorNegativoException(this.numero);
	}

	@Override
	public String toString() {
		return "Conta [numero=" + numero + ", saldo=" + saldo + "]";
	}
	
	
}