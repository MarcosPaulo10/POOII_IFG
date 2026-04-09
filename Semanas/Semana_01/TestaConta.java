
public class TestaConta {

	public static void main(String[] args) {
		
		Conta c1 = new Conta("C0001", 1000);
		Conta c2 = new Conta("C0002", 500);
		
		System.out.println(c1);
		try {
			c1.creditar(50);
			System.out.println(c1);
			c1.debitar(1100);
			System.out.println(c1);
			
		}
		catch (ValorNegativoException | SaldoNegativoException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(c1);
		
		System.out.println(c2);
		
	}

}