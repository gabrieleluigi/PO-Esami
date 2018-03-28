package torneidicalcio;

public class Calciatore extends Tesserato{
	private Squadra squadra;
	private String ruolo;
	private int numeroMaglia;
	
	public Calciatore(int numeroTessera, String nome, String cognome, Squadra squadra, String ruolo, int numeroMaglia) {
		super(numeroTessera, nome, cognome);
		this.squadra = squadra;
		this.ruolo = ruolo;
		this.numeroMaglia = numeroMaglia;
	}

	public Squadra getSquadra() {
		return squadra;
	}

	public String getRuolo() {
		return ruolo;
	}

	public int getNumeroMaglia() {
		return numeroMaglia;
	}
	
}

