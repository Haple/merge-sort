import java.util.Arrays;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Teste{

	private static int TAMANHO_VETOR = 10;

	public static void main(String[] args){
		boolean thread = false;
		if(args.length == 0){
			System.out.println("Primeiro parâmetro: números de elementos no vetor de teste");
			System.out.println("Segundo parâmetro (opcional): --thread");
			return;
		}
		TAMANHO_VETOR = Integer.parseInt(args[0]);
		if(args.length == 2 && args[1].toLowerCase().equals("--thread")){
			thread = true;
		}
		if(thread)
			testeVetorComParalelismo();
		else
			testeVetorSemParalelismo();
	}

	private static void testeVetorComParalelismo(){
		try{
			System.out.println("Vetor com Threads");
			Vetor<Integer> vet = new Vetor<Integer>(TAMANHO_VETOR);
			for(int i=0;i<TAMANHO_VETOR;i++){
				vet.adicione(new Random().nextInt(TAMANHO_VETOR));
			}
			System.out.println("Vetor criado com " + TAMANHO_VETOR + " posições");
			LocalDateTime inicio = LocalDateTime.now();
			vet.mergeSort();
			LocalDateTime fim = LocalDateTime.now();
			System.out.println("Milisegundos: " + ChronoUnit.MILLIS.between(inicio,fim));
			//System.out.println("Vetor: " + vet);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	private static void testeVetorSemParalelismo(){
		try{
			System.out.println("Vetor sem Threads");
			Vetor0<Integer> vet = new Vetor0<Integer>(TAMANHO_VETOR);
			for(int i=0;i<TAMANHO_VETOR;i++){
				vet.adicione(new Random().nextInt(TAMANHO_VETOR));
			}
			System.out.println("Vetor criado com " + TAMANHO_VETOR + " posições");
			LocalDateTime inicio = LocalDateTime.now();
			vet.mergeSort();
			LocalDateTime fim = LocalDateTime.now();
			System.out.println("Milisegundos: " + ChronoUnit.MILLIS.between(inicio,fim));
			//System.out.println("Vetor: " + vet);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
