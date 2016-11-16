package filtropalavras;

import filtropalavras.implementacao.Filtro;
import filtropalavras.interfaces.FiltroInterface;

public class Main {
	
	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		
		FiltroInterface fi = new Filtro();
		
		String[] array = {"texto", "vamos"};
		
		fi.recebeArquivo("texto.txt", array);
		
		fi.substitui("texto", "TP1!@!@!");
		
		//fi.deletaPalavras();
		
		
		
	}
}
