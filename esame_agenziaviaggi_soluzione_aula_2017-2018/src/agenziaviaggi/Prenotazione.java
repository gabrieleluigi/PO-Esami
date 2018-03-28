package agenziaviaggi;

public class Prenotazione {
	private double importo;
	
	public Prenotazione(double importo) { //necessario per definire super in PrenotazioneVolo
		this.importo = importo;
	}

	public double getImporto(){
		return importo;
	}
}
