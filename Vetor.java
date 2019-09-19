import java.util.Arrays;
import java.lang.reflect.*;

public class Vetor <X extends Comparable<X>>{

	private Object[] vetor;
	private int qtd=0;

	/**
	 * Constrói um novo vetor com uma capacidade fixa.
	 */
	public Vetor(int capacidade) throws Exception{
		if(capacidade<=0)
			throw new Exception("Capacidade invalida");
		this.vetor=new Object[capacidade];
	}

	/**
	 * Construtor de cópia.
	 */
	public Vetor(Vetor modelo) throws Exception{
		if(modelo==null)
			throw new Exception("Vetor inválido");
		this.qtd = modelo.qtd;
		this.vetor = new Object[modelo.vetor.length];
		for(int i=0; i<this.qtd; i++)
			this.vetor[i]=meuCloneDeX((X)modelo.vetor[i]);
	}

	public Object clone(){
		Vetor clone = null;
		try{
			clone = new Vetor(this);
		}catch(Exception e){}
		return clone;
	}

	private X meuCloneDeX(X x){
		X ret=null;
		try{
			Class<?> classe = x.getClass();
			Method metodo = classe.getMethod("clone");
			ret=(X)metodo.invoke(x);
		}catch(Exception e){}
		return ret;
	}

	/**
	 * Adiciona um item no vetor.
	 */
	public void adicione(X x) throws Exception{
		if(x==null)
			throw new Exception("Valor ausente");
		if(this.qtd==this.vetor.length)
			throw new Exception("Nao cabe");
		if(x instanceof Cloneable)
			this.vetor[this.qtd]=meuCloneDeX(x);
		else
			this.vetor[this.qtd]=x;
		this.qtd++;
	}

	/**
	 * Remove um elemento específico do vetor.
	 * Caso tenham itens repetidos, o primeiro item
	 * encontrado é o que será removido.
	 */
	public void remova(X x) throws Exception{
		if(x==null)
			throw new Exception("Item inválido");
		if(this.qtd==0)
			throw new Exception("Vetor vazio");
		int i = 0;
		for(; i<this.qtd; i++){
			if(this.vetor[i].equals(x)){
				this.remova(i);
			}
		}
	}

	/**
	 * Remove um item de uma posição específica do vetor.
	 */
	public void remova(int posicao) throws Exception{
		if(posicao >= this.qtd || posicao < 0)
			throw new Exception("Posicao inválida");
		for(int i = posicao; i < this.qtd-1; i++){
			this.vetor[i] = this.vetor[i+1];
		}
		this.vetor[this.qtd-1] = null;
		this.qtd--;
	}

	/**
	 * Ordena o vetor usando o algoritmo Merge Sort.
	 */
	public void mergeSort() throws Exception{
		if(this.qtd<=1)
			throw new Exception("Nada para ordenar");
		this.vetor=this.sort(this.vetor, 0,this.qtd-1);
	}

	private  Object[] sort(Object[] vetor, int inicio, int fim){
		// condicao de parada
		if(inicio == fim){
			Object[] retorno = new Object[1];
			retorno[0] = vetor[inicio];
			return retorno;
		}
		int tamanho = fim - inicio + 1;
		int metade = inicio + tamanho/2;
		return merge(sort(vetor,inicio, metade-1),
			sort(vetor,metade,fim));
	}

	private Object[] merge(Object[] v1, Object[] v2){
                Object[] v3 = new Object[v1.length + v2.length];
		int i=0; int j=0; int k=0;
		while(i < v1.length && j < v2.length){
			if(((X)v1[i]).compareTo((X)v2[j]) > 0)
				v3[k++] = v2[j++];
			else if (((X)v1[i]).compareTo((X)v2[j]) < 0)
				v3[k++] = v1[i++];
			else {
	 			v3[k++] = v1[i++];
				v3[k++] = v2[j++];
			}
		}
		while(i < v1.length)
			v3[k++] = v1[i++];
		while(j < v2.length)
			v3[k++] = v2[j++];
		return v3;
	}

	public int hashCode(){
		int primo = 31;
		int resultado = 1;
		resultado += primo * resultado + this.qtd;
		for(int i=0; i<this.qtd; i++)
			resultado += primo * resultado 
				+ ((this.vetor[i] == null) ? 0 : this.vetor[i].hashCode());
		return resultado;
	}

	/**
	 * Verifica se outro objeto é igual a ele.
	 * @param obj o objeto a ser comparado.
	 */
	public boolean equals(Object obj){
		if(obj==null)
			return false;
		if(this==obj)
			return true;
		if(this.getClass() != obj.getClass())
			return false;
		Vetor outro = (Vetor)obj;
		if(this.qtd != outro.qtd)
			return false;
		for(int i=0; i<this.qtd; i++)
			if(!this.vetor[i].equals(outro.vetor[i]))
				return false;
		return true;
	}

	@Override
	public String toString() {
		String retorno = "[";
		for(int i=0; i<this.qtd-1;i++){
			retorno+=this.vetor[i].toString()+", ";
		}
		retorno+=this.vetor[this.qtd-1].toString()+"]";
		return retorno;
	}
}
