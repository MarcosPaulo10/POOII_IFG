
public class ValorNegativoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValorNegativoException(String numero) {
		super("Valor para crédito na conta " + numero + " deve ser maior que zero!");
	}
	
}