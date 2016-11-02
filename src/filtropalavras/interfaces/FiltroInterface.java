package filtropalavras.interfaces;

public interface FiltroInterface {

	public void recebeArquivo(String arquivoTXT);
	
	public void recebeArquivo(String arquivoTXT, String[] palavrasComparadas);
	
	public void arrayComparacao(String[] palavrasComparadas);
	
	public void filtro();
	
	public void filtro(String arquivoTXT);
	
	public void filtro(String arquivoTXT, String[] palavrasComparadas);
	
	public void substitui(String palavraProcurada, String novaPalavra);
	
	public void substitui(String arquivoTXT, String palavraProcurada, String novaPalavra);
	
	public void deletaPalavras();
	
	public void deletaPalavras(String arquivoTXT);
	
	public void deletaPalavras(String arquivoTXT, String[] palavrasDeletadas);	
	
}
