package filtropalavras.implementacao;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import filtropalavras.interfaces.FiltroInterface;

public abstract class FiltroAbstrato implements FiltroInterface {

	protected FileReader leitorArquivo;
	protected String[] arrayComparacao;
	protected Map<String, Integer> map = new HashMap<>();

	public String[] getArrayComparacao() {
		return arrayComparacao;
	}

	public void setArrayComparacao(String[] arrayComparacao) {
		this.arrayComparacao = arrayComparacao;
	}

	public FileReader getArquivo() {
		return leitorArquivo;
	}

	public void setArquivo(FileReader arquivo) {
		this.leitorArquivo = arquivo;
	}

}
