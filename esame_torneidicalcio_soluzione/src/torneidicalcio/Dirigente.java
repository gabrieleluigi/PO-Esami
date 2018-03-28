package torneidicalcio;

public class Dirigente extends Tesserato {

	Squadra squadra;
	String ruolo;
	
	public Dirigente(int numeroTessera, String nome, String cognome, Squadra squadra, String ruolo) {
		super(numeroTessera, nome, cognome);
		
		this.squadra = squadra;
		this.ruolo=ruolo;
	}

	public Squadra getSquadra() {
		return squadra;
	}

	public String getRuolo() {
		return ruolo;
	}	
}
