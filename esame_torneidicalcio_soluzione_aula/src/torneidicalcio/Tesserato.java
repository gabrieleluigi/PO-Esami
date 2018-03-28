package torneidicalcio;

public class Tesserato {
	private int numeroTessera;
	private String nome;
	private String cognome;
	
	public Tesserato(int numeroTessera, String nome, String cognome) {
		this.numeroTessera = numeroTessera;
		this.nome = nome;
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}
}
