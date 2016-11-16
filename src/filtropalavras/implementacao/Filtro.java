package filtropalavras.implementacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * Classe que implementa FiltroInterface
 * 
 * @author Mateus e Marcus
 *
 */

public class Filtro extends FiltroAbstrato {

	/**
	 * Recebe o aquivo a ser lido
	 */

	@Override
	public void recebeArquivo(String arquivoTXT) {
		if (arquivoTXT == null)
			throw new IllegalArgumentException();

		try {
			leitorArquivo = new FileReader(arquivoTXT);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Arquivo não encontrado", e);
		}
	}

	/**
	 * Recebe o arquivo e as palavras para serem comparadas
	 */

	@Override
	public void recebeArquivo(String arquivoTXT, String[] palavrasComparadas) {
		recebeArquivo(arquivoTXT);
		setArrayComparacao(palavrasComparadas);
	}

	/**
	 * Recebe apenas as palavras a serem comparada+s
	 */

	@Override
	public void arrayComparacao(String[] palavrasComparadas) {
		if (palavrasComparadas == null)
			throw new IllegalArgumentException();

		setArrayComparacao(palavrasComparadas);
	}

	/**
	 * Imprime a palavra e quantas vezes ela foi repetida
	 * 
	 */

	@Override
	public void filtro() {
		checarArquivo();
		checarPalavras();

		String line;

		BufferedReader reader = new BufferedReader(leitorArquivo);

		try {

			while ((line = reader.readLine()) != null) {
				line = line.replaceAll("\\,", "");
				line = line.replaceAll("\\.", "");
				line = line.replaceAll("\\:", "");
				line = line.replaceAll("\\;", "");
				line = line.replaceAll("\\!", "");
				checarLinha(line);
			}

		} catch (IOException e) {
			throw new RuntimeException("Erro na leitura do arquivo", e);
		}

		map.forEach((s, i) -> System.out.println("Palvara: " + s + " Repetida: " + i + "x"));
	}

	private void checarLinha(String line) {

		StringTokenizer tokens = new StringTokenizer(line);
		String token;
		Arrays.asList(arrayComparacao).forEach(s -> map.put(s, 0));

		while (tokens.hasMoreTokens()) {
			token = tokens.nextToken().toLowerCase();

			for (String string : arrayComparacao) {

				if (string.equals(token)) {
					map.put(string, map.get(string) + 1);
				}
			}

		}

	}

	/**
	 * Recebe o arquivo a ser lido e imprime a palavra e quantas vezes ela foi
	 * repetida
	 * 
	 */

	@Override
	public void filtro(String arquivoTXT) {
		recebeArquivo(arquivoTXT);
		filtro();
	}

	/**
	 * Recebe o arquivo a ser lido, as palavras a serem comparadas e imprime a
	 * palavra e quantas vezes ela foi repetida
	 * 
	 */

	@Override
	public void filtro(String arquivoTXT, String[] palavrasComparadas) {
		recebeArquivo(arquivoTXT, palavrasComparadas);
		filtro();
	}

	/**
	 * Procura por um palavra e substitui ela
	 */

	@Override
	public void substitui(String palavraProcurada, String novaPalavra) {
		checarArquivo();
		BufferedReader br = new BufferedReader(leitorArquivo);
		
		// Cria novo arquivo para palavras substituidas
		// Lê uma linha por vez no arquivo original
		// Substitui a palavra e escreve a linha no arquivo novo
		String novoArquivo = novaPalavra + ".txt";
		String linha;
		try (FileWriter fw = new FileWriter(novoArquivo);
				PrintWriter pw = new PrintWriter(fw)) {
			
			while ((linha = br.readLine()) != null) {
				linha.replaceAll(palavraProcurada, novaPalavra);
				pw.print(linha);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recebe o arquivo a ser liso, procura por uma palavra e substitui ela
	 */

	@Override
	public void substitui(String arquivoTXT, String palavraProcurada, String novaPalavra) {
		recebeArquivo(arquivoTXT);
		substitui(palavraProcurada, novaPalavra);
	}

	/**
	 * Gera um result.txt como o novo texto sem as palavras deletadas
	 */

	@Override
	public void deletaPalavras() {
		checarArquivo();
		checarPalavras();

		try (BufferedReader reader = new BufferedReader(leitorArquivo);
				PrintWriter archive = new PrintWriter("resultado.txt");) {

			String line;

			while ((line = reader.readLine()) != null) {

				for (String string : Arrays.asList(arrayComparacao)) {

					line = line.replaceAll(string, "");
				}
				archive.println(line);
			}

		} catch (IOException e) {
			throw new RuntimeException("Arquivo nao encontrado", e);
		}

	}

	@Override
	public void deletaPalavras(String arquivoTXT) {
		recebeArquivo(arquivoTXT);
		deletaPalavras();
	}

	@Override
	public void deletaPalavras(String arquivoTXT, String[] palavrasDeletadas) {
		recebeArquivo(arquivoTXT, palavrasDeletadas);
		deletaPalavras();
	}

	private void checarArquivo() {
		if (leitorArquivo == null)
			throw new IllegalAccessError("Arquivo n�o definido");
	}

	private void checarPalavras() {
		if (arrayComparacao == null)
			throw new IllegalAccessError("Array de palavras n�o definido");
	}

}
