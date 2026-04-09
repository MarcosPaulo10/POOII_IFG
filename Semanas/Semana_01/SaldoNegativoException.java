
public class SaldoNegativoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaldoNegativoException(String numero) {
		super("Saldo Insuficiente na conta de número : " + numero);
	}

}