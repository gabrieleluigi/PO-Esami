package torneidicalcio;

public class Tesserato implements Comparable<Tesserato>{

	private int numeroTessera;
	private String nome;
	private String cognome;
	
	public Tesserato(int numeroTessera, String nome, String cognome){
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

	@Override
	public int compareTo(Tesserato altro) {
		int diff =  this.getNome().compareTo(altro.getNome());

		if(diff!=0)
			return diff;
		else
			return this.getCognome().compareTo(altro.getCognome());
	}
	
	public int getNumeroTessera() {
		return numeroTessera;
	}


}
