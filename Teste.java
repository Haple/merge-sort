import java.util.Arrays;

public class Teste{

	public static void main(String[] args){
		try{
			Vetor<Integer> teste = new Vetor<Integer>(10);
			teste.adicione(231);
			teste.adicione(378);
			teste.adicione(34);
			teste.adicione(2);
			teste.adicione(123);
			System.out.println("Vetor desordenado: " + teste);
			teste.mergeSort();
			System.out.println("Vetor ordenado: " + teste);

			teste.remova(4);
			System.out.println("Posicao 4 removida: " + teste);
			teste.remova(new Integer(34));
			System.out.println("Item 34 removido: " + teste);

			Vetor<Integer> teste2 = new Vetor<Integer>(9);
			teste2.adicione(231);
			teste2.adicione(123);
			teste2.adicione(4);
			teste2.mergeSort();
			System.out.println("Teste == Teste2 ? " + teste.equals(teste2));

			teste2.adicione(129);
			System.out.println("Teste hash: " + teste.hashCode());
			System.out.println("Teste2 hash: " + teste.hashCode());

			Vetor<Integer> teste3 = (Vetor<Integer>)teste.clone();
			System.out.println("Teste3 é o mesmo q Teste2? " + (teste3 == teste2));
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
