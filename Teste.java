import java.util.Arrays;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Teste{

	private static final int TAMANHO_VETOR = 50000000;

	public static void main(String[] args){
		if(args.length > 0 && args[0].toLowerCase().equals("p"))
			testeVetorComParalelismo();
		else
			testeVetorSemParalelismo();
	}

	private static void testeVetorComParalelismo(){
		try{
			System.out.println("Vetor com Threads");
			Vetor<Integer> vet = new Vetor<Integer>(TAMANHO_VETOR);
			for(int i=0;i<TAMANHO_VETOR;i++){
				vet.adicione(new Random().nextInt(1000));
			}
			System.out.println("Vetor criado com " + TAMANHO_VETOR + " posições");
			LocalDateTime inicio = LocalDateTime.now();
			vet.mergeSort();
			LocalDateTime fim = LocalDateTime.now();
			System.out.println("Milisegundos: " + ChronoUnit.MILLIS.between(inicio,fim));
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	private static void testeVetorSemParalelismo(){
		try{
			System.out.println("Vetor sem Threads");
			Vetor0<Integer> vet = new Vetor0<Integer>(TAMANHO_VETOR);
			for(int i=0;i<TAMANHO_VETOR;i++){
				vet.adicione(new Random().nextInt(1000));
			}
			System.out.println("Vetor criado com " + TAMANHO_VETOR + " posições");
			LocalDateTime inicio = LocalDateTime.now();
			vet.mergeSort();
			LocalDateTime fim = LocalDateTime.now();
			System.out.println("Milisegundos: " + ChronoUnit.MILLIS.between(inicio,fim));
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
